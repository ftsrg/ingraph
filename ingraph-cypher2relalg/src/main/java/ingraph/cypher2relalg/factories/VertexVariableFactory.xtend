package ingraph.cypher2relalg.factories

import relalg.VertexVariable
import relalg.RelalgContainer

class VertexVariableFactory extends VariableFactory<VertexVariable> {
	
	new(RelalgContainer container) {
		super(container)
	}
	
	override createSpecificNamedElement() {
		createVertexVariable
	}
}
