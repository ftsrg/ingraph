package ingraph.cypher2relalg.factories

import relalg.EdgeVariable

class EdgeVariableFactory extends ElementFactory<EdgeVariable> {
	override createSpecificNamedElement() {
		createEdgeVariable
	}
}
