package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.CypherParser

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
        CypherParser.parseString('''
        MATCH (n)
        RETURN labels(n)
        ''')
    }

}
