// Generated from Cypher.g4 by ANTLR 4.5.3
package cypher.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CypherParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, T__42=43, T__43=44, T__44=45, 
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, StringLiteral=52, 
		EscapedChar=53, HexInteger=54, DecimalInteger=55, OctalInteger=56, HexString=57, 
		DigitString=58, OctalString=59, HexDigit=60, Digit=61, OctDigit=62, UNION=63, 
		ALL=64, OPTIONAL=65, MATCH=66, UNWIND=67, AS=68, MERGE=69, ON=70, CREATE=71, 
		SET=72, DELETE=73, DETACH=74, REMOVE=75, WITH=76, DISTINCT=77, RETURN=78, 
		ORDER=79, BY=80, L_SKIP=81, LIMIT=82, DESCENDING=83, DESC=84, ASCENDING=85, 
		ASC=86, WHERE=87, OR=88, XOR=89, AND=90, NOT=91, IN=92, STARTS=93, ENDS=94, 
		CONTAINS=95, IS=96, NULL=97, TRUE=98, FALSE=99, COUNT=100, FILTER=101, 
		EXTRACT=102, ANY=103, NONE=104, SINGLE=105, UnescapedSymbolicName=106, 
		IdentifierStart=107, IdentifierPart=108, EscapedSymbolicName=109, WHITESPACE=110, 
		Comment=111, L_0X=112;
	public static final int
		RULE_cypher = 0, RULE_statement = 1, RULE_query = 2, RULE_regularQuery = 3, 
		RULE_singleQuery = 4, RULE_union = 5, RULE_clause = 6, RULE_match = 7, 
		RULE_unwind = 8, RULE_merge = 9, RULE_mergeAction = 10, RULE_create = 11, 
		RULE_set = 12, RULE_setItem = 13, RULE_delete = 14, RULE_remove = 15, 
		RULE_removeItem = 16, RULE_with = 17, RULE_return = 18, RULE_returnBody = 19, 
		RULE_returnItems = 20, RULE_returnItem = 21, RULE_order = 22, RULE_skip = 23, 
		RULE_limit = 24, RULE_sortItem = 25, RULE_where = 26, RULE_pattern = 27, 
		RULE_patternPart = 28, RULE_anonymousPatternPart = 29, RULE_patternElement = 30, 
		RULE_nodePattern = 31, RULE_patternElementChain = 32, RULE_relationshipPattern = 33, 
		RULE_relationshipDetail = 34, RULE_properties = 35, RULE_relationshipTypes = 36, 
		RULE_nodeLabels = 37, RULE_nodeLabel = 38, RULE_rangeLiteral = 39, RULE_labelName = 40, 
		RULE_relTypeName = 41, RULE_expression = 42, RULE_expression12 = 43, RULE_expression11 = 44, 
		RULE_expression10 = 45, RULE_expression9 = 46, RULE_expression8 = 47, 
		RULE_expression7 = 48, RULE_expression6 = 49, RULE_expression5 = 50, RULE_expression4 = 51, 
		RULE_expression3 = 52, RULE_expression2 = 53, RULE_atom = 54, RULE_partialComparisonExpression = 55, 
		RULE_parenthesizedExpression = 56, RULE_relationshipsPattern = 57, RULE_filterExpression = 58, 
		RULE_idInColl = 59, RULE_functionInvocation = 60, RULE_functionName = 61, 
		RULE_listComprehension = 62, RULE_propertyLookup = 63, RULE_variable = 64, 
		RULE_numberLiteral = 65, RULE_mapLiteral = 66, RULE_parameter = 67, RULE_propertyExpression = 68, 
		RULE_propertyKeyName = 69, RULE_integerLiteral = 70, RULE_doubleLiteral = 71, 
		RULE_exponentDecimalReal = 72, RULE_regularDecimalReal = 73, RULE_symbolicName = 74, 
		RULE_ws = 75, RULE_sp = 76, RULE_leftArrowHead = 77, RULE_rightArrowHead = 78, 
		RULE_dash = 79, RULE_digit = 80;
	public static final String[] ruleNames = {
		"cypher", "statement", "query", "regularQuery", "singleQuery", "union", 
		"clause", "match", "unwind", "merge", "mergeAction", "create", "set", 
		"setItem", "delete", "remove", "removeItem", "with", "return", "returnBody", 
		"returnItems", "returnItem", "order", "skip", "limit", "sortItem", "where", 
		"pattern", "patternPart", "anonymousPatternPart", "patternElement", "nodePattern", 
		"patternElementChain", "relationshipPattern", "relationshipDetail", "properties", 
		"relationshipTypes", "nodeLabels", "nodeLabel", "rangeLiteral", "labelName", 
		"relTypeName", "expression", "expression12", "expression11", "expression10", 
		"expression9", "expression8", "expression7", "expression6", "expression5", 
		"expression4", "expression3", "expression2", "atom", "partialComparisonExpression", 
		"parenthesizedExpression", "relationshipsPattern", "filterExpression", 
		"idInColl", "functionInvocation", "functionName", "listComprehension", 
		"propertyLookup", "variable", "numberLiteral", "mapLiteral", "parameter", 
		"propertyExpression", "propertyKeyName", "integerLiteral", "doubleLiteral", 
		"exponentDecimalReal", "regularDecimalReal", "symbolicName", "ws", "sp", 
		"leftArrowHead", "rightArrowHead", "dash", "digit"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'='", "'+='", "'*'", "'('", "')'", "'['", "'?'", 
		"']'", "':'", "'|'", "'..'", "'+'", "'-'", "'/'", "'%'", "'^'", "'=~'", 
		"'<>'", "'!='", "'<'", "'>'", "'<='", "'>='", "'.'", "'!'", "'{'", "'}'", 
		"'$'", "'E'", "'e'", "'⟨'", "'〈'", "'﹤'", "'＜'", "'⟩'", "'〉'", "'﹥'", 
		"'＞'", "'­'", "'‐'", "'‑'", "'‒'", "'–'", "'—'", "'―'", "'−'", "'﹘'", 
		"'﹣'", "'－'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, "StringLiteral", "EscapedChar", "HexInteger", 
		"DecimalInteger", "OctalInteger", "HexString", "DigitString", "OctalString", 
		"HexDigit", "Digit", "OctDigit", "UNION", "ALL", "OPTIONAL", "MATCH", 
		"UNWIND", "AS", "MERGE", "ON", "CREATE", "SET", "DELETE", "DETACH", "REMOVE", 
		"WITH", "DISTINCT", "RETURN", "ORDER", "BY", "L_SKIP", "LIMIT", "DESCENDING", 
		"DESC", "ASCENDING", "ASC", "WHERE", "OR", "XOR", "AND", "NOT", "IN", 
		"STARTS", "ENDS", "CONTAINS", "IS", "NULL", "TRUE", "FALSE", "COUNT", 
		"FILTER", "EXTRACT", "ANY", "NONE", "SINGLE", "UnescapedSymbolicName", 
		"IdentifierStart", "IdentifierPart", "EscapedSymbolicName", "WHITESPACE", 
		"Comment", "L_0X"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Cypher.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CypherParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class CypherContext extends ParserRuleContext {
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public CypherContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cypher; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterCypher(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitCypher(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitCypher(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CypherContext cypher() throws RecognitionException {
		CypherContext _localctx = new CypherContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_cypher);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			ws();
			setState(163);
			statement();
			setState(167);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(164);
				ws();
				setState(165);
				match(T__0);
				}
				break;
			}
			setState(169);
			ws();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public QueryContext query() {
			return getRuleContext(QueryContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			query();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QueryContext extends ParserRuleContext {
		public RegularQueryContext regularQuery() {
			return getRuleContext(RegularQueryContext.class,0);
		}
		public QueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryContext query() throws RecognitionException {
		QueryContext _localctx = new QueryContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_query);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			regularQuery();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RegularQueryContext extends ParserRuleContext {
		public SingleQueryContext singleQuery() {
			return getRuleContext(SingleQueryContext.class,0);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<UnionContext> union() {
			return getRuleContexts(UnionContext.class);
		}
		public UnionContext union(int i) {
			return getRuleContext(UnionContext.class,i);
		}
		public RegularQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_regularQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRegularQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRegularQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRegularQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RegularQueryContext regularQuery() throws RecognitionException {
		RegularQueryContext _localctx = new RegularQueryContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_regularQuery);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			singleQuery();
			setState(181);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(176);
					ws();
					setState(177);
					union();
					}
					} 
				}
				setState(183);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SingleQueryContext extends ParserRuleContext {
		public List<ClauseContext> clause() {
			return getRuleContexts(ClauseContext.class);
		}
		public ClauseContext clause(int i) {
			return getRuleContext(ClauseContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public SingleQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singleQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSingleQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSingleQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitSingleQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SingleQueryContext singleQuery() throws RecognitionException {
		SingleQueryContext _localctx = new SingleQueryContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_singleQuery);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(184);
			clause();
			setState(190);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(185);
					ws();
					setState(186);
					clause();
					}
					} 
				}
				setState(192);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnionContext extends ParserRuleContext {
		public TerminalNode UNION() { return getToken(CypherParser.UNION, 0); }
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public TerminalNode ALL() { return getToken(CypherParser.ALL, 0); }
		public SingleQueryContext singleQuery() {
			return getRuleContext(SingleQueryContext.class,0);
		}
		public UnionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_union; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterUnion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitUnion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitUnion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnionContext union() throws RecognitionException {
		UnionContext _localctx = new UnionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_union);
		try {
			setState(200);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(193);
				match(UNION);
				setState(194);
				sp();
				setState(195);
				match(ALL);
				setState(196);
				singleQuery();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(198);
				match(UNION);
				setState(199);
				singleQuery();
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClauseContext extends ParserRuleContext {
		public MatchContext match() {
			return getRuleContext(MatchContext.class,0);
		}
		public UnwindContext unwind() {
			return getRuleContext(UnwindContext.class,0);
		}
		public MergeContext merge() {
			return getRuleContext(MergeContext.class,0);
		}
		public CreateContext create() {
			return getRuleContext(CreateContext.class,0);
		}
		public SetContext set() {
			return getRuleContext(SetContext.class,0);
		}
		public DeleteContext delete() {
			return getRuleContext(DeleteContext.class,0);
		}
		public RemoveContext remove() {
			return getRuleContext(RemoveContext.class,0);
		}
		public WithContext with() {
			return getRuleContext(WithContext.class,0);
		}
		public ReturnContext return_() {
			return getRuleContext(ReturnContext.class,0);
		}
		public ClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitClause(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitClause(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ClauseContext clause() throws RecognitionException {
		ClauseContext _localctx = new ClauseContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_clause);
		try {
			setState(211);
			switch (_input.LA(1)) {
			case OPTIONAL:
			case MATCH:
				enterOuterAlt(_localctx, 1);
				{
				setState(202);
				match();
				}
				break;
			case UNWIND:
				enterOuterAlt(_localctx, 2);
				{
				setState(203);
				unwind();
				}
				break;
			case MERGE:
				enterOuterAlt(_localctx, 3);
				{
				setState(204);
				merge();
				}
				break;
			case CREATE:
				enterOuterAlt(_localctx, 4);
				{
				setState(205);
				create();
				}
				break;
			case SET:
				enterOuterAlt(_localctx, 5);
				{
				setState(206);
				set();
				}
				break;
			case DELETE:
			case DETACH:
				enterOuterAlt(_localctx, 6);
				{
				setState(207);
				delete();
				}
				break;
			case REMOVE:
				enterOuterAlt(_localctx, 7);
				{
				setState(208);
				remove();
				}
				break;
			case WITH:
				enterOuterAlt(_localctx, 8);
				{
				setState(209);
				with();
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 9);
				{
				setState(210);
				return_();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MatchContext extends ParserRuleContext {
		public TerminalNode MATCH() { return getToken(CypherParser.MATCH, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public TerminalNode OPTIONAL() { return getToken(CypherParser.OPTIONAL, 0); }
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public MatchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_match; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterMatch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitMatch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitMatch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchContext match() throws RecognitionException {
		MatchContext _localctx = new MatchContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_match);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			_la = _input.LA(1);
			if (_la==OPTIONAL) {
				{
				setState(213);
				match(OPTIONAL);
				setState(214);
				sp();
				}
			}

			setState(217);
			match(MATCH);
			setState(218);
			ws();
			setState(219);
			pattern();
			setState(223);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(220);
				ws();
				setState(221);
				where();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class UnwindContext extends ParserRuleContext {
		public TerminalNode UNWIND() { return getToken(CypherParser.UNWIND, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode AS() { return getToken(CypherParser.AS, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public UnwindContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unwind; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterUnwind(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitUnwind(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitUnwind(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnwindContext unwind() throws RecognitionException {
		UnwindContext _localctx = new UnwindContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_unwind);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			match(UNWIND);
			setState(226);
			ws();
			setState(227);
			expression();
			setState(228);
			sp();
			setState(229);
			match(AS);
			setState(230);
			sp();
			setState(231);
			variable();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MergeContext extends ParserRuleContext {
		public TerminalNode MERGE() { return getToken(CypherParser.MERGE, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public PatternPartContext patternPart() {
			return getRuleContext(PatternPartContext.class,0);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public List<MergeActionContext> mergeAction() {
			return getRuleContexts(MergeActionContext.class);
		}
		public MergeActionContext mergeAction(int i) {
			return getRuleContext(MergeActionContext.class,i);
		}
		public MergeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_merge; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterMerge(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitMerge(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitMerge(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MergeContext merge() throws RecognitionException {
		MergeContext _localctx = new MergeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_merge);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			match(MERGE);
			setState(234);
			ws();
			setState(235);
			patternPart();
			setState(241);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(236);
					sp();
					setState(237);
					mergeAction();
					}
					} 
				}
				setState(243);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MergeActionContext extends ParserRuleContext {
		public TerminalNode ON() { return getToken(CypherParser.ON, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode MATCH() { return getToken(CypherParser.MATCH, 0); }
		public SetContext set() {
			return getRuleContext(SetContext.class,0);
		}
		public TerminalNode CREATE() { return getToken(CypherParser.CREATE, 0); }
		public MergeActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mergeAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterMergeAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitMergeAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitMergeAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MergeActionContext mergeAction() throws RecognitionException {
		MergeActionContext _localctx = new MergeActionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_mergeAction);
		try {
			setState(256);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(244);
				match(ON);
				setState(245);
				sp();
				setState(246);
				match(MATCH);
				setState(247);
				sp();
				setState(248);
				set();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(250);
				match(ON);
				setState(251);
				sp();
				setState(252);
				match(CREATE);
				setState(253);
				sp();
				setState(254);
				set();
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CreateContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(CypherParser.CREATE, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public CreateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterCreate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitCreate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitCreate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateContext create() throws RecognitionException {
		CreateContext _localctx = new CreateContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_create);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			match(CREATE);
			setState(259);
			ws();
			setState(260);
			pattern();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetContext extends ParserRuleContext {
		public TerminalNode SET() { return getToken(CypherParser.SET, 0); }
		public List<SetItemContext> setItem() {
			return getRuleContexts(SetItemContext.class);
		}
		public SetItemContext setItem(int i) {
			return getRuleContext(SetItemContext.class,i);
		}
		public SetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSet(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSet(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitSet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetContext set() throws RecognitionException {
		SetContext _localctx = new SetContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_set);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			match(SET);
			setState(263);
			setItem();
			setState(268);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(264);
				match(T__1);
				setState(265);
				setItem();
				}
				}
				setState(270);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SetItemContext extends ParserRuleContext {
		public PropertyExpressionContext propertyExpression() {
			return getRuleContext(PropertyExpressionContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public NodeLabelsContext nodeLabels() {
			return getRuleContext(NodeLabelsContext.class,0);
		}
		public SetItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSetItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSetItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitSetItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetItemContext setItem() throws RecognitionException {
		SetItemContext _localctx = new SetItemContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_setItem);
		try {
			setState(286);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(271);
				propertyExpression();
				setState(272);
				match(T__2);
				setState(273);
				expression();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(275);
				variable();
				setState(276);
				match(T__2);
				setState(277);
				expression();
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(279);
				variable();
				setState(280);
				match(T__3);
				setState(281);
				expression();
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(283);
				variable();
				setState(284);
				nodeLabels();
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeleteContext extends ParserRuleContext {
		public TerminalNode DELETE() { return getToken(CypherParser.DELETE, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode DETACH() { return getToken(CypherParser.DETACH, 0); }
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public DeleteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delete; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterDelete(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitDelete(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitDelete(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DeleteContext delete() throws RecognitionException {
		DeleteContext _localctx = new DeleteContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_delete);
		int _la;
		try {
			setState(308);
			switch (_input.LA(1)) {
			case DELETE:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(288);
				match(DELETE);
				setState(289);
				expression();
				setState(294);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(290);
					match(T__1);
					setState(291);
					expression();
					}
					}
					setState(296);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				break;
			case DETACH:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(297);
				match(DETACH);
				setState(298);
				sp();
				setState(299);
				match(DELETE);
				setState(300);
				expression();
				setState(305);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(301);
					match(T__1);
					setState(302);
					expression();
					}
					}
					setState(307);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RemoveContext extends ParserRuleContext {
		public TerminalNode REMOVE() { return getToken(CypherParser.REMOVE, 0); }
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public List<RemoveItemContext> removeItem() {
			return getRuleContexts(RemoveItemContext.class);
		}
		public RemoveItemContext removeItem(int i) {
			return getRuleContext(RemoveItemContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public RemoveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_remove; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRemove(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRemove(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRemove(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RemoveContext remove() throws RecognitionException {
		RemoveContext _localctx = new RemoveContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_remove);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(310);
			match(REMOVE);
			setState(311);
			sp();
			setState(312);
			removeItem();
			setState(320);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(313);
					ws();
					setState(314);
					match(T__1);
					setState(315);
					ws();
					setState(316);
					removeItem();
					}
					} 
				}
				setState(322);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RemoveItemContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public NodeLabelsContext nodeLabels() {
			return getRuleContext(NodeLabelsContext.class,0);
		}
		public PropertyExpressionContext propertyExpression() {
			return getRuleContext(PropertyExpressionContext.class,0);
		}
		public RemoveItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_removeItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRemoveItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRemoveItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRemoveItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RemoveItemContext removeItem() throws RecognitionException {
		RemoveItemContext _localctx = new RemoveItemContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_removeItem);
		try {
			setState(327);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(323);
				variable();
				setState(324);
				nodeLabels();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(326);
				propertyExpression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WithContext extends ParserRuleContext {
		public TerminalNode WITH() { return getToken(CypherParser.WITH, 0); }
		public TerminalNode DISTINCT() { return getToken(CypherParser.DISTINCT, 0); }
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public ReturnBodyContext returnBody() {
			return getRuleContext(ReturnBodyContext.class,0);
		}
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public WithContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_with; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterWith(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitWith(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitWith(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WithContext with() throws RecognitionException {
		WithContext _localctx = new WithContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_with);
		int _la;
		try {
			setState(342);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(329);
				match(WITH);
				setState(330);
				match(DISTINCT);
				setState(331);
				sp();
				setState(332);
				returnBody();
				setState(334);
				_la = _input.LA(1);
				if (_la==WHERE) {
					{
					setState(333);
					where();
					}
				}

				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(336);
				match(WITH);
				setState(337);
				sp();
				setState(338);
				returnBody();
				setState(340);
				_la = _input.LA(1);
				if (_la==WHERE) {
					{
					setState(339);
					where();
					}
				}

				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(CypherParser.RETURN, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode DISTINCT() { return getToken(CypherParser.DISTINCT, 0); }
		public ReturnBodyContext returnBody() {
			return getRuleContext(ReturnBodyContext.class,0);
		}
		public ReturnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitReturn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnContext return_() throws RecognitionException {
		ReturnContext _localctx = new ReturnContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_return);
		try {
			setState(354);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(344);
				match(RETURN);
				setState(345);
				sp();
				setState(346);
				match(DISTINCT);
				setState(347);
				sp();
				setState(348);
				returnBody();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(350);
				match(RETURN);
				setState(351);
				sp();
				setState(352);
				returnBody();
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnBodyContext extends ParserRuleContext {
		public ReturnItemsContext returnItems() {
			return getRuleContext(ReturnItemsContext.class,0);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public OrderContext order() {
			return getRuleContext(OrderContext.class,0);
		}
		public SkipContext skip() {
			return getRuleContext(SkipContext.class,0);
		}
		public LimitContext limit() {
			return getRuleContext(LimitContext.class,0);
		}
		public ReturnBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReturnBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReturnBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitReturnBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnBodyContext returnBody() throws RecognitionException {
		ReturnBodyContext _localctx = new ReturnBodyContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_returnBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
			returnItems();
			setState(360);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(357);
				sp();
				setState(358);
				order();
				}
				break;
			}
			setState(365);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(362);
				sp();
				setState(363);
				skip();
				}
				break;
			}
			setState(370);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(367);
				sp();
				setState(368);
				limit();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnItemsContext extends ParserRuleContext {
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<ReturnItemContext> returnItem() {
			return getRuleContexts(ReturnItemContext.class);
		}
		public ReturnItemContext returnItem(int i) {
			return getRuleContext(ReturnItemContext.class,i);
		}
		public ReturnItemsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnItems; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReturnItems(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReturnItems(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitReturnItems(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnItemsContext returnItems() throws RecognitionException {
		ReturnItemsContext _localctx = new ReturnItemsContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_returnItems);
		try {
			int _alt;
			setState(394);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(372);
				match(T__4);
				setState(380);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(373);
						ws();
						setState(374);
						match(T__1);
						setState(375);
						ws();
						setState(376);
						returnItem();
						}
						} 
					}
					setState(382);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				}
				}
				}
				break;
			case T__5:
			case T__7:
			case T__13:
			case T__14:
			case T__25:
			case T__27:
			case T__29:
			case StringLiteral:
			case HexInteger:
			case DecimalInteger:
			case OctalInteger:
			case HexString:
			case Digit:
			case UNION:
			case ALL:
			case OPTIONAL:
			case MATCH:
			case UNWIND:
			case AS:
			case MERGE:
			case ON:
			case CREATE:
			case SET:
			case DELETE:
			case DETACH:
			case REMOVE:
			case WITH:
			case DISTINCT:
			case RETURN:
			case ORDER:
			case BY:
			case L_SKIP:
			case LIMIT:
			case DESCENDING:
			case DESC:
			case ASCENDING:
			case ASC:
			case WHERE:
			case OR:
			case XOR:
			case AND:
			case NOT:
			case IN:
			case STARTS:
			case ENDS:
			case CONTAINS:
			case IS:
			case NULL:
			case TRUE:
			case FALSE:
			case COUNT:
			case FILTER:
			case EXTRACT:
			case ANY:
			case NONE:
			case SINGLE:
			case UnescapedSymbolicName:
			case EscapedSymbolicName:
			case WHITESPACE:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(383);
				returnItem();
				setState(391);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(384);
						ws();
						setState(385);
						match(T__1);
						setState(386);
						ws();
						setState(387);
						returnItem();
						}
						} 
					}
					setState(393);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
				}
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnItemContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode AS() { return getToken(CypherParser.AS, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public ReturnItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReturnItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReturnItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitReturnItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReturnItemContext returnItem() throws RecognitionException {
		ReturnItemContext _localctx = new ReturnItemContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_returnItem);
		try {
			setState(403);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(396);
				expression();
				setState(397);
				sp();
				setState(398);
				match(AS);
				setState(399);
				sp();
				setState(400);
				variable();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(402);
				expression();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OrderContext extends ParserRuleContext {
		public TerminalNode ORDER() { return getToken(CypherParser.ORDER, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode BY() { return getToken(CypherParser.BY, 0); }
		public List<SortItemContext> sortItem() {
			return getRuleContexts(SortItemContext.class);
		}
		public SortItemContext sortItem(int i) {
			return getRuleContext(SortItemContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public OrderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_order; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterOrder(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitOrder(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitOrder(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OrderContext order() throws RecognitionException {
		OrderContext _localctx = new OrderContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_order);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(405);
			match(ORDER);
			setState(406);
			sp();
			setState(407);
			match(BY);
			setState(408);
			sp();
			setState(409);
			sortItem();
			setState(416);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(410);
				match(T__1);
				setState(411);
				ws();
				setState(412);
				sortItem();
				}
				}
				setState(418);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SkipContext extends ParserRuleContext {
		public TerminalNode L_SKIP() { return getToken(CypherParser.L_SKIP, 0); }
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SkipContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_skip; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSkip(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSkip(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitSkip(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SkipContext skip() throws RecognitionException {
		SkipContext _localctx = new SkipContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_skip);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(419);
			match(L_SKIP);
			setState(420);
			sp();
			setState(421);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LimitContext extends ParserRuleContext {
		public TerminalNode LIMIT() { return getToken(CypherParser.LIMIT, 0); }
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public LimitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_limit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterLimit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitLimit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitLimit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LimitContext limit() throws RecognitionException {
		LimitContext _localctx = new LimitContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_limit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(423);
			match(LIMIT);
			setState(424);
			sp();
			setState(425);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SortItemContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DESCENDING() { return getToken(CypherParser.DESCENDING, 0); }
		public TerminalNode DESC() { return getToken(CypherParser.DESC, 0); }
		public TerminalNode ASCENDING() { return getToken(CypherParser.ASCENDING, 0); }
		public TerminalNode ASC() { return getToken(CypherParser.ASC, 0); }
		public SortItemContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sortItem; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSortItem(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSortItem(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitSortItem(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SortItemContext sortItem() throws RecognitionException {
		SortItemContext _localctx = new SortItemContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_sortItem);
		int _la;
		try {
			setState(434);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(427);
				expression();
				setState(428);
				_la = _input.LA(1);
				if ( !(_la==DESCENDING || _la==DESC) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(430);
				expression();
				setState(432);
				_la = _input.LA(1);
				if (_la==ASCENDING || _la==ASC) {
					{
					setState(431);
					_la = _input.LA(1);
					if ( !(_la==ASCENDING || _la==ASC) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
				}

				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhereContext extends ParserRuleContext {
		public TerminalNode WHERE() { return getToken(CypherParser.WHERE, 0); }
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public WhereContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_where; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterWhere(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitWhere(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitWhere(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhereContext where() throws RecognitionException {
		WhereContext _localctx = new WhereContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_where);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(436);
			match(WHERE);
			setState(437);
			sp();
			setState(438);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PatternContext extends ParserRuleContext {
		public List<PatternPartContext> patternPart() {
			return getRuleContexts(PatternPartContext.class);
		}
		public PatternPartContext patternPart(int i) {
			return getRuleContext(PatternPartContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public PatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternContext pattern() throws RecognitionException {
		PatternContext _localctx = new PatternContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_pattern);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(440);
			patternPart();
			setState(448);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(441);
					ws();
					setState(442);
					match(T__1);
					setState(443);
					ws();
					setState(444);
					patternPart();
					}
					} 
				}
				setState(450);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PatternPartContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public AnonymousPatternPartContext anonymousPatternPart() {
			return getRuleContext(AnonymousPatternPartContext.class,0);
		}
		public PatternPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patternPart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPatternPart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPatternPart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPatternPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternPartContext patternPart() throws RecognitionException {
		PatternPartContext _localctx = new PatternPartContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_patternPart);
		try {
			setState(458);
			switch (_input.LA(1)) {
			case HexString:
			case UNION:
			case ALL:
			case OPTIONAL:
			case MATCH:
			case UNWIND:
			case AS:
			case MERGE:
			case ON:
			case CREATE:
			case SET:
			case DELETE:
			case DETACH:
			case REMOVE:
			case WITH:
			case DISTINCT:
			case RETURN:
			case ORDER:
			case BY:
			case L_SKIP:
			case LIMIT:
			case DESCENDING:
			case DESC:
			case ASCENDING:
			case ASC:
			case WHERE:
			case OR:
			case XOR:
			case AND:
			case NOT:
			case IN:
			case STARTS:
			case ENDS:
			case CONTAINS:
			case IS:
			case NULL:
			case TRUE:
			case FALSE:
			case COUNT:
			case FILTER:
			case EXTRACT:
			case ANY:
			case NONE:
			case SINGLE:
			case UnescapedSymbolicName:
			case EscapedSymbolicName:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(451);
				variable();
				setState(452);
				ws();
				setState(453);
				match(T__2);
				setState(454);
				ws();
				setState(455);
				anonymousPatternPart();
				}
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(457);
				anonymousPatternPart();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnonymousPatternPartContext extends ParserRuleContext {
		public PatternElementContext patternElement() {
			return getRuleContext(PatternElementContext.class,0);
		}
		public AnonymousPatternPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anonymousPatternPart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterAnonymousPatternPart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitAnonymousPatternPart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitAnonymousPatternPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnonymousPatternPartContext anonymousPatternPart() throws RecognitionException {
		AnonymousPatternPartContext _localctx = new AnonymousPatternPartContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_anonymousPatternPart);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(460);
			patternElement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PatternElementContext extends ParserRuleContext {
		public NodePatternContext nodePattern() {
			return getRuleContext(NodePatternContext.class,0);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<PatternElementChainContext> patternElementChain() {
			return getRuleContexts(PatternElementChainContext.class);
		}
		public PatternElementChainContext patternElementChain(int i) {
			return getRuleContext(PatternElementChainContext.class,i);
		}
		public PatternElementContext patternElement() {
			return getRuleContext(PatternElementContext.class,0);
		}
		public PatternElementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patternElement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPatternElement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPatternElement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPatternElement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternElementContext patternElement() throws RecognitionException {
		PatternElementContext _localctx = new PatternElementContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_patternElement);
		try {
			int _alt;
			setState(475);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(462);
				nodePattern();
				setState(468);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(463);
						ws();
						setState(464);
						patternElementChain();
						}
						} 
					}
					setState(470);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
				}
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(471);
				match(T__5);
				setState(472);
				patternElement();
				setState(473);
				match(T__6);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodePatternContext extends ParserRuleContext {
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public NodeLabelsContext nodeLabels() {
			return getRuleContext(NodeLabelsContext.class,0);
		}
		public PropertiesContext properties() {
			return getRuleContext(PropertiesContext.class,0);
		}
		public NodePatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodePattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterNodePattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitNodePattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitNodePattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodePatternContext nodePattern() throws RecognitionException {
		NodePatternContext _localctx = new NodePatternContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_nodePattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(477);
			match(T__5);
			setState(478);
			ws();
			setState(482);
			_la = _input.LA(1);
			if (((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (HexString - 57)) | (1L << (UNION - 57)) | (1L << (ALL - 57)) | (1L << (OPTIONAL - 57)) | (1L << (MATCH - 57)) | (1L << (UNWIND - 57)) | (1L << (AS - 57)) | (1L << (MERGE - 57)) | (1L << (ON - 57)) | (1L << (CREATE - 57)) | (1L << (SET - 57)) | (1L << (DELETE - 57)) | (1L << (DETACH - 57)) | (1L << (REMOVE - 57)) | (1L << (WITH - 57)) | (1L << (DISTINCT - 57)) | (1L << (RETURN - 57)) | (1L << (ORDER - 57)) | (1L << (BY - 57)) | (1L << (L_SKIP - 57)) | (1L << (LIMIT - 57)) | (1L << (DESCENDING - 57)) | (1L << (DESC - 57)) | (1L << (ASCENDING - 57)) | (1L << (ASC - 57)) | (1L << (WHERE - 57)) | (1L << (OR - 57)) | (1L << (XOR - 57)) | (1L << (AND - 57)) | (1L << (NOT - 57)) | (1L << (IN - 57)) | (1L << (STARTS - 57)) | (1L << (ENDS - 57)) | (1L << (CONTAINS - 57)) | (1L << (IS - 57)) | (1L << (NULL - 57)) | (1L << (TRUE - 57)) | (1L << (FALSE - 57)) | (1L << (COUNT - 57)) | (1L << (FILTER - 57)) | (1L << (EXTRACT - 57)) | (1L << (ANY - 57)) | (1L << (NONE - 57)) | (1L << (SINGLE - 57)) | (1L << (UnescapedSymbolicName - 57)) | (1L << (EscapedSymbolicName - 57)))) != 0)) {
				{
				setState(479);
				variable();
				setState(480);
				ws();
				}
			}

			setState(487);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(484);
				nodeLabels();
				setState(485);
				ws();
				}
			}

			setState(492);
			_la = _input.LA(1);
			if (_la==T__27 || _la==T__29) {
				{
				setState(489);
				properties();
				setState(490);
				ws();
				}
			}

			setState(494);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PatternElementChainContext extends ParserRuleContext {
		public RelationshipPatternContext relationshipPattern() {
			return getRuleContext(RelationshipPatternContext.class,0);
		}
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public NodePatternContext nodePattern() {
			return getRuleContext(NodePatternContext.class,0);
		}
		public PatternElementChainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_patternElementChain; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPatternElementChain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPatternElementChain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPatternElementChain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternElementChainContext patternElementChain() throws RecognitionException {
		PatternElementChainContext _localctx = new PatternElementChainContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_patternElementChain);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(496);
			relationshipPattern();
			setState(497);
			ws();
			setState(498);
			nodePattern();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationshipPatternContext extends ParserRuleContext {
		public LeftArrowHeadContext leftArrowHead() {
			return getRuleContext(LeftArrowHeadContext.class,0);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<DashContext> dash() {
			return getRuleContexts(DashContext.class);
		}
		public DashContext dash(int i) {
			return getRuleContext(DashContext.class,i);
		}
		public RightArrowHeadContext rightArrowHead() {
			return getRuleContext(RightArrowHeadContext.class,0);
		}
		public RelationshipDetailContext relationshipDetail() {
			return getRuleContext(RelationshipDetailContext.class,0);
		}
		public RelationshipPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelationshipPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelationshipPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRelationshipPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipPatternContext relationshipPattern() throws RecognitionException {
		RelationshipPatternContext _localctx = new RelationshipPatternContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_relationshipPattern);
		int _la;
		try {
			setState(540);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(500);
				leftArrowHead();
				setState(501);
				ws();
				setState(502);
				dash();
				setState(503);
				ws();
				setState(505);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(504);
					relationshipDetail();
					}
				}

				setState(507);
				ws();
				setState(508);
				dash();
				setState(509);
				ws();
				setState(510);
				rightArrowHead();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(512);
				leftArrowHead();
				setState(513);
				ws();
				setState(514);
				dash();
				setState(515);
				ws();
				setState(517);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(516);
					relationshipDetail();
					}
				}

				setState(519);
				ws();
				setState(520);
				dash();
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(522);
				dash();
				setState(523);
				ws();
				setState(525);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(524);
					relationshipDetail();
					}
				}

				setState(527);
				ws();
				setState(528);
				dash();
				setState(529);
				ws();
				setState(530);
				rightArrowHead();
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(532);
				dash();
				setState(533);
				ws();
				setState(535);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(534);
					relationshipDetail();
					}
				}

				setState(537);
				ws();
				setState(538);
				dash();
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationshipDetailContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public RelationshipTypesContext relationshipTypes() {
			return getRuleContext(RelationshipTypesContext.class,0);
		}
		public RangeLiteralContext rangeLiteral() {
			return getRuleContext(RangeLiteralContext.class,0);
		}
		public PropertiesContext properties() {
			return getRuleContext(PropertiesContext.class,0);
		}
		public RelationshipDetailContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipDetail; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelationshipDetail(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelationshipDetail(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRelationshipDetail(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipDetailContext relationshipDetail() throws RecognitionException {
		RelationshipDetailContext _localctx = new RelationshipDetailContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_relationshipDetail);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(542);
			match(T__7);
			setState(544);
			_la = _input.LA(1);
			if (((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (HexString - 57)) | (1L << (UNION - 57)) | (1L << (ALL - 57)) | (1L << (OPTIONAL - 57)) | (1L << (MATCH - 57)) | (1L << (UNWIND - 57)) | (1L << (AS - 57)) | (1L << (MERGE - 57)) | (1L << (ON - 57)) | (1L << (CREATE - 57)) | (1L << (SET - 57)) | (1L << (DELETE - 57)) | (1L << (DETACH - 57)) | (1L << (REMOVE - 57)) | (1L << (WITH - 57)) | (1L << (DISTINCT - 57)) | (1L << (RETURN - 57)) | (1L << (ORDER - 57)) | (1L << (BY - 57)) | (1L << (L_SKIP - 57)) | (1L << (LIMIT - 57)) | (1L << (DESCENDING - 57)) | (1L << (DESC - 57)) | (1L << (ASCENDING - 57)) | (1L << (ASC - 57)) | (1L << (WHERE - 57)) | (1L << (OR - 57)) | (1L << (XOR - 57)) | (1L << (AND - 57)) | (1L << (NOT - 57)) | (1L << (IN - 57)) | (1L << (STARTS - 57)) | (1L << (ENDS - 57)) | (1L << (CONTAINS - 57)) | (1L << (IS - 57)) | (1L << (NULL - 57)) | (1L << (TRUE - 57)) | (1L << (FALSE - 57)) | (1L << (COUNT - 57)) | (1L << (FILTER - 57)) | (1L << (EXTRACT - 57)) | (1L << (ANY - 57)) | (1L << (NONE - 57)) | (1L << (SINGLE - 57)) | (1L << (UnescapedSymbolicName - 57)) | (1L << (EscapedSymbolicName - 57)))) != 0)) {
				{
				setState(543);
				variable();
				}
			}

			setState(547);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(546);
				match(T__8);
				}
			}

			setState(550);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(549);
				relationshipTypes();
				}
			}

			setState(554);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(552);
				match(T__4);
				setState(553);
				rangeLiteral();
				}
			}

			setState(557);
			_la = _input.LA(1);
			if (_la==T__27 || _la==T__29) {
				{
				setState(556);
				properties();
				}
			}

			setState(559);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertiesContext extends ParserRuleContext {
		public MapLiteralContext mapLiteral() {
			return getRuleContext(MapLiteralContext.class,0);
		}
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public PropertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_properties; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterProperties(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitProperties(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitProperties(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertiesContext properties() throws RecognitionException {
		PropertiesContext _localctx = new PropertiesContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_properties);
		try {
			setState(563);
			switch (_input.LA(1)) {
			case T__27:
				enterOuterAlt(_localctx, 1);
				{
				setState(561);
				mapLiteral();
				}
				break;
			case T__29:
				enterOuterAlt(_localctx, 2);
				{
				setState(562);
				parameter();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationshipTypesContext extends ParserRuleContext {
		public List<RelTypeNameContext> relTypeName() {
			return getRuleContexts(RelTypeNameContext.class);
		}
		public RelTypeNameContext relTypeName(int i) {
			return getRuleContext(RelTypeNameContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public RelationshipTypesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipTypes; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelationshipTypes(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelationshipTypes(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRelationshipTypes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipTypesContext relationshipTypes() throws RecognitionException {
		RelationshipTypesContext _localctx = new RelationshipTypesContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_relationshipTypes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(565);
			match(T__10);
			setState(566);
			relTypeName();
			setState(577);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11 || _la==WHITESPACE) {
				{
				{
				setState(567);
				ws();
				setState(568);
				match(T__11);
				setState(570);
				_la = _input.LA(1);
				if (_la==T__10) {
					{
					setState(569);
					match(T__10);
					}
				}

				setState(572);
				ws();
				setState(573);
				relTypeName();
				}
				}
				setState(579);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeLabelsContext extends ParserRuleContext {
		public List<NodeLabelContext> nodeLabel() {
			return getRuleContexts(NodeLabelContext.class);
		}
		public NodeLabelContext nodeLabel(int i) {
			return getRuleContext(NodeLabelContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public NodeLabelsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeLabels; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterNodeLabels(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitNodeLabels(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitNodeLabels(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodeLabelsContext nodeLabels() throws RecognitionException {
		NodeLabelsContext _localctx = new NodeLabelsContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_nodeLabels);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(580);
			nodeLabel();
			setState(586);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,50,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(581);
					ws();
					setState(582);
					nodeLabel();
					}
					} 
				}
				setState(588);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,50,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NodeLabelContext extends ParserRuleContext {
		public LabelNameContext labelName() {
			return getRuleContext(LabelNameContext.class,0);
		}
		public NodeLabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeLabel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterNodeLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitNodeLabel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitNodeLabel(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodeLabelContext nodeLabel() throws RecognitionException {
		NodeLabelContext _localctx = new NodeLabelContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_nodeLabel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(589);
			match(T__10);
			setState(590);
			labelName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RangeLiteralContext extends ParserRuleContext {
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<IntegerLiteralContext> integerLiteral() {
			return getRuleContexts(IntegerLiteralContext.class);
		}
		public IntegerLiteralContext integerLiteral(int i) {
			return getRuleContext(IntegerLiteralContext.class,i);
		}
		public RangeLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rangeLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRangeLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRangeLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRangeLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RangeLiteralContext rangeLiteral() throws RecognitionException {
		RangeLiteralContext _localctx = new RangeLiteralContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_rangeLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(592);
			ws();
			setState(596);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HexInteger) | (1L << DecimalInteger) | (1L << OctalInteger))) != 0)) {
				{
				setState(593);
				integerLiteral();
				setState(594);
				ws();
				}
			}

			setState(605);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(598);
				match(T__12);
				setState(599);
				ws();
				setState(603);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HexInteger) | (1L << DecimalInteger) | (1L << OctalInteger))) != 0)) {
					{
					setState(600);
					integerLiteral();
					setState(601);
					ws();
					}
				}

				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LabelNameContext extends ParserRuleContext {
		public SymbolicNameContext symbolicName() {
			return getRuleContext(SymbolicNameContext.class,0);
		}
		public LabelNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_labelName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterLabelName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitLabelName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitLabelName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LabelNameContext labelName() throws RecognitionException {
		LabelNameContext _localctx = new LabelNameContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_labelName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(607);
			symbolicName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelTypeNameContext extends ParserRuleContext {
		public SymbolicNameContext symbolicName() {
			return getRuleContext(SymbolicNameContext.class,0);
		}
		public RelTypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relTypeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelTypeName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRelTypeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelTypeNameContext relTypeName() throws RecognitionException {
		RelTypeNameContext _localctx = new RelTypeNameContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_relTypeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(609);
			symbolicName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public Expression12Context expression12() {
			return getRuleContext(Expression12Context.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(611);
			expression12();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression12Context extends ParserRuleContext {
		public List<Expression11Context> expression11() {
			return getRuleContexts(Expression11Context.class);
		}
		public Expression11Context expression11(int i) {
			return getRuleContext(Expression11Context.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public List<TerminalNode> OR() { return getTokens(CypherParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(CypherParser.OR, i);
		}
		public Expression12Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression12; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression12(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression12(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression12(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression12Context expression12() throws RecognitionException {
		Expression12Context _localctx = new Expression12Context(_ctx, getState());
		enterRule(_localctx, 86, RULE_expression12);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(613);
			expression11();
			setState(621);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(614);
					sp();
					setState(615);
					match(OR);
					setState(616);
					sp();
					setState(617);
					expression11();
					}
					} 
				}
				setState(623);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression11Context extends ParserRuleContext {
		public List<Expression10Context> expression10() {
			return getRuleContexts(Expression10Context.class);
		}
		public Expression10Context expression10(int i) {
			return getRuleContext(Expression10Context.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public List<TerminalNode> XOR() { return getTokens(CypherParser.XOR); }
		public TerminalNode XOR(int i) {
			return getToken(CypherParser.XOR, i);
		}
		public Expression11Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression11; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression11(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression11(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression11(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression11Context expression11() throws RecognitionException {
		Expression11Context _localctx = new Expression11Context(_ctx, getState());
		enterRule(_localctx, 88, RULE_expression11);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(624);
			expression10();
			setState(632);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(625);
					sp();
					setState(626);
					match(XOR);
					setState(627);
					sp();
					setState(628);
					expression10();
					}
					} 
				}
				setState(634);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression10Context extends ParserRuleContext {
		public List<Expression9Context> expression9() {
			return getRuleContexts(Expression9Context.class);
		}
		public Expression9Context expression9(int i) {
			return getRuleContext(Expression9Context.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public List<TerminalNode> AND() { return getTokens(CypherParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(CypherParser.AND, i);
		}
		public Expression10Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression10; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression10(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression10(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression10(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression10Context expression10() throws RecognitionException {
		Expression10Context _localctx = new Expression10Context(_ctx, getState());
		enterRule(_localctx, 90, RULE_expression10);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(635);
			expression9();
			setState(643);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(636);
					sp();
					setState(637);
					match(AND);
					setState(638);
					sp();
					setState(639);
					expression9();
					}
					} 
				}
				setState(645);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression9Context extends ParserRuleContext {
		public Expression8Context expression8() {
			return getRuleContext(Expression8Context.class,0);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public List<TerminalNode> NOT() { return getTokens(CypherParser.NOT); }
		public TerminalNode NOT(int i) {
			return getToken(CypherParser.NOT, i);
		}
		public Expression9Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression9; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression9(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression9(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression9(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression9Context expression9() throws RecognitionException {
		Expression9Context _localctx = new Expression9Context(_ctx, getState());
		enterRule(_localctx, 92, RULE_expression9);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(652);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(646);
				sp();
				setState(647);
				match(NOT);
				setState(648);
				sp();
				}
				}
				setState(654);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(655);
			expression8();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression8Context extends ParserRuleContext {
		public Expression7Context expression7() {
			return getRuleContext(Expression7Context.class,0);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<PartialComparisonExpressionContext> partialComparisonExpression() {
			return getRuleContexts(PartialComparisonExpressionContext.class);
		}
		public PartialComparisonExpressionContext partialComparisonExpression(int i) {
			return getRuleContext(PartialComparisonExpressionContext.class,i);
		}
		public Expression8Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression8; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression8(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression8(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression8(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression8Context expression8() throws RecognitionException {
		Expression8Context _localctx = new Expression8Context(_ctx, getState());
		enterRule(_localctx, 94, RULE_expression8);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(657);
			expression7();
			setState(663);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(658);
					ws();
					setState(659);
					partialComparisonExpression();
					}
					} 
				}
				setState(665);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression7Context extends ParserRuleContext {
		public List<Expression6Context> expression6() {
			return getRuleContexts(Expression6Context.class);
		}
		public Expression6Context expression6(int i) {
			return getRuleContext(Expression6Context.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public Expression7Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression7; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression7(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression7(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression7(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression7Context expression7() throws RecognitionException {
		Expression7Context _localctx = new Expression7Context(_ctx, getState());
		enterRule(_localctx, 96, RULE_expression7);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(666);
			expression6();
			setState(679);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(677);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
					case 1:
						{
						{
						setState(667);
						ws();
						setState(668);
						match(T__13);
						setState(669);
						ws();
						setState(670);
						expression6();
						}
						}
						break;
					case 2:
						{
						{
						setState(672);
						ws();
						setState(673);
						match(T__14);
						setState(674);
						ws();
						setState(675);
						expression6();
						}
						}
						break;
					}
					} 
				}
				setState(681);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression6Context extends ParserRuleContext {
		public List<Expression5Context> expression5() {
			return getRuleContexts(Expression5Context.class);
		}
		public Expression5Context expression5(int i) {
			return getRuleContext(Expression5Context.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public Expression6Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression6; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression6(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression6(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression6(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression6Context expression6() throws RecognitionException {
		Expression6Context _localctx = new Expression6Context(_ctx, getState());
		enterRule(_localctx, 98, RULE_expression6);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(682);
			expression5();
			setState(700);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(698);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
					case 1:
						{
						{
						setState(683);
						ws();
						setState(684);
						match(T__4);
						setState(685);
						ws();
						setState(686);
						expression5();
						}
						}
						break;
					case 2:
						{
						{
						setState(688);
						ws();
						setState(689);
						match(T__15);
						setState(690);
						ws();
						setState(691);
						expression5();
						}
						}
						break;
					case 3:
						{
						{
						setState(693);
						ws();
						setState(694);
						match(T__16);
						setState(695);
						ws();
						setState(696);
						expression5();
						}
						}
						break;
					}
					} 
				}
				setState(702);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression5Context extends ParserRuleContext {
		public List<Expression4Context> expression4() {
			return getRuleContexts(Expression4Context.class);
		}
		public Expression4Context expression4(int i) {
			return getRuleContext(Expression4Context.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public Expression5Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression5; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression5(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression5(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression5(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression5Context expression5() throws RecognitionException {
		Expression5Context _localctx = new Expression5Context(_ctx, getState());
		enterRule(_localctx, 100, RULE_expression5);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(703);
			expression4();
			setState(711);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(704);
					ws();
					setState(705);
					match(T__17);
					setState(706);
					ws();
					setState(707);
					expression4();
					}
					} 
				}
				setState(713);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression4Context extends ParserRuleContext {
		public Expression3Context expression3() {
			return getRuleContext(Expression3Context.class,0);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public Expression4Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression4; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression4(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression4(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression4(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression4Context expression4() throws RecognitionException {
		Expression4Context _localctx = new Expression4Context(_ctx, getState());
		enterRule(_localctx, 102, RULE_expression4);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(718);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13 || _la==T__14) {
				{
				{
				setState(714);
				_la = _input.LA(1);
				if ( !(_la==T__13 || _la==T__14) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(715);
				ws();
				}
				}
				setState(720);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(721);
			expression3();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression3Context extends ParserRuleContext {
		public List<Expression2Context> expression2() {
			return getRuleContexts(Expression2Context.class);
		}
		public Expression2Context expression2(int i) {
			return getRuleContext(Expression2Context.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public List<TerminalNode> IS() { return getTokens(CypherParser.IS); }
		public TerminalNode IS(int i) {
			return getToken(CypherParser.IS, i);
		}
		public List<TerminalNode> NULL() { return getTokens(CypherParser.NULL); }
		public TerminalNode NULL(int i) {
			return getToken(CypherParser.NULL, i);
		}
		public List<TerminalNode> NOT() { return getTokens(CypherParser.NOT); }
		public TerminalNode NOT(int i) {
			return getToken(CypherParser.NOT, i);
		}
		public List<TerminalNode> IN() { return getTokens(CypherParser.IN); }
		public TerminalNode IN(int i) {
			return getToken(CypherParser.IN, i);
		}
		public List<TerminalNode> STARTS() { return getTokens(CypherParser.STARTS); }
		public TerminalNode STARTS(int i) {
			return getToken(CypherParser.STARTS, i);
		}
		public List<TerminalNode> WITH() { return getTokens(CypherParser.WITH); }
		public TerminalNode WITH(int i) {
			return getToken(CypherParser.WITH, i);
		}
		public List<TerminalNode> ENDS() { return getTokens(CypherParser.ENDS); }
		public TerminalNode ENDS(int i) {
			return getToken(CypherParser.ENDS, i);
		}
		public List<TerminalNode> CONTAINS() { return getTokens(CypherParser.CONTAINS); }
		public TerminalNode CONTAINS(int i) {
			return getToken(CypherParser.CONTAINS, i);
		}
		public Expression3Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression3; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression3(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression3(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression3Context expression3() throws RecognitionException {
		Expression3Context _localctx = new Expression3Context(_ctx, getState());
		enterRule(_localctx, 104, RULE_expression3);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(723);
			expression2();
			setState(778);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,69,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(776);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
					case 1:
						{
						{
						setState(724);
						ws();
						setState(725);
						match(T__7);
						setState(726);
						expression();
						setState(727);
						match(T__9);
						}
						}
						break;
					case 2:
						{
						{
						setState(729);
						ws();
						setState(730);
						match(T__7);
						setState(732);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__7) | (1L << T__13) | (1L << T__14) | (1L << T__25) | (1L << T__27) | (1L << T__29) | (1L << StringLiteral) | (1L << HexInteger) | (1L << DecimalInteger) | (1L << OctalInteger) | (1L << HexString) | (1L << Digit) | (1L << UNION))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (ALL - 64)) | (1L << (OPTIONAL - 64)) | (1L << (MATCH - 64)) | (1L << (UNWIND - 64)) | (1L << (AS - 64)) | (1L << (MERGE - 64)) | (1L << (ON - 64)) | (1L << (CREATE - 64)) | (1L << (SET - 64)) | (1L << (DELETE - 64)) | (1L << (DETACH - 64)) | (1L << (REMOVE - 64)) | (1L << (WITH - 64)) | (1L << (DISTINCT - 64)) | (1L << (RETURN - 64)) | (1L << (ORDER - 64)) | (1L << (BY - 64)) | (1L << (L_SKIP - 64)) | (1L << (LIMIT - 64)) | (1L << (DESCENDING - 64)) | (1L << (DESC - 64)) | (1L << (ASCENDING - 64)) | (1L << (ASC - 64)) | (1L << (WHERE - 64)) | (1L << (OR - 64)) | (1L << (XOR - 64)) | (1L << (AND - 64)) | (1L << (NOT - 64)) | (1L << (IN - 64)) | (1L << (STARTS - 64)) | (1L << (ENDS - 64)) | (1L << (CONTAINS - 64)) | (1L << (IS - 64)) | (1L << (NULL - 64)) | (1L << (TRUE - 64)) | (1L << (FALSE - 64)) | (1L << (COUNT - 64)) | (1L << (FILTER - 64)) | (1L << (EXTRACT - 64)) | (1L << (ANY - 64)) | (1L << (NONE - 64)) | (1L << (SINGLE - 64)) | (1L << (UnescapedSymbolicName - 64)) | (1L << (EscapedSymbolicName - 64)) | (1L << (WHITESPACE - 64)))) != 0)) {
							{
							setState(731);
							expression();
							}
						}

						setState(734);
						match(T__12);
						setState(736);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__7) | (1L << T__13) | (1L << T__14) | (1L << T__25) | (1L << T__27) | (1L << T__29) | (1L << StringLiteral) | (1L << HexInteger) | (1L << DecimalInteger) | (1L << OctalInteger) | (1L << HexString) | (1L << Digit) | (1L << UNION))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (ALL - 64)) | (1L << (OPTIONAL - 64)) | (1L << (MATCH - 64)) | (1L << (UNWIND - 64)) | (1L << (AS - 64)) | (1L << (MERGE - 64)) | (1L << (ON - 64)) | (1L << (CREATE - 64)) | (1L << (SET - 64)) | (1L << (DELETE - 64)) | (1L << (DETACH - 64)) | (1L << (REMOVE - 64)) | (1L << (WITH - 64)) | (1L << (DISTINCT - 64)) | (1L << (RETURN - 64)) | (1L << (ORDER - 64)) | (1L << (BY - 64)) | (1L << (L_SKIP - 64)) | (1L << (LIMIT - 64)) | (1L << (DESCENDING - 64)) | (1L << (DESC - 64)) | (1L << (ASCENDING - 64)) | (1L << (ASC - 64)) | (1L << (WHERE - 64)) | (1L << (OR - 64)) | (1L << (XOR - 64)) | (1L << (AND - 64)) | (1L << (NOT - 64)) | (1L << (IN - 64)) | (1L << (STARTS - 64)) | (1L << (ENDS - 64)) | (1L << (CONTAINS - 64)) | (1L << (IS - 64)) | (1L << (NULL - 64)) | (1L << (TRUE - 64)) | (1L << (FALSE - 64)) | (1L << (COUNT - 64)) | (1L << (FILTER - 64)) | (1L << (EXTRACT - 64)) | (1L << (ANY - 64)) | (1L << (NONE - 64)) | (1L << (SINGLE - 64)) | (1L << (UnescapedSymbolicName - 64)) | (1L << (EscapedSymbolicName - 64)) | (1L << (WHITESPACE - 64)))) != 0)) {
							{
							setState(735);
							expression();
							}
						}

						setState(738);
						match(T__9);
						}
						}
						break;
					case 3:
						{
						{
						setState(759);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
						case 1:
							{
							{
							setState(740);
							ws();
							setState(741);
							match(T__18);
							}
							}
							break;
						case 2:
							{
							{
							setState(743);
							sp();
							setState(744);
							match(IN);
							}
							}
							break;
						case 3:
							{
							{
							setState(746);
							sp();
							setState(747);
							match(STARTS);
							setState(748);
							sp();
							setState(749);
							match(WITH);
							}
							}
							break;
						case 4:
							{
							{
							setState(751);
							sp();
							setState(752);
							match(ENDS);
							setState(753);
							sp();
							setState(754);
							match(WITH);
							}
							}
							break;
						case 5:
							{
							{
							setState(756);
							sp();
							setState(757);
							match(CONTAINS);
							}
							}
							break;
						}
						setState(761);
						ws();
						setState(762);
						expression2();
						}
						}
						break;
					case 4:
						{
						{
						setState(764);
						sp();
						setState(765);
						match(IS);
						setState(766);
						sp();
						setState(767);
						match(NULL);
						}
						}
						break;
					case 5:
						{
						{
						setState(769);
						sp();
						setState(770);
						match(IS);
						setState(771);
						sp();
						setState(772);
						match(NOT);
						setState(773);
						sp();
						setState(774);
						match(NULL);
						}
						}
						break;
					}
					} 
				}
				setState(780);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,69,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Expression2Context extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public List<PropertyLookupContext> propertyLookup() {
			return getRuleContexts(PropertyLookupContext.class);
		}
		public PropertyLookupContext propertyLookup(int i) {
			return getRuleContext(PropertyLookupContext.class,i);
		}
		public List<NodeLabelsContext> nodeLabels() {
			return getRuleContexts(NodeLabelsContext.class);
		}
		public NodeLabelsContext nodeLabels(int i) {
			return getRuleContext(NodeLabelsContext.class,i);
		}
		public Expression2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExpression2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExpression2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExpression2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Expression2Context expression2() throws RecognitionException {
		Expression2Context _localctx = new Expression2Context(_ctx, getState());
		enterRule(_localctx, 106, RULE_expression2);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(781);
			atom();
			setState(786);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,71,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(784);
					switch (_input.LA(1)) {
					case T__25:
					case WHITESPACE:
						{
						setState(782);
						propertyLookup();
						}
						break;
					case T__10:
						{
						setState(783);
						nodeLabels();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(788);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,71,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AtomContext extends ParserRuleContext {
		public NumberLiteralContext numberLiteral() {
			return getRuleContext(NumberLiteralContext.class,0);
		}
		public TerminalNode StringLiteral() { return getToken(CypherParser.StringLiteral, 0); }
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public TerminalNode TRUE() { return getToken(CypherParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(CypherParser.FALSE, 0); }
		public TerminalNode NULL() { return getToken(CypherParser.NULL, 0); }
		public TerminalNode COUNT() { return getToken(CypherParser.COUNT, 0); }
		public MapLiteralContext mapLiteral() {
			return getRuleContext(MapLiteralContext.class,0);
		}
		public ListComprehensionContext listComprehension() {
			return getRuleContext(ListComprehensionContext.class,0);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode FILTER() { return getToken(CypherParser.FILTER, 0); }
		public FilterExpressionContext filterExpression() {
			return getRuleContext(FilterExpressionContext.class,0);
		}
		public TerminalNode EXTRACT() { return getToken(CypherParser.EXTRACT, 0); }
		public TerminalNode ALL() { return getToken(CypherParser.ALL, 0); }
		public TerminalNode ANY() { return getToken(CypherParser.ANY, 0); }
		public TerminalNode NONE() { return getToken(CypherParser.NONE, 0); }
		public TerminalNode SINGLE() { return getToken(CypherParser.SINGLE, 0); }
		public RelationshipsPatternContext relationshipsPattern() {
			return getRuleContext(RelationshipsPatternContext.class,0);
		}
		public ParenthesizedExpressionContext parenthesizedExpression() {
			return getRuleContext(ParenthesizedExpressionContext.class,0);
		}
		public FunctionInvocationContext functionInvocation() {
			return getRuleContext(FunctionInvocationContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterAtom(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitAtom(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_atom);
		int _la;
		try {
			setState(875);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(789);
				numberLiteral();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(790);
				match(StringLiteral);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(791);
				parameter();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(792);
				match(TRUE);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(793);
				match(FALSE);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(794);
				match(NULL);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				{
				setState(795);
				match(COUNT);
				setState(796);
				match(T__5);
				setState(797);
				match(T__4);
				setState(798);
				match(T__6);
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(799);
				mapLiteral();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(800);
				listComprehension();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				{
				setState(801);
				match(T__7);
				setState(802);
				ws();
				setState(803);
				expression();
				setState(804);
				ws();
				setState(812);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(805);
					match(T__1);
					setState(806);
					ws();
					setState(807);
					expression();
					setState(808);
					ws();
					}
					}
					setState(814);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(815);
				match(T__9);
				}
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				{
				setState(817);
				match(FILTER);
				setState(818);
				ws();
				setState(819);
				match(T__5);
				setState(820);
				ws();
				setState(821);
				filterExpression();
				setState(822);
				ws();
				setState(823);
				match(T__6);
				}
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				{
				setState(825);
				match(EXTRACT);
				setState(826);
				ws();
				setState(827);
				match(T__5);
				setState(828);
				ws();
				setState(829);
				filterExpression();
				setState(830);
				ws();
				setState(835);
				_la = _input.LA(1);
				if (_la==T__11 || _la==WHITESPACE) {
					{
					setState(831);
					ws();
					setState(832);
					match(T__11);
					setState(833);
					expression();
					}
				}

				setState(837);
				match(T__6);
				}
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				{
				setState(839);
				match(ALL);
				setState(840);
				ws();
				setState(841);
				match(T__5);
				setState(842);
				ws();
				setState(843);
				filterExpression();
				setState(844);
				ws();
				setState(845);
				match(T__6);
				}
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				{
				setState(847);
				match(ANY);
				setState(848);
				ws();
				setState(849);
				match(T__5);
				setState(850);
				ws();
				setState(851);
				filterExpression();
				setState(852);
				ws();
				setState(853);
				match(T__6);
				}
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				{
				setState(855);
				match(NONE);
				setState(856);
				ws();
				setState(857);
				match(T__5);
				setState(858);
				ws();
				setState(859);
				filterExpression();
				setState(860);
				ws();
				setState(861);
				match(T__6);
				}
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				{
				setState(863);
				match(SINGLE);
				setState(864);
				ws();
				setState(865);
				match(T__5);
				setState(866);
				ws();
				setState(867);
				filterExpression();
				setState(868);
				ws();
				setState(869);
				match(T__6);
				}
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(871);
				relationshipsPattern();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(872);
				parenthesizedExpression();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(873);
				functionInvocation();
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(874);
				variable();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PartialComparisonExpressionContext extends ParserRuleContext {
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public Expression7Context expression7() {
			return getRuleContext(Expression7Context.class,0);
		}
		public PartialComparisonExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_partialComparisonExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPartialComparisonExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPartialComparisonExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPartialComparisonExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PartialComparisonExpressionContext partialComparisonExpression() throws RecognitionException {
		PartialComparisonExpressionContext _localctx = new PartialComparisonExpressionContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_partialComparisonExpression);
		try {
			setState(905);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(877);
				match(T__2);
				setState(878);
				ws();
				setState(879);
				expression7();
				}
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(881);
				match(T__19);
				setState(882);
				ws();
				setState(883);
				expression7();
				}
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(885);
				match(T__20);
				setState(886);
				ws();
				setState(887);
				expression7();
				}
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(889);
				match(T__21);
				setState(890);
				ws();
				setState(891);
				expression7();
				}
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(893);
				match(T__22);
				setState(894);
				ws();
				setState(895);
				expression7();
				}
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 6);
				{
				{
				setState(897);
				match(T__23);
				setState(898);
				ws();
				setState(899);
				expression7();
				}
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 7);
				{
				{
				setState(901);
				match(T__24);
				setState(902);
				ws();
				setState(903);
				expression7();
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParenthesizedExpressionContext extends ParserRuleContext {
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParenthesizedExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parenthesizedExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterParenthesizedExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitParenthesizedExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitParenthesizedExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParenthesizedExpressionContext parenthesizedExpression() throws RecognitionException {
		ParenthesizedExpressionContext _localctx = new ParenthesizedExpressionContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_parenthesizedExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(907);
			match(T__5);
			setState(908);
			ws();
			setState(909);
			expression();
			setState(910);
			ws();
			setState(911);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RelationshipsPatternContext extends ParserRuleContext {
		public NodePatternContext nodePattern() {
			return getRuleContext(NodePatternContext.class,0);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<PatternElementChainContext> patternElementChain() {
			return getRuleContexts(PatternElementChainContext.class);
		}
		public PatternElementChainContext patternElementChain(int i) {
			return getRuleContext(PatternElementChainContext.class,i);
		}
		public RelationshipsPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipsPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelationshipsPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelationshipsPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRelationshipsPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipsPatternContext relationshipsPattern() throws RecognitionException {
		RelationshipsPatternContext _localctx = new RelationshipsPatternContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_relationshipsPattern);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(913);
			nodePattern();
			setState(917); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(914);
					ws();
					setState(915);
					patternElementChain();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(919); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FilterExpressionContext extends ParserRuleContext {
		public IdInCollContext idInColl() {
			return getRuleContext(IdInCollContext.class,0);
		}
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public FilterExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_filterExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterFilterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitFilterExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitFilterExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FilterExpressionContext filterExpression() throws RecognitionException {
		FilterExpressionContext _localctx = new FilterExpressionContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_filterExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(921);
			idInColl();
			setState(925);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
			case 1:
				{
				setState(922);
				ws();
				setState(923);
				where();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdInCollContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode IN() { return getToken(CypherParser.IN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IdInCollContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idInColl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterIdInColl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitIdInColl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitIdInColl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdInCollContext idInColl() throws RecognitionException {
		IdInCollContext _localctx = new IdInCollContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_idInColl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(927);
			variable();
			setState(928);
			sp();
			setState(929);
			match(IN);
			setState(930);
			sp();
			setState(931);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionInvocationContext extends ParserRuleContext {
		public FunctionNameContext functionName() {
			return getRuleContext(FunctionNameContext.class,0);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode DISTINCT() { return getToken(CypherParser.DISTINCT, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public FunctionInvocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionInvocation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterFunctionInvocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitFunctionInvocation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitFunctionInvocation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionInvocationContext functionInvocation() throws RecognitionException {
		FunctionInvocationContext _localctx = new FunctionInvocationContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_functionInvocation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(933);
			functionName();
			setState(934);
			ws();
			setState(935);
			match(T__5);
			setState(936);
			ws();
			setState(938);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
			case 1:
				{
				setState(937);
				match(DISTINCT);
				}
				break;
			}
			setState(950);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
			case 1:
				{
				setState(940);
				expression();
				setState(947);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(941);
					match(T__1);
					setState(942);
					ws();
					setState(943);
					expression();
					}
					}
					setState(949);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
			setState(952);
			ws();
			setState(953);
			match(T__6);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunctionNameContext extends ParserRuleContext {
		public SymbolicNameContext symbolicName() {
			return getRuleContext(SymbolicNameContext.class,0);
		}
		public FunctionNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterFunctionName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitFunctionName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitFunctionName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionNameContext functionName() throws RecognitionException {
		FunctionNameContext _localctx = new FunctionNameContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_functionName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(955);
			symbolicName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ListComprehensionContext extends ParserRuleContext {
		public FilterExpressionContext filterExpression() {
			return getRuleContext(FilterExpressionContext.class,0);
		}
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ListComprehensionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_listComprehension; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterListComprehension(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitListComprehension(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitListComprehension(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListComprehensionContext listComprehension() throws RecognitionException {
		ListComprehensionContext _localctx = new ListComprehensionContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_listComprehension);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(957);
			match(T__7);
			setState(958);
			filterExpression();
			setState(963);
			_la = _input.LA(1);
			if (_la==T__11 || _la==WHITESPACE) {
				{
				setState(959);
				ws();
				setState(960);
				match(T__11);
				setState(961);
				expression();
				}
			}

			setState(965);
			match(T__9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyLookupContext extends ParserRuleContext {
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public PropertyKeyNameContext propertyKeyName() {
			return getRuleContext(PropertyKeyNameContext.class,0);
		}
		public PropertyLookupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyLookup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPropertyLookup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPropertyLookup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPropertyLookup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyLookupContext propertyLookup() throws RecognitionException {
		PropertyLookupContext _localctx = new PropertyLookupContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_propertyLookup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(967);
			ws();
			setState(968);
			match(T__25);
			setState(969);
			ws();
			setState(974);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
			case 1:
				{
				{
				setState(970);
				propertyKeyName();
				setState(971);
				_la = _input.LA(1);
				if ( !(_la==T__8 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				}
				break;
			case 2:
				{
				setState(973);
				propertyKeyName();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public SymbolicNameContext symbolicName() {
			return getRuleContext(SymbolicNameContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(976);
			symbolicName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberLiteralContext extends ParserRuleContext {
		public DoubleLiteralContext doubleLiteral() {
			return getRuleContext(DoubleLiteralContext.class,0);
		}
		public IntegerLiteralContext integerLiteral() {
			return getRuleContext(IntegerLiteralContext.class,0);
		}
		public NumberLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numberLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterNumberLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitNumberLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitNumberLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberLiteralContext numberLiteral() throws RecognitionException {
		NumberLiteralContext _localctx = new NumberLiteralContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_numberLiteral);
		try {
			setState(980);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(978);
				doubleLiteral();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(979);
				integerLiteral();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MapLiteralContext extends ParserRuleContext {
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<PropertyKeyNameContext> propertyKeyName() {
			return getRuleContexts(PropertyKeyNameContext.class);
		}
		public PropertyKeyNameContext propertyKeyName(int i) {
			return getRuleContext(PropertyKeyNameContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public MapLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mapLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterMapLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitMapLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitMapLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MapLiteralContext mapLiteral() throws RecognitionException {
		MapLiteralContext _localctx = new MapLiteralContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_mapLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(982);
			match(T__27);
			setState(983);
			ws();
			setState(1004);
			_la = _input.LA(1);
			if (((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (HexString - 57)) | (1L << (UNION - 57)) | (1L << (ALL - 57)) | (1L << (OPTIONAL - 57)) | (1L << (MATCH - 57)) | (1L << (UNWIND - 57)) | (1L << (AS - 57)) | (1L << (MERGE - 57)) | (1L << (ON - 57)) | (1L << (CREATE - 57)) | (1L << (SET - 57)) | (1L << (DELETE - 57)) | (1L << (DETACH - 57)) | (1L << (REMOVE - 57)) | (1L << (WITH - 57)) | (1L << (DISTINCT - 57)) | (1L << (RETURN - 57)) | (1L << (ORDER - 57)) | (1L << (BY - 57)) | (1L << (L_SKIP - 57)) | (1L << (LIMIT - 57)) | (1L << (DESCENDING - 57)) | (1L << (DESC - 57)) | (1L << (ASCENDING - 57)) | (1L << (ASC - 57)) | (1L << (WHERE - 57)) | (1L << (OR - 57)) | (1L << (XOR - 57)) | (1L << (AND - 57)) | (1L << (NOT - 57)) | (1L << (IN - 57)) | (1L << (STARTS - 57)) | (1L << (ENDS - 57)) | (1L << (CONTAINS - 57)) | (1L << (IS - 57)) | (1L << (NULL - 57)) | (1L << (TRUE - 57)) | (1L << (FALSE - 57)) | (1L << (COUNT - 57)) | (1L << (FILTER - 57)) | (1L << (EXTRACT - 57)) | (1L << (ANY - 57)) | (1L << (NONE - 57)) | (1L << (SINGLE - 57)) | (1L << (UnescapedSymbolicName - 57)) | (1L << (EscapedSymbolicName - 57)))) != 0)) {
				{
				setState(984);
				propertyKeyName();
				setState(985);
				ws();
				setState(986);
				match(T__10);
				setState(987);
				ws();
				setState(988);
				expression();
				setState(989);
				ws();
				setState(1001);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(990);
					match(T__1);
					setState(991);
					ws();
					setState(992);
					propertyKeyName();
					setState(993);
					ws();
					setState(994);
					match(T__10);
					setState(995);
					ws();
					setState(996);
					expression();
					setState(997);
					ws();
					}
					}
					setState(1003);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(1006);
			match(T__28);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParameterContext extends ParserRuleContext {
		public SymbolicNameContext symbolicName() {
			return getRuleContext(SymbolicNameContext.class,0);
		}
		public TerminalNode DecimalInteger() { return getToken(CypherParser.DecimalInteger, 0); }
		public ParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterContext parameter() throws RecognitionException {
		ParameterContext _localctx = new ParameterContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1008);
			match(T__29);
			setState(1011);
			switch (_input.LA(1)) {
			case HexString:
			case UNION:
			case ALL:
			case OPTIONAL:
			case MATCH:
			case UNWIND:
			case AS:
			case MERGE:
			case ON:
			case CREATE:
			case SET:
			case DELETE:
			case DETACH:
			case REMOVE:
			case WITH:
			case DISTINCT:
			case RETURN:
			case ORDER:
			case BY:
			case L_SKIP:
			case LIMIT:
			case DESCENDING:
			case DESC:
			case ASCENDING:
			case ASC:
			case WHERE:
			case OR:
			case XOR:
			case AND:
			case NOT:
			case IN:
			case STARTS:
			case ENDS:
			case CONTAINS:
			case IS:
			case NULL:
			case TRUE:
			case FALSE:
			case COUNT:
			case FILTER:
			case EXTRACT:
			case ANY:
			case NONE:
			case SINGLE:
			case UnescapedSymbolicName:
			case EscapedSymbolicName:
				{
				setState(1009);
				symbolicName();
				}
				break;
			case DecimalInteger:
				{
				setState(1010);
				match(DecimalInteger);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyExpressionContext extends ParserRuleContext {
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<PropertyLookupContext> propertyLookup() {
			return getRuleContexts(PropertyLookupContext.class);
		}
		public PropertyLookupContext propertyLookup(int i) {
			return getRuleContext(PropertyLookupContext.class,i);
		}
		public PropertyExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPropertyExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPropertyExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPropertyExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyExpressionContext propertyExpression() throws RecognitionException {
		PropertyExpressionContext _localctx = new PropertyExpressionContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_propertyExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1013);
			atom();
			setState(1017); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1014);
					ws();
					setState(1015);
					propertyLookup();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1019); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,87,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PropertyKeyNameContext extends ParserRuleContext {
		public SymbolicNameContext symbolicName() {
			return getRuleContext(SymbolicNameContext.class,0);
		}
		public PropertyKeyNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_propertyKeyName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPropertyKeyName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPropertyKeyName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPropertyKeyName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertyKeyNameContext propertyKeyName() throws RecognitionException {
		PropertyKeyNameContext _localctx = new PropertyKeyNameContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_propertyKeyName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1021);
			symbolicName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IntegerLiteralContext extends ParserRuleContext {
		public TerminalNode HexInteger() { return getToken(CypherParser.HexInteger, 0); }
		public TerminalNode OctalInteger() { return getToken(CypherParser.OctalInteger, 0); }
		public TerminalNode DecimalInteger() { return getToken(CypherParser.DecimalInteger, 0); }
		public IntegerLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterIntegerLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitIntegerLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitIntegerLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerLiteralContext integerLiteral() throws RecognitionException {
		IntegerLiteralContext _localctx = new IntegerLiteralContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_integerLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1023);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HexInteger) | (1L << DecimalInteger) | (1L << OctalInteger))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoubleLiteralContext extends ParserRuleContext {
		public ExponentDecimalRealContext exponentDecimalReal() {
			return getRuleContext(ExponentDecimalRealContext.class,0);
		}
		public RegularDecimalRealContext regularDecimalReal() {
			return getRuleContext(RegularDecimalRealContext.class,0);
		}
		public DoubleLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doubleLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterDoubleLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitDoubleLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitDoubleLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DoubleLiteralContext doubleLiteral() throws RecognitionException {
		DoubleLiteralContext _localctx = new DoubleLiteralContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_doubleLiteral);
		try {
			setState(1027);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,88,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1025);
				exponentDecimalReal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1026);
				regularDecimalReal();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExponentDecimalRealContext extends ParserRuleContext {
		public TerminalNode DigitString() { return getToken(CypherParser.DigitString, 0); }
		public List<TerminalNode> DecimalInteger() { return getTokens(CypherParser.DecimalInteger); }
		public TerminalNode DecimalInteger(int i) {
			return getToken(CypherParser.DecimalInteger, i);
		}
		public List<TerminalNode> Digit() { return getTokens(CypherParser.Digit); }
		public TerminalNode Digit(int i) {
			return getToken(CypherParser.Digit, i);
		}
		public ExponentDecimalRealContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exponentDecimalReal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExponentDecimalReal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExponentDecimalReal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExponentDecimalReal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExponentDecimalRealContext exponentDecimalReal() throws RecognitionException {
		ExponentDecimalRealContext _localctx = new ExponentDecimalRealContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_exponentDecimalReal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1035);
			switch (_input.LA(1)) {
			case T__25:
			case Digit:
				{
				setState(1030); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(1029);
					_la = _input.LA(1);
					if ( !(_la==T__25 || _la==Digit) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					}
					setState(1032); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__25 || _la==Digit );
				}
				break;
			case DecimalInteger:
				{
				setState(1034);
				match(DecimalInteger);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1039);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
			case 1:
				{
				setState(1037);
				_la = _input.LA(1);
				if ( !(_la==T__30 || _la==T__31) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case 2:
				{
				setState(1038);
				_la = _input.LA(1);
				if ( !(_la==T__30 || _la==T__31) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			}
			setState(1041);
			_la = _input.LA(1);
			if ( !(_la==DecimalInteger || _la==DigitString) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RegularDecimalRealContext extends ParserRuleContext {
		public TerminalNode DigitString() { return getToken(CypherParser.DigitString, 0); }
		public List<TerminalNode> DecimalInteger() { return getTokens(CypherParser.DecimalInteger); }
		public TerminalNode DecimalInteger(int i) {
			return getToken(CypherParser.DecimalInteger, i);
		}
		public List<TerminalNode> Digit() { return getTokens(CypherParser.Digit); }
		public TerminalNode Digit(int i) {
			return getToken(CypherParser.Digit, i);
		}
		public RegularDecimalRealContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_regularDecimalReal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRegularDecimalReal(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRegularDecimalReal(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRegularDecimalReal(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RegularDecimalRealContext regularDecimalReal() throws RecognitionException {
		RegularDecimalRealContext _localctx = new RegularDecimalRealContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_regularDecimalReal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1050);
			switch (_input.LA(1)) {
			case T__25:
			case Digit:
				{
				setState(1046);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Digit) {
					{
					{
					setState(1043);
					match(Digit);
					}
					}
					setState(1048);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case DecimalInteger:
				{
				setState(1049);
				match(DecimalInteger);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1052);
			match(T__25);
			setState(1053);
			_la = _input.LA(1);
			if ( !(_la==DecimalInteger || _la==DigitString) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SymbolicNameContext extends ParserRuleContext {
		public TerminalNode UnescapedSymbolicName() { return getToken(CypherParser.UnescapedSymbolicName, 0); }
		public TerminalNode EscapedSymbolicName() { return getToken(CypherParser.EscapedSymbolicName, 0); }
		public TerminalNode UNION() { return getToken(CypherParser.UNION, 0); }
		public TerminalNode ALL() { return getToken(CypherParser.ALL, 0); }
		public TerminalNode OPTIONAL() { return getToken(CypherParser.OPTIONAL, 0); }
		public TerminalNode MATCH() { return getToken(CypherParser.MATCH, 0); }
		public TerminalNode UNWIND() { return getToken(CypherParser.UNWIND, 0); }
		public TerminalNode AS() { return getToken(CypherParser.AS, 0); }
		public TerminalNode MERGE() { return getToken(CypherParser.MERGE, 0); }
		public TerminalNode ON() { return getToken(CypherParser.ON, 0); }
		public TerminalNode CREATE() { return getToken(CypherParser.CREATE, 0); }
		public TerminalNode SET() { return getToken(CypherParser.SET, 0); }
		public TerminalNode DELETE() { return getToken(CypherParser.DELETE, 0); }
		public TerminalNode DETACH() { return getToken(CypherParser.DETACH, 0); }
		public TerminalNode REMOVE() { return getToken(CypherParser.REMOVE, 0); }
		public TerminalNode WITH() { return getToken(CypherParser.WITH, 0); }
		public TerminalNode DISTINCT() { return getToken(CypherParser.DISTINCT, 0); }
		public TerminalNode RETURN() { return getToken(CypherParser.RETURN, 0); }
		public TerminalNode ORDER() { return getToken(CypherParser.ORDER, 0); }
		public TerminalNode BY() { return getToken(CypherParser.BY, 0); }
		public TerminalNode L_SKIP() { return getToken(CypherParser.L_SKIP, 0); }
		public TerminalNode LIMIT() { return getToken(CypherParser.LIMIT, 0); }
		public TerminalNode DESCENDING() { return getToken(CypherParser.DESCENDING, 0); }
		public TerminalNode DESC() { return getToken(CypherParser.DESC, 0); }
		public TerminalNode ASCENDING() { return getToken(CypherParser.ASCENDING, 0); }
		public TerminalNode ASC() { return getToken(CypherParser.ASC, 0); }
		public TerminalNode WHERE() { return getToken(CypherParser.WHERE, 0); }
		public TerminalNode OR() { return getToken(CypherParser.OR, 0); }
		public TerminalNode XOR() { return getToken(CypherParser.XOR, 0); }
		public TerminalNode AND() { return getToken(CypherParser.AND, 0); }
		public TerminalNode NOT() { return getToken(CypherParser.NOT, 0); }
		public TerminalNode IN() { return getToken(CypherParser.IN, 0); }
		public TerminalNode STARTS() { return getToken(CypherParser.STARTS, 0); }
		public TerminalNode ENDS() { return getToken(CypherParser.ENDS, 0); }
		public TerminalNode CONTAINS() { return getToken(CypherParser.CONTAINS, 0); }
		public TerminalNode IS() { return getToken(CypherParser.IS, 0); }
		public TerminalNode NULL() { return getToken(CypherParser.NULL, 0); }
		public TerminalNode TRUE() { return getToken(CypherParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(CypherParser.FALSE, 0); }
		public TerminalNode COUNT() { return getToken(CypherParser.COUNT, 0); }
		public TerminalNode FILTER() { return getToken(CypherParser.FILTER, 0); }
		public TerminalNode EXTRACT() { return getToken(CypherParser.EXTRACT, 0); }
		public TerminalNode ANY() { return getToken(CypherParser.ANY, 0); }
		public TerminalNode NONE() { return getToken(CypherParser.NONE, 0); }
		public TerminalNode SINGLE() { return getToken(CypherParser.SINGLE, 0); }
		public TerminalNode HexString() { return getToken(CypherParser.HexString, 0); }
		public SymbolicNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_symbolicName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSymbolicName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSymbolicName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitSymbolicName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SymbolicNameContext symbolicName() throws RecognitionException {
		SymbolicNameContext _localctx = new SymbolicNameContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_symbolicName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1055);
			_la = _input.LA(1);
			if ( !(((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (HexString - 57)) | (1L << (UNION - 57)) | (1L << (ALL - 57)) | (1L << (OPTIONAL - 57)) | (1L << (MATCH - 57)) | (1L << (UNWIND - 57)) | (1L << (AS - 57)) | (1L << (MERGE - 57)) | (1L << (ON - 57)) | (1L << (CREATE - 57)) | (1L << (SET - 57)) | (1L << (DELETE - 57)) | (1L << (DETACH - 57)) | (1L << (REMOVE - 57)) | (1L << (WITH - 57)) | (1L << (DISTINCT - 57)) | (1L << (RETURN - 57)) | (1L << (ORDER - 57)) | (1L << (BY - 57)) | (1L << (L_SKIP - 57)) | (1L << (LIMIT - 57)) | (1L << (DESCENDING - 57)) | (1L << (DESC - 57)) | (1L << (ASCENDING - 57)) | (1L << (ASC - 57)) | (1L << (WHERE - 57)) | (1L << (OR - 57)) | (1L << (XOR - 57)) | (1L << (AND - 57)) | (1L << (NOT - 57)) | (1L << (IN - 57)) | (1L << (STARTS - 57)) | (1L << (ENDS - 57)) | (1L << (CONTAINS - 57)) | (1L << (IS - 57)) | (1L << (NULL - 57)) | (1L << (TRUE - 57)) | (1L << (FALSE - 57)) | (1L << (COUNT - 57)) | (1L << (FILTER - 57)) | (1L << (EXTRACT - 57)) | (1L << (ANY - 57)) | (1L << (NONE - 57)) | (1L << (SINGLE - 57)) | (1L << (UnescapedSymbolicName - 57)) | (1L << (EscapedSymbolicName - 57)))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WsContext extends ParserRuleContext {
		public List<TerminalNode> WHITESPACE() { return getTokens(CypherParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(CypherParser.WHITESPACE, i);
		}
		public WsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ws; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterWs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitWs(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitWs(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WsContext ws() throws RecognitionException {
		WsContext _localctx = new WsContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_ws);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1060);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1057);
					match(WHITESPACE);
					}
					} 
				}
				setState(1062);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SpContext extends ParserRuleContext {
		public List<TerminalNode> WHITESPACE() { return getTokens(CypherParser.WHITESPACE); }
		public TerminalNode WHITESPACE(int i) {
			return getToken(CypherParser.WHITESPACE, i);
		}
		public SpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitSp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpContext sp() throws RecognitionException {
		SpContext _localctx = new SpContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_sp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1064); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1063);
					match(WHITESPACE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1066); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,95,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LeftArrowHeadContext extends ParserRuleContext {
		public LeftArrowHeadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_leftArrowHead; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterLeftArrowHead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitLeftArrowHead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitLeftArrowHead(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LeftArrowHeadContext leftArrowHead() throws RecognitionException {
		LeftArrowHeadContext _localctx = new LeftArrowHeadContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_leftArrowHead);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1068);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RightArrowHeadContext extends ParserRuleContext {
		public RightArrowHeadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rightArrowHead; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRightArrowHead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRightArrowHead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRightArrowHead(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RightArrowHeadContext rightArrowHead() throws RecognitionException {
		RightArrowHeadContext _localctx = new RightArrowHeadContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_rightArrowHead);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1070);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__22) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << T__39))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DashContext extends ParserRuleContext {
		public DashContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dash; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterDash(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitDash(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitDash(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DashContext dash() throws RecognitionException {
		DashContext _localctx = new DashContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_dash);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1072);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__40) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44) | (1L << T__45) | (1L << T__46) | (1L << T__47) | (1L << T__48) | (1L << T__49) | (1L << T__50))) != 0)) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DigitContext extends ParserRuleContext {
		public TerminalNode Digit() { return getToken(CypherParser.Digit, 0); }
		public DigitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_digit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterDigit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitDigit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitDigit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DigitContext digit() throws RecognitionException {
		DigitContext _localctx = new DigitContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_digit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1074);
			match(Digit);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3r\u0437\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\3\2\3\2\3\2"+
		"\3\2\3\2\5\2\u00aa\n\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\7\5\u00b6"+
		"\n\5\f\5\16\5\u00b9\13\5\3\6\3\6\3\6\3\6\7\6\u00bf\n\6\f\6\16\6\u00c2"+
		"\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u00cb\n\7\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\5\b\u00d6\n\b\3\t\3\t\5\t\u00da\n\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\5\t\u00e2\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\7\13\u00f2\n\13\f\13\16\13\u00f5\13\13\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0103\n\f\3\r\3\r\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\7\16\u010d\n\16\f\16\16\16\u0110\13\16\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0121\n\17"+
		"\3\20\3\20\3\20\3\20\7\20\u0127\n\20\f\20\16\20\u012a\13\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\7\20\u0132\n\20\f\20\16\20\u0135\13\20\5\20\u0137"+
		"\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u0141\n\21\f\21\16"+
		"\21\u0144\13\21\3\22\3\22\3\22\3\22\5\22\u014a\n\22\3\23\3\23\3\23\3\23"+
		"\3\23\5\23\u0151\n\23\3\23\3\23\3\23\3\23\5\23\u0157\n\23\5\23\u0159\n"+
		"\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u0165\n\24"+
		"\3\25\3\25\3\25\3\25\5\25\u016b\n\25\3\25\3\25\3\25\5\25\u0170\n\25\3"+
		"\25\3\25\3\25\5\25\u0175\n\25\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u017d"+
		"\n\26\f\26\16\26\u0180\13\26\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u0188"+
		"\n\26\f\26\16\26\u018b\13\26\5\26\u018d\n\26\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\5\27\u0196\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\7\30\u01a1\n\30\f\30\16\30\u01a4\13\30\3\31\3\31\3\31\3\31\3\32\3\32"+
		"\3\32\3\32\3\33\3\33\3\33\3\33\3\33\5\33\u01b3\n\33\5\33\u01b5\n\33\3"+
		"\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\7\35\u01c1\n\35\f\35"+
		"\16\35\u01c4\13\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u01cd\n\36"+
		"\3\37\3\37\3 \3 \3 \3 \7 \u01d5\n \f \16 \u01d8\13 \3 \3 \3 \3 \5 \u01de"+
		"\n \3!\3!\3!\3!\3!\5!\u01e5\n!\3!\3!\3!\5!\u01ea\n!\3!\3!\3!\5!\u01ef"+
		"\n!\3!\3!\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\5#\u01fc\n#\3#\3#\3#\3#\3#\3"+
		"#\3#\3#\3#\3#\5#\u0208\n#\3#\3#\3#\3#\3#\3#\5#\u0210\n#\3#\3#\3#\3#\3"+
		"#\3#\3#\3#\5#\u021a\n#\3#\3#\3#\5#\u021f\n#\3$\3$\5$\u0223\n$\3$\5$\u0226"+
		"\n$\3$\5$\u0229\n$\3$\3$\5$\u022d\n$\3$\5$\u0230\n$\3$\3$\3%\3%\5%\u0236"+
		"\n%\3&\3&\3&\3&\3&\5&\u023d\n&\3&\3&\3&\7&\u0242\n&\f&\16&\u0245\13&\3"+
		"\'\3\'\3\'\3\'\7\'\u024b\n\'\f\'\16\'\u024e\13\'\3(\3(\3(\3)\3)\3)\3)"+
		"\5)\u0257\n)\3)\3)\3)\3)\3)\5)\u025e\n)\5)\u0260\n)\3*\3*\3+\3+\3,\3,"+
		"\3-\3-\3-\3-\3-\3-\7-\u026e\n-\f-\16-\u0271\13-\3.\3.\3.\3.\3.\3.\7.\u0279"+
		"\n.\f.\16.\u027c\13.\3/\3/\3/\3/\3/\3/\7/\u0284\n/\f/\16/\u0287\13/\3"+
		"\60\3\60\3\60\3\60\7\60\u028d\n\60\f\60\16\60\u0290\13\60\3\60\3\60\3"+
		"\61\3\61\3\61\3\61\7\61\u0298\n\61\f\61\16\61\u029b\13\61\3\62\3\62\3"+
		"\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\7\62\u02a8\n\62\f\62\16\62"+
		"\u02ab\13\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3"+
		"\63\3\63\3\63\3\63\3\63\7\63\u02bd\n\63\f\63\16\63\u02c0\13\63\3\64\3"+
		"\64\3\64\3\64\3\64\3\64\7\64\u02c8\n\64\f\64\16\64\u02cb\13\64\3\65\3"+
		"\65\7\65\u02cf\n\65\f\65\16\65\u02d2\13\65\3\65\3\65\3\66\3\66\3\66\3"+
		"\66\3\66\3\66\3\66\3\66\3\66\5\66\u02df\n\66\3\66\3\66\5\66\u02e3\n\66"+
		"\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66"+
		"\3\66\3\66\3\66\3\66\3\66\3\66\3\66\5\66\u02fa\n\66\3\66\3\66\3\66\3\66"+
		"\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\7\66\u030b\n\66"+
		"\f\66\16\66\u030e\13\66\3\67\3\67\3\67\7\67\u0313\n\67\f\67\16\67\u0316"+
		"\13\67\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38"+
		"\78\u032d\n8\f8\168\u0330\138\38\38\38\38\38\38\38\38\38\38\38\38\38\3"+
		"8\38\38\38\38\38\38\58\u0346\n8\38\38\38\38\38\38\38\38\38\38\38\38\3"+
		"8\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\3"+
		"8\38\38\58\u036e\n8\39\39\39\39\39\39\39\39\39\39\39\39\39\39\39\39\3"+
		"9\39\39\39\39\39\39\39\39\39\39\39\59\u038c\n9\3:\3:\3:\3:\3:\3:\3;\3"+
		";\3;\3;\6;\u0398\n;\r;\16;\u0399\3<\3<\3<\3<\5<\u03a0\n<\3=\3=\3=\3=\3"+
		"=\3=\3>\3>\3>\3>\3>\5>\u03ad\n>\3>\3>\3>\3>\3>\7>\u03b4\n>\f>\16>\u03b7"+
		"\13>\5>\u03b9\n>\3>\3>\3>\3?\3?\3@\3@\3@\3@\3@\3@\5@\u03c6\n@\3@\3@\3"+
		"A\3A\3A\3A\3A\3A\3A\5A\u03d1\nA\3B\3B\3C\3C\5C\u03d7\nC\3D\3D\3D\3D\3"+
		"D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\7D\u03ea\nD\fD\16D\u03ed\13D\5D"+
		"\u03ef\nD\3D\3D\3E\3E\3E\5E\u03f6\nE\3F\3F\3F\3F\6F\u03fc\nF\rF\16F\u03fd"+
		"\3G\3G\3H\3H\3I\3I\5I\u0406\nI\3J\6J\u0409\nJ\rJ\16J\u040a\3J\5J\u040e"+
		"\nJ\3J\3J\5J\u0412\nJ\3J\3J\3K\7K\u0417\nK\fK\16K\u041a\13K\3K\5K\u041d"+
		"\nK\3K\3K\3K\3L\3L\3M\7M\u0425\nM\fM\16M\u0428\13M\3N\6N\u042b\nN\rN\16"+
		"N\u042c\3O\3O\3P\3P\3Q\3Q\3R\3R\3R\2\2S\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080"+
		"\u0082\u0084\u0086\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098"+
		"\u009a\u009c\u009e\u00a0\u00a2\2\16\3\2UV\3\2WX\3\2\20\21\4\2\13\13\35"+
		"\35\3\28:\4\2\34\34??\3\2!\"\4\299<<\5\2;;Aloo\4\2\30\30#&\4\2\31\31\'"+
		"*\4\2\21\21+\65\u046e\2\u00a4\3\2\2\2\4\u00ad\3\2\2\2\6\u00af\3\2\2\2"+
		"\b\u00b1\3\2\2\2\n\u00ba\3\2\2\2\f\u00ca\3\2\2\2\16\u00d5\3\2\2\2\20\u00d9"+
		"\3\2\2\2\22\u00e3\3\2\2\2\24\u00eb\3\2\2\2\26\u0102\3\2\2\2\30\u0104\3"+
		"\2\2\2\32\u0108\3\2\2\2\34\u0120\3\2\2\2\36\u0136\3\2\2\2 \u0138\3\2\2"+
		"\2\"\u0149\3\2\2\2$\u0158\3\2\2\2&\u0164\3\2\2\2(\u0166\3\2\2\2*\u018c"+
		"\3\2\2\2,\u0195\3\2\2\2.\u0197\3\2\2\2\60\u01a5\3\2\2\2\62\u01a9\3\2\2"+
		"\2\64\u01b4\3\2\2\2\66\u01b6\3\2\2\28\u01ba\3\2\2\2:\u01cc\3\2\2\2<\u01ce"+
		"\3\2\2\2>\u01dd\3\2\2\2@\u01df\3\2\2\2B\u01f2\3\2\2\2D\u021e\3\2\2\2F"+
		"\u0220\3\2\2\2H\u0235\3\2\2\2J\u0237\3\2\2\2L\u0246\3\2\2\2N\u024f\3\2"+
		"\2\2P\u0252\3\2\2\2R\u0261\3\2\2\2T\u0263\3\2\2\2V\u0265\3\2\2\2X\u0267"+
		"\3\2\2\2Z\u0272\3\2\2\2\\\u027d\3\2\2\2^\u028e\3\2\2\2`\u0293\3\2\2\2"+
		"b\u029c\3\2\2\2d\u02ac\3\2\2\2f\u02c1\3\2\2\2h\u02d0\3\2\2\2j\u02d5\3"+
		"\2\2\2l\u030f\3\2\2\2n\u036d\3\2\2\2p\u038b\3\2\2\2r\u038d\3\2\2\2t\u0393"+
		"\3\2\2\2v\u039b\3\2\2\2x\u03a1\3\2\2\2z\u03a7\3\2\2\2|\u03bd\3\2\2\2~"+
		"\u03bf\3\2\2\2\u0080\u03c9\3\2\2\2\u0082\u03d2\3\2\2\2\u0084\u03d6\3\2"+
		"\2\2\u0086\u03d8\3\2\2\2\u0088\u03f2\3\2\2\2\u008a\u03f7\3\2\2\2\u008c"+
		"\u03ff\3\2\2\2\u008e\u0401\3\2\2\2\u0090\u0405\3\2\2\2\u0092\u040d\3\2"+
		"\2\2\u0094\u041c\3\2\2\2\u0096\u0421\3\2\2\2\u0098\u0426\3\2\2\2\u009a"+
		"\u042a\3\2\2\2\u009c\u042e\3\2\2\2\u009e\u0430\3\2\2\2\u00a0\u0432\3\2"+
		"\2\2\u00a2\u0434\3\2\2\2\u00a4\u00a5\5\u0098M\2\u00a5\u00a9\5\4\3\2\u00a6"+
		"\u00a7\5\u0098M\2\u00a7\u00a8\7\3\2\2\u00a8\u00aa\3\2\2\2\u00a9\u00a6"+
		"\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ac\5\u0098M"+
		"\2\u00ac\3\3\2\2\2\u00ad\u00ae\5\6\4\2\u00ae\5\3\2\2\2\u00af\u00b0\5\b"+
		"\5\2\u00b0\7\3\2\2\2\u00b1\u00b7\5\n\6\2\u00b2\u00b3\5\u0098M\2\u00b3"+
		"\u00b4\5\f\7\2\u00b4\u00b6\3\2\2\2\u00b5\u00b2\3\2\2\2\u00b6\u00b9\3\2"+
		"\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\t\3\2\2\2\u00b9\u00b7"+
		"\3\2\2\2\u00ba\u00c0\5\16\b\2\u00bb\u00bc\5\u0098M\2\u00bc\u00bd\5\16"+
		"\b\2\u00bd\u00bf\3\2\2\2\u00be\u00bb\3\2\2\2\u00bf\u00c2\3\2\2\2\u00c0"+
		"\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\13\3\2\2\2\u00c2\u00c0\3\2\2"+
		"\2\u00c3\u00c4\7A\2\2\u00c4\u00c5\5\u009aN\2\u00c5\u00c6\7B\2\2\u00c6"+
		"\u00c7\5\n\6\2\u00c7\u00cb\3\2\2\2\u00c8\u00c9\7A\2\2\u00c9\u00cb\5\n"+
		"\6\2\u00ca\u00c3\3\2\2\2\u00ca\u00c8\3\2\2\2\u00cb\r\3\2\2\2\u00cc\u00d6"+
		"\5\20\t\2\u00cd\u00d6\5\22\n\2\u00ce\u00d6\5\24\13\2\u00cf\u00d6\5\30"+
		"\r\2\u00d0\u00d6\5\32\16\2\u00d1\u00d6\5\36\20\2\u00d2\u00d6\5 \21\2\u00d3"+
		"\u00d6\5$\23\2\u00d4\u00d6\5&\24\2\u00d5\u00cc\3\2\2\2\u00d5\u00cd\3\2"+
		"\2\2\u00d5\u00ce\3\2\2\2\u00d5\u00cf\3\2\2\2\u00d5\u00d0\3\2\2\2\u00d5"+
		"\u00d1\3\2\2\2\u00d5\u00d2\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d5\u00d4\3\2"+
		"\2\2\u00d6\17\3\2\2\2\u00d7\u00d8\7C\2\2\u00d8\u00da\5\u009aN\2\u00d9"+
		"\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da\u00db\3\2\2\2\u00db\u00dc\7D"+
		"\2\2\u00dc\u00dd\5\u0098M\2\u00dd\u00e1\58\35\2\u00de\u00df\5\u0098M\2"+
		"\u00df\u00e0\5\66\34\2\u00e0\u00e2\3\2\2\2\u00e1\u00de\3\2\2\2\u00e1\u00e2"+
		"\3\2\2\2\u00e2\21\3\2\2\2\u00e3\u00e4\7E\2\2\u00e4\u00e5\5\u0098M\2\u00e5"+
		"\u00e6\5V,\2\u00e6\u00e7\5\u009aN\2\u00e7\u00e8\7F\2\2\u00e8\u00e9\5\u009a"+
		"N\2\u00e9\u00ea\5\u0082B\2\u00ea\23\3\2\2\2\u00eb\u00ec\7G\2\2\u00ec\u00ed"+
		"\5\u0098M\2\u00ed\u00f3\5:\36\2\u00ee\u00ef\5\u009aN\2\u00ef\u00f0\5\26"+
		"\f\2\u00f0\u00f2\3\2\2\2\u00f1\u00ee\3\2\2\2\u00f2\u00f5\3\2\2\2\u00f3"+
		"\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\25\3\2\2\2\u00f5\u00f3\3\2\2"+
		"\2\u00f6\u00f7\7H\2\2\u00f7\u00f8\5\u009aN\2\u00f8\u00f9\7D\2\2\u00f9"+
		"\u00fa\5\u009aN\2\u00fa\u00fb\5\32\16\2\u00fb\u0103\3\2\2\2\u00fc\u00fd"+
		"\7H\2\2\u00fd\u00fe\5\u009aN\2\u00fe\u00ff\7I\2\2\u00ff\u0100\5\u009a"+
		"N\2\u0100\u0101\5\32\16\2\u0101\u0103\3\2\2\2\u0102\u00f6\3\2\2\2\u0102"+
		"\u00fc\3\2\2\2\u0103\27\3\2\2\2\u0104\u0105\7I\2\2\u0105\u0106\5\u0098"+
		"M\2\u0106\u0107\58\35\2\u0107\31\3\2\2\2\u0108\u0109\7J\2\2\u0109\u010e"+
		"\5\34\17\2\u010a\u010b\7\4\2\2\u010b\u010d\5\34\17\2\u010c\u010a\3\2\2"+
		"\2\u010d\u0110\3\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f\33"+
		"\3\2\2\2\u0110\u010e\3\2\2\2\u0111\u0112\5\u008aF\2\u0112\u0113\7\5\2"+
		"\2\u0113\u0114\5V,\2\u0114\u0121\3\2\2\2\u0115\u0116\5\u0082B\2\u0116"+
		"\u0117\7\5\2\2\u0117\u0118\5V,\2\u0118\u0121\3\2\2\2\u0119\u011a\5\u0082"+
		"B\2\u011a\u011b\7\6\2\2\u011b\u011c\5V,\2\u011c\u0121\3\2\2\2\u011d\u011e"+
		"\5\u0082B\2\u011e\u011f\5L\'\2\u011f\u0121\3\2\2\2\u0120\u0111\3\2\2\2"+
		"\u0120\u0115\3\2\2\2\u0120\u0119\3\2\2\2\u0120\u011d\3\2\2\2\u0121\35"+
		"\3\2\2\2\u0122\u0123\7K\2\2\u0123\u0128\5V,\2\u0124\u0125\7\4\2\2\u0125"+
		"\u0127\5V,\2\u0126\u0124\3\2\2\2\u0127\u012a\3\2\2\2\u0128\u0126\3\2\2"+
		"\2\u0128\u0129\3\2\2\2\u0129\u0137\3\2\2\2\u012a\u0128\3\2\2\2\u012b\u012c"+
		"\7L\2\2\u012c\u012d\5\u009aN\2\u012d\u012e\7K\2\2\u012e\u0133\5V,\2\u012f"+
		"\u0130\7\4\2\2\u0130\u0132\5V,\2\u0131\u012f\3\2\2\2\u0132\u0135\3\2\2"+
		"\2\u0133\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134\u0137\3\2\2\2\u0135\u0133"+
		"\3\2\2\2\u0136\u0122\3\2\2\2\u0136\u012b\3\2\2\2\u0137\37\3\2\2\2\u0138"+
		"\u0139\7M\2\2\u0139\u013a\5\u009aN\2\u013a\u0142\5\"\22\2\u013b\u013c"+
		"\5\u0098M\2\u013c\u013d\7\4\2\2\u013d\u013e\5\u0098M\2\u013e\u013f\5\""+
		"\22\2\u013f\u0141\3\2\2\2\u0140\u013b\3\2\2\2\u0141\u0144\3\2\2\2\u0142"+
		"\u0140\3\2\2\2\u0142\u0143\3\2\2\2\u0143!\3\2\2\2\u0144\u0142\3\2\2\2"+
		"\u0145\u0146\5\u0082B\2\u0146\u0147\5L\'\2\u0147\u014a\3\2\2\2\u0148\u014a"+
		"\5\u008aF\2\u0149\u0145\3\2\2\2\u0149\u0148\3\2\2\2\u014a#\3\2\2\2\u014b"+
		"\u014c\7N\2\2\u014c\u014d\7O\2\2\u014d\u014e\5\u009aN\2\u014e\u0150\5"+
		"(\25\2\u014f\u0151\5\66\34\2\u0150\u014f\3\2\2\2\u0150\u0151\3\2\2\2\u0151"+
		"\u0159\3\2\2\2\u0152\u0153\7N\2\2\u0153\u0154\5\u009aN\2\u0154\u0156\5"+
		"(\25\2\u0155\u0157\5\66\34\2\u0156\u0155\3\2\2\2\u0156\u0157\3\2\2\2\u0157"+
		"\u0159\3\2\2\2\u0158\u014b\3\2\2\2\u0158\u0152\3\2\2\2\u0159%\3\2\2\2"+
		"\u015a\u015b\7P\2\2\u015b\u015c\5\u009aN\2\u015c\u015d\7O\2\2\u015d\u015e"+
		"\5\u009aN\2\u015e\u015f\5(\25\2\u015f\u0165\3\2\2\2\u0160\u0161\7P\2\2"+
		"\u0161\u0162\5\u009aN\2\u0162\u0163\5(\25\2\u0163\u0165\3\2\2\2\u0164"+
		"\u015a\3\2\2\2\u0164\u0160\3\2\2\2\u0165\'\3\2\2\2\u0166\u016a\5*\26\2"+
		"\u0167\u0168\5\u009aN\2\u0168\u0169\5.\30\2\u0169\u016b\3\2\2\2\u016a"+
		"\u0167\3\2\2\2\u016a\u016b\3\2\2\2\u016b\u016f\3\2\2\2\u016c\u016d\5\u009a"+
		"N\2\u016d\u016e\5\60\31\2\u016e\u0170\3\2\2\2\u016f\u016c\3\2\2\2\u016f"+
		"\u0170\3\2\2\2\u0170\u0174\3\2\2\2\u0171\u0172\5\u009aN\2\u0172\u0173"+
		"\5\62\32\2\u0173\u0175\3\2\2\2\u0174\u0171\3\2\2\2\u0174\u0175\3\2\2\2"+
		"\u0175)\3\2\2\2\u0176\u017e\7\7\2\2\u0177\u0178\5\u0098M\2\u0178\u0179"+
		"\7\4\2\2\u0179\u017a\5\u0098M\2\u017a\u017b\5,\27\2\u017b\u017d\3\2\2"+
		"\2\u017c\u0177\3\2\2\2\u017d\u0180\3\2\2\2\u017e\u017c\3\2\2\2\u017e\u017f"+
		"\3\2\2\2\u017f\u018d\3\2\2\2\u0180\u017e\3\2\2\2\u0181\u0189\5,\27\2\u0182"+
		"\u0183\5\u0098M\2\u0183\u0184\7\4\2\2\u0184\u0185\5\u0098M\2\u0185\u0186"+
		"\5,\27\2\u0186\u0188\3\2\2\2\u0187\u0182\3\2\2\2\u0188\u018b\3\2\2\2\u0189"+
		"\u0187\3\2\2\2\u0189\u018a\3\2\2\2\u018a\u018d\3\2\2\2\u018b\u0189\3\2"+
		"\2\2\u018c\u0176\3\2\2\2\u018c\u0181\3\2\2\2\u018d+\3\2\2\2\u018e\u018f"+
		"\5V,\2\u018f\u0190\5\u009aN\2\u0190\u0191\7F\2\2\u0191\u0192\5\u009aN"+
		"\2\u0192\u0193\5\u0082B\2\u0193\u0196\3\2\2\2\u0194\u0196\5V,\2\u0195"+
		"\u018e\3\2\2\2\u0195\u0194\3\2\2\2\u0196-\3\2\2\2\u0197\u0198\7Q\2\2\u0198"+
		"\u0199\5\u009aN\2\u0199\u019a\7R\2\2\u019a\u019b\5\u009aN\2\u019b\u01a2"+
		"\5\64\33\2\u019c\u019d\7\4\2\2\u019d\u019e\5\u0098M\2\u019e\u019f\5\64"+
		"\33\2\u019f\u01a1\3\2\2\2\u01a0\u019c\3\2\2\2\u01a1\u01a4\3\2\2\2\u01a2"+
		"\u01a0\3\2\2\2\u01a2\u01a3\3\2\2\2\u01a3/\3\2\2\2\u01a4\u01a2\3\2\2\2"+
		"\u01a5\u01a6\7S\2\2\u01a6\u01a7\5\u009aN\2\u01a7\u01a8\5V,\2\u01a8\61"+
		"\3\2\2\2\u01a9\u01aa\7T\2\2\u01aa\u01ab\5\u009aN\2\u01ab\u01ac\5V,\2\u01ac"+
		"\63\3\2\2\2\u01ad\u01ae\5V,\2\u01ae\u01af\t\2\2\2\u01af\u01b5\3\2\2\2"+
		"\u01b0\u01b2\5V,\2\u01b1\u01b3\t\3\2\2\u01b2\u01b1\3\2\2\2\u01b2\u01b3"+
		"\3\2\2\2\u01b3\u01b5\3\2\2\2\u01b4\u01ad\3\2\2\2\u01b4\u01b0\3\2\2\2\u01b5"+
		"\65\3\2\2\2\u01b6\u01b7\7Y\2\2\u01b7\u01b8\5\u009aN\2\u01b8\u01b9\5V,"+
		"\2\u01b9\67\3\2\2\2\u01ba\u01c2\5:\36\2\u01bb\u01bc\5\u0098M\2\u01bc\u01bd"+
		"\7\4\2\2\u01bd\u01be\5\u0098M\2\u01be\u01bf\5:\36\2\u01bf\u01c1\3\2\2"+
		"\2\u01c0\u01bb\3\2\2\2\u01c1\u01c4\3\2\2\2\u01c2\u01c0\3\2\2\2\u01c2\u01c3"+
		"\3\2\2\2\u01c39\3\2\2\2\u01c4\u01c2\3\2\2\2\u01c5\u01c6\5\u0082B\2\u01c6"+
		"\u01c7\5\u0098M\2\u01c7\u01c8\7\5\2\2\u01c8\u01c9\5\u0098M\2\u01c9\u01ca"+
		"\5<\37\2\u01ca\u01cd\3\2\2\2\u01cb\u01cd\5<\37\2\u01cc\u01c5\3\2\2\2\u01cc"+
		"\u01cb\3\2\2\2\u01cd;\3\2\2\2\u01ce\u01cf\5> \2\u01cf=\3\2\2\2\u01d0\u01d6"+
		"\5@!\2\u01d1\u01d2\5\u0098M\2\u01d2\u01d3\5B\"\2\u01d3\u01d5\3\2\2\2\u01d4"+
		"\u01d1\3\2\2\2\u01d5\u01d8\3\2\2\2\u01d6\u01d4\3\2\2\2\u01d6\u01d7\3\2"+
		"\2\2\u01d7\u01de\3\2\2\2\u01d8\u01d6\3\2\2\2\u01d9\u01da\7\b\2\2\u01da"+
		"\u01db\5> \2\u01db\u01dc\7\t\2\2\u01dc\u01de\3\2\2\2\u01dd\u01d0\3\2\2"+
		"\2\u01dd\u01d9\3\2\2\2\u01de?\3\2\2\2\u01df\u01e0\7\b\2\2\u01e0\u01e4"+
		"\5\u0098M\2\u01e1\u01e2\5\u0082B\2\u01e2\u01e3\5\u0098M\2\u01e3\u01e5"+
		"\3\2\2\2\u01e4\u01e1\3\2\2\2\u01e4\u01e5\3\2\2\2\u01e5\u01e9\3\2\2\2\u01e6"+
		"\u01e7\5L\'\2\u01e7\u01e8\5\u0098M\2\u01e8\u01ea\3\2\2\2\u01e9\u01e6\3"+
		"\2\2\2\u01e9\u01ea\3\2\2\2\u01ea\u01ee\3\2\2\2\u01eb\u01ec\5H%\2\u01ec"+
		"\u01ed\5\u0098M\2\u01ed\u01ef\3\2\2\2\u01ee\u01eb\3\2\2\2\u01ee\u01ef"+
		"\3\2\2\2\u01ef\u01f0\3\2\2\2\u01f0\u01f1\7\t\2\2\u01f1A\3\2\2\2\u01f2"+
		"\u01f3\5D#\2\u01f3\u01f4\5\u0098M\2\u01f4\u01f5\5@!\2\u01f5C\3\2\2\2\u01f6"+
		"\u01f7\5\u009cO\2\u01f7\u01f8\5\u0098M\2\u01f8\u01f9\5\u00a0Q\2\u01f9"+
		"\u01fb\5\u0098M\2\u01fa\u01fc\5F$\2\u01fb\u01fa\3\2\2\2\u01fb\u01fc\3"+
		"\2\2\2\u01fc\u01fd\3\2\2\2\u01fd\u01fe\5\u0098M\2\u01fe\u01ff\5\u00a0"+
		"Q\2\u01ff\u0200\5\u0098M\2\u0200\u0201\5\u009eP\2\u0201\u021f\3\2\2\2"+
		"\u0202\u0203\5\u009cO\2\u0203\u0204\5\u0098M\2\u0204\u0205\5\u00a0Q\2"+
		"\u0205\u0207\5\u0098M\2\u0206\u0208\5F$\2\u0207\u0206\3\2\2\2\u0207\u0208"+
		"\3\2\2\2\u0208\u0209\3\2\2\2\u0209\u020a\5\u0098M\2\u020a\u020b\5\u00a0"+
		"Q\2\u020b\u021f\3\2\2\2\u020c\u020d\5\u00a0Q\2\u020d\u020f\5\u0098M\2"+
		"\u020e\u0210\5F$\2\u020f\u020e\3\2\2\2\u020f\u0210\3\2\2\2\u0210\u0211"+
		"\3\2\2\2\u0211\u0212\5\u0098M\2\u0212\u0213\5\u00a0Q\2\u0213\u0214\5\u0098"+
		"M\2\u0214\u0215\5\u009eP\2\u0215\u021f\3\2\2\2\u0216\u0217\5\u00a0Q\2"+
		"\u0217\u0219\5\u0098M\2\u0218\u021a\5F$\2\u0219\u0218\3\2\2\2\u0219\u021a"+
		"\3\2\2\2\u021a\u021b\3\2\2\2\u021b\u021c\5\u0098M\2\u021c\u021d\5\u00a0"+
		"Q\2\u021d\u021f\3\2\2\2\u021e\u01f6\3\2\2\2\u021e\u0202\3\2\2\2\u021e"+
		"\u020c\3\2\2\2\u021e\u0216\3\2\2\2\u021fE\3\2\2\2\u0220\u0222\7\n\2\2"+
		"\u0221\u0223\5\u0082B\2\u0222\u0221\3\2\2\2\u0222\u0223\3\2\2\2\u0223"+
		"\u0225\3\2\2\2\u0224\u0226\7\13\2\2\u0225\u0224\3\2\2\2\u0225\u0226\3"+
		"\2\2\2\u0226\u0228\3\2\2\2\u0227\u0229\5J&\2\u0228\u0227\3\2\2\2\u0228"+
		"\u0229\3\2\2\2\u0229\u022c\3\2\2\2\u022a\u022b\7\7\2\2\u022b\u022d\5P"+
		")\2\u022c\u022a\3\2\2\2\u022c\u022d\3\2\2\2\u022d\u022f\3\2\2\2\u022e"+
		"\u0230\5H%\2\u022f\u022e\3\2\2\2\u022f\u0230\3\2\2\2\u0230\u0231\3\2\2"+
		"\2\u0231\u0232\7\f\2\2\u0232G\3\2\2\2\u0233\u0236\5\u0086D\2\u0234\u0236"+
		"\5\u0088E\2\u0235\u0233\3\2\2\2\u0235\u0234\3\2\2\2\u0236I\3\2\2\2\u0237"+
		"\u0238\7\r\2\2\u0238\u0243\5T+\2\u0239\u023a\5\u0098M\2\u023a\u023c\7"+
		"\16\2\2\u023b\u023d\7\r\2\2\u023c\u023b\3\2\2\2\u023c\u023d\3\2\2\2\u023d"+
		"\u023e\3\2\2\2\u023e\u023f\5\u0098M\2\u023f\u0240\5T+\2\u0240\u0242\3"+
		"\2\2\2\u0241\u0239\3\2\2\2\u0242\u0245\3\2\2\2\u0243\u0241\3\2\2\2\u0243"+
		"\u0244\3\2\2\2\u0244K\3\2\2\2\u0245\u0243\3\2\2\2\u0246\u024c\5N(\2\u0247"+
		"\u0248\5\u0098M\2\u0248\u0249\5N(\2\u0249\u024b\3\2\2\2\u024a\u0247\3"+
		"\2\2\2\u024b\u024e\3\2\2\2\u024c\u024a\3\2\2\2\u024c\u024d\3\2\2\2\u024d"+
		"M\3\2\2\2\u024e\u024c\3\2\2\2\u024f\u0250\7\r\2\2\u0250\u0251\5R*\2\u0251"+
		"O\3\2\2\2\u0252\u0256\5\u0098M\2\u0253\u0254\5\u008eH\2\u0254\u0255\5"+
		"\u0098M\2\u0255\u0257\3\2\2\2\u0256\u0253\3\2\2\2\u0256\u0257\3\2\2\2"+
		"\u0257\u025f\3\2\2\2\u0258\u0259\7\17\2\2\u0259\u025d\5\u0098M\2\u025a"+
		"\u025b\5\u008eH\2\u025b\u025c\5\u0098M\2\u025c\u025e\3\2\2\2\u025d\u025a"+
		"\3\2\2\2\u025d\u025e\3\2\2\2\u025e\u0260\3\2\2\2\u025f\u0258\3\2\2\2\u025f"+
		"\u0260\3\2\2\2\u0260Q\3\2\2\2\u0261\u0262\5\u0096L\2\u0262S\3\2\2\2\u0263"+
		"\u0264\5\u0096L\2\u0264U\3\2\2\2\u0265\u0266\5X-\2\u0266W\3\2\2\2\u0267"+
		"\u026f\5Z.\2\u0268\u0269\5\u009aN\2\u0269\u026a\7Z\2\2\u026a\u026b\5\u009a"+
		"N\2\u026b\u026c\5Z.\2\u026c\u026e\3\2\2\2\u026d\u0268\3\2\2\2\u026e\u0271"+
		"\3\2\2\2\u026f\u026d\3\2\2\2\u026f\u0270\3\2\2\2\u0270Y\3\2\2\2\u0271"+
		"\u026f\3\2\2\2\u0272\u027a\5\\/\2\u0273\u0274\5\u009aN\2\u0274\u0275\7"+
		"[\2\2\u0275\u0276\5\u009aN\2\u0276\u0277\5\\/\2\u0277\u0279\3\2\2\2\u0278"+
		"\u0273\3\2\2\2\u0279\u027c\3\2\2\2\u027a\u0278\3\2\2\2\u027a\u027b\3\2"+
		"\2\2\u027b[\3\2\2\2\u027c\u027a\3\2\2\2\u027d\u0285\5^\60\2\u027e\u027f"+
		"\5\u009aN\2\u027f\u0280\7\\\2\2\u0280\u0281\5\u009aN\2\u0281\u0282\5^"+
		"\60\2\u0282\u0284\3\2\2\2\u0283\u027e\3\2\2\2\u0284\u0287\3\2\2\2\u0285"+
		"\u0283\3\2\2\2\u0285\u0286\3\2\2\2\u0286]\3\2\2\2\u0287\u0285\3\2\2\2"+
		"\u0288\u0289\5\u009aN\2\u0289\u028a\7]\2\2\u028a\u028b\5\u009aN\2\u028b"+
		"\u028d\3\2\2\2\u028c\u0288\3\2\2\2\u028d\u0290\3\2\2\2\u028e\u028c\3\2"+
		"\2\2\u028e\u028f\3\2\2\2\u028f\u0291\3\2\2\2\u0290\u028e\3\2\2\2\u0291"+
		"\u0292\5`\61\2\u0292_\3\2\2\2\u0293\u0299\5b\62\2\u0294\u0295\5\u0098"+
		"M\2\u0295\u0296\5p9\2\u0296\u0298\3\2\2\2\u0297\u0294\3\2\2\2\u0298\u029b"+
		"\3\2\2\2\u0299\u0297\3\2\2\2\u0299\u029a\3\2\2\2\u029aa\3\2\2\2\u029b"+
		"\u0299\3\2\2\2\u029c\u02a9\5d\63\2\u029d\u029e\5\u0098M\2\u029e\u029f"+
		"\7\20\2\2\u029f\u02a0\5\u0098M\2\u02a0\u02a1\5d\63\2\u02a1\u02a8\3\2\2"+
		"\2\u02a2\u02a3\5\u0098M\2\u02a3\u02a4\7\21\2\2\u02a4\u02a5\5\u0098M\2"+
		"\u02a5\u02a6\5d\63\2\u02a6\u02a8\3\2\2\2\u02a7\u029d\3\2\2\2\u02a7\u02a2"+
		"\3\2\2\2\u02a8\u02ab\3\2\2\2\u02a9\u02a7\3\2\2\2\u02a9\u02aa\3\2\2\2\u02aa"+
		"c\3\2\2\2\u02ab\u02a9\3\2\2\2\u02ac\u02be\5f\64\2\u02ad\u02ae\5\u0098"+
		"M\2\u02ae\u02af\7\7\2\2\u02af\u02b0\5\u0098M\2\u02b0\u02b1\5f\64\2\u02b1"+
		"\u02bd\3\2\2\2\u02b2\u02b3\5\u0098M\2\u02b3\u02b4\7\22\2\2\u02b4\u02b5"+
		"\5\u0098M\2\u02b5\u02b6\5f\64\2\u02b6\u02bd\3\2\2\2\u02b7\u02b8\5\u0098"+
		"M\2\u02b8\u02b9\7\23\2\2\u02b9\u02ba\5\u0098M\2\u02ba\u02bb\5f\64\2\u02bb"+
		"\u02bd\3\2\2\2\u02bc\u02ad\3\2\2\2\u02bc\u02b2\3\2\2\2\u02bc\u02b7\3\2"+
		"\2\2\u02bd\u02c0\3\2\2\2\u02be\u02bc\3\2\2\2\u02be\u02bf\3\2\2\2\u02bf"+
		"e\3\2\2\2\u02c0\u02be\3\2\2\2\u02c1\u02c9\5h\65\2\u02c2\u02c3\5\u0098"+
		"M\2\u02c3\u02c4\7\24\2\2\u02c4\u02c5\5\u0098M\2\u02c5\u02c6\5h\65\2\u02c6"+
		"\u02c8\3\2\2\2\u02c7\u02c2\3\2\2\2\u02c8\u02cb\3\2\2\2\u02c9\u02c7\3\2"+
		"\2\2\u02c9\u02ca\3\2\2\2\u02cag\3\2\2\2\u02cb\u02c9\3\2\2\2\u02cc\u02cd"+
		"\t\4\2\2\u02cd\u02cf\5\u0098M\2\u02ce\u02cc\3\2\2\2\u02cf\u02d2\3\2\2"+
		"\2\u02d0\u02ce\3\2\2\2\u02d0\u02d1\3\2\2\2\u02d1\u02d3\3\2\2\2\u02d2\u02d0"+
		"\3\2\2\2\u02d3\u02d4\5j\66\2\u02d4i\3\2\2\2\u02d5\u030c\5l\67\2\u02d6"+
		"\u02d7\5\u0098M\2\u02d7\u02d8\7\n\2\2\u02d8\u02d9\5V,\2\u02d9\u02da\7"+
		"\f\2\2\u02da\u030b\3\2\2\2\u02db\u02dc\5\u0098M\2\u02dc\u02de\7\n\2\2"+
		"\u02dd\u02df\5V,\2\u02de\u02dd\3\2\2\2\u02de\u02df\3\2\2\2\u02df\u02e0"+
		"\3\2\2\2\u02e0\u02e2\7\17\2\2\u02e1\u02e3\5V,\2\u02e2\u02e1\3\2\2\2\u02e2"+
		"\u02e3\3\2\2\2\u02e3\u02e4\3\2\2\2\u02e4\u02e5\7\f\2\2\u02e5\u030b\3\2"+
		"\2\2\u02e6\u02e7\5\u0098M\2\u02e7\u02e8\7\25\2\2\u02e8\u02fa\3\2\2\2\u02e9"+
		"\u02ea\5\u009aN\2\u02ea\u02eb\7^\2\2\u02eb\u02fa\3\2\2\2\u02ec\u02ed\5"+
		"\u009aN\2\u02ed\u02ee\7_\2\2\u02ee\u02ef\5\u009aN\2\u02ef\u02f0\7N\2\2"+
		"\u02f0\u02fa\3\2\2\2\u02f1\u02f2\5\u009aN\2\u02f2\u02f3\7`\2\2\u02f3\u02f4"+
		"\5\u009aN\2\u02f4\u02f5\7N\2\2\u02f5\u02fa\3\2\2\2\u02f6\u02f7\5\u009a"+
		"N\2\u02f7\u02f8\7a\2\2\u02f8\u02fa\3\2\2\2\u02f9\u02e6\3\2\2\2\u02f9\u02e9"+
		"\3\2\2\2\u02f9\u02ec\3\2\2\2\u02f9\u02f1\3\2\2\2\u02f9\u02f6\3\2\2\2\u02fa"+
		"\u02fb\3\2\2\2\u02fb\u02fc\5\u0098M\2\u02fc\u02fd\5l\67\2\u02fd\u030b"+
		"\3\2\2\2\u02fe\u02ff\5\u009aN\2\u02ff\u0300\7b\2\2\u0300\u0301\5\u009a"+
		"N\2\u0301\u0302\7c\2\2\u0302\u030b\3\2\2\2\u0303\u0304\5\u009aN\2\u0304"+
		"\u0305\7b\2\2\u0305\u0306\5\u009aN\2\u0306\u0307\7]\2\2\u0307\u0308\5"+
		"\u009aN\2\u0308\u0309\7c\2\2\u0309\u030b\3\2\2\2\u030a\u02d6\3\2\2\2\u030a"+
		"\u02db\3\2\2\2\u030a\u02f9\3\2\2\2\u030a\u02fe\3\2\2\2\u030a\u0303\3\2"+
		"\2\2\u030b\u030e\3\2\2\2\u030c\u030a\3\2\2\2\u030c\u030d\3\2\2\2\u030d"+
		"k\3\2\2\2\u030e\u030c\3\2\2\2\u030f\u0314\5n8\2\u0310\u0313\5\u0080A\2"+
		"\u0311\u0313\5L\'\2\u0312\u0310\3\2\2\2\u0312\u0311\3\2\2\2\u0313\u0316"+
		"\3\2\2\2\u0314\u0312\3\2\2\2\u0314\u0315\3\2\2\2\u0315m\3\2\2\2\u0316"+
		"\u0314\3\2\2\2\u0317\u036e\5\u0084C\2\u0318\u036e\7\66\2\2\u0319\u036e"+
		"\5\u0088E\2\u031a\u036e\7d\2\2\u031b\u036e\7e\2\2\u031c\u036e\7c\2\2\u031d"+
		"\u031e\7f\2\2\u031e\u031f\7\b\2\2\u031f\u0320\7\7\2\2\u0320\u036e\7\t"+
		"\2\2\u0321\u036e\5\u0086D\2\u0322\u036e\5~@\2\u0323\u0324\7\n\2\2\u0324"+
		"\u0325\5\u0098M\2\u0325\u0326\5V,\2\u0326\u032e\5\u0098M\2\u0327\u0328"+
		"\7\4\2\2\u0328\u0329\5\u0098M\2\u0329\u032a\5V,\2\u032a\u032b\5\u0098"+
		"M\2\u032b\u032d\3\2\2\2\u032c\u0327\3\2\2\2\u032d\u0330\3\2\2\2\u032e"+
		"\u032c\3\2\2\2\u032e\u032f\3\2\2\2\u032f\u0331\3\2\2\2\u0330\u032e\3\2"+
		"\2\2\u0331\u0332\7\f\2\2\u0332\u036e\3\2\2\2\u0333\u0334\7g\2\2\u0334"+
		"\u0335\5\u0098M\2\u0335\u0336\7\b\2\2\u0336\u0337\5\u0098M\2\u0337\u0338"+
		"\5v<\2\u0338\u0339\5\u0098M\2\u0339\u033a\7\t\2\2\u033a\u036e\3\2\2\2"+
		"\u033b\u033c\7h\2\2\u033c\u033d\5\u0098M\2\u033d\u033e\7\b\2\2\u033e\u033f"+
		"\5\u0098M\2\u033f\u0340\5v<\2\u0340\u0345\5\u0098M\2\u0341\u0342\5\u0098"+
		"M\2\u0342\u0343\7\16\2\2\u0343\u0344\5V,\2\u0344\u0346\3\2\2\2\u0345\u0341"+
		"\3\2\2\2\u0345\u0346\3\2\2\2\u0346\u0347\3\2\2\2\u0347\u0348\7\t\2\2\u0348"+
		"\u036e\3\2\2\2\u0349\u034a\7B\2\2\u034a\u034b\5\u0098M\2\u034b\u034c\7"+
		"\b\2\2\u034c\u034d\5\u0098M\2\u034d\u034e\5v<\2\u034e\u034f\5\u0098M\2"+
		"\u034f\u0350\7\t\2\2\u0350\u036e\3\2\2\2\u0351\u0352\7i\2\2\u0352\u0353"+
		"\5\u0098M\2\u0353\u0354\7\b\2\2\u0354\u0355\5\u0098M\2\u0355\u0356\5v"+
		"<\2\u0356\u0357\5\u0098M\2\u0357\u0358\7\t\2\2\u0358\u036e\3\2\2\2\u0359"+
		"\u035a\7j\2\2\u035a\u035b\5\u0098M\2\u035b\u035c\7\b\2\2\u035c\u035d\5"+
		"\u0098M\2\u035d\u035e\5v<\2\u035e\u035f\5\u0098M\2\u035f\u0360\7\t\2\2"+
		"\u0360\u036e\3\2\2\2\u0361\u0362\7k\2\2\u0362\u0363\5\u0098M\2\u0363\u0364"+
		"\7\b\2\2\u0364\u0365\5\u0098M\2\u0365\u0366\5v<\2\u0366\u0367\5\u0098"+
		"M\2\u0367\u0368\7\t\2\2\u0368\u036e\3\2\2\2\u0369\u036e\5t;\2\u036a\u036e"+
		"\5r:\2\u036b\u036e\5z>\2\u036c\u036e\5\u0082B\2\u036d\u0317\3\2\2\2\u036d"+
		"\u0318\3\2\2\2\u036d\u0319\3\2\2\2\u036d\u031a\3\2\2\2\u036d\u031b\3\2"+
		"\2\2\u036d\u031c\3\2\2\2\u036d\u031d\3\2\2\2\u036d\u0321\3\2\2\2\u036d"+
		"\u0322\3\2\2\2\u036d\u0323\3\2\2\2\u036d\u0333\3\2\2\2\u036d\u033b\3\2"+
		"\2\2\u036d\u0349\3\2\2\2\u036d\u0351\3\2\2\2\u036d\u0359\3\2\2\2\u036d"+
		"\u0361\3\2\2\2\u036d\u0369\3\2\2\2\u036d\u036a\3\2\2\2\u036d\u036b\3\2"+
		"\2\2\u036d\u036c\3\2\2\2\u036eo\3\2\2\2\u036f\u0370\7\5\2\2\u0370\u0371"+
		"\5\u0098M\2\u0371\u0372\5b\62\2\u0372\u038c\3\2\2\2\u0373\u0374\7\26\2"+
		"\2\u0374\u0375\5\u0098M\2\u0375\u0376\5b\62\2\u0376\u038c\3\2\2\2\u0377"+
		"\u0378\7\27\2\2\u0378\u0379\5\u0098M\2\u0379\u037a\5b\62\2\u037a\u038c"+
		"\3\2\2\2\u037b\u037c\7\30\2\2\u037c\u037d\5\u0098M\2\u037d\u037e\5b\62"+
		"\2\u037e\u038c\3\2\2\2\u037f\u0380\7\31\2\2\u0380\u0381\5\u0098M\2\u0381"+
		"\u0382\5b\62\2\u0382\u038c\3\2\2\2\u0383\u0384\7\32\2\2\u0384\u0385\5"+
		"\u0098M\2\u0385\u0386\5b\62\2\u0386\u038c\3\2\2\2\u0387\u0388\7\33\2\2"+
		"\u0388\u0389\5\u0098M\2\u0389\u038a\5b\62\2\u038a\u038c\3\2\2\2\u038b"+
		"\u036f\3\2\2\2\u038b\u0373\3\2\2\2\u038b\u0377\3\2\2\2\u038b\u037b\3\2"+
		"\2\2\u038b\u037f\3\2\2\2\u038b\u0383\3\2\2\2\u038b\u0387\3\2\2\2\u038c"+
		"q\3\2\2\2\u038d\u038e\7\b\2\2\u038e\u038f\5\u0098M\2\u038f\u0390\5V,\2"+
		"\u0390\u0391\5\u0098M\2\u0391\u0392\7\t\2\2\u0392s\3\2\2\2\u0393\u0397"+
		"\5@!\2\u0394\u0395\5\u0098M\2\u0395\u0396\5B\"\2\u0396\u0398\3\2\2\2\u0397"+
		"\u0394\3\2\2\2\u0398\u0399\3\2\2\2\u0399\u0397\3\2\2\2\u0399\u039a\3\2"+
		"\2\2\u039au\3\2\2\2\u039b\u039f\5x=\2\u039c\u039d\5\u0098M\2\u039d\u039e"+
		"\5\66\34\2\u039e\u03a0\3\2\2\2\u039f\u039c\3\2\2\2\u039f\u03a0\3\2\2\2"+
		"\u03a0w\3\2\2\2\u03a1\u03a2\5\u0082B\2\u03a2\u03a3\5\u009aN\2\u03a3\u03a4"+
		"\7^\2\2\u03a4\u03a5\5\u009aN\2\u03a5\u03a6\5V,\2\u03a6y\3\2\2\2\u03a7"+
		"\u03a8\5|?\2\u03a8\u03a9\5\u0098M\2\u03a9\u03aa\7\b\2\2\u03aa\u03ac\5"+
		"\u0098M\2\u03ab\u03ad\7O\2\2\u03ac\u03ab\3\2\2\2\u03ac\u03ad\3\2\2\2\u03ad"+
		"\u03b8\3\2\2\2\u03ae\u03b5\5V,\2\u03af\u03b0\7\4\2\2\u03b0\u03b1\5\u0098"+
		"M\2\u03b1\u03b2\5V,\2\u03b2\u03b4\3\2\2\2\u03b3\u03af\3\2\2\2\u03b4\u03b7"+
		"\3\2\2\2\u03b5\u03b3\3\2\2\2\u03b5\u03b6\3\2\2\2\u03b6\u03b9\3\2\2\2\u03b7"+
		"\u03b5\3\2\2\2\u03b8\u03ae\3\2\2\2\u03b8\u03b9\3\2\2\2\u03b9\u03ba\3\2"+
		"\2\2\u03ba\u03bb\5\u0098M\2\u03bb\u03bc\7\t\2\2\u03bc{\3\2\2\2\u03bd\u03be"+
		"\5\u0096L\2\u03be}\3\2\2\2\u03bf\u03c0\7\n\2\2\u03c0\u03c5\5v<\2\u03c1"+
		"\u03c2\5\u0098M\2\u03c2\u03c3\7\16\2\2\u03c3\u03c4\5V,\2\u03c4\u03c6\3"+
		"\2\2\2\u03c5\u03c1\3\2\2\2\u03c5\u03c6\3\2\2\2\u03c6\u03c7\3\2\2\2\u03c7"+
		"\u03c8\7\f\2\2\u03c8\177\3\2\2\2\u03c9\u03ca\5\u0098M\2\u03ca\u03cb\7"+
		"\34\2\2\u03cb\u03d0\5\u0098M\2\u03cc\u03cd\5\u008cG\2\u03cd\u03ce\t\5"+
		"\2\2\u03ce\u03d1\3\2\2\2\u03cf\u03d1\5\u008cG\2\u03d0\u03cc\3\2\2\2\u03d0"+
		"\u03cf\3\2\2\2\u03d1\u0081\3\2\2\2\u03d2\u03d3\5\u0096L\2\u03d3\u0083"+
		"\3\2\2\2\u03d4\u03d7\5\u0090I\2\u03d5\u03d7\5\u008eH\2\u03d6\u03d4\3\2"+
		"\2\2\u03d6\u03d5\3\2\2\2\u03d7\u0085\3\2\2\2\u03d8\u03d9\7\36\2\2\u03d9"+
		"\u03ee\5\u0098M\2\u03da\u03db\5\u008cG\2\u03db\u03dc\5\u0098M\2\u03dc"+
		"\u03dd\7\r\2\2\u03dd\u03de\5\u0098M\2\u03de\u03df\5V,\2\u03df\u03eb\5"+
		"\u0098M\2\u03e0\u03e1\7\4\2\2\u03e1\u03e2\5\u0098M\2\u03e2\u03e3\5\u008c"+
		"G\2\u03e3\u03e4\5\u0098M\2\u03e4\u03e5\7\r\2\2\u03e5\u03e6\5\u0098M\2"+
		"\u03e6\u03e7\5V,\2\u03e7\u03e8\5\u0098M\2\u03e8\u03ea\3\2\2\2\u03e9\u03e0"+
		"\3\2\2\2\u03ea\u03ed\3\2\2\2\u03eb\u03e9\3\2\2\2\u03eb\u03ec\3\2\2\2\u03ec"+
		"\u03ef\3\2\2\2\u03ed\u03eb\3\2\2\2\u03ee\u03da\3\2\2\2\u03ee\u03ef\3\2"+
		"\2\2\u03ef\u03f0\3\2\2\2\u03f0\u03f1\7\37\2\2\u03f1\u0087\3\2\2\2\u03f2"+
		"\u03f5\7 \2\2\u03f3\u03f6\5\u0096L\2\u03f4\u03f6\79\2\2\u03f5\u03f3\3"+
		"\2\2\2\u03f5\u03f4\3\2\2\2\u03f6\u0089\3\2\2\2\u03f7\u03fb\5n8\2\u03f8"+
		"\u03f9\5\u0098M\2\u03f9\u03fa\5\u0080A\2\u03fa\u03fc\3\2\2\2\u03fb\u03f8"+
		"\3\2\2\2\u03fc\u03fd\3\2\2\2\u03fd\u03fb\3\2\2\2\u03fd\u03fe\3\2\2\2\u03fe"+
		"\u008b\3\2\2\2\u03ff\u0400\5\u0096L\2\u0400\u008d\3\2\2\2\u0401\u0402"+
		"\t\6\2\2\u0402\u008f\3\2\2\2\u0403\u0406\5\u0092J\2\u0404\u0406\5\u0094"+
		"K\2\u0405\u0403\3\2\2\2\u0405\u0404\3\2\2\2\u0406\u0091\3\2\2\2\u0407"+
		"\u0409\t\7\2\2\u0408\u0407\3\2\2\2\u0409\u040a\3\2\2\2\u040a\u0408\3\2"+
		"\2\2\u040a\u040b\3\2\2\2\u040b\u040e\3\2\2\2\u040c\u040e\79\2\2\u040d"+
		"\u0408\3\2\2\2\u040d\u040c\3\2\2\2\u040e\u0411\3\2\2\2\u040f\u0412\t\b"+
		"\2\2\u0410\u0412\t\b\2\2\u0411\u040f\3\2\2\2\u0411\u0410\3\2\2\2\u0412"+
		"\u0413\3\2\2\2\u0413\u0414\t\t\2\2\u0414\u0093\3\2\2\2\u0415\u0417\7?"+
		"\2\2\u0416\u0415\3\2\2\2\u0417\u041a\3\2\2\2\u0418\u0416\3\2\2\2\u0418"+
		"\u0419\3\2\2\2\u0419\u041d\3\2\2\2\u041a\u0418\3\2\2\2\u041b\u041d\79"+
		"\2\2\u041c\u0418\3\2\2\2\u041c\u041b\3\2\2\2\u041d\u041e\3\2\2\2\u041e"+
		"\u041f\7\34\2\2\u041f\u0420\t\t\2\2\u0420\u0095\3\2\2\2\u0421\u0422\t"+
		"\n\2\2\u0422\u0097\3\2\2\2\u0423\u0425\7p\2\2\u0424\u0423\3\2\2\2\u0425"+
		"\u0428\3\2\2\2\u0426\u0424\3\2\2\2\u0426\u0427\3\2\2\2\u0427\u0099\3\2"+
		"\2\2\u0428\u0426\3\2\2\2\u0429\u042b\7p\2\2\u042a\u0429\3\2\2\2\u042b"+
		"\u042c\3\2\2\2\u042c\u042a\3\2\2\2\u042c\u042d\3\2\2\2\u042d\u009b\3\2"+
		"\2\2\u042e\u042f\t\13\2\2\u042f\u009d\3\2\2\2\u0430\u0431\t\f\2\2\u0431"+
		"\u009f\3\2\2\2\u0432\u0433\t\r\2\2\u0433\u00a1\3\2\2\2\u0434\u0435\7?"+
		"\2\2\u0435\u00a3\3\2\2\2b\u00a9\u00b7\u00c0\u00ca\u00d5\u00d9\u00e1\u00f3"+
		"\u0102\u010e\u0120\u0128\u0133\u0136\u0142\u0149\u0150\u0156\u0158\u0164"+
		"\u016a\u016f\u0174\u017e\u0189\u018c\u0195\u01a2\u01b2\u01b4\u01c2\u01cc"+
		"\u01d6\u01dd\u01e4\u01e9\u01ee\u01fb\u0207\u020f\u0219\u021e\u0222\u0225"+
		"\u0228\u022c\u022f\u0235\u023c\u0243\u024c\u0256\u025d\u025f\u026f\u027a"+
		"\u0285\u028e\u0299\u02a7\u02a9\u02bc\u02be\u02c9\u02d0\u02de\u02e2\u02f9"+
		"\u030a\u030c\u0312\u0314\u032e\u0345\u036d\u038b\u0399\u039f\u03ac\u03b5"+
		"\u03b8\u03c5\u03d0\u03d6\u03eb\u03ee\u03f5\u03fd\u0405\u040a\u040d\u0411"+
		"\u0418\u041c\u0426\u042c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}