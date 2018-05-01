package ingraph.compiler.sql

import ingraph.compiler.test.CompilerTest
import ingraph.model.expr.{EdgeAttribute, ElementAttribute, PropertyAttribute, VertexAttribute}
import ingraph.model.fplan._
import org.apache.spark.sql.catalyst.expressions.{EqualTo, Literal}
import org.apache.spark.sql.types.StringType

object CompileSql {
  def escapeQuotes(name: String, toBeDoubled: String = "\"") = name.replace(toBeDoubled, toBeDoubled + toBeDoubled)

  def escapeSingleQuotes(string: String) = escapeQuotes(string, "'")
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

  private def getSql(node: Any): String = {
    node match {
      case node: GetVertices => {
        val columns =
          (
            s"""vertex_id AS "${escapeQuotes(node.jnode.v.resolvedName.get.resolvedName)}"""" +:
              node.requiredProperties.map(prop =>s"""(SELECT value FROM vertex_property WHERE parent = vertex_id AND key = '${escapeSingleQuotes(prop.name)}') AS "${escapeQuotes(prop.resolvedName.get.resolvedName)}""""))
            .mkString(", ")

        s"""SELECT $columns FROM vertex"""
      }
      case node: Join => {
        val joinAttributesConditions = node.left.jnode.outputSet.intersect(node.right.jnode.outputSet)
          .map(attr => attr.asInstanceOf[ElementAttribute].resolvedName.get.resolvedName)
          .map(name => s""""${escapeQuotes(name)}"""")
          .map(escapedName => s"left_query.$escapedName = right_query.$escapedName")
        val wherePart = if (joinAttributesConditions.isEmpty)
          ""
        else
          "\nWHERE " + joinAttributesConditions.mkString(" AND ")

        s"""SELECT * FROM
           |  (${getSql(node.left)}) left_query
           |  ,
           |  (${getSql(node.right)}) right_query$wherePart""".stripMargin
      }
      case node: Projection => {
        val columns = node.jnode.projectList.map(proj => s"""${getSql(proj.child)} AS "${escapeQuotes(proj.resolvedName.get.resolvedName)}"""").mkString(", ")
        s"""SELECT $columns FROM
           |  (
           |    ${getSql(node.child)}
           |  )""".stripMargin
      }
      case node: Selection =>
        val condition = getSql(node.jnode.condition)
        s"""SELECT * FROM
           |  (
           |    ${getSql(node.child)}
           |  )
           |WHERE $condition""".stripMargin
      case node: GetEdges =>
        val columns = ("*" +:
          node.requiredProperties.map(getSqlForProperty)
          ).mkString(", ")

        val fromColumnName = escapeQuotes(node.jnode.src.resolvedName.get.resolvedName)
        val edgeColumnName = escapeQuotes(node.jnode.edge.resolvedName.get.resolvedName)
        val toColumnName = escapeQuotes(node.jnode.trg.resolvedName.get.resolvedName)

        val edgeLabelsEscaped = node.jnode.edge.labels.edgeLabels.map(name =>s"""'${escapeSingleQuotes(name)}'""")
        val typeConstraintPart = if (edgeLabelsEscaped.isEmpty)
          ""
        else
          s"""  WHERE type IN (${edgeLabelsEscaped.mkString(", ")})
             |""".stripMargin

        val undirectedSelectPart = if (node.jnode.directed)
          ""
        else
          s"""  UNION ALL
             |  SELECT "to", edge_id, "from" FROM edge
             |$typeConstraintPart""".stripMargin

        s"""SELECT $columns FROM
           |  (
           |    SELECT "from" AS "$fromColumnName", edge_id AS "$edgeColumnName", "to" AS "$toColumnName" FROM edge
           |  $typeConstraintPart$undirectedSelectPart)""".stripMargin
      case node: AllDifferent => {
        val childSql = getSql(node.child)

        val edges = node.jnode.outputSet.collect { case attr: EdgeAttribute => attr }.toList
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
             |  )
             |  WHERE ${edgeConditions.mkString("\n  AND ")}""".stripMargin
        }
      }
      case node: FNode => node.children.map(getSql).mkString("\n")
      case node: VertexAttribute => '"' + escapeQuotes(node.resolvedName.get.resolvedName) + '"'
      case node: PropertyAttribute => '"' + escapeQuotes(node.resolvedName.get.resolvedName) + '"'
      case node: Literal if node.dataType.isInstanceOf[StringType] => '\'' + escapeSingleQuotes(node.value.toString) + '\''
      case node: EqualTo => s"${getSql(node.left)} = ${getSql(node.right)}"
      case _ => ""
    }
  }


  def getNodes(plan: FNode): Seq[FNode] = {
    if (plan.isInstanceOf[LeafFNode]) return plan :: Nil
    return plan.children.flatMap(x => getNodes(x)) :+ plan
  }
}
