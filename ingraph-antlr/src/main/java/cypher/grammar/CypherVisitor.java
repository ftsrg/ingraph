package cypher.grammar;// Generated from /home/szarnyasg/git/ingraph/openCypher/Cypher.g4 by ANTLR 4.5.3
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CypherParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CypherVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CypherParser#cypher}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCypher(CypherParser.CypherContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(CypherParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery(CypherParser.QueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#regularQuery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegularQuery(CypherParser.RegularQueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#singleQuery}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSingleQuery(CypherParser.SingleQueryContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#union}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnion(CypherParser.UnionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClause(CypherParser.ClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#match}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatch(CypherParser.MatchContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#unwind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnwind(CypherParser.UnwindContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#merge}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMerge(CypherParser.MergeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#mergeAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMergeAction(CypherParser.MergeActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#create}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreate(CypherParser.CreateContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet(CypherParser.SetContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#setItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSetItem(CypherParser.SetItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#delete}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDelete(CypherParser.DeleteContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#remove}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemove(CypherParser.RemoveContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#removeItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRemoveItem(CypherParser.RemoveItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#with}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWith(CypherParser.WithContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn(CypherParser.ReturnContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#returnBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnBody(CypherParser.ReturnBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#returnItems}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnItems(CypherParser.ReturnItemsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#returnItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnItem(CypherParser.ReturnItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#order}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrder(CypherParser.OrderContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#skip}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSkip(CypherParser.SkipContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#limit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLimit(CypherParser.LimitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#sortItem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSortItem(CypherParser.SortItemContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#where}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhere(CypherParser.WhereContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#pattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPattern(CypherParser.PatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#patternPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatternPart(CypherParser.PatternPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#anonymousPatternPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnonymousPatternPart(CypherParser.AnonymousPatternPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#patternElement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatternElement(CypherParser.PatternElementContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#nodePattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNodePattern(CypherParser.NodePatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#patternElementChain}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatternElementChain(CypherParser.PatternElementChainContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relationshipPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipPattern(CypherParser.RelationshipPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relationshipDetail}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipDetail(CypherParser.RelationshipDetailContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#properties}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProperties(CypherParser.PropertiesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relationshipTypes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipTypes(CypherParser.RelationshipTypesContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#nodeLabels}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNodeLabels(CypherParser.NodeLabelsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#nodeLabel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNodeLabel(CypherParser.NodeLabelContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rangeLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRangeLiteral(CypherParser.RangeLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#labelName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLabelName(CypherParser.LabelNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relTypeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelTypeName(CypherParser.RelTypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(CypherParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression12}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression12(CypherParser.Expression12Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression11}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression11(CypherParser.Expression11Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression10}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression10(CypherParser.Expression10Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression9}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression9(CypherParser.Expression9Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression8}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression8(CypherParser.Expression8Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression7}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression7(CypherParser.Expression7Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression6}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression6(CypherParser.Expression6Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression5}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression5(CypherParser.Expression5Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression4}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression4(CypherParser.Expression4Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression3(CypherParser.Expression3Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#expression2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression2(CypherParser.Expression2Context ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(CypherParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#partialComparisonExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPartialComparisonExpression(CypherParser.PartialComparisonExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#parenthesizedExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesizedExpression(CypherParser.ParenthesizedExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#relationshipsPattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationshipsPattern(CypherParser.RelationshipsPatternContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#filterExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilterExpression(CypherParser.FilterExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#idInColl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdInColl(CypherParser.IdInCollContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#functionInvocation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionInvocation(CypherParser.FunctionInvocationContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#functionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionName(CypherParser.FunctionNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#listComprehension}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitListComprehension(CypherParser.ListComprehensionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#propertyLookup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyLookup(CypherParser.PropertyLookupContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(CypherParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#numberLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumberLiteral(CypherParser.NumberLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#mapLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMapLiteral(CypherParser.MapLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#parameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameter(CypherParser.ParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#propertyExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyExpression(CypherParser.PropertyExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#propertyKeyName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPropertyKeyName(CypherParser.PropertyKeyNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#signedIntegerLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignedIntegerLiteral(CypherParser.SignedIntegerLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#unsignedIntegerLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsignedIntegerLiteral(CypherParser.UnsignedIntegerLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#hexInteger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHexInteger(CypherParser.HexIntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#decimalInteger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimalInteger(CypherParser.DecimalIntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#octalInteger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOctalInteger(CypherParser.OctalIntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#unsignedHexInteger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsignedHexInteger(CypherParser.UnsignedHexIntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#unsignedDecimalInteger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsignedDecimalInteger(CypherParser.UnsignedDecimalIntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#unsignedOctalInteger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsignedOctalInteger(CypherParser.UnsignedOctalIntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#hexString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHexString(CypherParser.HexStringContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#digitString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDigitString(CypherParser.DigitStringContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#octalString}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOctalString(CypherParser.OctalStringContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#digit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDigit(CypherParser.DigitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#octDigit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOctDigit(CypherParser.OctDigitContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#doubleLiteral}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDoubleLiteral(CypherParser.DoubleLiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#exponentDecimalReal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExponentDecimalReal(CypherParser.ExponentDecimalRealContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#regularDecimalReal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegularDecimalReal(CypherParser.RegularDecimalRealContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#symbolicName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSymbolicName(CypherParser.SymbolicNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#ws}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWs(CypherParser.WsContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#sp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSp(CypherParser.SpContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#leftArrowHead}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeftArrowHead(CypherParser.LeftArrowHeadContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#rightArrowHead}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRightArrowHead(CypherParser.RightArrowHeadContext ctx);
	/**
	 * Visit a parse tree produced by {@link CypherParser#dash}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDash(CypherParser.DashContext ctx);
}