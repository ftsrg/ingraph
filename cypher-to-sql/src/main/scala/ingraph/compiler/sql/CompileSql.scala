package ingraph.compiler.sql

import ingraph.compiler.sql.IndentationPreservingStringInterpolation._
import ingraph.compiler.sql.driver.ValueJsonConversion
import ingraph.compiler.test.CompilerTest
import ingraph.model.expr._
import ingraph.model.misc.Function
import ingraph.model.{fplan, nplan}
import org.apache.spark.sql.catalyst.expressions.{BinaryComparison, BinaryOperator, Literal, Not}
import org.apache.spark.sql.types.{LongType, StringType}
import org.neo4j.driver.v1.{Value, Values}

object CompileSql {
  def escapeQuotes(name: String, toBeDoubled: String = "\"") = name.replace(toBeDoubled, toBeDoubled + toBeDoubled)

  def escapeSingleQuotes(string: String) = escapeQuotes(string, "'")

  def getSingleQuotedString(string: String): String =
    '\'' + escapeSingleQuotes(string) + '\''

  def getQuotedColumnName(name: String): String =
    '"' + escapeQuotes(name) + '"'

  def getQuotedColumnName(resolvableName: ResolvableName): String =
    getQuotedColumnName(resolvableName.resolvedName.get.resolvedName)

  def getNodes(plan: fplan.FNode): Seq[fplan.FNode] = {
    if (plan.isInstanceOf[fplan.LeafFNode]) plan :: Nil
    else plan.children.flatMap(x => getNodes(x)) :+ plan
  }

  def getSqlForProperty(requiredPropety: ResolvableName, nnode: nplan.GetEdges): String = {
    requiredPropety match {
      case prop: PropertyAttribute => {
        val propertyTableName =
          prop.elementAttribute match {
            case _: VertexAttribute => "vertex_property"
            case _: EdgeAttribute => "edge_property"
          }
        // prop.elementAttribute.resolvedName can be invalid in this context because of renaming,
        //  since it has the name of the column as it is in the outermost part of the query.
        val parentColumnName = getQuotedColumnName(nnode.output.find(_.name == prop.elementAttribute.name).get)
        val propertyName = getSingleQuotedString(prop.name)
        val newPropertyColumnName = getQuotedColumnName(prop)

        s"""(SELECT value FROM $propertyTableName WHERE parent = $parentColumnName AND key = $propertyName) AS $newPropertyColumnName"""
      }
      case _ => ""
    }
  }

  def getProjectionSql(node: fplan.UnaryFNode, renamePairs: Traversable[(String, String)], childSql: String, options: CompilerOptions): String = {
    val columns = renamePairs.map(pair => s"""${pair._1} AS ${pair._2}""").mkString(", ")
    i"""SELECT $columns FROM
       |  (
       |    $childSql
       |  ) subquery"""
  }

  def getGetEdgesSql(node: fplan.GetEdges, forcedFromColumnName: Option[String] = None, forcedEdgeColumnName: Option[String] = None, forcedToColumnName: Option[String] = None) = {
    def getColumnName(forcedColumnName: Option[String], originalAttributeProvider: nplan.GetEdges => ResolvableName): String = {
      forcedColumnName.map(getQuotedColumnName).getOrElse(getQuotedColumnName(originalAttributeProvider(node.nnode)))
    }

    val columns = ("*" +:
      node.requiredProperties.map(getSqlForProperty(_, node.nnode))
      ).mkString(", ")

    // TODO handle properties from edge lists

    val fromColumnName = getColumnName(forcedFromColumnName, _.src)
    val edgeColumnName = getColumnName(forcedEdgeColumnName, _.edge)
    val toColumnName = getColumnName(forcedToColumnName, _.trg)

    val edgeLabelsEscaped = node.nnode.edge.labels.edgeLabels.map(name => getSingleQuotedString(name))
    val typeConstraintPart = if (edgeLabelsEscaped.isEmpty)
      ""
    else
      s"WHERE type IN (${edgeLabelsEscaped.mkString(", ")})"

    val undirectedSelectPart = if (node.nnode.directed)
      ""
    else
      i"""UNION ALL
         |SELECT "to", edge_id, "from" FROM edge
         |$typeConstraintPart"""

    val vertexLabelConstraints = Map(fromColumnName -> node.src, toColumnName -> node.trg)
      .mapValues(_.labels)
      .flatMap { case (columnName, labelSet) => getVertexLabelSqlCondition(labelSet, columnName) }
      .toSeq
    val vertexLabelConstraintPart =
      if (vertexLabelConstraints.isEmpty) ""
      else i"WHERE ${vertexLabelConstraints.mkString(" AND\n")}"

    i"""SELECT $columns FROM
       |  (
       |    SELECT "from" AS $fromColumnName, edge_id AS $edgeColumnName, "to" AS $toColumnName FROM edge
       |    $typeConstraintPart
       |    $undirectedSelectPart
       |  ) subquery
       |$vertexLabelConstraintPart"""
  }

