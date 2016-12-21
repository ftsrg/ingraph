package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class TriadicSelectionParserTest {
    
    /*
    Scenario: Handling triadic friend of a friend
    Given the binary-tree-1 graph
    */
    @Test
    @Category(FailingTests)
    def void testTriadicSelection_01() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)-[:KNOWS]->(b)-->(c)
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TriadicSelection_01")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling triadic friend of a friend that is not a friend
    Given the binary-tree-1 graph
    */
    @Test
    @Category(FailingTests)
    def void testTriadicSelection_02() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)-[:KNOWS]->(b)-->(c)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NULL
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TriadicSelection_02")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling triadic friend of a friend that is not a friend with different relationship type
    Given the binary-tree-1 graph
    */
    @Test
    @Category(FailingTests)
    def void testTriadicSelection_03() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)-[:KNOWS]->(b)-->(c)
        OPTIONAL MATCH (a)-[r:FOLLOWS]->(c)
        WITH c WHERE r IS NULL
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TriadicSelection_03")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling triadic friend of a friend that is not a friend with superset of relationship type
    Given the binary-tree-1 graph
    */
    @Test
    @Category(FailingTests)
    def void testTriadicSelection_04() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)-[:KNOWS]->(b)-->(c)
        OPTIONAL MATCH (a)-[r]->(c)
        WITH c WHERE r IS NULL
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TriadicSelection_04")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling triadic friend of a friend that is not a friend with implicit subset of relationship type
    Given the binary-tree-1 graph
    */
    @Test
    @Category(FailingTests)
    def void testTriadicSelection_05() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)-->(b)-->(c)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NULL
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TriadicSelection_05")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling triadic friend of a friend that is not a friend with explicit subset of relationship type
    Given the binary-tree-1 graph
    */
    @Test
    @Category(FailingTests)
    def void testTriadicSelection_06() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)-[:KNOWS|FOLLOWS]->(b)-->(c)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NULL
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TriadicSelection_06")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling triadic friend of a friend that is not a friend with same labels
    Given the binary-tree-2 graph
    */
    @Test
    @Category(FailingTests)
    def void testTriadicSelection_07() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)-[:KNOWS]->(b:X)-->(c:X)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NULL
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TriadicSelection_07")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling triadic friend of a friend that is not a friend with different labels
    Given the binary-tree-2 graph
    */
    @Test
    @Category(FailingTests)
    def void testTriadicSelection_08() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)-[:KNOWS]->(b:X)-->(c:Y)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NULL
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TriadicSelection_08")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling triadic friend of a friend that is not a friend with implicit subset of labels
    Given the binary-tree-2 graph
    */
    @Test
    @Category(FailingTests)
    def void testTriadicSelection_09() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)-[:KNOWS]->(b)-->(c:X)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NULL
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TriadicSelection_09")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling triadic friend of a friend that is not a friend with implicit superset of labels
    Given the binary-tree-2 graph
    */
    @Test
    @Category(FailingTests)
    def void testTriadicSelection_10() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)-[:KNOWS]->(b:X)-->(c)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NULL
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TriadicSelection_10")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling triadic friend of a friend that is a friend
    Given the binary-tree-2 graph
    */
    @Test
    @Category(FailingTests)
    def void testTriadicSelection_11() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)-[:KNOWS]->(b)-->(c)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NOT NULL
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TriadicSelection_11")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling triadic friend of a friend that is a friend with different relationship type
    Given the binary-tree-1 graph
    */
    @Test
    @Category(FailingTests)
    def void testTriadicSelection_12() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)-[:KNOWS]->(b)-->(c)
        OPTIONAL MATCH (a)-[r:FOLLOWS]->(c)
        WITH c WHERE r IS NOT NULL
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TriadicSelection_12")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling triadic friend of a friend that is a friend with superset of relationship type
    Given the binary-tree-1 graph
    */
    @Test
    @Category(FailingTests)
    def void testTriadicSelection_13() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)-[:KNOWS]->(b)-->(c)
        OPTIONAL MATCH (a)-[r]->(c)
        WITH c WHERE r IS NOT NULL
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TriadicSelection_13")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling triadic friend of a friend that is a friend with implicit subset of relationship type
    Given the binary-tree-1 graph
    */
    @Test
    @Category(FailingTests)
    def void testTriadicSelection_14() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)-->(b)-->(c)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NOT NULL
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TriadicSelection_14")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling triadic friend of a friend that is a friend with explicit subset of relationship type
    Given the binary-tree-1 graph
    */
    @Test
    @Category(FailingTests)
    def void testTriadicSelection_15() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)-[:KNOWS|FOLLOWS]->(b)-->(c)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NOT NULL
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TriadicSelection_15")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling triadic friend of a friend that is a friend with same labels
    Given the binary-tree-2 graph
    */
    @Test
    @Category(FailingTests)
    def void testTriadicSelection_16() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)-[:KNOWS]->(b:X)-->(c:X)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NOT NULL
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TriadicSelection_16")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling triadic friend of a friend that is a friend with different labels
    Given the binary-tree-2 graph
    */
    @Test
    @Category(FailingTests)
    def void testTriadicSelection_17() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)-[:KNOWS]->(b:X)-->(c:Y)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NOT NULL
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TriadicSelection_17")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling triadic friend of a friend that is a friend with implicit subset of labels
    Given the binary-tree-2 graph
    */
    @Test
    @Category(FailingTests)
    def void testTriadicSelection_18() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)-[:KNOWS]->(b)-->(c:X)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NOT NULL
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TriadicSelection_18")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling triadic friend of a friend that is a friend with implicit superset of labels
    Given the binary-tree-2 graph
    */
    @Test
    @Category(FailingTests)
    def void testTriadicSelection_19() {
        val cypher = CypherParser.parseString('''
        MATCH (a:A)-[:KNOWS]->(b:X)-->(c)
        OPTIONAL MATCH (a)-[r:KNOWS]->(c)
        WITH c WHERE r IS NOT NULL
        RETURN c.name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TriadicSelection_19")
        Cypher2Relalg.processCypher(cypher)
    }

}
