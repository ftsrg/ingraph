package ingraph.compiler.sql

import com.google.gson.Gson
import ingraph.compiler.test.CompilerTest
import ingraph.model.expr._
import ingraph.model.fplan._
import ingraph.model.nplan
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

  private def getGetEdgesSql(node: GetEdges, forcedFromColumnName: Option[String] = None, forcedEdgeColumnName: Option[String] = None, forcedToColumnName: Option[String] = None) = {
    def getColumnName(forcedColumnName: Option[String], originalAttributeProvider: nplan.GetEdges => ResolvableName): String = {
      forcedColumnName.map(getQuotedColumnName).getOrElse(getQuotedColumnName(originalAttributeProvider(node.nnode)))
    }

    val columns = ("*" +:
      node.requiredProperties.map(getSqlForProperty)
      ).mkString(", ")

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

    i"""SELECT $columns FROM
       |  (
       |    SELECT "from" AS $fromColumnName, edge_id AS $edgeColumnName, "to" AS $toColumnName FROM edge
       |    $typeConstraintPart
       |    $undirectedSelectPart
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

          val vertexLabelsAsRowValues = node.nnode.v.labels.vertexLabels.map("(" + getSingleQuotedString(_) + ")")
          val labelConstraint = if (vertexLabelsAsRowValues.isEmpty)
            ""
          else
            s"""WHERE NOT EXISTS(VALUES ${vertexLabelsAsRowValues.mkString(", ")}
               |                 EXCEPT ALL
               |                 SELECT name
               |                 FROM label
               |                 WHERE parent = vertex_id)""".stripMargin

          i"""SELECT
             |  $columns
             |FROM vertex
             |$labelConstraint"""
        }
        case node: Join => {
          val joinAttributeNames = node.flatCommon.map(getQuotedColumnName)
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
        case node: TransitiveJoin => {

          val leftNode = node.left
          val edgesNode = node.right

          val leftSql = getSql(leftNode)
          val edgesSql = getGetEdgesSql(edgesNode, Some("current_from"), Some("edge_id"))

          val edgeListAttribute = node.nnode.edgeList
          val edgeListName = getQuotedColumnName(edgesNode.nnode.edge)
          val edgesFromVertexName = getQuotedColumnName(edgesNode.nnode.src)
          val edgesToVertexName = getQuotedColumnName(edgesNode.nnode.trg)
          val leftNodeColumnNames = leftNode.flatSchema.map(getQuotedColumnName).mkString(", ")
          val joinNodeColumnNames = node.flatSchema.map(getQuotedColumnName).mkString(",\n")

          val lowerBound = edgeListAttribute.minHops.getOrElse(1)
          val lowerBoundConstraint =
            if (lowerBound != 0)
              s"WHERE array_length($edgeListName) >= $lowerBound"
            else
              ""

          val upperBoundConstraint =
            if (edgeListAttribute.maxHops.isDefined)
              s"AND array_length($edgeListName) < ${edgeListAttribute.maxHops.get}"
            else
              ""

          i"""WITH RECURSIVE recursive_table AS (
             |  (
             |    WITH left_query AS (
             |      $leftSql
             |    )
             |    SELECT
             |      *,
             |      ARRAY [] :: integer [] AS $edgeListName,
             |      $edgesFromVertexName AS next_from,
             |      $edgesFromVertexName AS $edgesToVertexName
             |    FROM left_query
             |  )
             |  UNION ALL
             |  SELECT
             |    $leftNodeColumnNames,
             |    ($edgeListName|| edge_id) AS $edgeListName,
             |    edges.$edgesToVertexName AS nextFrom,
             |    edges.$edgesToVertexName
             |  FROM
             |    ( $edgesSql
             |    ) edges
             |    INNER JOIN recursive_table
             |      ON edge_id <> ALL ($edgeListName) -- edge uniqueness
             |         AND next_from = current_from
             |         $upperBoundConstraint
             |)
             |SELECT
             |  $joinNodeColumnNames
             |FROM recursive_table
             |$lowerBoundConstraint"""
        }
        case node: Production => {
          val renamePairs = node.output.zip(node.outputNames).map { case (attribute, outputName) => (getQuotedColumnName(attribute), getQuotedColumnName(outputName)) }
          getProjectionSql(node, renamePairs)
        }
        case node: Projection => {
          val renamePairs = node.flatSchema.map(proj => (getSql(proj), getQuotedColumnName(proj)))
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
          getGetEdgesSql(node)
        case node: AllDifferent => {
          val childSql = getSql(node.child)
          val edgeIdsArray = ("ARRAY[]::INTEGER[]" +: node.nnode.edges.map(getQuotedColumnName)).mkString(" || ")
          // only more than 1 node must be checked for uniqueness (edge list can contain more edges)
          val allDifferentNeeded = node.nnode.edges.size > 1 || node.nnode.edges.exists(_.isInstanceOf[EdgeListAttribute])

          if (allDifferentNeeded)
            i"""SELECT * FROM
               |  (
               |    $childSql
               |  ) subquery
               |  WHERE is_unique($edgeIdsArray)"""
          else
            s"""   -- noop
               |$childSql""".stripMargin
        }
        case node: SortAndTop => {
          val childSql = getSql(node.child)
          val nnode = node.nnode

          val orderByParts = nnode.order.map(order => getSql(order.child) + " " + order.direction.sql + " " + order.nullOrdering.sql)
          val limitPart = nnode.limitExpr.map { case Literal(num: Long, _) => s"LIMIT $num" }.getOrElse("")
          val offsetPart = nnode.skipExpr.map { case Literal(num: Long, _) => s"OFFSET $num" }.getOrElse("")

          i"""SELECT * FROM
             |  (
             |    $childSql
             |  ) subquery
             |  ORDER BY ${orderByParts.mkString(", ")}
             |  $limitPart
             |  $offsetPart"""
        }
        case node: DuplicateElimination => {
          val childSql = getSql(node.child)

          i"""SELECT DISTINCT * FROM
             |  (
             |    $childSql
             |  ) subquery"""
        }
        case node: Dual => "SELECT"
        case node: ReturnItem => getSql(node.child)
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
