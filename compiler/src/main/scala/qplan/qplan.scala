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

// leaf operators
case class GetVertices(v: VertexAttribute) extends LeafQNode {
  override def output = Seq(v)
}

// unary operators
case class AllDifferent(child: QNode) extends UnaryQNode {}

case class DuplicateElimination(child: QNode) extends UnaryQNode {}

case class Expand(src: VertexAttribute,
                  trg: VertexAttribute,
                  edge: EdgeAttribute,
                  dir: Direction,
                  child: QNode) extends UnaryQNode with NavigationDescriptor {
  override def output = child.output ++ Seq(edge, trg)
}

case class Production(child: QNode) extends UnaryQNode {}

// inspired by org.apache.spark.sql.catalyst.plans.logical.Project
case class Projection(projectList: Seq[NamedExpression], child: QNode) extends UnaryQNode {
  override def output = projectList.map(_.toAttribute)
}

// inspired by org.apache.spark.sql.catalyst.plans.logical.Filter
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

case class Join(left: QNode, right: QNode) extends EquiJoinLike {

}

case class AntiJoin(left: QNode, right: QNode) extends JoinLike {
  override def output: Seq[Attribute] = left.output
}

// DML operators
abstract class CudOperator(child: QNode) extends UnaryQNode {}

case class Create(attributes: Seq[Attribute], child: QNode) extends CudOperator(child) {}

case class Delete(attributes: Seq[Attribute], detach: Boolean, child: QNode) extends CudOperator(child) {}
