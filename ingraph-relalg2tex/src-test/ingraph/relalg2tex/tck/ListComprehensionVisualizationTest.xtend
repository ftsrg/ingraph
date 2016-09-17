package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.RelalgTreeSerializer

class ListComprehensionVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer(true)
    
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
        val container = RelalgParser.parse('''
        MATCH p = (n)-->()
        RETURN [x IN collect(p) | head(nodes(x))] AS p
        ''')
        serializer.serialize(container, "ListComprehension_01")
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
        val container = RelalgParser.parse('''
        MATCH p = (n:A)-->()
        WITH [x IN collect(p) | head(nodes(x))] AS p, count(n) AS c
        RETURN p, c
        ''')
        serializer.serialize(container, "ListComprehension_02")
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
        val container = RelalgParser.parse('''
        MATCH (n)-->(b)
        WHERE n.prop IN [x IN labels(b) | lower(x)]
        RETURN b
        ''')
        serializer.serialize(container, "ListComprehension_03")
    }

}
