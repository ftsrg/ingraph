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
			$«children(expression)»$
		'''
	}

	/**
	 * children
	 */
	def dispatch CharSequence children(GetVerticesOperator op) {
		'''«op.operatorToTex»'''
	}

	def dispatch CharSequence children(GetEdgesOperator op) {
		'''«op.operatorToTex»'''
	}

	def dispatch CharSequence children(UnaryOperator op) {
		'''«op.operatorToTex» \left(«op.getInput.children»\right)'''
	}

	def dispatch CharSequence children(BinaryOperator op) {
		'''«op.getLeftInput.children» «op.operatorToTex» «op.getRightInput.children»'''
	}

	/**
	 * operatorSymbol
	 */
	def dispatch operatorToTex(GetEdgesOperator op) {
		'''\getedges«op.sourceVertexVariable.toTexParameter»«op.targetVertexVariable.toTexParameter»«op.edgeVariable.toTexParameter»'''
	}

}
