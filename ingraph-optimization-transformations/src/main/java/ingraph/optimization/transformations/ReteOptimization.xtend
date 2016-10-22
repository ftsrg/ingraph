package ingraph.optimization.transformations

import ingraph.optimization.patterns.CascadableSelectionMatcher
import relalg.RelalgContainer

class ReteOptimization extends AbstractRelalgTransformation {

	def optimize(RelalgContainer container) {
		val statements = register(container)
		statements.fireWhilePossible(cascadeConditionRule)
		return container
	}

	/**
	 * Cascading condition rule
	 */
	protected def cascadeConditionRule() {
		createRule() //
		.precondition(CascadableSelectionMatcher.querySpecification) //
		.action [ //
			println("cascadeConditionRule fired")
			val c = selectionOperator.container

			val selectionOperator = selectionOperator
			val leftOperand = leftOperand
			val rightOperand = rightOperand

			val selectionOperator1 = createSelectionOperator => [
				container = c
				condition = leftOperand
				input = selectionOperator.input
			]
			val selectionOperator2 = createSelectionOperator => [
				container = c
				condition = rightOperand
				input = selectionOperator1
			]
			changeOperator(parentOperator, selectionOperator, selectionOperator2)
		].build
	}

}
