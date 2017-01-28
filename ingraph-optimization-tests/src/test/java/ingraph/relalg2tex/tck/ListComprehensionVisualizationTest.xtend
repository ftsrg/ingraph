package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.inferencers.SchemaInferencer
import ingraph.relalg2tex.serializers.RelalgTreeSerializer

class ListComprehensionVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer
    extension SchemaInferencer inferencer = new SchemaInferencer
    
    /*
    Scenario: Returning a list comprehension
    Given an empty graph
    And having executed:
      """
      CREATE (a:A)
      CREATE (a)-[:T]->(:B),
             (a)-[:T]->(:C)
      """
    */
    @Test
    def void testListComprehension_01() {
        val container = Cypher2Relalg.processString('''
        MATCH p = (n)-->()
        RETURN [x IN collect(p) | head(nodes(x))] AS p
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/ListComprehension_01")
    }

    /*
    Scenario: Using a list comprehension in a WITH
    Given an empty graph
    And having executed:
      """
      CREATE (a:A)
      CREATE (a)-[:T]->(:B),
             (a)-[:T]->(:C)
      """
    */
    @Test
    def void testListComprehension_02() {
        val container = Cypher2Relalg.processString('''
        MATCH p = (n:A)-->()
        WITH [x IN collect(p) | head(nodes(x))] AS p, count(n) AS c
        RETURN p, c
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/ListComprehension_02")
    }

    /*
    Scenario: Using a list comprehension in a WHERE
    Given an empty graph
    And having executed:
      """
      CREATE (a:A {prop: 'c'})
      CREATE (a)-[:T]->(:B),
             (a)-[:T]->(:C)
      """
    */
    @Test
    def void testListComprehension_03() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)-->(b)
        WHERE n.prop IN [x IN labels(b) | lower(x)]
        RETURN b
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/ListComprehension_03")
    }

}
