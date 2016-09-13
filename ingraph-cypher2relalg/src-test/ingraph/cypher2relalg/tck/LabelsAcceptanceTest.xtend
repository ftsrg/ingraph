package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class LabelsAcceptanceTest {
    
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

    /*
    Scenario: Removing a label
    And having executed:
      """
      CREATE (:Foo:Bar)
      """
    */
    @Test
    def void testLabelsAcceptance_11() {
        RelalgParser.parse('''
        MATCH (n)
        REMOVE n:Foo
        RETURN labels(n)
        ''')
    }

    /*
    Scenario: Removing a non-existent label
    And having executed:
      """
      CREATE (:Foo)
      """
    */
    @Test
    def void testLabelsAcceptance_12() {
        RelalgParser.parse('''
        MATCH (n)
        REMOVE n:Bar
        RETURN labels(n)
        ''')
    }

}
