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
            // InternalFeature.g:1201:18: ( ( '\\r' )? ( '\\n' )? )
            // InternalFeature.g:1201:20: ( '\\r' )? ( '\\n' )?
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

            // InternalFeature.g:1201:26: ( '\\n' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='\n') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // InternalFeature.g:1201:26: '\\n'
                    {
                    match('\n'); 

                    }
                    break;

            }


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
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='\t'||LA4_0==' ') ) {
                    alt4=1;
                }


                switch (alt4) {
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
            	    break loop4;
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
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='N') ) {
                alt5=1;
            }
            else if ( (LA5_0=='F') ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
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
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='\u0000' && LA6_0<='\t')||(LA6_0>='\u000B' && LA6_0<='\f')||(LA6_0>='\u000E' && LA6_0<='\uFFFF')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalFeature.g:1205:47: RULE_NNL
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
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>='\u0000' && LA7_0<='\t')||(LA7_0>='\u000B' && LA7_0<='\f')||(LA7_0>='\u000E' && LA7_0<='\uFFFF')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalFeature.g:1207:34: RULE_NNL
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
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='\u0000' && LA8_0<='\t')||(LA8_0>='\u000B' && LA8_0<='\f')||(LA8_0>='\u000E' && LA8_0<='\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalFeature.g:1209:20: RULE_NNL
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
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>='\u0000' && LA9_0<='\t')||(LA9_0>='\u000B' && LA9_0<='\f')||(LA9_0>='\u000E' && LA9_0<='\uFFFF')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalFeature.g:1211:31: RULE_NNL
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
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='\u0000' && LA10_0<='\t')||(LA10_0>='\u000B' && LA10_0<='\f')||(LA10_0>='\u000E' && LA10_0<='\uFFFF')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalFeature.g:1213:49: RULE_NNL
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
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>='\u0000' && LA11_0<='\t')||(LA11_0>='\u000B' && LA11_0<='\f')||(LA11_0>='\u000E' && LA11_0<='\uFFFF')) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalFeature.g:1215:79: RULE_NNL
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
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>='\u0000' && LA12_0<='\t')||(LA12_0>='\u000B' && LA12_0<='\f')||(LA12_0>='\u000E' && LA12_0<='\uFFFF')) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalFeature.g:1217:53: RULE_NNL
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
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>='\u0000' && LA13_0<='\t')||(LA13_0>='\u000B' && LA13_0<='\f')||(LA13_0>='\u000E' && LA13_0<='\uFFFF')) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // InternalFeature.g:1219:26: RULE_NNL
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
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( ((LA14_0>='\u0000' && LA14_0<='\t')||(LA14_0>='\u000B' && LA14_0<='\f')||(LA14_0>='\u000E' && LA14_0<='\uFFFF')) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalFeature.g:1221:26: RULE_NNL
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
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>='\u0000' && LA15_0<='\t')||(LA15_0>='\u000B' && LA15_0<='\f')||(LA15_0>='\u000E' && LA15_0<='\uFFFF')) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalFeature.g:1223:28: RULE_NNL
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
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>='\u0000' && LA16_0<='\t')||(LA16_0>='\u000B' && LA16_0<='\f')||(LA16_0>='\u000E' && LA16_0<='\uFFFF')) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalFeature.g:1225:24: RULE_NNL
            	    {
            	    mRULE_NNL(); 

            	    }
            	    break;

            	default :
            	    break loop16;
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
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0>='\u0000' && LA17_0<='\t')||(LA17_0>='\u000B' && LA17_0<='\f')||(LA17_0>='\u000E' && LA17_0<='{')||(LA17_0>='}' && LA17_0<='\uFFFF')) ) {
                    alt17=1;
                }


                switch (alt17) {
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
            	    if ( cnt17 >= 1 ) break loop17;
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
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
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0=='\"') ) {
                alt20=1;
            }
            else if ( (LA20_0=='\'') ) {
                alt20=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }
            switch (alt20) {
                case 1 :
                    // InternalFeature.g:1231:14: '\"\"\"' ( options {greedy=false; } : . )* '\"\"\"'
                    {
                    match("\"\"\""); 

                    // InternalFeature.g:1231:20: ( options {greedy=false; } : . )*
                    loop18:
                    do {
                        int alt18=2;
                        int LA18_0 = input.LA(1);

                        if ( (LA18_0=='\"') ) {
                            int LA18_1 = input.LA(2);

                            if ( (LA18_1=='\"') ) {
                                int LA18_3 = input.LA(3);

                                if ( (LA18_3=='\"') ) {
                                    alt18=2;
                                }
                                else if ( ((LA18_3>='\u0000' && LA18_3<='!')||(LA18_3>='#' && LA18_3<='\uFFFF')) ) {
                                    alt18=1;
                                }


                            }
                            else if ( ((LA18_1>='\u0000' && LA18_1<='!')||(LA18_1>='#' && LA18_1<='\uFFFF')) ) {
                                alt18=1;
                            }


                        }
                        else if ( ((LA18_0>='\u0000' && LA18_0<='!')||(LA18_0>='#' && LA18_0<='\uFFFF')) ) {
                            alt18=1;
                        }


                        switch (alt18) {
                    	case 1 :
                    	    // InternalFeature.g:1231:48: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop18;
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
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0=='\'') ) {
                            int LA19_1 = input.LA(2);

                            if ( (LA19_1=='\'') ) {
                                int LA19_3 = input.LA(3);

                                if ( (LA19_3=='\'') ) {
                                    alt19=2;
                                }
                                else if ( ((LA19_3>='\u0000' && LA19_3<='&')||(LA19_3>='(' && LA19_3<='\uFFFF')) ) {
                                    alt19=1;
                                }


                            }
                            else if ( ((LA19_1>='\u0000' && LA19_1<='&')||(LA19_1>='(' && LA19_1<='\uFFFF')) ) {
                                alt19=1;
                            }


                        }
                        else if ( ((LA19_0>='\u0000' && LA19_0<='&')||(LA19_0>='(' && LA19_0<='\uFFFF')) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // InternalFeature.g:1231:95: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop19;
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
            int cnt21=0;
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0>='\u0000' && LA21_0<='\t')||(LA21_0>='\u000B' && LA21_0<='\f')||(LA21_0>='\u000E' && LA21_0<='\uFFFF')) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalFeature.g:1233:16: RULE_NNL
            	    {
            	    mRULE_NNL(); 

            	    }
            	    break;

            	default :
            	    if ( cnt21 >= 1 ) break loop21;
                        EarlyExitException eee =
                            new EarlyExitException(21, input);
                        throw eee;
                }
                cnt21++;
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
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( ((LA22_0>='\u0000' && LA22_0<='\t')||(LA22_0>='\u000B' && LA22_0<='\f')||(LA22_0>='\u000E' && LA22_0<='\uFFFF')) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // InternalFeature.g:1235:23: RULE_NNL
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
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( ((LA23_0>='\u0000' && LA23_0<='\t')||(LA23_0>='\u000B' && LA23_0<='\f')||(LA23_0>='\u000E' && LA23_0<='\uFFFF')) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // InternalFeature.g:1237:45: RULE_NNL
            	    {
            	    mRULE_NNL(); 

            	    }
            	    break;

            	default :
            	    break loop23;
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
            int cnt24=0;
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( ((LA24_0>='\t' && LA24_0<='\n')||LA24_0=='\r'||LA24_0==' ') ) {
                    alt24=1;
                }


                switch (alt24) {
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
            	    if ( cnt24 >= 1 ) break loop24;
                        EarlyExitException eee =
                            new EarlyExitException(24, input);
                        throw eee;
                }
                cnt24++;
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
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0=='^') ) {
                alt25=1;
            }
            switch (alt25) {
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
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0>='0' && LA26_0<='9')||(LA26_0>='A' && LA26_0<='Z')||LA26_0=='_'||(LA26_0>='a' && LA26_0<='z')) ) {
                    alt26=1;
                }


                switch (alt26) {
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
            	    break loop26;
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
            int cnt27=0;
            loop27:
            do {
                int alt27=2;
                int LA27_0 = input.LA(1);

                if ( ((LA27_0>='0' && LA27_0<='9')) ) {
                    alt27=1;
                }


                switch (alt27) {
            	case 1 :
            	    // InternalFeature.g:1243:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt27 >= 1 ) break loop27;
                        EarlyExitException eee =
                            new EarlyExitException(27, input);
                        throw eee;
                }
                cnt27++;
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
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0=='\"') ) {
                alt30=1;
            }
            else if ( (LA30_0=='\'') ) {
                alt30=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;
            }
            switch (alt30) {
                case 1 :
                    // InternalFeature.g:1245:16: '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // InternalFeature.g:1245:20: ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop28:
                    do {
                        int alt28=3;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0=='\\') ) {
                            alt28=1;
                        }
                        else if ( ((LA28_0>='\u0000' && LA28_0<='!')||(LA28_0>='#' && LA28_0<='[')||(LA28_0>=']' && LA28_0<='\uFFFF')) ) {
                            alt28=2;
                        }


                        switch (alt28) {
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
                    	    break loop28;
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
                    loop29:
                    do {
                        int alt29=3;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0=='\\') ) {
                            alt29=1;
                        }
                        else if ( ((LA29_0>='\u0000' && LA29_0<='&')||(LA29_0>='(' && LA29_0<='[')||(LA29_0>=']' && LA29_0<='\uFFFF')) ) {
                            alt29=2;
                        }


                        switch (alt29) {
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
                    	    break loop29;
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
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0=='*') ) {
                    int LA31_1 = input.LA(2);

                    if ( (LA31_1=='/') ) {
                        alt31=2;
                    }
                    else if ( ((LA31_1>='\u0000' && LA31_1<='.')||(LA31_1>='0' && LA31_1<='\uFFFF')) ) {
                        alt31=1;
                    }


                }
                else if ( ((LA31_0>='\u0000' && LA31_0<=')')||(LA31_0>='+' && LA31_0<='\uFFFF')) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // InternalFeature.g:1247:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop31;
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
        int alt32=24;
        alt32 = dfa32.predict(input);
        switch (alt32) {
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


    protected DFA32 dfa32 = new DFA32(this);
    static final String DFA32_eotS =
        "\1\uffff\12\30\1\44\2\30\1\61\1\66\1\30\1\uffff\3\30\1\uffff\2\30\1\uffff\13\30\1\uffff\1\44\1\uffff\12\30\2\uffff\2\66\2\uffff\1\30\1\uffff\22\30\2\uffff\4\30\1\uffff\11\30\1\164\1\165\16\30\1\uffff\5\30\2\164\3\uffff\1\165\1\uffff\1\165\2\30\1\u008b\1\u008f\2\30\1\uffff\10\30\2\u008b\2\uffff\2\u008f\2\uffff\1\u009a\2\177\7\30\1\uffff\1\u009a\1\uffff\1\u009a\2\30\1\u00ab\7\30\2\u00ab\2\uffff\4\30\1\u00bd\2\30\1\uffff\1\30\1\u00ab\1\30\1\u00c1\2\30\2\u00bd\2\uffff\1\30\1\uffff\1\u00cc\1\uffff\1\u00c1\1\uffff\1\u00c1\3\30\1\u00cf\2\u00cc\2\uffff\2\30\1\uffff\1\u00cf\1\uffff\1\u00cf\10\30\3\u00df\2\uffff";
    static final String DFA32_eofS =
        "\u00e0\uffff";
    static final String DFA32_minS =
        "\1\0\3\60\1\40\6\60\5\0\1\101\1\uffff\2\60\1\52\1\uffff\2\60\1\uffff\2\60\1\40\1\167\1\40\6\60\1\uffff\1\0\1\uffff\1\42\4\0\1\47\4\0\2\uffff\1\0\1\12\2\uffff\1\60\1\uffff\1\60\1\0\3\60\1\157\2\141\1\40\5\60\4\0\2\uffff\4\0\1\uffff\4\0\3\60\1\162\1\156\2\0\2\60\2\40\1\60\11\0\1\uffff\3\60\1\144\1\164\1\0\1\12\3\uffff\1\12\1\uffff\1\0\2\60\2\0\1\40\1\0\1\uffff\1\0\3\60\1\145\1\40\2\60\1\0\1\12\2\uffff\1\0\1\12\2\uffff\3\0\3\60\1\162\1\164\2\60\1\uffff\1\12\1\uffff\1\0\1\11\1\60\1\0\1\40\1\157\1\11\1\60\2\11\1\60\1\0\1\12\2\uffff\1\164\1\40\1\11\1\60\1\0\1\60\1\12\1\uffff\1\11\1\0\1\157\1\0\1\165\1\60\1\0\1\12\2\uffff\1\11\1\uffff\1\0\1\uffff\1\12\1\uffff\1\0\1\164\1\60\1\11\2\0\1\12\2\uffff\1\154\1\60\1\uffff\1\12\1\uffff\1\0\1\151\1\60\1\156\1\60\1\145\3\11\2\0\1\12\2\uffff";
    static final String DFA32_maxS =
        "\1\uffff\12\172\5\uffff\1\172\1\uffff\1\172\1\71\1\52\1\uffff\2\172\1\uffff\3\172\1\167\7\172\1\uffff\1\uffff\1\uffff\1\42\4\uffff\1\47\4\uffff\2\uffff\1\uffff\1\12\2\uffff\1\172\1\uffff\1\71\1\uffff\3\172\1\157\2\141\6\172\4\uffff\2\uffff\4\uffff\1\uffff\4\uffff\3\172\1\162\1\156\2\uffff\5\172\11\uffff\1\uffff\3\172\1\144\1\164\1\uffff\1\12\3\uffff\1\12\1\uffff\1\uffff\2\172\2\uffff\1\172\1\uffff\1\uffff\1\uffff\3\172\1\145\1\40\2\172\1\uffff\1\12\2\uffff\1\uffff\1\12\2\uffff\3\uffff\3\172\1\162\1\164\2\172\1\uffff\1\12\1\uffff\1\uffff\2\172\1\uffff\1\40\1\157\2\172\1\72\1\40\1\172\1\uffff\1\12\2\uffff\1\164\1\40\1\117\1\172\1\uffff\1\172\1\12\1\uffff\1\40\1\uffff\1\157\1\uffff\1\165\1\172\1\uffff\1\12\2\uffff\1\172\1\uffff\1\uffff\1\uffff\1\12\1\uffff\1\uffff\1\164\1\172\1\72\2\uffff\1\12\2\uffff\1\154\1\172\1\uffff\1\12\1\uffff\1\uffff\1\151\1\172\1\156\1\172\1\145\1\172\2\72\2\uffff\1\12\2\uffff";
    static final String DFA32_acceptS =
        "\21\uffff\1\23\3\uffff\1\22\2\uffff\1\22\13\uffff\1\15\1\uffff\1\16\12\uffff\1\30\1\20\2\uffff\2\21\1\uffff\1\23\22\uffff\1\26\1\22\4\uffff\1\22\31\uffff\1\27\7\uffff\2\4\1\14\1\uffff\1\14\7\uffff\1\17\12\uffff\2\11\2\uffff\2\12\12\uffff\1\13\1\uffff\1\13\15\uffff\2\2\7\uffff\1\1\10\uffff\2\6\1\uffff\1\1\1\uffff\1\5\1\uffff\1\5\7\uffff\2\3\2\uffff\1\10\1\uffff\1\10\14\uffff\2\7";
    static final String DFA32_specialS =
        "\1\57\12\uffff\1\27\1\34\1\64\1\22\1\66\25\uffff\1\60\2\uffff\1\11\1\7\1\17\1\71\1\uffff\1\16\1\31\1\42\1\4\2\uffff\1\32\6\uffff\1\24\14\uffff\1\52\1\6\1\15\1\67\2\uffff\1\70\1\30\1\40\1\3\1\uffff\1\26\1\51\1\65\1\23\5\uffff\1\75\1\41\5\uffff\1\76\1\37\1\53\1\10\1\47\1\63\1\2\1\21\1\74\6\uffff\1\35\6\uffff\1\33\2\uffff\1\61\1\0\1\uffff\1\54\1\uffff\1\72\7\uffff\1\73\3\uffff\1\1\3\uffff\1\20\1\44\1\62\12\uffff\1\12\2\uffff\1\45\7\uffff\1\100\7\uffff\1\46\4\uffff\1\43\1\uffff\1\13\2\uffff\1\77\5\uffff\1\55\3\uffff\1\56\3\uffff\1\36\1\14\10\uffff\1\50\10\uffff\1\5\1\25\3\uffff}>";
    static final String[] DFA32_transitionS = {
            "\11\25\2\21\2\25\1\21\22\25\1\21\1\25\1\14\1\17\3\25\1\15\7\25\1\24\12\23\6\25\1\16\1\5\1\7\2\22\1\1\1\3\1\12\1\22\1\4\4\22\1\2\4\22\1\6\1\11\2\22\1\10\3\22\3\25\1\20\1\22\1\25\32\22\1\25\1\13\uff83\25",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\27\27\1\26\2\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\1\31\31\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\4\27\1\32\25\27",
            "\1\34\17\uffff\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\15\27\1\33\14\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\15\27\1\36\4\27\1\35\7\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\2\27\1\37\27\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\1\40\31\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\7\27\1\41\22\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\7\27\1\42\22\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\10\27\1\43\21\27",
            "\11\46\1\45\1\uffff\2\46\1\uffff\22\46\1\45\133\46\1\uffff\uff83\46",
            "\12\53\1\52\2\53\1\51\24\53\1\47\71\53\1\50\uffa3\53",
            "\12\60\1\57\2\60\1\56\31\60\1\54\64\60\1\55\uffa3\60",
            "\12\62\1\uffff\2\62\1\uffff\ufff2\62",
            "\12\63\1\65\2\63\1\64\ufff2\63",
            "\32\67\4\uffff\1\67\1\uffff\32\67",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\71",
            "\1\72",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\1\73\31\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\21\27\1\74\10\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\1\75\31\27",
            "\1\76\17\uffff\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\77",
            "\1\100\17\uffff\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\3\27\1\101\26\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\4\27\1\102\25\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\2\27\1\103\27\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\4\27\1\104\25\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\4\27\1\105\25\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\25\27\1\106\4\27",
            "",
            "\11\46\1\45\1\uffff\2\46\1\uffff\22\46\1\45\133\46\1\uffff\uff83\46",
            "",
            "\1\107",
            "\12\112\1\111\2\112\1\110\ufff2\112",
            "\12\113\1\52\ufff5\113",
            "\0\113",
            "\12\53\1\52\2\53\1\51\24\53\1\114\71\53\1\50\uffa3\53",
            "\1\115",
            "\12\120\1\117\2\120\1\116\ufff2\120",
            "\12\113\1\57\ufff5\113",
            "\0\113",
            "\12\60\1\57\2\60\1\56\31\60\1\121\64\60\1\55\uffa3\60",
            "",
            "",
            "\12\63\1\65\2\63\1\64\ufff2\63",
            "\1\65",
            "",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "\12\71",
            "\12\125\1\124\2\125\1\123\34\125\1\122\uffd5\125",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\14\27\1\126\15\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\21\27\1\127\10\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\23\27\1\130\6\27",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\134\17\uffff\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\15\27\1\135\14\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\12\27\1\136\17\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\15\27\1\137\14\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\15\27\1\140\14\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\4\27\1\141\25\27",
            "\12\145\1\144\2\145\1\143\24\145\1\142\uffdd\145",
            "\12\113\1\52\ufff5\113",
            "\0\113",
            "\12\53\1\52\2\53\1\51\24\53\1\114\71\53\1\50\uffa3\53",
            "",
            "",
            "\12\151\1\147\2\151\1\146\31\151\1\150\uffd8\151",
            "\12\113\1\57\ufff5\113",
            "\0\113",
            "\12\60\1\57\2\60\1\56\31\60\1\121\64\60\1\55\uffa3\60",
            "",
            "\12\125\1\124\2\125\1\123\34\125\1\122\4\125\1\152\uffd0\125",
            "\12\153\1\124\ufff5\153",
            "\0\153",
            "\12\125\1\124\2\125\1\123\34\125\1\122\uffd5\125",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\17\27\1\154\12\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\1\155\31\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\24\27\1\156\5\27",
            "\1\157",
            "\1\160",
            "\12\161\1\163\2\161\1\162\ufff2\161",
            "\12\170\1\167\2\170\1\166\ufff2\170",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\1\171\31\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\6\27\1\172\23\27",
            "\1\173\17\uffff\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\174\17\uffff\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\15\27\1\175\14\27",
            "\12\145\1\144\2\145\1\143\24\145\1\176\uffdd\145",
            "\12\177\1\144\ufff5\177",
            "\0\177",
            "\12\145\1\144\2\145\1\143\24\145\1\142\uffdd\145",
            "\12\177\1\147\ufff5\177",
            "\0\177",
            "\12\151\1\147\2\151\1\146\31\151\1\u0080\uffd8\151",
            "\12\151\1\147\2\151\1\146\31\151\1\150\uffd8\151",
            "\12\125\1\124\2\125\1\123\34\125\1\122\uffd5\125",
            "",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\13\27\1\u0081\16\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\23\27\1\u0082\6\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\21\27\1\u0083\10\27",
            "\1\u0084",
            "\1\u0085",
            "\12\161\1\163\2\161\1\162\ufff2\161",
            "\1\163",
            "",
            "",
            "",
            "\1\167",
            "",
            "\12\170\1\167\2\170\1\166\ufff2\170",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\21\27\1\u0086\10\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\21\27\1\u0087\10\27",
            "\12\u0088\1\u008a\2\u0088\1\u0089\ufff2\u0088",
            "\12\u008c\1\u008e\2\u008c\1\u008d\ufff2\u008c",
            "\1\u0090\17\uffff\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\145\1\144\2\145\1\143\24\145\1\u0091\uffdd\145",
            "",
            "\12\151\1\147\2\151\1\146\31\151\1\u0092\uffd8\151",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\4\27\1\u0093\25\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\10\27\1\u0094\21\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\4\27\1\u0095\25\27",
            "\1\u0096",
            "\1\u0097",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\10\27\1\u0098\21\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\16\27\1\u0099\13\27",
            "\12\u0088\1\u008a\2\u0088\1\u0089\ufff2\u0088",
            "\1\u008a",
            "",
            "",
            "\12\u008c\1\u008e\2\u008c\1\u008d\ufff2\u008c",
            "\1\u008e",
            "",
            "",
            "\12\u009d\1\u009c\2\u009d\1\u009b\ufff2\u009d",
            "\12\145\1\144\2\145\1\143\24\145\1\u0091\uffdd\145",
            "\12\151\1\147\2\151\1\146\31\151\1\u0092\uffd8\151",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\22\27\1\u009e\7\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\25\27\1\u009f\4\27",
            "\12\27\1\u00a0\6\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\u00a1",
            "\1\u00a2",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\16\27\1\u00a3\13\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\24\27\1\u00a4\5\27",
            "",
            "\1\u009c",
            "",
            "\12\u009d\1\u009c\2\u009d\1\u009b\ufff2\u009d",
            "\1\u00a5\26\uffff\1\u00a5\17\uffff\12\27\1\u00a6\6\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\4\27\1\u00a7\25\27",
            "\12\u00a8\1\u00aa\2\u00a8\1\u00a9\ufff2\u00a8",
            "\1\u00ac",
            "\1\u00ad",
            "\1\u00ae\26\uffff\1\u00ae\17\uffff\12\27\1\u00b0\6\uffff\16\27\1\u00af\13\27\4\uffff\1\27\1\uffff\32\27",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\15\27\1\u00b1\14\27",
            "\1\u00a5\26\uffff\1\u00a5\31\uffff\1\u00a6",
            "\1\u00b4\1\u00b3\2\uffff\1\u00b2\22\uffff\1\u00b4",
            "\12\27\1\u00b5\6\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\12\u00a8\1\u00aa\2\u00a8\1\u00a9\ufff2\u00a8",
            "\1\u00aa",
            "",
            "",
            "\1\u00b6",
            "\1\u00b7",
            "\1\u00ae\26\uffff\1\u00ae\31\uffff\1\u00b0\24\uffff\1\u00b8",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\24\27\1\u00b9\5\27",
            "\12\u00ba\1\u00bc\2\u00ba\1\u00bb\ufff2\u00ba",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\3\27\1\u00be\26\27",
            "\1\u00b3",
            "",
            "\1\u00b4\1\u00b3\2\uffff\1\u00b2\22\uffff\1\u00b4",
            "\12\u00a8\1\u00aa\2\u00a8\1\u00a9\ufff2\u00a8",
            "\1\u00c0",
            "\12\u00c4\1\u00c3\2\u00c4\1\u00c2\ufff2\u00c4",
            "\1\u00c5",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\23\27\1\u00c6\6\27",
            "\12\u00ba\1\u00bc\2\u00ba\1\u00bb\ufff2\u00ba",
            "\1\u00bc",
            "",
            "",
            "\1\u00c7\26\uffff\1\u00c7\17\uffff\12\27\1\u00c8\6\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "",
            "\12\u00c9\1\u00cb\2\u00c9\1\u00ca\ufff2\u00c9",
            "",
            "\1\u00c3",
            "",
            "\12\u00c4\1\u00c3\2\u00c4\1\u00c2\ufff2\u00c4",
            "\1\u00cd",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\13\27\1\u00ce\16\27",
            "\1\u00c7\26\uffff\1\u00c7\31\uffff\1\u00c8",
            "\12\u00d2\1\u00d1\2\u00d2\1\u00d0\ufff2\u00d2",
            "\12\u00c9\1\u00cb\2\u00c9\1\u00ca\ufff2\u00c9",
            "\1\u00cb",
            "",
            "",
            "\1\u00d3",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\10\27\1\u00d4\21\27",
            "",
            "\1\u00d1",
            "",
            "\12\u00d2\1\u00d1\2\u00d2\1\u00d0\ufff2\u00d2",
            "\1\u00d5",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\15\27\1\u00d6\14\27",
            "\1\u00d7",
            "\12\27\7\uffff\32\27\4\uffff\1\27\1\uffff\4\27\1\u00d8\25\27",
            "\1\u00d9",
            "\1\u00da\26\uffff\1\u00da\17\uffff\12\27\1\u00db\6\uffff\32\27\4\uffff\1\27\1\uffff\32\27",
            "\1\u00da\26\uffff\1\u00da\31\uffff\1\u00db",
            "\1\u00da\26\uffff\1\u00da\31\uffff\1\u00db",
            "\12\u00dc\1\u00de\2\u00dc\1\u00dd\ufff2\u00dc",
            "\12\u00dc\1\u00de\2\u00dc\1\u00dd\ufff2\u00dc",
            "\1\u00de",
            "",
            ""
    };

    static final short[] DFA32_eot = DFA.unpackEncodedString(DFA32_eotS);
    static final short[] DFA32_eof = DFA.unpackEncodedString(DFA32_eofS);
    static final char[] DFA32_min = DFA.unpackEncodedStringToUnsignedChars(DFA32_minS);
    static final char[] DFA32_max = DFA.unpackEncodedStringToUnsignedChars(DFA32_maxS);
    static final short[] DFA32_accept = DFA.unpackEncodedString(DFA32_acceptS);
    static final short[] DFA32_special = DFA.unpackEncodedString(DFA32_specialS);
    static final short[][] DFA32_transition;

    static {
        int numStates = DFA32_transitionS.length;
        DFA32_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA32_transition[i] = DFA.unpackEncodedString(DFA32_transitionS[i]);
        }
    }

    class DFA32 extends DFA {

        public DFA32(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 32;
            this.eot = DFA32_eot;
            this.eof = DFA32_eof;
            this.min = DFA32_min;
            this.max = DFA32_max;
            this.accept = DFA32_accept;
            this.special = DFA32_special;
            this.transition = DFA32_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( RULE_EXAMPLE_HEADING | RULE_FEATURE_TEXT | RULE_IN_ORDER_TO | RULE_AS_A | RULE_I_WANT_TO | RULE_SCENARIO_TEXT | RULE_SCENARIO_OUTLINE_TEXT | RULE_BACKGROUND_TEXT | RULE_WHEN_TEXT | RULE_THEN_TEXT | RULE_GIVEN_TEXT | RULE_AND_TEXT | RULE_EXAMPLE_ROW_END | RULE_EXAMPLE_CELL | RULE_CODE | RULE_TAG | RULE_SL_COMMENT | RULE_TEXT | RULE_WS | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA32_124 = input.LA(1);

                        s = -1;
                        if ( ((LA32_124>='\u0000' && LA32_124<='\t')||(LA32_124>='\u000B' && LA32_124<='\f')||(LA32_124>='\u000E' && LA32_124<='\uFFFF')) ) {s = 140;}

                        else if ( (LA32_124=='\r') ) {s = 141;}

                        else if ( (LA32_124=='\n') ) {s = 142;}

                        else s = 143;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA32_140 = input.LA(1);

                        s = -1;
                        if ( (LA32_140=='\r') ) {s = 141;}

                        else if ( (LA32_140=='\n') ) {s = 142;}

                        else if ( ((LA32_140>='\u0000' && LA32_140<='\t')||(LA32_140>='\u000B' && LA32_140<='\f')||(LA32_140>='\u000E' && LA32_140<='\uFFFF')) ) {s = 140;}

                        else s = 143;

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA32_104 = input.LA(1);

                        s = -1;
                        if ( (LA32_104=='\'') ) {s = 128;}

                        else if ( (LA32_104=='\r') ) {s = 102;}

                        else if ( (LA32_104=='\n') ) {s = 103;}

                        else if ( ((LA32_104>='\u0000' && LA32_104<='\t')||(LA32_104>='\u000B' && LA32_104<='\f')||(LA32_104>='\u000E' && LA32_104<='&')||(LA32_104>='(' && LA32_104<='\uFFFF')) ) {s = 105;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA32_80 = input.LA(1);

                        s = -1;
                        if ( (LA32_80=='\r') ) {s = 46;}

                        else if ( (LA32_80=='\n') ) {s = 47;}

                        else if ( (LA32_80=='\'') ) {s = 81;}

                        else if ( (LA32_80=='\\') ) {s = 45;}

                        else if ( ((LA32_80>='\u0000' && LA32_80<='\t')||(LA32_80>='\u000B' && LA32_80<='\f')||(LA32_80>='\u000E' && LA32_80<='&')||(LA32_80>='(' && LA32_80<='[')||(LA32_80>=']' && LA32_80<='\uFFFF')) ) {s = 48;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA32_48 = input.LA(1);

                        s = -1;
                        if ( (LA32_48=='\r') ) {s = 46;}

                        else if ( (LA32_48=='\n') ) {s = 47;}

                        else if ( (LA32_48=='\'') ) {s = 81;}

                        else if ( (LA32_48=='\\') ) {s = 45;}

                        else if ( ((LA32_48>='\u0000' && LA32_48<='\t')||(LA32_48>='\u000B' && LA32_48<='\f')||(LA32_48>='\u000E' && LA32_48<='&')||(LA32_48>='(' && LA32_48<='[')||(LA32_48>=']' && LA32_48<='\uFFFF')) ) {s = 48;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA32_219 = input.LA(1);

                        s = -1;
                        if ( ((LA32_219>='\u0000' && LA32_219<='\t')||(LA32_219>='\u000B' && LA32_219<='\f')||(LA32_219>='\u000E' && LA32_219<='\uFFFF')) ) {s = 220;}

                        else if ( (LA32_219=='\r') ) {s = 221;}

                        else if ( (LA32_219=='\n') ) {s = 222;}

                        else s = 223;

                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA32_72 = input.LA(1);

                        s = -1;
                        if ( ((LA32_72>='\u0000' && LA32_72<='\t')||(LA32_72>='\u000B' && LA32_72<='\uFFFF')) ) {s = 75;}

                        else if ( (LA32_72=='\n') ) {s = 42;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 7 : 
                        int LA32_41 = input.LA(1);

                        s = -1;
                        if ( ((LA32_41>='\u0000' && LA32_41<='\t')||(LA32_41>='\u000B' && LA32_41<='\uFFFF')) ) {s = 75;}

                        else if ( (LA32_41=='\n') ) {s = 42;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 8 : 
                        int LA32_101 = input.LA(1);

                        s = -1;
                        if ( (LA32_101=='\"') ) {s = 98;}

                        else if ( (LA32_101=='\r') ) {s = 99;}

                        else if ( (LA32_101=='\n') ) {s = 100;}

                        else if ( ((LA32_101>='\u0000' && LA32_101<='\t')||(LA32_101>='\u000B' && LA32_101<='\f')||(LA32_101>='\u000E' && LA32_101<='!')||(LA32_101>='#' && LA32_101<='\uFFFF')) ) {s = 101;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 9 : 
                        int LA32_40 = input.LA(1);

                        s = -1;
                        if ( (LA32_40=='\r') ) {s = 72;}

                        else if ( (LA32_40=='\n') ) {s = 73;}

                        else if ( ((LA32_40>='\u0000' && LA32_40<='\t')||(LA32_40>='\u000B' && LA32_40<='\f')||(LA32_40>='\u000E' && LA32_40<='\uFFFF')) ) {s = 74;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 10 : 
                        int LA32_157 = input.LA(1);

                        s = -1;
                        if ( (LA32_157=='\r') ) {s = 155;}

                        else if ( (LA32_157=='\n') ) {s = 156;}

                        else if ( ((LA32_157>='\u0000' && LA32_157<='\t')||(LA32_157>='\u000B' && LA32_157<='\f')||(LA32_157>='\u000E' && LA32_157<='\uFFFF')) ) {s = 157;}

                        else s = 154;

                        if ( s>=0 ) return s;
                        break;
                    case 11 : 
                        int LA32_183 = input.LA(1);

                        s = -1;
                        if ( (LA32_183=='\r') ) {s = 194;}

                        else if ( (LA32_183=='\n') ) {s = 195;}

                        else if ( ((LA32_183>='\u0000' && LA32_183<='\t')||(LA32_183>='\u000B' && LA32_183<='\f')||(LA32_183>='\u000E' && LA32_183<='\uFFFF')) ) {s = 196;}

                        else s = 193;

                        if ( s>=0 ) return s;
                        break;
                    case 12 : 
                        int LA32_201 = input.LA(1);

                        s = -1;
                        if ( (LA32_201=='\r') ) {s = 202;}

                        else if ( (LA32_201=='\n') ) {s = 203;}

                        else if ( ((LA32_201>='\u0000' && LA32_201<='\t')||(LA32_201>='\u000B' && LA32_201<='\f')||(LA32_201>='\u000E' && LA32_201<='\uFFFF')) ) {s = 201;}

                        else s = 204;

                        if ( s>=0 ) return s;
                        break;
                    case 13 : 
                        int LA32_73 = input.LA(1);

                        s = -1;
                        if ( ((LA32_73>='\u0000' && LA32_73<='\uFFFF')) ) {s = 75;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 14 : 
                        int LA32_45 = input.LA(1);

                        s = -1;
                        if ( (LA32_45=='\r') ) {s = 78;}

                        else if ( (LA32_45=='\n') ) {s = 79;}

                        else if ( ((LA32_45>='\u0000' && LA32_45<='\t')||(LA32_45>='\u000B' && LA32_45<='\f')||(LA32_45>='\u000E' && LA32_45<='\uFFFF')) ) {s = 80;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 15 : 
                        int LA32_42 = input.LA(1);

                        s = -1;
                        if ( ((LA32_42>='\u0000' && LA32_42<='\uFFFF')) ) {s = 75;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 16 : 
                        int LA32_144 = input.LA(1);

                        s = -1;
                        if ( (LA32_144=='\r') ) {s = 155;}

                        else if ( (LA32_144=='\n') ) {s = 156;}

                        else if ( ((LA32_144>='\u0000' && LA32_144<='\t')||(LA32_144>='\u000B' && LA32_144<='\f')||(LA32_144>='\u000E' && LA32_144<='\uFFFF')) ) {s = 157;}

                        else s = 154;

                        if ( s>=0 ) return s;
                        break;
                    case 17 : 
                        int LA32_105 = input.LA(1);

                        s = -1;
                        if ( (LA32_105=='\'') ) {s = 104;}

                        else if ( (LA32_105=='\r') ) {s = 102;}

                        else if ( (LA32_105=='\n') ) {s = 103;}

                        else if ( ((LA32_105>='\u0000' && LA32_105<='\t')||(LA32_105>='\u000B' && LA32_105<='\f')||(LA32_105>='\u000E' && LA32_105<='&')||(LA32_105>='(' && LA32_105<='\uFFFF')) ) {s = 105;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 18 : 
                        int LA32_14 = input.LA(1);

                        s = -1;
                        if ( ((LA32_14>='\u0000' && LA32_14<='\t')||(LA32_14>='\u000B' && LA32_14<='\f')||(LA32_14>='\u000E' && LA32_14<='\uFFFF')) ) {s = 50;}

                        else s = 49;

                        if ( s>=0 ) return s;
                        break;
                    case 19 : 
                        int LA32_85 = input.LA(1);

                        s = -1;
                        if ( (LA32_85=='*') ) {s = 82;}

                        else if ( (LA32_85=='\r') ) {s = 83;}

                        else if ( (LA32_85=='\n') ) {s = 84;}

                        else if ( ((LA32_85>='\u0000' && LA32_85<='\t')||(LA32_85>='\u000B' && LA32_85<='\f')||(LA32_85>='\u000E' && LA32_85<=')')||(LA32_85>='+' && LA32_85<='\uFFFF')) ) {s = 85;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 20 : 
                        int LA32_58 = input.LA(1);

                        s = -1;
                        if ( (LA32_58=='*') ) {s = 82;}

                        else if ( (LA32_58=='\r') ) {s = 83;}

                        else if ( (LA32_58=='\n') ) {s = 84;}

                        else if ( ((LA32_58>='\u0000' && LA32_58<='\t')||(LA32_58>='\u000B' && LA32_58<='\f')||(LA32_58>='\u000E' && LA32_58<=')')||(LA32_58>='+' && LA32_58<='\uFFFF')) ) {s = 85;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 21 : 
                        int LA32_220 = input.LA(1);

                        s = -1;
                        if ( (LA32_220=='\r') ) {s = 221;}

                        else if ( (LA32_220=='\n') ) {s = 222;}

                        else if ( ((LA32_220>='\u0000' && LA32_220<='\t')||(LA32_220>='\u000B' && LA32_220<='\f')||(LA32_220>='\u000E' && LA32_220<='\uFFFF')) ) {s = 220;}

                        else s = 223;

                        if ( s>=0 ) return s;
                        break;
                    case 22 : 
                        int LA32_82 = input.LA(1);

                        s = -1;
                        if ( (LA32_82=='/') ) {s = 106;}

                        else if ( (LA32_82=='*') ) {s = 82;}

                        else if ( (LA32_82=='\r') ) {s = 83;}

                        else if ( (LA32_82=='\n') ) {s = 84;}

                        else if ( ((LA32_82>='\u0000' && LA32_82<='\t')||(LA32_82>='\u000B' && LA32_82<='\f')||(LA32_82>='\u000E' && LA32_82<=')')||(LA32_82>='+' && LA32_82<='.')||(LA32_82>='0' && LA32_82<='\uFFFF')) ) {s = 85;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 23 : 
                        int LA32_11 = input.LA(1);

                        s = -1;
                        if ( (LA32_11=='\t'||LA32_11==' ') ) {s = 37;}

                        else if ( ((LA32_11>='\u0000' && LA32_11<='\b')||(LA32_11>='\u000B' && LA32_11<='\f')||(LA32_11>='\u000E' && LA32_11<='\u001F')||(LA32_11>='!' && LA32_11<='{')||(LA32_11>='}' && LA32_11<='\uFFFF')) ) {s = 38;}

                        else s = 36;

                        if ( s>=0 ) return s;
                        break;
                    case 24 : 
                        int LA32_78 = input.LA(1);

                        s = -1;
                        if ( ((LA32_78>='\u0000' && LA32_78<='\t')||(LA32_78>='\u000B' && LA32_78<='\uFFFF')) ) {s = 75;}

                        else if ( (LA32_78=='\n') ) {s = 47;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 25 : 
                        int LA32_46 = input.LA(1);

                        s = -1;
                        if ( (LA32_46=='\n') ) {s = 47;}

                        else if ( ((LA32_46>='\u0000' && LA32_46<='\t')||(LA32_46>='\u000B' && LA32_46<='\uFFFF')) ) {s = 75;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 26 : 
                        int LA32_51 = input.LA(1);

                        s = -1;
                        if ( (LA32_51=='\r') ) {s = 52;}

                        else if ( (LA32_51=='\n') ) {s = 53;}

                        else if ( ((LA32_51>='\u0000' && LA32_51<='\t')||(LA32_51>='\u000B' && LA32_51<='\f')||(LA32_51>='\u000E' && LA32_51<='\uFFFF')) ) {s = 51;}

                        else s = 54;

                        if ( s>=0 ) return s;
                        break;
                    case 27 : 
                        int LA32_120 = input.LA(1);

                        s = -1;
                        if ( (LA32_120=='\r') ) {s = 118;}

                        else if ( (LA32_120=='\n') ) {s = 119;}

                        else if ( ((LA32_120>='\u0000' && LA32_120<='\t')||(LA32_120>='\u000B' && LA32_120<='\f')||(LA32_120>='\u000E' && LA32_120<='\uFFFF')) ) {s = 120;}

                        else s = 117;

                        if ( s>=0 ) return s;
                        break;
                    case 28 : 
                        int LA32_12 = input.LA(1);

                        s = -1;
                        if ( (LA32_12=='\"') ) {s = 39;}

                        else if ( (LA32_12=='\\') ) {s = 40;}

                        else if ( (LA32_12=='\r') ) {s = 41;}

                        else if ( (LA32_12=='\n') ) {s = 42;}

                        else if ( ((LA32_12>='\u0000' && LA32_12<='\t')||(LA32_12>='\u000B' && LA32_12<='\f')||(LA32_12>='\u000E' && LA32_12<='!')||(LA32_12>='#' && LA32_12<='[')||(LA32_12>=']' && LA32_12<='\uFFFF')) ) {s = 43;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 29 : 
                        int LA32_113 = input.LA(1);

                        s = -1;
                        if ( (LA32_113=='\r') ) {s = 114;}

                        else if ( (LA32_113=='\n') ) {s = 115;}

                        else if ( ((LA32_113>='\u0000' && LA32_113<='\t')||(LA32_113>='\u000B' && LA32_113<='\f')||(LA32_113>='\u000E' && LA32_113<='\uFFFF')) ) {s = 113;}

                        else s = 116;

                        if ( s>=0 ) return s;
                        break;
                    case 30 : 
                        int LA32_200 = input.LA(1);

                        s = -1;
                        if ( (LA32_200=='\r') ) {s = 208;}

                        else if ( (LA32_200=='\n') ) {s = 209;}

                        else if ( ((LA32_200>='\u0000' && LA32_200<='\t')||(LA32_200>='\u000B' && LA32_200<='\f')||(LA32_200>='\u000E' && LA32_200<='\uFFFF')) ) {s = 210;}

                        else s = 207;

                        if ( s>=0 ) return s;
                        break;
                    case 31 : 
                        int LA32_99 = input.LA(1);

                        s = -1;
                        if ( ((LA32_99>='\u0000' && LA32_99<='\t')||(LA32_99>='\u000B' && LA32_99<='\uFFFF')) ) {s = 127;}

                        else if ( (LA32_99=='\n') ) {s = 100;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 32 : 
                        int LA32_79 = input.LA(1);

                        s = -1;
                        if ( ((LA32_79>='\u0000' && LA32_79<='\uFFFF')) ) {s = 75;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 33 : 
                        int LA32_92 = input.LA(1);

                        s = -1;
                        if ( (LA32_92=='\r') ) {s = 118;}

                        else if ( (LA32_92=='\n') ) {s = 119;}

                        else if ( ((LA32_92>='\u0000' && LA32_92<='\t')||(LA32_92>='\u000B' && LA32_92<='\f')||(LA32_92>='\u000E' && LA32_92<='\uFFFF')) ) {s = 120;}

                        else s = 117;

                        if ( s>=0 ) return s;
                        break;
                    case 34 : 
                        int LA32_47 = input.LA(1);

                        s = -1;
                        if ( ((LA32_47>='\u0000' && LA32_47<='\uFFFF')) ) {s = 75;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 35 : 
                        int LA32_181 = input.LA(1);

                        s = -1;
                        if ( (LA32_181=='\r') ) {s = 169;}

                        else if ( (LA32_181=='\n') ) {s = 170;}

                        else if ( ((LA32_181>='\u0000' && LA32_181<='\t')||(LA32_181>='\u000B' && LA32_181<='\f')||(LA32_181>='\u000E' && LA32_181<='\uFFFF')) ) {s = 168;}

                        else s = 171;

                        if ( s>=0 ) return s;
                        break;
                    case 36 : 
                        int LA32_145 = input.LA(1);

                        s = -1;
                        if ( (LA32_145=='\"') ) {s = 145;}

                        else if ( (LA32_145=='\r') ) {s = 99;}

                        else if ( (LA32_145=='\n') ) {s = 100;}

                        else if ( ((LA32_145>='\u0000' && LA32_145<='\t')||(LA32_145>='\u000B' && LA32_145<='\f')||(LA32_145>='\u000E' && LA32_145<='!')||(LA32_145>='#' && LA32_145<='\uFFFF')) ) {s = 101;}

                        else s = 127;

                        if ( s>=0 ) return s;
                        break;
                    case 37 : 
                        int LA32_160 = input.LA(1);

                        s = -1;
                        if ( ((LA32_160>='\u0000' && LA32_160<='\t')||(LA32_160>='\u000B' && LA32_160<='\f')||(LA32_160>='\u000E' && LA32_160<='\uFFFF')) ) {s = 168;}

                        else if ( (LA32_160=='\r') ) {s = 169;}

                        else if ( (LA32_160=='\n') ) {s = 170;}

                        else s = 171;

                        if ( s>=0 ) return s;
                        break;
                    case 38 : 
                        int LA32_176 = input.LA(1);

                        s = -1;
                        if ( ((LA32_176>='\u0000' && LA32_176<='\t')||(LA32_176>='\u000B' && LA32_176<='\f')||(LA32_176>='\u000E' && LA32_176<='\uFFFF')) ) {s = 186;}

                        else if ( (LA32_176=='\r') ) {s = 187;}

                        else if ( (LA32_176=='\n') ) {s = 188;}

                        else s = 189;

                        if ( s>=0 ) return s;
                        break;
                    case 39 : 
                        int LA32_102 = input.LA(1);

                        s = -1;
                        if ( ((LA32_102>='\u0000' && LA32_102<='\t')||(LA32_102>='\u000B' && LA32_102<='\uFFFF')) ) {s = 127;}

                        else if ( (LA32_102=='\n') ) {s = 103;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 40 : 
                        int LA32_210 = input.LA(1);

                        s = -1;
                        if ( (LA32_210=='\r') ) {s = 208;}

                        else if ( (LA32_210=='\n') ) {s = 209;}

                        else if ( ((LA32_210>='\u0000' && LA32_210<='\t')||(LA32_210>='\u000B' && LA32_210<='\f')||(LA32_210>='\u000E' && LA32_210<='\uFFFF')) ) {s = 210;}

                        else s = 207;

                        if ( s>=0 ) return s;
                        break;
                    case 41 : 
                        int LA32_83 = input.LA(1);

                        s = -1;
                        if ( ((LA32_83>='\u0000' && LA32_83<='\t')||(LA32_83>='\u000B' && LA32_83<='\uFFFF')) ) {s = 107;}

                        else if ( (LA32_83=='\n') ) {s = 84;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 42 : 
                        int LA32_71 = input.LA(1);

                        s = -1;
                        if ( (LA32_71=='\"') ) {s = 98;}

                        else if ( (LA32_71=='\r') ) {s = 99;}

                        else if ( (LA32_71=='\n') ) {s = 100;}

                        else if ( ((LA32_71>='\u0000' && LA32_71<='\t')||(LA32_71>='\u000B' && LA32_71<='\f')||(LA32_71>='\u000E' && LA32_71<='!')||(LA32_71>='#' && LA32_71<='\uFFFF')) ) {s = 101;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 43 : 
                        int LA32_100 = input.LA(1);

                        s = -1;
                        if ( ((LA32_100>='\u0000' && LA32_100<='\uFFFF')) ) {s = 127;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 44 : 
                        int LA32_126 = input.LA(1);

                        s = -1;
                        if ( (LA32_126=='\"') ) {s = 145;}

                        else if ( (LA32_126=='\r') ) {s = 99;}

                        else if ( (LA32_126=='\n') ) {s = 100;}

                        else if ( ((LA32_126>='\u0000' && LA32_126<='\t')||(LA32_126>='\u000B' && LA32_126<='\f')||(LA32_126>='\u000E' && LA32_126<='!')||(LA32_126>='#' && LA32_126<='\uFFFF')) ) {s = 101;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 45 : 
                        int LA32_192 = input.LA(1);

                        s = -1;
                        if ( ((LA32_192>='\u0000' && LA32_192<='\t')||(LA32_192>='\u000B' && LA32_192<='\f')||(LA32_192>='\u000E' && LA32_192<='\uFFFF')) ) {s = 201;}

                        else if ( (LA32_192=='\r') ) {s = 202;}

                        else if ( (LA32_192=='\n') ) {s = 203;}

                        else s = 204;

                        if ( s>=0 ) return s;
                        break;
                    case 46 : 
                        int LA32_196 = input.LA(1);

                        s = -1;
                        if ( (LA32_196=='\r') ) {s = 194;}

                        else if ( (LA32_196=='\n') ) {s = 195;}

                        else if ( ((LA32_196>='\u0000' && LA32_196<='\t')||(LA32_196>='\u000B' && LA32_196<='\f')||(LA32_196>='\u000E' && LA32_196<='\uFFFF')) ) {s = 196;}

                        else s = 193;

                        if ( s>=0 ) return s;
                        break;
                    case 47 : 
                        int LA32_0 = input.LA(1);

                        s = -1;
                        if ( (LA32_0=='E') ) {s = 1;}

                        else if ( (LA32_0=='N') ) {s = 2;}

                        else if ( (LA32_0=='F') ) {s = 3;}

                        else if ( (LA32_0=='I') ) {s = 4;}

                        else if ( (LA32_0=='A') ) {s = 5;}

                        else if ( (LA32_0=='S') ) {s = 6;}

                        else if ( (LA32_0=='B') ) {s = 7;}

                        else if ( (LA32_0=='W') ) {s = 8;}

                        else if ( (LA32_0=='T') ) {s = 9;}

                        else if ( (LA32_0=='G') ) {s = 10;}

                        else if ( (LA32_0=='|') ) {s = 11;}

                        else if ( (LA32_0=='\"') ) {s = 12;}

                        else if ( (LA32_0=='\'') ) {s = 13;}

                        else if ( (LA32_0=='@') ) {s = 14;}

                        else if ( (LA32_0=='#') ) {s = 15;}

                        else if ( (LA32_0=='^') ) {s = 16;}

                        else if ( ((LA32_0>='\t' && LA32_0<='\n')||LA32_0=='\r'||LA32_0==' ') ) {s = 17;}

                        else if ( ((LA32_0>='C' && LA32_0<='D')||LA32_0=='H'||(LA32_0>='J' && LA32_0<='M')||(LA32_0>='O' && LA32_0<='R')||(LA32_0>='U' && LA32_0<='V')||(LA32_0>='X' && LA32_0<='Z')||LA32_0=='_'||(LA32_0>='a' && LA32_0<='z')) ) {s = 18;}

                        else if ( ((LA32_0>='0' && LA32_0<='9')) ) {s = 19;}

                        else if ( (LA32_0=='/') ) {s = 20;}

                        else if ( ((LA32_0>='\u0000' && LA32_0<='\b')||(LA32_0>='\u000B' && LA32_0<='\f')||(LA32_0>='\u000E' && LA32_0<='\u001F')||LA32_0=='!'||(LA32_0>='$' && LA32_0<='&')||(LA32_0>='(' && LA32_0<='.')||(LA32_0>=':' && LA32_0<='?')||(LA32_0>='[' && LA32_0<=']')||LA32_0=='`'||LA32_0=='{'||(LA32_0>='}' && LA32_0<='\uFFFF')) ) {s = 21;}

                        if ( s>=0 ) return s;
                        break;
                    case 48 : 
                        int LA32_37 = input.LA(1);

                        s = -1;
                        if ( (LA32_37=='\t'||LA32_37==' ') ) {s = 37;}

                        else if ( ((LA32_37>='\u0000' && LA32_37<='\b')||(LA32_37>='\u000B' && LA32_37<='\f')||(LA32_37>='\u000E' && LA32_37<='\u001F')||(LA32_37>='!' && LA32_37<='{')||(LA32_37>='}' && LA32_37<='\uFFFF')) ) {s = 38;}

                        else s = 36;

                        if ( s>=0 ) return s;
                        break;
                    case 49 : 
                        int LA32_123 = input.LA(1);

                        s = -1;
                        if ( ((LA32_123>='\u0000' && LA32_123<='\t')||(LA32_123>='\u000B' && LA32_123<='\f')||(LA32_123>='\u000E' && LA32_123<='\uFFFF')) ) {s = 136;}

                        else if ( (LA32_123=='\r') ) {s = 137;}

                        else if ( (LA32_123=='\n') ) {s = 138;}

                        else s = 139;

                        if ( s>=0 ) return s;
                        break;
                    case 50 : 
                        int LA32_146 = input.LA(1);

                        s = -1;
                        if ( (LA32_146=='\'') ) {s = 146;}

                        else if ( (LA32_146=='\r') ) {s = 102;}

                        else if ( (LA32_146=='\n') ) {s = 103;}

                        else if ( ((LA32_146>='\u0000' && LA32_146<='\t')||(LA32_146>='\u000B' && LA32_146<='\f')||(LA32_146>='\u000E' && LA32_146<='&')||(LA32_146>='(' && LA32_146<='\uFFFF')) ) {s = 105;}

                        else s = 127;

                        if ( s>=0 ) return s;
                        break;
                    case 51 : 
                        int LA32_103 = input.LA(1);

                        s = -1;
                        if ( ((LA32_103>='\u0000' && LA32_103<='\uFFFF')) ) {s = 127;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 52 : 
                        int LA32_13 = input.LA(1);

                        s = -1;
                        if ( (LA32_13=='\'') ) {s = 44;}

                        else if ( (LA32_13=='\\') ) {s = 45;}

                        else if ( (LA32_13=='\r') ) {s = 46;}

                        else if ( (LA32_13=='\n') ) {s = 47;}

                        else if ( ((LA32_13>='\u0000' && LA32_13<='\t')||(LA32_13>='\u000B' && LA32_13<='\f')||(LA32_13>='\u000E' && LA32_13<='&')||(LA32_13>='(' && LA32_13<='[')||(LA32_13>=']' && LA32_13<='\uFFFF')) ) {s = 48;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 53 : 
                        int LA32_84 = input.LA(1);

                        s = -1;
                        if ( ((LA32_84>='\u0000' && LA32_84<='\uFFFF')) ) {s = 107;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 54 : 
                        int LA32_15 = input.LA(1);

                        s = -1;
                        if ( ((LA32_15>='\u0000' && LA32_15<='\t')||(LA32_15>='\u000B' && LA32_15<='\f')||(LA32_15>='\u000E' && LA32_15<='\uFFFF')) ) {s = 51;}

                        else if ( (LA32_15=='\r') ) {s = 52;}

                        else if ( (LA32_15=='\n') ) {s = 53;}

                        else s = 54;

                        if ( s>=0 ) return s;
                        break;
                    case 55 : 
                        int LA32_74 = input.LA(1);

                        s = -1;
                        if ( (LA32_74=='\"') ) {s = 76;}

                        else if ( (LA32_74=='\\') ) {s = 40;}

                        else if ( (LA32_74=='\r') ) {s = 41;}

                        else if ( (LA32_74=='\n') ) {s = 42;}

                        else if ( ((LA32_74>='\u0000' && LA32_74<='\t')||(LA32_74>='\u000B' && LA32_74<='\f')||(LA32_74>='\u000E' && LA32_74<='!')||(LA32_74>='#' && LA32_74<='[')||(LA32_74>=']' && LA32_74<='\uFFFF')) ) {s = 43;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 56 : 
                        int LA32_77 = input.LA(1);

                        s = -1;
                        if ( (LA32_77=='\r') ) {s = 102;}

                        else if ( (LA32_77=='\n') ) {s = 103;}

                        else if ( (LA32_77=='\'') ) {s = 104;}

                        else if ( ((LA32_77>='\u0000' && LA32_77<='\t')||(LA32_77>='\u000B' && LA32_77<='\f')||(LA32_77>='\u000E' && LA32_77<='&')||(LA32_77>='(' && LA32_77<='\uFFFF')) ) {s = 105;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 57 : 
                        int LA32_43 = input.LA(1);

                        s = -1;
                        if ( (LA32_43=='\r') ) {s = 41;}

                        else if ( (LA32_43=='\n') ) {s = 42;}

                        else if ( (LA32_43=='\"') ) {s = 76;}

                        else if ( (LA32_43=='\\') ) {s = 40;}

                        else if ( ((LA32_43>='\u0000' && LA32_43<='\t')||(LA32_43>='\u000B' && LA32_43<='\f')||(LA32_43>='\u000E' && LA32_43<='!')||(LA32_43>='#' && LA32_43<='[')||(LA32_43>=']' && LA32_43<='\uFFFF')) ) {s = 43;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 58 : 
                        int LA32_128 = input.LA(1);

                        s = -1;
                        if ( (LA32_128=='\'') ) {s = 146;}

                        else if ( (LA32_128=='\r') ) {s = 102;}

                        else if ( (LA32_128=='\n') ) {s = 103;}

                        else if ( ((LA32_128>='\u0000' && LA32_128<='\t')||(LA32_128>='\u000B' && LA32_128<='\f')||(LA32_128>='\u000E' && LA32_128<='&')||(LA32_128>='(' && LA32_128<='\uFFFF')) ) {s = 105;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 59 : 
                        int LA32_136 = input.LA(1);

                        s = -1;
                        if ( (LA32_136=='\r') ) {s = 137;}

                        else if ( (LA32_136=='\n') ) {s = 138;}

                        else if ( ((LA32_136>='\u0000' && LA32_136<='\t')||(LA32_136>='\u000B' && LA32_136<='\f')||(LA32_136>='\u000E' && LA32_136<='\uFFFF')) ) {s = 136;}

                        else s = 139;

                        if ( s>=0 ) return s;
                        break;
                    case 60 : 
                        int LA32_106 = input.LA(1);

                        s = -1;
                        if ( (LA32_106=='*') ) {s = 82;}

                        else if ( (LA32_106=='\r') ) {s = 83;}

                        else if ( (LA32_106=='\n') ) {s = 84;}

                        else if ( ((LA32_106>='\u0000' && LA32_106<='\t')||(LA32_106>='\u000B' && LA32_106<='\f')||(LA32_106>='\u000E' && LA32_106<=')')||(LA32_106>='+' && LA32_106<='\uFFFF')) ) {s = 85;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 61 : 
                        int LA32_91 = input.LA(1);

                        s = -1;
                        if ( ((LA32_91>='\u0000' && LA32_91<='\t')||(LA32_91>='\u000B' && LA32_91<='\f')||(LA32_91>='\u000E' && LA32_91<='\uFFFF')) ) {s = 113;}

                        else if ( (LA32_91=='\r') ) {s = 114;}

                        else if ( (LA32_91=='\n') ) {s = 115;}

                        else s = 116;

                        if ( s>=0 ) return s;
                        break;
                    case 62 : 
                        int LA32_98 = input.LA(1);

                        s = -1;
                        if ( (LA32_98=='\"') ) {s = 126;}

                        else if ( (LA32_98=='\r') ) {s = 99;}

                        else if ( (LA32_98=='\n') ) {s = 100;}

                        else if ( ((LA32_98>='\u0000' && LA32_98<='\t')||(LA32_98>='\u000B' && LA32_98<='\f')||(LA32_98>='\u000E' && LA32_98<='!')||(LA32_98>='#' && LA32_98<='\uFFFF')) ) {s = 101;}

                        else s = 24;

                        if ( s>=0 ) return s;
                        break;
                    case 63 : 
                        int LA32_186 = input.LA(1);

                        s = -1;
                        if ( (LA32_186=='\r') ) {s = 187;}

                        else if ( (LA32_186=='\n') ) {s = 188;}

                        else if ( ((LA32_186>='\u0000' && LA32_186<='\t')||(LA32_186>='\u000B' && LA32_186<='\f')||(LA32_186>='\u000E' && LA32_186<='\uFFFF')) ) {s = 186;}

                        else s = 189;

                        if ( s>=0 ) return s;
                        break;
                    case 64 : 
                        int LA32_168 = input.LA(1);

                        s = -1;
                        if ( (LA32_168=='\r') ) {s = 169;}

                        else if ( (LA32_168=='\n') ) {s = 170;}

                        else if ( ((LA32_168>='\u0000' && LA32_168<='\t')||(LA32_168>='\u000B' && LA32_168<='\f')||(LA32_168>='\u000E' && LA32_168<='\uFFFF')) ) {s = 168;}

                        else s = 171;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 32, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}