package ingraph.optimization.transformations

import ingraph.relalg.calculators.ExternalSchemaCalculator
import relalg.RelalgContainer

class SearchPlanCalculator {

//	val oneStepSchemaCalculator = new OneStepSchemaCalculator
	val externalSchemaCalculator = new ExternalSchemaCalculator

	def apply(RelalgContainer searchPlan) {
		val simplifyingTransformation = new SimplifyingTransformation(searchPlan)
		val simplifiedPlan = simplifyingTransformation.simplify
//		val simplifiedPlanWithSchema = oneStepSchemaCalculator.calculateSchema(simplifiedPlan)
		val simplifiedPlanWithSchema = externalSchemaCalculator.calculateExternalSchema(simplifiedPlan)

		return simplifiedPlanWithSchema
	}

}
