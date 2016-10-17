package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.CypherParser

class TernaryLogicAcceptanceParserTest {
    
    /*
    Scenario: The inverse of a null is a null
    */
    @Test
    def void testTernaryLogicAcceptance_01() {
        CypherParser.parseString('''
        RETURN NOT null AS value
        ''')
    }

    /*
    Scenario: A literal null IS null
    */
    @Test
    def void testTernaryLogicAcceptance_02() {
        CypherParser.parseString('''
        RETURN null IS NULL AS value
        ''')
    }

    /*
    Scenario: A literal null is not IS NOT null
    */
    @Test
    def void testTernaryLogicAcceptance_03() {
        CypherParser.parseString('''
        RETURN null IS NOT NULL AS value
        ''')
    }

    /*
    Scenario: It is unknown - i.e. null - if a null is equal to a null
    */
    @Test
    def void testTernaryLogicAcceptance_04() {
        CypherParser.parseString('''
        RETURN null = null AS value
        ''')
    }

    /*
    Scenario: It is unknown - i.e. null - if a null is not equal to a null
    */
    @Test
    def void testTernaryLogicAcceptance_05() {
        CypherParser.parseString('''
        RETURN null <> null AS value
        ''')
    }

}
