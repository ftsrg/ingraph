package ingraph.model.gplan

import ingraph.model.expr._
import ingraph.model.expr.types.{TProjectList, TSortOrder}
import ingraph.model.plan._
import ingraph.model.treenodes._
import org.apache.spark.sql.catalyst.expressions.{Expression, SortOrder}
import org.apache.spark.sql.catalyst.analysis.UnresolvedAttribute
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

/**
  * GNodes for building a gplan tree
  */
trait GNode extends LogicalPlan {
  override def output: Seq[ResolvableName]
  def expressionChildren: Seq[Expression] = Seq()
}
abstract class LeafGNode extends GenericLeafNode[GNode] with GNode
abstract class UnaryGNode extends GenericUnaryNode[GNode] with GNode {
  override def output: Seq[ResolvableName] = child.output
}
abstract class BinaryGNode extends GenericBinaryNode[GNode] with GNode

/**
  * A stub leaf node for the gplan indicating incomplete compilation.
  */
abstract class AbstractGStub extends LeafGNode {
  override def output = Seq()
}

/**
  * A stub leaf node for the gplan indicating incomplete compilation,
  * i.e. something is not implemented but would be needed to compile the particular query.
  * @param note An optional note
  */
case class GStub(note: String = "FIXME") extends AbstractGStub {}

/**
  * A placeholder leafnode which should never leave the cypher2gplan compiler.
  *
  * This compiler sometimes creates incomplete subtrees,
  * e.g. a Join whose left input will be used for chaining.
  *
  * This placeholder will indicate the places where the tree is incomplete.
  *
  * \@jmarton is not sure if we need this as its original aim to chain query parts together was solved in a different way.
  */
abstract class PlaceHolder() extends AbstractGStub() {}

// leaf nodes
case class GetVertices(v: VertexAttribute) extends LeafGNode with TGetVertices {
  override def output = Seq(v)
  override def expressionChildren = Seq(v)
}

case class Dual() extends LeafGNode with TDual{
  override def output = Seq()
}

// unary nodes
case class AllDifferent(edges: Seq[AbstractEdgeAttribute], child: GNode) extends UnaryGNode with TAllDifferent {
  override def expressionChildren = edges
}

case class DuplicateElimination(child: GNode) extends UnaryGNode with TDuplicateElimination {}

case class Expand(src: VertexAttribute,
                  trg: VertexAttribute,
                  edge: AbstractEdgeAttribute,
                  dir: Direction,
                  child: GNode) extends UnaryGNode with NavigationDescriptor {
  override def output = child.output ++ Seq(edge, trg)
  override def expressionChildren = Seq(src, trg, edge)
}

case class Production(child: GNode) extends UnaryGNode with TProduction {}

/**
  * The Projection operator is the renaissance man of the query plan specification.
  *
  * It can:
  * - Project variables to a schema, i.e. only keeping a certain set of variables.
  * - Extract attributes from a vertex/edge, e.g. it can project to n.name, r.weight, etc. (other operators, such as the SelectionOperator are also capable of this feat)
  * - Evaluate metafunctions, such as labels(n), relationship(r), keys(e), properties(e), by relying on input from the NullaryOperators
  *   (i.e. the inferencer algorithm has to propagate these variables to the LeafQNode instances)
  * - Evaluate other functions, e.g. sin(x), substring(s, from, to), toBoolean(s), etc.
  */
abstract class AbstractProjection(projectList: TProjectList, child: GNode) extends UnaryGNode with ProjectionDescriptor with TAbstractProjection {
  override def output = projectList
  override def expressionChildren = projectList
}

/**
  * An UnresolvedProjection will either be resolved to a Projection or to a Grouping based on its projectList,
  * followed by DuplicateElimination, Sort, Top, Selection
  */
case class UnresolvedProjection(override val projectList: TProjectList, override val child: GNode, distinct: Boolean = false, sortOrder: Option[TSortOrder] = None, skipExpr: Option[Expression] = None, limitExpr: Option[Expression] = None, selectionCondition: Option[Expression] = None) extends AbstractProjection(projectList, child)
case class Projection(override val projectList: TProjectList, override val child: GNode) extends AbstractProjection(projectList, child)

