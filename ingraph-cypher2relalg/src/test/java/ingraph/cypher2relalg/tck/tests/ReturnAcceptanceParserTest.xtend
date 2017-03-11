package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import ingraph.relalg.util.RelalgUtil
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
        CypherUtil.save(cypher, "cypher-asts/tck/ReturnAcceptance_01")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/ReturnAcceptance_01")
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
        CypherUtil.save(cypher, "cypher-asts/tck/ReturnAcceptance_02")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/ReturnAcceptance_02")
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
        CypherUtil.save(cypher, "cypher-asts/tck/ReturnAcceptance_03")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/ReturnAcceptance_03")
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
        CypherUtil.save(cypher, "cypher-asts/tck/ReturnAcceptance_04")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/ReturnAcceptance_04")
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
        CypherUtil.save(cypher, "cypher-asts/tck/ReturnAcceptance_05")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/ReturnAcceptance_05")
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
        CypherUtil.save(cypher, "cypher-asts/tck/ReturnAcceptance_06")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/ReturnAcceptance_06")
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
        CypherUtil.save(cypher, "cypher-asts/tck/ReturnAcceptance_07")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/ReturnAcceptance_07")
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
        CypherUtil.save(cypher, "cypher-asts/tck/ReturnAcceptance_08")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/ReturnAcceptance_08")
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
        CypherUtil.save(cypher, "cypher-asts/tck/ReturnAcceptance_09")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/ReturnAcceptance_09")
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
        CypherUtil.save(cypher, "cypher-asts/tck/ReturnAcceptance_10")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/ReturnAcceptance_10")
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
        CypherUtil.save(cypher, "cypher-asts/tck/ReturnAcceptance_11")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/ReturnAcceptance_11")
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
        CypherUtil.save(cypher, "cypher-asts/tck/ReturnAcceptance_12")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/ReturnAcceptance_12")
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
        CypherUtil.save(cypher, "cypher-asts/tck/ReturnAcceptance_13")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/ReturnAcceptance_13")
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
        CypherUtil.save(cypher, "cypher-asts/tck/ReturnAcceptance_14")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/ReturnAcceptance_14")
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
        CypherUtil.save(cypher, "cypher-asts/tck/ReturnAcceptance_15")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/ReturnAcceptance_15")
    }

}
