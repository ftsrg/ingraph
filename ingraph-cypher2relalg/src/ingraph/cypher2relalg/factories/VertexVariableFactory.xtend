package ingraph.cypher2relalg.factories

import relalg.VertexVariable

class VertexVariableFactory extends VariableFactory<VertexVariable> {
	override createSpecificNamedElement() {
		createVertexVariable
	}
}
