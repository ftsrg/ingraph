package org.xtext.example.mydsl.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import org.xtext.example.mydsl.services.MyDslGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalMyDslParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'<'", "'\\u27E8'", "'\\u3008'", "'\\uFE64'", "'\\uFF1C'", "'>'", "'\\u27E9'", "'\\u3009'", "'\\uFE65'", "'\\uFF1E'", "'-'", "'\\u00AD'", "'\\u2010'", "'\\u2011'", "'\\u2012'", "'\\u2013'", "'\\u2014'", "'\\u2015'", "'\\u2212'", "'\\uFE58'", "'\\uFE63'", "'\\uFF0D'", "'CREATE'", "'('", "':'", "')'", "'['", "']'"
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

    	public void setGrammarAccess(MyDslGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleCypherQuery"
    // InternalMyDsl.g:53:1: entryRuleCypherQuery : ruleCypherQuery EOF ;
    public final void entryRuleCypherQuery() throws RecognitionException {
        try {
            // InternalMyDsl.g:54:1: ( ruleCypherQuery EOF )
            // InternalMyDsl.g:55:1: ruleCypherQuery EOF
            {
             before(grammarAccess.getCypherQueryRule()); 
            pushFollow(FOLLOW_1);
            ruleCypherQuery();

            state._fsp--;

             after(grammarAccess.getCypherQueryRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCypherQuery"


    // $ANTLR start "ruleCypherQuery"
    // InternalMyDsl.g:62:1: ruleCypherQuery : ( ( ( rule__CypherQuery__QueriesAssignment ) ) ( ( rule__CypherQuery__QueriesAssignment )* ) ) ;
    public final void ruleCypherQuery() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:66:2: ( ( ( ( rule__CypherQuery__QueriesAssignment ) ) ( ( rule__CypherQuery__QueriesAssignment )* ) ) )
            // InternalMyDsl.g:67:2: ( ( ( rule__CypherQuery__QueriesAssignment ) ) ( ( rule__CypherQuery__QueriesAssignment )* ) )
            {
            // InternalMyDsl.g:67:2: ( ( ( rule__CypherQuery__QueriesAssignment ) ) ( ( rule__CypherQuery__QueriesAssignment )* ) )
            // InternalMyDsl.g:68:3: ( ( rule__CypherQuery__QueriesAssignment ) ) ( ( rule__CypherQuery__QueriesAssignment )* )
            {
            // InternalMyDsl.g:68:3: ( ( rule__CypherQuery__QueriesAssignment ) )
            // InternalMyDsl.g:69:4: ( rule__CypherQuery__QueriesAssignment )
            {
             before(grammarAccess.getCypherQueryAccess().getQueriesAssignment()); 
            // InternalMyDsl.g:70:4: ( rule__CypherQuery__QueriesAssignment )
            // InternalMyDsl.g:70:5: rule__CypherQuery__QueriesAssignment
            {
            pushFollow(FOLLOW_3);
            rule__CypherQuery__QueriesAssignment();

            state._fsp--;


            }

             after(grammarAccess.getCypherQueryAccess().getQueriesAssignment()); 

            }

            // InternalMyDsl.g:73:3: ( ( rule__CypherQuery__QueriesAssignment )* )
            // InternalMyDsl.g:74:4: ( rule__CypherQuery__QueriesAssignment )*
            {
             before(grammarAccess.getCypherQueryAccess().getQueriesAssignment()); 
            // InternalMyDsl.g:75:4: ( rule__CypherQuery__QueriesAssignment )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==33) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalMyDsl.g:75:5: rule__CypherQuery__QueriesAssignment
            	    {
            	    pushFollow(FOLLOW_3);
            	    rule__CypherQuery__QueriesAssignment();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

             after(grammarAccess.getCypherQueryAccess().getQueriesAssignment()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCypherQuery"


    // $ANTLR start "entryRuleQuery"
    // InternalMyDsl.g:85:1: entryRuleQuery : ruleQuery EOF ;
    public final void entryRuleQuery() throws RecognitionException {
        try {
            // InternalMyDsl.g:86:1: ( ruleQuery EOF )
            // InternalMyDsl.g:87:1: ruleQuery EOF
            {
             before(grammarAccess.getQueryRule()); 
            pushFollow(FOLLOW_1);
            ruleQuery();

            state._fsp--;

             after(grammarAccess.getQueryRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleQuery"


    // $ANTLR start "ruleQuery"
    // InternalMyDsl.g:94:1: ruleQuery : ( ( rule__Query__Group__0 ) ) ;
    public final void ruleQuery() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:98:2: ( ( ( rule__Query__Group__0 ) ) )
            // InternalMyDsl.g:99:2: ( ( rule__Query__Group__0 ) )
            {
            // InternalMyDsl.g:99:2: ( ( rule__Query__Group__0 ) )
            // InternalMyDsl.g:100:3: ( rule__Query__Group__0 )
            {
             before(grammarAccess.getQueryAccess().getGroup()); 
            // InternalMyDsl.g:101:3: ( rule__Query__Group__0 )
            // InternalMyDsl.g:101:4: rule__Query__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Query__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getQueryAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleQuery"


    // $ANTLR start "entryRulePattern"
    // InternalMyDsl.g:110:1: entryRulePattern : rulePattern EOF ;
    public final void entryRulePattern() throws RecognitionException {
        try {
            // InternalMyDsl.g:111:1: ( rulePattern EOF )
            // InternalMyDsl.g:112:1: rulePattern EOF
            {
             before(grammarAccess.getPatternRule()); 
            pushFollow(FOLLOW_1);
            rulePattern();

            state._fsp--;

             after(grammarAccess.getPatternRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePattern"


    // $ANTLR start "rulePattern"
    // InternalMyDsl.g:119:1: rulePattern : ( ( ( rule__Pattern__PartsAssignment ) ) ( ( rule__Pattern__PartsAssignment )* ) ) ;
    public final void rulePattern() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:123:2: ( ( ( ( rule__Pattern__PartsAssignment ) ) ( ( rule__Pattern__PartsAssignment )* ) ) )
            // InternalMyDsl.g:124:2: ( ( ( rule__Pattern__PartsAssignment ) ) ( ( rule__Pattern__PartsAssignment )* ) )
            {
            // InternalMyDsl.g:124:2: ( ( ( rule__Pattern__PartsAssignment ) ) ( ( rule__Pattern__PartsAssignment )* ) )
            // InternalMyDsl.g:125:3: ( ( rule__Pattern__PartsAssignment ) ) ( ( rule__Pattern__PartsAssignment )* )
            {
            // InternalMyDsl.g:125:3: ( ( rule__Pattern__PartsAssignment ) )
            // InternalMyDsl.g:126:4: ( rule__Pattern__PartsAssignment )
            {
             before(grammarAccess.getPatternAccess().getPartsAssignment()); 
            // InternalMyDsl.g:127:4: ( rule__Pattern__PartsAssignment )
            // InternalMyDsl.g:127:5: rule__Pattern__PartsAssignment
            {
            pushFollow(FOLLOW_4);
            rule__Pattern__PartsAssignment();

            state._fsp--;


            }

             after(grammarAccess.getPatternAccess().getPartsAssignment()); 

            }

            // InternalMyDsl.g:130:3: ( ( rule__Pattern__PartsAssignment )* )
            // InternalMyDsl.g:131:4: ( rule__Pattern__PartsAssignment )*
            {
             before(grammarAccess.getPatternAccess().getPartsAssignment()); 
            // InternalMyDsl.g:132:4: ( rule__Pattern__PartsAssignment )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==34) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalMyDsl.g:132:5: rule__Pattern__PartsAssignment
            	    {
            	    pushFollow(FOLLOW_4);
            	    rule__Pattern__PartsAssignment();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

             after(grammarAccess.getPatternAccess().getPartsAssignment()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePattern"


    // $ANTLR start "entryRulePatternPart"
    // InternalMyDsl.g:142:1: entryRulePatternPart : rulePatternPart EOF ;
    public final void entryRulePatternPart() throws RecognitionException {
        try {
            // InternalMyDsl.g:143:1: ( rulePatternPart EOF )
            // InternalMyDsl.g:144:1: rulePatternPart EOF
            {
             before(grammarAccess.getPatternPartRule()); 
            pushFollow(FOLLOW_1);
            rulePatternPart();

            state._fsp--;

             after(grammarAccess.getPatternPartRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePatternPart"


    // $ANTLR start "rulePatternPart"
    // InternalMyDsl.g:151:1: rulePatternPart : ( ( rule__PatternPart__Group__0 ) ) ;
    public final void rulePatternPart() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:155:2: ( ( ( rule__PatternPart__Group__0 ) ) )
            // InternalMyDsl.g:156:2: ( ( rule__PatternPart__Group__0 ) )
            {
            // InternalMyDsl.g:156:2: ( ( rule__PatternPart__Group__0 ) )
            // InternalMyDsl.g:157:3: ( rule__PatternPart__Group__0 )
            {
             before(grammarAccess.getPatternPartAccess().getGroup()); 
            // InternalMyDsl.g:158:3: ( rule__PatternPart__Group__0 )
            // InternalMyDsl.g:158:4: rule__PatternPart__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__PatternPart__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPatternPartAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePatternPart"


    // $ANTLR start "entryRuleNodePattern"
    // InternalMyDsl.g:167:1: entryRuleNodePattern : ruleNodePattern EOF ;
    public final void entryRuleNodePattern() throws RecognitionException {
        try {
            // InternalMyDsl.g:168:1: ( ruleNodePattern EOF )
            // InternalMyDsl.g:169:1: ruleNodePattern EOF
            {
             before(grammarAccess.getNodePatternRule()); 
            pushFollow(FOLLOW_1);
            ruleNodePattern();

            state._fsp--;

             after(grammarAccess.getNodePatternRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleNodePattern"


    // $ANTLR start "ruleNodePattern"
    // InternalMyDsl.g:176:1: ruleNodePattern : ( ( rule__NodePattern__Group__0 ) ) ;
    public final void ruleNodePattern() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:180:2: ( ( ( rule__NodePattern__Group__0 ) ) )
            // InternalMyDsl.g:181:2: ( ( rule__NodePattern__Group__0 ) )
            {
            // InternalMyDsl.g:181:2: ( ( rule__NodePattern__Group__0 ) )
            // InternalMyDsl.g:182:3: ( rule__NodePattern__Group__0 )
            {
             before(grammarAccess.getNodePatternAccess().getGroup()); 
            // InternalMyDsl.g:183:3: ( rule__NodePattern__Group__0 )
            // InternalMyDsl.g:183:4: rule__NodePattern__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__NodePattern__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getNodePatternAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleNodePattern"


    // $ANTLR start "entryRulePatternElementChain"
    // InternalMyDsl.g:192:1: entryRulePatternElementChain : rulePatternElementChain EOF ;
    public final void entryRulePatternElementChain() throws RecognitionException {
        try {
            // InternalMyDsl.g:193:1: ( rulePatternElementChain EOF )
            // InternalMyDsl.g:194:1: rulePatternElementChain EOF
            {
             before(grammarAccess.getPatternElementChainRule()); 
            pushFollow(FOLLOW_1);
            rulePatternElementChain();

            state._fsp--;

             after(grammarAccess.getPatternElementChainRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePatternElementChain"


    // $ANTLR start "rulePatternElementChain"
    // InternalMyDsl.g:201:1: rulePatternElementChain : ( ( rule__PatternElementChain__Group__0 ) ) ;
    public final void rulePatternElementChain() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:205:2: ( ( ( rule__PatternElementChain__Group__0 ) ) )
            // InternalMyDsl.g:206:2: ( ( rule__PatternElementChain__Group__0 ) )
            {
            // InternalMyDsl.g:206:2: ( ( rule__PatternElementChain__Group__0 ) )
            // InternalMyDsl.g:207:3: ( rule__PatternElementChain__Group__0 )
            {
             before(grammarAccess.getPatternElementChainAccess().getGroup()); 
            // InternalMyDsl.g:208:3: ( rule__PatternElementChain__Group__0 )
            // InternalMyDsl.g:208:4: rule__PatternElementChain__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__PatternElementChain__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPatternElementChainAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePatternElementChain"


    // $ANTLR start "entryRuleRelationshipPattern"
    // InternalMyDsl.g:217:1: entryRuleRelationshipPattern : ruleRelationshipPattern EOF ;
    public final void entryRuleRelationshipPattern() throws RecognitionException {
        try {
            // InternalMyDsl.g:218:1: ( ruleRelationshipPattern EOF )
            // InternalMyDsl.g:219:1: ruleRelationshipPattern EOF
            {
             before(grammarAccess.getRelationshipPatternRule()); 
            pushFollow(FOLLOW_1);
            ruleRelationshipPattern();

            state._fsp--;

             after(grammarAccess.getRelationshipPatternRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleRelationshipPattern"


    // $ANTLR start "ruleRelationshipPattern"
    // InternalMyDsl.g:226:1: ruleRelationshipPattern : ( ( rule__RelationshipPattern__Alternatives ) ) ;
    public final void ruleRelationshipPattern() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:230:2: ( ( ( rule__RelationshipPattern__Alternatives ) ) )
            // InternalMyDsl.g:231:2: ( ( rule__RelationshipPattern__Alternatives ) )
            {
            // InternalMyDsl.g:231:2: ( ( rule__RelationshipPattern__Alternatives ) )
            // InternalMyDsl.g:232:3: ( rule__RelationshipPattern__Alternatives )
            {
             before(grammarAccess.getRelationshipPatternAccess().getAlternatives()); 
            // InternalMyDsl.g:233:3: ( rule__RelationshipPattern__Alternatives )
            // InternalMyDsl.g:233:4: rule__RelationshipPattern__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__RelationshipPattern__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getRelationshipPatternAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleRelationshipPattern"


    // $ANTLR start "entryRuleRelationshipDetail"
    // InternalMyDsl.g:242:1: entryRuleRelationshipDetail : ruleRelationshipDetail EOF ;
    public final void entryRuleRelationshipDetail() throws RecognitionException {
        try {
            // InternalMyDsl.g:243:1: ( ruleRelationshipDetail EOF )
            // InternalMyDsl.g:244:1: ruleRelationshipDetail EOF
            {
             before(grammarAccess.getRelationshipDetailRule()); 
            pushFollow(FOLLOW_1);
            ruleRelationshipDetail();

            state._fsp--;

             after(grammarAccess.getRelationshipDetailRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleRelationshipDetail"


    // $ANTLR start "ruleRelationshipDetail"
    // InternalMyDsl.g:251:1: ruleRelationshipDetail : ( ( rule__RelationshipDetail__Group__0 ) ) ;
    public final void ruleRelationshipDetail() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:255:2: ( ( ( rule__RelationshipDetail__Group__0 ) ) )
            // InternalMyDsl.g:256:2: ( ( rule__RelationshipDetail__Group__0 ) )
            {
            // InternalMyDsl.g:256:2: ( ( rule__RelationshipDetail__Group__0 ) )
            // InternalMyDsl.g:257:3: ( rule__RelationshipDetail__Group__0 )
            {
             before(grammarAccess.getRelationshipDetailAccess().getGroup()); 
            // InternalMyDsl.g:258:3: ( rule__RelationshipDetail__Group__0 )
            // InternalMyDsl.g:258:4: rule__RelationshipDetail__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__RelationshipDetail__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getRelationshipDetailAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleRelationshipDetail"


    // $ANTLR start "entryRuleVariable"
    // InternalMyDsl.g:267:1: entryRuleVariable : ruleVariable EOF ;
    public final void entryRuleVariable() throws RecognitionException {
        try {
            // InternalMyDsl.g:268:1: ( ruleVariable EOF )
            // InternalMyDsl.g:269:1: ruleVariable EOF
            {
             before(grammarAccess.getVariableRule()); 
            pushFollow(FOLLOW_1);
            ruleVariable();

            state._fsp--;

             after(grammarAccess.getVariableRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleVariable"


    // $ANTLR start "ruleVariable"
    // InternalMyDsl.g:276:1: ruleVariable : ( ( rule__Variable__NameAssignment ) ) ;
    public final void ruleVariable() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:280:2: ( ( ( rule__Variable__NameAssignment ) ) )
            // InternalMyDsl.g:281:2: ( ( rule__Variable__NameAssignment ) )
            {
            // InternalMyDsl.g:281:2: ( ( rule__Variable__NameAssignment ) )
            // InternalMyDsl.g:282:3: ( rule__Variable__NameAssignment )
            {
             before(grammarAccess.getVariableAccess().getNameAssignment()); 
            // InternalMyDsl.g:283:3: ( rule__Variable__NameAssignment )
            // InternalMyDsl.g:283:4: rule__Variable__NameAssignment
            {
            pushFollow(FOLLOW_2);
            rule__Variable__NameAssignment();

            state._fsp--;


            }

             after(grammarAccess.getVariableAccess().getNameAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleVariable"


    // $ANTLR start "entryRuleLabel"
    // InternalMyDsl.g:292:1: entryRuleLabel : ruleLabel EOF ;
    public final void entryRuleLabel() throws RecognitionException {
        try {
            // InternalMyDsl.g:293:1: ( ruleLabel EOF )
            // InternalMyDsl.g:294:1: ruleLabel EOF
            {
             before(grammarAccess.getLabelRule()); 
            pushFollow(FOLLOW_1);
            ruleLabel();

            state._fsp--;

             after(grammarAccess.getLabelRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleLabel"


    // $ANTLR start "ruleLabel"
    // InternalMyDsl.g:301:1: ruleLabel : ( ( rule__Label__NameAssignment ) ) ;
    public final void ruleLabel() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:305:2: ( ( ( rule__Label__NameAssignment ) ) )
            // InternalMyDsl.g:306:2: ( ( rule__Label__NameAssignment ) )
            {
            // InternalMyDsl.g:306:2: ( ( rule__Label__NameAssignment ) )
            // InternalMyDsl.g:307:3: ( rule__Label__NameAssignment )
            {
             before(grammarAccess.getLabelAccess().getNameAssignment()); 
            // InternalMyDsl.g:308:3: ( rule__Label__NameAssignment )
            // InternalMyDsl.g:308:4: rule__Label__NameAssignment
            {
            pushFollow(FOLLOW_2);
            rule__Label__NameAssignment();

            state._fsp--;


            }

             after(grammarAccess.getLabelAccess().getNameAssignment()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleLabel"


    // $ANTLR start "entryRuleleftArrowHead"
    // InternalMyDsl.g:317:1: entryRuleleftArrowHead : ruleleftArrowHead EOF ;
    public final void entryRuleleftArrowHead() throws RecognitionException {
        try {
            // InternalMyDsl.g:318:1: ( ruleleftArrowHead EOF )
            // InternalMyDsl.g:319:1: ruleleftArrowHead EOF
            {
             before(grammarAccess.getLeftArrowHeadRule()); 
            pushFollow(FOLLOW_1);
            ruleleftArrowHead();

            state._fsp--;

             after(grammarAccess.getLeftArrowHeadRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleleftArrowHead"


    // $ANTLR start "ruleleftArrowHead"
    // InternalMyDsl.g:326:1: ruleleftArrowHead : ( ( rule__LeftArrowHead__Alternatives ) ) ;
    public final void ruleleftArrowHead() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:330:2: ( ( ( rule__LeftArrowHead__Alternatives ) ) )
            // InternalMyDsl.g:331:2: ( ( rule__LeftArrowHead__Alternatives ) )
            {
            // InternalMyDsl.g:331:2: ( ( rule__LeftArrowHead__Alternatives ) )
            // InternalMyDsl.g:332:3: ( rule__LeftArrowHead__Alternatives )
            {
             before(grammarAccess.getLeftArrowHeadAccess().getAlternatives()); 
            // InternalMyDsl.g:333:3: ( rule__LeftArrowHead__Alternatives )
            // InternalMyDsl.g:333:4: rule__LeftArrowHead__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__LeftArrowHead__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getLeftArrowHeadAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleleftArrowHead"


    // $ANTLR start "entryRulerightArrowHead"
    // InternalMyDsl.g:342:1: entryRulerightArrowHead : rulerightArrowHead EOF ;
    public final void entryRulerightArrowHead() throws RecognitionException {
        try {
            // InternalMyDsl.g:343:1: ( rulerightArrowHead EOF )
            // InternalMyDsl.g:344:1: rulerightArrowHead EOF
            {
             before(grammarAccess.getRightArrowHeadRule()); 
            pushFollow(FOLLOW_1);
            rulerightArrowHead();

            state._fsp--;

             after(grammarAccess.getRightArrowHeadRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulerightArrowHead"


    // $ANTLR start "rulerightArrowHead"
    // InternalMyDsl.g:351:1: rulerightArrowHead : ( ( rule__RightArrowHead__Alternatives ) ) ;
    public final void rulerightArrowHead() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:355:2: ( ( ( rule__RightArrowHead__Alternatives ) ) )
            // InternalMyDsl.g:356:2: ( ( rule__RightArrowHead__Alternatives ) )
            {
            // InternalMyDsl.g:356:2: ( ( rule__RightArrowHead__Alternatives ) )
            // InternalMyDsl.g:357:3: ( rule__RightArrowHead__Alternatives )
            {
             before(grammarAccess.getRightArrowHeadAccess().getAlternatives()); 
            // InternalMyDsl.g:358:3: ( rule__RightArrowHead__Alternatives )
            // InternalMyDsl.g:358:4: rule__RightArrowHead__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__RightArrowHead__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getRightArrowHeadAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulerightArrowHead"


    // $ANTLR start "entryRuledash"
    // InternalMyDsl.g:367:1: entryRuledash : ruledash EOF ;
    public final void entryRuledash() throws RecognitionException {
        try {
            // InternalMyDsl.g:368:1: ( ruledash EOF )
            // InternalMyDsl.g:369:1: ruledash EOF
            {
             before(grammarAccess.getDashRule()); 
            pushFollow(FOLLOW_1);
            ruledash();

            state._fsp--;

             after(grammarAccess.getDashRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuledash"


    // $ANTLR start "ruledash"
    // InternalMyDsl.g:376:1: ruledash : ( ( rule__Dash__Alternatives ) ) ;
    public final void ruledash() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:380:2: ( ( ( rule__Dash__Alternatives ) ) )
            // InternalMyDsl.g:381:2: ( ( rule__Dash__Alternatives ) )
            {
            // InternalMyDsl.g:381:2: ( ( rule__Dash__Alternatives ) )
            // InternalMyDsl.g:382:3: ( rule__Dash__Alternatives )
            {
             before(grammarAccess.getDashAccess().getAlternatives()); 
            // InternalMyDsl.g:383:3: ( rule__Dash__Alternatives )
            // InternalMyDsl.g:383:4: rule__Dash__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Dash__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getDashAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruledash"


    // $ANTLR start "rule__RelationshipPattern__Alternatives"
    // InternalMyDsl.g:391:1: rule__RelationshipPattern__Alternatives : ( ( ( rule__RelationshipPattern__Group_0__0 ) ) | ( ( rule__RelationshipPattern__Group_1__0 ) ) | ( ( rule__RelationshipPattern__Group_2__0 ) ) | ( ( rule__RelationshipPattern__Group_3__0 ) ) );
    public final void rule__RelationshipPattern__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:395:1: ( ( ( rule__RelationshipPattern__Group_0__0 ) ) | ( ( rule__RelationshipPattern__Group_1__0 ) ) | ( ( rule__RelationshipPattern__Group_2__0 ) ) | ( ( rule__RelationshipPattern__Group_3__0 ) ) )
            int alt3=4;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // InternalMyDsl.g:396:2: ( ( rule__RelationshipPattern__Group_0__0 ) )
                    {
                    // InternalMyDsl.g:396:2: ( ( rule__RelationshipPattern__Group_0__0 ) )
                    // InternalMyDsl.g:397:3: ( rule__RelationshipPattern__Group_0__0 )
                    {
                     before(grammarAccess.getRelationshipPatternAccess().getGroup_0()); 
                    // InternalMyDsl.g:398:3: ( rule__RelationshipPattern__Group_0__0 )
                    // InternalMyDsl.g:398:4: rule__RelationshipPattern__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__RelationshipPattern__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getRelationshipPatternAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:402:2: ( ( rule__RelationshipPattern__Group_1__0 ) )
                    {
                    // InternalMyDsl.g:402:2: ( ( rule__RelationshipPattern__Group_1__0 ) )
                    // InternalMyDsl.g:403:3: ( rule__RelationshipPattern__Group_1__0 )
                    {
                     before(grammarAccess.getRelationshipPatternAccess().getGroup_1()); 
                    // InternalMyDsl.g:404:3: ( rule__RelationshipPattern__Group_1__0 )
                    // InternalMyDsl.g:404:4: rule__RelationshipPattern__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__RelationshipPattern__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getRelationshipPatternAccess().getGroup_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalMyDsl.g:408:2: ( ( rule__RelationshipPattern__Group_2__0 ) )
                    {
                    // InternalMyDsl.g:408:2: ( ( rule__RelationshipPattern__Group_2__0 ) )
                    // InternalMyDsl.g:409:3: ( rule__RelationshipPattern__Group_2__0 )
                    {
                     before(grammarAccess.getRelationshipPatternAccess().getGroup_2()); 
                    // InternalMyDsl.g:410:3: ( rule__RelationshipPattern__Group_2__0 )
                    // InternalMyDsl.g:410:4: rule__RelationshipPattern__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__RelationshipPattern__Group_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getRelationshipPatternAccess().getGroup_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalMyDsl.g:414:2: ( ( rule__RelationshipPattern__Group_3__0 ) )
                    {
                    // InternalMyDsl.g:414:2: ( ( rule__RelationshipPattern__Group_3__0 ) )
                    // InternalMyDsl.g:415:3: ( rule__RelationshipPattern__Group_3__0 )
                    {
                     before(grammarAccess.getRelationshipPatternAccess().getGroup_3()); 
                    // InternalMyDsl.g:416:3: ( rule__RelationshipPattern__Group_3__0 )
                    // InternalMyDsl.g:416:4: rule__RelationshipPattern__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__RelationshipPattern__Group_3__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getRelationshipPatternAccess().getGroup_3()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Alternatives"


    // $ANTLR start "rule__LeftArrowHead__Alternatives"
    // InternalMyDsl.g:424:1: rule__LeftArrowHead__Alternatives : ( ( '<' ) | ( '\\u27E8' ) | ( '\\u3008' ) | ( '\\uFE64' ) | ( '\\uFF1C' ) );
    public final void rule__LeftArrowHead__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:428:1: ( ( '<' ) | ( '\\u27E8' ) | ( '\\u3008' ) | ( '\\uFE64' ) | ( '\\uFF1C' ) )
            int alt4=5;
            switch ( input.LA(1) ) {
            case 11:
                {
                alt4=1;
                }
                break;
            case 12:
                {
                alt4=2;
                }
                break;
            case 13:
                {
                alt4=3;
                }
                break;
            case 14:
                {
                alt4=4;
                }
                break;
            case 15:
                {
                alt4=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // InternalMyDsl.g:429:2: ( '<' )
                    {
                    // InternalMyDsl.g:429:2: ( '<' )
                    // InternalMyDsl.g:430:3: '<'
                    {
                     before(grammarAccess.getLeftArrowHeadAccess().getLessThanSignKeyword_0()); 
                    match(input,11,FOLLOW_2); 
                     after(grammarAccess.getLeftArrowHeadAccess().getLessThanSignKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:435:2: ( '\\u27E8' )
                    {
                    // InternalMyDsl.g:435:2: ( '\\u27E8' )
                    // InternalMyDsl.g:436:3: '\\u27E8'
                    {
                     before(grammarAccess.getLeftArrowHeadAccess().getMathematicalLeftAngleBracketKeyword_1()); 
                    match(input,12,FOLLOW_2); 
                     after(grammarAccess.getLeftArrowHeadAccess().getMathematicalLeftAngleBracketKeyword_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalMyDsl.g:441:2: ( '\\u3008' )
                    {
                    // InternalMyDsl.g:441:2: ( '\\u3008' )
                    // InternalMyDsl.g:442:3: '\\u3008'
                    {
                     before(grammarAccess.getLeftArrowHeadAccess().getLeftAngleBracketKeyword_2()); 
                    match(input,13,FOLLOW_2); 
                     after(grammarAccess.getLeftArrowHeadAccess().getLeftAngleBracketKeyword_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalMyDsl.g:447:2: ( '\\uFE64' )
                    {
                    // InternalMyDsl.g:447:2: ( '\\uFE64' )
                    // InternalMyDsl.g:448:3: '\\uFE64'
                    {
                     before(grammarAccess.getLeftArrowHeadAccess().getSmallLessThanSignKeyword_3()); 
                    match(input,14,FOLLOW_2); 
                     after(grammarAccess.getLeftArrowHeadAccess().getSmallLessThanSignKeyword_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalMyDsl.g:453:2: ( '\\uFF1C' )
                    {
                    // InternalMyDsl.g:453:2: ( '\\uFF1C' )
                    // InternalMyDsl.g:454:3: '\\uFF1C'
                    {
                     before(grammarAccess.getLeftArrowHeadAccess().getFullwidthLessThanSignKeyword_4()); 
                    match(input,15,FOLLOW_2); 
                     after(grammarAccess.getLeftArrowHeadAccess().getFullwidthLessThanSignKeyword_4()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__LeftArrowHead__Alternatives"


    // $ANTLR start "rule__RightArrowHead__Alternatives"
    // InternalMyDsl.g:463:1: rule__RightArrowHead__Alternatives : ( ( '>' ) | ( '\\u27E9' ) | ( '\\u3009' ) | ( '\\uFE65' ) | ( '\\uFF1E' ) );
    public final void rule__RightArrowHead__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:467:1: ( ( '>' ) | ( '\\u27E9' ) | ( '\\u3009' ) | ( '\\uFE65' ) | ( '\\uFF1E' ) )
            int alt5=5;
            switch ( input.LA(1) ) {
            case 16:
                {
                alt5=1;
                }
                break;
            case 17:
                {
                alt5=2;
                }
                break;
            case 18:
                {
                alt5=3;
                }
                break;
            case 19:
                {
                alt5=4;
                }
                break;
            case 20:
                {
                alt5=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // InternalMyDsl.g:468:2: ( '>' )
                    {
                    // InternalMyDsl.g:468:2: ( '>' )
                    // InternalMyDsl.g:469:3: '>'
                    {
                     before(grammarAccess.getRightArrowHeadAccess().getGreaterThanSignKeyword_0()); 
                    match(input,16,FOLLOW_2); 
                     after(grammarAccess.getRightArrowHeadAccess().getGreaterThanSignKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:474:2: ( '\\u27E9' )
                    {
                    // InternalMyDsl.g:474:2: ( '\\u27E9' )
                    // InternalMyDsl.g:475:3: '\\u27E9'
                    {
                     before(grammarAccess.getRightArrowHeadAccess().getMathematicalRightAngleBracketKeyword_1()); 
                    match(input,17,FOLLOW_2); 
                     after(grammarAccess.getRightArrowHeadAccess().getMathematicalRightAngleBracketKeyword_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalMyDsl.g:480:2: ( '\\u3009' )
                    {
                    // InternalMyDsl.g:480:2: ( '\\u3009' )
                    // InternalMyDsl.g:481:3: '\\u3009'
                    {
                     before(grammarAccess.getRightArrowHeadAccess().getRightAngleBracketKeyword_2()); 
                    match(input,18,FOLLOW_2); 
                     after(grammarAccess.getRightArrowHeadAccess().getRightAngleBracketKeyword_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalMyDsl.g:486:2: ( '\\uFE65' )
                    {
                    // InternalMyDsl.g:486:2: ( '\\uFE65' )
                    // InternalMyDsl.g:487:3: '\\uFE65'
                    {
                     before(grammarAccess.getRightArrowHeadAccess().getSmallGreaterThanSignKeyword_3()); 
                    match(input,19,FOLLOW_2); 
                     after(grammarAccess.getRightArrowHeadAccess().getSmallGreaterThanSignKeyword_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalMyDsl.g:492:2: ( '\\uFF1E' )
                    {
                    // InternalMyDsl.g:492:2: ( '\\uFF1E' )
                    // InternalMyDsl.g:493:3: '\\uFF1E'
                    {
                     before(grammarAccess.getRightArrowHeadAccess().getFullwidthGreaterThanSignKeyword_4()); 
                    match(input,20,FOLLOW_2); 
                     after(grammarAccess.getRightArrowHeadAccess().getFullwidthGreaterThanSignKeyword_4()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RightArrowHead__Alternatives"


    // $ANTLR start "rule__Dash__Alternatives"
    // InternalMyDsl.g:502:1: rule__Dash__Alternatives : ( ( '-' ) | ( '\\u00AD' ) | ( '\\u2010' ) | ( '\\u2011' ) | ( '\\u2012' ) | ( '\\u2013' ) | ( '\\u2014' ) | ( '\\u2015' ) | ( '\\u2212' ) | ( '\\uFE58' ) | ( '\\uFE63' ) | ( '\\uFF0D' ) );
    public final void rule__Dash__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:506:1: ( ( '-' ) | ( '\\u00AD' ) | ( '\\u2010' ) | ( '\\u2011' ) | ( '\\u2012' ) | ( '\\u2013' ) | ( '\\u2014' ) | ( '\\u2015' ) | ( '\\u2212' ) | ( '\\uFE58' ) | ( '\\uFE63' ) | ( '\\uFF0D' ) )
            int alt6=12;
            switch ( input.LA(1) ) {
            case 21:
                {
                alt6=1;
                }
                break;
            case 22:
                {
                alt6=2;
                }
                break;
            case 23:
                {
                alt6=3;
                }
                break;
            case 24:
                {
                alt6=4;
                }
                break;
            case 25:
                {
                alt6=5;
                }
                break;
            case 26:
                {
                alt6=6;
                }
                break;
            case 27:
                {
                alt6=7;
                }
                break;
            case 28:
                {
                alt6=8;
                }
                break;
            case 29:
                {
                alt6=9;
                }
                break;
            case 30:
                {
                alt6=10;
                }
                break;
            case 31:
                {
                alt6=11;
                }
                break;
            case 32:
                {
                alt6=12;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // InternalMyDsl.g:507:2: ( '-' )
                    {
                    // InternalMyDsl.g:507:2: ( '-' )
                    // InternalMyDsl.g:508:3: '-'
                    {
                     before(grammarAccess.getDashAccess().getHyphenMinusKeyword_0()); 
                    match(input,21,FOLLOW_2); 
                     after(grammarAccess.getDashAccess().getHyphenMinusKeyword_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:513:2: ( '\\u00AD' )
                    {
                    // InternalMyDsl.g:513:2: ( '\\u00AD' )
                    // InternalMyDsl.g:514:3: '\\u00AD'
                    {
                     before(grammarAccess.getDashAccess().getSoftHyphenKeyword_1()); 
                    match(input,22,FOLLOW_2); 
                     after(grammarAccess.getDashAccess().getSoftHyphenKeyword_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalMyDsl.g:519:2: ( '\\u2010' )
                    {
                    // InternalMyDsl.g:519:2: ( '\\u2010' )
                    // InternalMyDsl.g:520:3: '\\u2010'
                    {
                     before(grammarAccess.getDashAccess().getHyphenKeyword_2()); 
                    match(input,23,FOLLOW_2); 
                     after(grammarAccess.getDashAccess().getHyphenKeyword_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalMyDsl.g:525:2: ( '\\u2011' )
                    {
                    // InternalMyDsl.g:525:2: ( '\\u2011' )
                    // InternalMyDsl.g:526:3: '\\u2011'
                    {
                     before(grammarAccess.getDashAccess().getNonBreakingHyphenKeyword_3()); 
                    match(input,24,FOLLOW_2); 
                     after(grammarAccess.getDashAccess().getNonBreakingHyphenKeyword_3()); 

                    }


                    }
                    break;
                case 5 :
                    // InternalMyDsl.g:531:2: ( '\\u2012' )
                    {
                    // InternalMyDsl.g:531:2: ( '\\u2012' )
                    // InternalMyDsl.g:532:3: '\\u2012'
                    {
                     before(grammarAccess.getDashAccess().getFigureDashKeyword_4()); 
                    match(input,25,FOLLOW_2); 
                     after(grammarAccess.getDashAccess().getFigureDashKeyword_4()); 

                    }


                    }
                    break;
                case 6 :
                    // InternalMyDsl.g:537:2: ( '\\u2013' )
                    {
                    // InternalMyDsl.g:537:2: ( '\\u2013' )
                    // InternalMyDsl.g:538:3: '\\u2013'
                    {
                     before(grammarAccess.getDashAccess().getEnDashKeyword_5()); 
                    match(input,26,FOLLOW_2); 
                     after(grammarAccess.getDashAccess().getEnDashKeyword_5()); 

                    }


                    }
                    break;
                case 7 :
                    // InternalMyDsl.g:543:2: ( '\\u2014' )
                    {
                    // InternalMyDsl.g:543:2: ( '\\u2014' )
                    // InternalMyDsl.g:544:3: '\\u2014'
                    {
                     before(grammarAccess.getDashAccess().getEmDashKeyword_6()); 
                    match(input,27,FOLLOW_2); 
                     after(grammarAccess.getDashAccess().getEmDashKeyword_6()); 

                    }


                    }
                    break;
                case 8 :
                    // InternalMyDsl.g:549:2: ( '\\u2015' )
                    {
                    // InternalMyDsl.g:549:2: ( '\\u2015' )
                    // InternalMyDsl.g:550:3: '\\u2015'
                    {
                     before(grammarAccess.getDashAccess().getHorizontalBarKeyword_7()); 
                    match(input,28,FOLLOW_2); 
                     after(grammarAccess.getDashAccess().getHorizontalBarKeyword_7()); 

                    }


                    }
                    break;
                case 9 :
                    // InternalMyDsl.g:555:2: ( '\\u2212' )
                    {
                    // InternalMyDsl.g:555:2: ( '\\u2212' )
                    // InternalMyDsl.g:556:3: '\\u2212'
                    {
                     before(grammarAccess.getDashAccess().getMinusSignKeyword_8()); 
                    match(input,29,FOLLOW_2); 
                     after(grammarAccess.getDashAccess().getMinusSignKeyword_8()); 

                    }


                    }
                    break;
                case 10 :
                    // InternalMyDsl.g:561:2: ( '\\uFE58' )
                    {
                    // InternalMyDsl.g:561:2: ( '\\uFE58' )
                    // InternalMyDsl.g:562:3: '\\uFE58'
                    {
                     before(grammarAccess.getDashAccess().getSmallEmDashKeyword_9()); 
                    match(input,30,FOLLOW_2); 
                     after(grammarAccess.getDashAccess().getSmallEmDashKeyword_9()); 

                    }


                    }
                    break;
                case 11 :
                    // InternalMyDsl.g:567:2: ( '\\uFE63' )
                    {
                    // InternalMyDsl.g:567:2: ( '\\uFE63' )
                    // InternalMyDsl.g:568:3: '\\uFE63'
                    {
                     before(grammarAccess.getDashAccess().getSmallHyphenMinusKeyword_10()); 
                    match(input,31,FOLLOW_2); 
                     after(grammarAccess.getDashAccess().getSmallHyphenMinusKeyword_10()); 

                    }


                    }
                    break;
                case 12 :
                    // InternalMyDsl.g:573:2: ( '\\uFF0D' )
                    {
                    // InternalMyDsl.g:573:2: ( '\\uFF0D' )
                    // InternalMyDsl.g:574:3: '\\uFF0D'
                    {
                     before(grammarAccess.getDashAccess().getFullwidthHyphenMinusKeyword_11()); 
                    match(input,32,FOLLOW_2); 
                     after(grammarAccess.getDashAccess().getFullwidthHyphenMinusKeyword_11()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Dash__Alternatives"


    // $ANTLR start "rule__Query__Group__0"
    // InternalMyDsl.g:583:1: rule__Query__Group__0 : rule__Query__Group__0__Impl rule__Query__Group__1 ;
    public final void rule__Query__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:587:1: ( rule__Query__Group__0__Impl rule__Query__Group__1 )
            // InternalMyDsl.g:588:2: rule__Query__Group__0__Impl rule__Query__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__Query__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Query__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Query__Group__0"


    // $ANTLR start "rule__Query__Group__0__Impl"
    // InternalMyDsl.g:595:1: rule__Query__Group__0__Impl : ( 'CREATE' ) ;
    public final void rule__Query__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:599:1: ( ( 'CREATE' ) )
            // InternalMyDsl.g:600:1: ( 'CREATE' )
            {
            // InternalMyDsl.g:600:1: ( 'CREATE' )
            // InternalMyDsl.g:601:2: 'CREATE'
            {
             before(grammarAccess.getQueryAccess().getCREATEKeyword_0()); 
            match(input,33,FOLLOW_2); 
             after(grammarAccess.getQueryAccess().getCREATEKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Query__Group__0__Impl"


    // $ANTLR start "rule__Query__Group__1"
    // InternalMyDsl.g:610:1: rule__Query__Group__1 : rule__Query__Group__1__Impl ;
    public final void rule__Query__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:614:1: ( rule__Query__Group__1__Impl )
            // InternalMyDsl.g:615:2: rule__Query__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Query__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Query__Group__1"


    // $ANTLR start "rule__Query__Group__1__Impl"
    // InternalMyDsl.g:621:1: rule__Query__Group__1__Impl : ( ( rule__Query__PatternAssignment_1 ) ) ;
    public final void rule__Query__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:625:1: ( ( ( rule__Query__PatternAssignment_1 ) ) )
            // InternalMyDsl.g:626:1: ( ( rule__Query__PatternAssignment_1 ) )
            {
            // InternalMyDsl.g:626:1: ( ( rule__Query__PatternAssignment_1 ) )
            // InternalMyDsl.g:627:2: ( rule__Query__PatternAssignment_1 )
            {
             before(grammarAccess.getQueryAccess().getPatternAssignment_1()); 
            // InternalMyDsl.g:628:2: ( rule__Query__PatternAssignment_1 )
            // InternalMyDsl.g:628:3: rule__Query__PatternAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Query__PatternAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getQueryAccess().getPatternAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Query__Group__1__Impl"


    // $ANTLR start "rule__PatternPart__Group__0"
    // InternalMyDsl.g:637:1: rule__PatternPart__Group__0 : rule__PatternPart__Group__0__Impl rule__PatternPart__Group__1 ;
    public final void rule__PatternPart__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:641:1: ( rule__PatternPart__Group__0__Impl rule__PatternPart__Group__1 )
            // InternalMyDsl.g:642:2: rule__PatternPart__Group__0__Impl rule__PatternPart__Group__1
            {
            pushFollow(FOLLOW_6);
            rule__PatternPart__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__PatternPart__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PatternPart__Group__0"


    // $ANTLR start "rule__PatternPart__Group__0__Impl"
    // InternalMyDsl.g:649:1: rule__PatternPart__Group__0__Impl : ( ( rule__PatternPart__NodeAssignment_0 ) ) ;
    public final void rule__PatternPart__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:653:1: ( ( ( rule__PatternPart__NodeAssignment_0 ) ) )
            // InternalMyDsl.g:654:1: ( ( rule__PatternPart__NodeAssignment_0 ) )
            {
            // InternalMyDsl.g:654:1: ( ( rule__PatternPart__NodeAssignment_0 ) )
            // InternalMyDsl.g:655:2: ( rule__PatternPart__NodeAssignment_0 )
            {
             before(grammarAccess.getPatternPartAccess().getNodeAssignment_0()); 
            // InternalMyDsl.g:656:2: ( rule__PatternPart__NodeAssignment_0 )
            // InternalMyDsl.g:656:3: rule__PatternPart__NodeAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__PatternPart__NodeAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getPatternPartAccess().getNodeAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PatternPart__Group__0__Impl"


    // $ANTLR start "rule__PatternPart__Group__1"
    // InternalMyDsl.g:664:1: rule__PatternPart__Group__1 : rule__PatternPart__Group__1__Impl ;
    public final void rule__PatternPart__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:668:1: ( rule__PatternPart__Group__1__Impl )
            // InternalMyDsl.g:669:2: rule__PatternPart__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__PatternPart__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PatternPart__Group__1"


    // $ANTLR start "rule__PatternPart__Group__1__Impl"
    // InternalMyDsl.g:675:1: rule__PatternPart__Group__1__Impl : ( ( ( rule__PatternPart__ChainAssignment_1 ) ) ( ( rule__PatternPart__ChainAssignment_1 )* ) ) ;
    public final void rule__PatternPart__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:679:1: ( ( ( ( rule__PatternPart__ChainAssignment_1 ) ) ( ( rule__PatternPart__ChainAssignment_1 )* ) ) )
            // InternalMyDsl.g:680:1: ( ( ( rule__PatternPart__ChainAssignment_1 ) ) ( ( rule__PatternPart__ChainAssignment_1 )* ) )
            {
            // InternalMyDsl.g:680:1: ( ( ( rule__PatternPart__ChainAssignment_1 ) ) ( ( rule__PatternPart__ChainAssignment_1 )* ) )
            // InternalMyDsl.g:681:2: ( ( rule__PatternPart__ChainAssignment_1 ) ) ( ( rule__PatternPart__ChainAssignment_1 )* )
            {
            // InternalMyDsl.g:681:2: ( ( rule__PatternPart__ChainAssignment_1 ) )
            // InternalMyDsl.g:682:3: ( rule__PatternPart__ChainAssignment_1 )
            {
             before(grammarAccess.getPatternPartAccess().getChainAssignment_1()); 
            // InternalMyDsl.g:683:3: ( rule__PatternPart__ChainAssignment_1 )
            // InternalMyDsl.g:683:4: rule__PatternPart__ChainAssignment_1
            {
            pushFollow(FOLLOW_7);
            rule__PatternPart__ChainAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getPatternPartAccess().getChainAssignment_1()); 

            }

            // InternalMyDsl.g:686:2: ( ( rule__PatternPart__ChainAssignment_1 )* )
            // InternalMyDsl.g:687:3: ( rule__PatternPart__ChainAssignment_1 )*
            {
             before(grammarAccess.getPatternPartAccess().getChainAssignment_1()); 
            // InternalMyDsl.g:688:3: ( rule__PatternPart__ChainAssignment_1 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>=11 && LA7_0<=15)||(LA7_0>=21 && LA7_0<=32)) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalMyDsl.g:688:4: rule__PatternPart__ChainAssignment_1
            	    {
            	    pushFollow(FOLLOW_7);
            	    rule__PatternPart__ChainAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

             after(grammarAccess.getPatternPartAccess().getChainAssignment_1()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PatternPart__Group__1__Impl"


    // $ANTLR start "rule__NodePattern__Group__0"
    // InternalMyDsl.g:698:1: rule__NodePattern__Group__0 : rule__NodePattern__Group__0__Impl rule__NodePattern__Group__1 ;
    public final void rule__NodePattern__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:702:1: ( rule__NodePattern__Group__0__Impl rule__NodePattern__Group__1 )
            // InternalMyDsl.g:703:2: rule__NodePattern__Group__0__Impl rule__NodePattern__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__NodePattern__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NodePattern__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NodePattern__Group__0"


    // $ANTLR start "rule__NodePattern__Group__0__Impl"
    // InternalMyDsl.g:710:1: rule__NodePattern__Group__0__Impl : ( '(' ) ;
    public final void rule__NodePattern__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:714:1: ( ( '(' ) )
            // InternalMyDsl.g:715:1: ( '(' )
            {
            // InternalMyDsl.g:715:1: ( '(' )
            // InternalMyDsl.g:716:2: '('
            {
             before(grammarAccess.getNodePatternAccess().getLeftParenthesisKeyword_0()); 
            match(input,34,FOLLOW_2); 
             after(grammarAccess.getNodePatternAccess().getLeftParenthesisKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NodePattern__Group__0__Impl"


    // $ANTLR start "rule__NodePattern__Group__1"
    // InternalMyDsl.g:725:1: rule__NodePattern__Group__1 : rule__NodePattern__Group__1__Impl rule__NodePattern__Group__2 ;
    public final void rule__NodePattern__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:729:1: ( rule__NodePattern__Group__1__Impl rule__NodePattern__Group__2 )
            // InternalMyDsl.g:730:2: rule__NodePattern__Group__1__Impl rule__NodePattern__Group__2
            {
            pushFollow(FOLLOW_8);
            rule__NodePattern__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NodePattern__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NodePattern__Group__1"


    // $ANTLR start "rule__NodePattern__Group__1__Impl"
    // InternalMyDsl.g:737:1: rule__NodePattern__Group__1__Impl : ( ( rule__NodePattern__VariableAssignment_1 )? ) ;
    public final void rule__NodePattern__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:741:1: ( ( ( rule__NodePattern__VariableAssignment_1 )? ) )
            // InternalMyDsl.g:742:1: ( ( rule__NodePattern__VariableAssignment_1 )? )
            {
            // InternalMyDsl.g:742:1: ( ( rule__NodePattern__VariableAssignment_1 )? )
            // InternalMyDsl.g:743:2: ( rule__NodePattern__VariableAssignment_1 )?
            {
             before(grammarAccess.getNodePatternAccess().getVariableAssignment_1()); 
            // InternalMyDsl.g:744:2: ( rule__NodePattern__VariableAssignment_1 )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_ID) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalMyDsl.g:744:3: rule__NodePattern__VariableAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__NodePattern__VariableAssignment_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getNodePatternAccess().getVariableAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NodePattern__Group__1__Impl"


    // $ANTLR start "rule__NodePattern__Group__2"
    // InternalMyDsl.g:752:1: rule__NodePattern__Group__2 : rule__NodePattern__Group__2__Impl rule__NodePattern__Group__3 ;
    public final void rule__NodePattern__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:756:1: ( rule__NodePattern__Group__2__Impl rule__NodePattern__Group__3 )
            // InternalMyDsl.g:757:2: rule__NodePattern__Group__2__Impl rule__NodePattern__Group__3
            {
            pushFollow(FOLLOW_9);
            rule__NodePattern__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NodePattern__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NodePattern__Group__2"


    // $ANTLR start "rule__NodePattern__Group__2__Impl"
    // InternalMyDsl.g:764:1: rule__NodePattern__Group__2__Impl : ( ':' ) ;
    public final void rule__NodePattern__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:768:1: ( ( ':' ) )
            // InternalMyDsl.g:769:1: ( ':' )
            {
            // InternalMyDsl.g:769:1: ( ':' )
            // InternalMyDsl.g:770:2: ':'
            {
             before(grammarAccess.getNodePatternAccess().getColonKeyword_2()); 
            match(input,35,FOLLOW_2); 
             after(grammarAccess.getNodePatternAccess().getColonKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NodePattern__Group__2__Impl"


    // $ANTLR start "rule__NodePattern__Group__3"
    // InternalMyDsl.g:779:1: rule__NodePattern__Group__3 : rule__NodePattern__Group__3__Impl rule__NodePattern__Group__4 ;
    public final void rule__NodePattern__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:783:1: ( rule__NodePattern__Group__3__Impl rule__NodePattern__Group__4 )
            // InternalMyDsl.g:784:2: rule__NodePattern__Group__3__Impl rule__NodePattern__Group__4
            {
            pushFollow(FOLLOW_9);
            rule__NodePattern__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__NodePattern__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NodePattern__Group__3"


    // $ANTLR start "rule__NodePattern__Group__3__Impl"
    // InternalMyDsl.g:791:1: rule__NodePattern__Group__3__Impl : ( ( rule__NodePattern__LabelAssignment_3 )? ) ;
    public final void rule__NodePattern__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:795:1: ( ( ( rule__NodePattern__LabelAssignment_3 )? ) )
            // InternalMyDsl.g:796:1: ( ( rule__NodePattern__LabelAssignment_3 )? )
            {
            // InternalMyDsl.g:796:1: ( ( rule__NodePattern__LabelAssignment_3 )? )
            // InternalMyDsl.g:797:2: ( rule__NodePattern__LabelAssignment_3 )?
            {
             before(grammarAccess.getNodePatternAccess().getLabelAssignment_3()); 
            // InternalMyDsl.g:798:2: ( rule__NodePattern__LabelAssignment_3 )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RULE_ID) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalMyDsl.g:798:3: rule__NodePattern__LabelAssignment_3
                    {
                    pushFollow(FOLLOW_2);
                    rule__NodePattern__LabelAssignment_3();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getNodePatternAccess().getLabelAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NodePattern__Group__3__Impl"


    // $ANTLR start "rule__NodePattern__Group__4"
    // InternalMyDsl.g:806:1: rule__NodePattern__Group__4 : rule__NodePattern__Group__4__Impl ;
    public final void rule__NodePattern__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:810:1: ( rule__NodePattern__Group__4__Impl )
            // InternalMyDsl.g:811:2: rule__NodePattern__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__NodePattern__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NodePattern__Group__4"


    // $ANTLR start "rule__NodePattern__Group__4__Impl"
    // InternalMyDsl.g:817:1: rule__NodePattern__Group__4__Impl : ( ')' ) ;
    public final void rule__NodePattern__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:821:1: ( ( ')' ) )
            // InternalMyDsl.g:822:1: ( ')' )
            {
            // InternalMyDsl.g:822:1: ( ')' )
            // InternalMyDsl.g:823:2: ')'
            {
             before(grammarAccess.getNodePatternAccess().getRightParenthesisKeyword_4()); 
            match(input,36,FOLLOW_2); 
             after(grammarAccess.getNodePatternAccess().getRightParenthesisKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NodePattern__Group__4__Impl"


    // $ANTLR start "rule__PatternElementChain__Group__0"
    // InternalMyDsl.g:833:1: rule__PatternElementChain__Group__0 : rule__PatternElementChain__Group__0__Impl rule__PatternElementChain__Group__1 ;
    public final void rule__PatternElementChain__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:837:1: ( rule__PatternElementChain__Group__0__Impl rule__PatternElementChain__Group__1 )
            // InternalMyDsl.g:838:2: rule__PatternElementChain__Group__0__Impl rule__PatternElementChain__Group__1
            {
            pushFollow(FOLLOW_5);
            rule__PatternElementChain__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__PatternElementChain__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PatternElementChain__Group__0"


    // $ANTLR start "rule__PatternElementChain__Group__0__Impl"
    // InternalMyDsl.g:845:1: rule__PatternElementChain__Group__0__Impl : ( ( rule__PatternElementChain__RelationshipPatternAssignment_0 ) ) ;
    public final void rule__PatternElementChain__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:849:1: ( ( ( rule__PatternElementChain__RelationshipPatternAssignment_0 ) ) )
            // InternalMyDsl.g:850:1: ( ( rule__PatternElementChain__RelationshipPatternAssignment_0 ) )
            {
            // InternalMyDsl.g:850:1: ( ( rule__PatternElementChain__RelationshipPatternAssignment_0 ) )
            // InternalMyDsl.g:851:2: ( rule__PatternElementChain__RelationshipPatternAssignment_0 )
            {
             before(grammarAccess.getPatternElementChainAccess().getRelationshipPatternAssignment_0()); 
            // InternalMyDsl.g:852:2: ( rule__PatternElementChain__RelationshipPatternAssignment_0 )
            // InternalMyDsl.g:852:3: rule__PatternElementChain__RelationshipPatternAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__PatternElementChain__RelationshipPatternAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getPatternElementChainAccess().getRelationshipPatternAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PatternElementChain__Group__0__Impl"


    // $ANTLR start "rule__PatternElementChain__Group__1"
    // InternalMyDsl.g:860:1: rule__PatternElementChain__Group__1 : rule__PatternElementChain__Group__1__Impl ;
    public final void rule__PatternElementChain__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:864:1: ( rule__PatternElementChain__Group__1__Impl )
            // InternalMyDsl.g:865:2: rule__PatternElementChain__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__PatternElementChain__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PatternElementChain__Group__1"


    // $ANTLR start "rule__PatternElementChain__Group__1__Impl"
    // InternalMyDsl.g:871:1: rule__PatternElementChain__Group__1__Impl : ( ( rule__PatternElementChain__NodePatternAssignment_1 ) ) ;
    public final void rule__PatternElementChain__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:875:1: ( ( ( rule__PatternElementChain__NodePatternAssignment_1 ) ) )
            // InternalMyDsl.g:876:1: ( ( rule__PatternElementChain__NodePatternAssignment_1 ) )
            {
            // InternalMyDsl.g:876:1: ( ( rule__PatternElementChain__NodePatternAssignment_1 ) )
            // InternalMyDsl.g:877:2: ( rule__PatternElementChain__NodePatternAssignment_1 )
            {
             before(grammarAccess.getPatternElementChainAccess().getNodePatternAssignment_1()); 
            // InternalMyDsl.g:878:2: ( rule__PatternElementChain__NodePatternAssignment_1 )
            // InternalMyDsl.g:878:3: rule__PatternElementChain__NodePatternAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__PatternElementChain__NodePatternAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getPatternElementChainAccess().getNodePatternAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PatternElementChain__Group__1__Impl"


    // $ANTLR start "rule__RelationshipPattern__Group_0__0"
    // InternalMyDsl.g:887:1: rule__RelationshipPattern__Group_0__0 : rule__RelationshipPattern__Group_0__0__Impl rule__RelationshipPattern__Group_0__1 ;
    public final void rule__RelationshipPattern__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:891:1: ( rule__RelationshipPattern__Group_0__0__Impl rule__RelationshipPattern__Group_0__1 )
            // InternalMyDsl.g:892:2: rule__RelationshipPattern__Group_0__0__Impl rule__RelationshipPattern__Group_0__1
            {
            pushFollow(FOLLOW_10);
            rule__RelationshipPattern__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RelationshipPattern__Group_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_0__0"


    // $ANTLR start "rule__RelationshipPattern__Group_0__0__Impl"
    // InternalMyDsl.g:899:1: rule__RelationshipPattern__Group_0__0__Impl : ( ruleleftArrowHead ) ;
    public final void rule__RelationshipPattern__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:903:1: ( ( ruleleftArrowHead ) )
            // InternalMyDsl.g:904:1: ( ruleleftArrowHead )
            {
            // InternalMyDsl.g:904:1: ( ruleleftArrowHead )
            // InternalMyDsl.g:905:2: ruleleftArrowHead
            {
             before(grammarAccess.getRelationshipPatternAccess().getLeftArrowHeadParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleleftArrowHead();

            state._fsp--;

             after(grammarAccess.getRelationshipPatternAccess().getLeftArrowHeadParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_0__0__Impl"


    // $ANTLR start "rule__RelationshipPattern__Group_0__1"
    // InternalMyDsl.g:914:1: rule__RelationshipPattern__Group_0__1 : rule__RelationshipPattern__Group_0__1__Impl rule__RelationshipPattern__Group_0__2 ;
    public final void rule__RelationshipPattern__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:918:1: ( rule__RelationshipPattern__Group_0__1__Impl rule__RelationshipPattern__Group_0__2 )
            // InternalMyDsl.g:919:2: rule__RelationshipPattern__Group_0__1__Impl rule__RelationshipPattern__Group_0__2
            {
            pushFollow(FOLLOW_11);
            rule__RelationshipPattern__Group_0__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RelationshipPattern__Group_0__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_0__1"


    // $ANTLR start "rule__RelationshipPattern__Group_0__1__Impl"
    // InternalMyDsl.g:926:1: rule__RelationshipPattern__Group_0__1__Impl : ( ruledash ) ;
    public final void rule__RelationshipPattern__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:930:1: ( ( ruledash ) )
            // InternalMyDsl.g:931:1: ( ruledash )
            {
            // InternalMyDsl.g:931:1: ( ruledash )
            // InternalMyDsl.g:932:2: ruledash
            {
             before(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_0_1()); 
            pushFollow(FOLLOW_2);
            ruledash();

            state._fsp--;

             after(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_0__1__Impl"


    // $ANTLR start "rule__RelationshipPattern__Group_0__2"
    // InternalMyDsl.g:941:1: rule__RelationshipPattern__Group_0__2 : rule__RelationshipPattern__Group_0__2__Impl rule__RelationshipPattern__Group_0__3 ;
    public final void rule__RelationshipPattern__Group_0__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:945:1: ( rule__RelationshipPattern__Group_0__2__Impl rule__RelationshipPattern__Group_0__3 )
            // InternalMyDsl.g:946:2: rule__RelationshipPattern__Group_0__2__Impl rule__RelationshipPattern__Group_0__3
            {
            pushFollow(FOLLOW_11);
            rule__RelationshipPattern__Group_0__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RelationshipPattern__Group_0__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_0__2"


    // $ANTLR start "rule__RelationshipPattern__Group_0__2__Impl"
    // InternalMyDsl.g:953:1: rule__RelationshipPattern__Group_0__2__Impl : ( ( ruleRelationshipDetail )? ) ;
    public final void rule__RelationshipPattern__Group_0__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:957:1: ( ( ( ruleRelationshipDetail )? ) )
            // InternalMyDsl.g:958:1: ( ( ruleRelationshipDetail )? )
            {
            // InternalMyDsl.g:958:1: ( ( ruleRelationshipDetail )? )
            // InternalMyDsl.g:959:2: ( ruleRelationshipDetail )?
            {
             before(grammarAccess.getRelationshipPatternAccess().getRelationshipDetailParserRuleCall_0_2()); 
            // InternalMyDsl.g:960:2: ( ruleRelationshipDetail )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==37) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalMyDsl.g:960:3: ruleRelationshipDetail
                    {
                    pushFollow(FOLLOW_2);
                    ruleRelationshipDetail();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getRelationshipPatternAccess().getRelationshipDetailParserRuleCall_0_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_0__2__Impl"


    // $ANTLR start "rule__RelationshipPattern__Group_0__3"
    // InternalMyDsl.g:968:1: rule__RelationshipPattern__Group_0__3 : rule__RelationshipPattern__Group_0__3__Impl rule__RelationshipPattern__Group_0__4 ;
    public final void rule__RelationshipPattern__Group_0__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:972:1: ( rule__RelationshipPattern__Group_0__3__Impl rule__RelationshipPattern__Group_0__4 )
            // InternalMyDsl.g:973:2: rule__RelationshipPattern__Group_0__3__Impl rule__RelationshipPattern__Group_0__4
            {
            pushFollow(FOLLOW_12);
            rule__RelationshipPattern__Group_0__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RelationshipPattern__Group_0__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_0__3"


    // $ANTLR start "rule__RelationshipPattern__Group_0__3__Impl"
    // InternalMyDsl.g:980:1: rule__RelationshipPattern__Group_0__3__Impl : ( ruledash ) ;
    public final void rule__RelationshipPattern__Group_0__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:984:1: ( ( ruledash ) )
            // InternalMyDsl.g:985:1: ( ruledash )
            {
            // InternalMyDsl.g:985:1: ( ruledash )
            // InternalMyDsl.g:986:2: ruledash
            {
             before(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_0_3()); 
            pushFollow(FOLLOW_2);
            ruledash();

            state._fsp--;

             after(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_0_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_0__3__Impl"


    // $ANTLR start "rule__RelationshipPattern__Group_0__4"
    // InternalMyDsl.g:995:1: rule__RelationshipPattern__Group_0__4 : rule__RelationshipPattern__Group_0__4__Impl ;
    public final void rule__RelationshipPattern__Group_0__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:999:1: ( rule__RelationshipPattern__Group_0__4__Impl )
            // InternalMyDsl.g:1000:2: rule__RelationshipPattern__Group_0__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__RelationshipPattern__Group_0__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_0__4"


    // $ANTLR start "rule__RelationshipPattern__Group_0__4__Impl"
    // InternalMyDsl.g:1006:1: rule__RelationshipPattern__Group_0__4__Impl : ( rulerightArrowHead ) ;
    public final void rule__RelationshipPattern__Group_0__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1010:1: ( ( rulerightArrowHead ) )
            // InternalMyDsl.g:1011:1: ( rulerightArrowHead )
            {
            // InternalMyDsl.g:1011:1: ( rulerightArrowHead )
            // InternalMyDsl.g:1012:2: rulerightArrowHead
            {
             before(grammarAccess.getRelationshipPatternAccess().getRightArrowHeadParserRuleCall_0_4()); 
            pushFollow(FOLLOW_2);
            rulerightArrowHead();

            state._fsp--;

             after(grammarAccess.getRelationshipPatternAccess().getRightArrowHeadParserRuleCall_0_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_0__4__Impl"


    // $ANTLR start "rule__RelationshipPattern__Group_1__0"
    // InternalMyDsl.g:1022:1: rule__RelationshipPattern__Group_1__0 : rule__RelationshipPattern__Group_1__0__Impl rule__RelationshipPattern__Group_1__1 ;
    public final void rule__RelationshipPattern__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1026:1: ( rule__RelationshipPattern__Group_1__0__Impl rule__RelationshipPattern__Group_1__1 )
            // InternalMyDsl.g:1027:2: rule__RelationshipPattern__Group_1__0__Impl rule__RelationshipPattern__Group_1__1
            {
            pushFollow(FOLLOW_10);
            rule__RelationshipPattern__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RelationshipPattern__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_1__0"


    // $ANTLR start "rule__RelationshipPattern__Group_1__0__Impl"
    // InternalMyDsl.g:1034:1: rule__RelationshipPattern__Group_1__0__Impl : ( ruleleftArrowHead ) ;
    public final void rule__RelationshipPattern__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1038:1: ( ( ruleleftArrowHead ) )
            // InternalMyDsl.g:1039:1: ( ruleleftArrowHead )
            {
            // InternalMyDsl.g:1039:1: ( ruleleftArrowHead )
            // InternalMyDsl.g:1040:2: ruleleftArrowHead
            {
             before(grammarAccess.getRelationshipPatternAccess().getLeftArrowHeadParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleleftArrowHead();

            state._fsp--;

             after(grammarAccess.getRelationshipPatternAccess().getLeftArrowHeadParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_1__0__Impl"


    // $ANTLR start "rule__RelationshipPattern__Group_1__1"
    // InternalMyDsl.g:1049:1: rule__RelationshipPattern__Group_1__1 : rule__RelationshipPattern__Group_1__1__Impl rule__RelationshipPattern__Group_1__2 ;
    public final void rule__RelationshipPattern__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1053:1: ( rule__RelationshipPattern__Group_1__1__Impl rule__RelationshipPattern__Group_1__2 )
            // InternalMyDsl.g:1054:2: rule__RelationshipPattern__Group_1__1__Impl rule__RelationshipPattern__Group_1__2
            {
            pushFollow(FOLLOW_11);
            rule__RelationshipPattern__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RelationshipPattern__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_1__1"


    // $ANTLR start "rule__RelationshipPattern__Group_1__1__Impl"
    // InternalMyDsl.g:1061:1: rule__RelationshipPattern__Group_1__1__Impl : ( ruledash ) ;
    public final void rule__RelationshipPattern__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1065:1: ( ( ruledash ) )
            // InternalMyDsl.g:1066:1: ( ruledash )
            {
            // InternalMyDsl.g:1066:1: ( ruledash )
            // InternalMyDsl.g:1067:2: ruledash
            {
             before(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_1_1()); 
            pushFollow(FOLLOW_2);
            ruledash();

            state._fsp--;

             after(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_1__1__Impl"


    // $ANTLR start "rule__RelationshipPattern__Group_1__2"
    // InternalMyDsl.g:1076:1: rule__RelationshipPattern__Group_1__2 : rule__RelationshipPattern__Group_1__2__Impl rule__RelationshipPattern__Group_1__3 ;
    public final void rule__RelationshipPattern__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1080:1: ( rule__RelationshipPattern__Group_1__2__Impl rule__RelationshipPattern__Group_1__3 )
            // InternalMyDsl.g:1081:2: rule__RelationshipPattern__Group_1__2__Impl rule__RelationshipPattern__Group_1__3
            {
            pushFollow(FOLLOW_11);
            rule__RelationshipPattern__Group_1__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RelationshipPattern__Group_1__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_1__2"


    // $ANTLR start "rule__RelationshipPattern__Group_1__2__Impl"
    // InternalMyDsl.g:1088:1: rule__RelationshipPattern__Group_1__2__Impl : ( ( ruleRelationshipDetail )? ) ;
    public final void rule__RelationshipPattern__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1092:1: ( ( ( ruleRelationshipDetail )? ) )
            // InternalMyDsl.g:1093:1: ( ( ruleRelationshipDetail )? )
            {
            // InternalMyDsl.g:1093:1: ( ( ruleRelationshipDetail )? )
            // InternalMyDsl.g:1094:2: ( ruleRelationshipDetail )?
            {
             before(grammarAccess.getRelationshipPatternAccess().getRelationshipDetailParserRuleCall_1_2()); 
            // InternalMyDsl.g:1095:2: ( ruleRelationshipDetail )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==37) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // InternalMyDsl.g:1095:3: ruleRelationshipDetail
                    {
                    pushFollow(FOLLOW_2);
                    ruleRelationshipDetail();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getRelationshipPatternAccess().getRelationshipDetailParserRuleCall_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_1__2__Impl"


    // $ANTLR start "rule__RelationshipPattern__Group_1__3"
    // InternalMyDsl.g:1103:1: rule__RelationshipPattern__Group_1__3 : rule__RelationshipPattern__Group_1__3__Impl ;
    public final void rule__RelationshipPattern__Group_1__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1107:1: ( rule__RelationshipPattern__Group_1__3__Impl )
            // InternalMyDsl.g:1108:2: rule__RelationshipPattern__Group_1__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__RelationshipPattern__Group_1__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_1__3"


    // $ANTLR start "rule__RelationshipPattern__Group_1__3__Impl"
    // InternalMyDsl.g:1114:1: rule__RelationshipPattern__Group_1__3__Impl : ( ruledash ) ;
    public final void rule__RelationshipPattern__Group_1__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1118:1: ( ( ruledash ) )
            // InternalMyDsl.g:1119:1: ( ruledash )
            {
            // InternalMyDsl.g:1119:1: ( ruledash )
            // InternalMyDsl.g:1120:2: ruledash
            {
             before(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_1_3()); 
            pushFollow(FOLLOW_2);
            ruledash();

            state._fsp--;

             after(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_1_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_1__3__Impl"


    // $ANTLR start "rule__RelationshipPattern__Group_2__0"
    // InternalMyDsl.g:1130:1: rule__RelationshipPattern__Group_2__0 : rule__RelationshipPattern__Group_2__0__Impl rule__RelationshipPattern__Group_2__1 ;
    public final void rule__RelationshipPattern__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1134:1: ( rule__RelationshipPattern__Group_2__0__Impl rule__RelationshipPattern__Group_2__1 )
            // InternalMyDsl.g:1135:2: rule__RelationshipPattern__Group_2__0__Impl rule__RelationshipPattern__Group_2__1
            {
            pushFollow(FOLLOW_11);
            rule__RelationshipPattern__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RelationshipPattern__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_2__0"


    // $ANTLR start "rule__RelationshipPattern__Group_2__0__Impl"
    // InternalMyDsl.g:1142:1: rule__RelationshipPattern__Group_2__0__Impl : ( ruledash ) ;
    public final void rule__RelationshipPattern__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1146:1: ( ( ruledash ) )
            // InternalMyDsl.g:1147:1: ( ruledash )
            {
            // InternalMyDsl.g:1147:1: ( ruledash )
            // InternalMyDsl.g:1148:2: ruledash
            {
             before(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruledash();

            state._fsp--;

             after(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_2__0__Impl"


    // $ANTLR start "rule__RelationshipPattern__Group_2__1"
    // InternalMyDsl.g:1157:1: rule__RelationshipPattern__Group_2__1 : rule__RelationshipPattern__Group_2__1__Impl rule__RelationshipPattern__Group_2__2 ;
    public final void rule__RelationshipPattern__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1161:1: ( rule__RelationshipPattern__Group_2__1__Impl rule__RelationshipPattern__Group_2__2 )
            // InternalMyDsl.g:1162:2: rule__RelationshipPattern__Group_2__1__Impl rule__RelationshipPattern__Group_2__2
            {
            pushFollow(FOLLOW_11);
            rule__RelationshipPattern__Group_2__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RelationshipPattern__Group_2__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_2__1"


    // $ANTLR start "rule__RelationshipPattern__Group_2__1__Impl"
    // InternalMyDsl.g:1169:1: rule__RelationshipPattern__Group_2__1__Impl : ( ( ruleRelationshipDetail )? ) ;
    public final void rule__RelationshipPattern__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1173:1: ( ( ( ruleRelationshipDetail )? ) )
            // InternalMyDsl.g:1174:1: ( ( ruleRelationshipDetail )? )
            {
            // InternalMyDsl.g:1174:1: ( ( ruleRelationshipDetail )? )
            // InternalMyDsl.g:1175:2: ( ruleRelationshipDetail )?
            {
             before(grammarAccess.getRelationshipPatternAccess().getRelationshipDetailParserRuleCall_2_1()); 
            // InternalMyDsl.g:1176:2: ( ruleRelationshipDetail )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==37) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalMyDsl.g:1176:3: ruleRelationshipDetail
                    {
                    pushFollow(FOLLOW_2);
                    ruleRelationshipDetail();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getRelationshipPatternAccess().getRelationshipDetailParserRuleCall_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_2__1__Impl"


    // $ANTLR start "rule__RelationshipPattern__Group_2__2"
    // InternalMyDsl.g:1184:1: rule__RelationshipPattern__Group_2__2 : rule__RelationshipPattern__Group_2__2__Impl rule__RelationshipPattern__Group_2__3 ;
    public final void rule__RelationshipPattern__Group_2__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1188:1: ( rule__RelationshipPattern__Group_2__2__Impl rule__RelationshipPattern__Group_2__3 )
            // InternalMyDsl.g:1189:2: rule__RelationshipPattern__Group_2__2__Impl rule__RelationshipPattern__Group_2__3
            {
            pushFollow(FOLLOW_12);
            rule__RelationshipPattern__Group_2__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RelationshipPattern__Group_2__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_2__2"


    // $ANTLR start "rule__RelationshipPattern__Group_2__2__Impl"
    // InternalMyDsl.g:1196:1: rule__RelationshipPattern__Group_2__2__Impl : ( ruledash ) ;
    public final void rule__RelationshipPattern__Group_2__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1200:1: ( ( ruledash ) )
            // InternalMyDsl.g:1201:1: ( ruledash )
            {
            // InternalMyDsl.g:1201:1: ( ruledash )
            // InternalMyDsl.g:1202:2: ruledash
            {
             before(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_2_2()); 
            pushFollow(FOLLOW_2);
            ruledash();

            state._fsp--;

             after(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_2_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_2__2__Impl"


    // $ANTLR start "rule__RelationshipPattern__Group_2__3"
    // InternalMyDsl.g:1211:1: rule__RelationshipPattern__Group_2__3 : rule__RelationshipPattern__Group_2__3__Impl ;
    public final void rule__RelationshipPattern__Group_2__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1215:1: ( rule__RelationshipPattern__Group_2__3__Impl )
            // InternalMyDsl.g:1216:2: rule__RelationshipPattern__Group_2__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__RelationshipPattern__Group_2__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_2__3"


    // $ANTLR start "rule__RelationshipPattern__Group_2__3__Impl"
    // InternalMyDsl.g:1222:1: rule__RelationshipPattern__Group_2__3__Impl : ( rulerightArrowHead ) ;
    public final void rule__RelationshipPattern__Group_2__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1226:1: ( ( rulerightArrowHead ) )
            // InternalMyDsl.g:1227:1: ( rulerightArrowHead )
            {
            // InternalMyDsl.g:1227:1: ( rulerightArrowHead )
            // InternalMyDsl.g:1228:2: rulerightArrowHead
            {
             before(grammarAccess.getRelationshipPatternAccess().getRightArrowHeadParserRuleCall_2_3()); 
            pushFollow(FOLLOW_2);
            rulerightArrowHead();

            state._fsp--;

             after(grammarAccess.getRelationshipPatternAccess().getRightArrowHeadParserRuleCall_2_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_2__3__Impl"


    // $ANTLR start "rule__RelationshipPattern__Group_3__0"
    // InternalMyDsl.g:1238:1: rule__RelationshipPattern__Group_3__0 : rule__RelationshipPattern__Group_3__0__Impl rule__RelationshipPattern__Group_3__1 ;
    public final void rule__RelationshipPattern__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1242:1: ( rule__RelationshipPattern__Group_3__0__Impl rule__RelationshipPattern__Group_3__1 )
            // InternalMyDsl.g:1243:2: rule__RelationshipPattern__Group_3__0__Impl rule__RelationshipPattern__Group_3__1
            {
            pushFollow(FOLLOW_11);
            rule__RelationshipPattern__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RelationshipPattern__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_3__0"


    // $ANTLR start "rule__RelationshipPattern__Group_3__0__Impl"
    // InternalMyDsl.g:1250:1: rule__RelationshipPattern__Group_3__0__Impl : ( ruledash ) ;
    public final void rule__RelationshipPattern__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1254:1: ( ( ruledash ) )
            // InternalMyDsl.g:1255:1: ( ruledash )
            {
            // InternalMyDsl.g:1255:1: ( ruledash )
            // InternalMyDsl.g:1256:2: ruledash
            {
             before(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruledash();

            state._fsp--;

             after(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_3__0__Impl"


    // $ANTLR start "rule__RelationshipPattern__Group_3__1"
    // InternalMyDsl.g:1265:1: rule__RelationshipPattern__Group_3__1 : rule__RelationshipPattern__Group_3__1__Impl rule__RelationshipPattern__Group_3__2 ;
    public final void rule__RelationshipPattern__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1269:1: ( rule__RelationshipPattern__Group_3__1__Impl rule__RelationshipPattern__Group_3__2 )
            // InternalMyDsl.g:1270:2: rule__RelationshipPattern__Group_3__1__Impl rule__RelationshipPattern__Group_3__2
            {
            pushFollow(FOLLOW_11);
            rule__RelationshipPattern__Group_3__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RelationshipPattern__Group_3__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_3__1"


    // $ANTLR start "rule__RelationshipPattern__Group_3__1__Impl"
    // InternalMyDsl.g:1277:1: rule__RelationshipPattern__Group_3__1__Impl : ( ( ruleRelationshipDetail )? ) ;
    public final void rule__RelationshipPattern__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1281:1: ( ( ( ruleRelationshipDetail )? ) )
            // InternalMyDsl.g:1282:1: ( ( ruleRelationshipDetail )? )
            {
            // InternalMyDsl.g:1282:1: ( ( ruleRelationshipDetail )? )
            // InternalMyDsl.g:1283:2: ( ruleRelationshipDetail )?
            {
             before(grammarAccess.getRelationshipPatternAccess().getRelationshipDetailParserRuleCall_3_1()); 
            // InternalMyDsl.g:1284:2: ( ruleRelationshipDetail )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==37) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalMyDsl.g:1284:3: ruleRelationshipDetail
                    {
                    pushFollow(FOLLOW_2);
                    ruleRelationshipDetail();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getRelationshipPatternAccess().getRelationshipDetailParserRuleCall_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_3__1__Impl"


    // $ANTLR start "rule__RelationshipPattern__Group_3__2"
    // InternalMyDsl.g:1292:1: rule__RelationshipPattern__Group_3__2 : rule__RelationshipPattern__Group_3__2__Impl ;
    public final void rule__RelationshipPattern__Group_3__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1296:1: ( rule__RelationshipPattern__Group_3__2__Impl )
            // InternalMyDsl.g:1297:2: rule__RelationshipPattern__Group_3__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__RelationshipPattern__Group_3__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_3__2"


    // $ANTLR start "rule__RelationshipPattern__Group_3__2__Impl"
    // InternalMyDsl.g:1303:1: rule__RelationshipPattern__Group_3__2__Impl : ( ruledash ) ;
    public final void rule__RelationshipPattern__Group_3__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1307:1: ( ( ruledash ) )
            // InternalMyDsl.g:1308:1: ( ruledash )
            {
            // InternalMyDsl.g:1308:1: ( ruledash )
            // InternalMyDsl.g:1309:2: ruledash
            {
             before(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_3_2()); 
            pushFollow(FOLLOW_2);
            ruledash();

            state._fsp--;

             after(grammarAccess.getRelationshipPatternAccess().getDashParserRuleCall_3_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipPattern__Group_3__2__Impl"


    // $ANTLR start "rule__RelationshipDetail__Group__0"
    // InternalMyDsl.g:1319:1: rule__RelationshipDetail__Group__0 : rule__RelationshipDetail__Group__0__Impl rule__RelationshipDetail__Group__1 ;
    public final void rule__RelationshipDetail__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1323:1: ( rule__RelationshipDetail__Group__0__Impl rule__RelationshipDetail__Group__1 )
            // InternalMyDsl.g:1324:2: rule__RelationshipDetail__Group__0__Impl rule__RelationshipDetail__Group__1
            {
            pushFollow(FOLLOW_8);
            rule__RelationshipDetail__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RelationshipDetail__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipDetail__Group__0"


    // $ANTLR start "rule__RelationshipDetail__Group__0__Impl"
    // InternalMyDsl.g:1331:1: rule__RelationshipDetail__Group__0__Impl : ( '[' ) ;
    public final void rule__RelationshipDetail__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1335:1: ( ( '[' ) )
            // InternalMyDsl.g:1336:1: ( '[' )
            {
            // InternalMyDsl.g:1336:1: ( '[' )
            // InternalMyDsl.g:1337:2: '['
            {
             before(grammarAccess.getRelationshipDetailAccess().getLeftSquareBracketKeyword_0()); 
            match(input,37,FOLLOW_2); 
             after(grammarAccess.getRelationshipDetailAccess().getLeftSquareBracketKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipDetail__Group__0__Impl"


    // $ANTLR start "rule__RelationshipDetail__Group__1"
    // InternalMyDsl.g:1346:1: rule__RelationshipDetail__Group__1 : rule__RelationshipDetail__Group__1__Impl rule__RelationshipDetail__Group__2 ;
    public final void rule__RelationshipDetail__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1350:1: ( rule__RelationshipDetail__Group__1__Impl rule__RelationshipDetail__Group__2 )
            // InternalMyDsl.g:1351:2: rule__RelationshipDetail__Group__1__Impl rule__RelationshipDetail__Group__2
            {
            pushFollow(FOLLOW_8);
            rule__RelationshipDetail__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RelationshipDetail__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipDetail__Group__1"


    // $ANTLR start "rule__RelationshipDetail__Group__1__Impl"
    // InternalMyDsl.g:1358:1: rule__RelationshipDetail__Group__1__Impl : ( ( rule__RelationshipDetail__VariableAssignment_1 )? ) ;
    public final void rule__RelationshipDetail__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1362:1: ( ( ( rule__RelationshipDetail__VariableAssignment_1 )? ) )
            // InternalMyDsl.g:1363:1: ( ( rule__RelationshipDetail__VariableAssignment_1 )? )
            {
            // InternalMyDsl.g:1363:1: ( ( rule__RelationshipDetail__VariableAssignment_1 )? )
            // InternalMyDsl.g:1364:2: ( rule__RelationshipDetail__VariableAssignment_1 )?
            {
             before(grammarAccess.getRelationshipDetailAccess().getVariableAssignment_1()); 
            // InternalMyDsl.g:1365:2: ( rule__RelationshipDetail__VariableAssignment_1 )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==RULE_ID) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // InternalMyDsl.g:1365:3: rule__RelationshipDetail__VariableAssignment_1
                    {
                    pushFollow(FOLLOW_2);
                    rule__RelationshipDetail__VariableAssignment_1();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getRelationshipDetailAccess().getVariableAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipDetail__Group__1__Impl"


    // $ANTLR start "rule__RelationshipDetail__Group__2"
    // InternalMyDsl.g:1373:1: rule__RelationshipDetail__Group__2 : rule__RelationshipDetail__Group__2__Impl rule__RelationshipDetail__Group__3 ;
    public final void rule__RelationshipDetail__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1377:1: ( rule__RelationshipDetail__Group__2__Impl rule__RelationshipDetail__Group__3 )
            // InternalMyDsl.g:1378:2: rule__RelationshipDetail__Group__2__Impl rule__RelationshipDetail__Group__3
            {
            pushFollow(FOLLOW_13);
            rule__RelationshipDetail__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RelationshipDetail__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipDetail__Group__2"


    // $ANTLR start "rule__RelationshipDetail__Group__2__Impl"
    // InternalMyDsl.g:1385:1: rule__RelationshipDetail__Group__2__Impl : ( ':' ) ;
    public final void rule__RelationshipDetail__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1389:1: ( ( ':' ) )
            // InternalMyDsl.g:1390:1: ( ':' )
            {
            // InternalMyDsl.g:1390:1: ( ':' )
            // InternalMyDsl.g:1391:2: ':'
            {
             before(grammarAccess.getRelationshipDetailAccess().getColonKeyword_2()); 
            match(input,35,FOLLOW_2); 
             after(grammarAccess.getRelationshipDetailAccess().getColonKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipDetail__Group__2__Impl"


    // $ANTLR start "rule__RelationshipDetail__Group__3"
    // InternalMyDsl.g:1400:1: rule__RelationshipDetail__Group__3 : rule__RelationshipDetail__Group__3__Impl rule__RelationshipDetail__Group__4 ;
    public final void rule__RelationshipDetail__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1404:1: ( rule__RelationshipDetail__Group__3__Impl rule__RelationshipDetail__Group__4 )
            // InternalMyDsl.g:1405:2: rule__RelationshipDetail__Group__3__Impl rule__RelationshipDetail__Group__4
            {
            pushFollow(FOLLOW_13);
            rule__RelationshipDetail__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__RelationshipDetail__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipDetail__Group__3"


    // $ANTLR start "rule__RelationshipDetail__Group__3__Impl"
    // InternalMyDsl.g:1412:1: rule__RelationshipDetail__Group__3__Impl : ( ( rule__RelationshipDetail__LabelAssignment_3 )? ) ;
    public final void rule__RelationshipDetail__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1416:1: ( ( ( rule__RelationshipDetail__LabelAssignment_3 )? ) )
            // InternalMyDsl.g:1417:1: ( ( rule__RelationshipDetail__LabelAssignment_3 )? )
            {
            // InternalMyDsl.g:1417:1: ( ( rule__RelationshipDetail__LabelAssignment_3 )? )
            // InternalMyDsl.g:1418:2: ( rule__RelationshipDetail__LabelAssignment_3 )?
            {
             before(grammarAccess.getRelationshipDetailAccess().getLabelAssignment_3()); 
            // InternalMyDsl.g:1419:2: ( rule__RelationshipDetail__LabelAssignment_3 )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==RULE_ID) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalMyDsl.g:1419:3: rule__RelationshipDetail__LabelAssignment_3
                    {
                    pushFollow(FOLLOW_2);
                    rule__RelationshipDetail__LabelAssignment_3();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getRelationshipDetailAccess().getLabelAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipDetail__Group__3__Impl"


    // $ANTLR start "rule__RelationshipDetail__Group__4"
    // InternalMyDsl.g:1427:1: rule__RelationshipDetail__Group__4 : rule__RelationshipDetail__Group__4__Impl ;
    public final void rule__RelationshipDetail__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1431:1: ( rule__RelationshipDetail__Group__4__Impl )
            // InternalMyDsl.g:1432:2: rule__RelationshipDetail__Group__4__Impl
            {
            pushFollow(FOLLOW_2);
            rule__RelationshipDetail__Group__4__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipDetail__Group__4"


    // $ANTLR start "rule__RelationshipDetail__Group__4__Impl"
    // InternalMyDsl.g:1438:1: rule__RelationshipDetail__Group__4__Impl : ( ']' ) ;
    public final void rule__RelationshipDetail__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1442:1: ( ( ']' ) )
            // InternalMyDsl.g:1443:1: ( ']' )
            {
            // InternalMyDsl.g:1443:1: ( ']' )
            // InternalMyDsl.g:1444:2: ']'
            {
             before(grammarAccess.getRelationshipDetailAccess().getRightSquareBracketKeyword_4()); 
            match(input,38,FOLLOW_2); 
             after(grammarAccess.getRelationshipDetailAccess().getRightSquareBracketKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipDetail__Group__4__Impl"


    // $ANTLR start "rule__CypherQuery__QueriesAssignment"
    // InternalMyDsl.g:1454:1: rule__CypherQuery__QueriesAssignment : ( ruleQuery ) ;
    public final void rule__CypherQuery__QueriesAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1458:1: ( ( ruleQuery ) )
            // InternalMyDsl.g:1459:2: ( ruleQuery )
            {
            // InternalMyDsl.g:1459:2: ( ruleQuery )
            // InternalMyDsl.g:1460:3: ruleQuery
            {
             before(grammarAccess.getCypherQueryAccess().getQueriesQueryParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            ruleQuery();

            state._fsp--;

             after(grammarAccess.getCypherQueryAccess().getQueriesQueryParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__CypherQuery__QueriesAssignment"


    // $ANTLR start "rule__Query__PatternAssignment_1"
    // InternalMyDsl.g:1469:1: rule__Query__PatternAssignment_1 : ( rulePattern ) ;
    public final void rule__Query__PatternAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1473:1: ( ( rulePattern ) )
            // InternalMyDsl.g:1474:2: ( rulePattern )
            {
            // InternalMyDsl.g:1474:2: ( rulePattern )
            // InternalMyDsl.g:1475:3: rulePattern
            {
             before(grammarAccess.getQueryAccess().getPatternPatternParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            rulePattern();

            state._fsp--;

             after(grammarAccess.getQueryAccess().getPatternPatternParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Query__PatternAssignment_1"


    // $ANTLR start "rule__Pattern__PartsAssignment"
    // InternalMyDsl.g:1484:1: rule__Pattern__PartsAssignment : ( rulePatternPart ) ;
    public final void rule__Pattern__PartsAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1488:1: ( ( rulePatternPart ) )
            // InternalMyDsl.g:1489:2: ( rulePatternPart )
            {
            // InternalMyDsl.g:1489:2: ( rulePatternPart )
            // InternalMyDsl.g:1490:3: rulePatternPart
            {
             before(grammarAccess.getPatternAccess().getPartsPatternPartParserRuleCall_0()); 
            pushFollow(FOLLOW_2);
            rulePatternPart();

            state._fsp--;

             after(grammarAccess.getPatternAccess().getPartsPatternPartParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Pattern__PartsAssignment"


    // $ANTLR start "rule__PatternPart__NodeAssignment_0"
    // InternalMyDsl.g:1499:1: rule__PatternPart__NodeAssignment_0 : ( ruleNodePattern ) ;
    public final void rule__PatternPart__NodeAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1503:1: ( ( ruleNodePattern ) )
            // InternalMyDsl.g:1504:2: ( ruleNodePattern )
            {
            // InternalMyDsl.g:1504:2: ( ruleNodePattern )
            // InternalMyDsl.g:1505:3: ruleNodePattern
            {
             before(grammarAccess.getPatternPartAccess().getNodeNodePatternParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleNodePattern();

            state._fsp--;

             after(grammarAccess.getPatternPartAccess().getNodeNodePatternParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PatternPart__NodeAssignment_0"


    // $ANTLR start "rule__PatternPart__ChainAssignment_1"
    // InternalMyDsl.g:1514:1: rule__PatternPart__ChainAssignment_1 : ( rulePatternElementChain ) ;
    public final void rule__PatternPart__ChainAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1518:1: ( ( rulePatternElementChain ) )
            // InternalMyDsl.g:1519:2: ( rulePatternElementChain )
            {
            // InternalMyDsl.g:1519:2: ( rulePatternElementChain )
            // InternalMyDsl.g:1520:3: rulePatternElementChain
            {
             before(grammarAccess.getPatternPartAccess().getChainPatternElementChainParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            rulePatternElementChain();

            state._fsp--;

             after(grammarAccess.getPatternPartAccess().getChainPatternElementChainParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PatternPart__ChainAssignment_1"


    // $ANTLR start "rule__NodePattern__VariableAssignment_1"
    // InternalMyDsl.g:1529:1: rule__NodePattern__VariableAssignment_1 : ( ruleVariable ) ;
    public final void rule__NodePattern__VariableAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1533:1: ( ( ruleVariable ) )
            // InternalMyDsl.g:1534:2: ( ruleVariable )
            {
            // InternalMyDsl.g:1534:2: ( ruleVariable )
            // InternalMyDsl.g:1535:3: ruleVariable
            {
             before(grammarAccess.getNodePatternAccess().getVariableVariableParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleVariable();

            state._fsp--;

             after(grammarAccess.getNodePatternAccess().getVariableVariableParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NodePattern__VariableAssignment_1"


    // $ANTLR start "rule__NodePattern__LabelAssignment_3"
    // InternalMyDsl.g:1544:1: rule__NodePattern__LabelAssignment_3 : ( ruleLabel ) ;
    public final void rule__NodePattern__LabelAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1548:1: ( ( ruleLabel ) )
            // InternalMyDsl.g:1549:2: ( ruleLabel )
            {
            // InternalMyDsl.g:1549:2: ( ruleLabel )
            // InternalMyDsl.g:1550:3: ruleLabel
            {
             before(grammarAccess.getNodePatternAccess().getLabelLabelParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleLabel();

            state._fsp--;

             after(grammarAccess.getNodePatternAccess().getLabelLabelParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__NodePattern__LabelAssignment_3"


    // $ANTLR start "rule__PatternElementChain__RelationshipPatternAssignment_0"
    // InternalMyDsl.g:1559:1: rule__PatternElementChain__RelationshipPatternAssignment_0 : ( ruleRelationshipPattern ) ;
    public final void rule__PatternElementChain__RelationshipPatternAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1563:1: ( ( ruleRelationshipPattern ) )
            // InternalMyDsl.g:1564:2: ( ruleRelationshipPattern )
            {
            // InternalMyDsl.g:1564:2: ( ruleRelationshipPattern )
            // InternalMyDsl.g:1565:3: ruleRelationshipPattern
            {
             before(grammarAccess.getPatternElementChainAccess().getRelationshipPatternRelationshipPatternParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleRelationshipPattern();

            state._fsp--;

             after(grammarAccess.getPatternElementChainAccess().getRelationshipPatternRelationshipPatternParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PatternElementChain__RelationshipPatternAssignment_0"


    // $ANTLR start "rule__PatternElementChain__NodePatternAssignment_1"
    // InternalMyDsl.g:1574:1: rule__PatternElementChain__NodePatternAssignment_1 : ( ruleNodePattern ) ;
    public final void rule__PatternElementChain__NodePatternAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1578:1: ( ( ruleNodePattern ) )
            // InternalMyDsl.g:1579:2: ( ruleNodePattern )
            {
            // InternalMyDsl.g:1579:2: ( ruleNodePattern )
            // InternalMyDsl.g:1580:3: ruleNodePattern
            {
             before(grammarAccess.getPatternElementChainAccess().getNodePatternNodePatternParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleNodePattern();

            state._fsp--;

             after(grammarAccess.getPatternElementChainAccess().getNodePatternNodePatternParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PatternElementChain__NodePatternAssignment_1"


    // $ANTLR start "rule__RelationshipDetail__VariableAssignment_1"
    // InternalMyDsl.g:1589:1: rule__RelationshipDetail__VariableAssignment_1 : ( ruleVariable ) ;
    public final void rule__RelationshipDetail__VariableAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1593:1: ( ( ruleVariable ) )
            // InternalMyDsl.g:1594:2: ( ruleVariable )
            {
            // InternalMyDsl.g:1594:2: ( ruleVariable )
            // InternalMyDsl.g:1595:3: ruleVariable
            {
             before(grammarAccess.getRelationshipDetailAccess().getVariableVariableParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleVariable();

            state._fsp--;

             after(grammarAccess.getRelationshipDetailAccess().getVariableVariableParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipDetail__VariableAssignment_1"


    // $ANTLR start "rule__RelationshipDetail__LabelAssignment_3"
    // InternalMyDsl.g:1604:1: rule__RelationshipDetail__LabelAssignment_3 : ( ruleLabel ) ;
    public final void rule__RelationshipDetail__LabelAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1608:1: ( ( ruleLabel ) )
            // InternalMyDsl.g:1609:2: ( ruleLabel )
            {
            // InternalMyDsl.g:1609:2: ( ruleLabel )
            // InternalMyDsl.g:1610:3: ruleLabel
            {
             before(grammarAccess.getRelationshipDetailAccess().getLabelLabelParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleLabel();

            state._fsp--;

             after(grammarAccess.getRelationshipDetailAccess().getLabelLabelParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__RelationshipDetail__LabelAssignment_3"


    // $ANTLR start "rule__Variable__NameAssignment"
    // InternalMyDsl.g:1619:1: rule__Variable__NameAssignment : ( RULE_ID ) ;
    public final void rule__Variable__NameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1623:1: ( ( RULE_ID ) )
            // InternalMyDsl.g:1624:2: ( RULE_ID )
            {
            // InternalMyDsl.g:1624:2: ( RULE_ID )
            // InternalMyDsl.g:1625:3: RULE_ID
            {
             before(grammarAccess.getVariableAccess().getNameIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getVariableAccess().getNameIDTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Variable__NameAssignment"


    // $ANTLR start "rule__Label__NameAssignment"
    // InternalMyDsl.g:1634:1: rule__Label__NameAssignment : ( RULE_ID ) ;
    public final void rule__Label__NameAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalMyDsl.g:1638:1: ( ( RULE_ID ) )
            // InternalMyDsl.g:1639:2: ( RULE_ID )
            {
            // InternalMyDsl.g:1639:2: ( RULE_ID )
            // InternalMyDsl.g:1640:3: RULE_ID
            {
             before(grammarAccess.getLabelAccess().getNameIDTerminalRuleCall_0()); 
            match(input,RULE_ID,FOLLOW_2); 
             after(grammarAccess.getLabelAccess().getNameIDTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Label__NameAssignment"

    // Delegated rules


    protected DFA3 dfa3 = new DFA3(this);
    static final String dfa_1s = "\104\uffff";
    static final String dfa_2s = "\37\uffff\14\72\1\uffff\14\77\14\uffff";
    static final String dfa_3s = "\1\13\35\25\1\4\14\20\1\4\14\20\1\43\1\4\2\uffff\1\43\1\4\2\uffff\1\46\1\25\1\46\1\25";
    static final String dfa_4s = "\6\40\30\45\1\43\14\42\1\43\14\42\1\43\1\46\2\uffff\1\43\1\46\2\uffff\1\46\1\40\1\46\1\40";
    static final String dfa_5s = "\72\uffff\1\4\1\3\2\uffff\1\1\1\2\4\uffff";
    static final String dfa_6s = "\104\uffff}>";
    static final String[] dfa_7s = {
            "\1\1\1\2\1\3\1\4\1\5\5\uffff\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\1\17\1\20\1\21",
            "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34\1\35",
            "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34\1\35",
            "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34\1\35",
            "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34\1\35",
            "\1\22\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1\32\1\33\1\34\1\35",
            "\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52\4\uffff\1\36",
            "\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52\4\uffff\1\36",
            "\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52\4\uffff\1\36",
            "\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52\4\uffff\1\36",
            "\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52\4\uffff\1\36",
            "\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52\4\uffff\1\36",
            "\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52\4\uffff\1\36",
            "\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52\4\uffff\1\36",
            "\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52\4\uffff\1\36",
            "\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52\4\uffff\1\36",
            "\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52\4\uffff\1\36",
            "\1\37\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50\1\51\1\52\4\uffff\1\36",
            "\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67\4\uffff\1\53",
            "\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67\4\uffff\1\53",
            "\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67\4\uffff\1\53",
            "\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67\4\uffff\1\53",
            "\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67\4\uffff\1\53",
            "\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67\4\uffff\1\53",
            "\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67\4\uffff\1\53",
            "\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67\4\uffff\1\53",
            "\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67\4\uffff\1\53",
            "\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67\4\uffff\1\53",
            "\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67\4\uffff\1\53",
            "\1\54\1\55\1\56\1\57\1\60\1\61\1\62\1\63\1\64\1\65\1\66\1\67\4\uffff\1\53",
            "\1\70\36\uffff\1\71",
            "\5\73\15\uffff\1\72",
            "\5\73\15\uffff\1\72",
            "\5\73\15\uffff\1\72",
            "\5\73\15\uffff\1\72",
            "\5\73\15\uffff\1\72",
            "\5\73\15\uffff\1\72",
            "\5\73\15\uffff\1\72",
            "\5\73\15\uffff\1\72",
            "\5\73\15\uffff\1\72",
            "\5\73\15\uffff\1\72",
            "\5\73\15\uffff\1\72",
            "\5\73\15\uffff\1\72",
            "\1\74\36\uffff\1\75",
            "\5\76\15\uffff\1\77",
            "\5\76\15\uffff\1\77",
            "\5\76\15\uffff\1\77",
            "\5\76\15\uffff\1\77",
            "\5\76\15\uffff\1\77",
            "\5\76\15\uffff\1\77",
            "\5\76\15\uffff\1\77",
            "\5\76\15\uffff\1\77",
            "\5\76\15\uffff\1\77",
            "\5\76\15\uffff\1\77",
            "\5\76\15\uffff\1\77",
            "\5\76\15\uffff\1\77",
            "\1\71",
            "\1\100\41\uffff\1\101",
            "",
            "",
            "\1\75",
            "\1\102\41\uffff\1\103",
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

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = dfa_1;
            this.eof = dfa_2;
            this.min = dfa_3;
            this.max = dfa_4;
            this.accept = dfa_5;
            this.special = dfa_6;
            this.transition = dfa_7;
        }
        public String getDescription() {
            return "391:1: rule__RelationshipPattern__Alternatives : ( ( ( rule__RelationshipPattern__Group_0__0 ) ) | ( ( rule__RelationshipPattern__Group_1__0 ) ) | ( ( rule__RelationshipPattern__Group_2__0 ) ) | ( ( rule__RelationshipPattern__Group_3__0 ) ) );";
        }
    }
 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x00000001FFE0F800L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x00000001FFE0F802L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000800000010L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000001000000010L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x00000001FFE00000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x00000021FFE00000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x00000000001F0000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000004000000010L});

}