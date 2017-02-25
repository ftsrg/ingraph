package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.inferencers.BasicSchemaInferencer
import ingraph.relalg2tex.relalgconverters.Relalg2TexTreeConverter

class LiteralsVisualizationTest {

    extension Relalg2TexTreeConverter converter = new Relalg2TexTreeConverter
    extension BasicSchemaInferencer inferencer = new BasicSchemaInferencer
    
    /*
    Scenario: Return an integer
    */
    @Test
    def void testLiterals_01() {
        val container = Cypher2Relalg.processString('''
        RETURN 1 AS literal
        ''')
        container.inferBasicSchema
        container.convert("tck/Literals_01")
    }

    /*
    Scenario: Return a float
    */
    @Test
    def void testLiterals_02() {
        val container = Cypher2Relalg.processString('''
        RETURN 1.0 AS literal
        ''')
        container.inferBasicSchema
        container.convert("tck/Literals_02")
    }

    /*
    Scenario: Return a float in exponent form
    */
    @Test
    def void testLiterals_03() {
        val container = Cypher2Relalg.processString('''
        RETURN -1e-9 AS literal
        ''')
        container.inferBasicSchema
        container.convert("tck/Literals_03")
    }

    /*
    Scenario: Return a boolean
    */
    @Test
    def void testLiterals_04() {
        val container = Cypher2Relalg.processString('''
        RETURN true AS literal
        ''')
        container.inferBasicSchema
        container.convert("tck/Literals_04")
    }

    /*
    Scenario: Return a single-quoted string
    */
    @Test
    def void testLiterals_05() {
        val container = Cypher2Relalg.processString('''
        RETURN '' AS literal
        ''')
        container.inferBasicSchema
        container.convert("tck/Literals_05")
    }

    /*
    Scenario: Return a double-quoted string
    */
    @Test
    def void testLiterals_06() {
        val container = Cypher2Relalg.processString('''
        RETURN "" AS literal
        ''')
        container.inferBasicSchema
        container.convert("tck/Literals_06")
    }

    /*
    Scenario: Return null
    */
    @Test
    def void testLiterals_07() {
        val container = Cypher2Relalg.processString('''
        RETURN null AS literal
        ''')
        container.inferBasicSchema
        container.convert("tck/Literals_07")
    }

    /*
    Scenario: Return an empty list
    */
    @Test
    def void testLiterals_08() {
        val container = Cypher2Relalg.processString('''
        RETURN [] AS literal
        ''')
        container.inferBasicSchema
        container.convert("tck/Literals_08")
    }

    /*
    Scenario: Return a nonempty list
    */
    @Test
    def void testLiterals_09() {
        val container = Cypher2Relalg.processString('''
        RETURN [0, 1, 2] AS literal
        ''')
        container.inferBasicSchema
        container.convert("tck/Literals_09")
    }

    /*
    Scenario: Return an empty map
    */
    @Test
    def void testLiterals_10() {
        val container = Cypher2Relalg.processString('''
        RETURN {} AS literal
        ''')
        container.inferBasicSchema
        container.convert("tck/Literals_10")
    }

    /*
    Scenario: Return a nonempty map
    */
    @Test
    def void testLiterals_11() {
        val container = Cypher2Relalg.processString('''
        RETURN {k1: 0, k2: 'string'} AS literal
        ''')
        container.inferBasicSchema
        container.convert("tck/Literals_11")
    }

}
