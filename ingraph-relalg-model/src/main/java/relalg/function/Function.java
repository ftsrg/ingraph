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
  //                                              input            t
  //             name              category       types           output type  min. arity
  AVG           ("avg",            FunctionCategory.AGGREGATION,   l(NUMBER),       NUMBER,      1),
  COLLECT       ("collect",        FunctionCategory.AGGREGATION,   l(NUMBER),       LIST_TYPE,   1),
  COUNT         ("count",          FunctionCategory.AGGREGATION,   l(NUMBER),       NUMBER,      1),
  COUNT_ALL     ("count(*)",       FunctionCategory.AGGREGATION,   l(NUMBER),       NUMBER,      0), // count(*)
  MAX           ("max",            FunctionCategory.AGGREGATION,   l(NUMBER),       NUMBER,      1),
  MIN           ("min",            FunctionCategory.AGGREGATION,   l(NUMBER),       NUMBER,      1),
  SUM           ("sum",            FunctionCategory.AGGREGATION,   l(NUMBER),       NUMBER,      1),

  STDDEV        ("stddev",         FunctionCategory.AGGREGATION,   l(NUMBER),       NUMBER,      1),
  STDDEVP       ("stddevp",        FunctionCategory.AGGREGATION,   l(NUMBER),       NUMBER,      1),
  PERCENTILECONT("percentileCont", FunctionCategory.AGGREGATION,   l(NUMBER,
                                                                     FLOAT),        NUMBER,      2),
  PERCENTILEDISC("percentileDisc", FunctionCategory.AGGREGATION,   l(NUMBER,
                                                                     FLOAT),        NUMBER,      2),

  TOBOOLEAN     ("toBoolean",      FunctionCategory.CONVERSION,    l(ANY),          BOOLEAN,     1), // toBoolean( expression )
  TOINTEGER     ("toInt",          FunctionCategory.CONVERSION,    l(ANY),          INTEGER,     1), // toInteger( expression )
  TOINT         ("toInt",          FunctionCategory.CONVERSION,    l(ANY),          INTEGER,     1), // toInt( expression )
  TOFLOAT       ("toFloat",        FunctionCategory.CONVERSION,    l(ANY),          FLOAT,       1), // toFloat( expression )
  TOSTRING      ("toString",       FunctionCategory.STRING,        l(ANY),          STRING,      1), // toString( expression )

  LABELS        ("labels",         FunctionCategory.META,          l(NODE),         LIST_TYPE,   1), // labels( node )
  KEYS          ("keys",           FunctionCategory.META,          l(ELEMENT),      LIST_TYPE,   1), // keys( property-container )
  PROPERTIES    ("properties",     FunctionCategory.META,          l(ELEMENT),      MAP,         1), // properties( expression ) -- "If the argument is a node or a relationship, the returned map is a map of its properties .If the argument is already a map, it is returned unchanged."
  RELATIONSHIPS ("relationships",  FunctionCategory.META,          l(PATH),         LIST_TYPE,   1), // relationships( path )

  TAIL          ("tail",           FunctionCategory.LIST,          l(LIST_TYPE),    LIST_TYPE,   1), // tail( expression )
  NODES         ("nodes",          FunctionCategory.LIST,          l(PATH),         LIST_TYPE,   1), // nodes( path )
  RANGE         ("range",          FunctionCategory.LIST,          l(INTEGER,
                                                                     INTEGER,
                                                                     INTEGER),      LIST_TYPE,   1), // range( start, end [, step] )

  E             ("e",              FunctionCategory.LOGARITHMIC,   l(NONE),         FLOAT,       0), // e()
  EXP           ("e",              FunctionCategory.LOGARITHMIC,   l(NUMBER),       FLOAT,       1), // e( expression )
  LOG           ("log",            FunctionCategory.LOGARITHMIC,   l(NUMBER),       FLOAT,       1), // log( expression )
  LOG10         ("log10",          FunctionCategory.LOGARITHMIC,   l(NUMBER),       FLOAT,       1), // log10( expression )
  SQRT          ("sqrt",           FunctionCategory.LOGARITHMIC,   l(NUMBER),       FLOAT,       1), // sqrt( expression )

  ABS           ("abs",            FunctionCategory.NUMERIC,       l(NUMBER),       NUMBER,      1), // abs( expression )

  CEIL          ("ceil",           FunctionCategory.NUMERIC,       l(NUMBER),       INTEGER,     1), // ceil( expression )
  FLOOR         ("floor",          FunctionCategory.NUMERIC,       l(NUMBER),       INTEGER,     1), // floor( expression )
  ROUND         ("round",          FunctionCategory.NUMERIC,       l(NUMBER),       INTEGER,     1), // round( expression )
  SIGN          ("sign",           FunctionCategory.NUMERIC,       l(NUMBER),       INTEGER,     1), // sign( expression )

  RAND          ("rand",           FunctionCategory.NUMERIC,       l(NONE),         FLOAT,       0), // rand()

  EXISTS        ("exists",         FunctionCategory.PREDICATE,     l(ANY),          BOOLEAN,     1),
  IN_COLLECTION ("in_collection",  FunctionCategory.PREDICATE,     l(ANY,
                                                                     LIST_TYPE),    BOOLEAN,     2),

  COALESCE      ("coalesce",       FunctionCategory.SCALAR,        l(ANY),          ANY,         1), // coalesce( expression [, expression]* )
  STARTNODE     ("startNode",      FunctionCategory.SCALAR,        l(RELATIONSHIP), NODE,        1), // startNode( relationship )
  ENDNODE       ("endNode",        FunctionCategory.SCALAR,        l(RELATIONSHIP), NODE,        1), // endNode( relationship )
  HEAD          ("head",           FunctionCategory.SCALAR,        l(LIST_TYPE),    ANY,         1), // head( expression )
  LAST          ("last",           FunctionCategory.SCALAR,        l(LIST_TYPE),    ANY,         1), // last( expression )
  LENGTH        ("length",         FunctionCategory.SCALAR,        l(ANY),          INTEGER,     1), // length( path ), length( string )
  SIZE          ("size",           FunctionCategory.SCALAR,        l(LIST_TYPE),    INTEGER,     1), // size( list ), size( pattern expression )
  TYPE          ("type",           FunctionCategory.SCALAR,        l(RELATIONSHIP), STRING,      1), // type( relationship )
  ID            ("id",             FunctionCategory.SCALAR,        l(ELEMENT),      INTEGER,     1), // id( property-container )

  LEFT          ("left",           FunctionCategory.STRING,        l(STRING,
                                                                     INTEGER),      STRING,      2), // left( original, length )
  RIGHT         ("right",          FunctionCategory.STRING,        l(STRING,
                                                                     INTEGER),      STRING,      2), // right( original, length )
  LTRIM         ("ltrim",          FunctionCategory.STRING,        l(STRING),       STRING,      1), // ltrim( original )
  RTRIM         ("rtrim",          FunctionCategory.STRING,        l(STRING),       STRING,      1), // rtrim( original )
  TRIM          ("trim",           FunctionCategory.STRING,        l(STRING),       STRING,      1), // trim( original )
  REPLACE       ("replace",        FunctionCategory.STRING,        l(STRING,
                                                                     STRING,
                                                                     STRING),       STRING,      3), // replace( original, search, replace )
  REGEX_LIKE    ("regexLike",      FunctionCategory.STRING,        l(STRING,
                                                                     STRING),       BOOLEAN,     2), // string =~ pattern
  REVERSE       ("reverse",        FunctionCategory.STRING,        l(STRING),       STRING,      1), // reverse( original )
  SUBSTRING     ("substring",      FunctionCategory.STRING,        l(STRING,
                                                                     STRING,
                                                                     INTEGER),      STRING,      2), // substring( original, start [, length] )
  TOLOWER       ("toLower",        FunctionCategory.STRING,        l(STRING),       STRING,      1), // lower( original )
  TOUPPER       ("toUpper",        FunctionCategory.STRING,        l(STRING),       STRING,      1), // upper( original )

  STARTS_WITH   ("startsWith",     FunctionCategory.STRING,        l(STRING,
                                                                     STRING),       BOOLEAN,     2), // STARTS_WITH(string, prefixString)
  ENDS_WITH     ("endsWith",       FunctionCategory.STRING,        l(STRING,
                                                                     STRING),       BOOLEAN,     2), // ENDS_WITH(string, postfixString)
  CONTAINS      ("contains",       FunctionCategory.STRING,        l(STRING,
                                                                     STRING),       BOOLEAN,     2), // CONTAINS(string, middleString)

  SPLIT         ("split",          FunctionCategory.STRING,        l(STRING,
                                                                     STRING),       LIST_TYPE,   2), // split( original, splitPattern )

  ACOS          ("acos",           FunctionCategory.TRIGONOMETRIC, l(NUMBER),       FLOAT,       1), // acos( expression )
  ASIN          ("asin",           FunctionCategory.TRIGONOMETRIC, l(NUMBER),       FLOAT,       1), // asin( expression )
  ATAN          ("atan",           FunctionCategory.TRIGONOMETRIC, l(NUMBER),       FLOAT,       1), // atan( expression )
  ATAN2         ("atan2",          FunctionCategory.TRIGONOMETRIC, l(NUMBER,
                                                                     NUMBER),       FLOAT,       2), // atan2( expression1, expression2 )
  COS           ("cos",            FunctionCategory.TRIGONOMETRIC, l(NUMBER),       FLOAT,       1), // cos( expression )
  COT           ("cot",            FunctionCategory.TRIGONOMETRIC, l(NUMBER),       FLOAT,       1), // cot( expression )
  SIN           ("sin",            FunctionCategory.TRIGONOMETRIC, l(NUMBER),       FLOAT,       1), // sin( expression )
  TAN           ("tan",            FunctionCategory.TRIGONOMETRIC, l(NUMBER),       FLOAT,       1), // tan( expression )

  DEGREES       ("degrees",        FunctionCategory.TRIGONOMETRIC, l(NUMBER),       FLOAT,       1), // degrees( expression )
  RADIANS       ("radians",        FunctionCategory.TRIGONOMETRIC, l(NUMBER),       FLOAT,       1), // radians( expression )

  PI            ("pi",             FunctionCategory.TRIGONOMETRIC, l(NONE),         FLOAT,       0), // pi()
;
  private final String prettyName;
  private final FunctionCategory category;
  private final List<CypherType> inputTypes;
  private final CypherType outputType;
  private final int minimumArity;

  Function(final String prettyName, final FunctionCategory category, final List<CypherType> inputTypes, final CypherType outputType, final int minimumArity) {
    this.prettyName = prettyName;
    this.category = category;
    this.inputTypes = inputTypes;
    this.outputType = outputType;
    this.minimumArity = minimumArity;
  }

  public String getPrettyName() {
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
