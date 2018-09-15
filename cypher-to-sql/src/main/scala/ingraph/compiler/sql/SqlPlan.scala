package ingraph.compiler.sql

import ingraph.compiler.sql.CompileSql.{getQuotedColumnName, getSingleQuotedString, _}
import ingraph.compiler.sql.IndentationPreservingStringInterpolation._
import ingraph.compiler.sql.Production.{getSqlRecursively, withQueryNamePrefix}
import ingraph.model.expr.{EdgeListAttribute, NodeHasLabelsAttribute}
import ingraph.model.fplan
import ingraph.model.fplan.{BinaryFNode, FNode, LeafFNode, UnaryFNode}
import ingraph.model.treenodes.{GenericBinaryNode, GenericLeafNode, GenericUnaryNode}
import org.apache.spark.sql.catalyst.expressions.{Attribute, Literal}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

import scala.reflect.ClassTag

trait SqlNode extends LogicalPlan {
  def options: CompilerOptions

  override def children: Seq[SqlNode]

  override def output: Seq[Attribute] = ???

  protected def innerSql: String

  def sqlQueryName: String = withQueryNamePrefix + options.nodeId

  def sql: String = {
    val nodeType = getClass.getSimpleName

    s"""-- $nodeType
       |$innerSql""".stripMargin
  }
}

abstract class SqlNodeCreator[+SqlType <: SqlNode, -FPlanType <: FNode](implicit sqlTag: ClassTag[SqlType], fPlanTag: ClassTag[FPlanType]) {
  def applyIfPossible(fNode: FNode, options: CompilerOptions): Option[(SqlNode with WithFNodeOrigin[FNode], CompilerOptions)] =
    fNode match {
      case fNode: FPlanType => Some(apply(fNode, options))
      case _ => None
    }

  def apply(fNode: FPlanType, options: CompilerOptions): (SqlNode with WithFNodeOrigin[FNode], CompilerOptions)

  def transformOptions(option: CompilerOptions): CompilerOptions =
    CompilerOptions(option.parameters, option.nodeId + 1, option.unwrapJson)

  def transformOptions[LocalSqlType](tuple: (LocalSqlType, CompilerOptions)): (LocalSqlType, CompilerOptions) =
    (tuple._1, transformOptions(tuple._2))
}

abstract class SqlNodeCreator2[+SqlType <: BinarySqlNodeFromFNode[BinaryFNode], -FPlanType <: BinaryFNode]
(implicit sqlTag: ClassTag[SqlType], fPlanTag: ClassTag[FPlanType])
  extends SqlNodeCreator[SqlType, FPlanType] {

  def create(fNode: FPlanType,
             left: SqlNode with WithFNodeOrigin[FNode],
             right: SqlNode with WithFNodeOrigin[FNode],
             options: CompilerOptions)
  : (SqlType, CompilerOptions)

  override def apply(fNode: FPlanType, options: CompilerOptions): (SqlType, CompilerOptions) = {
    val (left, leftOptions) = transformOptions(SqlNode(fNode.left, options))
    val (right, rightOptions) = transformOptions(SqlNode(fNode.right, leftOptions))

    create(fNode, left, right, rightOptions)
  }
}

abstract class SqlNodeCreator1[+SqlType <: UnarySqlNodeFromFNode[UnaryFNode], -FPlanType <: UnaryFNode]
(implicit sqlTag: ClassTag[SqlType], fPlanTag: ClassTag[FPlanType])
  extends SqlNodeCreator[SqlType, FPlanType] {

  def create(fNode: FPlanType,
             child: SqlNode with WithFNodeOrigin[FNode],
             options: CompilerOptions)
  : (SqlType, CompilerOptions)

  override def apply(fNode: FPlanType, options: CompilerOptions): (SqlType, CompilerOptions) = {
    val (child, childOptions) = transformOptions(SqlNode(fNode.child, options))

    create(fNode, child, childOptions)
  }
}

abstract class SqlNodeCreator0[+SqlType <: LeafSqlNodeFromFNode[LeafFNode], -FPlanType <: LeafFNode]
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

abstract class LeafSqlNode
  extends GenericLeafNode[SqlNode] with SqlNode {}

abstract class UnarySqlNode(val child: SqlNode)
  extends GenericUnaryNode[SqlNode] with SqlNode {

  val childSql = child.sqlQueryName
}

abstract class BinarySqlNode
(val left: SqlNode, val right: SqlNode)
  extends GenericBinaryNode[SqlNode] with SqlNode {

  def leftSql = left.sqlQueryName

  def rightSql = right.sqlQueryName
}

abstract class LeafSqlNodeFromFNode[+T <: LeafFNode]
  extends LeafSqlNode with WithFNodeOrigin[T] {}

abstract class UnarySqlNodeFromFNode[+T <: UnaryFNode]
(child: SqlNode)
  extends UnarySqlNode(child) with WithFNodeOrigin[T] {}

abstract class BinarySqlNodeFromFNode[+T <: BinaryFNode]
(left: SqlNode, right: SqlNode)
  extends BinarySqlNode(left, right) with WithFNodeOrigin[T] {}

class GetEdges(val fNode: fplan.GetEdges,
               val options: CompilerOptions)
  extends LeafSqlNodeFromFNode[fplan.GetEdges] {
  override def innerSql: String = getGetEdgesSql(fNode)
}

