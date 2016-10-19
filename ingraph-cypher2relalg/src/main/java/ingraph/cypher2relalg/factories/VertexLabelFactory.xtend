package ingraph.cypher2relalg.factories

import relalg.VertexLabel
import relalg.RelalgContainer

class VertexLabelFactory extends ElementFactory<VertexLabel> {
	
	new(RelalgContainer container) {
		super(container)
	}
	
	override createSpecificNamedElement() {
		createVertexLabel
	}
}
