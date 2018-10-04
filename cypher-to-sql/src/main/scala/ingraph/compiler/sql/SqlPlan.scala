package ingraph.compiler.sql

import ingraph.compiler.sql.CompileSql.{getQuotedColumnName, getSingleQuotedString, _}
import ingraph.compiler.sql.Create.graphElementTypeString
import ingraph.compiler.sql.IndentationPreservingStringInterpolation._
import ingraph.compiler.sql.Production.{getSqlRecursively, withQueryNamePrefix}
import ingraph.compiler.sql.SqlNodeCreator._
import ingraph.compiler.sql.TransitiveJoin.{currentFromColumnName, edgeIdColumnName}
import ingraph.model.expr._
import ingraph.model.fplan
import ingraph.model.fplan.{BinaryFNode, FNode, LeafFNode, UnaryFNode}
import ingraph.model.treenodes.{GenericBinaryNode, GenericLeafNode, GenericUnaryNode}
import org.apache.spark.sql.catalyst.expressions.Literal
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan
import org.cytosm.common.gtop.GTop
import org.cytosm.common.gtop.implementation.relational.ImplementationNode

import scala.Function.tupled
import scala.collection.JavaConverters._
import scala.reflect.ClassTag

trait SqlNode extends LogicalPlan {
  def options: CompilerOptions

  def lastOptions: CompilerOptions = options

  def nextOptions: CompilerOptions = transformOptions(lastOptions)

  override def children: Seq[SqlNode]

  override def output: Seq[ResolvableName] = ???

  protected def innerSql: String

  def originalSqlQueryName: String = withQueryNamePrefix + lastOptions.nodeId

  def sqlQueryNameToRefer: String = originalSqlQueryName

  val nodeType: String = getClass.getSimpleName

  def sql: Option[String] = Some(innerSql)
}

abstract class SqlNodeCreator[-FPlanType <: FNode](implicit fPlanTag: ClassTag[FPlanType]) {
  def applyIfPossible(fNode: FNode, options: CompilerOptions): Option[(SqlNode, CompilerOptions)] =
    fNode match {
      case fNode: FPlanType => Some(apply(fNode, options))
      case _ => None
    }

  def apply(fNode: FPlanType, options: CompilerOptions): (SqlNode, CompilerOptions)
}

object SqlNodeCreator {
  def transformOptions(option: CompilerOptions): CompilerOptions =
    option.copy(nodeId = option.nodeId + 1)

  def transformOptions[LocalSqlType](tuple: (LocalSqlType, CompilerOptions)): (LocalSqlType, CompilerOptions) =
    (tuple._1, transformOptions(tuple._2))
}

abstract class SqlNodeCreator2[-FPlanType <: BinaryFNode]
(implicit fPlanTag: ClassTag[FPlanType])
  extends SqlNodeCreator[FPlanType] {

  def create(fNode: FPlanType,
             left: SqlNode,
             right: SqlNode,
             options: CompilerOptions)
  : (SqlNode, CompilerOptions)

  override def apply(fNode: FPlanType, options: CompilerOptions): (SqlNode, CompilerOptions) = {
    val (left, leftOptions) = transformOptions(SqlNode(fNode.left, options))
    val (right, rightOptions) = transformOptions(SqlNode(fNode.right, leftOptions))

    create(fNode, left, right, rightOptions)
  }
}

abstract class SqlNodeCreator1[-FPlanType <: UnaryFNode]
(implicit fPlanTag: ClassTag[FPlanType])
  extends SqlNodeCreator[FPlanType] {

  def create(fNode: FPlanType,
             child: SqlNode,
             options: CompilerOptions)
  : (SqlNode, CompilerOptions)

  override def apply(fNode: FPlanType, options: CompilerOptions): (SqlNode, CompilerOptions) = {
    val (child, childOptions) = transformOptions(SqlNode(fNode.child, options))

    create(fNode, child, childOptions)
  }
}

abstract class SqlNodeCreator0[-FPlanType <: LeafFNode]
(implicit fPlanTag: ClassTag[FPlanType])
  extends SqlNodeCreator[FPlanType] {

  def create(fNode: FPlanType,
             options: CompilerOptions)
  : (SqlNode, CompilerOptions)

  override def apply(fNode: FPlanType, options: CompilerOptions): (SqlNode, CompilerOptions) =
    create(fNode, options)
}

