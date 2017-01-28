package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.inferencers.BasicSchemaInferencer
import ingraph.relalg2tex.serializers.RelalgTreeSerializer

class LabelsAcceptanceVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer
    extension BasicSchemaInferencer inferencer = new BasicSchemaInferencer
    
    /*
    Scenario: Using `labels()` in return clauses
    And having executed:
      """
      CREATE ()
      """
    */
    @Test
    def void testLabelsAcceptance_10() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)
        RETURN labels(n)
        ''')
        container.inferBasicSchema
        serializer.serialize(container, "tck/LabelsAcceptance_10")
    }

}
