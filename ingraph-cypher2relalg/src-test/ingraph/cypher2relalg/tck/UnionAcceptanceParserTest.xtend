package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.CypherParser

class UnionAcceptanceParserTest {
    
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
        CypherParser.parseString('''
        MATCH (a:A)
        RETURN a AS a
        UNION
        MATCH (b:B)
        RETURN b AS a
        ''')
    }

    /*
    Scenario: Two elements, both unique, not distinct
    Given an empty graph
    */
    @Test
    def void testUnionAcceptance_02() {
        CypherParser.parseString('''
        RETURN 1 AS x
        UNION ALL
        RETURN 2 AS x
        ''')
    }

    /*
    Scenario: Two elements, both unique, distinct
    Given an empty graph
    */
    @Test
    def void testUnionAcceptance_03() {
        CypherParser.parseString('''
        RETURN 1 AS x
        UNION
        RETURN 2 AS x
        ''')
    }

    /*
    Scenario: Three elements, two unique, distinct
    Given an empty graph
    */
    @Test
    def void testUnionAcceptance_04() {
        CypherParser.parseString('''
        RETURN 2 AS x
        UNION
        RETURN 1 AS x
        UNION
        RETURN 2 AS x
        ''')
    }

    /*
    Scenario: Three elements, two unique, not distinct
    Given an empty graph
    */
    @Test
    def void testUnionAcceptance_05() {
        CypherParser.parseString('''
        RETURN 2 AS x
        UNION ALL
        RETURN 1 AS x
        UNION ALL
        RETURN 2 AS x
        ''')
    }

}
