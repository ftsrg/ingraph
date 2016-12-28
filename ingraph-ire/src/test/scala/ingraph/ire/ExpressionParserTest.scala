package ingraph.ire

import org.scalatest.FlatSpec
import relalg.ArithmeticComparisonOperator
import relalg.impl.{ArithmeticComparisonExpressionImpl, AttributeVariableImpl, StringLiteralImpl}
import relalg.RelalgFactory

class ExpressionParserTest extends FlatSpec {
  
  "equal to" should "be parsed" in {
    val factory = RelalgFactory.eINSTANCE
    
    val exp = factory.createArithmeticComparisonExpression
    val left = factory.createStringLiteral
    left.setValue("emfsucks")
    exp.setLeftOperand(left)

    val right = factory.createAttributeVariable
    right.setName("test")
    exp.setRightOperand(right)

    exp.setOperator(ArithmeticComparisonOperator.EQUAL_TO)
    val func = ExpressionParser.parse(exp)
    assert(func(Map("test" -> "emfsucks")))
    assert(!func(Map("test" -> "emfrocks")))
  }
  
}
