package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.RelalgTreeSerializer

class LargeIntegerEqualityVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer
    
    /*
    Scenario: Does not lose precision
    */
    @Test
    def void testLargeIntegerEquality_01() {
        val container = RelalgParser.parse('''
        MATCH (p:Label)
        RETURN p.id
        ''')
        serializer.serialize(container, "LargeIntegerEquality_01")
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
        serializer.serialize(container, "LargeIntegerEquality_02")
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
        serializer.serialize(container, "LargeIntegerEquality_03")
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
        serializer.serialize(container, "LargeIntegerEquality_04")
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
        serializer.serialize(container, "LargeIntegerEquality_05")
    }

}