/**
  * The Grouping operator adds grouping functionality to the basic ProjectionDescriptor.
  *
  * If Projection operator was the renaissance man, this is Baroque of the relational graph algebra model.
  */
case class Grouping(aggregationCriteria: Seq[Expression], projectList: TProjectList, child: GNode) extends UnaryGNode with ProjectionDescriptor with TGrouping {
  override def output = projectList
  override def expressionChildren = projectList ++ aggregationCriteria
}

case class Selection(condition: Expression, child: GNode) extends UnaryGNode with TSelection {
  override def expressionChildren = Seq(condition)
}

case class Sort(order: TSortOrder, child: GNode) extends UnaryGNode with TSort {
  override def expressionChildren = order
}

case class Top(skipExpr: Option[Expression] = None, limitExpr: Option[Expression] = None, child: GNode) extends UnaryGNode with TTop {
  override def expressionChildren = Seq(skipExpr, limitExpr).flatten
}

case class Unwind(unwindAttribute: UnwindAttribute, child: GNode) extends UnaryGNode with TUnwind {
  override def output = child.output ++ Seq(unwindAttribute)
  override def expressionChildren = Seq(unwindAttribute)
}

// binary nodes
case class Union(all: Boolean, left: GNode, right: GNode) extends BinaryGNode with TUnion{
  override def output: Seq[ResolvableName] = left.output
}

abstract class JoinLike extends BinaryGNode with TJoinLike {
  def commonAttributes = left.output.filterNot(right.output.contains(_))
  override def output: Seq[ResolvableName] = left.output ++ right.output
}

abstract class EquiJoinLike extends JoinLike {
  override def output: Seq[ResolvableName] = left.output ++ right.output.filterNot(commonAttributes.contains(_))
}

case class Join(left: GNode, right: GNode) extends EquiJoinLike with TJoin {}

case class LeftOuterJoin(left: GNode, right: GNode) extends EquiJoinLike with TLeftOuterJoin {}

/**
  * An equi-join like left outer join, i.e. the join condition is composed of two parts AND-ed:
  *  - equality on the attributes that share a common name, and
  *  - the predicate given in condition.
  *
  * Note: this never filters on its left input!
  */
case class ThetaLeftOuterJoin(left: GNode, right: GNode, condition: Expression) extends EquiJoinLike with TThetaLeftOuterJoin {
  override def expressionChildren = Seq(condition)
}

case class AntiJoin(left: GNode, right: GNode) extends EquiJoinLike with TAntiJoin {
  override def output: Seq[ResolvableName] = left.output
}

// DML operators
abstract class CudOperator(child: GNode) extends UnaryGNode

case class Create(attributes: Seq[ResolvableName], child: GNode) extends CudOperator(child) with TCreate {
  override def expressionChildren = attributes
}

case class UnresolvedDelete(attributes: Seq[UnresolvedAttribute], detach: Boolean, child: GNode) extends CudOperator(child) {
  override def expressionChildren = attributes
}
case class Delete(attributes: Seq[ResolvableName], detach: Boolean, child: GNode) extends CudOperator(child) with TDelete {
  override def expressionChildren = attributes
}

case class Merge(attributes: Seq[ResolvableName], child: GNode) extends CudOperator(child) with TMerge {
  override def expressionChildren = attributes
}

case class SetNode(vertexLabelUpdates: Set[VertexLabelUpdate], child: GNode) extends CudOperator(child) with TSetNode {
  override def expressionChildren = vertexLabelUpdates.map( vlu => vlu.vertex ).toSeq
}

case class Remove(vertexLabelUpdates: Set[VertexLabelUpdate], child: GNode) extends CudOperator(child) with TRemove {
  override def expressionChildren = vertexLabelUpdates.map( vlu => vlu.vertex ).toSeq
}
