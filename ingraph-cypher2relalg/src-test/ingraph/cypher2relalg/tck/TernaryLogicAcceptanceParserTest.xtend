package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class TernaryLogicAcceptanceParserTest {
    
    /*
    Scenario: The inverse of a null is a null
    */
    @Test
    def void testTernaryLogicAcceptance_01() {
        RelalgParser.parse('''
        RETURN NOT null AS value
        ''')
    }

    /*
    Scenario: A literal null IS null
    */
    @Test
    def void testTernaryLogicAcceptance_02() {
        RelalgParser.parse('''
        RETURN null IS NULL AS value
        ''')
    }

    /*
    Scenario: A literal null is not IS NOT null
    */
    @Test
    def void testTernaryLogicAcceptance_03() {
        RelalgParser.parse('''
        RETURN null IS NOT NULL AS value
        ''')
    }

    /*
    Scenario: It is unknown - i.e. null - if a null is equal to a null
    */
    @Test
    def void testTernaryLogicAcceptance_04() {
        RelalgParser.parse('''
        RETURN null = null AS value
        ''')
    }

    /*
    Scenario: It is unknown - i.e. null - if a null is not equal to a null
    */
    @Test
    def void testTernaryLogicAcceptance_05() {
        RelalgParser.parse('''
        RETURN null <> null AS value
        ''')
    }

}
