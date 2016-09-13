package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.AlgebraTreeDrawer

class WhereAcceptanceVisualizationTest {

    val static AlgebraTreeDrawer drawer = new AlgebraTreeDrawer(true)
    
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
        val container = RelalgParser.parse('''
        MATCH (n)
        WHERE NOT(n.name = 'apa' AND false)
        RETURN n
        ''')
        drawer.serialize(container, "WhereAcceptance")
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
        val container = RelalgParser.parse('''
        MATCH (n:Label)
        WHERE n.prop < 10
        RETURN n.prop AS prop
        ''')
        drawer.serialize(container, "WhereAcceptance")
    }

}
