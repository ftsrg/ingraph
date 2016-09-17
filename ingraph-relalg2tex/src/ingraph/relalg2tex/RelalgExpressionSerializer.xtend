package ingraph.relalg2tex

import relalg.AlphaOperator
import relalg.BetaOperator
import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
import relalg.Operator

class RelalgExpressionSerializer extends AbstractRelalgSerializer {

	/**
	 * whether to use parentheses in the TeX expressions
	 */
	boolean parentheses

	new(boolean parentheses) {
		super(true)
		this.parentheses = parentheses
	}

	override serializeBody(Operator expression) {
		'''
		\begin{preview}
		$«children(expression)»$
		\end{preview}
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

	def dispatch CharSequence children(AlphaOperator op) {
		'''«op.operatorSymbol» \left(«op.input.children»\right)'''
	}

	def dispatch CharSequence children(BetaOperator op) {
		'''«op.leftInput.children» «op.operatorSymbol» «op.rightInput.children»'''
	}

}
