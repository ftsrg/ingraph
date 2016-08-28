package ingraph.cypher2relalg.factories

import relalg.EdgeVariable

class EdgeVariableFactory extends VariableFactory<EdgeVariable> {
	override createSpecificNamedElement() {
		createEdgeVariable
	}
}
