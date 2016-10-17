package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.CypherParser

class MatchAcceptanceParserTest {
    
    /*
    Scenario: Path query should return results in written order
    Given an empty graph
    And having executed:
      """
      CREATE (:Label1)<-[:TYPE]-(:Label2)
      """
    */
    @Test
    def void testMatchAcceptance_01() {
        CypherParser.parseString('''
        MATCH p = (a:Label1)<--(:Label2)
        RETURN p
        ''')
    }

    /*
    Scenario: Longer path query should return results in written order
    Given an empty graph
    And having executed:
      """
      CREATE (:Label1)<-[:T1]-(:Label2)-[:T2]->(:Label3)
      """
    */
    @Test
    def void testMatchAcceptance_02() {
        CypherParser.parseString('''
        MATCH p = (a:Label1)<--(:Label2)--()
        RETURN p
        ''')
    }

    /*
    Scenario: Use multiple MATCH clauses to do a Cartesian product
    Given an empty graph
    And having executed:
      """
      CREATE ({value: 1}),
        ({value: 2}),
        ({value: 3})
      """
    */
    @Test
    def void testMatchAcceptance_03() {
        CypherParser.parseString('''
        MATCH (n), (m)
        RETURN n.value AS n, m.value AS m
        ''')
    }

    /*
    Scenario: Use params in pattern matching predicates
    Given an empty graph
    And having executed:
      """
      CREATE (:A)-[:T {foo: 'bar'}]->(:B {name: 'me'})
      """
    And parameters are:
      | param | 'bar' |
    */
    @Test
    def void testMatchAcceptance_04() {
        CypherParser.parseString('''
        MATCH (a)-[r]->(b)
        WHERE r.foo = $param
        RETURN b
        ''')
    }

    /*
    Scenario: Filter out based on node prop name
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'Someone'})<-[:X]-()-[:X]->({name: 'Andres'})
      """
    */
    @Test
    def void testMatchAcceptance_05() {
        CypherParser.parseString('''
        MATCH ()-[rel:X]-(a)
        WHERE a.name = 'Andres'
        RETURN a
        ''')
    }

    /*
    Scenario: Honour the column name for RETURN items
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'Someone'})
      """
    */
    @Test
    def void testMatchAcceptance_06() {
        CypherParser.parseString('''
        MATCH (a)
        WITH a.name AS a
        RETURN a
        ''')
    }

    /*
    Scenario: Filter based on rel prop name
    Given an empty graph
    And having executed:
      """
      CREATE (:A)<-[:KNOWS {name: 'monkey'}]-()-[:KNOWS {name: 'woot'}]->(:B)
      """
    */
    @Test
    def void testMatchAcceptance_07() {
        CypherParser.parseString('''
        MATCH (node)-[r:KNOWS]->(a)
        WHERE r.name = 'monkey'
        RETURN a
        ''')
    }

    /*
    Scenario: Cope with shadowed variables
    Given an empty graph
    And having executed:
      """
      CREATE ({value: 1, name: 'King Kong'}),
        ({value: 2, name: 'Ann Darrow'})
      """
    */
    @Test
    def void testMatchAcceptance_08() {
        CypherParser.parseString('''
        MATCH (n)
        WITH n.name AS n
        RETURN n
        ''')
    }

    /*
    Scenario: Get neighbours
    Given an empty graph
    And having executed:
      """
      CREATE (a:A {value: 1})-[:KNOWS]->(b:B {value: 2})
      """
    */
    @Test
    def void testMatchAcceptance_09() {
        CypherParser.parseString('''
        MATCH (n1)-[rel:KNOWS]->(n2)
        RETURN n1, n2
        ''')
    }

    /*
    Scenario: Get two related nodes
    Given an empty graph
    And having executed:
      """
      CREATE (a:A {value: 1}),
        (a)-[:KNOWS]->(b:B {value: 2}),
        (a)-[:KNOWS]->(c:C {value: 3})
      """
    */
    @Test
    def void testMatchAcceptance_10() {
        CypherParser.parseString('''
        MATCH ()-[rel:KNOWS]->(x)
        RETURN x
        ''')
    }

    /*
    Scenario: Get related to related to
    Given an empty graph
    And having executed:
      """
      CREATE (a:A {value: 1})-[:KNOWS]->(b:B {value: 2})-[:FRIEND]->(c:C {value: 3})
      """
    */
    @Test
    def void testMatchAcceptance_11() {
        CypherParser.parseString('''
        MATCH (n)-->(a)-->(b)
        RETURN b
        ''')
    }

