package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.RelalgTreeSerializer

class LiteralsVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer
    
    /*
    Scenario: Return an integer
    */
    @Test
    def void testLiterals_01() {
        val container = RelalgParser.parse('''
        RETURN 1 AS literal
        ''')
        serializer.serialize(container, "Literals_01")
    }

    /*
    Scenario: Return a float
    */
    @Test
    def void testLiterals_02() {
        val container = RelalgParser.parse('''
        RETURN 1.0 AS literal
        ''')
        serializer.serialize(container, "Literals_02")
    }

    /*
    Scenario: Return a float in exponent form
    */
    @Test
    def void testLiterals_03() {
        val container = RelalgParser.parse('''
        RETURN -1e-9 AS literal
        ''')
        serializer.serialize(container, "Literals_03")
    }

    /*
    Scenario: Return a boolean
    */
    @Test
    def void testLiterals_04() {
        val container = RelalgParser.parse('''
        RETURN true AS literal
        ''')
        serializer.serialize(container, "Literals_04")
    }

    /*
    Scenario: Return a single-quoted string
    */
    @Test
    def void testLiterals_05() {
        val container = RelalgParser.parse('''
        RETURN '' AS literal
        ''')
        serializer.serialize(container, "Literals_05")
    }

    /*
    Scenario: Return a double-quoted string
    */
    @Test
    def void testLiterals_06() {
        val container = RelalgParser.parse('''
        RETURN "" AS literal
        ''')
        serializer.serialize(container, "Literals_06")
    }

    /*
    Scenario: Return null
    */
    @Test
    def void testLiterals_07() {
        val container = RelalgParser.parse('''
        RETURN null AS literal
        ''')
        serializer.serialize(container, "Literals_07")
    }

    /*
    Scenario: Return an empty list
    */
    @Test
    def void testLiterals_08() {
        val container = RelalgParser.parse('''
        RETURN [] AS literal
        ''')
        serializer.serialize(container, "Literals_08")
    }

    /*
    Scenario: Return a nonempty list
    */
    @Test
    def void testLiterals_09() {
        val container = RelalgParser.parse('''
        RETURN [0, 1, 2] AS literal
        ''')
        serializer.serialize(container, "Literals_09")
    }

    /*
    Scenario: Return an empty map
    */
    @Test
    def void testLiterals_10() {
        val container = RelalgParser.parse('''
        RETURN {} AS literal
        ''')
        serializer.serialize(container, "Literals_10")
    }

    /*
    Scenario: Return a nonempty map
    */
    @Test
    def void testLiterals_11() {
        val container = RelalgParser.parse('''
        RETURN {k1: 0, k2: 'string'} AS literal
        ''')
        serializer.serialize(container, "Literals_11")
    }

}
