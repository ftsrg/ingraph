package ingraph.relalg2tex

import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
import relalg.Operator
import relalg.UnaryOperator
import relalg.BinaryOperator

class RelalgExpressionSerializer extends AbstractRelalgSerializer {

	/**
	 * whether to use parentheses in the TeX expressions
	 */
	boolean parentheses

	new(boolean standaloneDocument, boolean includeMutualVariables, boolean parentheses) {
		super(standaloneDocument, includeMutualVariables)
		this.parentheses = parentheses
	}

	override serializeBody(Operator expression) {
		'''
			«IF standaloneDocument»$$«ENDIF»
			«children(expression)»
			«IF standaloneDocument»$$«ENDIF»
		'''
	}

	/**
	 * children
	 */
	def dispatch CharSequence children(GetVerticesOperator op) {
		'''«op.operator»'''
	}

	def dispatch CharSequence children(GetEdgesOperator op) {
		'''«op.operator»'''
	}

	def dispatch CharSequence children(UnaryOperator op) {
		'''«op.operator» \Big(«op.input.children»\Big)'''
	}

	def dispatch CharSequence children(BinaryOperator op) {
		'''«op.leftInput.children» «op.operator» «op.rightInput.children»'''
	}

}
