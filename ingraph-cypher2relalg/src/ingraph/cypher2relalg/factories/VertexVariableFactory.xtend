package ingraph.cypher2relalg.factories

import org.eclipse.xtend.lib.annotations.Accessors
import relalg.VertexVariable

class VertexVariableFactory extends ElementFactory<VertexVariable> {

	@Deprecated
	@Accessors val vertexVariables = elements

	@Deprecated
	def createVertexVariable(String vertexVariableName) {
		createElement(vertexVariableName)
	}

	override createSpecificNamedElement() {
		createVertexVariable
	}
}
