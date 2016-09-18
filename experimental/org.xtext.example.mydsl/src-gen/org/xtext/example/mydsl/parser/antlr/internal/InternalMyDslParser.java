package org.xtext.example.mydsl.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.xtext.example.mydsl.services.MyDslGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalMyDslParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'CREATE'", "'('", "':'", "')'", "'['", "']'", "'<'", "'\\u27E8'", "'\\u3008'", "'\\uFE64'", "'\\uFF1C'", "'>'", "'\\u27E9'", "'\\u3009'", "'\\uFE65'", "'\\uFF1E'", "'-'", "'\\u00AD'", "'\\u2010'", "'\\u2011'", "'\\u2012'", "'\\u2013'", "'\\u2014'", "'\\u2015'", "'\\u2212'", "'\\uFE58'", "'\\uFE63'", "'\\uFF0D'"
    };
    public static final int RULE_STRING=6;
    public static final int RULE_SL_COMMENT=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__37=37;
    public static final int T__16=16;
    public static final int T__38=38;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__11=11;
    public static final int T__33=33;
    public static final int T__12=12;
    public static final int T__34=34;
    public static final int T__13=13;
    public static final int T__35=35;
    public static final int T__14=14;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_ID=4;
    public static final int RULE_WS=9;
    public static final int RULE_ANY_OTHER=10;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=5;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=7;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalMyDslParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalMyDslParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalMyDslParser.tokenNames; }
    public String getGrammarFileName() { return "InternalMyDsl.g"; }



     	private MyDslGrammarAccess grammarAccess;

        public InternalMyDslParser(TokenStream input, MyDslGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "CypherQuery";
       	}

       	@Override
       	protected MyDslGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleCypherQuery"
    // InternalMyDsl.g:64:1: entryRuleCypherQuery returns [EObject current=null] : iv_ruleCypherQuery= ruleCypherQuery EOF ;
    public final EObject entryRuleCypherQuery() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCypherQuery = null;


        try {
            // InternalMyDsl.g:64:52: (iv_ruleCypherQuery= ruleCypherQuery EOF )
            // InternalMyDsl.g:65:2: iv_ruleCypherQuery= ruleCypherQuery EOF
            {
             newCompositeNode(grammarAccess.getCypherQueryRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCypherQuery=ruleCypherQuery();

            state._fsp--;

             current =iv_ruleCypherQuery; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCypherQuery"


    // $ANTLR start "ruleCypherQuery"
    // InternalMyDsl.g:71:1: ruleCypherQuery returns [EObject current=null] : ( (lv_queries_0_0= ruleQuery ) )+ ;
    public final EObject ruleCypherQuery() throws RecognitionException {
        EObject current = null;

        EObject lv_queries_0_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:77:2: ( ( (lv_queries_0_0= ruleQuery ) )+ )
            // InternalMyDsl.g:78:2: ( (lv_queries_0_0= ruleQuery ) )+
            {
            // InternalMyDsl.g:78:2: ( (lv_queries_0_0= ruleQuery ) )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==11) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalMyDsl.g:79:3: (lv_queries_0_0= ruleQuery )
            	    {
            	    // InternalMyDsl.g:79:3: (lv_queries_0_0= ruleQuery )
            	    // InternalMyDsl.g:80:4: lv_queries_0_0= ruleQuery
            	    {

            	    				newCompositeNode(grammarAccess.getCypherQueryAccess().getQueriesQueryParserRuleCall_0());
            	    			
            	    pushFollow(FOLLOW_3);
            	    lv_queries_0_0=ruleQuery();

            	    state._fsp--;


            	    				if (current==null) {
            	    					current = createModelElementForParent(grammarAccess.getCypherQueryRule());
            	    				}
            	    				add(
            	    					current,
            	    					"queries",
            	    					lv_queries_0_0,
            	    					"org.xtext.example.mydsl.MyDsl.Query");
            	    				afterParserOrEnumRuleCall();
            	    			

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCypherQuery"


    // $ANTLR start "entryRuleQuery"
    // InternalMyDsl.g:100:1: entryRuleQuery returns [EObject current=null] : iv_ruleQuery= ruleQuery EOF ;
    public final EObject entryRuleQuery() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleQuery = null;


        try {
            // InternalMyDsl.g:100:46: (iv_ruleQuery= ruleQuery EOF )
            // InternalMyDsl.g:101:2: iv_ruleQuery= ruleQuery EOF
            {
             newCompositeNode(grammarAccess.getQueryRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleQuery=ruleQuery();

            state._fsp--;

             current =iv_ruleQuery; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleQuery"


    // $ANTLR start "ruleQuery"
    // InternalMyDsl.g:107:1: ruleQuery returns [EObject current=null] : (otherlv_0= 'CREATE' ( (lv_pattern_1_0= rulePattern ) ) ) ;
    public final EObject ruleQuery() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_pattern_1_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:113:2: ( (otherlv_0= 'CREATE' ( (lv_pattern_1_0= rulePattern ) ) ) )
            // InternalMyDsl.g:114:2: (otherlv_0= 'CREATE' ( (lv_pattern_1_0= rulePattern ) ) )
            {
            // InternalMyDsl.g:114:2: (otherlv_0= 'CREATE' ( (lv_pattern_1_0= rulePattern ) ) )
            // InternalMyDsl.g:115:3: otherlv_0= 'CREATE' ( (lv_pattern_1_0= rulePattern ) )
            {
            otherlv_0=(Token)match(input,11,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getQueryAccess().getCREATEKeyword_0());
            		
            // InternalMyDsl.g:119:3: ( (lv_pattern_1_0= rulePattern ) )
            // InternalMyDsl.g:120:4: (lv_pattern_1_0= rulePattern )
            {
            // InternalMyDsl.g:120:4: (lv_pattern_1_0= rulePattern )
            // InternalMyDsl.g:121:5: lv_pattern_1_0= rulePattern
            {

            					newCompositeNode(grammarAccess.getQueryAccess().getPatternPatternParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_pattern_1_0=rulePattern();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getQueryRule());
            					}
            					set(
            						current,
            						"pattern",
            						lv_pattern_1_0,
            						"org.xtext.example.mydsl.MyDsl.Pattern");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQuery"


    // $ANTLR start "entryRulePattern"
    // InternalMyDsl.g:142:1: entryRulePattern returns [EObject current=null] : iv_rulePattern= rulePattern EOF ;
    public final EObject entryRulePattern() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePattern = null;


        try {
            // InternalMyDsl.g:142:48: (iv_rulePattern= rulePattern EOF )
            // InternalMyDsl.g:143:2: iv_rulePattern= rulePattern EOF
            {
             newCompositeNode(grammarAccess.getPatternRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePattern=rulePattern();

            state._fsp--;

             current =iv_rulePattern; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePattern"


    // $ANTLR start "rulePattern"
    // InternalMyDsl.g:149:1: rulePattern returns [EObject current=null] : ( (lv_parts_0_0= rulePatternPart ) )+ ;
    public final EObject rulePattern() throws RecognitionException {
        EObject current = null;

        EObject lv_parts_0_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:155:2: ( ( (lv_parts_0_0= rulePatternPart ) )+ )
            // InternalMyDsl.g:156:2: ( (lv_parts_0_0= rulePatternPart ) )+
            {
            // InternalMyDsl.g:156:2: ( (lv_parts_0_0= rulePatternPart ) )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==12) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalMyDsl.g:157:3: (lv_parts_0_0= rulePatternPart )
            	    {
            	    // InternalMyDsl.g:157:3: (lv_parts_0_0= rulePatternPart )
            	    // InternalMyDsl.g:158:4: lv_parts_0_0= rulePatternPart
            	    {

            	    				newCompositeNode(grammarAccess.getPatternAccess().getPartsPatternPartParserRuleCall_0());
            	    			
            	    pushFollow(FOLLOW_5);
            	    lv_parts_0_0=rulePatternPart();

            	    state._fsp--;


            	    				if (current==null) {
            	    					current = createModelElementForParent(grammarAccess.getPatternRule());
            	    				}
            	    				add(
            	    					current,
            	    					"parts",
            	    					lv_parts_0_0,
            	    					"org.xtext.example.mydsl.MyDsl.PatternPart");
            	    				afterParserOrEnumRuleCall();
            	    			

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePattern"


    // $ANTLR start "entryRulePatternPart"
    // InternalMyDsl.g:178:1: entryRulePatternPart returns [EObject current=null] : iv_rulePatternPart= rulePatternPart EOF ;
    public final EObject entryRulePatternPart() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePatternPart = null;


        try {
            // InternalMyDsl.g:178:52: (iv_rulePatternPart= rulePatternPart EOF )
            // InternalMyDsl.g:179:2: iv_rulePatternPart= rulePatternPart EOF
            {
             newCompositeNode(grammarAccess.getPatternPartRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePatternPart=rulePatternPart();

            state._fsp--;

             current =iv_rulePatternPart; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePatternPart"


    // $ANTLR start "rulePatternPart"
    // InternalMyDsl.g:185:1: rulePatternPart returns [EObject current=null] : ( ( (lv_node_0_0= ruleNodePattern ) ) ( (lv_chain_1_0= rulePatternElementChain ) )+ ) ;
    public final EObject rulePatternPart() throws RecognitionException {
        EObject current = null;

        EObject lv_node_0_0 = null;

        EObject lv_chain_1_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:191:2: ( ( ( (lv_node_0_0= ruleNodePattern ) ) ( (lv_chain_1_0= rulePatternElementChain ) )+ ) )
            // InternalMyDsl.g:192:2: ( ( (lv_node_0_0= ruleNodePattern ) ) ( (lv_chain_1_0= rulePatternElementChain ) )+ )
            {
            // InternalMyDsl.g:192:2: ( ( (lv_node_0_0= ruleNodePattern ) ) ( (lv_chain_1_0= rulePatternElementChain ) )+ )
            // InternalMyDsl.g:193:3: ( (lv_node_0_0= ruleNodePattern ) ) ( (lv_chain_1_0= rulePatternElementChain ) )+
            {
            // InternalMyDsl.g:193:3: ( (lv_node_0_0= ruleNodePattern ) )
            // InternalMyDsl.g:194:4: (lv_node_0_0= ruleNodePattern )
            {
            // InternalMyDsl.g:194:4: (lv_node_0_0= ruleNodePattern )
            // InternalMyDsl.g:195:5: lv_node_0_0= ruleNodePattern
            {

            					newCompositeNode(grammarAccess.getPatternPartAccess().getNodeNodePatternParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_6);
            lv_node_0_0=ruleNodePattern();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPatternPartRule());
            					}
            					set(
            						current,
            						"node",
            						lv_node_0_0,
            						"org.xtext.example.mydsl.MyDsl.NodePattern");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalMyDsl.g:212:3: ( (lv_chain_1_0= rulePatternElementChain ) )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>=17 && LA3_0<=21)||(LA3_0>=27 && LA3_0<=38)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalMyDsl.g:213:4: (lv_chain_1_0= rulePatternElementChain )
            	    {
            	    // InternalMyDsl.g:213:4: (lv_chain_1_0= rulePatternElementChain )
            	    // InternalMyDsl.g:214:5: lv_chain_1_0= rulePatternElementChain
            	    {

            	    					newCompositeNode(grammarAccess.getPatternPartAccess().getChainPatternElementChainParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_7);
            	    lv_chain_1_0=rulePatternElementChain();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getPatternPartRule());
            	    					}
            	    					add(
            	    						current,
            	    						"chain",
            	    						lv_chain_1_0,
            	    						"org.xtext.example.mydsl.MyDsl.PatternElementChain");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePatternPart"


    // $ANTLR start "entryRuleNodePattern"
    // InternalMyDsl.g:235:1: entryRuleNodePattern returns [EObject current=null] : iv_ruleNodePattern= ruleNodePattern EOF ;
    public final EObject entryRuleNodePattern() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNodePattern = null;


        try {
            // InternalMyDsl.g:235:52: (iv_ruleNodePattern= ruleNodePattern EOF )
            // InternalMyDsl.g:236:2: iv_ruleNodePattern= ruleNodePattern EOF
            {
             newCompositeNode(grammarAccess.getNodePatternRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNodePattern=ruleNodePattern();

            state._fsp--;

             current =iv_ruleNodePattern; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNodePattern"


    // $ANTLR start "ruleNodePattern"
    // InternalMyDsl.g:242:1: ruleNodePattern returns [EObject current=null] : (otherlv_0= '(' ( (lv_variable_1_0= ruleVariable ) )? otherlv_2= ':' ( (lv_label_3_0= ruleLabel ) )? otherlv_4= ')' ) ;
    public final EObject ruleNodePattern() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_variable_1_0 = null;

        EObject lv_label_3_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:248:2: ( (otherlv_0= '(' ( (lv_variable_1_0= ruleVariable ) )? otherlv_2= ':' ( (lv_label_3_0= ruleLabel ) )? otherlv_4= ')' ) )
            // InternalMyDsl.g:249:2: (otherlv_0= '(' ( (lv_variable_1_0= ruleVariable ) )? otherlv_2= ':' ( (lv_label_3_0= ruleLabel ) )? otherlv_4= ')' )
            {
            // InternalMyDsl.g:249:2: (otherlv_0= '(' ( (lv_variable_1_0= ruleVariable ) )? otherlv_2= ':' ( (lv_label_3_0= ruleLabel ) )? otherlv_4= ')' )
            // InternalMyDsl.g:250:3: otherlv_0= '(' ( (lv_variable_1_0= ruleVariable ) )? otherlv_2= ':' ( (lv_label_3_0= ruleLabel ) )? otherlv_4= ')'
            {
            otherlv_0=(Token)match(input,12,FOLLOW_8); 

            			newLeafNode(otherlv_0, grammarAccess.getNodePatternAccess().getLeftParenthesisKeyword_0());
            		
            // InternalMyDsl.g:254:3: ( (lv_variable_1_0= ruleVariable ) )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==RULE_ID) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalMyDsl.g:255:4: (lv_variable_1_0= ruleVariable )
                    {
                    // InternalMyDsl.g:255:4: (lv_variable_1_0= ruleVariable )
                    // InternalMyDsl.g:256:5: lv_variable_1_0= ruleVariable
                    {

                    					newCompositeNode(grammarAccess.getNodePatternAccess().getVariableVariableParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_9);
                    lv_variable_1_0=ruleVariable();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getNodePatternRule());
                    					}
                    					set(
                    						current,
                    						"variable",
                    						lv_variable_1_0,
                    						"org.xtext.example.mydsl.MyDsl.Variable");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,13,FOLLOW_10); 

            			newLeafNode(otherlv_2, grammarAccess.getNodePatternAccess().getColonKeyword_2());
            		
            // InternalMyDsl.g:277:3: ( (lv_label_3_0= ruleLabel ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_ID) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalMyDsl.g:278:4: (lv_label_3_0= ruleLabel )
                    {
                    // InternalMyDsl.g:278:4: (lv_label_3_0= ruleLabel )
                    // InternalMyDsl.g:279:5: lv_label_3_0= ruleLabel
                    {

                    					newCompositeNode(grammarAccess.getNodePatternAccess().getLabelLabelParserRuleCall_3_0());
                    				
                    pushFollow(FOLLOW_11);
                    lv_label_3_0=ruleLabel();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getNodePatternRule());
                    					}
                    					set(
                    						current,
                    						"label",
                    						lv_label_3_0,
                    						"org.xtext.example.mydsl.MyDsl.Label");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,14,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getNodePatternAccess().getRightParenthesisKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNodePattern"


    // $ANTLR start "entryRulePatternElementChain"
    // InternalMyDsl.g:304:1: entryRulePatternElementChain returns [EObject current=null] : iv_rulePatternElementChain= rulePatternElementChain EOF ;
    public final EObject entryRulePatternElementChain() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePatternElementChain = null;


        try {
            // InternalMyDsl.g:304:60: (iv_rulePatternElementChain= rulePatternElementChain EOF )
            // InternalMyDsl.g:305:2: iv_rulePatternElementChain= rulePatternElementChain EOF
            {
             newCompositeNode(grammarAccess.getPatternElementChainRule()); 
            pushFollow(FOLLOW_1);
            iv_rulePatternElementChain=rulePatternElementChain();

            state._fsp--;

             current =iv_rulePatternElementChain; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePatternElementChain"


    // $ANTLR start "rulePatternElementChain"
    // InternalMyDsl.g:311:1: rulePatternElementChain returns [EObject current=null] : ( ( (lv_relationshipPattern_0_0= ruleRelationshipPattern ) ) ( (lv_nodePattern_1_0= ruleNodePattern ) ) ) ;
    public final EObject rulePatternElementChain() throws RecognitionException {
        EObject current = null;

        EObject lv_relationshipPattern_0_0 = null;

        EObject lv_nodePattern_1_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:317:2: ( ( ( (lv_relationshipPattern_0_0= ruleRelationshipPattern ) ) ( (lv_nodePattern_1_0= ruleNodePattern ) ) ) )
            // InternalMyDsl.g:318:2: ( ( (lv_relationshipPattern_0_0= ruleRelationshipPattern ) ) ( (lv_nodePattern_1_0= ruleNodePattern ) ) )
            {
            // InternalMyDsl.g:318:2: ( ( (lv_relationshipPattern_0_0= ruleRelationshipPattern ) ) ( (lv_nodePattern_1_0= ruleNodePattern ) ) )
            // InternalMyDsl.g:319:3: ( (lv_relationshipPattern_0_0= ruleRelationshipPattern ) ) ( (lv_nodePattern_1_0= ruleNodePattern ) )
            {
            // InternalMyDsl.g:319:3: ( (lv_relationshipPattern_0_0= ruleRelationshipPattern ) )
            // InternalMyDsl.g:320:4: (lv_relationshipPattern_0_0= ruleRelationshipPattern )
            {
            // InternalMyDsl.g:320:4: (lv_relationshipPattern_0_0= ruleRelationshipPattern )
            // InternalMyDsl.g:321:5: lv_relationshipPattern_0_0= ruleRelationshipPattern
            {

            					newCompositeNode(grammarAccess.getPatternElementChainAccess().getRelationshipPatternRelationshipPatternParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_4);
            lv_relationshipPattern_0_0=ruleRelationshipPattern();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPatternElementChainRule());
            					}
            					set(
            						current,
            						"relationshipPattern",
            						lv_relationshipPattern_0_0,
            						"org.xtext.example.mydsl.MyDsl.RelationshipPattern");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalMyDsl.g:338:3: ( (lv_nodePattern_1_0= ruleNodePattern ) )
            // InternalMyDsl.g:339:4: (lv_nodePattern_1_0= ruleNodePattern )
            {
            // InternalMyDsl.g:339:4: (lv_nodePattern_1_0= ruleNodePattern )
            // InternalMyDsl.g:340:5: lv_nodePattern_1_0= ruleNodePattern
            {

            					newCompositeNode(grammarAccess.getPatternElementChainAccess().getNodePatternNodePatternParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_nodePattern_1_0=ruleNodePattern();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getPatternElementChainRule());
            					}
            					set(
            						current,
            						"nodePattern",
            						lv_nodePattern_1_0,
            						"org.xtext.example.mydsl.MyDsl.NodePattern");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePatternElementChain"


    // $ANTLR start "entryRuleRelationshipPattern"
    // InternalMyDsl.g:361:1: entryRuleRelationshipPattern returns [EObject current=null] : iv_ruleRelationshipPattern= ruleRelationshipPattern EOF ;
    public final EObject entryRuleRelationshipPattern() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelationshipPattern = null;


        try {
            // InternalMyDsl.g:361:60: (iv_ruleRelationshipPattern= ruleRelationshipPattern EOF )
            // InternalMyDsl.g:362:2: iv_ruleRelationshipPattern= ruleRelationshipPattern EOF
            {
             newCompositeNode(grammarAccess.getRelationshipPatternRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRelationshipPattern=ruleRelationshipPattern();

            state._fsp--;

             current =iv_ruleRelationshipPattern; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRelationshipPattern"


    // $ANTLR start "ruleRelationshipPattern"
    // InternalMyDsl.g:368:1: ruleRelationshipPattern returns [EObject current=null] : ( ( ruleleftArrowHead ruledash (this_RelationshipDetail_2= ruleRelationshipDetail )? ruledash rulerightArrowHead ) | ( ruleleftArrowHead ruledash (this_RelationshipDetail_7= ruleRelationshipDetail )? ruledash ) | ( ruledash (this_RelationshipDetail_10= ruleRelationshipDetail )? ruledash rulerightArrowHead ) | ( ruledash (this_RelationshipDetail_14= ruleRelationshipDetail )? ruledash ) ) ;
    public final EObject ruleRelationshipPattern() throws RecognitionException {
        EObject current = null;

        EObject this_RelationshipDetail_2 = null;

        EObject this_RelationshipDetail_7 = null;

        EObject this_RelationshipDetail_10 = null;

        EObject this_RelationshipDetail_14 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:374:2: ( ( ( ruleleftArrowHead ruledash (this_RelationshipDetail_2= ruleRelationshipDetail )? ruledash rulerightArrowHead ) | ( ruleleftArrowHead ruledash (this_RelationshipDetail_7= ruleRelationshipDetail )? ruledash ) | ( ruledash (this_RelationshipDetail_10= ruleRelationshipDetail )? ruledash rulerightArrowHead ) | ( ruledash (this_RelationshipDetail_14= ruleRelationshipDetail )? ruledash ) ) )
            // InternalMyDsl.g:375:2: ( ( ruleleftArrowHead ruledash (this_RelationshipDetail_2= ruleRelationshipDetail )? ruledash rulerightArrowHead ) | ( ruleleftArrowHead ruledash (this_RelationshipDetail_7= ruleRelationshipDetail )? ruledash ) | ( ruledash (this_RelationshipDetail_10= ruleRelationshipDetail )? ruledash rulerightArrowHead ) | ( ruledash (this_RelationshipDetail_14= ruleRelationshipDetail )? ruledash ) )
            {
            // InternalMyDsl.g:375:2: ( ( ruleleftArrowHead ruledash (this_RelationshipDetail_2= ruleRelationshipDetail )? ruledash rulerightArrowHead ) | ( ruleleftArrowHead ruledash (this_RelationshipDetail_7= ruleRelationshipDetail )? ruledash ) | ( ruledash (this_RelationshipDetail_10= ruleRelationshipDetail )? ruledash rulerightArrowHead ) | ( ruledash (this_RelationshipDetail_14= ruleRelationshipDetail )? ruledash ) )
            int alt10=4;
            alt10 = dfa10.predict(input);
            switch (alt10) {
                case 1 :
                    // InternalMyDsl.g:376:3: ( ruleleftArrowHead ruledash (this_RelationshipDetail_2= ruleRelationshipDetail )? ruledash rulerightArrowHead )
                    {
                    // InternalMyDsl.g:376:3: ( ruleleftArrowHead ruledash (this_RelationshipDetail_2= ruleRelationshipDetail )? ruledash rulerightArrowHead )
                    // InternalMyDsl.g:377:4: ruleleftArrowHead ruledash (this_RelationshipDetail_2= ruleRelationshipDetail )? ruledash rulerightArrowHead
                    {

                    				newCompositeNode(grammarAccess.getRelationshipPatternAccess().getLeftArrowHeadParserRuleCall_0_0());
                    			
                    pushFollow(FOLLOW_12);
                    ruleleftArrowHead();

                    state._fsp--;


                    				afterParserOrEnumRuleCall();
                    			

                    				newCompositeNode(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_0_1());
                    			
                    pushFollow(FOLLOW_13);
                    ruledash();

                    state._fsp--;


                    				afterParserOrEnumRuleCall();
                    			
                    // InternalMyDsl.g:391:4: (this_RelationshipDetail_2= ruleRelationshipDetail )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( (LA6_0==15) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // InternalMyDsl.g:392:5: this_RelationshipDetail_2= ruleRelationshipDetail
                            {

                            					newCompositeNode(grammarAccess.getRelationshipPatternAccess().getRelationshipDetailParserRuleCall_0_2());
                            				
                            pushFollow(FOLLOW_12);
                            this_RelationshipDetail_2=ruleRelationshipDetail();

                            state._fsp--;


                            					current = this_RelationshipDetail_2;
                            					afterParserOrEnumRuleCall();
                            				

                            }
                            break;

                    }


                    				newCompositeNode(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_0_3());
                    			
                    pushFollow(FOLLOW_14);
                    ruledash();

                    state._fsp--;


                    				afterParserOrEnumRuleCall();
                    			

                    				newCompositeNode(grammarAccess.getRelationshipPatternAccess().getRightArrowHeadParserRuleCall_0_4());
                    			
                    pushFollow(FOLLOW_2);
                    rulerightArrowHead();

                    state._fsp--;


                    				afterParserOrEnumRuleCall();
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:417:3: ( ruleleftArrowHead ruledash (this_RelationshipDetail_7= ruleRelationshipDetail )? ruledash )
                    {
                    // InternalMyDsl.g:417:3: ( ruleleftArrowHead ruledash (this_RelationshipDetail_7= ruleRelationshipDetail )? ruledash )
                    // InternalMyDsl.g:418:4: ruleleftArrowHead ruledash (this_RelationshipDetail_7= ruleRelationshipDetail )? ruledash
                    {

                    				newCompositeNode(grammarAccess.getRelationshipPatternAccess().getLeftArrowHeadParserRuleCall_1_0());
                    			
                    pushFollow(FOLLOW_12);
                    ruleleftArrowHead();

                    state._fsp--;


                    				afterParserOrEnumRuleCall();
                    			

                    				newCompositeNode(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_1_1());
                    			
                    pushFollow(FOLLOW_13);
                    ruledash();

                    state._fsp--;


                    				afterParserOrEnumRuleCall();
                    			
                    // InternalMyDsl.g:432:4: (this_RelationshipDetail_7= ruleRelationshipDetail )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==15) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // InternalMyDsl.g:433:5: this_RelationshipDetail_7= ruleRelationshipDetail
                            {

                            					newCompositeNode(grammarAccess.getRelationshipPatternAccess().getRelationshipDetailParserRuleCall_1_2());
                            				
                            pushFollow(FOLLOW_12);
                            this_RelationshipDetail_7=ruleRelationshipDetail();

                            state._fsp--;


                            					current = this_RelationshipDetail_7;
                            					afterParserOrEnumRuleCall();
                            				

                            }
                            break;

                    }


                    				newCompositeNode(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_1_3());
                    			
                    pushFollow(FOLLOW_2);
                    ruledash();

                    state._fsp--;


                    				afterParserOrEnumRuleCall();
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalMyDsl.g:451:3: ( ruledash (this_RelationshipDetail_10= ruleRelationshipDetail )? ruledash rulerightArrowHead )
                    {
                    // InternalMyDsl.g:451:3: ( ruledash (this_RelationshipDetail_10= ruleRelationshipDetail )? ruledash rulerightArrowHead )
                    // InternalMyDsl.g:452:4: ruledash (this_RelationshipDetail_10= ruleRelationshipDetail )? ruledash rulerightArrowHead
                    {

                    				newCompositeNode(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_2_0());
                    			
                    pushFollow(FOLLOW_13);
                    ruledash();

                    state._fsp--;


                    				afterParserOrEnumRuleCall();
                    			
                    // InternalMyDsl.g:459:4: (this_RelationshipDetail_10= ruleRelationshipDetail )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0==15) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // InternalMyDsl.g:460:5: this_RelationshipDetail_10= ruleRelationshipDetail
                            {

                            					newCompositeNode(grammarAccess.getRelationshipPatternAccess().getRelationshipDetailParserRuleCall_2_1());
                            				
                            pushFollow(FOLLOW_12);
                            this_RelationshipDetail_10=ruleRelationshipDetail();

                            state._fsp--;


                            					current = this_RelationshipDetail_10;
                            					afterParserOrEnumRuleCall();
                            				

                            }
                            break;

                    }


                    				newCompositeNode(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_2_2());
                    			
                    pushFollow(FOLLOW_14);
                    ruledash();

                    state._fsp--;


                    				afterParserOrEnumRuleCall();
                    			

                    				newCompositeNode(grammarAccess.getRelationshipPatternAccess().getRightArrowHeadParserRuleCall_2_3());
                    			
                    pushFollow(FOLLOW_2);
                    rulerightArrowHead();

                    state._fsp--;


                    				afterParserOrEnumRuleCall();
                    			

                    }


                    }
                    break;
                case 4 :
                    // InternalMyDsl.g:485:3: ( ruledash (this_RelationshipDetail_14= ruleRelationshipDetail )? ruledash )
                    {
                    // InternalMyDsl.g:485:3: ( ruledash (this_RelationshipDetail_14= ruleRelationshipDetail )? ruledash )
                    // InternalMyDsl.g:486:4: ruledash (this_RelationshipDetail_14= ruleRelationshipDetail )? ruledash
                    {

                    				newCompositeNode(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_3_0());
                    			
                    pushFollow(FOLLOW_13);
                    ruledash();

                    state._fsp--;


                    				afterParserOrEnumRuleCall();
                    			
                    // InternalMyDsl.g:493:4: (this_RelationshipDetail_14= ruleRelationshipDetail )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==15) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // InternalMyDsl.g:494:5: this_RelationshipDetail_14= ruleRelationshipDetail
                            {

                            					newCompositeNode(grammarAccess.getRelationshipPatternAccess().getRelationshipDetailParserRuleCall_3_1());
                            				
                            pushFollow(FOLLOW_12);
                            this_RelationshipDetail_14=ruleRelationshipDetail();

                            state._fsp--;


                            					current = this_RelationshipDetail_14;
                            					afterParserOrEnumRuleCall();
                            				

                            }
                            break;

                    }


                    				newCompositeNode(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_3_2());
                    			
                    pushFollow(FOLLOW_2);
                    ruledash();

                    state._fsp--;


                    				afterParserOrEnumRuleCall();
                    			

                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRelationshipPattern"


    // $ANTLR start "entryRuleRelationshipDetail"
    // InternalMyDsl.g:515:1: entryRuleRelationshipDetail returns [EObject current=null] : iv_ruleRelationshipDetail= ruleRelationshipDetail EOF ;
    public final EObject entryRuleRelationshipDetail() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelationshipDetail = null;


        try {
            // InternalMyDsl.g:515:59: (iv_ruleRelationshipDetail= ruleRelationshipDetail EOF )
            // InternalMyDsl.g:516:2: iv_ruleRelationshipDetail= ruleRelationshipDetail EOF
            {
             newCompositeNode(grammarAccess.getRelationshipDetailRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRelationshipDetail=ruleRelationshipDetail();

            state._fsp--;

             current =iv_ruleRelationshipDetail; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRelationshipDetail"


    // $ANTLR start "ruleRelationshipDetail"
    // InternalMyDsl.g:522:1: ruleRelationshipDetail returns [EObject current=null] : (otherlv_0= '[' ( (lv_variable_1_0= ruleVariable ) )? otherlv_2= ':' ( (lv_label_3_0= ruleLabel ) )? otherlv_4= ']' ) ;
    public final EObject ruleRelationshipDetail() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_variable_1_0 = null;

        EObject lv_label_3_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:528:2: ( (otherlv_0= '[' ( (lv_variable_1_0= ruleVariable ) )? otherlv_2= ':' ( (lv_label_3_0= ruleLabel ) )? otherlv_4= ']' ) )
            // InternalMyDsl.g:529:2: (otherlv_0= '[' ( (lv_variable_1_0= ruleVariable ) )? otherlv_2= ':' ( (lv_label_3_0= ruleLabel ) )? otherlv_4= ']' )
            {
            // InternalMyDsl.g:529:2: (otherlv_0= '[' ( (lv_variable_1_0= ruleVariable ) )? otherlv_2= ':' ( (lv_label_3_0= ruleLabel ) )? otherlv_4= ']' )
            // InternalMyDsl.g:530:3: otherlv_0= '[' ( (lv_variable_1_0= ruleVariable ) )? otherlv_2= ':' ( (lv_label_3_0= ruleLabel ) )? otherlv_4= ']'
            {
            otherlv_0=(Token)match(input,15,FOLLOW_8); 

            			newLeafNode(otherlv_0, grammarAccess.getRelationshipDetailAccess().getLeftSquareBracketKeyword_0());
            		
            // InternalMyDsl.g:534:3: ( (lv_variable_1_0= ruleVariable ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==RULE_ID) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalMyDsl.g:535:4: (lv_variable_1_0= ruleVariable )
                    {
                    // InternalMyDsl.g:535:4: (lv_variable_1_0= ruleVariable )
                    // InternalMyDsl.g:536:5: lv_variable_1_0= ruleVariable
                    {

                    					newCompositeNode(grammarAccess.getRelationshipDetailAccess().getVariableVariableParserRuleCall_1_0());
                    				
                    pushFollow(FOLLOW_9);
                    lv_variable_1_0=ruleVariable();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getRelationshipDetailRule());
                    					}
                    					set(
                    						current,
                    						"variable",
                    						lv_variable_1_0,
                    						"org.xtext.example.mydsl.MyDsl.Variable");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_2=(Token)match(input,13,FOLLOW_15); 

            			newLeafNode(otherlv_2, grammarAccess.getRelationshipDetailAccess().getColonKeyword_2());
            		
            // InternalMyDsl.g:557:3: ( (lv_label_3_0= ruleLabel ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_ID) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalMyDsl.g:558:4: (lv_label_3_0= ruleLabel )
                    {
                    // InternalMyDsl.g:558:4: (lv_label_3_0= ruleLabel )
                    // InternalMyDsl.g:559:5: lv_label_3_0= ruleLabel
                    {

                    					newCompositeNode(grammarAccess.getRelationshipDetailAccess().getLabelLabelParserRuleCall_3_0());
                    				
                    pushFollow(FOLLOW_16);
                    lv_label_3_0=ruleLabel();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getRelationshipDetailRule());
                    					}
                    					set(
                    						current,
                    						"label",
                    						lv_label_3_0,
                    						"org.xtext.example.mydsl.MyDsl.Label");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,16,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getRelationshipDetailAccess().getRightSquareBracketKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRelationshipDetail"


    // $ANTLR start "entryRuleVariable"
    // InternalMyDsl.g:584:1: entryRuleVariable returns [EObject current=null] : iv_ruleVariable= ruleVariable EOF ;
    public final EObject entryRuleVariable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVariable = null;


        try {
            // InternalMyDsl.g:584:49: (iv_ruleVariable= ruleVariable EOF )
            // InternalMyDsl.g:585:2: iv_ruleVariable= ruleVariable EOF
            {
             newCompositeNode(grammarAccess.getVariableRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVariable=ruleVariable();

            state._fsp--;

             current =iv_ruleVariable; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVariable"


    // $ANTLR start "ruleVariable"
    // InternalMyDsl.g:591:1: ruleVariable returns [EObject current=null] : ( (lv_name_0_0= RULE_ID ) ) ;
    public final EObject ruleVariable() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;


        	enterRule();

        try {
            // InternalMyDsl.g:597:2: ( ( (lv_name_0_0= RULE_ID ) ) )
            // InternalMyDsl.g:598:2: ( (lv_name_0_0= RULE_ID ) )
            {
            // InternalMyDsl.g:598:2: ( (lv_name_0_0= RULE_ID ) )
            // InternalMyDsl.g:599:3: (lv_name_0_0= RULE_ID )
            {
            // InternalMyDsl.g:599:3: (lv_name_0_0= RULE_ID )
            // InternalMyDsl.g:600:4: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            				newLeafNode(lv_name_0_0, grammarAccess.getVariableAccess().getNameIDTerminalRuleCall_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getVariableRule());
            				}
            				setWithLastConsumed(
            					current,
            					"name",
            					lv_name_0_0,
            					"org.eclipse.xtext.common.Terminals.ID");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVariable"


    // $ANTLR start "entryRuleLabel"
    // InternalMyDsl.g:619:1: entryRuleLabel returns [EObject current=null] : iv_ruleLabel= ruleLabel EOF ;
    public final EObject entryRuleLabel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleLabel = null;


        try {
            // InternalMyDsl.g:619:46: (iv_ruleLabel= ruleLabel EOF )
            // InternalMyDsl.g:620:2: iv_ruleLabel= ruleLabel EOF
            {
             newCompositeNode(grammarAccess.getLabelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleLabel=ruleLabel();

            state._fsp--;

             current =iv_ruleLabel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleLabel"


    // $ANTLR start "ruleLabel"
    // InternalMyDsl.g:626:1: ruleLabel returns [EObject current=null] : ( (lv_name_0_0= RULE_ID ) ) ;
    public final EObject ruleLabel() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;


        	enterRule();

        try {
            // InternalMyDsl.g:632:2: ( ( (lv_name_0_0= RULE_ID ) ) )
            // InternalMyDsl.g:633:2: ( (lv_name_0_0= RULE_ID ) )
            {
            // InternalMyDsl.g:633:2: ( (lv_name_0_0= RULE_ID ) )
            // InternalMyDsl.g:634:3: (lv_name_0_0= RULE_ID )
            {
            // InternalMyDsl.g:634:3: (lv_name_0_0= RULE_ID )
            // InternalMyDsl.g:635:4: lv_name_0_0= RULE_ID
            {
            lv_name_0_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            				newLeafNode(lv_name_0_0, grammarAccess.getLabelAccess().getNameIDTerminalRuleCall_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getLabelRule());
            				}
            				setWithLastConsumed(
            					current,
            					"name",
            					lv_name_0_0,
            					"org.eclipse.xtext.common.Terminals.ID");
            			

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleLabel"


    // $ANTLR start "entryRuleleftArrowHead"
    // InternalMyDsl.g:654:1: entryRuleleftArrowHead returns [String current=null] : iv_ruleleftArrowHead= ruleleftArrowHead EOF ;
    public final String entryRuleleftArrowHead() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleleftArrowHead = null;


        try {
            // InternalMyDsl.g:654:53: (iv_ruleleftArrowHead= ruleleftArrowHead EOF )
            // InternalMyDsl.g:655:2: iv_ruleleftArrowHead= ruleleftArrowHead EOF
            {
             newCompositeNode(grammarAccess.getLeftArrowHeadRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleleftArrowHead=ruleleftArrowHead();

            state._fsp--;

             current =iv_ruleleftArrowHead.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleleftArrowHead"


    // $ANTLR start "ruleleftArrowHead"
    // InternalMyDsl.g:661:1: ruleleftArrowHead returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '<' | kw= '\\u27E8' | kw= '\\u3008' | kw= '\\uFE64' | kw= '\\uFF1C' ) ;
    public final AntlrDatatypeRuleToken ruleleftArrowHead() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalMyDsl.g:667:2: ( (kw= '<' | kw= '\\u27E8' | kw= '\\u3008' | kw= '\\uFE64' | kw= '\\uFF1C' ) )
            // InternalMyDsl.g:668:2: (kw= '<' | kw= '\\u27E8' | kw= '\\u3008' | kw= '\\uFE64' | kw= '\\uFF1C' )
            {
            // InternalMyDsl.g:668:2: (kw= '<' | kw= '\\u27E8' | kw= '\\u3008' | kw= '\\uFE64' | kw= '\\uFF1C' )
            int alt13=5;
            switch ( input.LA(1) ) {
            case 17:
                {
                alt13=1;
                }
                break;
            case 18:
                {
                alt13=2;
                }
                break;
            case 19:
                {
                alt13=3;
                }
                break;
            case 20:
                {
                alt13=4;
                }
                break;
            case 21:
                {
                alt13=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;
            }

            switch (alt13) {
                case 1 :
                    // InternalMyDsl.g:669:3: kw= '<'
                    {
                    kw=(Token)match(input,17,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getLeftArrowHeadAccess().getLessThanSignKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:675:3: kw= '\\u27E8'
                    {
                    kw=(Token)match(input,18,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getLeftArrowHeadAccess().getMathematicalLeftAngleBracketKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalMyDsl.g:681:3: kw= '\\u3008'
                    {
                    kw=(Token)match(input,19,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getLeftArrowHeadAccess().getLeftAngleBracketKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalMyDsl.g:687:3: kw= '\\uFE64'
                    {
                    kw=(Token)match(input,20,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getLeftArrowHeadAccess().getSmallLessThanSignKeyword_3());
                    		

                    }
                    break;
                case 5 :
                    // InternalMyDsl.g:693:3: kw= '\\uFF1C'
                    {
                    kw=(Token)match(input,21,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getLeftArrowHeadAccess().getFullwidthLessThanSignKeyword_4());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleleftArrowHead"


    // $ANTLR start "entryRulerightArrowHead"
    // InternalMyDsl.g:702:1: entryRulerightArrowHead returns [String current=null] : iv_rulerightArrowHead= rulerightArrowHead EOF ;
    public final String entryRulerightArrowHead() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_rulerightArrowHead = null;


        try {
            // InternalMyDsl.g:702:54: (iv_rulerightArrowHead= rulerightArrowHead EOF )
            // InternalMyDsl.g:703:2: iv_rulerightArrowHead= rulerightArrowHead EOF
            {
             newCompositeNode(grammarAccess.getRightArrowHeadRule()); 
            pushFollow(FOLLOW_1);
            iv_rulerightArrowHead=rulerightArrowHead();

            state._fsp--;

             current =iv_rulerightArrowHead.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulerightArrowHead"


    // $ANTLR start "rulerightArrowHead"
    // InternalMyDsl.g:709:1: rulerightArrowHead returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '>' | kw= '\\u27E9' | kw= '\\u3009' | kw= '\\uFE65' | kw= '\\uFF1E' ) ;
    public final AntlrDatatypeRuleToken rulerightArrowHead() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalMyDsl.g:715:2: ( (kw= '>' | kw= '\\u27E9' | kw= '\\u3009' | kw= '\\uFE65' | kw= '\\uFF1E' ) )
            // InternalMyDsl.g:716:2: (kw= '>' | kw= '\\u27E9' | kw= '\\u3009' | kw= '\\uFE65' | kw= '\\uFF1E' )
            {
            // InternalMyDsl.g:716:2: (kw= '>' | kw= '\\u27E9' | kw= '\\u3009' | kw= '\\uFE65' | kw= '\\uFF1E' )
            int alt14=5;
            switch ( input.LA(1) ) {
            case 22:
                {
                alt14=1;
                }
                break;
            case 23:
                {
                alt14=2;
                }
                break;
            case 24:
                {
                alt14=3;
                }
                break;
            case 25:
                {
                alt14=4;
                }
                break;
            case 26:
                {
                alt14=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // InternalMyDsl.g:717:3: kw= '>'
                    {
                    kw=(Token)match(input,22,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getRightArrowHeadAccess().getGreaterThanSignKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:723:3: kw= '\\u27E9'
                    {
                    kw=(Token)match(input,23,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getRightArrowHeadAccess().getMathematicalRightAngleBracketKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalMyDsl.g:729:3: kw= '\\u3009'
                    {
                    kw=(Token)match(input,24,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getRightArrowHeadAccess().getRightAngleBracketKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalMyDsl.g:735:3: kw= '\\uFE65'
                    {
                    kw=(Token)match(input,25,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getRightArrowHeadAccess().getSmallGreaterThanSignKeyword_3());
                    		

                    }
                    break;
                case 5 :
                    // InternalMyDsl.g:741:3: kw= '\\uFF1E'
                    {
                    kw=(Token)match(input,26,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getRightArrowHeadAccess().getFullwidthGreaterThanSignKeyword_4());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "rulerightArrowHead"


    // $ANTLR start "entryRuledash"
    // InternalMyDsl.g:750:1: entryRuledash returns [String current=null] : iv_ruledash= ruledash EOF ;
    public final String entryRuledash() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruledash = null;


        try {
            // InternalMyDsl.g:750:44: (iv_ruledash= ruledash EOF )
            // InternalMyDsl.g:751:2: iv_ruledash= ruledash EOF
            {
             newCompositeNode(grammarAccess.getDashRule()); 
            pushFollow(FOLLOW_1);
            iv_ruledash=ruledash();

            state._fsp--;

             current =iv_ruledash.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuledash"


    // $ANTLR start "ruledash"
    // InternalMyDsl.g:757:1: ruledash returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '-' | kw= '\\u00AD' | kw= '\\u2010' | kw= '\\u2011' | kw= '\\u2012' | kw= '\\u2013' | kw= '\\u2014' | kw= '\\u2015' | kw= '\\u2212' | kw= '\\uFE58' | kw= '\\uFE63' | kw= '\\uFF0D' ) ;
    public final AntlrDatatypeRuleToken ruledash() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalMyDsl.g:763:2: ( (kw= '-' | kw= '\\u00AD' | kw= '\\u2010' | kw= '\\u2011' | kw= '\\u2012' | kw= '\\u2013' | kw= '\\u2014' | kw= '\\u2015' | kw= '\\u2212' | kw= '\\uFE58' | kw= '\\uFE63' | kw= '\\uFF0D' ) )
            // InternalMyDsl.g:764:2: (kw= '-' | kw= '\\u00AD' | kw= '\\u2010' | kw= '\\u2011' | kw= '\\u2012' | kw= '\\u2013' | kw= '\\u2014' | kw= '\\u2015' | kw= '\\u2212' | kw= '\\uFE58' | kw= '\\uFE63' | kw= '\\uFF0D' )
            {
            // InternalMyDsl.g:764:2: (kw= '-' | kw= '\\u00AD' | kw= '\\u2010' | kw= '\\u2011' | kw= '\\u2012' | kw= '\\u2013' | kw= '\\u2014' | kw= '\\u2015' | kw= '\\u2212' | kw= '\\uFE58' | kw= '\\uFE63' | kw= '\\uFF0D' )
            int alt15=12;
            switch ( input.LA(1) ) {
            case 27:
                {
                alt15=1;
                }
                break;
            case 28:
                {
                alt15=2;
                }
                break;
            case 29:
                {
                alt15=3;
                }
                break;
            case 30:
                {
                alt15=4;
                }
                break;
            case 31:
                {
                alt15=5;
                }
                break;
            case 32:
                {
                alt15=6;
                }
                break;
            case 33:
                {
                alt15=7;
                }
                break;
            case 34:
                {
                alt15=8;
                }
                break;
            case 35:
                {
                alt15=9;
                }
                break;
            case 36:
                {
                alt15=10;
                }
                break;
            case 37:
                {
                alt15=11;
                }
                break;
            case 38:
                {
                alt15=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }

            switch (alt15) {
                case 1 :
                    // InternalMyDsl.g:765:3: kw= '-'
                    {
                    kw=(Token)match(input,27,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getDashAccess().getHyphenMinusKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:771:3: kw= '\\u00AD'
                    {
                    kw=(Token)match(input,28,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getDashAccess().getSoftHyphenKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalMyDsl.g:777:3: kw= '\\u2010'
                    {
                    kw=(Token)match(input,29,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getDashAccess().getHyphenKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalMyDsl.g:783:3: kw= '\\u2011'
                    {
                    kw=(Token)match(input,30,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getDashAccess().getNonBreakingHyphenKeyword_3());
                    		

                    }
                    break;
                case 5 :
                    // InternalMyDsl.g:789:3: kw= '\\u2012'
                    {
                    kw=(Token)match(input,31,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getDashAccess().getFigureDashKeyword_4());
                    		

                    }
                    break;
                case 6 :
                    // InternalMyDsl.g:795:3: kw= '\\u2013'
                    {
                    kw=(Token)match(input,32,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getDashAccess().getEnDashKeyword_5());
                    		

                    }
                    break;
                case 7 :
                    // InternalMyDsl.g:801:3: kw= '\\u2014'
                    {
                    kw=(Token)match(input,33,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getDashAccess().getEmDashKeyword_6());
                    		

                    }
                    break;
                case 8 :
                    // InternalMyDsl.g:807:3: kw= '\\u2015'
                    {
                    kw=(Token)match(input,34,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getDashAccess().getHorizontalBarKeyword_7());
                    		

                    }
                    break;
                case 9 :
                    // InternalMyDsl.g:813:3: kw= '\\u2212'
                    {
                    kw=(Token)match(input,35,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getDashAccess().getMinusSignKeyword_8());
                    		

                    }
                    break;
                case 10 :
                    // InternalMyDsl.g:819:3: kw= '\\uFE58'
                    {
                    kw=(Token)match(input,36,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getDashAccess().getSmallEmDashKeyword_9());
                    		

                    }
                    break;
                case 11 :
                    // InternalMyDsl.g:825:3: kw= '\\uFE63'
                    {
                    kw=(Token)match(input,37,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getDashAccess().getSmallHyphenMinusKeyword_10());
                    		

                    }
                    break;
                case 12 :
                    // InternalMyDsl.g:831:3: kw= '\\uFF0D'
                    {
                    kw=(Token)match(input,38,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getDashAccess().getFullwidthHyphenMinusKeyword_11());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruledash"

    // Delegated rules


    protected DFA10 dfa10 = new DFA10(this);
    static final String dfa_1s = "\104\uffff";
    static final String dfa_2s = "\37\uffff\14\72\1\uffff\14\76\14\uffff";
    static final String dfa_3s = "\1\21\5\33\30\17\1\4\14\14\1\4\14\14\1\15\1\4\2\uffff\1\15\1\4\2\uffff\1\20\1\33\1\20\1\33";
    static final String dfa_4s = "\36\46\1\15\14\32\1\15\14\32\1\15\1\20\2\uffff\1\15\1\20\2\uffff\1\20\1\46\1\20\1\46";
    static final String dfa_5s = "\72\uffff\1\4\1\3\2\uffff\1\2\1\1\4\uffff";
    static final String dfa_6s = "\104\uffff}>";
    static final String[] dfa_7s = {
            "\1\1\1\2\1\3\1\4\1\5\5\uffff\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21",
            "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34\1\35",
            "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34\1\35",
            "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34\1\35",
            "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34\1\35",
            "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34\1\35",
            "\1\36\13\uffff\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52",
            "\1\36\13\uffff\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52",
            "\1\36\13\uffff\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52",
            "\1\36\13\uffff\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52",
            "\1\36\13\uffff\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52",
            "\1\36\13\uffff\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52",
            "\1\36\13\uffff\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52",
            "\1\36\13\uffff\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52",
            "\1\36\13\uffff\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52",
            "\1\36\13\uffff\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52",
            "\1\36\13\uffff\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52",
            "\1\36\13\uffff\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52",
            "\1\53\13\uffff\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67",
            "\1\53\13\uffff\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67",
            "\1\53\13\uffff\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67",
            "\1\53\13\uffff\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67",
            "\1\53\13\uffff\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67",
            "\1\53\13\uffff\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67",
            "\1\53\13\uffff\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67",
            "\1\53\13\uffff\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67",
            "\1\53\13\uffff\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67",
            "\1\53\13\uffff\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67",
            "\1\53\13\uffff\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67",
            "\1\53\13\uffff\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67",
            "\1\70\10\uffff\1\71",
            "\1\72\11\uffff\5\73",
            "\1\72\11\uffff\5\73",
            "\1\72\11\uffff\5\73",
            "\1\72\11\uffff\5\73",
            "\1\72\11\uffff\5\73",
            "\1\72\11\uffff\5\73",
            "\1\72\11\uffff\5\73",
            "\1\72\11\uffff\5\73",
            "\1\72\11\uffff\5\73",
            "\1\72\11\uffff\5\73",
            "\1\72\11\uffff\5\73",
            "\1\72\11\uffff\5\73",
            "\1\74\10\uffff\1\75",
            "\1\76\11\uffff\5\77",
            "\1\76\11\uffff\5\77",
            "\1\76\11\uffff\5\77",
            "\1\76\11\uffff\5\77",
            "\1\76\11\uffff\5\77",
            "\1\76\11\uffff\5\77",
            "\1\76\11\uffff\5\77",
            "\1\76\11\uffff\5\77",
            "\1\76\11\uffff\5\77",
            "\1\76\11\uffff\5\77",
            "\1\76\11\uffff\5\77",
            "\1\76\11\uffff\5\77",
            "\1\71",
            "\1\100\13\uffff\1\101",
            "",
            "",
            "\1\75",
            "\1\102\13\uffff\1\103",
            "",
            "",
            "\1\101",
            "\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52",
            "\1\103",
            "\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67"
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final short[] dfa_2 = DFA.unpackEncodedString(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final char[] dfa_4 = DFA.unpackEncodedStringToUnsignedChars(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[] dfa_6 = DFA.unpackEncodedString(dfa_6s);
    static final short[][] dfa_7 = unpackEncodedStringArray(dfa_7s);

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "375:2: ( ( ruleleftArrowHead ruledash (this_RelationshipDetail_2= ruleRelationshipDetail )? ruledash rulerightArrowHead ) | ( ruleleftArrowHead ruledash (this_RelationshipDetail_7= ruleRelationshipDetail )? ruledash ) | ( ruledash (this_RelationshipDetail_10= ruleRelationshipDetail )? ruledash rulerightArrowHead ) | ( ruledash (this_RelationshipDetail_14= ruleRelationshipDetail )? ruledash ) )";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000007FF83E0000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000007FF83E0002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000002010L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000004010L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000007FF8000000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000007FF8008000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000007C00000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000010000L});

}