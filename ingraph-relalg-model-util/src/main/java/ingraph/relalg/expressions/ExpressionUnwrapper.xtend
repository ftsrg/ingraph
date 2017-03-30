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
			return av.expVar.extract
		} else {
			throw new IllegalStateException(
				"AttributeVariable must have non-null value for element or expVar, but not for both!")
		}
	}

	def static Variable extract(ExpressionVariable ev) {
		val expression = ev.expression
		if (expression instanceof VariableExpression) {
			val variable = expression.variable
			if (variable instanceof ExpressionVariable) {
				return extract(variable)
			} else {
				variable
			}
		}
	}
}
