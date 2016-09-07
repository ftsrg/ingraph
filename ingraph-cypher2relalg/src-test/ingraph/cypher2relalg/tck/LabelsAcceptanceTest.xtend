package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class LabelsAcceptanceTest {
    
    @Test
    def void testLabelsAcceptance_10() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN labels(n)
        ''')
    }
        
    @Test
    def void testLabelsAcceptance_11() {
        RelalgParser.parse('''
        MATCH (n)
        REMOVE n:Foo
        RETURN labels(n)
        ''')
    }
        
    @Test
    def void testLabelsAcceptance_12() {
        RelalgParser.parse('''
        MATCH (n)
        REMOVE n:Bar
        RETURN labels(n)
        ''')
    }
        
}
    