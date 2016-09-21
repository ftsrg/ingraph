package ingraph.cypher2relalg.cypherlisteners

import ingraph.antlr.CypherBaseListener
import ingraph.antlr.CypherParser.AnonymousPatternPartContext
import ingraph.antlr.CypherParser.AtomContext
import ingraph.antlr.CypherParser.ClauseContext
import ingraph.antlr.CypherParser.CreateContext
import ingraph.antlr.CypherParser.CypherContext
import ingraph.antlr.CypherParser.DashContext
import ingraph.antlr.CypherParser.DeleteContext
import ingraph.antlr.CypherParser.DigitContext
import ingraph.antlr.CypherParser.DoubleLiteralContext
import ingraph.antlr.CypherParser.ExponentDecimalRealContext
import ingraph.antlr.CypherParser.Expression10Context
import ingraph.antlr.CypherParser.Expression11Context
import ingraph.antlr.CypherParser.Expression12Context
import ingraph.antlr.CypherParser.Expression2Context
import ingraph.antlr.CypherParser.Expression3Context
import ingraph.antlr.CypherParser.Expression4Context
import ingraph.antlr.CypherParser.Expression5Context
import ingraph.antlr.CypherParser.Expression6Context
import ingraph.antlr.CypherParser.Expression7Context
import ingraph.antlr.CypherParser.Expression8Context
import ingraph.antlr.CypherParser.Expression9Context
import ingraph.antlr.CypherParser.ExpressionContext
import ingraph.antlr.CypherParser.FilterExpressionContext
import ingraph.antlr.CypherParser.FunctionInvocationContext
import ingraph.antlr.CypherParser.FunctionNameContext
import ingraph.antlr.CypherParser.IdInCollContext
import ingraph.antlr.CypherParser.IntegerLiteralContext
import ingraph.antlr.CypherParser.LabelNameContext
import ingraph.antlr.CypherParser.LeftArrowHeadContext
import ingraph.antlr.CypherParser.LimitContext
import ingraph.antlr.CypherParser.ListComprehensionContext
import ingraph.antlr.CypherParser.MapLiteralContext
import ingraph.antlr.CypherParser.MatchContext
import ingraph.antlr.CypherParser.MergeActionContext
import ingraph.antlr.CypherParser.MergeContext
import ingraph.antlr.CypherParser.NodeLabelContext
import ingraph.antlr.CypherParser.NodeLabelsContext
import ingraph.antlr.CypherParser.NodePatternContext
import ingraph.antlr.CypherParser.NumberLiteralContext
import ingraph.antlr.CypherParser.OrderContext
import ingraph.antlr.CypherParser.ParameterContext
import ingraph.antlr.CypherParser.ParenthesizedExpressionContext
import ingraph.antlr.CypherParser.PartialComparisonExpressionContext
import ingraph.antlr.CypherParser.PatternContext
import ingraph.antlr.CypherParser.PatternElementChainContext
import ingraph.antlr.CypherParser.PatternElementContext
import ingraph.antlr.CypherParser.PatternPartContext
import ingraph.antlr.CypherParser.PropertiesContext
import ingraph.antlr.CypherParser.PropertyExpressionContext
import ingraph.antlr.CypherParser.PropertyKeyNameContext
import ingraph.antlr.CypherParser.PropertyLookupContext
import ingraph.antlr.CypherParser.QueryContext
import ingraph.antlr.CypherParser.RangeLiteralContext
import ingraph.antlr.CypherParser.RegularDecimalRealContext
import ingraph.antlr.CypherParser.RegularQueryContext
import ingraph.antlr.CypherParser.RelTypeNameContext
import ingraph.antlr.CypherParser.RelationshipDetailContext
import ingraph.antlr.CypherParser.RelationshipPatternContext
import ingraph.antlr.CypherParser.RelationshipTypesContext
import ingraph.antlr.CypherParser.RelationshipsPatternContext
import ingraph.antlr.CypherParser.RemoveContext
import ingraph.antlr.CypherParser.RemoveItemContext
import ingraph.antlr.CypherParser.ReturnBodyContext
import ingraph.antlr.CypherParser.ReturnContext
import ingraph.antlr.CypherParser.ReturnItemContext
import ingraph.antlr.CypherParser.ReturnItemsContext
import ingraph.antlr.CypherParser.RightArrowHeadContext
import ingraph.antlr.CypherParser.SetContext
import ingraph.antlr.CypherParser.SetItemContext
import ingraph.antlr.CypherParser.SingleQueryContext
import ingraph.antlr.CypherParser.SkipContext
import ingraph.antlr.CypherParser.SortItemContext
import ingraph.antlr.CypherParser.SpContext
import ingraph.antlr.CypherParser.StatementContext
import ingraph.antlr.CypherParser.SymbolicNameContext
import ingraph.antlr.CypherParser.UnionContext
import ingraph.antlr.CypherParser.UnwindContext
import ingraph.antlr.CypherParser.VariableContext
import ingraph.antlr.CypherParser.WhereContext
import ingraph.antlr.CypherParser.WithContext
import ingraph.antlr.CypherParser.WsContext
import org.antlr.v4.runtime.tree.ParseTree

