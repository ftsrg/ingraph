package ingraph.cypher2relalg.cypherlisteners

import ingraph.antlr.CypherParser.LabelNameContext
import ingraph.antlr.CypherParser.LeftArrowHeadContext
import ingraph.antlr.CypherParser.MatchContext
import ingraph.antlr.CypherParser.NodeLabelContext
import ingraph.antlr.CypherParser.NodeLabelsContext
import ingraph.antlr.CypherParser.NodePatternContext
import ingraph.antlr.CypherParser.PatternContext
import ingraph.antlr.CypherParser.PatternElementChainContext
import ingraph.antlr.CypherParser.PatternElementContext
import ingraph.antlr.CypherParser.PatternPartContext
import ingraph.antlr.CypherParser.QueryContext
import ingraph.antlr.CypherParser.RelTypeNameContext
import ingraph.antlr.CypherParser.RelationshipDetailContext
import ingraph.antlr.CypherParser.RelationshipTypesContext
import ingraph.antlr.CypherParser.RightArrowHeadContext
import ingraph.antlr.CypherParser.SingleQueryContext
import ingraph.antlr.CypherParser.SymbolicNameContext
import ingraph.antlr.CypherParser.VariableContext
import ingraph.cypher2relalg.factories.EdgeLabelFactory
import ingraph.cypher2relalg.factories.EdgeVariableFactory
import ingraph.cypher2relalg.factories.VertexLabelFactory
import ingraph.cypher2relalg.factories.VertexVariableFactory
import java.util.ArrayList
import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors
import relalg.AlgebraExpression
import relalg.Direction
import relalg.EdgeVariable
import relalg.ExpandOperator
import relalg.GetVerticesOperator
import relalg.JoinOperator
import relalg.ProductionOperator
import relalg.VertexVariable

class RelalgCypherListener extends RelalgBaseCypherListener {

	@Accessors val vertexVariableFactory = new VertexVariableFactory
	@Accessors val edgeVariableFactory = new EdgeVariableFactory

	@Accessors val vertexLabelFactory = new VertexLabelFactory
	@Accessors val edgeLabelFactory = new EdgeLabelFactory

	@Accessors(value=PUBLIC_GETTER) var ProductionOperator rootExpression

	/*
	 * List of single queries.
	 * 
	 * FIXME: union should be made upon multiple elements.
	 */
	var List<AlgebraExpression> query_SingleQueryList

	override enterQuery(QueryContext ctx) {
		query_SingleQueryList = new ArrayList<AlgebraExpression>
	}

	override exitQuery(QueryContext ctx) {
		val i = query_SingleQueryList.iterator
		if (i.hasNext) { // FIXME: union of multiple singleQuery's
			rootExpression = createProductionOperator
			rootExpression.input = i.next
		}
	}

	/*
	 * List of relalg subtrees for each MATCH clause.
	 * 
	 * Should be joined together upon exitSingleQuery.
	 */
	var List<AlgebraExpression> singleQuery_MatchList

	override enterSingleQuery(SingleQueryContext ctx) {
		singleQuery_MatchList = new ArrayList<AlgebraExpression>
	}

	override exitSingleQuery(SingleQueryContext ctx) {
		query_SingleQueryList.add(buildLeftDeepTree(typeof(JoinOperator), singleQuery_MatchList?.iterator))
	}

	var AlgebraExpression match_AlgebraExpression

	// TODO: where
	override enterMatch(MatchContext ctx) {
		match_AlgebraExpression = null
	}

	override exitMatch(MatchContext ctx) {
		if (match_AlgebraExpression != null) {
			singleQuery_MatchList.add(match_AlgebraExpression)
		}
	}

	var List<AlgebraExpression> pattern_PatternPartList

	override enterPattern(PatternContext ctx) {
		pattern_PatternPartList = new ArrayList<AlgebraExpression>
	}

	override exitPattern(PatternContext ctx) {
		match_AlgebraExpression = createAllDifferentOperator => [
			input = buildLeftDeepTree(typeof(JoinOperator), pattern_PatternPartList?.iterator)
		]
	}

	var AlgebraExpression patternPart_AlgebraExpression

	override enterPatternPart(PatternPartContext ctx) {
		patternPart_AlgebraExpression = null
	}

	override exitPatternPart(PatternPartContext ctx) {
		if (patternPart_AlgebraExpression != null) {
			pattern_PatternPartList.add(patternPart_AlgebraExpression)
		}
	}

