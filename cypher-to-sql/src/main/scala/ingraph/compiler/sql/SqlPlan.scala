package ingraph.compiler.sql

import ingraph.compiler.sql.CompileSql.{getQuotedColumnName, getSingleQuotedString, _}
import ingraph.compiler.sql.IndentationPreservingStringInterpolation._
import ingraph.model.expr.{EdgeListAttribute, NodeHasLabelsAttribute}
import ingraph.model.fplan
import ingraph.model.fplan.{BinaryFNode, FNode, LeafFNode, UnaryFNode}
import ingraph.model.treenodes.{GenericBinaryNode, GenericLeafNode, GenericUnaryNode}
import org.apache.spark.sql.catalyst.expressions.{Attribute, Literal}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

import scala.reflect.ClassTag

trait SqlNode extends LogicalPlan {
  override def children: Seq[SqlNode]

  override def output: Seq[Attribute] = ???

  protected def innerSql: String

  def sql: String = {
    val nodeType = getClass.getSimpleName

    i"""-- $nodeType
       |$innerSql"""
  }
}

abstract class SqlNodeCreator[+SqlType <: SqlNode, -FPlanType <: FNode](implicit sqlTag: ClassTag[SqlType], fPlanTag: ClassTag[FPlanType]) {
  def applyIfPossible(fNode: FNode, options: CompilerOptions): Option[(SqlNode with WithFNodeOrigin[FNode], CompilerOptions)] =
    fNode match {
      case fNode: FPlanType => Some(apply(fNode, options))
      case _ => None
    }

  def apply(fNode: FPlanType, options: CompilerOptions): (SqlNode with WithFNodeOrigin[FNode], CompilerOptions)
}

abstract class SqlNodeCreator2[+SqlType <: BinarySqlNode[BinaryFNode], -FPlanType <: BinaryFNode]
(implicit sqlTag: ClassTag[SqlType], fPlanTag: ClassTag[FPlanType])
  extends SqlNodeCreator[SqlType, FPlanType] {

  def create(fNode: FPlanType,
             left: SqlNode with WithFNodeOrigin[FNode],
             right: SqlNode with WithFNodeOrigin[FNode],
             options: CompilerOptions)
  : (SqlType, CompilerOptions)

  override def apply(fNode: FPlanType, options: CompilerOptions): (SqlType, CompilerOptions) = {
    val (left, _) = SqlNode(fNode.left, options)
    val (right, _) = SqlNode(fNode.right, options)

    create(fNode, left, right, options)
  }
}

abstract class SqlNodeCreator1[+SqlType <: UnarySqlNode[UnaryFNode], -FPlanType <: UnaryFNode]
(implicit sqlTag: ClassTag[SqlType], fPlanTag: ClassTag[FPlanType])
  extends SqlNodeCreator[SqlType, FPlanType] {

  def create(fNode: FPlanType,
             child: SqlNode with WithFNodeOrigin[FNode],
             options: CompilerOptions)
  : (SqlType, CompilerOptions)

  override def apply(fNode: FPlanType, options: CompilerOptions): (SqlType, CompilerOptions) = {
    val (child, _) = SqlNode(fNode.child, options)

    create(fNode, child, options)
  }
}

abstract class SqlNodeCreator0[+SqlType <: LeafSqlNode[LeafFNode], -FPlanType <: LeafFNode]
(implicit sqlTag: ClassTag[SqlType], fPlanTag: ClassTag[FPlanType])
  extends SqlNodeCreator[SqlType, FPlanType] {

  def create(fNode: FPlanType,
             options: CompilerOptions)
  : (SqlType, CompilerOptions)

  override def apply(fNode: FPlanType, options: CompilerOptions): (SqlType, CompilerOptions) =
    create(fNode, options)
}

