package ingraph.relalg2rete

import ingraph.logger.IngraphLogger
import ingraph.optimization.patterns.EmptyAllDifferentOperatorMatcher
import ingraph.optimization.patterns.UnnecessaryJoinMatcher
import ingraph.optimization.transformations.AbstractRelalgTransformation
import relalg.RelalgContainer

class SimplifyingTransformation extends AbstractRelalgTransformation {

	extension IngraphLogger logger = new IngraphLogger(SimplifyingTransformation.name)

	new(RelalgContainer container) {
		super(container)
	}
	
	def simplify() {
		info("Simplifying relational algebra expression")

		if (container.simplifiedPlan || container.incrementalPlan) {
			throw new IllegalStateException(
				"The query plan is already simplified or incremental. Simplfication transformation should be invoked on a non-simplified search plan")
		}

		statements.fireWhilePossible(unnecessaryJoinOperatorRule)
		statements.fireWhilePossible(emptyAllDifferentOperatorRule)
		container.simplifiedPlan = true
		return container
	}
	
	/**
	 * [a] Remove unnecessary JoinOperators
	 */
	protected def unnecessaryJoinOperatorRule() {
		createRule() //
		.precondition(UnnecessaryJoinMatcher.querySpecification) //
		.action [ //
			info('''unnecessaryJoinOperatorRule fired for «equiJoinLikeOperator»''')
			changeChildOperator(parentOperator, equiJoinLikeOperator, leftInputOperator)
		].build
	}

	/**
	 * [b] Remove unnecessary AllDifferentOperators
	 */
	protected def emptyAllDifferentOperatorRule() {
		createRule() //
		.precondition(EmptyAllDifferentOperatorMatcher.querySpecification) //
		.action [ //
			info('''emptyAllDifferentOperatorRule fired for «allDifferentOperator»''')
			changeChildOperator(parentOperator, allDifferentOperator, inputOperator)
		].build
	}

}
