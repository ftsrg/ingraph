package relalgfunction;

import static relalgfunction.CypherType.ANY;
import static relalgfunction.CypherType.BOOLEAN;
import static relalgfunction.CypherType.ELEMENT;
import static relalgfunction.CypherType.FLOAT;
import static relalgfunction.CypherType.INTEGER;
import static relalgfunction.CypherType.LIST_TYPE;
import static relalgfunction.CypherType.MAP;
import static relalgfunction.CypherType.NODE;
import static relalgfunction.CypherType.NONE;
import static relalgfunction.CypherType.NUMBER;
import static relalgfunction.CypherType.PATH;
import static relalgfunction.CypherType.RELATIONSHIP;
import static relalgfunction.CypherType.STRING;

import java.util.Arrays;
import java.util.List;

public enum Function {
  //                                             input            output     min.
  //             category                        types            type      arity
  AVG           (FunctionCategory.AGGREGATION,   l(NUMBER),       NUMBER,    1),
  COUNT         (FunctionCategory.AGGREGATION,   l(NUMBER),       NUMBER,    1),
  COUNT_ALL     (FunctionCategory.AGGREGATION,   l(NUMBER),       NUMBER,    1), // count(*)
  MAX           (FunctionCategory.AGGREGATION,   l(NUMBER),       NUMBER,    1),
  MIN           (FunctionCategory.AGGREGATION,   l(NUMBER),       NUMBER,    1),
  SUM           (FunctionCategory.AGGREGATION,   l(NUMBER),       NUMBER,    1),

  STDDEV        (FunctionCategory.AGGREGATION,   l(NUMBER),       NUMBER,    1),
  STDDEVP       (FunctionCategory.AGGREGATION,   l(NUMBER),       NUMBER,    1),
  PERCENTILECONT(FunctionCategory.AGGREGATION,   l(NUMBER,
                                                   FLOAT),        NUMBER,    2),
  PERCENTILEDISC(FunctionCategory.AGGREGATION,   l(NUMBER,
                                                   FLOAT),        NUMBER,    2),

  TOBOOLEAN     (FunctionCategory.CONVERSION,    l(ANY),          BOOLEAN,   1),
  TOINTEGER     (FunctionCategory.CONVERSION,    l(ANY),          INTEGER,   1),
  TOINT         (FunctionCategory.CONVERSION,    l(ANY),          INTEGER,   1), // toInt( expression )
  TOFLOAT       (FunctionCategory.CONVERSION,    l(ANY),          FLOAT,     1), // toFloat( expression )
  TOSTRING      (FunctionCategory.STRING,        l(ANY),          STRING,    1), // toString( expression )

  RELATIONSHIPS (FunctionCategory.LIST,          l(PATH),         LIST_TYPE, 1), // relationships( path )
  TAIL          (FunctionCategory.LIST,          l(LIST_TYPE),    LIST_TYPE, 1), // tail( expression )
  KEYS          (FunctionCategory.LIST,          l(ELEMENT),      LIST_TYPE, 1), // keys( property-container )
  LABELS        (FunctionCategory.LIST,          l(NODE),         LIST_TYPE, 1), // labels( node )
  NODES         (FunctionCategory.LIST,          l(PATH),         LIST_TYPE, 1), // nodes( path )
  RANGE         (FunctionCategory.LIST,          l(INTEGER,
		                                           INTEGER,
		                                           INTEGER),      LIST_TYPE, 1), // range( start, end [, step] )

  E             (FunctionCategory.LOGARITHMIC,   l(NONE),         FLOAT,     0), // e()
  EXP           (FunctionCategory.LOGARITHMIC,   l(NUMBER),       FLOAT,     1), // e( expression )
  LOG           (FunctionCategory.LOGARITHMIC,   l(NUMBER),       FLOAT,     1), // log( expression )
  LOG10         (FunctionCategory.LOGARITHMIC,   l(NUMBER),       FLOAT,     1), // log10( expression )
  SQRT          (FunctionCategory.LOGARITHMIC,   l(NUMBER),       FLOAT,     1), // sqrt( expression )

  ABS           (FunctionCategory.NUMERIC,       l(NUMBER),       NUMBER,    1), // abs( expression )

  CEIL          (FunctionCategory.NUMERIC,       l(NUMBER),       INTEGER,   1), // ceil( expression )
  FLOOR         (FunctionCategory.NUMERIC,       l(NUMBER),       INTEGER,   1), // floor( expression )
  ROUND         (FunctionCategory.NUMERIC,       l(NUMBER),       INTEGER,   1), // round( expression )
  SIGN          (FunctionCategory.NUMERIC,       l(NUMBER),       INTEGER,   1), // sign( expression )

  RAND          (FunctionCategory.NUMERIC,       l(NONE),         FLOAT,     0), // rand()

  EXISTS        (FunctionCategory.PREDICATE,     l(ANY),          BOOLEAN,   1),
  IN_COLLECTION (FunctionCategory.PREDICATE,     l(ANY,
		                                           LIST_TYPE),    BOOLEAN,   2),

