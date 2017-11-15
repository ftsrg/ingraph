package ingraph.model.misc;

import java.util.Arrays;
import java.util.List;

public enum Function {
  //             name              category                        output type             ma input types

  // aggregation functions
  AVG           ("avg",            FunctionCategory.AGGREGATION,   CypherType.NUMBER,      1, l(CypherType.NUMBER)                   ),
  COLLECT       ("collect",        FunctionCategory.AGGREGATION,   CypherType.LIST_TYPE,   1, l(CypherType.NUMBER)                   ),
  COUNT         ("count",          FunctionCategory.AGGREGATION,   CypherType.NUMBER,      1, l(CypherType.NUMBER)                   ),
  COUNT_ALL     ("count(*)",       FunctionCategory.AGGREGATION,   CypherType.NUMBER,      0, l(CypherType.NUMBER)                   ), // count(*)
  MAX           ("max",            FunctionCategory.AGGREGATION,   CypherType.NUMBER,      1, l(CypherType.NUMBER)                   ),
  MIN           ("min",            FunctionCategory.AGGREGATION,   CypherType.NUMBER,      1, l(CypherType.NUMBER)                   ),
  SUM           ("sum",            FunctionCategory.AGGREGATION,   CypherType.NUMBER,      1, l(CypherType.NUMBER)                   ),

  STDDEV        ("stddev",         FunctionCategory.AGGREGATION,   CypherType.NUMBER,      1, l(CypherType.NUMBER)                   ),
  STDDEVP       ("stddevp",        FunctionCategory.AGGREGATION,   CypherType.NUMBER,      1, l(CypherType.NUMBER)                   ),
  PERCENTILECONT("percentileCont", FunctionCategory.AGGREGATION,   CypherType.NUMBER,      2, l(CypherType.NUMBER, CypherType.FLOAT)            ),
  PERCENTILEDISC("percentileDisc", FunctionCategory.AGGREGATION,   CypherType.NUMBER,      2, l(CypherType.NUMBER, CypherType.FLOAT)            ),

  // metafunctions
  // these are functions that require the engine to propgate some information from the get*nodes, e.g. labels of a node
  LABELS        ("labels",         FunctionCategory.META,          CypherType.LIST_TYPE,   1, l(CypherType.NODE)                     ), // labels( node )
  KEYS          ("keys",           FunctionCategory.META,          CypherType.LIST_TYPE,   1, l(CypherType.ELEMENT)                  ), // keys( property-container )
  PROPERTIES    ("properties",     FunctionCategory.META,          CypherType.MAP,         1, l(CypherType.ELEMENT)                  ), // properties( expression ) -- "If the argument is a node or a relationship, the returned map is a map of its properties .If the argument is already a map, it is returned unchanged."
  RELATIONSHIPS ("relationships",  FunctionCategory.META,          CypherType.LIST_TYPE,   1, l(CypherType.PATH)                     ), // relationships( path )
  TYPE          ("type",           FunctionCategory.META,          CypherType.STRING,      1, l(CypherType.RELATIONSHIP)             ), // type( relationship )
  STARTNODE     ("startNode",      FunctionCategory.META,          CypherType.NODE,        1, l(CypherType.RELATIONSHIP)             ), // startNode( relationship )
  ENDNODE       ("endNode",        FunctionCategory.META,          CypherType.NODE,        1, l(CypherType.RELATIONSHIP)             ), // endNode( relationship )

  // list functions
  TAIL          ("tail",           FunctionCategory.LIST,          CypherType.LIST_TYPE,   1, l(CypherType.LIST_TYPE)                ), // tail( expression )
  NODES         ("nodes",          FunctionCategory.LIST,          CypherType.LIST_TYPE,   1, l(CypherType.PATH)                     ), // nodes( path )
  RANGE         ("range",          FunctionCategory.LIST,          CypherType.LIST_TYPE,   1, l(CypherType.INTEGER, CypherType.INTEGER, CypherType.INTEGER)), // range( start, end [, step] )

  // conversion functions
  TOBOOLEAN     ("toBoolean",      FunctionCategory.CONVERSION,    CypherType.BOOLEAN,     1, l(CypherType.ANY)                      ), // toBoolean( expression )
  TOINTEGER     ("toInt",          FunctionCategory.CONVERSION,    CypherType.INTEGER,     1, l(CypherType.ANY)                      ), // toInteger( expression )
  TOINT         ("toInt",          FunctionCategory.CONVERSION,    CypherType.INTEGER,     1, l(CypherType.ANY)                      ), // toInt( expression )
  TOFLOAT       ("toFloat",        FunctionCategory.CONVERSION,    CypherType.FLOAT,       1, l(CypherType.ANY)                      ), // toFloat( expression )
  TOSTRING      ("toString",       FunctionCategory.CONVERSION,    CypherType.STRING,      1, l(CypherType.ANY)                      ), // toString( expression )