/**
 * Overrides all enter* methods of the CyherListener to throw
 * UnsupportedException until overridden.
 *
 * Some methods are defined not to throw
 */
class RelalgBaseUnsupportedCypherListener extends CypherBaseListener {
	def i_am_unsupported(ParseTree ctx) {
		throw new UnsupportedOperationException(ctx.text)
	}
	def i_am_unsupported(String s) {
		throw new UnsupportedOperationException(s)
	}
	override enterCypher(CypherContext ctx) { } // top-level rule
	override enterStatement(StatementContext ctx) { } // just a passthrough to query
	override enterQuery(QueryContext ctx) { i_am_unsupported(ctx); }
	// built up of singleQuery and union, which will decide on (un)supportedness
	override enterRegularQuery(RegularQueryContext ctx) { }
	override enterSingleQuery(SingleQueryContext ctx) { i_am_unsupported(ctx); }
	override enterUnion(UnionContext ctx) { i_am_unsupported(ctx); }
	override enterClause(ClauseContext ctx) { } // parts will decide on (un)supportedness
	override enterMatch(MatchContext ctx) { i_am_unsupported(ctx); }
	override enterUnwind(UnwindContext ctx) { i_am_unsupported(ctx); }
	override enterWith(WithContext ctx) { i_am_unsupported(ctx); }
	override enterReturn(ReturnContext ctx) { i_am_unsupported(ctx); }
	override enterReturnBody(ReturnBodyContext ctx) { i_am_unsupported(ctx); }
	override enterReturnItems(ReturnItemsContext ctx) { } // * handled in returnBody, retirnItem handled there
	override enterReturnItem(ReturnItemContext ctx) { i_am_unsupported(ctx); }
	override enterOrder(OrderContext ctx) { i_am_unsupported(ctx); }
	override enterSkip(SkipContext ctx) { i_am_unsupported(ctx); }
	override enterLimit(LimitContext ctx) { i_am_unsupported(ctx); }
	override enterSortItem(SortItemContext ctx) { i_am_unsupported(ctx); }
	override enterWhere(WhereContext ctx) { i_am_unsupported(ctx); }
	override enterPattern(PatternContext ctx) { i_am_unsupported(ctx); }
	override enterPatternPart(PatternPartContext ctx) { i_am_unsupported(ctx); }
	override enterAnonymousPatternPart(AnonymousPatternPartContext ctx) { } // just a passthrough to patternElement
	override enterPatternElement(PatternElementContext ctx) { i_am_unsupported(ctx); }
	override enterNodePattern(NodePatternContext ctx) { i_am_unsupported(ctx); }
	override enterPatternElementChain(PatternElementChainContext ctx) { i_am_unsupported(ctx); }
	override enterRelationshipPattern(RelationshipPatternContext ctx) { } // processed directly in patternElementChain
	override enterRelationshipDetail(RelationshipDetailContext ctx) { i_am_unsupported(ctx); }
	override enterProperties(PropertiesContext ctx) { i_am_unsupported(ctx); }
	override enterRelationshipTypes(RelationshipTypesContext ctx) { } // processed directly in relationshipDetail (has TODO to handle multiple types)
	override enterNodeLabels(NodeLabelsContext ctx) { } // list of nodeLabel
	override enterNodeLabel(NodeLabelContext ctx) { } // text node, prepended with :
	override enterRangeLiteral(RangeLiteralContext ctx) { i_am_unsupported(ctx); }
	override enterLabelName(LabelNameContext ctx) { } // text node
	override enterRelTypeName(RelTypeNameContext ctx) { }  // processed directly in relationshipDetail (has TODO to handle multiple type)

