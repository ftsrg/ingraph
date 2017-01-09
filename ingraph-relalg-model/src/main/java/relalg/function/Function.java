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

public enum Function {
  COLLECT       (FunctionCategory.AGGREGATION,   ANY,          LIST_TYPE, 1),

  AVG           (FunctionCategory.AGGREGATION,   NUMBER,       NUMBER,    1),
  COUNT         (FunctionCategory.AGGREGATION,   NUMBER,       NUMBER,    1),
  MAX           (FunctionCategory.AGGREGATION,   NUMBER,       NUMBER,    1),
  MIN           (FunctionCategory.AGGREGATION,   NUMBER,       NUMBER,    1),
  SUM           (FunctionCategory.AGGREGATION,   NUMBER,       NUMBER,    1),

  STDDEV        (FunctionCategory.STATISTICAL,   NUMBER,       NUMBER,    1),
  STDDEVP       (FunctionCategory.STATISTICAL,   NUMBER,       NUMBER,    1),
  PERCENTILECONT(FunctionCategory.STATISTICAL,   NUMBER,       NUMBER,    1),
  PERCENTILEDISC(FunctionCategory.STATISTICAL,   NUMBER,       NUMBER,    1),

  TOBOOLEAN     (FunctionCategory.CONVERSION,    ANY,          BOOLEAN,   1),
  TOINTEGER     (FunctionCategory.CONVERSION,    ANY,          INTEGER,   1),
  TOINT         (FunctionCategory.CONVERSION,    ANY,          INTEGER,   1), // toInt( expression )
  TOFLOAT       (FunctionCategory.CONVERSION,    ANY,          FLOAT,     1), // toFloat( expression )
  TOSTRING      (FunctionCategory.STRING,        ANY,          STRING,    1), // toString( expression )

  RELATIONSHIPS (FunctionCategory.LIST,          PATH,         LIST_TYPE, 1), // relationships( path )
  TAIL          (FunctionCategory.LIST,          LIST_TYPE,    LIST_TYPE, 1), // tail( expression )
  KEYS          (FunctionCategory.LIST,          ELEMENT,      LIST_TYPE, 1), // keys( property-container )
  LABELS        (FunctionCategory.LIST,          NODE,         LIST_TYPE, 1), // labels( node )
  NODES         (FunctionCategory.LIST,          PATH,         LIST_TYPE, 1), // nodes( path )
  RANGE         (FunctionCategory.LIST,          INTEGER,      LIST_TYPE, 1), // range( start, end [, step] )

  E             (FunctionCategory.LOGARITHMIC,   NONE,         FLOAT,     0), // e()
  EXP           (FunctionCategory.LOGARITHMIC,   NUMBER,       FLOAT,     1), // e( expression )
  LOG           (FunctionCategory.LOGARITHMIC,   NUMBER,       FLOAT,     1), // log( expression )
  LOG10         (FunctionCategory.LOGARITHMIC,   NUMBER,       FLOAT,     1), // log10( expression )
  SQRT          (FunctionCategory.LOGARITHMIC,   NUMBER,       FLOAT,     1), // sqrt( expression )

  ABS           (FunctionCategory.NUMERIC,       NUMBER,       NUMBER,    1), // abs( expression )

  CEIL          (FunctionCategory.NUMERIC,       NUMBER,       INTEGER,   1), // ceil( expression )
  FLOOR         (FunctionCategory.NUMERIC,       NUMBER,       INTEGER,   1), // floor( expression )
  ROUND         (FunctionCategory.NUMERIC,       NUMBER,       INTEGER,   1), // round( expression )
  SIGN          (FunctionCategory.NUMERIC,       NUMBER,       INTEGER,   1), // sign( expression )

  RAND          (FunctionCategory.NUMERIC,       NONE,         FLOAT,     0), // rand()

  EXISTS        (FunctionCategory.PREDICATE,     ANY,          BOOLEAN,   1),

