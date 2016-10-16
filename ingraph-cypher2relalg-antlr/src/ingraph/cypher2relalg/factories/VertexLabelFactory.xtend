package ingraph.cypher2relalg.factories

import relalg.RelationalAlgebraContainer
import relalg.VertexLabel

class VertexLabelFactory extends ElementFactory<VertexLabel> {
	
	new(RelationalAlgebraContainer container) {
		super(container)
	}
	
	override createSpecificNamedElement() {
		createVertexLabel
	}
}
