package ingraph.cypher2relalg.factories

import relalg.VertexVariable

class VertexVariableFactory extends ElementFactory<VertexVariable> {
	override createSpecificNamedElement() {
		createVertexVariable
	}
}
