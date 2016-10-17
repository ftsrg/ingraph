package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.CypherParser

class ColumnNameAcceptanceParserTest {
    
    /*
    Scenario: Keeping used expression 1
    */
    @Test
    def void testColumnNameAcceptance_01() {
        CypherParser.parseString('''
        MATCH (n)
        RETURN cOuNt( * )
        ''')
    }

    /*
    Scenario: Keeping used expression 2
    */
    @Test
    def void testColumnNameAcceptance_02() {
        CypherParser.parseString('''
        MATCH p = (n)-->(b)
        RETURN nOdEs( p )
        ''')
    }

    /*
    Scenario: Keeping used expression 3
    */
    @Test
    def void testColumnNameAcceptance_03() {
        CypherParser.parseString('''
        MATCH p = (n)-->(b)
        RETURN coUnt( dIstInct p )
        ''')
    }

    /*
    Scenario: Keeping used expression 4
    */
    @Test
    def void testColumnNameAcceptance_04() {
        CypherParser.parseString('''
        MATCH p = (n)-->(b)
        RETURN aVg(    n.aGe     )
        ''')
    }

}
