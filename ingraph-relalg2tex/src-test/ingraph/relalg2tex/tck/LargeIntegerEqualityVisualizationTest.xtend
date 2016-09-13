package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.AlgebraTreeDrawer

class LargeIntegerEqualityVisualizationTest {

    val static AlgebraTreeDrawer drawer = new AlgebraTreeDrawer(true)
    
    /*
    Scenario: Does not lose precision
    */
    @Test
    def void testLargeIntegerEquality_01() {
        val container = RelalgParser.parse('''
        MATCH (p:Label)
        RETURN p.id
        ''')
        drawer.serialize(container, "LargeIntegerEquality")
    }

    /*
    Scenario: Handling inlined equality of large integer
    */
    @Test
    def void testLargeIntegerEquality_02() {
        val container = RelalgParser.parse('''
        MATCH (p:Label {id: 4611686018427387905})
        RETURN p.id
        ''')
        drawer.serialize(container, "LargeIntegerEquality")
    }

    /*
    Scenario: Handling explicit equality of large integer
    */
    @Test
    def void testLargeIntegerEquality_03() {
        val container = RelalgParser.parse('''
        MATCH (p:Label)
        WHERE p.id = 4611686018427387905
        RETURN p.id
        ''')
        drawer.serialize(container, "LargeIntegerEquality")
    }

    /*
    Scenario: Handling inlined equality of large integer, non-equal values
    */
    @Test
    def void testLargeIntegerEquality_04() {
        val container = RelalgParser.parse('''
        MATCH (p:Label {id : 4611686018427387900})
        RETURN p.id
        ''')
        drawer.serialize(container, "LargeIntegerEquality")
    }

    /*
    Scenario: Handling explicit equality of large integer, non-equal values
    */
    @Test
    def void testLargeIntegerEquality_05() {
        val container = RelalgParser.parse('''
        MATCH (p:Label)
        WHERE p.id = 4611686018427387900
        RETURN p.id
        ''')
        drawer.serialize(container, "LargeIntegerEquality")
    }

}
