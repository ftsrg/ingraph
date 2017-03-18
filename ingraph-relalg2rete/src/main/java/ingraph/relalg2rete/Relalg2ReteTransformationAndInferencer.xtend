package ingraph.relalg2rete

import ingraph.relalg.inferencers.OneStepSchemaInferencer
import relalg.RelalgContainer

class Relalg2ReteTransformationAndInferencer {

	static val relalg2rete = new Relalg2ReteTransformation
	static val oneStepSchemaInferencer = new OneStepSchemaInferencer

	def static apply(RelalgContainer relalg) {
		oneStepSchemaInferencer.inferCompleteSchema(relalg2rete.transformToRete(relalg))
	}

}
