// Generated from Cypher.g4 by ANTLR 4.5.3
package ingraph.antlr;
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
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
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
			setState(203);
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
				ws();
				setState(197);
				singleQuery();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(199);
				match(UNION);
				setState(200);
				ws();
				setState(201);
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
			setState(214);
			switch (_input.LA(1)) {
			case OPTIONAL:
			case MATCH:
				enterOuterAlt(_localctx, 1);
				{
				setState(205);
				match();
				}
				break;
			case UNWIND:
				enterOuterAlt(_localctx, 2);
				{
				setState(206);
				unwind();
				}
				break;
			case MERGE:
				enterOuterAlt(_localctx, 3);
				{
				setState(207);
				merge();
				}
				break;
			case CREATE:
				enterOuterAlt(_localctx, 4);
				{
				setState(208);
				create();
				}
				break;
			case SET:
				enterOuterAlt(_localctx, 5);
				{
				setState(209);
				set();
				}
				break;
			case DELETE:
			case DETACH:
				enterOuterAlt(_localctx, 6);
				{
				setState(210);
				delete();
				}
				break;
			case REMOVE:
				enterOuterAlt(_localctx, 7);
				{
				setState(211);
				remove();
				}
				break;
			case WITH:
				enterOuterAlt(_localctx, 8);
				{
				setState(212);
				with();
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 9);
				{
				setState(213);
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
			setState(218);
			_la = _input.LA(1);
			if (_la==OPTIONAL) {
				{
				setState(216);
				match(OPTIONAL);
				setState(217);
				sp();
				}
			}

			setState(220);
			match(MATCH);
			setState(221);
			ws();
			setState(222);
			pattern();
			setState(226);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(223);
				ws();
				setState(224);
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
			setState(228);
			match(UNWIND);
			setState(229);
			ws();
			setState(230);
			expression();
			setState(231);
			sp();
			setState(232);
			match(AS);
			setState(233);
			sp();
			setState(234);
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
			setState(236);
			match(MERGE);
			setState(237);
			ws();
			setState(238);
			patternPart();
			setState(244);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(239);
					sp();
					setState(240);
					mergeAction();
					}
					} 
				}
				setState(246);
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
			setState(259);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(247);
				match(ON);
				setState(248);
				sp();
				setState(249);
				match(MATCH);
				setState(250);
				sp();
				setState(251);
				set();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(253);
				match(ON);
				setState(254);
				sp();
				setState(255);
				match(CREATE);
				setState(256);
				sp();
				setState(257);
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
			setState(261);
			match(CREATE);
			setState(262);
			ws();
			setState(263);
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
			setState(265);
			match(SET);
			setState(266);
			setItem();
			setState(271);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(267);
				match(T__1);
				setState(268);
				setItem();
				}
				}
				setState(273);
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
			setState(289);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(274);
				propertyExpression();
				setState(275);
				match(T__2);
				setState(276);
				expression();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(278);
				variable();
				setState(279);
				match(T__2);
				setState(280);
				expression();
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(282);
				variable();
				setState(283);
				match(T__3);
				setState(284);
				expression();
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(286);
				variable();
				setState(287);
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
			setState(311);
			switch (_input.LA(1)) {
			case DELETE:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(291);
				match(DELETE);
				setState(292);
				expression();
				setState(297);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(293);
					match(T__1);
					setState(294);
					expression();
					}
					}
					setState(299);
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
				setState(300);
				match(DETACH);
				setState(301);
				sp();
				setState(302);
				match(DELETE);
				setState(303);
				expression();
				setState(308);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(304);
					match(T__1);
					setState(305);
					expression();
					}
					}
					setState(310);
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
			setState(313);
			match(REMOVE);
			setState(314);
			sp();
			setState(315);
			removeItem();
			setState(323);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(316);
					ws();
					setState(317);
					match(T__1);
					setState(318);
					ws();
					setState(319);
					removeItem();
					}
					} 
				}
				setState(325);
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
			setState(330);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(326);
				variable();
				setState(327);
				nodeLabels();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(329);
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
			setState(345);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(332);
				match(WITH);
				setState(333);
				match(DISTINCT);
				setState(334);
				sp();
				setState(335);
				returnBody();
				setState(337);
				_la = _input.LA(1);
				if (_la==WHERE) {
					{
					setState(336);
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
				setState(339);
				match(WITH);
				setState(340);
				sp();
				setState(341);
				returnBody();
				setState(343);
				_la = _input.LA(1);
				if (_la==WHERE) {
					{
					setState(342);
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
			setState(357);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(347);
				match(RETURN);
				setState(348);
				sp();
				setState(349);
				match(DISTINCT);
				setState(350);
				sp();
				setState(351);
				returnBody();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(353);
				match(RETURN);
				setState(354);
				sp();
				setState(355);
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
			setState(359);
			returnItems();
			setState(363);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(360);
				sp();
				setState(361);
				order();
				}
				break;
			}
			setState(368);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(365);
				sp();
				setState(366);
				skip();
				}
				break;
			}
			setState(373);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(370);
				sp();
				setState(371);
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
			setState(397);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(375);
				match(T__4);
				setState(383);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(376);
						ws();
						setState(377);
						match(T__1);
						setState(378);
						ws();
						setState(379);
						returnItem();
						}
						} 
					}
					setState(385);
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
				setState(386);
				returnItem();
				setState(394);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(387);
						ws();
						setState(388);
						match(T__1);
						setState(389);
						ws();
						setState(390);
						returnItem();
						}
						} 
					}
					setState(396);
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
			setState(406);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(399);
				expression();
				setState(400);
				sp();
				setState(401);
				match(AS);
				setState(402);
				sp();
				setState(403);
				variable();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(405);
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
			setState(408);
			match(ORDER);
			setState(409);
			sp();
			setState(410);
			match(BY);
			setState(411);
			sp();
			setState(412);
			sortItem();
			setState(419);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(413);
				match(T__1);
				setState(414);
				ws();
				setState(415);
				sortItem();
				}
				}
				setState(421);
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
			setState(422);
			match(L_SKIP);
			setState(423);
			sp();
			setState(424);
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
			setState(426);
			match(LIMIT);
			setState(427);
			sp();
			setState(428);
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
			setState(437);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(430);
				expression();
				setState(431);
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
				setState(433);
				expression();
				setState(435);
				_la = _input.LA(1);
				if (_la==ASCENDING || _la==ASC) {
					{
					setState(434);
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
			setState(439);
			match(WHERE);
			setState(440);
			sp();
			setState(441);
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
			setState(443);
			patternPart();
			setState(451);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(444);
					ws();
					setState(445);
					match(T__1);
					setState(446);
					ws();
					setState(447);
					patternPart();
					}
					} 
				}
				setState(453);
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
			setState(461);
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
				setState(454);
				variable();
				setState(455);
				ws();
				setState(456);
				match(T__2);
				setState(457);
				ws();
				setState(458);
				anonymousPatternPart();
				}
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(460);
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
			setState(463);
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
			setState(478);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(465);
				nodePattern();
				setState(471);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(466);
						ws();
						setState(467);
						patternElementChain();
						}
						} 
					}
					setState(473);
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
				setState(474);
				match(T__5);
				setState(475);
				patternElement();
				setState(476);
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
			setState(480);
			match(T__5);
			setState(481);
			ws();
			setState(485);
			_la = _input.LA(1);
			if (((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (HexString - 57)) | (1L << (UNION - 57)) | (1L << (ALL - 57)) | (1L << (OPTIONAL - 57)) | (1L << (MATCH - 57)) | (1L << (UNWIND - 57)) | (1L << (AS - 57)) | (1L << (MERGE - 57)) | (1L << (ON - 57)) | (1L << (CREATE - 57)) | (1L << (SET - 57)) | (1L << (DELETE - 57)) | (1L << (DETACH - 57)) | (1L << (REMOVE - 57)) | (1L << (WITH - 57)) | (1L << (DISTINCT - 57)) | (1L << (RETURN - 57)) | (1L << (ORDER - 57)) | (1L << (BY - 57)) | (1L << (L_SKIP - 57)) | (1L << (LIMIT - 57)) | (1L << (DESCENDING - 57)) | (1L << (DESC - 57)) | (1L << (ASCENDING - 57)) | (1L << (ASC - 57)) | (1L << (WHERE - 57)) | (1L << (OR - 57)) | (1L << (XOR - 57)) | (1L << (AND - 57)) | (1L << (NOT - 57)) | (1L << (IN - 57)) | (1L << (STARTS - 57)) | (1L << (ENDS - 57)) | (1L << (CONTAINS - 57)) | (1L << (IS - 57)) | (1L << (NULL - 57)) | (1L << (TRUE - 57)) | (1L << (FALSE - 57)) | (1L << (COUNT - 57)) | (1L << (FILTER - 57)) | (1L << (EXTRACT - 57)) | (1L << (ANY - 57)) | (1L << (NONE - 57)) | (1L << (SINGLE - 57)) | (1L << (UnescapedSymbolicName - 57)) | (1L << (EscapedSymbolicName - 57)))) != 0)) {
				{
				setState(482);
				variable();
				setState(483);
				ws();
				}
			}

			setState(490);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(487);
				nodeLabels();
				setState(488);
				ws();
				}
			}

			setState(495);
			_la = _input.LA(1);
			if (_la==T__27 || _la==T__29) {
				{
				setState(492);
				properties();
				setState(493);
				ws();
				}
			}

			setState(497);
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
			setState(499);
			relationshipPattern();
			setState(500);
			ws();
			setState(501);
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
			setState(543);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(503);
				leftArrowHead();
				setState(504);
				ws();
				setState(505);
				dash();
				setState(506);
				ws();
				setState(508);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(507);
					relationshipDetail();
					}
				}

				setState(510);
				ws();
				setState(511);
				dash();
				setState(512);
				ws();
				setState(513);
				rightArrowHead();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(515);
				leftArrowHead();
				setState(516);
				ws();
				setState(517);
				dash();
				setState(518);
				ws();
				setState(520);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(519);
					relationshipDetail();
					}
				}

				setState(522);
				ws();
				setState(523);
				dash();
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(525);
				dash();
				setState(526);
				ws();
				setState(528);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(527);
					relationshipDetail();
					}
				}

				setState(530);
				ws();
				setState(531);
				dash();
				setState(532);
				ws();
				setState(533);
				rightArrowHead();
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(535);
				dash();
				setState(536);
				ws();
				setState(538);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(537);
					relationshipDetail();
					}
				}

				setState(540);
				ws();
				setState(541);
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
			setState(545);
			match(T__7);
			setState(547);
			_la = _input.LA(1);
			if (((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (HexString - 57)) | (1L << (UNION - 57)) | (1L << (ALL - 57)) | (1L << (OPTIONAL - 57)) | (1L << (MATCH - 57)) | (1L << (UNWIND - 57)) | (1L << (AS - 57)) | (1L << (MERGE - 57)) | (1L << (ON - 57)) | (1L << (CREATE - 57)) | (1L << (SET - 57)) | (1L << (DELETE - 57)) | (1L << (DETACH - 57)) | (1L << (REMOVE - 57)) | (1L << (WITH - 57)) | (1L << (DISTINCT - 57)) | (1L << (RETURN - 57)) | (1L << (ORDER - 57)) | (1L << (BY - 57)) | (1L << (L_SKIP - 57)) | (1L << (LIMIT - 57)) | (1L << (DESCENDING - 57)) | (1L << (DESC - 57)) | (1L << (ASCENDING - 57)) | (1L << (ASC - 57)) | (1L << (WHERE - 57)) | (1L << (OR - 57)) | (1L << (XOR - 57)) | (1L << (AND - 57)) | (1L << (NOT - 57)) | (1L << (IN - 57)) | (1L << (STARTS - 57)) | (1L << (ENDS - 57)) | (1L << (CONTAINS - 57)) | (1L << (IS - 57)) | (1L << (NULL - 57)) | (1L << (TRUE - 57)) | (1L << (FALSE - 57)) | (1L << (COUNT - 57)) | (1L << (FILTER - 57)) | (1L << (EXTRACT - 57)) | (1L << (ANY - 57)) | (1L << (NONE - 57)) | (1L << (SINGLE - 57)) | (1L << (UnescapedSymbolicName - 57)) | (1L << (EscapedSymbolicName - 57)))) != 0)) {
				{
				setState(546);
				variable();
				}
			}

			setState(550);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(549);
				match(T__8);
				}
			}

			setState(553);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(552);
				relationshipTypes();
				}
			}

			setState(556);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(555);
				rangeLiteral();
				}
			}

			setState(559);
			_la = _input.LA(1);
			if (_la==T__27 || _la==T__29) {
				{
				setState(558);
				properties();
				}
			}

			setState(561);
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
			setState(565);
			switch (_input.LA(1)) {
			case T__27:
				enterOuterAlt(_localctx, 1);
				{
				setState(563);
				mapLiteral();
				}
				break;
			case T__29:
				enterOuterAlt(_localctx, 2);
				{
				setState(564);
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
			setState(567);
			match(T__10);
			setState(568);
			relTypeName();
			setState(579);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11 || _la==WHITESPACE) {
				{
				{
				setState(569);
				ws();
				setState(570);
				match(T__11);
				setState(572);
				_la = _input.LA(1);
				if (_la==T__10) {
					{
					setState(571);
					match(T__10);
					}
				}

				setState(574);
				ws();
				setState(575);
				relTypeName();
				}
				}
				setState(581);
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
			setState(582);
			nodeLabel();
			setState(588);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,50,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(583);
					ws();
					setState(584);
					nodeLabel();
					}
					} 
				}
				setState(590);
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
			setState(591);
			match(T__10);
			setState(592);
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
			setState(594);
			match(T__4);
			setState(595);
			ws();
			setState(599);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HexInteger) | (1L << DecimalInteger) | (1L << OctalInteger))) != 0)) {
				{
				setState(596);
				integerLiteral();
				setState(597);
				ws();
				}
			}

			setState(608);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(601);
				match(T__12);
				setState(602);
				ws();
				setState(606);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << HexInteger) | (1L << DecimalInteger) | (1L << OctalInteger))) != 0)) {
					{
					setState(603);
					integerLiteral();
					setState(604);
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
			setState(610);
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
			setState(612);
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
			setState(614);
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
			setState(616);
			expression11();
			setState(624);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(617);
					sp();
					setState(618);
					match(OR);
					setState(619);
					sp();
					setState(620);
					expression11();
					}
					} 
				}
				setState(626);
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
			setState(627);
			expression10();
			setState(635);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(628);
					sp();
					setState(629);
					match(XOR);
					setState(630);
					sp();
					setState(631);
					expression10();
					}
					} 
				}
				setState(637);
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
			setState(638);
			expression9();
			setState(646);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(639);
					sp();
					setState(640);
					match(AND);
					setState(641);
					sp();
					setState(642);
					expression9();
					}
					} 
				}
				setState(648);
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
			setState(655);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(649);
				sp();
				setState(650);
				match(NOT);
				setState(651);
				sp();
				}
				}
				setState(657);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(658);
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
			setState(660);
			expression7();
			setState(666);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(661);
					ws();
					setState(662);
					partialComparisonExpression();
					}
					} 
				}
				setState(668);
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
			setState(669);
			expression6();
			setState(682);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(680);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
					case 1:
						{
						{
						setState(670);
						ws();
						setState(671);
						match(T__13);
						setState(672);
						ws();
						setState(673);
						expression6();
						}
						}
						break;
					case 2:
						{
						{
						setState(675);
						ws();
						setState(676);
						match(T__14);
						setState(677);
						ws();
						setState(678);
						expression6();
						}
						}
						break;
					}
					} 
				}
				setState(684);
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
			setState(685);
			expression5();
			setState(703);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(701);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
					case 1:
						{
						{
						setState(686);
						ws();
						setState(687);
						match(T__4);
						setState(688);
						ws();
						setState(689);
						expression5();
						}
						}
						break;
					case 2:
						{
						{
						setState(691);
						ws();
						setState(692);
						match(T__15);
						setState(693);
						ws();
						setState(694);
						expression5();
						}
						}
						break;
					case 3:
						{
						{
						setState(696);
						ws();
						setState(697);
						match(T__16);
						setState(698);
						ws();
						setState(699);
						expression5();
						}
						}
						break;
					}
					} 
				}
				setState(705);
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
			setState(706);
			expression4();
			setState(714);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(707);
					ws();
					setState(708);
					match(T__17);
					setState(709);
					ws();
					setState(710);
					expression4();
					}
					} 
				}
				setState(716);
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
			setState(721);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__13 || _la==T__14) {
				{
				{
				setState(717);
				_la = _input.LA(1);
				if ( !(_la==T__13 || _la==T__14) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(718);
				ws();
				}
				}
				setState(723);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(724);
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
			setState(726);
			expression2();
			setState(781);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,69,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(779);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
					case 1:
						{
						{
						setState(727);
						ws();
						setState(728);
						match(T__7);
						setState(729);
						expression();
						setState(730);
						match(T__9);
						}
						}
						break;
					case 2:
						{
						{
						setState(732);
						ws();
						setState(733);
						match(T__7);
						setState(735);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__7) | (1L << T__13) | (1L << T__14) | (1L << T__25) | (1L << T__27) | (1L << T__29) | (1L << StringLiteral) | (1L << HexInteger) | (1L << DecimalInteger) | (1L << OctalInteger) | (1L << HexString) | (1L << Digit) | (1L << UNION))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (ALL - 64)) | (1L << (OPTIONAL - 64)) | (1L << (MATCH - 64)) | (1L << (UNWIND - 64)) | (1L << (AS - 64)) | (1L << (MERGE - 64)) | (1L << (ON - 64)) | (1L << (CREATE - 64)) | (1L << (SET - 64)) | (1L << (DELETE - 64)) | (1L << (DETACH - 64)) | (1L << (REMOVE - 64)) | (1L << (WITH - 64)) | (1L << (DISTINCT - 64)) | (1L << (RETURN - 64)) | (1L << (ORDER - 64)) | (1L << (BY - 64)) | (1L << (L_SKIP - 64)) | (1L << (LIMIT - 64)) | (1L << (DESCENDING - 64)) | (1L << (DESC - 64)) | (1L << (ASCENDING - 64)) | (1L << (ASC - 64)) | (1L << (WHERE - 64)) | (1L << (OR - 64)) | (1L << (XOR - 64)) | (1L << (AND - 64)) | (1L << (NOT - 64)) | (1L << (IN - 64)) | (1L << (STARTS - 64)) | (1L << (ENDS - 64)) | (1L << (CONTAINS - 64)) | (1L << (IS - 64)) | (1L << (NULL - 64)) | (1L << (TRUE - 64)) | (1L << (FALSE - 64)) | (1L << (COUNT - 64)) | (1L << (FILTER - 64)) | (1L << (EXTRACT - 64)) | (1L << (ANY - 64)) | (1L << (NONE - 64)) | (1L << (SINGLE - 64)) | (1L << (UnescapedSymbolicName - 64)) | (1L << (EscapedSymbolicName - 64)) | (1L << (WHITESPACE - 64)))) != 0)) {
							{
							setState(734);
							expression();
							}
						}

						setState(737);
						match(T__12);
						setState(739);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__7) | (1L << T__13) | (1L << T__14) | (1L << T__25) | (1L << T__27) | (1L << T__29) | (1L << StringLiteral) | (1L << HexInteger) | (1L << DecimalInteger) | (1L << OctalInteger) | (1L << HexString) | (1L << Digit) | (1L << UNION))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (ALL - 64)) | (1L << (OPTIONAL - 64)) | (1L << (MATCH - 64)) | (1L << (UNWIND - 64)) | (1L << (AS - 64)) | (1L << (MERGE - 64)) | (1L << (ON - 64)) | (1L << (CREATE - 64)) | (1L << (SET - 64)) | (1L << (DELETE - 64)) | (1L << (DETACH - 64)) | (1L << (REMOVE - 64)) | (1L << (WITH - 64)) | (1L << (DISTINCT - 64)) | (1L << (RETURN - 64)) | (1L << (ORDER - 64)) | (1L << (BY - 64)) | (1L << (L_SKIP - 64)) | (1L << (LIMIT - 64)) | (1L << (DESCENDING - 64)) | (1L << (DESC - 64)) | (1L << (ASCENDING - 64)) | (1L << (ASC - 64)) | (1L << (WHERE - 64)) | (1L << (OR - 64)) | (1L << (XOR - 64)) | (1L << (AND - 64)) | (1L << (NOT - 64)) | (1L << (IN - 64)) | (1L << (STARTS - 64)) | (1L << (ENDS - 64)) | (1L << (CONTAINS - 64)) | (1L << (IS - 64)) | (1L << (NULL - 64)) | (1L << (TRUE - 64)) | (1L << (FALSE - 64)) | (1L << (COUNT - 64)) | (1L << (FILTER - 64)) | (1L << (EXTRACT - 64)) | (1L << (ANY - 64)) | (1L << (NONE - 64)) | (1L << (SINGLE - 64)) | (1L << (UnescapedSymbolicName - 64)) | (1L << (EscapedSymbolicName - 64)) | (1L << (WHITESPACE - 64)))) != 0)) {
							{
							setState(738);
							expression();
							}
						}

						setState(741);
						match(T__9);
						}
						}
						break;
					case 3:
						{
						{
						setState(762);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
						case 1:
							{
							{
							setState(743);
							ws();
							setState(744);
							match(T__18);
							}
							}
							break;
						case 2:
							{
							{
							setState(746);
							sp();
							setState(747);
							match(IN);
							}
							}
							break;
						case 3:
							{
							{
							setState(749);
							sp();
							setState(750);
							match(STARTS);
							setState(751);
							sp();
							setState(752);
							match(WITH);
							}
							}
							break;
						case 4:
							{
							{
							setState(754);
							sp();
							setState(755);
							match(ENDS);
							setState(756);
							sp();
							setState(757);
							match(WITH);
							}
							}
							break;
						case 5:
							{
							{
							setState(759);
							sp();
							setState(760);
							match(CONTAINS);
							}
							}
							break;
						}
						setState(764);
						ws();
						setState(765);
						expression2();
						}
						}
						break;
					case 4:
						{
						{
						setState(767);
						sp();
						setState(768);
						match(IS);
						setState(769);
						sp();
						setState(770);
						match(NULL);
						}
						}
						break;
					case 5:
						{
						{
						setState(772);
						sp();
						setState(773);
						match(IS);
						setState(774);
						sp();
						setState(775);
						match(NOT);
						setState(776);
						sp();
						setState(777);
						match(NULL);
						}
						}
						break;
					}
					} 
				}
				setState(783);
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
			setState(784);
			atom();
			setState(789);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,71,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(787);
					switch (_input.LA(1)) {
					case T__25:
					case WHITESPACE:
						{
						setState(785);
						propertyLookup();
						}
						break;
					case T__10:
						{
						setState(786);
						nodeLabels();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(791);
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
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public MapLiteralContext mapLiteral() {
			return getRuleContext(MapLiteralContext.class,0);
		}
		public ListComprehensionContext listComprehension() {
			return getRuleContext(ListComprehensionContext.class,0);
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
			setState(882);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(792);
				numberLiteral();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(793);
				match(StringLiteral);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(794);
				parameter();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(795);
				match(TRUE);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(796);
				match(FALSE);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(797);
				match(NULL);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				{
				setState(798);
				match(COUNT);
				setState(799);
				ws();
				setState(800);
				match(T__5);
				setState(801);
				ws();
				setState(802);
				match(T__4);
				setState(803);
				ws();
				setState(804);
				match(T__6);
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(806);
				mapLiteral();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(807);
				listComprehension();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				{
				setState(808);
				match(T__7);
				setState(809);
				ws();
				setState(810);
				expression();
				setState(811);
				ws();
				setState(819);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(812);
					match(T__1);
					setState(813);
					ws();
					setState(814);
					expression();
					setState(815);
					ws();
					}
					}
					setState(821);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(822);
				match(T__9);
				}
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				{
				setState(824);
				match(FILTER);
				setState(825);
				ws();
				setState(826);
				match(T__5);
				setState(827);
				ws();
				setState(828);
				filterExpression();
				setState(829);
				ws();
				setState(830);
				match(T__6);
				}
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				{
				setState(832);
				match(EXTRACT);
				setState(833);
				ws();
				setState(834);
				match(T__5);
				setState(835);
				ws();
				setState(836);
				filterExpression();
				setState(837);
				ws();
				setState(842);
				_la = _input.LA(1);
				if (_la==T__11 || _la==WHITESPACE) {
					{
					setState(838);
					ws();
					setState(839);
					match(T__11);
					setState(840);
					expression();
					}
				}

				setState(844);
				match(T__6);
				}
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				{
				setState(846);
				match(ALL);
				setState(847);
				ws();
				setState(848);
				match(T__5);
				setState(849);
				ws();
				setState(850);
				filterExpression();
				setState(851);
				ws();
				setState(852);
				match(T__6);
				}
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				{
				setState(854);
				match(ANY);
				setState(855);
				ws();
				setState(856);
				match(T__5);
				setState(857);
				ws();
				setState(858);
				filterExpression();
				setState(859);
				ws();
				setState(860);
				match(T__6);
				}
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				{
				setState(862);
				match(NONE);
				setState(863);
				ws();
				setState(864);
				match(T__5);
				setState(865);
				ws();
				setState(866);
				filterExpression();
				setState(867);
				ws();
				setState(868);
				match(T__6);
				}
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				{
				setState(870);
				match(SINGLE);
				setState(871);
				ws();
				setState(872);
				match(T__5);
				setState(873);
				ws();
				setState(874);
				filterExpression();
				setState(875);
				ws();
				setState(876);
				match(T__6);
				}
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(878);
				relationshipsPattern();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(879);
				parenthesizedExpression();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(880);
				functionInvocation();
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(881);
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
			setState(912);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(884);
				match(T__2);
				setState(885);
				ws();
				setState(886);
				expression7();
				}
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(888);
				match(T__19);
				setState(889);
				ws();
				setState(890);
				expression7();
				}
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(892);
				match(T__20);
				setState(893);
				ws();
				setState(894);
				expression7();
				}
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(896);
				match(T__21);
				setState(897);
				ws();
				setState(898);
				expression7();
				}
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(900);
				match(T__22);
				setState(901);
				ws();
				setState(902);
				expression7();
				}
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 6);
				{
				{
				setState(904);
				match(T__23);
				setState(905);
				ws();
				setState(906);
				expression7();
				}
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 7);
				{
				{
				setState(908);
				match(T__24);
				setState(909);
				ws();
				setState(910);
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
			setState(914);
			match(T__5);
			setState(915);
			ws();
			setState(916);
			expression();
			setState(917);
			ws();
			setState(918);
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
			setState(920);
			nodePattern();
			setState(924); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(921);
					ws();
					setState(922);
					patternElementChain();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(926); 
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
			setState(928);
			idInColl();
			setState(932);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
			case 1:
				{
				setState(929);
				ws();
				setState(930);
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
			setState(934);
			variable();
			setState(935);
			sp();
			setState(936);
			match(IN);
			setState(937);
			sp();
			setState(938);
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
			setState(940);
			functionName();
			setState(941);
			ws();
			setState(942);
			match(T__5);
			setState(943);
			ws();
			setState(946);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
			case 1:
				{
				setState(944);
				match(DISTINCT);
				setState(945);
				ws();
				}
				break;
			}
			setState(960);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__7) | (1L << T__13) | (1L << T__14) | (1L << T__25) | (1L << T__27) | (1L << T__29) | (1L << StringLiteral) | (1L << HexInteger) | (1L << DecimalInteger) | (1L << OctalInteger) | (1L << HexString) | (1L << Digit) | (1L << UNION))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (ALL - 64)) | (1L << (OPTIONAL - 64)) | (1L << (MATCH - 64)) | (1L << (UNWIND - 64)) | (1L << (AS - 64)) | (1L << (MERGE - 64)) | (1L << (ON - 64)) | (1L << (CREATE - 64)) | (1L << (SET - 64)) | (1L << (DELETE - 64)) | (1L << (DETACH - 64)) | (1L << (REMOVE - 64)) | (1L << (WITH - 64)) | (1L << (DISTINCT - 64)) | (1L << (RETURN - 64)) | (1L << (ORDER - 64)) | (1L << (BY - 64)) | (1L << (L_SKIP - 64)) | (1L << (LIMIT - 64)) | (1L << (DESCENDING - 64)) | (1L << (DESC - 64)) | (1L << (ASCENDING - 64)) | (1L << (ASC - 64)) | (1L << (WHERE - 64)) | (1L << (OR - 64)) | (1L << (XOR - 64)) | (1L << (AND - 64)) | (1L << (NOT - 64)) | (1L << (IN - 64)) | (1L << (STARTS - 64)) | (1L << (ENDS - 64)) | (1L << (CONTAINS - 64)) | (1L << (IS - 64)) | (1L << (NULL - 64)) | (1L << (TRUE - 64)) | (1L << (FALSE - 64)) | (1L << (COUNT - 64)) | (1L << (FILTER - 64)) | (1L << (EXTRACT - 64)) | (1L << (ANY - 64)) | (1L << (NONE - 64)) | (1L << (SINGLE - 64)) | (1L << (UnescapedSymbolicName - 64)) | (1L << (EscapedSymbolicName - 64)) | (1L << (WHITESPACE - 64)))) != 0)) {
				{
				setState(948);
				expression();
				setState(955);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(949);
					match(T__1);
					setState(950);
					ws();
					setState(951);
					expression();
					}
					}
					setState(957);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(958);
				ws();
				}
			}

			setState(962);
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
			setState(964);
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
			setState(966);
			match(T__7);
			setState(967);
			filterExpression();
			setState(972);
			_la = _input.LA(1);
			if (_la==T__11 || _la==WHITESPACE) {
				{
				setState(968);
				ws();
				setState(969);
				match(T__11);
				setState(970);
				expression();
				}
			}

			setState(974);
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
			setState(976);
			ws();
			setState(977);
			match(T__25);
			setState(978);
			ws();
			setState(983);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
			case 1:
				{
				{
				setState(979);
				propertyKeyName();
				setState(980);
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
				setState(982);
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
			setState(985);
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
			setState(989);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(987);
				doubleLiteral();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(988);
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
			setState(991);
			match(T__27);
			setState(992);
			ws();
			setState(1013);
			_la = _input.LA(1);
			if (((((_la - 57)) & ~0x3f) == 0 && ((1L << (_la - 57)) & ((1L << (HexString - 57)) | (1L << (UNION - 57)) | (1L << (ALL - 57)) | (1L << (OPTIONAL - 57)) | (1L << (MATCH - 57)) | (1L << (UNWIND - 57)) | (1L << (AS - 57)) | (1L << (MERGE - 57)) | (1L << (ON - 57)) | (1L << (CREATE - 57)) | (1L << (SET - 57)) | (1L << (DELETE - 57)) | (1L << (DETACH - 57)) | (1L << (REMOVE - 57)) | (1L << (WITH - 57)) | (1L << (DISTINCT - 57)) | (1L << (RETURN - 57)) | (1L << (ORDER - 57)) | (1L << (BY - 57)) | (1L << (L_SKIP - 57)) | (1L << (LIMIT - 57)) | (1L << (DESCENDING - 57)) | (1L << (DESC - 57)) | (1L << (ASCENDING - 57)) | (1L << (ASC - 57)) | (1L << (WHERE - 57)) | (1L << (OR - 57)) | (1L << (XOR - 57)) | (1L << (AND - 57)) | (1L << (NOT - 57)) | (1L << (IN - 57)) | (1L << (STARTS - 57)) | (1L << (ENDS - 57)) | (1L << (CONTAINS - 57)) | (1L << (IS - 57)) | (1L << (NULL - 57)) | (1L << (TRUE - 57)) | (1L << (FALSE - 57)) | (1L << (COUNT - 57)) | (1L << (FILTER - 57)) | (1L << (EXTRACT - 57)) | (1L << (ANY - 57)) | (1L << (NONE - 57)) | (1L << (SINGLE - 57)) | (1L << (UnescapedSymbolicName - 57)) | (1L << (EscapedSymbolicName - 57)))) != 0)) {
				{
				setState(993);
				propertyKeyName();
				setState(994);
				ws();
				setState(995);
				match(T__10);
				setState(996);
				ws();
				setState(997);
				expression();
				setState(998);
				ws();
				setState(1010);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(999);
					match(T__1);
					setState(1000);
					ws();
					setState(1001);
					propertyKeyName();
					setState(1002);
					ws();
					setState(1003);
					match(T__10);
					setState(1004);
					ws();
					setState(1005);
					expression();
					setState(1006);
					ws();
					}
					}
					setState(1012);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(1015);
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
			setState(1017);
			match(T__29);
			setState(1020);
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
				setState(1018);
				symbolicName();
				}
				break;
			case DecimalInteger:
				{
				setState(1019);
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
			setState(1022);
			atom();
			setState(1026); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1023);
					ws();
					setState(1024);
					propertyLookup();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1028); 
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
			setState(1030);
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
			setState(1032);
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
			setState(1036);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,88,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1034);
				exponentDecimalReal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1035);
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
			setState(1044);
			switch (_input.LA(1)) {
			case T__25:
			case Digit:
				{
				setState(1039); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(1038);
					_la = _input.LA(1);
					if ( !(_la==T__25 || _la==Digit) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					}
					setState(1041); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==T__25 || _la==Digit );
				}
				break;
			case DecimalInteger:
				{
				setState(1043);
				match(DecimalInteger);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1048);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,91,_ctx) ) {
			case 1:
				{
				setState(1046);
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
				setState(1047);
				_la = _input.LA(1);
				if ( !(_la==T__30 || _la==T__31) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			}
			setState(1050);
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
			setState(1059);
			switch (_input.LA(1)) {
			case T__25:
			case Digit:
				{
				setState(1055);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Digit) {
					{
					{
					setState(1052);
					match(Digit);
					}
					}
					setState(1057);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case DecimalInteger:
				{
				setState(1058);
				match(DecimalInteger);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1061);
			match(T__25);
			setState(1062);
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
			setState(1064);
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
			setState(1069);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1066);
					match(WHITESPACE);
					}
					} 
				}
				setState(1071);
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
			setState(1073); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1072);
					match(WHITESPACE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1075); 
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
			setState(1077);
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
			setState(1079);
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
			setState(1081);
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
			setState(1083);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3r\u0440\4\2\t\2\4"+
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
		"\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u00ce\n\7\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00d9\n\b\3\t\3\t\5\t\u00dd\n\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\5\t\u00e5\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\7\13\u00f5\n\13\f\13\16\13\u00f8\13\13\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0106\n\f\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\7\16\u0110\n\16\f\16\16\16\u0113\13\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17"+
		"\u0124\n\17\3\20\3\20\3\20\3\20\7\20\u012a\n\20\f\20\16\20\u012d\13\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u0135\n\20\f\20\16\20\u0138\13\20"+
		"\5\20\u013a\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u0144\n"+
		"\21\f\21\16\21\u0147\13\21\3\22\3\22\3\22\3\22\5\22\u014d\n\22\3\23\3"+
		"\23\3\23\3\23\3\23\5\23\u0154\n\23\3\23\3\23\3\23\3\23\5\23\u015a\n\23"+
		"\5\23\u015c\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24"+
		"\u0168\n\24\3\25\3\25\3\25\3\25\5\25\u016e\n\25\3\25\3\25\3\25\5\25\u0173"+
		"\n\25\3\25\3\25\3\25\5\25\u0178\n\25\3\26\3\26\3\26\3\26\3\26\3\26\7\26"+
		"\u0180\n\26\f\26\16\26\u0183\13\26\3\26\3\26\3\26\3\26\3\26\3\26\7\26"+
		"\u018b\n\26\f\26\16\26\u018e\13\26\5\26\u0190\n\26\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\5\27\u0199\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\7\30\u01a4\n\30\f\30\16\30\u01a7\13\30\3\31\3\31\3\31\3\31\3\32"+
		"\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\5\33\u01b6\n\33\5\33\u01b8\n"+
		"\33\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\7\35\u01c4\n\35"+
		"\f\35\16\35\u01c7\13\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u01d0"+
		"\n\36\3\37\3\37\3 \3 \3 \3 \7 \u01d8\n \f \16 \u01db\13 \3 \3 \3 \3 \5"+
		" \u01e1\n \3!\3!\3!\3!\3!\5!\u01e8\n!\3!\3!\3!\5!\u01ed\n!\3!\3!\3!\5"+
		"!\u01f2\n!\3!\3!\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\5#\u01ff\n#\3#\3#\3#\3"+
		"#\3#\3#\3#\3#\3#\3#\5#\u020b\n#\3#\3#\3#\3#\3#\3#\5#\u0213\n#\3#\3#\3"+
		"#\3#\3#\3#\3#\3#\5#\u021d\n#\3#\3#\3#\5#\u0222\n#\3$\3$\5$\u0226\n$\3"+
		"$\5$\u0229\n$\3$\5$\u022c\n$\3$\5$\u022f\n$\3$\5$\u0232\n$\3$\3$\3%\3"+
		"%\5%\u0238\n%\3&\3&\3&\3&\3&\5&\u023f\n&\3&\3&\3&\7&\u0244\n&\f&\16&\u0247"+
		"\13&\3\'\3\'\3\'\3\'\7\'\u024d\n\'\f\'\16\'\u0250\13\'\3(\3(\3(\3)\3)"+
		"\3)\3)\3)\5)\u025a\n)\3)\3)\3)\3)\3)\5)\u0261\n)\5)\u0263\n)\3*\3*\3+"+
		"\3+\3,\3,\3-\3-\3-\3-\3-\3-\7-\u0271\n-\f-\16-\u0274\13-\3.\3.\3.\3.\3"+
		".\3.\7.\u027c\n.\f.\16.\u027f\13.\3/\3/\3/\3/\3/\3/\7/\u0287\n/\f/\16"+
		"/\u028a\13/\3\60\3\60\3\60\3\60\7\60\u0290\n\60\f\60\16\60\u0293\13\60"+
		"\3\60\3\60\3\61\3\61\3\61\3\61\7\61\u029b\n\61\f\61\16\61\u029e\13\61"+
		"\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\7\62\u02ab\n\62"+
		"\f\62\16\62\u02ae\13\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3"+
		"\63\3\63\3\63\3\63\3\63\3\63\3\63\7\63\u02c0\n\63\f\63\16\63\u02c3\13"+
		"\63\3\64\3\64\3\64\3\64\3\64\3\64\7\64\u02cb\n\64\f\64\16\64\u02ce\13"+
		"\64\3\65\3\65\7\65\u02d2\n\65\f\65\16\65\u02d5\13\65\3\65\3\65\3\66\3"+
		"\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\5\66\u02e2\n\66\3\66\3\66\5\66"+
		"\u02e6\n\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66"+
		"\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\5\66\u02fd\n\66\3\66\3\66"+
		"\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\7\66"+
		"\u030e\n\66\f\66\16\66\u0311\13\66\3\67\3\67\3\67\7\67\u0316\n\67\f\67"+
		"\16\67\u0319\13\67\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38"+
		"\38\38\38\38\38\38\38\38\78\u0334\n8\f8\168\u0337\138\38\38\38\38\38\3"+
		"8\38\38\38\38\38\38\38\38\38\38\38\38\38\38\58\u034d\n8\38\38\38\38\3"+
		"8\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\3"+
		"8\38\38\38\38\38\38\38\38\38\38\58\u0375\n8\39\39\39\39\39\39\39\39\3"+
		"9\39\39\39\39\39\39\39\39\39\39\39\39\39\39\39\39\39\39\39\59\u0393\n"+
		"9\3:\3:\3:\3:\3:\3:\3;\3;\3;\3;\6;\u039f\n;\r;\16;\u03a0\3<\3<\3<\3<\5"+
		"<\u03a7\n<\3=\3=\3=\3=\3=\3=\3>\3>\3>\3>\3>\3>\5>\u03b5\n>\3>\3>\3>\3"+
		">\3>\7>\u03bc\n>\f>\16>\u03bf\13>\3>\3>\5>\u03c3\n>\3>\3>\3?\3?\3@\3@"+
		"\3@\3@\3@\3@\5@\u03cf\n@\3@\3@\3A\3A\3A\3A\3A\3A\3A\5A\u03da\nA\3B\3B"+
		"\3C\3C\5C\u03e0\nC\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D"+
		"\7D\u03f3\nD\fD\16D\u03f6\13D\5D\u03f8\nD\3D\3D\3E\3E\3E\5E\u03ff\nE\3"+
		"F\3F\3F\3F\6F\u0405\nF\rF\16F\u0406\3G\3G\3H\3H\3I\3I\5I\u040f\nI\3J\6"+
		"J\u0412\nJ\rJ\16J\u0413\3J\5J\u0417\nJ\3J\3J\5J\u041b\nJ\3J\3J\3K\7K\u0420"+
		"\nK\fK\16K\u0423\13K\3K\5K\u0426\nK\3K\3K\3K\3L\3L\3M\7M\u042e\nM\fM\16"+
		"M\u0431\13M\3N\6N\u0434\nN\rN\16N\u0435\3O\3O\3P\3P\3Q\3Q\3R\3R\3R\2\2"+
		"S\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDF"+
		"HJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c"+
		"\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2\2\16"+
		"\3\2UV\3\2WX\3\2\20\21\4\2\13\13\35\35\3\28:\4\2\34\34??\3\2!\"\4\299"+
		"<<\5\2;;Aloo\4\2\30\30#&\4\2\31\31\'*\4\2\21\21+\65\u0477\2\u00a4\3\2"+
		"\2\2\4\u00ad\3\2\2\2\6\u00af\3\2\2\2\b\u00b1\3\2\2\2\n\u00ba\3\2\2\2\f"+
		"\u00cd\3\2\2\2\16\u00d8\3\2\2\2\20\u00dc\3\2\2\2\22\u00e6\3\2\2\2\24\u00ee"+
		"\3\2\2\2\26\u0105\3\2\2\2\30\u0107\3\2\2\2\32\u010b\3\2\2\2\34\u0123\3"+
		"\2\2\2\36\u0139\3\2\2\2 \u013b\3\2\2\2\"\u014c\3\2\2\2$\u015b\3\2\2\2"+
		"&\u0167\3\2\2\2(\u0169\3\2\2\2*\u018f\3\2\2\2,\u0198\3\2\2\2.\u019a\3"+
		"\2\2\2\60\u01a8\3\2\2\2\62\u01ac\3\2\2\2\64\u01b7\3\2\2\2\66\u01b9\3\2"+
		"\2\28\u01bd\3\2\2\2:\u01cf\3\2\2\2<\u01d1\3\2\2\2>\u01e0\3\2\2\2@\u01e2"+
		"\3\2\2\2B\u01f5\3\2\2\2D\u0221\3\2\2\2F\u0223\3\2\2\2H\u0237\3\2\2\2J"+
		"\u0239\3\2\2\2L\u0248\3\2\2\2N\u0251\3\2\2\2P\u0254\3\2\2\2R\u0264\3\2"+
		"\2\2T\u0266\3\2\2\2V\u0268\3\2\2\2X\u026a\3\2\2\2Z\u0275\3\2\2\2\\\u0280"+
		"\3\2\2\2^\u0291\3\2\2\2`\u0296\3\2\2\2b\u029f\3\2\2\2d\u02af\3\2\2\2f"+
		"\u02c4\3\2\2\2h\u02d3\3\2\2\2j\u02d8\3\2\2\2l\u0312\3\2\2\2n\u0374\3\2"+
		"\2\2p\u0392\3\2\2\2r\u0394\3\2\2\2t\u039a\3\2\2\2v\u03a2\3\2\2\2x\u03a8"+
		"\3\2\2\2z\u03ae\3\2\2\2|\u03c6\3\2\2\2~\u03c8\3\2\2\2\u0080\u03d2\3\2"+
		"\2\2\u0082\u03db\3\2\2\2\u0084\u03df\3\2\2\2\u0086\u03e1\3\2\2\2\u0088"+
		"\u03fb\3\2\2\2\u008a\u0400\3\2\2\2\u008c\u0408\3\2\2\2\u008e\u040a\3\2"+
		"\2\2\u0090\u040e\3\2\2\2\u0092\u0416\3\2\2\2\u0094\u0425\3\2\2\2\u0096"+
		"\u042a\3\2\2\2\u0098\u042f\3\2\2\2\u009a\u0433\3\2\2\2\u009c\u0437\3\2"+
		"\2\2\u009e\u0439\3\2\2\2\u00a0\u043b\3\2\2\2\u00a2\u043d\3\2\2\2\u00a4"+
		"\u00a5\5\u0098M\2\u00a5\u00a9\5\4\3\2\u00a6\u00a7\5\u0098M\2\u00a7\u00a8"+
		"\7\3\2\2\u00a8\u00aa\3\2\2\2\u00a9\u00a6\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa"+
		"\u00ab\3\2\2\2\u00ab\u00ac\5\u0098M\2\u00ac\3\3\2\2\2\u00ad\u00ae\5\6"+
		"\4\2\u00ae\5\3\2\2\2\u00af\u00b0\5\b\5\2\u00b0\7\3\2\2\2\u00b1\u00b7\5"+
		"\n\6\2\u00b2\u00b3\5\u0098M\2\u00b3\u00b4\5\f\7\2\u00b4\u00b6\3\2\2\2"+
		"\u00b5\u00b2\3\2\2\2\u00b6\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8"+
		"\3\2\2\2\u00b8\t\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00c0\5\16\b\2\u00bb"+
		"\u00bc\5\u0098M\2\u00bc\u00bd\5\16\b\2\u00bd\u00bf\3\2\2\2\u00be\u00bb"+
		"\3\2\2\2\u00bf\u00c2\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1"+
		"\13\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c3\u00c4\7A\2\2\u00c4\u00c5\5\u009a"+
		"N\2\u00c5\u00c6\7B\2\2\u00c6\u00c7\5\u0098M\2\u00c7\u00c8\5\n\6\2\u00c8"+
		"\u00ce\3\2\2\2\u00c9\u00ca\7A\2\2\u00ca\u00cb\5\u0098M\2\u00cb\u00cc\5"+
		"\n\6\2\u00cc\u00ce\3\2\2\2\u00cd\u00c3\3\2\2\2\u00cd\u00c9\3\2\2\2\u00ce"+
		"\r\3\2\2\2\u00cf\u00d9\5\20\t\2\u00d0\u00d9\5\22\n\2\u00d1\u00d9\5\24"+
		"\13\2\u00d2\u00d9\5\30\r\2\u00d3\u00d9\5\32\16\2\u00d4\u00d9\5\36\20\2"+
		"\u00d5\u00d9\5 \21\2\u00d6\u00d9\5$\23\2\u00d7\u00d9\5&\24\2\u00d8\u00cf"+
		"\3\2\2\2\u00d8\u00d0\3\2\2\2\u00d8\u00d1\3\2\2\2\u00d8\u00d2\3\2\2\2\u00d8"+
		"\u00d3\3\2\2\2\u00d8\u00d4\3\2\2\2\u00d8\u00d5\3\2\2\2\u00d8\u00d6\3\2"+
		"\2\2\u00d8\u00d7\3\2\2\2\u00d9\17\3\2\2\2\u00da\u00db\7C\2\2\u00db\u00dd"+
		"\5\u009aN\2\u00dc\u00da\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00de\3\2\2"+
		"\2\u00de\u00df\7D\2\2\u00df\u00e0\5\u0098M\2\u00e0\u00e4\58\35\2\u00e1"+
		"\u00e2\5\u0098M\2\u00e2\u00e3\5\66\34\2\u00e3\u00e5\3\2\2\2\u00e4\u00e1"+
		"\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\21\3\2\2\2\u00e6\u00e7\7E\2\2\u00e7"+
		"\u00e8\5\u0098M\2\u00e8\u00e9\5V,\2\u00e9\u00ea\5\u009aN\2\u00ea\u00eb"+
		"\7F\2\2\u00eb\u00ec\5\u009aN\2\u00ec\u00ed\5\u0082B\2\u00ed\23\3\2\2\2"+
		"\u00ee\u00ef\7G\2\2\u00ef\u00f0\5\u0098M\2\u00f0\u00f6\5:\36\2\u00f1\u00f2"+
		"\5\u009aN\2\u00f2\u00f3\5\26\f\2\u00f3\u00f5\3\2\2\2\u00f4\u00f1\3\2\2"+
		"\2\u00f5\u00f8\3\2\2\2\u00f6\u00f4\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\25"+
		"\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f9\u00fa\7H\2\2\u00fa\u00fb\5\u009aN\2"+
		"\u00fb\u00fc\7D\2\2\u00fc\u00fd\5\u009aN\2\u00fd\u00fe\5\32\16\2\u00fe"+
		"\u0106\3\2\2\2\u00ff\u0100\7H\2\2\u0100\u0101\5\u009aN\2\u0101\u0102\7"+
		"I\2\2\u0102\u0103\5\u009aN\2\u0103\u0104\5\32\16\2\u0104\u0106\3\2\2\2"+
		"\u0105\u00f9\3\2\2\2\u0105\u00ff\3\2\2\2\u0106\27\3\2\2\2\u0107\u0108"+
		"\7I\2\2\u0108\u0109\5\u0098M\2\u0109\u010a\58\35\2\u010a\31\3\2\2\2\u010b"+
		"\u010c\7J\2\2\u010c\u0111\5\34\17\2\u010d\u010e\7\4\2\2\u010e\u0110\5"+
		"\34\17\2\u010f\u010d\3\2\2\2\u0110\u0113\3\2\2\2\u0111\u010f\3\2\2\2\u0111"+
		"\u0112\3\2\2\2\u0112\33\3\2\2\2\u0113\u0111\3\2\2\2\u0114\u0115\5\u008a"+
		"F\2\u0115\u0116\7\5\2\2\u0116\u0117\5V,\2\u0117\u0124\3\2\2\2\u0118\u0119"+
		"\5\u0082B\2\u0119\u011a\7\5\2\2\u011a\u011b\5V,\2\u011b\u0124\3\2\2\2"+
		"\u011c\u011d\5\u0082B\2\u011d\u011e\7\6\2\2\u011e\u011f\5V,\2\u011f\u0124"+
		"\3\2\2\2\u0120\u0121\5\u0082B\2\u0121\u0122\5L\'\2\u0122\u0124\3\2\2\2"+
		"\u0123\u0114\3\2\2\2\u0123\u0118\3\2\2\2\u0123\u011c\3\2\2\2\u0123\u0120"+
		"\3\2\2\2\u0124\35\3\2\2\2\u0125\u0126\7K\2\2\u0126\u012b\5V,\2\u0127\u0128"+
		"\7\4\2\2\u0128\u012a\5V,\2\u0129\u0127\3\2\2\2\u012a\u012d\3\2\2\2\u012b"+
		"\u0129\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u013a\3\2\2\2\u012d\u012b\3\2"+
		"\2\2\u012e\u012f\7L\2\2\u012f\u0130\5\u009aN\2\u0130\u0131\7K\2\2\u0131"+
		"\u0136\5V,\2\u0132\u0133\7\4\2\2\u0133\u0135\5V,\2\u0134\u0132\3\2\2\2"+
		"\u0135\u0138\3\2\2\2\u0136\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u013a"+
		"\3\2\2\2\u0138\u0136\3\2\2\2\u0139\u0125\3\2\2\2\u0139\u012e\3\2\2\2\u013a"+
		"\37\3\2\2\2\u013b\u013c\7M\2\2\u013c\u013d\5\u009aN\2\u013d\u0145\5\""+
		"\22\2\u013e\u013f\5\u0098M\2\u013f\u0140\7\4\2\2\u0140\u0141\5\u0098M"+
		"\2\u0141\u0142\5\"\22\2\u0142\u0144\3\2\2\2\u0143\u013e\3\2\2\2\u0144"+
		"\u0147\3\2\2\2\u0145\u0143\3\2\2\2\u0145\u0146\3\2\2\2\u0146!\3\2\2\2"+
		"\u0147\u0145\3\2\2\2\u0148\u0149\5\u0082B\2\u0149\u014a\5L\'\2\u014a\u014d"+
		"\3\2\2\2\u014b\u014d\5\u008aF\2\u014c\u0148\3\2\2\2\u014c\u014b\3\2\2"+
		"\2\u014d#\3\2\2\2\u014e\u014f\7N\2\2\u014f\u0150\7O\2\2\u0150\u0151\5"+
		"\u009aN\2\u0151\u0153\5(\25\2\u0152\u0154\5\66\34\2\u0153\u0152\3\2\2"+
		"\2\u0153\u0154\3\2\2\2\u0154\u015c\3\2\2\2\u0155\u0156\7N\2\2\u0156\u0157"+
		"\5\u009aN\2\u0157\u0159\5(\25\2\u0158\u015a\5\66\34\2\u0159\u0158\3\2"+
		"\2\2\u0159\u015a\3\2\2\2\u015a\u015c\3\2\2\2\u015b\u014e\3\2\2\2\u015b"+
		"\u0155\3\2\2\2\u015c%\3\2\2\2\u015d\u015e\7P\2\2\u015e\u015f\5\u009aN"+
		"\2\u015f\u0160\7O\2\2\u0160\u0161\5\u009aN\2\u0161\u0162\5(\25\2\u0162"+
		"\u0168\3\2\2\2\u0163\u0164\7P\2\2\u0164\u0165\5\u009aN\2\u0165\u0166\5"+
		"(\25\2\u0166\u0168\3\2\2\2\u0167\u015d\3\2\2\2\u0167\u0163\3\2\2\2\u0168"+
		"\'\3\2\2\2\u0169\u016d\5*\26\2\u016a\u016b\5\u009aN\2\u016b\u016c\5.\30"+
		"\2\u016c\u016e\3\2\2\2\u016d\u016a\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u0172"+
		"\3\2\2\2\u016f\u0170\5\u009aN\2\u0170\u0171\5\60\31\2\u0171\u0173\3\2"+
		"\2\2\u0172\u016f\3\2\2\2\u0172\u0173\3\2\2\2\u0173\u0177\3\2\2\2\u0174"+
		"\u0175\5\u009aN\2\u0175\u0176\5\62\32\2\u0176\u0178\3\2\2\2\u0177\u0174"+
		"\3\2\2\2\u0177\u0178\3\2\2\2\u0178)\3\2\2\2\u0179\u0181\7\7\2\2\u017a"+
		"\u017b\5\u0098M\2\u017b\u017c\7\4\2\2\u017c\u017d\5\u0098M\2\u017d\u017e"+
		"\5,\27\2\u017e\u0180\3\2\2\2\u017f\u017a\3\2\2\2\u0180\u0183\3\2\2\2\u0181"+
		"\u017f\3\2\2\2\u0181\u0182\3\2\2\2\u0182\u0190\3\2\2\2\u0183\u0181\3\2"+
		"\2\2\u0184\u018c\5,\27\2\u0185\u0186\5\u0098M\2\u0186\u0187\7\4\2\2\u0187"+
		"\u0188\5\u0098M\2\u0188\u0189\5,\27\2\u0189\u018b\3\2\2\2\u018a\u0185"+
		"\3\2\2\2\u018b\u018e\3\2\2\2\u018c\u018a\3\2\2\2\u018c\u018d\3\2\2\2\u018d"+
		"\u0190\3\2\2\2\u018e\u018c\3\2\2\2\u018f\u0179\3\2\2\2\u018f\u0184\3\2"+
		"\2\2\u0190+\3\2\2\2\u0191\u0192\5V,\2\u0192\u0193\5\u009aN\2\u0193\u0194"+
		"\7F\2\2\u0194\u0195\5\u009aN\2\u0195\u0196\5\u0082B\2\u0196\u0199\3\2"+
		"\2\2\u0197\u0199\5V,\2\u0198\u0191\3\2\2\2\u0198\u0197\3\2\2\2\u0199-"+
		"\3\2\2\2\u019a\u019b\7Q\2\2\u019b\u019c\5\u009aN\2\u019c\u019d\7R\2\2"+
		"\u019d\u019e\5\u009aN\2\u019e\u01a5\5\64\33\2\u019f\u01a0\7\4\2\2\u01a0"+
		"\u01a1\5\u0098M\2\u01a1\u01a2\5\64\33\2\u01a2\u01a4\3\2\2\2\u01a3\u019f"+
		"\3\2\2\2\u01a4\u01a7\3\2\2\2\u01a5\u01a3\3\2\2\2\u01a5\u01a6\3\2\2\2\u01a6"+
		"/\3\2\2\2\u01a7\u01a5\3\2\2\2\u01a8\u01a9\7S\2\2\u01a9\u01aa\5\u009aN"+
		"\2\u01aa\u01ab\5V,\2\u01ab\61\3\2\2\2\u01ac\u01ad\7T\2\2\u01ad\u01ae\5"+
		"\u009aN\2\u01ae\u01af\5V,\2\u01af\63\3\2\2\2\u01b0\u01b1\5V,\2\u01b1\u01b2"+
		"\t\2\2\2\u01b2\u01b8\3\2\2\2\u01b3\u01b5\5V,\2\u01b4\u01b6\t\3\2\2\u01b5"+
		"\u01b4\3\2\2\2\u01b5\u01b6\3\2\2\2\u01b6\u01b8\3\2\2\2\u01b7\u01b0\3\2"+
		"\2\2\u01b7\u01b3\3\2\2\2\u01b8\65\3\2\2\2\u01b9\u01ba\7Y\2\2\u01ba\u01bb"+
		"\5\u009aN\2\u01bb\u01bc\5V,\2\u01bc\67\3\2\2\2\u01bd\u01c5\5:\36\2\u01be"+
		"\u01bf\5\u0098M\2\u01bf\u01c0\7\4\2\2\u01c0\u01c1\5\u0098M\2\u01c1\u01c2"+
		"\5:\36\2\u01c2\u01c4\3\2\2\2\u01c3\u01be\3\2\2\2\u01c4\u01c7\3\2\2\2\u01c5"+
		"\u01c3\3\2\2\2\u01c5\u01c6\3\2\2\2\u01c69\3\2\2\2\u01c7\u01c5\3\2\2\2"+
		"\u01c8\u01c9\5\u0082B\2\u01c9\u01ca\5\u0098M\2\u01ca\u01cb\7\5\2\2\u01cb"+
		"\u01cc\5\u0098M\2\u01cc\u01cd\5<\37\2\u01cd\u01d0\3\2\2\2\u01ce\u01d0"+
		"\5<\37\2\u01cf\u01c8\3\2\2\2\u01cf\u01ce\3\2\2\2\u01d0;\3\2\2\2\u01d1"+
		"\u01d2\5> \2\u01d2=\3\2\2\2\u01d3\u01d9\5@!\2\u01d4\u01d5\5\u0098M\2\u01d5"+
		"\u01d6\5B\"\2\u01d6\u01d8\3\2\2\2\u01d7\u01d4\3\2\2\2\u01d8\u01db\3\2"+
		"\2\2\u01d9\u01d7\3\2\2\2\u01d9\u01da\3\2\2\2\u01da\u01e1\3\2\2\2\u01db"+
		"\u01d9\3\2\2\2\u01dc\u01dd\7\b\2\2\u01dd\u01de\5> \2\u01de\u01df\7\t\2"+
		"\2\u01df\u01e1\3\2\2\2\u01e0\u01d3\3\2\2\2\u01e0\u01dc\3\2\2\2\u01e1?"+
		"\3\2\2\2\u01e2\u01e3\7\b\2\2\u01e3\u01e7\5\u0098M\2\u01e4\u01e5\5\u0082"+
		"B\2\u01e5\u01e6\5\u0098M\2\u01e6\u01e8\3\2\2\2\u01e7\u01e4\3\2\2\2\u01e7"+
		"\u01e8\3\2\2\2\u01e8\u01ec\3\2\2\2\u01e9\u01ea\5L\'\2\u01ea\u01eb\5\u0098"+
		"M\2\u01eb\u01ed\3\2\2\2\u01ec\u01e9\3\2\2\2\u01ec\u01ed\3\2\2\2\u01ed"+
		"\u01f1\3\2\2\2\u01ee\u01ef\5H%\2\u01ef\u01f0\5\u0098M\2\u01f0\u01f2\3"+
		"\2\2\2\u01f1\u01ee\3\2\2\2\u01f1\u01f2\3\2\2\2\u01f2\u01f3\3\2\2\2\u01f3"+
		"\u01f4\7\t\2\2\u01f4A\3\2\2\2\u01f5\u01f6\5D#\2\u01f6\u01f7\5\u0098M\2"+
		"\u01f7\u01f8\5@!\2\u01f8C\3\2\2\2\u01f9\u01fa\5\u009cO\2\u01fa\u01fb\5"+
		"\u0098M\2\u01fb\u01fc\5\u00a0Q\2\u01fc\u01fe\5\u0098M\2\u01fd\u01ff\5"+
		"F$\2\u01fe\u01fd\3\2\2\2\u01fe\u01ff\3\2\2\2\u01ff\u0200\3\2\2\2\u0200"+
		"\u0201\5\u0098M\2\u0201\u0202\5\u00a0Q\2\u0202\u0203\5\u0098M\2\u0203"+
		"\u0204\5\u009eP\2\u0204\u0222\3\2\2\2\u0205\u0206\5\u009cO\2\u0206\u0207"+
		"\5\u0098M\2\u0207\u0208\5\u00a0Q\2\u0208\u020a\5\u0098M\2\u0209\u020b"+
		"\5F$\2\u020a\u0209\3\2\2\2\u020a\u020b\3\2\2\2\u020b\u020c\3\2\2\2\u020c"+
		"\u020d\5\u0098M\2\u020d\u020e\5\u00a0Q\2\u020e\u0222\3\2\2\2\u020f\u0210"+
		"\5\u00a0Q\2\u0210\u0212\5\u0098M\2\u0211\u0213\5F$\2\u0212\u0211\3\2\2"+
		"\2\u0212\u0213\3\2\2\2\u0213\u0214\3\2\2\2\u0214\u0215\5\u0098M\2\u0215"+
		"\u0216\5\u00a0Q\2\u0216\u0217\5\u0098M\2\u0217\u0218\5\u009eP\2\u0218"+
		"\u0222\3\2\2\2\u0219\u021a\5\u00a0Q\2\u021a\u021c\5\u0098M\2\u021b\u021d"+
		"\5F$\2\u021c\u021b\3\2\2\2\u021c\u021d\3\2\2\2\u021d\u021e\3\2\2\2\u021e"+
		"\u021f\5\u0098M\2\u021f\u0220\5\u00a0Q\2\u0220\u0222\3\2\2\2\u0221\u01f9"+
		"\3\2\2\2\u0221\u0205\3\2\2\2\u0221\u020f\3\2\2\2\u0221\u0219\3\2\2\2\u0222"+
		"E\3\2\2\2\u0223\u0225\7\n\2\2\u0224\u0226\5\u0082B\2\u0225\u0224\3\2\2"+
		"\2\u0225\u0226\3\2\2\2\u0226\u0228\3\2\2\2\u0227\u0229\7\13\2\2\u0228"+
		"\u0227\3\2\2\2\u0228\u0229\3\2\2\2\u0229\u022b\3\2\2\2\u022a\u022c\5J"+
		"&\2\u022b\u022a\3\2\2\2\u022b\u022c\3\2\2\2\u022c\u022e\3\2\2\2\u022d"+
		"\u022f\5P)\2\u022e\u022d\3\2\2\2\u022e\u022f\3\2\2\2\u022f\u0231\3\2\2"+
		"\2\u0230\u0232\5H%\2\u0231\u0230\3\2\2\2\u0231\u0232\3\2\2\2\u0232\u0233"+
		"\3\2\2\2\u0233\u0234\7\f\2\2\u0234G\3\2\2\2\u0235\u0238\5\u0086D\2\u0236"+
		"\u0238\5\u0088E\2\u0237\u0235\3\2\2\2\u0237\u0236\3\2\2\2\u0238I\3\2\2"+
		"\2\u0239\u023a\7\r\2\2\u023a\u0245\5T+\2\u023b\u023c\5\u0098M\2\u023c"+
		"\u023e\7\16\2\2\u023d\u023f\7\r\2\2\u023e\u023d\3\2\2\2\u023e\u023f\3"+
		"\2\2\2\u023f\u0240\3\2\2\2\u0240\u0241\5\u0098M\2\u0241\u0242\5T+\2\u0242"+
		"\u0244\3\2\2\2\u0243\u023b\3\2\2\2\u0244\u0247\3\2\2\2\u0245\u0243\3\2"+
		"\2\2\u0245\u0246\3\2\2\2\u0246K\3\2\2\2\u0247\u0245\3\2\2\2\u0248\u024e"+
		"\5N(\2\u0249\u024a\5\u0098M\2\u024a\u024b\5N(\2\u024b\u024d\3\2\2\2\u024c"+
		"\u0249\3\2\2\2\u024d\u0250\3\2\2\2\u024e\u024c\3\2\2\2\u024e\u024f\3\2"+
		"\2\2\u024fM\3\2\2\2\u0250\u024e\3\2\2\2\u0251\u0252\7\r\2\2\u0252\u0253"+
		"\5R*\2\u0253O\3\2\2\2\u0254\u0255\7\7\2\2\u0255\u0259\5\u0098M\2\u0256"+
		"\u0257\5\u008eH\2\u0257\u0258\5\u0098M\2\u0258\u025a\3\2\2\2\u0259\u0256"+
		"\3\2\2\2\u0259\u025a\3\2\2\2\u025a\u0262\3\2\2\2\u025b\u025c\7\17\2\2"+
		"\u025c\u0260\5\u0098M\2\u025d\u025e\5\u008eH\2\u025e\u025f\5\u0098M\2"+
		"\u025f\u0261\3\2\2\2\u0260\u025d\3\2\2\2\u0260\u0261\3\2\2\2\u0261\u0263"+
		"\3\2\2\2\u0262\u025b\3\2\2\2\u0262\u0263\3\2\2\2\u0263Q\3\2\2\2\u0264"+
		"\u0265\5\u0096L\2\u0265S\3\2\2\2\u0266\u0267\5\u0096L\2\u0267U\3\2\2\2"+
		"\u0268\u0269\5X-\2\u0269W\3\2\2\2\u026a\u0272\5Z.\2\u026b\u026c\5\u009a"+
		"N\2\u026c\u026d\7Z\2\2\u026d\u026e\5\u009aN\2\u026e\u026f\5Z.\2\u026f"+
		"\u0271\3\2\2\2\u0270\u026b\3\2\2\2\u0271\u0274\3\2\2\2\u0272\u0270\3\2"+
		"\2\2\u0272\u0273\3\2\2\2\u0273Y\3\2\2\2\u0274\u0272\3\2\2\2\u0275\u027d"+
		"\5\\/\2\u0276\u0277\5\u009aN\2\u0277\u0278\7[\2\2\u0278\u0279\5\u009a"+
		"N\2\u0279\u027a\5\\/\2\u027a\u027c\3\2\2\2\u027b\u0276\3\2\2\2\u027c\u027f"+
		"\3\2\2\2\u027d\u027b\3\2\2\2\u027d\u027e\3\2\2\2\u027e[\3\2\2\2\u027f"+
		"\u027d\3\2\2\2\u0280\u0288\5^\60\2\u0281\u0282\5\u009aN\2\u0282\u0283"+
		"\7\\\2\2\u0283\u0284\5\u009aN\2\u0284\u0285\5^\60\2\u0285\u0287\3\2\2"+
		"\2\u0286\u0281\3\2\2\2\u0287\u028a\3\2\2\2\u0288\u0286\3\2\2\2\u0288\u0289"+
		"\3\2\2\2\u0289]\3\2\2\2\u028a\u0288\3\2\2\2\u028b\u028c\5\u009aN\2\u028c"+
		"\u028d\7]\2\2\u028d\u028e\5\u009aN\2\u028e\u0290\3\2\2\2\u028f\u028b\3"+
		"\2\2\2\u0290\u0293\3\2\2\2\u0291\u028f\3\2\2\2\u0291\u0292\3\2\2\2\u0292"+
		"\u0294\3\2\2\2\u0293\u0291\3\2\2\2\u0294\u0295\5`\61\2\u0295_\3\2\2\2"+
		"\u0296\u029c\5b\62\2\u0297\u0298\5\u0098M\2\u0298\u0299\5p9\2\u0299\u029b"+
		"\3\2\2\2\u029a\u0297\3\2\2\2\u029b\u029e\3\2\2\2\u029c\u029a\3\2\2\2\u029c"+
		"\u029d\3\2\2\2\u029da\3\2\2\2\u029e\u029c\3\2\2\2\u029f\u02ac\5d\63\2"+
		"\u02a0\u02a1\5\u0098M\2\u02a1\u02a2\7\20\2\2\u02a2\u02a3\5\u0098M\2\u02a3"+
		"\u02a4\5d\63\2\u02a4\u02ab\3\2\2\2\u02a5\u02a6\5\u0098M\2\u02a6\u02a7"+
		"\7\21\2\2\u02a7\u02a8\5\u0098M\2\u02a8\u02a9\5d\63\2\u02a9\u02ab\3\2\2"+
		"\2\u02aa\u02a0\3\2\2\2\u02aa\u02a5\3\2\2\2\u02ab\u02ae\3\2\2\2\u02ac\u02aa"+
		"\3\2\2\2\u02ac\u02ad\3\2\2\2\u02adc\3\2\2\2\u02ae\u02ac\3\2\2\2\u02af"+
		"\u02c1\5f\64\2\u02b0\u02b1\5\u0098M\2\u02b1\u02b2\7\7\2\2\u02b2\u02b3"+
		"\5\u0098M\2\u02b3\u02b4\5f\64\2\u02b4\u02c0\3\2\2\2\u02b5\u02b6\5\u0098"+
		"M\2\u02b6\u02b7\7\22\2\2\u02b7\u02b8\5\u0098M\2\u02b8\u02b9\5f\64\2\u02b9"+
		"\u02c0\3\2\2\2\u02ba\u02bb\5\u0098M\2\u02bb\u02bc\7\23\2\2\u02bc\u02bd"+
		"\5\u0098M\2\u02bd\u02be\5f\64\2\u02be\u02c0\3\2\2\2\u02bf\u02b0\3\2\2"+
		"\2\u02bf\u02b5\3\2\2\2\u02bf\u02ba\3\2\2\2\u02c0\u02c3\3\2\2\2\u02c1\u02bf"+
		"\3\2\2\2\u02c1\u02c2\3\2\2\2\u02c2e\3\2\2\2\u02c3\u02c1\3\2\2\2\u02c4"+
		"\u02cc\5h\65\2\u02c5\u02c6\5\u0098M\2\u02c6\u02c7\7\24\2\2\u02c7\u02c8"+
		"\5\u0098M\2\u02c8\u02c9\5h\65\2\u02c9\u02cb\3\2\2\2\u02ca\u02c5\3\2\2"+
		"\2\u02cb\u02ce\3\2\2\2\u02cc\u02ca\3\2\2\2\u02cc\u02cd\3\2\2\2\u02cdg"+
		"\3\2\2\2\u02ce\u02cc\3\2\2\2\u02cf\u02d0\t\4\2\2\u02d0\u02d2\5\u0098M"+
		"\2\u02d1\u02cf\3\2\2\2\u02d2\u02d5\3\2\2\2\u02d3\u02d1\3\2\2\2\u02d3\u02d4"+
		"\3\2\2\2\u02d4\u02d6\3\2\2\2\u02d5\u02d3\3\2\2\2\u02d6\u02d7\5j\66\2\u02d7"+
		"i\3\2\2\2\u02d8\u030f\5l\67\2\u02d9\u02da\5\u0098M\2\u02da\u02db\7\n\2"+
		"\2\u02db\u02dc\5V,\2\u02dc\u02dd\7\f\2\2\u02dd\u030e\3\2\2\2\u02de\u02df"+
		"\5\u0098M\2\u02df\u02e1\7\n\2\2\u02e0\u02e2\5V,\2\u02e1\u02e0\3\2\2\2"+
		"\u02e1\u02e2\3\2\2\2\u02e2\u02e3\3\2\2\2\u02e3\u02e5\7\17\2\2\u02e4\u02e6"+
		"\5V,\2\u02e5\u02e4\3\2\2\2\u02e5\u02e6\3\2\2\2\u02e6\u02e7\3\2\2\2\u02e7"+
		"\u02e8\7\f\2\2\u02e8\u030e\3\2\2\2\u02e9\u02ea\5\u0098M\2\u02ea\u02eb"+
		"\7\25\2\2\u02eb\u02fd\3\2\2\2\u02ec\u02ed\5\u009aN\2\u02ed\u02ee\7^\2"+
		"\2\u02ee\u02fd\3\2\2\2\u02ef\u02f0\5\u009aN\2\u02f0\u02f1\7_\2\2\u02f1"+
		"\u02f2\5\u009aN\2\u02f2\u02f3\7N\2\2\u02f3\u02fd\3\2\2\2\u02f4\u02f5\5"+
		"\u009aN\2\u02f5\u02f6\7`\2\2\u02f6\u02f7\5\u009aN\2\u02f7\u02f8\7N\2\2"+
		"\u02f8\u02fd\3\2\2\2\u02f9\u02fa\5\u009aN\2\u02fa\u02fb\7a\2\2\u02fb\u02fd"+
		"\3\2\2\2\u02fc\u02e9\3\2\2\2\u02fc\u02ec\3\2\2\2\u02fc\u02ef\3\2\2\2\u02fc"+
		"\u02f4\3\2\2\2\u02fc\u02f9\3\2\2\2\u02fd\u02fe\3\2\2\2\u02fe\u02ff\5\u0098"+
		"M\2\u02ff\u0300\5l\67\2\u0300\u030e\3\2\2\2\u0301\u0302\5\u009aN\2\u0302"+
		"\u0303\7b\2\2\u0303\u0304\5\u009aN\2\u0304\u0305\7c\2\2\u0305\u030e\3"+
		"\2\2\2\u0306\u0307\5\u009aN\2\u0307\u0308\7b\2\2\u0308\u0309\5\u009aN"+
		"\2\u0309\u030a\7]\2\2\u030a\u030b\5\u009aN\2\u030b\u030c\7c\2\2\u030c"+
		"\u030e\3\2\2\2\u030d\u02d9\3\2\2\2\u030d\u02de\3\2\2\2\u030d\u02fc\3\2"+
		"\2\2\u030d\u0301\3\2\2\2\u030d\u0306\3\2\2\2\u030e\u0311\3\2\2\2\u030f"+
		"\u030d\3\2\2\2\u030f\u0310\3\2\2\2\u0310k\3\2\2\2\u0311\u030f\3\2\2\2"+
		"\u0312\u0317\5n8\2\u0313\u0316\5\u0080A\2\u0314\u0316\5L\'\2\u0315\u0313"+
		"\3\2\2\2\u0315\u0314\3\2\2\2\u0316\u0319\3\2\2\2\u0317\u0315\3\2\2\2\u0317"+
		"\u0318\3\2\2\2\u0318m\3\2\2\2\u0319\u0317\3\2\2\2\u031a\u0375\5\u0084"+
		"C\2\u031b\u0375\7\66\2\2\u031c\u0375\5\u0088E\2\u031d\u0375\7d\2\2\u031e"+
		"\u0375\7e\2\2\u031f\u0375\7c\2\2\u0320\u0321\7f\2\2\u0321\u0322\5\u0098"+
		"M\2\u0322\u0323\7\b\2\2\u0323\u0324\5\u0098M\2\u0324\u0325\7\7\2\2\u0325"+
		"\u0326\5\u0098M\2\u0326\u0327\7\t\2\2\u0327\u0375\3\2\2\2\u0328\u0375"+
		"\5\u0086D\2\u0329\u0375\5~@\2\u032a\u032b\7\n\2\2\u032b\u032c\5\u0098"+
		"M\2\u032c\u032d\5V,\2\u032d\u0335\5\u0098M\2\u032e\u032f\7\4\2\2\u032f"+
		"\u0330\5\u0098M\2\u0330\u0331\5V,\2\u0331\u0332\5\u0098M\2\u0332\u0334"+
		"\3\2\2\2\u0333\u032e\3\2\2\2\u0334\u0337\3\2\2\2\u0335\u0333\3\2\2\2\u0335"+
		"\u0336\3\2\2\2\u0336\u0338\3\2\2\2\u0337\u0335\3\2\2\2\u0338\u0339\7\f"+
		"\2\2\u0339\u0375\3\2\2\2\u033a\u033b\7g\2\2\u033b\u033c\5\u0098M\2\u033c"+
		"\u033d\7\b\2\2\u033d\u033e\5\u0098M\2\u033e\u033f\5v<\2\u033f\u0340\5"+
		"\u0098M\2\u0340\u0341\7\t\2\2\u0341\u0375\3\2\2\2\u0342\u0343\7h\2\2\u0343"+
		"\u0344\5\u0098M\2\u0344\u0345\7\b\2\2\u0345\u0346\5\u0098M\2\u0346\u0347"+
		"\5v<\2\u0347\u034c\5\u0098M\2\u0348\u0349\5\u0098M\2\u0349\u034a\7\16"+
		"\2\2\u034a\u034b\5V,\2\u034b\u034d\3\2\2\2\u034c\u0348\3\2\2\2\u034c\u034d"+
		"\3\2\2\2\u034d\u034e\3\2\2\2\u034e\u034f\7\t\2\2\u034f\u0375\3\2\2\2\u0350"+
		"\u0351\7B\2\2\u0351\u0352\5\u0098M\2\u0352\u0353\7\b\2\2\u0353\u0354\5"+
		"\u0098M\2\u0354\u0355\5v<\2\u0355\u0356\5\u0098M\2\u0356\u0357\7\t\2\2"+
		"\u0357\u0375\3\2\2\2\u0358\u0359\7i\2\2\u0359\u035a\5\u0098M\2\u035a\u035b"+
		"\7\b\2\2\u035b\u035c\5\u0098M\2\u035c\u035d\5v<\2\u035d\u035e\5\u0098"+
		"M\2\u035e\u035f\7\t\2\2\u035f\u0375\3\2\2\2\u0360\u0361\7j\2\2\u0361\u0362"+
		"\5\u0098M\2\u0362\u0363\7\b\2\2\u0363\u0364\5\u0098M\2\u0364\u0365\5v"+
		"<\2\u0365\u0366\5\u0098M\2\u0366\u0367\7\t\2\2\u0367\u0375\3\2\2\2\u0368"+
		"\u0369\7k\2\2\u0369\u036a\5\u0098M\2\u036a\u036b\7\b\2\2\u036b\u036c\5"+
		"\u0098M\2\u036c\u036d\5v<\2\u036d\u036e\5\u0098M\2\u036e\u036f\7\t\2\2"+
		"\u036f\u0375\3\2\2\2\u0370\u0375\5t;\2\u0371\u0375\5r:\2\u0372\u0375\5"+
		"z>\2\u0373\u0375\5\u0082B\2\u0374\u031a\3\2\2\2\u0374\u031b\3\2\2\2\u0374"+
		"\u031c\3\2\2\2\u0374\u031d\3\2\2\2\u0374\u031e\3\2\2\2\u0374\u031f\3\2"+
		"\2\2\u0374\u0320\3\2\2\2\u0374\u0328\3\2\2\2\u0374\u0329\3\2\2\2\u0374"+
		"\u032a\3\2\2\2\u0374\u033a\3\2\2\2\u0374\u0342\3\2\2\2\u0374\u0350\3\2"+
		"\2\2\u0374\u0358\3\2\2\2\u0374\u0360\3\2\2\2\u0374\u0368\3\2\2\2\u0374"+
		"\u0370\3\2\2\2\u0374\u0371\3\2\2\2\u0374\u0372\3\2\2\2\u0374\u0373\3\2"+
		"\2\2\u0375o\3\2\2\2\u0376\u0377\7\5\2\2\u0377\u0378\5\u0098M\2\u0378\u0379"+
		"\5b\62\2\u0379\u0393\3\2\2\2\u037a\u037b\7\26\2\2\u037b\u037c\5\u0098"+
		"M\2\u037c\u037d\5b\62\2\u037d\u0393\3\2\2\2\u037e\u037f\7\27\2\2\u037f"+
		"\u0380\5\u0098M\2\u0380\u0381\5b\62\2\u0381\u0393\3\2\2\2\u0382\u0383"+
		"\7\30\2\2\u0383\u0384\5\u0098M\2\u0384\u0385\5b\62\2\u0385\u0393\3\2\2"+
		"\2\u0386\u0387\7\31\2\2\u0387\u0388\5\u0098M\2\u0388\u0389\5b\62\2\u0389"+
		"\u0393\3\2\2\2\u038a\u038b\7\32\2\2\u038b\u038c\5\u0098M\2\u038c\u038d"+
		"\5b\62\2\u038d\u0393\3\2\2\2\u038e\u038f\7\33\2\2\u038f\u0390\5\u0098"+
		"M\2\u0390\u0391\5b\62\2\u0391\u0393\3\2\2\2\u0392\u0376\3\2\2\2\u0392"+
		"\u037a\3\2\2\2\u0392\u037e\3\2\2\2\u0392\u0382\3\2\2\2\u0392\u0386\3\2"+
		"\2\2\u0392\u038a\3\2\2\2\u0392\u038e\3\2\2\2\u0393q\3\2\2\2\u0394\u0395"+
		"\7\b\2\2\u0395\u0396\5\u0098M\2\u0396\u0397\5V,\2\u0397\u0398\5\u0098"+
		"M\2\u0398\u0399\7\t\2\2\u0399s\3\2\2\2\u039a\u039e\5@!\2\u039b\u039c\5"+
		"\u0098M\2\u039c\u039d\5B\"\2\u039d\u039f\3\2\2\2\u039e\u039b\3\2\2\2\u039f"+
		"\u03a0\3\2\2\2\u03a0\u039e\3\2\2\2\u03a0\u03a1\3\2\2\2\u03a1u\3\2\2\2"+
		"\u03a2\u03a6\5x=\2\u03a3\u03a4\5\u0098M\2\u03a4\u03a5\5\66\34\2\u03a5"+
		"\u03a7\3\2\2\2\u03a6\u03a3\3\2\2\2\u03a6\u03a7\3\2\2\2\u03a7w\3\2\2\2"+
		"\u03a8\u03a9\5\u0082B\2\u03a9\u03aa\5\u009aN\2\u03aa\u03ab\7^\2\2\u03ab"+
		"\u03ac\5\u009aN\2\u03ac\u03ad\5V,\2\u03ady\3\2\2\2\u03ae\u03af\5|?\2\u03af"+
		"\u03b0\5\u0098M\2\u03b0\u03b1\7\b\2\2\u03b1\u03b4\5\u0098M\2\u03b2\u03b3"+
		"\7O\2\2\u03b3\u03b5\5\u0098M\2\u03b4\u03b2\3\2\2\2\u03b4\u03b5\3\2\2\2"+
		"\u03b5\u03c2\3\2\2\2\u03b6\u03bd\5V,\2\u03b7\u03b8\7\4\2\2\u03b8\u03b9"+
		"\5\u0098M\2\u03b9\u03ba\5V,\2\u03ba\u03bc\3\2\2\2\u03bb\u03b7\3\2\2\2"+
		"\u03bc\u03bf\3\2\2\2\u03bd\u03bb\3\2\2\2\u03bd\u03be\3\2\2\2\u03be\u03c0"+
		"\3\2\2\2\u03bf\u03bd\3\2\2\2\u03c0\u03c1\5\u0098M\2\u03c1\u03c3\3\2\2"+
		"\2\u03c2\u03b6\3\2\2\2\u03c2\u03c3\3\2\2\2\u03c3\u03c4\3\2\2\2\u03c4\u03c5"+
		"\7\t\2\2\u03c5{\3\2\2\2\u03c6\u03c7\5\u0096L\2\u03c7}\3\2\2\2\u03c8\u03c9"+
		"\7\n\2\2\u03c9\u03ce\5v<\2\u03ca\u03cb\5\u0098M\2\u03cb\u03cc\7\16\2\2"+
		"\u03cc\u03cd\5V,\2\u03cd\u03cf\3\2\2\2\u03ce\u03ca\3\2\2\2\u03ce\u03cf"+
		"\3\2\2\2\u03cf\u03d0\3\2\2\2\u03d0\u03d1\7\f\2\2\u03d1\177\3\2\2\2\u03d2"+
		"\u03d3\5\u0098M\2\u03d3\u03d4\7\34\2\2\u03d4\u03d9\5\u0098M\2\u03d5\u03d6"+
		"\5\u008cG\2\u03d6\u03d7\t\5\2\2\u03d7\u03da\3\2\2\2\u03d8\u03da\5\u008c"+
		"G\2\u03d9\u03d5\3\2\2\2\u03d9\u03d8\3\2\2\2\u03da\u0081\3\2\2\2\u03db"+
		"\u03dc\5\u0096L\2\u03dc\u0083\3\2\2\2\u03dd\u03e0\5\u0090I\2\u03de\u03e0"+
		"\5\u008eH\2\u03df\u03dd\3\2\2\2\u03df\u03de\3\2\2\2\u03e0\u0085\3\2\2"+
		"\2\u03e1\u03e2\7\36\2\2\u03e2\u03f7\5\u0098M\2\u03e3\u03e4\5\u008cG\2"+
		"\u03e4\u03e5\5\u0098M\2\u03e5\u03e6\7\r\2\2\u03e6\u03e7\5\u0098M\2\u03e7"+
		"\u03e8\5V,\2\u03e8\u03f4\5\u0098M\2\u03e9\u03ea\7\4\2\2\u03ea\u03eb\5"+
		"\u0098M\2\u03eb\u03ec\5\u008cG\2\u03ec\u03ed\5\u0098M\2\u03ed\u03ee\7"+
		"\r\2\2\u03ee\u03ef\5\u0098M\2\u03ef\u03f0\5V,\2\u03f0\u03f1\5\u0098M\2"+
		"\u03f1\u03f3\3\2\2\2\u03f2\u03e9\3\2\2\2\u03f3\u03f6\3\2\2\2\u03f4\u03f2"+
		"\3\2\2\2\u03f4\u03f5\3\2\2\2\u03f5\u03f8\3\2\2\2\u03f6\u03f4\3\2\2\2\u03f7"+
		"\u03e3\3\2\2\2\u03f7\u03f8\3\2\2\2\u03f8\u03f9\3\2\2\2\u03f9\u03fa\7\37"+
		"\2\2\u03fa\u0087\3\2\2\2\u03fb\u03fe\7 \2\2\u03fc\u03ff\5\u0096L\2\u03fd"+
		"\u03ff\79\2\2\u03fe\u03fc\3\2\2\2\u03fe\u03fd\3\2\2\2\u03ff\u0089\3\2"+
		"\2\2\u0400\u0404\5n8\2\u0401\u0402\5\u0098M\2\u0402\u0403\5\u0080A\2\u0403"+
		"\u0405\3\2\2\2\u0404\u0401\3\2\2\2\u0405\u0406\3\2\2\2\u0406\u0404\3\2"+
		"\2\2\u0406\u0407\3\2\2\2\u0407\u008b\3\2\2\2\u0408\u0409\5\u0096L\2\u0409"+
		"\u008d\3\2\2\2\u040a\u040b\t\6\2\2\u040b\u008f\3\2\2\2\u040c\u040f\5\u0092"+
		"J\2\u040d\u040f\5\u0094K\2\u040e\u040c\3\2\2\2\u040e\u040d\3\2\2\2\u040f"+
		"\u0091\3\2\2\2\u0410\u0412\t\7\2\2\u0411\u0410\3\2\2\2\u0412\u0413\3\2"+
		"\2\2\u0413\u0411\3\2\2\2\u0413\u0414\3\2\2\2\u0414\u0417\3\2\2\2\u0415"+
		"\u0417\79\2\2\u0416\u0411\3\2\2\2\u0416\u0415\3\2\2\2\u0417\u041a\3\2"+
		"\2\2\u0418\u041b\t\b\2\2\u0419\u041b\t\b\2\2\u041a\u0418\3\2\2\2\u041a"+
		"\u0419\3\2\2\2\u041b\u041c\3\2\2\2\u041c\u041d\t\t\2\2\u041d\u0093\3\2"+
		"\2\2\u041e\u0420\7?\2\2\u041f\u041e\3\2\2\2\u0420\u0423\3\2\2\2\u0421"+
		"\u041f\3\2\2\2\u0421\u0422\3\2\2\2\u0422\u0426\3\2\2\2\u0423\u0421\3\2"+
		"\2\2\u0424\u0426\79\2\2\u0425\u0421\3\2\2\2\u0425\u0424\3\2\2\2\u0426"+
		"\u0427\3\2\2\2\u0427\u0428\7\34\2\2\u0428\u0429\t\t\2\2\u0429\u0095\3"+
		"\2\2\2\u042a\u042b\t\n\2\2\u042b\u0097\3\2\2\2\u042c\u042e\7p\2\2\u042d"+
		"\u042c\3\2\2\2\u042e\u0431\3\2\2\2\u042f\u042d\3\2\2\2\u042f\u0430\3\2"+
		"\2\2\u0430\u0099\3\2\2\2\u0431\u042f\3\2\2\2\u0432\u0434\7p\2\2\u0433"+
		"\u0432\3\2\2\2\u0434\u0435\3\2\2\2\u0435\u0433\3\2\2\2\u0435\u0436\3\2"+
		"\2\2\u0436\u009b\3\2\2\2\u0437\u0438\t\13\2\2\u0438\u009d\3\2\2\2\u0439"+
		"\u043a\t\f\2\2\u043a\u009f\3\2\2\2\u043b\u043c\t\r\2\2\u043c\u00a1\3\2"+
		"\2\2\u043d\u043e\7?\2\2\u043e\u00a3\3\2\2\2b\u00a9\u00b7\u00c0\u00cd\u00d8"+
		"\u00dc\u00e4\u00f6\u0105\u0111\u0123\u012b\u0136\u0139\u0145\u014c\u0153"+
		"\u0159\u015b\u0167\u016d\u0172\u0177\u0181\u018c\u018f\u0198\u01a5\u01b5"+
		"\u01b7\u01c5\u01cf\u01d9\u01e0\u01e7\u01ec\u01f1\u01fe\u020a\u0212\u021c"+
		"\u0221\u0225\u0228\u022b\u022e\u0231\u0237\u023e\u0245\u024e\u0259\u0260"+
		"\u0262\u0272\u027d\u0288\u0291\u029c\u02aa\u02ac\u02bf\u02c1\u02cc\u02d3"+
		"\u02e1\u02e5\u02fc\u030d\u030f\u0315\u0317\u0335\u034c\u0374\u0392\u03a0"+
		"\u03a6\u03b4\u03bd\u03c2\u03ce\u03d9\u03df\u03f4\u03f7\u03fe\u0406\u040e"+
		"\u0413\u0416\u041a\u0421\u0425\u042f\u0435";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}