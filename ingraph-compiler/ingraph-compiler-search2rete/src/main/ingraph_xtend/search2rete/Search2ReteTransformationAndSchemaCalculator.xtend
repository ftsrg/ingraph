package ingraph.search2rete

import ingraph.optimization.transformations.SimplifyingTransformation
import ingraph.relalg.calculators.OneStepSchemaCalculator
import relalg.RelalgContainer

class Search2ReteTransformationAndSchemaCalculator {

	val oneStepSchemaCalculator = new OneStepSchemaCalculator

	def apply(RelalgContainer searchPlan) {
		val simplifyingTransformation = new SimplifyingTransformation(searchPlan)
		val simplifiedPlan = simplifyingTransformation.simplify

		val search2rete = new Search2ReteTransformation(simplifiedPlan)
		val retePlan = search2rete.transformToRete

		val retePlanWithSchema = oneStepSchemaCalculator.calculateSchema(retePlan)

		return retePlanWithSchema
	}

}
