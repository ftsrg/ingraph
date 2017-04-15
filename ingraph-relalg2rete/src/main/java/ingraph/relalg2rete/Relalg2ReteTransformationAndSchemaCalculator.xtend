package ingraph.relalg2rete

import ingraph.relalg.calculators.OneStepSchemaCalculator
import relalg.RelalgContainer

class Relalg2ReteTransformationAndSchemaCalculator {

	val oneStepSchemaCalculator = new OneStepSchemaCalculator

	def apply(RelalgContainer searchPlan) {
		val relalg2rete = new Relalg2ReteTransformation(searchPlan)
		val retePlan = relalg2rete.transformToRete();
		val retePlanWithSchema = oneStepSchemaCalculator.calculateSchema(retePlan)
		return retePlanWithSchema
	}

}
