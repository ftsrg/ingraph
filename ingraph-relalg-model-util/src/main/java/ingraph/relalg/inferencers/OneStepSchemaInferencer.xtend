package ingraph.relalg.inferencers

import relalg.RelalgContainer

class OneStepSchemaInferencer {

	extension BasicSchemaInferencer basicSchemaInferencer = new BasicSchemaInferencer()
	extension ExtraVariableInferencer extraVariableInferencer = new ExtraVariableInferencer()
	extension FullSchemaInferencer fullSchemaInferencer = new FullSchemaInferencer()

	def inferCompleteSchema(RelalgContainer container) {
		container.inferBasicSchema()
		container.inferExtraVariables()
		container.inferFullSchema()
	}

}
