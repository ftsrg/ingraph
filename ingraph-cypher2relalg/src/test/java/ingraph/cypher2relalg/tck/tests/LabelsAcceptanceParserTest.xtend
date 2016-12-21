package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class LabelsAcceptanceParserTest {
    
    /*
    Scenario: Using `labels()` in return clauses
    And having executed:
      """
      CREATE ()
      """
    */
    @Test
    @Category(FailingTests)
    def void testLabelsAcceptance_10() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN labels(n)
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/LabelsAcceptance_10")
        Cypher2Relalg.processCypher(cypher)
    }

}
