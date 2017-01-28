package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.inferencers.SchemaInferencer
import ingraph.relalg2tex.serializers.RelalgTreeSerializer

class TernaryLogicAcceptanceVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer
    extension SchemaInferencer inferencer = new SchemaInferencer
    
    /*
    Scenario: The inverse of a null is a null
    */
    @Test
    def void testTernaryLogicAcceptance_01() {
        val container = Cypher2Relalg.processString('''
        RETURN NOT null AS value
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/TernaryLogicAcceptance_01")
    }

    /*
    Scenario: A literal null IS null
    */
    @Test
    def void testTernaryLogicAcceptance_02() {
        val container = Cypher2Relalg.processString('''
        RETURN null IS NULL AS value
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/TernaryLogicAcceptance_02")
    }

    /*
    Scenario: A literal null is not IS NOT null
    */
    @Test
    def void testTernaryLogicAcceptance_03() {
        val container = Cypher2Relalg.processString('''
        RETURN null IS NOT NULL AS value
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/TernaryLogicAcceptance_03")
    }

    /*
    Scenario: It is unknown - i.e. null - if a null is equal to a null
    */
    @Test
    def void testTernaryLogicAcceptance_04() {
        val container = Cypher2Relalg.processString('''
        RETURN null = null AS value
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/TernaryLogicAcceptance_04")
    }

    /*
    Scenario: It is unknown - i.e. null - if a null is not equal to a null
    */
    @Test
    def void testTernaryLogicAcceptance_05() {
        val container = Cypher2Relalg.processString('''
        RETURN null <> null AS value
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/TernaryLogicAcceptance_05")
    }

}
