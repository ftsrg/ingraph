package ingraph.relalg2tex

import relalg.AlgebraExpression
import relalg.AlphaOperator
import relalg.BetaOperator
import relalg.GetVerticesOperator

class AlgebraTreeDrawer extends TexSerializer {

	new(boolean full) {
		super(full)
	}

	override serializeBody(AlgebraExpression expression) {
		'''
			\begin{tikzpicture}[]
			\Tree
			«toNode(expression)»
			;
			\end{tikzpicture}
		'''
	}

	/**
	 * toNode
	 */
	def CharSequence toNode(AlgebraExpression expression) {
		'''
		[. {$«expression?.operatorSymbol»$}«expression?.children»
		]'''
	}

	/**
	 * childrent
	 */
	def dispatch children(GetVerticesOperator op) {
		''''''
	}

	def dispatch children(AlphaOperator op) {
		'''
			
				«op.input.toNode»
		'''
	}

	def dispatch children(BetaOperator op) {
		'''
			
				«op.leftInput.toNode»
				«op.rightInput.toNode»
		'''
	}

}
