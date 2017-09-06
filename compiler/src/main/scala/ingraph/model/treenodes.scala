package ingraph.model.treenodes

import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

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
