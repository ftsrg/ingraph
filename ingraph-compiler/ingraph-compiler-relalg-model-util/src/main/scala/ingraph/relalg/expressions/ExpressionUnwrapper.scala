package ingraph.relalg.expressions

import relalg.{AttributeVariable, ExpressionVariable, Variable, VariableExpression}

object ExpressionUnwrapper {

  def extractBaseVariable(av: AttributeVariable): Variable  = {
    if (av.getElement != null && av.getExpVar == null) {
      return av.getElement
    } else if (av.getElement == null && av.getExpVar != null) {
      return extractExpressionVariable(av.getExpVar)
    } else {
      throw new IllegalStateException(
        "AttributeVariable must have non-null value for element or expVar, but not for both!")
    }
  }

  def extractExpressionVariable(ev: ExpressionVariable): Variable  = {
    val expression = ev.getExpression
    expression match {
      case ve: VariableExpression => extractVariableExpression(ve)
      case _ => ev
    }
  }

  def extractVariableExpression(ve: VariableExpression): Variable  = {
    val variable = ve.getVariable
    variable match {
      case ev: ExpressionVariable => extractExpressionVariable(ev)
      case _ => variable
    }
  }

}
