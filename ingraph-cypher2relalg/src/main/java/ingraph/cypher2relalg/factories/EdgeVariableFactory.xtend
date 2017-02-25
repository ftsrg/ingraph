package ingraph.cypher2relalg.factories

import org.slizaa.neo4j.opencypher.openCypher.RelationshipDetail
import relalg.EdgeVariable
import relalg.RelalgContainer

class EdgeVariableFactory extends VariableFactory<EdgeVariable> {

	new(RelalgContainer container) {
		super(container)
	}

	override createSpecificNamedElement() {
		createEdgeVariable => [
			edgeLabelSet = createEdgeLabelSet
		]
	}

	def createElement(RelationshipDetail rd) {
		if (rd.variable !== null) {
			createElement(rd.variable.name)
		} else {
			createDontCareElement(rd)
		}
	}
}
