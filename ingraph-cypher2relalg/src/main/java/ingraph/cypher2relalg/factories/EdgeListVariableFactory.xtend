package ingraph.cypher2relalg.factories

import org.slizaa.neo4j.opencypher.openCypher.RelationshipDetail
import relalg.EdgeListVariable
import relalg.RelalgContainer

class EdgeListVariableFactory extends VariableFactory<EdgeListVariable> {

	new(RelalgContainer container) {
		super(container)
	}

	override createSpecificNamedElement() {
		createEdgeListVariable => [
			edgeLabelSet = createEdgeLabelSet
		]
	}

	def createElement(RelationshipDetail rd) {
		if (rd?.variable !== null) {
			createElement(rd.variable.name)
		} else {
			createDontCareElement(rd)
		}
	}
}
