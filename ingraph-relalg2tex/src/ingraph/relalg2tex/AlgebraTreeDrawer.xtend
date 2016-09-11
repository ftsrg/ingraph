package ingraph.relalg2tex

import relalg.AlgebraExpression
import relalg.AlphaOperator
import relalg.BetaOperator
import relalg.GetVerticesOperator
import relalg.GetEdgesOperator

class AlgebraTreeDrawer extends TexSerializer {

	new(boolean full) {
		super(full)
	}

	override serializeBody(AlgebraExpression expression) {
		'''
			\begin{preview}
			\begin{tikzpicture}
			\Tree
			«toNode(expression)»
			;
			\end{tikzpicture}
			\end{preview}
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
	 * children
	 */
	def dispatch children(GetVerticesOperator op) {
		''''''
	}

	def dispatch children(GetEdgesOperator op) {
		''''''
	/**
	 * children
	 */
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