	var List<ExpandOperator> patternElement_ExpandList
	var GetVerticesOperator patternElement_GetVerticesOperator

	override enterPatternElement(PatternElementContext ctx) {
		if (ctx.getChild(PatternElementContext, 0) == null) {
			// this is the most inner non-parenthesized patternElement, so we will process this
			patternElement_ExpandList = new ArrayList<ExpandOperator>
			patternElement_GetVerticesOperator = null
		}
	}

	override exitPatternElement(PatternElementContext ctx) {
		if (ctx.getChild(PatternElementContext, 0) == null) {
			// this is the most inner non-parenthesized patternElement, so we assemble the relalg model part
			// chain expand operators together and add sourceVertexVariables
			var lastVertexVariable = patternElement_GetVerticesOperator.vertexVariable
			var AlgebraExpression lastAlgebraExpression = patternElement_GetVerticesOperator

			for (ExpandOperator op : patternElement_ExpandList) {
				op.sourceVertexVariable = lastVertexVariable
				op.input = lastAlgebraExpression

				lastVertexVariable = op.targetVertexVariable
				lastAlgebraExpression = op
			}
		}

	// FIXME "return"
	}

	var EdgeVariable patternElementChain_EdgeVariable
	var VertexVariable patternElementChain_VertexVariable

	override enterPatternElementChain(PatternElementChainContext ctx) {
		patternElementChain_EdgeVariable = null
		patternElementChain_VertexVariable = null
	}

	override exitPatternElementChain(PatternElementChainContext ctx) {
		if (patternElementChain_EdgeVariable == null) { // variable was omitted: we create a dontCare variable
			patternElementChain_EdgeVariable = edgeVariableFactory.createElement(null)
		}
		val isLeftArrow = ctx.getChild(LeftArrowHeadContext, 0) != null
		val isRightArrow = ctx.getChild(RightArrowHeadContext, 0) != null

		val expandOperator = createExpandOperator() =>
			[
				edgeVariable = patternElementChain_EdgeVariable;
				direction = if(isLeftArrow && isRightArrow || ! (isLeftArrow || isRightArrow)) Direction.
					BOTH else if(isLeftArrow) Direction.IN else Direction.OUT;
				targetVertexVariable = patternElementChain_VertexVariable
			]

		if (ctx.parent instanceof PatternElementContext) {
			patternElement_ExpandList.add(expandOperator)
		}
	}

	override enterRelationshipDetail(RelationshipDetailContext ctx) {
		val variableCtx = ctx.getChild(VariableContext, 0)
		val relationshipTypesCtx = ctx.getChild(RelationshipTypesContext, 0)

		val edgeVariableName = variableCtx?.getChild(SymbolicNameContext, 0)?.text
		val edgeVariable = edgeVariableFactory.createElement(edgeVariableName)

		if (relationshipTypesCtx != null) {
			val edgeLabelName = relationshipTypesCtx.getChild(RelTypeNameContext, 0)?.getChild(SymbolicNameContext, 0)?.
				text

			val edgeLabel = edgeLabelFactory.createElement(edgeLabelName)
			edgeVariable.ensureLabel(edgeLabel)
		}

		patternElementChain_EdgeVariable = edgeVariable
	}

	override enterNodePattern(NodePatternContext ctx) {
		val variableCtx = ctx.getChild(VariableContext, 0)
		val nodeLabelsCtx = ctx.getChild(NodeLabelsContext, 0)

		val vertexVariableName = variableCtx?.getChild(SymbolicNameContext, 0)?.text
		val vertexVariable = vertexVariableFactory.createElement(vertexVariableName)

		if (nodeLabelsCtx != null) {
			val vertexLabelName = nodeLabelsCtx.getChild(NodeLabelContext, 0)?.getChild(LabelNameContext, 0)?.getChild(
				SymbolicNameContext, 0)?.text

			val vertexLabel = vertexLabelFactory.createElement(vertexLabelName)
			vertexVariable.ensureLabel(vertexLabel)
		}

		if (ctx.parent instanceof PatternElementChainContext) {
			patternElementChain_VertexVariable = vertexVariable
		} else if (ctx.parent instanceof PatternElementContext) {
			patternElement_GetVerticesOperator = createGetVerticesOperator
			patternElement_GetVerticesOperator.vertexVariable = vertexVariable
		}
	}
}
