package ingraph.cypher2relalg.factories

import relalg.RelationalAlgebraContainer
import relalg.VertexVariable

class VertexVariableFactory extends VariableFactory<VertexVariable> {
	
	new(RelationalAlgebraContainer container) {
		super(container)
	}
	
	override createSpecificNamedElement() {
		createVertexVariable
	}
}
