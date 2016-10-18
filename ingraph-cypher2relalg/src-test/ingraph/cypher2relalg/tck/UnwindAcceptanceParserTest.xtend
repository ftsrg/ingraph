package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2RelAlg
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil

class UnwindAcceptanceParserTest {
    
    /*
    Scenario: Unwinding a list
    Given any graph
    */
    @Test
    def void testUnwindAcceptance_01() {
        val cypher = CypherParser.parseString('''
        UNWIND [1, 2, 3] AS x
        RETURN x
        ''')
        CypherUtil.save(cypher, "../cypxmi/UnwindAcceptance_01")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Unwinding a range
    Given any graph
    */
    @Test
    def void testUnwindAcceptance_02() {
        val cypher = CypherParser.parseString('''
        UNWIND range(1, 3) AS x
        RETURN x
        ''')
        CypherUtil.save(cypher, "../cypxmi/UnwindAcceptance_02")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Unwinding a concatenation of lists
    Given any graph
    */
    @Test
    def void testUnwindAcceptance_03() {
        val cypher = CypherParser.parseString('''
        WITH [1, 2, 3] AS first, [4, 5, 6] AS second
        UNWIND (first + second) AS x
        RETURN x
        ''')
        CypherUtil.save(cypher, "../cypxmi/UnwindAcceptance_03")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Unwinding a collected unwound expression
    Given any graph
    */
    @Test
    def void testUnwindAcceptance_04() {
        val cypher = CypherParser.parseString('''
        UNWIND RANGE(1, 2) AS row
        WITH collect(row) AS rows
        UNWIND rows AS x
        RETURN x
        ''')
        CypherUtil.save(cypher, "../cypxmi/UnwindAcceptance_04")
        Cypher2RelAlg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH (row)
        WITH collect(row) AS rows
        UNWIND rows AS node
        RETURN node.id
        ''')
        CypherUtil.save(cypher, "../cypxmi/UnwindAcceptance_05")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Double unwinding a list of lists
    Given any graph
    */
    @Test
    def void testUnwindAcceptance_07() {
        val cypher = CypherParser.parseString('''
        WITH [[1, 2, 3], [4, 5, 6]] AS lol
        UNWIND lol AS x
        UNWIND x AS y
        RETURN y
        ''')
        CypherUtil.save(cypher, "../cypxmi/UnwindAcceptance_07")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Unwinding the empty list
    Given any graph
    */
    @Test
    def void testUnwindAcceptance_08() {
        val cypher = CypherParser.parseString('''
        UNWIND [] AS empty
        RETURN empty
        ''')
        CypherUtil.save(cypher, "../cypxmi/UnwindAcceptance_08")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Unwinding null
    Given any graph
    */
    @Test
    def void testUnwindAcceptance_09() {
        val cypher = CypherParser.parseString('''
        UNWIND null AS nil
        RETURN nil
        ''')
        CypherUtil.save(cypher, "../cypxmi/UnwindAcceptance_09")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Unwinding list with duplicates
    Given any graph
    */
    @Test
    def void testUnwindAcceptance_10() {
        val cypher = CypherParser.parseString('''
        UNWIND [1, 1, 2, 2, 3, 3, 4, 4, 5, 5] AS duplicate
        RETURN duplicate
        ''')
        CypherUtil.save(cypher, "../cypxmi/UnwindAcceptance_10")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Unwind does not prune context
    Given any graph
    */
    @Test
    def void testUnwindAcceptance_11() {
        val cypher = CypherParser.parseString('''
        WITH [1, 2, 3] AS list
        UNWIND list AS x
        RETURN *
        ''')
        CypherUtil.save(cypher, "../cypxmi/UnwindAcceptance_11")
        Cypher2RelAlg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH (a:S)-[:X]->(b1)
        WITH a, collect(b1) AS bees
        UNWIND bees AS b2
        MATCH (a)-[:Y]->(b2)
        RETURN a, b2
        ''')
        CypherUtil.save(cypher, "../cypxmi/UnwindAcceptance_12")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Multiple unwinds after each other
    Given any graph
    */
    @Test
    def void testUnwindAcceptance_13() {
        val cypher = CypherParser.parseString('''
        WITH [1, 2] AS xs, [3, 4] AS ys, [5, 6] AS zs
        UNWIND xs AS x
        UNWIND ys AS y
        UNWIND zs AS z
        RETURN *
        ''')
        CypherUtil.save(cypher, "../cypxmi/UnwindAcceptance_13")
        Cypher2RelAlg.processCypher(cypher)
    }

}