object GetEdges extends SqlNodeCreator0[GetEdges, fplan.GetEdges] {
  override def create(fNode: fplan.GetEdges,
                      options: CompilerOptions)
  : (GetEdges, CompilerOptions) =
    (new GetEdges(fNode, options), options)
}

class GetVertices(val fNode: fplan.GetVertices,
                  val options: CompilerOptions)
  extends LeafSqlNodeFromFNode[fplan.GetVertices] {
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
                     val options: CompilerOptions)
  extends BinarySqlNodeFromFNode[fplan.TransitiveJoin](left, right) {
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
       |    WITH left_query AS (SELECT * FROM $leftSql)
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
                   val options: CompilerOptions)
  extends BinarySqlNodeFromFNode[fplan.EquiJoinLike](left, right) {

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
       |  $leftSql AS left_query
       |  $joinType JOIN
       |  $rightSql AS right_query
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
               val options: CompilerOptions)
  extends BinarySqlNodeFromFNode[fplan.AntiJoin](left, right) {
  val columnConditions = fNode.commonAttributes
    .map(getQuotedColumnName)
    .map(name => s"left_query.$name = right_query.$name")

  val conditionPart = if (columnConditions.isEmpty)
    "TRUE"
  else
    columnConditions.mkString(" AND\n")

  override def innerSql: String =
    i"""SELECT * FROM
       |  $leftSql AS left_query
       |WHERE NOT EXISTS(
       |  SELECT * FROM
       |    $rightSql AS right_query
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
                 val options: CompilerOptions)
  extends UnarySqlNodeFromFNode[fplan.Production](child) {
  val renamePairs = fNode.output.zip(fNode.outputNames)
    .map {
      case (attribute, outputName) =>
        convertAttributeAtProductionNode(attribute) -> getQuotedColumnName(outputName)
    }

  override def innerSql: String = {
    val withQueries =
      getSqlRecursively(child)
        .map { case (nodeId, sql) =>
          i"""$withQueryNamePrefix$nodeId AS
             | ($sql)"""
        }.mkString(",\n")

    val projectionSql = getProjectionSql(fNode, renamePairs, childSql, options)
    s"""WITH
       |$withQueries
       |$projectionSql""".stripMargin
  }
}

object Production extends SqlNodeCreator1[Production, fplan.Production] {

  val withQueryNamePrefix = "q"

  def getSqlRecursively(sqlNode: SqlNode): Seq[(Int, String)] = {
    sqlNode.children.flatMap(getSqlRecursively) :+
      (sqlNode.options.nodeId, sqlNode.sql)
  }

  override def create(fNode: fplan.Production,
                      child: SqlNode with WithFNodeOrigin[FNode],
                      options: CompilerOptions): (Production, CompilerOptions) =
    (new Production(fNode, child, options), options)
}

class Projection(val fNode: fplan.Projection,
                 override val child: SqlNode with WithFNodeOrigin[FNode],
                 val options: CompilerOptions)
  extends UnarySqlNodeFromFNode[fplan.Projection](child) {
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
                val options: CompilerOptions)
  extends UnarySqlNodeFromFNode[fplan.Selection](child) {
  val condition = getSql(fNode.nnode.condition, options)

  override def innerSql: String =
    i"""SELECT * FROM $childSql AS subquery
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
                   val options: CompilerOptions)
  extends UnarySqlNodeFromFNode[fplan.AllDifferent](child) {
  val edgeIdsArray = ("ARRAY[]::INTEGER[]" +: fNode.nnode.edges.map(getQuotedColumnName)).mkString(" || ")
  // only more than 1 node must be checked for uniqueness (edge list can contain more edges)
  val allDifferentNeeded = fNode.nnode.edges.size > 1 || fNode.nnode.edges.exists(_.isInstanceOf[EdgeListAttribute])

  val wherePart = if (allDifferentNeeded)
    i"""WHERE is_unique($edgeIdsArray)"""
  else ""

  override def innerSql: String =
    i"""SELECT * FROM $childSql AS subquery
       |  $wherePart"""
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
                 val options: CompilerOptions)
  extends UnarySqlNodeFromFNode[fplan.SortAndTop](child) {
  val nnode = fNode.nnode

  val orderByParts = nnode.order.map(order => getSql(order.child, options) + " " + order.direction.sql + " " + order.nullOrdering.sql)
  val limitPart = nnode.limitExpr.map { case Literal(num: Long, _) => s"LIMIT $num" }.getOrElse("")
  val offsetPart = nnode.skipExpr.map { case Literal(num: Long, _) => s"OFFSET $num" }.getOrElse("")

  override def innerSql: String =
    i"""SELECT * FROM $childSql AS subquery
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
                           val options: CompilerOptions)
  extends UnarySqlNodeFromFNode[fplan.DuplicateElimination](child) {
  override def innerSql: String =
    i"""SELECT DISTINCT * FROM $childSql AS subquery"""
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
               val options: CompilerOptions)
  extends UnarySqlNodeFromFNode[fplan.Grouping](child) {

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
           val options: CompilerOptions)
  extends LeafSqlNodeFromFNode[fplan.Dual] {

  override def innerSql: String = "SELECT"
}

object Dual extends SqlNodeCreator0[Dual, fplan.Dual] {
  override def create(fNode: fplan.Dual,
                      options: CompilerOptions)
  : (Dual, CompilerOptions) =
    (new Dual(fNode, options), options)
}








