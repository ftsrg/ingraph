package ingraph.model.tplan

import ingraph.model.expr.UnwindAttribute
import ingraph.model.fplan
import ingraph.model.treenodes.{GenericBinaryNode, GenericLeafNode, GenericUnaryNode}
import org.apache.spark.sql.catalyst.expressions.{Expression, SortOrder}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

trait TNode extends LogicalPlan {
  def fnode: fplan.FNode
  override def children: Seq[TNode]
  override def output = Seq()
}

abstract class LeafTNode extends GenericLeafNode[TNode] with TNode
abstract class UnaryTNode extends GenericUnaryNode[TNode] with TNode
abstract class BinaryTNode extends GenericBinaryNode[TNode] with TNode

trait JoinLike extends BinaryTNode {
  val leftMask: Seq[Int]
  val rightMask: Seq[Int]
}
trait EquiJoinLike extends JoinLike {}

// leaf nodes
case class GetVertices(fnode: fplan.GetVertices) extends LeafTNode {
}

case class GetEdges(fnode: fplan.GetEdges) extends LeafTNode {

}

case class GetEdgeLists(fnode: fplan.GetEdgeLists) extends LeafTNode {

}

case class Dual(fnode: fplan.Dual) extends LeafTNode {

}

// unary nodes
case class AllDifferent(fnode: fplan.AllDifferent, child: TNode) extends UnaryTNode {}

case class DuplicateElimination(fnode: fplan.DuplicateElimination, child: TNode) extends UnaryTNode {}

case class Production(fnode: fplan.Production, child: TNode) extends UnaryTNode {}

case class Projection(projectionTuple: Seq[Expression], fnode: fplan.Projection, child: TNode) extends UnaryTNode {}

case class Grouping(aggregationCriteria: Seq[Expression], projectionTuple: Seq[Expression], fnode: fplan.Grouping, child: TNode) extends UnaryTNode {}

case class Selection(condition: Expression, fnode: fplan.Selection,child: TNode) extends UnaryTNode {}

case class Unwind(unwindAttribute: UnwindAttribute, fnode: fplan.Unwind, child: TNode) extends UnaryTNode {}

case class SortAndTop(skipExpr: Option[Expression],
                      limitExpr: Option[Expression],
                      order: Seq[SortOrder],
                      fnode: fplan.SortAndTop,
                      child: TNode) extends UnaryTNode {}

// binary nodes
case class Union(fnode: fplan.Union, left: TNode, right: TNode) extends BinaryTNode {}

case class AntiJoin(leftMask: Seq[Int], rightMask: Seq[Int],
                    fnode: fplan.AntiJoin,
                    left: TNode, right: TNode) extends BinaryTNode with JoinLike {}

case class Join(leftMask: Seq[Int], rightMask: Seq[Int],
                fnode: fplan.Join,
                left: TNode, right: TNode
               ) extends BinaryTNode with EquiJoinLike {}

case class LeftOuterJoin(leftMask: Seq[Int], rightMask: Seq[Int],
                         fnode: fplan.LeftOuterJoin,
                         left: TNode, right: TNode
                        ) extends BinaryTNode with EquiJoinLike {}

case class ThetaLeftOuterJoin(leftMask: Seq[Int], rightMask: Seq[Int],
                              condition: Expression,
                              fnode: fplan.ThetaLeftOuterJoin,
                              left: TNode, right: TNode) extends BinaryTNode with EquiJoinLike {}

case class Create(fnode: fplan.Create, child: TNode) extends UnaryTNode {}

case class Delete(fnode: fplan.Delete, child: TNode) extends UnaryTNode {}

case class Merge(fnode: fplan.Merge, child: TNode) extends UnaryTNode {}

case class SetNode(fnode: fplan.SetNode, child: TNode) extends UnaryTNode {}

case class Remove(fnode: fplan.Remove, child: TNode) extends UnaryTNode {}
