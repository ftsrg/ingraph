package ingraph.cypher2relalg.factories

import relalg.EdgeVariable
import relalg.RelalgContainer

class EdgeVariableFactory extends VariableFactory<EdgeVariable> {
	
	new(RelalgContainer container) {
		super(container)
	}
	
	override createSpecificNamedElement() {
		createEdgeVariable
	}
}
