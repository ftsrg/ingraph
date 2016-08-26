package ingraph.cypher2relalg.factories

import ingraph.cypher2relalg.factories.ElementFactory
import relalg.EdgeLabel

class EdgeLabelFactory extends ElementFactory<EdgeLabel> {
	override createSpecificNamedElement() {
		createEdgeLabel
	}
}
