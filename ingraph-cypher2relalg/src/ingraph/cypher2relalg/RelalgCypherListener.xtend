package ingraph.cypher2relalg

import ingraph.antlr.CypherBaseListener
import ingraph.antlr.CypherParser.LabelNameContext
import ingraph.antlr.CypherParser.NodeLabelContext
import ingraph.antlr.CypherParser.NodeLabelsContext
import ingraph.antlr.CypherParser.NodePatternContext
import ingraph.antlr.CypherParser.RelationshipDetailContext
import ingraph.antlr.CypherParser.SymbolicNameContext
import ingraph.antlr.CypherParser.VariableContext
import java.util.HashMap
import org.eclipse.xtend.lib.annotations.Accessors
import relalg.EdgeLabel
import relalg.EdgeVariable
import relalg.RelalgFactory
import relalg.VertexLabel
import relalg.VertexVariable
import ingraph.antlr.CypherParser.RelationshipTypesContext
import ingraph.antlr.CypherParser.RelTypeNameContext

class RelalgCypherListener extends CypherBaseListener {

	extension RelalgFactory factory = RelalgFactory.eINSTANCE

	@Accessors val vertexVariables = new HashMap<String, VertexVariable>()
	@Accessors val vertexLabels = new HashMap<String, VertexLabel>()

	@Accessors val edgeVariables = new HashMap<String, EdgeVariable>()
	@Accessors val edgeLabels = new HashMap<String, EdgeLabel>()

	override enterNodePattern(NodePatternContext ctx) {
		val variableCtx = ctx.getChild(VariableContext, 0)
		val nodeLabelsCtx = ctx.getChild(NodeLabelsContext, 0)

		if (variableCtx != null) {
			val vertexVariableName = variableCtx?.getChild(SymbolicNameContext, 0)?.text

			val vertexVariable = createVertexVariable => [name = vertexVariableName]
			vertexVariables.putIfAbsent(vertexVariableName, vertexVariable)
		}

		if (nodeLabelsCtx != null) {
			val vertexLabelName = nodeLabelsCtx.getChild(NodeLabelContext, 0)?.getChild(LabelNameContext, 0)?.getChild(
				SymbolicNameContext, 0)?.text

			val vertexLabel = createVertexLabel => [name = vertexLabelName]
			vertexLabels.putIfAbsent(vertexLabelName, vertexLabel)
		}
	}

	override enterRelationshipDetail(RelationshipDetailContext ctx) {
		val variableCtx = ctx.getChild(VariableContext, 0)
		val relationshipTypesCtx = ctx.getChild(RelationshipTypesContext, 0)

		if (variableCtx != null) {
			val edgeVariableName = variableCtx?.getChild(SymbolicNameContext, 0)?.text

			val edgeVariable = createEdgeVariable => [name = edgeVariableName]
			edgeVariables.putIfAbsent(edgeVariableName, edgeVariable)
		}

		if (relationshipTypesCtx != null) {
			val edgeLabelName = relationshipTypesCtx.getChild(RelTypeNameContext, 0)?.getChild(SymbolicNameContext, 0)?.
				text

			val edgeLabel = createEdgeLabel => [name = edgeLabelName]
			edgeLabels.putIfAbsent(edgeLabelName, edgeLabel)
		}
	}

}
