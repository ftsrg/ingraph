package ingraph.relalg2tex

import relalg.AllDifferentOperator
import relalg.AlphaOperator
import relalg.BetaOperator
import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
import relalg.Operator

class RelalgTreeSerializer extends AbstractRelalgSerializer {

	new() {
		super(true)
	}

	override serializeBody(Operator expression) {
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
	 
	def dispatch CharSequence toNode(Operator expression) {
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