    /*
    Scenario: Handle comparison between node properties
    Given an empty graph
    And having executed:
      """
      CREATE (a:A {animal: 'monkey'}),
        (b:B {animal: 'cow'}),
        (c:C {animal: 'monkey'}),
        (d:D {animal: 'cow'}),
        (a)-[:KNOWS]->(b),
        (a)-[:KNOWS]->(c),
        (d)-[:KNOWS]->(b),
        (d)-[:KNOWS]->(c)
      """
    */
    @Test
    def void testMatchAcceptance_12() {
        CypherParser.parseString('''
        MATCH (n)-[rel]->(x)
        WHERE n.animal = x.animal
        RETURN n, x
        ''')
    }

    /*
    Scenario: Return two subgraphs with bound undirected relationship
    Given an empty graph
    And having executed:
      """
      CREATE (a:A {value: 1})-[:REL {name: 'r'}]->(b:B {value: 2})
      """
    */
    @Test
    def void testMatchAcceptance_13() {
        CypherParser.parseString('''
        MATCH (a)-[r {name: 'r'}]-(b)
        RETURN a, b
        ''')
    }

    /*
    Scenario: Return two subgraphs with bound undirected relationship and optional relationship
    Given an empty graph
    And having executed:
      """
      CREATE (a:A {value: 1})-[:REL {name: 'r1'}]->(b:B {value: 2})-[:REL {name: 'r2'}]->(c:C {value: 3})
      """
    */
    @Test
    def void testMatchAcceptance_14() {
        CypherParser.parseString('''
        MATCH (a)-[r {name: 'r1'}]-(b)
        OPTIONAL MATCH (b)-[r2]-(c)
        WHERE r <> r2
        RETURN a, b, c
        ''')
    }

    /*
    Scenario: Rel type function works as expected
    Given an empty graph
    And having executed:
      """
      CREATE (a:A {name: 'A'}),
        (b:B {name: 'B'}),
        (c:C {name: 'C'}),
        (a)-[:KNOWS]->(b),
        (a)-[:HATES]->(c)
      """
    */
    @Test
    def void testMatchAcceptance_15() {
        CypherParser.parseString('''
        MATCH (n {name: 'A'})-[r]->(x)
        WHERE type(r) = 'KNOWS'
        RETURN x
        ''')
    }

    /*
    Scenario: Walk alternative relationships
    Given an empty graph
    And having executed:
      """
      CREATE (a {name: 'A'}),
        (b {name: 'B'}),
        (c {name: 'C'}),
        (a)-[:KNOWS]->(b),
        (a)-[:HATES]->(c),
        (a)-[:WONDERS]->(c)
      """
    */
    @Test
    def void testMatchAcceptance_16() {
        CypherParser.parseString('''
        MATCH (n)-[r]->(x)
        WHERE type(r) = 'KNOWS' OR type(r) = 'HATES'
        RETURN r
        ''')
    }

    /*
    Scenario: Handle OR in the WHERE clause
    Given an empty graph
    And having executed:
      """
      CREATE (a:A {p1: 12}),
        (b:B {p2: 13}),
        (c:C)
      """
    */
    @Test
    def void testMatchAcceptance_17() {
        CypherParser.parseString('''
        MATCH (n)
        WHERE n.p1 = 12 OR n.p2 = 13
        RETURN n
        ''')
    }

    /*
    Scenario: Return a simple path
    Given an empty graph
    And having executed:
      """
      CREATE (a:A {name: 'A'})-[:KNOWS]->(b:B {name: 'B'})
      """
    */
    @Test
    def void testMatchAcceptance_18() {
        CypherParser.parseString('''
        MATCH p = (a {name: 'A'})-->(b)
        RETURN p
        ''')
    }

    /*
    Scenario: Return a three node path
    Given an empty graph
    And having executed:
      """
      CREATE (a:A {name: 'A'})-[:KNOWS]->(b:B {name: 'B'})-[:KNOWS]->(c:C {name: 'C'})
      """
    */
    @Test
    def void testMatchAcceptance_19() {
        CypherParser.parseString('''
        MATCH p = (a {name: 'A'})-[rel1]->(b)-[rel2]->(c)
        RETURN p
        ''')
    }

    /*
    Scenario: Do not return anything because path length does not match
    Given an empty graph
    And having executed:
      """
      CREATE (a:A {name: 'A'})-[:KNOWS]->(b:B {name: 'B'})
      """
    */
    @Test
    def void testMatchAcceptance_20() {
        CypherParser.parseString('''
        MATCH p = (n)-->(x)
        WHERE length(p) = 10
        RETURN x
        ''')
    }

