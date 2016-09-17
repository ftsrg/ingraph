package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.RelalgTreeSerializer

class UnionAcceptanceVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer(true)
    
    /*
    Scenario: Should be able to create text output from union queries
    Given an empty graph
    And having executed:
      """
      CREATE (:A), (:B)
      """
    */
    @Test
    def void testUnionAcceptance_01() {
        val container = RelalgParser.parse('''
        MATCH (a:A)
        RETURN a AS a
        UNION
        MATCH (b:B)
        RETURN b AS a
        ''')
        serializer.serialize(container, "UnionAcceptance_01")
    }

    /*
    Scenario: Two elements, both unique, not distinct
    Given an empty graph
    */
    @Test
    def void testUnionAcceptance_02() {
        val container = RelalgParser.parse('''
        RETURN 1 AS x
        UNION ALL
        RETURN 2 AS x
        ''')
        serializer.serialize(container, "UnionAcceptance_02")
    }

    /*
    Scenario: Two elements, both unique, distinct
    Given an empty graph
    */
    @Test
    def void testUnionAcceptance_03() {
        val container = RelalgParser.parse('''
        RETURN 1 AS x
        UNION
        RETURN 2 AS x
        ''')
        serializer.serialize(container, "UnionAcceptance_03")
    }

    /*
    Scenario: Three elements, two unique, distinct
    Given an empty graph
    */
    @Test
    def void testUnionAcceptance_04() {
        val container = RelalgParser.parse('''
        RETURN 2 AS x
        UNION
        RETURN 1 AS x
        UNION
        RETURN 2 AS x
        ''')
        serializer.serialize(container, "UnionAcceptance_04")
    }

    /*
    Scenario: Three elements, two unique, not distinct
    Given an empty graph
    */
    @Test
    def void testUnionAcceptance_05() {
        val container = RelalgParser.parse('''
        RETURN 2 AS x
        UNION ALL
        RETURN 1 AS x
        UNION ALL
        RETURN 2 AS x
        ''')
        serializer.serialize(container, "UnionAcceptance_05")
    }

}
