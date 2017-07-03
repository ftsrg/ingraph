package relalg.function;

import static relalg.function.CypherType.ANY;
import static relalg.function.CypherType.BOOLEAN;
import static relalg.function.CypherType.ELEMENT;
import static relalg.function.CypherType.FLOAT;
import static relalg.function.CypherType.INTEGER;
import static relalg.function.CypherType.LIST_TYPE;
import static relalg.function.CypherType.MAP;
import static relalg.function.CypherType.NODE;
import static relalg.function.CypherType.NONE;
import static relalg.function.CypherType.NUMBER;
import static relalg.function.CypherType.PATH;
import static relalg.function.CypherType.RELATIONSHIP;
import static relalg.function.CypherType.STRING;

import java.util.Arrays;
import java.util.List;

public enum Function {
  //             name              category                        output type  ma input types

  // aggregation functions
  AVG           ("avg",            FunctionCategory.AGGREGATION,   NUMBER,      1, l(NUMBER)                   ),
  COLLECT       ("collect",        FunctionCategory.AGGREGATION,   LIST_TYPE,   1, l(NUMBER)                   ),
  COUNT         ("count",          FunctionCategory.AGGREGATION,   NUMBER,      1, l(NUMBER)                   ),
  COUNT_ALL     ("count(*)",       FunctionCategory.AGGREGATION,   NUMBER,      0, l(NUMBER)                   ), // count(*)
  MAX           ("max",            FunctionCategory.AGGREGATION,   NUMBER,      1, l(NUMBER)                   ),
  MIN           ("min",            FunctionCategory.AGGREGATION,   NUMBER,      1, l(NUMBER)                   ),
  SUM           ("sum",            FunctionCategory.AGGREGATION,   NUMBER,      1, l(NUMBER)                   ),

  STDDEV        ("stddev",         FunctionCategory.AGGREGATION,   NUMBER,      1, l(NUMBER)                   ),
  STDDEVP       ("stddevp",        FunctionCategory.AGGREGATION,   NUMBER,      1, l(NUMBER)                   ),
  PERCENTILECONT("percentileCont", FunctionCategory.AGGREGATION,   NUMBER,      2, l(NUMBER, FLOAT)            ),
  PERCENTILEDISC("percentileDisc", FunctionCategory.AGGREGATION,   NUMBER,      2, l(NUMBER, FLOAT)            ),

  // metafunctions
  // these are functions that require the engine to propgate some information from the get*nodes, e.g. labels of a node
  LABELS        ("labels",         FunctionCategory.META,          LIST_TYPE,   1, l(NODE)                     ), // labels( node )
  KEYS          ("keys",           FunctionCategory.META,          LIST_TYPE,   1, l(ELEMENT)                  ), // keys( property-container )
  PROPERTIES    ("properties",     FunctionCategory.META,          MAP,         1, l(ELEMENT)                  ), // properties( expression ) -- "If the argument is a node or a relationship, the returned map is a map of its properties .If the argument is already a map, it is returned unchanged."
  RELATIONSHIPS ("relationships",  FunctionCategory.META,          LIST_TYPE,   1, l(PATH)                     ), // relationships( path )
  TYPE          ("type",           FunctionCategory.META,          STRING,      1, l(RELATIONSHIP)             ), // type( relationship )
  STARTNODE     ("startNode",      FunctionCategory.META,          NODE,        1, l(RELATIONSHIP)             ), // startNode( relationship )
  ENDNODE       ("endNode",        FunctionCategory.META,          NODE,        1, l(RELATIONSHIP)             ), // endNode( relationship )

  // list functions
  TAIL          ("tail",           FunctionCategory.LIST,          LIST_TYPE,   1, l(LIST_TYPE)                ), // tail( expression )
  NODES         ("nodes",          FunctionCategory.LIST,          LIST_TYPE,   1, l(PATH)                     ), // nodes( path )
  RANGE         ("range",          FunctionCategory.LIST,          LIST_TYPE,   1, l(INTEGER, INTEGER, INTEGER)), // range( start, end [, step] )

