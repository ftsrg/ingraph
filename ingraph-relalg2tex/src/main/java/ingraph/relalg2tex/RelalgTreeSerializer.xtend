package ingraph.relalg2tex

import relalg.AllDifferentOperator
import relalg.AlphaOperator
import relalg.BetaOperator
import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
import relalg.Operator
import relalg.NullaryOperator

class RelalgTreeSerializer extends AbstractRelalgSerializer {

	new() {
		super(true)
	}

	new(boolean standaloneDocument) {
		super(standaloneDocument)
	}

	override serializeBody(Operator expression) {
		'''
			\begin{forest} for tree={align=center}
			«toNode(expression)»
			;
			\end{forest}
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
			[{$«expression?.operatorSymbol»$ \\ \footnotesize $\color{gray} \langle \var{«expression.schema.map[ name.escape ].join(', ')»} \rangle$}«expression?.children»
			«IF expression instanceof NullaryOperator»,tier=input,for tree={blue,densely dashed}«ENDIF»]
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
