package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.inferencers.BasicSchemaInferencer
import ingraph.relalg2tex.serializers.RelalgTreeSerializer

class StartingPointAcceptanceVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer
    extension BasicSchemaInferencer inferencer = new BasicSchemaInferencer
    
    /*
    Scenario: Find all nodes
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'a'}),
             ({name: 'b'}),
             ({name: 'c'})
      """
    */
    @Test
    def void testStartingPointAcceptance_01() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)
        RETURN n
        ''')
        container.inferBasicSchema
        serializer.serialize(container, "tck/StartingPointAcceptance_01")
    }

    /*
    Scenario: Find labelled nodes
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'a'}),
             (:Person),
             (:Animal),
             (:Animal)
      """
    */
    @Test
    def void testStartingPointAcceptance_02() {
        val container = Cypher2Relalg.processString('''
        MATCH (n:Animal)
        RETURN n
        ''')
        container.inferBasicSchema
        serializer.serialize(container, "tck/StartingPointAcceptance_02")
    }

    /*
    Scenario: Find nodes by property
    Given an empty graph
    And having executed:
      """
      CREATE ({prop: 1}),
             ({prop: 2})
      """
    */
    @Test
    def void testStartingPointAcceptance_03() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)
        WHERE n.prop = 2
        RETURN n
        ''')
        container.inferBasicSchema
        serializer.serialize(container, "tck/StartingPointAcceptance_03")
    }

}
