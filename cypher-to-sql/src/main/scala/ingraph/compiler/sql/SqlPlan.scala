package ingraph.compiler.sql

import ingraph.compiler.sql.CompileSql.{getQuotedColumnName, getSingleQuotedString, _}
import ingraph.compiler.sql.IndentationPreservingStringInterpolation._
import ingraph.model.expr.{EdgeListAttribute, NodeHasLabelsAttribute}
import ingraph.model.fplan
import ingraph.model.fplan.{BinaryFNode, FNode, LeafFNode, UnaryFNode}
import ingraph.model.treenodes.{GenericBinaryNode, GenericLeafNode, GenericUnaryNode}
import org.apache.spark.sql.catalyst.expressions.{Attribute, Literal}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

trait SqlNode extends LogicalPlan {
  override def children: Seq[SqlNode]

  override def output: Seq[Attribute] = ???

  def sql: String
}

object SqlNode {
  def apply(fNode: fplan.GetEdges, options: CompilerOptions): (GetEdges, CompilerOptions) =
    GetEdges(fNode, options)

  def apply(fNode: FNode, options: CompilerOptions): (SqlNode with WithFNodeOrigin[FNode], CompilerOptions) =
    fNode match {
      case fNode: BinaryFNode => {
        val (left, _) = SqlNode(fNode.left, options)
        val (right, _) = SqlNode(fNode.right, options)

        fNode match {
          case fNode: fplan.AntiJoin => AntiJoin(fNode, left, right, options)
          // transform right child here to keep type information
          case fNode: fplan.TransitiveJoin => TransitiveJoin(fNode, left, apply(fNode.right, options)._1, options)
          // EquiJoinLike nodes except TransitiveJoin
          case fNode: fplan.EquiJoinLike => EquiJoinLike(fNode, left, right, options)
        }
      }
      case fNode: UnaryFNode => {
        val (child, _) = SqlNode(fNode.child, options)

        fNode match {
          case fNode: fplan.SortAndTop => SortAndTop(fNode, child, options)
          case fNode: fplan.Selection => Selection(fNode, child, options)
          case fNode: fplan.Production => Production(fNode, child, options)
          case fNode: fplan.Projection => Projection(fNode, child, options)
          case fNode: fplan.DuplicateElimination => DuplicateElimination(fNode, child, options)
          case fNode: fplan.Grouping => Grouping(fNode, child, options)
          case fNode: fplan.AllDifferent => AllDifferent(fNode, child, options)
        }
      }

      case fNode: LeafFNode => {
        fNode match {
          case fNode: fplan.Dual => Dual(fNode, options)
          case fNode: fplan.GetVertices => GetVertices(fNode, options)
          case fNode: fplan.GetEdges => apply(fNode, options)
        }
      }
    }
}

trait WithFNodeOrigin[+T <: FNode] extends Equals with Product1[T] {
  this: SqlNode =>
  def fNode: T

  override def _1: T = fNode

  override def canEqual(that: Any): Boolean =
    that match {
      case that: WithFNodeOrigin[FNode] => fNode canEqual that.fNode
      case _ => false
    }

  override def equals(that: Any): Boolean =
    (this canEqual that) &&
      (that match {
        case that: WithFNodeOrigin[FNode] => fNode equals that.fNode
        case _ => false
      })
}

abstract class LeafSqlNode[+T <: LeafFNode] extends GenericLeafNode[SqlNode] with SqlNode with WithFNodeOrigin[T] {}

abstract class UnarySqlNode[+T <: UnaryFNode](
                                               val child: SqlNode with WithFNodeOrigin[FNode])
  extends GenericUnaryNode[SqlNode] with SqlNode with WithFNodeOrigin[T] {

  val childSql = child.sql
}

abstract class BinarySqlNode[+T <: BinaryFNode](val left: SqlNode with WithFNodeOrigin[FNode],
                                                val right: SqlNode with WithFNodeOrigin[FNode])
  extends GenericBinaryNode[SqlNode] with SqlNode with WithFNodeOrigin[T] {

  def leftSql = left.sql

  def rightSql = right.sql
}

