package ingraph.report.featuregrammar.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalFeatureLexer extends Lexer {
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

    public InternalFeatureLexer() {;} 
    public InternalFeatureLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalFeatureLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "InternalFeature.g"; }

    // $ANTLR start "RULE_EXAMPLE_HEADING"
    public final void mRULE_EXAMPLE_HEADING() throws RecognitionException {
        try {
            int _type = RULE_EXAMPLE_HEADING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1197:22: ( 'Examples' RULE_SPACES ':' RULE_SPACES ( '\\r' )? '\\n' )
            // InternalFeature.g:1197:24: 'Examples' RULE_SPACES ':' RULE_SPACES ( '\\r' )? '\\n'
            {
            match("Examples"); 

            mRULE_SPACES(); 
            match(':'); 
            mRULE_SPACES(); 
            // InternalFeature.g:1197:63: ( '\\r' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='\r') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // InternalFeature.g:1197:63: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_EXAMPLE_HEADING"

    // $ANTLR start "RULE_NNL"
    public final void mRULE_NNL() throws RecognitionException {
        try {
            // InternalFeature.g:1199:19: (~ ( ( '\\r' | '\\n' ) ) )
            // InternalFeature.g:1199:21: ~ ( ( '\\r' | '\\n' ) )
            {
            if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_NNL"

    // $ANTLR start "RULE_NL"
    public final void mRULE_NL() throws RecognitionException {
        try {
            // InternalFeature.g:1201:18: ( ( '\\r' )? '\\n' )
            // InternalFeature.g:1201:20: ( '\\r' )? '\\n'
            {
            // InternalFeature.g:1201:20: ( '\\r' )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='\r') ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalFeature.g:1201:20: '\\r'
                    {
                    match('\r'); 

                    }
                    break;

            }

            match('\n'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_NL"

    // $ANTLR start "RULE_SPACES"
    public final void mRULE_SPACES() throws RecognitionException {
        try {
            // InternalFeature.g:1203:22: ( ( ' ' | '\\t' )* )
            // InternalFeature.g:1203:24: ( ' ' | '\\t' )*
            {
            // InternalFeature.g:1203:24: ( ' ' | '\\t' )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='\t'||LA3_0==' ') ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalFeature.g:
            	    {
            	    if ( input.LA(1)=='\t'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_SPACES"

    // $ANTLR start "RULE_FEATURE_TEXT"
    public final void mRULE_FEATURE_TEXT() throws RecognitionException {
        try {
            int _type = RULE_FEATURE_TEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1205:19: ( ( 'Narrative:' | 'Feature:' ) ( RULE_NNL )* RULE_NL )
            // InternalFeature.g:1205:21: ( 'Narrative:' | 'Feature:' ) ( RULE_NNL )* RULE_NL
            {
            // InternalFeature.g:1205:21: ( 'Narrative:' | 'Feature:' )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='N') ) {
                alt4=1;
            }
            else if ( (LA4_0=='F') ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // InternalFeature.g:1205:22: 'Narrative:'
                    {
                    match("Narrative:"); 


                    }
                    break;
                case 2 :
                    // InternalFeature.g:1205:35: 'Feature:'
                    {
                    match("Feature:"); 


                    }
                    break;

            }

            // InternalFeature.g:1205:47: ( RULE_NNL )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='\u0000' && LA5_0<='\t')||(LA5_0>='\u000B' && LA5_0<='\f')||(LA5_0>='\u000E' && LA5_0<='\uFFFF')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalFeature.g:1205:47: RULE_NNL
            	    {
            	    mRULE_NNL(); 

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            mRULE_NL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_FEATURE_TEXT"

    // $ANTLR start "RULE_IN_ORDER_TO"
    public final void mRULE_IN_ORDER_TO() throws RecognitionException {
        try {
            int _type = RULE_IN_ORDER_TO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1207:18: ( 'In order to' ( RULE_NNL )* RULE_NL )
            // InternalFeature.g:1207:20: 'In order to' ( RULE_NNL )* RULE_NL
            {
            match("In order to"); 

            // InternalFeature.g:1207:34: ( RULE_NNL )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='\u0000' && LA6_0<='\t')||(LA6_0>='\u000B' && LA6_0<='\f')||(LA6_0>='\u000E' && LA6_0<='\uFFFF')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalFeature.g:1207:34: RULE_NNL
            	    {
            	    mRULE_NNL(); 

            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            mRULE_NL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_IN_ORDER_TO"

    // $ANTLR start "RULE_AS_A"
    public final void mRULE_AS_A() throws RecognitionException {
        try {
            int _type = RULE_AS_A;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1209:11: ( 'As a' ( RULE_NNL )* RULE_NL )
            // InternalFeature.g:1209:13: 'As a' ( RULE_NNL )* RULE_NL
            {
            match("As a"); 

            // InternalFeature.g:1209:20: ( RULE_NNL )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='\u0000' && LA7_0<='\t')||(LA7_0>='\u000B' && LA7_0<='\f')||(LA7_0>='\u000E' && LA7_0<='\uFFFF')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalFeature.g:1209:20: RULE_NNL
            	    {
            	    mRULE_NNL(); 

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            mRULE_NL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_AS_A"

    // $ANTLR start "RULE_I_WANT_TO"
    public final void mRULE_I_WANT_TO() throws RecognitionException {
        try {
            int _type = RULE_I_WANT_TO;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1211:16: ( 'I want to ' ( RULE_NNL )* RULE_NL )
            // InternalFeature.g:1211:18: 'I want to ' ( RULE_NNL )* RULE_NL
            {
            match("I want to "); 

            // InternalFeature.g:1211:31: ( RULE_NNL )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='\u0000' && LA8_0<='\t')||(LA8_0>='\u000B' && LA8_0<='\f')||(LA8_0>='\u000E' && LA8_0<='\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalFeature.g:1211:31: RULE_NNL
            	    {
            	    mRULE_NNL(); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            mRULE_NL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_I_WANT_TO"

    // $ANTLR start "RULE_SCENARIO_TEXT"
    public final void mRULE_SCENARIO_TEXT() throws RecognitionException {
        try {
            int _type = RULE_SCENARIO_TEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1213:20: ( 'Scenario' RULE_SPACES ':' ( RULE_NNL )* RULE_NL )
            // InternalFeature.g:1213:22: 'Scenario' RULE_SPACES ':' ( RULE_NNL )* RULE_NL
            {
            match("Scenario"); 

            mRULE_SPACES(); 
            match(':'); 
            // InternalFeature.g:1213:49: ( RULE_NNL )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>='\u0000' && LA9_0<='\t')||(LA9_0>='\u000B' && LA9_0<='\f')||(LA9_0>='\u000E' && LA9_0<='\uFFFF')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalFeature.g:1213:49: RULE_NNL
            	    {
            	    mRULE_NNL(); 

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            mRULE_NL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SCENARIO_TEXT"

    // $ANTLR start "RULE_SCENARIO_OUTLINE_TEXT"
    public final void mRULE_SCENARIO_OUTLINE_TEXT() throws RecognitionException {
        try {
            int _type = RULE_SCENARIO_OUTLINE_TEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1215:28: ( 'Scenario' RULE_SPACES 'Outline' RULE_SPACES ':' ( RULE_NNL )* RULE_NL )
            // InternalFeature.g:1215:30: 'Scenario' RULE_SPACES 'Outline' RULE_SPACES ':' ( RULE_NNL )* RULE_NL
            {
            match("Scenario"); 

            mRULE_SPACES(); 
            match("Outline"); 

            mRULE_SPACES(); 
            match(':'); 
            // InternalFeature.g:1215:79: ( RULE_NNL )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='\u0000' && LA10_0<='\t')||(LA10_0>='\u000B' && LA10_0<='\f')||(LA10_0>='\u000E' && LA10_0<='\uFFFF')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalFeature.g:1215:79: RULE_NNL
            	    {
            	    mRULE_NNL(); 

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            mRULE_NL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SCENARIO_OUTLINE_TEXT"

    // $ANTLR start "RULE_BACKGROUND_TEXT"
    public final void mRULE_BACKGROUND_TEXT() throws RecognitionException {
        try {
            int _type = RULE_BACKGROUND_TEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1217:22: ( 'Background' RULE_SPACES ':' ( RULE_NNL )* RULE_NL )
            // InternalFeature.g:1217:24: 'Background' RULE_SPACES ':' ( RULE_NNL )* RULE_NL
            {
            match("Background"); 

            mRULE_SPACES(); 
            match(':'); 
            // InternalFeature.g:1217:53: ( RULE_NNL )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>='\u0000' && LA11_0<='\t')||(LA11_0>='\u000B' && LA11_0<='\f')||(LA11_0>='\u000E' && LA11_0<='\uFFFF')) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalFeature.g:1217:53: RULE_NNL
            	    {
            	    mRULE_NNL(); 

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

            mRULE_NL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_BACKGROUND_TEXT"

    // $ANTLR start "RULE_WHEN_TEXT"
    public final void mRULE_WHEN_TEXT() throws RecognitionException {
        try {
            int _type = RULE_WHEN_TEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1219:16: ( 'When ' ( RULE_NNL )* RULE_NL )
            // InternalFeature.g:1219:18: 'When ' ( RULE_NNL )* RULE_NL
            {
            match("When "); 

            // InternalFeature.g:1219:26: ( RULE_NNL )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>='\u0000' && LA12_0<='\t')||(LA12_0>='\u000B' && LA12_0<='\f')||(LA12_0>='\u000E' && LA12_0<='\uFFFF')) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalFeature.g:1219:26: RULE_NNL
            	    {
            	    mRULE_NNL(); 

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            mRULE_NL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WHEN_TEXT"

    // $ANTLR start "RULE_THEN_TEXT"
    public final void mRULE_THEN_TEXT() throws RecognitionException {
        try {
            int _type = RULE_THEN_TEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1221:16: ( 'Then ' ( RULE_NNL )* RULE_NL )
            // InternalFeature.g:1221:18: 'Then ' ( RULE_NNL )* RULE_NL
            {
            match("Then "); 

            // InternalFeature.g:1221:26: ( RULE_NNL )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>='\u0000' && LA13_0<='\t')||(LA13_0>='\u000B' && LA13_0<='\f')||(LA13_0>='\u000E' && LA13_0<='\uFFFF')) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalFeature.g:1221:26: RULE_NNL
            	    {
            	    mRULE_NNL(); 

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);

            mRULE_NL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_THEN_TEXT"

    // $ANTLR start "RULE_GIVEN_TEXT"
    public final void mRULE_GIVEN_TEXT() throws RecognitionException {
        try {
            int _type = RULE_GIVEN_TEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1223:17: ( 'Given ' ( RULE_NNL )* RULE_NL )
            // InternalFeature.g:1223:19: 'Given ' ( RULE_NNL )* RULE_NL
            {
            match("Given "); 

            // InternalFeature.g:1223:28: ( RULE_NNL )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>='\u0000' && LA14_0<='\t')||(LA14_0>='\u000B' && LA14_0<='\f')||(LA14_0>='\u000E' && LA14_0<='\uFFFF')) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalFeature.g:1223:28: RULE_NNL
            	    {
            	    mRULE_NNL(); 

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);

            mRULE_NL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_GIVEN_TEXT"

    // $ANTLR start "RULE_AND_TEXT"
    public final void mRULE_AND_TEXT() throws RecognitionException {
        try {
            int _type = RULE_AND_TEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1225:15: ( 'And ' ( RULE_NNL )* RULE_NL )
            // InternalFeature.g:1225:17: 'And ' ( RULE_NNL )* RULE_NL
            {
            match("And "); 

            // InternalFeature.g:1225:24: ( RULE_NNL )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>='\u0000' && LA15_0<='\t')||(LA15_0>='\u000B' && LA15_0<='\f')||(LA15_0>='\u000E' && LA15_0<='\uFFFF')) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalFeature.g:1225:24: RULE_NNL
            	    {
            	    mRULE_NNL(); 

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);

            mRULE_NL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_AND_TEXT"

    // $ANTLR start "RULE_EXAMPLE_ROW_END"
    public final void mRULE_EXAMPLE_ROW_END() throws RecognitionException {
        try {
            int _type = RULE_EXAMPLE_ROW_END;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1227:22: ( '|' RULE_SPACES RULE_NL )
            // InternalFeature.g:1227:24: '|' RULE_SPACES RULE_NL
            {
            match('|'); 
            mRULE_SPACES(); 
            mRULE_NL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_EXAMPLE_ROW_END"

    // $ANTLR start "RULE_EXAMPLE_CELL"
    public final void mRULE_EXAMPLE_CELL() throws RecognitionException {
        try {
            int _type = RULE_EXAMPLE_CELL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1229:19: ( '|' (~ ( ( '\\r' | '\\n' | '|' ) ) )+ )
            // InternalFeature.g:1229:21: '|' (~ ( ( '\\r' | '\\n' | '|' ) ) )+
            {
            match('|'); 
            // InternalFeature.g:1229:25: (~ ( ( '\\r' | '\\n' | '|' ) ) )+
            int cnt16=0;
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>='\u0000' && LA16_0<='\t')||(LA16_0>='\u000B' && LA16_0<='\f')||(LA16_0>='\u000E' && LA16_0<='{')||(LA16_0>='}' && LA16_0<='\uFFFF')) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalFeature.g:1229:25: ~ ( ( '\\r' | '\\n' | '|' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='{')||(input.LA(1)>='}' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt16 >= 1 ) break loop16;
                        EarlyExitException eee =
                            new EarlyExitException(16, input);
                        throw eee;
                }
                cnt16++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_EXAMPLE_CELL"

    // $ANTLR start "RULE_CODE"
    public final void mRULE_CODE() throws RecognitionException {
        try {
            int _type = RULE_CODE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1231:11: ( ( '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"' | '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\'' ) )
            // InternalFeature.g:1231:13: ( '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"' | '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\'' )
            {
            // InternalFeature.g:1231:13: ( '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"' | '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\'' )
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0=='\"') ) {
                alt19=1;
            }
            else if ( (LA19_0=='\'') ) {
                alt19=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }
            switch (alt19) {
                case 1 :
                    // InternalFeature.g:1231:14: '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"'
                    {
                    match("\"\"\""); 

                    // InternalFeature.g:1231:20: ( options {greedy=false; } : . )*
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0=='\"') ) {
                            int LA17_1 = input.LA(2);

                            if ( (LA17_1=='\"') ) {
                                int LA17_3 = input.LA(3);

                                if ( (LA17_3=='\"') ) {
                                    alt17=2;
                                }
                                else if ( ((LA17_3>='\u0000' && LA17_3<='!')||(LA17_3>='#' && LA17_3<='\uFFFF')) ) {
                                    alt17=1;
                                }


                            }
                            else if ( ((LA17_1>='\u0000' && LA17_1<='!')||(LA17_1>='#' && LA17_1<='\uFFFF')) ) {
                                alt17=1;
                            }


                        }
                        else if ( ((LA17_0>='\u0000' && LA17_0<='!')||(LA17_0>='#' && LA17_0<='\uFFFF')) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // InternalFeature.g:1231:48: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop17;
                        }
                    } while (true);

                    match("\"\"\""); 


                    }
                    break;
                case 2 :
                    // InternalFeature.g:1231:58: '\\'\\'\\'' ( options {greedy=false; } : . )* '\\'\\'\\''
                    {
                    match("'''"); 

                    // InternalFeature.g:1231:67: ( options {greedy=false; } : . )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0=='\'') ) {
                            int LA18_1 = input.LA(2);

                            if ( (LA18_1=='\'') ) {
                                int LA18_3 = input.LA(3);

                                if ( (LA18_3=='\'') ) {
                                    alt18=2;
                                }
                                else if ( ((LA18_3>='\u0000' && LA18_3<='&')||(LA18_3>='(' && LA18_3<='\uFFFF')) ) {
                                    alt18=1;
                                }


                            }
                            else if ( ((LA18_1>='\u0000' && LA18_1<='&')||(LA18_1>='(' && LA18_1<='\uFFFF')) ) {
                                alt18=1;
                            }


                        }
                        else if ( ((LA18_0>='\u0000' && LA18_0<='&')||(LA18_0>='(' && LA18_0<='\uFFFF')) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // InternalFeature.g:1231:95: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop18;
                        }
                    } while (true);

                    match("'''"); 


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_CODE"

    // $ANTLR start "RULE_TAG"
    public final void mRULE_TAG() throws RecognitionException {
        try {
            int _type = RULE_TAG;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1233:10: ( '@' ( RULE_NNL )+ RULE_NL )
            // InternalFeature.g:1233:12: '@' ( RULE_NNL )+ RULE_NL
            {
            match('@'); 
            // InternalFeature.g:1233:16: ( RULE_NNL )+
            int cnt20=0;
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( ((LA20_0>='\u0000' && LA20_0<='\t')||(LA20_0>='\u000B' && LA20_0<='\f')||(LA20_0>='\u000E' && LA20_0<='\uFFFF')) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // InternalFeature.g:1233:16: RULE_NNL
            	    {
            	    mRULE_NNL(); 

            	    }
            	    break;

            	default :
            	    if ( cnt20 >= 1 ) break loop20;
                        EarlyExitException eee =
                            new EarlyExitException(20, input);
                        throw eee;
                }
                cnt20++;
            } while (true);

            mRULE_NL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_TAG"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1235:17: ( '#' ( RULE_NNL )* RULE_NL )
            // InternalFeature.g:1235:19: '#' ( RULE_NNL )* RULE_NL
            {
            match('#'); 
            // InternalFeature.g:1235:23: ( RULE_NNL )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0>='\u0000' && LA21_0<='\t')||(LA21_0>='\u000B' && LA21_0<='\f')||(LA21_0>='\u000E' && LA21_0<='\uFFFF')) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalFeature.g:1235:23: RULE_NNL
            	    {
            	    mRULE_NNL(); 

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);

            mRULE_NL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_TEXT"
    public final void mRULE_TEXT() throws RecognitionException {
        try {
            int _type = RULE_TEXT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1237:11: (~ ( ( '|' | ' ' | '\\t' | '\\r' | '\\n' | '@' ) ) ( RULE_NNL )* RULE_NL )
            // InternalFeature.g:1237:13: ~ ( ( '|' | ' ' | '\\t' | '\\r' | '\\n' | '@' ) ) ( RULE_NNL )* RULE_NL
            {
            if ( (input.LA(1)>='\u0000' && input.LA(1)<='\b')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\u001F')||(input.LA(1)>='!' && input.LA(1)<='?')||(input.LA(1)>='A' && input.LA(1)<='{')||(input.LA(1)>='}' && input.LA(1)<='\uFFFF') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalFeature.g:1237:45: ( RULE_NNL )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>='\u0000' && LA22_0<='\t')||(LA22_0>='\u000B' && LA22_0<='\f')||(LA22_0>='\u000E' && LA22_0<='\uFFFF')) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalFeature.g:1237:45: RULE_NNL
            	    {
            	    mRULE_NNL(); 

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            mRULE_NL(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_TEXT"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1239:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // InternalFeature.g:1239:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // InternalFeature.g:1239:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt23=0;
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( ((LA23_0>='\t' && LA23_0<='\n')||LA23_0=='\r'||LA23_0==' ') ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalFeature.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt23 >= 1 ) break loop23;
                        EarlyExitException eee =
                            new EarlyExitException(23, input);
                        throw eee;
                }
                cnt23++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1241:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // InternalFeature.g:1241:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // InternalFeature.g:1241:11: ( '^' )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0=='^') ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // InternalFeature.g:1241:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalFeature.g:1241:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( ((LA25_0>='0' && LA25_0<='9')||(LA25_0>='A' && LA25_0<='Z')||LA25_0=='_'||(LA25_0>='a' && LA25_0<='z')) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // InternalFeature.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1243:10: ( ( '0' .. '9' )+ )
            // InternalFeature.g:1243:12: ( '0' .. '9' )+
            {
            // InternalFeature.g:1243:12: ( '0' .. '9' )+
            int cnt26=0;
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0>='0' && LA26_0<='9')) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // InternalFeature.g:1243:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt26 >= 1 ) break loop26;
                        EarlyExitException eee =
                            new EarlyExitException(26, input);
                        throw eee;
                }
                cnt26++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1245:13: ( ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // InternalFeature.g:1245:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // InternalFeature.g:1245:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0=='\"') ) {
                alt29=1;
            }
            else if ( (LA29_0=='\'') ) {
                alt29=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // InternalFeature.g:1245:16: '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // InternalFeature.g:1245:20: ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop27:
                    do {
                        int alt27=3;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0=='\\') ) {
                            alt27=1;
                        }
                        else if ( ((LA27_0>='\u0000' && LA27_0<='!')||(LA27_0>='#' && LA27_0<='[')||(LA27_0>=']' && LA27_0<='\uFFFF')) ) {
                            alt27=2;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // InternalFeature.g:1245:21: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalFeature.g:1245:28: ~ ( ( '\\\\' | '\"' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop27;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // InternalFeature.g:1245:48: '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // InternalFeature.g:1245:53: ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop28:
                    do {
                        int alt28=3;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0=='\\') ) {
                            alt28=1;
                        }
                        else if ( ((LA28_0>='\u0000' && LA28_0<='&')||(LA28_0>='(' && LA28_0<='[')||(LA28_0>=']' && LA28_0<='\uFFFF')) ) {
                            alt28=2;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // InternalFeature.g:1245:54: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalFeature.g:1245:61: ~ ( ( '\\\\' | '\\'' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop28;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1247:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // InternalFeature.g:1247:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // InternalFeature.g:1247:24: ( options {greedy=false; } : . )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0=='*') ) {
                    int LA30_1 = input.LA(2);

                    if ( (LA30_1=='/') ) {
                        alt30=2;
                    }
                    else if ( ((LA30_1>='\u0000' && LA30_1<='.')||(LA30_1>='0' && LA30_1<='\uFFFF')) ) {
                        alt30=1;
                    }


                }
                else if ( ((LA30_0>='\u0000' && LA30_0<=')')||(LA30_0>='+' && LA30_0<='\uFFFF')) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // InternalFeature.g:1247:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalFeature.g:1249:16: ( . )
            // InternalFeature.g:1249:18: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    public void mTokens() throws RecognitionException {
        // InternalFeature.g:1:8: ( RULE_EXAMPLE_HEADING | RULE_FEATURE_TEXT | RULE_IN_ORDER_TO | RULE_AS_A | RULE_I_WANT_TO | RULE_SCENARIO_TEXT | RULE_SCENARIO_OUTLINE_TEXT | RULE_BACKGROUND_TEXT | RULE_WHEN_TEXT | RULE_THEN_TEXT | RULE_GIVEN_TEXT | RULE_AND_TEXT | RULE_EXAMPLE_ROW_END | RULE_EXAMPLE_CELL | RULE_CODE | RULE_TAG | RULE_SL_COMMENT | RULE_TEXT | RULE_WS | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_ANY_OTHER )
        int alt31=24;
        alt31 = dfa31.predict(input);
        switch (alt31) {
            case 1 :
                // InternalFeature.g:1:10: RULE_EXAMPLE_HEADING
                {
                mRULE_EXAMPLE_HEADING(); 

                }
                break;
            case 2 :
                // InternalFeature.g:1:31: RULE_FEATURE_TEXT
                {
                mRULE_FEATURE_TEXT(); 

                }
                break;
            case 3 :
                // InternalFeature.g:1:49: RULE_IN_ORDER_TO
                {
                mRULE_IN_ORDER_TO(); 

                }
                break;
            case 4 :
                // InternalFeature.g:1:66: RULE_AS_A
                {
                mRULE_AS_A(); 

                }
                break;
            case 5 :
                // InternalFeature.g:1:76: RULE_I_WANT_TO
                {
                mRULE_I_WANT_TO(); 

                }
                break;
            case 6 :
                // InternalFeature.g:1:91: RULE_SCENARIO_TEXT
                {
                mRULE_SCENARIO_TEXT(); 

                }
                break;
            case 7 :
                // InternalFeature.g:1:110: RULE_SCENARIO_OUTLINE_TEXT
                {
                mRULE_SCENARIO_OUTLINE_TEXT(); 

                }
                break;
            case 8 :
                // InternalFeature.g:1:137: RULE_BACKGROUND_TEXT
                {
                mRULE_BACKGROUND_TEXT(); 

                }
                break;
            case 9 :
                // InternalFeature.g:1:158: RULE_WHEN_TEXT
                {
                mRULE_WHEN_TEXT(); 

                }
                break;
            case 10 :
                // InternalFeature.g:1:173: RULE_THEN_TEXT
                {
                mRULE_THEN_TEXT(); 

                }
                break;
            case 11 :
                // InternalFeature.g:1:188: RULE_GIVEN_TEXT
                {
                mRULE_GIVEN_TEXT(); 

                }
                break;
            case 12 :
                // InternalFeature.g:1:204: RULE_AND_TEXT
                {
                mRULE_AND_TEXT(); 

                }
                break;
            case 13 :
                // InternalFeature.g:1:218: RULE_EXAMPLE_ROW_END
                {
                mRULE_EXAMPLE_ROW_END(); 

                }
                break;
            case 14 :
                // InternalFeature.g:1:239: RULE_EXAMPLE_CELL
                {
                mRULE_EXAMPLE_CELL(); 

                }
                break;
            case 15 :
                // InternalFeature.g:1:257: RULE_CODE
                {
                mRULE_CODE(); 

                }
                break;
            case 16 :
                // InternalFeature.g:1:267: RULE_TAG
                {
                mRULE_TAG(); 

                }
                break;
            case 17 :
                // InternalFeature.g:1:276: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 18 :
                // InternalFeature.g:1:292: RULE_TEXT
                {
                mRULE_TEXT(); 

                }
                break;
            case 19 :
                // InternalFeature.g:1:302: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 20 :
                // InternalFeature.g:1:310: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 21 :
                // InternalFeature.g:1:318: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 22 :
                // InternalFeature.g:1:327: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 23 :
                // InternalFeature.g:1:339: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 24 :
                // InternalFeature.g:1:355: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA31 dfa31 = new DFA31(this);
    static final String DFA31_eotS =
        "\1\uffff\12\30\6\46\1\uffff\1\30\1\71\2\46\2\30\2\uffff\3\30\1\uffff\7\30\1\47\3\uffff\1\111\2\uffff\1\31\1\uffff\1\111\2\uffff\1\31\5\uffff\1\30\2\uffff\1\71\1\uffff\3\30\3\uffff\6\30\3\uffff\1\31\1\uffff\1\111\2\uffff\1\31\1\uffff\1\111\3\uffff\1\31\1\uffff\3\30\4\uffff\5\30\2\uffff\1\31\3\uffff\1\31\1\uffff\1\155\1\uffff\3\30\10\uffff\2\30\2\uffff\1\30\3\uffff\3\30\4\uffff\2\30\7\uffff\2\177\3\30\2\uffff\2\30\5\uffff\2\30\3\uffff\2\30\3\uffff\1\30\6\uffff\1\30\1\uffff\1\30\10\uffff\1\30\3\uffff\1\30\6\uffff\1\30\10\uffff\1\30\5\uffff\1\30\2\uffff\1\30\1\uffff\1\30\7\uffff";
    static final String DFA31_eofS =
        "\u00e2\uffff";
    static final String DFA31_minS =
        "\21\0\1\uffff\6\0\2\uffff\13\0\1\11\3\uffff\12\0\1\uffff\1\0\1\12\1\uffff\1\0\2\uffff\17\0\1\uffff\11\0\1\uffff\31\0\1\uffff\6\0\1\12\1\uffff\1\0\1\12\1\uffff\6\0\1\uffff\6\0\2\uffff\3\0\1\12\1\uffff\1\0\1\12\1\uffff\12\0\2\uffff\1\0\1\12\1\uffff\7\0\1\uffff\4\0\1\12\1\uffff\7\0\1\12\1\uffff\1\0\1\uffff\5\0\1\12\1\uffff\1\0\1\uffff\2\0\1\12\1\uffff\2\0\1\uffff\3\0\1\12\2\uffff\3\0\1\12\2\uffff\2\0\1\uffff\10\0\1\12\2\uffff";
    static final String DFA31_maxS =
        "\21\uffff\1\uffff\6\uffff\2\uffff\13\uffff\1\40\3\uffff\12\uffff\1\uffff\1\uffff\1\12\1\uffff\1\uffff\2\uffff\17\uffff\1\uffff\11\uffff\1\uffff\31\uffff\1\uffff\6\uffff\1\12\1\uffff\1\uffff\1\12\1\uffff\6\uffff\1\uffff\6\uffff\2\uffff\3\uffff\1\12\1\uffff\1\uffff\1\12\1\uffff\12\uffff\2\uffff\1\uffff\1\12\1\uffff\7\uffff\1\uffff\4\uffff\1\12\1\uffff\7\uffff\1\12\1\uffff\1\uffff\1\uffff\5\uffff\1\12\1\uffff\1\uffff\1\uffff\2\uffff\1\12\1\uffff\2\uffff\1\uffff\3\uffff\1\12\2\uffff\3\uffff\1\12\2\uffff\2\uffff\1\uffff\10\uffff\1\12\2\uffff";
    static final String DFA31_acceptS =
        "\21\uffff\1\23\6\uffff\1\24\1\22\14\uffff\1\30\1\16\1\15\12\uffff\1\20\2\uffff\1\21\1\uffff\1\23\1\25\17\uffff\1\26\11\uffff\1\21\31\uffff\1\27\7\uffff\1\4\2\uffff\1\14\6\uffff\1\17\6\uffff\1\4\1\14\4\uffff\1\11\2\uffff\1\12\12\uffff\1\11\1\12\2\uffff\1\13\7\uffff\1\13\5\uffff\1\2\10\uffff\1\1\1\uffff\1\2\6\uffff\1\6\1\uffff\1\1\3\uffff\1\5\2\uffff\1\6\4\uffff\1\3\1\5\4\uffff\1\10\1\3\2\uffff\1\10\11\uffff\2\7";
    static final String DFA31_specialS =
        "\1\35\1\u00a5\1\u00a8\1\u00ad\1\114\1\u0091\1\u0081\1\45\1\72\1\74\1\112\1\76\1\21\1\135\1\67\1\75\1\u0095\1\uffff\1\u0094\1\15\1\4\1\6\1\24\1\10\2\uffff\1\41\1\60\1\73\1\123\1\106\1\u008e\1\u009c\1\164\1\u0082\1\u0086\1\u0089\4\uffff\1\34\1\u00ab\1\u00a1\1\u00ac\1\161\1\56\1\3\1\23\1\52\1\u0099\1\uffff\1\1\2\uffff\1\u0098\2\uffff\1\20\1\12\1\26\1\43\1\62\1\77\1\124\1\120\1\u008f\1\u009e\1\165\1\u0083\1\u0087\1\u008a\1\111\1\uffff\1\u009d\1\u00a9\1\151\1\137\1\157\1\22\1\42\1\u0096\1\162\1\uffff\1\14\1\122\1\153\1\13\1\27\1\44\1\63\1\100\1\125\1\70\1\115\1\u00a0\1\166\1\u0084\1\u0088\1\u008b\1\u008d\1\57\1\121\1\u00a6\1\u0097\1\116\1\146\1\7\1\u0085\1\uffff\1\30\1\46\1\64\1\101\1\126\1\u009a\2\uffff\1\71\2\uffff\1\u00a2\1\167\1\u0093\1\u00a7\1\u008c\1\117\1\uffff\1\172\1\31\1\50\1\65\1\102\1\127\2\uffff\1\u00a3\1\170\1\u009f\2\uffff\1\0\2\uffff\1\33\1\54\1\134\1\32\1\51\1\66\1\103\1\130\1\u00a4\1\171\2\uffff\1\25\2\uffff\1\136\1\53\1\11\1\104\1\131\1\47\1\173\1\uffff\1\u00aa\1\17\1\55\1\144\2\uffff\1\107\1\133\1\u0080\1\140\1\u009b\1\174\1\177\2\uffff\1\5\1\uffff\1\110\1\175\1\152\1\141\1\36\2\uffff\1\105\1\uffff\1\16\1\2\2\uffff\1\154\1\142\1\uffff\1\132\1\176\1\u0090\3\uffff\1\155\1\145\1\u0092\3\uffff\1\156\1\147\1\uffff\1\160\1\150\1\163\1\37\1\61\1\113\1\40\1\143\3\uffff}>";
    static final String[] DFA31_transitionS = {
            "\11\25\2\21\2\25\1\21\22\25\1\21\1\25\1\14\1\17\3\25\1\15\7\25\1\24\12\23\6\25\1\16\1\5\1\7\2\22\1\1\1\3\1\12\1\22\1\4\4\22\1\2\4\22\1\6\1\11\2\22\1\10\3\22\3\25\1\20\1\22\1\25\32\22\1\25\1\13\uff83\25",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\27\27\1\26\2\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\1\32\31\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\4\27\1\33\25\27\uff85\31",
            "\40\31\1\35\17\31\12\27\7\31\32\27\4\31\1\27\1\31\15\27\1\34\14\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\15\27\1\37\4\27\1\36\7\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\2\27\1\40\27\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\1\41\31\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\7\27\1\42\22\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\7\27\1\43\22\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\10\27\1\44\21\27\uff85\31",
            "\11\47\1\45\1\50\2\47\1\50\22\47\1\45\133\47\1\uffff\uff83\47",
            "\12\55\1\54\2\55\1\53\24\55\1\51\71\55\1\52\uffa3\55",
            "\12\62\1\61\2\62\1\60\31\62\1\56\64\62\1\57\uffa3\62",
            "\12\63\1\uffff\2\63\1\uffff\ufff2\63",
            "\12\64\1\66\2\64\1\65\ufff2\64",
            "\101\31\32\67\4\31\1\67\1\31\32\67\uff85\31",
            "",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\32\27\uff85\31",
            "\60\31\12\72\uffc6\31",
            "\52\31\1\73\uffd5\31",
            "\0\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\1\74\31\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\32\27\uff85\31",
            "",
            "",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\21\27\1\75\10\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\1\76\31\27\uff85\31",
            "\40\31\1\77\17\31\12\27\7\31\32\27\4\31\1\27\1\31\32\27\uff85\31",
            "\167\31\1\100\uff88\31",
            "\40\31\1\101\17\31\12\27\7\31\32\27\4\31\1\27\1\31\32\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\3\27\1\102\26\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\4\27\1\103\25\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\2\27\1\104\27\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\4\27\1\105\25\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\4\27\1\106\25\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\25\27\1\107\4\27\uff85\31",
            "\1\45\1\50\2\uffff\1\50\22\uffff\1\45",
            "",
            "",
            "",
            "\42\31\1\110\uffdd\31",
            "\12\114\1\113\2\114\1\112\ufff2\114",
            "\12\111\1\54\ufff5\111",
            "\0\111",
            "\12\55\1\54\2\55\1\53\24\55\1\115\71\55\1\52\uffa3\55",
            "\47\31\1\116\uffd8\31",
            "\12\121\1\120\2\121\1\117\ufff2\121",
            "\12\111\1\61\ufff5\111",
            "\0\111",
            "\12\62\1\61\2\62\1\60\31\62\1\122\64\62\1\57\uffa3\62",
            "",
            "\12\64\1\66\2\64\1\65\ufff2\64",
            "\1\66",
            "",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\32\27\uff85\31",
            "",
            "",
            "\60\31\12\72\uffc6\31",
            "\12\127\1\126\2\127\1\125\34\127\1\124\uffd5\127",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\14\27\1\130\15\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\21\27\1\131\10\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\23\27\1\132\6\27\uff85\31",
            "\157\31\1\133\uff90\31",
            "\141\31\1\134\uff9e\31",
            "\141\31\1\135\uff9e\31",
            "\40\31\1\136\17\31\12\27\7\31\32\27\4\31\1\27\1\31\32\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\15\27\1\137\14\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\12\27\1\140\17\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\15\27\1\141\14\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\15\27\1\142\14\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\4\27\1\143\25\27\uff85\31",
            "\12\147\1\146\2\147\1\145\24\147\1\144\uffdd\147",
            "",
            "\12\111\1\54\ufff5\111",
            "\0\111",
            "\12\55\1\54\2\55\1\53\24\55\1\115\71\55\1\52\uffa3\55",
            "\0\31",
            "\12\153\1\152\2\153\1\151\31\153\1\150\uffd8\153",
            "\12\111\1\61\ufff5\111",
            "\0\111",
            "\12\62\1\61\2\62\1\60\31\62\1\122\64\62\1\57\uffa3\62",
            "\0\31",
            "",
            "\12\127\1\126\2\127\1\125\34\127\1\124\4\127\1\154\uffd0\127",
            "\12\155\1\126\ufff5\155",
            "\0\155",
            "\12\127\1\126\2\127\1\125\34\127\1\124\uffd5\127",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\17\27\1\156\12\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\1\157\31\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\24\27\1\160\5\27\uff85\31",
            "\162\31\1\161\uff8d\31",
            "\156\31\1\162\uff91\31",
            "\12\163\1\165\2\163\1\164\ufff2\163",
            "\12\166\1\170\2\166\1\167\ufff2\166",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\1\171\31\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\6\27\1\172\23\27\uff85\31",
            "\40\31\1\173\17\31\12\27\7\31\32\27\4\31\1\27\1\31\32\27\uff85\31",
            "\40\31\1\174\17\31\12\27\7\31\32\27\4\31\1\27\1\31\32\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\15\27\1\175\14\27\uff85\31",
            "\12\147\1\146\2\147\1\145\24\147\1\176\uffdd\147",
            "\12\177\1\146\ufff5\177",
            "\0\177",
            "\12\147\1\146\2\147\1\145\24\147\1\144\uffdd\147",
            "\12\153\1\152\2\153\1\151\31\153\1\u0080\uffd8\153",
            "\12\177\1\152\ufff5\177",
            "\0\177",
            "\12\153\1\152\2\153\1\151\31\153\1\150\uffd8\153",
            "\12\127\1\126\2\127\1\125\34\127\1\124\uffd5\127",
            "",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\13\27\1\u0081\16\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\23\27\1\u0082\6\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\21\27\1\u0083\10\27\uff85\31",
            "\144\31\1\u0084\uff9b\31",
            "\164\31\1\u0085\uff8b\31",
            "\12\163\1\165\2\163\1\164\ufff2\163",
            "\1\165",
            "",
            "\12\166\1\170\2\166\1\167\ufff2\166",
            "\1\170",
            "",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\21\27\1\u0088\10\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\21\27\1\u0089\10\27\uff85\31",
            "\12\u008a\1\u008c\2\u008a\1\u008b\ufff2\u008a",
            "\12\u008d\1\u008f\2\u008d\1\u008e\ufff2\u008d",
            "\40\31\1\u0090\17\31\12\27\7\31\32\27\4\31\1\27\1\31\32\27\uff85\31",
            "\12\147\1\146\2\147\1\145\24\147\1\u0091\uffdd\147",
            "",
            "\12\153\1\152\2\153\1\151\31\153\1\u0092\uffd8\153",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\4\27\1\u0093\25\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\10\27\1\u0094\21\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\4\27\1\u0095\25\27\uff85\31",
            "\145\31\1\u0096\uff9a\31",
            "\40\31\1\u0097\uffdf\31",
            "",
            "",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\10\27\1\u0098\21\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\16\27\1\u0099\13\27\uff85\31",
            "\12\u008a\1\u008c\2\u008a\1\u008b\ufff2\u008a",
            "\1\u008c",
            "",
            "\12\u008d\1\u008f\2\u008d\1\u008e\ufff2\u008d",
            "\1\u008f",
            "",
            "\12\u009c\1\u009e\2\u009c\1\u009d\ufff2\u009c",
            "\12\147\1\146\2\147\1\145\24\147\1\u0091\uffdd\147",
            "\12\153\1\152\2\153\1\151\31\153\1\u0092\uffd8\153",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\22\27\1\u009f\7\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\25\27\1\u00a0\4\27\uff85\31",
            "\60\31\12\27\1\u00a1\6\31\32\27\4\31\1\27\1\31\32\27\uff85\31",
            "\162\31\1\u00a2\uff8d\31",
            "\164\31\1\u00a3\uff8b\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\16\27\1\u00a4\13\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\24\27\1\u00a5\5\27\uff85\31",
            "",
            "",
            "\12\u009c\1\u009e\2\u009c\1\u009d\ufff2\u009c",
            "\1\u009e",
            "",
            "\11\31\1\u00a7\26\31\1\u00a7\17\31\12\27\1\u00a8\6\31\32\27\4\31\1\27\1\31\32\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\4\27\1\u00a9\25\27\uff85\31",
            "\12\u00aa\1\u00ac\2\u00aa\1\u00ab\ufff2\u00aa",
            "\40\31\1\u00ad\uffdf\31",
            "\157\31\1\u00ae\uff90\31",
            "\11\31\1\u00af\26\31\1\u00af\17\31\12\27\1\u00b1\6\31\16\27\1\u00b0\13\27\4\31\1\27\1\31\32\27\uff85\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\15\27\1\u00b2\14\27\uff85\31",
            "",
            "\11\31\1\u00a7\26\31\1\u00a7\31\31\1\u00a8\uffc5\31",
            "\11\31\1\u00b3\1\u00b5\2\31\1\u00b4\22\31\1\u00b3\uffdf\31",
            "\60\31\12\27\1\u00b6\6\31\32\27\4\31\1\27\1\31\32\27\uff85\31",
            "\12\u00aa\1\u00ac\2\u00aa\1\u00ab\ufff2\u00aa",
            "\1\u00ac",
            "",
            "\164\31\1\u00b8\uff8b\31",
            "\40\31\1\u00b9\uffdf\31",
            "\11\31\1\u00af\26\31\1\u00af\31\31\1\u00b1\24\31\1\u00ba\uffb0\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\24\27\1\u00bb\5\27\uff85\31",
            "\12\u00bc\1\u00be\2\u00bc\1\u00bd\ufff2\u00bc",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\3\27\1\u00bf\26\27\uff85\31",
            "\11\31\1\u00b3\1\u00b5\2\31\1\u00b4\22\31\1\u00b3\uffdf\31",
            "\1\u00b5",
            "",
            "\12\u00aa\1\u00ac\2\u00aa\1\u00ab\ufff2\u00aa",
            "",
            "\157\31\1\u00c1\uff90\31",
            "\12\u00c2\1\u00c4\2\u00c2\1\u00c3\ufff2\u00c2",
            "\165\31\1\u00c5\uff8a\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\23\27\1\u00c6\6\27\uff85\31",
            "\12\u00bc\1\u00be\2\u00bc\1\u00bd\ufff2\u00bc",
            "\1\u00be",
            "",
            "\11\31\1\u00c8\26\31\1\u00c8\17\31\12\27\1\u00c9\6\31\32\27\4\31\1\27\1\31\32\27\uff85\31",
            "",
            "\12\u00ca\1\u00cc\2\u00ca\1\u00cb\ufff2\u00ca",
            "\12\u00c2\1\u00c4\2\u00c2\1\u00c3\ufff2\u00c2",
            "\1\u00c4",
            "",
            "\164\31\1\u00ce\uff8b\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\13\27\1\u00cf\16\27\uff85\31",
            "",
            "\11\31\1\u00c8\26\31\1\u00c8\31\31\1\u00c9\uffc5\31",
            "\12\u00d0\1\u00d2\2\u00d0\1\u00d1\ufff2\u00d0",
            "\12\u00ca\1\u00cc\2\u00ca\1\u00cb\ufff2\u00ca",
            "\1\u00cc",
            "",
            "",
            "\154\31\1\u00d4\uff93\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\10\27\1\u00d5\21\27\uff85\31",
            "\12\u00d0\1\u00d2\2\u00d0\1\u00d1\ufff2\u00d0",
            "\1\u00d2",
            "",
            "",
            "\151\31\1\u00d7\uff96\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\15\27\1\u00d8\14\27\uff85\31",
            "",
            "\156\31\1\u00d9\uff91\31",
            "\60\31\12\27\7\31\32\27\4\31\1\27\1\31\4\27\1\u00da\25\27\uff85\31",
            "\145\31\1\u00db\uff9a\31",
            "\11\31\1\u00dc\26\31\1\u00dc\17\31\12\27\1\u00dd\6\31\32\27\4\31\1\27\1\31\32\27\uff85\31",
            "\11\31\1\u00dc\26\31\1\u00dc\31\31\1\u00dd\uffc5\31",
            "\11\31\1\u00dc\26\31\1\u00dc\31\31\1\u00dd\uffc5\31",
            "\12\u00de\1\u00e0\2\u00de\1\u00df\ufff2\u00de",
            "\12\u00de\1\u00e0\2\u00de\1\u00df\ufff2\u00de",
            "\1\u00e0",
            "",
            ""
    };

    static final short[] DFA31_eot = DFA.unpackEncodedString(DFA31_eotS);
    static final short[] DFA31_eof = DFA.unpackEncodedString(DFA31_eofS);
    static final char[] DFA31_min = DFA.unpackEncodedStringToUnsignedChars(DFA31_minS);
    static final char[] DFA31_max = DFA.unpackEncodedStringToUnsignedChars(DFA31_maxS);
    static final short[] DFA31_accept = DFA.unpackEncodedString(DFA31_acceptS);
    static final short[] DFA31_special = DFA.unpackEncodedString(DFA31_specialS);
    static final short[][] DFA31_transition;

    static {
        int numStates = DFA31_transitionS.length;
        DFA31_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA31_transition[i] = DFA.unpackEncodedString(DFA31_transitionS[i]);
        }
    }

    class DFA31 extends DFA {

        public DFA31(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 31;
            this.eot = DFA31_eot;
            this.eof = DFA31_eof;
            this.min = DFA31_min;
            this.max = DFA31_max;
            this.accept = DFA31_accept;
            this.special = DFA31_special;
            this.transition = DFA31_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( RULE_EXAMPLE_HEADING | RULE_FEATURE_TEXT | RULE_IN_ORDER_TO | RULE_AS_A | RULE_I_WANT_TO | RULE_SCENARIO_TEXT | RULE_SCENARIO_OUTLINE_TEXT | RULE_BACKGROUND_TEXT | RULE_WHEN_TEXT | RULE_THEN_TEXT | RULE_GIVEN_TEXT | RULE_AND_TEXT | RULE_EXAMPLE_ROW_END | RULE_EXAMPLE_CELL | RULE_CODE | RULE_TAG | RULE_SL_COMMENT | RULE_TEXT | RULE_WS | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA31_141 = input.LA(1);

                        s = -1;
                        if ( (LA31_141=='\r') ) {s = 142;}

                        else if ( (LA31_141=='\n') ) {s = 143;}

                        else if ( ((LA31_141>='\u0000' && LA31_141<='\t')||(LA31_141>='\u000B' && LA31_141<='\f')||(LA31_141>='\u000E' && LA31_141<='\uFFFF')) ) {s = 141;}

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA31_52 = input.LA(1);

                        s = -1;
                        if ( (LA31_52=='\r') ) {s = 53;}

                        else if ( (LA31_52=='\n') ) {s = 54;}

                        else if ( ((LA31_52>='\u0000' && LA31_52<='\t')||(LA31_52>='\u000B' && LA31_52<='\f')||(LA31_52>='\u000E' && LA31_52<='\uFFFF')) ) {s = 52;}

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA31_194 = input.LA(1);

                        s = -1;
                        if ( (LA31_194=='\r') ) {s = 195;}

                        else if ( (LA31_194=='\n') ) {s = 196;}

                        else if ( ((LA31_194>='\u0000' && LA31_194<='\t')||(LA31_194>='\u000B' && LA31_194<='\f')||(LA31_194>='\u000E' && LA31_194<='\uFFFF')) ) {s = 194;}

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA31_47 = input.LA(1);

                        s = -1;
                        if ( (LA31_47=='\r') ) {s = 79;}

                        else if ( (LA31_47=='\n') ) {s = 80;}

                        else if ( ((LA31_47>='\u0000' && LA31_47<='\t')||(LA31_47>='\u000B' && LA31_47<='\f')||(LA31_47>='\u000E' && LA31_47<='\uFFFF')) ) {s = 81;}

                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA31_20 = input.LA(1);

                        s = -1;
                        if ( (LA31_20=='*') ) {s = 59;}

                        else if ( ((LA31_20>='\u0000' && LA31_20<=')')||(LA31_20>='+' && LA31_20<='\uFFFF')) ) {s = 25;}

                        else s = 38;

                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA31_182 = input.LA(1);

                        s = -1;
                        if ( ((LA31_182>='\u0000' && LA31_182<='\t')||(LA31_182>='\u000B' && LA31_182<='\f')||(LA31_182>='\u000E' && LA31_182<='\uFFFF')) ) {s = 170;}

                        else if ( (LA31_182=='\r') ) {s = 171;}

                        else if ( (LA31_182=='\n') ) {s = 172;}

                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA31_21 = input.LA(1);

                        s = -1;
                        if ( ((LA31_21>='\u0000' && LA31_21<='\uFFFF')) ) {s = 25;}

                        else s = 38;

                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA31_107 = input.LA(1);

                        s = -1;
                        if ( (LA31_107=='\'') ) {s = 104;}

                        else if ( (LA31_107=='\r') ) {s = 105;}

                        else if ( (LA31_107=='\n') ) {s = 106;}

                        else if ( ((LA31_107>='\u0000' && LA31_107<='\t')||(LA31_107>='\u000B' && LA31_107<='\f')||(LA31_107>='\u000E' && LA31_107<='&')||(LA31_107>='(' && LA31_107<='\uFFFF')) ) {s = 107;}

                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA31_23 = input.LA(1);

                        s = -1;
                        if ( ((LA31_23>='0' && LA31_23<='9')||(LA31_23>='A' && LA31_23<='Z')||LA31_23=='_'||(LA31_23>='a' && LA31_23<='z')) ) {s = 23;}

                        else if ( ((LA31_23>='\u0000' && LA31_23<='/')||(LA31_23>=':' && LA31_23<='@')||(LA31_23>='[' && LA31_23<='^')||LA31_23=='`'||(LA31_23>='{' && LA31_23<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA31_161 = input.LA(1);

                        s = -1;
                        if ( ((LA31_161>='\u0000' && LA31_161<='\t')||(LA31_161>='\u000B' && LA31_161<='\f')||(LA31_161>='\u000E' && LA31_161<='\uFFFF')) ) {s = 170;}

                        else if ( (LA31_161=='\r') ) {s = 171;}

                        else if ( (LA31_161=='\n') ) {s = 172;}

                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA31_59 = input.LA(1);

                        s = -1;
                        if ( (LA31_59=='*') ) {s = 84;}

                        else if ( (LA31_59=='\r') ) {s = 85;}

                        else if ( (LA31_59=='\n') ) {s = 86;}

                        else if ( ((LA31_59>='\u0000' && LA31_59<='\t')||(LA31_59>='\u000B' && LA31_59<='\f')||(LA31_59>='\u000E' && LA31_59<=')')||(LA31_59>='+' && LA31_59<='\uFFFF')) ) {s = 87;}

                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA31_87 = input.LA(1);

                        s = -1;
                        if ( (LA31_87=='*') ) {s = 84;}

                        else if ( (LA31_87=='\r') ) {s = 85;}

                        else if ( (LA31_87=='\n') ) {s = 86;}

                        else if ( ((LA31_87>='\u0000' && LA31_87<='\t')||(LA31_87>='\u000B' && LA31_87<='\f')||(LA31_87>='\u000E' && LA31_87<=')')||(LA31_87>='+' && LA31_87<='\uFFFF')) ) {s = 87;}

                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA31_84 = input.LA(1);

                        s = -1;
                        if ( (LA31_84=='/') ) {s = 108;}

                        else if ( (LA31_84=='*') ) {s = 84;}

                        else if ( (LA31_84=='\r') ) {s = 85;}

                        else if ( (LA31_84=='\n') ) {s = 86;}

                        else if ( ((LA31_84>='\u0000' && LA31_84<='\t')||(LA31_84>='\u000B' && LA31_84<='\f')||(LA31_84>='\u000E' && LA31_84<=')')||(LA31_84>='+' && LA31_84<='.')||(LA31_84>='0' && LA31_84<='\uFFFF')) ) {s = 87;}

                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA31_19 = input.LA(1);

                        s = -1;
                        if ( ((LA31_19>='0' && LA31_19<='9')) ) {s = 58;}

                        else if ( ((LA31_19>='\u0000' && LA31_19<='/')||(LA31_19>=':' && LA31_19<='\uFFFF')) ) {s = 25;}

                        else s = 57;

                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA31_193 = input.LA(1);

                        s = -1;
                        if ( ((LA31_193>='\u0000' && LA31_193<='\t')||(LA31_193>='\u000B' && LA31_193<='\f')||(LA31_193>='\u000E' && LA31_193<='\uFFFF')) ) {s = 202;}

                        else if ( (LA31_193=='\r') ) {s = 203;}

                        else if ( (LA31_193=='\n') ) {s = 204;}

                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA31_168 = input.LA(1);

                        s = -1;
                        if ( (LA31_168=='\t'||LA31_168==' ') ) {s = 179;}

                        else if ( (LA31_168=='\r') ) {s = 180;}

                        else if ( (LA31_168=='\n') ) {s = 181;}

                        else if ( ((LA31_168>='\u0000' && LA31_168<='\b')||(LA31_168>='\u000B' && LA31_168<='\f')||(LA31_168>='\u000E' && LA31_168<='\u001F')||(LA31_168>='!' && LA31_168<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA31_58 = input.LA(1);

                        s = -1;
                        if ( ((LA31_58>='0' && LA31_58<='9')) ) {s = 58;}

                        else if ( ((LA31_58>='\u0000' && LA31_58<='/')||(LA31_58>=':' && LA31_58<='\uFFFF')) ) {s = 25;}

                        else s = 57;

                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA31_12 = input.LA(1);

                        s = -1;
                        if ( (LA31_12=='\"') ) {s = 41;}

                        else if ( (LA31_12=='\\') ) {s = 42;}

                        else if ( (LA31_12=='\r') ) {s = 43;}

                        else if ( (LA31_12=='\n') ) {s = 44;}

                        else if ( ((LA31_12>='\u0000' && LA31_12<='\t')||(LA31_12>='\u000B' && LA31_12<='\f')||(LA31_12>='\u000E' && LA31_12<='!')||(LA31_12>='#' && LA31_12<='[')||(LA31_12>=']' && LA31_12<='\uFFFF')) ) {s = 45;}

                        else s = 38;

                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA31_79 = input.LA(1);

                        s = -1;
                        if ( ((LA31_79>='\u0000' && LA31_79<='\t')||(LA31_79>='\u000B' && LA31_79<='\uFFFF')) ) {s = 73;}

                        else if ( (LA31_79=='\n') ) {s = 49;}

                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA31_48 = input.LA(1);

                        s = -1;
                        if ( (LA31_48=='\n') ) {s = 49;}

                        else if ( ((LA31_48>='\u0000' && LA31_48<='\t')||(LA31_48>='\u000B' && LA31_48<='\uFFFF')) ) {s = 73;}

                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA31_22 = input.LA(1);

                        s = -1;
                        if ( (LA31_22=='a') ) {s = 60;}

                        else if ( ((LA31_22>='0' && LA31_22<='9')||(LA31_22>='A' && LA31_22<='Z')||LA31_22=='_'||(LA31_22>='b' && LA31_22<='z')) ) {s = 23;}

                        else if ( ((LA31_22>='\u0000' && LA31_22<='/')||(LA31_22>=':' && LA31_22<='@')||(LA31_22>='[' && LA31_22<='^')||LA31_22=='`'||(LA31_22>='{' && LA31_22<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA31_156 = input.LA(1);

                        s = -1;
                        if ( (LA31_156=='\r') ) {s = 157;}

                        else if ( (LA31_156=='\n') ) {s = 158;}

                        else if ( ((LA31_156>='\u0000' && LA31_156<='\t')||(LA31_156>='\u000B' && LA31_156<='\f')||(LA31_156>='\u000E' && LA31_156<='\uFFFF')) ) {s = 156;}

                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA31_60 = input.LA(1);

                        s = -1;
                        if ( (LA31_60=='m') ) {s = 88;}

                        else if ( ((LA31_60>='0' && LA31_60<='9')||(LA31_60>='A' && LA31_60<='Z')||LA31_60=='_'||(LA31_60>='a' && LA31_60<='l')||(LA31_60>='n' && LA31_60<='z')) ) {s = 23;}

                        else if ( ((LA31_60>='\u0000' && LA31_60<='/')||(LA31_60>=':' && LA31_60<='@')||(LA31_60>='[' && LA31_60<='^')||LA31_60=='`'||(LA31_60>='{' && LA31_60<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA31_88 = input.LA(1);

                        s = -1;
                        if ( (LA31_88=='p') ) {s = 110;}

                        else if ( ((LA31_88>='0' && LA31_88<='9')||(LA31_88>='A' && LA31_88<='Z')||LA31_88=='_'||(LA31_88>='a' && LA31_88<='o')||(LA31_88>='q' && LA31_88<='z')) ) {s = 23;}

                        else if ( ((LA31_88>='\u0000' && LA31_88<='/')||(LA31_88>=':' && LA31_88<='@')||(LA31_88>='[' && LA31_88<='^')||LA31_88=='`'||(LA31_88>='{' && LA31_88<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA31_110 = input.LA(1);

                        s = -1;
                        if ( (LA31_110=='l') ) {s = 129;}

                        else if ( ((LA31_110>='0' && LA31_110<='9')||(LA31_110>='A' && LA31_110<='Z')||LA31_110=='_'||(LA31_110>='a' && LA31_110<='k')||(LA31_110>='m' && LA31_110<='z')) ) {s = 23;}

                        else if ( ((LA31_110>='\u0000' && LA31_110<='/')||(LA31_110>=':' && LA31_110<='@')||(LA31_110>='[' && LA31_110<='^')||LA31_110=='`'||(LA31_110>='{' && LA31_110<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 25 : 
                        int LA31_129 = input.LA(1);

                        s = -1;
                        if ( (LA31_129=='e') ) {s = 147;}

                        else if ( ((LA31_129>='0' && LA31_129<='9')||(LA31_129>='A' && LA31_129<='Z')||LA31_129=='_'||(LA31_129>='a' && LA31_129<='d')||(LA31_129>='f' && LA31_129<='z')) ) {s = 23;}

                        else if ( ((LA31_129>='\u0000' && LA31_129<='/')||(LA31_129>=':' && LA31_129<='@')||(LA31_129>='[' && LA31_129<='^')||LA31_129=='`'||(LA31_129>='{' && LA31_129<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 26 : 
                        int LA31_147 = input.LA(1);

                        s = -1;
                        if ( (LA31_147=='s') ) {s = 159;}

                        else if ( ((LA31_147>='0' && LA31_147<='9')||(LA31_147>='A' && LA31_147<='Z')||LA31_147=='_'||(LA31_147>='a' && LA31_147<='r')||(LA31_147>='t' && LA31_147<='z')) ) {s = 23;}

                        else if ( ((LA31_147>='\u0000' && LA31_147<='/')||(LA31_147>=':' && LA31_147<='@')||(LA31_147>='[' && LA31_147<='^')||LA31_147=='`'||(LA31_147>='{' && LA31_147<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 27 : 
                        int LA31_144 = input.LA(1);

                        s = -1;
                        if ( ((LA31_144>='\u0000' && LA31_144<='\t')||(LA31_144>='\u000B' && LA31_144<='\f')||(LA31_144>='\u000E' && LA31_144<='\uFFFF')) ) {s = 156;}

                        else if ( (LA31_144=='\r') ) {s = 157;}

                        else if ( (LA31_144=='\n') ) {s = 158;}

                        if ( s>=0 ) return s;
                        break;
                    case 28 : 
                        int LA31_41 = input.LA(1);

                        s = -1;
                        if ( (LA31_41=='\"') ) {s = 72;}

                        else if ( ((LA31_41>='\u0000' && LA31_41<='!')||(LA31_41>='#' && LA31_41<='\uFFFF')) ) {s = 25;}

                        else s = 73;

                        if ( s>=0 ) return s;
                        break;
                    case 29 : 
                        int LA31_0 = input.LA(1);

                        s = -1;
                        if ( (LA31_0=='E') ) {s = 1;}

                        else if ( (LA31_0=='N') ) {s = 2;}

                        else if ( (LA31_0=='F') ) {s = 3;}

                        else if ( (LA31_0=='I') ) {s = 4;}

                        else if ( (LA31_0=='A') ) {s = 5;}

                        else if ( (LA31_0=='S') ) {s = 6;}

                        else if ( (LA31_0=='B') ) {s = 7;}

                        else if ( (LA31_0=='W') ) {s = 8;}

                        else if ( (LA31_0=='T') ) {s = 9;}

                        else if ( (LA31_0=='G') ) {s = 10;}

                        else if ( (LA31_0=='|') ) {s = 11;}

                        else if ( (LA31_0=='\"') ) {s = 12;}

                        else if ( (LA31_0=='\'') ) {s = 13;}

                        else if ( (LA31_0=='@') ) {s = 14;}

                        else if ( (LA31_0=='#') ) {s = 15;}

                        else if ( (LA31_0=='^') ) {s = 16;}

                        else if ( ((LA31_0>='\t' && LA31_0<='\n')||LA31_0=='\r'||LA31_0==' ') ) {s = 17;}

                        else if ( ((LA31_0>='C' && LA31_0<='D')||LA31_0=='H'||(LA31_0>='J' && LA31_0<='M')||(LA31_0>='O' && LA31_0<='R')||(LA31_0>='U' && LA31_0<='V')||(LA31_0>='X' && LA31_0<='Z')||LA31_0=='_'||(LA31_0>='a' && LA31_0<='z')) ) {s = 18;}

                        else if ( ((LA31_0>='0' && LA31_0<='9')) ) {s = 19;}

                        else if ( (LA31_0=='/') ) {s = 20;}

                        else if ( ((LA31_0>='\u0000' && LA31_0<='\b')||(LA31_0>='\u000B' && LA31_0<='\f')||(LA31_0>='\u000E' && LA31_0<='\u001F')||LA31_0=='!'||(LA31_0>='$' && LA31_0<='&')||(LA31_0>='(' && LA31_0<='.')||(LA31_0>=':' && LA31_0<='?')||(LA31_0>='[' && LA31_0<=']')||LA31_0=='`'||LA31_0=='{'||(LA31_0>='}' && LA31_0<='\uFFFF')) ) {s = 21;}

                        if ( s>=0 ) return s;
                        break;
                    case 30 : 
                        int LA31_188 = input.LA(1);

                        s = -1;
                        if ( (LA31_188=='\r') ) {s = 189;}

                        else if ( (LA31_188=='\n') ) {s = 190;}

                        else if ( ((LA31_188>='\u0000' && LA31_188<='\t')||(LA31_188>='\u000B' && LA31_188<='\f')||(LA31_188>='\u000E' && LA31_188<='\uFFFF')) ) {s = 188;}

                        if ( s>=0 ) return s;
                        break;
                    case 31 : 
                        int LA31_218 = input.LA(1);

                        s = -1;
                        if ( ((LA31_218>='0' && LA31_218<='9')||(LA31_218>='A' && LA31_218<='Z')||LA31_218=='_'||(LA31_218>='a' && LA31_218<='z')) ) {s = 23;}

                        else if ( (LA31_218=='\t'||LA31_218==' ') ) {s = 220;}

                        else if ( (LA31_218==':') ) {s = 221;}

                        else if ( ((LA31_218>='\u0000' && LA31_218<='\b')||(LA31_218>='\n' && LA31_218<='\u001F')||(LA31_218>='!' && LA31_218<='/')||(LA31_218>=';' && LA31_218<='@')||(LA31_218>='[' && LA31_218<='^')||LA31_218=='`'||(LA31_218>='{' && LA31_218<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 32 : 
                        int LA31_221 = input.LA(1);

                        s = -1;
                        if ( ((LA31_221>='\u0000' && LA31_221<='\t')||(LA31_221>='\u000B' && LA31_221<='\f')||(LA31_221>='\u000E' && LA31_221<='\uFFFF')) ) {s = 222;}

                        else if ( (LA31_221=='\r') ) {s = 223;}

                        else if ( (LA31_221=='\n') ) {s = 224;}

                        if ( s>=0 ) return s;
                        break;
                    case 33 : 
                        int LA31_26 = input.LA(1);

                        s = -1;
                        if ( (LA31_26=='r') ) {s = 61;}

                        else if ( ((LA31_26>='0' && LA31_26<='9')||(LA31_26>='A' && LA31_26<='Z')||LA31_26=='_'||(LA31_26>='a' && LA31_26<='q')||(LA31_26>='s' && LA31_26<='z')) ) {s = 23;}

                        else if ( ((LA31_26>='\u0000' && LA31_26<='/')||(LA31_26>=':' && LA31_26<='@')||(LA31_26>='[' && LA31_26<='^')||LA31_26=='`'||(LA31_26>='{' && LA31_26<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 34 : 
                        int LA31_80 = input.LA(1);

                        s = -1;
                        if ( ((LA31_80>='\u0000' && LA31_80<='\uFFFF')) ) {s = 73;}

                        else s = 25;

                        if ( s>=0 ) return s;
                        break;
                    case 35 : 
                        int LA31_61 = input.LA(1);

                        s = -1;
                        if ( (LA31_61=='r') ) {s = 89;}

                        else if ( ((LA31_61>='0' && LA31_61<='9')||(LA31_61>='A' && LA31_61<='Z')||LA31_61=='_'||(LA31_61>='a' && LA31_61<='q')||(LA31_61>='s' && LA31_61<='z')) ) {s = 23;}

                        else if ( ((LA31_61>='\u0000' && LA31_61<='/')||(LA31_61>=':' && LA31_61<='@')||(LA31_61>='[' && LA31_61<='^')||LA31_61=='`'||(LA31_61>='{' && LA31_61<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 36 : 
                        int LA31_89 = input.LA(1);

                        s = -1;
                        if ( (LA31_89=='a') ) {s = 111;}

                        else if ( ((LA31_89>='0' && LA31_89<='9')||(LA31_89>='A' && LA31_89<='Z')||LA31_89=='_'||(LA31_89>='b' && LA31_89<='z')) ) {s = 23;}

                        else if ( ((LA31_89>='\u0000' && LA31_89<='/')||(LA31_89>=':' && LA31_89<='@')||(LA31_89>='[' && LA31_89<='^')||LA31_89=='`'||(LA31_89>='{' && LA31_89<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 37 : 
                        int LA31_7 = input.LA(1);

                        s = -1;
                        if ( (LA31_7=='a') ) {s = 33;}

                        else if ( ((LA31_7>='0' && LA31_7<='9')||(LA31_7>='A' && LA31_7<='Z')||LA31_7=='_'||(LA31_7>='b' && LA31_7<='z')) ) {s = 23;}

                        else if ( ((LA31_7>='\u0000' && LA31_7<='/')||(LA31_7>=':' && LA31_7<='@')||(LA31_7>='[' && LA31_7<='^')||LA31_7=='`'||(LA31_7>='{' && LA31_7<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 38 : 
                        int LA31_111 = input.LA(1);

                        s = -1;
                        if ( (LA31_111=='t') ) {s = 130;}

                        else if ( ((LA31_111>='0' && LA31_111<='9')||(LA31_111>='A' && LA31_111<='Z')||LA31_111=='_'||(LA31_111>='a' && LA31_111<='s')||(LA31_111>='u' && LA31_111<='z')) ) {s = 23;}

                        else if ( ((LA31_111>='\u0000' && LA31_111<='/')||(LA31_111>=':' && LA31_111<='@')||(LA31_111>='[' && LA31_111<='^')||LA31_111=='`'||(LA31_111>='{' && LA31_111<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 39 : 
                        int LA31_164 = input.LA(1);

                        s = -1;
                        if ( (LA31_164=='\t'||LA31_164==' ') ) {s = 175;}

                        else if ( (LA31_164=='O') ) {s = 176;}

                        else if ( (LA31_164==':') ) {s = 177;}

                        else if ( ((LA31_164>='0' && LA31_164<='9')||(LA31_164>='A' && LA31_164<='N')||(LA31_164>='P' && LA31_164<='Z')||LA31_164=='_'||(LA31_164>='a' && LA31_164<='z')) ) {s = 23;}

                        else if ( ((LA31_164>='\u0000' && LA31_164<='\b')||(LA31_164>='\n' && LA31_164<='\u001F')||(LA31_164>='!' && LA31_164<='/')||(LA31_164>=';' && LA31_164<='@')||(LA31_164>='[' && LA31_164<='^')||LA31_164=='`'||(LA31_164>='{' && LA31_164<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 40 : 
                        int LA31_130 = input.LA(1);

                        s = -1;
                        if ( (LA31_130=='i') ) {s = 148;}

                        else if ( ((LA31_130>='0' && LA31_130<='9')||(LA31_130>='A' && LA31_130<='Z')||LA31_130=='_'||(LA31_130>='a' && LA31_130<='h')||(LA31_130>='j' && LA31_130<='z')) ) {s = 23;}

                        else if ( ((LA31_130>='\u0000' && LA31_130<='/')||(LA31_130>=':' && LA31_130<='@')||(LA31_130>='[' && LA31_130<='^')||LA31_130=='`'||(LA31_130>='{' && LA31_130<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 41 : 
                        int LA31_148 = input.LA(1);

                        s = -1;
                        if ( (LA31_148=='v') ) {s = 160;}

                        else if ( ((LA31_148>='0' && LA31_148<='9')||(LA31_148>='A' && LA31_148<='Z')||LA31_148=='_'||(LA31_148>='a' && LA31_148<='u')||(LA31_148>='w' && LA31_148<='z')) ) {s = 23;}

                        else if ( ((LA31_148>='\u0000' && LA31_148<='/')||(LA31_148>=':' && LA31_148<='@')||(LA31_148>='[' && LA31_148<='^')||LA31_148=='`'||(LA31_148>='{' && LA31_148<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 42 : 
                        int LA31_49 = input.LA(1);

                        s = -1;
                        if ( ((LA31_49>='\u0000' && LA31_49<='\uFFFF')) ) {s = 73;}

                        else s = 25;

                        if ( s>=0 ) return s;
                        break;
                    case 43 : 
                        int LA31_160 = input.LA(1);

                        s = -1;
                        if ( (LA31_160=='e') ) {s = 169;}

                        else if ( ((LA31_160>='0' && LA31_160<='9')||(LA31_160>='A' && LA31_160<='Z')||LA31_160=='_'||(LA31_160>='a' && LA31_160<='d')||(LA31_160>='f' && LA31_160<='z')) ) {s = 23;}

                        else if ( ((LA31_160>='\u0000' && LA31_160<='/')||(LA31_160>=':' && LA31_160<='@')||(LA31_160>='[' && LA31_160<='^')||LA31_160=='`'||(LA31_160>='{' && LA31_160<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 44 : 
                        int LA31_145 = input.LA(1);

                        s = -1;
                        if ( (LA31_145=='\"') ) {s = 145;}

                        else if ( (LA31_145=='\r') ) {s = 101;}

                        else if ( (LA31_145=='\n') ) {s = 102;}

                        else if ( ((LA31_145>='\u0000' && LA31_145<='\t')||(LA31_145>='\u000B' && LA31_145<='\f')||(LA31_145>='\u000E' && LA31_145<='!')||(LA31_145>='#' && LA31_145<='\uFFFF')) ) {s = 103;}

                        else s = 127;

                        if ( s>=0 ) return s;
                        break;
                    case 45 : 
                        int LA31_169 = input.LA(1);

                        s = -1;
                        if ( (LA31_169==':') ) {s = 182;}

                        else if ( ((LA31_169>='0' && LA31_169<='9')||(LA31_169>='A' && LA31_169<='Z')||LA31_169=='_'||(LA31_169>='a' && LA31_169<='z')) ) {s = 23;}

                        else if ( ((LA31_169>='\u0000' && LA31_169<='/')||(LA31_169>=';' && LA31_169<='@')||(LA31_169>='[' && LA31_169<='^')||LA31_169=='`'||(LA31_169>='{' && LA31_169<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 46 : 
                        int LA31_46 = input.LA(1);

                        s = -1;
                        if ( (LA31_46=='\'') ) {s = 78;}

                        else if ( ((LA31_46>='\u0000' && LA31_46<='&')||(LA31_46>='(' && LA31_46<='\uFFFF')) ) {s = 25;}

                        else s = 73;

                        if ( s>=0 ) return s;
                        break;
                    case 47 : 
                        int LA31_101 = input.LA(1);

                        s = -1;
                        if ( (LA31_101=='\n') ) {s = 102;}

                        else if ( ((LA31_101>='\u0000' && LA31_101<='\t')||(LA31_101>='\u000B' && LA31_101<='\uFFFF')) ) {s = 127;}

                        if ( s>=0 ) return s;
                        break;
                    case 48 : 
                        int LA31_27 = input.LA(1);

                        s = -1;
                        if ( (LA31_27=='a') ) {s = 62;}

                        else if ( ((LA31_27>='0' && LA31_27<='9')||(LA31_27>='A' && LA31_27<='Z')||LA31_27=='_'||(LA31_27>='b' && LA31_27<='z')) ) {s = 23;}

                        else if ( ((LA31_27>='\u0000' && LA31_27<='/')||(LA31_27>=':' && LA31_27<='@')||(LA31_27>='[' && LA31_27<='^')||LA31_27=='`'||(LA31_27>='{' && LA31_27<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 49 : 
                        int LA31_219 = input.LA(1);

                        s = -1;
                        if ( (LA31_219=='\t'||LA31_219==' ') ) {s = 220;}

                        else if ( (LA31_219==':') ) {s = 221;}

                        else if ( ((LA31_219>='\u0000' && LA31_219<='\b')||(LA31_219>='\n' && LA31_219<='\u001F')||(LA31_219>='!' && LA31_219<='9')||(LA31_219>=';' && LA31_219<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 50 : 
                        int LA31_62 = input.LA(1);

                        s = -1;
                        if ( (LA31_62=='t') ) {s = 90;}

                        else if ( ((LA31_62>='0' && LA31_62<='9')||(LA31_62>='A' && LA31_62<='Z')||LA31_62=='_'||(LA31_62>='a' && LA31_62<='s')||(LA31_62>='u' && LA31_62<='z')) ) {s = 23;}

                        else if ( ((LA31_62>='\u0000' && LA31_62<='/')||(LA31_62>=':' && LA31_62<='@')||(LA31_62>='[' && LA31_62<='^')||LA31_62=='`'||(LA31_62>='{' && LA31_62<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 51 : 
                        int LA31_90 = input.LA(1);

                        s = -1;
                        if ( (LA31_90=='u') ) {s = 112;}

                        else if ( ((LA31_90>='0' && LA31_90<='9')||(LA31_90>='A' && LA31_90<='Z')||LA31_90=='_'||(LA31_90>='a' && LA31_90<='t')||(LA31_90>='v' && LA31_90<='z')) ) {s = 23;}

                        else if ( ((LA31_90>='\u0000' && LA31_90<='/')||(LA31_90>=':' && LA31_90<='@')||(LA31_90>='[' && LA31_90<='^')||LA31_90=='`'||(LA31_90>='{' && LA31_90<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 52 : 
                        int LA31_112 = input.LA(1);

                        s = -1;
                        if ( (LA31_112=='r') ) {s = 131;}

                        else if ( ((LA31_112>='0' && LA31_112<='9')||(LA31_112>='A' && LA31_112<='Z')||LA31_112=='_'||(LA31_112>='a' && LA31_112<='q')||(LA31_112>='s' && LA31_112<='z')) ) {s = 23;}

                        else if ( ((LA31_112>='\u0000' && LA31_112<='/')||(LA31_112>=':' && LA31_112<='@')||(LA31_112>='[' && LA31_112<='^')||LA31_112=='`'||(LA31_112>='{' && LA31_112<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 53 : 
                        int LA31_131 = input.LA(1);

                        s = -1;
                        if ( (LA31_131=='e') ) {s = 149;}

                        else if ( ((LA31_131>='0' && LA31_131<='9')||(LA31_131>='A' && LA31_131<='Z')||LA31_131=='_'||(LA31_131>='a' && LA31_131<='d')||(LA31_131>='f' && LA31_131<='z')) ) {s = 23;}

                        else if ( ((LA31_131>='\u0000' && LA31_131<='/')||(LA31_131>=':' && LA31_131<='@')||(LA31_131>='[' && LA31_131<='^')||LA31_131=='`'||(LA31_131>='{' && LA31_131<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 54 : 
                        int LA31_149 = input.LA(1);

                        s = -1;
                        if ( (LA31_149==':') ) {s = 161;}

                        else if ( ((LA31_149>='0' && LA31_149<='9')||(LA31_149>='A' && LA31_149<='Z')||LA31_149=='_'||(LA31_149>='a' && LA31_149<='z')) ) {s = 23;}

                        else if ( ((LA31_149>='\u0000' && LA31_149<='/')||(LA31_149>=';' && LA31_149<='@')||(LA31_149>='[' && LA31_149<='^')||LA31_149=='`'||(LA31_149>='{' && LA31_149<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 55 : 
                        int LA31_14 = input.LA(1);

                        s = -1;
                        if ( ((LA31_14>='\u0000' && LA31_14<='\t')||(LA31_14>='\u000B' && LA31_14<='\f')||(LA31_14>='\u000E' && LA31_14<='\uFFFF')) ) {s = 51;}

                        else s = 38;

                        if ( s>=0 ) return s;
                        break;
                    case 56 : 
                        int LA31_93 = input.LA(1);

                        s = -1;
                        if ( ((LA31_93>='\u0000' && LA31_93<='\t')||(LA31_93>='\u000B' && LA31_93<='\f')||(LA31_93>='\u000E' && LA31_93<='\uFFFF')) ) {s = 115;}

                        else if ( (LA31_93=='\r') ) {s = 116;}

                        else if ( (LA31_93=='\n') ) {s = 117;}

                        if ( s>=0 ) return s;
                        break;
                    case 57 : 
                        int LA31_118 = input.LA(1);

                        s = -1;
                        if ( (LA31_118=='\r') ) {s = 119;}

                        else if ( (LA31_118=='\n') ) {s = 120;}

                        else if ( ((LA31_118>='\u0000' && LA31_118<='\t')||(LA31_118>='\u000B' && LA31_118<='\f')||(LA31_118>='\u000E' && LA31_118<='\uFFFF')) ) {s = 118;}

                        if ( s>=0 ) return s;
                        break;
                    case 58 : 
                        int LA31_8 = input.LA(1);

                        s = -1;
                        if ( (LA31_8=='h') ) {s = 34;}

                        else if ( ((LA31_8>='0' && LA31_8<='9')||(LA31_8>='A' && LA31_8<='Z')||LA31_8=='_'||(LA31_8>='a' && LA31_8<='g')||(LA31_8>='i' && LA31_8<='z')) ) {s = 23;}

                        else if ( ((LA31_8>='\u0000' && LA31_8<='/')||(LA31_8>=':' && LA31_8<='@')||(LA31_8>='[' && LA31_8<='^')||LA31_8=='`'||(LA31_8>='{' && LA31_8<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 59 : 
                        int LA31_28 = input.LA(1);

                        s = -1;
                        if ( (LA31_28==' ') ) {s = 63;}

                        else if ( ((LA31_28>='0' && LA31_28<='9')||(LA31_28>='A' && LA31_28<='Z')||LA31_28=='_'||(LA31_28>='a' && LA31_28<='z')) ) {s = 23;}

                        else if ( ((LA31_28>='\u0000' && LA31_28<='\u001F')||(LA31_28>='!' && LA31_28<='/')||(LA31_28>=':' && LA31_28<='@')||(LA31_28>='[' && LA31_28<='^')||LA31_28=='`'||(LA31_28>='{' && LA31_28<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 60 : 
                        int LA31_9 = input.LA(1);

                        s = -1;
                        if ( (LA31_9=='h') ) {s = 35;}

                        else if ( ((LA31_9>='0' && LA31_9<='9')||(LA31_9>='A' && LA31_9<='Z')||LA31_9=='_'||(LA31_9>='a' && LA31_9<='g')||(LA31_9>='i' && LA31_9<='z')) ) {s = 23;}

                        else if ( ((LA31_9>='\u0000' && LA31_9<='/')||(LA31_9>=':' && LA31_9<='@')||(LA31_9>='[' && LA31_9<='^')||LA31_9=='`'||(LA31_9>='{' && LA31_9<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 61 : 
                        int LA31_15 = input.LA(1);

                        s = -1;
                        if ( ((LA31_15>='\u0000' && LA31_15<='\t')||(LA31_15>='\u000B' && LA31_15<='\f')||(LA31_15>='\u000E' && LA31_15<='\uFFFF')) ) {s = 52;}

                        else if ( (LA31_15=='\r') ) {s = 53;}

                        else if ( (LA31_15=='\n') ) {s = 54;}

                        else s = 38;

                        if ( s>=0 ) return s;
                        break;
                    case 62 : 
                        int LA31_11 = input.LA(1);

                        s = -1;
                        if ( (LA31_11=='\t'||LA31_11==' ') ) {s = 37;}

                        else if ( ((LA31_11>='\u0000' && LA31_11<='\b')||(LA31_11>='\u000B' && LA31_11<='\f')||(LA31_11>='\u000E' && LA31_11<='\u001F')||(LA31_11>='!' && LA31_11<='{')||(LA31_11>='}' && LA31_11<='\uFFFF')) ) {s = 39;}

                        else if ( (LA31_11=='\n'||LA31_11=='\r') ) {s = 40;}

                        else s = 38;

                        if ( s>=0 ) return s;
                        break;
                    case 63 : 
                        int LA31_63 = input.LA(1);

                        s = -1;
                        if ( (LA31_63=='o') ) {s = 91;}

                        else if ( ((LA31_63>='\u0000' && LA31_63<='n')||(LA31_63>='p' && LA31_63<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 64 : 
                        int LA31_91 = input.LA(1);

                        s = -1;
                        if ( (LA31_91=='r') ) {s = 113;}

                        else if ( ((LA31_91>='\u0000' && LA31_91<='q')||(LA31_91>='s' && LA31_91<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 65 : 
                        int LA31_113 = input.LA(1);

                        s = -1;
                        if ( (LA31_113=='d') ) {s = 132;}

                        else if ( ((LA31_113>='\u0000' && LA31_113<='c')||(LA31_113>='e' && LA31_113<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 66 : 
                        int LA31_132 = input.LA(1);

                        s = -1;
                        if ( (LA31_132=='e') ) {s = 150;}

                        else if ( ((LA31_132>='\u0000' && LA31_132<='d')||(LA31_132>='f' && LA31_132<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 67 : 
                        int LA31_150 = input.LA(1);

                        s = -1;
                        if ( (LA31_150=='r') ) {s = 162;}

                        else if ( ((LA31_150>='\u0000' && LA31_150<='q')||(LA31_150>='s' && LA31_150<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 68 : 
                        int LA31_162 = input.LA(1);

                        s = -1;
                        if ( (LA31_162==' ') ) {s = 173;}

                        else if ( ((LA31_162>='\u0000' && LA31_162<='\u001F')||(LA31_162>='!' && LA31_162<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 69 : 
                        int LA31_191 = input.LA(1);

                        s = -1;
                        if ( (LA31_191=='\t'||LA31_191==' ') ) {s = 200;}

                        else if ( (LA31_191==':') ) {s = 201;}

                        else if ( ((LA31_191>='0' && LA31_191<='9')||(LA31_191>='A' && LA31_191<='Z')||LA31_191=='_'||(LA31_191>='a' && LA31_191<='z')) ) {s = 23;}

                        else if ( ((LA31_191>='\u0000' && LA31_191<='\b')||(LA31_191>='\n' && LA31_191<='\u001F')||(LA31_191>='!' && LA31_191<='/')||(LA31_191>=';' && LA31_191<='@')||(LA31_191>='[' && LA31_191<='^')||LA31_191=='`'||(LA31_191>='{' && LA31_191<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 70 : 
                        int LA31_30 = input.LA(1);

                        s = -1;
                        if ( (LA31_30==' ') ) {s = 65;}

                        else if ( ((LA31_30>='0' && LA31_30<='9')||(LA31_30>='A' && LA31_30<='Z')||LA31_30=='_'||(LA31_30>='a' && LA31_30<='z')) ) {s = 23;}

                        else if ( ((LA31_30>='\u0000' && LA31_30<='\u001F')||(LA31_30>='!' && LA31_30<='/')||(LA31_30>=':' && LA31_30<='@')||(LA31_30>='[' && LA31_30<='^')||LA31_30=='`'||(LA31_30>='{' && LA31_30<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 71 : 
                        int LA31_173 = input.LA(1);

                        s = -1;
                        if ( (LA31_173=='t') ) {s = 184;}

                        else if ( ((LA31_173>='\u0000' && LA31_173<='s')||(LA31_173>='u' && LA31_173<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 72 : 
                        int LA31_184 = input.LA(1);

                        s = -1;
                        if ( (LA31_184=='o') ) {s = 193;}

                        else if ( ((LA31_184>='\u0000' && LA31_184<='n')||(LA31_184>='p' && LA31_184<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 73 : 
                        int LA31_72 = input.LA(1);

                        s = -1;
                        if ( (LA31_72=='\"') ) {s = 100;}

                        else if ( (LA31_72=='\r') ) {s = 101;}

                        else if ( (LA31_72=='\n') ) {s = 102;}

                        else if ( ((LA31_72>='\u0000' && LA31_72<='\t')||(LA31_72>='\u000B' && LA31_72<='\f')||(LA31_72>='\u000E' && LA31_72<='!')||(LA31_72>='#' && LA31_72<='\uFFFF')) ) {s = 103;}

                        if ( s>=0 ) return s;
                        break;
                    case 74 : 
                        int LA31_10 = input.LA(1);

                        s = -1;
                        if ( (LA31_10=='i') ) {s = 36;}

                        else if ( ((LA31_10>='0' && LA31_10<='9')||(LA31_10>='A' && LA31_10<='Z')||LA31_10=='_'||(LA31_10>='a' && LA31_10<='h')||(LA31_10>='j' && LA31_10<='z')) ) {s = 23;}

                        else if ( ((LA31_10>='\u0000' && LA31_10<='/')||(LA31_10>=':' && LA31_10<='@')||(LA31_10>='[' && LA31_10<='^')||LA31_10=='`'||(LA31_10>='{' && LA31_10<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 75 : 
                        int LA31_220 = input.LA(1);

                        s = -1;
                        if ( (LA31_220==':') ) {s = 221;}

                        else if ( (LA31_220=='\t'||LA31_220==' ') ) {s = 220;}

                        else if ( ((LA31_220>='\u0000' && LA31_220<='\b')||(LA31_220>='\n' && LA31_220<='\u001F')||(LA31_220>='!' && LA31_220<='9')||(LA31_220>=';' && LA31_220<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 76 : 
                        int LA31_4 = input.LA(1);

                        s = -1;
                        if ( (LA31_4=='n') ) {s = 28;}

                        else if ( (LA31_4==' ') ) {s = 29;}

                        else if ( ((LA31_4>='0' && LA31_4<='9')||(LA31_4>='A' && LA31_4<='Z')||LA31_4=='_'||(LA31_4>='a' && LA31_4<='m')||(LA31_4>='o' && LA31_4<='z')) ) {s = 23;}

                        else if ( ((LA31_4>='\u0000' && LA31_4<='\u001F')||(LA31_4>='!' && LA31_4<='/')||(LA31_4>=':' && LA31_4<='@')||(LA31_4>='[' && LA31_4<='^')||LA31_4=='`'||(LA31_4>='{' && LA31_4<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 77 : 
                        int LA31_94 = input.LA(1);

                        s = -1;
                        if ( ((LA31_94>='\u0000' && LA31_94<='\t')||(LA31_94>='\u000B' && LA31_94<='\f')||(LA31_94>='\u000E' && LA31_94<='\uFFFF')) ) {s = 118;}

                        else if ( (LA31_94=='\r') ) {s = 119;}

                        else if ( (LA31_94=='\n') ) {s = 120;}

                        if ( s>=0 ) return s;
                        break;
                    case 78 : 
                        int LA31_105 = input.LA(1);

                        s = -1;
                        if ( (LA31_105=='\n') ) {s = 106;}

                        else if ( ((LA31_105>='\u0000' && LA31_105<='\t')||(LA31_105>='\u000B' && LA31_105<='\uFFFF')) ) {s = 127;}

                        if ( s>=0 ) return s;
                        break;
                    case 79 : 
                        int LA31_126 = input.LA(1);

                        s = -1;
                        if ( (LA31_126=='\"') ) {s = 145;}

                        else if ( (LA31_126=='\r') ) {s = 101;}

                        else if ( (LA31_126=='\n') ) {s = 102;}

                        else if ( ((LA31_126>='\u0000' && LA31_126<='\t')||(LA31_126>='\u000B' && LA31_126<='\f')||(LA31_126>='\u000E' && LA31_126<='!')||(LA31_126>='#' && LA31_126<='\uFFFF')) ) {s = 103;}

                        if ( s>=0 ) return s;
                        break;
                    case 80 : 
                        int LA31_65 = input.LA(1);

                        s = -1;
                        if ( (LA31_65=='a') ) {s = 93;}

                        else if ( ((LA31_65>='\u0000' && LA31_65<='`')||(LA31_65>='b' && LA31_65<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 81 : 
                        int LA31_102 = input.LA(1);

                        s = -1;
                        if ( ((LA31_102>='\u0000' && LA31_102<='\uFFFF')) ) {s = 127;}

                        else s = 25;

                        if ( s>=0 ) return s;
                        break;
                    case 82 : 
                        int LA31_85 = input.LA(1);

                        s = -1;
                        if ( (LA31_85=='\n') ) {s = 86;}

                        else if ( ((LA31_85>='\u0000' && LA31_85<='\t')||(LA31_85>='\u000B' && LA31_85<='\uFFFF')) ) {s = 109;}

                        if ( s>=0 ) return s;
                        break;
                    case 83 : 
                        int LA31_29 = input.LA(1);

                        s = -1;
                        if ( (LA31_29=='w') ) {s = 64;}

                        else if ( ((LA31_29>='\u0000' && LA31_29<='v')||(LA31_29>='x' && LA31_29<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 84 : 
                        int LA31_64 = input.LA(1);

                        s = -1;
                        if ( (LA31_64=='a') ) {s = 92;}

                        else if ( ((LA31_64>='\u0000' && LA31_64<='`')||(LA31_64>='b' && LA31_64<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 85 : 
                        int LA31_92 = input.LA(1);

                        s = -1;
                        if ( (LA31_92=='n') ) {s = 114;}

                        else if ( ((LA31_92>='\u0000' && LA31_92<='m')||(LA31_92>='o' && LA31_92<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 86 : 
                        int LA31_114 = input.LA(1);

                        s = -1;
                        if ( (LA31_114=='t') ) {s = 133;}

                        else if ( ((LA31_114>='\u0000' && LA31_114<='s')||(LA31_114>='u' && LA31_114<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 87 : 
                        int LA31_133 = input.LA(1);

                        s = -1;
                        if ( (LA31_133==' ') ) {s = 151;}

                        else if ( ((LA31_133>='\u0000' && LA31_133<='\u001F')||(LA31_133>='!' && LA31_133<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 88 : 
                        int LA31_151 = input.LA(1);

                        s = -1;
                        if ( (LA31_151=='t') ) {s = 163;}

                        else if ( ((LA31_151>='\u0000' && LA31_151<='s')||(LA31_151>='u' && LA31_151<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 89 : 
                        int LA31_163 = input.LA(1);

                        s = -1;
                        if ( (LA31_163=='o') ) {s = 174;}

                        else if ( ((LA31_163>='\u0000' && LA31_163<='n')||(LA31_163>='p' && LA31_163<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 90 : 
                        int LA31_200 = input.LA(1);

                        s = -1;
                        if ( (LA31_200==':') ) {s = 201;}

                        else if ( (LA31_200=='\t'||LA31_200==' ') ) {s = 200;}

                        else if ( ((LA31_200>='\u0000' && LA31_200<='\b')||(LA31_200>='\n' && LA31_200<='\u001F')||(LA31_200>='!' && LA31_200<='9')||(LA31_200>=';' && LA31_200<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 91 : 
                        int LA31_174 = input.LA(1);

                        s = -1;
                        if ( (LA31_174==' ') ) {s = 185;}

                        else if ( ((LA31_174>='\u0000' && LA31_174<='\u001F')||(LA31_174>='!' && LA31_174<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 92 : 
                        int LA31_146 = input.LA(1);

                        s = -1;
                        if ( (LA31_146=='\'') ) {s = 146;}

                        else if ( (LA31_146=='\r') ) {s = 105;}

                        else if ( (LA31_146=='\n') ) {s = 106;}

                        else if ( ((LA31_146>='\u0000' && LA31_146<='\t')||(LA31_146>='\u000B' && LA31_146<='\f')||(LA31_146>='\u000E' && LA31_146<='&')||(LA31_146>='(' && LA31_146<='\uFFFF')) ) {s = 107;}

                        else s = 127;

                        if ( s>=0 ) return s;
                        break;
                    case 93 : 
                        int LA31_13 = input.LA(1);

                        s = -1;
                        if ( (LA31_13=='\'') ) {s = 46;}

                        else if ( (LA31_13=='\\') ) {s = 47;}

                        else if ( (LA31_13=='\r') ) {s = 48;}

                        else if ( (LA31_13=='\n') ) {s = 49;}

                        else if ( ((LA31_13>='\u0000' && LA31_13<='\t')||(LA31_13>='\u000B' && LA31_13<='\f')||(LA31_13>='\u000E' && LA31_13<='&')||(LA31_13>='(' && LA31_13<='[')||(LA31_13>=']' && LA31_13<='\uFFFF')) ) {s = 50;}

                        else s = 38;

                        if ( s>=0 ) return s;
                        break;
                    case 94 : 
                        int LA31_159 = input.LA(1);

                        s = -1;
                        if ( (LA31_159=='\t'||LA31_159==' ') ) {s = 167;}

                        else if ( (LA31_159==':') ) {s = 168;}

                        else if ( ((LA31_159>='0' && LA31_159<='9')||(LA31_159>='A' && LA31_159<='Z')||LA31_159=='_'||(LA31_159>='a' && LA31_159<='z')) ) {s = 23;}

                        else if ( ((LA31_159>='\u0000' && LA31_159<='\b')||(LA31_159>='\n' && LA31_159<='\u001F')||(LA31_159>='!' && LA31_159<='/')||(LA31_159>=';' && LA31_159<='@')||(LA31_159>='[' && LA31_159<='^')||LA31_159=='`'||(LA31_159>='{' && LA31_159<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 95 : 
                        int LA31_77 = input.LA(1);

                        s = -1;
                        if ( ((LA31_77>='\u0000' && LA31_77<='\uFFFF')) ) {s = 25;}

                        else s = 73;

                        if ( s>=0 ) return s;
                        break;
                    case 96 : 
                        int LA31_176 = input.LA(1);

                        s = -1;
                        if ( (LA31_176=='u') ) {s = 187;}

                        else if ( ((LA31_176>='0' && LA31_176<='9')||(LA31_176>='A' && LA31_176<='Z')||LA31_176=='_'||(LA31_176>='a' && LA31_176<='t')||(LA31_176>='v' && LA31_176<='z')) ) {s = 23;}

                        else if ( ((LA31_176>='\u0000' && LA31_176<='/')||(LA31_176>=':' && LA31_176<='@')||(LA31_176>='[' && LA31_176<='^')||LA31_176=='`'||(LA31_176>='{' && LA31_176<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 97 : 
                        int LA31_187 = input.LA(1);

                        s = -1;
                        if ( (LA31_187=='t') ) {s = 198;}

                        else if ( ((LA31_187>='0' && LA31_187<='9')||(LA31_187>='A' && LA31_187<='Z')||LA31_187=='_'||(LA31_187>='a' && LA31_187<='s')||(LA31_187>='u' && LA31_187<='z')) ) {s = 23;}

                        else if ( ((LA31_187>='\u0000' && LA31_187<='/')||(LA31_187>=':' && LA31_187<='@')||(LA31_187>='[' && LA31_187<='^')||LA31_187=='`'||(LA31_187>='{' && LA31_187<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 98 : 
                        int LA31_198 = input.LA(1);

                        s = -1;
                        if ( (LA31_198=='l') ) {s = 207;}

                        else if ( ((LA31_198>='0' && LA31_198<='9')||(LA31_198>='A' && LA31_198<='Z')||LA31_198=='_'||(LA31_198>='a' && LA31_198<='k')||(LA31_198>='m' && LA31_198<='z')) ) {s = 23;}

                        else if ( ((LA31_198>='\u0000' && LA31_198<='/')||(LA31_198>=':' && LA31_198<='@')||(LA31_198>='[' && LA31_198<='^')||LA31_198=='`'||(LA31_198>='{' && LA31_198<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 99 : 
                        int LA31_222 = input.LA(1);

                        s = -1;
                        if ( (LA31_222=='\r') ) {s = 223;}

                        else if ( (LA31_222=='\n') ) {s = 224;}

                        else if ( ((LA31_222>='\u0000' && LA31_222<='\t')||(LA31_222>='\u000B' && LA31_222<='\f')||(LA31_222>='\u000E' && LA31_222<='\uFFFF')) ) {s = 222;}

                        if ( s>=0 ) return s;
                        break;
                    case 100 : 
                        int LA31_170 = input.LA(1);

                        s = -1;
                        if ( (LA31_170=='\r') ) {s = 171;}

                        else if ( (LA31_170=='\n') ) {s = 172;}

                        else if ( ((LA31_170>='\u0000' && LA31_170<='\t')||(LA31_170>='\u000B' && LA31_170<='\f')||(LA31_170>='\u000E' && LA31_170<='\uFFFF')) ) {s = 170;}

                        if ( s>=0 ) return s;
                        break;
                    case 101 : 
                        int LA31_207 = input.LA(1);

                        s = -1;
                        if ( (LA31_207=='i') ) {s = 213;}

                        else if ( ((LA31_207>='0' && LA31_207<='9')||(LA31_207>='A' && LA31_207<='Z')||LA31_207=='_'||(LA31_207>='a' && LA31_207<='h')||(LA31_207>='j' && LA31_207<='z')) ) {s = 23;}

                        else if ( ((LA31_207>='\u0000' && LA31_207<='/')||(LA31_207>=':' && LA31_207<='@')||(LA31_207>='[' && LA31_207<='^')||LA31_207=='`'||(LA31_207>='{' && LA31_207<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 102 : 
                        int LA31_106 = input.LA(1);

                        s = -1;
                        if ( ((LA31_106>='\u0000' && LA31_106<='\uFFFF')) ) {s = 127;}

                        else s = 25;

                        if ( s>=0 ) return s;
                        break;
                    case 103 : 
                        int LA31_213 = input.LA(1);

                        s = -1;
                        if ( (LA31_213=='n') ) {s = 216;}

                        else if ( ((LA31_213>='0' && LA31_213<='9')||(LA31_213>='A' && LA31_213<='Z')||LA31_213=='_'||(LA31_213>='a' && LA31_213<='m')||(LA31_213>='o' && LA31_213<='z')) ) {s = 23;}

                        else if ( ((LA31_213>='\u0000' && LA31_213<='/')||(LA31_213>=':' && LA31_213<='@')||(LA31_213>='[' && LA31_213<='^')||LA31_213=='`'||(LA31_213>='{' && LA31_213<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 104 : 
                        int LA31_216 = input.LA(1);

                        s = -1;
                        if ( (LA31_216=='e') ) {s = 218;}

                        else if ( ((LA31_216>='0' && LA31_216<='9')||(LA31_216>='A' && LA31_216<='Z')||LA31_216=='_'||(LA31_216>='a' && LA31_216<='d')||(LA31_216>='f' && LA31_216<='z')) ) {s = 23;}

                        else if ( ((LA31_216>='\u0000' && LA31_216<='/')||(LA31_216>=':' && LA31_216<='@')||(LA31_216>='[' && LA31_216<='^')||LA31_216=='`'||(LA31_216>='{' && LA31_216<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 105 : 
                        int LA31_76 = input.LA(1);

                        s = -1;
                        if ( (LA31_76=='\"') ) {s = 77;}

                        else if ( (LA31_76=='\\') ) {s = 42;}

                        else if ( (LA31_76=='\r') ) {s = 43;}

                        else if ( (LA31_76=='\n') ) {s = 44;}

                        else if ( ((LA31_76>='\u0000' && LA31_76<='\t')||(LA31_76>='\u000B' && LA31_76<='\f')||(LA31_76>='\u000E' && LA31_76<='!')||(LA31_76>='#' && LA31_76<='[')||(LA31_76>=']' && LA31_76<='\uFFFF')) ) {s = 45;}

                        if ( s>=0 ) return s;
                        break;
                    case 106 : 
                        int LA31_186 = input.LA(1);

                        s = -1;
                        if ( (LA31_186=='u') ) {s = 197;}

                        else if ( ((LA31_186>='\u0000' && LA31_186<='t')||(LA31_186>='v' && LA31_186<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 107 : 
                        int LA31_86 = input.LA(1);

                        s = -1;
                        if ( ((LA31_86>='\u0000' && LA31_86<='\uFFFF')) ) {s = 109;}

                        else s = 25;

                        if ( s>=0 ) return s;
                        break;
                    case 108 : 
                        int LA31_197 = input.LA(1);

                        s = -1;
                        if ( (LA31_197=='t') ) {s = 206;}

                        else if ( ((LA31_197>='\u0000' && LA31_197<='s')||(LA31_197>='u' && LA31_197<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 109 : 
                        int LA31_206 = input.LA(1);

                        s = -1;
                        if ( (LA31_206=='l') ) {s = 212;}

                        else if ( ((LA31_206>='\u0000' && LA31_206<='k')||(LA31_206>='m' && LA31_206<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 110 : 
                        int LA31_212 = input.LA(1);

                        s = -1;
                        if ( (LA31_212=='i') ) {s = 215;}

                        else if ( ((LA31_212>='\u0000' && LA31_212<='h')||(LA31_212>='j' && LA31_212<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 111 : 
                        int LA31_78 = input.LA(1);

                        s = -1;
                        if ( (LA31_78=='\'') ) {s = 104;}

                        else if ( (LA31_78=='\r') ) {s = 105;}

                        else if ( (LA31_78=='\n') ) {s = 106;}

                        else if ( ((LA31_78>='\u0000' && LA31_78<='\t')||(LA31_78>='\u000B' && LA31_78<='\f')||(LA31_78>='\u000E' && LA31_78<='&')||(LA31_78>='(' && LA31_78<='\uFFFF')) ) {s = 107;}

                        if ( s>=0 ) return s;
                        break;
                    case 112 : 
                        int LA31_215 = input.LA(1);

                        s = -1;
                        if ( (LA31_215=='n') ) {s = 217;}

                        else if ( ((LA31_215>='\u0000' && LA31_215<='m')||(LA31_215>='o' && LA31_215<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 113 : 
                        int LA31_45 = input.LA(1);

                        s = -1;
                        if ( (LA31_45=='\"') ) {s = 77;}

                        else if ( (LA31_45=='\\') ) {s = 42;}

                        else if ( (LA31_45=='\r') ) {s = 43;}

                        else if ( (LA31_45=='\n') ) {s = 44;}

                        else if ( ((LA31_45>='\u0000' && LA31_45<='\t')||(LA31_45>='\u000B' && LA31_45<='\f')||(LA31_45>='\u000E' && LA31_45<='!')||(LA31_45>='#' && LA31_45<='[')||(LA31_45>=']' && LA31_45<='\uFFFF')) ) {s = 45;}

                        if ( s>=0 ) return s;
                        break;
                    case 114 : 
                        int LA31_82 = input.LA(1);

                        s = -1;
                        if ( ((LA31_82>='\u0000' && LA31_82<='\uFFFF')) ) {s = 25;}

                        else s = 73;

                        if ( s>=0 ) return s;
                        break;
                    case 115 : 
                        int LA31_217 = input.LA(1);

                        s = -1;
                        if ( (LA31_217=='e') ) {s = 219;}

                        else if ( ((LA31_217>='\u0000' && LA31_217<='d')||(LA31_217>='f' && LA31_217<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 116 : 
                        int LA31_33 = input.LA(1);

                        s = -1;
                        if ( (LA31_33=='c') ) {s = 68;}

                        else if ( ((LA31_33>='0' && LA31_33<='9')||(LA31_33>='A' && LA31_33<='Z')||LA31_33=='_'||(LA31_33>='a' && LA31_33<='b')||(LA31_33>='d' && LA31_33<='z')) ) {s = 23;}

                        else if ( ((LA31_33>='\u0000' && LA31_33<='/')||(LA31_33>=':' && LA31_33<='@')||(LA31_33>='[' && LA31_33<='^')||LA31_33=='`'||(LA31_33>='{' && LA31_33<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 117 : 
                        int LA31_68 = input.LA(1);

                        s = -1;
                        if ( (LA31_68=='k') ) {s = 96;}

                        else if ( ((LA31_68>='0' && LA31_68<='9')||(LA31_68>='A' && LA31_68<='Z')||LA31_68=='_'||(LA31_68>='a' && LA31_68<='j')||(LA31_68>='l' && LA31_68<='z')) ) {s = 23;}

                        else if ( ((LA31_68>='\u0000' && LA31_68<='/')||(LA31_68>=':' && LA31_68<='@')||(LA31_68>='[' && LA31_68<='^')||LA31_68=='`'||(LA31_68>='{' && LA31_68<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 118 : 
                        int LA31_96 = input.LA(1);

                        s = -1;
                        if ( (LA31_96=='g') ) {s = 122;}

                        else if ( ((LA31_96>='0' && LA31_96<='9')||(LA31_96>='A' && LA31_96<='Z')||LA31_96=='_'||(LA31_96>='a' && LA31_96<='f')||(LA31_96>='h' && LA31_96<='z')) ) {s = 23;}

                        else if ( ((LA31_96>='\u0000' && LA31_96<='/')||(LA31_96>=':' && LA31_96<='@')||(LA31_96>='[' && LA31_96<='^')||LA31_96=='`'||(LA31_96>='{' && LA31_96<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 119 : 
                        int LA31_122 = input.LA(1);

                        s = -1;
                        if ( (LA31_122=='r') ) {s = 137;}

                        else if ( ((LA31_122>='0' && LA31_122<='9')||(LA31_122>='A' && LA31_122<='Z')||LA31_122=='_'||(LA31_122>='a' && LA31_122<='q')||(LA31_122>='s' && LA31_122<='z')) ) {s = 23;}

                        else if ( ((LA31_122>='\u0000' && LA31_122<='/')||(LA31_122>=':' && LA31_122<='@')||(LA31_122>='[' && LA31_122<='^')||LA31_122=='`'||(LA31_122>='{' && LA31_122<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 120 : 
                        int LA31_137 = input.LA(1);

                        s = -1;
                        if ( (LA31_137=='o') ) {s = 153;}

                        else if ( ((LA31_137>='0' && LA31_137<='9')||(LA31_137>='A' && LA31_137<='Z')||LA31_137=='_'||(LA31_137>='a' && LA31_137<='n')||(LA31_137>='p' && LA31_137<='z')) ) {s = 23;}

                        else if ( ((LA31_137>='\u0000' && LA31_137<='/')||(LA31_137>=':' && LA31_137<='@')||(LA31_137>='[' && LA31_137<='^')||LA31_137=='`'||(LA31_137>='{' && LA31_137<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 121 : 
                        int LA31_153 = input.LA(1);

                        s = -1;
                        if ( (LA31_153=='u') ) {s = 165;}

                        else if ( ((LA31_153>='0' && LA31_153<='9')||(LA31_153>='A' && LA31_153<='Z')||LA31_153=='_'||(LA31_153>='a' && LA31_153<='t')||(LA31_153>='v' && LA31_153<='z')) ) {s = 23;}

                        else if ( ((LA31_153>='\u0000' && LA31_153<='/')||(LA31_153>=':' && LA31_153<='@')||(LA31_153>='[' && LA31_153<='^')||LA31_153=='`'||(LA31_153>='{' && LA31_153<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 122 : 
                        int LA31_128 = input.LA(1);

                        s = -1;
                        if ( (LA31_128=='\'') ) {s = 146;}

                        else if ( (LA31_128=='\r') ) {s = 105;}

                        else if ( (LA31_128=='\n') ) {s = 106;}

                        else if ( ((LA31_128>='\u0000' && LA31_128<='\t')||(LA31_128>='\u000B' && LA31_128<='\f')||(LA31_128>='\u000E' && LA31_128<='&')||(LA31_128>='(' && LA31_128<='\uFFFF')) ) {s = 107;}

                        if ( s>=0 ) return s;
                        break;
                    case 123 : 
                        int LA31_165 = input.LA(1);

                        s = -1;
                        if ( (LA31_165=='n') ) {s = 178;}

                        else if ( ((LA31_165>='0' && LA31_165<='9')||(LA31_165>='A' && LA31_165<='Z')||LA31_165=='_'||(LA31_165>='a' && LA31_165<='m')||(LA31_165>='o' && LA31_165<='z')) ) {s = 23;}

                        else if ( ((LA31_165>='\u0000' && LA31_165<='/')||(LA31_165>=':' && LA31_165<='@')||(LA31_165>='[' && LA31_165<='^')||LA31_165=='`'||(LA31_165>='{' && LA31_165<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 124 : 
                        int LA31_178 = input.LA(1);

                        s = -1;
                        if ( (LA31_178=='d') ) {s = 191;}

                        else if ( ((LA31_178>='0' && LA31_178<='9')||(LA31_178>='A' && LA31_178<='Z')||LA31_178=='_'||(LA31_178>='a' && LA31_178<='c')||(LA31_178>='e' && LA31_178<='z')) ) {s = 23;}

                        else if ( ((LA31_178>='\u0000' && LA31_178<='/')||(LA31_178>=':' && LA31_178<='@')||(LA31_178>='[' && LA31_178<='^')||LA31_178=='`'||(LA31_178>='{' && LA31_178<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 125 : 
                        int LA31_185 = input.LA(1);

                        s = -1;
                        if ( ((LA31_185>='\u0000' && LA31_185<='\t')||(LA31_185>='\u000B' && LA31_185<='\f')||(LA31_185>='\u000E' && LA31_185<='\uFFFF')) ) {s = 194;}

                        else if ( (LA31_185=='\r') ) {s = 195;}

                        else if ( (LA31_185=='\n') ) {s = 196;}

                        if ( s>=0 ) return s;
                        break;
                    case 126 : 
                        int LA31_201 = input.LA(1);

                        s = -1;
                        if ( ((LA31_201>='\u0000' && LA31_201<='\t')||(LA31_201>='\u000B' && LA31_201<='\f')||(LA31_201>='\u000E' && LA31_201<='\uFFFF')) ) {s = 208;}

                        else if ( (LA31_201=='\r') ) {s = 209;}

                        else if ( (LA31_201=='\n') ) {s = 210;}

                        if ( s>=0 ) return s;
                        break;
                    case 127 : 
                        int LA31_179 = input.LA(1);

                        s = -1;
                        if ( (LA31_179=='\r') ) {s = 180;}

                        else if ( (LA31_179=='\n') ) {s = 181;}

                        else if ( (LA31_179=='\t'||LA31_179==' ') ) {s = 179;}

                        else if ( ((LA31_179>='\u0000' && LA31_179<='\b')||(LA31_179>='\u000B' && LA31_179<='\f')||(LA31_179>='\u000E' && LA31_179<='\u001F')||(LA31_179>='!' && LA31_179<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 128 : 
                        int LA31_175 = input.LA(1);

                        s = -1;
                        if ( (LA31_175==':') ) {s = 177;}

                        else if ( (LA31_175=='\t'||LA31_175==' ') ) {s = 175;}

                        else if ( (LA31_175=='O') ) {s = 186;}

                        else if ( ((LA31_175>='\u0000' && LA31_175<='\b')||(LA31_175>='\n' && LA31_175<='\u001F')||(LA31_175>='!' && LA31_175<='9')||(LA31_175>=';' && LA31_175<='N')||(LA31_175>='P' && LA31_175<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 129 : 
                        int LA31_6 = input.LA(1);

                        s = -1;
                        if ( (LA31_6=='c') ) {s = 32;}

                        else if ( ((LA31_6>='0' && LA31_6<='9')||(LA31_6>='A' && LA31_6<='Z')||LA31_6=='_'||(LA31_6>='a' && LA31_6<='b')||(LA31_6>='d' && LA31_6<='z')) ) {s = 23;}

                        else if ( ((LA31_6>='\u0000' && LA31_6<='/')||(LA31_6>=':' && LA31_6<='@')||(LA31_6>='[' && LA31_6<='^')||LA31_6=='`'||(LA31_6>='{' && LA31_6<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 130 : 
                        int LA31_34 = input.LA(1);

                        s = -1;
                        if ( (LA31_34=='e') ) {s = 69;}

                        else if ( ((LA31_34>='0' && LA31_34<='9')||(LA31_34>='A' && LA31_34<='Z')||LA31_34=='_'||(LA31_34>='a' && LA31_34<='d')||(LA31_34>='f' && LA31_34<='z')) ) {s = 23;}

                        else if ( ((LA31_34>='\u0000' && LA31_34<='/')||(LA31_34>=':' && LA31_34<='@')||(LA31_34>='[' && LA31_34<='^')||LA31_34=='`'||(LA31_34>='{' && LA31_34<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 131 : 
                        int LA31_69 = input.LA(1);

                        s = -1;
                        if ( (LA31_69=='n') ) {s = 97;}

                        else if ( ((LA31_69>='0' && LA31_69<='9')||(LA31_69>='A' && LA31_69<='Z')||LA31_69=='_'||(LA31_69>='a' && LA31_69<='m')||(LA31_69>='o' && LA31_69<='z')) ) {s = 23;}

                        else if ( ((LA31_69>='\u0000' && LA31_69<='/')||(LA31_69>=':' && LA31_69<='@')||(LA31_69>='[' && LA31_69<='^')||LA31_69=='`'||(LA31_69>='{' && LA31_69<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 132 : 
                        int LA31_97 = input.LA(1);

                        s = -1;
                        if ( (LA31_97==' ') ) {s = 123;}

                        else if ( ((LA31_97>='0' && LA31_97<='9')||(LA31_97>='A' && LA31_97<='Z')||LA31_97=='_'||(LA31_97>='a' && LA31_97<='z')) ) {s = 23;}

                        else if ( ((LA31_97>='\u0000' && LA31_97<='\u001F')||(LA31_97>='!' && LA31_97<='/')||(LA31_97>=':' && LA31_97<='@')||(LA31_97>='[' && LA31_97<='^')||LA31_97=='`'||(LA31_97>='{' && LA31_97<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 133 : 
                        int LA31_108 = input.LA(1);

                        s = -1;
                        if ( (LA31_108=='*') ) {s = 84;}

                        else if ( (LA31_108=='\r') ) {s = 85;}

                        else if ( (LA31_108=='\n') ) {s = 86;}

                        else if ( ((LA31_108>='\u0000' && LA31_108<='\t')||(LA31_108>='\u000B' && LA31_108<='\f')||(LA31_108>='\u000E' && LA31_108<=')')||(LA31_108>='+' && LA31_108<='\uFFFF')) ) {s = 87;}

                        else s = 109;

                        if ( s>=0 ) return s;
                        break;
                    case 134 : 
                        int LA31_35 = input.LA(1);

                        s = -1;
                        if ( (LA31_35=='e') ) {s = 70;}

                        else if ( ((LA31_35>='0' && LA31_35<='9')||(LA31_35>='A' && LA31_35<='Z')||LA31_35=='_'||(LA31_35>='a' && LA31_35<='d')||(LA31_35>='f' && LA31_35<='z')) ) {s = 23;}

                        else if ( ((LA31_35>='\u0000' && LA31_35<='/')||(LA31_35>=':' && LA31_35<='@')||(LA31_35>='[' && LA31_35<='^')||LA31_35=='`'||(LA31_35>='{' && LA31_35<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 135 : 
                        int LA31_70 = input.LA(1);

                        s = -1;
                        if ( (LA31_70=='n') ) {s = 98;}

                        else if ( ((LA31_70>='0' && LA31_70<='9')||(LA31_70>='A' && LA31_70<='Z')||LA31_70=='_'||(LA31_70>='a' && LA31_70<='m')||(LA31_70>='o' && LA31_70<='z')) ) {s = 23;}

                        else if ( ((LA31_70>='\u0000' && LA31_70<='/')||(LA31_70>=':' && LA31_70<='@')||(LA31_70>='[' && LA31_70<='^')||LA31_70=='`'||(LA31_70>='{' && LA31_70<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 136 : 
                        int LA31_98 = input.LA(1);

                        s = -1;
                        if ( (LA31_98==' ') ) {s = 124;}

                        else if ( ((LA31_98>='0' && LA31_98<='9')||(LA31_98>='A' && LA31_98<='Z')||LA31_98=='_'||(LA31_98>='a' && LA31_98<='z')) ) {s = 23;}

                        else if ( ((LA31_98>='\u0000' && LA31_98<='\u001F')||(LA31_98>='!' && LA31_98<='/')||(LA31_98>=':' && LA31_98<='@')||(LA31_98>='[' && LA31_98<='^')||LA31_98=='`'||(LA31_98>='{' && LA31_98<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 137 : 
                        int LA31_36 = input.LA(1);

                        s = -1;
                        if ( (LA31_36=='v') ) {s = 71;}

                        else if ( ((LA31_36>='0' && LA31_36<='9')||(LA31_36>='A' && LA31_36<='Z')||LA31_36=='_'||(LA31_36>='a' && LA31_36<='u')||(LA31_36>='w' && LA31_36<='z')) ) {s = 23;}

                        else if ( ((LA31_36>='\u0000' && LA31_36<='/')||(LA31_36>=':' && LA31_36<='@')||(LA31_36>='[' && LA31_36<='^')||LA31_36=='`'||(LA31_36>='{' && LA31_36<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 138 : 
                        int LA31_71 = input.LA(1);

                        s = -1;
                        if ( (LA31_71=='e') ) {s = 99;}

                        else if ( ((LA31_71>='0' && LA31_71<='9')||(LA31_71>='A' && LA31_71<='Z')||LA31_71=='_'||(LA31_71>='a' && LA31_71<='d')||(LA31_71>='f' && LA31_71<='z')) ) {s = 23;}

                        else if ( ((LA31_71>='\u0000' && LA31_71<='/')||(LA31_71>=':' && LA31_71<='@')||(LA31_71>='[' && LA31_71<='^')||LA31_71=='`'||(LA31_71>='{' && LA31_71<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 139 : 
                        int LA31_99 = input.LA(1);

                        s = -1;
                        if ( (LA31_99=='n') ) {s = 125;}

                        else if ( ((LA31_99>='0' && LA31_99<='9')||(LA31_99>='A' && LA31_99<='Z')||LA31_99=='_'||(LA31_99>='a' && LA31_99<='m')||(LA31_99>='o' && LA31_99<='z')) ) {s = 23;}

                        else if ( ((LA31_99>='\u0000' && LA31_99<='/')||(LA31_99>=':' && LA31_99<='@')||(LA31_99>='[' && LA31_99<='^')||LA31_99=='`'||(LA31_99>='{' && LA31_99<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 140 : 
                        int LA31_125 = input.LA(1);

                        s = -1;
                        if ( (LA31_125==' ') ) {s = 144;}

                        else if ( ((LA31_125>='0' && LA31_125<='9')||(LA31_125>='A' && LA31_125<='Z')||LA31_125=='_'||(LA31_125>='a' && LA31_125<='z')) ) {s = 23;}

                        else if ( ((LA31_125>='\u0000' && LA31_125<='\u001F')||(LA31_125>='!' && LA31_125<='/')||(LA31_125>=':' && LA31_125<='@')||(LA31_125>='[' && LA31_125<='^')||LA31_125=='`'||(LA31_125>='{' && LA31_125<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 141 : 
                        int LA31_100 = input.LA(1);

                        s = -1;
                        if ( (LA31_100=='\"') ) {s = 126;}

                        else if ( (LA31_100=='\r') ) {s = 101;}

                        else if ( (LA31_100=='\n') ) {s = 102;}

                        else if ( ((LA31_100>='\u0000' && LA31_100<='\t')||(LA31_100>='\u000B' && LA31_100<='\f')||(LA31_100>='\u000E' && LA31_100<='!')||(LA31_100>='#' && LA31_100<='\uFFFF')) ) {s = 103;}

                        if ( s>=0 ) return s;
                        break;
                    case 142 : 
                        int LA31_31 = input.LA(1);

                        s = -1;
                        if ( (LA31_31=='d') ) {s = 66;}

                        else if ( ((LA31_31>='0' && LA31_31<='9')||(LA31_31>='A' && LA31_31<='Z')||LA31_31=='_'||(LA31_31>='a' && LA31_31<='c')||(LA31_31>='e' && LA31_31<='z')) ) {s = 23;}

                        else if ( ((LA31_31>='\u0000' && LA31_31<='/')||(LA31_31>=':' && LA31_31<='@')||(LA31_31>='[' && LA31_31<='^')||LA31_31=='`'||(LA31_31>='{' && LA31_31<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 143 : 
                        int LA31_66 = input.LA(1);

                        s = -1;
                        if ( (LA31_66==' ') ) {s = 94;}

                        else if ( ((LA31_66>='0' && LA31_66<='9')||(LA31_66>='A' && LA31_66<='Z')||LA31_66=='_'||(LA31_66>='a' && LA31_66<='z')) ) {s = 23;}

                        else if ( ((LA31_66>='\u0000' && LA31_66<='\u001F')||(LA31_66>='!' && LA31_66<='/')||(LA31_66>=':' && LA31_66<='@')||(LA31_66>='[' && LA31_66<='^')||LA31_66=='`'||(LA31_66>='{' && LA31_66<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 144 : 
                        int LA31_202 = input.LA(1);

                        s = -1;
                        if ( (LA31_202=='\r') ) {s = 203;}

                        else if ( (LA31_202=='\n') ) {s = 204;}

                        else if ( ((LA31_202>='\u0000' && LA31_202<='\t')||(LA31_202>='\u000B' && LA31_202<='\f')||(LA31_202>='\u000E' && LA31_202<='\uFFFF')) ) {s = 202;}

                        if ( s>=0 ) return s;
                        break;
                    case 145 : 
                        int LA31_5 = input.LA(1);

                        s = -1;
                        if ( (LA31_5=='s') ) {s = 30;}

                        else if ( (LA31_5=='n') ) {s = 31;}

                        else if ( ((LA31_5>='0' && LA31_5<='9')||(LA31_5>='A' && LA31_5<='Z')||LA31_5=='_'||(LA31_5>='a' && LA31_5<='m')||(LA31_5>='o' && LA31_5<='r')||(LA31_5>='t' && LA31_5<='z')) ) {s = 23;}

                        else if ( ((LA31_5>='\u0000' && LA31_5<='/')||(LA31_5>=':' && LA31_5<='@')||(LA31_5>='[' && LA31_5<='^')||LA31_5=='`'||(LA31_5>='{' && LA31_5<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 146 : 
                        int LA31_208 = input.LA(1);

                        s = -1;
                        if ( (LA31_208=='\r') ) {s = 209;}

                        else if ( (LA31_208=='\n') ) {s = 210;}

                        else if ( ((LA31_208>='\u0000' && LA31_208<='\t')||(LA31_208>='\u000B' && LA31_208<='\f')||(LA31_208>='\u000E' && LA31_208<='\uFFFF')) ) {s = 208;}

                        if ( s>=0 ) return s;
                        break;
                    case 147 : 
                        int LA31_123 = input.LA(1);

                        s = -1;
                        if ( ((LA31_123>='\u0000' && LA31_123<='\t')||(LA31_123>='\u000B' && LA31_123<='\f')||(LA31_123>='\u000E' && LA31_123<='\uFFFF')) ) {s = 138;}

                        else if ( (LA31_123=='\r') ) {s = 139;}

                        else if ( (LA31_123=='\n') ) {s = 140;}

                        if ( s>=0 ) return s;
                        break;
                    case 148 : 
                        int LA31_18 = input.LA(1);

                        s = -1;
                        if ( ((LA31_18>='0' && LA31_18<='9')||(LA31_18>='A' && LA31_18<='Z')||LA31_18=='_'||(LA31_18>='a' && LA31_18<='z')) ) {s = 23;}

                        else if ( ((LA31_18>='\u0000' && LA31_18<='/')||(LA31_18>=':' && LA31_18<='@')||(LA31_18>='[' && LA31_18<='^')||LA31_18=='`'||(LA31_18>='{' && LA31_18<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 149 : 
                        int LA31_16 = input.LA(1);

                        s = -1;
                        if ( ((LA31_16>='A' && LA31_16<='Z')||LA31_16=='_'||(LA31_16>='a' && LA31_16<='z')) ) {s = 55;}

                        else if ( ((LA31_16>='\u0000' && LA31_16<='@')||(LA31_16>='[' && LA31_16<='^')||LA31_16=='`'||(LA31_16>='{' && LA31_16<='\uFFFF')) ) {s = 25;}

                        else s = 38;

                        if ( s>=0 ) return s;
                        break;
                    case 150 : 
                        int LA31_81 = input.LA(1);

                        s = -1;
                        if ( (LA31_81=='\'') ) {s = 82;}

                        else if ( (LA31_81=='\\') ) {s = 47;}

                        else if ( (LA31_81=='\r') ) {s = 48;}

                        else if ( (LA31_81=='\n') ) {s = 49;}

                        else if ( ((LA31_81>='\u0000' && LA31_81<='\t')||(LA31_81>='\u000B' && LA31_81<='\f')||(LA31_81>='\u000E' && LA31_81<='&')||(LA31_81>='(' && LA31_81<='[')||(LA31_81>=']' && LA31_81<='\uFFFF')) ) {s = 50;}

                        if ( s>=0 ) return s;
                        break;
                    case 151 : 
                        int LA31_104 = input.LA(1);

                        s = -1;
                        if ( (LA31_104=='\'') ) {s = 128;}

                        else if ( (LA31_104=='\r') ) {s = 105;}

                        else if ( (LA31_104=='\n') ) {s = 106;}

                        else if ( ((LA31_104>='\u0000' && LA31_104<='\t')||(LA31_104>='\u000B' && LA31_104<='\f')||(LA31_104>='\u000E' && LA31_104<='&')||(LA31_104>='(' && LA31_104<='\uFFFF')) ) {s = 107;}

                        if ( s>=0 ) return s;
                        break;
                    case 152 : 
                        int LA31_55 = input.LA(1);

                        s = -1;
                        if ( ((LA31_55>='0' && LA31_55<='9')||(LA31_55>='A' && LA31_55<='Z')||LA31_55=='_'||(LA31_55>='a' && LA31_55<='z')) ) {s = 23;}

                        else if ( ((LA31_55>='\u0000' && LA31_55<='/')||(LA31_55>=':' && LA31_55<='@')||(LA31_55>='[' && LA31_55<='^')||LA31_55=='`'||(LA31_55>='{' && LA31_55<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 153 : 
                        int LA31_50 = input.LA(1);

                        s = -1;
                        if ( (LA31_50=='\'') ) {s = 82;}

                        else if ( (LA31_50=='\\') ) {s = 47;}

                        else if ( (LA31_50=='\r') ) {s = 48;}

                        else if ( (LA31_50=='\n') ) {s = 49;}

                        else if ( ((LA31_50>='\u0000' && LA31_50<='\t')||(LA31_50>='\u000B' && LA31_50<='\f')||(LA31_50>='\u000E' && LA31_50<='&')||(LA31_50>='(' && LA31_50<='[')||(LA31_50>=']' && LA31_50<='\uFFFF')) ) {s = 50;}

                        if ( s>=0 ) return s;
                        break;
                    case 154 : 
                        int LA31_115 = input.LA(1);

                        s = -1;
                        if ( (LA31_115=='\r') ) {s = 116;}

                        else if ( (LA31_115=='\n') ) {s = 117;}

                        else if ( ((LA31_115>='\u0000' && LA31_115<='\t')||(LA31_115>='\u000B' && LA31_115<='\f')||(LA31_115>='\u000E' && LA31_115<='\uFFFF')) ) {s = 115;}

                        if ( s>=0 ) return s;
                        break;
                    case 155 : 
                        int LA31_177 = input.LA(1);

                        s = -1;
                        if ( ((LA31_177>='\u0000' && LA31_177<='\t')||(LA31_177>='\u000B' && LA31_177<='\f')||(LA31_177>='\u000E' && LA31_177<='\uFFFF')) ) {s = 188;}

                        else if ( (LA31_177=='\r') ) {s = 189;}

                        else if ( (LA31_177=='\n') ) {s = 190;}

                        if ( s>=0 ) return s;
                        break;
                    case 156 : 
                        int LA31_32 = input.LA(1);

                        s = -1;
                        if ( (LA31_32=='e') ) {s = 67;}

                        else if ( ((LA31_32>='0' && LA31_32<='9')||(LA31_32>='A' && LA31_32<='Z')||LA31_32=='_'||(LA31_32>='a' && LA31_32<='d')||(LA31_32>='f' && LA31_32<='z')) ) {s = 23;}

                        else if ( ((LA31_32>='\u0000' && LA31_32<='/')||(LA31_32>=':' && LA31_32<='@')||(LA31_32>='[' && LA31_32<='^')||LA31_32=='`'||(LA31_32>='{' && LA31_32<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 157 : 
                        int LA31_74 = input.LA(1);

                        s = -1;
                        if ( (LA31_74=='\n') ) {s = 44;}

                        else if ( ((LA31_74>='\u0000' && LA31_74<='\t')||(LA31_74>='\u000B' && LA31_74<='\uFFFF')) ) {s = 73;}

                        if ( s>=0 ) return s;
                        break;
                    case 158 : 
                        int LA31_67 = input.LA(1);

                        s = -1;
                        if ( (LA31_67=='n') ) {s = 95;}

                        else if ( ((LA31_67>='0' && LA31_67<='9')||(LA31_67>='A' && LA31_67<='Z')||LA31_67=='_'||(LA31_67>='a' && LA31_67<='m')||(LA31_67>='o' && LA31_67<='z')) ) {s = 23;}

                        else if ( ((LA31_67>='\u0000' && LA31_67<='/')||(LA31_67>=':' && LA31_67<='@')||(LA31_67>='[' && LA31_67<='^')||LA31_67=='`'||(LA31_67>='{' && LA31_67<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 159 : 
                        int LA31_138 = input.LA(1);

                        s = -1;
                        if ( (LA31_138=='\r') ) {s = 139;}

                        else if ( (LA31_138=='\n') ) {s = 140;}

                        else if ( ((LA31_138>='\u0000' && LA31_138<='\t')||(LA31_138>='\u000B' && LA31_138<='\f')||(LA31_138>='\u000E' && LA31_138<='\uFFFF')) ) {s = 138;}

                        if ( s>=0 ) return s;
                        break;
                    case 160 : 
                        int LA31_95 = input.LA(1);

                        s = -1;
                        if ( (LA31_95=='a') ) {s = 121;}

                        else if ( ((LA31_95>='0' && LA31_95<='9')||(LA31_95>='A' && LA31_95<='Z')||LA31_95=='_'||(LA31_95>='b' && LA31_95<='z')) ) {s = 23;}

                        else if ( ((LA31_95>='\u0000' && LA31_95<='/')||(LA31_95>=':' && LA31_95<='@')||(LA31_95>='[' && LA31_95<='^')||LA31_95=='`'||(LA31_95>='{' && LA31_95<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 161 : 
                        int LA31_43 = input.LA(1);

                        s = -1;
                        if ( ((LA31_43>='\u0000' && LA31_43<='\t')||(LA31_43>='\u000B' && LA31_43<='\uFFFF')) ) {s = 73;}

                        else if ( (LA31_43=='\n') ) {s = 44;}

                        if ( s>=0 ) return s;
                        break;
                    case 162 : 
                        int LA31_121 = input.LA(1);

                        s = -1;
                        if ( (LA31_121=='r') ) {s = 136;}

                        else if ( ((LA31_121>='0' && LA31_121<='9')||(LA31_121>='A' && LA31_121<='Z')||LA31_121=='_'||(LA31_121>='a' && LA31_121<='q')||(LA31_121>='s' && LA31_121<='z')) ) {s = 23;}

                        else if ( ((LA31_121>='\u0000' && LA31_121<='/')||(LA31_121>=':' && LA31_121<='@')||(LA31_121>='[' && LA31_121<='^')||LA31_121=='`'||(LA31_121>='{' && LA31_121<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 163 : 
                        int LA31_136 = input.LA(1);

                        s = -1;
                        if ( (LA31_136=='i') ) {s = 152;}

                        else if ( ((LA31_136>='0' && LA31_136<='9')||(LA31_136>='A' && LA31_136<='Z')||LA31_136=='_'||(LA31_136>='a' && LA31_136<='h')||(LA31_136>='j' && LA31_136<='z')) ) {s = 23;}

                        else if ( ((LA31_136>='\u0000' && LA31_136<='/')||(LA31_136>=':' && LA31_136<='@')||(LA31_136>='[' && LA31_136<='^')||LA31_136=='`'||(LA31_136>='{' && LA31_136<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 164 : 
                        int LA31_152 = input.LA(1);

                        s = -1;
                        if ( (LA31_152=='o') ) {s = 164;}

                        else if ( ((LA31_152>='0' && LA31_152<='9')||(LA31_152>='A' && LA31_152<='Z')||LA31_152=='_'||(LA31_152>='a' && LA31_152<='n')||(LA31_152>='p' && LA31_152<='z')) ) {s = 23;}

                        else if ( ((LA31_152>='\u0000' && LA31_152<='/')||(LA31_152>=':' && LA31_152<='@')||(LA31_152>='[' && LA31_152<='^')||LA31_152=='`'||(LA31_152>='{' && LA31_152<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 165 : 
                        int LA31_1 = input.LA(1);

                        s = -1;
                        if ( (LA31_1=='x') ) {s = 22;}

                        else if ( ((LA31_1>='0' && LA31_1<='9')||(LA31_1>='A' && LA31_1<='Z')||LA31_1=='_'||(LA31_1>='a' && LA31_1<='w')||(LA31_1>='y' && LA31_1<='z')) ) {s = 23;}

                        else if ( ((LA31_1>='\u0000' && LA31_1<='/')||(LA31_1>=':' && LA31_1<='@')||(LA31_1>='[' && LA31_1<='^')||LA31_1=='`'||(LA31_1>='{' && LA31_1<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 166 : 
                        int LA31_103 = input.LA(1);

                        s = -1;
                        if ( (LA31_103=='\"') ) {s = 100;}

                        else if ( (LA31_103=='\r') ) {s = 101;}

                        else if ( (LA31_103=='\n') ) {s = 102;}

                        else if ( ((LA31_103>='\u0000' && LA31_103<='\t')||(LA31_103>='\u000B' && LA31_103<='\f')||(LA31_103>='\u000E' && LA31_103<='!')||(LA31_103>='#' && LA31_103<='\uFFFF')) ) {s = 103;}

                        if ( s>=0 ) return s;
                        break;
                    case 167 : 
                        int LA31_124 = input.LA(1);

                        s = -1;
                        if ( ((LA31_124>='\u0000' && LA31_124<='\t')||(LA31_124>='\u000B' && LA31_124<='\f')||(LA31_124>='\u000E' && LA31_124<='\uFFFF')) ) {s = 141;}

                        else if ( (LA31_124=='\r') ) {s = 142;}

                        else if ( (LA31_124=='\n') ) {s = 143;}

                        if ( s>=0 ) return s;
                        break;
                    case 168 : 
                        int LA31_2 = input.LA(1);

                        s = -1;
                        if ( (LA31_2=='a') ) {s = 26;}

                        else if ( ((LA31_2>='0' && LA31_2<='9')||(LA31_2>='A' && LA31_2<='Z')||LA31_2=='_'||(LA31_2>='b' && LA31_2<='z')) ) {s = 23;}

                        else if ( ((LA31_2>='\u0000' && LA31_2<='/')||(LA31_2>=':' && LA31_2<='@')||(LA31_2>='[' && LA31_2<='^')||LA31_2=='`'||(LA31_2>='{' && LA31_2<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 169 : 
                        int LA31_75 = input.LA(1);

                        s = -1;
                        if ( ((LA31_75>='\u0000' && LA31_75<='\uFFFF')) ) {s = 73;}

                        else s = 25;

                        if ( s>=0 ) return s;
                        break;
                    case 170 : 
                        int LA31_167 = input.LA(1);

                        s = -1;
                        if ( (LA31_167==':') ) {s = 168;}

                        else if ( (LA31_167=='\t'||LA31_167==' ') ) {s = 167;}

                        else if ( ((LA31_167>='\u0000' && LA31_167<='\b')||(LA31_167>='\n' && LA31_167<='\u001F')||(LA31_167>='!' && LA31_167<='9')||(LA31_167>=';' && LA31_167<='\uFFFF')) ) {s = 25;}

                        if ( s>=0 ) return s;
                        break;
                    case 171 : 
                        int LA31_42 = input.LA(1);

                        s = -1;
                        if ( (LA31_42=='\r') ) {s = 74;}

                        else if ( (LA31_42=='\n') ) {s = 75;}

                        else if ( ((LA31_42>='\u0000' && LA31_42<='\t')||(LA31_42>='\u000B' && LA31_42<='\f')||(LA31_42>='\u000E' && LA31_42<='\uFFFF')) ) {s = 76;}

                        if ( s>=0 ) return s;
                        break;
                    case 172 : 
                        int LA31_44 = input.LA(1);

                        s = -1;
                        if ( ((LA31_44>='\u0000' && LA31_44<='\uFFFF')) ) {s = 73;}

                        else s = 25;

                        if ( s>=0 ) return s;
                        break;
                    case 173 : 
                        int LA31_3 = input.LA(1);

                        s = -1;
                        if ( (LA31_3=='e') ) {s = 27;}

                        else if ( ((LA31_3>='0' && LA31_3<='9')||(LA31_3>='A' && LA31_3<='Z')||LA31_3=='_'||(LA31_3>='a' && LA31_3<='d')||(LA31_3>='f' && LA31_3<='z')) ) {s = 23;}

                        else if ( ((LA31_3>='\u0000' && LA31_3<='/')||(LA31_3>=':' && LA31_3<='@')||(LA31_3>='[' && LA31_3<='^')||LA31_3=='`'||(LA31_3>='{' && LA31_3<='\uFFFF')) ) {s = 25;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 31, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}