package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class WhereAcceptanceTest {
    
    @Test
    def void testWhereAcceptance_01() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE NOT(n.name = 'apa' AND false)
        RETURN n
        ''')
    }
        
    @Test
    def void testWhereAcceptance_02() {
        RelalgParser.parse('''
        MATCH (n:Label)
        WHERE n.prop < 10
        RETURN n.prop AS prop
        ''')
    }
        
}
    