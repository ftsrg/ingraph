package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class SkipLimitAcceptanceTest {
    
    @Test
    def void testSkipLimitAcceptance_01() {
        RelalgParser.parse('''
        MATCH (n)
        WITH n SKIP toInteger(rand()*9)
        WITH count(*) AS count
        RETURN count > 0 AS nonEmpty
        ''')
    }
        
    @Test
    def void testSkipLimitAcceptance_02() {
        RelalgParser.parse('''
        MATCH (n)
        WITH n LIMIT toInteger(ceil(1.7))
        RETURN count(*) AS count
        ''')
    }
        
}
    