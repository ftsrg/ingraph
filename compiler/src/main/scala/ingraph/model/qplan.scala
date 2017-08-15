package ingraph.model.qplan

import ingraph.model.expr._
import org.apache.spark.sql.catalyst.expressions.{Attribute, Expression, NamedExpression, SortOrder}
import org.apache.spark.sql.catalyst.plans.logical.{BinaryNode, LeafNode, LogicalPlan, UnaryNode}

trait QNode extends LogicalPlan
abstract class LeafQNode extends LeafNode with QNode {
}
abstract class UnaryQNode extends UnaryNode with QNode {
  override def output: Seq[Attribute] = child.output
}
abstract class BinaryQNode extends BinaryNode with QNode

/**
  * A stub leaf node for the qplan indicating incomplete compilation.
  */
abstract class AbstractQStub extends LeafQNode{
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

// leaf operators
case class GetVertices(v: VertexAttribute) extends LeafQNode {
  override def output = Seq(v)
}

case class Dual() extends LeafQNode {
  override def output = Seq()
}

// unary operators
case class AllDifferent(child: QNode, edges: Seq[EdgeAttribute]) extends UnaryQNode {}

case class DuplicateElimination(child: QNode) extends UnaryQNode {}

case class Expand(src: VertexAttribute,
                  trg: VertexAttribute,
                  edge: EdgeAttribute,
                  dir: Direction,
                  child: QNode) extends UnaryQNode with NavigationDescriptor {
  override def output = child.output ++ Seq(edge, trg)
}

case class Production(child: QNode) extends UnaryQNode {}

case class Projection(projectList: Seq[NamedExpression], child: QNode) extends UnaryQNode {
  override def output = projectList.map(_.toAttribute)
}

case class Selection(condition: Expression, child: QNode) extends UnaryQNode {}

case class Sort(order: Seq[SortOrder], child: QNode) extends UnaryQNode {}

case class Top(skipExpr: Expression, limitExpr: Expression, child: QNode) extends UnaryQNode {}

case class Unwind(element: Attribute, child: QNode) extends UnaryQNode {
  override def output = child.output.updated(child.output.indexOf(element), element) // TODO
}

// binary operators
case class Union(left: QNode, right: QNode) extends BinaryQNode {
  override def output: Seq[Attribute] = left.output
}

abstract class JoinLike extends BinaryQNode {
  def commonAttributes = left.output.filterNot(right.output.contains(_))
  override def output: Seq[Attribute] = left.output ++ right.output
}

abstract class EquiJoinLike extends JoinLike {
  override def output: Seq[Attribute] = left.output ++ right.output.filterNot(commonAttributes.contains(_))
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
case class ThetaLeftOuterJoin(left: QNode, right: QNode, condition: ExpressionBase ) extends EquiJoinLike {}

case class AntiJoin(left: QNode, right: QNode) extends JoinLike {
  override def output: Seq[Attribute] = left.output
}

// DML operators
abstract class CudOperator(child: QNode) extends UnaryQNode {}

case class Create(attributes: Seq[Attribute], child: QNode) extends CudOperator(child) {}

case class Delete(attributes: Seq[Attribute], detach: Boolean, child: QNode) extends CudOperator(child) {}
