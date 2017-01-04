package relalg.function;

public enum Function {
  AVG(FunctionCategory.AGGREGATION)
, COLLECT(FunctionCategory.AGGREGATION)
, COUNT(FunctionCategory.AGGREGATION)
, MAX(FunctionCategory.AGGREGATION)
, MIN(FunctionCategory.AGGREGATION)
, SUM(FunctionCategory.AGGREGATION)
, TOBOOLEAN(FunctionCategory.CONVERSION)
, TOINTEGER(FunctionCategory.CONVERSION)
, TOINT(FunctionCategory.CONVERSION)
, TOFLOAT(FunctionCategory.CONVERSION)
, RELATIONSHIPS(FunctionCategory.LIST)
, TAIL(FunctionCategory.LIST)
, KEYS(FunctionCategory.LIST)
, LABELS(FunctionCategory.LIST)
, NODES(FunctionCategory.LIST)
, RANGE(FunctionCategory.LIST)
, E(FunctionCategory.LOGARITHMIC)
, EXP(FunctionCategory.LOGARITHMIC)
, LOG(FunctionCategory.LOGARITHMIC)
, LOG10(FunctionCategory.LOGARITHMIC)
, SQRT(FunctionCategory.LOGARITHMIC)
, ABS(FunctionCategory.NUMERIC)
, CEIL(FunctionCategory.NUMERIC)
, FLOOR(FunctionCategory.NUMERIC)
, RAND(FunctionCategory.NUMERIC)
, ROUND(FunctionCategory.NUMERIC)
, SIGN(FunctionCategory.NUMERIC)
, EXISTS(FunctionCategory.PREDICATE)
, COALESCE(FunctionCategory.SCALAR)
, ENDNODE(FunctionCategory.SCALAR)
, HEAD(FunctionCategory.SCALAR)
, LENGTH(FunctionCategory.SCALAR)
, LAST(FunctionCategory.SCALAR)
, PROPERTIES(FunctionCategory.SCALAR)
, SIZE(FunctionCategory.SCALAR)
, STARTNODE(FunctionCategory.SCALAR)
, TYPE(FunctionCategory.SCALAR)
, STDDEV(FunctionCategory.STATISTICAL)
, STDDEVP(FunctionCategory.STATISTICAL)
, PERCENTILECONT(FunctionCategory.STATISTICAL)
, PERCENTILEDISC(FunctionCategory.STATISTICAL)
, LEFT(FunctionCategory.STRING)
, LTRIM(FunctionCategory.STRING)
, TRIM(FunctionCategory.STRING)
, REPLACE(FunctionCategory.STRING)
, REVERSE(FunctionCategory.STRING)
, RIGHT(FunctionCategory.STRING)
, RTRIM(FunctionCategory.STRING)
, SPLIT(FunctionCategory.STRING)
, SUBSTRING(FunctionCategory.STRING)
, TOLOWER(FunctionCategory.STRING)
, TOSTRING(FunctionCategory.STRING)
, TOUPPER(FunctionCategory.STRING)
, ACOS(FunctionCategory.TRIGONOMETRIC)
, ASIN(FunctionCategory.TRIGONOMETRIC)
, ATAN(FunctionCategory.TRIGONOMETRIC)
, ATAN2(FunctionCategory.TRIGONOMETRIC)
, COS(FunctionCategory.TRIGONOMETRIC)
, COT(FunctionCategory.TRIGONOMETRIC)
, DEGREES(FunctionCategory.TRIGONOMETRIC)
, PI(FunctionCategory.TRIGONOMETRIC)
, RADIANS(FunctionCategory.TRIGONOMETRIC)
, SIN(FunctionCategory.TRIGONOMETRIC)
, TAN(FunctionCategory.TRIGONOMETRIC)
;

  private final FunctionCategory category;

  Function(FunctionCategory category) {
    this.category = category;
  }

  public FunctionCategory getCategory() {
	  return this.category;
  }
}
