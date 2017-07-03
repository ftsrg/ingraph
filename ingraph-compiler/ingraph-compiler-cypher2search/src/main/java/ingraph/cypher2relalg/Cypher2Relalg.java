package ingraph.cypher2relalg;

import ingraph.cypher2relalg.RelalgBuilder;
import ingraph.cypherparser.CypherParser;
import org.slizaa.neo4j.opencypher.openCypher.Cypher;
import relalg.RelalgContainer;

@SuppressWarnings("all")
public class Cypher2Relalg {
  public static RelalgContainer processFile(final String queryFile) {
    final Cypher cypher = CypherParser.parseFile(queryFile);
    return Cypher2Relalg.processCypher(cypher, queryFile);
  }
  
  @Deprecated
  public static RelalgContainer processString(final String queryString) {
    return Cypher2Relalg.processString(queryString, null);
  }
  
  public static RelalgContainer processString(final String queryString, final String queryName) {
    final Cypher cypher = CypherParser.parseString(queryString);
    return Cypher2Relalg.processCypher(cypher, queryName);
  }
  
  @Deprecated
  public static RelalgContainer processCypher(final Cypher cypher) {
    return Cypher2Relalg.processCypher(cypher, null);
  }
  
  public static RelalgContainer processCypher(final Cypher cypher, final String queryName) {
    final RelalgBuilder builder = new RelalgBuilder();
    return builder.build(cypher, queryName);
  }
}
