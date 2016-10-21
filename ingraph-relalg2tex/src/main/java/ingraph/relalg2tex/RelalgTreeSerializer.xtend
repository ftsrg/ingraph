package ingraph.relalg2tex

import relalg.AllDifferentOperator
import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
import relalg.Operator
import relalg.NullaryOperator
import relalg.UnaryOperator
import relalg.BinaryOperator
import relalg.Cardinality

class RelalgTreeSerializer extends AbstractRelalgSerializer {

	val includeCardinality = true

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
	def CharSequence toNode(Operator op) {
		'''
«««		Optimization: an AllDifferent operator with a single edge variable is not useful at all.
«««		«IF (expression instanceof AllDifferentOperator) && (expression as AllDifferentOperator).edgeVariables.length <= 1»
«««			«toNode((expression as AllDifferentOperator).getInput)»
«««		«ELSE»
			[
			{$«op?.operatorToTex»$ \\
			\footnotesize $\color{gray} \langle \var{«op.schema.map[ name.escape ].join(', ')»} \rangle$
			«IF includeCardinality» \\ \footnotesize \# «op.cardinality.prettyPrint»«ENDIF»}''' +
		'''«op?.children»''' + // invoke children
		'''
			«IF op instanceof NullaryOperator»,tier=input,for tree={blue,densely dashed}«ENDIF»
			]
«««		«ENDIF»
		'''
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

	def dispatch children(UnaryOperator op) {
		'''

				«op.getInput?.toNode»
		'''
	}

	def dispatch children(BinaryOperator op) {
		'''

				«op.getLeftInput.toNode»
				«op.getRightInput.toNode»
		'''
	}

	def prettyPrint(Cardinality cardinality) {
		return String.format("%.02f", cardinality?.value)
	}

}
