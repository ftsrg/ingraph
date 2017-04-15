package ingraph.relalg2rete

import ingraph.relalg.calculators.OneStepSchemaCalculator
import relalg.RelalgContainer

class Relalg2ReteTransformationAndSchemaCalculator {

	val relalg2rete = new Relalg2ReteTransformation
	val oneStepSchemaCalculator = new OneStepSchemaCalculator

	def apply(RelalgContainer searchPlan) {
		val retePlan = relalg2rete.transformToRete(searchPlan);
		val retePlanWithSchema = oneStepSchemaCalculator.calculateSchema(retePlan)
		return retePlanWithSchema
	}

}
