package ingraph.cypher2relalg

import ingraph.antlr.CypherBaseListener
import ingraph.antlr.CypherParser.LabelNameContext
import ingraph.antlr.CypherParser.NodeLabelContext
import ingraph.antlr.CypherParser.NodeLabelsContext
import ingraph.antlr.CypherParser.NodePatternContext
import ingraph.antlr.CypherParser.SymbolicNameContext
import ingraph.antlr.CypherParser.VariableContext
import java.util.HashMap
import relalg.RelalgFactory
import relalg.VertexLabel
import relalg.VertexVariable
import org.eclipse.xtend.lib.annotations.Accessors

class RelalgCypherListener extends CypherBaseListener {

	extension RelalgFactory factory = RelalgFactory.eINSTANCE

	@Accessors val vertexVariables = new HashMap<String, VertexVariable>()
	@Accessors val vertexLabels = new HashMap<String, VertexLabel>() 

	override enterNodePattern(NodePatternContext ctx) {
		val variableCtx = ctx.getChild(VariableContext, 0)
		val nodeLabelsCtx = ctx.getChild(NodeLabelsContext, 0)

		if (variableCtx != null) {
			val vertexVariableName = variableCtx?.getChild(SymbolicNameContext, 0)?.text

			val vertexVariable = createVertexVariable => [name = vertexVariableName]
			vertexVariables.putIfAbsent(vertexVariableName, vertexVariable)


			if (nodeLabelsCtx != null) {
				val vertexLabelName = nodeLabelsCtx.getChild(NodeLabelContext, 0)?.getChild(LabelNameContext, 0)?.getChild(
					SymbolicNameContext, 0)?.text;
				
				val vertexLabel = createVertexLabel => [name = vertexLabelName]
				vertexLabels.putIfAbsent(vertexLabelName, vertexLabel)
			}
		}
	}	
	
}
