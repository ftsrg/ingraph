package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil

class ReturnAcceptance2ParserTest {
    
    /*
    Scenario: Accept valid Unicode literal
    Given any graph
    */
    @Test
    def void testReturnAcceptance2_05() {
        val cypher = CypherParser.parseString('''
        RETURN '\u01FF' AS a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_05")
        Cypher2Relalg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n
        LIMIT 0
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_06")
        Cypher2Relalg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n
        ORDER BY n
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_07")
        Cypher2Relalg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n.name, count(*) AS foo
        ORDER BY n.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_08")
        Cypher2Relalg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN DISTINCT n.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_09")
        Cypher2Relalg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH p = (a:Start)-->(b)
        RETURN *
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_10")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: `sqrt()` returning float values
    Given any graph
    */
    @Test
    def void testReturnAcceptance2_13() {
        val cypher = CypherParser.parseString('''
        RETURN sqrt(12.96)
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_13")
        Cypher2Relalg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH (me)-[r1:ATE]->()<-[r2:ATE]-(you)
        WHERE me.name = 'Michael'
        WITH me, count(DISTINCT r1) AS H1, count(DISTINCT r2) AS H2, you
        MATCH (me)-[r1:ATE]->()<-[r2:ATE]-(you)
        RETURN me, you, sum((1 - abs(r1.times / H1 - r2.times / H2)) * (r1.times + r2.times) / (H1 + H2)) AS sum
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_14")
        Cypher2Relalg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH ()-->()
        WITH 1 AS x
        MATCH ()-[r1]->()<--()
        RETURN sum(r1.times)
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_15")
        Cypher2Relalg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_16")
        Cypher2Relalg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        RETURN {a: 1, b: 'foo'}
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_17")
        Cypher2Relalg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH (a)
        RETURN exists(a.id), a IS NOT NULL
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_18")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Concatenating and returning the size of literal lists
    Given any graph
    */
    @Test
    def void testReturnAcceptance2_19() {
        val cypher = CypherParser.parseString('''
        RETURN size([[], []] + [[]]) AS l
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_19")
        Cypher2Relalg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH (a)
        RETURN a.count
        ORDER BY a.count
        SKIP 10
        LIMIT 10
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_21")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: `substring()` with default second argument
    Given any graph
    */
    @Test
    def void testReturnAcceptance2_22() {
        val cypher = CypherParser.parseString('''
        RETURN substring('0123456789', 1) AS s
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_22")
        Cypher2Relalg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN *
        ORDER BY n.id
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_23")
        Cypher2Relalg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN DISTINCT n.id AS id
        ORDER BY id DESC
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_24")
        Cypher2Relalg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN DISTINCT n
        ORDER BY n.id
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_25")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Arithmetic expressions should propagate null values
    Given any graph
    */
    @Test
    def void testReturnAcceptance2_26() {
        val cypher = CypherParser.parseString('''
        RETURN 1 + (2 - (3 * (4 / (5 ^ (6 % null))))) AS a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_26")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Indexing into nested literal lists
    Given any graph
    */
    @Test
    def void testReturnAcceptance2_27() {
        val cypher = CypherParser.parseString('''
        RETURN [[1]][0][0]
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_27")
        Cypher2Relalg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH (a)
        RETURN a.id AS a, a.id
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_28")
        Cypher2Relalg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH (a)
        RETURN a, count(a) + 3
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_29")
        Cypher2Relalg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WITH a.a AS a, count(*) AS count
        RETURN count
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_31")
        Cypher2Relalg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH (person:Person)<--(message)<-[like]-(:Person)
        WITH like.creationDate AS likeTime, person AS person
        ORDER BY likeTime, message.id
        WITH head(collect({likeTime: likeTime})) AS latestLike, person AS person
        RETURN latestLike.likeTime AS likeTime
        ORDER BY likeTime
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_32")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Concatenating lists of same type
    Given any graph
    */
    @Test
    def void testReturnAcceptance2_33() {
        val cypher = CypherParser.parseString('''
        RETURN [1, 10, 100] + [4, 5] AS foo
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_33")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Appending lists of same type
    Given any graph
    */
    @Test
    def void testReturnAcceptance2_34() {
        val cypher = CypherParser.parseString('''
        RETURN [false, true] + false AS foo
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_34")
        Cypher2Relalg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN count(DISTINCT {foo: n.list}) AS count
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_35")
        Cypher2Relalg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH (n)
        WITH DISTINCT {foo: n.list} AS map
        RETURN count(*)
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_36")
        Cypher2Relalg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN count(DISTINCT {foo: [[n.list, n.list], [n.list, n.list]]}) AS count
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_37")
        Cypher2Relalg.processCypher(cypher)
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
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN count(DISTINCT {foo: [{bar: n.list}, {baz: {apa: n.list}}]}) AS count
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ReturnAcceptance2_38")
        Cypher2Relalg.processCypher(cypher)
    }

}
