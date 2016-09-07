package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class LabelsAcceptanceTest {
    
    @Test
    def void testLabelsAcceptance_01() {
        RelalgParser.parse('''
        MATCH (n)
        SET n:Foo
        RETURN labels(n)
        ''')
    }
        
    @Test
    def void testLabelsAcceptance_02() {
        RelalgParser.parse('''
        MATCH (n)
        SET n :Foo
        RETURN labels(n)
        ''')
    }
        
    @Test
    def void testLabelsAcceptance_03() {
        RelalgParser.parse('''
        MATCH (n)
        SET n:Foo:Bar
        RETURN labels(n)
        ''')
    }
        
    @Test
    def void testLabelsAcceptance_04() {
        RelalgParser.parse('''
        MATCH (n)
        SET n :Foo :Bar
        RETURN labels(n)
        ''')
    }
        
    @Test
    def void testLabelsAcceptance_05() {
        RelalgParser.parse('''
        MATCH (n)
        SET n :Foo:Bar
        RETURN labels(n)
        ''')
    }
        
    @Test
    def void testLabelsAcceptance_06() {
        RelalgParser.parse('''
        CREATE (node)
        RETURN labels(node)
        ''')
    }
        
    @Test
    def void testLabelsAcceptance_07() {
        RelalgParser.parse('''
        CREATE (node:Foo:Bar {name: 'Mattias'})
        RETURN labels(node)
        ''')
    }
        
    @Test
    def void testLabelsAcceptance_08() {
        RelalgParser.parse('''
        CREATE (node :Foo:Bar)
        RETURN labels(node)
        ''')
    }
        
    @Test
    def void testLabelsAcceptance_09() {
        RelalgParser.parse('''
        CREATE (n:Person)-[:OWNS]->(:Dog)
        RETURN labels(n)
        ''')
    }
        
    @Test
    def void testLabelsAcceptance_10() {
        RelalgParser.parse('''
        CREATE (n:Foo)-[:T1]->(),
        (n:Bar)-[:T2]->()
        ''')
    }
        
    @Test
    def void testLabelsAcceptance_11() {
        RelalgParser.parse('''
        CREATE ()<-[:T2]-(n:Foo),
        (n:Bar)<-[:T1]-()
        ''')
    }
        
    @Test
    def void testLabelsAcceptance_12() {
        RelalgParser.parse('''
        CREATE (n:Foo)
        CREATE (n:Bar)-[:OWNS]->(:Dog)
        ''')
    }
        
    @Test
    def void testLabelsAcceptance_13() {
        RelalgParser.parse('''
        CREATE (n {})
        CREATE (n:Bar)-[:OWNS]->(:Dog)
        ''')
    }
        
    @Test
    def void testLabelsAcceptance_14() {
        RelalgParser.parse('''
        CREATE (n:Foo)
        CREATE (n {})-[:OWNS]->(:Dog)
        ''')
    }
        
    @Test
    def void testLabelsAcceptance_15() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN labels(n)
        ''')
    }
        
    @Test
    def void testLabelsAcceptance_16() {
        RelalgParser.parse('''
        MATCH (n)
        REMOVE n:Foo
        RETURN labels(n)
        ''')
    }
        
    @Test
    def void testLabelsAcceptance_17() {
        RelalgParser.parse('''
        MATCH (n)
        REMOVE n:Bar
        RETURN labels(n)
        ''')
    }
        
}
    