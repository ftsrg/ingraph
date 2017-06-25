package ingraph.search2rete

import ingraph.optimization.transformations.AbstractRelalgTransformation
import relalg.RelalgContainer

class Search2ReteTransformation extends AbstractRelalgTransformation {

	new(RelalgContainer container) {
		super(container)
	}

	def transformToRete() {
		info("Transforming relational algebra expression to Rete network")

		if (container.incrementalPlan) {
			throw new IllegalStateException(
				"The relalg plan is already a Rete plan. Search2ReteTransformation should be invoked on a (non-incremental) search plan")
		}
		statements.fireWhilePossible(getVerticesAndExpandOperatorRule)
		statements.fireWhilePossible(defaultExpandOperatorRule)
		statements.fireWhilePossible(transitiveExpandOperatorRule)
		statements.fireWhilePossible(sortAndTopOperatorRule)
		statements.fireWhilePossible(leftOuterJoinAndSelectionRule)
		container.incrementalPlan = true
		return container
	}

}
