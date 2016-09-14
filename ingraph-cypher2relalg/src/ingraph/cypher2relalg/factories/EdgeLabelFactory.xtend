package ingraph.cypher2relalg.factories

import ingraph.cypher2relalg.factories.ElementFactory
import relalg.EdgeLabel
import relalg.Container

class EdgeLabelFactory extends ElementFactory<EdgeLabel> {
	
	new(Container container) {
		super(container)
	}
	
	override createSpecificNamedElement() {
		createEdgeLabel
	}
}
