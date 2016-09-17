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
	def CharSequence toNode(
		Operator expression) {
		'''
		«IF (expression instanceof AllDifferentOperator) && (expression as AllDifferentOperator).edgeVariables.length <= 1»
			«toNode((expression as AllDifferentOperator).input)»
		«ELSE»
			[. {$«expression?.operatorSymbol» \{\var{«expression.schema.map[ name.escape ].join(", ")»}\}$}«expression?.children»
			]
		«ENDIF»'''
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
