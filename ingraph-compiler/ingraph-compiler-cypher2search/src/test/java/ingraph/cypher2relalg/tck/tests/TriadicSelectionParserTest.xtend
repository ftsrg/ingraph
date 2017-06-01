package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import ingraph.relalg.util.RelalgUtil
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
        CypherUtil.save(cypher, "cypher-asts/tck/TriadicSelection_01")
        val container = Cypher2Relalg.processCypher(cypher, "testTriadicSelection_01")
        RelalgUtil.save(container, "relalg-models/tck/TriadicSelection_01")
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
        CypherUtil.save(cypher, "cypher-asts/tck/TriadicSelection_02")
        val container = Cypher2Relalg.processCypher(cypher, "testTriadicSelection_02")
        RelalgUtil.save(container, "relalg-models/tck/TriadicSelection_02")
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
        CypherUtil.save(cypher, "cypher-asts/tck/TriadicSelection_03")
        val container = Cypher2Relalg.processCypher(cypher, "testTriadicSelection_03")
        RelalgUtil.save(container, "relalg-models/tck/TriadicSelection_03")
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
        CypherUtil.save(cypher, "cypher-asts/tck/TriadicSelection_04")
        val container = Cypher2Relalg.processCypher(cypher, "testTriadicSelection_04")
        RelalgUtil.save(container, "relalg-models/tck/TriadicSelection_04")
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
        CypherUtil.save(cypher, "cypher-asts/tck/TriadicSelection_05")
        val container = Cypher2Relalg.processCypher(cypher, "testTriadicSelection_05")
        RelalgUtil.save(container, "relalg-models/tck/TriadicSelection_05")
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
        CypherUtil.save(cypher, "cypher-asts/tck/TriadicSelection_06")
        val container = Cypher2Relalg.processCypher(cypher, "testTriadicSelection_06")
        RelalgUtil.save(container, "relalg-models/tck/TriadicSelection_06")
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
        CypherUtil.save(cypher, "cypher-asts/tck/TriadicSelection_07")
        val container = Cypher2Relalg.processCypher(cypher, "testTriadicSelection_07")
        RelalgUtil.save(container, "relalg-models/tck/TriadicSelection_07")
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
        CypherUtil.save(cypher, "cypher-asts/tck/TriadicSelection_08")
        val container = Cypher2Relalg.processCypher(cypher, "testTriadicSelection_08")
        RelalgUtil.save(container, "relalg-models/tck/TriadicSelection_08")
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
        CypherUtil.save(cypher, "cypher-asts/tck/TriadicSelection_09")
        val container = Cypher2Relalg.processCypher(cypher, "testTriadicSelection_09")
        RelalgUtil.save(container, "relalg-models/tck/TriadicSelection_09")
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
        CypherUtil.save(cypher, "cypher-asts/tck/TriadicSelection_10")
        val container = Cypher2Relalg.processCypher(cypher, "testTriadicSelection_10")
        RelalgUtil.save(container, "relalg-models/tck/TriadicSelection_10")
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
        CypherUtil.save(cypher, "cypher-asts/tck/TriadicSelection_11")
        val container = Cypher2Relalg.processCypher(cypher, "testTriadicSelection_11")
        RelalgUtil.save(container, "relalg-models/tck/TriadicSelection_11")
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
        CypherUtil.save(cypher, "cypher-asts/tck/TriadicSelection_12")
        val container = Cypher2Relalg.processCypher(cypher, "testTriadicSelection_12")
        RelalgUtil.save(container, "relalg-models/tck/TriadicSelection_12")
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
        CypherUtil.save(cypher, "cypher-asts/tck/TriadicSelection_13")
        val container = Cypher2Relalg.processCypher(cypher, "testTriadicSelection_13")
        RelalgUtil.save(container, "relalg-models/tck/TriadicSelection_13")
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
        CypherUtil.save(cypher, "cypher-asts/tck/TriadicSelection_14")
        val container = Cypher2Relalg.processCypher(cypher, "testTriadicSelection_14")
        RelalgUtil.save(container, "relalg-models/tck/TriadicSelection_14")
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
        CypherUtil.save(cypher, "cypher-asts/tck/TriadicSelection_15")
        val container = Cypher2Relalg.processCypher(cypher, "testTriadicSelection_15")
        RelalgUtil.save(container, "relalg-models/tck/TriadicSelection_15")
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
        CypherUtil.save(cypher, "cypher-asts/tck/TriadicSelection_16")
        val container = Cypher2Relalg.processCypher(cypher, "testTriadicSelection_16")
        RelalgUtil.save(container, "relalg-models/tck/TriadicSelection_16")
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
        CypherUtil.save(cypher, "cypher-asts/tck/TriadicSelection_17")
        val container = Cypher2Relalg.processCypher(cypher, "testTriadicSelection_17")
        RelalgUtil.save(container, "relalg-models/tck/TriadicSelection_17")
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
        CypherUtil.save(cypher, "cypher-asts/tck/TriadicSelection_18")
        val container = Cypher2Relalg.processCypher(cypher, "testTriadicSelection_18")
        RelalgUtil.save(container, "relalg-models/tck/TriadicSelection_18")
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
        CypherUtil.save(cypher, "cypher-asts/tck/TriadicSelection_19")
        val container = Cypher2Relalg.processCypher(cypher, "testTriadicSelection_19")
        RelalgUtil.save(container, "relalg-models/tck/TriadicSelection_19")
    }

}
