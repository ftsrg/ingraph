package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import org.junit.Test
import org.junit.experimental.categories.Category

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
    @Category(FailingTests)
    def void testOptionalMatch_01() {
        val cypher = CypherParser.parseString('''
        MATCH (p:Player)-[:PLAYS_FOR]->(team:Team)
        OPTIONAL MATCH (p)-[s:SUPPORTS]->(team)
        RETURN count(*) AS matches, s IS NULL AS optMatch
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatch_01")
        Cypher2Relalg.processCypher(cypher)
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
    @Category(FailingTests)
    def void testOptionalMatch_02() {
        val cypher = CypherParser.parseString('''
        MATCH (p:Player)-[:PLAYS_FOR]->(team:Team)
        OPTIONAL MATCH (p)-[s:SUPPORTS]->(team)
        RETURN count(*) AS matches, s IS NULL AS optMatch
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatch_02")
        Cypher2Relalg.processCypher(cypher)
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
    @Category(FailingTests)
    def void testOptionalMatch_03() {
        val cypher = CypherParser.parseString('''
        MATCH (p:Player)-[:PLAYS_FOR]->(team:Team)
        OPTIONAL MATCH (p)-[s:SUPPORTS]->(team)
        RETURN count(*) AS matches, s IS NULL AS optMatch
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatch_03")
        Cypher2Relalg.processCypher(cypher)
    }

}
