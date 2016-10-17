package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.CypherParser

class UnwindAcceptanceParserTest {
    
    /*
    Scenario: Unwinding a list
    Given any graph
    */
    @Test
    def void testUnwindAcceptance_01() {
        CypherParser.parseString('''
        UNWIND [1, 2, 3] AS x
        RETURN x
        ''')
    }

    /*
    Scenario: Unwinding a range
    Given any graph
    */
    @Test
    def void testUnwindAcceptance_02() {
        CypherParser.parseString('''
        UNWIND range(1, 3) AS x
        RETURN x
        ''')
    }

    /*
    Scenario: Unwinding a concatenation of lists
    Given any graph
    */
    @Test
    def void testUnwindAcceptance_03() {
        CypherParser.parseString('''
        WITH [1, 2, 3] AS first, [4, 5, 6] AS second
        UNWIND (first + second) AS x
        RETURN x
        ''')
    }

    /*
    Scenario: Unwinding a collected unwound expression
    Given any graph
    */
    @Test
    def void testUnwindAcceptance_04() {
        CypherParser.parseString('''
        UNWIND RANGE(1, 2) AS row
        WITH collect(row) AS rows
        UNWIND rows AS x
        RETURN x
        ''')
    }

    /*
    Scenario: Unwinding a collected expression
    Given an empty graph
    And having executed:
      """
      CREATE ({id: 1}), ({id: 2})
      """
    */
    @Test
    def void testUnwindAcceptance_05() {
        CypherParser.parseString('''
        MATCH (row)
        WITH collect(row) AS rows
        UNWIND rows AS node
        RETURN node.id
        ''')
    }

    /*
    Scenario: Double unwinding a list of lists
    Given any graph
    */
    @Test
    def void testUnwindAcceptance_07() {
        CypherParser.parseString('''
        WITH [[1, 2, 3], [4, 5, 6]] AS lol
        UNWIND lol AS x
        UNWIND x AS y
        RETURN y
        ''')
    }

    /*
    Scenario: Unwinding the empty list
    Given any graph
    */
    @Test
    def void testUnwindAcceptance_08() {
        CypherParser.parseString('''
        UNWIND [] AS empty
        RETURN empty
        ''')
    }

    /*
    Scenario: Unwinding null
    Given any graph
    */
    @Test
    def void testUnwindAcceptance_09() {
        CypherParser.parseString('''
        UNWIND null AS nil
        RETURN nil
        ''')
    }

    /*
    Scenario: Unwinding list with duplicates
    Given any graph
    */
    @Test
    def void testUnwindAcceptance_10() {
        CypherParser.parseString('''
        UNWIND [1, 1, 2, 2, 3, 3, 4, 4, 5, 5] AS duplicate
        RETURN duplicate
        ''')
    }

    /*
    Scenario: Unwind does not prune context
    Given any graph
    */
    @Test
    def void testUnwindAcceptance_11() {
        CypherParser.parseString('''
        WITH [1, 2, 3] AS list
        UNWIND list AS x
        RETURN *
        ''')
    }

    /*
    Scenario: Unwind does not remove variables from scope
    Given an empty graph
    And having executed:
      """
      CREATE (s:S),
        (n),
        (e:E),
        (s)-[:X]->(e),
        (s)-[:Y]->(e),
        (n)-[:Y]->(e)
      """
    */
    @Test
    def void testUnwindAcceptance_12() {
        CypherParser.parseString('''
        MATCH (a:S)-[:X]->(b1)
        WITH a, collect(b1) AS bees
        UNWIND bees AS b2
        MATCH (a)-[:Y]->(b2)
        RETURN a, b2
        ''')
    }

    /*
    Scenario: Multiple unwinds after each other
    Given any graph
    */
    @Test
    def void testUnwindAcceptance_13() {
        CypherParser.parseString('''
        WITH [1, 2] AS xs, [3, 4] AS ys, [5, 6] AS zs
        UNWIND xs AS x
        UNWIND ys AS y
        UNWIND zs AS z
        RETURN *
        ''')
    }

}