object SqlNode {
  def apply(fNode: FNode, options: CompilerOptions): (SqlNode with WithFNodeOrigin[FNode], CompilerOptions) = {
    val creators = Seq[SqlNodeCreator[_ <: SqlNode, _ <: FNode]](
      AntiJoin,
      // TransitiveJoin must precede EquiJoinLike
      TransitiveJoin,
      // EquiJoinLike nodes except TransitiveJoin
      EquiJoinLike,

      SortAndTop,
      Selection,
      Production,
      Projection,
      DuplicateElimination,
      Grouping,
      AllDifferent,

      Dual,
      GetVertices,
      GetEdges
    )
    val option = creators.collectFirst(Function.unlift(_.applyIfPossible(fNode, options)))

    option.get
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
  override def innerSql: String = getGetEdgesSql(fNode)
}

object GetEdges extends SqlNodeCreator0[GetEdges, fplan.GetEdges] {
  override def create(fNode: fplan.GetEdges,
                      options: CompilerOptions)
  : (GetEdges, CompilerOptions) =
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

  override def innerSql: String =
    i"""SELECT
       |  $columns
       |FROM vertex
       |$labelConstraint"""
}

object GetVertices extends SqlNodeCreator0[GetVertices, fplan.GetVertices] {
  override def create(fNode: fplan.GetVertices,
                      options: CompilerOptions)
  : (GetVertices, CompilerOptions) =
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

  override def innerSql: String =
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

object TransitiveJoin extends SqlNodeCreator2[TransitiveJoin, fplan.TransitiveJoin] {
  override def create(fNode: fplan.TransitiveJoin,
                      left: SqlNode with WithFNodeOrigin[FNode],
                      right: SqlNode with WithFNodeOrigin[FNode],
                      options: CompilerOptions)
  : (TransitiveJoin, CompilerOptions) =
    (new TransitiveJoin(fNode, left, right.asInstanceOf[GetEdges], options), options)
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

  override def innerSql: String =
    i"""SELECT $resultColumns FROM
       |  ( $leftSql
       |  ) left_query
       |  $joinType JOIN
       |  ( $rightSql
       |  ) right_query
       |$joinConditionPart"""
}

object EquiJoinLike extends SqlNodeCreator2[EquiJoinLike, fplan.EquiJoinLike] {
  override def create(fNode: fplan.EquiJoinLike,
                      left: SqlNode with WithFNodeOrigin[FNode],
                      right: SqlNode with WithFNodeOrigin[FNode],
                      options: CompilerOptions)
  : (EquiJoinLike, CompilerOptions) =
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

  override def innerSql: String =
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

object AntiJoin extends SqlNodeCreator2[AntiJoin, fplan.AntiJoin] {
  override def create(fNode: fplan.AntiJoin,
                      left: SqlNode with WithFNodeOrigin[FNode],
                      right: SqlNode with WithFNodeOrigin[FNode],
                      options: CompilerOptions)
  : (AntiJoin, CompilerOptions) =
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

