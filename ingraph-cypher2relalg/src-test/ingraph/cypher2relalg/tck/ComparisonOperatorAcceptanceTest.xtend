package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class ComparisonOperatorAcceptanceTest {
    
    /*
    Scenario: Handling numerical ranges 1
    Given an empty graph
    And having executed:
      """
      UNWIND [1, 2, 3] AS i
      CREATE ({value: i})
      """
    */
    @Test
    def void testComparisonOperatorAcceptance_01() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE 1 < n.value < 3
        RETURN n.value
        ''')
    }

    /*
    Scenario: Handling numerical ranges 2
    Given an empty graph
    And having executed:
      """
      UNWIND [1, 2, 3] AS i
      CREATE ({value: i})
      """
    */
    @Test
    def void testComparisonOperatorAcceptance_02() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE 1 < n.value <= 3
        RETURN n.value
        ''')
    }

    /*
    Scenario: Handling numerical ranges 3
    Given an empty graph
    And having executed:
      """
      UNWIND [1, 2, 3] AS i
      CREATE ({value: i})
      """
    */
    @Test
    def void testComparisonOperatorAcceptance_03() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE 1 <= n.value < 3
        RETURN n.value
        ''')
    }

    /*
    Scenario: Handling numerical ranges 4
    Given an empty graph
    And having executed:
      """
      UNWIND [1, 2, 3] AS i
      CREATE ({value: i})
      """
    */
    @Test
    def void testComparisonOperatorAcceptance_04() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE 1 <= n.value <= 3
        RETURN n.value
        ''')
    }

    /*
    Scenario: Handling string ranges 1
    Given an empty graph
    And having executed:
      """
      UNWIND ['a', 'b', 'c'] AS c
      CREATE ({value: c})
      """
    */
    @Test
    def void testComparisonOperatorAcceptance_05() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE 'a' < n.value < 'c'
        RETURN n.value
        ''')
    }

    /*
    Scenario: Handling string ranges 2
    Given an empty graph
    And having executed:
      """
      UNWIND ['a', 'b', 'c'] AS c
      CREATE ({value: c})
      """
    */
    @Test
    def void testComparisonOperatorAcceptance_06() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE 'a' < n.value <= 'c'
        RETURN n.value
        ''')
    }

    /*
    Scenario: Handling string ranges 3
    Given an empty graph
    And having executed:
      """
      UNWIND ['a', 'b', 'c'] AS c
      CREATE ({value: c})
      """
    */
    @Test
    def void testComparisonOperatorAcceptance_07() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE 'a' <= n.value < 'c'
        RETURN n.value
        ''')
    }

    /*
    Scenario: Handling string ranges 4
    Given an empty graph
    And having executed:
      """
      UNWIND ['a', 'b', 'c'] AS c
      CREATE ({value: c})
      """
    */
    @Test
    def void testComparisonOperatorAcceptance_08() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE 'a' <= n.value <= 'c'
        RETURN n.value
        ''')
    }

    /*
    Scenario: Handling empty range
    Given an empty graph
    And having executed:
      """
      CREATE ({value: 3})
      """
    */
    @Test
    def void testComparisonOperatorAcceptance_09() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE 10 < n.value <= 3
        RETURN n.value
        ''')
    }

    /*
    Scenario: Handling long chains of operators
    Given an empty graph
    And having executed:
      """
      CREATE (a:A {prop1: 3, prop2: 4})
      CREATE (b:B {prop1: 4, prop2: 5})
      CREATE (c:C {prop1: 4, prop2: 4})
      CREATE (a)-[:R]->(b)
      CREATE (b)-[:R]->(c)
      CREATE (c)-[:R]->(a)
      """
    */
    @Test
    def void testComparisonOperatorAcceptance_10() {
        RelalgParser.parse('''
        MATCH (n)-->(m)
        WHERE n.prop1 < m.prop1 = n.prop2 <> m.prop2
        RETURN labels(m)
        ''')
    }

}