  def getSql(node: Any, options: CompilerOptions): String = {
    val sqlString =
      node match {
        case node: ReturnItem => getSql(node.child, options)
        case FunctionInvocation(Function.NODE_HAS_LABELS, (vertexColumn: VertexAttribute) :: (vertexLabelSet: VertexLabelSet) :: Nil, false) => {
          getVertexLabelSqlCondition(vertexLabelSet, getQuotedColumnName(vertexColumn)).get
        }
        case FunctionInvocation(functor@Function.COUNT_ALL, Nil, false) => {
          functor.getPrettyName
        }
        case node: FunctionInvocation => {
          val functionName = node.functor match {
            case Function.COLLECT => "array_agg"
            // ignore ID function since the column already contains the ID
            case functor@Function.ID => "/*" + functor.getPrettyName + "*/"
            case functor => functor.getPrettyName
          }
          val parametersString = node.children.map(getSql(_, options)).mkString(", ")
          val distinctPart = if (node.isDistinct) "DISTINCT " else ""

          functionName + "(" + distinctPart + parametersString + ")"
        }
        case node: BinaryOperator => {
          // convert Literals to JSON only if they are compared to JSON results
          val localUnwrapJson = node.isInstanceOf[BinaryComparison] &&
            node.children.forall {
              case _: PropertyAttribute => false
              case FunctionInvocation(Function.TYPE, _, _) => false
              case _ => true
            }

          val leftSql = getSql(node.left, CompilerOptions(localUnwrapJson, options.parameters))
          val rightSql = getSql(node.right, CompilerOptions(localUnwrapJson, options.parameters))
          val operator = node.sqlOperator

          if (Seq(leftSql, rightSql).exists(_.contains("\n")))
            i"""(
               |  $leftSql
               |$operator
               |  $rightSql
               |)"""
          else
            s"($leftSql $operator $rightSql)"
        }
        case node: ResolvableName => getQuotedColumnName(node)
        case node: IndexLookupExpression => {
          // TODO support indexing of SQL arrays too
          // JSON indexing
          // {"type": "ListValue", "value": {"values": [{"type": "StringValue", "value": {"val": "a"}}, {"type": "StringValue", "value": {"val": "b"}}]}}
          getSql(node.collection, options) + "->'value'->'values'->" + node.index.toString
        }
        case node: Literal => getSqlForLiteral(node, options)
        case node: Not => "NOT(" + getSql(node.child, options) + ")"
        case node: Parameter => {
          val value = options.parameters(node.name)
          getSqlForLiteral(Literal(value), options)
        }
      }

    sqlString
  }

  def convertAttributeAtProductionNode(attribute: ResolvableName): String = {
    val columnName = getQuotedColumnName(attribute)

    attribute match {
      case ReturnItem(VertexAttribute(_, _, _, _, _), _, _) => "to_vertex(" + columnName + ")"
      case ReturnItem(EdgeAttribute(_, _, _, _, _), _, _) => "to_edge(" + columnName + ")"
      case _ => columnName
    }
  }

  def getSqlForLiteral(literal: Literal, options: CompilerOptions): String = {
    if (options.unwrapJson) {
      val sqlString = literal.dataType match {
        // SQL database cannot parse literal suffix (e.g. 42L)
        case _: LongType => literal.value.toString
        case _ => literal.sql
      }

      sqlString
    }
    else {
      val pojoValue = literal.dataType match {
        // https://github.com/apache/spark/blob/b3d88ac02940eff4c867d3acb79fe5ff9d724e83/sql/catalyst/src/main/scala/org/apache/spark/sql/catalyst/expressions/literals.scala#L59
        // String is stored in other type, needs conversion before JSON serialization
        case _: StringType => literal.value.toString
        case _ => literal.value
      }

      val value = Values.value(pojoValue)
      val jsonString = ValueJsonConversion.gson.toJson(value, classOf[Value])
      val sqlStringLiteral = Literal(jsonString)

      sqlStringLiteral.sql
    }
  }

  def getVertexLabelSqlCondition(labelSet: VertexLabelSet, columnName: String): Option[String] = {
    val labelsAsRowValues = labelSet.vertexLabels.map("(" + getSingleQuotedString(_) + ")")

    if (labelsAsRowValues.isEmpty)
      None
    else
      Some(
        s"""NOT EXISTS(VALUES ${labelsAsRowValues.mkString(", ")}
           |           EXCEPT ALL
           |           SELECT name
           |           FROM label
           |           WHERE parent = $columnName)""".stripMargin)
  }
}

class CompileSql(val cypherQuery: String, val parameters: Map[String, Any] = Map()) extends CompilerTest {

  val stages = compile(cypherQuery)

  val fplan = stages.fplan
  val (sqlNode, _) = SqlNode(fplan, CompilerOptions())
  val sql = sqlNode.sql

  def run(): String = {
    println(sql)
    sql
  }

}

case class CompilerOptions(unwrapJson: Boolean = false, parameters: Map[String, Any] = Map()) {}
