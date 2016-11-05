package ingraph.cypher2relalg.factories

import relalg.EdgeLabel
import relalg.RelalgContainer

class EdgeLabelFactory extends NamedElementFactory<EdgeLabel> {
	
	new(RelalgContainer container) {
		super(container)
	}
	
	override createSpecificNamedElement() {
		createEdgeLabel
	}
}
