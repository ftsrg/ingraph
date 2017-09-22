package ingraph.sandbox

import org.apache.spark.sql.catalyst.expressions.Attribute
import org.apache.spark.sql.catalyst.plans.logical.{LeafNode, LogicalPlan, UnaryNode}
import org.scalatest.FunSuite

trait Plan1Node extends LogicalPlan

case class OpA1() extends LeafNode with Plan1Node {
  override def output: Seq[Attribute] = Seq()
}
case class OpB1(child: Plan1Node) extends UnaryNode with Plan1Node {
  override def output: Seq[Attribute] = Seq()
}

trait Plan2Node extends LogicalPlan

case class OpA2() extends LeafNode with Plan2Node {
  override def output: Seq[Attribute] = Seq()
}
case class OpB2(child: Plan2Node) extends UnaryNode with Plan2Node {
  override def output: Seq[Attribute] = Seq()
}

object Plan1ToPlan2 {
  def transform(plan: Plan1Node): Plan2Node = {
    plan.transformDown {
      case OpA1() => OpA2()
      case OpB1(child) => OpB2(transform(child))
    }
  }.asInstanceOf[Plan2Node]
}

class InferencerSandboxTest extends FunSuite {
  test("inferencer") {
    val p1 = OpB1(OpA1())
    val p2 = Plan1ToPlan2.transform(p1)

    println(p1)
    println(p2)
  }
}

