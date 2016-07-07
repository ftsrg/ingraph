package cypher.grammar;
// Generated from /home/szarnyasg/git/openCypher/grammar/generated/Cypher.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CypherParser}.
 */
public interface CypherListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CypherParser#cypher}.
	 * @param ctx the parse tree
	 */
	void enterCypher(CypherParser.CypherContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#cypher}.
	 * @param ctx the parse tree
	 */
	void exitCypher(CypherParser.CypherContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#queryOptions}.
	 * @param ctx the parse tree
	 */
	void enterQueryOptions(CypherParser.QueryOptionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#queryOptions}.
	 * @param ctx the parse tree
	 */
	void exitQueryOptions(CypherParser.QueryOptionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#anyCypherOption}.
	 * @param ctx the parse tree
	 */
	void enterAnyCypherOption(CypherParser.AnyCypherOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#anyCypherOption}.
	 * @param ctx the parse tree
	 */
	void exitAnyCypherOption(CypherParser.AnyCypherOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#cypherOption}.
	 * @param ctx the parse tree
	 */
	void enterCypherOption(CypherParser.CypherOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#cypherOption}.
	 * @param ctx the parse tree
	 */
	void exitCypherOption(CypherParser.CypherOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#versionNumber}.
	 * @param ctx the parse tree
	 */
	void enterVersionNumber(CypherParser.VersionNumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#versionNumber}.
	 * @param ctx the parse tree
	 */
	void exitVersionNumber(CypherParser.VersionNumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#explain}.
	 * @param ctx the parse tree
	 */
	void enterExplain(CypherParser.ExplainContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#explain}.
	 * @param ctx the parse tree
	 */
	void exitExplain(CypherParser.ExplainContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#profile}.
	 * @param ctx the parse tree
	 */
	void enterProfile(CypherParser.ProfileContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#profile}.
	 * @param ctx the parse tree
	 */
	void exitProfile(CypherParser.ProfileContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#configurationOption}.
	 * @param ctx the parse tree
	 */
	void enterConfigurationOption(CypherParser.ConfigurationOptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#configurationOption}.
	 * @param ctx the parse tree
	 */
	void exitConfigurationOption(CypherParser.ConfigurationOptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(CypherParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(CypherParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(CypherParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(CypherParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#regularQuery}.
	 * @param ctx the parse tree
	 */
	void enterRegularQuery(CypherParser.RegularQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#regularQuery}.
	 * @param ctx the parse tree
	 */
	void exitRegularQuery(CypherParser.RegularQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#bulkImportQuery}.
	 * @param ctx the parse tree
	 */
	void enterBulkImportQuery(CypherParser.BulkImportQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#bulkImportQuery}.
	 * @param ctx the parse tree
	 */
	void exitBulkImportQuery(CypherParser.BulkImportQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#singleQuery}.
	 * @param ctx the parse tree
	 */
	void enterSingleQuery(CypherParser.SingleQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#singleQuery}.
	 * @param ctx the parse tree
	 */
	void exitSingleQuery(CypherParser.SingleQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#periodicCommitHint}.
	 * @param ctx the parse tree
	 */
	void enterPeriodicCommitHint(CypherParser.PeriodicCommitHintContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#periodicCommitHint}.
	 * @param ctx the parse tree
	 */
	void exitPeriodicCommitHint(CypherParser.PeriodicCommitHintContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#loadCSVQuery}.
	 * @param ctx the parse tree
	 */
	void enterLoadCSVQuery(CypherParser.LoadCSVQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#loadCSVQuery}.
	 * @param ctx the parse tree
	 */
	void exitLoadCSVQuery(CypherParser.LoadCSVQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#union}.
	 * @param ctx the parse tree
	 */
	void enterUnion(CypherParser.UnionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#union}.
	 * @param ctx the parse tree
	 */
	void exitUnion(CypherParser.UnionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#clause}.
	 * @param ctx the parse tree
	 */
	void enterClause(CypherParser.ClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#clause}.
	 * @param ctx the parse tree
	 */
	void exitClause(CypherParser.ClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(CypherParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(CypherParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#createUniqueConstraint}.
	 * @param ctx the parse tree
	 */
	void enterCreateUniqueConstraint(CypherParser.CreateUniqueConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#createUniqueConstraint}.
	 * @param ctx the parse tree
	 */
	void exitCreateUniqueConstraint(CypherParser.CreateUniqueConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#createNodePropertyExistenceConstraint}.
	 * @param ctx the parse tree
	 */
	void enterCreateNodePropertyExistenceConstraint(CypherParser.CreateNodePropertyExistenceConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#createNodePropertyExistenceConstraint}.
	 * @param ctx the parse tree
	 */
	void exitCreateNodePropertyExistenceConstraint(CypherParser.CreateNodePropertyExistenceConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#createRelationshipPropertyExistenceConstraint}.
	 * @param ctx the parse tree
	 */
	void enterCreateRelationshipPropertyExistenceConstraint(CypherParser.CreateRelationshipPropertyExistenceConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#createRelationshipPropertyExistenceConstraint}.
	 * @param ctx the parse tree
	 */
	void exitCreateRelationshipPropertyExistenceConstraint(CypherParser.CreateRelationshipPropertyExistenceConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#createIndex}.
	 * @param ctx the parse tree
	 */
	void enterCreateIndex(CypherParser.CreateIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#createIndex}.
	 * @param ctx the parse tree
	 */
	void exitCreateIndex(CypherParser.CreateIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#dropUniqueConstraint}.
	 * @param ctx the parse tree
	 */
	void enterDropUniqueConstraint(CypherParser.DropUniqueConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#dropUniqueConstraint}.
	 * @param ctx the parse tree
	 */
	void exitDropUniqueConstraint(CypherParser.DropUniqueConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#dropNodePropertyExistenceConstraint}.
	 * @param ctx the parse tree
	 */
	void enterDropNodePropertyExistenceConstraint(CypherParser.DropNodePropertyExistenceConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#dropNodePropertyExistenceConstraint}.
	 * @param ctx the parse tree
	 */
	void exitDropNodePropertyExistenceConstraint(CypherParser.DropNodePropertyExistenceConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#dropRelationshipPropertyExistenceConstraint}.
	 * @param ctx the parse tree
	 */
	void enterDropRelationshipPropertyExistenceConstraint(CypherParser.DropRelationshipPropertyExistenceConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#dropRelationshipPropertyExistenceConstraint}.
	 * @param ctx the parse tree
	 */
	void exitDropRelationshipPropertyExistenceConstraint(CypherParser.DropRelationshipPropertyExistenceConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#dropIndex}.
	 * @param ctx the parse tree
	 */
	void enterDropIndex(CypherParser.DropIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#dropIndex}.
	 * @param ctx the parse tree
	 */
	void exitDropIndex(CypherParser.DropIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#index}.
	 * @param ctx the parse tree
	 */
	void enterIndex(CypherParser.IndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#index}.
	 * @param ctx the parse tree
	 */
	void exitIndex(CypherParser.IndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#uniqueConstraint}.
	 * @param ctx the parse tree
	 */
	void enterUniqueConstraint(CypherParser.UniqueConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#uniqueConstraint}.
	 * @param ctx the parse tree
	 */
	void exitUniqueConstraint(CypherParser.UniqueConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#nodePropertyExistenceConstraint}.
	 * @param ctx the parse tree
	 */
	void enterNodePropertyExistenceConstraint(CypherParser.NodePropertyExistenceConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#nodePropertyExistenceConstraint}.
	 * @param ctx the parse tree
	 */
	void exitNodePropertyExistenceConstraint(CypherParser.NodePropertyExistenceConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipPropertyExistenceConstraint}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipPropertyExistenceConstraint(CypherParser.RelationshipPropertyExistenceConstraintContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipPropertyExistenceConstraint}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipPropertyExistenceConstraint(CypherParser.RelationshipPropertyExistenceConstraintContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipPatternSyntax}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipPatternSyntax(CypherParser.RelationshipPatternSyntaxContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipPatternSyntax}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipPatternSyntax(CypherParser.RelationshipPatternSyntaxContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#loadCSV}.
	 * @param ctx the parse tree
	 */
	void enterLoadCSV(CypherParser.LoadCSVContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#loadCSV}.
	 * @param ctx the parse tree
	 */
	void exitLoadCSV(CypherParser.LoadCSVContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#match}.
	 * @param ctx the parse tree
	 */
	void enterMatch(CypherParser.MatchContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#match}.
	 * @param ctx the parse tree
	 */
	void exitMatch(CypherParser.MatchContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#unwind}.
	 * @param ctx the parse tree
	 */
	void enterUnwind(CypherParser.UnwindContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#unwind}.
	 * @param ctx the parse tree
	 */
	void exitUnwind(CypherParser.UnwindContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#merge}.
	 * @param ctx the parse tree
	 */
	void enterMerge(CypherParser.MergeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#merge}.
	 * @param ctx the parse tree
	 */
	void exitMerge(CypherParser.MergeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#mergeAction}.
	 * @param ctx the parse tree
	 */
	void enterMergeAction(CypherParser.MergeActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#mergeAction}.
	 * @param ctx the parse tree
	 */
	void exitMergeAction(CypherParser.MergeActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#create}.
	 * @param ctx the parse tree
	 */
	void enterCreate(CypherParser.CreateContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#create}.
	 * @param ctx the parse tree
	 */
	void exitCreate(CypherParser.CreateContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#set}.
	 * @param ctx the parse tree
	 */
	void enterSet(CypherParser.SetContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#set}.
	 * @param ctx the parse tree
	 */
	void exitSet(CypherParser.SetContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#setItem}.
	 * @param ctx the parse tree
	 */
	void enterSetItem(CypherParser.SetItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#setItem}.
	 * @param ctx the parse tree
	 */
	void exitSetItem(CypherParser.SetItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#delete}.
	 * @param ctx the parse tree
	 */
	void enterDelete(CypherParser.DeleteContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#delete}.
	 * @param ctx the parse tree
	 */
	void exitDelete(CypherParser.DeleteContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#remove}.
	 * @param ctx the parse tree
	 */
	void enterRemove(CypherParser.RemoveContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#remove}.
	 * @param ctx the parse tree
	 */
	void exitRemove(CypherParser.RemoveContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#removeItem}.
	 * @param ctx the parse tree
	 */
	void enterRemoveItem(CypherParser.RemoveItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#removeItem}.
	 * @param ctx the parse tree
	 */
	void exitRemoveItem(CypherParser.RemoveItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#foreach}.
	 * @param ctx the parse tree
	 */
	void enterForeach(CypherParser.ForeachContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#foreach}.
	 * @param ctx the parse tree
	 */
	void exitForeach(CypherParser.ForeachContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#with}.
	 * @param ctx the parse tree
	 */
	void enterWith(CypherParser.WithContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#with}.
	 * @param ctx the parse tree
	 */
	void exitWith(CypherParser.WithContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#return}.
	 * @param ctx the parse tree
	 */
	void enterReturn(CypherParser.ReturnContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#return}.
	 * @param ctx the parse tree
	 */
	void exitReturn(CypherParser.ReturnContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#returnBody}.
	 * @param ctx the parse tree
	 */
	void enterReturnBody(CypherParser.ReturnBodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#returnBody}.
	 * @param ctx the parse tree
	 */
	void exitReturnBody(CypherParser.ReturnBodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#returnItems}.
	 * @param ctx the parse tree
	 */
	void enterReturnItems(CypherParser.ReturnItemsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#returnItems}.
	 * @param ctx the parse tree
	 */
	void exitReturnItems(CypherParser.ReturnItemsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#returnItem}.
	 * @param ctx the parse tree
	 */
	void enterReturnItem(CypherParser.ReturnItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#returnItem}.
	 * @param ctx the parse tree
	 */
	void exitReturnItem(CypherParser.ReturnItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#order}.
	 * @param ctx the parse tree
	 */
	void enterOrder(CypherParser.OrderContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#order}.
	 * @param ctx the parse tree
	 */
	void exitOrder(CypherParser.OrderContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#skip}.
	 * @param ctx the parse tree
	 */
	void enterSkip(CypherParser.SkipContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#skip}.
	 * @param ctx the parse tree
	 */
	void exitSkip(CypherParser.SkipContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#limit}.
	 * @param ctx the parse tree
	 */
	void enterLimit(CypherParser.LimitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#limit}.
	 * @param ctx the parse tree
	 */
	void exitLimit(CypherParser.LimitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#sortItem}.
	 * @param ctx the parse tree
	 */
	void enterSortItem(CypherParser.SortItemContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#sortItem}.
	 * @param ctx the parse tree
	 */
	void exitSortItem(CypherParser.SortItemContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#hint}.
	 * @param ctx the parse tree
	 */
	void enterHint(CypherParser.HintContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#hint}.
	 * @param ctx the parse tree
	 */
	void exitHint(CypherParser.HintContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(CypherParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(CypherParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#startPoint}.
	 * @param ctx the parse tree
	 */
	void enterStartPoint(CypherParser.StartPointContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#startPoint}.
	 * @param ctx the parse tree
	 */
	void exitStartPoint(CypherParser.StartPointContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#lookup}.
	 * @param ctx the parse tree
	 */
	void enterLookup(CypherParser.LookupContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#lookup}.
	 * @param ctx the parse tree
	 */
	void exitLookup(CypherParser.LookupContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#nodeLookup}.
	 * @param ctx the parse tree
	 */
	void enterNodeLookup(CypherParser.NodeLookupContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#nodeLookup}.
	 * @param ctx the parse tree
	 */
	void exitNodeLookup(CypherParser.NodeLookupContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipLookup}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipLookup(CypherParser.RelationshipLookupContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipLookup}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipLookup(CypherParser.RelationshipLookupContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#identifiedIndexLookup}.
	 * @param ctx the parse tree
	 */
	void enterIdentifiedIndexLookup(CypherParser.IdentifiedIndexLookupContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#identifiedIndexLookup}.
	 * @param ctx the parse tree
	 */
	void exitIdentifiedIndexLookup(CypherParser.IdentifiedIndexLookupContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#indexQuery}.
	 * @param ctx the parse tree
	 */
	void enterIndexQuery(CypherParser.IndexQueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#indexQuery}.
	 * @param ctx the parse tree
	 */
	void exitIndexQuery(CypherParser.IndexQueryContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#idLookup}.
	 * @param ctx the parse tree
	 */
	void enterIdLookup(CypherParser.IdLookupContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#idLookup}.
	 * @param ctx the parse tree
	 */
	void exitIdLookup(CypherParser.IdLookupContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#literalIds}.
	 * @param ctx the parse tree
	 */
	void enterLiteralIds(CypherParser.LiteralIdsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#literalIds}.
	 * @param ctx the parse tree
	 */
	void exitLiteralIds(CypherParser.LiteralIdsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#where}.
	 * @param ctx the parse tree
	 */
	void enterWhere(CypherParser.WhereContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#where}.
	 * @param ctx the parse tree
	 */
	void exitWhere(CypherParser.WhereContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#pattern}.
	 * @param ctx the parse tree
	 */
	void enterPattern(CypherParser.PatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#pattern}.
	 * @param ctx the parse tree
	 */
	void exitPattern(CypherParser.PatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#patternPart}.
	 * @param ctx the parse tree
	 */
	void enterPatternPart(CypherParser.PatternPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#patternPart}.
	 * @param ctx the parse tree
	 */
	void exitPatternPart(CypherParser.PatternPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#anonymousPatternPart}.
	 * @param ctx the parse tree
	 */
	void enterAnonymousPatternPart(CypherParser.AnonymousPatternPartContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#anonymousPatternPart}.
	 * @param ctx the parse tree
	 */
	void exitAnonymousPatternPart(CypherParser.AnonymousPatternPartContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#shortestPathPattern}.
	 * @param ctx the parse tree
	 */
	void enterShortestPathPattern(CypherParser.ShortestPathPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#shortestPathPattern}.
	 * @param ctx the parse tree
	 */
	void exitShortestPathPattern(CypherParser.ShortestPathPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#patternElement}.
	 * @param ctx the parse tree
	 */
	void enterPatternElement(CypherParser.PatternElementContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#patternElement}.
	 * @param ctx the parse tree
	 */
	void exitPatternElement(CypherParser.PatternElementContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#nodePattern}.
	 * @param ctx the parse tree
	 */
	void enterNodePattern(CypherParser.NodePatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#nodePattern}.
	 * @param ctx the parse tree
	 */
	void exitNodePattern(CypherParser.NodePatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#patternElementChain}.
	 * @param ctx the parse tree
	 */
	void enterPatternElementChain(CypherParser.PatternElementChainContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#patternElementChain}.
	 * @param ctx the parse tree
	 */
	void exitPatternElementChain(CypherParser.PatternElementChainContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipPattern}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipPattern(CypherParser.RelationshipPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipPattern}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipPattern(CypherParser.RelationshipPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipDetail}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipDetail(CypherParser.RelationshipDetailContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipDetail}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipDetail(CypherParser.RelationshipDetailContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#properties}.
	 * @param ctx the parse tree
	 */
	void enterProperties(CypherParser.PropertiesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#properties}.
	 * @param ctx the parse tree
	 */
	void exitProperties(CypherParser.PropertiesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relType}.
	 * @param ctx the parse tree
	 */
	void enterRelType(CypherParser.RelTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relType}.
	 * @param ctx the parse tree
	 */
	void exitRelType(CypherParser.RelTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipTypes}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipTypes(CypherParser.RelationshipTypesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipTypes}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipTypes(CypherParser.RelationshipTypesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#nodeLabels}.
	 * @param ctx the parse tree
	 */
	void enterNodeLabels(CypherParser.NodeLabelsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#nodeLabels}.
	 * @param ctx the parse tree
	 */
	void exitNodeLabels(CypherParser.NodeLabelsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#nodeLabel}.
	 * @param ctx the parse tree
	 */
	void enterNodeLabel(CypherParser.NodeLabelContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#nodeLabel}.
	 * @param ctx the parse tree
	 */
	void exitNodeLabel(CypherParser.NodeLabelContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rangeLiteral}.
	 * @param ctx the parse tree
	 */
	void enterRangeLiteral(CypherParser.RangeLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rangeLiteral}.
	 * @param ctx the parse tree
	 */
	void exitRangeLiteral(CypherParser.RangeLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#labelName}.
	 * @param ctx the parse tree
	 */
	void enterLabelName(CypherParser.LabelNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#labelName}.
	 * @param ctx the parse tree
	 */
	void exitLabelName(CypherParser.LabelNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relTypeName}.
	 * @param ctx the parse tree
	 */
	void enterRelTypeName(CypherParser.RelTypeNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relTypeName}.
	 * @param ctx the parse tree
	 */
	void exitRelTypeName(CypherParser.RelTypeNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(CypherParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(CypherParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression12}.
	 * @param ctx the parse tree
	 */
	void enterExpression12(CypherParser.Expression12Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression12}.
	 * @param ctx the parse tree
	 */
	void exitExpression12(CypherParser.Expression12Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression11}.
	 * @param ctx the parse tree
	 */
	void enterExpression11(CypherParser.Expression11Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression11}.
	 * @param ctx the parse tree
	 */
	void exitExpression11(CypherParser.Expression11Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression10}.
	 * @param ctx the parse tree
	 */
	void enterExpression10(CypherParser.Expression10Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression10}.
	 * @param ctx the parse tree
	 */
	void exitExpression10(CypherParser.Expression10Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression9}.
	 * @param ctx the parse tree
	 */
	void enterExpression9(CypherParser.Expression9Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression9}.
	 * @param ctx the parse tree
	 */
	void exitExpression9(CypherParser.Expression9Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression8}.
	 * @param ctx the parse tree
	 */
	void enterExpression8(CypherParser.Expression8Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression8}.
	 * @param ctx the parse tree
	 */
	void exitExpression8(CypherParser.Expression8Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression7}.
	 * @param ctx the parse tree
	 */
	void enterExpression7(CypherParser.Expression7Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression7}.
	 * @param ctx the parse tree
	 */
	void exitExpression7(CypherParser.Expression7Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression6}.
	 * @param ctx the parse tree
	 */
	void enterExpression6(CypherParser.Expression6Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression6}.
	 * @param ctx the parse tree
	 */
	void exitExpression6(CypherParser.Expression6Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression5}.
	 * @param ctx the parse tree
	 */
	void enterExpression5(CypherParser.Expression5Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression5}.
	 * @param ctx the parse tree
	 */
	void exitExpression5(CypherParser.Expression5Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression4}.
	 * @param ctx the parse tree
	 */
	void enterExpression4(CypherParser.Expression4Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression4}.
	 * @param ctx the parse tree
	 */
	void exitExpression4(CypherParser.Expression4Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression3}.
	 * @param ctx the parse tree
	 */
	void enterExpression3(CypherParser.Expression3Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression3}.
	 * @param ctx the parse tree
	 */
	void exitExpression3(CypherParser.Expression3Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#expression2}.
	 * @param ctx the parse tree
	 */
	void enterExpression2(CypherParser.Expression2Context ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#expression2}.
	 * @param ctx the parse tree
	 */
	void exitExpression2(CypherParser.Expression2Context ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(CypherParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(CypherParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#reduce}.
	 * @param ctx the parse tree
	 */
	void enterReduce(CypherParser.ReduceContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#reduce}.
	 * @param ctx the parse tree
	 */
	void exitReduce(CypherParser.ReduceContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#partialComparisonExpression}.
	 * @param ctx the parse tree
	 */
	void enterPartialComparisonExpression(CypherParser.PartialComparisonExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#partialComparisonExpression}.
	 * @param ctx the parse tree
	 */
	void exitPartialComparisonExpression(CypherParser.PartialComparisonExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#parenthesizedExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenthesizedExpression(CypherParser.ParenthesizedExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#parenthesizedExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenthesizedExpression(CypherParser.ParenthesizedExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#relationshipsPattern}.
	 * @param ctx the parse tree
	 */
	void enterRelationshipsPattern(CypherParser.RelationshipsPatternContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#relationshipsPattern}.
	 * @param ctx the parse tree
	 */
	void exitRelationshipsPattern(CypherParser.RelationshipsPatternContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#filterExpression}.
	 * @param ctx the parse tree
	 */
	void enterFilterExpression(CypherParser.FilterExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#filterExpression}.
	 * @param ctx the parse tree
	 */
	void exitFilterExpression(CypherParser.FilterExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#idInColl}.
	 * @param ctx the parse tree
	 */
	void enterIdInColl(CypherParser.IdInCollContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#idInColl}.
	 * @param ctx the parse tree
	 */
	void exitIdInColl(CypherParser.IdInCollContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#functionInvocation}.
	 * @param ctx the parse tree
	 */
	void enterFunctionInvocation(CypherParser.FunctionInvocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#functionInvocation}.
	 * @param ctx the parse tree
	 */
	void exitFunctionInvocation(CypherParser.FunctionInvocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#functionName}.
	 * @param ctx the parse tree
	 */
	void enterFunctionName(CypherParser.FunctionNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#functionName}.
	 * @param ctx the parse tree
	 */
	void exitFunctionName(CypherParser.FunctionNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#listComprehension}.
	 * @param ctx the parse tree
	 */
	void enterListComprehension(CypherParser.ListComprehensionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#listComprehension}.
	 * @param ctx the parse tree
	 */
	void exitListComprehension(CypherParser.ListComprehensionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#propertyLookup}.
	 * @param ctx the parse tree
	 */
	void enterPropertyLookup(CypherParser.PropertyLookupContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#propertyLookup}.
	 * @param ctx the parse tree
	 */
	void exitPropertyLookup(CypherParser.PropertyLookupContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#caseExpression}.
	 * @param ctx the parse tree
	 */
	void enterCaseExpression(CypherParser.CaseExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#caseExpression}.
	 * @param ctx the parse tree
	 */
	void exitCaseExpression(CypherParser.CaseExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#caseAlternatives}.
	 * @param ctx the parse tree
	 */
	void enterCaseAlternatives(CypherParser.CaseAlternativesContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#caseAlternatives}.
	 * @param ctx the parse tree
	 */
	void exitCaseAlternatives(CypherParser.CaseAlternativesContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#variable}.
	 * @param ctx the parse tree
	 */
	void enterVariable(CypherParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#variable}.
	 * @param ctx the parse tree
	 */
	void exitVariable(CypherParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#numberLiteral}.
	 * @param ctx the parse tree
	 */
	void enterNumberLiteral(CypherParser.NumberLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#numberLiteral}.
	 * @param ctx the parse tree
	 */
	void exitNumberLiteral(CypherParser.NumberLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#mapLiteral}.
	 * @param ctx the parse tree
	 */
	void enterMapLiteral(CypherParser.MapLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#mapLiteral}.
	 * @param ctx the parse tree
	 */
	void exitMapLiteral(CypherParser.MapLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#parameter}.
	 * @param ctx the parse tree
	 */
	void enterParameter(CypherParser.ParameterContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#parameter}.
	 * @param ctx the parse tree
	 */
	void exitParameter(CypherParser.ParameterContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#propertyExpression}.
	 * @param ctx the parse tree
	 */
	void enterPropertyExpression(CypherParser.PropertyExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#propertyExpression}.
	 * @param ctx the parse tree
	 */
	void exitPropertyExpression(CypherParser.PropertyExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#propertyKeyName}.
	 * @param ctx the parse tree
	 */
	void enterPropertyKeyName(CypherParser.PropertyKeyNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#propertyKeyName}.
	 * @param ctx the parse tree
	 */
	void exitPropertyKeyName(CypherParser.PropertyKeyNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#signedIntegerLiteral}.
	 * @param ctx the parse tree
	 */
	void enterSignedIntegerLiteral(CypherParser.SignedIntegerLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#signedIntegerLiteral}.
	 * @param ctx the parse tree
	 */
	void exitSignedIntegerLiteral(CypherParser.SignedIntegerLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#unsignedIntegerLiteral}.
	 * @param ctx the parse tree
	 */
	void enterUnsignedIntegerLiteral(CypherParser.UnsignedIntegerLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#unsignedIntegerLiteral}.
	 * @param ctx the parse tree
	 */
	void exitUnsignedIntegerLiteral(CypherParser.UnsignedIntegerLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#hexInteger}.
	 * @param ctx the parse tree
	 */
	void enterHexInteger(CypherParser.HexIntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#hexInteger}.
	 * @param ctx the parse tree
	 */
	void exitHexInteger(CypherParser.HexIntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#decimalInteger}.
	 * @param ctx the parse tree
	 */
	void enterDecimalInteger(CypherParser.DecimalIntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#decimalInteger}.
	 * @param ctx the parse tree
	 */
	void exitDecimalInteger(CypherParser.DecimalIntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#octalInteger}.
	 * @param ctx the parse tree
	 */
	void enterOctalInteger(CypherParser.OctalIntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#octalInteger}.
	 * @param ctx the parse tree
	 */
	void exitOctalInteger(CypherParser.OctalIntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#unsignedHexInteger}.
	 * @param ctx the parse tree
	 */
	void enterUnsignedHexInteger(CypherParser.UnsignedHexIntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#unsignedHexInteger}.
	 * @param ctx the parse tree
	 */
	void exitUnsignedHexInteger(CypherParser.UnsignedHexIntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#unsignedDecimalInteger}.
	 * @param ctx the parse tree
	 */
	void enterUnsignedDecimalInteger(CypherParser.UnsignedDecimalIntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#unsignedDecimalInteger}.
	 * @param ctx the parse tree
	 */
	void exitUnsignedDecimalInteger(CypherParser.UnsignedDecimalIntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#unsignedOctalInteger}.
	 * @param ctx the parse tree
	 */
	void enterUnsignedOctalInteger(CypherParser.UnsignedOctalIntegerContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#unsignedOctalInteger}.
	 * @param ctx the parse tree
	 */
	void exitUnsignedOctalInteger(CypherParser.UnsignedOctalIntegerContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#hexString}.
	 * @param ctx the parse tree
	 */
	void enterHexString(CypherParser.HexStringContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#hexString}.
	 * @param ctx the parse tree
	 */
	void exitHexString(CypherParser.HexStringContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#digitString}.
	 * @param ctx the parse tree
	 */
	void enterDigitString(CypherParser.DigitStringContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#digitString}.
	 * @param ctx the parse tree
	 */
	void exitDigitString(CypherParser.DigitStringContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#octalString}.
	 * @param ctx the parse tree
	 */
	void enterOctalString(CypherParser.OctalStringContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#octalString}.
	 * @param ctx the parse tree
	 */
	void exitOctalString(CypherParser.OctalStringContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#digit}.
	 * @param ctx the parse tree
	 */
	void enterDigit(CypherParser.DigitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#digit}.
	 * @param ctx the parse tree
	 */
	void exitDigit(CypherParser.DigitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#octDigit}.
	 * @param ctx the parse tree
	 */
	void enterOctDigit(CypherParser.OctDigitContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#octDigit}.
	 * @param ctx the parse tree
	 */
	void exitOctDigit(CypherParser.OctDigitContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#doubleLiteral}.
	 * @param ctx the parse tree
	 */
	void enterDoubleLiteral(CypherParser.DoubleLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#doubleLiteral}.
	 * @param ctx the parse tree
	 */
	void exitDoubleLiteral(CypherParser.DoubleLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#exponentDecimalReal}.
	 * @param ctx the parse tree
	 */
	void enterExponentDecimalReal(CypherParser.ExponentDecimalRealContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#exponentDecimalReal}.
	 * @param ctx the parse tree
	 */
	void exitExponentDecimalReal(CypherParser.ExponentDecimalRealContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#regularDecimalReal}.
	 * @param ctx the parse tree
	 */
	void enterRegularDecimalReal(CypherParser.RegularDecimalRealContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#regularDecimalReal}.
	 * @param ctx the parse tree
	 */
	void exitRegularDecimalReal(CypherParser.RegularDecimalRealContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#symbolicName}.
	 * @param ctx the parse tree
	 */
	void enterSymbolicName(CypherParser.SymbolicNameContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#symbolicName}.
	 * @param ctx the parse tree
	 */
	void exitSymbolicName(CypherParser.SymbolicNameContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#ws}.
	 * @param ctx the parse tree
	 */
	void enterWs(CypherParser.WsContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#ws}.
	 * @param ctx the parse tree
	 */
	void exitWs(CypherParser.WsContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#sp}.
	 * @param ctx the parse tree
	 */
	void enterSp(CypherParser.SpContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#sp}.
	 * @param ctx the parse tree
	 */
	void exitSp(CypherParser.SpContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#leftArrowHead}.
	 * @param ctx the parse tree
	 */
	void enterLeftArrowHead(CypherParser.LeftArrowHeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#leftArrowHead}.
	 * @param ctx the parse tree
	 */
	void exitLeftArrowHead(CypherParser.LeftArrowHeadContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#rightArrowHead}.
	 * @param ctx the parse tree
	 */
	void enterRightArrowHead(CypherParser.RightArrowHeadContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#rightArrowHead}.
	 * @param ctx the parse tree
	 */
	void exitRightArrowHead(CypherParser.RightArrowHeadContext ctx);
	/**
	 * Enter a parse tree produced by {@link CypherParser#dash}.
	 * @param ctx the parse tree
	 */
	void enterDash(CypherParser.DashContext ctx);
	/**
	 * Exit a parse tree produced by {@link CypherParser#dash}.
	 * @param ctx the parse tree
	 */
	void exitDash(CypherParser.DashContext ctx);
}