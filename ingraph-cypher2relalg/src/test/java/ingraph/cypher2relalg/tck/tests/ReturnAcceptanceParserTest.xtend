package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class ReturnAcceptanceParserTest {
    
    /*
    Scenario: Allow addition
    Given an empty graph
    And having executed:
      """
      CREATE ({id: 1337, version: 99})
      """
    */
    @Test
    @Category(FailingTests)
    def void testReturnAcceptance_01() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.id = 1337
        RETURN a.version + 5
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance_01")
        Cypher2Relalg.processCypher(cypher)
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
    @Category(FailingTests)
    def void testReturnAcceptance_02() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n
        LIMIT 2
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance_02")
        Cypher2Relalg.processCypher(cypher)
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
    @Category(FailingTests)
    def void testReturnAcceptance_03() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n
        ORDER BY n.name ASC
        SKIP 2
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance_03")
        Cypher2Relalg.processCypher(cypher)
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
    @Category(FailingTests)
    def void testReturnAcceptance_04() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n
        ORDER BY n.name ASC
        SKIP $skipAmount
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance_04")
        Cypher2Relalg.processCypher(cypher)
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
    @Category(FailingTests)
    def void testReturnAcceptance_05() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n
        ORDER BY n.name ASC
        SKIP 2
        LIMIT 2
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance_05")
        Cypher2Relalg.processCypher(cypher)
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
    @Category(FailingTests)
    def void testReturnAcceptance_06() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n
        ORDER BY n.name ASC
        SKIP $s
        LIMIT $l
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance_06")
        Cypher2Relalg.processCypher(cypher)
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
    @Category(FailingTests)
    def void testReturnAcceptance_07() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n.division, max(n.age)
        ORDER BY max(n.age)
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance_07")
        Cypher2Relalg.processCypher(cypher)
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
    @Category(FailingTests)
    def void testReturnAcceptance_08() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        RETURN DISTINCT a
        ORDER BY a.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance_08")
        Cypher2Relalg.processCypher(cypher)
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
    @Category(FailingTests)
    def void testReturnAcceptance_09() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        RETURN a AS ColumnName
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance_09")
        Cypher2Relalg.processCypher(cypher)
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
    @Category(FailingTests)
    def void testReturnAcceptance_10() {
        val cypher = CypherParser.parseString('''
        MATCH (a)-->(b)
        RETURN DISTINCT b
        ORDER BY b.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance_10")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Arithmetic precedence test
    Given any graph
    */
    @Test
    @Category(FailingTests)
    def void testReturnAcceptance_11() {
        val cypher = CypherParser.parseString('''
        RETURN 12 / 4 * 3 - 2 * 4
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance_11")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Arithmetic precedence with parenthesis test
    Given any graph
    */
    @Test
    @Category(FailingTests)
    def void testReturnAcceptance_12() {
        val cypher = CypherParser.parseString('''
        RETURN 12 / 4 * (3 - 2 * 4)
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance_12")
        Cypher2Relalg.processCypher(cypher)
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
    @Category(FailingTests)
    def void testReturnAcceptance_13() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        RETURN a, count(*)
        ORDER BY count(*)
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance_13")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Absolute function
    Given any graph
    */
    @Test
    @Category(FailingTests)
    def void testReturnAcceptance_14() {
        val cypher = CypherParser.parseString('''
        RETURN abs(-1)
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance_14")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Return collection size
    Given any graph
    */
    @Test
    @Category(FailingTests)
    def void testReturnAcceptance_15() {
        val cypher = CypherParser.parseString('''
        RETURN size([1, 2, 3]) AS n
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance_15")
        Cypher2Relalg.processCypher(cypher)
    }

}
