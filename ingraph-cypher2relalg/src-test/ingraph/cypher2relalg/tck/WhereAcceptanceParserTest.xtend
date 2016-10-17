package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.CypherParser

class WhereAcceptanceParserTest {
    
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
        CypherParser.parseString('''
        MATCH (n)
        WHERE NOT(n.name = 'apa' AND false)
        RETURN n
        ''')
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
        CypherParser.parseString('''
        MATCH (n:Label)
        WHERE n.prop < 10
        RETURN n.prop AS prop
        ''')
    }

}
