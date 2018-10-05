package ingraph.compiler.sql

import ingraph.compiler.sql.CompileSql.getSql
import ingraph.compiler.sql.IndentationPreservingStringInterpolation._
import ingraph.compiler.sql.driver.ValueJsonConversion
import ingraph.compiler.test.CompilerTest
import ingraph.model.expr._
import ingraph.model.misc.Function
import ingraph.model.{fplan, nplan}
import org.apache.spark.sql.catalyst.expressions.{BinaryComparison, BinaryOperator, CaseWhen, Expression, IsNotNull, IsNull, Literal, Not}
import org.apache.spark.sql.types.{LongType, StringType}
import org.cytosm.common.gtop.GTop
import org.neo4j.driver.v1.{Value, Values}

import scala.collection.JavaConverters._

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
        //  here is a workaround
        val parentColumnName = getQuotedColumnName(nnode.output.find(_.name == prop.elementAttribute.name).get)
        val propertyName = getSingleQuotedString(prop.name)
        val newPropertyColumnName = getQuotedColumnName(prop)

        s"""(SELECT value FROM $propertyTableName WHERE parent = $parentColumnName AND key = $propertyName) AS $newPropertyColumnName"""
      }
      case _ => ""
    }
  }

  def getProjectionSql(renamePairs: Traversable[(String, String)], childSql: String, options: CompilerOptions): String = {
    val columns = renamePairs.map(pair => s"""${pair._1} AS ${pair._2}""").mkString(", ")
    i"""SELECT $columns
       |  FROM $childSql AS subquery"""
  }

  def getGetEdgesSql(node: fplan.GetEdges, options: CompilerOptions, forcedFromColumnName: Option[String] = None, forcedEdgeColumnName: Option[String] = None, forcedToColumnName: Option[String] = None) = {
    assert(node.nnode.directed, "Cannot compile undirected GetEdges. Use UnionAll instead.")

    def getColumnName(forcedColumnName: Option[String], originalAttributeProvider: nplan.GetEdges => ResolvableName): String = {
      forcedColumnName.map(getQuotedColumnName).getOrElse(getQuotedColumnName(originalAttributeProvider(node.nnode)))
    }

    val gTop: Option[GTop] = options.gTop

    val edgeLabels = node.nnode.edge.labels.edgeLabels

    val implNode = gTop.flatMap(_.getImplementationLevel
      .getImplementationEdges.asScala
      .find(_.getTypes.asScala.toSet.intersect(edgeLabels).nonEmpty))

    val traversalHop = implNode.map(_.getPaths.get(0).getTraversalHops.asScala.head)
    val sourceNode = traversalHop.flatMap(hop => gTop.get.getImplementationLevel.getImplementationNodes.asScala.find(_.getTableName == hop.getSourceTableName))
    val destinationNode = traversalHop.flatMap(hop => gTop.get.getImplementationLevel.getImplementationNodes.asScala.find(_.getTableName == hop.getDestinationTableName))

    val edgeTable = traversalHop.map(_.getJoinTableName).map(getQuotedColumnName).getOrElse("edge")
    val fromColumn = traversalHop.map(_.getJoinTableSourceColumn).map(getQuotedColumnName).getOrElse("\"from\"")
    val toColumn = traversalHop.map(_.getJoinTableDestinationColumn).map(getQuotedColumnName).getOrElse("\"to\"")
    val edgeColumn = traversalHop.map(_ => s"ROW($fromColumn, $toColumn)").getOrElse("edge_id")

    val fromColumnNewName = getColumnName(forcedFromColumnName, _.src)
    val edgeColumnNewName = getColumnName(forcedEdgeColumnName, _.edge)
    val toColumnNewName = getColumnName(forcedToColumnName, _.trg)

    // TODO handle properties from edge lists

    val propertiesWithTables =
      if (gTop.isEmpty) None
      else Some(node.requiredProperties.map {
        case prop@PropertyAttribute(propName, element, _) => {
          val (vertexTable, tableName, attributes) = element match {
            case vertex: VertexAttribute if vertex.resolvedName == node.nnode.src.resolvedName =>
              (sourceNode, sourceNode.get.getTableName, sourceNode.get.getAttributes)
            case vertex: VertexAttribute if vertex.resolvedName == node.nnode.trg.resolvedName =>
              (destinationNode, destinationNode.get.getTableName, destinationNode.get.getAttributes)
            case _: EdgeAttribute =>
              (None, traversalHop.get.getJoinTableName, traversalHop.get.getAttributes)
          }
          val columnName = attributes.asScala.find(_.getAbstractionLevelName == propName).get.getColumnName
          val newName = getQuotedColumnName(prop)

          (vertexTable, tableName, columnName, newName)
        }
      })

    val extraColumns =
      propertiesWithTables
        .map(_.map { case (_, tableName, columnName, newName) =>
          getQuotedColumnName(tableName) + '.' + getQuotedColumnName(columnName) + " AS " + newName
        })
        .getOrElse(node.requiredProperties.map(getSqlForProperty(_, node.nnode)))
    val extraColumnsPart = extraColumns.mkString(", ")
    val commaBeforeExtraColumns =
      if (extraColumns.nonEmpty) ","
      else ""

    val extraTablesToJoin = propertiesWithTables
      .map(_.flatMap { case (vertexTable, _, _, _) => vertexTable }.toSet)
      .getOrElse(Set())
    val joinVertexTablesPart =
      extraTablesToJoin.map { vertexTable =>
        val sourceTableName = getQuotedColumnName(traversalHop.get.getSourceTableName)
        val joinSourceColumn = getQuotedColumnName(traversalHop.get.getJoinTableSourceColumn)
        val sourceColumn = getQuotedColumnName(traversalHop.get.getSourceTableColumn)

        val destinationTableName = getQuotedColumnName(traversalHop.get.getDestinationTableName)
        val destinationColumn = getQuotedColumnName(traversalHop.get.getDestinationTableColumn)
        val joinDestinationColumn = getQuotedColumnName(traversalHop.get.getJoinTableDestinationColumn)

        if (vertexTable == sourceNode.get)
          s"JOIN $sourceTableName ON ($edgeTable.$joinSourceColumn = $sourceTableName.$sourceColumn)"
        else if (vertexTable == destinationNode.get)
          s"JOIN $destinationTableName ON ($edgeTable.$joinDestinationColumn = $destinationTableName.$destinationColumn)"
        else
          throw new NoSuchElementException("Unexpected table");
      }
        .mkString("\n")

    val edgeLabelsEscaped = edgeLabels.map(name => getSingleQuotedString(name))
    val typeConstraint = if (edgeLabelsEscaped.isEmpty || gTop.isDefined)
      None
    else
      Some(s"type IN (${edgeLabelsEscaped.mkString(", ")})")

    val vertexLabelConstraints =
      if (gTop.nonEmpty)
        Seq()
      else
        Map(fromColumnNewName -> node.src, toColumnNewName -> node.trg)
          .mapValues(_.labels)
          .flatMap { case (columnName, labelSet) => getVertexLabelSqlCondition(labelSet, columnName) }
          .toSeq

    val constraints = vertexLabelConstraints ++ typeConstraint
    val constraintPart = getWhereClause(constraints)

    if (gTop.isDefined)
      i"""SELECT $fromColumn AS $fromColumnNewName, $edgeColumn AS $edgeColumnNewName, $toColumn AS $toColumnNewName$commaBeforeExtraColumns
         |  $extraColumnsPart
         |  FROM $edgeTable
         |    $joinVertexTablesPart
         |$constraintPart"""
    else
      i"""SELECT $fromColumnNewName, $edgeColumnNewName, $toColumnNewName$commaBeforeExtraColumns $extraColumnsPart FROM
         |  (
         |    SELECT "from" AS $fromColumnNewName, edge_id AS $edgeColumnNewName, "to" AS $toColumnNewName FROM edge
         |    ${getWhereClause(typeConstraint)}
         |  ) subquery
         |${getWhereClause(vertexLabelConstraints)}"""
  }

  def getWhereClause(constraints: Iterable[String]): String = {
    if (constraints.isEmpty) ""
    else i"WHERE ${constraints.mkString(" AND\n")}"
  }

  def getSql(node: Any, options: CompilerOptions): String = {
    val sqlString =
      node match {
        case IsNull(child) => IsNull(ExpressionWrapper(child, options)).sql
        case IsNotNull(child) => IsNotNull(ExpressionWrapper(child, options)).sql
        case ListExpression(list) => "ARRAY[" + list.map(getSql(_, options)).mkString(", ") + "]"
        case CaseWhen(branches, elseValue) => {
          val branchesPart = branches
            .map { case (c, v) => getSql(c, options) -> getSql(v, options) }
            .map { case (condition, value) => s"WHEN $condition THEN $value" }
            .mkString("\n")
          val elsePart = elseValue
            .map(getSql(_, options))
            .map("ELSE " + _)
            .getOrElse("")

          i"""CASE $branchesPart
             |     $elsePart
             |END"""
        }
        case node: ReturnItem => getSql(node.child, options)
        case FunctionInvocation(Function.LENGTH, Seq(edgeListAttribute: EdgeListAttribute), false) =>
          "array_length(" + getSql(edgeListAttribute, options) + ")"
        case FunctionInvocation(Function.NODE_HAS_LABELS, (vertexColumn: VertexAttribute) :: (vertexLabelSet: VertexLabelSet) :: Nil, false) => {
          getVertexLabelSqlCondition(vertexLabelSet, getQuotedColumnName(vertexColumn)).get
        }
        case FunctionInvocation(Function.EXISTS, Seq(propertyAttribute: PropertyAttribute), false) => {
          getSql(propertyAttribute, options) + " IS NOT NULL"
        }
        case FunctionInvocation(Function.TOINTEGER | Function.TOINT, Seq(expr: Expression), false) => {
          "(" + getSql(expr, options.copy(unwrapJson = true)) + ")::INTEGER"
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

          val leftSql = getSql(node.left, options.copy(unwrapJson = localUnwrapJson))
          val rightSql = getSql(node.right, options.copy(unwrapJson = localUnwrapJson))
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
        case node: ResolvableName => {
          val unwrapPart =
            if (options.unwrapJson && node.isInstanceOf[PropertyAttribute])
              "->'value'->>'val'"
            else
              ""
          getQuotedColumnName(node) + unwrapPart
        }
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

      sqlStringLiteral.sql + "::jsonb"
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

class CompileSql(val cypherQuery: String, val parameters: Map[String, Any] = Map(), gTop: Option[GTop] = None) extends CompilerTest {

  val stages = compile(cypherQuery)

  val fplan = stages.fplan

  // TODO don't execute it more than once
  def sqlNode = SqlNode(fplan, CompilerOptions(parameters, gTop))._1

  def sql = sqlNode.sql.get

  def run(): String = {
    println(sql)
    sql
  }

}

case class CompilerOptions(parameters: Map[String, Any] = Map(), gTop: Option[GTop] = None, nodeId: Int = 0, unwrapJson: Boolean = false) {}

case class ExpressionWrapper(expr: Expression, options: CompilerOptions) extends ExpressionBase {
  override def children: Seq[Expression] = expr.children

  override def productElement(n: Int): Any = expr.productElement(n)

  override def productArity: Int = expr.productArity

  override def canEqual(that: Any): Boolean = expr.canEqual(that)

  override def sql: String = getSql(expr, options)
}
