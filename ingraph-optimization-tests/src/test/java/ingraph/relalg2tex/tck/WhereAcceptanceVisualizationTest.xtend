package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.inferencers.SchemaInferencer
import ingraph.relalg2tex.serializers.RelalgTreeSerializer

class WhereAcceptanceVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer
    extension SchemaInferencer inferencer = new SchemaInferencer
    
    /*
    Scenario: NOT and false
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'a'})
      """
    */
    @Test
    def void testWhereAcceptance_01() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)
        WHERE NOT(n.name = 'apa' AND false)
        RETURN n
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/WhereAcceptance_01")
    }

    /*
    Scenario: Fail when trying to compare strings and numbers
    Given an empty graph
    And having executed:
      """
      CREATE (:Label {prop: '15'})
      """
    */
    @Test
    def void testWhereAcceptance_02() {
        val container = Cypher2Relalg.processString('''
        MATCH (n:Label)
        WHERE n.prop < 10
        RETURN n.prop AS prop
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/WhereAcceptance_02")
    }

}
