package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.RelalgTreeSerializer

class LabelsAcceptanceVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer
    
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
        serializer.serialize(container, "LabelsAcceptance_10")
    }

}
