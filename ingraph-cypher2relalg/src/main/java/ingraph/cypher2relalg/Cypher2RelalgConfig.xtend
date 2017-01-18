package ingraph.cypher2relalg

class Cypher2RelalgConfig {
  static def isDebugMode() {
    "1".equals(System.getenv("DEBUG"))
    || "1".equals(System.getenv("DEBUG_INGRAPH"))
    || "1".equals(System.getenv("DEBUG_INGRAPH_CYPHER2RELALG"))
  }
}