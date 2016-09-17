package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.RelalgTreeSerializer

class NullAcceptanceVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer(true)
    
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
        serializer.serialize(container, "NullAcceptance_02")
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
        serializer.serialize(container, "NullAcceptance_06")
    }

}
