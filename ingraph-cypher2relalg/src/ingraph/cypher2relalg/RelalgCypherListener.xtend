package ingraph.cypher2relalg

import ingraph.antlr.CypherBaseListener
import ingraph.antlr.CypherParser.LabelNameContext
import ingraph.antlr.CypherParser.NodeLabelContext
import ingraph.antlr.CypherParser.NodeLabelsContext
import ingraph.antlr.CypherParser.NodePatternContext
import ingraph.antlr.CypherParser.RelTypeNameContext
import ingraph.antlr.CypherParser.RelationshipDetailContext
import ingraph.antlr.CypherParser.RelationshipTypesContext
import ingraph.antlr.CypherParser.SymbolicNameContext
import ingraph.antlr.CypherParser.VariableContext
import ingraph.cypher2relalg.factories.EdgeVariableFactory
import java.util.HashMap
import org.eclipse.xtend.lib.annotations.Accessors
import relalg.EdgeLabel
import relalg.EdgeVariable
import relalg.RelalgFactory
import relalg.VertexLabel
import relalg.VertexVariable
import ingraph.cypher2relalg.factories.VertexVariableFactory

class RelalgCypherListener extends CypherBaseListener {

	protected extension RelalgFactory factory = RelalgFactory.eINSTANCE

	@Accessors val vertexVariableFactory = new VertexVariableFactory
	@Accessors val edgeVariableFactory = new EdgeVariableFactory

	@Accessors val vertexLabels = new HashMap<String, VertexLabel>
	@Accessors val edgeLabels = new HashMap<String, EdgeLabel>

	override enterNodePattern(NodePatternContext ctx) {
		val variableCtx = ctx.getChild(VariableContext, 0)
		val nodeLabelsCtx = ctx.getChild(NodeLabelsContext, 0)

		val vertexVariableName = variableCtx?.getChild(SymbolicNameContext, 0)?.text
		val vertexVariable = vertexVariableFactory.createVertexVariable(vertexVariableName)

		if (nodeLabelsCtx != null) {
			val vertexLabelName = nodeLabelsCtx.getChild(NodeLabelContext, 0)?.getChild(LabelNameContext, 0)?.getChild(
				SymbolicNameContext, 0)?.text

			val vertexLabel = createVertexLabel => [name = vertexLabelName]
			vertexLabels.putIfAbsent(vertexLabelName, vertexLabel)
			vertexVariable.ensureLabel(vertexLabel)
		}
	}

	override enterRelationshipDetail(RelationshipDetailContext ctx) {
		val variableCtx = ctx.getChild(VariableContext, 0)
		val relationshipTypesCtx = ctx.getChild(RelationshipTypesContext, 0)

		val edgeVariableName = variableCtx?.getChild(SymbolicNameContext, 0)?.text
		val edgeVariable = edgeVariableFactory.createEdgeVariable(edgeVariableName)

		if (relationshipTypesCtx != null) {
			val edgeLabelName = relationshipTypesCtx.getChild(RelTypeNameContext, 0)?.getChild(SymbolicNameContext, 0)?.
				text

			val edgeLabel = createEdgeLabel => [name = edgeLabelName]
			edgeLabels.putIfAbsent(edgeLabelName, edgeLabel)
			edgeVariable.ensureLabel(edgeLabel)
		}
	}

	def ensureLabel(VertexVariable vertexVariable, VertexLabel label) {
		vertexVariable.vertexLabel = label
	}

	def ensureLabel(EdgeVariable edgeVariable, EdgeLabel label) {
		edgeVariable.edgeLabel = label
	}

}
