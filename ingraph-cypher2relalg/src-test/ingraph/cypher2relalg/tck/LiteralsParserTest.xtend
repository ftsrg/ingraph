package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.CypherParser

class LiteralsParserTest {
    
    /*
    Scenario: Return an integer
    */
    @Test
    def void testLiterals_01() {
        CypherParser.parseString('''
        RETURN 1 AS literal
        ''')
    }

    /*
    Scenario: Return a float
    */
    @Test
    def void testLiterals_02() {
        CypherParser.parseString('''
        RETURN 1.0 AS literal
        ''')
    }

    /*
    Scenario: Return a float in exponent form
    */
    @Test
    def void testLiterals_03() {
        CypherParser.parseString('''
        RETURN -1e-9 AS literal
        ''')
    }

    /*
    Scenario: Return a boolean
    */
    @Test
    def void testLiterals_04() {
        CypherParser.parseString('''
        RETURN true AS literal
        ''')
    }

    /*
    Scenario: Return a single-quoted string
    */
    @Test
    def void testLiterals_05() {
        CypherParser.parseString('''
        RETURN '' AS literal
        ''')
    }

    /*
    Scenario: Return a double-quoted string
    */
    @Test
    def void testLiterals_06() {
        CypherParser.parseString('''
        RETURN "" AS literal
        ''')
    }

    /*
    Scenario: Return null
    */
    @Test
    def void testLiterals_07() {
        CypherParser.parseString('''
        RETURN null AS literal
        ''')
    }

    /*
    Scenario: Return an empty list
    */
    @Test
    def void testLiterals_08() {
        CypherParser.parseString('''
        RETURN [] AS literal
        ''')
    }

    /*
    Scenario: Return a nonempty list
    */
    @Test
    def void testLiterals_09() {
        CypherParser.parseString('''
        RETURN [0, 1, 2] AS literal
        ''')
    }

    /*
    Scenario: Return an empty map
    */
    @Test
    def void testLiterals_10() {
        CypherParser.parseString('''
        RETURN {} AS literal
        ''')
    }

    /*
    Scenario: Return a nonempty map
    */
    @Test
    def void testLiterals_11() {
        CypherParser.parseString('''
        RETURN {k1: 0, k2: 'string'} AS literal
        ''')
    }

}
