package ingraph.optimization.transformations.reteoptimization

import ingraph.optimization.patterns.CascadableSelectionMatcher
import ingraph.optimization.transformations.AbstractRelalgTransformation
import relalg.RelalgContainer
import ingraph.optimization.patterns.SwappableSelectionMatcher

class ReteOptimization extends AbstractRelalgTransformation {

	def optimize(RelalgContainer container) {
		val statements = register(container)
		statements.fireWhilePossible(cascadingSelectionsRule)
		statements.fireOne(swappableSelectionsRule)
		return container
	}
	
	protected def cascadingSelectionsRule() {
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

	protected def swappableSelectionsRule() {
		createRule() //
		.precondition(SwappableSelectionMatcher.querySpecification) //
		.action [ //
			println("swappableSelections fired")
			selectionOperator1.input = selectionOperator2.input
			selectionOperator2.input = selectionOperator1
			
			changeOperator(parentOperator, selectionOperator1, selectionOperator2)
		].build
	}

}
