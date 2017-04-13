package ingraph.relalg2rete

import relalg.RelalgContainer
import ingraph.relalg.inferencers.OneStepSchemaCalculator

class Relalg2ReteTransformationAndSchemaCalculator {

	static val relalg2rete = new Relalg2ReteTransformation
	static val oneStepSchemaCalculator = new OneStepSchemaCalculator

	def static apply(RelalgContainer relalg) {
		oneStepSchemaCalculator.inferCompleteSchema(relalg2rete.transformToRete(relalg))
	}

}
