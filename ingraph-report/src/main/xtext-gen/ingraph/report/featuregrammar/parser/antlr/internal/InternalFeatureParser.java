package ingraph.report.featuregrammar.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import ingraph.report.featuregrammar.services.FeatureGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalFeatureParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_TAG", "RULE_FEATURE_TEXT", "RULE_TEXT", "RULE_AS_A", "RULE_IN_ORDER_TO", "RULE_I_WANT_TO", "RULE_SCENARIO_TEXT", "RULE_SCENARIO_OUTLINE_TEXT", "RULE_EXAMPLE_HEADING", "RULE_EXAMPLE_ROW_END", "RULE_EXAMPLE_CELL", "RULE_BACKGROUND_TEXT", "RULE_GIVEN_TEXT", "RULE_WHEN_TEXT", "RULE_THEN_TEXT", "RULE_AND_TEXT", "RULE_CODE", "RULE_SPACES", "RULE_NNL", "RULE_NL", "RULE_SL_COMMENT", "RULE_WS", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_ANY_OTHER"
    };
    public static final int RULE_WHEN_TEXT=17;
    public static final int RULE_STRING=28;
    public static final int RULE_TAG=4;
    public static final int RULE_I_WANT_TO=9;
    public static final int RULE_THEN_TEXT=18;
    public static final int RULE_FEATURE_TEXT=5;
    public static final int RULE_SCENARIO_TEXT=10;
    public static final int RULE_SL_COMMENT=24;
    public static final int RULE_GIVEN_TEXT=16;
    public static final int RULE_EXAMPLE_HEADING=12;
    public static final int RULE_BACKGROUND_TEXT=15;
    public static final int EOF=-1;
    public static final int RULE_IN_ORDER_TO=8;
    public static final int RULE_AND_TEXT=19;
    public static final int RULE_ID=26;
    public static final int RULE_WS=25;
    public static final int RULE_SCENARIO_OUTLINE_TEXT=11;
    public static final int RULE_ANY_OTHER=30;
    public static final int RULE_SPACES=21;
    public static final int RULE_CODE=20;
    public static final int RULE_NNL=22;
    public static final int RULE_EXAMPLE_CELL=14;
    public static final int RULE_INT=27;
    public static final int RULE_AS_A=7;
    public static final int RULE_ML_COMMENT=29;
    public static final int RULE_TEXT=6;
    public static final int RULE_NL=23;
    public static final int RULE_EXAMPLE_ROW_END=13;

    // delegates
    // delegators


        public InternalFeatureParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalFeatureParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalFeatureParser.tokenNames; }
    public String getGrammarFileName() { return "InternalFeature.g"; }



     	private FeatureGrammarAccess grammarAccess;

        public InternalFeatureParser(TokenStream input, FeatureGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Feature";
       	}

       	@Override
       	protected FeatureGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleFeature"
    // InternalFeature.g:64:1: entryRuleFeature returns [EObject current=null] : iv_ruleFeature= ruleFeature EOF ;
    public final EObject entryRuleFeature() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFeature = null;


        try {
            // InternalFeature.g:64:48: (iv_ruleFeature= ruleFeature EOF )
            // InternalFeature.g:65:2: iv_ruleFeature= ruleFeature EOF
            {
             newCompositeNode(grammarAccess.getFeatureRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFeature=ruleFeature();

            state._fsp--;

             current =iv_ruleFeature; 
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
    // $ANTLR end "entryRuleFeature"


    // $ANTLR start "ruleFeature"
    // InternalFeature.g:71:1: ruleFeature returns [EObject current=null] : ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_name_1_0= RULE_FEATURE_TEXT ) )? ( (lv_elements_2_0= ruleNarrativeElement ) )* ( (lv_scenarios_3_0= ruleAbstractScenario ) )* ) ;
    public final EObject ruleFeature() throws RecognitionException {
        EObject current = null;

        Token lv_tags_0_0=null;
        Token lv_name_1_0=null;
        EObject lv_elements_2_0 = null;

        EObject lv_scenarios_3_0 = null;



        	enterRule();

        try {
            // InternalFeature.g:77:2: ( ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_name_1_0= RULE_FEATURE_TEXT ) )? ( (lv_elements_2_0= ruleNarrativeElement ) )* ( (lv_scenarios_3_0= ruleAbstractScenario ) )* ) )
            // InternalFeature.g:78:2: ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_name_1_0= RULE_FEATURE_TEXT ) )? ( (lv_elements_2_0= ruleNarrativeElement ) )* ( (lv_scenarios_3_0= ruleAbstractScenario ) )* )
            {
            // InternalFeature.g:78:2: ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_name_1_0= RULE_FEATURE_TEXT ) )? ( (lv_elements_2_0= ruleNarrativeElement ) )* ( (lv_scenarios_3_0= ruleAbstractScenario ) )* )
            // InternalFeature.g:79:3: ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_name_1_0= RULE_FEATURE_TEXT ) )? ( (lv_elements_2_0= ruleNarrativeElement ) )* ( (lv_scenarios_3_0= ruleAbstractScenario ) )*
            {
            // InternalFeature.g:79:3: ( (lv_tags_0_0= RULE_TAG ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_TAG) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalFeature.g:80:4: (lv_tags_0_0= RULE_TAG )
            	    {
            	    // InternalFeature.g:80:4: (lv_tags_0_0= RULE_TAG )
            	    // InternalFeature.g:81:5: lv_tags_0_0= RULE_TAG
            	    {
            	    lv_tags_0_0=(Token)match(input,RULE_TAG,FOLLOW_3); 

            	    					newLeafNode(lv_tags_0_0, grammarAccess.getFeatureAccess().getTagsTAGTerminalRuleCall_0_0());
            	    				

            	    					if (current==null) {
            	    						current = createModelElement(grammarAccess.getFeatureRule());
            	    					}
            	    					addWithLastConsumed(
            	    						current,
            	    						"tags",
            	    						lv_tags_0_0,
            	    						"ingraph.report.featuregrammar.Feature.TAG");
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // InternalFeature.g:97:3: ( (lv_name_1_0= RULE_FEATURE_TEXT ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_FEATURE_TEXT) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalFeature.g:98:4: (lv_name_1_0= RULE_FEATURE_TEXT )
                    {
                    // InternalFeature.g:98:4: (lv_name_1_0= RULE_FEATURE_TEXT )
                    // InternalFeature.g:99:5: lv_name_1_0= RULE_FEATURE_TEXT
                    {
                    lv_name_1_0=(Token)match(input,RULE_FEATURE_TEXT,FOLLOW_4); 

                    					newLeafNode(lv_name_1_0, grammarAccess.getFeatureAccess().getNameFEATURE_TEXTTerminalRuleCall_1_0());
                    				

                    					if (current==null) {
                    						current = createModelElement(grammarAccess.getFeatureRule());
                    					}
                    					setWithLastConsumed(
                    						current,
                    						"name",
                    						lv_name_1_0,
                    						"ingraph.report.featuregrammar.Feature.FEATURE_TEXT");
                    				

                    }


                    }
                    break;

            }

            // InternalFeature.g:115:3: ( (lv_elements_2_0= ruleNarrativeElement ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>=RULE_TEXT && LA3_0<=RULE_I_WANT_TO)) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalFeature.g:116:4: (lv_elements_2_0= ruleNarrativeElement )
            	    {
            	    // InternalFeature.g:116:4: (lv_elements_2_0= ruleNarrativeElement )
            	    // InternalFeature.g:117:5: lv_elements_2_0= ruleNarrativeElement
            	    {

            	    					newCompositeNode(grammarAccess.getFeatureAccess().getElementsNarrativeElementParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_4);
            	    lv_elements_2_0=ruleNarrativeElement();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getFeatureRule());
            	    					}
            	    					add(
            	    						current,
            	    						"elements",
            	    						lv_elements_2_0,
            	    						"ingraph.report.featuregrammar.Feature.NarrativeElement");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // InternalFeature.g:134:3: ( (lv_scenarios_3_0= ruleAbstractScenario ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==RULE_TAG||(LA4_0>=RULE_SCENARIO_TEXT && LA4_0<=RULE_SCENARIO_OUTLINE_TEXT)||LA4_0==RULE_BACKGROUND_TEXT) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalFeature.g:135:4: (lv_scenarios_3_0= ruleAbstractScenario )
            	    {
            	    // InternalFeature.g:135:4: (lv_scenarios_3_0= ruleAbstractScenario )
            	    // InternalFeature.g:136:5: lv_scenarios_3_0= ruleAbstractScenario
            	    {

            	    					newCompositeNode(grammarAccess.getFeatureAccess().getScenariosAbstractScenarioParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_5);
            	    lv_scenarios_3_0=ruleAbstractScenario();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getFeatureRule());
            	    					}
            	    					add(
            	    						current,
            	    						"scenarios",
            	    						lv_scenarios_3_0,
            	    						"ingraph.report.featuregrammar.Feature.AbstractScenario");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
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
    // $ANTLR end "ruleFeature"


    // $ANTLR start "entryRuleNarrativeElement"
    // InternalFeature.g:157:1: entryRuleNarrativeElement returns [EObject current=null] : iv_ruleNarrativeElement= ruleNarrativeElement EOF ;
    public final EObject entryRuleNarrativeElement() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNarrativeElement = null;


        try {
            // InternalFeature.g:157:57: (iv_ruleNarrativeElement= ruleNarrativeElement EOF )
            // InternalFeature.g:158:2: iv_ruleNarrativeElement= ruleNarrativeElement EOF
            {
             newCompositeNode(grammarAccess.getNarrativeElementRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleNarrativeElement=ruleNarrativeElement();

            state._fsp--;

             current =iv_ruleNarrativeElement; 
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
    // $ANTLR end "entryRuleNarrativeElement"


    // $ANTLR start "ruleNarrativeElement"
    // InternalFeature.g:164:1: ruleNarrativeElement returns [EObject current=null] : (this_InOrderTo_0= ruleInOrderTo | this_AsA_1= ruleAsA | this_IWantTo_2= ruleIWantTo | this_FreeText_3= ruleFreeText ) ;
    public final EObject ruleNarrativeElement() throws RecognitionException {
        EObject current = null;

        EObject this_InOrderTo_0 = null;

        EObject this_AsA_1 = null;

        EObject this_IWantTo_2 = null;

        EObject this_FreeText_3 = null;



        	enterRule();

        try {
            // InternalFeature.g:170:2: ( (this_InOrderTo_0= ruleInOrderTo | this_AsA_1= ruleAsA | this_IWantTo_2= ruleIWantTo | this_FreeText_3= ruleFreeText ) )
            // InternalFeature.g:171:2: (this_InOrderTo_0= ruleInOrderTo | this_AsA_1= ruleAsA | this_IWantTo_2= ruleIWantTo | this_FreeText_3= ruleFreeText )
            {
            // InternalFeature.g:171:2: (this_InOrderTo_0= ruleInOrderTo | this_AsA_1= ruleAsA | this_IWantTo_2= ruleIWantTo | this_FreeText_3= ruleFreeText )
            int alt5=4;
            switch ( input.LA(1) ) {
            case RULE_IN_ORDER_TO:
                {
                alt5=1;
                }
                break;
            case RULE_AS_A:
                {
                alt5=2;
                }
                break;
            case RULE_I_WANT_TO:
                {
                alt5=3;
                }
                break;
            case RULE_TEXT:
                {
                alt5=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // InternalFeature.g:172:3: this_InOrderTo_0= ruleInOrderTo
                    {

                    			newCompositeNode(grammarAccess.getNarrativeElementAccess().getInOrderToParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_InOrderTo_0=ruleInOrderTo();

                    state._fsp--;


                    			current = this_InOrderTo_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFeature.g:181:3: this_AsA_1= ruleAsA
                    {

                    			newCompositeNode(grammarAccess.getNarrativeElementAccess().getAsAParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_AsA_1=ruleAsA();

                    state._fsp--;


                    			current = this_AsA_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalFeature.g:190:3: this_IWantTo_2= ruleIWantTo
                    {

                    			newCompositeNode(grammarAccess.getNarrativeElementAccess().getIWantToParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_IWantTo_2=ruleIWantTo();

                    state._fsp--;


                    			current = this_IWantTo_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalFeature.g:199:3: this_FreeText_3= ruleFreeText
                    {

                    			newCompositeNode(grammarAccess.getNarrativeElementAccess().getFreeTextParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_FreeText_3=ruleFreeText();

                    state._fsp--;


                    			current = this_FreeText_3;
                    			afterParserOrEnumRuleCall();
                    		

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
    // $ANTLR end "ruleNarrativeElement"


    // $ANTLR start "entryRuleFreeText"
    // InternalFeature.g:211:1: entryRuleFreeText returns [EObject current=null] : iv_ruleFreeText= ruleFreeText EOF ;
    public final EObject entryRuleFreeText() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFreeText = null;


        try {
            // InternalFeature.g:211:49: (iv_ruleFreeText= ruleFreeText EOF )
            // InternalFeature.g:212:2: iv_ruleFreeText= ruleFreeText EOF
            {
             newCompositeNode(grammarAccess.getFreeTextRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFreeText=ruleFreeText();

            state._fsp--;

             current =iv_ruleFreeText; 
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
    // $ANTLR end "entryRuleFreeText"


    // $ANTLR start "ruleFreeText"
    // InternalFeature.g:218:1: ruleFreeText returns [EObject current=null] : ( (lv_name_0_0= RULE_TEXT ) ) ;
    public final EObject ruleFreeText() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;


        	enterRule();

        try {
            // InternalFeature.g:224:2: ( ( (lv_name_0_0= RULE_TEXT ) ) )
            // InternalFeature.g:225:2: ( (lv_name_0_0= RULE_TEXT ) )
            {
            // InternalFeature.g:225:2: ( (lv_name_0_0= RULE_TEXT ) )
            // InternalFeature.g:226:3: (lv_name_0_0= RULE_TEXT )
            {
            // InternalFeature.g:226:3: (lv_name_0_0= RULE_TEXT )
            // InternalFeature.g:227:4: lv_name_0_0= RULE_TEXT
            {
            lv_name_0_0=(Token)match(input,RULE_TEXT,FOLLOW_2); 

            				newLeafNode(lv_name_0_0, grammarAccess.getFreeTextAccess().getNameTEXTTerminalRuleCall_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getFreeTextRule());
            				}
            				setWithLastConsumed(
            					current,
            					"name",
            					lv_name_0_0,
            					"ingraph.report.featuregrammar.Feature.TEXT");
            			

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
    // $ANTLR end "ruleFreeText"


    // $ANTLR start "entryRuleAsA"
    // InternalFeature.g:246:1: entryRuleAsA returns [EObject current=null] : iv_ruleAsA= ruleAsA EOF ;
    public final EObject entryRuleAsA() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAsA = null;


        try {
            // InternalFeature.g:246:44: (iv_ruleAsA= ruleAsA EOF )
            // InternalFeature.g:247:2: iv_ruleAsA= ruleAsA EOF
            {
             newCompositeNode(grammarAccess.getAsARule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAsA=ruleAsA();

            state._fsp--;

             current =iv_ruleAsA; 
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
    // $ANTLR end "entryRuleAsA"


    // $ANTLR start "ruleAsA"
    // InternalFeature.g:253:1: ruleAsA returns [EObject current=null] : ( (lv_name_0_0= RULE_AS_A ) ) ;
    public final EObject ruleAsA() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;


        	enterRule();

        try {
            // InternalFeature.g:259:2: ( ( (lv_name_0_0= RULE_AS_A ) ) )
            // InternalFeature.g:260:2: ( (lv_name_0_0= RULE_AS_A ) )
            {
            // InternalFeature.g:260:2: ( (lv_name_0_0= RULE_AS_A ) )
            // InternalFeature.g:261:3: (lv_name_0_0= RULE_AS_A )
            {
            // InternalFeature.g:261:3: (lv_name_0_0= RULE_AS_A )
            // InternalFeature.g:262:4: lv_name_0_0= RULE_AS_A
            {
            lv_name_0_0=(Token)match(input,RULE_AS_A,FOLLOW_2); 

            				newLeafNode(lv_name_0_0, grammarAccess.getAsAAccess().getNameAS_ATerminalRuleCall_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getAsARule());
            				}
            				setWithLastConsumed(
            					current,
            					"name",
            					lv_name_0_0,
            					"ingraph.report.featuregrammar.Feature.AS_A");
            			

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
    // $ANTLR end "ruleAsA"


    // $ANTLR start "entryRuleInOrderTo"
    // InternalFeature.g:281:1: entryRuleInOrderTo returns [EObject current=null] : iv_ruleInOrderTo= ruleInOrderTo EOF ;
    public final EObject entryRuleInOrderTo() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInOrderTo = null;


        try {
            // InternalFeature.g:281:50: (iv_ruleInOrderTo= ruleInOrderTo EOF )
            // InternalFeature.g:282:2: iv_ruleInOrderTo= ruleInOrderTo EOF
            {
             newCompositeNode(grammarAccess.getInOrderToRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInOrderTo=ruleInOrderTo();

            state._fsp--;

             current =iv_ruleInOrderTo; 
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
    // $ANTLR end "entryRuleInOrderTo"


    // $ANTLR start "ruleInOrderTo"
    // InternalFeature.g:288:1: ruleInOrderTo returns [EObject current=null] : ( (lv_name_0_0= RULE_IN_ORDER_TO ) ) ;
    public final EObject ruleInOrderTo() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;


        	enterRule();

        try {
            // InternalFeature.g:294:2: ( ( (lv_name_0_0= RULE_IN_ORDER_TO ) ) )
            // InternalFeature.g:295:2: ( (lv_name_0_0= RULE_IN_ORDER_TO ) )
            {
            // InternalFeature.g:295:2: ( (lv_name_0_0= RULE_IN_ORDER_TO ) )
            // InternalFeature.g:296:3: (lv_name_0_0= RULE_IN_ORDER_TO )
            {
            // InternalFeature.g:296:3: (lv_name_0_0= RULE_IN_ORDER_TO )
            // InternalFeature.g:297:4: lv_name_0_0= RULE_IN_ORDER_TO
            {
            lv_name_0_0=(Token)match(input,RULE_IN_ORDER_TO,FOLLOW_2); 

            				newLeafNode(lv_name_0_0, grammarAccess.getInOrderToAccess().getNameIN_ORDER_TOTerminalRuleCall_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getInOrderToRule());
            				}
            				setWithLastConsumed(
            					current,
            					"name",
            					lv_name_0_0,
            					"ingraph.report.featuregrammar.Feature.IN_ORDER_TO");
            			

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
    // $ANTLR end "ruleInOrderTo"


    // $ANTLR start "entryRuleIWantTo"
    // InternalFeature.g:316:1: entryRuleIWantTo returns [EObject current=null] : iv_ruleIWantTo= ruleIWantTo EOF ;
    public final EObject entryRuleIWantTo() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIWantTo = null;


        try {
            // InternalFeature.g:316:48: (iv_ruleIWantTo= ruleIWantTo EOF )
            // InternalFeature.g:317:2: iv_ruleIWantTo= ruleIWantTo EOF
            {
             newCompositeNode(grammarAccess.getIWantToRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIWantTo=ruleIWantTo();

            state._fsp--;

             current =iv_ruleIWantTo; 
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
    // $ANTLR end "entryRuleIWantTo"


    // $ANTLR start "ruleIWantTo"
    // InternalFeature.g:323:1: ruleIWantTo returns [EObject current=null] : ( (lv_name_0_0= RULE_I_WANT_TO ) ) ;
    public final EObject ruleIWantTo() throws RecognitionException {
        EObject current = null;

        Token lv_name_0_0=null;


        	enterRule();

        try {
            // InternalFeature.g:329:2: ( ( (lv_name_0_0= RULE_I_WANT_TO ) ) )
            // InternalFeature.g:330:2: ( (lv_name_0_0= RULE_I_WANT_TO ) )
            {
            // InternalFeature.g:330:2: ( (lv_name_0_0= RULE_I_WANT_TO ) )
            // InternalFeature.g:331:3: (lv_name_0_0= RULE_I_WANT_TO )
            {
            // InternalFeature.g:331:3: (lv_name_0_0= RULE_I_WANT_TO )
            // InternalFeature.g:332:4: lv_name_0_0= RULE_I_WANT_TO
            {
            lv_name_0_0=(Token)match(input,RULE_I_WANT_TO,FOLLOW_2); 

            				newLeafNode(lv_name_0_0, grammarAccess.getIWantToAccess().getNameI_WANT_TOTerminalRuleCall_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getIWantToRule());
            				}
            				setWithLastConsumed(
            					current,
            					"name",
            					lv_name_0_0,
            					"ingraph.report.featuregrammar.Feature.I_WANT_TO");
            			

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
    // $ANTLR end "ruleIWantTo"


    // $ANTLR start "entryRuleAbstractScenario"
    // InternalFeature.g:351:1: entryRuleAbstractScenario returns [EObject current=null] : iv_ruleAbstractScenario= ruleAbstractScenario EOF ;
    public final EObject entryRuleAbstractScenario() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAbstractScenario = null;


        try {
            // InternalFeature.g:351:57: (iv_ruleAbstractScenario= ruleAbstractScenario EOF )
            // InternalFeature.g:352:2: iv_ruleAbstractScenario= ruleAbstractScenario EOF
            {
             newCompositeNode(grammarAccess.getAbstractScenarioRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAbstractScenario=ruleAbstractScenario();

            state._fsp--;

             current =iv_ruleAbstractScenario; 
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
    // $ANTLR end "entryRuleAbstractScenario"


    // $ANTLR start "ruleAbstractScenario"
    // InternalFeature.g:358:1: ruleAbstractScenario returns [EObject current=null] : (this_Background_0= ruleBackground | this_Scenario_1= ruleScenario | this_ScenarioWithOutline_2= ruleScenarioWithOutline ) ;
    public final EObject ruleAbstractScenario() throws RecognitionException {
        EObject current = null;

        EObject this_Background_0 = null;

        EObject this_Scenario_1 = null;

        EObject this_ScenarioWithOutline_2 = null;



        	enterRule();

        try {
            // InternalFeature.g:364:2: ( (this_Background_0= ruleBackground | this_Scenario_1= ruleScenario | this_ScenarioWithOutline_2= ruleScenarioWithOutline ) )
            // InternalFeature.g:365:2: (this_Background_0= ruleBackground | this_Scenario_1= ruleScenario | this_ScenarioWithOutline_2= ruleScenarioWithOutline )
            {
            // InternalFeature.g:365:2: (this_Background_0= ruleBackground | this_Scenario_1= ruleScenario | this_ScenarioWithOutline_2= ruleScenarioWithOutline )
            int alt6=3;
            alt6 = dfa6.predict(input);
            switch (alt6) {
                case 1 :
                    // InternalFeature.g:366:3: this_Background_0= ruleBackground
                    {

                    			newCompositeNode(grammarAccess.getAbstractScenarioAccess().getBackgroundParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Background_0=ruleBackground();

                    state._fsp--;


                    			current = this_Background_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFeature.g:375:3: this_Scenario_1= ruleScenario
                    {

                    			newCompositeNode(grammarAccess.getAbstractScenarioAccess().getScenarioParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Scenario_1=ruleScenario();

                    state._fsp--;


                    			current = this_Scenario_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalFeature.g:384:3: this_ScenarioWithOutline_2= ruleScenarioWithOutline
                    {

                    			newCompositeNode(grammarAccess.getAbstractScenarioAccess().getScenarioWithOutlineParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_ScenarioWithOutline_2=ruleScenarioWithOutline();

                    state._fsp--;


                    			current = this_ScenarioWithOutline_2;
                    			afterParserOrEnumRuleCall();
                    		

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
    // $ANTLR end "ruleAbstractScenario"


    // $ANTLR start "entryRuleScenario"
    // InternalFeature.g:396:1: entryRuleScenario returns [EObject current=null] : iv_ruleScenario= ruleScenario EOF ;
    public final EObject entryRuleScenario() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScenario = null;


        try {
            // InternalFeature.g:396:49: (iv_ruleScenario= ruleScenario EOF )
            // InternalFeature.g:397:2: iv_ruleScenario= ruleScenario EOF
            {
             newCompositeNode(grammarAccess.getScenarioRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleScenario=ruleScenario();

            state._fsp--;

             current =iv_ruleScenario; 
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
    // $ANTLR end "entryRuleScenario"


    // $ANTLR start "ruleScenario"
    // InternalFeature.g:403:1: ruleScenario returns [EObject current=null] : ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_name_1_0= RULE_SCENARIO_TEXT ) ) ( (lv_elements_2_0= ruleNarrativeElement ) )* ( (lv_steps_3_0= ruleStep ) )+ ) ;
    public final EObject ruleScenario() throws RecognitionException {
        EObject current = null;

        Token lv_tags_0_0=null;
        Token lv_name_1_0=null;
        EObject lv_elements_2_0 = null;

        EObject lv_steps_3_0 = null;



        	enterRule();

        try {
            // InternalFeature.g:409:2: ( ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_name_1_0= RULE_SCENARIO_TEXT ) ) ( (lv_elements_2_0= ruleNarrativeElement ) )* ( (lv_steps_3_0= ruleStep ) )+ ) )
            // InternalFeature.g:410:2: ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_name_1_0= RULE_SCENARIO_TEXT ) ) ( (lv_elements_2_0= ruleNarrativeElement ) )* ( (lv_steps_3_0= ruleStep ) )+ )
            {
            // InternalFeature.g:410:2: ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_name_1_0= RULE_SCENARIO_TEXT ) ) ( (lv_elements_2_0= ruleNarrativeElement ) )* ( (lv_steps_3_0= ruleStep ) )+ )
            // InternalFeature.g:411:3: ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_name_1_0= RULE_SCENARIO_TEXT ) ) ( (lv_elements_2_0= ruleNarrativeElement ) )* ( (lv_steps_3_0= ruleStep ) )+
            {
            // InternalFeature.g:411:3: ( (lv_tags_0_0= RULE_TAG ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==RULE_TAG) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalFeature.g:412:4: (lv_tags_0_0= RULE_TAG )
            	    {
            	    // InternalFeature.g:412:4: (lv_tags_0_0= RULE_TAG )
            	    // InternalFeature.g:413:5: lv_tags_0_0= RULE_TAG
            	    {
            	    lv_tags_0_0=(Token)match(input,RULE_TAG,FOLLOW_6); 

            	    					newLeafNode(lv_tags_0_0, grammarAccess.getScenarioAccess().getTagsTAGTerminalRuleCall_0_0());
            	    				

            	    					if (current==null) {
            	    						current = createModelElement(grammarAccess.getScenarioRule());
            	    					}
            	    					addWithLastConsumed(
            	    						current,
            	    						"tags",
            	    						lv_tags_0_0,
            	    						"ingraph.report.featuregrammar.Feature.TAG");
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            // InternalFeature.g:429:3: ( (lv_name_1_0= RULE_SCENARIO_TEXT ) )
            // InternalFeature.g:430:4: (lv_name_1_0= RULE_SCENARIO_TEXT )
            {
            // InternalFeature.g:430:4: (lv_name_1_0= RULE_SCENARIO_TEXT )
            // InternalFeature.g:431:5: lv_name_1_0= RULE_SCENARIO_TEXT
            {
            lv_name_1_0=(Token)match(input,RULE_SCENARIO_TEXT,FOLLOW_7); 

            					newLeafNode(lv_name_1_0, grammarAccess.getScenarioAccess().getNameSCENARIO_TEXTTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getScenarioRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"ingraph.report.featuregrammar.Feature.SCENARIO_TEXT");
            				

            }


            }

            // InternalFeature.g:447:3: ( (lv_elements_2_0= ruleNarrativeElement ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>=RULE_TEXT && LA8_0<=RULE_I_WANT_TO)) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalFeature.g:448:4: (lv_elements_2_0= ruleNarrativeElement )
            	    {
            	    // InternalFeature.g:448:4: (lv_elements_2_0= ruleNarrativeElement )
            	    // InternalFeature.g:449:5: lv_elements_2_0= ruleNarrativeElement
            	    {

            	    					newCompositeNode(grammarAccess.getScenarioAccess().getElementsNarrativeElementParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_7);
            	    lv_elements_2_0=ruleNarrativeElement();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getScenarioRule());
            	    					}
            	    					add(
            	    						current,
            	    						"elements",
            	    						lv_elements_2_0,
            	    						"ingraph.report.featuregrammar.Feature.NarrativeElement");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // InternalFeature.g:466:3: ( (lv_steps_3_0= ruleStep ) )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                alt9 = dfa9.predict(input);
                switch (alt9) {
            	case 1 :
            	    // InternalFeature.g:467:4: (lv_steps_3_0= ruleStep )
            	    {
            	    // InternalFeature.g:467:4: (lv_steps_3_0= ruleStep )
            	    // InternalFeature.g:468:5: lv_steps_3_0= ruleStep
            	    {

            	    					newCompositeNode(grammarAccess.getScenarioAccess().getStepsStepParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_8);
            	    lv_steps_3_0=ruleStep();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getScenarioRule());
            	    					}
            	    					add(
            	    						current,
            	    						"steps",
            	    						lv_steps_3_0,
            	    						"ingraph.report.featuregrammar.Feature.Step");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
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
    // $ANTLR end "ruleScenario"


    // $ANTLR start "entryRuleScenarioWithOutline"
    // InternalFeature.g:489:1: entryRuleScenarioWithOutline returns [EObject current=null] : iv_ruleScenarioWithOutline= ruleScenarioWithOutline EOF ;
    public final EObject entryRuleScenarioWithOutline() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleScenarioWithOutline = null;


        try {
            // InternalFeature.g:489:60: (iv_ruleScenarioWithOutline= ruleScenarioWithOutline EOF )
            // InternalFeature.g:490:2: iv_ruleScenarioWithOutline= ruleScenarioWithOutline EOF
            {
             newCompositeNode(grammarAccess.getScenarioWithOutlineRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleScenarioWithOutline=ruleScenarioWithOutline();

            state._fsp--;

             current =iv_ruleScenarioWithOutline; 
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
    // $ANTLR end "entryRuleScenarioWithOutline"


    // $ANTLR start "ruleScenarioWithOutline"
    // InternalFeature.g:496:1: ruleScenarioWithOutline returns [EObject current=null] : ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_name_1_0= RULE_SCENARIO_OUTLINE_TEXT ) ) ( (lv_elements_2_0= ruleNarrativeElement ) )* ( (lv_steps_3_0= ruleStep ) )+ ( (lv_example_4_0= ruleExample ) ) ) ;
    public final EObject ruleScenarioWithOutline() throws RecognitionException {
        EObject current = null;

        Token lv_tags_0_0=null;
        Token lv_name_1_0=null;
        EObject lv_elements_2_0 = null;

        EObject lv_steps_3_0 = null;

        EObject lv_example_4_0 = null;



        	enterRule();

        try {
            // InternalFeature.g:502:2: ( ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_name_1_0= RULE_SCENARIO_OUTLINE_TEXT ) ) ( (lv_elements_2_0= ruleNarrativeElement ) )* ( (lv_steps_3_0= ruleStep ) )+ ( (lv_example_4_0= ruleExample ) ) ) )
            // InternalFeature.g:503:2: ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_name_1_0= RULE_SCENARIO_OUTLINE_TEXT ) ) ( (lv_elements_2_0= ruleNarrativeElement ) )* ( (lv_steps_3_0= ruleStep ) )+ ( (lv_example_4_0= ruleExample ) ) )
            {
            // InternalFeature.g:503:2: ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_name_1_0= RULE_SCENARIO_OUTLINE_TEXT ) ) ( (lv_elements_2_0= ruleNarrativeElement ) )* ( (lv_steps_3_0= ruleStep ) )+ ( (lv_example_4_0= ruleExample ) ) )
            // InternalFeature.g:504:3: ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_name_1_0= RULE_SCENARIO_OUTLINE_TEXT ) ) ( (lv_elements_2_0= ruleNarrativeElement ) )* ( (lv_steps_3_0= ruleStep ) )+ ( (lv_example_4_0= ruleExample ) )
            {
            // InternalFeature.g:504:3: ( (lv_tags_0_0= RULE_TAG ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==RULE_TAG) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalFeature.g:505:4: (lv_tags_0_0= RULE_TAG )
            	    {
            	    // InternalFeature.g:505:4: (lv_tags_0_0= RULE_TAG )
            	    // InternalFeature.g:506:5: lv_tags_0_0= RULE_TAG
            	    {
            	    lv_tags_0_0=(Token)match(input,RULE_TAG,FOLLOW_9); 

            	    					newLeafNode(lv_tags_0_0, grammarAccess.getScenarioWithOutlineAccess().getTagsTAGTerminalRuleCall_0_0());
            	    				

            	    					if (current==null) {
            	    						current = createModelElement(grammarAccess.getScenarioWithOutlineRule());
            	    					}
            	    					addWithLastConsumed(
            	    						current,
            	    						"tags",
            	    						lv_tags_0_0,
            	    						"ingraph.report.featuregrammar.Feature.TAG");
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            // InternalFeature.g:522:3: ( (lv_name_1_0= RULE_SCENARIO_OUTLINE_TEXT ) )
            // InternalFeature.g:523:4: (lv_name_1_0= RULE_SCENARIO_OUTLINE_TEXT )
            {
            // InternalFeature.g:523:4: (lv_name_1_0= RULE_SCENARIO_OUTLINE_TEXT )
            // InternalFeature.g:524:5: lv_name_1_0= RULE_SCENARIO_OUTLINE_TEXT
            {
            lv_name_1_0=(Token)match(input,RULE_SCENARIO_OUTLINE_TEXT,FOLLOW_7); 

            					newLeafNode(lv_name_1_0, grammarAccess.getScenarioWithOutlineAccess().getNameSCENARIO_OUTLINE_TEXTTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getScenarioWithOutlineRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"ingraph.report.featuregrammar.Feature.SCENARIO_OUTLINE_TEXT");
            				

            }


            }

            // InternalFeature.g:540:3: ( (lv_elements_2_0= ruleNarrativeElement ) )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>=RULE_TEXT && LA11_0<=RULE_I_WANT_TO)) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalFeature.g:541:4: (lv_elements_2_0= ruleNarrativeElement )
            	    {
            	    // InternalFeature.g:541:4: (lv_elements_2_0= ruleNarrativeElement )
            	    // InternalFeature.g:542:5: lv_elements_2_0= ruleNarrativeElement
            	    {

            	    					newCompositeNode(grammarAccess.getScenarioWithOutlineAccess().getElementsNarrativeElementParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_7);
            	    lv_elements_2_0=ruleNarrativeElement();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getScenarioWithOutlineRule());
            	    					}
            	    					add(
            	    						current,
            	    						"elements",
            	    						lv_elements_2_0,
            	    						"ingraph.report.featuregrammar.Feature.NarrativeElement");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            // InternalFeature.g:559:3: ( (lv_steps_3_0= ruleStep ) )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==RULE_TAG||(LA12_0>=RULE_GIVEN_TEXT && LA12_0<=RULE_AND_TEXT)) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalFeature.g:560:4: (lv_steps_3_0= ruleStep )
            	    {
            	    // InternalFeature.g:560:4: (lv_steps_3_0= ruleStep )
            	    // InternalFeature.g:561:5: lv_steps_3_0= ruleStep
            	    {

            	    					newCompositeNode(grammarAccess.getScenarioWithOutlineAccess().getStepsStepParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_10);
            	    lv_steps_3_0=ruleStep();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getScenarioWithOutlineRule());
            	    					}
            	    					add(
            	    						current,
            	    						"steps",
            	    						lv_steps_3_0,
            	    						"ingraph.report.featuregrammar.Feature.Step");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);

            // InternalFeature.g:578:3: ( (lv_example_4_0= ruleExample ) )
            // InternalFeature.g:579:4: (lv_example_4_0= ruleExample )
            {
            // InternalFeature.g:579:4: (lv_example_4_0= ruleExample )
            // InternalFeature.g:580:5: lv_example_4_0= ruleExample
            {

            					newCompositeNode(grammarAccess.getScenarioWithOutlineAccess().getExampleExampleParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_2);
            lv_example_4_0=ruleExample();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getScenarioWithOutlineRule());
            					}
            					set(
            						current,
            						"example",
            						lv_example_4_0,
            						"ingraph.report.featuregrammar.Feature.Example");
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
    // $ANTLR end "ruleScenarioWithOutline"


    // $ANTLR start "entryRuleExample"
    // InternalFeature.g:601:1: entryRuleExample returns [EObject current=null] : iv_ruleExample= ruleExample EOF ;
    public final EObject entryRuleExample() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExample = null;


        try {
            // InternalFeature.g:601:48: (iv_ruleExample= ruleExample EOF )
            // InternalFeature.g:602:2: iv_ruleExample= ruleExample EOF
            {
             newCompositeNode(grammarAccess.getExampleRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExample=ruleExample();

            state._fsp--;

             current =iv_ruleExample; 
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
    // $ANTLR end "entryRuleExample"


    // $ANTLR start "ruleExample"
    // InternalFeature.g:608:1: ruleExample returns [EObject current=null] : (this_EXAMPLE_HEADING_0= RULE_EXAMPLE_HEADING ( (lv_heading_1_0= ruleExampleRow ) ) ( (lv_rows_2_0= ruleExampleRow ) )* ) ;
    public final EObject ruleExample() throws RecognitionException {
        EObject current = null;

        Token this_EXAMPLE_HEADING_0=null;
        EObject lv_heading_1_0 = null;

        EObject lv_rows_2_0 = null;



        	enterRule();

        try {
            // InternalFeature.g:614:2: ( (this_EXAMPLE_HEADING_0= RULE_EXAMPLE_HEADING ( (lv_heading_1_0= ruleExampleRow ) ) ( (lv_rows_2_0= ruleExampleRow ) )* ) )
            // InternalFeature.g:615:2: (this_EXAMPLE_HEADING_0= RULE_EXAMPLE_HEADING ( (lv_heading_1_0= ruleExampleRow ) ) ( (lv_rows_2_0= ruleExampleRow ) )* )
            {
            // InternalFeature.g:615:2: (this_EXAMPLE_HEADING_0= RULE_EXAMPLE_HEADING ( (lv_heading_1_0= ruleExampleRow ) ) ( (lv_rows_2_0= ruleExampleRow ) )* )
            // InternalFeature.g:616:3: this_EXAMPLE_HEADING_0= RULE_EXAMPLE_HEADING ( (lv_heading_1_0= ruleExampleRow ) ) ( (lv_rows_2_0= ruleExampleRow ) )*
            {
            this_EXAMPLE_HEADING_0=(Token)match(input,RULE_EXAMPLE_HEADING,FOLLOW_11); 

            			newLeafNode(this_EXAMPLE_HEADING_0, grammarAccess.getExampleAccess().getEXAMPLE_HEADINGTerminalRuleCall_0());
            		
            // InternalFeature.g:620:3: ( (lv_heading_1_0= ruleExampleRow ) )
            // InternalFeature.g:621:4: (lv_heading_1_0= ruleExampleRow )
            {
            // InternalFeature.g:621:4: (lv_heading_1_0= ruleExampleRow )
            // InternalFeature.g:622:5: lv_heading_1_0= ruleExampleRow
            {

            					newCompositeNode(grammarAccess.getExampleAccess().getHeadingExampleRowParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_12);
            lv_heading_1_0=ruleExampleRow();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getExampleRule());
            					}
            					set(
            						current,
            						"heading",
            						lv_heading_1_0,
            						"ingraph.report.featuregrammar.Feature.ExampleRow");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFeature.g:639:3: ( (lv_rows_2_0= ruleExampleRow ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==RULE_EXAMPLE_CELL) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalFeature.g:640:4: (lv_rows_2_0= ruleExampleRow )
            	    {
            	    // InternalFeature.g:640:4: (lv_rows_2_0= ruleExampleRow )
            	    // InternalFeature.g:641:5: lv_rows_2_0= ruleExampleRow
            	    {

            	    					newCompositeNode(grammarAccess.getExampleAccess().getRowsExampleRowParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_12);
            	    lv_rows_2_0=ruleExampleRow();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getExampleRule());
            	    					}
            	    					add(
            	    						current,
            	    						"rows",
            	    						lv_rows_2_0,
            	    						"ingraph.report.featuregrammar.Feature.ExampleRow");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop13;
                }
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
    // $ANTLR end "ruleExample"


    // $ANTLR start "entryRuleExampleRow"
    // InternalFeature.g:662:1: entryRuleExampleRow returns [EObject current=null] : iv_ruleExampleRow= ruleExampleRow EOF ;
    public final EObject entryRuleExampleRow() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExampleRow = null;


        try {
            // InternalFeature.g:662:51: (iv_ruleExampleRow= ruleExampleRow EOF )
            // InternalFeature.g:663:2: iv_ruleExampleRow= ruleExampleRow EOF
            {
             newCompositeNode(grammarAccess.getExampleRowRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExampleRow=ruleExampleRow();

            state._fsp--;

             current =iv_ruleExampleRow; 
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
    // $ANTLR end "entryRuleExampleRow"


    // $ANTLR start "ruleExampleRow"
    // InternalFeature.g:669:1: ruleExampleRow returns [EObject current=null] : ( ( (lv_cells_0_0= ruleExampleCell ) )+ this_EXAMPLE_ROW_END_1= RULE_EXAMPLE_ROW_END ) ;
    public final EObject ruleExampleRow() throws RecognitionException {
        EObject current = null;

        Token this_EXAMPLE_ROW_END_1=null;
        EObject lv_cells_0_0 = null;



        	enterRule();

        try {
            // InternalFeature.g:675:2: ( ( ( (lv_cells_0_0= ruleExampleCell ) )+ this_EXAMPLE_ROW_END_1= RULE_EXAMPLE_ROW_END ) )
            // InternalFeature.g:676:2: ( ( (lv_cells_0_0= ruleExampleCell ) )+ this_EXAMPLE_ROW_END_1= RULE_EXAMPLE_ROW_END )
            {
            // InternalFeature.g:676:2: ( ( (lv_cells_0_0= ruleExampleCell ) )+ this_EXAMPLE_ROW_END_1= RULE_EXAMPLE_ROW_END )
            // InternalFeature.g:677:3: ( (lv_cells_0_0= ruleExampleCell ) )+ this_EXAMPLE_ROW_END_1= RULE_EXAMPLE_ROW_END
            {
            // InternalFeature.g:677:3: ( (lv_cells_0_0= ruleExampleCell ) )+
            int cnt14=0;
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==RULE_EXAMPLE_CELL) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalFeature.g:678:4: (lv_cells_0_0= ruleExampleCell )
            	    {
            	    // InternalFeature.g:678:4: (lv_cells_0_0= ruleExampleCell )
            	    // InternalFeature.g:679:5: lv_cells_0_0= ruleExampleCell
            	    {

            	    					newCompositeNode(grammarAccess.getExampleRowAccess().getCellsExampleCellParserRuleCall_0_0());
            	    				
            	    pushFollow(FOLLOW_13);
            	    lv_cells_0_0=ruleExampleCell();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getExampleRowRule());
            	    					}
            	    					add(
            	    						current,
            	    						"cells",
            	    						lv_cells_0_0,
            	    						"ingraph.report.featuregrammar.Feature.ExampleCell");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt14 >= 1 ) break loop14;
                        EarlyExitException eee =
                            new EarlyExitException(14, input);
                        throw eee;
                }
                cnt14++;
            } while (true);

            this_EXAMPLE_ROW_END_1=(Token)match(input,RULE_EXAMPLE_ROW_END,FOLLOW_2); 

            			newLeafNode(this_EXAMPLE_ROW_END_1, grammarAccess.getExampleRowAccess().getEXAMPLE_ROW_ENDTerminalRuleCall_1());
            		

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
    // $ANTLR end "ruleExampleRow"


    // $ANTLR start "entryRuleExampleCell"
    // InternalFeature.g:704:1: entryRuleExampleCell returns [EObject current=null] : iv_ruleExampleCell= ruleExampleCell EOF ;
    public final EObject entryRuleExampleCell() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExampleCell = null;


        try {
            // InternalFeature.g:704:52: (iv_ruleExampleCell= ruleExampleCell EOF )
            // InternalFeature.g:705:2: iv_ruleExampleCell= ruleExampleCell EOF
            {
             newCompositeNode(grammarAccess.getExampleCellRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExampleCell=ruleExampleCell();

            state._fsp--;

             current =iv_ruleExampleCell; 
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
    // $ANTLR end "entryRuleExampleCell"


    // $ANTLR start "ruleExampleCell"
    // InternalFeature.g:711:1: ruleExampleCell returns [EObject current=null] : ( (lv_value_0_0= RULE_EXAMPLE_CELL ) ) ;
    public final EObject ruleExampleCell() throws RecognitionException {
        EObject current = null;

        Token lv_value_0_0=null;


        	enterRule();

        try {
            // InternalFeature.g:717:2: ( ( (lv_value_0_0= RULE_EXAMPLE_CELL ) ) )
            // InternalFeature.g:718:2: ( (lv_value_0_0= RULE_EXAMPLE_CELL ) )
            {
            // InternalFeature.g:718:2: ( (lv_value_0_0= RULE_EXAMPLE_CELL ) )
            // InternalFeature.g:719:3: (lv_value_0_0= RULE_EXAMPLE_CELL )
            {
            // InternalFeature.g:719:3: (lv_value_0_0= RULE_EXAMPLE_CELL )
            // InternalFeature.g:720:4: lv_value_0_0= RULE_EXAMPLE_CELL
            {
            lv_value_0_0=(Token)match(input,RULE_EXAMPLE_CELL,FOLLOW_2); 

            				newLeafNode(lv_value_0_0, grammarAccess.getExampleCellAccess().getValueEXAMPLE_CELLTerminalRuleCall_0());
            			

            				if (current==null) {
            					current = createModelElement(grammarAccess.getExampleCellRule());
            				}
            				setWithLastConsumed(
            					current,
            					"value",
            					lv_value_0_0,
            					"ingraph.report.featuregrammar.Feature.EXAMPLE_CELL");
            			

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
    // $ANTLR end "ruleExampleCell"


    // $ANTLR start "entryRuleBackground"
    // InternalFeature.g:739:1: entryRuleBackground returns [EObject current=null] : iv_ruleBackground= ruleBackground EOF ;
    public final EObject entryRuleBackground() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBackground = null;


        try {
            // InternalFeature.g:739:51: (iv_ruleBackground= ruleBackground EOF )
            // InternalFeature.g:740:2: iv_ruleBackground= ruleBackground EOF
            {
             newCompositeNode(grammarAccess.getBackgroundRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBackground=ruleBackground();

            state._fsp--;

             current =iv_ruleBackground; 
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
    // $ANTLR end "entryRuleBackground"


    // $ANTLR start "ruleBackground"
    // InternalFeature.g:746:1: ruleBackground returns [EObject current=null] : ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_name_1_0= RULE_BACKGROUND_TEXT ) ) ( (lv_steps_2_0= ruleStep ) )* ) ;
    public final EObject ruleBackground() throws RecognitionException {
        EObject current = null;

        Token lv_tags_0_0=null;
        Token lv_name_1_0=null;
        EObject lv_steps_2_0 = null;



        	enterRule();

        try {
            // InternalFeature.g:752:2: ( ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_name_1_0= RULE_BACKGROUND_TEXT ) ) ( (lv_steps_2_0= ruleStep ) )* ) )
            // InternalFeature.g:753:2: ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_name_1_0= RULE_BACKGROUND_TEXT ) ) ( (lv_steps_2_0= ruleStep ) )* )
            {
            // InternalFeature.g:753:2: ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_name_1_0= RULE_BACKGROUND_TEXT ) ) ( (lv_steps_2_0= ruleStep ) )* )
            // InternalFeature.g:754:3: ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_name_1_0= RULE_BACKGROUND_TEXT ) ) ( (lv_steps_2_0= ruleStep ) )*
            {
            // InternalFeature.g:754:3: ( (lv_tags_0_0= RULE_TAG ) )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==RULE_TAG) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalFeature.g:755:4: (lv_tags_0_0= RULE_TAG )
            	    {
            	    // InternalFeature.g:755:4: (lv_tags_0_0= RULE_TAG )
            	    // InternalFeature.g:756:5: lv_tags_0_0= RULE_TAG
            	    {
            	    lv_tags_0_0=(Token)match(input,RULE_TAG,FOLLOW_14); 

            	    					newLeafNode(lv_tags_0_0, grammarAccess.getBackgroundAccess().getTagsTAGTerminalRuleCall_0_0());
            	    				

            	    					if (current==null) {
            	    						current = createModelElement(grammarAccess.getBackgroundRule());
            	    					}
            	    					addWithLastConsumed(
            	    						current,
            	    						"tags",
            	    						lv_tags_0_0,
            	    						"ingraph.report.featuregrammar.Feature.TAG");
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            // InternalFeature.g:772:3: ( (lv_name_1_0= RULE_BACKGROUND_TEXT ) )
            // InternalFeature.g:773:4: (lv_name_1_0= RULE_BACKGROUND_TEXT )
            {
            // InternalFeature.g:773:4: (lv_name_1_0= RULE_BACKGROUND_TEXT )
            // InternalFeature.g:774:5: lv_name_1_0= RULE_BACKGROUND_TEXT
            {
            lv_name_1_0=(Token)match(input,RULE_BACKGROUND_TEXT,FOLLOW_8); 

            					newLeafNode(lv_name_1_0, grammarAccess.getBackgroundAccess().getNameBACKGROUND_TEXTTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getBackgroundRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"ingraph.report.featuregrammar.Feature.BACKGROUND_TEXT");
            				

            }


            }

            // InternalFeature.g:790:3: ( (lv_steps_2_0= ruleStep ) )*
            loop16:
            do {
                int alt16=2;
                alt16 = dfa16.predict(input);
                switch (alt16) {
            	case 1 :
            	    // InternalFeature.g:791:4: (lv_steps_2_0= ruleStep )
            	    {
            	    // InternalFeature.g:791:4: (lv_steps_2_0= ruleStep )
            	    // InternalFeature.g:792:5: lv_steps_2_0= ruleStep
            	    {

            	    					newCompositeNode(grammarAccess.getBackgroundAccess().getStepsStepParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_8);
            	    lv_steps_2_0=ruleStep();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getBackgroundRule());
            	    					}
            	    					add(
            	    						current,
            	    						"steps",
            	    						lv_steps_2_0,
            	    						"ingraph.report.featuregrammar.Feature.Step");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop16;
                }
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
    // $ANTLR end "ruleBackground"


    // $ANTLR start "entryRuleStep"
    // InternalFeature.g:813:1: entryRuleStep returns [EObject current=null] : iv_ruleStep= ruleStep EOF ;
    public final EObject entryRuleStep() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStep = null;


        try {
            // InternalFeature.g:813:45: (iv_ruleStep= ruleStep EOF )
            // InternalFeature.g:814:2: iv_ruleStep= ruleStep EOF
            {
             newCompositeNode(grammarAccess.getStepRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStep=ruleStep();

            state._fsp--;

             current =iv_ruleStep; 
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
    // $ANTLR end "entryRuleStep"


    // $ANTLR start "ruleStep"
    // InternalFeature.g:820:1: ruleStep returns [EObject current=null] : (this_GivenStep_0= ruleGivenStep | this_WhenStep_1= ruleWhenStep | this_ThenStep_2= ruleThenStep | this_AndStep_3= ruleAndStep ) ;
    public final EObject ruleStep() throws RecognitionException {
        EObject current = null;

        EObject this_GivenStep_0 = null;

        EObject this_WhenStep_1 = null;

        EObject this_ThenStep_2 = null;

        EObject this_AndStep_3 = null;



        	enterRule();

        try {
            // InternalFeature.g:826:2: ( (this_GivenStep_0= ruleGivenStep | this_WhenStep_1= ruleWhenStep | this_ThenStep_2= ruleThenStep | this_AndStep_3= ruleAndStep ) )
            // InternalFeature.g:827:2: (this_GivenStep_0= ruleGivenStep | this_WhenStep_1= ruleWhenStep | this_ThenStep_2= ruleThenStep | this_AndStep_3= ruleAndStep )
            {
            // InternalFeature.g:827:2: (this_GivenStep_0= ruleGivenStep | this_WhenStep_1= ruleWhenStep | this_ThenStep_2= ruleThenStep | this_AndStep_3= ruleAndStep )
            int alt17=4;
            alt17 = dfa17.predict(input);
            switch (alt17) {
                case 1 :
                    // InternalFeature.g:828:3: this_GivenStep_0= ruleGivenStep
                    {

                    			newCompositeNode(grammarAccess.getStepAccess().getGivenStepParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_GivenStep_0=ruleGivenStep();

                    state._fsp--;


                    			current = this_GivenStep_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalFeature.g:837:3: this_WhenStep_1= ruleWhenStep
                    {

                    			newCompositeNode(grammarAccess.getStepAccess().getWhenStepParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_WhenStep_1=ruleWhenStep();

                    state._fsp--;


                    			current = this_WhenStep_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalFeature.g:846:3: this_ThenStep_2= ruleThenStep
                    {

                    			newCompositeNode(grammarAccess.getStepAccess().getThenStepParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_ThenStep_2=ruleThenStep();

                    state._fsp--;


                    			current = this_ThenStep_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalFeature.g:855:3: this_AndStep_3= ruleAndStep
                    {

                    			newCompositeNode(grammarAccess.getStepAccess().getAndStepParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_AndStep_3=ruleAndStep();

                    state._fsp--;


                    			current = this_AndStep_3;
                    			afterParserOrEnumRuleCall();
                    		

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
    // $ANTLR end "ruleStep"


    // $ANTLR start "entryRuleGivenStep"
    // InternalFeature.g:867:1: entryRuleGivenStep returns [EObject current=null] : iv_ruleGivenStep= ruleGivenStep EOF ;
    public final EObject entryRuleGivenStep() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGivenStep = null;


        try {
            // InternalFeature.g:867:50: (iv_ruleGivenStep= ruleGivenStep EOF )
            // InternalFeature.g:868:2: iv_ruleGivenStep= ruleGivenStep EOF
            {
             newCompositeNode(grammarAccess.getGivenStepRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGivenStep=ruleGivenStep();

            state._fsp--;

             current =iv_ruleGivenStep; 
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
    // $ANTLR end "entryRuleGivenStep"


    // $ANTLR start "ruleGivenStep"
    // InternalFeature.g:874:1: ruleGivenStep returns [EObject current=null] : ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_text_1_0= RULE_GIVEN_TEXT ) ) ( (lv_desc_2_0= ruleOptionalText ) ) ) ;
    public final EObject ruleGivenStep() throws RecognitionException {
        EObject current = null;

        Token lv_tags_0_0=null;
        Token lv_text_1_0=null;
        AntlrDatatypeRuleToken lv_desc_2_0 = null;



        	enterRule();

        try {
            // InternalFeature.g:880:2: ( ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_text_1_0= RULE_GIVEN_TEXT ) ) ( (lv_desc_2_0= ruleOptionalText ) ) ) )
            // InternalFeature.g:881:2: ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_text_1_0= RULE_GIVEN_TEXT ) ) ( (lv_desc_2_0= ruleOptionalText ) ) )
            {
            // InternalFeature.g:881:2: ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_text_1_0= RULE_GIVEN_TEXT ) ) ( (lv_desc_2_0= ruleOptionalText ) ) )
            // InternalFeature.g:882:3: ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_text_1_0= RULE_GIVEN_TEXT ) ) ( (lv_desc_2_0= ruleOptionalText ) )
            {
            // InternalFeature.g:882:3: ( (lv_tags_0_0= RULE_TAG ) )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==RULE_TAG) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalFeature.g:883:4: (lv_tags_0_0= RULE_TAG )
            	    {
            	    // InternalFeature.g:883:4: (lv_tags_0_0= RULE_TAG )
            	    // InternalFeature.g:884:5: lv_tags_0_0= RULE_TAG
            	    {
            	    lv_tags_0_0=(Token)match(input,RULE_TAG,FOLLOW_15); 

            	    					newLeafNode(lv_tags_0_0, grammarAccess.getGivenStepAccess().getTagsTAGTerminalRuleCall_0_0());
            	    				

            	    					if (current==null) {
            	    						current = createModelElement(grammarAccess.getGivenStepRule());
            	    					}
            	    					addWithLastConsumed(
            	    						current,
            	    						"tags",
            	    						lv_tags_0_0,
            	    						"ingraph.report.featuregrammar.Feature.TAG");
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

            // InternalFeature.g:900:3: ( (lv_text_1_0= RULE_GIVEN_TEXT ) )
            // InternalFeature.g:901:4: (lv_text_1_0= RULE_GIVEN_TEXT )
            {
            // InternalFeature.g:901:4: (lv_text_1_0= RULE_GIVEN_TEXT )
            // InternalFeature.g:902:5: lv_text_1_0= RULE_GIVEN_TEXT
            {
            lv_text_1_0=(Token)match(input,RULE_GIVEN_TEXT,FOLLOW_16); 

            					newLeafNode(lv_text_1_0, grammarAccess.getGivenStepAccess().getTextGIVEN_TEXTTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getGivenStepRule());
            					}
            					setWithLastConsumed(
            						current,
            						"text",
            						lv_text_1_0,
            						"ingraph.report.featuregrammar.Feature.GIVEN_TEXT");
            				

            }


            }

            // InternalFeature.g:918:3: ( (lv_desc_2_0= ruleOptionalText ) )
            // InternalFeature.g:919:4: (lv_desc_2_0= ruleOptionalText )
            {
            // InternalFeature.g:919:4: (lv_desc_2_0= ruleOptionalText )
            // InternalFeature.g:920:5: lv_desc_2_0= ruleOptionalText
            {

            					newCompositeNode(grammarAccess.getGivenStepAccess().getDescOptionalTextParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_desc_2_0=ruleOptionalText();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getGivenStepRule());
            					}
            					set(
            						current,
            						"desc",
            						lv_desc_2_0,
            						"ingraph.report.featuregrammar.Feature.OptionalText");
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
    // $ANTLR end "ruleGivenStep"


    // $ANTLR start "entryRuleWhenStep"
    // InternalFeature.g:941:1: entryRuleWhenStep returns [EObject current=null] : iv_ruleWhenStep= ruleWhenStep EOF ;
    public final EObject entryRuleWhenStep() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWhenStep = null;


        try {
            // InternalFeature.g:941:49: (iv_ruleWhenStep= ruleWhenStep EOF )
            // InternalFeature.g:942:2: iv_ruleWhenStep= ruleWhenStep EOF
            {
             newCompositeNode(grammarAccess.getWhenStepRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleWhenStep=ruleWhenStep();

            state._fsp--;

             current =iv_ruleWhenStep; 
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
    // $ANTLR end "entryRuleWhenStep"


    // $ANTLR start "ruleWhenStep"
    // InternalFeature.g:948:1: ruleWhenStep returns [EObject current=null] : ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_text_1_0= RULE_WHEN_TEXT ) ) ( (lv_desc_2_0= ruleOptionalText ) ) ) ;
    public final EObject ruleWhenStep() throws RecognitionException {
        EObject current = null;

        Token lv_tags_0_0=null;
        Token lv_text_1_0=null;
        AntlrDatatypeRuleToken lv_desc_2_0 = null;



        	enterRule();

        try {
            // InternalFeature.g:954:2: ( ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_text_1_0= RULE_WHEN_TEXT ) ) ( (lv_desc_2_0= ruleOptionalText ) ) ) )
            // InternalFeature.g:955:2: ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_text_1_0= RULE_WHEN_TEXT ) ) ( (lv_desc_2_0= ruleOptionalText ) ) )
            {
            // InternalFeature.g:955:2: ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_text_1_0= RULE_WHEN_TEXT ) ) ( (lv_desc_2_0= ruleOptionalText ) ) )
            // InternalFeature.g:956:3: ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_text_1_0= RULE_WHEN_TEXT ) ) ( (lv_desc_2_0= ruleOptionalText ) )
            {
            // InternalFeature.g:956:3: ( (lv_tags_0_0= RULE_TAG ) )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==RULE_TAG) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // InternalFeature.g:957:4: (lv_tags_0_0= RULE_TAG )
            	    {
            	    // InternalFeature.g:957:4: (lv_tags_0_0= RULE_TAG )
            	    // InternalFeature.g:958:5: lv_tags_0_0= RULE_TAG
            	    {
            	    lv_tags_0_0=(Token)match(input,RULE_TAG,FOLLOW_17); 

            	    					newLeafNode(lv_tags_0_0, grammarAccess.getWhenStepAccess().getTagsTAGTerminalRuleCall_0_0());
            	    				

            	    					if (current==null) {
            	    						current = createModelElement(grammarAccess.getWhenStepRule());
            	    					}
            	    					addWithLastConsumed(
            	    						current,
            	    						"tags",
            	    						lv_tags_0_0,
            	    						"ingraph.report.featuregrammar.Feature.TAG");
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);

            // InternalFeature.g:974:3: ( (lv_text_1_0= RULE_WHEN_TEXT ) )
            // InternalFeature.g:975:4: (lv_text_1_0= RULE_WHEN_TEXT )
            {
            // InternalFeature.g:975:4: (lv_text_1_0= RULE_WHEN_TEXT )
            // InternalFeature.g:976:5: lv_text_1_0= RULE_WHEN_TEXT
            {
            lv_text_1_0=(Token)match(input,RULE_WHEN_TEXT,FOLLOW_16); 

            					newLeafNode(lv_text_1_0, grammarAccess.getWhenStepAccess().getTextWHEN_TEXTTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getWhenStepRule());
            					}
            					setWithLastConsumed(
            						current,
            						"text",
            						lv_text_1_0,
            						"ingraph.report.featuregrammar.Feature.WHEN_TEXT");
            				

            }


            }

            // InternalFeature.g:992:3: ( (lv_desc_2_0= ruleOptionalText ) )
            // InternalFeature.g:993:4: (lv_desc_2_0= ruleOptionalText )
            {
            // InternalFeature.g:993:4: (lv_desc_2_0= ruleOptionalText )
            // InternalFeature.g:994:5: lv_desc_2_0= ruleOptionalText
            {

            					newCompositeNode(grammarAccess.getWhenStepAccess().getDescOptionalTextParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_desc_2_0=ruleOptionalText();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getWhenStepRule());
            					}
            					set(
            						current,
            						"desc",
            						lv_desc_2_0,
            						"ingraph.report.featuregrammar.Feature.OptionalText");
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
    // $ANTLR end "ruleWhenStep"


    // $ANTLR start "entryRuleThenStep"
    // InternalFeature.g:1015:1: entryRuleThenStep returns [EObject current=null] : iv_ruleThenStep= ruleThenStep EOF ;
    public final EObject entryRuleThenStep() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleThenStep = null;


        try {
            // InternalFeature.g:1015:49: (iv_ruleThenStep= ruleThenStep EOF )
            // InternalFeature.g:1016:2: iv_ruleThenStep= ruleThenStep EOF
            {
             newCompositeNode(grammarAccess.getThenStepRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleThenStep=ruleThenStep();

            state._fsp--;

             current =iv_ruleThenStep; 
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
    // $ANTLR end "entryRuleThenStep"


    // $ANTLR start "ruleThenStep"
    // InternalFeature.g:1022:1: ruleThenStep returns [EObject current=null] : ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_text_1_0= RULE_THEN_TEXT ) ) ( (lv_desc_2_0= ruleOptionalText ) ) ( (lv_rows_3_0= ruleExampleRow ) )* ) ;
    public final EObject ruleThenStep() throws RecognitionException {
        EObject current = null;

        Token lv_tags_0_0=null;
        Token lv_text_1_0=null;
        AntlrDatatypeRuleToken lv_desc_2_0 = null;

        EObject lv_rows_3_0 = null;



        	enterRule();

        try {
            // InternalFeature.g:1028:2: ( ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_text_1_0= RULE_THEN_TEXT ) ) ( (lv_desc_2_0= ruleOptionalText ) ) ( (lv_rows_3_0= ruleExampleRow ) )* ) )
            // InternalFeature.g:1029:2: ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_text_1_0= RULE_THEN_TEXT ) ) ( (lv_desc_2_0= ruleOptionalText ) ) ( (lv_rows_3_0= ruleExampleRow ) )* )
            {
            // InternalFeature.g:1029:2: ( ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_text_1_0= RULE_THEN_TEXT ) ) ( (lv_desc_2_0= ruleOptionalText ) ) ( (lv_rows_3_0= ruleExampleRow ) )* )
            // InternalFeature.g:1030:3: ( (lv_tags_0_0= RULE_TAG ) )* ( (lv_text_1_0= RULE_THEN_TEXT ) ) ( (lv_desc_2_0= ruleOptionalText ) ) ( (lv_rows_3_0= ruleExampleRow ) )*
            {
            // InternalFeature.g:1030:3: ( (lv_tags_0_0= RULE_TAG ) )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==RULE_TAG) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalFeature.g:1031:4: (lv_tags_0_0= RULE_TAG )
            	    {
            	    // InternalFeature.g:1031:4: (lv_tags_0_0= RULE_TAG )
            	    // InternalFeature.g:1032:5: lv_tags_0_0= RULE_TAG
            	    {
            	    lv_tags_0_0=(Token)match(input,RULE_TAG,FOLLOW_18); 

            	    					newLeafNode(lv_tags_0_0, grammarAccess.getThenStepAccess().getTagsTAGTerminalRuleCall_0_0());
            	    				

            	    					if (current==null) {
            	    						current = createModelElement(grammarAccess.getThenStepRule());
            	    					}
            	    					addWithLastConsumed(
            	    						current,
            	    						"tags",
            	    						lv_tags_0_0,
            	    						"ingraph.report.featuregrammar.Feature.TAG");
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);

            // InternalFeature.g:1048:3: ( (lv_text_1_0= RULE_THEN_TEXT ) )
            // InternalFeature.g:1049:4: (lv_text_1_0= RULE_THEN_TEXT )
            {
            // InternalFeature.g:1049:4: (lv_text_1_0= RULE_THEN_TEXT )
            // InternalFeature.g:1050:5: lv_text_1_0= RULE_THEN_TEXT
            {
            lv_text_1_0=(Token)match(input,RULE_THEN_TEXT,FOLLOW_16); 

            					newLeafNode(lv_text_1_0, grammarAccess.getThenStepAccess().getTextTHEN_TEXTTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getThenStepRule());
            					}
            					setWithLastConsumed(
            						current,
            						"text",
            						lv_text_1_0,
            						"ingraph.report.featuregrammar.Feature.THEN_TEXT");
            				

            }


            }

            // InternalFeature.g:1066:3: ( (lv_desc_2_0= ruleOptionalText ) )
            // InternalFeature.g:1067:4: (lv_desc_2_0= ruleOptionalText )
            {
            // InternalFeature.g:1067:4: (lv_desc_2_0= ruleOptionalText )
            // InternalFeature.g:1068:5: lv_desc_2_0= ruleOptionalText
            {

            					newCompositeNode(grammarAccess.getThenStepAccess().getDescOptionalTextParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_12);
            lv_desc_2_0=ruleOptionalText();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getThenStepRule());
            					}
            					set(
            						current,
            						"desc",
            						lv_desc_2_0,
            						"ingraph.report.featuregrammar.Feature.OptionalText");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalFeature.g:1085:3: ( (lv_rows_3_0= ruleExampleRow ) )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==RULE_EXAMPLE_CELL) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalFeature.g:1086:4: (lv_rows_3_0= ruleExampleRow )
            	    {
            	    // InternalFeature.g:1086:4: (lv_rows_3_0= ruleExampleRow )
            	    // InternalFeature.g:1087:5: lv_rows_3_0= ruleExampleRow
            	    {

            	    					newCompositeNode(grammarAccess.getThenStepAccess().getRowsExampleRowParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_12);
            	    lv_rows_3_0=ruleExampleRow();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getThenStepRule());
            	    					}
            	    					add(
            	    						current,
            	    						"rows",
            	    						lv_rows_3_0,
            	    						"ingraph.report.featuregrammar.Feature.ExampleRow");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop21;
                }
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
    // $ANTLR end "ruleThenStep"


    // $ANTLR start "entryRuleAndStep"
    // InternalFeature.g:1108:1: entryRuleAndStep returns [EObject current=null] : iv_ruleAndStep= ruleAndStep EOF ;
    public final EObject entryRuleAndStep() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAndStep = null;


        try {
            // InternalFeature.g:1108:48: (iv_ruleAndStep= ruleAndStep EOF )
            // InternalFeature.g:1109:2: iv_ruleAndStep= ruleAndStep EOF
            {
             newCompositeNode(grammarAccess.getAndStepRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAndStep=ruleAndStep();

            state._fsp--;

             current =iv_ruleAndStep; 
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
    // $ANTLR end "entryRuleAndStep"


    // $ANTLR start "ruleAndStep"
    // InternalFeature.g:1115:1: ruleAndStep returns [EObject current=null] : ( ( (lv_text_0_0= RULE_AND_TEXT ) ) ( (lv_desc_1_0= ruleOptionalText ) ) ) ;
    public final EObject ruleAndStep() throws RecognitionException {
        EObject current = null;

        Token lv_text_0_0=null;
        AntlrDatatypeRuleToken lv_desc_1_0 = null;



        	enterRule();

        try {
            // InternalFeature.g:1121:2: ( ( ( (lv_text_0_0= RULE_AND_TEXT ) ) ( (lv_desc_1_0= ruleOptionalText ) ) ) )
            // InternalFeature.g:1122:2: ( ( (lv_text_0_0= RULE_AND_TEXT ) ) ( (lv_desc_1_0= ruleOptionalText ) ) )
            {
            // InternalFeature.g:1122:2: ( ( (lv_text_0_0= RULE_AND_TEXT ) ) ( (lv_desc_1_0= ruleOptionalText ) ) )
            // InternalFeature.g:1123:3: ( (lv_text_0_0= RULE_AND_TEXT ) ) ( (lv_desc_1_0= ruleOptionalText ) )
            {
            // InternalFeature.g:1123:3: ( (lv_text_0_0= RULE_AND_TEXT ) )
            // InternalFeature.g:1124:4: (lv_text_0_0= RULE_AND_TEXT )
            {
            // InternalFeature.g:1124:4: (lv_text_0_0= RULE_AND_TEXT )
            // InternalFeature.g:1125:5: lv_text_0_0= RULE_AND_TEXT
            {
            lv_text_0_0=(Token)match(input,RULE_AND_TEXT,FOLLOW_16); 

            					newLeafNode(lv_text_0_0, grammarAccess.getAndStepAccess().getTextAND_TEXTTerminalRuleCall_0_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getAndStepRule());
            					}
            					setWithLastConsumed(
            						current,
            						"text",
            						lv_text_0_0,
            						"ingraph.report.featuregrammar.Feature.AND_TEXT");
            				

            }


            }

            // InternalFeature.g:1141:3: ( (lv_desc_1_0= ruleOptionalText ) )
            // InternalFeature.g:1142:4: (lv_desc_1_0= ruleOptionalText )
            {
            // InternalFeature.g:1142:4: (lv_desc_1_0= ruleOptionalText )
            // InternalFeature.g:1143:5: lv_desc_1_0= ruleOptionalText
            {

            					newCompositeNode(grammarAccess.getAndStepAccess().getDescOptionalTextParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_desc_1_0=ruleOptionalText();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAndStepRule());
            					}
            					set(
            						current,
            						"desc",
            						lv_desc_1_0,
            						"ingraph.report.featuregrammar.Feature.OptionalText");
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
    // $ANTLR end "ruleAndStep"


    // $ANTLR start "entryRuleOptionalText"
    // InternalFeature.g:1164:1: entryRuleOptionalText returns [String current=null] : iv_ruleOptionalText= ruleOptionalText EOF ;
    public final String entryRuleOptionalText() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOptionalText = null;


        try {
            // InternalFeature.g:1164:52: (iv_ruleOptionalText= ruleOptionalText EOF )
            // InternalFeature.g:1165:2: iv_ruleOptionalText= ruleOptionalText EOF
            {
             newCompositeNode(grammarAccess.getOptionalTextRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOptionalText=ruleOptionalText();

            state._fsp--;

             current =iv_ruleOptionalText.getText(); 
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
    // $ANTLR end "entryRuleOptionalText"


    // $ANTLR start "ruleOptionalText"
    // InternalFeature.g:1171:1: ruleOptionalText returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_TEXT_0= RULE_TEXT | this_CODE_1= RULE_CODE ) ;
    public final AntlrDatatypeRuleToken ruleOptionalText() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_TEXT_0=null;
        Token this_CODE_1=null;


        	enterRule();

        try {
            // InternalFeature.g:1177:2: ( (this_TEXT_0= RULE_TEXT | this_CODE_1= RULE_CODE ) )
            // InternalFeature.g:1178:2: (this_TEXT_0= RULE_TEXT | this_CODE_1= RULE_CODE )
            {
            // InternalFeature.g:1178:2: (this_TEXT_0= RULE_TEXT | this_CODE_1= RULE_CODE )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==RULE_TEXT) ) {
                alt22=1;
            }
            else if ( (LA22_0==RULE_CODE) ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // InternalFeature.g:1179:3: this_TEXT_0= RULE_TEXT
                    {
                    this_TEXT_0=(Token)match(input,RULE_TEXT,FOLLOW_2); 

                    			current.merge(this_TEXT_0);
                    		

                    			newLeafNode(this_TEXT_0, grammarAccess.getOptionalTextAccess().getTEXTTerminalRuleCall_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalFeature.g:1187:3: this_CODE_1= RULE_CODE
                    {
                    this_CODE_1=(Token)match(input,RULE_CODE,FOLLOW_2); 

                    			current.merge(this_CODE_1);
                    		

                    			newLeafNode(this_CODE_1, grammarAccess.getOptionalTextAccess().getCODETerminalRuleCall_1());
                    		

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
    // $ANTLR end "ruleOptionalText"

    // Delegated rules


    protected DFA6 dfa6 = new DFA6(this);
    protected DFA9 dfa9 = new DFA9(this);
    protected DFA16 dfa16 = new DFA16(this);
    protected DFA17 dfa17 = new DFA17(this);
    static final String dfa_1s = "\5\uffff";
    static final String dfa_2s = "\2\4\3\uffff";
    static final String dfa_3s = "\2\17\3\uffff";
    static final String dfa_4s = "\2\uffff\1\1\1\2\1\3";
    static final String dfa_5s = "\5\uffff}>";
    static final String[] dfa_6s = {
            "\1\1\5\uffff\1\3\1\4\3\uffff\1\2",
            "\1\1\5\uffff\1\3\1\4\3\uffff\1\2",
            "",
            "",
            ""
    };

    static final short[] dfa_1 = DFA.unpackEncodedString(dfa_1s);
    static final char[] dfa_2 = DFA.unpackEncodedStringToUnsignedChars(dfa_2s);
    static final char[] dfa_3 = DFA.unpackEncodedStringToUnsignedChars(dfa_3s);
    static final short[] dfa_4 = DFA.unpackEncodedString(dfa_4s);
    static final short[] dfa_5 = DFA.unpackEncodedString(dfa_5s);
    static final short[][] dfa_6 = unpackEncodedStringArray(dfa_6s);

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = dfa_1;
            this.eof = dfa_1;
            this.min = dfa_2;
            this.max = dfa_3;
            this.accept = dfa_4;
            this.special = dfa_5;
            this.transition = dfa_6;
        }
        public String getDescription() {
            return "365:2: (this_Background_0= ruleBackground | this_Scenario_1= ruleScenario | this_ScenarioWithOutline_2= ruleScenarioWithOutline )";
        }
    }
    static final String dfa_7s = "\4\uffff";
    static final String dfa_8s = "\1\1\3\uffff";
    static final String dfa_9s = "\1\4\1\uffff\1\4\1\uffff";
    static final String dfa_10s = "\1\23\1\uffff\1\22\1\uffff";
    static final String dfa_11s = "\1\uffff\1\2\1\uffff\1\1";
    static final String dfa_12s = "\4\uffff}>";
    static final String[] dfa_13s = {
            "\1\2\5\uffff\2\1\3\uffff\1\1\4\3",
            "",
            "\1\2\5\uffff\2\1\3\uffff\1\1\3\3",
            ""
    };

    static final short[] dfa_7 = DFA.unpackEncodedString(dfa_7s);
    static final short[] dfa_8 = DFA.unpackEncodedString(dfa_8s);
    static final char[] dfa_9 = DFA.unpackEncodedStringToUnsignedChars(dfa_9s);
    static final char[] dfa_10 = DFA.unpackEncodedStringToUnsignedChars(dfa_10s);
    static final short[] dfa_11 = DFA.unpackEncodedString(dfa_11s);
    static final short[] dfa_12 = DFA.unpackEncodedString(dfa_12s);
    static final short[][] dfa_13 = unpackEncodedStringArray(dfa_13s);

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = dfa_7;
            this.eof = dfa_8;
            this.min = dfa_9;
            this.max = dfa_10;
            this.accept = dfa_11;
            this.special = dfa_12;
            this.transition = dfa_13;
        }
        public String getDescription() {
            return "()+ loopback of 466:3: ( (lv_steps_3_0= ruleStep ) )+";
        }
    }

    class DFA16 extends DFA {

        public DFA16(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 16;
            this.eot = dfa_7;
            this.eof = dfa_8;
            this.min = dfa_9;
            this.max = dfa_10;
            this.accept = dfa_11;
            this.special = dfa_12;
            this.transition = dfa_13;
        }
        public String getDescription() {
            return "()* loopback of 790:3: ( (lv_steps_2_0= ruleStep ) )*";
        }
    }
    static final String dfa_14s = "\6\uffff";
    static final String dfa_15s = "\2\4\4\uffff";
    static final String dfa_16s = "\1\23\1\22\4\uffff";
    static final String dfa_17s = "\2\uffff\1\1\1\2\1\3\1\4";
    static final String dfa_18s = "\6\uffff}>";
    static final String[] dfa_19s = {
            "\1\1\13\uffff\1\2\1\3\1\4\1\5",
            "\1\1\13\uffff\1\2\1\3\1\4",
            "",
            "",
            "",
            ""
    };

    static final short[] dfa_14 = DFA.unpackEncodedString(dfa_14s);
    static final char[] dfa_15 = DFA.unpackEncodedStringToUnsignedChars(dfa_15s);
    static final char[] dfa_16 = DFA.unpackEncodedStringToUnsignedChars(dfa_16s);
    static final short[] dfa_17 = DFA.unpackEncodedString(dfa_17s);
    static final short[] dfa_18 = DFA.unpackEncodedString(dfa_18s);
    static final short[][] dfa_19 = unpackEncodedStringArray(dfa_19s);

    class DFA17 extends DFA {

        public DFA17(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 17;
            this.eot = dfa_14;
            this.eof = dfa_14;
            this.min = dfa_15;
            this.max = dfa_16;
            this.accept = dfa_17;
            this.special = dfa_18;
            this.transition = dfa_19;
        }
        public String getDescription() {
            return "827:2: (this_GivenStep_0= ruleGivenStep | this_WhenStep_1= ruleWhenStep | this_ThenStep_2= ruleThenStep | this_AndStep_3= ruleAndStep )";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000008FF2L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000008FD2L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000008C12L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000410L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x00000000000F03D0L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x00000000000F03D2L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000810L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x00000000000F13D0L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000008010L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000010010L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000000000100040L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000020010L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000040010L});

}