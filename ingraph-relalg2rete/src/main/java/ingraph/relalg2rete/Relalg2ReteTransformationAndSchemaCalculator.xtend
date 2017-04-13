package ingraph.relalg2rete

import ingraph.relalg.calculators.OneStepSchemaCalculator
import relalg.RelalgContainer

class Relalg2ReteTransformationAndSchemaCalculator {

	static val relalg2rete = new Relalg2ReteTransformation
	static val oneStepSchemaCalculator = new OneStepSchemaCalculator

	def static apply(RelalgContainer relalg) {
		oneStepSchemaCalculator.calculateSchema(relalg2rete.transformToRete(relalg))
	}

}
