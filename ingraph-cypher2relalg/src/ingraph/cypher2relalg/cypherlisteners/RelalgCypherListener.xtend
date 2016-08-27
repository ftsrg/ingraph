package ingraph.cypher2relalg.cypherlisteners

import ingraph.antlr.CypherBaseListener
import ingraph.antlr.CypherParser.CypherContext
import ingraph.antlr.CypherParser.LabelNameContext
import ingraph.antlr.CypherParser.NodeLabelContext
import ingraph.antlr.CypherParser.NodeLabelsContext
import ingraph.antlr.CypherParser.NodePatternContext
import ingraph.antlr.CypherParser.PatternContext
import ingraph.antlr.CypherParser.RelTypeNameContext
import ingraph.antlr.CypherParser.RelationshipDetailContext
import ingraph.antlr.CypherParser.RelationshipTypesContext
import ingraph.antlr.CypherParser.SymbolicNameContext
import ingraph.antlr.CypherParser.VariableContext
import ingraph.cypher2relalg.factories.EdgeVariableFactory
import ingraph.cypher2relalg.factories.VertexVariableFactory
import ingraph.cypher2relalg.factories.VertexLabelFactory
import ingraph.cypher2relalg.factories.EdgeLabelFactory
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.eclipse.xtend.lib.annotations.Accessors
import relalg.EdgeLabel
import relalg.EdgeVariable
import relalg.RelalgFactory
import relalg.VertexLabel
import relalg.VertexVariable

class RelalgCypherListener extends CypherBaseListener {

	protected extension RelalgFactory factory = RelalgFactory.eINSTANCE

	@Accessors val vertexVariableFactory = new VertexVariableFactory
	@Accessors val edgeVariableFactory = new EdgeVariableFactory

	@Accessors val vertexLabelFactory = new VertexLabelFactory
	@Accessors val edgeLabelFactory = new EdgeLabelFactory

	override enterNodePattern(NodePatternContext ctx) {
		val variableCtx = ctx.getChild(VariableContext, 0)
		val nodeLabelsCtx = ctx.getChild(NodeLabelsContext, 0)

		val vertexVariableName = variableCtx?.getChild(SymbolicNameContext, 0)?.text
		val vertexVariable = vertexVariableFactory.createVertexVariable(vertexVariableName)

		if (nodeLabelsCtx != null) {
			val vertexLabelName = nodeLabelsCtx.getChild(NodeLabelContext, 0)?.getChild(LabelNameContext, 0)?.getChild(
				SymbolicNameContext, 0)?.text

			val vertexLabel = vertexLabelFactory.createElement(vertexLabelName)
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

			val edgeLabel = edgeLabelFactory.createElement(edgeLabelName)
			edgeVariable.ensureLabel(edgeLabel)
		}
	}
	
	override enterPattern(PatternContext ctx) { 
		val listener = new PatternListener()
		ParseTreeWalker.DEFAULT.walk(listener, ctx)		
	}

	def ensureLabel(VertexVariable vertexVariable, VertexLabel label) {
		vertexVariable.vertexLabel = label
	}

	def ensureLabel(EdgeVariable edgeVariable, EdgeLabel label) {
		edgeVariable.edgeLabel = label
	}

}
