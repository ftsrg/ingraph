package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class EqualsAcceptanceParserTest {
    
    /*
    Scenario: Number-typed integer comparison
    Given an empty graph
    And having executed:
      """
      CREATE ({id: 0})
      """
    */
    @Test
    def void testEqualsAcceptance_01() {
        RelalgParser.parse('''
        WITH collect([0, 0.0]) AS numbers
        UNWIND numbers AS arr
        WITH arr[0] AS expected
        MATCH (n) WHERE toInteger(n.id) = expected
        RETURN n
        ''')
    }

    /*
    Scenario: Number-typed float comparison
    Given an empty graph
    And having executed:
      """
      CREATE ({id: 0})
      """
    */
    @Test
    def void testEqualsAcceptance_02() {
        RelalgParser.parse('''
        WITH collect([0.5, 0]) AS numbers
        UNWIND numbers AS arr
        WITH arr[0] AS expected
        MATCH (n) WHERE toInteger(n.id) = expected
        RETURN n
        ''')
    }

    /*
    Scenario: Any-typed string comparison
    Given an empty graph
    And having executed:
      """
      CREATE ({id: 0})
      """
    */
    @Test
    def void testEqualsAcceptance_03() {
        RelalgParser.parse('''
        WITH collect(['0', 0]) AS things
        UNWIND things AS arr
        WITH arr[0] AS expected
        MATCH (n) WHERE toInteger(n.id) = expected
        RETURN n
        ''')
    }

    /*
    Scenario: Comparing nodes to nodes
    Given an empty graph
    And having executed:
      """
      CREATE ()
      """
    */
    @Test
    def void testEqualsAcceptance_04() {
        RelalgParser.parse('''
        MATCH (a)
        WITH a
        MATCH (b)
        WHERE a = b
        RETURN count(b)
        ''')
    }

    /*
    Scenario: Comparing relationships to relationships
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:T]->()
      """
    */
    @Test
    def void testEqualsAcceptance_05() {
        RelalgParser.parse('''
        MATCH ()-[a]->()
        WITH a
        MATCH ()-[b]->()
        WHERE a = b
        RETURN count(b)
        ''')
    }

}
