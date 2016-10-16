package ingraph.cypher2relalg.factories

import relalg.EdgeVariable
import relalg.RelationalAlgebraContainer

class EdgeVariableFactory extends VariableFactory<EdgeVariable> {
	
	new(RelationalAlgebraContainer container) {
		super(container)
	}
	
	override createSpecificNamedElement() {
		createEdgeVariable
	}
}
