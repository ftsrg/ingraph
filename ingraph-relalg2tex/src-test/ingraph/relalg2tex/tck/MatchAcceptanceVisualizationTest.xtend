package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.CypherParser
import ingraph.relalg.util.SchemaInferencer
import ingraph.relalg2tex.RelalgTreeSerializer

class MatchAcceptanceVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer
    extension SchemaInferencer inferencer = new SchemaInferencer
    
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
        val container = CypherParser.parseString('''
        MATCH p = (a:Label1)<--(:Label2)
        RETURN p
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_01")
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
        val container = CypherParser.parseString('''
        MATCH p = (a:Label1)<--(:Label2)--()
        RETURN p
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_02")
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
        val container = CypherParser.parseString('''
        MATCH (n), (m)
        RETURN n.value AS n, m.value AS m
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_03")
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
        val container = CypherParser.parseString('''
        MATCH (a)-[r]->(b)
        WHERE r.foo = $param
        RETURN b
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_04")
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
        val container = CypherParser.parseString('''
        MATCH ()-[rel:X]-(a)
        WHERE a.name = 'Andres'
        RETURN a
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_05")
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
        val container = CypherParser.parseString('''
        MATCH (a)
        WITH a.name AS a
        RETURN a
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_06")
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
        val container = CypherParser.parseString('''
        MATCH (node)-[r:KNOWS]->(a)
        WHERE r.name = 'monkey'
        RETURN a
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_07")
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
        val container = CypherParser.parseString('''
        MATCH (n)
        WITH n.name AS n
        RETURN n
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_08")
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
        val container = CypherParser.parseString('''
        MATCH (n1)-[rel:KNOWS]->(n2)
        RETURN n1, n2
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_09")
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
        val container = CypherParser.parseString('''
        MATCH ()-[rel:KNOWS]->(x)
        RETURN x
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_10")
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
        val container = CypherParser.parseString('''
        MATCH (n)-->(a)-->(b)
        RETURN b
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_11")
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
        val container = CypherParser.parseString('''
        MATCH (n)-[rel]->(x)
        WHERE n.animal = x.animal
        RETURN n, x
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_12")
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
        val container = CypherParser.parseString('''
        MATCH (a)-[r {name: 'r'}]-(b)
        RETURN a, b
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_13")
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
        val container = CypherParser.parseString('''
        MATCH (a)-[r {name: 'r1'}]-(b)
        OPTIONAL MATCH (b)-[r2]-(c)
        WHERE r <> r2
        RETURN a, b, c
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_14")
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
        val container = CypherParser.parseString('''
        MATCH (n {name: 'A'})-[r]->(x)
        WHERE type(r) = 'KNOWS'
        RETURN x
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_15")
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
        val container = CypherParser.parseString('''
        MATCH (n)-[r]->(x)
        WHERE type(r) = 'KNOWS' OR type(r) = 'HATES'
        RETURN r
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_16")
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
        val container = CypherParser.parseString('''
        MATCH (n)
        WHERE n.p1 = 12 OR n.p2 = 13
        RETURN n
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_17")
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
        val container = CypherParser.parseString('''
        MATCH p = (a {name: 'A'})-->(b)
        RETURN p
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_18")
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
        val container = CypherParser.parseString('''
        MATCH p = (a {name: 'A'})-[rel1]->(b)-[rel2]->(c)
        RETURN p
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_19")
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
        val container = CypherParser.parseString('''
        MATCH p = (n)-->(x)
        WHERE length(p) = 10
        RETURN x
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_20")
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
        val container = CypherParser.parseString('''
        MATCH p = (n)-->(x)
        WHERE length(p) = 1
        RETURN x
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_21")
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
        val container = CypherParser.parseString('''
        MATCH p = (a)-[:REL*2..2]->(b:End)
        RETURN relationships(p)
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_22")
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
        val container = CypherParser.parseString('''
        MATCH p = (a:Start)-[:REL*2..2]->(b)
        RETURN relationships(p)
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_23")
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
        val container = CypherParser.parseString('''
        MATCH (a)-[r:REL*2..2]->(b:End)
        RETURN r
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_24")
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
        val container = CypherParser.parseString('''
        MATCH (a)-[r:REL*2..2]-(b:End)
        RETURN r
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_25")
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
        val container = CypherParser.parseString('''
        MATCH (a:Start)-[r:REL*2..2]-(b)
        RETURN r
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_26")
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
        val container = CypherParser.parseString('''
        MATCH p = (n {name: 'A'})-[:KNOWS*1..2]->(x)
        RETURN p
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_27")
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
        val container = CypherParser.parseString('''
        MATCH p = (a)-[*0..1]->(b)
        RETURN a, b, length(p) AS l
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_28")
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
        val container = CypherParser.parseString('''
        MATCH p = (a {name: 'A'})-[:KNOWS*0..1]->(b)-[:FRIEND*0..1]->(c)
        RETURN p
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_29")
    }

    /*
    Scenario: Accept skip zero
    Given any graph
    */
    @Test
    def void testMatchAcceptance_30() {
        val container = CypherParser.parseString('''
        MATCH (n)
        WHERE 1 = 0
        RETURN n SKIP 0
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "MatchAcceptance_30")
    }

}
