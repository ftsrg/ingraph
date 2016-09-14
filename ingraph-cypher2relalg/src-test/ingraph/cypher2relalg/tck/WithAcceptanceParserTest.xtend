package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

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
        RelalgParser.parse('''
        MATCH (a:A)
        WITH a
        MATCH (a)-->(b)
        RETURN *
        ''')
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
        RelalgParser.parse('''
        MATCH (a:A)
        WITH a
        ORDER BY a.name
        LIMIT 1
        MATCH (a)-->(b)
        RETURN a
        ''')
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
        RelalgParser.parse('''
        MATCH (a)
        WITH a
        MATCH (b)
        RETURN a, b
        ''')
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
        RelalgParser.parse('''
        MATCH (a:Begin)
        WITH a.prop AS property
        MATCH (b:End)
        WHERE property = b.prop
        RETURN b
        ''')
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
        RelalgParser.parse('''
        MATCH (a:Begin)
        WITH a.prop AS property
        LIMIT 1
        MATCH (b)
        WHERE b.id = property
        RETURN b
        ''')
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
        RelalgParser.parse('''
        MATCH (a)
        WITH a.prop AS property, a.key AS idToUse
        ORDER BY property
        SKIP 1
        MATCH (b)
        WHERE b.id = idToUse
        RETURN DISTINCT b
        ''')
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
        RelalgParser.parse('''
        MATCH (a)
        WITH a
        WHERE a.name = 'B'
        RETURN a
        ''')
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
        RelalgParser.parse('''
        MATCH (a)-->()
        WITH a, count(*) AS relCount
        WHERE relCount > 1
        RETURN a
        ''')
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
        RelalgParser.parse('''
        MATCH (a)
        WITH a.bar AS bars, count(*) AS relCount
        ORDER BY a.bar
        RETURN *
        ''')
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
        RelalgParser.parse('''
        MATCH (a)
        WITH DISTINCT a.bar AS bars
        ORDER BY a.bar
        RETURN *
        ''')
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
        RelalgParser.parse('''
        MATCH (a)
        WITH DISTINCT a.bar AS bars
        WHERE a.bar = 'B'
        RETURN *
        ''')
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
        RelalgParser.parse('''
        MATCH (a:A)-[r:REL]->(b:B)
        WITH a AS b, b AS tmp, r AS r
        WITH b AS a, r
        LIMIT 1
        MATCH (a)-[r]->(b)
        RETURN a, r, b
        ''')
    }

    /*
    Scenario: Null handling
    Given an empty graph
    */
    @Test
    def void testWithAcceptance_13() {
        RelalgParser.parse('''
        OPTIONAL MATCH (a:Start)
        WITH a
        MATCH (a)-->(b)
        RETURN *
        ''')
    }

    /*
    Scenario: Nested maps
    Given an empty graph
    */
    @Test
    def void testWithAcceptance_14() {
        RelalgParser.parse('''
        WITH {foo: {bar: 'baz'}} AS nestedMap
        RETURN nestedMap.foo.bar
        ''')
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
        RelalgParser.parse('''
        MATCH (n:A)
        WITH n
        LIMIT 1
        MATCH (m:B), (n)-->(x:X)
        RETURN *
        ''')
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
        RelalgParser.parse('''
        MATCH (n)
        WITH n
        WHERE n.prop = 42
        RETURN count(*)
        ''')
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
        RelalgParser.parse('''
        MATCH (david {name: 'David'})--(otherPerson)-->()
        WITH otherPerson, count(*) AS foaf
        WHERE foaf > 1
        WITH otherPerson
        WHERE otherPerson.name <> 'NotOther'
        RETURN count(*)
        ''')
    }

}
