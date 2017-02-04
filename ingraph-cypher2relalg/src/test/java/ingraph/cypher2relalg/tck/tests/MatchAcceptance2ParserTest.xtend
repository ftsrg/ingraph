package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class MatchAcceptance2ParserTest {
    
    /*
    Scenario: Do not return non-existent nodes
    Given an empty graph
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_01() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_01")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Do not return non-existent relationships
    Given an empty graph
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_02() {
        val cypher = CypherParser.parseString('''
        MATCH ()-[r]->()
        RETURN r
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_02")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Do not fail when evaluating predicates with illegal operations if the AND'ed predicate evaluates to false
    Given an empty graph
    And having executed:
      """
      CREATE (root:Root {name: 'x'}),
             (child1:TextNode {id: 'text'}),
             (child2:IntNode {id: 0})
      CREATE (root)-[:T]->(child1),
             (root)-[:T]->(child2)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_03() {
        val cypher = CypherParser.parseString('''
        MATCH (:Root {name: 'x'})-->(i:TextNode)
        WHERE i.id > 'te'
        RETURN i
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_03")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Do not fail when evaluating predicates with illegal operations if the OR'd predicate evaluates to true
    Given an empty graph
    And having executed:
      """
      CREATE (root:Root {name: 'x'}),
             (child1:TextNode {id: 'text'}),
             (child2:IntNode {id: 0})
      CREATE (root)-[:T]->(child1),
             (root)-[:T]->(child2)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_04() {
        val cypher = CypherParser.parseString('''
        MATCH (:Root {name: 'x'})-->(i)
        WHERE exists(i.id) OR i.id > 'te'
        RETURN i
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_04")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Fail when comparing strings and integers using > in an AND'd predicate
    Given an empty graph
    And having executed:
      """
      CREATE (root:Root {name: 'x'})-[:T]->(child:Child {id: 0})
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_05() {
        val cypher = CypherParser.parseString('''
        MATCH (:Root {name: 'x'})-->(i:Child)
        WHERE i.id > 'te'
        RETURN i
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_05")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Fail when comparing strings and integers using > in a OR'd predicate
    Given an empty graph
    And having executed:
      """
      CREATE (root:Root {name: 'x'})-[:T]->(child:Child {id: 0})
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_06() {
        val cypher = CypherParser.parseString('''
        MATCH (:Root {name: 'x'})-->(i:Child)
        WHERE NOT exists(i.id) OR i.id > 'te'
        RETURN i
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_06")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Aggregation with named paths
    Given an empty graph
    And having executed:
      """
      CREATE (n1 {num: 1}), (n2 {num: 2}),
             (n3 {num: 3}), (n4 {num: 4})
      CREATE (n1)-[:T]->(n2),
             (n3)-[:T]->(n4)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_07() {
        val cypher = CypherParser.parseString('''
        MATCH p = ()-[*]->()
        WITH count(*) AS count, p AS p
        WITH nodes(p) AS nodes
        RETURN *
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_07")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Zero-length variable length pattern in the middle of the pattern
    Given an empty graph
    And having executed:
      """
      CREATE (a {name: 'A'}), (b {name: 'B'}),
             (c {name: 'C'}), ({name: 'D'}),
             ({name: 'E'})
      CREATE (a)-[:CONTAINS]->(b),
             (b)-[:FRIEND]->(c)
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_08() {
        val cypher = CypherParser.parseString('''
        MATCH (a {name: 'A'})-[:CONTAINS*0..1]->(b)-[:FRIEND*0..1]->(c)
        RETURN a, b, c
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_08")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Simple variable length pattern
    Given an empty graph
    And having executed:
      """
      CREATE (a {name: 'A'}), (b {name: 'B'}),
             (c {name: 'C'}), (d {name: 'D'})
      CREATE (a)-[:CONTAINS]->(b),
             (b)-[:CONTAINS]->(c),
             (c)-[:CONTAINS]->(d)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_09() {
        val cypher = CypherParser.parseString('''
        MATCH (a {name: 'A'})-[*]->(x)
        RETURN x
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_09")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Variable length relationship without lower bound
    Given an empty graph
    And having executed:
      """
      CREATE (a {name: 'A'}), (b {name: 'B'}),
             (c {name: 'C'})
      CREATE (a)-[:KNOWS]->(b),
             (b)-[:KNOWS]->(c)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_10() {
        val cypher = CypherParser.parseString('''
        MATCH p = ({name: 'A'})-[:KNOWS*..2]->()
        RETURN p
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_10")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Variable length relationship without bounds
    Given an empty graph
    And having executed:
      """
      CREATE (a {name: 'A'}), (b {name: 'B'}),
             (c {name: 'C'})
      CREATE (a)-[:KNOWS]->(b),
             (b)-[:KNOWS]->(c)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_11() {
        val cypher = CypherParser.parseString('''
        MATCH p = ({name: 'A'})-[:KNOWS*..]->()
        RETURN p
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_11")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Returning bound nodes that are not part of the pattern
    Given an empty graph
    And having executed:
      """
      CREATE (a {name: 'A'}), (b {name: 'B'}),
             (c {name: 'C'})
      CREATE (a)-[:KNOWS]->(b)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_12() {
        val cypher = CypherParser.parseString('''
        MATCH (a {name: 'A'}), (c {name: 'C'})
        MATCH (a)-->(b)
        RETURN a, b, c
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_12")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Two bound nodes pointing to the same node
    Given an empty graph
    And having executed:
      """
      CREATE (a {name: 'A'}), (b {name: 'B'}),
             (x1 {name: 'x1'}), (x2 {name: 'x2'})
      CREATE (a)-[:KNOWS]->(x1),
             (a)-[:KNOWS]->(x2),
             (b)-[:KNOWS]->(x1),
             (b)-[:KNOWS]->(x2)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_13() {
        val cypher = CypherParser.parseString('''
        MATCH (a {name: 'A'}), (b {name: 'B'})
        MATCH (a)-->(x)<-->(b)
        RETURN x
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_13")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Three bound nodes pointing to the same node
    Given an empty graph
    And having executed:
      """
      CREATE (a {name: 'A'}), (b {name: 'B'}), (c {name: 'C'}),
             (x1 {name: 'x1'}), (x2 {name: 'x2'})
      CREATE (a)-[:KNOWS]->(x1),
             (a)-[:KNOWS]->(x2),
             (b)-[:KNOWS]->(x1),
             (b)-[:KNOWS]->(x2),
             (c)-[:KNOWS]->(x1),
             (c)-[:KNOWS]->(x2)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_14() {
        val cypher = CypherParser.parseString('''
        MATCH (a {name: 'A'}), (b {name: 'B'}), (c {name: 'C'})
        MATCH (a)-->(x), (b)-->(x), (c)-->(x)
        RETURN x
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_14")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Three bound nodes pointing to the same node with extra connections
    Given an empty graph
    And having executed:
      """
      CREATE (a {name: 'a'}), (b {name: 'b'}), (c {name: 'c'}),
             (d {name: 'd'}), (e {name: 'e'}), (f {name: 'f'}),
             (g {name: 'g'}), (h {name: 'h'}), (i {name: 'i'}),
             (j {name: 'j'}), (k {name: 'k'})
      CREATE (a)-[:KNOWS]->(d),
             (a)-[:KNOWS]->(e),
             (a)-[:KNOWS]->(f),
             (a)-[:KNOWS]->(g),
             (a)-[:KNOWS]->(i),
             (b)-[:KNOWS]->(d),
             (b)-[:KNOWS]->(e),
             (b)-[:KNOWS]->(f),
             (b)-[:KNOWS]->(h),
             (b)-[:KNOWS]->(k),
             (c)-[:KNOWS]->(d),
             (c)-[:KNOWS]->(e),
             (c)-[:KNOWS]->(h),
             (c)-[:KNOWS]->(g),
             (c)-[:KNOWS]->(j)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_15() {
        val cypher = CypherParser.parseString('''
        MATCH (a {name: 'a'}), (b {name: 'b'}), (c {name: 'c'})
        MATCH (a)-->(x), (b)-->(x), (c)-->(x)
        RETURN x
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_15")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: MATCH with OPTIONAL MATCH in longer pattern
    Given an empty graph
    And having executed:
      """
      CREATE (a {name: 'A'}), (b {name: 'B'}), (c {name: 'C'})
      CREATE (a)-[:KNOWS]->(b),
             (b)-[:KNOWS]->(c)
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_16() {
        val cypher = CypherParser.parseString('''
        MATCH (a {name: 'A'})
        OPTIONAL MATCH (a)-[:KNOWS]->()-[:KNOWS]->(foo)
        RETURN foo
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_16")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Optionally matching named paths
    Given an empty graph
    And having executed:
      """
      CREATE (a {name: 'A'}), (b {name: 'B'}), (c {name: 'C'})
      CREATE (a)-[:X]->(b)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_17() {
        val cypher = CypherParser.parseString('''
        MATCH (a {name: 'A'}), (x)
        WHERE x.name IN ['B', 'C']
        OPTIONAL MATCH p = (a)-->(x)
        RETURN x, p
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_17")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Optionally matching named paths with single and variable length patterns
    Given an empty graph
    And having executed:
      """
      CREATE (a {name: 'A'}), (b {name: 'B'})
      CREATE (a)-[:X]->(b)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_18() {
        val cypher = CypherParser.parseString('''
        MATCH (a {name: 'A'})
        OPTIONAL MATCH p = (a)-->(b)-[*]->(c)
        RETURN p
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_18")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Optionally matching named paths with variable length patterns
    Given an empty graph
    And having executed:
      """
      CREATE (a {name: 'A'}), (b {name: 'B'}), (c {name: 'C'})
      CREATE (a)-[:X]->(b)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_19() {
        val cypher = CypherParser.parseString('''
        MATCH (a {name: 'A'}), (x)
        WHERE x.name IN ['B', 'C']
        OPTIONAL MATCH p = (a)-[r*]->(x)
        RETURN r, x, p
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_19")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching variable length patterns from a bound node
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (b), (c)
      CREATE (a)-[:X]->(b),
             (b)-[:Y]->(c)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_20() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        MATCH (a)-[r*2]->()
        RETURN r
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_20")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Excluding connected nodes
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (b:B {id: 1}), (:B {id: 2})
      CREATE (a)-[:T]->(b)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_21() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A), (other:B)
        OPTIONAL MATCH (a)-[r]->(other)
        WITH other WHERE r IS NULL
        RETURN other
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_21")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Do not fail when predicates on optionally matched and missed nodes are invalid
    Given an empty graph
    And having executed:
      """
      CREATE (a), (b {name: 'Mark'})
      CREATE (a)-[:T]->(b)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_22() {
        val cypher = CypherParser.parseString('''
        MATCH (n)-->(x0)
        OPTIONAL MATCH (x0)-->(x1)
        WHERE x1.foo = 'bar'
        RETURN x0.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_22")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: MATCH and OPTIONAL MATCH on same pattern
    Given an empty graph
    And having executed:
      """
      CREATE (a {name: 'A'}), (b:B {name: 'B'}), (c:C {name: 'C'})
      CREATE (a)-[:T]->(b),
             (a)-[:T]->(c)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_23() {
        val cypher = CypherParser.parseString('''
        MATCH (a)-->(b)
        WHERE b:B
        OPTIONAL MATCH (a)-->(c)
        WHERE c:C
        RETURN a.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_23")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching using an undirected pattern
    Given an empty graph
    And having executed:
      """
      CREATE (:A {id: 0})-[:ADMIN]->(:B {id: 1})
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_24() {
        val cypher = CypherParser.parseString('''
        MATCH (a)-[:ADMIN]-(b)
        WHERE a:A
        RETURN a.id, b.id
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_24")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching all nodes
    Given an empty graph
    And having executed:
      """
      CREATE (:A), (:B)
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_25() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_25")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Comparing nodes for equality
    Given an empty graph
    And having executed:
      """
      CREATE (:A), (:B)
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_26() {
        val cypher = CypherParser.parseString('''
        MATCH (a), (b)
        WHERE a <> b
        RETURN a, b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_26")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching using self-referencing pattern returns no result
    Given an empty graph
    And having executed:
      """
      CREATE (a), (b), (c)
      CREATE (a)-[:T]->(b),
             (b)-[:T]->(c)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_27() {
        val cypher = CypherParser.parseString('''
        MATCH (a)-->(b), (b)-->(b)
        RETURN b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_27")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Variable length relationship in OPTIONAL MATCH
    Given an empty graph
    And having executed:
      """
      CREATE (:A), (:B)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_28() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A), (b:B)
        OPTIONAL MATCH (a)-[r*]-(b)
        WHERE r IS NULL
        AND a <> b
        RETURN b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_28")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching using relationship predicate with multiples of the same type
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (b:B)
      CREATE (a)-[:T]->(b)
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_29() {
        val cypher = CypherParser.parseString('''
        MATCH (a)-[:T|:T]->(b)
        RETURN b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_29")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: ORDER BY with LIMIT
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (n1 {x: 1}), (n2 {x: 2}),
             (m1), (m2)
      CREATE (a)-[:T]->(n1),
             (n1)-[:T]->(m1),
             (a)-[:T]->(n2),
             (n2)-[:T]->(m2)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_30() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)-->(n)-->(m)
        RETURN n.x, count(*)
        ORDER BY n.x
        LIMIT 1000
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_30")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Simple node property predicate
    Given an empty graph
    And having executed:
      """
      CREATE ({foo: 'bar'})
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_31() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        WHERE n.foo = 'bar'
        RETURN n
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_31")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling direction of named paths
    Given an empty graph
    And having executed:
      """
      CREATE (a:A)-[:T]->(b:B)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_32() {
        val cypher = CypherParser.parseString('''
        MATCH p = (b)<--(a)
        RETURN p
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_32")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Simple OPTIONAL MATCH on empty graph
    Given an empty graph
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_33() {
        val cypher = CypherParser.parseString('''
        OPTIONAL MATCH (n)
        RETURN n
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_33")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: OPTIONAL MATCH with previously bound nodes
    Given an empty graph
    And having executed:
      """
      CREATE ()
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_34() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        OPTIONAL MATCH (n)-[:NOT_EXIST]->(x)
        RETURN n, x
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_34")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: `collect()` filtering nulls
    Given an empty graph
    And having executed:
      """
      CREATE ()
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_35() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        OPTIONAL MATCH (n)-[:NOT_EXIST]->(x)
        RETURN n, collect(x)
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_35")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Multiple anonymous nodes in a pattern
    Given an empty graph
    And having executed:
      """
      CREATE (:A)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_36() {
        val cypher = CypherParser.parseString('''
        MATCH (a)<--()<--(b)-->()-->(c)
        WHERE a:A
        RETURN c
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_36")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching a relationship pattern using a label predicate
    Given an empty graph
    And having executed:
      """
      CREATE (a), (b1:Foo), (b2)
      CREATE (a)-[:T]->(b1),
             (a)-[:T]->(b2)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_37() {
        val cypher = CypherParser.parseString('''
        MATCH (a)-->(b:Foo)
        RETURN b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_37")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching a relationship pattern using a label predicate on both sides
    Given an empty graph
    And having executed:
      """
      CREATE (:A)-[:T1]->(:B),
             (:B)-[:T2]->(:A),
             (:B)-[:T3]->(:B),
             (:A)-[:T4]->(:A)
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_38() {
        val cypher = CypherParser.parseString('''
        MATCH (:A)-[r]->(:B)
        RETURN r
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_38")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching nodes using multiple labels
    Given an empty graph
    And having executed:
      """
      CREATE (:A:B:C), (:A:B), (:A:C), (:B:C),
             (:A), (:B), (:C)
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_39() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A:B:C)
        RETURN a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_39")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Returning label predicate expression
    Given an empty graph
    And having executed:
      """
      CREATE (), (:Foo)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_40() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN (n:Foo)
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_40")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching with many predicates and larger pattern
    Given an empty graph
    And having executed:
      """
      CREATE (advertiser {name: 'advertiser1', id: 0}),
             (thing {name: 'Color', id: 1}),
             (red {name: 'red'}),
             (p1 {name: 'product1'}),
             (p2 {name: 'product4'})
      CREATE (advertiser)-[:ADV_HAS_PRODUCT]->(p1),
             (advertiser)-[:ADV_HAS_PRODUCT]->(p2),
             (thing)-[:AA_HAS_VALUE]->(red),
             (p1)-[:AP_HAS_VALUE]->(red),
             (p2)-[:AP_HAS_VALUE]->(red)
      """
    And parameters are:
      | 1 | 0 |
      | 2 | 1 |
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_41() {
        val cypher = CypherParser.parseString('''
        MATCH (advertiser)-[:ADV_HAS_PRODUCT]->(out)-[:AP_HAS_VALUE]->(red)<-[:AA_HAS_VALUE]-(a)
        WHERE advertiser.id = $1
        AND a.id = $2
        AND red.name = 'red'
        AND out.name = 'product1'
        RETURN out.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_41")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Returning label predicate expression
    Given an empty graph
    And having executed:
      """
      CREATE (), (:Foo)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_42() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN (n:Foo)
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_42")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching using a simple pattern with label predicate
    Given an empty graph
    And having executed:
      """
      CREATE (a:Person {name: 'Alice'}), (b:Person {name: 'Bob'}),
             (c), (d)
      CREATE (a)-[:T]->(c),
             (b)-[:T]->(d)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_43() {
        val cypher = CypherParser.parseString('''
        MATCH (n:Person)-->()
        WHERE n.name = 'Bob'
        RETURN n
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_43")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching disconnected patterns
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (b:B), (c:C)
      CREATE (a)-[:T]->(b),
             (a)-[:T]->(c)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_44() {
        val cypher = CypherParser.parseString('''
        MATCH (a)-->(b)
        MATCH (c)-->(d)
        RETURN a, b, c, d
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_44")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Non-optional matches should not return nulls
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (b:B {id: 1}), (c:C {id: 2}), (d:D)
      CREATE (a)-[:T]->(b),
             (a)-[:T]->(c),
             (a)-[:T]->(d),
             (b)-[:T]->(c),
             (b)-[:T]->(d),
             (c)-[:T]->(d)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_45() {
        val cypher = CypherParser.parseString('''
        MATCH (a)--(b)--(c)--(d)--(a), (b)--(d)
        WHERE a.id = 1
        AND c.id = 2
        RETURN d
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_45")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling cyclic patterns
    Given an empty graph
    And having executed:
      """
      CREATE (a {name: 'a'}), (b {name: 'b'}), (c {name: 'c'})
      CREATE (a)-[:A]->(b),
             (b)-[:B]->(a),
             (b)-[:B]->(c)
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_46() {
        val cypher = CypherParser.parseString('''
        MATCH (a)-[:A]->()-[:B]->(a)
        RETURN a.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_46")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling cyclic patterns when separated into two parts
    Given an empty graph
    And having executed:
      """
      CREATE (a {name: 'a'}), (b {name: 'b'}), (c {name: 'c'})
      CREATE (a)-[:A]->(b),
             (b)-[:B]->(a),
             (b)-[:B]->(c)
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_47() {
        val cypher = CypherParser.parseString('''
        MATCH (a)-[:A]->(b), (b)-[:B]->(a)
        RETURN a.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_47")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling fixed-length variable length pattern
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:T]->()
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_48() {
        val cypher = CypherParser.parseString('''
        MATCH (a)-[r*1..1]->(b)
        RETURN r
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_48")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching from null nodes should return no results owing to finding no matches
    Given an empty graph
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_49() {
        val cypher = CypherParser.parseString('''
        OPTIONAL MATCH (a)
        WITH a
        MATCH (a)-->(b)
        RETURN b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_49")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching from null nodes should return no results owing to matches being filtered out
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:T]->()
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_50() {
        val cypher = CypherParser.parseString('''
        OPTIONAL MATCH (a:Label)
        WITH a
        MATCH (a)-->(b)
        RETURN b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_50")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Optionally matching from null nodes should return null
    Given an empty graph
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_51() {
        val cypher = CypherParser.parseString('''
        OPTIONAL MATCH (a)
        WITH a
        OPTIONAL MATCH (a)-->(b)
        RETURN b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_51")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: OPTIONAL MATCH returns null
    Given an empty graph
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_52() {
        val cypher = CypherParser.parseString('''
        OPTIONAL MATCH (a)
        RETURN a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_52")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Zero-length named path
    Given an empty graph
    And having executed:
      """
      CREATE ()
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_53() {
        val cypher = CypherParser.parseString('''
        MATCH p = (a)
        RETURN p
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_53")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Variable-length named path
    Given an empty graph
    And having executed:
      """
      CREATE ()
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_54() {
        val cypher = CypherParser.parseString('''
        MATCH p = ()-[*0..]->()
        RETURN p
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_54")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching with aggregation
    Given an empty graph
    And having executed:
      """
      CREATE ({prop: 42})
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_55() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n.prop AS n, count(n) AS count
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_55")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching using a relationship that is already bound
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:T1]->(),
             ()-[:T2]->()
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_56() {
        val cypher = CypherParser.parseString('''
        MATCH ()-[r1]->()
        WITH r1 AS r2
        MATCH ()-[r2]->()
        RETURN r2 AS rel
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_56")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching using a relationship that is already bound, in conjunction with aggregation
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:T1]->(),
             ()-[:T2]->()
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_57() {
        val cypher = CypherParser.parseString('''
        MATCH ()-[r1]->()
        WITH r1 AS r2, count(*) AS c
        ORDER BY c
        MATCH ()-[r2]->()
        RETURN r2 AS rel
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_57")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching using a relationship that is already bound, in conjunction with aggregation and ORDER BY
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:T1 {id: 0}]->(),
             ()-[:T2 {id: 1}]->()
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_58() {
        val cypher = CypherParser.parseString('''
        MATCH (a)-[r]->(b)
        WITH a, r, b, count(*) AS c
        ORDER BY c
        MATCH (a)-[r]->(b)
        RETURN r AS rel
        ORDER BY rel.id
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_58")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching with LIMIT and optionally matching using a relationship that is already bound
    Given an empty graph
    And having executed:
      """
      CREATE (:A)-[:T]->(:B)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_59() {
        val cypher = CypherParser.parseString('''
        MATCH ()-[r]->()
        WITH r
        LIMIT 1
        OPTIONAL MATCH (a2)-[r]->(b2)
        RETURN a2, r, b2
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_59")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching with LIMIT and optionally matching using a relationship and node that are both already bound
    Given an empty graph
    And having executed:
      """
      CREATE (:A)-[:T]->(:B)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_60() {
        val cypher = CypherParser.parseString('''
        MATCH (a1)-[r]->()
        WITH r, a1
        LIMIT 1
        OPTIONAL MATCH (a1)-[r]->(b2)
        RETURN a1, r, b2
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_60")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching with LIMIT, then matching again using a relationship and node that are both already bound along with an additional predicate
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:T]->()
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_61() {
        val cypher = CypherParser.parseString('''
        MATCH (a1)-[r]->()
        WITH r, a1
        LIMIT 1
        MATCH (a1:X)-[r]->(b2)
        RETURN a1, r, b2
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_61")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching with LIMIT and predicates, then matching again using a relationship and node that are both already bound along with a duplicate predicate
    Given an empty graph
    And having executed:
      """
      CREATE (:X:Y)-[:T]->()
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_62() {
        val cypher = CypherParser.parseString('''
        MATCH (a1:X:Y)-[r]->()
        WITH r, a1
        LIMIT 1
        MATCH (a1:Y)-[r]->(b2)
        RETURN a1, r, b2
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_62")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching twice with conflicting relationship types on same relationship
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:T]->()
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_63() {
        val cypher = CypherParser.parseString('''
        MATCH (a1)-[r:T]->()
        WITH r, a1
        LIMIT 1
        MATCH (a1)-[r:Y]->(b2)
        RETURN a1, r, b2
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_63")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching twice with duplicate relationship types on same relationship
    Given an empty graph
    And having executed:
      """
      CREATE (:A)-[:T]->(:B)
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_64() {
        val cypher = CypherParser.parseString('''
        MATCH (a1)-[r:T]->() WITH r, a1
        LIMIT 1
        MATCH (a1)-[r:T]->(b2)
        RETURN a1, r, b2
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_64")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching relationships into a list and matching variable length using the list
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (b:B), (c:C)
      CREATE (a)-[:Y]->(b),
             (b)-[:Y]->(c)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_65() {
        val cypher = CypherParser.parseString('''
        MATCH ()-[r1]->()-[r2]->()
        WITH [r1, r2] AS rs
        LIMIT 1
        MATCH (first)-[rs*]->(second)
        RETURN first, second
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_65")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching relationships into a list and matching variable length using the list, with bound nodes
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (b:B), (c:C)
      CREATE (a)-[:Y]->(b),
             (b)-[:Y]->(c)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_66() {
        val cypher = CypherParser.parseString('''
        MATCH (a)-[r1]->()-[r2]->(b)
        WITH [r1, r2] AS rs, a AS first, b AS second
        LIMIT 1
        MATCH (first)-[rs*]->(second)
        RETURN first, second
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_66")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching relationships into a list and matching variable length using the list, with bound nodes, wrong direction
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (b:B), (c:C)
      CREATE (a)-[:Y]->(b),
             (b)-[:Y]->(c)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_67() {
        val cypher = CypherParser.parseString('''
        MATCH (a)-[r1]->()-[r2]->(b)
        WITH [r1, r2] AS rs, a AS second, b AS first
        LIMIT 1
        MATCH (first)-[rs*]->(second)
        RETURN first, second
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_67")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching and optionally matching with bound nodes in reverse direction
    Given an empty graph
    And having executed:
      """
      CREATE (:A)-[:T]->(:B)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_68() {
        val cypher = CypherParser.parseString('''
        MATCH (a1)-[r]->()
        WITH r, a1
        LIMIT 1
        OPTIONAL MATCH (a1)<-[r]-(b2)
        RETURN a1, r, b2
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_68")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching and optionally matching with unbound nodes and equality predicate in reverse direction
    Given an empty graph
    And having executed:
      """
      CREATE (:A)-[:T]->(:B)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_69() {
        val cypher = CypherParser.parseString('''
        MATCH (a1)-[r]->()
        WITH r, a1
        LIMIT 1
        OPTIONAL MATCH (a2)<-[r]-(b2)
        WHERE a1 = a2
        RETURN a1, r, b2, a2
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_69")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Fail when using property access on primitive type
    Given an empty graph
    And having executed:
      """
      CREATE ({prop: 42})
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_70() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        WITH n.prop AS n2
        RETURN n2.prop
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_70")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching and returning ordered results, with LIMIT
    Given an empty graph
    And having executed:
      """
      CREATE ({bar: 1}), ({bar: 3}), ({bar: 2})
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_71() {
        val cypher = CypherParser.parseString('''
        MATCH (foo)
        RETURN foo.bar AS x
        ORDER BY x DESC
        LIMIT 4
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_71")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Counting an empty graph
    Given an empty graph
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_72() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        RETURN count(a) > 0
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_72")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching variable length pattern with property predicate
    Given an empty graph
    And having executed:
      """
      CREATE (a:Artist:A), (b:Artist:B), (c:Artist:C)
      CREATE (a)-[:WORKED_WITH {year: 1987}]->(b),
             (b)-[:WORKED_WITH {year: 1988}]->(c)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_73() {
        val cypher = CypherParser.parseString('''
        MATCH (a:Artist)-[:WORKED_WITH* {year: 1988}]->(b:Artist)
        RETURN *
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_73")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Variable length pattern checking labels on endnodes
    Given an empty graph
    And having executed:
      """
      CREATE (a:Label {id: 0}), (b:Label {id: 1}), (c:Label {id: 2})
      CREATE (a)-[:T]->(b),
             (b)-[:T]->(c)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_74() {
        val cypher = CypherParser.parseString('''
        MATCH (a), (b)
        WHERE a.id = 0
        AND (a)-[:T]->(b:Label)
        OR (a)-[:T*]->(b:MissingLabel)
        RETURN DISTINCT b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_74")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Variable length pattern with label predicate on both sides
    Given an empty graph
    And having executed:
      """
      CREATE (a:Blue), (b:Red), (c:Green), (d:Yellow)
      CREATE (a)-[:T]->(b),
             (b)-[:T]->(c),
             (b)-[:T]->(d)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_75() {
        val cypher = CypherParser.parseString('''
        MATCH (a:Blue)-[r*]->(b:Green)
        RETURN count(r)
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_75")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Undirected named path
    Given an empty graph
    And having executed:
      """
      CREATE (a:Movie), (b)
      CREATE (b)-[:T]->(a)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_76() {
        val cypher = CypherParser.parseString('''
        MATCH p = (n:Movie)--(m)
        RETURN p
        LIMIT 1
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_76")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Named path with WITH
    Given an empty graph
    And having executed:
      """
      CREATE ()
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_77() {
        val cypher = CypherParser.parseString('''
        MATCH p = (a)
        WITH p
        RETURN p
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_77")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Named path with alternating directed/undirected relationships
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (b:B), (c:C)
      CREATE (b)-[:T]->(a),
             (c)-[:T]->(b)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_78() {
        val cypher = CypherParser.parseString('''
        MATCH p = (n)-->(m)--(o)
        RETURN p
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_78")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Named path with multiple alternating directed/undirected relationships
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (b:B), (c:C), (d:D)
      CREATE (b)-[:T]->(a),
             (c)-[:T]->(b),
             (d)-[:T]->(c)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_79() {
        val cypher = CypherParser.parseString('''
        MATCH path = (n)-->(m)--(o)--(p)
        RETURN path
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_79")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Named path with undirected fixed variable length pattern
    Given an empty graph
    And having executed:
      """
      CREATE (db1:Start), (db2:End), (mid), (other)
      CREATE (mid)-[:CONNECTED_TO]->(db1),
             (mid)-[:CONNECTED_TO]->(db2),
             (mid)-[:CONNECTED_TO]->(db2),
             (mid)-[:CONNECTED_TO]->(other),
             (mid)-[:CONNECTED_TO]->(other)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_80() {
        val cypher = CypherParser.parseString('''
        MATCH topRoute = (:Start)<-[:CONNECTED_TO]-()-[:CONNECTED_TO*3..3]-(:End)
        RETURN topRoute
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_80")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Returning a node property value
    Given an empty graph
    And having executed:
      """
      CREATE ({prop: 1})
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_81() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        RETURN a.prop
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_81")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Returning a relationship property value
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:T {prop: 1}]->()
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_82() {
        val cypher = CypherParser.parseString('''
        MATCH ()-[r]->()
        RETURN r.prop
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_82")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Projecting nodes and relationships
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (b:B)
      CREATE (a)-[:T]->(b)
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_83() {
        val cypher = CypherParser.parseString('''
        MATCH (a)-[r]->()
        RETURN a AS foo, r AS bar
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_83")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Missing node property should become null
    Given an empty graph
    And having executed:
      """
      CREATE ({foo: 1})
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_84() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        RETURN a.bar
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_84")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Missing relationship property should become null
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:T {foo: 1}]->()
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_85() {
        val cypher = CypherParser.parseString('''
        MATCH ()-[r]->()
        RETURN r.bar
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_85")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Returning multiple node property values
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'Philip J. Fry', age: 2046, seasons: [1, 2, 3, 4, 5, 6, 7]})
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_86() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        RETURN a.name, a.age, a.seasons
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_86")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Adding a property and a literal in projection
    Given an empty graph
    And having executed:
      """
      CREATE ({prop: 1})
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_87() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        RETURN a.prop + 1 AS foo
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_87")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Adding list properties in projection
    Given an empty graph
    And having executed:
      """
      CREATE ({prop1: [1, 2, 3], prop2: [4, 5]})
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_88() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        RETURN a.prop2 + a.prop1 AS foo
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_88")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Variable length relationship variables are lists of relationships
    Given an empty graph
    And having executed:
      """
      CREATE (a), (b), (c)
      CREATE (a)-[:T]->(b)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_89() {
        val cypher = CypherParser.parseString('''
        MATCH ()-[r*0..1]-()
        RETURN last(r) AS l
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_89")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Variable length patterns and nulls
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (b:B)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_90() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        OPTIONAL MATCH (a)-[:FOO]->(b:B)
        OPTIONAL MATCH (b)<-[:BAR*]-(c:B)
        RETURN a, b, c
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_90")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Projecting a list of nodes and relationships
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (b:B)
      CREATE (a)-[:T]->(b)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_91() {
        val cypher = CypherParser.parseString('''
        MATCH (n)-[r]->(m)
        RETURN [n, r, m] AS r
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_91")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Projecting a map of nodes and relationships
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (b:B)
      CREATE (a)-[:T]->(b)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_92() {
        val cypher = CypherParser.parseString('''
        MATCH (n)-[r]->(m)
        RETURN {node1: n, rel: r, node2: m} AS m
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_92")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Respecting direction when matching existing path
    Given an empty graph
    And having executed:
      """
      CREATE (a {prop: 'a'}), (b {prop: 'b'})
      CREATE (a)-[:T]->(b)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_93() {
        val cypher = CypherParser.parseString('''
        MATCH p = ({prop: 'a'})-->({prop: 'b'})
        RETURN p
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_93")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Respecting direction when matching non-existent path
    Given an empty graph
    And having executed:
      """
      CREATE (a {prop: 'a'}), (b {prop: 'b'})
      CREATE (a)-[:T]->(b)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_94() {
        val cypher = CypherParser.parseString('''
        MATCH p = ({prop: 'a'})<--({prop: 'b'})
        RETURN p
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_94")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Respecting direction when matching non-existent path with multiple directions
    Given an empty graph
    And having executed:
      """
      CREATE (a), (b)
      CREATE (a)-[:T]->(b),
             (b)-[:T]->(a)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_95() {
        val cypher = CypherParser.parseString('''
        MATCH p = (n)-->(k)<--(n)
        RETURN p
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_95")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching path with both directions should respect other directions
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (b:B)
      CREATE (a)-[:T1]->(b),
             (b)-[:T2]->(a)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_96() {
        val cypher = CypherParser.parseString('''
        MATCH p = (n)<-->(k)<--(n)
        RETURN p
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_96")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching path with multiple bidirectional relationships
    Given an empty graph
    And having executed:
      """
      CREATE (a:A), (b:B)
      CREATE (a)-[:T1]->(b),
             (b)-[:T2]->(a)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_97() {
        val cypher = CypherParser.parseString('''
        MATCH p=(n)<-->(k)<-->(n)
        RETURN p
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_97")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching nodes with many labels
    Given an empty graph
    And having executed:
      """
      CREATE (a:A:B:C:D:E:F:G:H:I:J:K:L:M),
             (b:U:V:W:X:Y:Z)
      CREATE (a)-[:T]->(b)
      """
    */
    @Test
    @Category(RegressionTests)
    def void testMatchAcceptance2_98() {
        val cypher = CypherParser.parseString('''
        MATCH (n:A:B:C:D:E:F:G:H:I:J:K:L:M)-[:T]->(m:Z:Y:X:W:V:U)
        RETURN n, m
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_98")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching longer variable length paths
    Given an empty graph
    And having executed:
      """
      CREATE (a {prop: 'start'}), (b {prop: 'end'})
      WITH *
      UNWIND range(1, 20) AS i
      CREATE (n {prop: i})
      WITH [a] + collect(n) + [b] AS nodeList
      UNWIND range(0, size(nodeList) - 2, 1) AS i
      WITH nodeList[i] AS n1, nodeList[i+1] AS n2
      CREATE (n1)-[:T]->(n2)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_99() {
        val cypher = CypherParser.parseString('''
        MATCH (n {prop: 'start'})-[:T*]->(m {prop: 'end'})
        RETURN m
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_99")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Matching a self-loop
    Given an empty graph
    And having executed:
      """
      CREATE (a)
      CREATE (a)-[:T]->(a)
      """
    */
    @Test
    @Category(FailingTests)
    def void testMatchAcceptance2_101() {
        val cypher = CypherParser.parseString('''
        MATCH ()-[r]-()
        RETURN type(r) AS r
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance2_101")
        Cypher2Relalg.processCypher(cypher)
    }

}
