package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class UnwindAcceptanceTest {
    
    /*
    Scenario: Unwinding a list
    Given any graph
    */
    @Test
    def void testUnwindAcceptance_01() {
        RelalgParser.parse('''
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
        RelalgParser.parse('''
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
        RelalgParser.parse('''
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
        RelalgParser.parse('''
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
        RelalgParser.parse('''
        MATCH (row)
        WITH collect(row) AS rows
        UNWIND rows AS node
        RETURN node.id
        ''')
    }

    /*
    Scenario: Creating nodes from an unwound parameter list
    Given an empty graph
    And having executed:
      """
      CREATE (:Year {year: 2016})
      """
    And parameters are:
      | events | [{year: 2016, id: 1}, {year: 2016, id: 2}] |
    */
    @Test
    def void testUnwindAcceptance_06() {
        RelalgParser.parse('''
        UNWIND $events AS event
        MATCH (y:Year {year: event.year})
        MERGE (e:Event {id: event.id})
        MERGE (y)<-[:IN]-(e)
        RETURN e.id AS x
        ORDER BY x
        ''')
    }

    /*
    Scenario: Double unwinding a list of lists
    Given any graph
    */
    @Test
    def void testUnwindAcceptance_07() {
        RelalgParser.parse('''
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
        RelalgParser.parse('''
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
        RelalgParser.parse('''
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
        RelalgParser.parse('''
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
        RelalgParser.parse('''
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
        RelalgParser.parse('''
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
        RelalgParser.parse('''
        WITH [1, 2] AS xs, [3, 4] AS ys, [5, 6] AS zs
        UNWIND xs AS x
        UNWIND ys AS y
        UNWIND zs AS z
        RETURN *
        ''')
    }

}
