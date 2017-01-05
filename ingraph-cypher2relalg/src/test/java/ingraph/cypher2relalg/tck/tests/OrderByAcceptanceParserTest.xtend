package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class OrderByAcceptanceParserTest {
    
    /*
    Scenario: ORDER BY should return results in ascending order
    And having executed:
      """
      CREATE (n1 {prop: 1}),
        (n2 {prop: 3}),
        (n3 {prop: -5})
      """
    */
    @Test
    @Category(RegressionTests)
    def void testOrderByAcceptance_01() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n.prop AS prop
        ORDER BY n.prop
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OrderByAcceptance_01")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: ORDER BY DESC should return results in descending order
    And having executed:
      """
      CREATE (n1 {prop: 1}),
        (n2 {prop: 3}),
        (n3 {prop: -5})
      """
    */
    @Test
    @Category(RegressionTests)
    def void testOrderByAcceptance_02() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n.prop AS prop
        ORDER BY n.prop DESC
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OrderByAcceptance_02")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: ORDER BY of a column introduced in RETURN should return salient results in ascending order
    */
    @Test
    @Category(FailingTests)
    def void testOrderByAcceptance_03() {
        val cypher = CypherParser.parseString('''
        WITH [0, 1] AS prows, [[2], [3, 4]] AS qrows
        UNWIND prows AS p
        UNWIND qrows[p] AS q
        WITH p, count(q) AS rng
        RETURN p
        ORDER BY rng
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OrderByAcceptance_03")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Renaming columns before ORDER BY should return results in ascending order
    And having executed:
      """
      CREATE (n1 {prop: 1}),
        (n2 {prop: 3}),
        (n3 {prop: -5})
      """
    */
    @Test
    @Category(FailingTests)
    def void testOrderByAcceptance_04() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n.prop AS n
        ORDER BY n + 2
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OrderByAcceptance_04")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handle projections with ORDER BY - GH#4937
    And having executed:
      """
      CREATE (c1:Crew {name: 'Neo', rank: 1}),
        (c2:Crew {name: 'Neo', rank: 2}),
        (c3:Crew {name: 'Neo', rank: 3}),
        (c4:Crew {name: 'Neo', rank: 4}),
        (c5:Crew {name: 'Neo', rank: 5})
      """
    */
    @Test
    @Category(FailingTests)
    def void testOrderByAcceptance_05() {
        val cypher = CypherParser.parseString('''
        MATCH (c:Crew {name: 'Neo'})
        WITH c, 0 AS relevance
        RETURN c.rank AS rank
        ORDER BY relevance, c.rank
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OrderByAcceptance_05")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: ORDER BY should order booleans in the expected order
    */
    @Test
    @Category(FailingTests)
    def void testOrderByAcceptance_06() {
        val cypher = CypherParser.parseString('''
        UNWIND [true, false] AS bools
        RETURN bools
        ORDER BY bools
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OrderByAcceptance_06")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: ORDER BY DESC should order booleans in the expected order
    */
    @Test
    @Category(FailingTests)
    def void testOrderByAcceptance_07() {
        val cypher = CypherParser.parseString('''
        UNWIND [true, false] AS bools
        RETURN bools
        ORDER BY bools DESC
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OrderByAcceptance_07")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: ORDER BY should order strings in the expected order
    */
    @Test
    @Category(FailingTests)
    def void testOrderByAcceptance_08() {
        val cypher = CypherParser.parseString('''
        UNWIND ['.*', '', ' ', 'one'] AS strings
        RETURN strings
        ORDER BY strings
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OrderByAcceptance_08")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: ORDER BY DESC should order strings in the expected order
    */
    @Test
    @Category(FailingTests)
    def void testOrderByAcceptance_09() {
        val cypher = CypherParser.parseString('''
        UNWIND ['.*', '', ' ', 'one'] AS strings
        RETURN strings
        ORDER BY strings DESC
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OrderByAcceptance_09")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: ORDER BY should order ints in the expected order
    */
    @Test
    @Category(FailingTests)
    def void testOrderByAcceptance_10() {
        val cypher = CypherParser.parseString('''
        UNWIND [1, 3, 2] AS ints
        RETURN ints
        ORDER BY ints
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OrderByAcceptance_10")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: ORDER BY DESC should order ints in the expected order
    */
    @Test
    @Category(FailingTests)
    def void testOrderByAcceptance_11() {
        val cypher = CypherParser.parseString('''
        UNWIND [1, 3, 2] AS ints
        RETURN ints
        ORDER BY ints DESC
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OrderByAcceptance_11")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: ORDER BY should order floats in the expected order
    */
    @Test
    @Category(FailingTests)
    def void testOrderByAcceptance_12() {
        val cypher = CypherParser.parseString('''
        UNWIND [1.5, 1.3, 999.99] AS floats
        RETURN floats
        ORDER BY floats
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OrderByAcceptance_12")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: ORDER BY DESC should order floats in the expected order
    */
    @Test
    @Category(FailingTests)
    def void testOrderByAcceptance_13() {
        val cypher = CypherParser.parseString('''
        UNWIND [1.5, 1.3, 999.99] AS floats
        RETURN floats
        ORDER BY floats DESC
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OrderByAcceptance_13")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handle ORDER BY with LIMIT 1
    And having executed:
      """
      CREATE (s:Person {name: 'Steven'}),
        (c:Person {name: 'Craig'})
      """
    */
    @Test
    @Category(RegressionTests)
    def void testOrderByAcceptance_14() {
        val cypher = CypherParser.parseString('''
        MATCH (p:Person)
        RETURN p.name AS name
        ORDER BY p.name
        LIMIT 1
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OrderByAcceptance_14")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: ORDER BY with LIMIT 0 should not generate errors
    */
    @Test
    @Category(RegressionTests)
    def void testOrderByAcceptance_15() {
        val cypher = CypherParser.parseString('''
        MATCH (p:Person)
        RETURN p.name AS name
        ORDER BY p.name
        LIMIT 0
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OrderByAcceptance_15")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: ORDER BY with negative parameter for LIMIT should not generate errors
    And parameters are:
      | limit | -1 |
    */
    @Test
    @Category(FailingTests)
    def void testOrderByAcceptance_16() {
        val cypher = CypherParser.parseString('''
        MATCH (p:Person)
        RETURN p.name AS name
        ORDER BY p.name
        LIMIT $limit
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OrderByAcceptance_16")
        Cypher2Relalg.processCypher(cypher)
    }

}
