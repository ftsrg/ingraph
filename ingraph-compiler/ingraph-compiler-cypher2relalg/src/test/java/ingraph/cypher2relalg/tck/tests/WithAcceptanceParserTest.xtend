package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import ingraph.relalg.util.RelalgUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class WithAcceptanceParserTest {
    
    /*
    Scenario: Passing on pattern nodes
    Given an empty graph
    And having executed:
      """
      CREATE (:A)-[:REL]->(:B)
      """
    */
    @Test
    @Category(FailingTests)
    def void testWithAcceptance_01() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        WITH a
        MATCH (a)-->(b)
        RETURN *
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/WithAcceptance_01")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/WithAcceptance_01")
    }

    /*
    Scenario: ORDER BY and LIMIT can be used
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (), (), (),
             (a)-[:REL]->()
      """
    */
    @Test
    @Category(FailingTests)
    def void testWithAcceptance_02() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        WITH a
        ORDER BY a.name
        LIMIT 1
        MATCH (a)-->(b)
        RETURN a
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/WithAcceptance_02")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/WithAcceptance_02")
    }

    /*
    Scenario: No dependencies between the query parts
    Given an empty graph
    And having executed:
      """
      CREATE (:A), (:B)
      """
    */
    @Test
    @Category(FailingTests)
    def void testWithAcceptance_03() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WITH a
        MATCH (b)
        RETURN a, b
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/WithAcceptance_03")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/WithAcceptance_03")
    }

    /*
    Scenario: Aliasing
    Given an empty graph
    And having executed:
      """
      CREATE (:Begin {prop: 42}),
             (:End {prop: 42}),
             (:End {prop: 3})
      """
    */
    @Test
    @Category(FailingTests)
    def void testWithAcceptance_04() {
        val cypher = CypherParser.parseString('''
        MATCH (a:Begin)
        WITH a.prop AS property
        MATCH (b:End)
        WHERE property = b.prop
        RETURN b
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/WithAcceptance_04")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/WithAcceptance_04")
    }

    /*
    Scenario: Handle dependencies across WITH
    Given an empty graph
    And having executed:
      """
      CREATE (a:End {prop: 42, id: 0}),
             (:End {prop: 3}),
             (:Begin {prop: a.id})
      """
    */
    @Test
    @Category(FailingTests)
    def void testWithAcceptance_05() {
        val cypher = CypherParser.parseString('''
        MATCH (a:Begin)
        WITH a.prop AS property
        LIMIT 1
        MATCH (b)
        WHERE b.id = property
        RETURN b
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/WithAcceptance_05")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/WithAcceptance_05")
    }

    /*
    Scenario: Handle dependencies across WITH with SKIP
    Given an empty graph
    And having executed:
      """
      CREATE (a {prop: 'A', key: 0, id: 0}),
             ({prop: 'B', key: a.id, id: 1}),
             ({prop: 'C', key: 0, id: 2})
      """
    */
    @Test
    @Category(FailingTests)
    def void testWithAcceptance_06() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WITH a.prop AS property, a.key AS idToUse
        ORDER BY property
        SKIP 1
        MATCH (b)
        WHERE b.id = idToUse
        RETURN DISTINCT b
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/WithAcceptance_06")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/WithAcceptance_06")
    }

    /*
    Scenario: WHERE after WITH should filter results
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'A'}),
             ({name: 'B'}),
             ({name: 'C'})
      """
    */
    @Test
    @Category(FailingTests)
    def void testWithAcceptance_07() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WITH a
        WHERE a.name = 'B'
        RETURN a
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/WithAcceptance_07")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/WithAcceptance_07")
    }

    /*
    Scenario: WHERE after WITH can filter on top of an aggregation
    Given an empty graph
    And having executed:
      """
      CREATE (a {name: 'A'}),
             (b {name: 'B'})
      CREATE (a)-[:REL]->(),
             (a)-[:REL]->(),
             (a)-[:REL]->(),
             (b)-[:REL]->()
      """
    */
    @Test
    @Category(FailingTests)
    def void testWithAcceptance_08() {
        val cypher = CypherParser.parseString('''
        MATCH (a)-->()
        WITH a, count(*) AS relCount
        WHERE relCount > 1
        RETURN a
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/WithAcceptance_08")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/WithAcceptance_08")
    }

    /*
    Scenario: ORDER BY on an aggregating key
    Given an empty graph
    And having executed:
      """
      CREATE ({bar: 'A'}),
             ({bar: 'A'}),
             ({bar: 'B'})
      """
    */
    @Test
    @Category(FailingTests)
    def void testWithAcceptance_09() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WITH a.bar AS bars, count(*) AS relCount
        ORDER BY a.bar
        RETURN *
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/WithAcceptance_09")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/WithAcceptance_09")
    }

    /*
    Scenario: ORDER BY a DISTINCT column
    Given an empty graph
    And having executed:
      """
      CREATE ({bar: 'A'}),
             ({bar: 'A'}),
             ({bar: 'B'})
      """
    */
    @Test
    @Category(FailingTests)
    def void testWithAcceptance_10() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WITH DISTINCT a.bar AS bars
        ORDER BY a.bar
        RETURN *
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/WithAcceptance_10")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/WithAcceptance_10")
    }

    /*
    Scenario: WHERE on a DISTINCT column
    Given an empty graph
    And having executed:
      """
      CREATE ({bar: 'A'}),
             ({bar: 'A'}),
             ({bar: 'B'})
      """
    */
    @Test
    @Category(FailingTests)
    def void testWithAcceptance_11() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WITH DISTINCT a.bar AS bars
        WHERE a.bar = 'B'
        RETURN *
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/WithAcceptance_11")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/WithAcceptance_11")
    }

    /*
    Scenario: A simple pattern with one bound endpoint
    Given an empty graph
    And having executed:
      """
      CREATE (:A)-[:REL]->(:B)
      """
    */
    @Test
    @Category(FailingTests)
    def void testWithAcceptance_12() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)-[r:REL]->(b:B)
        WITH a AS b, b AS tmp, r AS r
        WITH b AS a, r
        LIMIT 1
        MATCH (a)-[r]->(b)
        RETURN a, r, b
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/WithAcceptance_12")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/WithAcceptance_12")
    }

    /*
    Scenario: Null handling
    Given an empty graph
    */
    @Test
    @Category(FailingTests)
    def void testWithAcceptance_13() {
        val cypher = CypherParser.parseString('''
        OPTIONAL MATCH (a:Start)
        WITH a
        MATCH (a)-->(b)
        RETURN *
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/WithAcceptance_13")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/WithAcceptance_13")
    }

    /*
    Scenario: Nested maps
    Given an empty graph
    */
    @Test
    @Category(FailingTests)
    def void testWithAcceptance_14() {
        val cypher = CypherParser.parseString('''
        WITH {foo: {bar: 'baz'}} AS nestedMap
        RETURN nestedMap.foo.bar
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/WithAcceptance_14")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/WithAcceptance_14")
    }

    /*
    Scenario: Connected components succeeding WITH
    Given an empty graph
    And having executed:
      """
      CREATE (:A)-[:REL]->(:X)
      CREATE (:B)
      """
    */
    @Test
    @Category(FailingTests)
    def void testWithAcceptance_15() {
        val cypher = CypherParser.parseString('''
        MATCH (n:A)
        WITH n
        LIMIT 1
        MATCH (m:B), (n)-->(x:X)
        RETURN *
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/WithAcceptance_15")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/WithAcceptance_15")
    }

    /*
    Scenario: Single WITH using a predicate and aggregation
    Given an empty graph
    And having executed:
      """
      CREATE ({prop: 43}), ({prop: 42})
      """
    */
    @Test
    @Category(FailingTests)
    def void testWithAcceptance_16() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        WITH n
        WHERE n.prop = 42
        RETURN count(*)
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/WithAcceptance_16")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/WithAcceptance_16")
    }

    /*
    Scenario: Multiple WITHs using a predicate and aggregation
    Given an empty graph
    And having executed:
      """
      CREATE (a {name: 'David'}),
             (b {name: 'Other'}),
             (c {name: 'NotOther'}),
             (d {name: 'NotOther2'}),
             (a)-[:REL]->(b),
             (a)-[:REL]->(c),
             (a)-[:REL]->(d),
             (b)-[:REL]->(),
             (b)-[:REL]->(),
             (c)-[:REL]->(),
             (c)-[:REL]->(),
             (d)-[:REL]->()
      """
    */
    @Test
    @Category(FailingTests)
    def void testWithAcceptance_17() {
        val cypher = CypherParser.parseString('''
        MATCH (david {name: 'David'})--(otherPerson)-->()
        WITH otherPerson, count(*) AS foaf
        WHERE foaf > 1
        WITH otherPerson
        WHERE otherPerson.name <> 'NotOther'
        RETURN count(*)
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/WithAcceptance_17")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/WithAcceptance_17")
    }

}
