package relalg.impl

import ingraph.gqo.ExpressionParser
import org.scalatest.FlatSpec
import relalg.{ArithmeticComparisonExpression, ArithmeticComparisonOperator, StringLiteral, Variable}
import relalg.impl._

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
