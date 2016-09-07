package ingraph.cypher2relalg.cypherlisteners

import ingraph.antlr.CypherBaseListener
import ingraph.antlr.CypherParser
import org.antlr.v4.runtime.RuleContext

/**
 * Overrides all enter* methods of the CyherListener to throw
 * UnsupportedException until overridden.
 *
 * Some methods are defined not to throw
 */
class RelalgBaseUnsupportedCypherListener extends CypherBaseListener {
	def i_am_unsupported(RuleContext ctx) {
		throw new UnsupportedOperationException(ctx.text)
	}
	override enterCypher(CypherParser.CypherContext ctx) { } // top-level rule
	override enterStatement(CypherParser.StatementContext ctx) { } // just a passthrough to query
	override enterQuery(CypherParser.QueryContext ctx) { i_am_unsupported(ctx); }
	// built up of singleQuery and union, which will decide on (un)supportedness
	override enterRegularQuery(CypherParser.RegularQueryContext ctx) { }
	override enterSingleQuery(CypherParser.SingleQueryContext ctx) { i_am_unsupported(ctx); }
	override enterUnion(CypherParser.UnionContext ctx) { i_am_unsupported(ctx); }
	override enterClause(CypherParser.ClauseContext ctx) { } // parts will decide on (un)supportedness
	override enterMatch(CypherParser.MatchContext ctx) { i_am_unsupported(ctx); }
	override enterUnwind(CypherParser.UnwindContext ctx) { i_am_unsupported(ctx); }
	override enterMerge(CypherParser.MergeContext ctx) { i_am_unsupported(ctx); }
	override enterMergeAction(CypherParser.MergeActionContext ctx) { i_am_unsupported(ctx); }
	override enterCreate(CypherParser.CreateContext ctx) { i_am_unsupported(ctx); }
	override enterSet(CypherParser.SetContext ctx) { i_am_unsupported(ctx); }
	override enterSetItem(CypherParser.SetItemContext ctx) { i_am_unsupported(ctx); }
	override enterDelete(CypherParser.DeleteContext ctx) { i_am_unsupported(ctx); }
	override enterRemove(CypherParser.RemoveContext ctx) { i_am_unsupported(ctx); }
	override enterRemoveItem(CypherParser.RemoveItemContext ctx) { i_am_unsupported(ctx); }
	override enterWith(CypherParser.WithContext ctx) { i_am_unsupported(ctx); }
	override enterReturn(CypherParser.ReturnContext ctx) { i_am_unsupported(ctx); }
	override enterReturnBody(CypherParser.ReturnBodyContext ctx) { i_am_unsupported(ctx); }
	override enterReturnItems(CypherParser.ReturnItemsContext ctx) { } // * handled in returnBody, retirnItem handled there
	override enterReturnItem(CypherParser.ReturnItemContext ctx) { i_am_unsupported(ctx); }
	override enterOrder(CypherParser.OrderContext ctx) { i_am_unsupported(ctx); }
	override enterSkip(CypherParser.SkipContext ctx) { i_am_unsupported(ctx); }
	override enterLimit(CypherParser.LimitContext ctx) { i_am_unsupported(ctx); }
	override enterSortItem(CypherParser.SortItemContext ctx) { i_am_unsupported(ctx); }
	override enterWhere(CypherParser.WhereContext ctx) { i_am_unsupported(ctx); }
	override enterPattern(CypherParser.PatternContext ctx) { i_am_unsupported(ctx); }
	override enterPatternPart(CypherParser.PatternPartContext ctx) { i_am_unsupported(ctx); }
	override enterAnonymousPatternPart(CypherParser.AnonymousPatternPartContext ctx) { } // just a passthrough to patternElement
	override enterPatternElement(CypherParser.PatternElementContext ctx) { i_am_unsupported(ctx); }
	override enterNodePattern(CypherParser.NodePatternContext ctx) { i_am_unsupported(ctx); }
	override enterPatternElementChain(CypherParser.PatternElementChainContext ctx) { i_am_unsupported(ctx); }
	override enterRelationshipPattern(CypherParser.RelationshipPatternContext ctx) { } // processed directly in patternElementChain
	override enterRelationshipDetail(CypherParser.RelationshipDetailContext ctx) { i_am_unsupported(ctx); }
	override enterProperties(CypherParser.PropertiesContext ctx) { i_am_unsupported(ctx); }
	override enterRelationshipTypes(CypherParser.RelationshipTypesContext ctx) { } // processed directly in relationshipDetail (has TODO to handle multiple types)
	override enterNodeLabels(CypherParser.NodeLabelsContext ctx) { } // list of nodeLabel
	override enterNodeLabel(CypherParser.NodeLabelContext ctx) { } // text node, prepended with :
	override enterRangeLiteral(CypherParser.RangeLiteralContext ctx) { i_am_unsupported(ctx); }
	override enterLabelName(CypherParser.LabelNameContext ctx) { } // text node
	override enterRelTypeName(CypherParser.RelTypeNameContext ctx) { }  // processed directly in relationshipDetail (has TODO to handle multiple type)

