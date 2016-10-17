package ingraph.cypher2relalg.factories

import relalg.EdgeLabel
import relalg.RelationalAlgebraContainer

class EdgeLabelFactory extends ElementFactory<EdgeLabel> {
	
	new(RelationalAlgebraContainer container) {
		super(container)
	}
	
	override createSpecificNamedElement() {
		createEdgeLabel
	}
}