class GetEdges(val fNode: fplan.GetEdges,
               options: CompilerOptions) extends LeafSqlNode[fplan.GetEdges] {
  override def sql: String = getGetEdgesSql(fNode)
}

object GetEdges {
  def apply(fNode: fplan.GetEdges, options: CompilerOptions): (GetEdges, CompilerOptions) =
    (new GetEdges(fNode, options), options)
}

class GetVertices(val fNode: fplan.GetVertices,
                  options: CompilerOptions) extends LeafSqlNode[fplan.GetVertices] {
  val columns =
    (
      s"""vertex_id AS ${getQuotedColumnName(fNode.nnode.v)}""" +:
        fNode.requiredProperties
          // skip NodeHasLabelsAttribute since it has no resolvedName to use as column name
          // TODO use NodeHasLabelsAttribute
          .filterNot(_.isInstanceOf[NodeHasLabelsAttribute])
          .map(prop =>s"""(SELECT value FROM vertex_property WHERE parent = vertex_id AND key = ${getSingleQuotedString(prop.name)}) AS ${getQuotedColumnName(prop)}"""))
      .mkString(",\n")

  val labelConstraint = getVertexLabelSqlCondition(fNode.nnode.v.labels, "vertex_id")
    .map("WHERE " + _)
    .getOrElse("")

  override def sql: String =
    i"""SELECT
       |  $columns
       |FROM vertex
       |$labelConstraint"""
}

object GetVertices {
  def apply(fNode: fplan.GetVertices, options: CompilerOptions): (GetVertices, CompilerOptions) =
    (new GetVertices(fNode, options), options)
}

