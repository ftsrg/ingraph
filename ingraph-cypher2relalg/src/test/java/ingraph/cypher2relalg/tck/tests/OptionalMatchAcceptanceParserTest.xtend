package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class OptionalMatchAcceptanceParserTest {
    
    /*
    Scenario: Return null when no matches due to inline label predicate
    */
    @Test
    @Category(RegressionTests)
    def void testOptionalMatchAcceptance_01() {
        val cypher = CypherParser.parseString('''
        MATCH (n:Single)
        OPTIONAL MATCH (n)-[r]-(m:NonExistent)
        RETURN r
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatchAcceptance_01")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Return null when no matches due to label predicate in WHERE
    */
    @Test
    @Category(FailingTests)
    def void testOptionalMatchAcceptance_02() {
        val cypher = CypherParser.parseString('''
        MATCH (n:Single)
        OPTIONAL MATCH (n)-[r]-(m)
        WHERE m:NonExistent
        RETURN r
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatchAcceptance_02")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Respect predicates on the OPTIONAL MATCH
    */
    @Test
    @Category(RegressionTests)
    def void testOptionalMatchAcceptance_03() {
        val cypher = CypherParser.parseString('''
        MATCH (n:Single)
        OPTIONAL MATCH (n)-[r]-(m)
        WHERE m.prop = 42
        RETURN m
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatchAcceptance_03")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Returning label predicate on null node
    */
    @Test
    @Category(RegressionTests)
    def void testOptionalMatchAcceptance_04() {
        val cypher = CypherParser.parseString('''
        MATCH (n:Single)
        OPTIONAL MATCH (n)-[r:TYPE]-(m)
        RETURN m:TYPE
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatchAcceptance_04")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: MATCH after OPTIONAL MATCH
    */
    @Test
    @Category(FailingTests)
    def void testOptionalMatchAcceptance_05() {
        val cypher = CypherParser.parseString('''
        MATCH (a:Single)
        OPTIONAL MATCH (a)-->(b:NonExistent)
        OPTIONAL MATCH (a)-->(c:NonExistent)
        WITH coalesce(b, c) AS x
        MATCH (x)-->(d)
        RETURN d
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatchAcceptance_05")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: WITH after OPTIONAL MATCH
    */
    @Test
    @Category(FailingTests)
    def void testOptionalMatchAcceptance_06() {
        val cypher = CypherParser.parseString('''
        OPTIONAL MATCH (a:A)
        WITH a AS a
        MATCH (b:B)
        RETURN a, b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatchAcceptance_06")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Named paths in optional matches
    */
    @Test
    @Category(FailingTests)
    def void testOptionalMatchAcceptance_07() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)
        OPTIONAL MATCH p = (a)-[:X]->(b)
        RETURN p
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatchAcceptance_07")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: OPTIONAL MATCH and bound nodes
    */
    @Test
    @Category(FailingTests)
    def void testOptionalMatchAcceptance_08() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A), (b:C)
        OPTIONAL MATCH (x)-->(b)
        RETURN x
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatchAcceptance_08")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: OPTIONAL MATCH with labels on the optional end node
    And having executed:
      """
      CREATE (:X), (x:X), (y1:Y), (y2:Y:Z)
      CREATE (x)-[:REL]->(y1),
             (x)-[:REL]->(y2)
      """
    */
    @Test
    @Category(FailingTests)
    def void testOptionalMatchAcceptance_09() {
        val cypher = CypherParser.parseString('''
        MATCH (a:X)
        OPTIONAL MATCH (a)-->(b:Y)
        RETURN b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatchAcceptance_09")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Named paths inside optional matches with node predicates
    */
    @Test
    @Category(FailingTests)
    def void testOptionalMatchAcceptance_10() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A), (b:B)
        OPTIONAL MATCH p = (a)-[:X]->(b)
        RETURN p
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatchAcceptance_10")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Variable length optional relationships
    */
    @Test
    @Category(FailingTests)
    def void testOptionalMatchAcceptance_11() {
        val cypher = CypherParser.parseString('''
        MATCH (a:Single)
        OPTIONAL MATCH (a)-[*]->(b)
        RETURN b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatchAcceptance_11")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Variable length optional relationships with length predicates
    */
    @Test
    @Category(FailingTests)
    def void testOptionalMatchAcceptance_12() {
        val cypher = CypherParser.parseString('''
        MATCH (a:Single)
        OPTIONAL MATCH (a)-[*3..]-(b)
        RETURN b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatchAcceptance_12")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Optionally matching self-loops
    */
    @Test
    @Category(RegressionTests)
    def void testOptionalMatchAcceptance_13() {
        val cypher = CypherParser.parseString('''
        MATCH (a:B)
        OPTIONAL MATCH (a)-[r]-(a)
        RETURN r
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatchAcceptance_13")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Optionally matching self-loops without matches
    */
    @Test
    @Category(FailingTests)
    def void testOptionalMatchAcceptance_14() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE NOT (a:B)
        OPTIONAL MATCH (a)-[r]->(a)
        RETURN r
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatchAcceptance_14")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Variable length optional relationships with bound nodes
    */
    @Test
    @Category(FailingTests)
    def void testOptionalMatchAcceptance_15() {
        val cypher = CypherParser.parseString('''
        MATCH (a:Single), (x:C)
        OPTIONAL MATCH (a)-[*]->(x)
        RETURN x
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatchAcceptance_15")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Variable length optional relationships with bound nodes, no matches
    */
    @Test
    @Category(FailingTests)
    def void testOptionalMatchAcceptance_16() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A), (b:B)
        OPTIONAL MATCH p = (a)-[*]->(b)
        RETURN p
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatchAcceptance_16")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Longer pattern with bound nodes
    */
    @Test
    @Category(FailingTests)
    def void testOptionalMatchAcceptance_17() {
        val cypher = CypherParser.parseString('''
        MATCH (a:Single), (c:C)
        OPTIONAL MATCH (a)-->(b)-->(c)
        RETURN b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatchAcceptance_17")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Longer pattern with bound nodes without matches
    */
    @Test
    @Category(FailingTests)
    def void testOptionalMatchAcceptance_18() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A), (c:C)
        OPTIONAL MATCH (a)-->(b)-->(c)
        RETURN b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatchAcceptance_18")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling correlated optional matches; first does not match implies second does not match
    */
    @Test
    @Category(FailingTests)
    def void testOptionalMatchAcceptance_19() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A), (b:B)
        OPTIONAL MATCH (a)-->(x)
        OPTIONAL MATCH (x)-[r]->(b)
        RETURN x, r
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatchAcceptance_19")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling optional matches between optionally matched entities
    */
    @Test
    @Category(FailingTests)
    def void testOptionalMatchAcceptance_20() {
        val cypher = CypherParser.parseString('''
        OPTIONAL MATCH (a:NotThere)
        WITH a
        MATCH (b:B)
        WITH a, b
        OPTIONAL MATCH (b)-[r:NOR_THIS]->(a)
        RETURN a, b, r
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatchAcceptance_20")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling optional matches between nulls
    */
    @Test
    @Category(FailingTests)
    def void testOptionalMatchAcceptance_21() {
        val cypher = CypherParser.parseString('''
        OPTIONAL MATCH (a:NotThere)
        OPTIONAL MATCH (b:NotThere)
        WITH a, b
        OPTIONAL MATCH (b)-[r:NOR_THIS]->(a)
        RETURN a, b, r
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatchAcceptance_21")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: OPTIONAL MATCH and `collect()`
    And having executed:
      """
      CREATE (:DoesExist {property: 42})
      CREATE (:DoesExist {property: 43})
      CREATE (:DoesExist {property: 44})
      """
    */
    @Test
    @Category(FailingTests)
    def void testOptionalMatchAcceptance_22() {
        val cypher = CypherParser.parseString('''
        OPTIONAL MATCH (f:DoesExist)
        OPTIONAL MATCH (n:DoesNotExist)
        RETURN collect(DISTINCT n.property) AS a, collect(DISTINCT f.property) AS b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/OptionalMatchAcceptance_22")
        Cypher2Relalg.processCypher(cypher)
    }

}
