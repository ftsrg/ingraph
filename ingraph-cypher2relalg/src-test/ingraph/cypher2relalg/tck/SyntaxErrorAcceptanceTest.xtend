package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class SyntaxErrorAcceptanceTest {
    
    @Test
    def void testSyntaxErrorAcceptance_01() {
        RelalgParser.parse('''
        MATCH (a)
        RETURN foo(a)
        ''')
    }
        
    @Test
    def void testSyntaxErrorAcceptance_02() {
        RelalgParser.parse('''
        RETURN count(rand())
        ''')
    }
        
    @Test
    def void testSyntaxErrorAcceptance_03() {
        RelalgParser.parse('''
        RETURN 0x23G34
        ''')
    }
        
    @Test
    def void testSyntaxErrorAcceptance_04() {
        RelalgParser.parse('''
        RETURN 0x23j
        ''')
    }
        
}
    