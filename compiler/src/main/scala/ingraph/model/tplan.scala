package ingraph.model

import ingraph.model.fplan._
import ingraph.model.treenodes.{GenericBinaryNode, GenericLeafNode, GenericUnaryNode}
import org.apache.spark.sql.catalyst.expressions.{Attribute, NamedExpression}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

trait TNode extends LogicalPlan {
  def extraAttributes: Seq[NamedExpression]
  def fnode: FNode
  override def children: Seq[TNode]
}

abstract class LeafTNode extends GenericLeafNode[TNode] with TNode
abstract class UnaryTNode extends GenericUnaryNode[TNode] with TNode
abstract class BinaryFNode extends GenericBinaryNode[TNode] with TNode
