package ingraph.cypher2relalg.factories

import relalg.VertexLabel
import relalg.RelalgContainer

class VertexLabelFactory extends NamedElementFactory<VertexLabel> {

	new(RelalgContainer container) {
		super(container)
	}

	override createSpecificNamedElement() {
		createVertexLabel
	}
}