object SqlNode {
  def apply(fNode: FNode, options: CompilerOptions): (SqlNode, CompilerOptions) = {
    val creators = Seq[SqlNodeCreator[_ <: FNode]](
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
      Create,

      Dual,
      GetVertices,
      GetEdges
    )
    val option = creators.collectFirst(Function.unlift(_.applyIfPossible(fNode, options)))

    option.getOrElse(throw new NoSuchElementException(fNode.getClass.getName + " cannot be compiled to " + classOf[SqlNode].getSimpleName))
  }
}

trait WithFNodeOrigin[+T <: FNode] extends Equals with Product1[T] {
  this: SqlNode =>
  def fNode: T

  // TODO rework this to contain SQL specific output with type
  override def output: Seq[ResolvableName] = fNode.flatSchema

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

  override def sqlQueryNameToRefer: String =
    if (sql.isDefined) super.sqlQueryNameToRefer
    else child.sqlQueryNameToRefer

  val childSql = child.sqlQueryNameToRefer
}

abstract class BinarySqlNode
(val left: SqlNode, val right: SqlNode)
  extends GenericBinaryNode[SqlNode] with SqlNode {

  def leftSql = left.sqlQueryNameToRefer

  def rightSql = right.sqlQueryNameToRefer
}

abstract class LeafSqlNodeFromFNode[+T <: LeafFNode]
  extends LeafSqlNode with WithFNodeOrigin[T] {}

abstract class UnarySqlNodeFromFNode[+T <: UnaryFNode]
(child: SqlNode)
  extends UnarySqlNode(child) with WithFNodeOrigin[T] {}

abstract class BinarySqlNodeFromFNode[+T <: BinaryFNode]
(left: SqlNode, right: SqlNode)
  extends BinarySqlNode(left, right) with WithFNodeOrigin[T] {}

trait GetEdgesLike extends SqlNode {
  def fNode: fplan.GetEdges
}

class GetEdges(val fNode: fplan.GetEdges,
               val options: CompilerOptions)
  extends LeafSqlNodeFromFNode[fplan.GetEdges] with GetEdgesLike {
  override def innerSql: String = getGetEdgesSql(fNode, options)
}

object GetEdges extends SqlNodeCreator0[fplan.GetEdges] {
  override def create(fNodeInput: fplan.GetEdges,
                      options: CompilerOptions)
  : (SqlNode, CompilerOptions) = {
    if (fNodeInput.nnode.directed)
      (new GetEdges(fNodeInput, options), options)
    else {
      val sameDirectionNNode = fNodeInput.nnode.copy(directed = true)
      val oppositeDirectionNNode = sameDirectionNNode
        .copy(src = sameDirectionNNode.trg,
          trg = sameDirectionNNode.src)

      val sameDirectionFNode = fNodeInput.copy(nnode = sameDirectionNNode)
      val oppositeDirectionFNode = fNodeInput.copy(nnode = oppositeDirectionNNode)

      val (left, leftOptions) = transformOptions(create(sameDirectionFNode, options))
      val (right, rightOptions) = transformOptions(create(oppositeDirectionFNode, leftOptions))

      val unionAll = UnionAll(left, right, rightOptions)
      val identityNode = new IdentityNode(unionAll, unionAll.nextOptions) with GetEdgesLike {
        override def fNode: fplan.GetEdges = fNodeInput
      }

      identityNode -> identityNode.lastOptions
    }
  }
}

class GetVertices(val fNode: fplan.GetVertices,
                  val options: CompilerOptions)
  extends LeafSqlNodeFromFNode[fplan.GetVertices] {

  val gTop: Option[GTop] = options.gTop

  val vertexLabelConstraints = fNode.nnode.v.labels.vertexLabels
  // TODO support more source tables
  private val implNode: Option[ImplementationNode] = gTop
    .map(_.getImplementationLevel
      .getImplementationNodes.asScala
      .find(node =>
        // all vertex label constraints from the pattern must be satisfied,
        // i.e. the implementation nodes should contain all of them
        // empty = {vertex label constraints} \ {types in the table}
        vertexLabelConstraints.diff(node.getTypes.asScala.toSet).isEmpty)
      .get)
  val vertexTable = implNode.map(_.getTableName).getOrElse("vertex")
  // TODO support complex primary key
  val vertexIdColumn = implNode.map(_.getId.get(0).getColumnName).getOrElse("vertex_id")

  val columns =
    (
      s"""$vertexIdColumn AS ${getQuotedColumnName(fNode.nnode.v)}""" +:
        fNode.requiredProperties
          // skip NodeHasLabelsAttribute since it has no resolvedName to use as column name
          // TODO use NodeHasLabelsAttribute
          .filterNot(_.isInstanceOf[NodeHasLabelsAttribute])
          .map { prop =>
            // TODO insert NULL if no column with that name
            val propertyValuePart = implNode
              .map(_.getAttributes.asScala
                .find(prop.name == _.getAbstractionLevelName)
                .map(_.getColumnName)
                .map(getQuotedColumnName)
                .get)
              .getOrElse(
                s"(SELECT value FROM vertex_property WHERE parent = $vertexIdColumn AND key = ${getSingleQuotedString(prop.name)})")

            propertyValuePart + s" AS ${getQuotedColumnName(prop)}"
          })
      .mkString(",\n")

  val labelConstraint = getVertexLabelSqlCondition(fNode.nnode.v.labels, vertexIdColumn)
    .filter(_ => gTop.isEmpty) // no need for constraint if GTop is available
    .map("WHERE " + _)
    .getOrElse("")

  override def innerSql: String =
    i"""SELECT
       |  $columns
       |FROM $vertexTable
       |$labelConstraint"""
}

