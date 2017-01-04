package ingraph.cypher2relalg

class CypherFunctions {
 
  public static val aggregation = #["avg", "collect", "count", "max", "min", "sum"]
  public static val converter = #["toBoolean", "toInteger", "toInt", "toFloat"]
  public static val list = #["relationships", "tail", "keys", "labels", "nodes", "range"]
  public static val logarithmic = #["e", "exp", "log", "log10", "sqrt"]
  public static val numeric = #["abs", "ceil", "floor", "rand", "round", "sign"]
  public static val predicate = #["exists"]
  public static val scalar = #["coalesce", "endNode", "head", "length", "last", "properties", "size", "startNode", "type", "toFloat"]
  public static val statistical = #["stdDev", "stdDevP", "percentileCont", "percentileDisc"]
  public static val string = #["left", "lTrim", "trim", "replace", "reverse", "right", "rTrim", "split", "substring", "toLower", "toString", "toUpper"]
  public static val trigonometric = #["acos", "asin", "atan", "atan2", "cos", "cot", "degrees", "pi", "radians", "sin", "tan"]

}
