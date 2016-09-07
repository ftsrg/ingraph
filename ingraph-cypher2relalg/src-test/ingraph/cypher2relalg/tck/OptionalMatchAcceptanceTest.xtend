package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class OptionalMatchAcceptanceTest {
    
    @Test
    def void testOptionalMatchAcceptance_01() {
        RelalgParser.parse('''
        MATCH (n:Single)
        OPTIONAL MATCH (n)-[r]-(m:NonExistent)
        RETURN r
        ''')
    }
        
    @Test
    def void testOptionalMatchAcceptance_02() {
        RelalgParser.parse('''
        MATCH (n:Single)
        OPTIONAL MATCH (n)-[r]-(m)
        WHERE m:NonExistent
        RETURN r
        ''')
    }
        
    @Test
    def void testOptionalMatchAcceptance_03() {
        RelalgParser.parse('''
        MATCH (n:Single)
        OPTIONAL MATCH (n)-[r]-(m)
        WHERE m.prop = 42
        RETURN m
        ''')
    }
        
    @Test
    def void testOptionalMatchAcceptance_04() {
        RelalgParser.parse('''
        MATCH (n:Single)
        OPTIONAL MATCH (n)-[r:TYPE]-(m)
        RETURN m:TYPE
        ''')
    }
        
    @Test
    def void testOptionalMatchAcceptance_05() {
        RelalgParser.parse('''
        MATCH (a:Single)
        OPTIONAL MATCH (a)-->(b:NonExistent)
        OPTIONAL MATCH (a)-->(c:NonExistent)
        WITH coalesce(b, c) AS x
        MATCH (x)-->(d)
        RETURN d
        ''')
    }
        
    @Test
    def void testOptionalMatchAcceptance_06() {
        RelalgParser.parse('''
        OPTIONAL MATCH (a:A)
        WITH a AS a
        MATCH (b:B)
        RETURN a, b
        ''')
    }
        
    @Test
    def void testOptionalMatchAcceptance_07() {
        RelalgParser.parse('''
        MATCH (a:A)
        OPTIONAL MATCH p = (a)-[:X]->(b)
        RETURN p
        ''')
    }
        
    @Test
    def void testOptionalMatchAcceptance_08() {
        RelalgParser.parse('''
        MATCH (a:A), (b:C)
        OPTIONAL MATCH (x)-->(b)
        RETURN x
        ''')
    }
        
    @Test
    def void testOptionalMatchAcceptance_09() {
        RelalgParser.parse('''
        MATCH (a:X)
        OPTIONAL MATCH (a)-->(b:Y)
        RETURN b
        ''')
    }
        
    @Test
    def void testOptionalMatchAcceptance_10() {
        RelalgParser.parse('''
        MATCH (a:A), (b:B)
        OPTIONAL MATCH p = (a)-[:X]->(b)
        RETURN p
        ''')
    }
        
    @Test
    def void testOptionalMatchAcceptance_11() {
        RelalgParser.parse('''
        MATCH (a:Single)
        OPTIONAL MATCH (a)-[*]->(b)
        RETURN b
        ''')
    }
        
    @Test
    def void testOptionalMatchAcceptance_12() {
        RelalgParser.parse('''
        MATCH (a:Single)
        OPTIONAL MATCH (a)-[*3..]-(b)
        RETURN b
        ''')
    }
        
    @Test
    def void testOptionalMatchAcceptance_13() {
        RelalgParser.parse('''
        MATCH (a:B)
        OPTIONAL MATCH (a)-[r]-(a)
        RETURN r
        ''')
    }
        
    @Test
    def void testOptionalMatchAcceptance_14() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE NOT (a:B)
        OPTIONAL MATCH (a)-[r]->(a)
        RETURN r
        ''')
    }
        
    @Test
    def void testOptionalMatchAcceptance_15() {
        RelalgParser.parse('''
        MATCH (a:Single), (x:C)
        OPTIONAL MATCH (a)-[*]->(x)
        RETURN x
        ''')
    }
        
    @Test
    def void testOptionalMatchAcceptance_16() {
        RelalgParser.parse('''
        MATCH (a:A), (b:B)
        OPTIONAL MATCH p = (a)-[*]->(b)
        RETURN p
        ''')
    }
        
    @Test
    def void testOptionalMatchAcceptance_17() {
        RelalgParser.parse('''
        MATCH (a:Single), (c:C)
        OPTIONAL MATCH (a)-->(b)-->(c)
        RETURN b
        ''')
    }
        
    @Test
    def void testOptionalMatchAcceptance_18() {
        RelalgParser.parse('''
        MATCH (a:A), (c:C)
        OPTIONAL MATCH (a)-->(b)-->(c)
        RETURN b
        ''')
    }
        
    @Test
    def void testOptionalMatchAcceptance_19() {
        RelalgParser.parse('''
        MATCH (a:A), (b:B)
        OPTIONAL MATCH (a)-->(x)
        OPTIONAL MATCH (x)-[r]->(b)
        RETURN x, r
        ''')
    }
        
    @Test
    def void testOptionalMatchAcceptance_20() {
        RelalgParser.parse('''
        OPTIONAL MATCH (a:NotThere)
        WITH a
        MATCH (b:B)
        WITH a, b
        OPTIONAL MATCH (b)-[r:NOR_THIS]->(a)
        RETURN a, b, r
        ''')
    }
        
    @Test
    def void testOptionalMatchAcceptance_21() {
        RelalgParser.parse('''
        OPTIONAL MATCH (a:NotThere)
        OPTIONAL MATCH (b:NotThere)
        WITH a, b
        OPTIONAL MATCH (b)-[r:NOR_THIS]->(a)
        RETURN a, b, r
        ''')
    }
        
    @Test
    def void testOptionalMatchAcceptance_22() {
        RelalgParser.parse('''
        OPTIONAL MATCH (f:DoesExist)
        OPTIONAL MATCH (n:DoesNotExist)
        RETURN collect(DISTINCT n.property) AS a, collect(DISTINCT f.property) AS b
        ''')
    }
        
}
    