  COALESCE      (FunctionCategory.SCALAR,        ANY,          ANY,       1), // coalesce( expression [, expression]* )
  STARTNODE     (FunctionCategory.SCALAR,        RELATIONSHIP, NODE,      1), // startNode( relationship )
  ENDNODE       (FunctionCategory.SCALAR,        RELATIONSHIP, NODE,      1), // endNode( relationship )
  HEAD          (FunctionCategory.SCALAR,        LIST_TYPE,    ANY,       1), // head( expression )
  LAST          (FunctionCategory.SCALAR,        LIST_TYPE,    ANY,       1), // last( expression )
  LENGTH        (FunctionCategory.SCALAR,        ANY,          INTEGER,   1), // length( path ), length( string )
  PROPERTIES    (FunctionCategory.SCALAR,        ELEMENT,      MAP,       1), // properties( expression ) -- "If the argument is a node or a relationship, the returned map is a map of its properties .If the argument is already a map, it is returned unchanged."
  SIZE          (FunctionCategory.SCALAR,        LIST_TYPE,    INTEGER,   1), // size( list ), size( pattern expression )
  TYPE          (FunctionCategory.SCALAR,        RELATIONSHIP, STRING,    1), // type( relationship )

  LEFT          (FunctionCategory.STRING,        STRING,       STRING,    2), // left( original, length )
  RIGHT         (FunctionCategory.STRING,        STRING,       STRING,    2), // right( original, length )
  LTRIM         (FunctionCategory.STRING,        STRING,       STRING,    1), // ltrim( original )
  RTRIM         (FunctionCategory.STRING,        STRING,       STRING,    1), // rtrim( original )
  TRIM          (FunctionCategory.STRING,        STRING,       STRING,    1), // trim( original )
  REPLACE       (FunctionCategory.STRING,        STRING,       STRING,    3), // replace( original, search, replace )
  REVERSE       (FunctionCategory.STRING,        STRING,       STRING,    1), // reverse( original )
  SUBSTRING     (FunctionCategory.STRING,        ANY,          STRING,    2), // substring( original, start [, length] )
  TOLOWER       (FunctionCategory.STRING,        STRING,       STRING,    1), // lower( original )
  TOUPPER       (FunctionCategory.STRING,        STRING,       STRING,    1), // upper( original )

  STARTS_WITH   (FunctionCategory.STRING,        STRING,       BOOLEAN,   2), // STARTS_WITH(string, prefixString)
  ENDS_WITH     (FunctionCategory.STRING,        STRING,       BOOLEAN,   2), // ENDS_WITH(string, postfixString)
  CONTAINS      (FunctionCategory.STRING,        STRING,       BOOLEAN,   2), // CONTAINS(string, middleString)

  SPLIT         (FunctionCategory.STRING,        STRING,       LIST_TYPE, 1), // split( original, splitPattern )

  ACOS          (FunctionCategory.TRIGONOMETRIC, NUMBER,       FLOAT,     1), // acos( expression )
  ASIN          (FunctionCategory.TRIGONOMETRIC, NUMBER,       FLOAT,     1), // asin( expression )
  ATAN          (FunctionCategory.TRIGONOMETRIC, NUMBER,       FLOAT,     1), // atan( expression )
  ATAN2         (FunctionCategory.TRIGONOMETRIC, NUMBER,       FLOAT,     2), // atan2( expression1, expression2 )
  COS           (FunctionCategory.TRIGONOMETRIC, NUMBER,       FLOAT,     1), // cos( expression )
  COT           (FunctionCategory.TRIGONOMETRIC, NUMBER,       FLOAT,     1), // cot( expression )
  SIN           (FunctionCategory.TRIGONOMETRIC, NUMBER,       FLOAT,     1), // sin( expression )
  TAN           (FunctionCategory.TRIGONOMETRIC, NUMBER,       FLOAT,     1), // tan( expression )

  DEGREES       (FunctionCategory.TRIGONOMETRIC, NUMBER,       FLOAT,     1), // degrees( expression )
  RADIANS       (FunctionCategory.TRIGONOMETRIC, NUMBER,       FLOAT,     1), // radians( expression )

  PI            (FunctionCategory.TRIGONOMETRIC, NONE,         FLOAT,     0), // pi()
;

  private final FunctionCategory category;
  private final CypherType inputType;
  private final CypherType outputType;
  private final int minimumArity;

  Function(final FunctionCategory category, final CypherType inputType, final CypherType outputType, final int minimumArity) {
    this.category = category;
    this.inputType = inputType;
    this.outputType = outputType;
    this.minimumArity = minimumArity;
  }

  public FunctionCategory getCategory() {
    return this.category;
  }

  public CypherType getInputType() {
	return inputType;
  }

  public CypherType getOutputType() {
    return outputType;
  }

  public int getMinimumArity() {
    return minimumArity;
  }

}
