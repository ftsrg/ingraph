package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.inferencers.BasicSchemaInferencer
import ingraph.relalg2tex.converters.relalgconverters.Relalg2TexTreeConverter

class MatchingSelfRelationshipsVisualizationTest {

    extension Relalg2TexTreeConverter converter = new Relalg2TexTreeConverter
    extension BasicSchemaInferencer inferencer = new BasicSchemaInferencer
    
    /*
    Scenario: Undirected match in self-relationship graph
    Given an empty graph
    And having executed:
      """
      CREATE (a:A)-[:LOOP]->(a)
      """
    */
    @Test
    def void testMatchingSelfRelationships_01() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)-[r]-(b)
        RETURN a, r, b
        ''')
        container.inferBasicSchema
        container.convert("tck/MatchingSelfRelationships_01")
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
    def void testMatchingSelfRelationships_02() {
        val container = Cypher2Relalg.processString('''
        MATCH ()--()
        RETURN count(*)
        ''')
        container.inferBasicSchema
        container.convert("tck/MatchingSelfRelationships_02")
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
    def void testMatchingSelfRelationships_03() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)-[r]-(n)
        RETURN n, r
        ''')
        container.inferBasicSchema
        container.convert("tck/MatchingSelfRelationships_03")
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
    def void testMatchingSelfRelationships_04() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)--(n)
        RETURN count(*)
        ''')
        container.inferBasicSchema
        container.convert("tck/MatchingSelfRelationships_04")
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
    def void testMatchingSelfRelationships_05() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)-[r]-(b)
        RETURN a, r, b
        ''')
        container.inferBasicSchema
        container.convert("tck/MatchingSelfRelationships_05")
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
    def void testMatchingSelfRelationships_06() {
        val container = Cypher2Relalg.processString('''
        MATCH ()--()
        RETURN count(*)
        ''')
        container.inferBasicSchema
        container.convert("tck/MatchingSelfRelationships_06")
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
    def void testMatchingSelfRelationships_07() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)-[r]->(b)
        RETURN a, r, b
        ''')
        container.inferBasicSchema
        container.convert("tck/MatchingSelfRelationships_07")
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
    def void testMatchingSelfRelationships_08() {
        val container = Cypher2Relalg.processString('''
        MATCH ()-->()
        RETURN count(*)
        ''')
        container.inferBasicSchema
        container.convert("tck/MatchingSelfRelationships_08")
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
    def void testMatchingSelfRelationships_09() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)-[r]->(n)
        RETURN n, r
        ''')
        container.inferBasicSchema
        container.convert("tck/MatchingSelfRelationships_09")
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
    def void testMatchingSelfRelationships_10() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)-->(n)
        RETURN count(*)
        ''')
        container.inferBasicSchema
        container.convert("tck/MatchingSelfRelationships_10")
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
    def void testMatchingSelfRelationships_11() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)-[r]-(n)
        RETURN count(r)
        ''')
        container.inferBasicSchema
        container.convert("tck/MatchingSelfRelationships_11")
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
    def void testMatchingSelfRelationships_12() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)-[r]-(n)
        RETURN count(DISTINCT r)
        ''')
        container.inferBasicSchema
        container.convert("tck/MatchingSelfRelationships_12")
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
    def void testMatchingSelfRelationships_13() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)-[r]->(b)
        RETURN a, r, b
        ''')
        container.inferBasicSchema
        container.convert("tck/MatchingSelfRelationships_13")
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
    def void testMatchingSelfRelationships_14() {
        val container = Cypher2Relalg.processString('''
        MATCH ()-->()
        RETURN count(*)
        ''')
        container.inferBasicSchema
        container.convert("tck/MatchingSelfRelationships_14")
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
    def void testMatchingSelfRelationships_15() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)-[r]->(n)
        RETURN count(r)
        ''')
        container.inferBasicSchema
        container.convert("tck/MatchingSelfRelationships_15")
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
    def void testMatchingSelfRelationships_16() {
        val container = Cypher2Relalg.processString('''
        MATCH (x:A)-[r1]->(y)-[r2]-(z)
        RETURN x, r1, y, r2, z
        ''')
        container.inferBasicSchema
        container.convert("tck/MatchingSelfRelationships_16")
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
    def void testMatchingSelfRelationships_17() {
        val container = Cypher2Relalg.processString('''
        MATCH (:A)-->()--()
        RETURN count(*)
        ''')
        container.inferBasicSchema
        container.convert("tck/MatchingSelfRelationships_17")
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
    def void testMatchingSelfRelationships_18() {
        val container = Cypher2Relalg.processString('''
        MATCH (x)-[r1]-(y)-[r2]-(z)
        RETURN x, r1, y, r2, z
        ''')
        container.inferBasicSchema
        container.convert("tck/MatchingSelfRelationships_18")
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
    def void testMatchingSelfRelationships_19() {
        val container = Cypher2Relalg.processString('''
        MATCH ()-[]-()-[]-()
        RETURN count(*)
        ''')
        container.inferBasicSchema
        container.convert("tck/MatchingSelfRelationships_19")
    }

}
