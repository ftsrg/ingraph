package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class AggregationAcceptanceTest {
    
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
        RelalgParser.parse('''
        MATCH (n)
        RETURN count(n) / 60 / 60 AS count
        ''')
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
        RelalgParser.parse('''
        MATCH ()
        RETURN count(*) AS columnName
        ''')
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
        RelalgParser.parse('''
        MATCH (a)
        RETURN size(collect(a))
        ''')
    }
        
    /*
    Scenario: Handle aggregates inside non-aggregate expressions
    Given an empty graph
    */
    @Test
    def void testAggregationAcceptance_04() {
        RelalgParser.parse('''
        MATCH (a {name: 'Andres'})<-[:FATHER]-(child)
        RETURN {foo: a.name='Andres', kids: collect(child.name)}
        ''')
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
        RelalgParser.parse('''
        MATCH (a:L)-[rel]->(b)
        RETURN a, count(*)
        ''')
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
        RelalgParser.parse('''
        MATCH (n)
        RETURN n.division, count(*)
        ORDER BY count(*) DESC, n.division ASC
        ''')
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
        RelalgParser.parse('''
        MATCH (n)
        RETURN n.x, count(*)
        ''')
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
        RelalgParser.parse('''
        MATCH (n)
        RETURN n.y, count(n.x)
        ''')
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
        RelalgParser.parse('''
        MATCH (n)
        RETURN n.y, sum(n.x)
        ''')
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
        RelalgParser.parse('''
        MATCH p=(a:L)-[*]->(b)
        RETURN b, avg(length(p))
        ''')
    }
        
    /*
    Scenario: Distinct on unbound node
    Given an empty graph
    */
    @Test
    def void testAggregationAcceptance_11() {
        RelalgParser.parse('''
        OPTIONAL MATCH (a)
        RETURN count(DISTINCT a)
        ''')
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
        RelalgParser.parse('''
        MATCH (a)
        RETURN count(DISTINCT a.foo)
        ''')
    }
        
    /*
    Scenario: Collect distinct nulls
    Given any graph
    */
    @Test
    def void testAggregationAcceptance_13() {
        RelalgParser.parse('''
        UNWIND [null, null] AS x
        RETURN collect(DISTINCT x) AS c
        ''')
    }
        
    /*
    Scenario: Collect distinct values mixed with nulls
    Given any graph
    */
    @Test
    def void testAggregationAcceptance_14() {
        RelalgParser.parse('''
        UNWIND [null, 1, null] AS x
        RETURN collect(DISTINCT x) AS c
        ''')
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
        RelalgParser.parse('''
        MATCH (a)
        RETURN DISTINCT a.color, count(*)
        ''')
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
        RelalgParser.parse('''
        MATCH ()
        RETURN count(*) * 10 AS c
        ''')
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
        RelalgParser.parse('''
        MATCH (a:A), (b:X)
        RETURN count(a) * 10 + count(b) * 5 AS x
        ORDER BY x
        ''')
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
        RelalgParser.parse('''
        MATCH (n)
        RETURN count(n), collect(n)
        ''')
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
        RelalgParser.parse('''
        MATCH ()
        RETURN count(*)
        ''')
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
        RelalgParser.parse('''
        MATCH p = (a)-[*]->(b)
        RETURN collect(nodes(p)) AS paths, length(p) AS l
        ORDER BY l
        ''')
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
        RelalgParser.parse('''
        MATCH p = (a:T {name: 'a'})-[:R*]->(other:T)
        WHERE other <> a
        WITH a, other, min(length(p)) AS len
        RETURN a.name AS name, collect(other.name) AS others, len
        ''')
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
        RelalgParser.parse('''
        MATCH (a:A), (b:B)
        RETURN coalesce(a.prop, b.prop) AS foo,
        b.prop AS bar,
        {y: count(b)} AS baz
        ''')
    }
        
    /*
    Scenario: Projection during aggregation in WITH before MERGE and after WITH with predicate
    Given an empty graph
    And having executed:
      """
      CREATE (:A {prop: 42})
      """
    */
    @Test
    def void testAggregationAcceptance_23() {
        RelalgParser.parse('''
        UNWIND [42] AS props
        WITH props WHERE props > 32
        WITH DISTINCT props AS p
        MERGE (a:A {prop: p})
        RETURN a.prop AS prop
        ''')
    }
        
    /*
    Scenario: No overflow during summation
    Given any graph
    */
    @Test
    def void testAggregationAcceptance_24() {
        RelalgParser.parse('''
        UNWIND range(1000000, 2000000) AS i
        WITH i
        LIMIT 3000
        RETURN sum(i)
        ''')
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
        RelalgParser.parse('''
        MATCH ()-[r]-()
        RETURN count(r)
        ''')
    }
        
    /*
    Scenario: `max()` should aggregate strings
    Given any graph
    */
    @Test
    def void testAggregationAcceptance_26() {
        RelalgParser.parse('''
        UNWIND ['a', 'b', 'B', null, 'abc', 'abc1'] AS i
        RETURN max(i)
        ''')
    }
        
    /*
    Scenario: `min()` should aggregate strings
    Given any graph
    */
    @Test
    def void testAggregationAcceptance_27() {
        RelalgParser.parse('''
        UNWIND ['a', 'b', 'B', null, 'abc', 'abc1'] AS i
        RETURN min(i)
        ''')
    }
        
}
    