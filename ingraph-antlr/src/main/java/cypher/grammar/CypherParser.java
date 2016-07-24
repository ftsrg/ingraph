package cypher.grammar;// Generated from /home/szarnyasg/git/ingraph/openCypher/Cypher.g4 by ANTLR 4.5.3
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
		T__45=46, T__46=47, T__47=48, T__48=49, T__49=50, T__50=51, T__51=52, 
		T__52=53, T__53=54, T__54=55, T__55=56, T__56=57, T__57=58, T__58=59, 
		T__59=60, StringLiteral=61, EscapedChar=62, HexDigit=63, UNION=64, ALL=65, 
		OPTIONAL=66, MATCH=67, UNWIND=68, AS=69, MERGE=70, ON=71, CREATE=72, SET=73, 
		DELETE=74, DETACH=75, REMOVE=76, WITH=77, DISTINCT=78, RETURN=79, ORDER=80, 
		BY=81, L_SKIP=82, LIMIT=83, DESCENDING=84, DESC=85, ASCENDING=86, ASC=87, 
		WHERE=88, OR=89, XOR=90, AND=91, NOT=92, IN=93, STARTS=94, ENDS=95, CONTAINS=96, 
		IS=97, NULL=98, TRUE=99, FALSE=100, COUNT=101, FILTER=102, EXTRACT=103, 
		ANY=104, NONE=105, SINGLE=106, L_0X=107, UnescapedSymbolicName=108, IdentifierStart=109, 
		IdentifierPart=110, EscapedSymbolicName=111, WHITESPACE=112, Comment=113;
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
		RULE_propertyKeyName = 69, RULE_signedIntegerLiteral = 70, RULE_unsignedIntegerLiteral = 71, 
		RULE_hexInteger = 72, RULE_decimalInteger = 73, RULE_octalInteger = 74, 
		RULE_unsignedHexInteger = 75, RULE_unsignedDecimalInteger = 76, RULE_unsignedOctalInteger = 77, 
		RULE_hexString = 78, RULE_digitString = 79, RULE_octalString = 80, RULE_digit = 81, 
		RULE_octDigit = 82, RULE_doubleLiteral = 83, RULE_exponentDecimalReal = 84, 
		RULE_regularDecimalReal = 85, RULE_symbolicName = 86, RULE_ws = 87, RULE_sp = 88, 
		RULE_leftArrowHead = 89, RULE_rightArrowHead = 90, RULE_dash = 91;
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
		"propertyExpression", "propertyKeyName", "signedIntegerLiteral", "unsignedIntegerLiteral", 
		"hexInteger", "decimalInteger", "octalInteger", "unsignedHexInteger", 
		"unsignedDecimalInteger", "unsignedOctalInteger", "hexString", "digitString", 
		"octalString", "digit", "octDigit", "doubleLiteral", "exponentDecimalReal", 
		"regularDecimalReal", "symbolicName", "ws", "sp", "leftArrowHead", "rightArrowHead", 
		"dash"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'='", "'+='", "'*'", "'('", "')'", "'['", "'?'", 
		"']'", "':'", "'|'", "'..'", "'+'", "'-'", "'/'", "'%'", "'^'", "'=~'", 
		"'<>'", "'!='", "'<'", "'>'", "'<='", "'>='", "'.'", "'!'", "'{'", "'}'", 
		"'1'", "'2'", "'3'", "'4'", "'5'", "'6'", "'7'", "'8'", "'9'", "'0'", 
		"'E'", "'e'", "'⟨'", "'〈'", "'﹤'", "'＜'", "'⟩'", "'〉'", "'﹥'", "'＞'", 
		"'­'", "'‐'", "'‑'", "'‒'", "'–'", "'—'", "'―'", "'−'", "'﹘'", "'﹣'", 
		"'－'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, "StringLiteral", "EscapedChar", "HexDigit", "UNION", "ALL", "OPTIONAL", 
		"MATCH", "UNWIND", "AS", "MERGE", "ON", "CREATE", "SET", "DELETE", "DETACH", 
		"REMOVE", "WITH", "DISTINCT", "RETURN", "ORDER", "BY", "L_SKIP", "LIMIT", 
		"DESCENDING", "DESC", "ASCENDING", "ASC", "WHERE", "OR", "XOR", "AND", 
		"NOT", "IN", "STARTS", "ENDS", "CONTAINS", "IS", "NULL", "TRUE", "FALSE", 
		"COUNT", "FILTER", "EXTRACT", "ANY", "NONE", "SINGLE", "L_0X", "UnescapedSymbolicName", 
		"IdentifierStart", "IdentifierPart", "EscapedSymbolicName", "WHITESPACE", 
		"Comment"
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
			setState(184);
			ws();
			setState(185);
			statement();
			setState(189);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(186);
				ws();
				setState(187);
				match(T__0);
				}
				break;
			}
			setState(191);
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
			setState(193);
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
			setState(195);
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
			setState(197);
			singleQuery();
			setState(203);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(198);
					ws();
					setState(199);
					union();
					}
					} 
				}
				setState(205);
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
			setState(206);
			clause();
			setState(212);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(207);
					ws();
					setState(208);
					clause();
					}
					} 
				}
				setState(214);
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
			setState(222);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(215);
				match(UNION);
				setState(216);
				sp();
				setState(217);
				match(ALL);
				setState(218);
				singleQuery();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(220);
				match(UNION);
				setState(221);
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
		public ReturnContext _return() {
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
			setState(233);
			switch (_input.LA(1)) {
			case OPTIONAL:
			case MATCH:
				enterOuterAlt(_localctx, 1);
				{
				setState(224);
				match();
				}
				break;
			case UNWIND:
				enterOuterAlt(_localctx, 2);
				{
				setState(225);
				unwind();
				}
				break;
			case MERGE:
				enterOuterAlt(_localctx, 3);
				{
				setState(226);
				merge();
				}
				break;
			case CREATE:
				enterOuterAlt(_localctx, 4);
				{
				setState(227);
				create();
				}
				break;
			case SET:
				enterOuterAlt(_localctx, 5);
				{
				setState(228);
				set();
				}
				break;
			case DELETE:
			case DETACH:
				enterOuterAlt(_localctx, 6);
				{
				setState(229);
				delete();
				}
				break;
			case REMOVE:
				enterOuterAlt(_localctx, 7);
				{
				setState(230);
				remove();
				}
				break;
			case WITH:
				enterOuterAlt(_localctx, 8);
				{
				setState(231);
				with();
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 9);
				{
				setState(232);
				_return();
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
			setState(237);
			_la = _input.LA(1);
			if (_la==OPTIONAL) {
				{
				setState(235);
				match(OPTIONAL);
				setState(236);
				sp();
				}
			}

			setState(239);
			match(MATCH);
			setState(240);
			ws();
			setState(241);
			pattern();
			setState(245);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(242);
				ws();
				setState(243);
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
			setState(247);
			match(UNWIND);
			setState(248);
			ws();
			setState(249);
			expression();
			setState(250);
			sp();
			setState(251);
			match(AS);
			setState(252);
			sp();
			setState(253);
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
			setState(255);
			match(MERGE);
			setState(256);
			ws();
			setState(257);
			patternPart();
			setState(263);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(258);
					sp();
					setState(259);
					mergeAction();
					}
					} 
				}
				setState(265);
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
			setState(278);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(266);
				match(ON);
				setState(267);
				sp();
				setState(268);
				match(MATCH);
				setState(269);
				sp();
				setState(270);
				set();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(272);
				match(ON);
				setState(273);
				sp();
				setState(274);
				match(CREATE);
				setState(275);
				sp();
				setState(276);
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
			setState(280);
			match(CREATE);
			setState(281);
			ws();
			setState(282);
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
			setState(284);
			match(SET);
			setState(285);
			setItem();
			setState(290);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(286);
				match(T__1);
				setState(287);
				setItem();
				}
				}
				setState(292);
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
			setState(308);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(293);
				propertyExpression();
				setState(294);
				match(T__2);
				setState(295);
				expression();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(297);
				variable();
				setState(298);
				match(T__2);
				setState(299);
				expression();
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(301);
				variable();
				setState(302);
				match(T__3);
				setState(303);
				expression();
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(305);
				variable();
				setState(306);
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
			setState(330);
			switch (_input.LA(1)) {
			case DELETE:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(310);
				match(DELETE);
				setState(311);
				expression();
				setState(316);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(312);
					match(T__1);
					setState(313);
					expression();
					}
					}
					setState(318);
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
				setState(319);
				match(DETACH);
				setState(320);
				sp();
				setState(321);
				match(DELETE);
				setState(322);
				expression();
				setState(327);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(323);
					match(T__1);
					setState(324);
					expression();
					}
					}
					setState(329);
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
			setState(332);
			match(REMOVE);
			setState(333);
			sp();
			setState(334);
			removeItem();
			setState(342);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(335);
					ws();
					setState(336);
					match(T__1);
					setState(337);
					ws();
					setState(338);
					removeItem();
					}
					} 
				}
				setState(344);
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
			setState(349);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(345);
				variable();
				setState(346);
				nodeLabels();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(348);
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
			setState(364);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(351);
				match(WITH);
				setState(352);
				match(DISTINCT);
				setState(353);
				sp();
				setState(354);
				returnBody();
				setState(356);
				_la = _input.LA(1);
				if (_la==WHERE) {
					{
					setState(355);
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
				setState(358);
				match(WITH);
				setState(359);
				sp();
				setState(360);
				returnBody();
				setState(362);
				_la = _input.LA(1);
				if (_la==WHERE) {
					{
					setState(361);
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

	public final ReturnContext _return() throws RecognitionException {
		ReturnContext _localctx = new ReturnContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_return);
		try {
			setState(376);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(366);
				match(RETURN);
				setState(367);
				sp();
				setState(368);
				match(DISTINCT);
				setState(369);
				sp();
				setState(370);
				returnBody();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(372);
				match(RETURN);
				setState(373);
				sp();
				setState(374);
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
			setState(378);
			returnItems();
			setState(382);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				{
				setState(379);
				sp();
				setState(380);
				order();
				}
				break;
			}
			setState(387);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(384);
				sp();
				setState(385);
				skip();
				}
				break;
			}
			setState(392);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(389);
				sp();
				setState(390);
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
			setState(416);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(394);
				match(T__4);
				setState(402);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(395);
						ws();
						setState(396);
						match(T__1);
						setState(397);
						ws();
						setState(398);
						returnItem();
						}
						} 
					}
					setState(404);
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
			case T__30:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
			case T__37:
			case T__38:
			case StringLiteral:
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
			case L_0X:
			case UnescapedSymbolicName:
			case EscapedSymbolicName:
			case WHITESPACE:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(405);
				returnItem();
				setState(413);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,24,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(406);
						ws();
						setState(407);
						match(T__1);
						setState(408);
						ws();
						setState(409);
						returnItem();
						}
						} 
					}
					setState(415);
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
			setState(425);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(418);
				expression();
				setState(419);
				sp();
				setState(420);
				match(AS);
				setState(421);
				sp();
				setState(422);
				variable();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(424);
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
			setState(427);
			match(ORDER);
			setState(428);
			sp();
			setState(429);
			match(BY);
			setState(430);
			sp();
			setState(431);
			sortItem();
			setState(438);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(432);
				match(T__1);
				setState(433);
				ws();
				setState(434);
				sortItem();
				}
				}
				setState(440);
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
			setState(441);
			match(L_SKIP);
			setState(442);
			sp();
			setState(443);
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
			setState(445);
			match(LIMIT);
			setState(446);
			sp();
			setState(447);
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
			setState(456);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(449);
				expression();
				setState(450);
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
				setState(452);
				expression();
				setState(454);
				_la = _input.LA(1);
				if (_la==ASCENDING || _la==ASC) {
					{
					setState(453);
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
			setState(458);
			match(WHERE);
			setState(459);
			sp();
			setState(460);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(462);
			patternPart();
			setState(467);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1) {
				{
				{
				setState(463);
				match(T__1);
				setState(464);
				patternPart();
				}
				}
				setState(469);
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
			setState(477);
			switch (_input.LA(1)) {
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
			case L_0X:
			case UnescapedSymbolicName:
			case EscapedSymbolicName:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(470);
				variable();
				setState(471);
				ws();
				setState(472);
				match(T__2);
				setState(473);
				ws();
				setState(474);
				anonymousPatternPart();
				}
				}
				break;
			case T__5:
				enterOuterAlt(_localctx, 2);
				{
				setState(476);
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
			setState(479);
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
			setState(494);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(481);
				nodePattern();
				setState(487);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,32,_ctx);
				while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(482);
						ws();
						setState(483);
						patternElementChain();
						}
						} 
					}
					setState(489);
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
				setState(490);
				match(T__5);
				setState(491);
				patternElement();
				setState(492);
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
			setState(496);
			match(T__5);
			setState(497);
			ws();
			setState(501);
			_la = _input.LA(1);
			if (((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UNION - 64)) | (1L << (ALL - 64)) | (1L << (OPTIONAL - 64)) | (1L << (MATCH - 64)) | (1L << (UNWIND - 64)) | (1L << (AS - 64)) | (1L << (MERGE - 64)) | (1L << (ON - 64)) | (1L << (CREATE - 64)) | (1L << (SET - 64)) | (1L << (DELETE - 64)) | (1L << (DETACH - 64)) | (1L << (REMOVE - 64)) | (1L << (WITH - 64)) | (1L << (DISTINCT - 64)) | (1L << (RETURN - 64)) | (1L << (ORDER - 64)) | (1L << (BY - 64)) | (1L << (L_SKIP - 64)) | (1L << (LIMIT - 64)) | (1L << (DESCENDING - 64)) | (1L << (DESC - 64)) | (1L << (ASCENDING - 64)) | (1L << (ASC - 64)) | (1L << (WHERE - 64)) | (1L << (OR - 64)) | (1L << (XOR - 64)) | (1L << (AND - 64)) | (1L << (NOT - 64)) | (1L << (IN - 64)) | (1L << (STARTS - 64)) | (1L << (ENDS - 64)) | (1L << (CONTAINS - 64)) | (1L << (IS - 64)) | (1L << (NULL - 64)) | (1L << (TRUE - 64)) | (1L << (FALSE - 64)) | (1L << (COUNT - 64)) | (1L << (FILTER - 64)) | (1L << (EXTRACT - 64)) | (1L << (ANY - 64)) | (1L << (NONE - 64)) | (1L << (SINGLE - 64)) | (1L << (L_0X - 64)) | (1L << (UnescapedSymbolicName - 64)) | (1L << (EscapedSymbolicName - 64)))) != 0)) {
				{
				setState(498);
				variable();
				setState(499);
				ws();
				}
			}

			setState(506);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(503);
				nodeLabels();
				setState(504);
				ws();
				}
			}

			setState(511);
			_la = _input.LA(1);
			if (_la==T__27) {
				{
				setState(508);
				properties();
				setState(509);
				ws();
				}
			}

			setState(513);
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
			setState(515);
			relationshipPattern();
			setState(516);
			ws();
			setState(517);
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
			setState(559);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(519);
				leftArrowHead();
				setState(520);
				ws();
				setState(521);
				dash();
				setState(522);
				ws();
				setState(524);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(523);
					relationshipDetail();
					}
				}

				setState(526);
				ws();
				setState(527);
				dash();
				setState(528);
				ws();
				setState(529);
				rightArrowHead();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(531);
				leftArrowHead();
				setState(532);
				ws();
				setState(533);
				dash();
				setState(534);
				ws();
				setState(536);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(535);
					relationshipDetail();
					}
				}

				setState(538);
				ws();
				setState(539);
				dash();
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(541);
				dash();
				setState(542);
				ws();
				setState(544);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(543);
					relationshipDetail();
					}
				}

				setState(546);
				ws();
				setState(547);
				dash();
				setState(548);
				ws();
				setState(549);
				rightArrowHead();
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(551);
				dash();
				setState(552);
				ws();
				setState(554);
				_la = _input.LA(1);
				if (_la==T__7) {
					{
					setState(553);
					relationshipDetail();
					}
				}

				setState(556);
				ws();
				setState(557);
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
			setState(561);
			match(T__7);
			setState(563);
			_la = _input.LA(1);
			if (((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UNION - 64)) | (1L << (ALL - 64)) | (1L << (OPTIONAL - 64)) | (1L << (MATCH - 64)) | (1L << (UNWIND - 64)) | (1L << (AS - 64)) | (1L << (MERGE - 64)) | (1L << (ON - 64)) | (1L << (CREATE - 64)) | (1L << (SET - 64)) | (1L << (DELETE - 64)) | (1L << (DETACH - 64)) | (1L << (REMOVE - 64)) | (1L << (WITH - 64)) | (1L << (DISTINCT - 64)) | (1L << (RETURN - 64)) | (1L << (ORDER - 64)) | (1L << (BY - 64)) | (1L << (L_SKIP - 64)) | (1L << (LIMIT - 64)) | (1L << (DESCENDING - 64)) | (1L << (DESC - 64)) | (1L << (ASCENDING - 64)) | (1L << (ASC - 64)) | (1L << (WHERE - 64)) | (1L << (OR - 64)) | (1L << (XOR - 64)) | (1L << (AND - 64)) | (1L << (NOT - 64)) | (1L << (IN - 64)) | (1L << (STARTS - 64)) | (1L << (ENDS - 64)) | (1L << (CONTAINS - 64)) | (1L << (IS - 64)) | (1L << (NULL - 64)) | (1L << (TRUE - 64)) | (1L << (FALSE - 64)) | (1L << (COUNT - 64)) | (1L << (FILTER - 64)) | (1L << (EXTRACT - 64)) | (1L << (ANY - 64)) | (1L << (NONE - 64)) | (1L << (SINGLE - 64)) | (1L << (L_0X - 64)) | (1L << (UnescapedSymbolicName - 64)) | (1L << (EscapedSymbolicName - 64)))) != 0)) {
				{
				setState(562);
				variable();
				}
			}

			setState(566);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(565);
				match(T__8);
				}
			}

			setState(569);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(568);
				relationshipTypes();
				}
			}

			setState(573);
			_la = _input.LA(1);
			if (_la==T__4) {
				{
				setState(571);
				match(T__4);
				setState(572);
				rangeLiteral();
				}
			}

			setState(576);
			_la = _input.LA(1);
			if (_la==T__27) {
				{
				setState(575);
				properties();
				}
			}

			setState(578);
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
			setState(582);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(580);
				mapLiteral();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(581);
				parameter();
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
			setState(584);
			match(T__10);
			setState(585);
			relTypeName();
			setState(596);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__11 || _la==WHITESPACE) {
				{
				{
				setState(586);
				ws();
				setState(587);
				match(T__11);
				setState(589);
				_la = _input.LA(1);
				if (_la==T__10) {
					{
					setState(588);
					match(T__10);
					}
				}

				setState(591);
				ws();
				setState(592);
				relTypeName();
				}
				}
				setState(598);
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
			setState(599);
			nodeLabel();
			setState(605);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,50,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(600);
					ws();
					setState(601);
					nodeLabel();
					}
					} 
				}
				setState(607);
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
			setState(608);
			match(T__10);
			setState(609);
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
		public List<UnsignedIntegerLiteralContext> unsignedIntegerLiteral() {
			return getRuleContexts(UnsignedIntegerLiteralContext.class);
		}
		public UnsignedIntegerLiteralContext unsignedIntegerLiteral(int i) {
			return getRuleContext(UnsignedIntegerLiteralContext.class,i);
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
			setState(611);
			ws();
			setState(615);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38))) != 0)) {
				{
				setState(612);
				unsignedIntegerLiteral();
				setState(613);
				ws();
				}
			}

			setState(624);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(617);
				match(T__12);
				setState(618);
				ws();
				setState(622);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38))) != 0)) {
					{
					setState(619);
					unsignedIntegerLiteral();
					setState(620);
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
			setState(626);
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
			setState(628);
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
			setState(630);
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
			setState(632);
			expression11();
			setState(640);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,54,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(633);
					sp();
					setState(634);
					match(OR);
					setState(635);
					sp();
					setState(636);
					expression11();
					}
					} 
				}
				setState(642);
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
			setState(643);
			expression10();
			setState(651);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(644);
					sp();
					setState(645);
					match(XOR);
					setState(646);
					sp();
					setState(647);
					expression10();
					}
					} 
				}
				setState(653);
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
			setState(654);
			expression9();
			setState(662);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(655);
					sp();
					setState(656);
					match(AND);
					setState(657);
					sp();
					setState(658);
					expression9();
					}
					} 
				}
				setState(664);
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
			setState(671);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(665);
				sp();
				setState(666);
				match(NOT);
				setState(667);
				sp();
				}
				}
				setState(673);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(674);
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
			setState(676);
			expression7();
			setState(682);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,58,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(677);
					ws();
					setState(678);
					partialComparisonExpression();
					}
					} 
				}
				setState(684);
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
			setState(685);
			expression6();
			setState(698);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(696);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,59,_ctx) ) {
					case 1:
						{
						{
						setState(686);
						ws();
						setState(687);
						match(T__13);
						setState(688);
						ws();
						setState(689);
						expression6();
						}
						}
						break;
					case 2:
						{
						{
						setState(691);
						ws();
						setState(692);
						match(T__14);
						setState(693);
						ws();
						setState(694);
						expression6();
						}
						}
						break;
					}
					} 
				}
				setState(700);
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
			setState(701);
			expression5();
			setState(719);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(717);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
					case 1:
						{
						{
						setState(702);
						ws();
						setState(703);
						match(T__4);
						setState(704);
						ws();
						setState(705);
						expression5();
						}
						}
						break;
					case 2:
						{
						{
						setState(707);
						ws();
						setState(708);
						match(T__15);
						setState(709);
						ws();
						setState(710);
						expression5();
						}
						}
						break;
					case 3:
						{
						{
						setState(712);
						ws();
						setState(713);
						match(T__16);
						setState(714);
						ws();
						setState(715);
						expression5();
						}
						}
						break;
					}
					} 
				}
				setState(721);
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
			setState(722);
			expression4();
			setState(730);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,63,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(723);
					ws();
					setState(724);
					match(T__17);
					setState(725);
					ws();
					setState(726);
					expression4();
					}
					} 
				}
				setState(732);
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
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(737);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(733);
					_la = _input.LA(1);
					if ( !(_la==T__13 || _la==T__14) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(734);
					ws();
					}
					} 
				}
				setState(739);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,64,_ctx);
			}
			setState(740);
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
			setState(742);
			expression2();
			setState(797);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,69,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(795);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
					case 1:
						{
						{
						setState(743);
						ws();
						setState(744);
						match(T__7);
						setState(745);
						expression();
						setState(746);
						match(T__9);
						}
						}
						break;
					case 2:
						{
						{
						setState(748);
						ws();
						setState(749);
						match(T__7);
						setState(751);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__7) | (1L << T__13) | (1L << T__14) | (1L << T__25) | (1L << T__27) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << StringLiteral))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UNION - 64)) | (1L << (ALL - 64)) | (1L << (OPTIONAL - 64)) | (1L << (MATCH - 64)) | (1L << (UNWIND - 64)) | (1L << (AS - 64)) | (1L << (MERGE - 64)) | (1L << (ON - 64)) | (1L << (CREATE - 64)) | (1L << (SET - 64)) | (1L << (DELETE - 64)) | (1L << (DETACH - 64)) | (1L << (REMOVE - 64)) | (1L << (WITH - 64)) | (1L << (DISTINCT - 64)) | (1L << (RETURN - 64)) | (1L << (ORDER - 64)) | (1L << (BY - 64)) | (1L << (L_SKIP - 64)) | (1L << (LIMIT - 64)) | (1L << (DESCENDING - 64)) | (1L << (DESC - 64)) | (1L << (ASCENDING - 64)) | (1L << (ASC - 64)) | (1L << (WHERE - 64)) | (1L << (OR - 64)) | (1L << (XOR - 64)) | (1L << (AND - 64)) | (1L << (NOT - 64)) | (1L << (IN - 64)) | (1L << (STARTS - 64)) | (1L << (ENDS - 64)) | (1L << (CONTAINS - 64)) | (1L << (IS - 64)) | (1L << (NULL - 64)) | (1L << (TRUE - 64)) | (1L << (FALSE - 64)) | (1L << (COUNT - 64)) | (1L << (FILTER - 64)) | (1L << (EXTRACT - 64)) | (1L << (ANY - 64)) | (1L << (NONE - 64)) | (1L << (SINGLE - 64)) | (1L << (L_0X - 64)) | (1L << (UnescapedSymbolicName - 64)) | (1L << (EscapedSymbolicName - 64)) | (1L << (WHITESPACE - 64)))) != 0)) {
							{
							setState(750);
							expression();
							}
						}

						setState(753);
						match(T__12);
						setState(755);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__7) | (1L << T__13) | (1L << T__14) | (1L << T__25) | (1L << T__27) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << StringLiteral))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UNION - 64)) | (1L << (ALL - 64)) | (1L << (OPTIONAL - 64)) | (1L << (MATCH - 64)) | (1L << (UNWIND - 64)) | (1L << (AS - 64)) | (1L << (MERGE - 64)) | (1L << (ON - 64)) | (1L << (CREATE - 64)) | (1L << (SET - 64)) | (1L << (DELETE - 64)) | (1L << (DETACH - 64)) | (1L << (REMOVE - 64)) | (1L << (WITH - 64)) | (1L << (DISTINCT - 64)) | (1L << (RETURN - 64)) | (1L << (ORDER - 64)) | (1L << (BY - 64)) | (1L << (L_SKIP - 64)) | (1L << (LIMIT - 64)) | (1L << (DESCENDING - 64)) | (1L << (DESC - 64)) | (1L << (ASCENDING - 64)) | (1L << (ASC - 64)) | (1L << (WHERE - 64)) | (1L << (OR - 64)) | (1L << (XOR - 64)) | (1L << (AND - 64)) | (1L << (NOT - 64)) | (1L << (IN - 64)) | (1L << (STARTS - 64)) | (1L << (ENDS - 64)) | (1L << (CONTAINS - 64)) | (1L << (IS - 64)) | (1L << (NULL - 64)) | (1L << (TRUE - 64)) | (1L << (FALSE - 64)) | (1L << (COUNT - 64)) | (1L << (FILTER - 64)) | (1L << (EXTRACT - 64)) | (1L << (ANY - 64)) | (1L << (NONE - 64)) | (1L << (SINGLE - 64)) | (1L << (L_0X - 64)) | (1L << (UnescapedSymbolicName - 64)) | (1L << (EscapedSymbolicName - 64)) | (1L << (WHITESPACE - 64)))) != 0)) {
							{
							setState(754);
							expression();
							}
						}

						setState(757);
						match(T__9);
						}
						}
						break;
					case 3:
						{
						{
						setState(778);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
						case 1:
							{
							{
							setState(759);
							ws();
							setState(760);
							match(T__18);
							}
							}
							break;
						case 2:
							{
							{
							setState(762);
							sp();
							setState(763);
							match(IN);
							}
							}
							break;
						case 3:
							{
							{
							setState(765);
							sp();
							setState(766);
							match(STARTS);
							setState(767);
							sp();
							setState(768);
							match(WITH);
							}
							}
							break;
						case 4:
							{
							{
							setState(770);
							sp();
							setState(771);
							match(ENDS);
							setState(772);
							sp();
							setState(773);
							match(WITH);
							}
							}
							break;
						case 5:
							{
							{
							setState(775);
							sp();
							setState(776);
							match(CONTAINS);
							}
							}
							break;
						}
						setState(780);
						ws();
						setState(781);
						expression2();
						}
						}
						break;
					case 4:
						{
						{
						setState(783);
						sp();
						setState(784);
						match(IS);
						setState(785);
						sp();
						setState(786);
						match(NULL);
						}
						}
						break;
					case 5:
						{
						{
						setState(788);
						sp();
						setState(789);
						match(IS);
						setState(790);
						sp();
						setState(791);
						match(NOT);
						setState(792);
						sp();
						setState(793);
						match(NULL);
						}
						}
						break;
					}
					} 
				}
				setState(799);
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
			setState(800);
			atom();
			setState(805);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,71,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(803);
					switch (_input.LA(1)) {
					case T__25:
					case WHITESPACE:
						{
						setState(801);
						propertyLookup();
						}
						break;
					case T__10:
						{
						setState(802);
						nodeLabels();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(807);
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
			setState(894);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(808);
				numberLiteral();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(809);
				match(StringLiteral);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(810);
				parameter();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(811);
				match(TRUE);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(812);
				match(FALSE);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(813);
				match(NULL);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				{
				setState(814);
				match(COUNT);
				setState(815);
				match(T__5);
				setState(816);
				match(T__4);
				setState(817);
				match(T__6);
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(818);
				mapLiteral();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(819);
				listComprehension();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				{
				setState(820);
				match(T__7);
				setState(821);
				ws();
				setState(822);
				expression();
				setState(823);
				ws();
				setState(831);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(824);
					match(T__1);
					setState(825);
					ws();
					setState(826);
					expression();
					setState(827);
					ws();
					}
					}
					setState(833);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(834);
				match(T__9);
				}
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				{
				setState(836);
				match(FILTER);
				setState(837);
				ws();
				setState(838);
				match(T__5);
				setState(839);
				ws();
				setState(840);
				filterExpression();
				setState(841);
				ws();
				setState(842);
				match(T__6);
				}
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				{
				setState(844);
				match(EXTRACT);
				setState(845);
				ws();
				setState(846);
				match(T__5);
				setState(847);
				ws();
				setState(848);
				filterExpression();
				setState(849);
				ws();
				setState(854);
				_la = _input.LA(1);
				if (_la==T__11 || _la==WHITESPACE) {
					{
					setState(850);
					ws();
					setState(851);
					match(T__11);
					setState(852);
					expression();
					}
				}

				setState(856);
				match(T__6);
				}
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				{
				setState(858);
				match(ALL);
				setState(859);
				ws();
				setState(860);
				match(T__5);
				setState(861);
				ws();
				setState(862);
				filterExpression();
				setState(863);
				ws();
				setState(864);
				match(T__6);
				}
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				{
				setState(866);
				match(ANY);
				setState(867);
				ws();
				setState(868);
				match(T__5);
				setState(869);
				ws();
				setState(870);
				filterExpression();
				setState(871);
				ws();
				setState(872);
				match(T__6);
				}
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				{
				setState(874);
				match(NONE);
				setState(875);
				ws();
				setState(876);
				match(T__5);
				setState(877);
				ws();
				setState(878);
				filterExpression();
				setState(879);
				ws();
				setState(880);
				match(T__6);
				}
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				{
				setState(882);
				match(SINGLE);
				setState(883);
				ws();
				setState(884);
				match(T__5);
				setState(885);
				ws();
				setState(886);
				filterExpression();
				setState(887);
				ws();
				setState(888);
				match(T__6);
				}
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(890);
				relationshipsPattern();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(891);
				parenthesizedExpression();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(892);
				functionInvocation();
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(893);
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
			setState(924);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(896);
				match(T__2);
				setState(897);
				ws();
				setState(898);
				expression7();
				}
				}
				break;
			case T__19:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(900);
				match(T__19);
				setState(901);
				ws();
				setState(902);
				expression7();
				}
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(904);
				match(T__20);
				setState(905);
				ws();
				setState(906);
				expression7();
				}
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(908);
				match(T__21);
				setState(909);
				ws();
				setState(910);
				expression7();
				}
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(912);
				match(T__22);
				setState(913);
				ws();
				setState(914);
				expression7();
				}
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 6);
				{
				{
				setState(916);
				match(T__23);
				setState(917);
				ws();
				setState(918);
				expression7();
				}
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 7);
				{
				{
				setState(920);
				match(T__24);
				setState(921);
				ws();
				setState(922);
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
			setState(926);
			match(T__5);
			setState(927);
			ws();
			setState(928);
			expression();
			setState(929);
			ws();
			setState(930);
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
			setState(932);
			nodePattern();
			setState(936); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(933);
					ws();
					setState(934);
					patternElementChain();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(938); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
			} while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER );
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
			setState(940);
			idInColl();
			setState(944);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
			case 1:
				{
				setState(941);
				ws();
				setState(942);
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
			setState(946);
			variable();
			setState(947);
			sp();
			setState(948);
			match(IN);
			setState(949);
			sp();
			setState(950);
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
			setState(952);
			functionName();
			setState(953);
			ws();
			setState(954);
			match(T__5);
			setState(955);
			ws();
			setState(957);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
			case 1:
				{
				setState(956);
				match(DISTINCT);
				}
				break;
			}
			setState(969);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
			case 1:
				{
				setState(959);
				expression();
				setState(966);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(960);
					match(T__1);
					setState(961);
					ws();
					setState(962);
					expression();
					}
					}
					setState(968);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
			setState(971);
			ws();
			setState(972);
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
			setState(974);
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
			setState(976);
			match(T__7);
			setState(977);
			filterExpression();
			setState(982);
			_la = _input.LA(1);
			if (_la==T__11 || _la==WHITESPACE) {
				{
				setState(978);
				ws();
				setState(979);
				match(T__11);
				setState(980);
				expression();
				}
			}

			setState(984);
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
			setState(986);
			ws();
			setState(987);
			match(T__25);
			setState(988);
			ws();
			setState(993);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,82,_ctx) ) {
			case 1:
				{
				{
				setState(989);
				propertyKeyName();
				setState(990);
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
				setState(992);
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
			setState(995);
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
		public SignedIntegerLiteralContext signedIntegerLiteral() {
			return getRuleContext(SignedIntegerLiteralContext.class,0);
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
			setState(999);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(997);
				doubleLiteral();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(998);
				signedIntegerLiteral();
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
			setState(1001);
			match(T__27);
			setState(1002);
			ws();
			setState(1023);
			_la = _input.LA(1);
			if (((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UNION - 64)) | (1L << (ALL - 64)) | (1L << (OPTIONAL - 64)) | (1L << (MATCH - 64)) | (1L << (UNWIND - 64)) | (1L << (AS - 64)) | (1L << (MERGE - 64)) | (1L << (ON - 64)) | (1L << (CREATE - 64)) | (1L << (SET - 64)) | (1L << (DELETE - 64)) | (1L << (DETACH - 64)) | (1L << (REMOVE - 64)) | (1L << (WITH - 64)) | (1L << (DISTINCT - 64)) | (1L << (RETURN - 64)) | (1L << (ORDER - 64)) | (1L << (BY - 64)) | (1L << (L_SKIP - 64)) | (1L << (LIMIT - 64)) | (1L << (DESCENDING - 64)) | (1L << (DESC - 64)) | (1L << (ASCENDING - 64)) | (1L << (ASC - 64)) | (1L << (WHERE - 64)) | (1L << (OR - 64)) | (1L << (XOR - 64)) | (1L << (AND - 64)) | (1L << (NOT - 64)) | (1L << (IN - 64)) | (1L << (STARTS - 64)) | (1L << (ENDS - 64)) | (1L << (CONTAINS - 64)) | (1L << (IS - 64)) | (1L << (NULL - 64)) | (1L << (TRUE - 64)) | (1L << (FALSE - 64)) | (1L << (COUNT - 64)) | (1L << (FILTER - 64)) | (1L << (EXTRACT - 64)) | (1L << (ANY - 64)) | (1L << (NONE - 64)) | (1L << (SINGLE - 64)) | (1L << (L_0X - 64)) | (1L << (UnescapedSymbolicName - 64)) | (1L << (EscapedSymbolicName - 64)))) != 0)) {
				{
				setState(1003);
				propertyKeyName();
				setState(1004);
				ws();
				setState(1005);
				match(T__10);
				setState(1006);
				ws();
				setState(1007);
				expression();
				setState(1008);
				ws();
				setState(1020);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(1009);
					match(T__1);
					setState(1010);
					ws();
					setState(1011);
					propertyKeyName();
					setState(1012);
					ws();
					setState(1013);
					match(T__10);
					setState(1014);
					ws();
					setState(1015);
					expression();
					setState(1016);
					ws();
					}
					}
					setState(1022);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(1025);
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
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public SymbolicNameContext symbolicName() {
			return getRuleContext(SymbolicNameContext.class,0);
		}
		public UnsignedDecimalIntegerContext unsignedDecimalInteger() {
			return getRuleContext(UnsignedDecimalIntegerContext.class,0);
		}
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
			setState(1027);
			match(T__27);
			setState(1028);
			ws();
			setState(1031);
			switch (_input.LA(1)) {
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
			case L_0X:
			case UnescapedSymbolicName:
			case EscapedSymbolicName:
				{
				setState(1029);
				symbolicName();
				}
				break;
			case T__29:
			case T__30:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
			case T__37:
			case T__38:
				{
				setState(1030);
				unsignedDecimalInteger();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1033);
			ws();
			setState(1034);
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
			setState(1036);
			atom();
			setState(1040); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1037);
					ws();
					setState(1038);
					propertyLookup();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1042); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,87,_ctx);
			} while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER );
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
			setState(1044);
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

	public static class SignedIntegerLiteralContext extends ParserRuleContext {
		public HexIntegerContext hexInteger() {
			return getRuleContext(HexIntegerContext.class,0);
		}
		public OctalIntegerContext octalInteger() {
			return getRuleContext(OctalIntegerContext.class,0);
		}
		public DecimalIntegerContext decimalInteger() {
			return getRuleContext(DecimalIntegerContext.class,0);
		}
		public SignedIntegerLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signedIntegerLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterSignedIntegerLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitSignedIntegerLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitSignedIntegerLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SignedIntegerLiteralContext signedIntegerLiteral() throws RecognitionException {
		SignedIntegerLiteralContext _localctx = new SignedIntegerLiteralContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_signedIntegerLiteral);
		try {
			setState(1049);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,88,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1046);
				hexInteger();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1047);
				octalInteger();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1048);
				decimalInteger();
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

	public static class UnsignedIntegerLiteralContext extends ParserRuleContext {
		public UnsignedDecimalIntegerContext unsignedDecimalInteger() {
			return getRuleContext(UnsignedDecimalIntegerContext.class,0);
		}
		public UnsignedIntegerLiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unsignedIntegerLiteral; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterUnsignedIntegerLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitUnsignedIntegerLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitUnsignedIntegerLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnsignedIntegerLiteralContext unsignedIntegerLiteral() throws RecognitionException {
		UnsignedIntegerLiteralContext _localctx = new UnsignedIntegerLiteralContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_unsignedIntegerLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1051);
			unsignedDecimalInteger();
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

	public static class HexIntegerContext extends ParserRuleContext {
		public UnsignedHexIntegerContext unsignedHexInteger() {
			return getRuleContext(UnsignedHexIntegerContext.class,0);
		}
		public HexIntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hexInteger; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterHexInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitHexInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitHexInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HexIntegerContext hexInteger() throws RecognitionException {
		HexIntegerContext _localctx = new HexIntegerContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_hexInteger);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1054);
			_la = _input.LA(1);
			if (_la==T__14) {
				{
				setState(1053);
				match(T__14);
				}
			}

			setState(1056);
			unsignedHexInteger();
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

	public static class DecimalIntegerContext extends ParserRuleContext {
		public UnsignedDecimalIntegerContext unsignedDecimalInteger() {
			return getRuleContext(UnsignedDecimalIntegerContext.class,0);
		}
		public DecimalIntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimalInteger; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterDecimalInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitDecimalInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitDecimalInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DecimalIntegerContext decimalInteger() throws RecognitionException {
		DecimalIntegerContext _localctx = new DecimalIntegerContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_decimalInteger);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1059);
			_la = _input.LA(1);
			if (_la==T__14) {
				{
				setState(1058);
				match(T__14);
				}
			}

			setState(1061);
			unsignedDecimalInteger();
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

	public static class OctalIntegerContext extends ParserRuleContext {
		public UnsignedOctalIntegerContext unsignedOctalInteger() {
			return getRuleContext(UnsignedOctalIntegerContext.class,0);
		}
		public OctalIntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_octalInteger; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterOctalInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitOctalInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitOctalInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OctalIntegerContext octalInteger() throws RecognitionException {
		OctalIntegerContext _localctx = new OctalIntegerContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_octalInteger);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1064);
			_la = _input.LA(1);
			if (_la==T__14) {
				{
				setState(1063);
				match(T__14);
				}
			}

			setState(1066);
			unsignedOctalInteger();
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

	public static class UnsignedHexIntegerContext extends ParserRuleContext {
		public TerminalNode L_0X() { return getToken(CypherParser.L_0X, 0); }
		public HexStringContext hexString() {
			return getRuleContext(HexStringContext.class,0);
		}
		public UnsignedHexIntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unsignedHexInteger; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterUnsignedHexInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitUnsignedHexInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitUnsignedHexInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnsignedHexIntegerContext unsignedHexInteger() throws RecognitionException {
		UnsignedHexIntegerContext _localctx = new UnsignedHexIntegerContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_unsignedHexInteger);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1068);
			match(L_0X);
			setState(1069);
			hexString();
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

	public static class UnsignedDecimalIntegerContext extends ParserRuleContext {
		public DigitStringContext digitString() {
			return getRuleContext(DigitStringContext.class,0);
		}
		public UnsignedDecimalIntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unsignedDecimalInteger; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterUnsignedDecimalInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitUnsignedDecimalInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitUnsignedDecimalInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnsignedDecimalIntegerContext unsignedDecimalInteger() throws RecognitionException {
		UnsignedDecimalIntegerContext _localctx = new UnsignedDecimalIntegerContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_unsignedDecimalInteger);
		int _la;
		try {
			setState(1076);
			switch (_input.LA(1)) {
			case T__29:
			case T__30:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
			case T__37:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(1071);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(1073);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38))) != 0)) {
					{
					setState(1072);
					digitString();
					}
				}

				}
				}
				break;
			case T__38:
				enterOuterAlt(_localctx, 2);
				{
				setState(1075);
				match(T__38);
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

	public static class UnsignedOctalIntegerContext extends ParserRuleContext {
		public OctalStringContext octalString() {
			return getRuleContext(OctalStringContext.class,0);
		}
		public UnsignedOctalIntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unsignedOctalInteger; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterUnsignedOctalInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitUnsignedOctalInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitUnsignedOctalInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnsignedOctalIntegerContext unsignedOctalInteger() throws RecognitionException {
		UnsignedOctalIntegerContext _localctx = new UnsignedOctalIntegerContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_unsignedOctalInteger);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1078);
			match(T__38);
			setState(1079);
			octalString();
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

	public static class HexStringContext extends ParserRuleContext {
		public List<TerminalNode> HexDigit() { return getTokens(CypherParser.HexDigit); }
		public TerminalNode HexDigit(int i) {
			return getToken(CypherParser.HexDigit, i);
		}
		public HexStringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hexString; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterHexString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitHexString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitHexString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HexStringContext hexString() throws RecognitionException {
		HexStringContext _localctx = new HexStringContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_hexString);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1082); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1081);
				match(HexDigit);
				}
				}
				setState(1084); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==HexDigit );
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

	public static class DigitStringContext extends ParserRuleContext {
		public List<DigitContext> digit() {
			return getRuleContexts(DigitContext.class);
		}
		public DigitContext digit(int i) {
			return getRuleContext(DigitContext.class,i);
		}
		public DigitStringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_digitString; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterDigitString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitDigitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitDigitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DigitStringContext digitString() throws RecognitionException {
		DigitStringContext _localctx = new DigitStringContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_digitString);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1087); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1086);
				digit();
				}
				}
				setState(1089); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38))) != 0) );
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

	public static class OctalStringContext extends ParserRuleContext {
		public List<OctDigitContext> octDigit() {
			return getRuleContexts(OctDigitContext.class);
		}
		public OctDigitContext octDigit(int i) {
			return getRuleContext(OctDigitContext.class,i);
		}
		public OctalStringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_octalString; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterOctalString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitOctalString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitOctalString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OctalStringContext octalString() throws RecognitionException {
		OctalStringContext _localctx = new OctalStringContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_octalString);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1092); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1091);
				octDigit();
				}
				}
				setState(1094); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__38))) != 0) );
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
		enterRule(_localctx, 162, RULE_digit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1096);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38))) != 0)) ) {
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

	public static class OctDigitContext extends ParserRuleContext {
		public OctDigitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_octDigit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterOctDigit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitOctDigit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitOctDigit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OctDigitContext octDigit() throws RecognitionException {
		OctDigitContext _localctx = new OctDigitContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_octDigit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1098);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__38))) != 0)) ) {
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
		enterRule(_localctx, 166, RULE_doubleLiteral);
		try {
			setState(1102);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,97,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1100);
				exponentDecimalReal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1101);
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
		public DigitStringContext digitString() {
			return getRuleContext(DigitStringContext.class,0);
		}
		public List<DigitContext> digit() {
			return getRuleContexts(DigitContext.class);
		}
		public DigitContext digit(int i) {
			return getRuleContext(DigitContext.class,i);
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
		enterRule(_localctx, 168, RULE_exponentDecimalReal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1105);
			_la = _input.LA(1);
			if (_la==T__14) {
				{
				setState(1104);
				match(T__14);
				}
			}

			setState(1109); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(1109);
				switch (_input.LA(1)) {
				case T__29:
				case T__30:
				case T__31:
				case T__32:
				case T__33:
				case T__34:
				case T__35:
				case T__36:
				case T__37:
				case T__38:
					{
					setState(1107);
					digit();
					}
					break;
				case T__25:
					{
					setState(1108);
					match(T__25);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1111); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__25) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38))) != 0) );
			setState(1115);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,101,_ctx) ) {
			case 1:
				{
				setState(1113);
				_la = _input.LA(1);
				if ( !(_la==T__39 || _la==T__40) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case 2:
				{
				setState(1114);
				_la = _input.LA(1);
				if ( !(_la==T__39 || _la==T__40) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			}
			setState(1118);
			_la = _input.LA(1);
			if (_la==T__14) {
				{
				setState(1117);
				match(T__14);
				}
			}

			setState(1120);
			digitString();
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
		public DigitStringContext digitString() {
			return getRuleContext(DigitStringContext.class,0);
		}
		public List<DigitContext> digit() {
			return getRuleContexts(DigitContext.class);
		}
		public DigitContext digit(int i) {
			return getRuleContext(DigitContext.class,i);
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
		enterRule(_localctx, 170, RULE_regularDecimalReal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1123);
			_la = _input.LA(1);
			if (_la==T__14) {
				{
				setState(1122);
				match(T__14);
				}
			}

			setState(1128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38))) != 0)) {
				{
				{
				setState(1125);
				digit();
				}
				}
				setState(1130);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1131);
			match(T__25);
			setState(1132);
			digitString();
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
		public TerminalNode L_0X() { return getToken(CypherParser.L_0X, 0); }
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
		enterRule(_localctx, 172, RULE_symbolicName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1134);
			_la = _input.LA(1);
			if ( !(((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (UNION - 64)) | (1L << (ALL - 64)) | (1L << (OPTIONAL - 64)) | (1L << (MATCH - 64)) | (1L << (UNWIND - 64)) | (1L << (AS - 64)) | (1L << (MERGE - 64)) | (1L << (ON - 64)) | (1L << (CREATE - 64)) | (1L << (SET - 64)) | (1L << (DELETE - 64)) | (1L << (DETACH - 64)) | (1L << (REMOVE - 64)) | (1L << (WITH - 64)) | (1L << (DISTINCT - 64)) | (1L << (RETURN - 64)) | (1L << (ORDER - 64)) | (1L << (BY - 64)) | (1L << (L_SKIP - 64)) | (1L << (LIMIT - 64)) | (1L << (DESCENDING - 64)) | (1L << (DESC - 64)) | (1L << (ASCENDING - 64)) | (1L << (ASC - 64)) | (1L << (WHERE - 64)) | (1L << (OR - 64)) | (1L << (XOR - 64)) | (1L << (AND - 64)) | (1L << (NOT - 64)) | (1L << (IN - 64)) | (1L << (STARTS - 64)) | (1L << (ENDS - 64)) | (1L << (CONTAINS - 64)) | (1L << (IS - 64)) | (1L << (NULL - 64)) | (1L << (TRUE - 64)) | (1L << (FALSE - 64)) | (1L << (COUNT - 64)) | (1L << (FILTER - 64)) | (1L << (EXTRACT - 64)) | (1L << (ANY - 64)) | (1L << (NONE - 64)) | (1L << (SINGLE - 64)) | (1L << (L_0X - 64)) | (1L << (UnescapedSymbolicName - 64)) | (1L << (EscapedSymbolicName - 64)))) != 0)) ) {
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
		enterRule(_localctx, 174, RULE_ws);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1139);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,105,_ctx);
			while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1136);
					match(WHITESPACE);
					}
					} 
				}
				setState(1141);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,105,_ctx);
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
		enterRule(_localctx, 176, RULE_sp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1143); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1142);
					match(WHITESPACE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1145); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,106,_ctx);
			} while ( _alt!=2 && _alt!= ATN.INVALID_ALT_NUMBER );
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
		enterRule(_localctx, 178, RULE_leftArrowHead);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1147);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__21) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44))) != 0)) ) {
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
		enterRule(_localctx, 180, RULE_rightArrowHead);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1149);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__22) | (1L << T__45) | (1L << T__46) | (1L << T__47) | (1L << T__48))) != 0)) ) {
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
		enterRule(_localctx, 182, RULE_dash);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1151);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58) | (1L << T__59))) != 0)) ) {
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

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3s\u0484\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\3\2\3\2\3\2\3"+
		"\2\3\2\5\2\u00c0\n\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\7\5\u00cc"+
		"\n\5\f\5\16\5\u00cf\13\5\3\6\3\6\3\6\3\6\7\6\u00d5\n\6\f\6\16\6\u00d8"+
		"\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\5\7\u00e1\n\7\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\5\b\u00ec\n\b\3\t\3\t\5\t\u00f0\n\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\5\t\u00f8\n\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\7\13\u0108\n\13\f\13\16\13\u010b\13\13\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0119\n\f\3\r\3\r\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\7\16\u0123\n\16\f\16\16\16\u0126\13\16\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0137\n\17"+
		"\3\20\3\20\3\20\3\20\7\20\u013d\n\20\f\20\16\20\u0140\13\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\7\20\u0148\n\20\f\20\16\20\u014b\13\20\5\20\u014d"+
		"\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\7\21\u0157\n\21\f\21\16"+
		"\21\u015a\13\21\3\22\3\22\3\22\3\22\5\22\u0160\n\22\3\23\3\23\3\23\3\23"+
		"\3\23\5\23\u0167\n\23\3\23\3\23\3\23\3\23\5\23\u016d\n\23\5\23\u016f\n"+
		"\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u017b\n\24"+
		"\3\25\3\25\3\25\3\25\5\25\u0181\n\25\3\25\3\25\3\25\5\25\u0186\n\25\3"+
		"\25\3\25\3\25\5\25\u018b\n\25\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u0193"+
		"\n\26\f\26\16\26\u0196\13\26\3\26\3\26\3\26\3\26\3\26\3\26\7\26\u019e"+
		"\n\26\f\26\16\26\u01a1\13\26\5\26\u01a3\n\26\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\5\27\u01ac\n\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\7\30\u01b7\n\30\f\30\16\30\u01ba\13\30\3\31\3\31\3\31\3\31\3\32\3\32"+
		"\3\32\3\32\3\33\3\33\3\33\3\33\3\33\5\33\u01c9\n\33\5\33\u01cb\n\33\3"+
		"\34\3\34\3\34\3\34\3\35\3\35\3\35\7\35\u01d4\n\35\f\35\16\35\u01d7\13"+
		"\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\5\36\u01e0\n\36\3\37\3\37\3 \3"+
		" \3 \3 \7 \u01e8\n \f \16 \u01eb\13 \3 \3 \3 \3 \5 \u01f1\n \3!\3!\3!"+
		"\3!\3!\5!\u01f8\n!\3!\3!\3!\5!\u01fd\n!\3!\3!\3!\5!\u0202\n!\3!\3!\3\""+
		"\3\"\3\"\3\"\3#\3#\3#\3#\3#\5#\u020f\n#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#"+
		"\5#\u021b\n#\3#\3#\3#\3#\3#\3#\5#\u0223\n#\3#\3#\3#\3#\3#\3#\3#\3#\5#"+
		"\u022d\n#\3#\3#\3#\5#\u0232\n#\3$\3$\5$\u0236\n$\3$\5$\u0239\n$\3$\5$"+
		"\u023c\n$\3$\3$\5$\u0240\n$\3$\5$\u0243\n$\3$\3$\3%\3%\5%\u0249\n%\3&"+
		"\3&\3&\3&\3&\5&\u0250\n&\3&\3&\3&\7&\u0255\n&\f&\16&\u0258\13&\3\'\3\'"+
		"\3\'\3\'\7\'\u025e\n\'\f\'\16\'\u0261\13\'\3(\3(\3(\3)\3)\3)\3)\5)\u026a"+
		"\n)\3)\3)\3)\3)\3)\5)\u0271\n)\5)\u0273\n)\3*\3*\3+\3+\3,\3,\3-\3-\3-"+
		"\3-\3-\3-\7-\u0281\n-\f-\16-\u0284\13-\3.\3.\3.\3.\3.\3.\7.\u028c\n.\f"+
		".\16.\u028f\13.\3/\3/\3/\3/\3/\3/\7/\u0297\n/\f/\16/\u029a\13/\3\60\3"+
		"\60\3\60\3\60\7\60\u02a0\n\60\f\60\16\60\u02a3\13\60\3\60\3\60\3\61\3"+
		"\61\3\61\3\61\7\61\u02ab\n\61\f\61\16\61\u02ae\13\61\3\62\3\62\3\62\3"+
		"\62\3\62\3\62\3\62\3\62\3\62\3\62\3\62\7\62\u02bb\n\62\f\62\16\62\u02be"+
		"\13\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63"+
		"\3\63\3\63\3\63\7\63\u02d0\n\63\f\63\16\63\u02d3\13\63\3\64\3\64\3\64"+
		"\3\64\3\64\3\64\7\64\u02db\n\64\f\64\16\64\u02de\13\64\3\65\3\65\7\65"+
		"\u02e2\n\65\f\65\16\65\u02e5\13\65\3\65\3\65\3\66\3\66\3\66\3\66\3\66"+
		"\3\66\3\66\3\66\3\66\5\66\u02f2\n\66\3\66\3\66\5\66\u02f6\n\66\3\66\3"+
		"\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3"+
		"\66\3\66\3\66\3\66\3\66\3\66\5\66\u030d\n\66\3\66\3\66\3\66\3\66\3\66"+
		"\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\7\66\u031e\n\66\f\66"+
		"\16\66\u0321\13\66\3\67\3\67\3\67\7\67\u0326\n\67\f\67\16\67\u0329\13"+
		"\67\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\78"+
		"\u0340\n8\f8\168\u0343\138\38\38\38\38\38\38\38\38\38\38\38\38\38\38\3"+
		"8\38\38\38\38\38\58\u0359\n8\38\38\38\38\38\38\38\38\38\38\38\38\38\3"+
		"8\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\38\3"+
		"8\38\58\u0381\n8\39\39\39\39\39\39\39\39\39\39\39\39\39\39\39\39\39\3"+
		"9\39\39\39\39\39\39\39\39\39\39\59\u039f\n9\3:\3:\3:\3:\3:\3:\3;\3;\3"+
		";\3;\6;\u03ab\n;\r;\16;\u03ac\3<\3<\3<\3<\5<\u03b3\n<\3=\3=\3=\3=\3=\3"+
		"=\3>\3>\3>\3>\3>\5>\u03c0\n>\3>\3>\3>\3>\3>\7>\u03c7\n>\f>\16>\u03ca\13"+
		">\5>\u03cc\n>\3>\3>\3>\3?\3?\3@\3@\3@\3@\3@\3@\5@\u03d9\n@\3@\3@\3A\3"+
		"A\3A\3A\3A\3A\3A\5A\u03e4\nA\3B\3B\3C\3C\5C\u03ea\nC\3D\3D\3D\3D\3D\3"+
		"D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\7D\u03fd\nD\fD\16D\u0400\13D\5D\u0402"+
		"\nD\3D\3D\3E\3E\3E\3E\5E\u040a\nE\3E\3E\3E\3F\3F\3F\3F\6F\u0413\nF\rF"+
		"\16F\u0414\3G\3G\3H\3H\3H\5H\u041c\nH\3I\3I\3J\5J\u0421\nJ\3J\3J\3K\5"+
		"K\u0426\nK\3K\3K\3L\5L\u042b\nL\3L\3L\3M\3M\3M\3N\3N\5N\u0434\nN\3N\5"+
		"N\u0437\nN\3O\3O\3O\3P\6P\u043d\nP\rP\16P\u043e\3Q\6Q\u0442\nQ\rQ\16Q"+
		"\u0443\3R\6R\u0447\nR\rR\16R\u0448\3S\3S\3T\3T\3U\3U\5U\u0451\nU\3V\5"+
		"V\u0454\nV\3V\3V\6V\u0458\nV\rV\16V\u0459\3V\3V\5V\u045e\nV\3V\5V\u0461"+
		"\nV\3V\3V\3W\5W\u0466\nW\3W\7W\u0469\nW\fW\16W\u046c\13W\3W\3W\3W\3X\3"+
		"X\3Y\7Y\u0474\nY\fY\16Y\u0477\13Y\3Z\6Z\u047a\nZ\rZ\16Z\u047b\3[\3[\3"+
		"\\\3\\\3]\3]\3]\2\2^\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60"+
		"\62\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086"+
		"\u0088\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e"+
		"\u00a0\u00a2\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6"+
		"\u00b8\2\16\3\2VW\3\2XY\3\2\20\21\4\2\13\13\35\35\3\2 (\3\2 )\4\2 &))"+
		"\3\2*+\4\2Bnqq\4\2\30\30,/\4\2\31\31\60\63\4\2\21\21\64>\u04bc\2\u00ba"+
		"\3\2\2\2\4\u00c3\3\2\2\2\6\u00c5\3\2\2\2\b\u00c7\3\2\2\2\n\u00d0\3\2\2"+
		"\2\f\u00e0\3\2\2\2\16\u00eb\3\2\2\2\20\u00ef\3\2\2\2\22\u00f9\3\2\2\2"+
		"\24\u0101\3\2\2\2\26\u0118\3\2\2\2\30\u011a\3\2\2\2\32\u011e\3\2\2\2\34"+
		"\u0136\3\2\2\2\36\u014c\3\2\2\2 \u014e\3\2\2\2\"\u015f\3\2\2\2$\u016e"+
		"\3\2\2\2&\u017a\3\2\2\2(\u017c\3\2\2\2*\u01a2\3\2\2\2,\u01ab\3\2\2\2."+
		"\u01ad\3\2\2\2\60\u01bb\3\2\2\2\62\u01bf\3\2\2\2\64\u01ca\3\2\2\2\66\u01cc"+
		"\3\2\2\28\u01d0\3\2\2\2:\u01df\3\2\2\2<\u01e1\3\2\2\2>\u01f0\3\2\2\2@"+
		"\u01f2\3\2\2\2B\u0205\3\2\2\2D\u0231\3\2\2\2F\u0233\3\2\2\2H\u0248\3\2"+
		"\2\2J\u024a\3\2\2\2L\u0259\3\2\2\2N\u0262\3\2\2\2P\u0265\3\2\2\2R\u0274"+
		"\3\2\2\2T\u0276\3\2\2\2V\u0278\3\2\2\2X\u027a\3\2\2\2Z\u0285\3\2\2\2\\"+
		"\u0290\3\2\2\2^\u02a1\3\2\2\2`\u02a6\3\2\2\2b\u02af\3\2\2\2d\u02bf\3\2"+
		"\2\2f\u02d4\3\2\2\2h\u02e3\3\2\2\2j\u02e8\3\2\2\2l\u0322\3\2\2\2n\u0380"+
		"\3\2\2\2p\u039e\3\2\2\2r\u03a0\3\2\2\2t\u03a6\3\2\2\2v\u03ae\3\2\2\2x"+
		"\u03b4\3\2\2\2z\u03ba\3\2\2\2|\u03d0\3\2\2\2~\u03d2\3\2\2\2\u0080\u03dc"+
		"\3\2\2\2\u0082\u03e5\3\2\2\2\u0084\u03e9\3\2\2\2\u0086\u03eb\3\2\2\2\u0088"+
		"\u0405\3\2\2\2\u008a\u040e\3\2\2\2\u008c\u0416\3\2\2\2\u008e\u041b\3\2"+
		"\2\2\u0090\u041d\3\2\2\2\u0092\u0420\3\2\2\2\u0094\u0425\3\2\2\2\u0096"+
		"\u042a\3\2\2\2\u0098\u042e\3\2\2\2\u009a\u0436\3\2\2\2\u009c\u0438\3\2"+
		"\2\2\u009e\u043c\3\2\2\2\u00a0\u0441\3\2\2\2\u00a2\u0446\3\2\2\2\u00a4"+
		"\u044a\3\2\2\2\u00a6\u044c\3\2\2\2\u00a8\u0450\3\2\2\2\u00aa\u0453\3\2"+
		"\2\2\u00ac\u0465\3\2\2\2\u00ae\u0470\3\2\2\2\u00b0\u0475\3\2\2\2\u00b2"+
		"\u0479\3\2\2\2\u00b4\u047d\3\2\2\2\u00b6\u047f\3\2\2\2\u00b8\u0481\3\2"+
		"\2\2\u00ba\u00bb\5\u00b0Y\2\u00bb\u00bf\5\4\3\2\u00bc\u00bd\5\u00b0Y\2"+
		"\u00bd\u00be\7\3\2\2\u00be\u00c0\3\2\2\2\u00bf\u00bc\3\2\2\2\u00bf\u00c0"+
		"\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00c2\5\u00b0Y\2\u00c2\3\3\2\2\2\u00c3"+
		"\u00c4\5\6\4\2\u00c4\5\3\2\2\2\u00c5\u00c6\5\b\5\2\u00c6\7\3\2\2\2\u00c7"+
		"\u00cd\5\n\6\2\u00c8\u00c9\5\u00b0Y\2\u00c9\u00ca\5\f\7\2\u00ca\u00cc"+
		"\3\2\2\2\u00cb\u00c8\3\2\2\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd"+
		"\u00ce\3\2\2\2\u00ce\t\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0\u00d6\5\16\b"+
		"\2\u00d1\u00d2\5\u00b0Y\2\u00d2\u00d3\5\16\b\2\u00d3\u00d5\3\2\2\2\u00d4"+
		"\u00d1\3\2\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d6\u00d7\3\2"+
		"\2\2\u00d7\13\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9\u00da\7B\2\2\u00da\u00db"+
		"\5\u00b2Z\2\u00db\u00dc\7C\2\2\u00dc\u00dd\5\n\6\2\u00dd\u00e1\3\2\2\2"+
		"\u00de\u00df\7B\2\2\u00df\u00e1\5\n\6\2\u00e0\u00d9\3\2\2\2\u00e0\u00de"+
		"\3\2\2\2\u00e1\r\3\2\2\2\u00e2\u00ec\5\20\t\2\u00e3\u00ec\5\22\n\2\u00e4"+
		"\u00ec\5\24\13\2\u00e5\u00ec\5\30\r\2\u00e6\u00ec\5\32\16\2\u00e7\u00ec"+
		"\5\36\20\2\u00e8\u00ec\5 \21\2\u00e9\u00ec\5$\23\2\u00ea\u00ec\5&\24\2"+
		"\u00eb\u00e2\3\2\2\2\u00eb\u00e3\3\2\2\2\u00eb\u00e4\3\2\2\2\u00eb\u00e5"+
		"\3\2\2\2\u00eb\u00e6\3\2\2\2\u00eb\u00e7\3\2\2\2\u00eb\u00e8\3\2\2\2\u00eb"+
		"\u00e9\3\2\2\2\u00eb\u00ea\3\2\2\2\u00ec\17\3\2\2\2\u00ed\u00ee\7D\2\2"+
		"\u00ee\u00f0\5\u00b2Z\2\u00ef\u00ed\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0"+
		"\u00f1\3\2\2\2\u00f1\u00f2\7E\2\2\u00f2\u00f3\5\u00b0Y\2\u00f3\u00f7\5"+
		"8\35\2\u00f4\u00f5\5\u00b0Y\2\u00f5\u00f6\5\66\34\2\u00f6\u00f8\3\2\2"+
		"\2\u00f7\u00f4\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\21\3\2\2\2\u00f9\u00fa"+
		"\7F\2\2\u00fa\u00fb\5\u00b0Y\2\u00fb\u00fc\5V,\2\u00fc\u00fd\5\u00b2Z"+
		"\2\u00fd\u00fe\7G\2\2\u00fe\u00ff\5\u00b2Z\2\u00ff\u0100\5\u0082B\2\u0100"+
		"\23\3\2\2\2\u0101\u0102\7H\2\2\u0102\u0103\5\u00b0Y\2\u0103\u0109\5:\36"+
		"\2\u0104\u0105\5\u00b2Z\2\u0105\u0106\5\26\f\2\u0106\u0108\3\2\2\2\u0107"+
		"\u0104\3\2\2\2\u0108\u010b\3\2\2\2\u0109\u0107\3\2\2\2\u0109\u010a\3\2"+
		"\2\2\u010a\25\3\2\2\2\u010b\u0109\3\2\2\2\u010c\u010d\7I\2\2\u010d\u010e"+
		"\5\u00b2Z\2\u010e\u010f\7E\2\2\u010f\u0110\5\u00b2Z\2\u0110\u0111\5\32"+
		"\16\2\u0111\u0119\3\2\2\2\u0112\u0113\7I\2\2\u0113\u0114\5\u00b2Z\2\u0114"+
		"\u0115\7J\2\2\u0115\u0116\5\u00b2Z\2\u0116\u0117\5\32\16\2\u0117\u0119"+
		"\3\2\2\2\u0118\u010c\3\2\2\2\u0118\u0112\3\2\2\2\u0119\27\3\2\2\2\u011a"+
		"\u011b\7J\2\2\u011b\u011c\5\u00b0Y\2\u011c\u011d\58\35\2\u011d\31\3\2"+
		"\2\2\u011e\u011f\7K\2\2\u011f\u0124\5\34\17\2\u0120\u0121\7\4\2\2\u0121"+
		"\u0123\5\34\17\2\u0122\u0120\3\2\2\2\u0123\u0126\3\2\2\2\u0124\u0122\3"+
		"\2\2\2\u0124\u0125\3\2\2\2\u0125\33\3\2\2\2\u0126\u0124\3\2\2\2\u0127"+
		"\u0128\5\u008aF\2\u0128\u0129\7\5\2\2\u0129\u012a\5V,\2\u012a\u0137\3"+
		"\2\2\2\u012b\u012c\5\u0082B\2\u012c\u012d\7\5\2\2\u012d\u012e\5V,\2\u012e"+
		"\u0137\3\2\2\2\u012f\u0130\5\u0082B\2\u0130\u0131\7\6\2\2\u0131\u0132"+
		"\5V,\2\u0132\u0137\3\2\2\2\u0133\u0134\5\u0082B\2\u0134\u0135\5L\'\2\u0135"+
		"\u0137\3\2\2\2\u0136\u0127\3\2\2\2\u0136\u012b\3\2\2\2\u0136\u012f\3\2"+
		"\2\2\u0136\u0133\3\2\2\2\u0137\35\3\2\2\2\u0138\u0139\7L\2\2\u0139\u013e"+
		"\5V,\2\u013a\u013b\7\4\2\2\u013b\u013d\5V,\2\u013c\u013a\3\2\2\2\u013d"+
		"\u0140\3\2\2\2\u013e\u013c\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u014d\3\2"+
		"\2\2\u0140\u013e\3\2\2\2\u0141\u0142\7M\2\2\u0142\u0143\5\u00b2Z\2\u0143"+
		"\u0144\7L\2\2\u0144\u0149\5V,\2\u0145\u0146\7\4\2\2\u0146\u0148\5V,\2"+
		"\u0147\u0145\3\2\2\2\u0148\u014b\3\2\2\2\u0149\u0147\3\2\2\2\u0149\u014a"+
		"\3\2\2\2\u014a\u014d\3\2\2\2\u014b\u0149\3\2\2\2\u014c\u0138\3\2\2\2\u014c"+
		"\u0141\3\2\2\2\u014d\37\3\2\2\2\u014e\u014f\7N\2\2\u014f\u0150\5\u00b2"+
		"Z\2\u0150\u0158\5\"\22\2\u0151\u0152\5\u00b0Y\2\u0152\u0153\7\4\2\2\u0153"+
		"\u0154\5\u00b0Y\2\u0154\u0155\5\"\22\2\u0155\u0157\3\2\2\2\u0156\u0151"+
		"\3\2\2\2\u0157\u015a\3\2\2\2\u0158\u0156\3\2\2\2\u0158\u0159\3\2\2\2\u0159"+
		"!\3\2\2\2\u015a\u0158\3\2\2\2\u015b\u015c\5\u0082B\2\u015c\u015d\5L\'"+
		"\2\u015d\u0160\3\2\2\2\u015e\u0160\5\u008aF\2\u015f\u015b\3\2\2\2\u015f"+
		"\u015e\3\2\2\2\u0160#\3\2\2\2\u0161\u0162\7O\2\2\u0162\u0163\7P\2\2\u0163"+
		"\u0164\5\u00b2Z\2\u0164\u0166\5(\25\2\u0165\u0167\5\66\34\2\u0166\u0165"+
		"\3\2\2\2\u0166\u0167\3\2\2\2\u0167\u016f\3\2\2\2\u0168\u0169\7O\2\2\u0169"+
		"\u016a\5\u00b2Z\2\u016a\u016c\5(\25\2\u016b\u016d\5\66\34\2\u016c\u016b"+
		"\3\2\2\2\u016c\u016d\3\2\2\2\u016d\u016f\3\2\2\2\u016e\u0161\3\2\2\2\u016e"+
		"\u0168\3\2\2\2\u016f%\3\2\2\2\u0170\u0171\7Q\2\2\u0171\u0172\5\u00b2Z"+
		"\2\u0172\u0173\7P\2\2\u0173\u0174\5\u00b2Z\2\u0174\u0175\5(\25\2\u0175"+
		"\u017b\3\2\2\2\u0176\u0177\7Q\2\2\u0177\u0178\5\u00b2Z\2\u0178\u0179\5"+
		"(\25\2\u0179\u017b\3\2\2\2\u017a\u0170\3\2\2\2\u017a\u0176\3\2\2\2\u017b"+
		"\'\3\2\2\2\u017c\u0180\5*\26\2\u017d\u017e\5\u00b2Z\2\u017e\u017f\5.\30"+
		"\2\u017f\u0181\3\2\2\2\u0180\u017d\3\2\2\2\u0180\u0181\3\2\2\2\u0181\u0185"+
		"\3\2\2\2\u0182\u0183\5\u00b2Z\2\u0183\u0184\5\60\31\2\u0184\u0186\3\2"+
		"\2\2\u0185\u0182\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u018a\3\2\2\2\u0187"+
		"\u0188\5\u00b2Z\2\u0188\u0189\5\62\32\2\u0189\u018b\3\2\2\2\u018a\u0187"+
		"\3\2\2\2\u018a\u018b\3\2\2\2\u018b)\3\2\2\2\u018c\u0194\7\7\2\2\u018d"+
		"\u018e\5\u00b0Y\2\u018e\u018f\7\4\2\2\u018f\u0190\5\u00b0Y\2\u0190\u0191"+
		"\5,\27\2\u0191\u0193\3\2\2\2\u0192\u018d\3\2\2\2\u0193\u0196\3\2\2\2\u0194"+
		"\u0192\3\2\2\2\u0194\u0195\3\2\2\2\u0195\u01a3\3\2\2\2\u0196\u0194\3\2"+
		"\2\2\u0197\u019f\5,\27\2\u0198\u0199\5\u00b0Y\2\u0199\u019a\7\4\2\2\u019a"+
		"\u019b\5\u00b0Y\2\u019b\u019c\5,\27\2\u019c\u019e\3\2\2\2\u019d\u0198"+
		"\3\2\2\2\u019e\u01a1\3\2\2\2\u019f\u019d\3\2\2\2\u019f\u01a0\3\2\2\2\u01a0"+
		"\u01a3\3\2\2\2\u01a1\u019f\3\2\2\2\u01a2\u018c\3\2\2\2\u01a2\u0197\3\2"+
		"\2\2\u01a3+\3\2\2\2\u01a4\u01a5\5V,\2\u01a5\u01a6\5\u00b2Z\2\u01a6\u01a7"+
		"\7G\2\2\u01a7\u01a8\5\u00b2Z\2\u01a8\u01a9\5\u0082B\2\u01a9\u01ac\3\2"+
		"\2\2\u01aa\u01ac\5V,\2\u01ab\u01a4\3\2\2\2\u01ab\u01aa\3\2\2\2\u01ac-"+
		"\3\2\2\2\u01ad\u01ae\7R\2\2\u01ae\u01af\5\u00b2Z\2\u01af\u01b0\7S\2\2"+
		"\u01b0\u01b1\5\u00b2Z\2\u01b1\u01b8\5\64\33\2\u01b2\u01b3\7\4\2\2\u01b3"+
		"\u01b4\5\u00b0Y\2\u01b4\u01b5\5\64\33\2\u01b5\u01b7\3\2\2\2\u01b6\u01b2"+
		"\3\2\2\2\u01b7\u01ba\3\2\2\2\u01b8\u01b6\3\2\2\2\u01b8\u01b9\3\2\2\2\u01b9"+
		"/\3\2\2\2\u01ba\u01b8\3\2\2\2\u01bb\u01bc\7T\2\2\u01bc\u01bd\5\u00b2Z"+
		"\2\u01bd\u01be\5V,\2\u01be\61\3\2\2\2\u01bf\u01c0\7U\2\2\u01c0\u01c1\5"+
		"\u00b2Z\2\u01c1\u01c2\5V,\2\u01c2\63\3\2\2\2\u01c3\u01c4\5V,\2\u01c4\u01c5"+
		"\t\2\2\2\u01c5\u01cb\3\2\2\2\u01c6\u01c8\5V,\2\u01c7\u01c9\t\3\2\2\u01c8"+
		"\u01c7\3\2\2\2\u01c8\u01c9\3\2\2\2\u01c9\u01cb\3\2\2\2\u01ca\u01c3\3\2"+
		"\2\2\u01ca\u01c6\3\2\2\2\u01cb\65\3\2\2\2\u01cc\u01cd\7Z\2\2\u01cd\u01ce"+
		"\5\u00b2Z\2\u01ce\u01cf\5V,\2\u01cf\67\3\2\2\2\u01d0\u01d5\5:\36\2\u01d1"+
		"\u01d2\7\4\2\2\u01d2\u01d4\5:\36\2\u01d3\u01d1\3\2\2\2\u01d4\u01d7\3\2"+
		"\2\2\u01d5\u01d3\3\2\2\2\u01d5\u01d6\3\2\2\2\u01d69\3\2\2\2\u01d7\u01d5"+
		"\3\2\2\2\u01d8\u01d9\5\u0082B\2\u01d9\u01da\5\u00b0Y\2\u01da\u01db\7\5"+
		"\2\2\u01db\u01dc\5\u00b0Y\2\u01dc\u01dd\5<\37\2\u01dd\u01e0\3\2\2\2\u01de"+
		"\u01e0\5<\37\2\u01df\u01d8\3\2\2\2\u01df\u01de\3\2\2\2\u01e0;\3\2\2\2"+
		"\u01e1\u01e2\5> \2\u01e2=\3\2\2\2\u01e3\u01e9\5@!\2\u01e4\u01e5\5\u00b0"+
		"Y\2\u01e5\u01e6\5B\"\2\u01e6\u01e8\3\2\2\2\u01e7\u01e4\3\2\2\2\u01e8\u01eb"+
		"\3\2\2\2\u01e9\u01e7\3\2\2\2\u01e9\u01ea\3\2\2\2\u01ea\u01f1\3\2\2\2\u01eb"+
		"\u01e9\3\2\2\2\u01ec\u01ed\7\b\2\2\u01ed\u01ee\5> \2\u01ee\u01ef\7\t\2"+
		"\2\u01ef\u01f1\3\2\2\2\u01f0\u01e3\3\2\2\2\u01f0\u01ec\3\2\2\2\u01f1?"+
		"\3\2\2\2\u01f2\u01f3\7\b\2\2\u01f3\u01f7\5\u00b0Y\2\u01f4\u01f5\5\u0082"+
		"B\2\u01f5\u01f6\5\u00b0Y\2\u01f6\u01f8\3\2\2\2\u01f7\u01f4\3\2\2\2\u01f7"+
		"\u01f8\3\2\2\2\u01f8\u01fc\3\2\2\2\u01f9\u01fa\5L\'\2\u01fa\u01fb\5\u00b0"+
		"Y\2\u01fb\u01fd\3\2\2\2\u01fc\u01f9\3\2\2\2\u01fc\u01fd\3\2\2\2\u01fd"+
		"\u0201\3\2\2\2\u01fe\u01ff\5H%\2\u01ff\u0200\5\u00b0Y\2\u0200\u0202\3"+
		"\2\2\2\u0201\u01fe\3\2\2\2\u0201\u0202\3\2\2\2\u0202\u0203\3\2\2\2\u0203"+
		"\u0204\7\t\2\2\u0204A\3\2\2\2\u0205\u0206\5D#\2\u0206\u0207\5\u00b0Y\2"+
		"\u0207\u0208\5@!\2\u0208C\3\2\2\2\u0209\u020a\5\u00b4[\2\u020a\u020b\5"+
		"\u00b0Y\2\u020b\u020c\5\u00b8]\2\u020c\u020e\5\u00b0Y\2\u020d\u020f\5"+
		"F$\2\u020e\u020d\3\2\2\2\u020e\u020f\3\2\2\2\u020f\u0210\3\2\2\2\u0210"+
		"\u0211\5\u00b0Y\2\u0211\u0212\5\u00b8]\2\u0212\u0213\5\u00b0Y\2\u0213"+
		"\u0214\5\u00b6\\\2\u0214\u0232\3\2\2\2\u0215\u0216\5\u00b4[\2\u0216\u0217"+
		"\5\u00b0Y\2\u0217\u0218\5\u00b8]\2\u0218\u021a\5\u00b0Y\2\u0219\u021b"+
		"\5F$\2\u021a\u0219\3\2\2\2\u021a\u021b\3\2\2\2\u021b\u021c\3\2\2\2\u021c"+
		"\u021d\5\u00b0Y\2\u021d\u021e\5\u00b8]\2\u021e\u0232\3\2\2\2\u021f\u0220"+
		"\5\u00b8]\2\u0220\u0222\5\u00b0Y\2\u0221\u0223\5F$\2\u0222\u0221\3\2\2"+
		"\2\u0222\u0223\3\2\2\2\u0223\u0224\3\2\2\2\u0224\u0225\5\u00b0Y\2\u0225"+
		"\u0226\5\u00b8]\2\u0226\u0227\5\u00b0Y\2\u0227\u0228\5\u00b6\\\2\u0228"+
		"\u0232\3\2\2\2\u0229\u022a\5\u00b8]\2\u022a\u022c\5\u00b0Y\2\u022b\u022d"+
		"\5F$\2\u022c\u022b\3\2\2\2\u022c\u022d\3\2\2\2\u022d\u022e\3\2\2\2\u022e"+
		"\u022f\5\u00b0Y\2\u022f\u0230\5\u00b8]\2\u0230\u0232\3\2\2\2\u0231\u0209"+
		"\3\2\2\2\u0231\u0215\3\2\2\2\u0231\u021f\3\2\2\2\u0231\u0229\3\2\2\2\u0232"+
		"E\3\2\2\2\u0233\u0235\7\n\2\2\u0234\u0236\5\u0082B\2\u0235\u0234\3\2\2"+
		"\2\u0235\u0236\3\2\2\2\u0236\u0238\3\2\2\2\u0237\u0239\7\13\2\2\u0238"+
		"\u0237\3\2\2\2\u0238\u0239\3\2\2\2\u0239\u023b\3\2\2\2\u023a\u023c\5J"+
		"&\2\u023b\u023a\3\2\2\2\u023b\u023c\3\2\2\2\u023c\u023f\3\2\2\2\u023d"+
		"\u023e\7\7\2\2\u023e\u0240\5P)\2\u023f\u023d\3\2\2\2\u023f\u0240\3\2\2"+
		"\2\u0240\u0242\3\2\2\2\u0241\u0243\5H%\2\u0242\u0241\3\2\2\2\u0242\u0243"+
		"\3\2\2\2\u0243\u0244\3\2\2\2\u0244\u0245\7\f\2\2\u0245G\3\2\2\2\u0246"+
		"\u0249\5\u0086D\2\u0247\u0249\5\u0088E\2\u0248\u0246\3\2\2\2\u0248\u0247"+
		"\3\2\2\2\u0249I\3\2\2\2\u024a\u024b\7\r\2\2\u024b\u0256\5T+\2\u024c\u024d"+
		"\5\u00b0Y\2\u024d\u024f\7\16\2\2\u024e\u0250\7\r\2\2\u024f\u024e\3\2\2"+
		"\2\u024f\u0250\3\2\2\2\u0250\u0251\3\2\2\2\u0251\u0252\5\u00b0Y\2\u0252"+
		"\u0253\5T+\2\u0253\u0255\3\2\2\2\u0254\u024c\3\2\2\2\u0255\u0258\3\2\2"+
		"\2\u0256\u0254\3\2\2\2\u0256\u0257\3\2\2\2\u0257K\3\2\2\2\u0258\u0256"+
		"\3\2\2\2\u0259\u025f\5N(\2\u025a\u025b\5\u00b0Y\2\u025b\u025c\5N(\2\u025c"+
		"\u025e\3\2\2\2\u025d\u025a\3\2\2\2\u025e\u0261\3\2\2\2\u025f\u025d\3\2"+
		"\2\2\u025f\u0260\3\2\2\2\u0260M\3\2\2\2\u0261\u025f\3\2\2\2\u0262\u0263"+
		"\7\r\2\2\u0263\u0264\5R*\2\u0264O\3\2\2\2\u0265\u0269\5\u00b0Y\2\u0266"+
		"\u0267\5\u0090I\2\u0267\u0268\5\u00b0Y\2\u0268\u026a\3\2\2\2\u0269\u0266"+
		"\3\2\2\2\u0269\u026a\3\2\2\2\u026a\u0272\3\2\2\2\u026b\u026c\7\17\2\2"+
		"\u026c\u0270\5\u00b0Y\2\u026d\u026e\5\u0090I\2\u026e\u026f\5\u00b0Y\2"+
		"\u026f\u0271\3\2\2\2\u0270\u026d\3\2\2\2\u0270\u0271\3\2\2\2\u0271\u0273"+
		"\3\2\2\2\u0272\u026b\3\2\2\2\u0272\u0273\3\2\2\2\u0273Q\3\2\2\2\u0274"+
		"\u0275\5\u00aeX\2\u0275S\3\2\2\2\u0276\u0277\5\u00aeX\2\u0277U\3\2\2\2"+
		"\u0278\u0279\5X-\2\u0279W\3\2\2\2\u027a\u0282\5Z.\2\u027b\u027c\5\u00b2"+
		"Z\2\u027c\u027d\7[\2\2\u027d\u027e\5\u00b2Z\2\u027e\u027f\5Z.\2\u027f"+
		"\u0281\3\2\2\2\u0280\u027b\3\2\2\2\u0281\u0284\3\2\2\2\u0282\u0280\3\2"+
		"\2\2\u0282\u0283\3\2\2\2\u0283Y\3\2\2\2\u0284\u0282\3\2\2\2\u0285\u028d"+
		"\5\\/\2\u0286\u0287\5\u00b2Z\2\u0287\u0288\7\\\2\2\u0288\u0289\5\u00b2"+
		"Z\2\u0289\u028a\5\\/\2\u028a\u028c\3\2\2\2\u028b\u0286\3\2\2\2\u028c\u028f"+
		"\3\2\2\2\u028d\u028b\3\2\2\2\u028d\u028e\3\2\2\2\u028e[\3\2\2\2\u028f"+
		"\u028d\3\2\2\2\u0290\u0298\5^\60\2\u0291\u0292\5\u00b2Z\2\u0292\u0293"+
		"\7]\2\2\u0293\u0294\5\u00b2Z\2\u0294\u0295\5^\60\2\u0295\u0297\3\2\2\2"+
		"\u0296\u0291\3\2\2\2\u0297\u029a\3\2\2\2\u0298\u0296\3\2\2\2\u0298\u0299"+
		"\3\2\2\2\u0299]\3\2\2\2\u029a\u0298\3\2\2\2\u029b\u029c\5\u00b2Z\2\u029c"+
		"\u029d\7^\2\2\u029d\u029e\5\u00b2Z\2\u029e\u02a0\3\2\2\2\u029f\u029b\3"+
		"\2\2\2\u02a0\u02a3\3\2\2\2\u02a1\u029f\3\2\2\2\u02a1\u02a2\3\2\2\2\u02a2"+
		"\u02a4\3\2\2\2\u02a3\u02a1\3\2\2\2\u02a4\u02a5\5`\61\2\u02a5_\3\2\2\2"+
		"\u02a6\u02ac\5b\62\2\u02a7\u02a8\5\u00b0Y\2\u02a8\u02a9\5p9\2\u02a9\u02ab"+
		"\3\2\2\2\u02aa\u02a7\3\2\2\2\u02ab\u02ae\3\2\2\2\u02ac\u02aa\3\2\2\2\u02ac"+
		"\u02ad\3\2\2\2\u02ada\3\2\2\2\u02ae\u02ac\3\2\2\2\u02af\u02bc\5d\63\2"+
		"\u02b0\u02b1\5\u00b0Y\2\u02b1\u02b2\7\20\2\2\u02b2\u02b3\5\u00b0Y\2\u02b3"+
		"\u02b4\5d\63\2\u02b4\u02bb\3\2\2\2\u02b5\u02b6\5\u00b0Y\2\u02b6\u02b7"+
		"\7\21\2\2\u02b7\u02b8\5\u00b0Y\2\u02b8\u02b9\5d\63\2\u02b9\u02bb\3\2\2"+
		"\2\u02ba\u02b0\3\2\2\2\u02ba\u02b5\3\2\2\2\u02bb\u02be\3\2\2\2\u02bc\u02ba"+
		"\3\2\2\2\u02bc\u02bd\3\2\2\2\u02bdc\3\2\2\2\u02be\u02bc\3\2\2\2\u02bf"+
		"\u02d1\5f\64\2\u02c0\u02c1\5\u00b0Y\2\u02c1\u02c2\7\7\2\2\u02c2\u02c3"+
		"\5\u00b0Y\2\u02c3\u02c4\5f\64\2\u02c4\u02d0\3\2\2\2\u02c5\u02c6\5\u00b0"+
		"Y\2\u02c6\u02c7\7\22\2\2\u02c7\u02c8\5\u00b0Y\2\u02c8\u02c9\5f\64\2\u02c9"+
		"\u02d0\3\2\2\2\u02ca\u02cb\5\u00b0Y\2\u02cb\u02cc\7\23\2\2\u02cc\u02cd"+
		"\5\u00b0Y\2\u02cd\u02ce\5f\64\2\u02ce\u02d0\3\2\2\2\u02cf\u02c0\3\2\2"+
		"\2\u02cf\u02c5\3\2\2\2\u02cf\u02ca\3\2\2\2\u02d0\u02d3\3\2\2\2\u02d1\u02cf"+
		"\3\2\2\2\u02d1\u02d2\3\2\2\2\u02d2e\3\2\2\2\u02d3\u02d1\3\2\2\2\u02d4"+
		"\u02dc\5h\65\2\u02d5\u02d6\5\u00b0Y\2\u02d6\u02d7\7\24\2\2\u02d7\u02d8"+
		"\5\u00b0Y\2\u02d8\u02d9\5h\65\2\u02d9\u02db\3\2\2\2\u02da\u02d5\3\2\2"+
		"\2\u02db\u02de\3\2\2\2\u02dc\u02da\3\2\2\2\u02dc\u02dd\3\2\2\2\u02ddg"+
		"\3\2\2\2\u02de\u02dc\3\2\2\2\u02df\u02e0\t\4\2\2\u02e0\u02e2\5\u00b0Y"+
		"\2\u02e1\u02df\3\2\2\2\u02e2\u02e5\3\2\2\2\u02e3\u02e1\3\2\2\2\u02e3\u02e4"+
		"\3\2\2\2\u02e4\u02e6\3\2\2\2\u02e5\u02e3\3\2\2\2\u02e6\u02e7\5j\66\2\u02e7"+
		"i\3\2\2\2\u02e8\u031f\5l\67\2\u02e9\u02ea\5\u00b0Y\2\u02ea\u02eb\7\n\2"+
		"\2\u02eb\u02ec\5V,\2\u02ec\u02ed\7\f\2\2\u02ed\u031e\3\2\2\2\u02ee\u02ef"+
		"\5\u00b0Y\2\u02ef\u02f1\7\n\2\2\u02f0\u02f2\5V,\2\u02f1\u02f0\3\2\2\2"+
		"\u02f1\u02f2\3\2\2\2\u02f2\u02f3\3\2\2\2\u02f3\u02f5\7\17\2\2\u02f4\u02f6"+
		"\5V,\2\u02f5\u02f4\3\2\2\2\u02f5\u02f6\3\2\2\2\u02f6\u02f7\3\2\2\2\u02f7"+
		"\u02f8\7\f\2\2\u02f8\u031e\3\2\2\2\u02f9\u02fa\5\u00b0Y\2\u02fa\u02fb"+
		"\7\25\2\2\u02fb\u030d\3\2\2\2\u02fc\u02fd\5\u00b2Z\2\u02fd\u02fe\7_\2"+
		"\2\u02fe\u030d\3\2\2\2\u02ff\u0300\5\u00b2Z\2\u0300\u0301\7`\2\2\u0301"+
		"\u0302\5\u00b2Z\2\u0302\u0303\7O\2\2\u0303\u030d\3\2\2\2\u0304\u0305\5"+
		"\u00b2Z\2\u0305\u0306\7a\2\2\u0306\u0307\5\u00b2Z\2\u0307\u0308\7O\2\2"+
		"\u0308\u030d\3\2\2\2\u0309\u030a\5\u00b2Z\2\u030a\u030b\7b\2\2\u030b\u030d"+
		"\3\2\2\2\u030c\u02f9\3\2\2\2\u030c\u02fc\3\2\2\2\u030c\u02ff\3\2\2\2\u030c"+
		"\u0304\3\2\2\2\u030c\u0309\3\2\2\2\u030d\u030e\3\2\2\2\u030e\u030f\5\u00b0"+
		"Y\2\u030f\u0310\5l\67\2\u0310\u031e\3\2\2\2\u0311\u0312\5\u00b2Z\2\u0312"+
		"\u0313\7c\2\2\u0313\u0314\5\u00b2Z\2\u0314\u0315\7d\2\2\u0315\u031e\3"+
		"\2\2\2\u0316\u0317\5\u00b2Z\2\u0317\u0318\7c\2\2\u0318\u0319\5\u00b2Z"+
		"\2\u0319\u031a\7^\2\2\u031a\u031b\5\u00b2Z\2\u031b\u031c\7d\2\2\u031c"+
		"\u031e\3\2\2\2\u031d\u02e9\3\2\2\2\u031d\u02ee\3\2\2\2\u031d\u030c\3\2"+
		"\2\2\u031d\u0311\3\2\2\2\u031d\u0316\3\2\2\2\u031e\u0321\3\2\2\2\u031f"+
		"\u031d\3\2\2\2\u031f\u0320\3\2\2\2\u0320k\3\2\2\2\u0321\u031f\3\2\2\2"+
		"\u0322\u0327\5n8\2\u0323\u0326\5\u0080A\2\u0324\u0326\5L\'\2\u0325\u0323"+
		"\3\2\2\2\u0325\u0324\3\2\2\2\u0326\u0329\3\2\2\2\u0327\u0325\3\2\2\2\u0327"+
		"\u0328\3\2\2\2\u0328m\3\2\2\2\u0329\u0327\3\2\2\2\u032a\u0381\5\u0084"+
		"C\2\u032b\u0381\7?\2\2\u032c\u0381\5\u0088E\2\u032d\u0381\7e\2\2\u032e"+
		"\u0381\7f\2\2\u032f\u0381\7d\2\2\u0330\u0331\7g\2\2\u0331\u0332\7\b\2"+
		"\2\u0332\u0333\7\7\2\2\u0333\u0381\7\t\2\2\u0334\u0381\5\u0086D\2\u0335"+
		"\u0381\5~@\2\u0336\u0337\7\n\2\2\u0337\u0338\5\u00b0Y\2\u0338\u0339\5"+
		"V,\2\u0339\u0341\5\u00b0Y\2\u033a\u033b\7\4\2\2\u033b\u033c\5\u00b0Y\2"+
		"\u033c\u033d\5V,\2\u033d\u033e\5\u00b0Y\2\u033e\u0340\3\2\2\2\u033f\u033a"+
		"\3\2\2\2\u0340\u0343\3\2\2\2\u0341\u033f\3\2\2\2\u0341\u0342\3\2\2\2\u0342"+
		"\u0344\3\2\2\2\u0343\u0341\3\2\2\2\u0344\u0345\7\f\2\2\u0345\u0381\3\2"+
		"\2\2\u0346\u0347\7h\2\2\u0347\u0348\5\u00b0Y\2\u0348\u0349\7\b\2\2\u0349"+
		"\u034a\5\u00b0Y\2\u034a\u034b\5v<\2\u034b\u034c\5\u00b0Y\2\u034c\u034d"+
		"\7\t\2\2\u034d\u0381\3\2\2\2\u034e\u034f\7i\2\2\u034f\u0350\5\u00b0Y\2"+
		"\u0350\u0351\7\b\2\2\u0351\u0352\5\u00b0Y\2\u0352\u0353\5v<\2\u0353\u0358"+
		"\5\u00b0Y\2\u0354\u0355\5\u00b0Y\2\u0355\u0356\7\16\2\2\u0356\u0357\5"+
		"V,\2\u0357\u0359\3\2\2\2\u0358\u0354\3\2\2\2\u0358\u0359\3\2\2\2\u0359"+
		"\u035a\3\2\2\2\u035a\u035b\7\t\2\2\u035b\u0381\3\2\2\2\u035c\u035d\7C"+
		"\2\2\u035d\u035e\5\u00b0Y\2\u035e\u035f\7\b\2\2\u035f\u0360\5\u00b0Y\2"+
		"\u0360\u0361\5v<\2\u0361\u0362\5\u00b0Y\2\u0362\u0363\7\t\2\2\u0363\u0381"+
		"\3\2\2\2\u0364\u0365\7j\2\2\u0365\u0366\5\u00b0Y\2\u0366\u0367\7\b\2\2"+
		"\u0367\u0368\5\u00b0Y\2\u0368\u0369\5v<\2\u0369\u036a\5\u00b0Y\2\u036a"+
		"\u036b\7\t\2\2\u036b\u0381\3\2\2\2\u036c\u036d\7k\2\2\u036d\u036e\5\u00b0"+
		"Y\2\u036e\u036f\7\b\2\2\u036f\u0370\5\u00b0Y\2\u0370\u0371\5v<\2\u0371"+
		"\u0372\5\u00b0Y\2\u0372\u0373\7\t\2\2\u0373\u0381\3\2\2\2\u0374\u0375"+
		"\7l\2\2\u0375\u0376\5\u00b0Y\2\u0376\u0377\7\b\2\2\u0377\u0378\5\u00b0"+
		"Y\2\u0378\u0379\5v<\2\u0379\u037a\5\u00b0Y\2\u037a\u037b\7\t\2\2\u037b"+
		"\u0381\3\2\2\2\u037c\u0381\5t;\2\u037d\u0381\5r:\2\u037e\u0381\5z>\2\u037f"+
		"\u0381\5\u0082B\2\u0380\u032a\3\2\2\2\u0380\u032b\3\2\2\2\u0380\u032c"+
		"\3\2\2\2\u0380\u032d\3\2\2\2\u0380\u032e\3\2\2\2\u0380\u032f\3\2\2\2\u0380"+
		"\u0330\3\2\2\2\u0380\u0334\3\2\2\2\u0380\u0335\3\2\2\2\u0380\u0336\3\2"+
		"\2\2\u0380\u0346\3\2\2\2\u0380\u034e\3\2\2\2\u0380\u035c\3\2\2\2\u0380"+
		"\u0364\3\2\2\2\u0380\u036c\3\2\2\2\u0380\u0374\3\2\2\2\u0380\u037c\3\2"+
		"\2\2\u0380\u037d\3\2\2\2\u0380\u037e\3\2\2\2\u0380\u037f\3\2\2\2\u0381"+
		"o\3\2\2\2\u0382\u0383\7\5\2\2\u0383\u0384\5\u00b0Y\2\u0384\u0385\5b\62"+
		"\2\u0385\u039f\3\2\2\2\u0386\u0387\7\26\2\2\u0387\u0388\5\u00b0Y\2\u0388"+
		"\u0389\5b\62\2\u0389\u039f\3\2\2\2\u038a\u038b\7\27\2\2\u038b\u038c\5"+
		"\u00b0Y\2\u038c\u038d\5b\62\2\u038d\u039f\3\2\2\2\u038e\u038f\7\30\2\2"+
		"\u038f\u0390\5\u00b0Y\2\u0390\u0391\5b\62\2\u0391\u039f\3\2\2\2\u0392"+
		"\u0393\7\31\2\2\u0393\u0394\5\u00b0Y\2\u0394\u0395\5b\62\2\u0395\u039f"+
		"\3\2\2\2\u0396\u0397\7\32\2\2\u0397\u0398\5\u00b0Y\2\u0398\u0399\5b\62"+
		"\2\u0399\u039f\3\2\2\2\u039a\u039b\7\33\2\2\u039b\u039c\5\u00b0Y\2\u039c"+
		"\u039d\5b\62\2\u039d\u039f\3\2\2\2\u039e\u0382\3\2\2\2\u039e\u0386\3\2"+
		"\2\2\u039e\u038a\3\2\2\2\u039e\u038e\3\2\2\2\u039e\u0392\3\2\2\2\u039e"+
		"\u0396\3\2\2\2\u039e\u039a\3\2\2\2\u039fq\3\2\2\2\u03a0\u03a1\7\b\2\2"+
		"\u03a1\u03a2\5\u00b0Y\2\u03a2\u03a3\5V,\2\u03a3\u03a4\5\u00b0Y\2\u03a4"+
		"\u03a5\7\t\2\2\u03a5s\3\2\2\2\u03a6\u03aa\5@!\2\u03a7\u03a8\5\u00b0Y\2"+
		"\u03a8\u03a9\5B\"\2\u03a9\u03ab\3\2\2\2\u03aa\u03a7\3\2\2\2\u03ab\u03ac"+
		"\3\2\2\2\u03ac\u03aa\3\2\2\2\u03ac\u03ad\3\2\2\2\u03adu\3\2\2\2\u03ae"+
		"\u03b2\5x=\2\u03af\u03b0\5\u00b0Y\2\u03b0\u03b1\5\66\34\2\u03b1\u03b3"+
		"\3\2\2\2\u03b2\u03af\3\2\2\2\u03b2\u03b3\3\2\2\2\u03b3w\3\2\2\2\u03b4"+
		"\u03b5\5\u0082B\2\u03b5\u03b6\5\u00b2Z\2\u03b6\u03b7\7_\2\2\u03b7\u03b8"+
		"\5\u00b2Z\2\u03b8\u03b9\5V,\2\u03b9y\3\2\2\2\u03ba\u03bb\5|?\2\u03bb\u03bc"+
		"\5\u00b0Y\2\u03bc\u03bd\7\b\2\2\u03bd\u03bf\5\u00b0Y\2\u03be\u03c0\7P"+
		"\2\2\u03bf\u03be\3\2\2\2\u03bf\u03c0\3\2\2\2\u03c0\u03cb\3\2\2\2\u03c1"+
		"\u03c8\5V,\2\u03c2\u03c3\7\4\2\2\u03c3\u03c4\5\u00b0Y\2\u03c4\u03c5\5"+
		"V,\2\u03c5\u03c7\3\2\2\2\u03c6\u03c2\3\2\2\2\u03c7\u03ca\3\2\2\2\u03c8"+
		"\u03c6\3\2\2\2\u03c8\u03c9\3\2\2\2\u03c9\u03cc\3\2\2\2\u03ca\u03c8\3\2"+
		"\2\2\u03cb\u03c1\3\2\2\2\u03cb\u03cc\3\2\2\2\u03cc\u03cd\3\2\2\2\u03cd"+
		"\u03ce\5\u00b0Y\2\u03ce\u03cf\7\t\2\2\u03cf{\3\2\2\2\u03d0\u03d1\5\u00ae"+
		"X\2\u03d1}\3\2\2\2\u03d2\u03d3\7\n\2\2\u03d3\u03d8\5v<\2\u03d4\u03d5\5"+
		"\u00b0Y\2\u03d5\u03d6\7\16\2\2\u03d6\u03d7\5V,\2\u03d7\u03d9\3\2\2\2\u03d8"+
		"\u03d4\3\2\2\2\u03d8\u03d9\3\2\2\2\u03d9\u03da\3\2\2\2\u03da\u03db\7\f"+
		"\2\2\u03db\177\3\2\2\2\u03dc\u03dd\5\u00b0Y\2\u03dd\u03de\7\34\2\2\u03de"+
		"\u03e3\5\u00b0Y\2\u03df\u03e0\5\u008cG\2\u03e0\u03e1\t\5\2\2\u03e1\u03e4"+
		"\3\2\2\2\u03e2\u03e4\5\u008cG\2\u03e3\u03df\3\2\2\2\u03e3\u03e2\3\2\2"+
		"\2\u03e4\u0081\3\2\2\2\u03e5\u03e6\5\u00aeX\2\u03e6\u0083\3\2\2\2\u03e7"+
		"\u03ea\5\u00a8U\2\u03e8\u03ea\5\u008eH\2\u03e9\u03e7\3\2\2\2\u03e9\u03e8"+
		"\3\2\2\2\u03ea\u0085\3\2\2\2\u03eb\u03ec\7\36\2\2\u03ec\u0401\5\u00b0"+
		"Y\2\u03ed\u03ee\5\u008cG\2\u03ee\u03ef\5\u00b0Y\2\u03ef\u03f0\7\r\2\2"+
		"\u03f0\u03f1\5\u00b0Y\2\u03f1\u03f2\5V,\2\u03f2\u03fe\5\u00b0Y\2\u03f3"+
		"\u03f4\7\4\2\2\u03f4\u03f5\5\u00b0Y\2\u03f5\u03f6\5\u008cG\2\u03f6\u03f7"+
		"\5\u00b0Y\2\u03f7\u03f8\7\r\2\2\u03f8\u03f9\5\u00b0Y\2\u03f9\u03fa\5V"+
		",\2\u03fa\u03fb\5\u00b0Y\2\u03fb\u03fd\3\2\2\2\u03fc\u03f3\3\2\2\2\u03fd"+
		"\u0400\3\2\2\2\u03fe\u03fc\3\2\2\2\u03fe\u03ff\3\2\2\2\u03ff\u0402\3\2"+
		"\2\2\u0400\u03fe\3\2\2\2\u0401\u03ed\3\2\2\2\u0401\u0402\3\2\2\2\u0402"+
		"\u0403\3\2\2\2\u0403\u0404\7\37\2\2\u0404\u0087\3\2\2\2\u0405\u0406\7"+
		"\36\2\2\u0406\u0409\5\u00b0Y\2\u0407\u040a\5\u00aeX\2\u0408\u040a\5\u009a"+
		"N\2\u0409\u0407\3\2\2\2\u0409\u0408\3\2\2\2\u040a\u040b\3\2\2\2\u040b"+
		"\u040c\5\u00b0Y\2\u040c\u040d\7\37\2\2\u040d\u0089\3\2\2\2\u040e\u0412"+
		"\5n8\2\u040f\u0410\5\u00b0Y\2\u0410\u0411\5\u0080A\2\u0411\u0413\3\2\2"+
		"\2\u0412\u040f\3\2\2\2\u0413\u0414\3\2\2\2\u0414\u0412\3\2\2\2\u0414\u0415"+
		"\3\2\2\2\u0415\u008b\3\2\2\2\u0416\u0417\5\u00aeX\2\u0417\u008d\3\2\2"+
		"\2\u0418\u041c\5\u0092J\2\u0419\u041c\5\u0096L\2\u041a\u041c\5\u0094K"+
		"\2\u041b\u0418\3\2\2\2\u041b\u0419\3\2\2\2\u041b\u041a\3\2\2\2\u041c\u008f"+
		"\3\2\2\2\u041d\u041e\5\u009aN\2\u041e\u0091\3\2\2\2\u041f\u0421\7\21\2"+
		"\2\u0420\u041f\3\2\2\2\u0420\u0421\3\2\2\2\u0421\u0422\3\2\2\2\u0422\u0423"+
		"\5\u0098M\2\u0423\u0093\3\2\2\2\u0424\u0426\7\21\2\2\u0425\u0424\3\2\2"+
		"\2\u0425\u0426\3\2\2\2\u0426\u0427\3\2\2\2\u0427\u0428\5\u009aN\2\u0428"+
		"\u0095\3\2\2\2\u0429\u042b\7\21\2\2\u042a\u0429\3\2\2\2\u042a\u042b\3"+
		"\2\2\2\u042b\u042c\3\2\2\2\u042c\u042d\5\u009cO\2\u042d\u0097\3\2\2\2"+
		"\u042e\u042f\7m\2\2\u042f\u0430\5\u009eP\2\u0430\u0099\3\2\2\2\u0431\u0433"+
		"\t\6\2\2\u0432\u0434\5\u00a0Q\2\u0433\u0432\3\2\2\2\u0433\u0434\3\2\2"+
		"\2\u0434\u0437\3\2\2\2\u0435\u0437\7)\2\2\u0436\u0431\3\2\2\2\u0436\u0435"+
		"\3\2\2\2\u0437\u009b\3\2\2\2\u0438\u0439\7)\2\2\u0439\u043a\5\u00a2R\2"+
		"\u043a\u009d\3\2\2\2\u043b\u043d\7A\2\2\u043c\u043b\3\2\2\2\u043d\u043e"+
		"\3\2\2\2\u043e\u043c\3\2\2\2\u043e\u043f\3\2\2\2\u043f\u009f\3\2\2\2\u0440"+
		"\u0442\5\u00a4S\2\u0441\u0440\3\2\2\2\u0442\u0443\3\2\2\2\u0443\u0441"+
		"\3\2\2\2\u0443\u0444\3\2\2\2\u0444\u00a1\3\2\2\2\u0445\u0447\5\u00a6T"+
		"\2\u0446\u0445\3\2\2\2\u0447\u0448\3\2\2\2\u0448\u0446\3\2\2\2\u0448\u0449"+
		"\3\2\2\2\u0449\u00a3\3\2\2\2\u044a\u044b\t\7\2\2\u044b\u00a5\3\2\2\2\u044c"+
		"\u044d\t\b\2\2\u044d\u00a7\3\2\2\2\u044e\u0451\5\u00aaV\2\u044f\u0451"+
		"\5\u00acW\2\u0450\u044e\3\2\2\2\u0450\u044f\3\2\2\2\u0451\u00a9\3\2\2"+
		"\2\u0452\u0454\7\21\2\2\u0453\u0452\3\2\2\2\u0453\u0454\3\2\2\2\u0454"+
		"\u0457\3\2\2\2\u0455\u0458\5\u00a4S\2\u0456\u0458\7\34\2\2\u0457\u0455"+
		"\3\2\2\2\u0457\u0456\3\2\2\2\u0458\u0459\3\2\2\2\u0459\u0457\3\2\2\2\u0459"+
		"\u045a\3\2\2\2\u045a\u045d\3\2\2\2\u045b\u045e\t\t\2\2\u045c\u045e\t\t"+
		"\2\2\u045d\u045b\3\2\2\2\u045d\u045c\3\2\2\2\u045e\u0460\3\2\2\2\u045f"+
		"\u0461\7\21\2\2\u0460\u045f\3\2\2\2\u0460\u0461\3\2\2\2\u0461\u0462\3"+
		"\2\2\2\u0462\u0463\5\u00a0Q\2\u0463\u00ab\3\2\2\2\u0464\u0466\7\21\2\2"+
		"\u0465\u0464\3\2\2\2\u0465\u0466\3\2\2\2\u0466\u046a\3\2\2\2\u0467\u0469"+
		"\5\u00a4S\2\u0468\u0467\3\2\2\2\u0469\u046c\3\2\2\2\u046a\u0468\3\2\2"+
		"\2\u046a\u046b\3\2\2\2\u046b\u046d\3\2\2\2\u046c\u046a\3\2\2\2\u046d\u046e"+
		"\7\34\2\2\u046e\u046f\5\u00a0Q\2\u046f\u00ad\3\2\2\2\u0470\u0471\t\n\2"+
		"\2\u0471\u00af\3\2\2\2\u0472\u0474\7r\2\2\u0473\u0472\3\2\2\2\u0474\u0477"+
		"\3\2\2\2\u0475\u0473\3\2\2\2\u0475\u0476\3\2\2\2\u0476\u00b1\3\2\2\2\u0477"+
		"\u0475\3\2\2\2\u0478\u047a\7r\2\2\u0479\u0478\3\2\2\2\u047a\u047b\3\2"+
		"\2\2\u047b\u0479\3\2\2\2\u047b\u047c\3\2\2\2\u047c\u00b3\3\2\2\2\u047d"+
		"\u047e\t\13\2\2\u047e\u00b5\3\2\2\2\u047f\u0480\t\f\2\2\u0480\u00b7\3"+
		"\2\2\2\u0481\u0482\t\r\2\2\u0482\u00b9\3\2\2\2m\u00bf\u00cd\u00d6\u00e0"+
		"\u00eb\u00ef\u00f7\u0109\u0118\u0124\u0136\u013e\u0149\u014c\u0158\u015f"+
		"\u0166\u016c\u016e\u017a\u0180\u0185\u018a\u0194\u019f\u01a2\u01ab\u01b8"+
		"\u01c8\u01ca\u01d5\u01df\u01e9\u01f0\u01f7\u01fc\u0201\u020e\u021a\u0222"+
		"\u022c\u0231\u0235\u0238\u023b\u023f\u0242\u0248\u024f\u0256\u025f\u0269"+
		"\u0270\u0272\u0282\u028d\u0298\u02a1\u02ac\u02ba\u02bc\u02cf\u02d1\u02dc"+
		"\u02e3\u02f1\u02f5\u030c\u031d\u031f\u0325\u0327\u0341\u0358\u0380\u039e"+
		"\u03ac\u03b2\u03bf\u03c8\u03cb\u03d8\u03e3\u03e9\u03fe\u0401\u0409\u0414"+
		"\u041b\u0420\u0425\u042a\u0433\u0436\u043e\u0443\u0448\u0450\u0453\u0457"+
		"\u0459\u045d\u0460\u0465\u046a\u0475\u047b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}