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

	new(boolean standaloneDocument, boolean parentheses) {
		super(standaloneDocument)
		this.parentheses = parentheses
	}

	override serializeBody(Operator expression) {
		'''
		$«children(expression)»$
		'''
	}

	/**
	 * children
	 */
	def dispatch CharSequence children(GetVerticesOperator op) {
		'''«op.operatorSymbol»'''
	}

	def dispatch CharSequence children(GetEdgesOperator op) {
		'''«op.operatorSymbol»'''
	}

	def dispatch CharSequence children(UnaryOperator op) {
		'''«op.operatorSymbol» \left(«op.getInput.children»\right)'''
	}

	def dispatch CharSequence children(BinaryOperator op) {
		'''«op.getLeftInput.children» «op.operatorSymbol» «op.getRightInput.children»'''
	}

}
