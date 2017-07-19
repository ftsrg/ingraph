package ingraph.cypher2relalg.factories

import relalg.RelalgContainer
import relalg.VertexLabel

class VertexLabelFactory extends NamedElementFactory<VertexLabel> {

	new(RelalgContainer container) {
		super(container)
	}

	override createSpecificNamedElement() {
		modelFactory.createVertexLabel
	}
}