    /*
    Scenario: Pass the path length test
    Given an empty graph
    And having executed:
      """
      CREATE (a:A {name: 'A'})-[:KNOWS]->(b:B {name: 'B'})
      """
    */
    @Test
    def void testMatchAcceptance_21() {
        CypherParser.parseString('''
        MATCH p = (n)-->(x)
        WHERE length(p) = 1
        RETURN x
        ''')
    }

    /*
    Scenario: Return relationships by fetching them from the path - starting from the end
    Given an empty graph
    And having executed:
      """
      CREATE (a:A)-[:REL {value: 1}]->(b:B)-[:REL {value: 2}]->(e:End)
      """
    */
    @Test
    def void testMatchAcceptance_22() {
        CypherParser.parseString('''
        MATCH p = (a)-[:REL*2..2]->(b:End)
        RETURN relationships(p)
        ''')
    }

    /*
    Scenario: Return relationships by fetching them from the path
    Given an empty graph
    And having executed:
      """
      CREATE (s:Start)-[:REL {value: 1}]->(b:B)-[:REL {value: 2}]->(c:C)
      """
    */
    @Test
    def void testMatchAcceptance_23() {
        CypherParser.parseString('''
        MATCH p = (a:Start)-[:REL*2..2]->(b)
        RETURN relationships(p)
        ''')
    }

    /*
    Scenario: Return relationships by collecting them as a list - wrong way
    Given an empty graph
    And having executed:
      """
      CREATE (a:A)-[:REL {value: 1}]->(b:B)-[:REL {value: 2}]->(e:End)
      """
    */
    @Test
    def void testMatchAcceptance_24() {
        CypherParser.parseString('''
        MATCH (a)-[r:REL*2..2]->(b:End)
        RETURN r
        ''')
    }

    /*
    Scenario: Return relationships by collecting them as a list - undirected
    Given an empty graph
    And having executed:
      """
      CREATE (a:End {value: 1})-[:REL {value: 1}]->(b:B)-[:REL {value: 2}]->(c:End {value: 2})
      """
    */
    @Test
    def void testMatchAcceptance_25() {
        CypherParser.parseString('''
        MATCH (a)-[r:REL*2..2]-(b:End)
        RETURN r
        ''')
    }

    /*
    Scenario: Return relationships by collecting them as a list
    Given an empty graph
    And having executed:
      """
      CREATE (s:Start)-[:REL {value: 1}]->(b:B)-[:REL {value: 2}]->(c:C)
      """
    */
    @Test
    def void testMatchAcceptance_26() {
        CypherParser.parseString('''
        MATCH (a:Start)-[r:REL*2..2]-(b)
        RETURN r
        ''')
    }

    /*
    Scenario: Return a var length path
    Given an empty graph
    And having executed:
      """
      CREATE (a:A {name: 'A'})-[:KNOWS {value: 1}]->(b:B {name: 'B'})-[:KNOWS {value: 2}]->(c:C {name: 'C'})
      """
    */
    @Test
    def void testMatchAcceptance_27() {
        CypherParser.parseString('''
        MATCH p = (n {name: 'A'})-[:KNOWS*1..2]->(x)
        RETURN p
        ''')
    }

    /*
    Scenario: Return a var length path of length zero
    Given an empty graph
    And having executed:
      """
      CREATE (a:A)-[:REL]->(b:B)
      """
    */
    @Test
    def void testMatchAcceptance_28() {
        CypherParser.parseString('''
        MATCH p = (a)-[*0..1]->(b)
        RETURN a, b, length(p) AS l
        ''')
    }

    /*
    Scenario: Return a named var length path of length zero
    Given an empty graph
    And having executed:
      """
      CREATE (a:A {name: 'A'})-[:KNOWS]->(b:B {name: 'B'})-[:FRIEND]->(c:C {name: 'C'})
      """
    */
    @Test
    def void testMatchAcceptance_29() {
        CypherParser.parseString('''
        MATCH p = (a {name: 'A'})-[:KNOWS*0..1]->(b)-[:FRIEND*0..1]->(c)
        RETURN p
        ''')
    }

    /*
    Scenario: Accept skip zero
    Given any graph
    */
    @Test
    def void testMatchAcceptance_30() {
        CypherParser.parseString('''
        MATCH (n)
        WHERE 1 = 0
        RETURN n SKIP 0
        ''')
    }

}
