package ingraph.cypher2.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

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
        RelalgParser.parse('''
        MATCH (n)
        RETURN labels(n)
        ''')
    }

}