	// c/u/d operations
	override enterCreate(CreateContext ctx) { i_am_unsupported(ctx); }
	override enterMerge(MergeContext ctx) { i_am_unsupported(ctx); }
	override enterMergeAction(MergeActionContext ctx) { i_am_unsupported(ctx); }
	override enterSet(SetContext ctx) { i_am_unsupported(ctx); }
	override enterSetItem(SetItemContext ctx) { i_am_unsupported(ctx); }
	override enterDelete(DeleteContext ctx) { i_am_unsupported(ctx); }
	override enterRemove(RemoveContext ctx) { i_am_unsupported(ctx); }
	override enterRemoveItem(RemoveItemContext ctx) { i_am_unsupported(ctx); }

	// expression...
	override enterExpression(ExpressionContext ctx) { }
	override enterExpression12(Expression12Context ctx) { }
	override enterExpression11(Expression11Context ctx) { }
	override enterExpression10(Expression10Context ctx) { }
	override enterExpression9(Expression9Context ctx) { }
	override enterExpression8(Expression8Context ctx) { }
	override enterExpression7(Expression7Context ctx) { }
	override enterExpression6(Expression6Context ctx) { }
	override enterExpression5(Expression5Context ctx) { }
	override enterExpression4(Expression4Context ctx) { }
	override enterExpression3(Expression3Context ctx) { }
	override enterExpression2(Expression2Context ctx) { }
	override enterAtom(AtomContext ctx) { }
	override enterPartialComparisonExpression(PartialComparisonExpressionContext ctx) { }
	override enterParenthesizedExpression(ParenthesizedExpressionContext ctx) { }

	override enterRelationshipsPattern(RelationshipsPatternContext ctx) { i_am_unsupported(ctx); }
	override enterFilterExpression(FilterExpressionContext ctx) { i_am_unsupported(ctx); }
	override enterIdInColl(IdInCollContext ctx) { i_am_unsupported(ctx); }
	override enterFunctionInvocation(FunctionInvocationContext ctx) { i_am_unsupported(ctx); }
	override enterFunctionName(FunctionNameContext ctx) { } // handled in enterFunctionInvocation
	override enterListComprehension(ListComprehensionContext ctx) { i_am_unsupported(ctx); }
	override enterPropertyLookup(PropertyLookupContext ctx) { } // expr
	override enterVariable(VariableContext ctx) { }
	override enterNumberLiteral(NumberLiteralContext ctx) { }
	override enterMapLiteral(MapLiteralContext ctx) { i_am_unsupported(ctx); }
	override enterParameter(ParameterContext ctx) { i_am_unsupported(ctx); }
	override enterPropertyExpression(PropertyExpressionContext ctx) { }
	override enterPropertyKeyName(PropertyKeyNameContext ctx) { }

	// literals/text nodes and such
	override enterIntegerLiteral(IntegerLiteralContext ctx) { }
	override enterDoubleLiteral(DoubleLiteralContext ctx) { }
	override enterExponentDecimalReal(ExponentDecimalRealContext ctx) { }
	override enterRegularDecimalReal(RegularDecimalRealContext ctx) { }
	override enterSymbolicName(SymbolicNameContext ctx) { }
	override enterWs(WsContext ctx) { }
	override enterSp(SpContext ctx) { }
	override enterLeftArrowHead(LeftArrowHeadContext ctx) { }
	override enterRightArrowHead(RightArrowHeadContext ctx) { }
	override enterDash(DashContext ctx) { }
	override enterDigit(DigitContext ctx) { }
}