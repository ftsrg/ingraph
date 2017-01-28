package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.inferencers.BasicSchemaInferencer
import ingraph.relalg2tex.serializers.RelalgTreeSerializer

class ColumnNameAcceptanceVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer
    extension BasicSchemaInferencer inferencer = new BasicSchemaInferencer
    
    /*
    Scenario: Keeping used expression 1
    */
    @Test
    def void testColumnNameAcceptance_01() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)
        RETURN cOuNt( * )
        ''')
        container.inferBasicSchema
        serializer.serialize(container, "tck/ColumnNameAcceptance_01")
    }

    /*
    Scenario: Keeping used expression 2
    */
    @Test
    def void testColumnNameAcceptance_02() {
        val container = Cypher2Relalg.processString('''
        MATCH p = (n)-->(b)
        RETURN nOdEs( p )
        ''')
        container.inferBasicSchema
        serializer.serialize(container, "tck/ColumnNameAcceptance_02")
    }

    /*
    Scenario: Keeping used expression 3
    */
    @Test
    def void testColumnNameAcceptance_03() {
        val container = Cypher2Relalg.processString('''
        MATCH p = (n)-->(b)
        RETURN coUnt( dIstInct p )
        ''')
        container.inferBasicSchema
        serializer.serialize(container, "tck/ColumnNameAcceptance_03")
    }

    /*
    Scenario: Keeping used expression 4
    */
    @Test
    def void testColumnNameAcceptance_04() {
        val container = Cypher2Relalg.processString('''
        MATCH p = (n)-->(b)
        RETURN aVg(    n.aGe     )
        ''')
        container.inferBasicSchema
        serializer.serialize(container, "tck/ColumnNameAcceptance_04")
    }

}
