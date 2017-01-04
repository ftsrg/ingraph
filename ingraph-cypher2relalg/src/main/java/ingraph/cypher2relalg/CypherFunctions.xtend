package ingraph.cypher2relalg

class CypherFunctions {
 
  public static val aggregation = #["avg", "collect", "count", "max", "min", "sum"].map[toLowerCase]
  public static val converter = #["toBoolean", "toInteger", "toInt", "toFloat"].map[toLowerCase]
  public static val list = #["relationships", "tail", "keys", "labels", "nodes", "range"].map[toLowerCase]
  public static val logarithmic = #["e", "exp", "log", "log10", "sqrt"].map[toLowerCase]
  public static val numeric = #["abs", "ceil", "floor", "rand", "round", "sign"].map[toLowerCase]
  public static val predicate = #["exists"].map[toLowerCase]
  public static val scalar = #["coalesce", "endNode", "head", "length", "last", "properties", "size", "startNode", "type", "toFloat"].map[toLowerCase]
  public static val statistical = #["stdDev", "stdDevP", "percentileCont", "percentileDisc"].map[toLowerCase]
  public static val string = #["left", "lTrim", "trim", "replace", "reverse", "right", "rTrim", "split", "substring", "toLower", "toString", "toUpper"].map[toLowerCase]
  public static val trigonometric = #["acos", "asin", "atan", "atan2", "cos", "cot", "degrees", "pi", "radians", "sin", "tan"].map[toLowerCase]

}
