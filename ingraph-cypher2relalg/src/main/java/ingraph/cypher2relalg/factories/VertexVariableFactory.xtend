package ingraph.cypher2relalg.factories

import org.slizaa.neo4j.opencypher.openCypher.NodePattern
import relalg.RelalgContainer
import relalg.VertexVariable

class VertexVariableFactory extends VariableFactory<VertexVariable> {

	new(RelalgContainer container) {
		super(container)
	}

	override createSpecificNamedElement() {
		createVertexVariable => [
			vertexLabelSet = createVertexLabelSet
		]
	}

	def createElement(NodePattern rd) {
		if (rd.variable !== null) {
			createElement(rd.variable.name)
		} else {
			createDontCareElement(rd)
		}
	}
}
