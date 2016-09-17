package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.RelalgTreeSerializer

class WhereAcceptanceVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer(true)
    
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
        serializer.serialize(container, "WhereAcceptance_01")
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
        serializer.serialize(container, "WhereAcceptance_02")
    }

}
