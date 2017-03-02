package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import ingraph.relalg.util.RelalgUtil
import org.junit.Test
import org.junit.experimental.categories.Category

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
    @Category(FailingTests)
    def void testAggregationAcceptance_01() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN count(n) / 60 / 60 AS count
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_01")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_01")
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
    @Category(FailingTests)
    def void testAggregationAcceptance_02() {
        val cypher = CypherParser.parseString('''
        MATCH ()
        RETURN count(*) AS columnName
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_02")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_02")
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
    @Category(FailingTests)
    def void testAggregationAcceptance_03() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        RETURN size(collect(a))
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_03")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_03")
    }

    /*
    Scenario: Handle aggregates inside non-aggregate expressions
    Given an empty graph
    */
    @Test
    @Category(FailingTests)
    def void testAggregationAcceptance_04() {
        val cypher = CypherParser.parseString('''
        MATCH (a {name: 'Andres'})<-[:FATHER]-(child)
        RETURN {foo: a.name='Andres', kids: collect(child.name)}
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_04")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_04")
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
    @Category(FailingTests)
    def void testAggregationAcceptance_05() {
        val cypher = CypherParser.parseString('''
        MATCH (a:L)-[rel]->(b)
        RETURN a, count(*)
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_05")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_05")
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
    @Category(FailingTests)
    def void testAggregationAcceptance_06() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n.division, count(*)
        ORDER BY count(*) DESC, n.division ASC
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_06")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_06")
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
    @Category(FailingTests)
    def void testAggregationAcceptance_07() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n.x, count(*)
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_07")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_07")
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
    @Category(FailingTests)
    def void testAggregationAcceptance_08() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n.y, count(n.x)
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_08")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_08")
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
    @Category(FailingTests)
    def void testAggregationAcceptance_09() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n.y, sum(n.x)
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_09")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_09")
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
    @Category(FailingTests)
    def void testAggregationAcceptance_10() {
        val cypher = CypherParser.parseString('''
        MATCH p=(a:L)-[*]->(b)
        RETURN b, avg(length(p))
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_10")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_10")
    }

    /*
    Scenario: Distinct on unbound node
    Given an empty graph
    */
    @Test
    @Category(FailingTests)
    def void testAggregationAcceptance_11() {
        val cypher = CypherParser.parseString('''
        OPTIONAL MATCH (a)
        RETURN count(DISTINCT a)
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_11")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_11")
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
    @Category(FailingTests)
    def void testAggregationAcceptance_12() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        RETURN count(DISTINCT a.foo)
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_12")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_12")
    }

    /*
    Scenario: Collect distinct nulls
    Given any graph
    */
    @Test
    @Category(FailingTests)
    def void testAggregationAcceptance_13() {
        val cypher = CypherParser.parseString('''
        UNWIND [null, null] AS x
        RETURN collect(DISTINCT x) AS c
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_13")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_13")
    }

    /*
    Scenario: Collect distinct values mixed with nulls
    Given any graph
    */
    @Test
    @Category(FailingTests)
    def void testAggregationAcceptance_14() {
        val cypher = CypherParser.parseString('''
        UNWIND [null, 1, null] AS x
        RETURN collect(DISTINCT x) AS c
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_14")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_14")
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
    @Category(FailingTests)
    def void testAggregationAcceptance_15() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        RETURN DISTINCT a.color, count(*)
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_15")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_15")
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
    @Category(FailingTests)
    def void testAggregationAcceptance_16() {
        val cypher = CypherParser.parseString('''
        MATCH ()
        RETURN count(*) * 10 AS c
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_16")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_16")
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
    @Category(FailingTests)
    def void testAggregationAcceptance_17() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A), (b:X)
        RETURN count(a) * 10 + count(b) * 5 AS x
        ORDER BY x
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_17")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_17")
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
    @Category(FailingTests)
    def void testAggregationAcceptance_18() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN count(n), collect(n)
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_18")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_18")
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
    @Category(FailingTests)
    def void testAggregationAcceptance_19() {
        val cypher = CypherParser.parseString('''
        MATCH ()
        RETURN count(*)
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_19")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_19")
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
    @Category(FailingTests)
    def void testAggregationAcceptance_20() {
        val cypher = CypherParser.parseString('''
        MATCH p = (a)-[*]->(b)
        RETURN collect(nodes(p)) AS paths, length(p) AS l
        ORDER BY l
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_20")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_20")
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
    @Category(FailingTests)
    def void testAggregationAcceptance_21() {
        val cypher = CypherParser.parseString('''
        MATCH p = (a:T {name: 'a'})-[:R*]->(other:T)
        WHERE other <> a
        WITH a, other, min(length(p)) AS len
        RETURN a.name AS name, collect(other.name) AS others, len
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_21")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_21")
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
    @Category(FailingTests)
    def void testAggregationAcceptance_22() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A), (b:B)
        RETURN coalesce(a.prop, b.prop) AS foo,
        b.prop AS bar,
        {y: count(b)} AS baz
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_22")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_22")
    }

    /*
    Scenario: No overflow during summation
    Given any graph
    */
    @Test
    @Category(FailingTests)
    def void testAggregationAcceptance_24() {
        val cypher = CypherParser.parseString('''
        UNWIND range(1000000, 2000000) AS i
        WITH i
        LIMIT 3000
        RETURN sum(i)
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_24")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_24")
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
    @Category(FailingTests)
    def void testAggregationAcceptance_25() {
        val cypher = CypherParser.parseString('''
        MATCH ()-[r]-()
        RETURN count(r)
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_25")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_25")
    }

    /*
    Scenario: `max()` should aggregate strings
    Given any graph
    */
    @Test
    @Category(FailingTests)
    def void testAggregationAcceptance_26() {
        val cypher = CypherParser.parseString('''
        UNWIND ['a', 'b', 'B', null, 'abc', 'abc1'] AS i
        RETURN max(i)
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_26")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_26")
    }

    /*
    Scenario: `min()` should aggregate strings
    Given any graph
    */
    @Test
    @Category(FailingTests)
    def void testAggregationAcceptance_27() {
        val cypher = CypherParser.parseString('''
        UNWIND ['a', 'b', 'B', null, 'abc', 'abc1'] AS i
        RETURN min(i)
        ''')
        CypherUtil.save(cypher, "cypher-asts/AggregationAcceptance_27")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/AggregationAcceptance_27")
    }

}