  override def innerSql: String =
    getProjectionSql(fNode, renamePairs, childSql, options)
}

object Production extends SqlNodeCreator1[Production, fplan.Production] {
  override def create(fNode: fplan.Production,
                      child: SqlNode with WithFNodeOrigin[FNode],
                      options: CompilerOptions): (Production, CompilerOptions) =
    (new Production(fNode, child, options), options)
}

class Projection(val fNode: fplan.Projection,
                 override val child: SqlNode with WithFNodeOrigin[FNode],
                 options: CompilerOptions)
  extends UnarySqlNode[fplan.Projection](child) {
  val renamePairs = fNode.flatSchema.map(proj => (getSql(proj, options), getQuotedColumnName(proj)))

  override def innerSql: String =
    getProjectionSql(fNode, renamePairs, childSql, options)
}

object Projection extends SqlNodeCreator1[Projection, fplan.Projection] {
  override def create(fNode: fplan.Projection,
                      child: SqlNode with WithFNodeOrigin[FNode],
                      options: CompilerOptions)
  : (Projection, CompilerOptions) =
    (new Projection(fNode, child, options), options)
}

class Selection(val fNode: fplan.Selection,
                override val child: SqlNode with WithFNodeOrigin[FNode],
                options: CompilerOptions)
  extends UnarySqlNode[fplan.Selection](child) {
  val condition = getSql(fNode.nnode.condition, options)

  override def innerSql: String =
    i"""SELECT * FROM
       |  (
       |    $childSql
       |  ) subquery
       |WHERE $condition"""
}

object Selection extends SqlNodeCreator1[Selection, fplan.Selection] {
  override def create(fNode: fplan.Selection,
                      child: SqlNode with WithFNodeOrigin[FNode],
                      options: CompilerOptions)
  : (Selection, CompilerOptions) =
    (new Selection(fNode, child, options), options)
}

class AllDifferent(val fNode: fplan.AllDifferent,
                   override val child: SqlNode with WithFNodeOrigin[FNode],
                   options: CompilerOptions)
  extends UnarySqlNode[fplan.AllDifferent](child) {
  val edgeIdsArray = ("ARRAY[]::INTEGER[]" +: fNode.nnode.edges.map(getQuotedColumnName)).mkString(" || ")
  // only more than 1 node must be checked for uniqueness (edge list can contain more edges)
  val allDifferentNeeded = fNode.nnode.edges.size > 1 || fNode.nnode.edges.exists(_.isInstanceOf[EdgeListAttribute])

  override def innerSql: String =
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

object AllDifferent extends SqlNodeCreator1[AllDifferent, fplan.AllDifferent] {
  override def create(fNode: fplan.AllDifferent,
                      child: SqlNode with WithFNodeOrigin[FNode],
                      options: CompilerOptions)
  : (AllDifferent, CompilerOptions) =
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

  override def innerSql: String =
    i"""SELECT * FROM
       |  (
       |    $childSql
       |  ) subquery
       |  ORDER BY ${orderByParts.mkString(", ")}
       |  $limitPart
       |  $offsetPart"""
}

object SortAndTop extends SqlNodeCreator1[SortAndTop, fplan.SortAndTop] {
  override def create(fNode: fplan.SortAndTop,
                      child: SqlNode with WithFNodeOrigin[FNode],
                      options: CompilerOptions)
  : (SortAndTop, CompilerOptions) =
    (new SortAndTop(fNode, child, options), options)
}

class DuplicateElimination(val fNode: fplan.DuplicateElimination,
                           override val child: SqlNode with WithFNodeOrigin[FNode],
                           options: CompilerOptions)
  extends UnarySqlNode[fplan.DuplicateElimination](child) {
  override def innerSql: String =
    i"""SELECT DISTINCT * FROM
       |  (
       |    $childSql
       |  ) subquery"""
}

object DuplicateElimination extends SqlNodeCreator1[DuplicateElimination, fplan.DuplicateElimination] {
  override def create(fNode: fplan.DuplicateElimination,
                      child: SqlNode with WithFNodeOrigin[FNode],
                      options: CompilerOptions)
  : (DuplicateElimination, CompilerOptions) =
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

  override def innerSql: String =
    i"""$projectionSql
       |$groupByPart"""
}

object Grouping extends SqlNodeCreator1[Grouping, fplan.Grouping] {
  override def create(fNode: fplan.Grouping,
                      child: SqlNode with WithFNodeOrigin[FNode],
                      options: CompilerOptions)
  : (Grouping, CompilerOptions) =
    (new Grouping(fNode, child, options), options)
}

class Dual(val fNode: fplan.Dual,
           options: CompilerOptions)
  extends LeafSqlNode[fplan.Dual] {

  override def innerSql: String = "SELECT"
}

object Dual extends SqlNodeCreator0[Dual, fplan.Dual] {
  override def create(fNode: fplan.Dual,
                      options: CompilerOptions)
  : (Dual, CompilerOptions) =
    (new Dual(fNode, options), options)
}








