package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.RelalgTreeSerializer

class ColumnNameAcceptanceVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer
    
    /*
    Scenario: Keeping used expression 1
    */
    @Test
    def void testColumnNameAcceptance_01() {
        val container = RelalgParser.parse('''
        MATCH (n)
        RETURN cOuNt( * )
        ''')
        serializer.serialize(container, "ColumnNameAcceptance_01")
    }

    /*
    Scenario: Keeping used expression 2
    */
    @Test
    def void testColumnNameAcceptance_02() {
        val container = RelalgParser.parse('''
        MATCH p = (n)-->(b)
        RETURN nOdEs( p )
        ''')
        serializer.serialize(container, "ColumnNameAcceptance_02")
    }

    /*
    Scenario: Keeping used expression 3
    */
    @Test
    def void testColumnNameAcceptance_03() {
        val container = RelalgParser.parse('''
        MATCH p = (n)-->(b)
        RETURN coUnt( dIstInct p )
        ''')
        serializer.serialize(container, "ColumnNameAcceptance_03")
    }

    /*
    Scenario: Keeping used expression 4
    */
    @Test
    def void testColumnNameAcceptance_04() {
        val container = RelalgParser.parse('''
        MATCH p = (n)-->(b)
        RETURN aVg(    n.aGe     )
        ''')
        serializer.serialize(container, "ColumnNameAcceptance_04")
    }

}
