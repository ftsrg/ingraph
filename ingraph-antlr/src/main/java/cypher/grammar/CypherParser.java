package cypher.grammar;
import java.util.List;

import org.antlr.v4.runtime.NoViableAltException;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuntimeMetaData;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.VocabularyImpl;
// Generated from /home/szarnyasg/git/openCypher/grammar/generated/Cypher.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNDeserializer;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CypherParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

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
		T__59=60, StringLiteral=61, EscapedChar=62, HexDigit=63, CYPHER=64, EXPLAIN=65, 
		PROFILE=66, USING=67, PERIODIC=68, COMMIT=69, UNION=70, ALL=71, CREATE=72, 
		DROP=73, INDEX=74, ON=75, CONSTRAINT=76, ASSERT=77, IS=78, UNIQUE=79, 
		EXISTS=80, LOAD=81, CSV=82, WITH=83, HEADERS=84, FROM=85, AS=86, FIELDTERMINATOR=87, 
		OPTIONAL=88, MATCH=89, UNWIND=90, MERGE=91, SET=92, DELETE=93, DETACH=94, 
		REMOVE=95, FOREACH=96, IN=97, DISTINCT=98, RETURN=99, ORDER=100, BY=101, 
		L_SKIP=102, LIMIT=103, DESCENDING=104, DESC=105, ASCENDING=106, ASC=107, 
		JOIN=108, SCAN=109, START=110, NODE=111, RELATIONSHIP=112, REL=113, WHERE=114, 
		SHORTESTPATH=115, ALLSHORTESTPATHS=116, OR=117, XOR=118, AND=119, NOT=120, 
		STARTS=121, ENDS=122, CONTAINS=123, NULL=124, TRUE=125, FALSE=126, COUNT=127, 
		FILTER=128, EXTRACT=129, ANY=130, NONE=131, SINGLE=132, REDUCE=133, CASE=134, 
		ELSE=135, END=136, WHEN=137, THEN=138, L_0X=139, UnescapedSymbolicName=140, 
		IdentifierStart=141, IdentifierPart=142, EscapedSymbolicName=143, WHITESPACE=144, 
		Comment=145;
	public static final int
		RULE_cypher = 0, RULE_queryOptions = 1, RULE_anyCypherOption = 2, RULE_cypherOption = 3, 
		RULE_versionNumber = 4, RULE_explain = 5, RULE_profile = 6, RULE_configurationOption = 7, 
		RULE_statement = 8, RULE_query = 9, RULE_regularQuery = 10, RULE_bulkImportQuery = 11, 
		RULE_singleQuery = 12, RULE_periodicCommitHint = 13, RULE_loadCSVQuery = 14, 
		RULE_union = 15, RULE_clause = 16, RULE_command = 17, RULE_createUniqueConstraint = 18, 
		RULE_createNodePropertyExistenceConstraint = 19, RULE_createRelationshipPropertyExistenceConstraint = 20, 
		RULE_createIndex = 21, RULE_dropUniqueConstraint = 22, RULE_dropNodePropertyExistenceConstraint = 23, 
		RULE_dropRelationshipPropertyExistenceConstraint = 24, RULE_dropIndex = 25, 
		RULE_index = 26, RULE_uniqueConstraint = 27, RULE_nodePropertyExistenceConstraint = 28, 
		RULE_relationshipPropertyExistenceConstraint = 29, RULE_relationshipPatternSyntax = 30, 
		RULE_loadCSV = 31, RULE_match = 32, RULE_unwind = 33, RULE_merge = 34, 
		RULE_mergeAction = 35, RULE_create = 36, RULE_set = 37, RULE_setItem = 38, 
		RULE_delete = 39, RULE_remove = 40, RULE_removeItem = 41, RULE_foreach = 42, 
		RULE_with = 43, RULE_return = 44, RULE_returnBody = 45, RULE_returnItems = 46, 
		RULE_returnItem = 47, RULE_order = 48, RULE_skip = 49, RULE_limit = 50, 
		RULE_sortItem = 51, RULE_hint = 52, RULE_start = 53, RULE_startPoint = 54, 
		RULE_lookup = 55, RULE_nodeLookup = 56, RULE_relationshipLookup = 57, 
		RULE_identifiedIndexLookup = 58, RULE_indexQuery = 59, RULE_idLookup = 60, 
		RULE_literalIds = 61, RULE_where = 62, RULE_pattern = 63, RULE_patternPart = 64, 
		RULE_anonymousPatternPart = 65, RULE_shortestPathPattern = 66, RULE_patternElement = 67, 
		RULE_nodePattern = 68, RULE_patternElementChain = 69, RULE_relationshipPattern = 70, 
		RULE_relationshipDetail = 71, RULE_properties = 72, RULE_relType = 73, 
		RULE_relationshipTypes = 74, RULE_nodeLabels = 75, RULE_nodeLabel = 76, 
		RULE_rangeLiteral = 77, RULE_labelName = 78, RULE_relTypeName = 79, RULE_expression = 80, 
		RULE_expression12 = 81, RULE_expression11 = 82, RULE_expression10 = 83, 
		RULE_expression9 = 84, RULE_expression8 = 85, RULE_expression7 = 86, RULE_expression6 = 87, 
		RULE_expression5 = 88, RULE_expression4 = 89, RULE_expression3 = 90, RULE_expression2 = 91, 
		RULE_atom = 92, RULE_reduce = 93, RULE_partialComparisonExpression = 94, 
		RULE_parenthesizedExpression = 95, RULE_relationshipsPattern = 96, RULE_filterExpression = 97, 
		RULE_idInColl = 98, RULE_functionInvocation = 99, RULE_functionName = 100, 
		RULE_listComprehension = 101, RULE_propertyLookup = 102, RULE_caseExpression = 103, 
		RULE_caseAlternatives = 104, RULE_variable = 105, RULE_numberLiteral = 106, 
		RULE_mapLiteral = 107, RULE_parameter = 108, RULE_propertyExpression = 109, 
		RULE_propertyKeyName = 110, RULE_signedIntegerLiteral = 111, RULE_unsignedIntegerLiteral = 112, 
		RULE_hexInteger = 113, RULE_decimalInteger = 114, RULE_octalInteger = 115, 
		RULE_unsignedHexInteger = 116, RULE_unsignedDecimalInteger = 117, RULE_unsignedOctalInteger = 118, 
		RULE_hexString = 119, RULE_digitString = 120, RULE_octalString = 121, 
		RULE_digit = 122, RULE_octDigit = 123, RULE_doubleLiteral = 124, RULE_exponentDecimalReal = 125, 
		RULE_regularDecimalReal = 126, RULE_symbolicName = 127, RULE_ws = 128, 
		RULE_sp = 129, RULE_leftArrowHead = 130, RULE_rightArrowHead = 131, RULE_dash = 132;
	public static final String[] ruleNames = {
		"cypher", "queryOptions", "anyCypherOption", "cypherOption", "versionNumber", 
		"explain", "profile", "configurationOption", "statement", "query", "regularQuery", 
		"bulkImportQuery", "singleQuery", "periodicCommitHint", "loadCSVQuery", 
		"union", "clause", "command", "createUniqueConstraint", "createNodePropertyExistenceConstraint", 
		"createRelationshipPropertyExistenceConstraint", "createIndex", "dropUniqueConstraint", 
		"dropNodePropertyExistenceConstraint", "dropRelationshipPropertyExistenceConstraint", 
		"dropIndex", "index", "uniqueConstraint", "nodePropertyExistenceConstraint", 
		"relationshipPropertyExistenceConstraint", "relationshipPatternSyntax", 
		"loadCSV", "match", "unwind", "merge", "mergeAction", "create", "set", 
		"setItem", "delete", "remove", "removeItem", "foreach", "with", "return", 
		"returnBody", "returnItems", "returnItem", "order", "skip", "limit", "sortItem", 
		"hint", "start", "startPoint", "lookup", "nodeLookup", "relationshipLookup", 
		"identifiedIndexLookup", "indexQuery", "idLookup", "literalIds", "where", 
		"pattern", "patternPart", "anonymousPatternPart", "shortestPathPattern", 
		"patternElement", "nodePattern", "patternElementChain", "relationshipPattern", 
		"relationshipDetail", "properties", "relType", "relationshipTypes", "nodeLabels", 
		"nodeLabel", "rangeLiteral", "labelName", "relTypeName", "expression", 
		"expression12", "expression11", "expression10", "expression9", "expression8", 
		"expression7", "expression6", "expression5", "expression4", "expression3", 
		"expression2", "atom", "reduce", "partialComparisonExpression", "parenthesizedExpression", 
		"relationshipsPattern", "filterExpression", "idInColl", "functionInvocation", 
		"functionName", "listComprehension", "propertyLookup", "caseExpression", 
		"caseAlternatives", "variable", "numberLiteral", "mapLiteral", "parameter", 
		"propertyExpression", "propertyKeyName", "signedIntegerLiteral", "unsignedIntegerLiteral", 
		"hexInteger", "decimalInteger", "octalInteger", "unsignedHexInteger", 
		"unsignedDecimalInteger", "unsignedOctalInteger", "hexString", "digitString", 
		"octalString", "digit", "octDigit", "doubleLiteral", "exponentDecimalReal", 
		"regularDecimalReal", "symbolicName", "ws", "sp", "leftArrowHead", "rightArrowHead", 
		"dash"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "'.'", "'='", "'('", "')'", "'['", "']'", "','", "'+='", 
		"'|'", "'*'", "':'", "'?'", "'..'", "'+'", "'-'", "'/'", "'%'", "'^'", 
		"'=~'", "'<>'", "'!='", "'<'", "'>'", "'<='", "'>='", "'!'", "'{'", "'}'", 
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
		null, "StringLiteral", "EscapedChar", "HexDigit", "CYPHER", "EXPLAIN", 
		"PROFILE", "USING", "PERIODIC", "COMMIT", "UNION", "ALL", "CREATE", "DROP", 
		"INDEX", "ON", "CONSTRAINT", "ASSERT", "IS", "UNIQUE", "EXISTS", "LOAD", 
		"CSV", "WITH", "HEADERS", "FROM", "AS", "FIELDTERMINATOR", "OPTIONAL", 
		"MATCH", "UNWIND", "MERGE", "SET", "DELETE", "DETACH", "REMOVE", "FOREACH", 
		"IN", "DISTINCT", "RETURN", "ORDER", "BY", "L_SKIP", "LIMIT", "DESCENDING", 
		"DESC", "ASCENDING", "ASC", "JOIN", "SCAN", "START", "NODE", "RELATIONSHIP", 
		"REL", "WHERE", "SHORTESTPATH", "ALLSHORTESTPATHS", "OR", "XOR", "AND", 
		"NOT", "STARTS", "ENDS", "CONTAINS", "NULL", "TRUE", "FALSE", "COUNT", 
		"FILTER", "EXTRACT", "ANY", "NONE", "SINGLE", "REDUCE", "CASE", "ELSE", 
		"END", "WHEN", "THEN", "L_0X", "UnescapedSymbolicName", "IdentifierStart", 
		"IdentifierPart", "EscapedSymbolicName", "WHITESPACE", "Comment"
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
		public QueryOptionsContext queryOptions() {
			return getRuleContext(QueryOptionsContext.class,0);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(266);
			ws();
			setState(267);
			queryOptions();
			setState(268);
			statement();
			setState(269);
			ws();
			setState(271);
			_la = _input.LA(1);
			if (_la==T__0) {
				{
				setState(270);
				match(T__0);
				}
			}

			setState(273);
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

	public static class QueryOptionsContext extends ParserRuleContext {
		public List<AnyCypherOptionContext> anyCypherOption() {
			return getRuleContexts(AnyCypherOptionContext.class);
		}
		public AnyCypherOptionContext anyCypherOption(int i) {
			return getRuleContext(AnyCypherOptionContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public QueryOptionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_queryOptions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterQueryOptions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitQueryOptions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitQueryOptions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QueryOptionsContext queryOptions() throws RecognitionException {
		QueryOptionsContext _localctx = new QueryOptionsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_queryOptions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CYPHER - 64)) | (1L << (EXPLAIN - 64)) | (1L << (PROFILE - 64)))) != 0)) {
				{
				{
				setState(275);
				anyCypherOption();
				setState(276);
				ws();
				}
				}
				setState(282);
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

	public static class AnyCypherOptionContext extends ParserRuleContext {
		public CypherOptionContext cypherOption() {
			return getRuleContext(CypherOptionContext.class,0);
		}
		public ExplainContext explain() {
			return getRuleContext(ExplainContext.class,0);
		}
		public ProfileContext profile() {
			return getRuleContext(ProfileContext.class,0);
		}
		public AnyCypherOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_anyCypherOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterAnyCypherOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitAnyCypherOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitAnyCypherOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnyCypherOptionContext anyCypherOption() throws RecognitionException {
		AnyCypherOptionContext _localctx = new AnyCypherOptionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_anyCypherOption);
		try {
			setState(286);
			switch (_input.LA(1)) {
			case CYPHER:
				enterOuterAlt(_localctx, 1);
				{
				setState(283);
				cypherOption();
				}
				break;
			case EXPLAIN:
				enterOuterAlt(_localctx, 2);
				{
				setState(284);
				explain();
				}
				break;
			case PROFILE:
				enterOuterAlt(_localctx, 3);
				{
				setState(285);
				profile();
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

	public static class CypherOptionContext extends ParserRuleContext {
		public TerminalNode CYPHER() { return getToken(CypherParser.CYPHER, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public VersionNumberContext versionNumber() {
			return getRuleContext(VersionNumberContext.class,0);
		}
		public List<ConfigurationOptionContext> configurationOption() {
			return getRuleContexts(ConfigurationOptionContext.class);
		}
		public ConfigurationOptionContext configurationOption(int i) {
			return getRuleContext(ConfigurationOptionContext.class,i);
		}
		public CypherOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cypherOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterCypherOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitCypherOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitCypherOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CypherOptionContext cypherOption() throws RecognitionException {
		CypherOptionContext _localctx = new CypherOptionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_cypherOption);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			match(CYPHER);
			setState(292);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(289);
				sp();
				setState(290);
				versionNumber();
				}
				break;
			}
			setState(299);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(294);
					sp();
					setState(295);
					configurationOption();
					}
					} 
				}
				setState(301);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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

	public static class VersionNumberContext extends ParserRuleContext {
		public List<DigitStringContext> digitString() {
			return getRuleContexts(DigitStringContext.class);
		}
		public DigitStringContext digitString(int i) {
			return getRuleContext(DigitStringContext.class,i);
		}
		public VersionNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_versionNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterVersionNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitVersionNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitVersionNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VersionNumberContext versionNumber() throws RecognitionException {
		VersionNumberContext _localctx = new VersionNumberContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_versionNumber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			digitString();
			setState(303);
			match(T__1);
			setState(304);
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

	public static class ExplainContext extends ParserRuleContext {
		public TerminalNode EXPLAIN() { return getToken(CypherParser.EXPLAIN, 0); }
		public ExplainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explain; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterExplain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitExplain(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitExplain(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExplainContext explain() throws RecognitionException {
		ExplainContext _localctx = new ExplainContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_explain);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(306);
			match(EXPLAIN);
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

	public static class ProfileContext extends ParserRuleContext {
		public TerminalNode PROFILE() { return getToken(CypherParser.PROFILE, 0); }
		public ProfileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_profile; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterProfile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitProfile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitProfile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProfileContext profile() throws RecognitionException {
		ProfileContext _localctx = new ProfileContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_profile);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(308);
			match(PROFILE);
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

	public static class ConfigurationOptionContext extends ParserRuleContext {
		public List<SymbolicNameContext> symbolicName() {
			return getRuleContexts(SymbolicNameContext.class);
		}
		public SymbolicNameContext symbolicName(int i) {
			return getRuleContext(SymbolicNameContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public ConfigurationOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_configurationOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterConfigurationOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitConfigurationOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitConfigurationOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConfigurationOptionContext configurationOption() throws RecognitionException {
		ConfigurationOptionContext _localctx = new ConfigurationOptionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_configurationOption);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(310);
			symbolicName();
			setState(311);
			ws();
			setState(312);
			match(T__2);
			setState(313);
			ws();
			setState(314);
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

	public static class StatementContext extends ParserRuleContext {
		public CommandContext command() {
			return getRuleContext(CommandContext.class,0);
		}
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
		enterRule(_localctx, 16, RULE_statement);
		try {
			setState(318);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(316);
				command();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(317);
				query();
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

	public static class QueryContext extends ParserRuleContext {
		public RegularQueryContext regularQuery() {
			return getRuleContext(RegularQueryContext.class,0);
		}
		public BulkImportQueryContext bulkImportQuery() {
			return getRuleContext(BulkImportQueryContext.class,0);
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
		enterRule(_localctx, 18, RULE_query);
		try {
			setState(322);
			switch (_input.LA(1)) {
			case CREATE:
			case LOAD:
			case WITH:
			case OPTIONAL:
			case MATCH:
			case UNWIND:
			case MERGE:
			case SET:
			case DELETE:
			case DETACH:
			case REMOVE:
			case FOREACH:
			case RETURN:
			case START:
				enterOuterAlt(_localctx, 1);
				{
				setState(320);
				regularQuery();
				}
				break;
			case USING:
				enterOuterAlt(_localctx, 2);
				{
				setState(321);
				bulkImportQuery();
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
		enterRule(_localctx, 20, RULE_regularQuery);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(324);
			singleQuery();
			setState(330);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(325);
					ws();
					setState(326);
					union();
					}
					} 
				}
				setState(332);
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

	public static class BulkImportQueryContext extends ParserRuleContext {
		public PeriodicCommitHintContext periodicCommitHint() {
			return getRuleContext(PeriodicCommitHintContext.class,0);
		}
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public LoadCSVQueryContext loadCSVQuery() {
			return getRuleContext(LoadCSVQueryContext.class,0);
		}
		public BulkImportQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bulkImportQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterBulkImportQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitBulkImportQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitBulkImportQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BulkImportQueryContext bulkImportQuery() throws RecognitionException {
		BulkImportQueryContext _localctx = new BulkImportQueryContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_bulkImportQuery);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(333);
			periodicCommitHint();
			setState(334);
			ws();
			setState(335);
			loadCSVQuery();
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
		enterRule(_localctx, 24, RULE_singleQuery);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(337);
			clause();
			setState(343);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(338);
					ws();
					setState(339);
					clause();
					}
					} 
				}
				setState(345);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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

	public static class PeriodicCommitHintContext extends ParserRuleContext {
		public TerminalNode USING() { return getToken(CypherParser.USING, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode PERIODIC() { return getToken(CypherParser.PERIODIC, 0); }
		public TerminalNode COMMIT() { return getToken(CypherParser.COMMIT, 0); }
		public SignedIntegerLiteralContext signedIntegerLiteral() {
			return getRuleContext(SignedIntegerLiteralContext.class,0);
		}
		public PeriodicCommitHintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_periodicCommitHint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterPeriodicCommitHint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitPeriodicCommitHint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitPeriodicCommitHint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PeriodicCommitHintContext periodicCommitHint() throws RecognitionException {
		PeriodicCommitHintContext _localctx = new PeriodicCommitHintContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_periodicCommitHint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(346);
			match(USING);
			setState(347);
			sp();
			setState(348);
			match(PERIODIC);
			setState(349);
			sp();
			setState(350);
			match(COMMIT);
			setState(354);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(351);
				sp();
				setState(352);
				signedIntegerLiteral();
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

	public static class LoadCSVQueryContext extends ParserRuleContext {
		public LoadCSVContext loadCSV() {
			return getRuleContext(LoadCSVContext.class,0);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public List<ClauseContext> clause() {
			return getRuleContexts(ClauseContext.class);
		}
		public ClauseContext clause(int i) {
			return getRuleContext(ClauseContext.class,i);
		}
		public LoadCSVQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loadCSVQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterLoadCSVQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitLoadCSVQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitLoadCSVQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoadCSVQueryContext loadCSVQuery() throws RecognitionException {
		LoadCSVQueryContext _localctx = new LoadCSVQueryContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_loadCSVQuery);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(356);
			loadCSV();
			setState(362);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(357);
					ws();
					setState(358);
					clause();
					}
					} 
				}
				setState(364);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
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
		enterRule(_localctx, 30, RULE_union);
		try {
			setState(372);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(365);
				match(UNION);
				setState(366);
				sp();
				setState(367);
				match(ALL);
				setState(368);
				singleQuery();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(370);
				match(UNION);
				setState(371);
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
		public LoadCSVContext loadCSV() {
			return getRuleContext(LoadCSVContext.class,0);
		}
		public StartContext start() {
			return getRuleContext(StartContext.class,0);
		}
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
		public ForeachContext foreach() {
			return getRuleContext(ForeachContext.class,0);
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
		enterRule(_localctx, 32, RULE_clause);
		try {
			setState(386);
			switch (_input.LA(1)) {
			case LOAD:
				enterOuterAlt(_localctx, 1);
				{
				setState(374);
				loadCSV();
				}
				break;
			case START:
				enterOuterAlt(_localctx, 2);
				{
				setState(375);
				start();
				}
				break;
			case OPTIONAL:
			case MATCH:
				enterOuterAlt(_localctx, 3);
				{
				setState(376);
				match();
				}
				break;
			case UNWIND:
				enterOuterAlt(_localctx, 4);
				{
				setState(377);
				unwind();
				}
				break;
			case MERGE:
				enterOuterAlt(_localctx, 5);
				{
				setState(378);
				merge();
				}
				break;
			case CREATE:
				enterOuterAlt(_localctx, 6);
				{
				setState(379);
				create();
				}
				break;
			case SET:
				enterOuterAlt(_localctx, 7);
				{
				setState(380);
				set();
				}
				break;
			case DELETE:
			case DETACH:
				enterOuterAlt(_localctx, 8);
				{
				setState(381);
				delete();
				}
				break;
			case REMOVE:
				enterOuterAlt(_localctx, 9);
				{
				setState(382);
				remove();
				}
				break;
			case FOREACH:
				enterOuterAlt(_localctx, 10);
				{
				setState(383);
				foreach();
				}
				break;
			case WITH:
				enterOuterAlt(_localctx, 11);
				{
				setState(384);
				with();
				}
				break;
			case RETURN:
				enterOuterAlt(_localctx, 12);
				{
				setState(385);
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

	public static class CommandContext extends ParserRuleContext {
		public CreateIndexContext createIndex() {
			return getRuleContext(CreateIndexContext.class,0);
		}
		public DropIndexContext dropIndex() {
			return getRuleContext(DropIndexContext.class,0);
		}
		public CreateUniqueConstraintContext createUniqueConstraint() {
			return getRuleContext(CreateUniqueConstraintContext.class,0);
		}
		public DropUniqueConstraintContext dropUniqueConstraint() {
			return getRuleContext(DropUniqueConstraintContext.class,0);
		}
		public CreateNodePropertyExistenceConstraintContext createNodePropertyExistenceConstraint() {
			return getRuleContext(CreateNodePropertyExistenceConstraintContext.class,0);
		}
		public DropNodePropertyExistenceConstraintContext dropNodePropertyExistenceConstraint() {
			return getRuleContext(DropNodePropertyExistenceConstraintContext.class,0);
		}
		public CreateRelationshipPropertyExistenceConstraintContext createRelationshipPropertyExistenceConstraint() {
			return getRuleContext(CreateRelationshipPropertyExistenceConstraintContext.class,0);
		}
		public DropRelationshipPropertyExistenceConstraintContext dropRelationshipPropertyExistenceConstraint() {
			return getRuleContext(DropRelationshipPropertyExistenceConstraintContext.class,0);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterCommand(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitCommand(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitCommand(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_command);
		try {
			setState(396);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(388);
				createIndex();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(389);
				dropIndex();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(390);
				createUniqueConstraint();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(391);
				dropUniqueConstraint();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(392);
				createNodePropertyExistenceConstraint();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(393);
				dropNodePropertyExistenceConstraint();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(394);
				createRelationshipPropertyExistenceConstraint();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(395);
				dropRelationshipPropertyExistenceConstraint();
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

	public static class CreateUniqueConstraintContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(CypherParser.CREATE, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public UniqueConstraintContext uniqueConstraint() {
			return getRuleContext(UniqueConstraintContext.class,0);
		}
		public CreateUniqueConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createUniqueConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterCreateUniqueConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitCreateUniqueConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitCreateUniqueConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateUniqueConstraintContext createUniqueConstraint() throws RecognitionException {
		CreateUniqueConstraintContext _localctx = new CreateUniqueConstraintContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_createUniqueConstraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(398);
			match(CREATE);
			setState(399);
			ws();
			setState(400);
			uniqueConstraint();
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

	public static class CreateNodePropertyExistenceConstraintContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(CypherParser.CREATE, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public NodePropertyExistenceConstraintContext nodePropertyExistenceConstraint() {
			return getRuleContext(NodePropertyExistenceConstraintContext.class,0);
		}
		public CreateNodePropertyExistenceConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createNodePropertyExistenceConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterCreateNodePropertyExistenceConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitCreateNodePropertyExistenceConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitCreateNodePropertyExistenceConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateNodePropertyExistenceConstraintContext createNodePropertyExistenceConstraint() throws RecognitionException {
		CreateNodePropertyExistenceConstraintContext _localctx = new CreateNodePropertyExistenceConstraintContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_createNodePropertyExistenceConstraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(402);
			match(CREATE);
			setState(403);
			ws();
			setState(404);
			nodePropertyExistenceConstraint();
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

	public static class CreateRelationshipPropertyExistenceConstraintContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(CypherParser.CREATE, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public RelationshipPropertyExistenceConstraintContext relationshipPropertyExistenceConstraint() {
			return getRuleContext(RelationshipPropertyExistenceConstraintContext.class,0);
		}
		public CreateRelationshipPropertyExistenceConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createRelationshipPropertyExistenceConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterCreateRelationshipPropertyExistenceConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitCreateRelationshipPropertyExistenceConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitCreateRelationshipPropertyExistenceConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateRelationshipPropertyExistenceConstraintContext createRelationshipPropertyExistenceConstraint() throws RecognitionException {
		CreateRelationshipPropertyExistenceConstraintContext _localctx = new CreateRelationshipPropertyExistenceConstraintContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_createRelationshipPropertyExistenceConstraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(406);
			match(CREATE);
			setState(407);
			ws();
			setState(408);
			relationshipPropertyExistenceConstraint();
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

	public static class CreateIndexContext extends ParserRuleContext {
		public TerminalNode CREATE() { return getToken(CypherParser.CREATE, 0); }
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public IndexContext index() {
			return getRuleContext(IndexContext.class,0);
		}
		public CreateIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createIndex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterCreateIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitCreateIndex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitCreateIndex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CreateIndexContext createIndex() throws RecognitionException {
		CreateIndexContext _localctx = new CreateIndexContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_createIndex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(410);
			match(CREATE);
			setState(411);
			sp();
			setState(412);
			index();
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

	public static class DropUniqueConstraintContext extends ParserRuleContext {
		public TerminalNode DROP() { return getToken(CypherParser.DROP, 0); }
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public UniqueConstraintContext uniqueConstraint() {
			return getRuleContext(UniqueConstraintContext.class,0);
		}
		public DropUniqueConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropUniqueConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterDropUniqueConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitDropUniqueConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitDropUniqueConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropUniqueConstraintContext dropUniqueConstraint() throws RecognitionException {
		DropUniqueConstraintContext _localctx = new DropUniqueConstraintContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_dropUniqueConstraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(414);
			match(DROP);
			setState(415);
			sp();
			setState(416);
			uniqueConstraint();
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

	public static class DropNodePropertyExistenceConstraintContext extends ParserRuleContext {
		public TerminalNode DROP() { return getToken(CypherParser.DROP, 0); }
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public NodePropertyExistenceConstraintContext nodePropertyExistenceConstraint() {
			return getRuleContext(NodePropertyExistenceConstraintContext.class,0);
		}
		public DropNodePropertyExistenceConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropNodePropertyExistenceConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterDropNodePropertyExistenceConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitDropNodePropertyExistenceConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitDropNodePropertyExistenceConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropNodePropertyExistenceConstraintContext dropNodePropertyExistenceConstraint() throws RecognitionException {
		DropNodePropertyExistenceConstraintContext _localctx = new DropNodePropertyExistenceConstraintContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_dropNodePropertyExistenceConstraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
			match(DROP);
			setState(419);
			sp();
			setState(420);
			nodePropertyExistenceConstraint();
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

	public static class DropRelationshipPropertyExistenceConstraintContext extends ParserRuleContext {
		public TerminalNode DROP() { return getToken(CypherParser.DROP, 0); }
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public RelationshipPropertyExistenceConstraintContext relationshipPropertyExistenceConstraint() {
			return getRuleContext(RelationshipPropertyExistenceConstraintContext.class,0);
		}
		public DropRelationshipPropertyExistenceConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropRelationshipPropertyExistenceConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterDropRelationshipPropertyExistenceConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitDropRelationshipPropertyExistenceConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitDropRelationshipPropertyExistenceConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropRelationshipPropertyExistenceConstraintContext dropRelationshipPropertyExistenceConstraint() throws RecognitionException {
		DropRelationshipPropertyExistenceConstraintContext _localctx = new DropRelationshipPropertyExistenceConstraintContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_dropRelationshipPropertyExistenceConstraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(422);
			match(DROP);
			setState(423);
			sp();
			setState(424);
			relationshipPropertyExistenceConstraint();
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

	public static class DropIndexContext extends ParserRuleContext {
		public TerminalNode DROP() { return getToken(CypherParser.DROP, 0); }
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public IndexContext index() {
			return getRuleContext(IndexContext.class,0);
		}
		public DropIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dropIndex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterDropIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitDropIndex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitDropIndex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DropIndexContext dropIndex() throws RecognitionException {
		DropIndexContext _localctx = new DropIndexContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_dropIndex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(426);
			match(DROP);
			setState(427);
			sp();
			setState(428);
			index();
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

	public static class IndexContext extends ParserRuleContext {
		public TerminalNode INDEX() { return getToken(CypherParser.INDEX, 0); }
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public TerminalNode ON() { return getToken(CypherParser.ON, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public NodeLabelContext nodeLabel() {
			return getRuleContext(NodeLabelContext.class,0);
		}
		public PropertyKeyNameContext propertyKeyName() {
			return getRuleContext(PropertyKeyNameContext.class,0);
		}
		public IndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitIndex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitIndex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexContext index() throws RecognitionException {
		IndexContext _localctx = new IndexContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_index);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(430);
			match(INDEX);
			setState(431);
			sp();
			setState(432);
			match(ON);
			setState(433);
			ws();
			setState(434);
			nodeLabel();
			setState(435);
			match(T__3);
			setState(436);
			propertyKeyName();
			setState(437);
			match(T__4);
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

	public static class UniqueConstraintContext extends ParserRuleContext {
		public TerminalNode CONSTRAINT() { return getToken(CypherParser.CONSTRAINT, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode ON() { return getToken(CypherParser.ON, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public NodeLabelContext nodeLabel() {
			return getRuleContext(NodeLabelContext.class,0);
		}
		public TerminalNode ASSERT() { return getToken(CypherParser.ASSERT, 0); }
		public PropertyExpressionContext propertyExpression() {
			return getRuleContext(PropertyExpressionContext.class,0);
		}
		public TerminalNode IS() { return getToken(CypherParser.IS, 0); }
		public TerminalNode UNIQUE() { return getToken(CypherParser.UNIQUE, 0); }
		public UniqueConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_uniqueConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterUniqueConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitUniqueConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitUniqueConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UniqueConstraintContext uniqueConstraint() throws RecognitionException {
		UniqueConstraintContext _localctx = new UniqueConstraintContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_uniqueConstraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(439);
			match(CONSTRAINT);
			setState(440);
			sp();
			setState(441);
			match(ON);
			setState(442);
			ws();
			setState(443);
			match(T__3);
			setState(444);
			variable();
			setState(445);
			nodeLabel();
			setState(446);
			match(T__4);
			setState(447);
			match(ASSERT);
			setState(448);
			propertyExpression();
			setState(449);
			match(IS);
			setState(450);
			sp();
			setState(451);
			match(UNIQUE);
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

	public static class NodePropertyExistenceConstraintContext extends ParserRuleContext {
		public TerminalNode CONSTRAINT() { return getToken(CypherParser.CONSTRAINT, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode ON() { return getToken(CypherParser.ON, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public NodeLabelContext nodeLabel() {
			return getRuleContext(NodeLabelContext.class,0);
		}
		public TerminalNode ASSERT() { return getToken(CypherParser.ASSERT, 0); }
		public TerminalNode EXISTS() { return getToken(CypherParser.EXISTS, 0); }
		public PropertyExpressionContext propertyExpression() {
			return getRuleContext(PropertyExpressionContext.class,0);
		}
		public NodePropertyExistenceConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodePropertyExistenceConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterNodePropertyExistenceConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitNodePropertyExistenceConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitNodePropertyExistenceConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodePropertyExistenceConstraintContext nodePropertyExistenceConstraint() throws RecognitionException {
		NodePropertyExistenceConstraintContext _localctx = new NodePropertyExistenceConstraintContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_nodePropertyExistenceConstraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(453);
			match(CONSTRAINT);
			setState(454);
			sp();
			setState(455);
			match(ON);
			setState(456);
			ws();
			setState(457);
			match(T__3);
			setState(458);
			variable();
			setState(459);
			nodeLabel();
			setState(460);
			match(T__4);
			setState(461);
			match(ASSERT);
			setState(462);
			sp();
			setState(463);
			match(EXISTS);
			setState(464);
			match(T__3);
			setState(465);
			propertyExpression();
			setState(466);
			match(T__4);
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

	public static class RelationshipPropertyExistenceConstraintContext extends ParserRuleContext {
		public TerminalNode CONSTRAINT() { return getToken(CypherParser.CONSTRAINT, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode ON() { return getToken(CypherParser.ON, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public RelationshipPatternSyntaxContext relationshipPatternSyntax() {
			return getRuleContext(RelationshipPatternSyntaxContext.class,0);
		}
		public TerminalNode ASSERT() { return getToken(CypherParser.ASSERT, 0); }
		public TerminalNode EXISTS() { return getToken(CypherParser.EXISTS, 0); }
		public PropertyExpressionContext propertyExpression() {
			return getRuleContext(PropertyExpressionContext.class,0);
		}
		public RelationshipPropertyExistenceConstraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipPropertyExistenceConstraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelationshipPropertyExistenceConstraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelationshipPropertyExistenceConstraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRelationshipPropertyExistenceConstraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipPropertyExistenceConstraintContext relationshipPropertyExistenceConstraint() throws RecognitionException {
		RelationshipPropertyExistenceConstraintContext _localctx = new RelationshipPropertyExistenceConstraintContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_relationshipPropertyExistenceConstraint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(468);
			match(CONSTRAINT);
			setState(469);
			sp();
			setState(470);
			match(ON);
			setState(471);
			ws();
			setState(472);
			relationshipPatternSyntax();
			setState(473);
			match(ASSERT);
			setState(474);
			sp();
			setState(475);
			match(EXISTS);
			setState(476);
			match(T__3);
			setState(477);
			propertyExpression();
			setState(478);
			match(T__4);
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

	public static class RelationshipPatternSyntaxContext extends ParserRuleContext {
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
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public RelTypeContext relType() {
			return getRuleContext(RelTypeContext.class,0);
		}
		public RightArrowHeadContext rightArrowHead() {
			return getRuleContext(RightArrowHeadContext.class,0);
		}
		public LeftArrowHeadContext leftArrowHead() {
			return getRuleContext(LeftArrowHeadContext.class,0);
		}
		public RelationshipPatternSyntaxContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipPatternSyntax; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelationshipPatternSyntax(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelationshipPatternSyntax(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRelationshipPatternSyntax(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipPatternSyntaxContext relationshipPatternSyntax() throws RecognitionException {
		RelationshipPatternSyntaxContext _localctx = new RelationshipPatternSyntaxContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_relationshipPatternSyntax);
		try {
			setState(521);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(480);
				match(T__3);
				setState(481);
				ws();
				setState(482);
				match(T__4);
				setState(483);
				dash();
				setState(484);
				match(T__5);
				setState(485);
				variable();
				setState(486);
				relType();
				setState(487);
				match(T__6);
				setState(488);
				dash();
				setState(489);
				match(T__3);
				setState(490);
				ws();
				setState(491);
				match(T__4);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(493);
				match(T__3);
				setState(494);
				ws();
				setState(495);
				match(T__4);
				setState(496);
				dash();
				setState(497);
				match(T__5);
				setState(498);
				variable();
				setState(499);
				relType();
				setState(500);
				match(T__6);
				setState(501);
				dash();
				setState(502);
				rightArrowHead();
				setState(503);
				match(T__3);
				setState(504);
				ws();
				setState(505);
				match(T__4);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(507);
				match(T__3);
				setState(508);
				ws();
				setState(509);
				match(T__4);
				setState(510);
				leftArrowHead();
				setState(511);
				dash();
				setState(512);
				match(T__5);
				setState(513);
				variable();
				setState(514);
				relType();
				setState(515);
				match(T__6);
				setState(516);
				dash();
				setState(517);
				match(T__3);
				setState(518);
				ws();
				setState(519);
				match(T__4);
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

	public static class LoadCSVContext extends ParserRuleContext {
		public TerminalNode LOAD() { return getToken(CypherParser.LOAD, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode CSV() { return getToken(CypherParser.CSV, 0); }
		public TerminalNode FROM() { return getToken(CypherParser.FROM, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode AS() { return getToken(CypherParser.AS, 0); }
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode WITH() { return getToken(CypherParser.WITH, 0); }
		public TerminalNode HEADERS() { return getToken(CypherParser.HEADERS, 0); }
		public TerminalNode FIELDTERMINATOR() { return getToken(CypherParser.FIELDTERMINATOR, 0); }
		public TerminalNode StringLiteral() { return getToken(CypherParser.StringLiteral, 0); }
		public LoadCSVContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loadCSV; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterLoadCSV(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitLoadCSV(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitLoadCSV(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoadCSVContext loadCSV() throws RecognitionException {
		LoadCSVContext _localctx = new LoadCSVContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_loadCSV);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(523);
			match(LOAD);
			setState(524);
			sp();
			setState(525);
			match(CSV);
			setState(526);
			sp();
			setState(532);
			_la = _input.LA(1);
			if (_la==WITH) {
				{
				setState(527);
				match(WITH);
				setState(528);
				sp();
				setState(529);
				match(HEADERS);
				setState(530);
				sp();
				}
			}

			setState(534);
			match(FROM);
			setState(535);
			sp();
			setState(536);
			expression();
			setState(537);
			sp();
			setState(538);
			match(AS);
			setState(539);
			sp();
			setState(540);
			variable();
			setState(541);
			sp();
			setState(546);
			_la = _input.LA(1);
			if (_la==FIELDTERMINATOR) {
				{
				setState(542);
				match(FIELDTERMINATOR);
				setState(543);
				sp();
				setState(544);
				match(StringLiteral);
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
		public List<HintContext> hint() {
			return getRuleContexts(HintContext.class);
		}
		public HintContext hint(int i) {
			return getRuleContext(HintContext.class,i);
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
		enterRule(_localctx, 64, RULE_match);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(550);
			_la = _input.LA(1);
			if (_la==OPTIONAL) {
				{
				setState(548);
				match(OPTIONAL);
				setState(549);
				sp();
				}
			}

			setState(552);
			match(MATCH);
			setState(553);
			ws();
			setState(554);
			pattern();
			setState(555);
			ws();
			setState(559);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==USING) {
				{
				{
				setState(556);
				hint();
				}
				}
				setState(561);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(563);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(562);
				where();
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
		enterRule(_localctx, 66, RULE_unwind);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(565);
			match(UNWIND);
			setState(566);
			ws();
			setState(567);
			expression();
			setState(568);
			sp();
			setState(569);
			match(AS);
			setState(570);
			sp();
			setState(571);
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
		enterRule(_localctx, 68, RULE_merge);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(573);
			match(MERGE);
			setState(574);
			ws();
			setState(575);
			patternPart();
			setState(581);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(576);
					sp();
					setState(577);
					mergeAction();
					}
					} 
				}
				setState(583);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
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
		enterRule(_localctx, 70, RULE_mergeAction);
		try {
			setState(596);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(584);
				match(ON);
				setState(585);
				sp();
				setState(586);
				match(MATCH);
				setState(587);
				sp();
				setState(588);
				set();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(590);
				match(ON);
				setState(591);
				sp();
				setState(592);
				match(CREATE);
				setState(593);
				sp();
				setState(594);
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
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public TerminalNode UNIQUE() { return getToken(CypherParser.UNIQUE, 0); }
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
		enterRule(_localctx, 72, RULE_create);
		try {
			setState(608);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(598);
				match(CREATE);
				setState(599);
				sp();
				setState(600);
				match(UNIQUE);
				setState(601);
				ws();
				setState(602);
				pattern();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(604);
				match(CREATE);
				setState(605);
				ws();
				setState(606);
				pattern();
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
		enterRule(_localctx, 74, RULE_set);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(610);
			match(SET);
			setState(611);
			setItem();
			setState(616);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(612);
				match(T__7);
				setState(613);
				setItem();
				}
				}
				setState(618);
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
		enterRule(_localctx, 76, RULE_setItem);
		try {
			setState(634);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(619);
				propertyExpression();
				setState(620);
				match(T__2);
				setState(621);
				expression();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(623);
				variable();
				setState(624);
				match(T__2);
				setState(625);
				expression();
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(627);
				variable();
				setState(628);
				match(T__8);
				setState(629);
				expression();
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(631);
				variable();
				setState(632);
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
		enterRule(_localctx, 78, RULE_delete);
		int _la;
		try {
			setState(656);
			switch (_input.LA(1)) {
			case DELETE:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(636);
				match(DELETE);
				setState(637);
				expression();
				setState(642);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(638);
					match(T__7);
					setState(639);
					expression();
					}
					}
					setState(644);
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
				setState(645);
				match(DETACH);
				setState(646);
				sp();
				setState(647);
				match(DELETE);
				setState(648);
				expression();
				setState(653);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(649);
					match(T__7);
					setState(650);
					expression();
					}
					}
					setState(655);
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
		enterRule(_localctx, 80, RULE_remove);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(658);
			match(REMOVE);
			setState(659);
			sp();
			setState(660);
			removeItem();
			setState(668);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(661);
					ws();
					setState(662);
					match(T__7);
					setState(663);
					ws();
					setState(664);
					removeItem();
					}
					} 
				}
				setState(670);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
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
		enterRule(_localctx, 82, RULE_removeItem);
		try {
			setState(675);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(671);
				variable();
				setState(672);
				nodeLabels();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(674);
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

	public static class ForeachContext extends ParserRuleContext {
		public TerminalNode FOREACH() { return getToken(CypherParser.FOREACH, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
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
		public List<ClauseContext> clause() {
			return getRuleContexts(ClauseContext.class);
		}
		public ClauseContext clause(int i) {
			return getRuleContext(ClauseContext.class,i);
		}
		public ForeachContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_foreach; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterForeach(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitForeach(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitForeach(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForeachContext foreach() throws RecognitionException {
		ForeachContext _localctx = new ForeachContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_foreach);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(677);
			match(FOREACH);
			setState(678);
			ws();
			setState(679);
			match(T__3);
			setState(680);
			ws();
			setState(681);
			variable();
			setState(682);
			sp();
			setState(683);
			match(IN);
			setState(684);
			sp();
			setState(685);
			expression();
			setState(686);
			match(T__9);
			setState(690); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(687);
					sp();
					setState(688);
					clause();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(692); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(694);
			ws();
			setState(695);
			match(T__4);
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
		enterRule(_localctx, 86, RULE_with);
		int _la;
		try {
			setState(710);
			switch ( getInterpreter().adaptivePredict(_input,33,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(697);
				match(WITH);
				setState(698);
				match(DISTINCT);
				setState(699);
				sp();
				setState(700);
				returnBody();
				setState(702);
				_la = _input.LA(1);
				if (_la==WHERE) {
					{
					setState(701);
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
				setState(704);
				match(WITH);
				setState(705);
				sp();
				setState(706);
				returnBody();
				setState(708);
				_la = _input.LA(1);
				if (_la==WHERE) {
					{
					setState(707);
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
		enterRule(_localctx, 88, RULE_return);
		try {
			setState(722);
			switch ( getInterpreter().adaptivePredict(_input,34,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(712);
				match(RETURN);
				setState(713);
				sp();
				setState(714);
				match(DISTINCT);
				setState(715);
				sp();
				setState(716);
				returnBody();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(718);
				match(RETURN);
				setState(719);
				sp();
				setState(720);
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
		enterRule(_localctx, 90, RULE_returnBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(724);
			returnItems();
			setState(728);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(725);
				sp();
				setState(726);
				order();
				}
				break;
			}
			setState(733);
			switch ( getInterpreter().adaptivePredict(_input,36,_ctx) ) {
			case 1:
				{
				setState(730);
				sp();
				setState(731);
				skip();
				}
				break;
			}
			setState(738);
			switch ( getInterpreter().adaptivePredict(_input,37,_ctx) ) {
			case 1:
				{
				setState(735);
				sp();
				setState(736);
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
		enterRule(_localctx, 92, RULE_returnItems);
		try {
			int _alt;
			setState(762);
			switch (_input.LA(1)) {
			case T__10:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(740);
				match(T__10);
				setState(748);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(741);
						ws();
						setState(742);
						match(T__7);
						setState(743);
						ws();
						setState(744);
						returnItem();
						}
						} 
					}
					setState(750);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,38,_ctx);
				}
				}
				}
				break;
			case T__1:
			case T__3:
			case T__5:
			case T__15:
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
			case CYPHER:
			case EXPLAIN:
			case PROFILE:
			case USING:
			case PERIODIC:
			case COMMIT:
			case UNION:
			case ALL:
			case CREATE:
			case DROP:
			case INDEX:
			case ON:
			case CONSTRAINT:
			case ASSERT:
			case IS:
			case UNIQUE:
			case EXISTS:
			case LOAD:
			case CSV:
			case WITH:
			case HEADERS:
			case FROM:
			case AS:
			case FIELDTERMINATOR:
			case OPTIONAL:
			case MATCH:
			case UNWIND:
			case MERGE:
			case SET:
			case DELETE:
			case DETACH:
			case REMOVE:
			case FOREACH:
			case IN:
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
			case JOIN:
			case SCAN:
			case START:
			case NODE:
			case RELATIONSHIP:
			case REL:
			case WHERE:
			case SHORTESTPATH:
			case ALLSHORTESTPATHS:
			case OR:
			case XOR:
			case AND:
			case NOT:
			case STARTS:
			case ENDS:
			case CONTAINS:
			case NULL:
			case TRUE:
			case FALSE:
			case COUNT:
			case FILTER:
			case EXTRACT:
			case ANY:
			case NONE:
			case SINGLE:
			case REDUCE:
			case CASE:
			case ELSE:
			case END:
			case WHEN:
			case THEN:
			case L_0X:
			case UnescapedSymbolicName:
			case EscapedSymbolicName:
			case WHITESPACE:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(751);
				returnItem();
				setState(759);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(752);
						ws();
						setState(753);
						match(T__7);
						setState(754);
						ws();
						setState(755);
						returnItem();
						}
						} 
					}
					setState(761);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,39,_ctx);
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
		enterRule(_localctx, 94, RULE_returnItem);
		try {
			setState(771);
			switch ( getInterpreter().adaptivePredict(_input,41,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(764);
				expression();
				setState(765);
				sp();
				setState(766);
				match(AS);
				setState(767);
				sp();
				setState(768);
				variable();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(770);
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
		enterRule(_localctx, 96, RULE_order);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(773);
			match(ORDER);
			setState(774);
			sp();
			setState(775);
			match(BY);
			setState(776);
			sp();
			setState(777);
			sortItem();
			setState(784);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(778);
				match(T__7);
				setState(779);
				ws();
				setState(780);
				sortItem();
				}
				}
				setState(786);
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
		enterRule(_localctx, 98, RULE_skip);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(787);
			match(L_SKIP);
			setState(788);
			sp();
			setState(789);
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
		enterRule(_localctx, 100, RULE_limit);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(791);
			match(LIMIT);
			setState(792);
			sp();
			setState(793);
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
		enterRule(_localctx, 102, RULE_sortItem);
		int _la;
		try {
			setState(802);
			switch ( getInterpreter().adaptivePredict(_input,44,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(795);
				expression();
				setState(796);
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
				setState(798);
				expression();
				setState(800);
				_la = _input.LA(1);
				if (_la==ASCENDING || _la==ASC) {
					{
					setState(799);
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

	public static class HintContext extends ParserRuleContext {
		public TerminalNode USING() { return getToken(CypherParser.USING, 0); }
		public List<SpContext> sp() {
			return getRuleContexts(SpContext.class);
		}
		public SpContext sp(int i) {
			return getRuleContext(SpContext.class,i);
		}
		public TerminalNode INDEX() { return getToken(CypherParser.INDEX, 0); }
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public NodeLabelContext nodeLabel() {
			return getRuleContext(NodeLabelContext.class,0);
		}
		public PropertyKeyNameContext propertyKeyName() {
			return getRuleContext(PropertyKeyNameContext.class,0);
		}
		public TerminalNode JOIN() { return getToken(CypherParser.JOIN, 0); }
		public TerminalNode ON() { return getToken(CypherParser.ON, 0); }
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public TerminalNode SCAN() { return getToken(CypherParser.SCAN, 0); }
		public HintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterHint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitHint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitHint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HintContext hint() throws RecognitionException {
		HintContext _localctx = new HintContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_hint);
		try {
			int _alt;
			setState(838);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(804);
				match(USING);
				setState(805);
				sp();
				setState(806);
				match(INDEX);
				setState(807);
				sp();
				setState(808);
				variable();
				setState(809);
				nodeLabel();
				setState(810);
				match(T__3);
				setState(811);
				propertyKeyName();
				setState(812);
				match(T__4);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(814);
				match(USING);
				setState(815);
				sp();
				setState(816);
				match(JOIN);
				setState(817);
				sp();
				setState(818);
				match(ON);
				setState(819);
				sp();
				setState(820);
				variable();
				setState(828);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(821);
						ws();
						setState(822);
						match(T__7);
						setState(823);
						ws();
						setState(824);
						variable();
						}
						} 
					}
					setState(830);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,45,_ctx);
				}
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(831);
				match(USING);
				setState(832);
				sp();
				setState(833);
				match(SCAN);
				setState(834);
				sp();
				setState(835);
				variable();
				setState(836);
				nodeLabel();
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

	public static class StartContext extends ParserRuleContext {
		public TerminalNode START() { return getToken(CypherParser.START, 0); }
		public SpContext sp() {
			return getRuleContext(SpContext.class,0);
		}
		public List<StartPointContext> startPoint() {
			return getRuleContexts(StartPointContext.class);
		}
		public StartPointContext startPoint(int i) {
			return getRuleContext(StartPointContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public WhereContext where() {
			return getRuleContext(WhereContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_start);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(840);
			match(START);
			setState(841);
			sp();
			setState(842);
			startPoint();
			setState(850);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(843);
					ws();
					setState(844);
					match(T__7);
					setState(845);
					ws();
					setState(846);
					startPoint();
					}
					} 
				}
				setState(852);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,47,_ctx);
			}
			setState(854);
			_la = _input.LA(1);
			if (_la==WHERE) {
				{
				setState(853);
				where();
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

	public static class StartPointContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public LookupContext lookup() {
			return getRuleContext(LookupContext.class,0);
		}
		public StartPointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_startPoint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterStartPoint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitStartPoint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitStartPoint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartPointContext startPoint() throws RecognitionException {
		StartPointContext _localctx = new StartPointContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_startPoint);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(856);
			variable();
			setState(857);
			ws();
			setState(858);
			match(T__2);
			setState(859);
			ws();
			setState(860);
			lookup();
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

	public static class LookupContext extends ParserRuleContext {
		public NodeLookupContext nodeLookup() {
			return getRuleContext(NodeLookupContext.class,0);
		}
		public RelationshipLookupContext relationshipLookup() {
			return getRuleContext(RelationshipLookupContext.class,0);
		}
		public LookupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lookup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterLookup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitLookup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitLookup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LookupContext lookup() throws RecognitionException {
		LookupContext _localctx = new LookupContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_lookup);
		try {
			setState(864);
			switch (_input.LA(1)) {
			case NODE:
				enterOuterAlt(_localctx, 1);
				{
				setState(862);
				nodeLookup();
				}
				break;
			case RELATIONSHIP:
			case REL:
				enterOuterAlt(_localctx, 2);
				{
				setState(863);
				relationshipLookup();
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

	public static class NodeLookupContext extends ParserRuleContext {
		public TerminalNode NODE() { return getToken(CypherParser.NODE, 0); }
		public IdentifiedIndexLookupContext identifiedIndexLookup() {
			return getRuleContext(IdentifiedIndexLookupContext.class,0);
		}
		public IndexQueryContext indexQuery() {
			return getRuleContext(IndexQueryContext.class,0);
		}
		public IdLookupContext idLookup() {
			return getRuleContext(IdLookupContext.class,0);
		}
		public NodeLookupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nodeLookup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterNodeLookup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitNodeLookup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitNodeLookup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NodeLookupContext nodeLookup() throws RecognitionException {
		NodeLookupContext _localctx = new NodeLookupContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_nodeLookup);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(866);
			match(NODE);
			setState(870);
			switch ( getInterpreter().adaptivePredict(_input,50,_ctx) ) {
			case 1:
				{
				setState(867);
				identifiedIndexLookup();
				}
				break;
			case 2:
				{
				setState(868);
				indexQuery();
				}
				break;
			case 3:
				{
				setState(869);
				idLookup();
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

	public static class RelationshipLookupContext extends ParserRuleContext {
		public TerminalNode RELATIONSHIP() { return getToken(CypherParser.RELATIONSHIP, 0); }
		public TerminalNode REL() { return getToken(CypherParser.REL, 0); }
		public IdentifiedIndexLookupContext identifiedIndexLookup() {
			return getRuleContext(IdentifiedIndexLookupContext.class,0);
		}
		public IndexQueryContext indexQuery() {
			return getRuleContext(IndexQueryContext.class,0);
		}
		public IdLookupContext idLookup() {
			return getRuleContext(IdLookupContext.class,0);
		}
		public RelationshipLookupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationshipLookup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelationshipLookup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelationshipLookup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRelationshipLookup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationshipLookupContext relationshipLookup() throws RecognitionException {
		RelationshipLookupContext _localctx = new RelationshipLookupContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_relationshipLookup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(872);
			_la = _input.LA(1);
			if ( !(_la==RELATIONSHIP || _la==REL) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(876);
			switch ( getInterpreter().adaptivePredict(_input,51,_ctx) ) {
			case 1:
				{
				setState(873);
				identifiedIndexLookup();
				}
				break;
			case 2:
				{
				setState(874);
				indexQuery();
				}
				break;
			case 3:
				{
				setState(875);
				idLookup();
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

	public static class IdentifiedIndexLookupContext extends ParserRuleContext {
		public List<SymbolicNameContext> symbolicName() {
			return getRuleContexts(SymbolicNameContext.class);
		}
		public SymbolicNameContext symbolicName(int i) {
			return getRuleContext(SymbolicNameContext.class,i);
		}
		public TerminalNode StringLiteral() { return getToken(CypherParser.StringLiteral, 0); }
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public IdentifiedIndexLookupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifiedIndexLookup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterIdentifiedIndexLookup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitIdentifiedIndexLookup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitIdentifiedIndexLookup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifiedIndexLookupContext identifiedIndexLookup() throws RecognitionException {
		IdentifiedIndexLookupContext _localctx = new IdentifiedIndexLookupContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_identifiedIndexLookup);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(878);
			match(T__11);
			setState(879);
			symbolicName();
			setState(880);
			match(T__3);
			setState(881);
			symbolicName();
			setState(882);
			match(T__2);
			setState(885);
			switch (_input.LA(1)) {
			case StringLiteral:
				{
				setState(883);
				match(StringLiteral);
				}
				break;
			case T__27:
				{
				setState(884);
				parameter();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(887);
			match(T__4);
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

	public static class IndexQueryContext extends ParserRuleContext {
		public SymbolicNameContext symbolicName() {
			return getRuleContext(SymbolicNameContext.class,0);
		}
		public TerminalNode StringLiteral() { return getToken(CypherParser.StringLiteral, 0); }
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public IndexQueryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexQuery; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterIndexQuery(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitIndexQuery(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitIndexQuery(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexQueryContext indexQuery() throws RecognitionException {
		IndexQueryContext _localctx = new IndexQueryContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_indexQuery);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(889);
			match(T__11);
			setState(890);
			symbolicName();
			setState(891);
			match(T__3);
			setState(894);
			switch (_input.LA(1)) {
			case StringLiteral:
				{
				setState(892);
				match(StringLiteral);
				}
				break;
			case T__27:
				{
				setState(893);
				parameter();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(896);
			match(T__4);
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

	public static class IdLookupContext extends ParserRuleContext {
		public LiteralIdsContext literalIds() {
			return getRuleContext(LiteralIdsContext.class,0);
		}
		public ParameterContext parameter() {
			return getRuleContext(ParameterContext.class,0);
		}
		public IdLookupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_idLookup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterIdLookup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitIdLookup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitIdLookup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdLookupContext idLookup() throws RecognitionException {
		IdLookupContext _localctx = new IdLookupContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_idLookup);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(898);
			match(T__3);
			setState(902);
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
				setState(899);
				literalIds();
				}
				break;
			case T__27:
				{
				setState(900);
				parameter();
				}
				break;
			case T__10:
				{
				setState(901);
				match(T__10);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(904);
			match(T__4);
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

	public static class LiteralIdsContext extends ParserRuleContext {
		public List<UnsignedIntegerLiteralContext> unsignedIntegerLiteral() {
			return getRuleContexts(UnsignedIntegerLiteralContext.class);
		}
		public UnsignedIntegerLiteralContext unsignedIntegerLiteral(int i) {
			return getRuleContext(UnsignedIntegerLiteralContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public LiteralIdsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literalIds; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterLiteralIds(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitLiteralIds(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitLiteralIds(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralIdsContext literalIds() throws RecognitionException {
		LiteralIdsContext _localctx = new LiteralIdsContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_literalIds);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(906);
			unsignedIntegerLiteral();
			setState(914);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7 || _la==WHITESPACE) {
				{
				{
				setState(907);
				ws();
				setState(908);
				match(T__7);
				setState(909);
				ws();
				setState(910);
				unsignedIntegerLiteral();
				}
				}
				setState(916);
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
		enterRule(_localctx, 124, RULE_where);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(917);
			match(WHERE);
			setState(918);
			sp();
			setState(919);
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
		enterRule(_localctx, 126, RULE_pattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(921);
			patternPart();
			setState(926);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(922);
				match(T__7);
				setState(923);
				patternPart();
				}
				}
				setState(928);
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
		enterRule(_localctx, 128, RULE_patternPart);
		try {
			setState(936);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(929);
				variable();
				setState(930);
				ws();
				setState(931);
				match(T__2);
				setState(932);
				ws();
				setState(933);
				anonymousPatternPart();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(935);
				anonymousPatternPart();
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

	public static class AnonymousPatternPartContext extends ParserRuleContext {
		public ShortestPathPatternContext shortestPathPattern() {
			return getRuleContext(ShortestPathPatternContext.class,0);
		}
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
		enterRule(_localctx, 130, RULE_anonymousPatternPart);
		try {
			setState(940);
			switch (_input.LA(1)) {
			case SHORTESTPATH:
			case ALLSHORTESTPATHS:
				enterOuterAlt(_localctx, 1);
				{
				setState(938);
				shortestPathPattern();
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(939);
				patternElement();
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

	public static class ShortestPathPatternContext extends ParserRuleContext {
		public TerminalNode SHORTESTPATH() { return getToken(CypherParser.SHORTESTPATH, 0); }
		public PatternElementContext patternElement() {
			return getRuleContext(PatternElementContext.class,0);
		}
		public TerminalNode ALLSHORTESTPATHS() { return getToken(CypherParser.ALLSHORTESTPATHS, 0); }
		public ShortestPathPatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shortestPathPattern; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterShortestPathPattern(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitShortestPathPattern(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitShortestPathPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShortestPathPatternContext shortestPathPattern() throws RecognitionException {
		ShortestPathPatternContext _localctx = new ShortestPathPatternContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_shortestPathPattern);
		try {
			setState(952);
			switch (_input.LA(1)) {
			case SHORTESTPATH:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(942);
				match(SHORTESTPATH);
				setState(943);
				match(T__3);
				setState(944);
				patternElement();
				setState(945);
				match(T__4);
				}
				}
				break;
			case ALLSHORTESTPATHS:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(947);
				match(ALLSHORTESTPATHS);
				setState(948);
				match(T__3);
				setState(949);
				patternElement();
				setState(950);
				match(T__4);
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
		enterRule(_localctx, 134, RULE_patternElement);
		try {
			int _alt;
			setState(967);
			switch ( getInterpreter().adaptivePredict(_input,61,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(954);
				nodePattern();
				setState(960);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(955);
						ws();
						setState(956);
						patternElementChain();
						}
						} 
					}
					setState(962);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,60,_ctx);
				}
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(963);
				match(T__3);
				setState(964);
				patternElement();
				setState(965);
				match(T__4);
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
		enterRule(_localctx, 136, RULE_nodePattern);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(969);
			match(T__3);
			setState(970);
			ws();
			setState(974);
			_la = _input.LA(1);
			if (((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CYPHER - 64)) | (1L << (EXPLAIN - 64)) | (1L << (PROFILE - 64)) | (1L << (USING - 64)) | (1L << (PERIODIC - 64)) | (1L << (COMMIT - 64)) | (1L << (UNION - 64)) | (1L << (ALL - 64)) | (1L << (CREATE - 64)) | (1L << (DROP - 64)) | (1L << (INDEX - 64)) | (1L << (ON - 64)) | (1L << (CONSTRAINT - 64)) | (1L << (ASSERT - 64)) | (1L << (IS - 64)) | (1L << (UNIQUE - 64)) | (1L << (EXISTS - 64)) | (1L << (LOAD - 64)) | (1L << (CSV - 64)) | (1L << (WITH - 64)) | (1L << (HEADERS - 64)) | (1L << (FROM - 64)) | (1L << (AS - 64)) | (1L << (FIELDTERMINATOR - 64)) | (1L << (OPTIONAL - 64)) | (1L << (MATCH - 64)) | (1L << (UNWIND - 64)) | (1L << (MERGE - 64)) | (1L << (SET - 64)) | (1L << (DELETE - 64)) | (1L << (DETACH - 64)) | (1L << (REMOVE - 64)) | (1L << (FOREACH - 64)) | (1L << (IN - 64)) | (1L << (DISTINCT - 64)) | (1L << (RETURN - 64)) | (1L << (ORDER - 64)) | (1L << (BY - 64)) | (1L << (L_SKIP - 64)) | (1L << (LIMIT - 64)) | (1L << (DESCENDING - 64)) | (1L << (DESC - 64)) | (1L << (ASCENDING - 64)) | (1L << (ASC - 64)) | (1L << (JOIN - 64)) | (1L << (SCAN - 64)) | (1L << (START - 64)) | (1L << (NODE - 64)) | (1L << (RELATIONSHIP - 64)) | (1L << (REL - 64)) | (1L << (WHERE - 64)) | (1L << (SHORTESTPATH - 64)) | (1L << (ALLSHORTESTPATHS - 64)) | (1L << (OR - 64)) | (1L << (XOR - 64)) | (1L << (AND - 64)) | (1L << (NOT - 64)) | (1L << (STARTS - 64)) | (1L << (ENDS - 64)) | (1L << (CONTAINS - 64)) | (1L << (NULL - 64)) | (1L << (TRUE - 64)) | (1L << (FALSE - 64)) | (1L << (COUNT - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (FILTER - 128)) | (1L << (EXTRACT - 128)) | (1L << (ANY - 128)) | (1L << (NONE - 128)) | (1L << (SINGLE - 128)) | (1L << (REDUCE - 128)) | (1L << (CASE - 128)) | (1L << (ELSE - 128)) | (1L << (END - 128)) | (1L << (WHEN - 128)) | (1L << (THEN - 128)) | (1L << (L_0X - 128)) | (1L << (UnescapedSymbolicName - 128)) | (1L << (EscapedSymbolicName - 128)))) != 0)) {
				{
				setState(971);
				variable();
				setState(972);
				ws();
				}
			}

			setState(977);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(976);
				nodeLabels();
				}
			}

			setState(980);
			_la = _input.LA(1);
			if (_la==T__27) {
				{
				setState(979);
				properties();
				}
			}

			setState(982);
			ws();
			setState(983);
			match(T__4);
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
		enterRule(_localctx, 138, RULE_patternElementChain);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(985);
			relationshipPattern();
			setState(986);
			ws();
			setState(987);
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
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
		}
		public LeftArrowHeadContext leftArrowHead() {
			return getRuleContext(LeftArrowHeadContext.class,0);
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
		enterRule(_localctx, 140, RULE_relationshipPattern);
		int _la;
		try {
			setState(1033);
			switch ( getInterpreter().adaptivePredict(_input,69,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(989);
				ws();
				setState(990);
				leftArrowHead();
				setState(991);
				ws();
				setState(992);
				dash();
				setState(993);
				ws();
				setState(995);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(994);
					relationshipDetail();
					}
				}

				setState(997);
				ws();
				setState(998);
				dash();
				setState(999);
				ws();
				setState(1000);
				rightArrowHead();
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(1002);
				ws();
				setState(1003);
				leftArrowHead();
				setState(1004);
				ws();
				setState(1005);
				dash();
				setState(1006);
				ws();
				setState(1008);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(1007);
					relationshipDetail();
					}
				}

				setState(1010);
				ws();
				setState(1011);
				dash();
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(1013);
				ws();
				setState(1014);
				dash();
				setState(1015);
				ws();
				setState(1017);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(1016);
					relationshipDetail();
					}
				}

				setState(1019);
				ws();
				setState(1020);
				dash();
				setState(1021);
				ws();
				setState(1022);
				rightArrowHead();
				}
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(1024);
				ws();
				setState(1025);
				dash();
				setState(1026);
				ws();
				setState(1028);
				_la = _input.LA(1);
				if (_la==T__5) {
					{
					setState(1027);
					relationshipDetail();
					}
				}

				setState(1030);
				ws();
				setState(1031);
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
		public PropertiesContext properties() {
			return getRuleContext(PropertiesContext.class,0);
		}
		public RangeLiteralContext rangeLiteral() {
			return getRuleContext(RangeLiteralContext.class,0);
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
		enterRule(_localctx, 142, RULE_relationshipDetail);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1035);
			match(T__5);
			setState(1037);
			_la = _input.LA(1);
			if (((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CYPHER - 64)) | (1L << (EXPLAIN - 64)) | (1L << (PROFILE - 64)) | (1L << (USING - 64)) | (1L << (PERIODIC - 64)) | (1L << (COMMIT - 64)) | (1L << (UNION - 64)) | (1L << (ALL - 64)) | (1L << (CREATE - 64)) | (1L << (DROP - 64)) | (1L << (INDEX - 64)) | (1L << (ON - 64)) | (1L << (CONSTRAINT - 64)) | (1L << (ASSERT - 64)) | (1L << (IS - 64)) | (1L << (UNIQUE - 64)) | (1L << (EXISTS - 64)) | (1L << (LOAD - 64)) | (1L << (CSV - 64)) | (1L << (WITH - 64)) | (1L << (HEADERS - 64)) | (1L << (FROM - 64)) | (1L << (AS - 64)) | (1L << (FIELDTERMINATOR - 64)) | (1L << (OPTIONAL - 64)) | (1L << (MATCH - 64)) | (1L << (UNWIND - 64)) | (1L << (MERGE - 64)) | (1L << (SET - 64)) | (1L << (DELETE - 64)) | (1L << (DETACH - 64)) | (1L << (REMOVE - 64)) | (1L << (FOREACH - 64)) | (1L << (IN - 64)) | (1L << (DISTINCT - 64)) | (1L << (RETURN - 64)) | (1L << (ORDER - 64)) | (1L << (BY - 64)) | (1L << (L_SKIP - 64)) | (1L << (LIMIT - 64)) | (1L << (DESCENDING - 64)) | (1L << (DESC - 64)) | (1L << (ASCENDING - 64)) | (1L << (ASC - 64)) | (1L << (JOIN - 64)) | (1L << (SCAN - 64)) | (1L << (START - 64)) | (1L << (NODE - 64)) | (1L << (RELATIONSHIP - 64)) | (1L << (REL - 64)) | (1L << (WHERE - 64)) | (1L << (SHORTESTPATH - 64)) | (1L << (ALLSHORTESTPATHS - 64)) | (1L << (OR - 64)) | (1L << (XOR - 64)) | (1L << (AND - 64)) | (1L << (NOT - 64)) | (1L << (STARTS - 64)) | (1L << (ENDS - 64)) | (1L << (CONTAINS - 64)) | (1L << (NULL - 64)) | (1L << (TRUE - 64)) | (1L << (FALSE - 64)) | (1L << (COUNT - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (FILTER - 128)) | (1L << (EXTRACT - 128)) | (1L << (ANY - 128)) | (1L << (NONE - 128)) | (1L << (SINGLE - 128)) | (1L << (REDUCE - 128)) | (1L << (CASE - 128)) | (1L << (ELSE - 128)) | (1L << (END - 128)) | (1L << (WHEN - 128)) | (1L << (THEN - 128)) | (1L << (L_0X - 128)) | (1L << (UnescapedSymbolicName - 128)) | (1L << (EscapedSymbolicName - 128)))) != 0)) {
				{
				setState(1036);
				variable();
				}
			}

			setState(1040);
			_la = _input.LA(1);
			if (_la==T__12) {
				{
				setState(1039);
				match(T__12);
				}
			}

			setState(1043);
			_la = _input.LA(1);
			if (_la==T__11) {
				{
				setState(1042);
				relationshipTypes();
				}
			}

			setState(1049);
			_la = _input.LA(1);
			if (_la==T__10) {
				{
				setState(1045);
				match(T__10);
				setState(1047);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__13) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38))) != 0)) {
					{
					setState(1046);
					rangeLiteral();
					}
				}

				}
			}

			setState(1052);
			_la = _input.LA(1);
			if (_la==T__27) {
				{
				setState(1051);
				properties();
				}
			}

			setState(1054);
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
		enterRule(_localctx, 144, RULE_properties);
		try {
			setState(1058);
			switch ( getInterpreter().adaptivePredict(_input,76,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1056);
				mapLiteral();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1057);
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

	public static class RelTypeContext extends ParserRuleContext {
		public RelTypeNameContext relTypeName() {
			return getRuleContext(RelTypeNameContext.class,0);
		}
		public RelTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterRelType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitRelType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitRelType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelTypeContext relType() throws RecognitionException {
		RelTypeContext _localctx = new RelTypeContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_relType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1060);
			match(T__11);
			setState(1061);
			relTypeName();
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
		enterRule(_localctx, 148, RULE_relationshipTypes);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1063);
			match(T__11);
			setState(1064);
			relTypeName();
			setState(1075);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9 || _la==WHITESPACE) {
				{
				{
				setState(1065);
				ws();
				setState(1066);
				match(T__9);
				setState(1068);
				_la = _input.LA(1);
				if (_la==T__11) {
					{
					setState(1067);
					match(T__11);
					}
				}

				setState(1070);
				ws();
				setState(1071);
				relTypeName();
				}
				}
				setState(1077);
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
		enterRule(_localctx, 150, RULE_nodeLabels);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1078);
			nodeLabel();
			setState(1084);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1079);
					ws();
					setState(1080);
					nodeLabel();
					}
					} 
				}
				setState(1086);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,79,_ctx);
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
		enterRule(_localctx, 152, RULE_nodeLabel);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1087);
			match(T__11);
			setState(1088);
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
		public List<UnsignedIntegerLiteralContext> unsignedIntegerLiteral() {
			return getRuleContexts(UnsignedIntegerLiteralContext.class);
		}
		public UnsignedIntegerLiteralContext unsignedIntegerLiteral(int i) {
			return getRuleContext(UnsignedIntegerLiteralContext.class,i);
		}
		public List<WsContext> ws() {
			return getRuleContexts(WsContext.class);
		}
		public WsContext ws(int i) {
			return getRuleContext(WsContext.class,i);
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
		enterRule(_localctx, 154, RULE_rangeLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1093);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38))) != 0)) {
				{
				setState(1090);
				unsignedIntegerLiteral();
				setState(1091);
				ws();
				}
			}

			setState(1095);
			match(T__13);
			setState(1099);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38))) != 0) || _la==WHITESPACE) {
				{
				setState(1096);
				ws();
				setState(1097);
				unsignedIntegerLiteral();
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
		enterRule(_localctx, 156, RULE_labelName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1101);
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
		enterRule(_localctx, 158, RULE_relTypeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1103);
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
		enterRule(_localctx, 160, RULE_expression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1105);
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
		enterRule(_localctx, 162, RULE_expression12);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1107);
			expression11();
			setState(1115);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1108);
					sp();
					setState(1109);
					match(OR);
					setState(1110);
					sp();
					setState(1111);
					expression11();
					}
					} 
				}
				setState(1117);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,82,_ctx);
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
		enterRule(_localctx, 164, RULE_expression11);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1118);
			expression10();
			setState(1126);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,83,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1119);
					sp();
					setState(1120);
					match(XOR);
					setState(1121);
					sp();
					setState(1122);
					expression10();
					}
					} 
				}
				setState(1128);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,83,_ctx);
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
		enterRule(_localctx, 166, RULE_expression10);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1129);
			expression9();
			setState(1137);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1130);
					sp();
					setState(1131);
					match(AND);
					setState(1132);
					sp();
					setState(1133);
					expression9();
					}
					} 
				}
				setState(1139);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,84,_ctx);
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
		enterRule(_localctx, 168, RULE_expression9);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WHITESPACE) {
				{
				{
				setState(1140);
				sp();
				setState(1141);
				match(NOT);
				setState(1142);
				sp();
				}
				}
				setState(1148);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1149);
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
		enterRule(_localctx, 170, RULE_expression8);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1151);
			expression7();
			setState(1157);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,86,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1152);
					ws();
					setState(1153);
					partialComparisonExpression();
					}
					} 
				}
				setState(1159);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,86,_ctx);
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
		enterRule(_localctx, 172, RULE_expression7);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1160);
			expression6();
			setState(1173);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(1171);
					switch ( getInterpreter().adaptivePredict(_input,87,_ctx) ) {
					case 1:
						{
						{
						setState(1161);
						ws();
						setState(1162);
						match(T__14);
						setState(1163);
						ws();
						setState(1164);
						expression6();
						}
						}
						break;
					case 2:
						{
						{
						setState(1166);
						ws();
						setState(1167);
						match(T__15);
						setState(1168);
						ws();
						setState(1169);
						expression6();
						}
						}
						break;
					}
					} 
				}
				setState(1175);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
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
		enterRule(_localctx, 174, RULE_expression6);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1176);
			expression5();
			setState(1194);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,90,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(1192);
					switch ( getInterpreter().adaptivePredict(_input,89,_ctx) ) {
					case 1:
						{
						{
						setState(1177);
						ws();
						setState(1178);
						match(T__10);
						setState(1179);
						ws();
						setState(1180);
						expression5();
						}
						}
						break;
					case 2:
						{
						{
						setState(1182);
						ws();
						setState(1183);
						match(T__16);
						setState(1184);
						ws();
						setState(1185);
						expression5();
						}
						}
						break;
					case 3:
						{
						{
						setState(1187);
						ws();
						setState(1188);
						match(T__17);
						setState(1189);
						ws();
						setState(1190);
						expression5();
						}
						}
						break;
					}
					} 
				}
				setState(1196);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,90,_ctx);
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
		enterRule(_localctx, 176, RULE_expression5);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1197);
			expression4();
			setState(1205);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,91,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1198);
					ws();
					setState(1199);
					match(T__18);
					setState(1200);
					ws();
					setState(1201);
					expression4();
					}
					} 
				}
				setState(1207);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,91,_ctx);
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
		public List<Expression3Context> expression3() {
			return getRuleContexts(Expression3Context.class);
		}
		public Expression3Context expression3(int i) {
			return getRuleContext(Expression3Context.class,i);
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
		enterRule(_localctx, 178, RULE_expression4);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1208);
			expression3();
			setState(1216);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,92,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1209);
					ws();
					setState(1210);
					_la = _input.LA(1);
					if ( !(_la==T__14 || _la==T__15) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					setState(1211);
					ws();
					setState(1212);
					expression3();
					}
					} 
				}
				setState(1218);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,92,_ctx);
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
		enterRule(_localctx, 180, RULE_expression3);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1219);
			expression2();
			setState(1273);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,97,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(1271);
					switch ( getInterpreter().adaptivePredict(_input,96,_ctx) ) {
					case 1:
						{
						{
						setState(1220);
						ws();
						setState(1221);
						match(T__5);
						setState(1222);
						expression();
						setState(1223);
						match(T__6);
						}
						}
						break;
					case 2:
						{
						{
						setState(1225);
						ws();
						setState(1226);
						match(T__5);
						setState(1228);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__3) | (1L << T__5) | (1L << T__15) | (1L << T__27) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << StringLiteral))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CYPHER - 64)) | (1L << (EXPLAIN - 64)) | (1L << (PROFILE - 64)) | (1L << (USING - 64)) | (1L << (PERIODIC - 64)) | (1L << (COMMIT - 64)) | (1L << (UNION - 64)) | (1L << (ALL - 64)) | (1L << (CREATE - 64)) | (1L << (DROP - 64)) | (1L << (INDEX - 64)) | (1L << (ON - 64)) | (1L << (CONSTRAINT - 64)) | (1L << (ASSERT - 64)) | (1L << (IS - 64)) | (1L << (UNIQUE - 64)) | (1L << (EXISTS - 64)) | (1L << (LOAD - 64)) | (1L << (CSV - 64)) | (1L << (WITH - 64)) | (1L << (HEADERS - 64)) | (1L << (FROM - 64)) | (1L << (AS - 64)) | (1L << (FIELDTERMINATOR - 64)) | (1L << (OPTIONAL - 64)) | (1L << (MATCH - 64)) | (1L << (UNWIND - 64)) | (1L << (MERGE - 64)) | (1L << (SET - 64)) | (1L << (DELETE - 64)) | (1L << (DETACH - 64)) | (1L << (REMOVE - 64)) | (1L << (FOREACH - 64)) | (1L << (IN - 64)) | (1L << (DISTINCT - 64)) | (1L << (RETURN - 64)) | (1L << (ORDER - 64)) | (1L << (BY - 64)) | (1L << (L_SKIP - 64)) | (1L << (LIMIT - 64)) | (1L << (DESCENDING - 64)) | (1L << (DESC - 64)) | (1L << (ASCENDING - 64)) | (1L << (ASC - 64)) | (1L << (JOIN - 64)) | (1L << (SCAN - 64)) | (1L << (START - 64)) | (1L << (NODE - 64)) | (1L << (RELATIONSHIP - 64)) | (1L << (REL - 64)) | (1L << (WHERE - 64)) | (1L << (SHORTESTPATH - 64)) | (1L << (ALLSHORTESTPATHS - 64)) | (1L << (OR - 64)) | (1L << (XOR - 64)) | (1L << (AND - 64)) | (1L << (NOT - 64)) | (1L << (STARTS - 64)) | (1L << (ENDS - 64)) | (1L << (CONTAINS - 64)) | (1L << (NULL - 64)) | (1L << (TRUE - 64)) | (1L << (FALSE - 64)) | (1L << (COUNT - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (FILTER - 128)) | (1L << (EXTRACT - 128)) | (1L << (ANY - 128)) | (1L << (NONE - 128)) | (1L << (SINGLE - 128)) | (1L << (REDUCE - 128)) | (1L << (CASE - 128)) | (1L << (ELSE - 128)) | (1L << (END - 128)) | (1L << (WHEN - 128)) | (1L << (THEN - 128)) | (1L << (L_0X - 128)) | (1L << (UnescapedSymbolicName - 128)) | (1L << (EscapedSymbolicName - 128)) | (1L << (WHITESPACE - 128)))) != 0)) {
							{
							setState(1227);
							expression();
							}
						}

						setState(1230);
						match(T__13);
						setState(1232);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__3) | (1L << T__5) | (1L << T__15) | (1L << T__27) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38) | (1L << StringLiteral))) != 0) || ((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CYPHER - 64)) | (1L << (EXPLAIN - 64)) | (1L << (PROFILE - 64)) | (1L << (USING - 64)) | (1L << (PERIODIC - 64)) | (1L << (COMMIT - 64)) | (1L << (UNION - 64)) | (1L << (ALL - 64)) | (1L << (CREATE - 64)) | (1L << (DROP - 64)) | (1L << (INDEX - 64)) | (1L << (ON - 64)) | (1L << (CONSTRAINT - 64)) | (1L << (ASSERT - 64)) | (1L << (IS - 64)) | (1L << (UNIQUE - 64)) | (1L << (EXISTS - 64)) | (1L << (LOAD - 64)) | (1L << (CSV - 64)) | (1L << (WITH - 64)) | (1L << (HEADERS - 64)) | (1L << (FROM - 64)) | (1L << (AS - 64)) | (1L << (FIELDTERMINATOR - 64)) | (1L << (OPTIONAL - 64)) | (1L << (MATCH - 64)) | (1L << (UNWIND - 64)) | (1L << (MERGE - 64)) | (1L << (SET - 64)) | (1L << (DELETE - 64)) | (1L << (DETACH - 64)) | (1L << (REMOVE - 64)) | (1L << (FOREACH - 64)) | (1L << (IN - 64)) | (1L << (DISTINCT - 64)) | (1L << (RETURN - 64)) | (1L << (ORDER - 64)) | (1L << (BY - 64)) | (1L << (L_SKIP - 64)) | (1L << (LIMIT - 64)) | (1L << (DESCENDING - 64)) | (1L << (DESC - 64)) | (1L << (ASCENDING - 64)) | (1L << (ASC - 64)) | (1L << (JOIN - 64)) | (1L << (SCAN - 64)) | (1L << (START - 64)) | (1L << (NODE - 64)) | (1L << (RELATIONSHIP - 64)) | (1L << (REL - 64)) | (1L << (WHERE - 64)) | (1L << (SHORTESTPATH - 64)) | (1L << (ALLSHORTESTPATHS - 64)) | (1L << (OR - 64)) | (1L << (XOR - 64)) | (1L << (AND - 64)) | (1L << (NOT - 64)) | (1L << (STARTS - 64)) | (1L << (ENDS - 64)) | (1L << (CONTAINS - 64)) | (1L << (NULL - 64)) | (1L << (TRUE - 64)) | (1L << (FALSE - 64)) | (1L << (COUNT - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (FILTER - 128)) | (1L << (EXTRACT - 128)) | (1L << (ANY - 128)) | (1L << (NONE - 128)) | (1L << (SINGLE - 128)) | (1L << (REDUCE - 128)) | (1L << (CASE - 128)) | (1L << (ELSE - 128)) | (1L << (END - 128)) | (1L << (WHEN - 128)) | (1L << (THEN - 128)) | (1L << (L_0X - 128)) | (1L << (UnescapedSymbolicName - 128)) | (1L << (EscapedSymbolicName - 128)) | (1L << (WHITESPACE - 128)))) != 0)) {
							{
							setState(1231);
							expression();
							}
						}

						setState(1234);
						match(T__6);
						}
						}
						break;
					case 3:
						{
						{
						setState(1255);
						switch ( getInterpreter().adaptivePredict(_input,95,_ctx) ) {
						case 1:
							{
							{
							setState(1236);
							ws();
							setState(1237);
							match(T__19);
							}
							}
							break;
						case 2:
							{
							{
							setState(1239);
							sp();
							setState(1240);
							match(IN);
							}
							}
							break;
						case 3:
							{
							{
							setState(1242);
							sp();
							setState(1243);
							match(STARTS);
							setState(1244);
							sp();
							setState(1245);
							match(WITH);
							}
							}
							break;
						case 4:
							{
							{
							setState(1247);
							sp();
							setState(1248);
							match(ENDS);
							setState(1249);
							sp();
							setState(1250);
							match(WITH);
							}
							}
							break;
						case 5:
							{
							{
							setState(1252);
							sp();
							setState(1253);
							match(CONTAINS);
							}
							}
							break;
						}
						setState(1257);
						expression2();
						}
						}
						break;
					case 4:
						{
						{
						setState(1259);
						sp();
						setState(1260);
						match(IS);
						setState(1261);
						sp();
						setState(1262);
						match(NULL);
						}
						}
						break;
					case 5:
						{
						{
						setState(1264);
						sp();
						setState(1265);
						match(IS);
						setState(1266);
						sp();
						setState(1267);
						match(NOT);
						setState(1268);
						sp();
						setState(1269);
						match(NULL);
						}
						}
						break;
					}
					} 
				}
				setState(1275);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,97,_ctx);
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
		enterRule(_localctx, 182, RULE_expression2);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1276);
			atom();
			setState(1281);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,99,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(1279);
					switch (_input.LA(1)) {
					case T__1:
					case WHITESPACE:
						{
						setState(1277);
						propertyLookup();
						}
						break;
					case T__11:
						{
						setState(1278);
						nodeLabels();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					} 
				}
				setState(1283);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,99,_ctx);
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
		public CaseExpressionContext caseExpression() {
			return getRuleContext(CaseExpressionContext.class,0);
		}
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
		public ReduceContext reduce() {
			return getRuleContext(ReduceContext.class,0);
		}
		public TerminalNode ALL() { return getToken(CypherParser.ALL, 0); }
		public TerminalNode ANY() { return getToken(CypherParser.ANY, 0); }
		public TerminalNode NONE() { return getToken(CypherParser.NONE, 0); }
		public TerminalNode SINGLE() { return getToken(CypherParser.SINGLE, 0); }
		public ShortestPathPatternContext shortestPathPattern() {
			return getRuleContext(ShortestPathPatternContext.class,0);
		}
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
		enterRule(_localctx, 184, RULE_atom);
		int _la;
		try {
			setState(1373);
			switch ( getInterpreter().adaptivePredict(_input,102,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1284);
				numberLiteral();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1285);
				match(StringLiteral);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1286);
				parameter();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(1287);
				match(TRUE);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(1288);
				match(FALSE);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(1289);
				match(NULL);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(1290);
				caseExpression();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				{
				setState(1291);
				match(COUNT);
				setState(1292);
				match(T__3);
				setState(1293);
				match(T__10);
				setState(1294);
				match(T__4);
				}
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(1295);
				mapLiteral();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(1296);
				listComprehension();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				{
				setState(1297);
				match(T__5);
				setState(1298);
				ws();
				setState(1299);
				expression();
				setState(1300);
				ws();
				setState(1308);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(1301);
					match(T__7);
					setState(1302);
					ws();
					setState(1303);
					expression();
					setState(1304);
					ws();
					}
					}
					setState(1310);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1311);
				match(T__6);
				}
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				{
				setState(1313);
				match(FILTER);
				setState(1314);
				ws();
				setState(1315);
				match(T__3);
				setState(1316);
				ws();
				setState(1317);
				filterExpression();
				setState(1318);
				ws();
				setState(1319);
				match(T__4);
				}
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				{
				setState(1321);
				match(EXTRACT);
				setState(1322);
				ws();
				setState(1323);
				match(T__3);
				setState(1324);
				ws();
				setState(1325);
				filterExpression();
				setState(1326);
				ws();
				setState(1331);
				_la = _input.LA(1);
				if (_la==T__9 || _la==WHITESPACE) {
					{
					setState(1327);
					ws();
					setState(1328);
					match(T__9);
					setState(1329);
					expression();
					}
				}

				setState(1333);
				match(T__4);
				}
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(1335);
				reduce();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				{
				setState(1336);
				match(ALL);
				setState(1337);
				ws();
				setState(1338);
				match(T__3);
				setState(1339);
				ws();
				setState(1340);
				filterExpression();
				setState(1341);
				ws();
				setState(1342);
				match(T__4);
				}
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				{
				setState(1344);
				match(ANY);
				setState(1345);
				ws();
				setState(1346);
				match(T__3);
				setState(1347);
				ws();
				setState(1348);
				filterExpression();
				setState(1349);
				ws();
				setState(1350);
				match(T__4);
				}
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				{
				setState(1352);
				match(NONE);
				setState(1353);
				ws();
				setState(1354);
				match(T__3);
				setState(1355);
				ws();
				setState(1356);
				filterExpression();
				setState(1357);
				ws();
				setState(1358);
				match(T__4);
				}
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				{
				setState(1360);
				match(SINGLE);
				setState(1361);
				ws();
				setState(1362);
				match(T__3);
				setState(1363);
				ws();
				setState(1364);
				filterExpression();
				setState(1365);
				ws();
				setState(1366);
				match(T__4);
				}
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(1368);
				shortestPathPattern();
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(1369);
				relationshipsPattern();
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(1370);
				parenthesizedExpression();
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				setState(1371);
				functionInvocation();
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(1372);
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

	public static class ReduceContext extends ParserRuleContext {
		public TerminalNode REDUCE() { return getToken(CypherParser.REDUCE, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public IdInCollContext idInColl() {
			return getRuleContext(IdInCollContext.class,0);
		}
		public ReduceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_reduce; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterReduce(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitReduce(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitReduce(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReduceContext reduce() throws RecognitionException {
		ReduceContext _localctx = new ReduceContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_reduce);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1375);
			match(REDUCE);
			setState(1376);
			ws();
			setState(1377);
			match(T__3);
			setState(1378);
			variable();
			setState(1379);
			match(T__2);
			setState(1380);
			expression();
			setState(1381);
			match(T__7);
			setState(1382);
			idInColl();
			setState(1383);
			match(T__9);
			setState(1384);
			expression();
			setState(1385);
			match(T__4);
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
		enterRule(_localctx, 188, RULE_partialComparisonExpression);
		try {
			setState(1415);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(1387);
				match(T__2);
				setState(1388);
				ws();
				setState(1389);
				expression7();
				}
				}
				break;
			case T__20:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(1391);
				match(T__20);
				setState(1392);
				ws();
				setState(1393);
				expression7();
				}
				}
				break;
			case T__21:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(1395);
				match(T__21);
				setState(1396);
				ws();
				setState(1397);
				expression7();
				}
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 4);
				{
				{
				setState(1399);
				match(T__22);
				setState(1400);
				ws();
				setState(1401);
				expression7();
				}
				}
				break;
			case T__23:
				enterOuterAlt(_localctx, 5);
				{
				{
				setState(1403);
				match(T__23);
				setState(1404);
				ws();
				setState(1405);
				expression7();
				}
				}
				break;
			case T__24:
				enterOuterAlt(_localctx, 6);
				{
				{
				setState(1407);
				match(T__24);
				setState(1408);
				ws();
				setState(1409);
				expression7();
				}
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 7);
				{
				{
				setState(1411);
				match(T__25);
				setState(1412);
				ws();
				setState(1413);
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
		enterRule(_localctx, 190, RULE_parenthesizedExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1417);
			match(T__3);
			setState(1418);
			expression();
			setState(1419);
			match(T__4);
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
		enterRule(_localctx, 192, RULE_relationshipsPattern);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1421);
			nodePattern();
			setState(1425); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1422);
					ws();
					setState(1423);
					patternElementChain();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1427); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,104,_ctx);
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
		enterRule(_localctx, 194, RULE_filterExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1429);
			idInColl();
			setState(1433);
			switch ( getInterpreter().adaptivePredict(_input,105,_ctx) ) {
			case 1:
				{
				setState(1430);
				ws();
				setState(1431);
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
		enterRule(_localctx, 196, RULE_idInColl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1435);
			variable();
			setState(1436);
			sp();
			setState(1437);
			match(IN);
			setState(1438);
			sp();
			setState(1439);
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
		enterRule(_localctx, 198, RULE_functionInvocation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1441);
			functionName();
			setState(1442);
			ws();
			setState(1443);
			match(T__3);
			setState(1444);
			ws();
			setState(1446);
			switch ( getInterpreter().adaptivePredict(_input,106,_ctx) ) {
			case 1:
				{
				setState(1445);
				match(DISTINCT);
				}
				break;
			}
			setState(1456);
			switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
			case 1:
				{
				setState(1448);
				expression();
				setState(1453);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(1449);
					match(T__7);
					setState(1450);
					expression();
					}
					}
					setState(1455);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
			setState(1458);
			ws();
			setState(1459);
			match(T__4);
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
		enterRule(_localctx, 200, RULE_functionName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1461);
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
		enterRule(_localctx, 202, RULE_listComprehension);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1463);
			match(T__5);
			setState(1464);
			filterExpression();
			setState(1469);
			_la = _input.LA(1);
			if (_la==T__9 || _la==WHITESPACE) {
				{
				setState(1465);
				ws();
				setState(1466);
				match(T__9);
				setState(1467);
				expression();
				}
			}

			setState(1471);
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
		enterRule(_localctx, 204, RULE_propertyLookup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1473);
			ws();
			setState(1474);
			match(T__1);
			setState(1475);
			ws();
			setState(1480);
			switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
			case 1:
				{
				{
				setState(1476);
				propertyKeyName();
				setState(1477);
				_la = _input.LA(1);
				if ( !(_la==T__12 || _la==T__26) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				}
				break;
			case 2:
				{
				setState(1479);
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

	public static class CaseExpressionContext extends ParserRuleContext {
		public TerminalNode END() { return getToken(CypherParser.END, 0); }
		public WsContext ws() {
			return getRuleContext(WsContext.class,0);
		}
		public TerminalNode ELSE() { return getToken(CypherParser.ELSE, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode CASE() { return getToken(CypherParser.CASE, 0); }
		public List<CaseAlternativesContext> caseAlternatives() {
			return getRuleContexts(CaseAlternativesContext.class);
		}
		public CaseAlternativesContext caseAlternatives(int i) {
			return getRuleContext(CaseAlternativesContext.class,i);
		}
		public CaseExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterCaseExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitCaseExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitCaseExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseExpressionContext caseExpression() throws RecognitionException {
		CaseExpressionContext _localctx = new CaseExpressionContext(_ctx, getState());
		enterRule(_localctx, 206, RULE_caseExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1495);
			switch ( getInterpreter().adaptivePredict(_input,113,_ctx) ) {
			case 1:
				{
				{
				setState(1482);
				match(CASE);
				setState(1484); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(1483);
					caseAlternatives();
					}
					}
					setState(1486); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WHEN );
				}
				}
				break;
			case 2:
				{
				{
				setState(1488);
				match(CASE);
				setState(1489);
				expression();
				setState(1491); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(1490);
					caseAlternatives();
					}
					}
					setState(1493); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==WHEN );
				}
				}
				break;
			}
			setState(1501);
			_la = _input.LA(1);
			if (_la==ELSE || _la==WHITESPACE) {
				{
				setState(1497);
				ws();
				setState(1498);
				match(ELSE);
				setState(1499);
				expression();
				}
			}

			setState(1503);
			match(END);
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

	public static class CaseAlternativesContext extends ParserRuleContext {
		public TerminalNode WHEN() { return getToken(CypherParser.WHEN, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode THEN() { return getToken(CypherParser.THEN, 0); }
		public CaseAlternativesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_caseAlternatives; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).enterCaseAlternatives(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CypherListener ) ((CypherListener)listener).exitCaseAlternatives(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CypherVisitor ) return ((CypherVisitor<? extends T>)visitor).visitCaseAlternatives(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CaseAlternativesContext caseAlternatives() throws RecognitionException {
		CaseAlternativesContext _localctx = new CaseAlternativesContext(_ctx, getState());
		enterRule(_localctx, 208, RULE_caseAlternatives);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1505);
			match(WHEN);
			setState(1506);
			expression();
			setState(1507);
			match(THEN);
			setState(1508);
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
		enterRule(_localctx, 210, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1510);
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
		enterRule(_localctx, 212, RULE_numberLiteral);
		try {
			setState(1514);
			switch ( getInterpreter().adaptivePredict(_input,115,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1512);
				doubleLiteral();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1513);
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
		enterRule(_localctx, 214, RULE_mapLiteral);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1516);
			match(T__27);
			setState(1517);
			ws();
			setState(1538);
			_la = _input.LA(1);
			if (((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CYPHER - 64)) | (1L << (EXPLAIN - 64)) | (1L << (PROFILE - 64)) | (1L << (USING - 64)) | (1L << (PERIODIC - 64)) | (1L << (COMMIT - 64)) | (1L << (UNION - 64)) | (1L << (ALL - 64)) | (1L << (CREATE - 64)) | (1L << (DROP - 64)) | (1L << (INDEX - 64)) | (1L << (ON - 64)) | (1L << (CONSTRAINT - 64)) | (1L << (ASSERT - 64)) | (1L << (IS - 64)) | (1L << (UNIQUE - 64)) | (1L << (EXISTS - 64)) | (1L << (LOAD - 64)) | (1L << (CSV - 64)) | (1L << (WITH - 64)) | (1L << (HEADERS - 64)) | (1L << (FROM - 64)) | (1L << (AS - 64)) | (1L << (FIELDTERMINATOR - 64)) | (1L << (OPTIONAL - 64)) | (1L << (MATCH - 64)) | (1L << (UNWIND - 64)) | (1L << (MERGE - 64)) | (1L << (SET - 64)) | (1L << (DELETE - 64)) | (1L << (DETACH - 64)) | (1L << (REMOVE - 64)) | (1L << (FOREACH - 64)) | (1L << (IN - 64)) | (1L << (DISTINCT - 64)) | (1L << (RETURN - 64)) | (1L << (ORDER - 64)) | (1L << (BY - 64)) | (1L << (L_SKIP - 64)) | (1L << (LIMIT - 64)) | (1L << (DESCENDING - 64)) | (1L << (DESC - 64)) | (1L << (ASCENDING - 64)) | (1L << (ASC - 64)) | (1L << (JOIN - 64)) | (1L << (SCAN - 64)) | (1L << (START - 64)) | (1L << (NODE - 64)) | (1L << (RELATIONSHIP - 64)) | (1L << (REL - 64)) | (1L << (WHERE - 64)) | (1L << (SHORTESTPATH - 64)) | (1L << (ALLSHORTESTPATHS - 64)) | (1L << (OR - 64)) | (1L << (XOR - 64)) | (1L << (AND - 64)) | (1L << (NOT - 64)) | (1L << (STARTS - 64)) | (1L << (ENDS - 64)) | (1L << (CONTAINS - 64)) | (1L << (NULL - 64)) | (1L << (TRUE - 64)) | (1L << (FALSE - 64)) | (1L << (COUNT - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (FILTER - 128)) | (1L << (EXTRACT - 128)) | (1L << (ANY - 128)) | (1L << (NONE - 128)) | (1L << (SINGLE - 128)) | (1L << (REDUCE - 128)) | (1L << (CASE - 128)) | (1L << (ELSE - 128)) | (1L << (END - 128)) | (1L << (WHEN - 128)) | (1L << (THEN - 128)) | (1L << (L_0X - 128)) | (1L << (UnescapedSymbolicName - 128)) | (1L << (EscapedSymbolicName - 128)))) != 0)) {
				{
				setState(1518);
				propertyKeyName();
				setState(1519);
				ws();
				setState(1520);
				match(T__11);
				setState(1521);
				ws();
				setState(1522);
				expression();
				setState(1523);
				ws();
				setState(1535);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__7) {
					{
					{
					setState(1524);
					match(T__7);
					setState(1525);
					ws();
					setState(1526);
					propertyKeyName();
					setState(1527);
					ws();
					setState(1528);
					match(T__11);
					setState(1529);
					ws();
					setState(1530);
					expression();
					setState(1531);
					ws();
					}
					}
					setState(1537);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(1540);
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
		enterRule(_localctx, 216, RULE_parameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1542);
			match(T__27);
			setState(1543);
			ws();
			setState(1546);
			switch (_input.LA(1)) {
			case CYPHER:
			case EXPLAIN:
			case PROFILE:
			case USING:
			case PERIODIC:
			case COMMIT:
			case UNION:
			case ALL:
			case CREATE:
			case DROP:
			case INDEX:
			case ON:
			case CONSTRAINT:
			case ASSERT:
			case IS:
			case UNIQUE:
			case EXISTS:
			case LOAD:
			case CSV:
			case WITH:
			case HEADERS:
			case FROM:
			case AS:
			case FIELDTERMINATOR:
			case OPTIONAL:
			case MATCH:
			case UNWIND:
			case MERGE:
			case SET:
			case DELETE:
			case DETACH:
			case REMOVE:
			case FOREACH:
			case IN:
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
			case JOIN:
			case SCAN:
			case START:
			case NODE:
			case RELATIONSHIP:
			case REL:
			case WHERE:
			case SHORTESTPATH:
			case ALLSHORTESTPATHS:
			case OR:
			case XOR:
			case AND:
			case NOT:
			case STARTS:
			case ENDS:
			case CONTAINS:
			case NULL:
			case TRUE:
			case FALSE:
			case COUNT:
			case FILTER:
			case EXTRACT:
			case ANY:
			case NONE:
			case SINGLE:
			case REDUCE:
			case CASE:
			case ELSE:
			case END:
			case WHEN:
			case THEN:
			case L_0X:
			case UnescapedSymbolicName:
			case EscapedSymbolicName:
				{
				setState(1544);
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
				setState(1545);
				unsignedDecimalInteger();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(1548);
			ws();
			setState(1549);
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
		enterRule(_localctx, 218, RULE_propertyExpression);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1551);
			atom();
			setState(1555); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1552);
					ws();
					setState(1553);
					propertyLookup();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1557); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,119,_ctx);
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
		enterRule(_localctx, 220, RULE_propertyKeyName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1559);
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
		enterRule(_localctx, 222, RULE_signedIntegerLiteral);
		try {
			setState(1564);
			switch ( getInterpreter().adaptivePredict(_input,120,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1561);
				hexInteger();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1562);
				octalInteger();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1563);
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
		enterRule(_localctx, 224, RULE_unsignedIntegerLiteral);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1566);
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
		enterRule(_localctx, 226, RULE_hexInteger);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1569);
			_la = _input.LA(1);
			if (_la==T__15) {
				{
				setState(1568);
				match(T__15);
				}
			}

			setState(1571);
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
		enterRule(_localctx, 228, RULE_decimalInteger);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1574);
			_la = _input.LA(1);
			if (_la==T__15) {
				{
				setState(1573);
				match(T__15);
				}
			}

			setState(1576);
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
		enterRule(_localctx, 230, RULE_octalInteger);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1579);
			_la = _input.LA(1);
			if (_la==T__15) {
				{
				setState(1578);
				match(T__15);
				}
			}

			setState(1581);
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
		enterRule(_localctx, 232, RULE_unsignedHexInteger);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1583);
			match(L_0X);
			setState(1584);
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
		enterRule(_localctx, 234, RULE_unsignedDecimalInteger);
		int _la;
		try {
			setState(1591);
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
				setState(1586);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(1588);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38))) != 0)) {
					{
					setState(1587);
					digitString();
					}
				}

				}
				}
				break;
			case T__38:
				enterOuterAlt(_localctx, 2);
				{
				setState(1590);
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
		enterRule(_localctx, 236, RULE_unsignedOctalInteger);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1593);
			match(T__38);
			setState(1594);
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
		enterRule(_localctx, 238, RULE_hexString);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1597); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1596);
				match(HexDigit);
				}
				}
				setState(1599); 
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
		enterRule(_localctx, 240, RULE_digitString);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1602); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1601);
				digit();
				}
				}
				setState(1604); 
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
		enterRule(_localctx, 242, RULE_octalString);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1607); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1606);
				octDigit();
				}
				}
				setState(1609); 
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
		enterRule(_localctx, 244, RULE_digit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1611);
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
		enterRule(_localctx, 246, RULE_octDigit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1613);
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
		enterRule(_localctx, 248, RULE_doubleLiteral);
		try {
			setState(1617);
			switch ( getInterpreter().adaptivePredict(_input,129,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1615);
				exponentDecimalReal();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1616);
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
		enterRule(_localctx, 250, RULE_exponentDecimalReal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1620);
			_la = _input.LA(1);
			if (_la==T__15) {
				{
				setState(1619);
				match(T__15);
				}
			}

			setState(1624); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(1624);
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
					setState(1622);
					digit();
					}
					break;
				case T__1:
					{
					setState(1623);
					match(T__1);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(1626); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__1) | (1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38))) != 0) );
			setState(1630);
			switch ( getInterpreter().adaptivePredict(_input,133,_ctx) ) {
			case 1:
				{
				setState(1628);
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
				setState(1629);
				_la = _input.LA(1);
				if ( !(_la==T__39 || _la==T__40) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			}
			setState(1633);
			_la = _input.LA(1);
			if (_la==T__15) {
				{
				setState(1632);
				match(T__15);
				}
			}

			setState(1635);
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
		enterRule(_localctx, 252, RULE_regularDecimalReal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1638);
			_la = _input.LA(1);
			if (_la==T__15) {
				{
				setState(1637);
				match(T__15);
				}
			}

			setState(1643);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__29) | (1L << T__30) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << T__38))) != 0)) {
				{
				{
				setState(1640);
				digit();
				}
				}
				setState(1645);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1646);
			match(T__1);
			setState(1647);
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
		public TerminalNode CYPHER() { return getToken(CypherParser.CYPHER, 0); }
		public TerminalNode EXPLAIN() { return getToken(CypherParser.EXPLAIN, 0); }
		public TerminalNode PROFILE() { return getToken(CypherParser.PROFILE, 0); }
		public TerminalNode USING() { return getToken(CypherParser.USING, 0); }
		public TerminalNode PERIODIC() { return getToken(CypherParser.PERIODIC, 0); }
		public TerminalNode COMMIT() { return getToken(CypherParser.COMMIT, 0); }
		public TerminalNode UNION() { return getToken(CypherParser.UNION, 0); }
		public TerminalNode ALL() { return getToken(CypherParser.ALL, 0); }
		public TerminalNode CREATE() { return getToken(CypherParser.CREATE, 0); }
		public TerminalNode DROP() { return getToken(CypherParser.DROP, 0); }
		public TerminalNode INDEX() { return getToken(CypherParser.INDEX, 0); }
		public TerminalNode ON() { return getToken(CypherParser.ON, 0); }
		public TerminalNode CONSTRAINT() { return getToken(CypherParser.CONSTRAINT, 0); }
		public TerminalNode ASSERT() { return getToken(CypherParser.ASSERT, 0); }
		public TerminalNode IS() { return getToken(CypherParser.IS, 0); }
		public TerminalNode UNIQUE() { return getToken(CypherParser.UNIQUE, 0); }
		public TerminalNode EXISTS() { return getToken(CypherParser.EXISTS, 0); }
		public TerminalNode LOAD() { return getToken(CypherParser.LOAD, 0); }
		public TerminalNode CSV() { return getToken(CypherParser.CSV, 0); }
		public TerminalNode WITH() { return getToken(CypherParser.WITH, 0); }
		public TerminalNode HEADERS() { return getToken(CypherParser.HEADERS, 0); }
		public TerminalNode FROM() { return getToken(CypherParser.FROM, 0); }
		public TerminalNode AS() { return getToken(CypherParser.AS, 0); }
		public TerminalNode FIELDTERMINATOR() { return getToken(CypherParser.FIELDTERMINATOR, 0); }
		public TerminalNode OPTIONAL() { return getToken(CypherParser.OPTIONAL, 0); }
		public TerminalNode MATCH() { return getToken(CypherParser.MATCH, 0); }
		public TerminalNode UNWIND() { return getToken(CypherParser.UNWIND, 0); }
		public TerminalNode MERGE() { return getToken(CypherParser.MERGE, 0); }
		public TerminalNode SET() { return getToken(CypherParser.SET, 0); }
		public TerminalNode DELETE() { return getToken(CypherParser.DELETE, 0); }
		public TerminalNode DETACH() { return getToken(CypherParser.DETACH, 0); }
		public TerminalNode REMOVE() { return getToken(CypherParser.REMOVE, 0); }
		public TerminalNode FOREACH() { return getToken(CypherParser.FOREACH, 0); }
		public TerminalNode IN() { return getToken(CypherParser.IN, 0); }
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
		public TerminalNode JOIN() { return getToken(CypherParser.JOIN, 0); }
		public TerminalNode SCAN() { return getToken(CypherParser.SCAN, 0); }
		public TerminalNode START() { return getToken(CypherParser.START, 0); }
		public TerminalNode NODE() { return getToken(CypherParser.NODE, 0); }
		public TerminalNode RELATIONSHIP() { return getToken(CypherParser.RELATIONSHIP, 0); }
		public TerminalNode REL() { return getToken(CypherParser.REL, 0); }
		public TerminalNode WHERE() { return getToken(CypherParser.WHERE, 0); }
		public TerminalNode SHORTESTPATH() { return getToken(CypherParser.SHORTESTPATH, 0); }
		public TerminalNode ALLSHORTESTPATHS() { return getToken(CypherParser.ALLSHORTESTPATHS, 0); }
		public TerminalNode OR() { return getToken(CypherParser.OR, 0); }
		public TerminalNode XOR() { return getToken(CypherParser.XOR, 0); }
		public TerminalNode AND() { return getToken(CypherParser.AND, 0); }
		public TerminalNode NOT() { return getToken(CypherParser.NOT, 0); }
		public TerminalNode STARTS() { return getToken(CypherParser.STARTS, 0); }
		public TerminalNode ENDS() { return getToken(CypherParser.ENDS, 0); }
		public TerminalNode CONTAINS() { return getToken(CypherParser.CONTAINS, 0); }
		public TerminalNode NULL() { return getToken(CypherParser.NULL, 0); }
		public TerminalNode TRUE() { return getToken(CypherParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(CypherParser.FALSE, 0); }
		public TerminalNode COUNT() { return getToken(CypherParser.COUNT, 0); }
		public TerminalNode FILTER() { return getToken(CypherParser.FILTER, 0); }
		public TerminalNode EXTRACT() { return getToken(CypherParser.EXTRACT, 0); }
		public TerminalNode ANY() { return getToken(CypherParser.ANY, 0); }
		public TerminalNode NONE() { return getToken(CypherParser.NONE, 0); }
		public TerminalNode SINGLE() { return getToken(CypherParser.SINGLE, 0); }
		public TerminalNode REDUCE() { return getToken(CypherParser.REDUCE, 0); }
		public TerminalNode CASE() { return getToken(CypherParser.CASE, 0); }
		public TerminalNode ELSE() { return getToken(CypherParser.ELSE, 0); }
		public TerminalNode END() { return getToken(CypherParser.END, 0); }
		public TerminalNode WHEN() { return getToken(CypherParser.WHEN, 0); }
		public TerminalNode THEN() { return getToken(CypherParser.THEN, 0); }
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
		enterRule(_localctx, 254, RULE_symbolicName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1649);
			_la = _input.LA(1);
			if ( !(((((_la - 64)) & ~0x3f) == 0 && ((1L << (_la - 64)) & ((1L << (CYPHER - 64)) | (1L << (EXPLAIN - 64)) | (1L << (PROFILE - 64)) | (1L << (USING - 64)) | (1L << (PERIODIC - 64)) | (1L << (COMMIT - 64)) | (1L << (UNION - 64)) | (1L << (ALL - 64)) | (1L << (CREATE - 64)) | (1L << (DROP - 64)) | (1L << (INDEX - 64)) | (1L << (ON - 64)) | (1L << (CONSTRAINT - 64)) | (1L << (ASSERT - 64)) | (1L << (IS - 64)) | (1L << (UNIQUE - 64)) | (1L << (EXISTS - 64)) | (1L << (LOAD - 64)) | (1L << (CSV - 64)) | (1L << (WITH - 64)) | (1L << (HEADERS - 64)) | (1L << (FROM - 64)) | (1L << (AS - 64)) | (1L << (FIELDTERMINATOR - 64)) | (1L << (OPTIONAL - 64)) | (1L << (MATCH - 64)) | (1L << (UNWIND - 64)) | (1L << (MERGE - 64)) | (1L << (SET - 64)) | (1L << (DELETE - 64)) | (1L << (DETACH - 64)) | (1L << (REMOVE - 64)) | (1L << (FOREACH - 64)) | (1L << (IN - 64)) | (1L << (DISTINCT - 64)) | (1L << (RETURN - 64)) | (1L << (ORDER - 64)) | (1L << (BY - 64)) | (1L << (L_SKIP - 64)) | (1L << (LIMIT - 64)) | (1L << (DESCENDING - 64)) | (1L << (DESC - 64)) | (1L << (ASCENDING - 64)) | (1L << (ASC - 64)) | (1L << (JOIN - 64)) | (1L << (SCAN - 64)) | (1L << (START - 64)) | (1L << (NODE - 64)) | (1L << (RELATIONSHIP - 64)) | (1L << (REL - 64)) | (1L << (WHERE - 64)) | (1L << (SHORTESTPATH - 64)) | (1L << (ALLSHORTESTPATHS - 64)) | (1L << (OR - 64)) | (1L << (XOR - 64)) | (1L << (AND - 64)) | (1L << (NOT - 64)) | (1L << (STARTS - 64)) | (1L << (ENDS - 64)) | (1L << (CONTAINS - 64)) | (1L << (NULL - 64)) | (1L << (TRUE - 64)) | (1L << (FALSE - 64)) | (1L << (COUNT - 64)))) != 0) || ((((_la - 128)) & ~0x3f) == 0 && ((1L << (_la - 128)) & ((1L << (FILTER - 128)) | (1L << (EXTRACT - 128)) | (1L << (ANY - 128)) | (1L << (NONE - 128)) | (1L << (SINGLE - 128)) | (1L << (REDUCE - 128)) | (1L << (CASE - 128)) | (1L << (ELSE - 128)) | (1L << (END - 128)) | (1L << (WHEN - 128)) | (1L << (THEN - 128)) | (1L << (L_0X - 128)) | (1L << (UnescapedSymbolicName - 128)) | (1L << (EscapedSymbolicName - 128)))) != 0)) ) {
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
		enterRule(_localctx, 256, RULE_ws);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1654);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,137,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1651);
					match(WHITESPACE);
					}
					} 
				}
				setState(1656);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,137,_ctx);
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
		enterRule(_localctx, 258, RULE_sp);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1658); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(1657);
					match(WHITESPACE);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(1660); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,138,_ctx);
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
		enterRule(_localctx, 260, RULE_leftArrowHead);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1662);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__22) | (1L << T__41) | (1L << T__42) | (1L << T__43) | (1L << T__44))) != 0)) ) {
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
		enterRule(_localctx, 262, RULE_rightArrowHead);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1664);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__45) | (1L << T__46) | (1L << T__47) | (1L << T__48))) != 0)) ) {
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
		enterRule(_localctx, 264, RULE_dash);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1666);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__15) | (1L << T__49) | (1L << T__50) | (1L << T__51) | (1L << T__52) | (1L << T__53) | (1L << T__54) | (1L << T__55) | (1L << T__56) | (1L << T__57) | (1L << T__58) | (1L << T__59))) != 0)) ) {
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\u0093\u0687\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\4j\tj\4k\t"+
		"k\4l\tl\4m\tm\4n\tn\4o\to\4p\tp\4q\tq\4r\tr\4s\ts\4t\tt\4u\tu\4v\tv\4"+
		"w\tw\4x\tx\4y\ty\4z\tz\4{\t{\4|\t|\4}\t}\4~\t~\4\177\t\177\4\u0080\t\u0080"+
		"\4\u0081\t\u0081\4\u0082\t\u0082\4\u0083\t\u0083\4\u0084\t\u0084\4\u0085"+
		"\t\u0085\4\u0086\t\u0086\3\2\3\2\3\2\3\2\3\2\5\2\u0112\n\2\3\2\3\2\3\3"+
		"\3\3\3\3\7\3\u0119\n\3\f\3\16\3\u011c\13\3\3\4\3\4\3\4\5\4\u0121\n\4\3"+
		"\5\3\5\3\5\3\5\5\5\u0127\n\5\3\5\3\5\3\5\7\5\u012c\n\5\f\5\16\5\u012f"+
		"\13\5\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n"+
		"\5\n\u0141\n\n\3\13\3\13\5\13\u0145\n\13\3\f\3\f\3\f\3\f\7\f\u014b\n\f"+
		"\f\f\16\f\u014e\13\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\7\16\u0158\n"+
		"\16\f\16\16\16\u015b\13\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17"+
		"\u0165\n\17\3\20\3\20\3\20\3\20\7\20\u016b\n\20\f\20\16\20\u016e\13\20"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u0177\n\21\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u0185\n\22\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\5\23\u018f\n\23\3\24\3\24\3\24\3\24\3\25\3\25"+
		"\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30"+
		"\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 "+
		"\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 "+
		"\3 \3 \3 \3 \3 \5 \u020c\n \3!\3!\3!\3!\3!\3!\3!\3!\3!\5!\u0217\n!\3!"+
		"\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\5!\u0225\n!\3\"\3\"\5\"\u0229\n\"\3"+
		"\"\3\"\3\"\3\"\3\"\7\"\u0230\n\"\f\"\16\"\u0233\13\"\3\"\5\"\u0236\n\""+
		"\3#\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\7$\u0246\n$\f$\16$\u0249\13"+
		"$\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\5%\u0257\n%\3&\3&\3&\3&\3&\3&\3"+
		"&\3&\3&\3&\5&\u0263\n&\3\'\3\'\3\'\3\'\7\'\u0269\n\'\f\'\16\'\u026c\13"+
		"\'\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\5(\u027d\n(\3)\3)\3)\3"+
		")\7)\u0283\n)\f)\16)\u0286\13)\3)\3)\3)\3)\3)\3)\7)\u028e\n)\f)\16)\u0291"+
		"\13)\5)\u0293\n)\3*\3*\3*\3*\3*\3*\3*\3*\7*\u029d\n*\f*\16*\u02a0\13*"+
		"\3+\3+\3+\3+\5+\u02a6\n+\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\6,\u02b5"+
		"\n,\r,\16,\u02b6\3,\3,\3,\3-\3-\3-\3-\3-\5-\u02c1\n-\3-\3-\3-\3-\5-\u02c7"+
		"\n-\5-\u02c9\n-\3.\3.\3.\3.\3.\3.\3.\3.\3.\3.\5.\u02d5\n.\3/\3/\3/\3/"+
		"\5/\u02db\n/\3/\3/\3/\5/\u02e0\n/\3/\3/\3/\5/\u02e5\n/\3\60\3\60\3\60"+
		"\3\60\3\60\3\60\7\60\u02ed\n\60\f\60\16\60\u02f0\13\60\3\60\3\60\3\60"+
		"\3\60\3\60\3\60\7\60\u02f8\n\60\f\60\16\60\u02fb\13\60\5\60\u02fd\n\60"+
		"\3\61\3\61\3\61\3\61\3\61\3\61\3\61\5\61\u0306\n\61\3\62\3\62\3\62\3\62"+
		"\3\62\3\62\3\62\3\62\3\62\7\62\u0311\n\62\f\62\16\62\u0314\13\62\3\63"+
		"\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\5\65\u0323"+
		"\n\65\5\65\u0325\n\65\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66"+
		"\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\7\66\u033d"+
		"\n\66\f\66\16\66\u0340\13\66\3\66\3\66\3\66\3\66\3\66\3\66\3\66\5\66\u0349"+
		"\n\66\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\7\67\u0353\n\67\f\67\16"+
		"\67\u0356\13\67\3\67\5\67\u0359\n\67\38\38\38\38\38\38\39\39\59\u0363"+
		"\n9\3:\3:\3:\3:\5:\u0369\n:\3;\3;\3;\3;\5;\u036f\n;\3<\3<\3<\3<\3<\3<"+
		"\3<\5<\u0378\n<\3<\3<\3=\3=\3=\3=\3=\5=\u0381\n=\3=\3=\3>\3>\3>\3>\5>"+
		"\u0389\n>\3>\3>\3?\3?\3?\3?\3?\3?\7?\u0393\n?\f?\16?\u0396\13?\3@\3@\3"+
		"@\3@\3A\3A\3A\7A\u039f\nA\fA\16A\u03a2\13A\3B\3B\3B\3B\3B\3B\3B\5B\u03ab"+
		"\nB\3C\3C\5C\u03af\nC\3D\3D\3D\3D\3D\3D\3D\3D\3D\3D\5D\u03bb\nD\3E\3E"+
		"\3E\3E\7E\u03c1\nE\fE\16E\u03c4\13E\3E\3E\3E\3E\5E\u03ca\nE\3F\3F\3F\3"+
		"F\3F\5F\u03d1\nF\3F\5F\u03d4\nF\3F\5F\u03d7\nF\3F\3F\3F\3G\3G\3G\3G\3"+
		"H\3H\3H\3H\3H\3H\5H\u03e6\nH\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\5H\u03f3"+
		"\nH\3H\3H\3H\3H\3H\3H\3H\5H\u03fc\nH\3H\3H\3H\3H\3H\3H\3H\3H\3H\5H\u0407"+
		"\nH\3H\3H\3H\5H\u040c\nH\3I\3I\5I\u0410\nI\3I\5I\u0413\nI\3I\5I\u0416"+
		"\nI\3I\3I\5I\u041a\nI\5I\u041c\nI\3I\5I\u041f\nI\3I\3I\3J\3J\5J\u0425"+
		"\nJ\3K\3K\3K\3L\3L\3L\3L\3L\5L\u042f\nL\3L\3L\3L\7L\u0434\nL\fL\16L\u0437"+
		"\13L\3M\3M\3M\3M\7M\u043d\nM\fM\16M\u0440\13M\3N\3N\3N\3O\3O\3O\5O\u0448"+
		"\nO\3O\3O\3O\3O\5O\u044e\nO\3P\3P\3Q\3Q\3R\3R\3S\3S\3S\3S\3S\3S\7S\u045c"+
		"\nS\fS\16S\u045f\13S\3T\3T\3T\3T\3T\3T\7T\u0467\nT\fT\16T\u046a\13T\3"+
		"U\3U\3U\3U\3U\3U\7U\u0472\nU\fU\16U\u0475\13U\3V\3V\3V\3V\7V\u047b\nV"+
		"\fV\16V\u047e\13V\3V\3V\3W\3W\3W\3W\7W\u0486\nW\fW\16W\u0489\13W\3X\3"+
		"X\3X\3X\3X\3X\3X\3X\3X\3X\3X\7X\u0496\nX\fX\16X\u0499\13X\3Y\3Y\3Y\3Y"+
		"\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\7Y\u04ab\nY\fY\16Y\u04ae\13Y\3Z\3"+
		"Z\3Z\3Z\3Z\3Z\7Z\u04b6\nZ\fZ\16Z\u04b9\13Z\3[\3[\3[\3[\3[\3[\7[\u04c1"+
		"\n[\f[\16[\u04c4\13[\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\5\\\u04cf\n\\"+
		"\3\\\3\\\5\\\u04d3\n\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\"+
		"\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\5\\\u04ea\n\\\3\\\3\\\3\\\3\\\3\\"+
		"\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\3\\\7\\\u04fa\n\\\f\\\16\\\u04fd\13\\"+
		"\3]\3]\3]\7]\u0502\n]\f]\16]\u0505\13]\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3"+
		"^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\7^\u051d\n^\f^\16^\u0520\13^\3^\3^"+
		"\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\5^\u0536\n^\3^"+
		"\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^"+
		"\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\3^\5^\u0560\n^\3_\3_\3_"+
		"\3_\3_\3_\3_\3_\3_\3_\3_\3_\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`"+
		"\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\3`\5`\u058a\n`\3a\3a\3a\3a\3b"+
		"\3b\3b\3b\6b\u0594\nb\rb\16b\u0595\3c\3c\3c\3c\5c\u059c\nc\3d\3d\3d\3"+
		"d\3d\3d\3e\3e\3e\3e\3e\5e\u05a9\ne\3e\3e\3e\7e\u05ae\ne\fe\16e\u05b1\13"+
		"e\5e\u05b3\ne\3e\3e\3e\3f\3f\3g\3g\3g\3g\3g\3g\5g\u05c0\ng\3g\3g\3h\3"+
		"h\3h\3h\3h\3h\3h\5h\u05cb\nh\3i\3i\6i\u05cf\ni\ri\16i\u05d0\3i\3i\3i\6"+
		"i\u05d6\ni\ri\16i\u05d7\5i\u05da\ni\3i\3i\3i\3i\5i\u05e0\ni\3i\3i\3j\3"+
		"j\3j\3j\3j\3k\3k\3l\3l\5l\u05ed\nl\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3"+
		"m\3m\3m\3m\3m\3m\7m\u0600\nm\fm\16m\u0603\13m\5m\u0605\nm\3m\3m\3n\3n"+
		"\3n\3n\5n\u060d\nn\3n\3n\3n\3o\3o\3o\3o\6o\u0616\no\ro\16o\u0617\3p\3"+
		"p\3q\3q\3q\5q\u061f\nq\3r\3r\3s\5s\u0624\ns\3s\3s\3t\5t\u0629\nt\3t\3"+
		"t\3u\5u\u062e\nu\3u\3u\3v\3v\3v\3w\3w\5w\u0637\nw\3w\5w\u063a\nw\3x\3"+
		"x\3x\3y\6y\u0640\ny\ry\16y\u0641\3z\6z\u0645\nz\rz\16z\u0646\3{\6{\u064a"+
		"\n{\r{\16{\u064b\3|\3|\3}\3}\3~\3~\5~\u0654\n~\3\177\5\177\u0657\n\177"+
		"\3\177\3\177\6\177\u065b\n\177\r\177\16\177\u065c\3\177\3\177\5\177\u0661"+
		"\n\177\3\177\5\177\u0664\n\177\3\177\3\177\3\u0080\5\u0080\u0669\n\u0080"+
		"\3\u0080\7\u0080\u066c\n\u0080\f\u0080\16\u0080\u066f\13\u0080\3\u0080"+
		"\3\u0080\3\u0080\3\u0081\3\u0081\3\u0082\7\u0082\u0677\n\u0082\f\u0082"+
		"\16\u0082\u067a\13\u0082\3\u0083\6\u0083\u067d\n\u0083\r\u0083\16\u0083"+
		"\u067e\3\u0084\3\u0084\3\u0085\3\u0085\3\u0086\3\u0086\3\u0086\2\2\u0087"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@BDFH"+
		"JLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c"+
		"\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2\u00a4"+
		"\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8\u00ba\u00bc"+
		"\u00be\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc\u00ce\u00d0\u00d2\u00d4"+
		"\u00d6\u00d8\u00da\u00dc\u00de\u00e0\u00e2\u00e4\u00e6\u00e8\u00ea\u00ec"+
		"\u00ee\u00f0\u00f2\u00f4\u00f6\u00f8\u00fa\u00fc\u00fe\u0100\u0102\u0104"+
		"\u0106\u0108\u010a\2\17\3\2jk\3\2lm\3\2rs\3\2\21\22\4\2\17\17\35\35\3"+
		"\2 (\3\2 )\4\2 &))\3\2*+\4\2B\u008e\u0091\u0091\4\2\31\31,/\4\2\32\32"+
		"\60\63\4\2\22\22\64>\u06c8\2\u010c\3\2\2\2\4\u011a\3\2\2\2\6\u0120\3\2"+
		"\2\2\b\u0122\3\2\2\2\n\u0130\3\2\2\2\f\u0134\3\2\2\2\16\u0136\3\2\2\2"+
		"\20\u0138\3\2\2\2\22\u0140\3\2\2\2\24\u0144\3\2\2\2\26\u0146\3\2\2\2\30"+
		"\u014f\3\2\2\2\32\u0153\3\2\2\2\34\u015c\3\2\2\2\36\u0166\3\2\2\2 \u0176"+
		"\3\2\2\2\"\u0184\3\2\2\2$\u018e\3\2\2\2&\u0190\3\2\2\2(\u0194\3\2\2\2"+
		"*\u0198\3\2\2\2,\u019c\3\2\2\2.\u01a0\3\2\2\2\60\u01a4\3\2\2\2\62\u01a8"+
		"\3\2\2\2\64\u01ac\3\2\2\2\66\u01b0\3\2\2\28\u01b9\3\2\2\2:\u01c7\3\2\2"+
		"\2<\u01d6\3\2\2\2>\u020b\3\2\2\2@\u020d\3\2\2\2B\u0228\3\2\2\2D\u0237"+
		"\3\2\2\2F\u023f\3\2\2\2H\u0256\3\2\2\2J\u0262\3\2\2\2L\u0264\3\2\2\2N"+
		"\u027c\3\2\2\2P\u0292\3\2\2\2R\u0294\3\2\2\2T\u02a5\3\2\2\2V\u02a7\3\2"+
		"\2\2X\u02c8\3\2\2\2Z\u02d4\3\2\2\2\\\u02d6\3\2\2\2^\u02fc\3\2\2\2`\u0305"+
		"\3\2\2\2b\u0307\3\2\2\2d\u0315\3\2\2\2f\u0319\3\2\2\2h\u0324\3\2\2\2j"+
		"\u0348\3\2\2\2l\u034a\3\2\2\2n\u035a\3\2\2\2p\u0362\3\2\2\2r\u0364\3\2"+
		"\2\2t\u036a\3\2\2\2v\u0370\3\2\2\2x\u037b\3\2\2\2z\u0384\3\2\2\2|\u038c"+
		"\3\2\2\2~\u0397\3\2\2\2\u0080\u039b\3\2\2\2\u0082\u03aa\3\2\2\2\u0084"+
		"\u03ae\3\2\2\2\u0086\u03ba\3\2\2\2\u0088\u03c9\3\2\2\2\u008a\u03cb\3\2"+
		"\2\2\u008c\u03db\3\2\2\2\u008e\u040b\3\2\2\2\u0090\u040d\3\2\2\2\u0092"+
		"\u0424\3\2\2\2\u0094\u0426\3\2\2\2\u0096\u0429\3\2\2\2\u0098\u0438\3\2"+
		"\2\2\u009a\u0441\3\2\2\2\u009c\u0447\3\2\2\2\u009e\u044f\3\2\2\2\u00a0"+
		"\u0451\3\2\2\2\u00a2\u0453\3\2\2\2\u00a4\u0455\3\2\2\2\u00a6\u0460\3\2"+
		"\2\2\u00a8\u046b\3\2\2\2\u00aa\u047c\3\2\2\2\u00ac\u0481\3\2\2\2\u00ae"+
		"\u048a\3\2\2\2\u00b0\u049a\3\2\2\2\u00b2\u04af\3\2\2\2\u00b4\u04ba\3\2"+
		"\2\2\u00b6\u04c5\3\2\2\2\u00b8\u04fe\3\2\2\2\u00ba\u055f\3\2\2\2\u00bc"+
		"\u0561\3\2\2\2\u00be\u0589\3\2\2\2\u00c0\u058b\3\2\2\2\u00c2\u058f\3\2"+
		"\2\2\u00c4\u0597\3\2\2\2\u00c6\u059d\3\2\2\2\u00c8\u05a3\3\2\2\2\u00ca"+
		"\u05b7\3\2\2\2\u00cc\u05b9\3\2\2\2\u00ce\u05c3\3\2\2\2\u00d0\u05d9\3\2"+
		"\2\2\u00d2\u05e3\3\2\2\2\u00d4\u05e8\3\2\2\2\u00d6\u05ec\3\2\2\2\u00d8"+
		"\u05ee\3\2\2\2\u00da\u0608\3\2\2\2\u00dc\u0611\3\2\2\2\u00de\u0619\3\2"+
		"\2\2\u00e0\u061e\3\2\2\2\u00e2\u0620\3\2\2\2\u00e4\u0623\3\2\2\2\u00e6"+
		"\u0628\3\2\2\2\u00e8\u062d\3\2\2\2\u00ea\u0631\3\2\2\2\u00ec\u0639\3\2"+
		"\2\2\u00ee\u063b\3\2\2\2\u00f0\u063f\3\2\2\2\u00f2\u0644\3\2\2\2\u00f4"+
		"\u0649\3\2\2\2\u00f6\u064d\3\2\2\2\u00f8\u064f\3\2\2\2\u00fa\u0653\3\2"+
		"\2\2\u00fc\u0656\3\2\2\2\u00fe\u0668\3\2\2\2\u0100\u0673\3\2\2\2\u0102"+
		"\u0678\3\2\2\2\u0104\u067c\3\2\2\2\u0106\u0680\3\2\2\2\u0108\u0682\3\2"+
		"\2\2\u010a\u0684\3\2\2\2\u010c\u010d\5\u0102\u0082\2\u010d\u010e\5\4\3"+
		"\2\u010e\u010f\5\22\n\2\u010f\u0111\5\u0102\u0082\2\u0110\u0112\7\3\2"+
		"\2\u0111\u0110\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0114"+
		"\5\u0102\u0082\2\u0114\3\3\2\2\2\u0115\u0116\5\6\4\2\u0116\u0117\5\u0102"+
		"\u0082\2\u0117\u0119\3\2\2\2\u0118\u0115\3\2\2\2\u0119\u011c\3\2\2\2\u011a"+
		"\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011b\5\3\2\2\2\u011c\u011a\3\2\2\2"+
		"\u011d\u0121\5\b\5\2\u011e\u0121\5\f\7\2\u011f\u0121\5\16\b\2\u0120\u011d"+
		"\3\2\2\2\u0120\u011e\3\2\2\2\u0120\u011f\3\2\2\2\u0121\7\3\2\2\2\u0122"+
		"\u0126\7B\2\2\u0123\u0124\5\u0104\u0083\2\u0124\u0125\5\n\6\2\u0125\u0127"+
		"\3\2\2\2\u0126\u0123\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u012d\3\2\2\2\u0128"+
		"\u0129\5\u0104\u0083\2\u0129\u012a\5\20\t\2\u012a\u012c\3\2\2\2\u012b"+
		"\u0128\3\2\2\2\u012c\u012f\3\2\2\2\u012d\u012b\3\2\2\2\u012d\u012e\3\2"+
		"\2\2\u012e\t\3\2\2\2\u012f\u012d\3\2\2\2\u0130\u0131\5\u00f2z\2\u0131"+
		"\u0132\7\4\2\2\u0132\u0133\5\u00f2z\2\u0133\13\3\2\2\2\u0134\u0135\7C"+
		"\2\2\u0135\r\3\2\2\2\u0136\u0137\7D\2\2\u0137\17\3\2\2\2\u0138\u0139\5"+
		"\u0100\u0081\2\u0139\u013a\5\u0102\u0082\2\u013a\u013b\7\5\2\2\u013b\u013c"+
		"\5\u0102\u0082\2\u013c\u013d\5\u0100\u0081\2\u013d\21\3\2\2\2\u013e\u0141"+
		"\5$\23\2\u013f\u0141\5\24\13\2\u0140\u013e\3\2\2\2\u0140\u013f\3\2\2\2"+
		"\u0141\23\3\2\2\2\u0142\u0145\5\26\f\2\u0143\u0145\5\30\r\2\u0144\u0142"+
		"\3\2\2\2\u0144\u0143\3\2\2\2\u0145\25\3\2\2\2\u0146\u014c\5\32\16\2\u0147"+
		"\u0148\5\u0102\u0082\2\u0148\u0149\5 \21\2\u0149\u014b\3\2\2\2\u014a\u0147"+
		"\3\2\2\2\u014b\u014e\3\2\2\2\u014c\u014a\3\2\2\2\u014c\u014d\3\2\2\2\u014d"+
		"\27\3\2\2\2\u014e\u014c\3\2\2\2\u014f\u0150\5\34\17\2\u0150\u0151\5\u0102"+
		"\u0082\2\u0151\u0152\5\36\20\2\u0152\31\3\2\2\2\u0153\u0159\5\"\22\2\u0154"+
		"\u0155\5\u0102\u0082\2\u0155\u0156\5\"\22\2\u0156\u0158\3\2\2\2\u0157"+
		"\u0154\3\2\2\2\u0158\u015b\3\2\2\2\u0159\u0157\3\2\2\2\u0159\u015a\3\2"+
		"\2\2\u015a\33\3\2\2\2\u015b\u0159\3\2\2\2\u015c\u015d\7E\2\2\u015d\u015e"+
		"\5\u0104\u0083\2\u015e\u015f\7F\2\2\u015f\u0160\5\u0104\u0083\2\u0160"+
		"\u0164\7G\2\2\u0161\u0162\5\u0104\u0083\2\u0162\u0163\5\u00e0q\2\u0163"+
		"\u0165\3\2\2\2\u0164\u0161\3\2\2\2\u0164\u0165\3\2\2\2\u0165\35\3\2\2"+
		"\2\u0166\u016c\5@!\2\u0167\u0168\5\u0102\u0082\2\u0168\u0169\5\"\22\2"+
		"\u0169\u016b\3\2\2\2\u016a\u0167\3\2\2\2\u016b\u016e\3\2\2\2\u016c\u016a"+
		"\3\2\2\2\u016c\u016d\3\2\2\2\u016d\37\3\2\2\2\u016e\u016c\3\2\2\2\u016f"+
		"\u0170\7H\2\2\u0170\u0171\5\u0104\u0083\2\u0171\u0172\7I\2\2\u0172\u0173"+
		"\5\32\16\2\u0173\u0177\3\2\2\2\u0174\u0175\7H\2\2\u0175\u0177\5\32\16"+
		"\2\u0176\u016f\3\2\2\2\u0176\u0174\3\2\2\2\u0177!\3\2\2\2\u0178\u0185"+
		"\5@!\2\u0179\u0185\5l\67\2\u017a\u0185\5B\"\2\u017b\u0185\5D#\2\u017c"+
		"\u0185\5F$\2\u017d\u0185\5J&\2\u017e\u0185\5L\'\2\u017f\u0185\5P)\2\u0180"+
		"\u0185\5R*\2\u0181\u0185\5V,\2\u0182\u0185\5X-\2\u0183\u0185\5Z.\2\u0184"+
		"\u0178\3\2\2\2\u0184\u0179\3\2\2\2\u0184\u017a\3\2\2\2\u0184\u017b\3\2"+
		"\2\2\u0184\u017c\3\2\2\2\u0184\u017d\3\2\2\2\u0184\u017e\3\2\2\2\u0184"+
		"\u017f\3\2\2\2\u0184\u0180\3\2\2\2\u0184\u0181\3\2\2\2\u0184\u0182\3\2"+
		"\2\2\u0184\u0183\3\2\2\2\u0185#\3\2\2\2\u0186\u018f\5,\27\2\u0187\u018f"+
		"\5\64\33\2\u0188\u018f\5&\24\2\u0189\u018f\5.\30\2\u018a\u018f\5(\25\2"+
		"\u018b\u018f\5\60\31\2\u018c\u018f\5*\26\2\u018d\u018f\5\62\32\2\u018e"+
		"\u0186\3\2\2\2\u018e\u0187\3\2\2\2\u018e\u0188\3\2\2\2\u018e\u0189\3\2"+
		"\2\2\u018e\u018a\3\2\2\2\u018e\u018b\3\2\2\2\u018e\u018c\3\2\2\2\u018e"+
		"\u018d\3\2\2\2\u018f%\3\2\2\2\u0190\u0191\7J\2\2\u0191\u0192\5\u0102\u0082"+
		"\2\u0192\u0193\58\35\2\u0193\'\3\2\2\2\u0194\u0195\7J\2\2\u0195\u0196"+
		"\5\u0102\u0082\2\u0196\u0197\5:\36\2\u0197)\3\2\2\2\u0198\u0199\7J\2\2"+
		"\u0199\u019a\5\u0102\u0082\2\u019a\u019b\5<\37\2\u019b+\3\2\2\2\u019c"+
		"\u019d\7J\2\2\u019d\u019e\5\u0104\u0083\2\u019e\u019f\5\66\34\2\u019f"+
		"-\3\2\2\2\u01a0\u01a1\7K\2\2\u01a1\u01a2\5\u0104\u0083\2\u01a2\u01a3\5"+
		"8\35\2\u01a3/\3\2\2\2\u01a4\u01a5\7K\2\2\u01a5\u01a6\5\u0104\u0083\2\u01a6"+
		"\u01a7\5:\36\2\u01a7\61\3\2\2\2\u01a8\u01a9\7K\2\2\u01a9\u01aa\5\u0104"+
		"\u0083\2\u01aa\u01ab\5<\37\2\u01ab\63\3\2\2\2\u01ac\u01ad\7K\2\2\u01ad"+
		"\u01ae\5\u0104\u0083\2\u01ae\u01af\5\66\34\2\u01af\65\3\2\2\2\u01b0\u01b1"+
		"\7L\2\2\u01b1\u01b2\5\u0104\u0083\2\u01b2\u01b3\7M\2\2\u01b3\u01b4\5\u0102"+
		"\u0082\2\u01b4\u01b5\5\u009aN\2\u01b5\u01b6\7\6\2\2\u01b6\u01b7\5\u00de"+
		"p\2\u01b7\u01b8\7\7\2\2\u01b8\67\3\2\2\2\u01b9\u01ba\7N\2\2\u01ba\u01bb"+
		"\5\u0104\u0083\2\u01bb\u01bc\7M\2\2\u01bc\u01bd\5\u0102\u0082\2\u01bd"+
		"\u01be\7\6\2\2\u01be\u01bf\5\u00d4k\2\u01bf\u01c0\5\u009aN\2\u01c0\u01c1"+
		"\7\7\2\2\u01c1\u01c2\7O\2\2\u01c2\u01c3\5\u00dco\2\u01c3\u01c4\7P\2\2"+
		"\u01c4\u01c5\5\u0104\u0083\2\u01c5\u01c6\7Q\2\2\u01c69\3\2\2\2\u01c7\u01c8"+
		"\7N\2\2\u01c8\u01c9\5\u0104\u0083\2\u01c9\u01ca\7M\2\2\u01ca\u01cb\5\u0102"+
		"\u0082\2\u01cb\u01cc\7\6\2\2\u01cc\u01cd\5\u00d4k\2\u01cd\u01ce\5\u009a"+
		"N\2\u01ce\u01cf\7\7\2\2\u01cf\u01d0\7O\2\2\u01d0\u01d1\5\u0104\u0083\2"+
		"\u01d1\u01d2\7R\2\2\u01d2\u01d3\7\6\2\2\u01d3\u01d4\5\u00dco\2\u01d4\u01d5"+
		"\7\7\2\2\u01d5;\3\2\2\2\u01d6\u01d7\7N\2\2\u01d7\u01d8\5\u0104\u0083\2"+
		"\u01d8\u01d9\7M\2\2\u01d9\u01da\5\u0102\u0082\2\u01da\u01db\5> \2\u01db"+
		"\u01dc\7O\2\2\u01dc\u01dd\5\u0104\u0083\2\u01dd\u01de\7R\2\2\u01de\u01df"+
		"\7\6\2\2\u01df\u01e0\5\u00dco\2\u01e0\u01e1\7\7\2\2\u01e1=\3\2\2\2\u01e2"+
		"\u01e3\7\6\2\2\u01e3\u01e4\5\u0102\u0082\2\u01e4\u01e5\7\7\2\2\u01e5\u01e6"+
		"\5\u010a\u0086\2\u01e6\u01e7\7\b\2\2\u01e7\u01e8\5\u00d4k\2\u01e8\u01e9"+
		"\5\u0094K\2\u01e9\u01ea\7\t\2\2\u01ea\u01eb\5\u010a\u0086\2\u01eb\u01ec"+
		"\7\6\2\2\u01ec\u01ed\5\u0102\u0082\2\u01ed\u01ee\7\7\2\2\u01ee\u020c\3"+
		"\2\2\2\u01ef\u01f0\7\6\2\2\u01f0\u01f1\5\u0102\u0082\2\u01f1\u01f2\7\7"+
		"\2\2\u01f2\u01f3\5\u010a\u0086\2\u01f3\u01f4\7\b\2\2\u01f4\u01f5\5\u00d4"+
		"k\2\u01f5\u01f6\5\u0094K\2\u01f6\u01f7\7\t\2\2\u01f7\u01f8\5\u010a\u0086"+
		"\2\u01f8\u01f9\5\u0108\u0085\2\u01f9\u01fa\7\6\2\2\u01fa\u01fb\5\u0102"+
		"\u0082\2\u01fb\u01fc\7\7\2\2\u01fc\u020c\3\2\2\2\u01fd\u01fe\7\6\2\2\u01fe"+
		"\u01ff\5\u0102\u0082\2\u01ff\u0200\7\7\2\2\u0200\u0201\5\u0106\u0084\2"+
		"\u0201\u0202\5\u010a\u0086\2\u0202\u0203\7\b\2\2\u0203\u0204\5\u00d4k"+
		"\2\u0204\u0205\5\u0094K\2\u0205\u0206\7\t\2\2\u0206\u0207\5\u010a\u0086"+
		"\2\u0207\u0208\7\6\2\2\u0208\u0209\5\u0102\u0082\2\u0209\u020a\7\7\2\2"+
		"\u020a\u020c\3\2\2\2\u020b\u01e2\3\2\2\2\u020b\u01ef\3\2\2\2\u020b\u01fd"+
		"\3\2\2\2\u020c?\3\2\2\2\u020d\u020e\7S\2\2\u020e\u020f\5\u0104\u0083\2"+
		"\u020f\u0210\7T\2\2\u0210\u0216\5\u0104\u0083\2\u0211\u0212\7U\2\2\u0212"+
		"\u0213\5\u0104\u0083\2\u0213\u0214\7V\2\2\u0214\u0215\5\u0104\u0083\2"+
		"\u0215\u0217\3\2\2\2\u0216\u0211\3\2\2\2\u0216\u0217\3\2\2\2\u0217\u0218"+
		"\3\2\2\2\u0218\u0219\7W\2\2\u0219\u021a\5\u0104\u0083\2\u021a\u021b\5"+
		"\u00a2R\2\u021b\u021c\5\u0104\u0083\2\u021c\u021d\7X\2\2\u021d\u021e\5"+
		"\u0104\u0083\2\u021e\u021f\5\u00d4k\2\u021f\u0224\5\u0104\u0083\2\u0220"+
		"\u0221\7Y\2\2\u0221\u0222\5\u0104\u0083\2\u0222\u0223\7?\2\2\u0223\u0225"+
		"\3\2\2\2\u0224\u0220\3\2\2\2\u0224\u0225\3\2\2\2\u0225A\3\2\2\2\u0226"+
		"\u0227\7Z\2\2\u0227\u0229\5\u0104\u0083\2\u0228\u0226\3\2\2\2\u0228\u0229"+
		"\3\2\2\2\u0229\u022a\3\2\2\2\u022a\u022b\7[\2\2\u022b\u022c\5\u0102\u0082"+
		"\2\u022c\u022d\5\u0080A\2\u022d\u0231\5\u0102\u0082\2\u022e\u0230\5j\66"+
		"\2\u022f\u022e\3\2\2\2\u0230\u0233\3\2\2\2\u0231\u022f\3\2\2\2\u0231\u0232"+
		"\3\2\2\2\u0232\u0235\3\2\2\2\u0233\u0231\3\2\2\2\u0234\u0236\5~@\2\u0235"+
		"\u0234\3\2\2\2\u0235\u0236\3\2\2\2\u0236C\3\2\2\2\u0237\u0238\7\\\2\2"+
		"\u0238\u0239\5\u0102\u0082\2\u0239\u023a\5\u00a2R\2\u023a\u023b\5\u0104"+
		"\u0083\2\u023b\u023c\7X\2\2\u023c\u023d\5\u0104\u0083\2\u023d\u023e\5"+
		"\u00d4k\2\u023eE\3\2\2\2\u023f\u0240\7]\2\2\u0240\u0241\5\u0102\u0082"+
		"\2\u0241\u0247\5\u0082B\2\u0242\u0243\5\u0104\u0083\2\u0243\u0244\5H%"+
		"\2\u0244\u0246\3\2\2\2\u0245\u0242\3\2\2\2\u0246\u0249\3\2\2\2\u0247\u0245"+
		"\3\2\2\2\u0247\u0248\3\2\2\2\u0248G\3\2\2\2\u0249\u0247\3\2\2\2\u024a"+
		"\u024b\7M\2\2\u024b\u024c\5\u0104\u0083\2\u024c\u024d\7[\2\2\u024d\u024e"+
		"\5\u0104\u0083\2\u024e\u024f\5L\'\2\u024f\u0257\3\2\2\2\u0250\u0251\7"+
		"M\2\2\u0251\u0252\5\u0104\u0083\2\u0252\u0253\7J\2\2\u0253\u0254\5\u0104"+
		"\u0083\2\u0254\u0255\5L\'\2\u0255\u0257\3\2\2\2\u0256\u024a\3\2\2\2\u0256"+
		"\u0250\3\2\2\2\u0257I\3\2\2\2\u0258\u0259\7J\2\2\u0259\u025a\5\u0104\u0083"+
		"\2\u025a\u025b\7Q\2\2\u025b\u025c\5\u0102\u0082\2\u025c\u025d\5\u0080"+
		"A\2\u025d\u0263\3\2\2\2\u025e\u025f\7J\2\2\u025f\u0260\5\u0102\u0082\2"+
		"\u0260\u0261\5\u0080A\2\u0261\u0263\3\2\2\2\u0262\u0258\3\2\2\2\u0262"+
		"\u025e\3\2\2\2\u0263K\3\2\2\2\u0264\u0265\7^\2\2\u0265\u026a\5N(\2\u0266"+
		"\u0267\7\n\2\2\u0267\u0269\5N(\2\u0268\u0266\3\2\2\2\u0269\u026c\3\2\2"+
		"\2\u026a\u0268\3\2\2\2\u026a\u026b\3\2\2\2\u026bM\3\2\2\2\u026c\u026a"+
		"\3\2\2\2\u026d\u026e\5\u00dco\2\u026e\u026f\7\5\2\2\u026f\u0270\5\u00a2"+
		"R\2\u0270\u027d\3\2\2\2\u0271\u0272\5\u00d4k\2\u0272\u0273\7\5\2\2\u0273"+
		"\u0274\5\u00a2R\2\u0274\u027d\3\2\2\2\u0275\u0276\5\u00d4k\2\u0276\u0277"+
		"\7\13\2\2\u0277\u0278\5\u00a2R\2\u0278\u027d\3\2\2\2\u0279\u027a\5\u00d4"+
		"k\2\u027a\u027b\5\u0098M\2\u027b\u027d\3\2\2\2\u027c\u026d\3\2\2\2\u027c"+
		"\u0271\3\2\2\2\u027c\u0275\3\2\2\2\u027c\u0279\3\2\2\2\u027dO\3\2\2\2"+
		"\u027e\u027f\7_\2\2\u027f\u0284\5\u00a2R\2\u0280\u0281\7\n\2\2\u0281\u0283"+
		"\5\u00a2R\2\u0282\u0280\3\2\2\2\u0283\u0286\3\2\2\2\u0284\u0282\3\2\2"+
		"\2\u0284\u0285\3\2\2\2\u0285\u0293\3\2\2\2\u0286\u0284\3\2\2\2\u0287\u0288"+
		"\7`\2\2\u0288\u0289\5\u0104\u0083\2\u0289\u028a\7_\2\2\u028a\u028f\5\u00a2"+
		"R\2\u028b\u028c\7\n\2\2\u028c\u028e\5\u00a2R\2\u028d\u028b\3\2\2\2\u028e"+
		"\u0291\3\2\2\2\u028f\u028d\3\2\2\2\u028f\u0290\3\2\2\2\u0290\u0293\3\2"+
		"\2\2\u0291\u028f\3\2\2\2\u0292\u027e\3\2\2\2\u0292\u0287\3\2\2\2\u0293"+
		"Q\3\2\2\2\u0294\u0295\7a\2\2\u0295\u0296\5\u0104\u0083\2\u0296\u029e\5"+
		"T+\2\u0297\u0298\5\u0102\u0082\2\u0298\u0299\7\n\2\2\u0299\u029a\5\u0102"+
		"\u0082\2\u029a\u029b\5T+\2\u029b\u029d\3\2\2\2\u029c\u0297\3\2\2\2\u029d"+
		"\u02a0\3\2\2\2\u029e\u029c\3\2\2\2\u029e\u029f\3\2\2\2\u029fS\3\2\2\2"+
		"\u02a0\u029e\3\2\2\2\u02a1\u02a2\5\u00d4k\2\u02a2\u02a3\5\u0098M\2\u02a3"+
		"\u02a6\3\2\2\2\u02a4\u02a6\5\u00dco\2\u02a5\u02a1\3\2\2\2\u02a5\u02a4"+
		"\3\2\2\2\u02a6U\3\2\2\2\u02a7\u02a8\7b\2\2\u02a8\u02a9\5\u0102\u0082\2"+
		"\u02a9\u02aa\7\6\2\2\u02aa\u02ab\5\u0102\u0082\2\u02ab\u02ac\5\u00d4k"+
		"\2\u02ac\u02ad\5\u0104\u0083\2\u02ad\u02ae\7c\2\2\u02ae\u02af\5\u0104"+
		"\u0083\2\u02af\u02b0\5\u00a2R\2\u02b0\u02b4\7\f\2\2\u02b1\u02b2\5\u0104"+
		"\u0083\2\u02b2\u02b3\5\"\22\2\u02b3\u02b5\3\2\2\2\u02b4\u02b1\3\2\2\2"+
		"\u02b5\u02b6\3\2\2\2\u02b6\u02b4\3\2\2\2\u02b6\u02b7\3\2\2\2\u02b7\u02b8"+
		"\3\2\2\2\u02b8\u02b9\5\u0102\u0082\2\u02b9\u02ba\7\7\2\2\u02baW\3\2\2"+
		"\2\u02bb\u02bc\7U\2\2\u02bc\u02bd\7d\2\2\u02bd\u02be\5\u0104\u0083\2\u02be"+
		"\u02c0\5\\/\2\u02bf\u02c1\5~@\2\u02c0\u02bf\3\2\2\2\u02c0\u02c1\3\2\2"+
		"\2\u02c1\u02c9\3\2\2\2\u02c2\u02c3\7U\2\2\u02c3\u02c4\5\u0104\u0083\2"+
		"\u02c4\u02c6\5\\/\2\u02c5\u02c7\5~@\2\u02c6\u02c5\3\2\2\2\u02c6\u02c7"+
		"\3\2\2\2\u02c7\u02c9\3\2\2\2\u02c8\u02bb\3\2\2\2\u02c8\u02c2\3\2\2\2\u02c9"+
		"Y\3\2\2\2\u02ca\u02cb\7e\2\2\u02cb\u02cc\5\u0104\u0083\2\u02cc\u02cd\7"+
		"d\2\2\u02cd\u02ce\5\u0104\u0083\2\u02ce\u02cf\5\\/\2\u02cf\u02d5\3\2\2"+
		"\2\u02d0\u02d1\7e\2\2\u02d1\u02d2\5\u0104\u0083\2\u02d2\u02d3\5\\/\2\u02d3"+
		"\u02d5\3\2\2\2\u02d4\u02ca\3\2\2\2\u02d4\u02d0\3\2\2\2\u02d5[\3\2\2\2"+
		"\u02d6\u02da\5^\60\2\u02d7\u02d8\5\u0104\u0083\2\u02d8\u02d9\5b\62\2\u02d9"+
		"\u02db\3\2\2\2\u02da\u02d7\3\2\2\2\u02da\u02db\3\2\2\2\u02db\u02df\3\2"+
		"\2\2\u02dc\u02dd\5\u0104\u0083\2\u02dd\u02de\5d\63\2\u02de\u02e0\3\2\2"+
		"\2\u02df\u02dc\3\2\2\2\u02df\u02e0\3\2\2\2\u02e0\u02e4\3\2\2\2\u02e1\u02e2"+
		"\5\u0104\u0083\2\u02e2\u02e3\5f\64\2\u02e3\u02e5\3\2\2\2\u02e4\u02e1\3"+
		"\2\2\2\u02e4\u02e5\3\2\2\2\u02e5]\3\2\2\2\u02e6\u02ee\7\r\2\2\u02e7\u02e8"+
		"\5\u0102\u0082\2\u02e8\u02e9\7\n\2\2\u02e9\u02ea\5\u0102\u0082\2\u02ea"+
		"\u02eb\5`\61\2\u02eb\u02ed\3\2\2\2\u02ec\u02e7\3\2\2\2\u02ed\u02f0\3\2"+
		"\2\2\u02ee\u02ec\3\2\2\2\u02ee\u02ef\3\2\2\2\u02ef\u02fd\3\2\2\2\u02f0"+
		"\u02ee\3\2\2\2\u02f1\u02f9\5`\61\2\u02f2\u02f3\5\u0102\u0082\2\u02f3\u02f4"+
		"\7\n\2\2\u02f4\u02f5\5\u0102\u0082\2\u02f5\u02f6\5`\61\2\u02f6\u02f8\3"+
		"\2\2\2\u02f7\u02f2\3\2\2\2\u02f8\u02fb\3\2\2\2\u02f9\u02f7\3\2\2\2\u02f9"+
		"\u02fa\3\2\2\2\u02fa\u02fd\3\2\2\2\u02fb\u02f9\3\2\2\2\u02fc\u02e6\3\2"+
		"\2\2\u02fc\u02f1\3\2\2\2\u02fd_\3\2\2\2\u02fe\u02ff\5\u00a2R\2\u02ff\u0300"+
		"\5\u0104\u0083\2\u0300\u0301\7X\2\2\u0301\u0302\5\u0104\u0083\2\u0302"+
		"\u0303\5\u00d4k\2\u0303\u0306\3\2\2\2\u0304\u0306\5\u00a2R\2\u0305\u02fe"+
		"\3\2\2\2\u0305\u0304\3\2\2\2\u0306a\3\2\2\2\u0307\u0308\7f\2\2\u0308\u0309"+
		"\5\u0104\u0083\2\u0309\u030a\7g\2\2\u030a\u030b\5\u0104\u0083\2\u030b"+
		"\u0312\5h\65\2\u030c\u030d\7\n\2\2\u030d\u030e\5\u0102\u0082\2\u030e\u030f"+
		"\5h\65\2\u030f\u0311\3\2\2\2\u0310\u030c\3\2\2\2\u0311\u0314\3\2\2\2\u0312"+
		"\u0310\3\2\2\2\u0312\u0313\3\2\2\2\u0313c\3\2\2\2\u0314\u0312\3\2\2\2"+
		"\u0315\u0316\7h\2\2\u0316\u0317\5\u0104\u0083\2\u0317\u0318\5\u00a2R\2"+
		"\u0318e\3\2\2\2\u0319\u031a\7i\2\2\u031a\u031b\5\u0104\u0083\2\u031b\u031c"+
		"\5\u00a2R\2\u031cg\3\2\2\2\u031d\u031e\5\u00a2R\2\u031e\u031f\t\2\2\2"+
		"\u031f\u0325\3\2\2\2\u0320\u0322\5\u00a2R\2\u0321\u0323\t\3\2\2\u0322"+
		"\u0321\3\2\2\2\u0322\u0323\3\2\2\2\u0323\u0325\3\2\2\2\u0324\u031d\3\2"+
		"\2\2\u0324\u0320\3\2\2\2\u0325i\3\2\2\2\u0326\u0327\7E\2\2\u0327\u0328"+
		"\5\u0104\u0083\2\u0328\u0329\7L\2\2\u0329\u032a\5\u0104\u0083\2\u032a"+
		"\u032b\5\u00d4k\2\u032b\u032c\5\u009aN\2\u032c\u032d\7\6\2\2\u032d\u032e"+
		"\5\u00dep\2\u032e\u032f\7\7\2\2\u032f\u0349\3\2\2\2\u0330\u0331\7E\2\2"+
		"\u0331\u0332\5\u0104\u0083\2\u0332\u0333\7n\2\2\u0333\u0334\5\u0104\u0083"+
		"\2\u0334\u0335\7M\2\2\u0335\u0336\5\u0104\u0083\2\u0336\u033e\5\u00d4"+
		"k\2\u0337\u0338\5\u0102\u0082\2\u0338\u0339\7\n\2\2\u0339\u033a\5\u0102"+
		"\u0082\2\u033a\u033b\5\u00d4k\2\u033b\u033d\3\2\2\2\u033c\u0337\3\2\2"+
		"\2\u033d\u0340\3\2\2\2\u033e\u033c\3\2\2\2\u033e\u033f\3\2\2\2\u033f\u0349"+
		"\3\2\2\2\u0340\u033e\3\2\2\2\u0341\u0342\7E\2\2\u0342\u0343\5\u0104\u0083"+
		"\2\u0343\u0344\7o\2\2\u0344\u0345\5\u0104\u0083\2\u0345\u0346\5\u00d4"+
		"k\2\u0346\u0347\5\u009aN\2\u0347\u0349\3\2\2\2\u0348\u0326\3\2\2\2\u0348"+
		"\u0330\3\2\2\2\u0348\u0341\3\2\2\2\u0349k\3\2\2\2\u034a\u034b\7p\2\2\u034b"+
		"\u034c\5\u0104\u0083\2\u034c\u0354\5n8\2\u034d\u034e\5\u0102\u0082\2\u034e"+
		"\u034f\7\n\2\2\u034f\u0350\5\u0102\u0082\2\u0350\u0351\5n8\2\u0351\u0353"+
		"\3\2\2\2\u0352\u034d\3\2\2\2\u0353\u0356\3\2\2\2\u0354\u0352\3\2\2\2\u0354"+
		"\u0355\3\2\2\2\u0355\u0358\3\2\2\2\u0356\u0354\3\2\2\2\u0357\u0359\5~"+
		"@\2\u0358\u0357\3\2\2\2\u0358\u0359\3\2\2\2\u0359m\3\2\2\2\u035a\u035b"+
		"\5\u00d4k\2\u035b\u035c\5\u0102\u0082\2\u035c\u035d\7\5\2\2\u035d\u035e"+
		"\5\u0102\u0082\2\u035e\u035f\5p9\2\u035fo\3\2\2\2\u0360\u0363\5r:\2\u0361"+
		"\u0363\5t;\2\u0362\u0360\3\2\2\2\u0362\u0361\3\2\2\2\u0363q\3\2\2\2\u0364"+
		"\u0368\7q\2\2\u0365\u0369\5v<\2\u0366\u0369\5x=\2\u0367\u0369\5z>\2\u0368"+
		"\u0365\3\2\2\2\u0368\u0366\3\2\2\2\u0368\u0367\3\2\2\2\u0369s\3\2\2\2"+
		"\u036a\u036e\t\4\2\2\u036b\u036f\5v<\2\u036c\u036f\5x=\2\u036d\u036f\5"+
		"z>\2\u036e\u036b\3\2\2\2\u036e\u036c\3\2\2\2\u036e\u036d\3\2\2\2\u036f"+
		"u\3\2\2\2\u0370\u0371\7\16\2\2\u0371\u0372\5\u0100\u0081\2\u0372\u0373"+
		"\7\6\2\2\u0373\u0374\5\u0100\u0081\2\u0374\u0377\7\5\2\2\u0375\u0378\7"+
		"?\2\2\u0376\u0378\5\u00dan\2\u0377\u0375\3\2\2\2\u0377\u0376\3\2\2\2\u0378"+
		"\u0379\3\2\2\2\u0379\u037a\7\7\2\2\u037aw\3\2\2\2\u037b\u037c\7\16\2\2"+
		"\u037c\u037d\5\u0100\u0081\2\u037d\u0380\7\6\2\2\u037e\u0381\7?\2\2\u037f"+
		"\u0381\5\u00dan\2\u0380\u037e\3\2\2\2\u0380\u037f\3\2\2\2\u0381\u0382"+
		"\3\2\2\2\u0382\u0383\7\7\2\2\u0383y\3\2\2\2\u0384\u0388\7\6\2\2\u0385"+
		"\u0389\5|?\2\u0386\u0389\5\u00dan\2\u0387\u0389\7\r\2\2\u0388\u0385\3"+
		"\2\2\2\u0388\u0386\3\2\2\2\u0388\u0387\3\2\2\2\u0389\u038a\3\2\2\2\u038a"+
		"\u038b\7\7\2\2\u038b{\3\2\2\2\u038c\u0394\5\u00e2r\2\u038d\u038e\5\u0102"+
		"\u0082\2\u038e\u038f\7\n\2\2\u038f\u0390\5\u0102\u0082\2\u0390\u0391\5"+
		"\u00e2r\2\u0391\u0393\3\2\2\2\u0392\u038d\3\2\2\2\u0393\u0396\3\2\2\2"+
		"\u0394\u0392\3\2\2\2\u0394\u0395\3\2\2\2\u0395}\3\2\2\2\u0396\u0394\3"+
		"\2\2\2\u0397\u0398\7t\2\2\u0398\u0399\5\u0104\u0083\2\u0399\u039a\5\u00a2"+
		"R\2\u039a\177\3\2\2\2\u039b\u03a0\5\u0082B\2\u039c\u039d\7\n\2\2\u039d"+
		"\u039f\5\u0082B\2\u039e\u039c\3\2\2\2\u039f\u03a2\3\2\2\2\u03a0\u039e"+
		"\3\2\2\2\u03a0\u03a1\3\2\2\2\u03a1\u0081\3\2\2\2\u03a2\u03a0\3\2\2\2\u03a3"+
		"\u03a4\5\u00d4k\2\u03a4\u03a5\5\u0102\u0082\2\u03a5\u03a6\7\5\2\2\u03a6"+
		"\u03a7\5\u0102\u0082\2\u03a7\u03a8\5\u0084C\2\u03a8\u03ab\3\2\2\2\u03a9"+
		"\u03ab\5\u0084C\2\u03aa\u03a3\3\2\2\2\u03aa\u03a9\3\2\2\2\u03ab\u0083"+
		"\3\2\2\2\u03ac\u03af\5\u0086D\2\u03ad\u03af\5\u0088E\2\u03ae\u03ac\3\2"+
		"\2\2\u03ae\u03ad\3\2\2\2\u03af\u0085\3\2\2\2\u03b0\u03b1\7u\2\2\u03b1"+
		"\u03b2\7\6\2\2\u03b2\u03b3\5\u0088E\2\u03b3\u03b4\7\7\2\2\u03b4\u03bb"+
		"\3\2\2\2\u03b5\u03b6\7v\2\2\u03b6\u03b7\7\6\2\2\u03b7\u03b8\5\u0088E\2"+
		"\u03b8\u03b9\7\7\2\2\u03b9\u03bb\3\2\2\2\u03ba\u03b0\3\2\2\2\u03ba\u03b5"+
		"\3\2\2\2\u03bb\u0087\3\2\2\2\u03bc\u03c2\5\u008aF\2\u03bd\u03be\5\u0102"+
		"\u0082\2\u03be\u03bf\5\u008cG\2\u03bf\u03c1\3\2\2\2\u03c0\u03bd\3\2\2"+
		"\2\u03c1\u03c4\3\2\2\2\u03c2\u03c0\3\2\2\2\u03c2\u03c3\3\2\2\2\u03c3\u03ca"+
		"\3\2\2\2\u03c4\u03c2\3\2\2\2\u03c5\u03c6\7\6\2\2\u03c6\u03c7\5\u0088E"+
		"\2\u03c7\u03c8\7\7\2\2\u03c8\u03ca\3\2\2\2\u03c9\u03bc\3\2\2\2\u03c9\u03c5"+
		"\3\2\2\2\u03ca\u0089\3\2\2\2\u03cb\u03cc\7\6\2\2\u03cc\u03d0\5\u0102\u0082"+
		"\2\u03cd\u03ce\5\u00d4k\2\u03ce\u03cf\5\u0102\u0082\2\u03cf\u03d1\3\2"+
		"\2\2\u03d0\u03cd\3\2\2\2\u03d0\u03d1\3\2\2\2\u03d1\u03d3\3\2\2\2\u03d2"+
		"\u03d4\5\u0098M\2\u03d3\u03d2\3\2\2\2\u03d3\u03d4\3\2\2\2\u03d4\u03d6"+
		"\3\2\2\2\u03d5\u03d7\5\u0092J\2\u03d6\u03d5\3\2\2\2\u03d6\u03d7\3\2\2"+
		"\2\u03d7\u03d8\3\2\2\2\u03d8\u03d9\5\u0102\u0082\2\u03d9\u03da\7\7\2\2"+
		"\u03da\u008b\3\2\2\2\u03db\u03dc\5\u008eH\2\u03dc\u03dd\5\u0102\u0082"+
		"\2\u03dd\u03de\5\u008aF\2\u03de\u008d\3\2\2\2\u03df\u03e0\5\u0102\u0082"+
		"\2\u03e0\u03e1\5\u0106\u0084\2\u03e1\u03e2\5\u0102\u0082\2\u03e2\u03e3"+
		"\5\u010a\u0086\2\u03e3\u03e5\5\u0102\u0082\2\u03e4\u03e6\5\u0090I\2\u03e5"+
		"\u03e4\3\2\2\2\u03e5\u03e6\3\2\2\2\u03e6\u03e7\3\2\2\2\u03e7\u03e8\5\u0102"+
		"\u0082\2\u03e8\u03e9\5\u010a\u0086\2\u03e9\u03ea\5\u0102\u0082\2\u03ea"+
		"\u03eb\5\u0108\u0085\2\u03eb\u040c\3\2\2\2\u03ec\u03ed\5\u0102\u0082\2"+
		"\u03ed\u03ee\5\u0106\u0084\2\u03ee\u03ef\5\u0102\u0082\2\u03ef\u03f0\5"+
		"\u010a\u0086\2\u03f0\u03f2\5\u0102\u0082\2\u03f1\u03f3\5\u0090I\2\u03f2"+
		"\u03f1\3\2\2\2\u03f2\u03f3\3\2\2\2\u03f3\u03f4\3\2\2\2\u03f4\u03f5\5\u0102"+
		"\u0082\2\u03f5\u03f6\5\u010a\u0086\2\u03f6\u040c\3\2\2\2\u03f7\u03f8\5"+
		"\u0102\u0082\2\u03f8\u03f9\5\u010a\u0086\2\u03f9\u03fb\5\u0102\u0082\2"+
		"\u03fa\u03fc\5\u0090I\2\u03fb\u03fa\3\2\2\2\u03fb\u03fc\3\2\2\2\u03fc"+
		"\u03fd\3\2\2\2\u03fd\u03fe\5\u0102\u0082\2\u03fe\u03ff\5\u010a\u0086\2"+
		"\u03ff\u0400\5\u0102\u0082\2\u0400\u0401\5\u0108\u0085\2\u0401\u040c\3"+
		"\2\2\2\u0402\u0403\5\u0102\u0082\2\u0403\u0404\5\u010a\u0086\2\u0404\u0406"+
		"\5\u0102\u0082\2\u0405\u0407\5\u0090I\2\u0406\u0405\3\2\2\2\u0406\u0407"+
		"\3\2\2\2\u0407\u0408\3\2\2\2\u0408\u0409\5\u0102\u0082\2\u0409\u040a\5"+
		"\u010a\u0086\2\u040a\u040c\3\2\2\2\u040b\u03df\3\2\2\2\u040b\u03ec\3\2"+
		"\2\2\u040b\u03f7\3\2\2\2\u040b\u0402\3\2\2\2\u040c\u008f\3\2\2\2\u040d"+
		"\u040f\7\b\2\2\u040e\u0410\5\u00d4k\2\u040f\u040e\3\2\2\2\u040f\u0410"+
		"\3\2\2\2\u0410\u0412\3\2\2\2\u0411\u0413\7\17\2\2\u0412\u0411\3\2\2\2"+
		"\u0412\u0413\3\2\2\2\u0413\u0415\3\2\2\2\u0414\u0416\5\u0096L\2\u0415"+
		"\u0414\3\2\2\2\u0415\u0416\3\2\2\2\u0416\u041b\3\2\2\2\u0417\u0419\7\r"+
		"\2\2\u0418\u041a\5\u009cO\2\u0419\u0418\3\2\2\2\u0419\u041a\3\2\2\2\u041a"+
		"\u041c\3\2\2\2\u041b\u0417\3\2\2\2\u041b\u041c\3\2\2\2\u041c\u041e\3\2"+
		"\2\2\u041d\u041f\5\u0092J\2\u041e\u041d\3\2\2\2\u041e\u041f\3\2\2\2\u041f"+
		"\u0420\3\2\2\2\u0420\u0421\7\t\2\2\u0421\u0091\3\2\2\2\u0422\u0425\5\u00d8"+
		"m\2\u0423\u0425\5\u00dan\2\u0424\u0422\3\2\2\2\u0424\u0423\3\2\2\2\u0425"+
		"\u0093\3\2\2\2\u0426\u0427\7\16\2\2\u0427\u0428\5\u00a0Q\2\u0428\u0095"+
		"\3\2\2\2\u0429\u042a\7\16\2\2\u042a\u0435\5\u00a0Q\2\u042b\u042c\5\u0102"+
		"\u0082\2\u042c\u042e\7\f\2\2\u042d\u042f\7\16\2\2\u042e\u042d\3\2\2\2"+
		"\u042e\u042f\3\2\2\2\u042f\u0430\3\2\2\2\u0430\u0431\5\u0102\u0082\2\u0431"+
		"\u0432\5\u00a0Q\2\u0432\u0434\3\2\2\2\u0433\u042b\3\2\2\2\u0434\u0437"+
		"\3\2\2\2\u0435\u0433\3\2\2\2\u0435\u0436\3\2\2\2\u0436\u0097\3\2\2\2\u0437"+
		"\u0435\3\2\2\2\u0438\u043e\5\u009aN\2\u0439\u043a\5\u0102\u0082\2\u043a"+
		"\u043b\5\u009aN\2\u043b\u043d\3\2\2\2\u043c\u0439\3\2\2\2\u043d\u0440"+
		"\3\2\2\2\u043e\u043c\3\2\2\2\u043e\u043f\3\2\2\2\u043f\u0099\3\2\2\2\u0440"+
		"\u043e\3\2\2\2\u0441\u0442\7\16\2\2\u0442\u0443\5\u009eP\2\u0443\u009b"+
		"\3\2\2\2\u0444\u0445\5\u00e2r\2\u0445\u0446\5\u0102\u0082\2\u0446\u0448"+
		"\3\2\2\2\u0447\u0444\3\2\2\2\u0447\u0448\3\2\2\2\u0448\u0449\3\2\2\2\u0449"+
		"\u044d\7\20\2\2\u044a\u044b\5\u0102\u0082\2\u044b\u044c\5\u00e2r\2\u044c"+
		"\u044e\3\2\2\2\u044d\u044a\3\2\2\2\u044d\u044e\3\2\2\2\u044e\u009d\3\2"+
		"\2\2\u044f\u0450\5\u0100\u0081\2\u0450\u009f\3\2\2\2\u0451\u0452\5\u0100"+
		"\u0081\2\u0452\u00a1\3\2\2\2\u0453\u0454\5\u00a4S\2\u0454\u00a3\3\2\2"+
		"\2\u0455\u045d\5\u00a6T\2\u0456\u0457\5\u0104\u0083\2\u0457\u0458\7w\2"+
		"\2\u0458\u0459\5\u0104\u0083\2\u0459\u045a\5\u00a6T\2\u045a\u045c\3\2"+
		"\2\2\u045b\u0456\3\2\2\2\u045c\u045f\3\2\2\2\u045d\u045b\3\2\2\2\u045d"+
		"\u045e\3\2\2\2\u045e\u00a5\3\2\2\2\u045f\u045d\3\2\2\2\u0460\u0468\5\u00a8"+
		"U\2\u0461\u0462\5\u0104\u0083\2\u0462\u0463\7x\2\2\u0463\u0464\5\u0104"+
		"\u0083\2\u0464\u0465\5\u00a8U\2\u0465\u0467\3\2\2\2\u0466\u0461\3\2\2"+
		"\2\u0467\u046a\3\2\2\2\u0468\u0466\3\2\2\2\u0468\u0469\3\2\2\2\u0469\u00a7"+
		"\3\2\2\2\u046a\u0468\3\2\2\2\u046b\u0473\5\u00aaV\2\u046c\u046d\5\u0104"+
		"\u0083\2\u046d\u046e\7y\2\2\u046e\u046f\5\u0104\u0083\2\u046f\u0470\5"+
		"\u00aaV\2\u0470\u0472\3\2\2\2\u0471\u046c\3\2\2\2\u0472\u0475\3\2\2\2"+
		"\u0473\u0471\3\2\2\2\u0473\u0474\3\2\2\2\u0474\u00a9\3\2\2\2\u0475\u0473"+
		"\3\2\2\2\u0476\u0477\5\u0104\u0083\2\u0477\u0478\7z\2\2\u0478\u0479\5"+
		"\u0104\u0083\2\u0479\u047b\3\2\2\2\u047a\u0476\3\2\2\2\u047b\u047e\3\2"+
		"\2\2\u047c\u047a\3\2\2\2\u047c\u047d\3\2\2\2\u047d\u047f\3\2\2\2\u047e"+
		"\u047c\3\2\2\2\u047f\u0480\5\u00acW\2\u0480\u00ab\3\2\2\2\u0481\u0487"+
		"\5\u00aeX\2\u0482\u0483\5\u0102\u0082\2\u0483\u0484\5\u00be`\2\u0484\u0486"+
		"\3\2\2\2\u0485\u0482\3\2\2\2\u0486\u0489\3\2\2\2\u0487\u0485\3\2\2\2\u0487"+
		"\u0488\3\2\2\2\u0488\u00ad\3\2\2\2\u0489\u0487\3\2\2\2\u048a\u0497\5\u00b0"+
		"Y\2\u048b\u048c\5\u0102\u0082\2\u048c\u048d\7\21\2\2\u048d\u048e\5\u0102"+
		"\u0082\2\u048e\u048f\5\u00b0Y\2\u048f\u0496\3\2\2\2\u0490\u0491\5\u0102"+
		"\u0082\2\u0491\u0492\7\22\2\2\u0492\u0493\5\u0102\u0082\2\u0493\u0494"+
		"\5\u00b0Y\2\u0494\u0496\3\2\2\2\u0495\u048b\3\2\2\2\u0495\u0490\3\2\2"+
		"\2\u0496\u0499\3\2\2\2\u0497\u0495\3\2\2\2\u0497\u0498\3\2\2\2\u0498\u00af"+
		"\3\2\2\2\u0499\u0497\3\2\2\2\u049a\u04ac\5\u00b2Z\2\u049b\u049c\5\u0102"+
		"\u0082\2\u049c\u049d\7\r\2\2\u049d\u049e\5\u0102\u0082\2\u049e\u049f\5"+
		"\u00b2Z\2\u049f\u04ab\3\2\2\2\u04a0\u04a1\5\u0102\u0082\2\u04a1\u04a2"+
		"\7\23\2\2\u04a2\u04a3\5\u0102\u0082\2\u04a3\u04a4\5\u00b2Z\2\u04a4\u04ab"+
		"\3\2\2\2\u04a5\u04a6\5\u0102\u0082\2\u04a6\u04a7\7\24\2\2\u04a7\u04a8"+
		"\5\u0102\u0082\2\u04a8\u04a9\5\u00b2Z\2\u04a9\u04ab\3\2\2\2\u04aa\u049b"+
		"\3\2\2\2\u04aa\u04a0\3\2\2\2\u04aa\u04a5\3\2\2\2\u04ab\u04ae\3\2\2\2\u04ac"+
		"\u04aa\3\2\2\2\u04ac\u04ad\3\2\2\2\u04ad\u00b1\3\2\2\2\u04ae\u04ac\3\2"+
		"\2\2\u04af\u04b7\5\u00b4[\2\u04b0\u04b1\5\u0102\u0082\2\u04b1\u04b2\7"+
		"\25\2\2\u04b2\u04b3\5\u0102\u0082\2\u04b3\u04b4\5\u00b4[\2\u04b4\u04b6"+
		"\3\2\2\2\u04b5\u04b0\3\2\2\2\u04b6\u04b9\3\2\2\2\u04b7\u04b5\3\2\2\2\u04b7"+
		"\u04b8\3\2\2\2\u04b8\u00b3\3\2\2\2\u04b9\u04b7\3\2\2\2\u04ba\u04c2\5\u00b6"+
		"\\\2\u04bb\u04bc\5\u0102\u0082\2\u04bc\u04bd\t\5\2\2\u04bd\u04be\5\u0102"+
		"\u0082\2\u04be\u04bf\5\u00b6\\\2\u04bf\u04c1\3\2\2\2\u04c0\u04bb\3\2\2"+
		"\2\u04c1\u04c4\3\2\2\2\u04c2\u04c0\3\2\2\2\u04c2\u04c3\3\2\2\2\u04c3\u00b5"+
		"\3\2\2\2\u04c4\u04c2\3\2\2\2\u04c5\u04fb\5\u00b8]\2\u04c6\u04c7\5\u0102"+
		"\u0082\2\u04c7\u04c8\7\b\2\2\u04c8\u04c9\5\u00a2R\2\u04c9\u04ca\7\t\2"+
		"\2\u04ca\u04fa\3\2\2\2\u04cb\u04cc\5\u0102\u0082\2\u04cc\u04ce\7\b\2\2"+
		"\u04cd\u04cf\5\u00a2R\2\u04ce\u04cd\3\2\2\2\u04ce\u04cf\3\2\2\2\u04cf"+
		"\u04d0\3\2\2\2\u04d0\u04d2\7\20\2\2\u04d1\u04d3\5\u00a2R\2\u04d2\u04d1"+
		"\3\2\2\2\u04d2\u04d3\3\2\2\2\u04d3\u04d4\3\2\2\2\u04d4\u04d5\7\t\2\2\u04d5"+
		"\u04fa\3\2\2\2\u04d6\u04d7\5\u0102\u0082\2\u04d7\u04d8\7\26\2\2\u04d8"+
		"\u04ea\3\2\2\2\u04d9\u04da\5\u0104\u0083\2\u04da\u04db\7c\2\2\u04db\u04ea"+
		"\3\2\2\2\u04dc\u04dd\5\u0104\u0083\2\u04dd\u04de\7{\2\2\u04de\u04df\5"+
		"\u0104\u0083\2\u04df\u04e0\7U\2\2\u04e0\u04ea\3\2\2\2\u04e1\u04e2\5\u0104"+
		"\u0083\2\u04e2\u04e3\7|\2\2\u04e3\u04e4\5\u0104\u0083\2\u04e4\u04e5\7"+
		"U\2\2\u04e5\u04ea\3\2\2\2\u04e6\u04e7\5\u0104\u0083\2\u04e7\u04e8\7}\2"+
		"\2\u04e8\u04ea\3\2\2\2\u04e9\u04d6\3\2\2\2\u04e9\u04d9\3\2\2\2\u04e9\u04dc"+
		"\3\2\2\2\u04e9\u04e1\3\2\2\2\u04e9\u04e6\3\2\2\2\u04ea\u04eb\3\2\2\2\u04eb"+
		"\u04ec\5\u00b8]\2\u04ec\u04fa\3\2\2\2\u04ed\u04ee\5\u0104\u0083\2\u04ee"+
		"\u04ef\7P\2\2\u04ef\u04f0\5\u0104\u0083\2\u04f0\u04f1\7~\2\2\u04f1\u04fa"+
		"\3\2\2\2\u04f2\u04f3\5\u0104\u0083\2\u04f3\u04f4\7P\2\2\u04f4\u04f5\5"+
		"\u0104\u0083\2\u04f5\u04f6\7z\2\2\u04f6\u04f7\5\u0104\u0083\2\u04f7\u04f8"+
		"\7~\2\2\u04f8\u04fa\3\2\2\2\u04f9\u04c6\3\2\2\2\u04f9\u04cb\3\2\2\2\u04f9"+
		"\u04e9\3\2\2\2\u04f9\u04ed\3\2\2\2\u04f9\u04f2\3\2\2\2\u04fa\u04fd\3\2"+
		"\2\2\u04fb\u04f9\3\2\2\2\u04fb\u04fc\3\2\2\2\u04fc\u00b7\3\2\2\2\u04fd"+
		"\u04fb\3\2\2\2\u04fe\u0503\5\u00ba^\2\u04ff\u0502\5\u00ceh\2\u0500\u0502"+
		"\5\u0098M\2\u0501\u04ff\3\2\2\2\u0501\u0500\3\2\2\2\u0502\u0505\3\2\2"+
		"\2\u0503\u0501\3\2\2\2\u0503\u0504\3\2\2\2\u0504\u00b9\3\2\2\2\u0505\u0503"+
		"\3\2\2\2\u0506\u0560\5\u00d6l\2\u0507\u0560\7?\2\2\u0508\u0560\5\u00da"+
		"n\2\u0509\u0560\7\177\2\2\u050a\u0560\7\u0080\2\2\u050b\u0560\7~\2\2\u050c"+
		"\u0560\5\u00d0i\2\u050d\u050e\7\u0081\2\2\u050e\u050f\7\6\2\2\u050f\u0510"+
		"\7\r\2\2\u0510\u0560\7\7\2\2\u0511\u0560\5\u00d8m\2\u0512\u0560\5\u00cc"+
		"g\2\u0513\u0514\7\b\2\2\u0514\u0515\5\u0102\u0082\2\u0515\u0516\5\u00a2"+
		"R\2\u0516\u051e\5\u0102\u0082\2\u0517\u0518\7\n\2\2\u0518\u0519\5\u0102"+
		"\u0082\2\u0519\u051a\5\u00a2R\2\u051a\u051b\5\u0102\u0082\2\u051b\u051d"+
		"\3\2\2\2\u051c\u0517\3\2\2\2\u051d\u0520\3\2\2\2\u051e\u051c\3\2\2\2\u051e"+
		"\u051f\3\2\2\2\u051f\u0521\3\2\2\2\u0520\u051e\3\2\2\2\u0521\u0522\7\t"+
		"\2\2\u0522\u0560\3\2\2\2\u0523\u0524\7\u0082\2\2\u0524\u0525\5\u0102\u0082"+
		"\2\u0525\u0526\7\6\2\2\u0526\u0527\5\u0102\u0082\2\u0527\u0528\5\u00c4"+
		"c\2\u0528\u0529\5\u0102\u0082\2\u0529\u052a\7\7\2\2\u052a\u0560\3\2\2"+
		"\2\u052b\u052c\7\u0083\2\2\u052c\u052d\5\u0102\u0082\2\u052d\u052e\7\6"+
		"\2\2\u052e\u052f\5\u0102\u0082\2\u052f\u0530\5\u00c4c\2\u0530\u0535\5"+
		"\u0102\u0082\2\u0531\u0532\5\u0102\u0082\2\u0532\u0533\7\f\2\2\u0533\u0534"+
		"\5\u00a2R\2\u0534\u0536\3\2\2\2\u0535\u0531\3\2\2\2\u0535\u0536\3\2\2"+
		"\2\u0536\u0537\3\2\2\2\u0537\u0538\7\7\2\2\u0538\u0560\3\2\2\2\u0539\u0560"+
		"\5\u00bc_\2\u053a\u053b\7I\2\2\u053b\u053c\5\u0102\u0082\2\u053c\u053d"+
		"\7\6\2\2\u053d\u053e\5\u0102\u0082\2\u053e\u053f\5\u00c4c\2\u053f\u0540"+
		"\5\u0102\u0082\2\u0540\u0541\7\7\2\2\u0541\u0560\3\2\2\2\u0542\u0543\7"+
		"\u0084\2\2\u0543\u0544\5\u0102\u0082\2\u0544\u0545\7\6\2\2\u0545\u0546"+
		"\5\u0102\u0082\2\u0546\u0547\5\u00c4c\2\u0547\u0548\5\u0102\u0082\2\u0548"+
		"\u0549\7\7\2\2\u0549\u0560\3\2\2\2\u054a\u054b\7\u0085\2\2\u054b\u054c"+
		"\5\u0102\u0082\2\u054c\u054d\7\6\2\2\u054d\u054e\5\u0102\u0082\2\u054e"+
		"\u054f\5\u00c4c\2\u054f\u0550\5\u0102\u0082\2\u0550\u0551\7\7\2\2\u0551"+
		"\u0560\3\2\2\2\u0552\u0553\7\u0086\2\2\u0553\u0554\5\u0102\u0082\2\u0554"+
		"\u0555\7\6\2\2\u0555\u0556\5\u0102\u0082\2\u0556\u0557\5\u00c4c\2\u0557"+
		"\u0558\5\u0102\u0082\2\u0558\u0559\7\7\2\2\u0559\u0560\3\2\2\2\u055a\u0560"+
		"\5\u0086D\2\u055b\u0560\5\u00c2b\2\u055c\u0560\5\u00c0a\2\u055d\u0560"+
		"\5\u00c8e\2\u055e\u0560\5\u00d4k\2\u055f\u0506\3\2\2\2\u055f\u0507\3\2"+
		"\2\2\u055f\u0508\3\2\2\2\u055f\u0509\3\2\2\2\u055f\u050a\3\2\2\2\u055f"+
		"\u050b\3\2\2\2\u055f\u050c\3\2\2\2\u055f\u050d\3\2\2\2\u055f\u0511\3\2"+
		"\2\2\u055f\u0512\3\2\2\2\u055f\u0513\3\2\2\2\u055f\u0523\3\2\2\2\u055f"+
		"\u052b\3\2\2\2\u055f\u0539\3\2\2\2\u055f\u053a\3\2\2\2\u055f\u0542\3\2"+
		"\2\2\u055f\u054a\3\2\2\2\u055f\u0552\3\2\2\2\u055f\u055a\3\2\2\2\u055f"+
		"\u055b\3\2\2\2\u055f\u055c\3\2\2\2\u055f\u055d\3\2\2\2\u055f\u055e\3\2"+
		"\2\2\u0560\u00bb\3\2\2\2\u0561\u0562\7\u0087\2\2\u0562\u0563\5\u0102\u0082"+
		"\2\u0563\u0564\7\6\2\2\u0564\u0565\5\u00d4k\2\u0565\u0566\7\5\2\2\u0566"+
		"\u0567\5\u00a2R\2\u0567\u0568\7\n\2\2\u0568\u0569\5\u00c6d\2\u0569\u056a"+
		"\7\f\2\2\u056a\u056b\5\u00a2R\2\u056b\u056c\7\7\2\2\u056c\u00bd\3\2\2"+
		"\2\u056d\u056e\7\5\2\2\u056e\u056f\5\u0102\u0082\2\u056f\u0570\5\u00ae"+
		"X\2\u0570\u058a\3\2\2\2\u0571\u0572\7\27\2\2\u0572\u0573\5\u0102\u0082"+
		"\2\u0573\u0574\5\u00aeX\2\u0574\u058a\3\2\2\2\u0575\u0576\7\30\2\2\u0576"+
		"\u0577\5\u0102\u0082\2\u0577\u0578\5\u00aeX\2\u0578\u058a\3\2\2\2\u0579"+
		"\u057a\7\31\2\2\u057a\u057b\5\u0102\u0082\2\u057b\u057c\5\u00aeX\2\u057c"+
		"\u058a\3\2\2\2\u057d\u057e\7\32\2\2\u057e\u057f\5\u0102\u0082\2\u057f"+
		"\u0580\5\u00aeX\2\u0580\u058a\3\2\2\2\u0581\u0582\7\33\2\2\u0582\u0583"+
		"\5\u0102\u0082\2\u0583\u0584\5\u00aeX\2\u0584\u058a\3\2\2\2\u0585\u0586"+
		"\7\34\2\2\u0586\u0587\5\u0102\u0082\2\u0587\u0588\5\u00aeX\2\u0588\u058a"+
		"\3\2\2\2\u0589\u056d\3\2\2\2\u0589\u0571\3\2\2\2\u0589\u0575\3\2\2\2\u0589"+
		"\u0579\3\2\2\2\u0589\u057d\3\2\2\2\u0589\u0581\3\2\2\2\u0589\u0585\3\2"+
		"\2\2\u058a\u00bf\3\2\2\2\u058b\u058c\7\6\2\2\u058c\u058d\5\u00a2R\2\u058d"+
		"\u058e\7\7\2\2\u058e\u00c1\3\2\2\2\u058f\u0593\5\u008aF\2\u0590\u0591"+
		"\5\u0102\u0082\2\u0591\u0592\5\u008cG\2\u0592\u0594\3\2\2\2\u0593\u0590"+
		"\3\2\2\2\u0594\u0595\3\2\2\2\u0595\u0593\3\2\2\2\u0595\u0596\3\2\2\2\u0596"+
		"\u00c3\3\2\2\2\u0597\u059b\5\u00c6d\2\u0598\u0599\5\u0102\u0082\2\u0599"+
		"\u059a\5~@\2\u059a\u059c\3\2\2\2\u059b\u0598\3\2\2\2\u059b\u059c\3\2\2"+
		"\2\u059c\u00c5\3\2\2\2\u059d\u059e\5\u00d4k\2\u059e\u059f\5\u0104\u0083"+
		"\2\u059f\u05a0\7c\2\2\u05a0\u05a1\5\u0104\u0083\2\u05a1\u05a2\5\u00a2"+
		"R\2\u05a2\u00c7\3\2\2\2\u05a3\u05a4\5\u00caf\2\u05a4\u05a5\5\u0102\u0082"+
		"\2\u05a5\u05a6\7\6\2\2\u05a6\u05a8\5\u0102\u0082\2\u05a7\u05a9\7d\2\2"+
		"\u05a8\u05a7\3\2\2\2\u05a8\u05a9\3\2\2\2\u05a9\u05b2\3\2\2\2\u05aa\u05af"+
		"\5\u00a2R\2\u05ab\u05ac\7\n\2\2\u05ac\u05ae\5\u00a2R\2\u05ad\u05ab\3\2"+
		"\2\2\u05ae\u05b1\3\2\2\2\u05af\u05ad\3\2\2\2\u05af\u05b0\3\2\2\2\u05b0"+
		"\u05b3\3\2\2\2\u05b1\u05af\3\2\2\2\u05b2\u05aa\3\2\2\2\u05b2\u05b3\3\2"+
		"\2\2\u05b3\u05b4\3\2\2\2\u05b4\u05b5\5\u0102\u0082\2\u05b5\u05b6\7\7\2"+
		"\2\u05b6\u00c9\3\2\2\2\u05b7\u05b8\5\u0100\u0081\2\u05b8\u00cb\3\2\2\2"+
		"\u05b9\u05ba\7\b\2\2\u05ba\u05bf\5\u00c4c\2\u05bb\u05bc\5\u0102\u0082"+
		"\2\u05bc\u05bd\7\f\2\2\u05bd\u05be\5\u00a2R\2\u05be\u05c0\3\2\2\2\u05bf"+
		"\u05bb\3\2\2\2\u05bf\u05c0\3\2\2\2\u05c0\u05c1\3\2\2\2\u05c1\u05c2\7\t"+
		"\2\2\u05c2\u00cd\3\2\2\2\u05c3\u05c4\5\u0102\u0082\2\u05c4\u05c5\7\4\2"+
		"\2\u05c5\u05ca\5\u0102\u0082\2\u05c6\u05c7\5\u00dep\2\u05c7\u05c8\t\6"+
		"\2\2\u05c8\u05cb\3\2\2\2\u05c9\u05cb\5\u00dep\2\u05ca\u05c6\3\2\2\2\u05ca"+
		"\u05c9\3\2\2\2\u05cb\u00cf\3\2\2\2\u05cc\u05ce\7\u0088\2\2\u05cd\u05cf"+
		"\5\u00d2j\2\u05ce\u05cd\3\2\2\2\u05cf\u05d0\3\2\2\2\u05d0\u05ce\3\2\2"+
		"\2\u05d0\u05d1\3\2\2\2\u05d1\u05da\3\2\2\2\u05d2\u05d3\7\u0088\2\2\u05d3"+
		"\u05d5\5\u00a2R\2\u05d4\u05d6\5\u00d2j\2\u05d5\u05d4\3\2\2\2\u05d6\u05d7"+
		"\3\2\2\2\u05d7\u05d5\3\2\2\2\u05d7\u05d8\3\2\2\2\u05d8\u05da\3\2\2\2\u05d9"+
		"\u05cc\3\2\2\2\u05d9\u05d2\3\2\2\2\u05da\u05df\3\2\2\2\u05db\u05dc\5\u0102"+
		"\u0082\2\u05dc\u05dd\7\u0089\2\2\u05dd\u05de\5\u00a2R\2\u05de\u05e0\3"+
		"\2\2\2\u05df\u05db\3\2\2\2\u05df\u05e0\3\2\2\2\u05e0\u05e1\3\2\2\2\u05e1"+
		"\u05e2\7\u008a\2\2\u05e2\u00d1\3\2\2\2\u05e3\u05e4\7\u008b\2\2\u05e4\u05e5"+
		"\5\u00a2R\2\u05e5\u05e6\7\u008c\2\2\u05e6\u05e7\5\u00a2R\2\u05e7\u00d3"+
		"\3\2\2\2\u05e8\u05e9\5\u0100\u0081\2\u05e9\u00d5\3\2\2\2\u05ea\u05ed\5"+
		"\u00fa~\2\u05eb\u05ed\5\u00e0q\2\u05ec\u05ea\3\2\2\2\u05ec\u05eb\3\2\2"+
		"\2\u05ed\u00d7\3\2\2\2\u05ee\u05ef\7\36\2\2\u05ef\u0604\5\u0102\u0082"+
		"\2\u05f0\u05f1\5\u00dep\2\u05f1\u05f2\5\u0102\u0082\2\u05f2\u05f3\7\16"+
		"\2\2\u05f3\u05f4\5\u0102\u0082\2\u05f4\u05f5\5\u00a2R\2\u05f5\u0601\5"+
		"\u0102\u0082\2\u05f6\u05f7\7\n\2\2\u05f7\u05f8\5\u0102\u0082\2\u05f8\u05f9"+
		"\5\u00dep\2\u05f9\u05fa\5\u0102\u0082\2\u05fa\u05fb\7\16\2\2\u05fb\u05fc"+
		"\5\u0102\u0082\2\u05fc\u05fd\5\u00a2R\2\u05fd\u05fe\5\u0102\u0082\2\u05fe"+
		"\u0600\3\2\2\2\u05ff\u05f6\3\2\2\2\u0600\u0603\3\2\2\2\u0601\u05ff\3\2"+
		"\2\2\u0601\u0602\3\2\2\2\u0602\u0605\3\2\2\2\u0603\u0601\3\2\2\2\u0604"+
		"\u05f0\3\2\2\2\u0604\u0605\3\2\2\2\u0605\u0606\3\2\2\2\u0606\u0607\7\37"+
		"\2\2\u0607\u00d9\3\2\2\2\u0608\u0609\7\36\2\2\u0609\u060c\5\u0102\u0082"+
		"\2\u060a\u060d\5\u0100\u0081\2\u060b\u060d\5\u00ecw\2\u060c\u060a\3\2"+
		"\2\2\u060c\u060b\3\2\2\2\u060d\u060e\3\2\2\2\u060e\u060f\5\u0102\u0082"+
		"\2\u060f\u0610\7\37\2\2\u0610\u00db\3\2\2\2\u0611\u0615\5\u00ba^\2\u0612"+
		"\u0613\5\u0102\u0082\2\u0613\u0614\5\u00ceh\2\u0614\u0616\3\2\2\2\u0615"+
		"\u0612\3\2\2\2\u0616\u0617\3\2\2\2\u0617\u0615\3\2\2\2\u0617\u0618\3\2"+
		"\2\2\u0618\u00dd\3\2\2\2\u0619\u061a\5\u0100\u0081\2\u061a\u00df\3\2\2"+
		"\2\u061b\u061f\5\u00e4s\2\u061c\u061f\5\u00e8u\2\u061d\u061f\5\u00e6t"+
		"\2\u061e\u061b\3\2\2\2\u061e\u061c\3\2\2\2\u061e\u061d\3\2\2\2\u061f\u00e1"+
		"\3\2\2\2\u0620\u0621\5\u00ecw\2\u0621\u00e3\3\2\2\2\u0622\u0624\7\22\2"+
		"\2\u0623\u0622\3\2\2\2\u0623\u0624\3\2\2\2\u0624\u0625\3\2\2\2\u0625\u0626"+
		"\5\u00eav\2\u0626\u00e5\3\2\2\2\u0627\u0629\7\22\2\2\u0628\u0627\3\2\2"+
		"\2\u0628\u0629\3\2\2\2\u0629\u062a\3\2\2\2\u062a\u062b\5\u00ecw\2\u062b"+
		"\u00e7\3\2\2\2\u062c\u062e\7\22\2\2\u062d\u062c\3\2\2\2\u062d\u062e\3"+
		"\2\2\2\u062e\u062f\3\2\2\2\u062f\u0630\5\u00eex\2\u0630\u00e9\3\2\2\2"+
		"\u0631\u0632\7\u008d\2\2\u0632\u0633\5\u00f0y\2\u0633\u00eb\3\2\2\2\u0634"+
		"\u0636\t\7\2\2\u0635\u0637\5\u00f2z\2\u0636\u0635\3\2\2\2\u0636\u0637"+
		"\3\2\2\2\u0637\u063a\3\2\2\2\u0638\u063a\7)\2\2\u0639\u0634\3\2\2\2\u0639"+
		"\u0638\3\2\2\2\u063a\u00ed\3\2\2\2\u063b\u063c\7)\2\2\u063c\u063d\5\u00f4"+
		"{\2\u063d\u00ef\3\2\2\2\u063e\u0640\7A\2\2\u063f\u063e\3\2\2\2\u0640\u0641"+
		"\3\2\2\2\u0641\u063f\3\2\2\2\u0641\u0642\3\2\2\2\u0642\u00f1\3\2\2\2\u0643"+
		"\u0645\5\u00f6|\2\u0644\u0643\3\2\2\2\u0645\u0646\3\2\2\2\u0646\u0644"+
		"\3\2\2\2\u0646\u0647\3\2\2\2\u0647\u00f3\3\2\2\2\u0648\u064a\5\u00f8}"+
		"\2\u0649\u0648\3\2\2\2\u064a\u064b\3\2\2\2\u064b\u0649\3\2\2\2\u064b\u064c"+
		"\3\2\2\2\u064c\u00f5\3\2\2\2\u064d\u064e\t\b\2\2\u064e\u00f7\3\2\2\2\u064f"+
		"\u0650\t\t\2\2\u0650\u00f9\3\2\2\2\u0651\u0654\5\u00fc\177\2\u0652\u0654"+
		"\5\u00fe\u0080\2\u0653\u0651\3\2\2\2\u0653\u0652\3\2\2\2\u0654\u00fb\3"+
		"\2\2\2\u0655\u0657\7\22\2\2\u0656\u0655\3\2\2\2\u0656\u0657\3\2\2\2\u0657"+
		"\u065a\3\2\2\2\u0658\u065b\5\u00f6|\2\u0659\u065b\7\4\2\2\u065a\u0658"+
		"\3\2\2\2\u065a\u0659\3\2\2\2\u065b\u065c\3\2\2\2\u065c\u065a\3\2\2\2\u065c"+
		"\u065d\3\2\2\2\u065d\u0660\3\2\2\2\u065e\u0661\t\n\2\2\u065f\u0661\t\n"+
		"\2\2\u0660\u065e\3\2\2\2\u0660\u065f\3\2\2\2\u0661\u0663\3\2\2\2\u0662"+
		"\u0664\7\22\2\2\u0663\u0662\3\2\2\2\u0663\u0664\3\2\2\2\u0664\u0665\3"+
		"\2\2\2\u0665\u0666\5\u00f2z\2\u0666\u00fd\3\2\2\2\u0667\u0669\7\22\2\2"+
		"\u0668\u0667\3\2\2\2\u0668\u0669\3\2\2\2\u0669\u066d\3\2\2\2\u066a\u066c"+
		"\5\u00f6|\2\u066b\u066a\3\2\2\2\u066c\u066f\3\2\2\2\u066d\u066b\3\2\2"+
		"\2\u066d\u066e\3\2\2\2\u066e\u0670\3\2\2\2\u066f\u066d\3\2\2\2\u0670\u0671"+
		"\7\4\2\2\u0671\u0672\5\u00f2z\2\u0672\u00ff\3\2\2\2\u0673\u0674\t\13\2"+
		"\2\u0674\u0101\3\2\2\2\u0675\u0677\7\u0092\2\2\u0676\u0675\3\2\2\2\u0677"+
		"\u067a\3\2\2\2\u0678\u0676\3\2\2\2\u0678\u0679\3\2\2\2\u0679\u0103\3\2"+
		"\2\2\u067a\u0678\3\2\2\2\u067b\u067d\7\u0092\2\2\u067c\u067b\3\2\2\2\u067d"+
		"\u067e\3\2\2\2\u067e\u067c\3\2\2\2\u067e\u067f\3\2\2\2\u067f\u0105\3\2"+
		"\2\2\u0680\u0681\t\f\2\2\u0681\u0107\3\2\2\2\u0682\u0683\t\r\2\2\u0683"+
		"\u0109\3\2\2\2\u0684\u0685\t\16\2\2\u0685\u010b\3\2\2\2\u008d\u0111\u011a"+
		"\u0120\u0126\u012d\u0140\u0144\u014c\u0159\u0164\u016c\u0176\u0184\u018e"+
		"\u020b\u0216\u0224\u0228\u0231\u0235\u0247\u0256\u0262\u026a\u027c\u0284"+
		"\u028f\u0292\u029e\u02a5\u02b6\u02c0\u02c6\u02c8\u02d4\u02da\u02df\u02e4"+
		"\u02ee\u02f9\u02fc\u0305\u0312\u0322\u0324\u033e\u0348\u0354\u0358\u0362"+
		"\u0368\u036e\u0377\u0380\u0388\u0394\u03a0\u03aa\u03ae\u03ba\u03c2\u03c9"+
		"\u03d0\u03d3\u03d6\u03e5\u03f2\u03fb\u0406\u040b\u040f\u0412\u0415\u0419"+
		"\u041b\u041e\u0424\u042e\u0435\u043e\u0447\u044d\u045d\u0468\u0473\u047c"+
		"\u0487\u0495\u0497\u04aa\u04ac\u04b7\u04c2\u04ce\u04d2\u04e9\u04f9\u04fb"+
		"\u0501\u0503\u051e\u0535\u055f\u0589\u0595\u059b\u05a8\u05af\u05b2\u05bf"+
		"\u05ca\u05d0\u05d7\u05d9\u05df\u05ec\u0601\u0604\u060c\u0617\u061e\u0623"+
		"\u0628\u062d\u0636\u0639\u0641\u0646\u064b\u0653\u0656\u065a\u065c\u0660"+
		"\u0663\u0668\u066d\u0678\u067e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}