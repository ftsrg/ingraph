package ingraph.relalg.inferencers

import relalg.RelalgContainer

class OneStepSchemaInferencer {

	extension BasicSchemaInferencer basicSchemaInferencer = new BasicSchemaInferencer()
	extension ExtraAttributeInferencer extraAttributeInferencer = new ExtraAttributeInferencer()
	extension FullSchemaInferencer fullSchemaInferencer = new FullSchemaInferencer()

	def inferCompleteSchema(RelalgContainer container) {
		container.inferBasicSchema()
		container.inferExtraAttributes()
		container.inferFullSchema()
	}

}
