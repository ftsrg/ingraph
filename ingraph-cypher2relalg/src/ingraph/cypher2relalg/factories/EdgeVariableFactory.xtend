package ingraph.cypher2relalg.factories

import org.eclipse.xtend.lib.annotations.Accessors
import relalg.EdgeVariable

class EdgeVariableFactory extends ElementFactory<EdgeVariable> {

	@Deprecated
	@Accessors val edgeVariables = elements

	@Deprecated
	def createEdgeVariable(String edgeVariableName) {
		createElement(edgeVariableName)
	}

	override createSpecificNamedElement() {
		createEdgeVariable
	}
}
