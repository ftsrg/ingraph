package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import ingraph.relalg.util.RelalgUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class EqualsAcceptanceParserTest {
    
    /*
    Scenario: Number-typed integer comparison
    Given an empty graph
    And having executed:
      """
      CREATE ({id: 0})
      """
    */
    @Test
    @Category(FailingTests)
    def void testEqualsAcceptance_01() {
        val cypher = CypherParser.parseString('''
        WITH collect([0, 0.0]) AS numbers
        UNWIND numbers AS arr
        WITH arr[0] AS expected
        MATCH (n) WHERE toInteger(n.id) = expected
        RETURN n
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/EqualsAcceptance_01")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/EqualsAcceptance_01")
    }

    /*
    Scenario: Number-typed float comparison
    Given an empty graph
    And having executed:
      """
      CREATE ({id: 0})
      """
    */
    @Test
    @Category(FailingTests)
    def void testEqualsAcceptance_02() {
        val cypher = CypherParser.parseString('''
        WITH collect([0.5, 0]) AS numbers
        UNWIND numbers AS arr
        WITH arr[0] AS expected
        MATCH (n) WHERE toInteger(n.id) = expected
        RETURN n
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/EqualsAcceptance_02")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/EqualsAcceptance_02")
    }

    /*
    Scenario: Any-typed string comparison
    Given an empty graph
    And having executed:
      """
      CREATE ({id: 0})
      """
    */
    @Test
    @Category(FailingTests)
    def void testEqualsAcceptance_03() {
        val cypher = CypherParser.parseString('''
        WITH collect(['0', 0]) AS things
        UNWIND things AS arr
        WITH arr[0] AS expected
        MATCH (n) WHERE toInteger(n.id) = expected
        RETURN n
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/EqualsAcceptance_03")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/EqualsAcceptance_03")
    }

    /*
    Scenario: Comparing nodes to nodes
    Given an empty graph
    And having executed:
      """
      CREATE ()
      """
    */
    @Test
    @Category(FailingTests)
    def void testEqualsAcceptance_04() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WITH a
        MATCH (b)
        WHERE a = b
        RETURN count(b)
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/EqualsAcceptance_04")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/EqualsAcceptance_04")
    }

    /*
    Scenario: Comparing relationships to relationships
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:T]->()
      """
    */
    @Test
    @Category(FailingTests)
    def void testEqualsAcceptance_05() {
        val cypher = CypherParser.parseString('''
        MATCH ()-[a]->()
        WITH a
        MATCH ()-[b]->()
        WHERE a = b
        RETURN count(b)
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/EqualsAcceptance_05")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/EqualsAcceptance_05")
    }

}
