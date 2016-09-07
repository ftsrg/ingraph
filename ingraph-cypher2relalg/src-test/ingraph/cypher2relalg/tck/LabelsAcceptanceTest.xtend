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
    