package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class JoinAcceptanceTest {
    
    @Test
    def void testJoinAcceptance_01() {
        RelalgParser.parse('''
        MATCH (a:A), (b:B)
        WHERE a.id = b.id
        RETURN a, b
        ''')
    }
        
    @Test
    def void testJoinAcceptance_02() {
        RelalgParser.parse('''
        MATCH (a:A), (b:B)
        WHERE a.id = b.id
        RETURN a, b
        ''')
    }
        
}
    