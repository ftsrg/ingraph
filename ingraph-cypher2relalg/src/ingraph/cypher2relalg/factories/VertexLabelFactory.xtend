package ingraph.cypher2relalg.factories

import ingraph.cypher2relalg.factories.ElementFactory
import relalg.VertexLabel
import relalg.Container

class VertexLabelFactory extends ElementFactory<VertexLabel> {
	
	new(Container container) {
		super(container)
	}
	
	override createSpecificNamedElement() {
		createVertexLabel
	}
}
