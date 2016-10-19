package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2RelAlg
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil

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
    def void testWithAcceptance_01() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        WITH a
        MATCH (a)-->(b)
        RETURN *
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/WithAcceptance_01")
        Cypher2RelAlg.processCypher(cypher)
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
    def void testWithAcceptance_02() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        WITH a
        ORDER BY a.name
        LIMIT 1
        MATCH (a)-->(b)
        RETURN a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/WithAcceptance_02")
        Cypher2RelAlg.processCypher(cypher)
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
    def void testWithAcceptance_03() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WITH a
        MATCH (b)
        RETURN a, b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/WithAcceptance_03")
        Cypher2RelAlg.processCypher(cypher)
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
    def void testWithAcceptance_04() {
        val cypher = CypherParser.parseString('''
        MATCH (a:Begin)
        WITH a.prop AS property
        MATCH (b:End)
        WHERE property = b.prop
        RETURN b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/WithAcceptance_04")
        Cypher2RelAlg.processCypher(cypher)
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
    def void testWithAcceptance_05() {
        val cypher = CypherParser.parseString('''
        MATCH (a:Begin)
        WITH a.prop AS property
        LIMIT 1
        MATCH (b)
        WHERE b.id = property
        RETURN b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/WithAcceptance_05")
        Cypher2RelAlg.processCypher(cypher)
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
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/WithAcceptance_06")
        Cypher2RelAlg.processCypher(cypher)
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
    def void testWithAcceptance_07() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WITH a
        WHERE a.name = 'B'
        RETURN a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/WithAcceptance_07")
        Cypher2RelAlg.processCypher(cypher)
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
    def void testWithAcceptance_08() {
        val cypher = CypherParser.parseString('''
        MATCH (a)-->()
        WITH a, count(*) AS relCount
        WHERE relCount > 1
        RETURN a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/WithAcceptance_08")
        Cypher2RelAlg.processCypher(cypher)
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
    def void testWithAcceptance_09() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WITH a.bar AS bars, count(*) AS relCount
        ORDER BY a.bar
        RETURN *
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/WithAcceptance_09")
        Cypher2RelAlg.processCypher(cypher)
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
    def void testWithAcceptance_10() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WITH DISTINCT a.bar AS bars
        ORDER BY a.bar
        RETURN *
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/WithAcceptance_10")
        Cypher2RelAlg.processCypher(cypher)
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
    def void testWithAcceptance_11() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WITH DISTINCT a.bar AS bars
        WHERE a.bar = 'B'
        RETURN *
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/WithAcceptance_11")
        Cypher2RelAlg.processCypher(cypher)
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
    def void testWithAcceptance_12() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)-[r:REL]->(b:B)
        WITH a AS b, b AS tmp, r AS r
        WITH b AS a, r
        LIMIT 1
        MATCH (a)-[r]->(b)
        RETURN a, r, b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/WithAcceptance_12")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Null handling
    Given an empty graph
    */
    @Test
    def void testWithAcceptance_13() {
        val cypher = CypherParser.parseString('''
        OPTIONAL MATCH (a:Start)
        WITH a
        MATCH (a)-->(b)
        RETURN *
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/WithAcceptance_13")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Nested maps
    Given an empty graph
    */
    @Test
    def void testWithAcceptance_14() {
        val cypher = CypherParser.parseString('''
        WITH {foo: {bar: 'baz'}} AS nestedMap
        RETURN nestedMap.foo.bar
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/WithAcceptance_14")
        Cypher2RelAlg.processCypher(cypher)
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
    def void testWithAcceptance_15() {
        val cypher = CypherParser.parseString('''
        MATCH (n:A)
        WITH n
        LIMIT 1
        MATCH (m:B), (n)-->(x:X)
        RETURN *
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/WithAcceptance_15")
        Cypher2RelAlg.processCypher(cypher)
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
    def void testWithAcceptance_16() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        WITH n
        WHERE n.prop = 42
        RETURN count(*)
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/WithAcceptance_16")
        Cypher2RelAlg.processCypher(cypher)
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
    def void testWithAcceptance_17() {
        val cypher = CypherParser.parseString('''
        MATCH (david {name: 'David'})--(otherPerson)-->()
        WITH otherPerson, count(*) AS foaf
        WHERE foaf > 1
        WITH otherPerson
        WHERE otherPerson.name <> 'NotOther'
        RETURN count(*)
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/WithAcceptance_17")
        Cypher2RelAlg.processCypher(cypher)
    }

}
