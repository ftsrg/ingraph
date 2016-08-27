package ingraph.cypher2relalg.cypherlisteners

import ingraph.antlr.CypherBaseListener
import relalg.VertexVariable
import relalg.VertexLabel
import relalg.EdgeVariable
import relalg.EdgeLabel

class RelalgBaseCypherListener extends CypherBaseListener {
	def ensureLabel(VertexVariable vertexVariable, VertexLabel label) {
		vertexVariable.vertexLabel = label
	}

	def ensureLabel(EdgeVariable edgeVariable, EdgeLabel label) {
		edgeVariable.edgeLabel = label
	}
}