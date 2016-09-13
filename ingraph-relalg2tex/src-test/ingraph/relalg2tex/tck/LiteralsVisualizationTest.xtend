package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.AlgebraTreeDrawer

class LiteralsVisualizationTest {

    val static AlgebraTreeDrawer drawer = new AlgebraTreeDrawer(true)
    
    /*
    Scenario: Return an integer
    */
    @Test
    def void testLiterals_01() {
        val container = RelalgParser.parse('''
        RETURN 1 AS literal
        ''')
        drawer.serialize(container, "Literals")
    }

    /*
    Scenario: Return a float
    */
    @Test
    def void testLiterals_02() {
        val container = RelalgParser.parse('''
        RETURN 1.0 AS literal
        ''')
        drawer.serialize(container, "Literals")
    }

    /*
    Scenario: Return a float in exponent form
    */
    @Test
    def void testLiterals_03() {
        val container = RelalgParser.parse('''
        RETURN -1e-9 AS literal
        ''')
        drawer.serialize(container, "Literals")
    }

    /*
    Scenario: Return a boolean
    */
    @Test
    def void testLiterals_04() {
        val container = RelalgParser.parse('''
        RETURN true AS literal
        ''')
        drawer.serialize(container, "Literals")
    }

    /*
    Scenario: Return a single-quoted string
    */
    @Test
    def void testLiterals_05() {
        val container = RelalgParser.parse('''
        RETURN '' AS literal
        ''')
        drawer.serialize(container, "Literals")
    }

    /*
    Scenario: Return a double-quoted string
    */
    @Test
    def void testLiterals_06() {
        val container = RelalgParser.parse('''
        RETURN "" AS literal
        ''')
        drawer.serialize(container, "Literals")
    }

    /*
    Scenario: Return null
    */
    @Test
    def void testLiterals_07() {
        val container = RelalgParser.parse('''
        RETURN null AS literal
        ''')
        drawer.serialize(container, "Literals")
    }

    /*
    Scenario: Return an empty list
    */
    @Test
    def void testLiterals_08() {
        val container = RelalgParser.parse('''
        RETURN [] AS literal
        ''')
        drawer.serialize(container, "Literals")
    }

    /*
    Scenario: Return a nonempty list
    */
    @Test
    def void testLiterals_09() {
        val container = RelalgParser.parse('''
        RETURN [0, 1, 2] AS literal
        ''')
        drawer.serialize(container, "Literals")
    }

    /*
    Scenario: Return an empty map
    */
    @Test
    def void testLiterals_10() {
        val container = RelalgParser.parse('''
        RETURN {} AS literal
        ''')
        drawer.serialize(container, "Literals")
    }

    /*
    Scenario: Return a nonempty map
    */
    @Test
    def void testLiterals_11() {
        val container = RelalgParser.parse('''
        RETURN {k1: 0, k2: 'string'} AS literal
        ''')
        drawer.serialize(container, "Literals")
    }

}
