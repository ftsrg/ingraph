package ingraph.model.qplan

import ingraph.model.expr._
import ingraph.model.expr.types.TProjectList
import ingraph.model.treenodes._
import org.apache.spark.sql.catalyst.expressions.{Attribute, Expression, NamedExpression, SortOrder}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

/**
  * QNodes for building a qplan tree
  */
trait QNode extends LogicalPlan {
  override def output: Seq[ResolvableName]
}
abstract class LeafQNode extends GenericLeafNode[QNode] with QNode
abstract class UnaryQNode extends GenericUnaryNode[QNode] with QNode {
  override def output: Seq[ResolvableName] = child.output
}
abstract class BinaryQNode extends GenericBinaryNode[QNode] with QNode


/**
  * A stub leaf node for the qplan indicating incomplete compilation.
  */
abstract class AbstractQStub extends LeafQNode {
  override def output = Seq()
}

/**
  * A stub leaf node for the qplan indicating incomplete compilation,
  * i.e. something is not implemented but would be needed to compile the particular query.
  * @param note An optional note
  */
case class QStub(note: String = "FIXME") extends AbstractQStub {}

/**
  * A placeholder leafnode which should never leave the cypher2qplan compiler.
  *
  * This compiler sometimes creates incomplete subtrees,
  * e.g. a Join whose left input will be used for chaining.
  *
  * This placeholder will indicate the places where the tree is incomplete.
  *
  * \@jmarton is not sure if we need this as its original aim to chain query parts together was solved in a different way.
  */
abstract class PlaceHolder() extends AbstractQStub() {}

// leaf nodes
case class GetVertices(v: VertexAttribute) extends LeafQNode {
  override def output = Seq(v)
}

case class Dual() extends LeafQNode {
  override def output = Seq()
}

// unary nodes
case class AllDifferent(edges: Seq[EdgeAttribute], child: QNode) extends UnaryQNode {}

case class DuplicateElimination(child: QNode) extends UnaryQNode {}

case class Expand(src: VertexAttribute,
                  trg: VertexAttribute,
                  edge: AbstractEdgeAttribute,
                  dir: Direction,
                  child: QNode) extends UnaryQNode with NavigationDescriptor {
  override def output = child.output ++ Seq(edge, trg)
}

case class Production(child: QNode) extends UnaryQNode {}

/**
  * The Projection operator is the renaissance man of the Rete network.
  *
  * It can:
  * - Project variables to a schema, i.e. only keeping a certain set of variables.
  * - Extract attributes from a vertex/edge, e.g. it can project to n.name, r.weight, etc. (other operators, such as the SelectionOperator are also capable of this feat)
  * - Evaluate metafunctions, such as labels(n), relationship(r), keys(e), properties(e), by relying on input from the NullaryOperators
  *   (i.e. the inferencer algorithm has to propagate these variables to the LeafQNode instances)
  * - Evaluate other functions, e.g. sin(x), substring(s, from, to), toBoolean(s), etc.
  */
abstract class AbstractProjection(projectList: TProjectList, child: QNode) extends UnaryQNode with ProjectionDescriptor {
  override def output = projectList
}

/**
  * An UnresolvedProjection will either be resolved to a Projection or to a Grouping based on its projectList.
  */
case class UnresolvedProjection(override val projectList: TProjectList, override val child: QNode) extends AbstractProjection(projectList, child)
case class Projection(override val projectList: TProjectList, override val child: QNode) extends AbstractProjection(projectList, child)

/**
  * The Grouping operator adds grouping functionality to the basic ProjectionDescriptor.
  *
  * If Projection operator was the renaissance man, this is Baroque of the relational graph algebra model.
  */
case class Grouping(aggregationCriteria: Seq[Expression], projectList: TProjectList, child: QNode) extends UnaryQNode with ProjectionDescriptor {
  override def output = projectList
}

case class Selection(condition: Expression, child: QNode) extends UnaryQNode {}

case class Sort(order: Seq[SortOrder], child: QNode) extends UnaryQNode {}

case class Top(skipExpr: Option[Expression] = None, limitExpr: Option[Expression] = None, child: QNode) extends UnaryQNode {}

case class Unwind(unwindAttribute: UnwindAttribute, child: QNode) extends UnaryQNode {
  override def output = child.output ++ Seq(unwindAttribute) // child.output.updated(child.output.indexOf(element), element)
  // TODO indexOf might be unable to find the attribute
}

// binary nodes
case class Union(all: Boolean, left: QNode, right: QNode) extends BinaryQNode {
  override def output: Seq[ResolvableName] = left.output
}

abstract class JoinLike extends BinaryQNode {
  def commonAttributes = left.output.filterNot(right.output.contains(_))
  override def output: Seq[ResolvableName] = left.output ++ right.output
}

abstract class EquiJoinLike extends JoinLike {
  override def output: Seq[ResolvableName] = left.output ++ right.output.filterNot(commonAttributes.contains(_))
}

case class Join(left: QNode, right: QNode) extends EquiJoinLike {}

case class LeftOuterJoin(left: QNode, right: QNode) extends EquiJoinLike {}

/**
  * An equi-join like left outer join, i.e. the join condition is composed of two parts AND-ed:
  *  - equality on the attributes that share a common name, and
  *  - the predicate given in condition.
  *
  * Note: this never filters on its left input!
  */
case class ThetaLeftOuterJoin(left: QNode, right: QNode, condition: Expression) extends EquiJoinLike {}

case class AntiJoin(left: QNode, right: QNode) extends EquiJoinLike {
  override def output: Seq[ResolvableName] = left.output
}

// DML operators
abstract class CudOperator(child: QNode) extends UnaryQNode

case class Create(attributes: Seq[ResolvableName], child: QNode) extends CudOperator(child)

case class Delete(attributes: Seq[ResolvableName], detach: Boolean, child: QNode) extends CudOperator(child)

case class Merge(attributes: Seq[ResolvableName], child: QNode) extends CudOperator(child)

case class SetNode(vertexLabelUpdates: Set[VertexLabelUpdate], child: QNode) extends CudOperator(child)

case class Remove(vertexLabelUpdates: Set[VertexLabelUpdate], child: QNode) extends CudOperator(child)