  COALESCE      (FunctionCategory.SCALAR,        l(ANY),          ANY,       1), // coalesce( expression [, expression]* )
  STARTNODE     (FunctionCategory.SCALAR,        l(RELATIONSHIP), NODE,      1), // startNode( relationship )
  ENDNODE       (FunctionCategory.SCALAR,        l(RELATIONSHIP), NODE,      1), // endNode( relationship )
  HEAD          (FunctionCategory.SCALAR,        l(LIST_TYPE),    ANY,       1), // head( expression )
  LAST          (FunctionCategory.SCALAR,        l(LIST_TYPE),    ANY,       1), // last( expression )
  LENGTH        (FunctionCategory.SCALAR,        l(ANY),          INTEGER,   1), // length( path ), length( string )
  PROPERTIES    (FunctionCategory.SCALAR,        l(ELEMENT),      MAP,       1), // properties( expression ) -- "If the argument is a node or a relationship, the returned map is a map of its properties .If the argument is already a map, it is returned unchanged."
  SIZE          (FunctionCategory.SCALAR,        l(LIST_TYPE),    INTEGER,   1), // size( list ), size( pattern expression )
  TYPE          (FunctionCategory.SCALAR,        l(RELATIONSHIP), STRING,    1), // type( relationship )
  ID            (FunctionCategory.SCALAR,        l(ELEMENT),      INTEGER,   1), // id( property-container )

  LEFT          (FunctionCategory.STRING,        l(STRING,
		                                           INTEGER),      STRING,    2), // left( original, length )
  RIGHT         (FunctionCategory.STRING,        l(STRING,
		                                           INTEGER),      STRING,    2), // right( original, length )
  LTRIM         (FunctionCategory.STRING,        l(STRING),       STRING,    1), // ltrim( original )
  RTRIM         (FunctionCategory.STRING,        l(STRING),       STRING,    1), // rtrim( original )
  TRIM          (FunctionCategory.STRING,        l(STRING),       STRING,    1), // trim( original )
  REPLACE       (FunctionCategory.STRING,        l(STRING,
		                                           STRING,
		                                           STRING),       STRING,    3), // replace( original, search, replace )
  REGEX_LIKE	(FunctionCategory.STRING,		l(STRING,
                                                   STRING),	BOOLEAN,	2), // string =~ pattern
  REVERSE       (FunctionCategory.STRING,        l(STRING),       STRING,    1), // reverse( original )
  SUBSTRING     (FunctionCategory.STRING,        l(STRING,
		                                           STRING,
		                                           INTEGER),      STRING,    2), // substring( original, start [, length] )
  TOLOWER       (FunctionCategory.STRING,        l(STRING),       STRING,    1), // lower( original )
  TOUPPER       (FunctionCategory.STRING,        l(STRING),       STRING,    1), // upper( original )

  STARTS_WITH   (FunctionCategory.STRING,        l(STRING,
		                                           STRING),       BOOLEAN,   2), // STARTS_WITH(string, prefixString)
  ENDS_WITH     (FunctionCategory.STRING,        l(STRING,
		                                           STRING),       BOOLEAN,   2), // ENDS_WITH(string, postfixString)
  CONTAINS      (FunctionCategory.STRING,        l(STRING,
		                                           STRING),       BOOLEAN,   2), // CONTAINS(string, middleString)

  SPLIT         (FunctionCategory.STRING,        l(STRING,
		                                           STRING),       LIST_TYPE, 2), // split( original, splitPattern )

  ACOS          (FunctionCategory.TRIGONOMETRIC, l(NUMBER),       FLOAT,     1), // acos( expression )
  ASIN          (FunctionCategory.TRIGONOMETRIC, l(NUMBER),       FLOAT,     1), // asin( expression )
  ATAN          (FunctionCategory.TRIGONOMETRIC, l(NUMBER),       FLOAT,     1), // atan( expression )
  ATAN2         (FunctionCategory.TRIGONOMETRIC, l(NUMBER,
		                                           NUMBER),       FLOAT,     2), // atan2( expression1, expression2 )
  COS           (FunctionCategory.TRIGONOMETRIC, l(NUMBER),       FLOAT,     1), // cos( expression )
  COT           (FunctionCategory.TRIGONOMETRIC, l(NUMBER),       FLOAT,     1), // cot( expression )
  SIN           (FunctionCategory.TRIGONOMETRIC, l(NUMBER),       FLOAT,     1), // sin( expression )
  TAN           (FunctionCategory.TRIGONOMETRIC, l(NUMBER),       FLOAT,     1), // tan( expression )

  DEGREES       (FunctionCategory.TRIGONOMETRIC, l(NUMBER),       FLOAT,     1), // degrees( expression )
  RADIANS       (FunctionCategory.TRIGONOMETRIC, l(NUMBER),       FLOAT,     1), // radians( expression )

  PI            (FunctionCategory.TRIGONOMETRIC, l(NONE),         FLOAT,     0), // pi()
;

  private final FunctionCategory category;
  private final List<CypherType> inputTypes;
  private final CypherType outputType;
  private final int minimumArity;

  Function(final FunctionCategory category, final List<CypherType> inputTypes, final CypherType outputType, final int minimumArity) {
    this.category = category;
    this.inputTypes = inputTypes;
    this.outputType = outputType;
    this.minimumArity = minimumArity;
  }

  public String getLowerCaseName() {
	return this.toString().toLowerCase();
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
