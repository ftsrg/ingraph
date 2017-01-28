package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.inferencers.SchemaInferencer
import ingraph.relalg2tex.serializers.RelalgTreeSerializer

class LargeIntegerEqualityVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer
    extension SchemaInferencer inferencer = new SchemaInferencer
    
    /*
    Scenario: Does not lose precision
    */
    @Test
    def void testLargeIntegerEquality_01() {
        val container = Cypher2Relalg.processString('''
        MATCH (p:Label)
        RETURN p.id
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/LargeIntegerEquality_01")
    }

    /*
    Scenario: Handling inlined equality of large integer
    */
    @Test
    def void testLargeIntegerEquality_02() {
        val container = Cypher2Relalg.processString('''
        MATCH (p:Label {id: 4611686018427387905})
        RETURN p.id
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/LargeIntegerEquality_02")
    }

    /*
    Scenario: Handling explicit equality of large integer
    */
    @Test
    def void testLargeIntegerEquality_03() {
        val container = Cypher2Relalg.processString('''
        MATCH (p:Label)
        WHERE p.id = 4611686018427387905
        RETURN p.id
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/LargeIntegerEquality_03")
    }

    /*
    Scenario: Handling inlined equality of large integer, non-equal values
    */
    @Test
    def void testLargeIntegerEquality_04() {
        val container = Cypher2Relalg.processString('''
        MATCH (p:Label {id : 4611686018427387900})
        RETURN p.id
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/LargeIntegerEquality_04")
    }

    /*
    Scenario: Handling explicit equality of large integer, non-equal values
    */
    @Test
    def void testLargeIntegerEquality_05() {
        val container = Cypher2Relalg.processString('''
        MATCH (p:Label)
        WHERE p.id = 4611686018427387900
        RETURN p.id
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/LargeIntegerEquality_05")
    }

}
