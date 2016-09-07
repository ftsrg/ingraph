package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class MatchAcceptanceTest {
    
    @Test
    def void testMatchAcceptance_01() {
        RelalgParser.parse('''
        MATCH p = (a:Label1)<--(:Label2)
        RETURN p
        ''')
    }
        
    @Test
    def void testMatchAcceptance_02() {
        RelalgParser.parse('''
        MATCH p = (a:Label1)<--(:Label2)--()
        RETURN p
        ''')
    }
        
    @Test
    def void testMatchAcceptance_03() {
        RelalgParser.parse('''
        MATCH (n), (m)
        RETURN n.value AS n, m.value AS m
        ''')
    }
        
    @Test
    def void testMatchAcceptance_04() {
        RelalgParser.parse('''
        MATCH (a)-[r]->(b)
        WHERE r.foo = $param
        RETURN b
        ''')
    }
        
    @Test
    def void testMatchAcceptance_05() {
        RelalgParser.parse('''
        MATCH ()-[rel:X]-(a)
        WHERE a.name = 'Andres'
        RETURN a
        ''')
    }
        
    @Test
    def void testMatchAcceptance_06() {
        RelalgParser.parse('''
        MATCH (a)
        WITH a.name AS a
        RETURN a
        ''')
    }
        
    @Test
    def void testMatchAcceptance_07() {
        RelalgParser.parse('''
        MATCH (node)-[r:KNOWS]->(a)
        WHERE r.name = 'monkey'
        RETURN a
        ''')
    }
        
    @Test
    def void testMatchAcceptance_08() {
        RelalgParser.parse('''
        MATCH (n)
        WITH n.name AS n
        RETURN n
        ''')
    }
        
    @Test
    def void testMatchAcceptance_09() {
        RelalgParser.parse('''
        MATCH (n1)-[rel:KNOWS]->(n2)
        RETURN n1, n2
        ''')
    }
        
    @Test
    def void testMatchAcceptance_10() {
        RelalgParser.parse('''
        MATCH ()-[rel:KNOWS]->(x)
        RETURN x
        ''')
    }
        
    @Test
    def void testMatchAcceptance_11() {
        RelalgParser.parse('''
        MATCH (n)-->(a)-->(b)
        RETURN b
        ''')
    }
        
    @Test
    def void testMatchAcceptance_12() {
        RelalgParser.parse('''
        MATCH (n)-[rel]->(x)
        WHERE n.animal = x.animal
        RETURN n, x
        ''')
    }
        
    @Test
    def void testMatchAcceptance_13() {
        RelalgParser.parse('''
        MATCH (a)-[r {name: 'r'}]-(b)
        RETURN a, b
        ''')
    }
        
    @Test
    def void testMatchAcceptance_14() {
        RelalgParser.parse('''
        MATCH (a)-[r {name: 'r1'}]-(b)
        OPTIONAL MATCH (b)-[r2]-(c)
        WHERE r <> r2
        RETURN a, b, c
        ''')
    }
        
    @Test
    def void testMatchAcceptance_15() {
        RelalgParser.parse('''
        MATCH (n {name: 'A'})-[r]->(x)
        WHERE type(r) = 'KNOWS'
        RETURN x
        ''')
    }
        
    @Test
    def void testMatchAcceptance_16() {
        RelalgParser.parse('''
        MATCH (n)-[r]->(x)
        WHERE type(r) = 'KNOWS' OR type(r) = 'HATES'
        RETURN r
        ''')
    }
        
    @Test
    def void testMatchAcceptance_17() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE n.p1 = 12 OR n.p2 = 13
        RETURN n
        ''')
    }
        
    @Test
    def void testMatchAcceptance_18() {
        RelalgParser.parse('''
        MATCH p = (a {name: 'A'})-->(b)
        RETURN p
        ''')
    }
        
    @Test
    def void testMatchAcceptance_19() {
        RelalgParser.parse('''
        MATCH p = (a {name: 'A'})-[rel1]->(b)-[rel2]->(c)
        RETURN p
        ''')
    }
        
    @Test
    def void testMatchAcceptance_20() {
        RelalgParser.parse('''
        MATCH p = (n)-->(x)
        WHERE length(p) = 10
        RETURN x
        ''')
    }
        
    @Test
    def void testMatchAcceptance_21() {
        RelalgParser.parse('''
        MATCH p = (n)-->(x)
        WHERE length(p) = 1
        RETURN x
        ''')
    }
        
    @Test
    def void testMatchAcceptance_22() {
        RelalgParser.parse('''
        MATCH p = (a)-[:REL*2..2]->(b:End)
        RETURN relationships(p)
        ''')
    }
        
    @Test
    def void testMatchAcceptance_23() {
        RelalgParser.parse('''
        MATCH p = (a:Start)-[:REL*2..2]->(b)
        RETURN relationships(p)
        ''')
    }
        
    @Test
    def void testMatchAcceptance_24() {
        RelalgParser.parse('''
        MATCH (a)-[r:REL*2..2]->(b:End)
        RETURN r
        ''')
    }
        
    @Test
    def void testMatchAcceptance_25() {
        RelalgParser.parse('''
        MATCH (a)-[r:REL*2..2]-(b:End)
        RETURN r
        ''')
    }
        
    @Test
    def void testMatchAcceptance_26() {
        RelalgParser.parse('''
        MATCH (a:Start)-[r:REL*2..2]-(b)
        RETURN r
        ''')
    }
        
    @Test
    def void testMatchAcceptance_27() {
        RelalgParser.parse('''
        MATCH p = (n {name: 'A'})-[:KNOWS*1..2]->(x)
        RETURN p
        ''')
    }
        
    @Test
    def void testMatchAcceptance_28() {
        RelalgParser.parse('''
        MATCH p = (a)-[*0..1]->(b)
        RETURN a, b, length(p) AS l
        ''')
    }
        
    @Test
    def void testMatchAcceptance_29() {
        RelalgParser.parse('''
        MATCH p = (a {name: 'A'})-[:KNOWS*0..1]->(b)-[:FRIEND*0..1]->(c)
        RETURN p
        ''')
    }
        
    @Test
    def void testMatchAcceptance_30() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE 1 = 0
        RETURN n SKIP 0
        ''')
    }
        
}
    