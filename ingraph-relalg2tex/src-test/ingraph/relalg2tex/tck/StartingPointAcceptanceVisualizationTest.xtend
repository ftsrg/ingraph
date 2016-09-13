package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.AlgebraTreeDrawer

class StartingPointAcceptanceVisualizationTest {

    val static AlgebraTreeDrawer drawer = new AlgebraTreeDrawer(true)
    
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
        val container = RelalgParser.parse('''
        MATCH (n)
        RETURN n
        ''')
        drawer.serialize(container, "StartingPointAcceptance")
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
        val container = RelalgParser.parse('''
        MATCH (n:Animal)
        RETURN n
        ''')
        drawer.serialize(container, "StartingPointAcceptance")
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
        val container = RelalgParser.parse('''
        MATCH (n)
        WHERE n.prop = 2
        RETURN n
        ''')
        drawer.serialize(container, "StartingPointAcceptance")
    }

}
