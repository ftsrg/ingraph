package ingraph.relalg.util

import relalg.EdgeVariable
import relalg.ElementVariable
import relalg.ExpressionVariable
import relalg.VariableExpression
import relalg.VertexVariable

class ElementVariableExtractor {

	/**
	 * Finds and extracts (returns) the enclosed ElementVariable instance.
	 * If not found, null is returned.
	 * 
	 * For a VertexVariable and EdgeVariable, this is idempotent, i.e. returns the variable instance itself.
	 * 
	 * For an ExpressionVariable, see if it wraps a VertexVariable or EdgeVariable, and if so, returns that variable.
	 * Note, that this wrapping can be deep like
	 * (ExpressionVariable wraps VariableExpression wraps)+ ElementVariable
	 */
	def dispatch ElementVariable extractElementVariable(VertexVariable v) {
		v
	}

	def dispatch ElementVariable extractElementVariable(EdgeVariable e) {
		e
	}

	def dispatch ElementVariable extractElementVariable(ExpressionVariable ev) {
		val exp = ev.expression
		if (exp instanceof VariableExpression) {
			extractElementVariable(exp.variable)
		} else {
			throw new IllegalStateException("Cannot extract elementVariable")
		}
	}

}
