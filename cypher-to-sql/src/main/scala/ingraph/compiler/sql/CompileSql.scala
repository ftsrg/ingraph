package ingraph.compiler.sql

import com.google.gson.Gson
import ingraph.compiler.test.CompilerTest
import ingraph.model.expr._
import ingraph.model.fplan._
import ingraph.model.nplan
import org.apache.spark.sql.catalyst.expressions.{BinaryOperator, Literal, Not}
import org.apache.spark.sql.types.StringType
import IndentationPreservingStringInterpolation._
import ingraph.model.misc.Function

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

  def getNodes(plan: FNode): Seq[FNode] = {
    if (plan.isInstanceOf[LeafFNode]) plan :: Nil
    else plan.children.flatMap(x => getNodes(x)) :+ plan
  }
}

class CompileSql(val cypherQuery: String, val parameters: Map[String, Any] = Map()) extends CompilerTest {

  import CompileSql._

  val stages = compile(cypherQuery)

  val fplan = stages.fplan
  val sql = getSql(fplan)

  def run(): String = {
    println(sql)
    sql
  }

  private def getSqlForProperty(requiredPropety: ResolvableName, nnode: nplan.GetEdges): String = {
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

          val labelConstraint = getVertexLabelSqlCondition(node.nnode.v.labels, "vertex_id")
            .map("WHERE " + _)
            .getOrElse("")

          i"""SELECT
             |  $columns
             |FROM vertex
             |$labelConstraint"""
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
        // EquiJoinLike nodes except TransitiveJoin
        case node: EquiJoinLike => {
          val leftColumns = node.left.flatSchema.map(getQuotedColumnName)
          val rightColumns = node.right.flatSchema.map(getQuotedColumnName)
          // prefer columns from  the left query, because in left outer join the right ones may contain NULL
          val resultColumns =
            (leftColumns.map("left_query." + _) ++
              (rightColumns.toSet -- leftColumns).map("right_query." + _))
              .mkString(", ")

          val columnConditions = node.flatCommon
            .map(getQuotedColumnName)
            .map(name => s"left_query.$name = right_query.$name")
          val joinConditions = node match {
            case node: ThetaLeftOuterJoin => columnConditions :+ getSql(node.nnode.condition)
            case _ => columnConditions
          }

          val joinConditionPart = if (joinConditions.isEmpty)
            "ON TRUE"
          else
            i"ON ${joinConditions.mkString(" AND\n")}"

          val joinType = node match {
            case _: Join => "INNER"
            case _: LeftOuterJoin => "LEFT OUTER"
            case _: ThetaLeftOuterJoin => "LEFT OUTER"
          }

          getJoinSql(node, joinType, joinConditionPart, resultColumns)
        }
        case node: AntiJoin => {
          val columnConditions = node.flatCommon
            .map(getQuotedColumnName)
            .map(name => s"left_query.$name = right_query.$name")

          val conditionPart = if (columnConditions.isEmpty)
            "TRUE"
          else
            columnConditions.mkString(" AND\n")

          i"""SELECT * FROM
             |  ( ${getSql(node.left)}
             |  ) left_query
             |WHERE NOT EXISTS(
             |  SELECT * FROM
             |    ( ${getSql(node.right)}
             |    ) right_query
             |  WHERE $conditionPart
             |  )"""
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
        case node: Grouping => {
          val renamePairs = node.flatSchema.map(proj => (getSql(proj), getQuotedColumnName(proj)))
          val projectionSql = getProjectionSql(node, renamePairs)
          val groupByColumns = (node.nnode.aggregationCriteria ++ node.requiredProperties).map(getSql)
          val groupByPart = if (groupByColumns.isEmpty)
            ""
          else
            "GROUP BY " + groupByColumns.mkString(", ")

          i"""$projectionSql
             |$groupByPart"""
        }
        case node: Dual => "SELECT"
        case node: ReturnItem => getSql(node.child)
        case FunctionInvocation(Function.NODE_HAS_LABELS, (vertexColumn: VertexAttribute) :: (vertexLabelSet: VertexLabelSet) :: Nil, false) => {
          getVertexLabelSqlCondition(vertexLabelSet, getQuotedColumnName(vertexColumn)).get
        }
        case node: FunctionInvocation => {
          val functionName = node.functor match {
            case Function.COLLECT => "array_agg"
            case functor => functor.getPrettyName
          }
          val parametersString = node.children.map(getSql).mkString(", ")
          val distinctPart = if (node.isDistinct) "DISTINCT " else ""

          functionName + "(" + distinctPart + parametersString + ")"
        }
        case node: FNode => node.children.map(getSql).mkString("\n")
        case node: BinaryOperator => {
          val leftSql = getSql(node.left)
          val rightSql = getSql(node.right)
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
          getSql(node.collection) + "->" + node.index.toString
        }
        case node: Literal => {
          val pojoValue = node.dataType match {
            // https://github.com/apache/spark/blob/b3d88ac02940eff4c867d3acb79fe5ff9d724e83/sql/catalyst/src/main/scala/org/apache/spark/sql/catalyst/expressions/literals.scala#L59
            // String is stored in other type, needs conversion before JSON serialization
            case _: StringType => node.value.toString
            case _ => node.value
          }

          getSqlForPojoValue(pojoValue)
        }
        case node: Not => "NOT(" + getSql(node.child) + ")"
        case node: Parameter => {
          val value = parameters(node.name)
          getSqlForPojoValue(value)
        }
        case _ => ""
      }

    if (node.isInstanceOf[FNode])
      s"""-- ${node.getClass.getSimpleName}
         |$sqlString""".stripMargin
    else
      sqlString
  }

  private def getSqlForPojoValue(pojoValue: Any): String = {
    val jsonString = CompileSql.gson.toJson(pojoValue)
    val sqlStringLiteral = Literal(jsonString)

    sqlStringLiteral.sql
  }

  private def getVertexLabelSqlCondition(labelSet: VertexLabelSet, columnName: String): Option[String] = {
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

  private def getJoinSql(node: EquiJoinLike, joinType: String, conditionPart: String, resultColumns: String) = {
    i"""SELECT $resultColumns FROM
       |  ( ${getSql(node.left)}
       |  ) left_query
       |  $joinType JOIN
       |  ( ${getSql(node.right)}
       |  ) right_query
       |$conditionPart"""
  }
}
