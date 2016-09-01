package ingraph.cypher2relalg.factories

import relalg.VertexVariable
import relalg.Container

class VertexVariableFactory extends VariableFactory<VertexVariable> {
	
	new(Container container) {
		super(container)
	}
	
	override createSpecificNamedElement() {
		createVertexVariable
	}
}
