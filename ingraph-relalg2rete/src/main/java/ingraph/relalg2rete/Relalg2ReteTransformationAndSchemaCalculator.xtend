package ingraph.relalg2rete

import ingraph.relalg.calculators.OneStepSchemaCalculator
import relalg.RelalgContainer
import ingraph.relalg.util.RelalgUtil

class Relalg2ReteTransformationAndSchemaCalculator {

	val relalg2rete = new Relalg2ReteTransformation
	val oneStepSchemaCalculator = new OneStepSchemaCalculator

	def apply(RelalgContainer searchPlan) {
		RelalgUtil.save(searchPlan, '''search-plan-«searchPlan.name»''')
		val retePlan = relalg2rete.transformToRete(searchPlan);
		RelalgUtil.save(retePlan, '''rete-plan-«retePlan.name»''')
		val retePlanWithSchema = oneStepSchemaCalculator.calculateSchema(retePlan)
		RelalgUtil.save(retePlanWithSchema, '''rete-plan-with-schema-«retePlanWithSchema.name»''')
		return retePlanWithSchema
	}

}
