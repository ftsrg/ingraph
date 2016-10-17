package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.CypherParser
import ingraph.optimization.transformations.SchemaInferencer
import ingraph.relalg2tex.RelalgTreeSerializer

class ColumnNameAcceptanceVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer
    extension SchemaInferencer inferencer = new SchemaInferencer
    
    /*
    Scenario: Keeping used expression 1
    */
    @Test
    def void testColumnNameAcceptance_01() {
        val container = CypherParser.parseString('''
        MATCH (n)
        RETURN cOuNt( * )
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ColumnNameAcceptance_01")
    }

    /*
    Scenario: Keeping used expression 2
    */
    @Test
    def void testColumnNameAcceptance_02() {
        val container = CypherParser.parseString('''
        MATCH p = (n)-->(b)
        RETURN nOdEs( p )
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ColumnNameAcceptance_02")
    }

    /*
    Scenario: Keeping used expression 3
    */
    @Test
    def void testColumnNameAcceptance_03() {
        val container = CypherParser.parseString('''
        MATCH p = (n)-->(b)
        RETURN coUnt( dIstInct p )
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ColumnNameAcceptance_03")
    }

    /*
    Scenario: Keeping used expression 4
    */
    @Test
    def void testColumnNameAcceptance_04() {
        val container = CypherParser.parseString('''
        MATCH p = (n)-->(b)
        RETURN aVg(    n.aGe     )
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ColumnNameAcceptance_04")
    }

}
