package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.CypherParser
import ingraph.optimization.transformations.SchemaInferencer
import ingraph.relalg2tex.RelalgTreeSerializer

class OptionalMatchVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer
    extension SchemaInferencer inferencer = new SchemaInferencer
    
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
        val container = CypherParser.parseString('''
        MATCH (p:Player)-[:PLAYS_FOR]->(team:Team)
        OPTIONAL MATCH (p)-[s:SUPPORTS]->(team)
        RETURN count(*) AS matches, s IS NULL AS optMatch
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "OptionalMatch_01")
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
        val container = CypherParser.parseString('''
        MATCH (p:Player)-[:PLAYS_FOR]->(team:Team)
        OPTIONAL MATCH (p)-[s:SUPPORTS]->(team)
        RETURN count(*) AS matches, s IS NULL AS optMatch
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "OptionalMatch_02")
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
        val container = CypherParser.parseString('''
        MATCH (p:Player)-[:PLAYS_FOR]->(team:Team)
        OPTIONAL MATCH (p)-[s:SUPPORTS]->(team)
        RETURN count(*) AS matches, s IS NULL AS optMatch
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "OptionalMatch_03")
    }

}
