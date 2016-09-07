package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class NullAcceptanceTest {
    
    @Test
    def void testNullAcceptance_02() {
        RelalgParser.parse('''
        OPTIONAL MATCH (a:DoesNotExist)
        REMOVE a.prop
        RETURN a
        ''')
    }
        
    @Test
    def void testNullAcceptance_06() {
        RelalgParser.parse('''
        OPTIONAL MATCH (a:DoesNotExist)
        REMOVE a:L
        RETURN a
        ''')
    }
        
}
    