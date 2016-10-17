package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.CypherParser

class StartingPointAcceptanceParserTest {
    
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
        CypherParser.parseString('''
        MATCH (n)
        RETURN n
        ''')
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
        CypherParser.parseString('''
        MATCH (n:Animal)
        RETURN n
        ''')
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
        CypherParser.parseString('''
        MATCH (n)
        WHERE n.prop = 2
        RETURN n
        ''')
    }

}