  // conversion functions
  TOBOOLEAN     ("toBoolean",      FunctionCategory.CONVERSION,    BOOLEAN,     1, l(ANY)                      ), // toBoolean( expression )
  TOINTEGER     ("toInt",          FunctionCategory.CONVERSION,    INTEGER,     1, l(ANY)                      ), // toInteger( expression )
  TOINT         ("toInt",          FunctionCategory.CONVERSION,    INTEGER,     1, l(ANY)                      ), // toInt( expression )
  TOFLOAT       ("toFloat",        FunctionCategory.CONVERSION,    FLOAT,       1, l(ANY)                      ), // toFloat( expression )
  TOSTRING      ("toString",       FunctionCategory.CONVERSION,    STRING,      1, l(ANY)                      ), // toString( expression )

  // predicate functions
  EXISTS        ("exists",         FunctionCategory.PREDICATE,     BOOLEAN,     1, l(ANY)                      ),
  IN_COLLECTION ("in_collection",  FunctionCategory.PREDICATE,     BOOLEAN,     2, l(ANY, LIST_TYPE)           ),

  // scalar functions
  HEAD          ("head",           FunctionCategory.SCALAR,        ANY,         1, l(LIST_TYPE)                ), // head( expression )
  LAST          ("last",           FunctionCategory.SCALAR,        ANY,         1, l(LIST_TYPE)                ), // last( expression )
  SIZE          ("size",           FunctionCategory.SCALAR,        INTEGER,     1, l(LIST_TYPE)                ), // size( list ), size( pattern expression )
  LENGTH        ("length",         FunctionCategory.SCALAR,        INTEGER,     1, l(ANY)                      ), // length( path ), length( string )
  COALESCE      ("coalesce",       FunctionCategory.SCALAR,        ANY,         1, l(ANY)                      ), // coalesce( expression [, expression]* )
  ID            ("id",             FunctionCategory.SCALAR,        INTEGER,     1, l(ELEMENT)                  ), // id( property-container )

  // string functions
  LEFT          ("left",           FunctionCategory.STRING,        STRING,      2, l(STRING,INTEGER)           ), // left( original, length )
  RIGHT         ("right",          FunctionCategory.STRING,        STRING,      2, l(STRING, INTEGER)          ), // right( original, length )
  LTRIM         ("ltrim",          FunctionCategory.STRING,        STRING,      1, l(STRING)                   ), // ltrim( original )
  RTRIM         ("rtrim",          FunctionCategory.STRING,        STRING,      1, l(STRING)                   ), // rtrim( original )
  TRIM          ("trim",           FunctionCategory.STRING,        STRING,      1, l(STRING)                   ), // trim( original )
  REPLACE       ("replace",        FunctionCategory.STRING,        STRING,      3, l(STRING, STRING, STRING)   ), // replace( original, search, replace )
  REGEX_LIKE    ("regexLike",      FunctionCategory.STRING,        BOOLEAN,     2, l(STRING, STRING)           ), // string =~ pattern
  REVERSE       ("reverse",        FunctionCategory.STRING,        STRING,      1, l(STRING)                   ), // reverse( original )
  SUBSTRING     ("substring",      FunctionCategory.STRING,        STRING,      2, l(STRING, STRING, INTEGER)  ), // substring( original, start [, length] )
  TOLOWER       ("toLower",        FunctionCategory.STRING,        STRING,      1, l(STRING)                   ), // lower( original )
  TOUPPER       ("toUpper",        FunctionCategory.STRING,        STRING,      1, l(STRING)                   ), // upper( original )
  STARTS_WITH   ("startsWith",     FunctionCategory.STRING,        BOOLEAN,     2, l(STRING, STRING)           ), // STARTS_WITH(string, prefixString)
  ENDS_WITH     ("endsWith",       FunctionCategory.STRING,        BOOLEAN,     2, l(STRING, STRING)           ), // ENDS_WITH(string, postfixString)
  CONTAINS      ("contains",       FunctionCategory.STRING,        BOOLEAN,     2, l(STRING, STRING)           ), // CONTAINS(string, middleString)
  SPLIT         ("split",          FunctionCategory.STRING,        LIST_TYPE,   2, l(STRING, STRING)           ), // split( original, splitPattern )

  // logarithmic functions
  E             ("e",              FunctionCategory.LOGARITHMIC,   FLOAT,       0, l(NONE)                     ), // e()
  EXP           ("e",              FunctionCategory.LOGARITHMIC,   FLOAT,       1, l(NUMBER)                   ), // e( expression )
  LOG           ("log",            FunctionCategory.LOGARITHMIC,   FLOAT,       1, l(NUMBER)                   ), // log( expression )
  LOG10         ("log10",          FunctionCategory.LOGARITHMIC,   FLOAT,       1, l(NUMBER)                   ), // log10( expression )
  SQRT          ("sqrt",           FunctionCategory.LOGARITHMIC,   FLOAT,       1, l(NUMBER)                   ), // sqrt( expression )

