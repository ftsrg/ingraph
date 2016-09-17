package ingraph.cypher2.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class ColumnNameAcceptanceParserTest {
    
    /*
    Scenario: Keeping used expression 1
    */
    @Test
    def void testColumnNameAcceptance_01() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN cOuNt( * )
        ''')
    }

    /*
    Scenario: Keeping used expression 2
    */
    @Test
    def void testColumnNameAcceptance_02() {
        RelalgParser.parse('''
        MATCH p = (n)-->(b)
        RETURN nOdEs( p )
        ''')
    }

    /*
    Scenario: Keeping used expression 3
    */
    @Test
    def void testColumnNameAcceptance_03() {
        RelalgParser.parse('''
        MATCH p = (n)-->(b)
        RETURN coUnt( dIstInct p )
        ''')
    }

    /*
    Scenario: Keeping used expression 4
    */
    @Test
    def void testColumnNameAcceptance_04() {
        RelalgParser.parse('''
        MATCH p = (n)-->(b)
        RETURN aVg(    n.aGe     )
        ''')
    }

}
