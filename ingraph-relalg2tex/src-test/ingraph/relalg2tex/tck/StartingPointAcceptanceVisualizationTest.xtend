package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.CypherParser
import ingraph.optimization.transformations.SchemaInferencer
import ingraph.relalg2tex.RelalgTreeSerializer

class StartingPointAcceptanceVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer
    extension SchemaInferencer inferencer = new SchemaInferencer
    
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
        val container = CypherParser.parseString('''
        MATCH (n)
        RETURN n
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "StartingPointAcceptance_01")
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
        val container = CypherParser.parseString('''
        MATCH (n:Animal)
        RETURN n
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "StartingPointAcceptance_02")
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
        val container = CypherParser.parseString('''
        MATCH (n)
        WHERE n.prop = 2
        RETURN n
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "StartingPointAcceptance_03")
    }

}
