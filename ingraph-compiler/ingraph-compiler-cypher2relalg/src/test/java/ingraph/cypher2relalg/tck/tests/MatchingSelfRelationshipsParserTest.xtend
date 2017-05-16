package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import ingraph.relalg.util.RelalgUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class MatchingSelfRelationshipsParserTest {
    
    /*
    Scenario: Undirected match in self-relationship graph
    Given an empty graph
    And having executed:
      """
      CREATE (a:A)-[:LOOP]->(a)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchingSelfRelationships_01() {
        val cypher = CypherParser.parseString('''
        MATCH (a)-[r]-(b)
        RETURN a, r, b
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/MatchingSelfRelationships_01")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/MatchingSelfRelationships_01")
    }

    /*
    Scenario: Undirected match in self-relationship graph, count
    Given an empty graph
    And having executed:
      """
      CREATE (a:A)-[:LOOP]->(a)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchingSelfRelationships_02() {
        val cypher = CypherParser.parseString('''
        MATCH ()--()
        RETURN count(*)
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/MatchingSelfRelationships_02")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/MatchingSelfRelationships_02")
    }

    /*
    Scenario: Undirected match of self-relationship in self-relationship graph
    Given an empty graph
    And having executed:
      """
      CREATE (a:A)-[:LOOP]->(a)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchingSelfRelationships_03() {
        val cypher = CypherParser.parseString('''
        MATCH (n)-[r]-(n)
        RETURN n, r
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/MatchingSelfRelationships_03")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/MatchingSelfRelationships_03")
    }

    /*
    Scenario: Undirected match of self-relationship in self-relationship graph, count
    Given an empty graph
    And having executed:
      """
      CREATE (a:A)-[:LOOP]->(a)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchingSelfRelationships_04() {
        val cypher = CypherParser.parseString('''
        MATCH (n)--(n)
        RETURN count(*)
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/MatchingSelfRelationships_04")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/MatchingSelfRelationships_04")
    }

    /*
    Scenario: Undirected match on simple relationship graph
    Given an empty graph
    And having executed:
      """
      CREATE (:A)-[:LOOP]->(:B)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchingSelfRelationships_05() {
        val cypher = CypherParser.parseString('''
        MATCH (a)-[r]-(b)
        RETURN a, r, b
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/MatchingSelfRelationships_05")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/MatchingSelfRelationships_05")
    }

    /*
    Scenario: Undirected match on simple relationship graph, count
    Given an empty graph
    And having executed:
      """
      CREATE (:A)-[:LOOP]->(:B)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchingSelfRelationships_06() {
        val cypher = CypherParser.parseString('''
        MATCH ()--()
        RETURN count(*)
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/MatchingSelfRelationships_06")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/MatchingSelfRelationships_06")
    }

    /*
    Scenario: Directed match on self-relationship graph
    Given an empty graph
    And having executed:
      """
      CREATE (a:A)-[:LOOP]->(a)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchingSelfRelationships_07() {
        val cypher = CypherParser.parseString('''
        MATCH (a)-[r]->(b)
        RETURN a, r, b
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/MatchingSelfRelationships_07")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/MatchingSelfRelationships_07")
    }

    /*
    Scenario: Directed match on self-relationship graph, count
    Given an empty graph
    And having executed:
      """
      CREATE (a:A)-[:LOOP]->(a)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchingSelfRelationships_08() {
        val cypher = CypherParser.parseString('''
        MATCH ()-->()
        RETURN count(*)
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/MatchingSelfRelationships_08")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/MatchingSelfRelationships_08")
    }

    /*
    Scenario: Directed match of self-relationship on self-relationship graph
    Given an empty graph
    And having executed:
      """
      CREATE (a:A)-[:LOOP]->(a)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchingSelfRelationships_09() {
        val cypher = CypherParser.parseString('''
        MATCH (n)-[r]->(n)
        RETURN n, r
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/MatchingSelfRelationships_09")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/MatchingSelfRelationships_09")
    }

    /*
    Scenario: Directed match of self-relationship on self-relationship graph, count
    Given an empty graph
    And having executed:
      """
      CREATE (a:A)-[:LOOP]->(a)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchingSelfRelationships_10() {
        val cypher = CypherParser.parseString('''
        MATCH (n)-->(n)
        RETURN count(*)
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/MatchingSelfRelationships_10")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/MatchingSelfRelationships_10")
    }

    /*
    Scenario: Counting undirected self-relationships in self-relationship graph
    Given an empty graph
    And having executed:
      """
      CREATE (a:A)-[:LOOP]->(a)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchingSelfRelationships_11() {
        val cypher = CypherParser.parseString('''
        MATCH (n)-[r]-(n)
        RETURN count(r)
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/MatchingSelfRelationships_11")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/MatchingSelfRelationships_11")
    }

    /*
    Scenario: Counting distinct undirected self-relationships in self-relationship graph
    Given an empty graph
    And having executed:
      """
      CREATE (a:A)-[:LOOP]->(a)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchingSelfRelationships_12() {
        val cypher = CypherParser.parseString('''
        MATCH (n)-[r]-(n)
        RETURN count(DISTINCT r)
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/MatchingSelfRelationships_12")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/MatchingSelfRelationships_12")
    }

    /*
    Scenario: Directed match of a simple relationship
    Given an empty graph
    And having executed:
      """
      CREATE (:A)-[:LOOP]->(:B)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchingSelfRelationships_13() {
        val cypher = CypherParser.parseString('''
        MATCH (a)-[r]->(b)
        RETURN a, r, b
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/MatchingSelfRelationships_13")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/MatchingSelfRelationships_13")
    }

    /*
    Scenario: Directed match of a simple relationship, count
    Given an empty graph
    And having executed:
      """
      CREATE (:A)-[:LOOP]->(:B)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchingSelfRelationships_14() {
        val cypher = CypherParser.parseString('''
        MATCH ()-->()
        RETURN count(*)
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/MatchingSelfRelationships_14")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/MatchingSelfRelationships_14")
    }

    /*
    Scenario: Counting directed self-relationships
    Given an empty graph
    And having executed:
      """
      CREATE (a:A)-[:LOOP]->(a),
             ()-[:T]->()
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchingSelfRelationships_15() {
        val cypher = CypherParser.parseString('''
        MATCH (n)-[r]->(n)
        RETURN count(r)
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/MatchingSelfRelationships_15")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/MatchingSelfRelationships_15")
    }

    /*
    Scenario: Mixing directed and undirected pattern parts with self-relationship, simple
    Given an empty graph
    And having executed:
      """
      CREATE (:A)-[:T1]->(l:Looper),
             (l)-[:LOOP]->(l),
             (l)-[:T2]->(:B)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchingSelfRelationships_16() {
        val cypher = CypherParser.parseString('''
        MATCH (x:A)-[r1]->(y)-[r2]-(z)
        RETURN x, r1, y, r2, z
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/MatchingSelfRelationships_16")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/MatchingSelfRelationships_16")
    }

    /*
    Scenario: Mixing directed and undirected pattern parts with self-relationship, count
    Given an empty graph
    And having executed:
      """
      CREATE (:A)-[:T1]->(l:Looper),
             (l)-[:LOOP]->(l),
             (l)-[:T2]->(:B)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchingSelfRelationships_17() {
        val cypher = CypherParser.parseString('''
        MATCH (:A)-->()--()
        RETURN count(*)
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/MatchingSelfRelationships_17")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/MatchingSelfRelationships_17")
    }

    /*
    Scenario: Mixing directed and undirected pattern parts with self-relationship, undirected
    Given an empty graph
    And having executed:
      """
      CREATE (:A)-[:T1]->(l:Looper),
             (l)-[:LOOP]->(l),
             (l)-[:T2]->(:B)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchingSelfRelationships_18() {
        val cypher = CypherParser.parseString('''
        MATCH (x)-[r1]-(y)-[r2]-(z)
        RETURN x, r1, y, r2, z
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/MatchingSelfRelationships_18")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/MatchingSelfRelationships_18")
    }

    /*
    Scenario: Mixing directed and undirected pattern parts with self-relationship, undirected count
    Given an empty graph
    And having executed:
      """
      CREATE (:A)-[:T1]->(l:Looper),
             (l)-[:LOOP]->(l),
             (l)-[:T2]->(:B)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchingSelfRelationships_19() {
        val cypher = CypherParser.parseString('''
        MATCH ()-[]-()-[]-()
        RETURN count(*)
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/MatchingSelfRelationships_19")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/MatchingSelfRelationships_19")
    }

}
