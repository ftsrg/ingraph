package ingraph.compiler.sql

import com.google.gson.Gson
import ingraph.compiler.test.CompilerTest
import ingraph.model.expr._
import ingraph.model.fplan._
import org.apache.spark.sql.catalyst.expressions.{BinaryOperator, Literal}
import org.apache.spark.sql.types.StringType
import IndentationPreservingStringInterpolation._

object CompileSql {
  def escapeQuotes(name: String, toBeDoubled: String = "\"") = name.replace(toBeDoubled, toBeDoubled + toBeDoubled)

  def escapeSingleQuotes(string: String) = escapeQuotes(string, "'")

  private def getSingleQuotedString(string: String): String =
    '\'' + escapeSingleQuotes(string) + '\''

  private def getQuotedColumnName(name: String): String =
    '"' + escapeQuotes(name) + '"'

  private def getQuotedColumnName(resolvableName: ResolvableName): String =
    getQuotedColumnName(resolvableName.resolvedName.get.resolvedName)

  private val gson = new Gson()
}

class CompileSql(query: String) extends CompilerTest {

  import CompileSql._

  def run(): String = {
    val stages = compile(query)

    val fplan = stages.fplan
    val sql = getSql(fplan)
    println(sql)
    sql
  }

  private def getSqlForProperty(requiredPropety: Any): String = {
    requiredPropety match {
      case prop: PropertyAttribute => {
        val propertyTableName =
          prop.elementAttribute match {
            case _: VertexAttribute => "vertex_property"
            case _: EdgeAttribute => "edge_property"
          }
        val parentColumnName = getQuotedColumnName(prop.elementAttribute)
        val propertyName = getSingleQuotedString(prop.name)
        val newPropertyColumnName = getQuotedColumnName(prop)

        s"""(SELECT value FROM $propertyTableName WHERE parent = $parentColumnName AND key = $propertyName) AS $newPropertyColumnName"""
      }
      case _ => ""
    }
  }

  private def getProjectionSql(node: UnaryFNode, renamePairs: Traversable[(String, String)]): String = {
    val columns = renamePairs.map(pair => s"""${pair._1} AS ${pair._2}""").mkString(", ")
    i"""SELECT $columns FROM
       |  (
       |    ${getSql(node.child)}
       |  ) subquery"""
  }

  private def getSql(node: Any): String = {
    val sqlString =
      node match {
        case node: GetVertices => {
          val columns =
            (
              s"""vertex_id AS ${getQuotedColumnName(node.nnode.v)}""" +:
                node.requiredProperties.map(prop =>s"""(SELECT value FROM vertex_property WHERE parent = vertex_id AND key = ${getSingleQuotedString(prop.name)}) AS ${getQuotedColumnName(prop)}"""))
              .mkString(",\n")

          i"""SELECT
             |  $columns
             |FROM vertex"""
        }
        case node: Join => {
          val joinAttributeNames = node.left.nnode.outputSet.intersect(node.right.nnode.outputSet)
            .map(attr => getQuotedColumnName(attr.asInstanceOf[ElementAttribute]))
          val usingPart = if (joinAttributeNames.isEmpty)
            "ON TRUE"
          else
            "USING (" + joinAttributeNames.mkString(", ") + ")"

          i"""SELECT * FROM
             |  (  ${getSql(node.left)}
             |  ) left_query
             |  INNER JOIN
             |  (  ${getSql(node.right)}
             |  ) right_query
             |$usingPart"""
        }
        case node: Production => {
          val renamePairs = node.output.zip(node.outputNames).map { case (attribute, outputName) => (getQuotedColumnName(attribute), getQuotedColumnName(outputName)) }
          getProjectionSql(node, renamePairs)
        }
        case node: Projection => {
          val renamePairs = node.nnode.projectList.map(proj => (getSql(proj.child), getQuotedColumnName(proj)))
          getProjectionSql(node, renamePairs)
        }
        case node: Selection =>
          val condition = getSql(node.nnode.condition)
          i"""SELECT * FROM
             |  (
             |    ${getSql(node.child)}
             |  ) subquery
             |WHERE $condition"""
        case node: GetEdges =>
          val columns = ("*" +:
            node.requiredProperties.map(getSqlForProperty)
            ).mkString(", ")

          val fromColumnName = getQuotedColumnName(node.nnode.src)
          val edgeColumnName = getQuotedColumnName(node.nnode.edge)
          val toColumnName = getQuotedColumnName(node.nnode.trg)

          val edgeLabelsEscaped = node.nnode.edge.labels.edgeLabels.map(name => getSingleQuotedString(name))
          val typeConstraintPart = if (edgeLabelsEscaped.isEmpty)
            ""
          else
            s"WHERE type IN (${edgeLabelsEscaped.mkString(", ")})"

          val undirectedSelectPart = if (node.nnode.directed)
            ""
          else
            s"""  UNION ALL
               |  SELECT "to", edge_id, "from" FROM edge
               |  $typeConstraintPart""".stripMargin

          i"""SELECT $columns FROM
             |  (
             |    SELECT "from" AS $fromColumnName, edge_id AS $edgeColumnName, "to" AS $toColumnName FROM edge
             |    $typeConstraintPart
             |  $undirectedSelectPart) subquery"""
        case node: AllDifferent => {
          val childSql = getSql(node.child)

          // TODO handle EdgeListAttribute too
          val edges = node.nnode.edges.collect { case attr: EdgeAttribute => attr }.toList
          if (edges.length <= 1)
            childSql
          else {
            val edgeNames = edges.map(getQuotedColumnName)
            val edgeConditions = edgeNames.flatMap(column1 => edgeNames.map((column1, _)))
              .filter(pair => pair._1 < pair._2)
              .map(pair => s"""${pair._1} <> ${pair._2}""")

            i"""SELECT * FROM
               |  (
               |    $childSql
               |  ) subquery
               |  WHERE ${edgeConditions.mkString("\n  AND ")}"""
          }
        }
        case node: Dual => "SELECT"
        case node: FNode => node.children.map(getSql).mkString("\n")
        case node: BinaryOperator => s"""(${getSql(node.left)} ${node.sqlOperator} ${getSql(node.right)})"""
        case node: ResolvableName => getQuotedColumnName(node)
        case node: Literal => {
          val pojoValue = node.dataType match {
            // https://github.com/apache/spark/blob/b3d88ac02940eff4c867d3acb79fe5ff9d724e83/sql/catalyst/src/main/scala/org/apache/spark/sql/catalyst/expressions/literals.scala#L59
            // String is stored in other type, needs conversion before JSON serialization
            case _: StringType => node.value.toString
            case _ => node.value
          }
          val jsonString = CompileSql.gson.toJson(pojoValue)
          val sqlStringLiteral = Literal(jsonString)

          sqlStringLiteral.sql
        }
        case _ => ""
      }

    if (node.isInstanceOf[FNode])
      s"""-- ${node.getClass.getSimpleName}
         |$sqlString""".stripMargin
    else
      sqlString
  }

  def getNodes(plan: FNode): Seq[FNode] = {
    if (plan.isInstanceOf[LeafFNode]) return plan :: Nil
    return plan.children.flatMap(x => getNodes(x)) :+ plan
  }
}
