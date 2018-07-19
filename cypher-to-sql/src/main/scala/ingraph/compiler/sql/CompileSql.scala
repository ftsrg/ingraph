package ingraph.compiler.sql

import com.google.gson.Gson
import ingraph.compiler.test.CompilerTest
import ingraph.model.expr._
import ingraph.model.fplan._
import org.apache.spark.sql.catalyst.expressions.{BinaryOperator, Literal}
import org.apache.spark.sql.types.StringType

object CompileSql {
  def escapeQuotes(name: String, toBeDoubled: String = "\"") = name.replace(toBeDoubled, toBeDoubled + toBeDoubled)

  def escapeSingleQuotes(string: String) = escapeQuotes(string, "'")

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
        val parentColumnName = escapeQuotes(prop.elementAttribute.resolvedName.get.resolvedName)
        val propertyName = escapeSingleQuotes(prop.name)
        val newPropertyColumnName = escapeQuotes(prop.resolvedName.get.resolvedName)

        s"""(SELECT value FROM $propertyTableName WHERE parent = "$parentColumnName" AND key = '$propertyName') AS "$newPropertyColumnName""""
      }
      case _ => ""
    }
  }

  private def getProjectionSql(node: UnaryFNode, renamePairs: Traversable[(String, String)]): String = {
    val columns = renamePairs.map(pair => s"""${pair._1} AS ${pair._2}""").mkString(", ")
    s"""SELECT $columns FROM
       |  (
       |    ${getSql(node.child)}
       |  ) subquery""".stripMargin
  }

  private def getSql(node: Any): String = {
    node match {
      case node: GetVertices => {
        val columns =
          (
            s"""vertex_id AS "${escapeQuotes(node.nnode.v.resolvedName.get.resolvedName)}"""" +:
              node.requiredProperties.map(prop =>s"""(SELECT value FROM vertex_property WHERE parent = vertex_id AND key = '${escapeSingleQuotes(prop.name)}') AS "${escapeQuotes(prop.resolvedName.get.resolvedName)}""""))
            .mkString(", ")

        s"""SELECT $columns FROM vertex"""
      }
      case node: Join => {
        val joinAttributeNames = node.left.nnode.outputSet.intersect(node.right.nnode.outputSet)
          .map(attr => attr.asInstanceOf[ElementAttribute].resolvedName.get.resolvedName)
          .map(name => s""""${escapeQuotes(name)}"""")
        val usingPart = if (joinAttributeNames.isEmpty)
          ""
        else
          "\nUSING (" + joinAttributeNames.mkString(", ") + ")"

        s"""SELECT * FROM
           |  (${getSql(node.left)}) left_query
           |  INNER JOIN
           |  (${getSql(node.right)}) right_query$usingPart""".stripMargin
      }
      case node: Production => {
        val renamePairs = node.output.zip(node.outputNames).map(pair => (getSql(pair._1), '"' + escapeQuotes(pair._2) + '"'))
        getProjectionSql(node, renamePairs)
      }
      case node: Projection => {
        val renamePairs = node.nnode.projectList.map(proj => (getSql(proj.child), getSql(proj)))
        getProjectionSql(node, renamePairs)
      }
      case node: Selection =>
        val condition = getSql(node.nnode.condition)
        s"""SELECT * FROM
           |  (
           |    ${getSql(node.child)}
           |  ) subquery
           |WHERE $condition""".stripMargin
      case node: GetEdges =>
        val columns = ("*" +:
          node.requiredProperties.map(getSqlForProperty)
          ).mkString(", ")

        val fromColumnName = escapeQuotes(node.nnode.src.resolvedName.get.resolvedName)
        val edgeColumnName = escapeQuotes(node.nnode.edge.resolvedName.get.resolvedName)
        val toColumnName = escapeQuotes(node.nnode.trg.resolvedName.get.resolvedName)

        val edgeLabelsEscaped = node.nnode.edge.labels.edgeLabels.map(name =>s"""'${escapeSingleQuotes(name)}'""")
        val typeConstraintPart = if (edgeLabelsEscaped.isEmpty)
          ""
        else
          s"""  WHERE type IN (${edgeLabelsEscaped.mkString(", ")})
             |""".stripMargin

        val undirectedSelectPart = if (node.nnode.directed)
          ""
        else
          s"""  UNION ALL
             |  SELECT "to", edge_id, "from" FROM edge
             |$typeConstraintPart""".stripMargin

        s"""SELECT $columns FROM
           |  (
           |    SELECT "from" AS "$fromColumnName", edge_id AS "$edgeColumnName", "to" AS "$toColumnName" FROM edge
           |  $typeConstraintPart$undirectedSelectPart) subquery""".stripMargin
      case node: AllDifferent => {
        val childSql = getSql(node.child)

        val edges = node.nnode.outputSet.collect { case attr: EdgeAttribute => attr }.toList
        if (edges.length <= 1)
          childSql
        else {
          val edgeNames = edges.map(_.resolvedName.get.resolvedName)
            .map(name => s""""${escapeQuotes(name)}"""")
          val edgeConditions = edgeNames.flatMap(column1 => edgeNames.map((column1, _)))
            .filter(pair => pair._1 < pair._2)
            .map(pair => s"""${pair._1} <> ${pair._2}""")

          s"""SELECT * FROM
             |  (
             |    $childSql
             |  ) subquery
             |  WHERE ${edgeConditions.mkString("\n  AND ")}""".stripMargin
        }
      }
      case node: FNode => node.children.map(getSql).mkString("\n")
      case node: BinaryOperator => s"""(${getSql(node.left)} ${node.sqlOperator} ${getSql(node.right)})"""
      case node: ResolvableName => '"' + escapeQuotes(node.resolvedName.get.resolvedName) + '"'
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
  }


  def getNodes(plan: FNode): Seq[FNode] = {
    if (plan.isInstanceOf[LeafFNode]) return plan :: Nil
    return plan.children.flatMap(x => getNodes(x)) :+ plan
  }
}