	// expression...
	override enterExpression(CypherParser.ExpressionContext ctx) { }
	override enterExpression12(CypherParser.Expression12Context ctx) { }
	override enterExpression11(CypherParser.Expression11Context ctx) { }
	override enterExpression10(CypherParser.Expression10Context ctx) { }
	override enterExpression9(CypherParser.Expression9Context ctx) { }
	override enterExpression8(CypherParser.Expression8Context ctx) { }
	override enterExpression7(CypherParser.Expression7Context ctx) { }
	override enterExpression6(CypherParser.Expression6Context ctx) { }
	override enterExpression5(CypherParser.Expression5Context ctx) { }
	override enterExpression4(CypherParser.Expression4Context ctx) { }
	override enterExpression3(CypherParser.Expression3Context ctx) { }
	override enterExpression2(CypherParser.Expression2Context ctx) { }
	override enterAtom(CypherParser.AtomContext ctx) { }
	override enterPartialComparisonExpression(CypherParser.PartialComparisonExpressionContext ctx) { }
	override enterParenthesizedExpression(CypherParser.ParenthesizedExpressionContext ctx) { }

	override enterRelationshipsPattern(CypherParser.RelationshipsPatternContext ctx) { i_am_unsupported(ctx); }
	override enterFilterExpression(CypherParser.FilterExpressionContext ctx) { i_am_unsupported(ctx); }
	override enterIdInColl(CypherParser.IdInCollContext ctx) { i_am_unsupported(ctx); }
	override enterFunctionInvocation(CypherParser.FunctionInvocationContext ctx) { i_am_unsupported(ctx); }
	override enterFunctionName(CypherParser.FunctionNameContext ctx) { } // handled in enterFunctionInvocation
	override enterListComprehension(CypherParser.ListComprehensionContext ctx) { i_am_unsupported(ctx); }
	override enterPropertyLookup(CypherParser.PropertyLookupContext ctx) { } // expr
	override enterVariable(CypherParser.VariableContext ctx) { }
	override enterNumberLiteral(CypherParser.NumberLiteralContext ctx) { }
	override enterMapLiteral(CypherParser.MapLiteralContext ctx) { i_am_unsupported(ctx); }
	override enterParameter(CypherParser.ParameterContext ctx) { i_am_unsupported(ctx); }
	override enterPropertyExpression(CypherParser.PropertyExpressionContext ctx) { }
	override enterPropertyKeyName(CypherParser.PropertyKeyNameContext ctx) { }

	// literals/text nodes and such
	override enterIntegerLiteral(CypherParser.IntegerLiteralContext ctx) { }
	override enterDoubleLiteral(CypherParser.DoubleLiteralContext ctx) { }
	override enterExponentDecimalReal(CypherParser.ExponentDecimalRealContext ctx) { }
	override enterRegularDecimalReal(CypherParser.RegularDecimalRealContext ctx) { }
	override enterSymbolicName(CypherParser.SymbolicNameContext ctx) { }
	override enterWs(CypherParser.WsContext ctx) { }
	override enterSp(CypherParser.SpContext ctx) { }
	override enterLeftArrowHead(CypherParser.LeftArrowHeadContext ctx) { }
	override enterRightArrowHead(CypherParser.RightArrowHeadContext ctx) { }
	override enterDash(CypherParser.DashContext ctx) { }
	override enterDigit(CypherParser.DigitContext ctx) { }
}