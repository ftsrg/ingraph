package ingraph.relalg.expressions

import relalg.AttributeVariable
import relalg.ExpressionVariable
import relalg.Variable
import relalg.VariableExpression

class ExpressionUnwrapper {

	def static Variable extractBaseVariable(AttributeVariable av) {
		if (av.element !== null && av.expVar === null) {
			return av.element
		} else if (av.element === null && av.expVar !== null) {
			return av.expVar.extractExpressionVariable
		} else {
			throw new IllegalStateException(
				"AttributeVariable must have non-null value for element or expVar, but not for both!")
		}
	}

	def static Variable extractExpressionVariable(ExpressionVariable ev) {
		val expression = ev.expression
		if (expression instanceof VariableExpression) {
			extractVariableExpression(expression)
		} else {
			ev
		}
	}

	def static Variable extractVariableExpression(VariableExpression ve) {
		val variable = ve.variable
		if (variable instanceof ExpressionVariable) {
			return extractExpressionVariable(variable)
		} else {
			variable
		}
	}


}
