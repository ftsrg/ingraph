package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.inferencers.BasicSchemaInferencer
import ingraph.relalg2tex.relalgconverters.Relalg2TexTreeConverter

class SemanticErrorAcceptanceVisualizationTest {

    extension Relalg2TexTreeConverter converter = new Relalg2TexTreeConverter
    extension BasicSchemaInferencer inferencer = new BasicSchemaInferencer
    
    /*
    Scenario: Handling property access on the Any type
    */
    @Test
    def void testSemanticErrorAcceptance_01() {
        val container = Cypher2Relalg.processString('''
        WITH [{prop: 0}, 1] AS list
        RETURN (list[0]).prop
        ''')
        container.inferBasicSchema
        container.convert("tck/SemanticErrorAcceptance_01")
    }

    /*
    Scenario: Failing when performing property access on a non-map 1
    */
    @Test
    def void testSemanticErrorAcceptance_02() {
        val container = Cypher2Relalg.processString('''
        WITH [{prop: 0}, 1] AS list
        RETURN (list[1]).prop
        ''')
        container.inferBasicSchema
        container.convert("tck/SemanticErrorAcceptance_02")
    }

    /*
    Scenario: Bad arguments for `range()`
    */
    @Test
    def void testSemanticErrorAcceptance_04() {
        val container = Cypher2Relalg.processString('''
        RETURN range(2, 8, 0)
        ''')
        container.inferBasicSchema
        container.convert("tck/SemanticErrorAcceptance_04")
    }

}
