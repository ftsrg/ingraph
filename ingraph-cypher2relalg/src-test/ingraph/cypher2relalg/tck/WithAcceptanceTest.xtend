package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class WithAcceptanceTest {
    
    @Test
    def void testWithAcceptance_01() {
        RelalgParser.parse('''
        MATCH (a:A)
        WITH a
        MATCH (a)-->(b)
        RETURN *
        ''')
    }
        
    @Test
    def void testWithAcceptance_02() {
        RelalgParser.parse('''
        MATCH (a:A)
        WITH a
        ORDER BY a.name
        LIMIT 1
        MATCH (a)-->(b)
        RETURN a
        ''')
    }
        
    @Test
    def void testWithAcceptance_03() {
        RelalgParser.parse('''
        MATCH (a)
        WITH a
        MATCH (b)
        RETURN a, b
        ''')
    }
        
    @Test
    def void testWithAcceptance_04() {
        RelalgParser.parse('''
        MATCH (a:Begin)
        WITH a.prop AS property
        MATCH (b:End)
        WHERE property = b.prop
        RETURN b
        ''')
    }
        
    @Test
    def void testWithAcceptance_05() {
        RelalgParser.parse('''
        MATCH (a:Begin)
        WITH a.prop AS property
        LIMIT 1
        MATCH (b)
        WHERE b.id = property
        RETURN b
        ''')
    }
        
    @Test
    def void testWithAcceptance_06() {
        RelalgParser.parse('''
        MATCH (a)
        WITH a.prop AS property, a.key AS idToUse
        ORDER BY property
        SKIP 1
        MATCH (b)
        WHERE b.id = idToUse
        RETURN DISTINCT b
        ''')
    }
        
    @Test
    def void testWithAcceptance_07() {
        RelalgParser.parse('''
        MATCH (a)
        WITH a
        WHERE a.name = 'B'
        RETURN a
        ''')
    }
        
    @Test
    def void testWithAcceptance_08() {
        RelalgParser.parse('''
        MATCH (a)-->()
        WITH a, count(*) AS relCount
        WHERE relCount > 1
        RETURN a
        ''')
    }
        
    @Test
    def void testWithAcceptance_09() {
        RelalgParser.parse('''
        MATCH (a)
        WITH a.bar AS bars, count(*) AS relCount
        ORDER BY a.bar
        RETURN *
        ''')
    }
        
    @Test
    def void testWithAcceptance_10() {
        RelalgParser.parse('''
        MATCH (a)
        WITH DISTINCT a.bar AS bars
        ORDER BY a.bar
        RETURN *
        ''')
    }
        
    @Test
    def void testWithAcceptance_11() {
        RelalgParser.parse('''
        MATCH (a)
        WITH DISTINCT a.bar AS bars
        WHERE a.bar = 'B'
        RETURN *
        ''')
    }
        
    @Test
    def void testWithAcceptance_12() {
        RelalgParser.parse('''
        MATCH (a:A)-[r:REL]->(b:B)
        WITH a AS b, b AS tmp, r AS r
        WITH b AS a, r
        LIMIT 1
        MATCH (a)-[r]->(b)
        RETURN a, r, b
        ''')
    }
        
    @Test
    def void testWithAcceptance_13() {
        RelalgParser.parse('''
        OPTIONAL MATCH (a:Start)
        WITH a
        MATCH (a)-->(b)
        RETURN *
        ''')
    }
        
    @Test
    def void testWithAcceptance_14() {
        RelalgParser.parse('''
        WITH {foo: {bar: 'baz'}} AS nestedMap
        RETURN nestedMap.foo.bar
        ''')
    }
        
    @Test
    def void testWithAcceptance_15() {
        RelalgParser.parse('''
        MATCH (n:A)
        WITH n
        LIMIT 1
        MATCH (m:B), (n)-->(x:X)
        RETURN *
        ''')
    }
        
    @Test
    def void testWithAcceptance_16() {
        RelalgParser.parse('''
        MATCH (n)
        WITH n
        WHERE n.prop = 42
        RETURN count(*)
        ''')
    }
        
    @Test
    def void testWithAcceptance_17() {
        RelalgParser.parse('''
        MATCH (david {name: 'David'})--(otherPerson)-->()
        WITH otherPerson, count(*) AS foaf
        WHERE foaf > 1
        WITH otherPerson
        WHERE otherPerson.name <> 'NotOther'
        RETURN count(*)
        ''')
    }
        
}
    