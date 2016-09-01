package ingraph.cypher2relalg.factories

import relalg.EdgeVariable
import relalg.Container

class EdgeVariableFactory extends VariableFactory<EdgeVariable> {
	
	new(Container container) {
		super(container)
	}
	
	override createSpecificNamedElement() {
		createEdgeVariable
	}
}
