package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.inferencers.SchemaInferencer
import ingraph.relalg2tex.serializers.RelalgTreeSerializer

class SkipLimitAcceptanceVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer
    extension SchemaInferencer inferencer = new SchemaInferencer
    
    /*
    Scenario: SKIP with an expression that does not depend on variables
    And having executed:
      """
      UNWIND range(1, 10) AS i
      CREATE ({nr: i})
      """
    */
    @Test
    def void testSkipLimitAcceptance_01() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)
        WITH n SKIP toInteger(rand()*9)
        WITH count(*) AS count
        RETURN count > 0 AS nonEmpty
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/SkipLimitAcceptance_01")
    }

    /*
    Scenario: LIMIT with an expression that does not depend on variables
    And having executed:
      """
      UNWIND range(1, 3) AS i
      CREATE ({nr: i})
      """
    */
    @Test
    def void testSkipLimitAcceptance_02() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)
        WITH n LIMIT toInteger(ceil(1.7))
        RETURN count(*) AS count
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/SkipLimitAcceptance_02")
    }

}