object GetVertices extends SqlNodeCreator0[fplan.GetVertices] {
  override def create(fNode: fplan.GetVertices,
                      options: CompilerOptions)
  : (GetVertices, CompilerOptions) =
    (new GetVertices(fNode, options), options)
}

class TransitiveJoin(val fNode: fplan.TransitiveJoin,
                     override val left: SqlNode,
                     override val right: SqlNode,
                     val options: CompilerOptions)
  extends BinarySqlNodeFromFNode[fplan.TransitiveJoin](left, right) {
  val edgesFNode = fNode.right
  val edgesSql = rightSql

  val edgeListAttribute = fNode.nnode.edgeList
  val edgeListName = getQuotedColumnName(edgesFNode.nnode.edge)
  val edgesFromVertexName = getQuotedColumnName(edgesFNode.nnode.src)
  val edgesToVertexName = getQuotedColumnName(edgesFNode.nnode.trg)
  val leftNodeColumnNames = left.output.map(getQuotedColumnName).mkString(", ")
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
       |    ($edgeListName|| $edgeIdColumnName) AS $edgeListName,
       |    edges.$edgesToVertexName AS nextFrom,
       |    edges.$edgesToVertexName
       |  FROM (SELECT * FROM $edgesSql) edges
       |    INNER JOIN recursive_table
       |      ON $edgeIdColumnName <> ALL ($edgeListName) -- edge uniqueness
       |         AND next_from = $currentFromColumnName
       |         $upperBoundConstraint
       |)
       |SELECT
       |  $joinNodeColumnNames
       |FROM recursive_table
       |$lowerBoundConstraint"""
}

object TransitiveJoin extends SqlNodeCreator2[fplan.TransitiveJoin] {
  val currentFromColumnName = "current_from"
  val edgeIdColumnName = "edge_id"

  override def create(fNode: fplan.TransitiveJoin,
                      left: SqlNode,
                      right: SqlNode,
                      options: CompilerOptions)
  : (SqlNode, CompilerOptions) =
    (new TransitiveJoin(fNode, left, right, options), options)

  override def apply(fNode: fplan.TransitiveJoin, options: CompilerOptions): (SqlNode, CompilerOptions) = {
    // transform GetEdges at right
    val origFNode = fNode.right
    val origNNode = origFNode.nnode

    val origSrc = origNNode.src.copy(name = currentFromColumnName,
      resolvedName = Some(types.TResolvedNameValue(currentFromColumnName, currentFromColumnName)))
    val origEdge = origNNode.edge.copy(name = edgeIdColumnName,
      resolvedName = Some(types.TResolvedNameValue(edgeIdColumnName, edgeIdColumnName)))

    val getEdgesNNode = origNNode.copy(src = origSrc, edge = origEdge)
    val getEdgesFNode = origFNode.copy(nnode = getEdgesNNode)

    val (left, _) = SqlNode(fNode.left, options)
    val (right, _) = SqlNode(getEdgesFNode, left.nextOptions)

    create(fNode, left, right, right.nextOptions)
  }
}

// EquiJoinLike nodes except TransitiveJoin
class EquiJoinLike(val fNode: fplan.EquiJoinLike,
                   override val left: SqlNode,
                   override val right: SqlNode,
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

object EquiJoinLike extends SqlNodeCreator2[fplan.EquiJoinLike] {
  override def create(fNode: fplan.EquiJoinLike,
                      left: SqlNode,
                      right: SqlNode,
                      options: CompilerOptions)
  : (EquiJoinLike, CompilerOptions) =
    (new EquiJoinLike(fNode, left, right, options), options)
}

class AntiJoin(val fNode: fplan.AntiJoin,
               override val left: SqlNode,
               override val right: SqlNode,
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

object AntiJoin extends SqlNodeCreator2[fplan.AntiJoin] {
  override def create(fNode: fplan.AntiJoin,
                      left: SqlNode,
                      right: SqlNode,
                      options: CompilerOptions)
  : (AntiJoin, CompilerOptions) =
    (new AntiJoin(fNode, left, right, options), options)
}

class Production(val fNode: fplan.Production,
                 override val child: SqlNode,
                 val options: CompilerOptions)
  extends UnarySqlNodeFromFNode[fplan.Production](child) {
  val renamePairs = fNode.output.zip(fNode.outputNames)
    .map {
      case (attribute, outputName) =>
        convertAttributeAtProductionNode(attribute) -> getQuotedColumnName(outputName)
    }

  override def innerSql: String = {
    val children = getSqlRecursively(child)
    val emptyQueriesAtEndCount = children.reverse.takeWhile(_.sql.isEmpty).size
    val queriesAtEndWithoutComma = children.takeRight(emptyQueriesAtEndCount + 1).toSet
    val withQueries =
      children
        .map { sqlNode =>
          val commaIfNeeded =
            if (queriesAtEndWithoutComma.contains(sqlNode)) ""
            else ","

          if (sqlNode.sql.isDefined)
            i"""${sqlNode.sqlQueryNameToRefer} AS
               | (-- ${sqlNode.nodeType}
               |  ${sqlNode.sql.get})$commaIfNeeded"""
          else
            s"-- ${sqlNode.originalSqlQueryName} (${sqlNode.nodeType}): ${sqlNode.sqlQueryNameToRefer}"
        }.mkString("\n")

    val projectionSql = getProjectionSql(renamePairs, childSql, options)
    s"""WITH
       |$withQueries
       |$projectionSql""".stripMargin
  }
}

object Production extends SqlNodeCreator1[fplan.Production] {

  val withQueryNamePrefix = "q"

  def getSqlRecursively(sqlNode: SqlNode): Seq[SqlNode] = {
    val childQueries = sqlNode.children.flatMap(getSqlRecursively)

    childQueries :+ sqlNode
  }

  override def create(fNode: fplan.Production,
                      child: SqlNode,
                      options: CompilerOptions): (Production, CompilerOptions) =
    (new Production(fNode, child, options), options)
}

class Projection(val fNode: fplan.Projection,
                 override val child: SqlNode,
                 val options: CompilerOptions)
  extends UnarySqlNodeFromFNode[fplan.Projection](child) {
  val renamePairs = fNode.flatSchema.map(proj => (getSql(proj, options), getQuotedColumnName(proj)))

  override def innerSql: String =
    getProjectionSql(renamePairs, childSql, options)
}

object Projection extends SqlNodeCreator1[fplan.Projection] {
  override def create(fNode: fplan.Projection,
                      child: SqlNode,
                      options: CompilerOptions)
  : (Projection, CompilerOptions) =
    (new Projection(fNode, child, options), options)
}

class Selection(val fNode: fplan.Selection,
                override val child: SqlNode,
                val options: CompilerOptions)
  extends UnarySqlNodeFromFNode[fplan.Selection](child) {
  val condition = getSql(fNode.nnode.condition, options)

  override def innerSql: String =
    i"""SELECT * FROM $childSql AS subquery
       |WHERE $condition"""
}

object Selection extends SqlNodeCreator1[fplan.Selection] {
  override def create(fNode: fplan.Selection,
                      child: SqlNode,
                      options: CompilerOptions)
  : (Selection, CompilerOptions) =
    (new Selection(fNode, child, options), options)
}

class AllDifferent(val fNode: fplan.AllDifferent,
                   override val child: SqlNode,
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

  override def sql: Option[String] =
    if (allDifferentNeeded) super.sql
    else None
}

object AllDifferent extends SqlNodeCreator1[fplan.AllDifferent] {
  override def create(fNode: fplan.AllDifferent,
                      child: SqlNode,
                      options: CompilerOptions)
  : (AllDifferent, CompilerOptions) =
    (new AllDifferent(fNode, child, options), options)
}

class SortAndTop(val fNode: fplan.SortAndTop,
                 override val child: SqlNode,
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

object SortAndTop extends SqlNodeCreator1[fplan.SortAndTop] {
  override def create(fNode: fplan.SortAndTop,
                      child: SqlNode,
                      options: CompilerOptions)
  : (SortAndTop, CompilerOptions) =
    (new SortAndTop(fNode, child, options), options)
}

class DuplicateElimination(val fNode: fplan.DuplicateElimination,
                           override val child: SqlNode,
                           val options: CompilerOptions)
  extends UnarySqlNodeFromFNode[fplan.DuplicateElimination](child) {
  override def innerSql: String =
    i"""SELECT DISTINCT * FROM $childSql AS subquery"""
}

object DuplicateElimination extends SqlNodeCreator1[fplan.DuplicateElimination] {
  override def create(fNode: fplan.DuplicateElimination,
                      child: SqlNode,
                      options: CompilerOptions)
  : (DuplicateElimination, CompilerOptions) =
    (new DuplicateElimination(fNode, child, options), options)
}

class Grouping(val fNode: fplan.Grouping,
               override val child: SqlNode,
               val options: CompilerOptions)
  extends UnarySqlNodeFromFNode[fplan.Grouping](child) {

  val renamePairs = fNode.flatSchema.map(proj => (getSql(proj, options), getQuotedColumnName(proj)))
  val projectionSql = getProjectionSql(renamePairs, childSql, options)
  val groupByColumns = (fNode.nnode.aggregationCriteria ++ fNode.requiredProperties).map(getSql(_, options))
  val groupByPart = if (groupByColumns.isEmpty)
    ""
  else
    "GROUP BY " + groupByColumns.mkString(", ")

  override def innerSql: String =
    i"""$projectionSql
       |$groupByPart"""
}

object Grouping extends SqlNodeCreator1[fplan.Grouping] {
  override def create(fNode: fplan.Grouping,
                      child: SqlNode,
                      options: CompilerOptions)
  : (Grouping, CompilerOptions) =
    (new Grouping(fNode, child, options), options)
}

case class UnionAll(override val left: SqlNode,
                    override val right: SqlNode,
                    options: CompilerOptions)
  extends BinarySqlNode(left, right) {

  // the children must have the same number of columns and their types must match
  // additional constraint here is that the columns must have the same names in each subquery
  assert(left.output.map(_.resolvedName).toSet == right.output.map(_.resolvedName).toSet)

  // explicitly specify columns for the right query
  // to have the same order of columns as in the left query
  private val columns: String = left.output.map(getQuotedColumnName).mkString(", ")

  override protected def innerSql: String =
    i"""SELECT * FROM $leftSql
       |UNION ALL
       |SELECT $columns FROM $rightSql"""
}

case class GenerateId(child: SqlNode,
                      elementTypePrefix: String,
                      override val options: CompilerOptions,
                      newElementColumnName: String)
  extends LeafSqlNode {

  val childQueryName: String = child.sqlQueryNameToRefer

  override protected def innerSql: String = s"SELECT nextval('${elementTypePrefix}_seq') AS $newElementColumnName, * FROM $childQueryName"
}

case class InsertWithSelect(override val options: CompilerOptions,
                            tableName: String,
                            renamePairs: Traversable[(String, Option[String])],
                            fromTableNames: String*)
  extends LeafSqlNode {

  override protected def innerSql: String = {
    val selectPairsPart = renamePairs.map {
      case (oldName, Some(newName)) => oldName + " AS " + newName
      case (oldName, None) => oldName
    }.mkString(", ")
    val fromTablesPart = fromTableNames.mkString(", ")

    i"""INSERT INTO $tableName SELECT $selectPairsPart FROM $fromTablesPart"""
  }
}

object InsertWithSelect {
  def newElement(options: CompilerOptions,
                 tableName: String,
                 renamePairs: Traversable[(String, String)],
                 fromTableNames: String*)
  : InsertWithSelect =
    InsertWithSelect(options, tableName,
      renamePairs.map(tupled((origName, newName) => origName -> Some(newName))), fromTableNames: _*)
}

class Create(val fNode: fplan.Create,
             val child: SqlNode,
             val options: CompilerOptions)
  extends SqlNode with WithFNodeOrigin[fplan.Create] {

  val attribute = fNode.nnode.attribute.asInstanceOf[ElementAttribute]
  val typePrefix: String = graphElementTypeString(attribute)
  val newElementColumnName = getQuotedColumnName(attribute)

  val generateId = GenerateId(child, typePrefix, options, newElementColumnName)
  val genVertexIdQueryName: String = generateId.sqlQueryNameToRefer

  private val elementInsertProjectionPairs: Seq[(String, String)] = Seq(newElementColumnName -> (typePrefix + "_id")) ++
    // edge-specific columns: from, to, type
    Some(attribute)
      .collect { case a: RichEdgeAttribute => a }
      .map(edge => {
        val typeString = getSingleQuotedString(edge.edge.labels.edgeLabels.head)
        val (src, trg) = edge.dir match {
          case Out => edge.src -> edge.trg
          case In => edge.trg -> edge.src
        }

        val columnNames = Seq(getQuotedColumnName(src) -> "from", getQuotedColumnName(trg) -> "to")

        columnNames :+ (typeString -> "type")
      })
      .getOrElse(Seq())

  val insertElements =
    InsertWithSelect.newElement(generateId.nextOptions, typePrefix, elementInsertProjectionPairs, genVertexIdQueryName)

  // for vertex labels
  val labelStrings = Some(attribute)
    .collect { case a: VertexAttribute => a }
    .map(_.labels.vertexLabels.map(getSingleQuotedString))
    .getOrElse(Set())
  val labelInserts =
    if (labelStrings.isEmpty)
      None
    else {
      val labelNameRows = labelStrings.map("(" + _ + ")").mkString(", ")
      val labelsTable = s"(VALUES $labelNameRows) AS labels(l)"
      val renamePairs = Seq(
        genVertexIdQueryName + "." + newElementColumnName -> Some("parent"),
        "labels.l" -> Some("name"))

      Some(InsertWithSelect(insertElements.nextOptions, "label", renamePairs, genVertexIdQueryName, labelsTable))
    }
  val lastQueryBeforeProperties = labelInserts.getOrElse(insertElements)

  // scanLeft and drop: https://stackoverflow.com/a/47007773
  val propertyInserts = attribute.properties
    .scanLeft(lastQueryBeforeProperties) { case (lastQuery, (key, value)) =>
      val currentOptions = lastQuery.nextOptions

      val keyName = getSingleQuotedString(key)
      val valueSql = getSql(value, options)
      val renamePairs = Seq(newElementColumnName -> Some("parent"), keyName -> Some("key"), valueSql -> Some("value"))

      InsertWithSelect(currentOptions, typePrefix + "_property", renamePairs, genVertexIdQueryName)
    }.drop(1)

  override def children: Seq[SqlNode] =
    Seq(child, generateId, insertElements) ++ labelInserts ++ propertyInserts

  // this Create query has the last nodeId
  override def lastOptions: CompilerOptions = children.last.nextOptions

  override def innerSql: String =
    i"""SELECT * FROM $genVertexIdQueryName"""
}

object Create extends SqlNodeCreator[fplan.Create] {

  def graphElementTypeString(attribute: ElementAttribute): String =
    attribute match {
      case _: VertexAttribute => "vertex"
      case _: RichEdgeAttribute => "edge"
    }

  override def apply(fNode: fplan.Create,
                     options: CompilerOptions)
  : (SqlNode, CompilerOptions) = {
    val (child, childOptions) = transformOptions(SqlNode(fNode.child, options))

    val createNode = new Create(fNode, child, childOptions)

    (createNode, createNode.lastOptions)
  }
}

class Dual(val fNode: fplan.Dual,
           val options: CompilerOptions)
  extends LeafSqlNodeFromFNode[fplan.Dual] {

  override def innerSql: String = "SELECT"
}

object Dual extends SqlNodeCreator0[fplan.Dual] {
  override def create(fNode: fplan.Dual,
                      options: CompilerOptions)
  : (Dual, CompilerOptions) =
    (new Dual(fNode, options), options)
}

class IdentityNode(override val child: SqlNode,
                   val options: CompilerOptions)
  extends UnarySqlNode(child) {

  override def sql: Option[String] = None

  override protected def innerSql: String = ???

  override def productElement(n: Int): Any = child.productElement(n)

  override def productArity: Int = child.productArity

  override def canEqual(that: Any): Boolean =
    that match {
      case that: IdentityNode => child canEqual that.child
      case _ => false
    }
}

object IdentityNode {
  def apply(child: SqlNode): (SqlNode, CompilerOptions) = {
    val node = new IdentityNode(child, child.nextOptions)
    node -> node.lastOptions
  }
}