  // predicate functions
  EXISTS        ("exists",         FunctionCategory.PREDICATE,     CypherType.BOOLEAN,     1, l(CypherType.ANY)                      ),
  IN_COLLECTION ("in_collection",  FunctionCategory.PREDICATE,     CypherType.BOOLEAN,     2, l(CypherType.ANY, CypherType.LIST_TYPE)           ),

  // scalar functions
  HEAD          ("head",           FunctionCategory.SCALAR,        CypherType.ANY,         1, l(CypherType.LIST_TYPE)                ), // head( expression )
  LAST          ("last",           FunctionCategory.SCALAR,        CypherType.ANY,         1, l(CypherType.LIST_TYPE)                ), // last( expression )
  SIZE          ("size",           FunctionCategory.SCALAR,        CypherType.INTEGER,     1, l(CypherType.LIST_TYPE)                ), // size( list ), size( pattern expression )
  LENGTH        ("length",         FunctionCategory.SCALAR,        CypherType.INTEGER,     1, l(CypherType.ANY)                      ), // length( path ), length( string )
  COALESCE      ("coalesce",       FunctionCategory.SCALAR,        CypherType.ANY,         1, l(CypherType.ANY)                      ), // coalesce( expression [, expression]* )
  ID            ("id",             FunctionCategory.SCALAR,        CypherType.INTEGER,     1, l(CypherType.ELEMENT)                  ), // id( property-container )

  // string functions
  LEFT          ("left",           FunctionCategory.STRING,        CypherType.STRING,      2, l(CypherType.STRING, CypherType.INTEGER)           ), // left( original, length )
  RIGHT         ("right",          FunctionCategory.STRING,        CypherType.STRING,      2, l(CypherType.STRING, CypherType.INTEGER)          ), // right( original, length )
  LTRIM         ("ltrim",          FunctionCategory.STRING,        CypherType.STRING,      1, l(CypherType.STRING)                   ), // ltrim( original )
  RTRIM         ("rtrim",          FunctionCategory.STRING,        CypherType.STRING,      1, l(CypherType.STRING)                   ), // rtrim( original )
  TRIM          ("trim",           FunctionCategory.STRING,        CypherType.STRING,      1, l(CypherType.STRING)                   ), // trim( original )
  REPLACE       ("replace",        FunctionCategory.STRING,        CypherType.STRING,      3, l(CypherType.STRING, CypherType.STRING, CypherType.STRING)   ), // replace( original, search, replace )
  REGEX_LIKE    ("regexLike",      FunctionCategory.STRING,        CypherType.BOOLEAN,     2, l(CypherType.STRING, CypherType.STRING)           ), // string =~ pattern
  REVERSE       ("reverse",        FunctionCategory.STRING,        CypherType.STRING,      1, l(CypherType.STRING)                   ), // reverse( original )
  SUBSTRING     ("substring",      FunctionCategory.STRING,        CypherType.STRING,      2, l(CypherType.STRING, CypherType.STRING, CypherType.INTEGER)  ), // substring( original, start [, length] )
  TOLOWER       ("toLower",        FunctionCategory.STRING,        CypherType.STRING,      1, l(CypherType.STRING)                   ), // lower( original )
  TOUPPER       ("toUpper",        FunctionCategory.STRING,        CypherType.STRING,      1, l(CypherType.STRING)                   ), // upper( original )
  STARTS_WITH   ("startsWith",     FunctionCategory.STRING,        CypherType.BOOLEAN,     2, l(CypherType.STRING, CypherType.STRING)           ), // STARTS_WITH(string, prefixString)
  ENDS_WITH     ("endsWith",       FunctionCategory.STRING,        CypherType.BOOLEAN,     2, l(CypherType.STRING, CypherType.STRING)           ), // ENDS_WITH(string, postfixString)
  CONTAINS      ("contains",       FunctionCategory.STRING,        CypherType.BOOLEAN,     2, l(CypherType.STRING, CypherType.STRING)           ), // CONTAINS(string, middleString)
  SPLIT         ("split",          FunctionCategory.STRING,        CypherType.LIST_TYPE,   2, l(CypherType.STRING, CypherType.STRING)           ), // split( original, splitPattern )

  // logarithmic functions
  E             ("e",              FunctionCategory.LOGARITHMIC,   CypherType.FLOAT,       0, l(CypherType.NONE)                     ), // e()
  EXP           ("e",              FunctionCategory.LOGARITHMIC,   CypherType.FLOAT,       1, l(CypherType.NUMBER)                   ), // e( expression )
  LOG           ("log",            FunctionCategory.LOGARITHMIC,   CypherType.FLOAT,       1, l(CypherType.NUMBER)                   ), // log( expression )
  LOG10         ("log10",          FunctionCategory.LOGARITHMIC,   CypherType.FLOAT,       1, l(CypherType.NUMBER)                   ), // log10( expression )
  POW           ("pow",            FunctionCategory.LOGARITHMIC,   CypherType.FLOAT,       2, l(CypherType.NUMBER, CypherType.NUMBER)           ), // pow( expression )
  SQRT          ("sqrt",           FunctionCategory.LOGARITHMIC,   CypherType.FLOAT,       1, l(CypherType.NUMBER)                   ), // sqrt( expression )

