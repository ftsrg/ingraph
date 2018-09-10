package ingraph.compiler.sql

import ingraph.compiler.sql.IndentationPreservingStringInterpolation._
import ingraph.compiler.sql.driver.ValueJsonConversion
import ingraph.compiler.test.CompilerTest
import ingraph.model.expr._
import ingraph.model.fplan
import ingraph.model.misc.Function
import ingraph.model.nplan
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

  def getProjectionSql(node: fplan.UnaryFNode, renamePairs: Traversable[(String, String)], options: CompilerOptions): String = {
    val columns = renamePairs.map(pair => s"""${pair._1} AS ${pair._2}""").mkString(", ")
    i"""SELECT $columns FROM
       |  (
       |    ${getSql(node.child, options)}
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
        case node: fplan.GetVertices => {
          val columns =
            (
              s"""vertex_id AS ${getQuotedColumnName(node.nnode.v)}""" +:
                node.requiredProperties
                  // skip NodeHasLabelsAttribute since it has no resolvedName to use as column name
                  // TODO use NodeHasLabelsAttribute
                  .filterNot(_.isInstanceOf[NodeHasLabelsAttribute])
                  .map(prop =>s"""(SELECT value FROM vertex_property WHERE parent = vertex_id AND key = ${getSingleQuotedString(prop.name)}) AS ${getQuotedColumnName(prop)}"""))
              .mkString(",\n")

          val labelConstraint = getVertexLabelSqlCondition(node.nnode.v.labels, "vertex_id")
            .map("WHERE " + _)
            .getOrElse("")

          i"""SELECT
             |  $columns
             |FROM vertex
             |$labelConstraint"""
        }
        case node: fplan.TransitiveJoin => {
          val leftNode = node.left
          val edgesNode = node.right

          val leftSql = getSql(leftNode, options)
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
        case node: fplan.EquiJoinLike => {
          val leftColumns = node.left.flatSchema.map(getQuotedColumnName)
          val rightColumns = node.right.flatSchema.map(getQuotedColumnName)
          // prefer columns from  the left query, because in left outer join the right ones may contain NULL
          val resultColumns =
            (leftColumns.map("left_query." + _) ++
              (rightColumns.toSet -- leftColumns).map("right_query." + _))
              .mkString(", ")

          val columnConditions = node.commonAttributes
            .map(getQuotedColumnName)
            .map(name => s"left_query.$name = right_query.$name")
          val joinConditions = node match {
            case node: fplan.ThetaLeftOuterJoin => columnConditions :+ getSql(node.nnode.condition, options)
            case _ => columnConditions
          }

          val joinConditionPart = if (joinConditions.isEmpty)
            "ON TRUE"
          else
            i"ON ${joinConditions.mkString(" AND\n")}"

          val joinType = node match {
            case _: fplan.Join => "INNER"
            case _: fplan.LeftOuterJoin => "LEFT OUTER"
            case _: fplan.ThetaLeftOuterJoin => "LEFT OUTER"
          }

          getJoinSql(node, joinType, joinConditionPart, resultColumns, options)
        }
        case node: fplan.AntiJoin => {
          val columnConditions = node.commonAttributes
            .map(getQuotedColumnName)
            .map(name => s"left_query.$name = right_query.$name")

          val conditionPart = if (columnConditions.isEmpty)
            "TRUE"
          else
            columnConditions.mkString(" AND\n")

          i"""SELECT * FROM
             |  ( ${getSql(node.left, options)}
             |  ) left_query
             |WHERE NOT EXISTS(
             |  SELECT * FROM
             |    ( ${getSql(node.right, options)}
             |    ) right_query
             |  WHERE $conditionPart
             |  )"""
        }
        case node: fplan.Production => {
          val renamePairs = node.output.zip(node.outputNames)
            .map {
              case (attribute, outputName) =>
                convertAttributeAtProductionNode(attribute) -> getQuotedColumnName(outputName)
            }
          getProjectionSql(node, renamePairs, options)
        }
        case node: fplan.Projection => {
          val renamePairs = node.flatSchema.map(proj => (getSql(proj, options), getQuotedColumnName(proj)))
          getProjectionSql(node, renamePairs, options)
        }
        case node: fplan.Selection =>
          val condition = getSql(node.nnode.condition, options)
          i"""SELECT * FROM
             |  (
             |    ${getSql(node.child, options)}
             |  ) subquery
             |WHERE $condition"""
        case node: fplan.GetEdges =>
          getGetEdgesSql(node)
        case node: fplan.AllDifferent => {
          val childSql = getSql(node.child, options)
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
        case node: fplan.SortAndTop => {
          val childSql = getSql(node.child, options)
          val nnode = node.nnode

          val orderByParts = nnode.order.map(order => getSql(order.child, options) + " " + order.direction.sql + " " + order.nullOrdering.sql)
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
        case node: fplan.DuplicateElimination => {
          val childSql = getSql(node.child, options)

          i"""SELECT DISTINCT * FROM
             |  (
             |    $childSql
             |  ) subquery"""
        }
        case node: fplan.Grouping => {
          val renamePairs = node.flatSchema.map(proj => (getSql(proj, options), getQuotedColumnName(proj)))
          val projectionSql = getProjectionSql(node, renamePairs, options)
          val groupByColumns = (node.nnode.aggregationCriteria ++ node.requiredProperties).map(getSql(_, options))
          val groupByPart = if (groupByColumns.isEmpty)
            ""
          else
            "GROUP BY " + groupByColumns.mkString(", ")

          i"""$projectionSql
             |$groupByPart"""
        }
        case node: fplan.Dual => "SELECT"
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
        case node: fplan.FNode => node.children.map(getSql(_, options)).mkString("\n")
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
        case _ => ""
      }

    if (node.isInstanceOf[fplan.FNode])
      s"""-- ${node.getClass.getSimpleName}
         |$sqlString""".stripMargin
    else
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

  def getJoinSql(node: fplan.EquiJoinLike, joinType: String, conditionPart: String, resultColumns: String, options: CompilerOptions) = {
    i"""SELECT $resultColumns FROM
       |  ( ${getSql(node.left, options)}
       |  ) left_query
       |  $joinType JOIN
       |  ( ${getSql(node.right, options)}
       |  ) right_query
       |$conditionPart"""
  }
}

class CompileSql(val cypherQuery: String, val parameters: Map[String, Any] = Map()) extends CompilerTest {

  import CompileSql._

  val stages = compile(cypherQuery)

  val fplan = stages.fplan
  val sql = getSql(fplan, CompilerOptions())

  def run(): String = {
    println(sql)
    sql
  }

}

case class CompilerOptions(unwrapJson: Boolean = false, parameters: Map[String, Any] = Map()) {}
