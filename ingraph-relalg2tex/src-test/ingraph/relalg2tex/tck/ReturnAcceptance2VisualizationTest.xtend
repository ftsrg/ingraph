package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.util.SchemaInferencer
import ingraph.relalg2tex.RelalgTreeSerializer

class ReturnAcceptance2VisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer
    extension SchemaInferencer inferencer = new SchemaInferencer
    
    /*
    Scenario: Accept valid Unicode literal
    Given any graph
    */
    @Test
    def void testReturnAcceptance2_05() {
        val container = Cypher2Relalg.processString('''
        RETURN '\u01FF' AS a
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_05")
    }

    /*
    Scenario: LIMIT 0 should return an empty result
    Given an empty graph
    And having executed:
      """
      CREATE (), (), ()
      """
    */
    @Test
    def void testReturnAcceptance2_06() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)
        RETURN n
        LIMIT 0
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_06")
    }

    /*
    Scenario: Fail when ordering nodes
    Given an empty graph
    And having executed:
      """
      CREATE (), ()
      """
    */
    @Test
    def void testReturnAcceptance2_07() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)
        RETURN n
        ORDER BY n
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_07")
    }

    /*
    Scenario: Ordering with aggregation
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'nisse'})
      """
    */
    @Test
    def void testReturnAcceptance2_08() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)
        RETURN n.name, count(*) AS foo
        ORDER BY n.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_08")
    }

    /*
    Scenario: DISTINCT on nullable values
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'Florescu'}), (), ()
      """
    */
    @Test
    def void testReturnAcceptance2_09() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)
        RETURN DISTINCT n.name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_09")
    }

    /*
    Scenario: Return all variables
    Given an empty graph
    And having executed:
      """
      CREATE (:Start)-[:T]->()
      """
    */
    @Test
    def void testReturnAcceptance2_10() {
        val container = Cypher2Relalg.processString('''
        MATCH p = (a:Start)-->(b)
        RETURN *
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_10")
    }

    /*
    Scenario: `sqrt()` returning float values
    Given any graph
    */
    @Test
    def void testReturnAcceptance2_13() {
        val container = Cypher2Relalg.processString('''
        RETURN sqrt(12.96)
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_13")
    }

    /*
    Scenario: Arithmetic expressions inside aggregation
    Given an empty graph
    And having executed:
      """
      CREATE (andres {name: 'Andres'}),
             (michael {name: 'Michael'}),
             (peter {name: 'Peter'}),
             (bread {type: 'Bread'}),
             (veggies {type: 'Veggies'}),
             (meat {type: 'Meat'})
      CREATE (andres)-[:ATE {times: 10}]->(bread),
             (andres)-[:ATE {times: 8}]->(veggies),
             (michael)-[:ATE {times: 4}]->(veggies),
             (michael)-[:ATE {times: 6}]->(bread),
             (michael)-[:ATE {times: 9}]->(meat),
             (peter)-[:ATE {times: 7}]->(veggies),
             (peter)-[:ATE {times: 7}]->(bread),
             (peter)-[:ATE {times: 4}]->(meat)
      """
    */
    @Test
    def void testReturnAcceptance2_14() {
        val container = Cypher2Relalg.processString('''
        MATCH (me)-[r1:ATE]->()<-[r2:ATE]-(you)
        WHERE me.name = 'Michael'
        WITH me, count(DISTINCT r1) AS H1, count(DISTINCT r2) AS H2, you
        MATCH (me)-[r1:ATE]->()<-[r2:ATE]-(you)
        RETURN me, you, sum((1 - abs(r1.times / H1 - r2.times / H2)) * (r1.times + r2.times) / (H1 + H2)) AS sum
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_14")
    }

    /*
    Scenario: Matching and disregarding output, then matching again
    Given an empty graph
    And having executed:
      """
      CREATE (andres {name: 'Andres'}),
             (michael {name: 'Michael'}),
             (peter {name: 'Peter'}),
             (bread {type: 'Bread'}),
             (veggies {type: 'Veggies'}),
             (meat {type: 'Meat'})
      CREATE (andres)-[:ATE {times: 10}]->(bread),
             (andres)-[:ATE {times: 8}]->(veggies),
             (michael)-[:ATE {times: 4}]->(veggies),
             (michael)-[:ATE {times: 6}]->(bread),
             (michael)-[:ATE {times: 9}]->(meat),
             (peter)-[:ATE {times: 7}]->(veggies),
             (peter)-[:ATE {times: 7}]->(bread),
             (peter)-[:ATE {times: 4}]->(meat)
      """
    */
    @Test
    def void testReturnAcceptance2_15() {
        val container = Cypher2Relalg.processString('''
        MATCH ()-->()
        WITH 1 AS x
        MATCH ()-[r1]->()<--()
        RETURN sum(r1.times)
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_15")
    }

    /*
    Scenario: Returning a list property
    Given an empty graph
    And having executed:
      """
      CREATE ({foo: [1, 2, 3]})
      """
    */
    @Test
    def void testReturnAcceptance2_16() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)
        RETURN n
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_16")
    }

    /*
    Scenario: Returning a projected map
    Given an empty graph
    And having executed:
      """
      CREATE ({foo: [1, 2, 3]})
      """
    */
    @Test
    def void testReturnAcceptance2_17() {
        val container = Cypher2Relalg.processString('''
        RETURN {a: 1, b: 'foo'}
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_17")
    }

    /*
    Scenario: Returning an expression
    Given an empty graph
    And having executed:
      """
      CREATE ()
      """
    */
    @Test
    def void testReturnAcceptance2_18() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        RETURN exists(a.id), a IS NOT NULL
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_18")
    }

    /*
    Scenario: Concatenating and returning the size of literal lists
    Given any graph
    */
    @Test
    def void testReturnAcceptance2_19() {
        val container = Cypher2Relalg.processString('''
        RETURN size([[], []] + [[]]) AS l
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_19")
    }

    /*
    Scenario: Limiting amount of rows when there are fewer left than the LIMIT argument
    Given an empty graph
    And having executed:
      """
      UNWIND range(0, 15) AS i
      CREATE ({count: i})
      """
    */
    @Test
    def void testReturnAcceptance2_21() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        RETURN a.count
        ORDER BY a.count
        SKIP 10
        LIMIT 10
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_21")
    }

    /*
    Scenario: `substring()` with default second argument
    Given any graph
    */
    @Test
    def void testReturnAcceptance2_22() {
        val container = Cypher2Relalg.processString('''
        RETURN substring('0123456789', 1) AS s
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_22")
    }

    /*
    Scenario: Returning all variables with ordering
    Given an empty graph
    And having executed:
      """
      CREATE ({id: 1}), ({id: 10})
      """
    */
    @Test
    def void testReturnAcceptance2_23() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)
        RETURN *
        ORDER BY n.id
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_23")
    }

    /*
    Scenario: Using aliased DISTINCT expression in ORDER BY
    Given an empty graph
    And having executed:
      """
      CREATE ({id: 1}), ({id: 10})
      """
    */
    @Test
    def void testReturnAcceptance2_24() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)
        RETURN DISTINCT n.id AS id
        ORDER BY id DESC
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_24")
    }

    /*
    Scenario: Returned columns do not change from using ORDER BY
    Given an empty graph
    And having executed:
      """
      CREATE ({id: 1}), ({id: 10})
      """
    */
    @Test
    def void testReturnAcceptance2_25() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)
        RETURN DISTINCT n
        ORDER BY n.id
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_25")
    }

    /*
    Scenario: Arithmetic expressions should propagate null values
    Given any graph
    */
    @Test
    def void testReturnAcceptance2_26() {
        val container = Cypher2Relalg.processString('''
        RETURN 1 + (2 - (3 * (4 / (5 ^ (6 % null))))) AS a
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_26")
    }

    /*
    Scenario: Indexing into nested literal lists
    Given any graph
    */
    @Test
    def void testReturnAcceptance2_27() {
        val container = Cypher2Relalg.processString('''
        RETURN [[1]][0][0]
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_27")
    }

    /*
    Scenario: Aliasing expressions
    Given an empty graph
    And having executed:
      """
      CREATE ({id: 42})
      """
    */
    @Test
    def void testReturnAcceptance2_28() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        RETURN a.id AS a, a.id
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_28")
    }

    /*
    Scenario: Projecting an arithmetic expression with aggregation
    Given an empty graph
    And having executed:
      """
      CREATE ({id: 42})
      """
    */
    @Test
    def void testReturnAcceptance2_29() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        RETURN a, count(a) + 3
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_29")
    }

    /*
    Scenario: Aggregating by a list property has a correct definition of equality
    Given an empty graph
    And having executed:
      """
      CREATE ({a: [1, 2, 3]}), ({a: [1, 2, 3]})
      """
    */
    @Test
    def void testReturnAcceptance2_31() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        WITH a.a AS a, count(*) AS count
        RETURN count
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_31")
    }

    /*
    Scenario: Reusing variable names
    Given an empty graph
    And having executed:
      """
      CREATE (a:Person), (b:Person), (m:Message {id: 10})
      CREATE (a)-[:LIKE {creationDate: 20160614}]->(m)-[:POSTED_BY]->(b)
      """
    */
    @Test
    def void testReturnAcceptance2_32() {
        val container = Cypher2Relalg.processString('''
        MATCH (person:Person)<--(message)<-[like]-(:Person)
        WITH like.creationDate AS likeTime, person AS person
        ORDER BY likeTime, message.id
        WITH head(collect({likeTime: likeTime})) AS latestLike, person AS person
        RETURN latestLike.likeTime AS likeTime
        ORDER BY likeTime
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_32")
    }

    /*
    Scenario: Concatenating lists of same type
    Given any graph
    */
    @Test
    def void testReturnAcceptance2_33() {
        val container = Cypher2Relalg.processString('''
        RETURN [1, 10, 100] + [4, 5] AS foo
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_33")
    }

    /*
    Scenario: Appending lists of same type
    Given any graph
    */
    @Test
    def void testReturnAcceptance2_34() {
        val container = Cypher2Relalg.processString('''
        RETURN [false, true] + false AS foo
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_34")
    }

    /*
    Scenario: DISTINCT inside aggregation should work with lists in maps
    Given an empty graph
    And having executed:
      """
      CREATE ({list: ['A', 'B']}), ({list: ['A', 'B']})
      """
    */
    @Test
    def void testReturnAcceptance2_35() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)
        RETURN count(DISTINCT {foo: n.list}) AS count
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_35")
    }

    /*
    Scenario: Handling DISTINCT with lists in maps
    Given an empty graph
    And having executed:
      """
      CREATE ({list: ['A', 'B']}), ({list: ['A', 'B']})
      """
    */
    @Test
    def void testReturnAcceptance2_36() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)
        WITH DISTINCT {foo: n.list} AS map
        RETURN count(*)
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_36")
    }

    /*
    Scenario: DISTINCT inside aggregation should work with nested lists in maps
    Given an empty graph
    And having executed:
      """
      CREATE ({list: ['A', 'B']}), ({list: ['A', 'B']})
      """
    */
    @Test
    def void testReturnAcceptance2_37() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)
        RETURN count(DISTINCT {foo: [[n.list, n.list], [n.list, n.list]]}) AS count
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_37")
    }

    /*
    Scenario: DISTINCT inside aggregation should work with nested lists of maps in maps
    Given an empty graph
    And having executed:
      """
      CREATE ({list: ['A', 'B']}), ({list: ['A', 'B']})
      """
    */
    @Test
    def void testReturnAcceptance2_38() {
        val container = Cypher2Relalg.processString('''
        MATCH (n)
        RETURN count(DISTINCT {foo: [{bar: n.list}, {baz: {apa: n.list}}]}) AS count
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "ReturnAcceptance2_38")
    }

}
