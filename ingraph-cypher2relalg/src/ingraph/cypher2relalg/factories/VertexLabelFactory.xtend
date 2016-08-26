package ingraph.cypher2relalg.factories

import ingraph.cypher2relalg.factories.ElementFactory
import relalg.VertexLabel

class VertexLabelFactory extends ElementFactory<VertexLabel> {
	override createSpecificNamedElement() {
		createVertexLabel
	}
}
