package ingraph.relalg2tex

import relalg.AlgebraExpression
import relalg.AlphaOperator
import relalg.BetaOperator
import relalg.GetVerticesOperator
import relalg.GetEdgesOperator
import relalg.AllDifferentOperator

class RelAlgTreeDrawer extends RelAlgUtil {

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
	def dispatch CharSequence toNode(AllDifferentOperator expression) {
		'''
		«IF expression.edgeVariables.empty»
		«toNode(expression.input)»
		«ELSE»
		[. {$«expression?.operatorSymbol»$}«expression?.children»
		]
		«ENDIF»'''
	}
	 
	def dispatch CharSequence toNode(AlgebraExpression expression) {
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
			
				«op.input?.toNode»
		'''
	}

	def dispatch children(BetaOperator op) {
		'''
			
				«op.leftInput.toNode»
				«op.rightInput.toNode»
		'''
	}

}
