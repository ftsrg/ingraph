package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil

class JoinAcceptanceParserTest {
    
    /*
    Scenario: Find friends of others
    Given an empty graph
    And having executed:
      """
      CREATE (:A {id: 1}),
             (:A {id: 2}),
             (:B {id: 2}),
             (:B {id: 3})
      """
    */
    @Test
    def void testJoinAcceptance_01() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A), (b:B)
        WHERE a.id = b.id
        RETURN a, b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/JoinAcceptance_01")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Should only join when matching
    Given an empty graph
    And having executed:
      """
      UNWIND range(0, 1000) AS i
      CREATE (:A {id: i})
      MERGE (:B {id: i % 10})
      """
    */
    @Test
    def void testJoinAcceptance_02() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A), (b:B)
        WHERE a.id = b.id
        RETURN a, b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/JoinAcceptance_02")
        Cypher2Relalg.processCypher(cypher)
    }

}
