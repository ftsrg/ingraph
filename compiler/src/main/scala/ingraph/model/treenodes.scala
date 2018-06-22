package ingraph.model.treenodes

import ingraph.compiler.exceptions.UnexpectedTypeException
import ingraph.model.expr.{HasExtraChildren, RichEdgeAttribute}
import ingraph.model.fplan.FNode
import ingraph.model.nplan.NNode
import ingraph.model.qplan.QNode
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan
import org.apache.spark.sql.catalyst.{expressions => cExpr}


abstract class GenericLeafNode[TPlan <: LogicalPlan] extends LogicalPlan {
  override final def children: Seq[TPlan] = Nil
}

abstract class GenericUnaryNode[TPlan <: LogicalPlan] extends LogicalPlan {
  def child: TPlan

  override final def children: Seq[TPlan] = child :: Nil
}

abstract class GenericBinaryNode[TPlan <: LogicalPlan] extends LogicalPlan {
  def left: TPlan
  def right: TPlan

  override final def children: Seq[TPlan] = Seq(left, right)
}

/**
  * IngraphTreeNode is union type of all possible ingraph tree node types.
  */
sealed trait IngraphTreeNodeType
case class ExpressionTreeNode(n: cExpr.Expression) extends IngraphTreeNodeType
//case class FPlanTreeNode(n: FNode) extends IngraphTreeNode
//case class NPlanTreeNode(n: NNode) extends IngraphTreeNode
case class QPlanTreeNode(n: LogicalPlan) extends IngraphTreeNodeType
//case class TPlanTreeNode(n: TNode) extends IngraphTreeNode

object IngraphTreeNodeType {
  def apply(tn: Any): IngraphTreeNodeType = tn match {
    case e: cExpr.Expression => ExpressionTreeNode(e)
    case q: QNode => QPlanTreeNode(q)
    case x => throw new UnexpectedTypeException(x, "IngraphTreeNode construction")
  }
}


/**
  * Represents an Ingraph Tree Node, which is a utility interface to allow access
  * to children using an extended semantics:
  * e.g. theta join node is still a binary node in the sense that it has 2 inputs,
  * but it also has a condition, which should also be accessible in a uniform way.
  * ingraphChildren serves this purpose, but defaults to children.
  */
trait IngraphTreeNode[TreeNodeType <: IngraphTreeNodeType] {
  def ingraphChildren(n: TreeNodeType): Seq[IngraphTreeNodeType]
}

object IngraphTreeNode {

  def apply(tn: Any): IngraphTreeNodeType = IngraphTreeNodeType(tn)

  def ingraphChildren[T <: IngraphTreeNodeType](node: T): Seq[IngraphTreeNodeType] = node match {
    case ExpressionTreeNode(etn) => etn match {
      case hec: HasExtraChildren => (hec.extraChildren ++ hec.children).map(ExpressionTreeNode(_))
      case e => e.children.map(ExpressionTreeNode(_))
    }
    case QPlanTreeNode(q) => q match {
      case n: QNode => n.expressionChildren.map(ExpressionTreeNode(_)) ++ n.children.map(QPlanTreeNode (_) )
      case n => n.children.map(QPlanTreeNode (_))
    }
  }

  /**
    * Find the first node od IngraphTreeNodeType that satisfies the predicate `p`.
    * The predicate is applied in a pre-order way starting from `startNode`.
    */
  def find(p: IngraphTreeNodeType => Boolean, startNode: IngraphTreeNodeType): Option[IngraphTreeNodeType] = {
    if (p(startNode)) {
      Some(startNode)
    } else {
      ingraphChildren(startNode).foldLeft[Option[IngraphTreeNodeType]](Option.empty)( (b, a) => b.orElse(find(p, a)))
    }
  }
}
