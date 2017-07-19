package ingraph.cypher2relalg.factories

import relalg.AttributeVariable
import relalg.RelalgContainer

class AttributeVariableFactory extends VariableFactory<AttributeVariable> {

	new(RelalgContainer container) {
		super(container)
	}

	override createSpecificNamedElement() {
		modelFactory.createAttributeVariable
	}

}
