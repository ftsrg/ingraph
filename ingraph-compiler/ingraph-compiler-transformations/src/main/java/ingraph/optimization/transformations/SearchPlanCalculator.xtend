package ingraph.optimization.transformations

import ingraph.optimization.transformations.SimplifyingTransformation
import relalg.RelalgContainer

class SearchPlanCalculator {

	val oneStepSchemaCalculator = new ingraph.relalg.calculators.OneStepSchemaCalculator

	def apply(RelalgContainer searchPlan) {
		val simplifyingTransformation = new SimplifyingTransformation(searchPlan)
		val simplifiedPlan = simplifyingTransformation.simplify
		val simplifiedPlanWithSchema = oneStepSchemaCalculator.calculateSchema(simplifiedPlan)

		return simplifiedPlanWithSchema
	}

}
