package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class MiscellaneousErrorAcceptanceTest {
    
    @Test
    def void testMiscellaneousErrorAcceptance_01() {
        RelalgParser.parse('''
        RETURN '\uH'
        ''')
    }
        
    @Test
    def void testMiscellaneousErrorAcceptance_03() {
        RelalgParser.parse('''
        MERGE ({p: null})
        ''')
    }
        
    @Test
    def void testMiscellaneousErrorAcceptance_04() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE count(a) > 10
        RETURN a
        ''')
    }
        
    @Test
    def void testMiscellaneousErrorAcceptance_05() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n.prop1
        ORDER BY max(n.prop2)
        ''')
    }
        
    @Test
    def void testMiscellaneousErrorAcceptance_06() {
        RelalgParser.parse('''
        MATCH (n)
        WITH n.prop1 AS foo
        ORDER BY max(n.prop2)
        RETURN foo AS foo
        ''')
    }
        
    @Test
    def void testMiscellaneousErrorAcceptance_07() {
        RelalgParser.parse('''
        MATCH (a)
        WITH a, count(*)
        RETURN a
        ''')
    }
        
    @Test
    def void testMiscellaneousErrorAcceptance_09() {
        RelalgParser.parse('''
        MATCH (a)
        SET a.name = missing
        RETURN a
        ''')
    }
        
    @Test
    def void testMiscellaneousErrorAcceptance_10() {
        RelalgParser.parse('''
        MATCH (a)
        DELETE x
        ''')
    }
        
    @Test
    def void testMiscellaneousErrorAcceptance_12() {
        RelalgParser.parse('''
        MATCH p = (a)
        WITH p, a
        MATCH p = (a)-->(b)
        RETURN a
        ''')
    }
        
    @Test
    def void testMiscellaneousErrorAcceptance_13() {
        RelalgParser.parse('''
        MATCH (n)
        WITH [n] AS users
        MATCH (users)-->(messages)
        RETURN messages
        ''')
    }
        
    @Test
    def void testMiscellaneousErrorAcceptance_14() {
        RelalgParser.parse('''
        MATCH (n)
        MATCH (n)-[r*]->()
        WHERE r.foo = 'apa'
        RETURN r
        ''')
    }
        
    @Test
    def void testMiscellaneousErrorAcceptance_15() {
        RelalgParser.parse('''
        RETURN 1 AS a
        UNION
        RETURN 2 AS b
        ''')
    }
        
    @Test
    def void testMiscellaneousErrorAcceptance_16() {
        RelalgParser.parse('''
        RETURN 1 AS a
        UNION
        RETURN 2 AS a
        UNION ALL
        RETURN 3 AS a
        ''')
    }
        
    @Test
    def void testMiscellaneousErrorAcceptance_19() {
        RelalgParser.parse('''
        MATCH (n)
        DELETE n:Person
        ''')
    }
        
    @Test
    def void testMiscellaneousErrorAcceptance_21() {
        RelalgParser.parse('''
        RETURN 1 AS a, 2 AS a
        ''')
    }
        
    @Test
    def void testMiscellaneousErrorAcceptance_22() {
        RelalgParser.parse('''
        MATCH ()
        RETURN *
        ''')
    }
        
}
    