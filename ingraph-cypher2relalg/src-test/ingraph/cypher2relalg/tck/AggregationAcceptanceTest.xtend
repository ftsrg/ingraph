package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class AggregationAcceptanceTest {
    
    @Test
    def void testAggregationAcceptance_01() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN count(n) / 60 / 60 AS count
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_02() {
        RelalgParser.parse('''
        MATCH ()
        RETURN count(*) AS columnName
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_03() {
        RelalgParser.parse('''
        MATCH (a)
        RETURN size(collect(a))
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_04() {
        RelalgParser.parse('''
        MATCH (a {name: 'Andres'})<-[:FATHER]-(child)
        RETURN {foo: a.name='Andres', kids: collect(child.name)}
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_05() {
        RelalgParser.parse('''
        MATCH (a:L)-[rel]->(b)
        RETURN a, count(*)
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_06() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n.division, count(*)
        ORDER BY count(*) DESC, n.division ASC
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_07() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n.x, count(*)
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_08() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n.y, count(n.x)
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_09() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n.y, sum(n.x)
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_10() {
        RelalgParser.parse('''
        MATCH p=(a:L)-[*]->(b)
        RETURN b, avg(length(p))
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_11() {
        RelalgParser.parse('''
        OPTIONAL MATCH (a)
        RETURN count(DISTINCT a)
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_12() {
        RelalgParser.parse('''
        MATCH (a)
        RETURN count(DISTINCT a.foo)
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_13() {
        RelalgParser.parse('''
        UNWIND [null, null] AS x
        RETURN collect(DISTINCT x) AS c
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_14() {
        RelalgParser.parse('''
        UNWIND [null, 1, null] AS x
        RETURN collect(DISTINCT x) AS c
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_15() {
        RelalgParser.parse('''
        MATCH (a)
        RETURN DISTINCT a.color, count(*)
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_16() {
        RelalgParser.parse('''
        RETURN count(count(*))
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_17() {
        RelalgParser.parse('''
        MATCH ()
        RETURN count(*) * 10 AS c
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_18() {
        RelalgParser.parse('''
        MATCH (a:A), (b:X)
        RETURN count(a) * 10 + count(b) * 5 AS x
        ORDER BY x
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_19() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN count(n), collect(n)
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_20() {
        RelalgParser.parse('''
        MATCH ()
        RETURN count(*)
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_21() {
        RelalgParser.parse('''
        MATCH p = (a)-[*]->(b)
        RETURN collect(nodes(p)) AS paths, length(p) AS l
        ORDER BY l
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_22() {
        RelalgParser.parse('''
        MATCH p = (a:T {name: 'a'})-[:R*]->(other:T)
        WHERE other <> a
        WITH a, other, min(length(p)) AS len
        RETURN a.name AS name, collect(other.name) AS others, len
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_23() {
        RelalgParser.parse('''
        MATCH (a:A), (b:B)
        RETURN coalesce(a.prop, b.prop) AS foo,
        b.prop AS bar,
        {y: count(b)} AS baz
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_24() {
        RelalgParser.parse('''
        UNWIND [42] AS props
        WITH props WHERE props > 32
        WITH DISTINCT props AS p
        MERGE (a:A {prop: p})
        RETURN a.prop AS prop
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_25() {
        RelalgParser.parse('''
        UNWIND range(1000000, 2000000) AS i
        WITH i
        LIMIT 3000
        RETURN sum(i)
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_26() {
        RelalgParser.parse('''
        MATCH ()-[r]-()
        RETURN count(r)
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_27() {
        RelalgParser.parse('''
        UNWIND ['a', 'b', 'B', null, 'abc', 'abc1'] AS i
        RETURN max(i)
        ''')
    }
        
    @Test
    def void testAggregationAcceptance_28() {
        RelalgParser.parse('''
        UNWIND ['a', 'b', 'B', null, 'abc', 'abc1'] AS i
        RETURN min(i)
        ''')
    }
        
}
    