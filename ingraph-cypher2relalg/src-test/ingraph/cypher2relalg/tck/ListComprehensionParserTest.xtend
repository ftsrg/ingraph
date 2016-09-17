package ingraph.cypher2.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class ListComprehensionParserTest {
    
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
        RelalgParser.parse('''
        MATCH p = (n)-->()
        RETURN [x IN collect(p) | head(nodes(x))] AS p
        ''')
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
        RelalgParser.parse('''
        MATCH p = (n:A)-->()
        WITH [x IN collect(p) | head(nodes(x))] AS p, count(n) AS c
        RETURN p, c
        ''')
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
        RelalgParser.parse('''
        MATCH (n)-->(b)
        WHERE n.prop IN [x IN labels(b) | lower(x)]
        RETURN b
        ''')
    }

}
