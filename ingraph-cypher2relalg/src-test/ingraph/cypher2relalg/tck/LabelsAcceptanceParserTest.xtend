package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2RelAlg
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil

class LabelsAcceptanceParserTest {
    
    /*
    Scenario: Using `labels()` in return clauses
    And having executed:
      """
      CREATE ()
      """
    */
    @Test
    def void testLabelsAcceptance_10() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN labels(n)
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/LabelsAcceptance_10")
        Cypher2RelAlg.processCypher(cypher)
    }

}
