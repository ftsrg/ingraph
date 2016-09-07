package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class ColumnNameAcceptanceTest {
    
    @Test
    def void testColumnNameAcceptance_01() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN cOuNt( * )
        ''')
    }
        
    @Test
    def void testColumnNameAcceptance_02() {
        RelalgParser.parse('''
        MATCH p = (n)-->(b)
        RETURN nOdEs( p )
        ''')
    }
        
    @Test
    def void testColumnNameAcceptance_03() {
        RelalgParser.parse('''
        MATCH p = (n)-->(b)
        RETURN coUnt( dIstInct p )
        ''')
    }
        
    @Test
    def void testColumnNameAcceptance_04() {
        RelalgParser.parse('''
        MATCH p = (n)-->(b)
        RETURN aVg(    n.aGe     )
        ''')
    }
        
}
    