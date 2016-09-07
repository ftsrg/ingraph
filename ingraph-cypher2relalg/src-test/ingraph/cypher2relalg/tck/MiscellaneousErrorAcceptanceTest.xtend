package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class MiscellaneousErrorAcceptanceTest {
    
    @Test
    def void testMiscellaneousErrorAcceptance_02() {
        RelalgParser.parse('''
        MERGE ({p: null})
        ''')
    }
        
}
    