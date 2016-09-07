package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class StartingPointAcceptanceTest {
    
    @Test
    def void testStartingPointAcceptance_01() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n
        ''')
    }
        
    @Test
    def void testStartingPointAcceptance_02() {
        RelalgParser.parse('''
        MATCH (n:Animal)
        RETURN n
        ''')
    }
        
    @Test
    def void testStartingPointAcceptance_03() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE n.prop = 2
        RETURN n
        ''')
    }
        
}
    