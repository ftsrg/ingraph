package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class SemanticErrorAcceptanceTest {
    
    @Test
    def void testSemanticErrorAcceptance_01() {
        RelalgParser.parse('''
        MATCH ()
        RETURN foo
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_02() {
        RelalgParser.parse('''
        MATCH (s)
        WHERE s.name = undefinedVariable
        AND s.age = 10
        RETURN s
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_03() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE n.id IN ''
        RETURN 1
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_04() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE n.id IN 1
        RETURN 1
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_05() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE n.id IN 1.0
        RETURN 1
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_06() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE n.id IN true
        RETURN 1
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_07() {
        RelalgParser.parse('''
        MATCH (r)
        MATCH ()-[r]-()
        RETURN r
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_08() {
        RelalgParser.parse('''
        MATCH ()-[r]-(r)
        RETURN r
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_09() {
        RelalgParser.parse('''
        MATCH (r)
        RETURN type(r)
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_10() {
        RelalgParser.parse('''
        MATCH (r)
        RETURN length(r)
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_11() {
        RelalgParser.parse('''
        MATCH (a)-[r]->()-[r]->(a)
        RETURN r
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_12() {
        RelalgParser.parse('''
        RETURN NOT 'foo'
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_13() {
        RelalgParser.parse('''
        CREATE ()-[:FOO*2]->()
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_14() {
        RelalgParser.parse('''
        MERGE (a)
        MERGE (b)
        MERGE (a)-[:FOO*2]->(b)
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_15() {
        RelalgParser.parse('''
        MATCH (n {param})
        RETURN n
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_16() {
        RelalgParser.parse('''
        MATCH ()-[r:FOO {param}]->()
        RETURN r
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_17() {
        RelalgParser.parse('''
        MERGE (n {param})
        RETURN n
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_18() {
        RelalgParser.parse('''
        MERGE (a)
        MERGE (b)
        MERGE (a)-[r:FOO {param}]->(b)
        RETURN r
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_19() {
        RelalgParser.parse('''
        MATCH ()
        DELETE 1 + 1
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_20() {
        RelalgParser.parse('''
        MATCH (a)
        CREATE (a)
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_21() {
        RelalgParser.parse('''
        MATCH (a)
        CREATE (a)
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_22() {
        RelalgParser.parse('''
        MATCH ()-[r]->()
        CREATE ()-[r]->()
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_23() {
        RelalgParser.parse('''
        MATCH (a)-[r]->(b)
        MERGE (a)-[r]->(b)
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_24() {
        RelalgParser.parse('''
        MERGE (n)
        ON CREATE SET x.foo = 1
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_25() {
        RelalgParser.parse('''
        MERGE (n)
        ON MATCH SET x.foo = 1
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_26() {
        RelalgParser.parse('''
        OPTIONAL MATCH ()-->()
        MATCH ()-->(d)
        RETURN d
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_27() {
        RelalgParser.parse('''
        RETURN 1.34E999
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_28() {
        RelalgParser.parse('''
        WITH [{prop: 0}, 1] AS list
        RETURN (list[0]).prop
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_29() {
        RelalgParser.parse('''
        WITH [{prop: 0}, 1] AS list
        RETURN (list[1]).prop
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_30() {
        RelalgParser.parse('''
        CREATE (n {prop: 'foo'})
        WITH n.prop AS n2
        RETURN n2.prop
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_31() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN exists(n.prop + 1)
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_32() {
        RelalgParser.parse('''
        RETURN range(2, 8, 0)
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_33() {
        RelalgParser.parse('''
        RETURN 42 — 41
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_34() {
        RelalgParser.parse('''
        MATCH p = (a)-[*]->(b)
        RETURN size(p)
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_35() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN [x IN [1, 2, 3, 4, 5] | count(*)]
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_36() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n
        SKIP n.count
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_37() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n
        SKIP -1
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_38() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n
        LIMIT n.count
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_39() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n
        LIMIT -1
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_40() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n
        LIMIT 1.7
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_41() {
        RelalgParser.parse('''
        CREATE ()-->()
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_42() {
        RelalgParser.parse('''
        CREATE (a), (b)
        MERGE (a)-->(b)
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_43() {
        RelalgParser.parse('''
        MATCH (a), (b)
        MERGE (a)-[NO_COLON]->(b)
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_44() {
        RelalgParser.parse('''
        CREATE ()-[:A|:B]->()
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_45() {
        RelalgParser.parse('''
        CREATE (a), (b)
        MERGE (a)-[:A|:B]->(b)
        ''')
    }
        
}
    