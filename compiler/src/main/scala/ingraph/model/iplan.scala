package ingraph.model.iplan

import ingraph.model.expr
import ingraph.model.expr.EdgeAttribute
import ingraph.model.treenodes._
import org.apache.spark.sql.catalyst.expressions.{Expression, NamedExpression, SortOrder}
import org.apache.spark.sql.catalyst.plans.logical._

// abstract classes and traits
trait INode extends LogicalPlan {
  override def children: Seq[INode]
  override def output: Seq[NamedExpression]
}

abstract class LeafINode extends GenericLeafNode[INode] with INode {}
abstract class UnaryINode extends GenericUnaryNode[INode] with INode {
  override def output: Seq[NamedExpression] = child.output
}
abstract class BinaryINode extends GenericBinaryNode[INode] with INode {}

trait JoinLike extends BinaryINode {
  def common: Seq[NamedExpression] = left.output.filter(right.output.contains(_))
}
trait EquiJoinLike extends JoinLike {
  override def output: Seq[NamedExpression] = left.output ++ right.output.filter(left.output.contains(_))
}

// leaf nodes
case class GetVertices(v: expr.VertexAttribute) extends LeafINode {
  override def output: Seq[NamedExpression] = Seq(v)
}

case class GetEdges(src: expr.VertexAttribute,
                    trg: expr.VertexAttribute,
                    edge: expr.EdgeAttribute,
                    dir: expr.Direction)
  extends LeafINode with expr.NavigationDescriptor {
  override def output = Seq(src, trg, edge)
}

case class Dual() extends LeafINode {
  override def output = Seq()
}

// unary nodes
case class AllDifferent(child: INode,
                        edges: Seq[EdgeAttribute]) extends UnaryINode {}

case class DuplicateElimination(child: INode) extends UnaryINode {}

case class Production(child: INode) extends UnaryINode {}

case class Projection(projectList: Seq[NamedExpression],
                      child: INode) extends UnaryINode {
  //override def output: Seq[Attribute] = projectList // TODO
}

case class Selection(condition: Expression,
                     child: INode) extends UnaryINode {}

case class SortAndTop(skipExpr: Expression,
                      limitExpr: Expression,
                      order: Seq[SortOrder],
                      child: INode) extends UnaryINode {}

// binary nodes
case class Union(left: INode,
                 right: INode) extends BinaryINode {
  override def output: Seq[NamedExpression] = left.output
}

case class AntiJoin(left: INode, right: INode) extends BinaryINode with JoinLike {
  override def output: Seq[NamedExpression] = left.output
}

case class Join(left: INode, right: INode) extends BinaryINode with EquiJoinLike {}

case class LeftOuterJoin(left: INode, right: INode) extends BinaryINode with EquiJoinLike {}

case class ThetaLeftOuterJoin(
          left: INode,
          right: INode,
          condition: Expression) extends BinaryINode with EquiJoinLike {
}
