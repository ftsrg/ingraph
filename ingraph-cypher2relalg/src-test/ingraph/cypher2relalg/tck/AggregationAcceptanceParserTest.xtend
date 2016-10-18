package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2RelAlg
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil

class AggregationAcceptanceParserTest {
    
    /*
    Scenario: Support multiple divisions in aggregate function
    Given an empty graph
    And having executed:
      """
      UNWIND range(0, 7250) AS i
      CREATE ()
      """
    */
    @Test
    def void testAggregationAcceptance_01() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN count(n) / 60 / 60 AS count
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_01")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Support column renaming for aggregates as well
    Given an empty graph
    And having executed:
      """
      UNWIND range(0, 10) AS i
      CREATE ()
      """
    */
    @Test
    def void testAggregationAcceptance_02() {
        val cypher = CypherParser.parseString('''
        MATCH ()
        RETURN count(*) AS columnName
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_02")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Aggregates inside normal functions
    Given an empty graph
    And having executed:
      """
      UNWIND range(0, 10) AS i
      CREATE ()
      """
    */
    @Test
    def void testAggregationAcceptance_03() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        RETURN size(collect(a))
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_03")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Handle aggregates inside non-aggregate expressions
    Given an empty graph
    */
    @Test
    def void testAggregationAcceptance_04() {
        val cypher = CypherParser.parseString('''
        MATCH (a {name: 'Andres'})<-[:FATHER]-(child)
        RETURN {foo: a.name='Andres', kids: collect(child.name)}
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_04")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Count nodes
    Given an empty graph
    And having executed:
      """
      CREATE (a:L), (b1), (b2)
      CREATE (a)-[:A]->(b1), (a)-[:A]->(b2)
      """
    */
    @Test
    def void testAggregationAcceptance_05() {
        val cypher = CypherParser.parseString('''
        MATCH (a:L)-[rel]->(b)
        RETURN a, count(*)
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_05")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Sort on aggregate function and normal property
    Given an empty graph
    And having executed:
      """
      CREATE ({division: 'Sweden'})
      CREATE ({division: 'Germany'})
      CREATE ({division: 'England'})
      CREATE ({division: 'Sweden'})
      """
    */
    @Test
    def void testAggregationAcceptance_06() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n.division, count(*)
        ORDER BY count(*) DESC, n.division ASC
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_06")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Aggregate on property
    Given an empty graph
    And having executed:
      """
      CREATE ({x: 33})
      CREATE ({x: 33})
      CREATE ({x: 42})
      """
    */
    @Test
    def void testAggregationAcceptance_07() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n.x, count(*)
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_07")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Count non-null values
    Given an empty graph
    And having executed:
      """
      CREATE ({y: 'a', x: 33})
      CREATE ({y: 'a'})
      CREATE ({y: 'b', x: 42})
      """
    */
    @Test
    def void testAggregationAcceptance_08() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n.y, count(n.x)
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_08")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Sum non-null values
    Given an empty graph
    And having executed:
      """
      CREATE ({y: 'a', x: 33})
      CREATE ({y: 'a'})
      CREATE ({y: 'a', x: 42})
      """
    */
    @Test
    def void testAggregationAcceptance_09() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n.y, sum(n.x)
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_09")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Handle aggregation on functions
    Given an empty graph
    And having executed:
      """
      CREATE (a:L), (b1), (b2)
      CREATE (a)-[:A]->(b1), (a)-[:A]->(b2)
      """
    */
    @Test
    def void testAggregationAcceptance_10() {
        val cypher = CypherParser.parseString('''
        MATCH p=(a:L)-[*]->(b)
        RETURN b, avg(length(p))
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_10")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Distinct on unbound node
    Given an empty graph
    */
    @Test
    def void testAggregationAcceptance_11() {
        val cypher = CypherParser.parseString('''
        OPTIONAL MATCH (a)
        RETURN count(DISTINCT a)
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_11")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Distinct on null
    Given an empty graph
    And having executed:
      """
      CREATE ()
      """
    */
    @Test
    def void testAggregationAcceptance_12() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        RETURN count(DISTINCT a.foo)
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_12")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Collect distinct nulls
    Given any graph
    */
    @Test
    def void testAggregationAcceptance_13() {
        val cypher = CypherParser.parseString('''
        UNWIND [null, null] AS x
        RETURN collect(DISTINCT x) AS c
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_13")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Collect distinct values mixed with nulls
    Given any graph
    */
    @Test
    def void testAggregationAcceptance_14() {
        val cypher = CypherParser.parseString('''
        UNWIND [null, 1, null] AS x
        RETURN collect(DISTINCT x) AS c
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_14")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Aggregate on list values
    Given an empty graph
    And having executed:
      """
      CREATE ({color: ['red']})
      CREATE ({color: ['blue']})
      CREATE ({color: ['red']})
      """
    */
    @Test
    def void testAggregationAcceptance_15() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        RETURN DISTINCT a.color, count(*)
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_15")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Aggregates with arithmetics
    Given an empty graph
    And having executed:
      """
      CREATE ()
      """
    */
    @Test
    def void testAggregationAcceptance_16() {
        val cypher = CypherParser.parseString('''
        MATCH ()
        RETURN count(*) * 10 AS c
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_16")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Aggregates ordered by arithmetics
    Given an empty graph
    And having executed:
      """
      CREATE (:A), (:X), (:X)
      """
    */
    @Test
    def void testAggregationAcceptance_17() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A), (b:X)
        RETURN count(a) * 10 + count(b) * 5 AS x
        ORDER BY x
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_17")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Multiple aggregates on same variable
    Given an empty graph
    And having executed:
      """
      CREATE ()
      """
    */
    @Test
    def void testAggregationAcceptance_18() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN count(n), collect(n)
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_18")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Simple counting of nodes
    Given an empty graph
    And having executed:
      """
      UNWIND range(1, 100) AS i
      CREATE ()
      """
    */
    @Test
    def void testAggregationAcceptance_19() {
        val cypher = CypherParser.parseString('''
        MATCH ()
        RETURN count(*)
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_19")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Aggregation of named paths
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (b:B), (c:C), (d:D), (e:E), (f:F)
      CREATE (a)-[:R]->(b)
      CREATE (c)-[:R]->(d)
      CREATE (d)-[:R]->(e)
      CREATE (e)-[:R]->(f)
      """
    */
    @Test
    def void testAggregationAcceptance_20() {
        val cypher = CypherParser.parseString('''
        MATCH p = (a)-[*]->(b)
        RETURN collect(nodes(p)) AS paths, length(p) AS l
        ORDER BY l
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_20")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Aggregation with `min()`
    Given an empty graph
    And having executed:
      """
      CREATE (a:T {name: 'a'}), (b:T {name: 'b'}), (c:T {name: 'c'})
      CREATE (a)-[:R]->(b)
      CREATE (a)-[:R]->(c)
      CREATE (c)-[:R]->(b)
      """
    */
    @Test
    def void testAggregationAcceptance_21() {
        val cypher = CypherParser.parseString('''
        MATCH p = (a:T {name: 'a'})-[:R*]->(other:T)
        WHERE other <> a
        WITH a, other, min(length(p)) AS len
        RETURN a.name AS name, collect(other.name) AS others, len
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_21")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Handle subexpression in aggregation also occurring as standalone expression with nested aggregation in a literal map
    Given an empty graph
    And having executed:
      """
      CREATE (:A), (:B {prop: 42})
      """
    */
    @Test
    def void testAggregationAcceptance_22() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A), (b:B)
        RETURN coalesce(a.prop, b.prop) AS foo,
        b.prop AS bar,
        {y: count(b)} AS baz
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_22")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: No overflow during summation
    Given any graph
    */
    @Test
    def void testAggregationAcceptance_24() {
        val cypher = CypherParser.parseString('''
        UNWIND range(1000000, 2000000) AS i
        WITH i
        LIMIT 3000
        RETURN sum(i)
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_24")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Counting with loops
    Given an empty graph
    And having executed:
      """
      CREATE (a), (a)-[:R]->(a)
      """
    */
    @Test
    def void testAggregationAcceptance_25() {
        val cypher = CypherParser.parseString('''
        MATCH ()-[r]-()
        RETURN count(r)
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_25")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: `max()` should aggregate strings
    Given any graph
    */
    @Test
    def void testAggregationAcceptance_26() {
        val cypher = CypherParser.parseString('''
        UNWIND ['a', 'b', 'B', null, 'abc', 'abc1'] AS i
        RETURN max(i)
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_26")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: `min()` should aggregate strings
    Given any graph
    */
    @Test
    def void testAggregationAcceptance_27() {
        val cypher = CypherParser.parseString('''
        UNWIND ['a', 'b', 'B', null, 'abc', 'abc1'] AS i
        RETURN min(i)
        ''')
        CypherUtil.save(cypher, "../cypxmi/AggregationAcceptance_27")
        Cypher2RelAlg.processCypher(cypher)
    }

}
