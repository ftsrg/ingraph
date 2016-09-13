package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.AlgebraTreeDrawer

class ReturnAcceptanceVisualizationTest {

    val static AlgebraTreeDrawer drawer = new AlgebraTreeDrawer(true)
    
    /*
    Scenario: Allow addition
    Given an empty graph
    And having executed:
      """
      CREATE ({id: 1337, version: 99})
      """
    */
    @Test
    def void testReturnAcceptance_01() {
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE a.id = 1337
        RETURN a.version + 5
        ''')
        drawer.serialize(container, "ReturnAcceptance")
    }

    /*
    Scenario: Limit to two hits
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'A'}),
        ({name: 'B'}),
        ({name: 'C'}),
        ({name: 'D'}),
        ({name: 'E'})
      """
    */
    @Test
    def void testReturnAcceptance_02() {
        val container = RelalgParser.parse('''
        MATCH (n)
        RETURN n
        LIMIT 2
        ''')
        drawer.serialize(container, "ReturnAcceptance")
    }

    /*
    Scenario: Start the result from the second row
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'A'}),
        ({name: 'B'}),
        ({name: 'C'}),
        ({name: 'D'}),
        ({name: 'E'})
      """
    */
    @Test
    def void testReturnAcceptance_03() {
        val container = RelalgParser.parse('''
        MATCH (n)
        RETURN n
        ORDER BY n.name ASC
        SKIP 2
        ''')
        drawer.serialize(container, "ReturnAcceptance")
    }

    /*
    Scenario: Start the result from the second row by param
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'A'}),
        ({name: 'B'}),
        ({name: 'C'}),
        ({name: 'D'}),
        ({name: 'E'})
      """
    And parameters are:
      | skipAmount | 2 |
    */
    @Test
    def void testReturnAcceptance_04() {
        val container = RelalgParser.parse('''
        MATCH (n)
        RETURN n
        ORDER BY n.name ASC
        SKIP $skipAmount
        ''')
        drawer.serialize(container, "ReturnAcceptance")
    }

    /*
    Scenario: Get rows in the middle
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'A'}),
        ({name: 'B'}),
        ({name: 'C'}),
        ({name: 'D'}),
        ({name: 'E'})
      """
    */
    @Test
    def void testReturnAcceptance_05() {
        val container = RelalgParser.parse('''
        MATCH (n)
        RETURN n
        ORDER BY n.name ASC
        SKIP 2
        LIMIT 2
        ''')
        drawer.serialize(container, "ReturnAcceptance")
    }

    /*
    Scenario: Get rows in the middle by param
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'A'}),
        ({name: 'B'}),
        ({name: 'C'}),
        ({name: 'D'}),
        ({name: 'E'})
      """
    And parameters are:
      | s | 2 |
      | l | 2 |
    */
    @Test
    def void testReturnAcceptance_06() {
        val container = RelalgParser.parse('''
        MATCH (n)
        RETURN n
        ORDER BY n.name ASC
        SKIP $s
        LIMIT $l
        ''')
        drawer.serialize(container, "ReturnAcceptance")
    }

    /*
    Scenario: Sort on aggregated function
    Given an empty graph
    And having executed:
      """
      CREATE ({division: 'A', age: 22}),
        ({division: 'B', age: 33}),
        ({division: 'B', age: 44}),
        ({division: 'C', age: 55})
      """
    */
    @Test
    def void testReturnAcceptance_07() {
        val container = RelalgParser.parse('''
        MATCH (n)
        RETURN n.division, max(n.age)
        ORDER BY max(n.age)
        ''')
        drawer.serialize(container, "ReturnAcceptance")
    }

    /*
    Scenario: Support sort and distinct
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'A'}),
        ({name: 'B'}),
        ({name: 'C'})
      """
    */
    @Test
    def void testReturnAcceptance_08() {
        val container = RelalgParser.parse('''
        MATCH (a)
        RETURN DISTINCT a
        ORDER BY a.name
        ''')
        drawer.serialize(container, "ReturnAcceptance")
    }

    /*
    Scenario: Support column renaming
    Given an empty graph
    And having executed:
      """
      CREATE (:Singleton)
      """
    */
    @Test
    def void testReturnAcceptance_09() {
        val container = RelalgParser.parse('''
        MATCH (a)
        RETURN a AS ColumnName
        ''')
        drawer.serialize(container, "ReturnAcceptance")
    }

    /*
    Scenario: Support ordering by a property after being distinct-ified
    Given an empty graph
    And having executed:
      """
      CREATE (:A)-[:T]->(:B)
      """
    */
    @Test
    def void testReturnAcceptance_10() {
        val container = RelalgParser.parse('''
        MATCH (a)-->(b)
        RETURN DISTINCT b
        ORDER BY b.name
        ''')
        drawer.serialize(container, "ReturnAcceptance")
    }

    /*
    Scenario: Arithmetic precedence test
    Given any graph
    */
    @Test
    def void testReturnAcceptance_11() {
        val container = RelalgParser.parse('''
        RETURN 12 / 4 * 3 - 2 * 4
        ''')
        drawer.serialize(container, "ReturnAcceptance")
    }

    /*
    Scenario: Arithmetic precedence with parenthesis test
    Given any graph
    */
    @Test
    def void testReturnAcceptance_12() {
        val container = RelalgParser.parse('''
        RETURN 12 / 4 * (3 - 2 * 4)
        ''')
        drawer.serialize(container, "ReturnAcceptance")
    }

    /*
    Scenario: Count star should count everything in scope
    Given an empty graph
    And having executed:
      """
      CREATE (:L1), (:L2), (:L3)
      """
    */
    @Test
    def void testReturnAcceptance_13() {
        val container = RelalgParser.parse('''
        MATCH (a)
        RETURN a, count(*)
        ORDER BY count(*)
        ''')
        drawer.serialize(container, "ReturnAcceptance")
    }

    /*
    Scenario: Absolute function
    Given any graph
    */
    @Test
    def void testReturnAcceptance_14() {
        val container = RelalgParser.parse('''
        RETURN abs(-1)
        ''')
        drawer.serialize(container, "ReturnAcceptance")
    }

    /*
    Scenario: Return collection size
    Given any graph
    */
    @Test
    def void testReturnAcceptance_15() {
        val container = RelalgParser.parse('''
        RETURN size([1, 2, 3]) AS n
        ''')
        drawer.serialize(container, "ReturnAcceptance")
    }

}
