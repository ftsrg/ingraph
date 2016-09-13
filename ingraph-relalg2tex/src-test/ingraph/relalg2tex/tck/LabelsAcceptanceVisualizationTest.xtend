package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.AlgebraTreeDrawer

class LabelsAcceptanceVisualizationTest {

    val static AlgebraTreeDrawer drawer = new AlgebraTreeDrawer(true)
    
    /*
    Scenario: Using `labels()` in return clauses
    And having executed:
      """
      CREATE ()
      """
    */
    @Test
    def void testLabelsAcceptance_10() {
        val container = RelalgParser.parse('''
        MATCH (n)
        RETURN labels(n)
        ''')
        drawer.serialize(container, "LabelsAcceptance")
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
        val container = RelalgParser.parse('''
        MATCH (n)
        REMOVE n:Foo
        RETURN labels(n)
        ''')
        drawer.serialize(container, "LabelsAcceptance")
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
        val container = RelalgParser.parse('''
        MATCH (n)
        REMOVE n:Bar
        RETURN labels(n)
        ''')
        drawer.serialize(container, "LabelsAcceptance")
    }

}
