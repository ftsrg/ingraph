package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.RelAlgTreeDrawer

class NullAcceptanceVisualizationTest {

    val static RelAlgTreeDrawer drawer = new RelAlgTreeDrawer(true)
    
    /*
    Scenario: Ignore null when removing property
    Given an empty graph
    */
    @Test
    def void testNullAcceptance_02() {
        val container = RelalgParser.parse('''
        OPTIONAL MATCH (a:DoesNotExist)
        REMOVE a.prop
        RETURN a
        ''')
        drawer.serialize(container, "NullAcceptance_02")
    }

    /*
    Scenario: Ignore null when removing label
    Given an empty graph
    */
    @Test
    def void testNullAcceptance_06() {
        val container = RelalgParser.parse('''
        OPTIONAL MATCH (a:DoesNotExist)
        REMOVE a:L
        RETURN a
        ''')
        drawer.serialize(container, "NullAcceptance_06")
    }

}
