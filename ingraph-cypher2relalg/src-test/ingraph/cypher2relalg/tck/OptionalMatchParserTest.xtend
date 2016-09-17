package ingraph.cypher2.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class OptionalMatchParserTest {
    
    /*
    Scenario: Satisfies the open world assumption, relationships between same nodes
    Given an empty graph
    And having executed:
      """
      CREATE (a:Player), (b:Team)
      CREATE (a)-[:PLAYS_FOR]->(b),
             (a)-[:SUPPORTS]->(b)
      """
    */
    @Test
    def void testOptionalMatch_01() {
        RelalgParser.parse('''
        MATCH (p:Player)-[:PLAYS_FOR]->(team:Team)
        OPTIONAL MATCH (p)-[s:SUPPORTS]->(team)
        RETURN count(*) AS matches, s IS NULL AS optMatch
        ''')
    }

    /*
    Scenario: Satisfies the open world assumption, single relationship
    Given an empty graph
    And having executed:
      """
      CREATE (a:Player), (b:Team)
      CREATE (a)-[:PLAYS_FOR]->(b)
      """
    */
    @Test
    def void testOptionalMatch_02() {
        RelalgParser.parse('''
        MATCH (p:Player)-[:PLAYS_FOR]->(team:Team)
        OPTIONAL MATCH (p)-[s:SUPPORTS]->(team)
        RETURN count(*) AS matches, s IS NULL AS optMatch
        ''')
    }

    /*
    Scenario: Satisfies the open world assumption, relationships between different nodes
    Given an empty graph
    And having executed:
      """
      CREATE (a:Player), (b:Team), (c:Team)
      CREATE (a)-[:PLAYS_FOR]->(b),
             (a)-[:SUPPORTS]->(c)
      """
    */
    @Test
    def void testOptionalMatch_03() {
        RelalgParser.parse('''
        MATCH (p:Player)-[:PLAYS_FOR]->(team:Team)
        OPTIONAL MATCH (p)-[s:SUPPORTS]->(team)
        RETURN count(*) AS matches, s IS NULL AS optMatch
        ''')
    }

}