  // numeric functions
  ABS           ("abs",            FunctionCategory.NUMERIC,       CypherType.NUMBER,      1, l(CypherType.NUMBER)                   ), // abs( expression )
  CEIL          ("ceil",           FunctionCategory.NUMERIC,       CypherType.INTEGER,     1, l(CypherType.NUMBER)                   ), // ceil( expression )
  FLOOR         ("floor",          FunctionCategory.NUMERIC,       CypherType.INTEGER,     1, l(CypherType.NUMBER)                   ), // floor( expression )
  ROUND         ("round",          FunctionCategory.NUMERIC,       CypherType.INTEGER,     1, l(CypherType.NUMBER)                   ), // round( expression )
  SIGN          ("sign",           FunctionCategory.NUMERIC,       CypherType.INTEGER,     1, l(CypherType.NUMBER)                   ), // sign( expression )
  RAND          ("rand",           FunctionCategory.NUMERIC,       CypherType.FLOAT,       0, l(CypherType.NONE)                     ), // rand()

  // trigonometric functions
  ACOS          ("acos",           FunctionCategory.TRIGONOMETRIC, CypherType.FLOAT,       1, l(CypherType.NUMBER)                   ), // acos( expression )
  ASIN          ("asin",           FunctionCategory.TRIGONOMETRIC, CypherType.FLOAT,       1, l(CypherType.NUMBER)                   ), // asin( expression )
  ATAN          ("atan",           FunctionCategory.TRIGONOMETRIC, CypherType.FLOAT,       1, l(CypherType.NUMBER)                   ), // atan( expression )
  ATAN2         ("atan2",          FunctionCategory.TRIGONOMETRIC, CypherType.FLOAT,       2, l(CypherType.NUMBER, CypherType.NUMBER)           ), // atan2( expression1, expression2 )
  COS           ("cos",            FunctionCategory.TRIGONOMETRIC, CypherType.FLOAT,       1, l(CypherType.NUMBER)                   ), // cos( expression )
  COT           ("cot",            FunctionCategory.TRIGONOMETRIC, CypherType.FLOAT,       1, l(CypherType.NUMBER)                   ), // cot( expression )
  SIN           ("sin",            FunctionCategory.TRIGONOMETRIC, CypherType.FLOAT,       1, l(CypherType.NUMBER)                   ), // sin( expression )
  TAN           ("tan",            FunctionCategory.TRIGONOMETRIC, CypherType.FLOAT,       1, l(CypherType.NUMBER)                   ), // tan( expression )
  DEGREES       ("degrees",        FunctionCategory.TRIGONOMETRIC, CypherType.FLOAT,       1, l(CypherType.NUMBER)                   ), // degrees( expression )
  RADIANS       ("radians",        FunctionCategory.TRIGONOMETRIC, CypherType.FLOAT,       1, l(CypherType.NUMBER)                   ), // radians( expression )
  PI            ("pi",             FunctionCategory.TRIGONOMETRIC, CypherType.FLOAT,       0, l(CypherType.NONE)                     ), // pi()
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

  public String getPrettyName() { return prettyName; }

  @Deprecated()
  public static Function getByPrettyName(String prettyName) {
    return Function.valueOf(prettyName.toUpperCase());
  }

	/**
	 * Resolves a function name to a Function enum instance.
	 * @param name the openCypher name of the function to resolve
	 * @return the Function enum instance
	 */
	public static Function fromCypherName(String name) {
		Function functor = null;
		// exceptions handled here
		if (COUNT_ALL.getPrettyName().equalsIgnoreCase(name)) {
			functor = COUNT_ALL;
		} else {
			// fall back to the convention as enum elements are named after functions' openCypher name
			functor = Function.valueOf(name.toUpperCase());
		}
		return functor;
	}

	/**
	 * Resolves a function name to a Function enum instance and does some basic
	 * consistency checks between the functor, its arity and whther distinct is allowed.
	 * @param name the openCypher name of the function to resolve
	 * @param arity the arity used in the actual call
	 * @param isDistinct is the DISTINCT modifier used in the actual call?
	 * @return the Function enum instance
	 */
	public static Function fromCypherName(String name, int arity, boolean isDistinct) {
    Function functor = fromCypherName(name);

    if (isDistinct && ! functor.isAggregation()) {
      throw new RuntimeException("DISTINCT modifier not allowed in non-aggregating function call "+functor.getPrettyName()+"/"+arity);
    }

    if (arity < functor.getMinimumArity()) {
    	throw new RuntimeException("Call to function "+functor.getPrettyName()+"/"+arity+" inconsistent with required minimum arity of "+functor.getMinimumArity());
		}

    return functor;
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
	return outputType == CypherType.NUMBER
	    || outputType == CypherType.INTEGER
	    || outputType == CypherType.FLOAT
	    || outputType == CypherType.ANY
	    ;
  }
}
