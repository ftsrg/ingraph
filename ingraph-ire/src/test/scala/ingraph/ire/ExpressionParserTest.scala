package ingraph.ire

import org.scalatest.FlatSpec
import relalg.ArithmeticComparisonOperator
import relalg.impl.{ArithmeticComparisonExpressionImpl, AttributeVariableImpl, StringLiteralImpl}

/**
  * Created by wafle on 9/23/2016.
  */
class ExpressionParserTest extends FlatSpec {
  "equal to" should "be parsed" in {
    val exp = new ArithmeticComparisonExpressionImpl
    val left = new StringLiteralImpl
    left.setValue("emfsucks")
    exp.setLeftOperand(left)

    val right = new AttributeVariableImpl()
    right.setName("test")
    exp.setRightOperand(right)

    exp.setOperator(ArithmeticComparisonOperator.EQUAL_TO)
    val func = ExpressionParser.parse(exp)
    assert(func(Map("test" -> "emfsucks")))
    assert(!func(Map("test" -> "emfrocks")))
  }
}
