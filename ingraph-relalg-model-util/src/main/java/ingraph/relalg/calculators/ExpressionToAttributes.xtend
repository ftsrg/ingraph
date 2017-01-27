package ingraph.relalg.calculators

import com.google.common.collect.Iterables
import com.google.common.collect.Lists
import java.util.List
import relalg.ArithmeticComparisonExpression
import relalg.AttributeVariable
import relalg.BinaryLogicalExpression
import relalg.Expression
import relalg.UnaryLogicalExpression
import relalg.VariableExpression

class ExpressionToAttributes {

  def dispatch List<AttributeVariable> getAttributes(UnaryLogicalExpression expression) {
    getAttributes(expression.operand)
  }
  
  def dispatch List<AttributeVariable> getAttributes(ArithmeticComparisonExpression expression) {
    Lists.newArrayList(Iterables.concat(
      getAttributes(expression.leftOperand),
      getAttributes(expression.rightOperand)
    ))
  }

  def dispatch List<AttributeVariable> getAttributes(BinaryLogicalExpression expression) {
    Lists.newArrayList(Iterables.concat(
      getAttributes(expression.leftOperand),
      getAttributes(expression.rightOperand)
    ))
  }

  def dispatch List<AttributeVariable> getAttributes(VariableExpression expression) {
    if (expression.variable instanceof AttributeVariable) {
      #[expression.variable as AttributeVariable]
    } else {
      #[]
    }
  }
  
  // default branch: no attributes
  def dispatch List<AttributeVariable> getAttributes(Expression expression) {
    #[]
  }

}
