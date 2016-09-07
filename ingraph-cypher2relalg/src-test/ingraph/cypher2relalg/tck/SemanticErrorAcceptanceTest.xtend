package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class SemanticErrorAcceptanceTest {
    
    @Test
    def void testSemanticErrorAcceptance_01() {
        RelalgParser.parse('''
        WITH [{prop: 0}, 1] AS list
        RETURN (list[0]).prop
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_02() {
        RelalgParser.parse('''
        WITH [{prop: 0}, 1] AS list
        RETURN (list[1]).prop
        ''')
    }
        
    @Test
    def void testSemanticErrorAcceptance_04() {
        RelalgParser.parse('''
        RETURN range(2, 8, 0)
        ''')
    }
        
}
    