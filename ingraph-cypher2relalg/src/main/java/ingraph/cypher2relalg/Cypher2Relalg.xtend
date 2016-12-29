package ingraph.cypher2relalg

import ingraph.cypherparser.CypherParser
import org.slizaa.neo4j.opencypher.openCypher.Cypher
import relalg.RelalgContainer

class Cypher2Relalg {

  def static RelalgContainer processFile(String queryFile) {
    val cypher = CypherParser.parseFile(queryFile)
    return processCypher(cypher)
  }

  def static RelalgContainer processString(String queryString) {
    val cypher = CypherParser.parseString(queryString)
    return processCypher(cypher)
  }

  def static RelalgContainer processCypher(Cypher cypher) {
    val builder = new RelalgBuilder()
    return builder.build(cypher)
  }

}
