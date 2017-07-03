package ingraph.cypher2relalg;

@SuppressWarnings("all")
public class Cypher2RelalgConfig {
  public static boolean isDebugMode() {
    return (("1".equals(System.getenv("DEBUG")) || "1".equals(System.getenv("DEBUG_INGRAPH"))) || "1".equals(System.getenv("DEBUG_INGRAPH_CYPHER2SEARCH")));
  }
}