  // numeric functions
  ABS           ("abs",            FunctionCategory.NUMERIC,       NUMBER,      1, l(NUMBER)                   ), // abs( expression )
  CEIL          ("ceil",           FunctionCategory.NUMERIC,       INTEGER,     1, l(NUMBER)                   ), // ceil( expression )
  FLOOR         ("floor",          FunctionCategory.NUMERIC,       INTEGER,     1, l(NUMBER)                   ), // floor( expression )
  ROUND         ("round",          FunctionCategory.NUMERIC,       INTEGER,     1, l(NUMBER)                   ), // round( expression )
  SIGN          ("sign",           FunctionCategory.NUMERIC,       INTEGER,     1, l(NUMBER)                   ), // sign( expression )
  RAND          ("rand",           FunctionCategory.NUMERIC,       FLOAT,       0, l(NONE)                     ), // rand()

  // trigonometric functions
  ACOS          ("acos",           FunctionCategory.TRIGONOMETRIC, FLOAT,       1, l(NUMBER)                   ), // acos( expression )
  ASIN          ("asin",           FunctionCategory.TRIGONOMETRIC, FLOAT,       1, l(NUMBER)                   ), // asin( expression )
  ATAN          ("atan",           FunctionCategory.TRIGONOMETRIC, FLOAT,       1, l(NUMBER)                   ), // atan( expression )
  ATAN2         ("atan2",          FunctionCategory.TRIGONOMETRIC, FLOAT,       2, l(NUMBER, NUMBER)           ), // atan2( expression1, expression2 )
  COS           ("cos",            FunctionCategory.TRIGONOMETRIC, FLOAT,       1, l(NUMBER)                   ), // cos( expression )
  COT           ("cot",            FunctionCategory.TRIGONOMETRIC, FLOAT,       1, l(NUMBER)                   ), // cot( expression )
  SIN           ("sin",            FunctionCategory.TRIGONOMETRIC, FLOAT,       1, l(NUMBER)                   ), // sin( expression )
  TAN           ("tan",            FunctionCategory.TRIGONOMETRIC, FLOAT,       1, l(NUMBER)                   ), // tan( expression )
  DEGREES       ("degrees",        FunctionCategory.TRIGONOMETRIC, FLOAT,       1, l(NUMBER)                   ), // degrees( expression )
  RADIANS       ("radians",        FunctionCategory.TRIGONOMETRIC, FLOAT,       1, l(NUMBER)                   ), // radians( expression )
  PI            ("pi",             FunctionCategory.TRIGONOMETRIC, FLOAT,       0, l(NONE)                     ), // pi()
  ;
  private final String prettyName;
  private final FunctionCategory category;
  private final CypherType outputType;
  private final int minimumArity;
  private final List<CypherType> inputTypes;

  Function(final String prettyName, final FunctionCategory category, final CypherType outputType, final int minimumArity, final List<CypherType> inputTypes) {
    this.prettyName = prettyName;
    this.category = category;
    this.outputType = outputType;
    this.minimumArity = minimumArity;
    this.inputTypes = inputTypes;
  }

  @Override
  public String toString() {
    return prettyName;
  }

  public FunctionCategory getCategory() {
    return this.category;
  }

  public List<CypherType> getInputTypes() {
    return inputTypes;
  }

  public CypherType getOutputType() {
    return outputType;
  }

  public int getMinimumArity() {
    return minimumArity;
  }

  public static List<CypherType> l(CypherType... l) {
    return Arrays.asList(l);
  }

  public boolean isAggregation() {
    return category == FunctionCategory.AGGREGATION;
  }

  public boolean isMeta() {
    return category == FunctionCategory.META;
  }

  /**
   * Indicate if the function might provide a numeric results.
   *
   * For certain functions e.g. cosine (cos), this is obvious, but a list head returns ANY,
   * and whether it is numeric or not depends on the content of the list
   */
  public boolean mightBeNumericValued() {
	return outputType == NUMBER
	    || outputType == INTEGER
	    || outputType == FLOAT
	    || outputType == ANY
	    ;
  }
}