class TransitiveJoin(val fNode: fplan.TransitiveJoin,
                     override val left: SqlNode with WithFNodeOrigin[FNode],
                     override val right: GetEdges,
                     options: CompilerOptions)
  extends BinarySqlNode[fplan.TransitiveJoin](left, right) {
  val edgesNode = right.fNode

  // TODO override column names in right
  override val rightSql: String = getGetEdgesSql(edgesNode, Some("current_from"), Some("edge_id"))
  val edgesSql = rightSql

  val edgeListAttribute = fNode.nnode.edgeList
  val edgeListName = getQuotedColumnName(edgesNode.nnode.edge)
  val edgesFromVertexName = getQuotedColumnName(edgesNode.nnode.src)
  val edgesToVertexName = getQuotedColumnName(edgesNode.nnode.trg)
  val leftNodeColumnNames = left.fNode.flatSchema.map(getQuotedColumnName).mkString(", ")
  val joinNodeColumnNames = fNode.flatSchema.map(getQuotedColumnName).mkString(",\n")

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

  override def sql: String =
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

object TransitiveJoin {
  def apply(fNode: fplan.TransitiveJoin,
            left: SqlNode with WithFNodeOrigin[FNode],
            right: GetEdges,
            options: CompilerOptions): (TransitiveJoin, CompilerOptions) =
    (new TransitiveJoin(fNode, left, right, options), options)
}

// EquiJoinLike nodes except TransitiveJoin
class EquiJoinLike(val fNode: fplan.EquiJoinLike,
                   override val left: SqlNode with WithFNodeOrigin[FNode],
                   override val right: SqlNode with WithFNodeOrigin[FNode],
                   options: CompilerOptions)
  extends BinarySqlNode[fplan.EquiJoinLike](left, right) {

  val leftColumns = fNode.left.flatSchema.map(getQuotedColumnName)
  val rightColumns = fNode.right.flatSchema.map(getQuotedColumnName)
  // prefer columns from  the left query, because in left outer join the right ones may contain NULL
  val resultColumns =
    (leftColumns.map("left_query." + _) ++
      (rightColumns.toSet -- leftColumns).map("right_query." + _))
      .mkString(", ")

  val columnConditions = fNode.commonAttributes
    .map(getQuotedColumnName)
    .map(name => s"left_query.$name = right_query.$name")
  val joinConditions = fNode match {
    case node: fplan.ThetaLeftOuterJoin => columnConditions :+ getSql(node.nnode.condition, options)
    case _ => columnConditions
  }

  val joinConditionPart = if (joinConditions.isEmpty)
    "ON TRUE"
  else
    i"ON ${joinConditions.mkString(" AND\n")}"

  val joinType = fNode match {
    case _: fplan.Join => "INNER"
    case _: fplan.LeftOuterJoin => "LEFT OUTER"
    case _: fplan.ThetaLeftOuterJoin => "LEFT OUTER"
  }

  override def sql: String =
    i"""SELECT $resultColumns FROM
       |  ( $leftSql
       |  ) left_query
       |  $joinType JOIN
       |  ( $rightSql
       |  ) right_query
       |$joinConditionPart"""
}

object EquiJoinLike {
  def apply(fNode: fplan.EquiJoinLike,
            left: SqlNode with WithFNodeOrigin[FNode],
            right: SqlNode with WithFNodeOrigin[FNode],
            options: CompilerOptions): (EquiJoinLike, CompilerOptions) =
    (new EquiJoinLike(fNode, left, right, options), options)
}

class AntiJoin(val fNode: fplan.AntiJoin,
               override val left: SqlNode with WithFNodeOrigin[FNode],
               override val right: SqlNode with WithFNodeOrigin[FNode],
               options: CompilerOptions)
  extends BinarySqlNode[fplan.AntiJoin](left, right) {
  val columnConditions = fNode.commonAttributes
    .map(getQuotedColumnName)
    .map(name => s"left_query.$name = right_query.$name")

  val conditionPart = if (columnConditions.isEmpty)
    "TRUE"
  else
    columnConditions.mkString(" AND\n")

  override def sql: String =
    i"""SELECT * FROM
       |  ( $leftSql
       |  ) left_query
       |WHERE NOT EXISTS(
       |  SELECT * FROM
       |    ( $rightSql
       |    ) right_query
       |  WHERE $conditionPart
       |  )"""
}

object AntiJoin {
  def apply(fNode: fplan.AntiJoin,
            left: SqlNode with WithFNodeOrigin[FNode],
            right: SqlNode with WithFNodeOrigin[FNode],
            options: CompilerOptions): (AntiJoin, CompilerOptions) =
    (new AntiJoin(fNode, left, right, options), options)
}

class Production(val fNode: fplan.Production,
                 override val child: SqlNode with WithFNodeOrigin[FNode],
                 options: CompilerOptions)
  extends UnarySqlNode[fplan.Production](child) {
  val renamePairs = fNode.output.zip(fNode.outputNames)
    .map {
      case (attribute, outputName) =>
        convertAttributeAtProductionNode(attribute) -> getQuotedColumnName(outputName)
    }

  override def sql: String =
    getProjectionSql(fNode, renamePairs, childSql, options)
}

object Production {
  def apply(fNode: fplan.Production,
            child: SqlNode with WithFNodeOrigin[FNode],
            options: CompilerOptions): (Production, CompilerOptions) =
    (new Production(fNode, child, options), options)
}

class Projection(val fNode: fplan.Projection,
                 override val child: SqlNode with WithFNodeOrigin[FNode],
                 options: CompilerOptions)
  extends UnarySqlNode[fplan.Projection](child) {
  val renamePairs = fNode.flatSchema.map(proj => (getSql(proj, options), getQuotedColumnName(proj)))

  override def sql: String =
    getProjectionSql(fNode, renamePairs, childSql, options)
}

object Projection {
  def apply(fNode: fplan.Projection,
            child: SqlNode with WithFNodeOrigin[FNode],
            options: CompilerOptions): (Projection, CompilerOptions) =
    (new Projection(fNode, child, options), options)
}

class Selection(val fNode: fplan.Selection,
                override val child: SqlNode with WithFNodeOrigin[FNode],
                options: CompilerOptions)
  extends UnarySqlNode[fplan.Selection](child) {
  val condition = getSql(fNode.nnode.condition, options)

  override def sql: String =
    i"""SELECT * FROM
       |  (
       |    $childSql
       |  ) subquery
       |WHERE $condition"""
}

object Selection {
  def apply(fNode: fplan.Selection,
            child: SqlNode with WithFNodeOrigin[FNode],
            options: CompilerOptions): (Selection, CompilerOptions) =
    (new Selection(fNode, child, options), options)
}

class AllDifferent(val fNode: fplan.AllDifferent,
                   override val child: SqlNode with WithFNodeOrigin[FNode],
                   options: CompilerOptions)
  extends UnarySqlNode[fplan.AllDifferent](child) {
  val edgeIdsArray = ("ARRAY[]::INTEGER[]" +: fNode.nnode.edges.map(getQuotedColumnName)).mkString(" || ")
  // only more than 1 node must be checked for uniqueness (edge list can contain more edges)
  val allDifferentNeeded = fNode.nnode.edges.size > 1 || fNode.nnode.edges.exists(_.isInstanceOf[EdgeListAttribute])

  override def sql: String =
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

object AllDifferent {
  def apply(fNode: fplan.AllDifferent,
            child: SqlNode with WithFNodeOrigin[FNode],
            options: CompilerOptions): (AllDifferent, CompilerOptions) =
    (new AllDifferent(fNode, child, options), options)
}

class SortAndTop(val fNode: fplan.SortAndTop,
                 override val child: SqlNode with WithFNodeOrigin[FNode],
                 options: CompilerOptions)
  extends UnarySqlNode[fplan.SortAndTop](child) {
  val nnode = fNode.nnode

  val orderByParts = nnode.order.map(order => getSql(order.child, options) + " " + order.direction.sql + " " + order.nullOrdering.sql)
  val limitPart = nnode.limitExpr.map { case Literal(num: Long, _) => s"LIMIT $num" }.getOrElse("")
  val offsetPart = nnode.skipExpr.map { case Literal(num: Long, _) => s"OFFSET $num" }.getOrElse("")

  override def sql: String =
    i"""SELECT * FROM
       |  (
       |    $childSql
       |  ) subquery
       |  ORDER BY ${orderByParts.mkString(", ")}
       |  $limitPart
       |  $offsetPart"""
}

object SortAndTop {
  def apply(fNode: fplan.SortAndTop,
            child: SqlNode with WithFNodeOrigin[FNode],
            options: CompilerOptions): (SortAndTop, CompilerOptions) =
    (new SortAndTop(fNode, child, options), options)
}

class DuplicateElimination(val fNode: fplan.DuplicateElimination,
                           override val child: SqlNode with WithFNodeOrigin[FNode],
                           options: CompilerOptions)
  extends UnarySqlNode[fplan.DuplicateElimination](child) {
  override def sql: String =
    i"""SELECT DISTINCT * FROM
       |  (
       |    $childSql
       |  ) subquery"""
}

object DuplicateElimination {
  def apply(fNode: fplan.DuplicateElimination,
            child: SqlNode with WithFNodeOrigin[FNode],
            options: CompilerOptions): (DuplicateElimination, CompilerOptions) =
    (new DuplicateElimination(fNode, child, options), options)
}

class Grouping(val fNode: fplan.Grouping,
               override val child: SqlNode with WithFNodeOrigin[FNode],
               options: CompilerOptions)
  extends UnarySqlNode[fplan.Grouping](child) {

  val renamePairs = fNode.flatSchema.map(proj => (getSql(proj, options), getQuotedColumnName(proj)))
  val projectionSql = getProjectionSql(fNode, renamePairs, childSql, options)
  val groupByColumns = (fNode.nnode.aggregationCriteria ++ fNode.requiredProperties).map(getSql(_, options))
  val groupByPart = if (groupByColumns.isEmpty)
    ""
  else
    "GROUP BY " + groupByColumns.mkString(", ")

  override def sql: String =
    i"""$projectionSql
       |$groupByPart"""
}

object Grouping {
  def apply(fNode: fplan.Grouping,
            child: SqlNode with WithFNodeOrigin[FNode],
            options: CompilerOptions): (Grouping, CompilerOptions) =
    (new Grouping(fNode, child, options), options)
}

class Dual(val fNode: fplan.Dual,
           options: CompilerOptions)
  extends LeafSqlNode[fplan.Dual] {

  override def sql: String = "SELECT"
}

object Dual {
  def apply(fNode: fplan.Dual,
            options: CompilerOptions): (Dual, CompilerOptions) =
    (new Dual(fNode, options), options)
}








