package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.AlgebraTreeDrawer

class NullAcceptanceVisualizationTest {

    val static AlgebraTreeDrawer drawer = new AlgebraTreeDrawer(true)
    
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
        drawer.serialize(container, "NullAcceptance")
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
        drawer.serialize(container, "NullAcceptance")
    }

}
