package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class ReturnAcceptanceTest {
    
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
        RelalgParser.parse('''
        MATCH (a)
        WHERE a.id = 1337
        RETURN a.version + 5
        ''')
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
        RelalgParser.parse('''
        MATCH (n)
        RETURN n
        LIMIT 2
        ''')
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
        RelalgParser.parse('''
        MATCH (n)
        RETURN n
        ORDER BY n.name ASC
        SKIP 2
        ''')
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
        RelalgParser.parse('''
        MATCH (n)
        RETURN n
        ORDER BY n.name ASC
        SKIP $skipAmount
        ''')
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
        RelalgParser.parse('''
        MATCH (n)
        RETURN n
        ORDER BY n.name ASC
        SKIP 2
        LIMIT 2
        ''')
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
        RelalgParser.parse('''
        MATCH (n)
        RETURN n
        ORDER BY n.name ASC
        SKIP $s
        LIMIT $l
        ''')
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
        RelalgParser.parse('''
        MATCH (n)
        RETURN n.division, max(n.age)
        ORDER BY max(n.age)
        ''')
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
        RelalgParser.parse('''
        MATCH (a)
        RETURN DISTINCT a
        ORDER BY a.name
        ''')
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
        RelalgParser.parse('''
        MATCH (a)
        RETURN a AS ColumnName
        ''')
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
        RelalgParser.parse('''
        MATCH (a)-->(b)
        RETURN DISTINCT b
        ORDER BY b.name
        ''')
    }
        
    /*
    Scenario: Arithmetic precedence test
    Given any graph
    */
    @Test
    def void testReturnAcceptance_11() {
        RelalgParser.parse('''
        RETURN 12 / 4 * 3 - 2 * 4
        ''')
    }
        
    /*
    Scenario: Arithmetic precedence with parenthesis test
    Given any graph
    */
    @Test
    def void testReturnAcceptance_12() {
        RelalgParser.parse('''
        RETURN 12 / 4 * (3 - 2 * 4)
        ''')
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
        RelalgParser.parse('''
        MATCH (a)
        RETURN a, count(*)
        ORDER BY count(*)
        ''')
    }
        
    /*
    Scenario: Absolute function
    Given any graph
    */
    @Test
    def void testReturnAcceptance_14() {
        RelalgParser.parse('''
        RETURN abs(-1)
        ''')
    }
        
    /*
    Scenario: Return collection size
    Given any graph
    */
    @Test
    def void testReturnAcceptance_15() {
        RelalgParser.parse('''
        RETURN size([1, 2, 3]) AS n
        ''')
    }
        
}
    