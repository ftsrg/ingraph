package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class ComparabilityParserTest {
    
    /*
    Scenario: Fail when comparing nodes to parameters
    Given an empty graph
    And having executed:
      """
      CREATE ()
      """
    And parameters are:
      | param | 'foo' |
    */
    @Test
    @Category(FailingTests)
    def void testComparability_01() {
        val cypher = CypherParser.parseString('''
        MATCH (b)
        WHERE b = $param
        RETURN b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Comparability_01")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Fail when comparing parameters to nodes
    Given an empty graph
    And having executed:
      """
      CREATE ()
      """
    And parameters are:
      | param | 'foo' |
    */
    @Test
    @Category(FailingTests)
    def void testComparability_02() {
        val cypher = CypherParser.parseString('''
        MATCH (b)
        WHERE $param = b
        RETURN b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Comparability_02")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Comparing nodes to properties
    Given an empty graph
    And having executed:
      """
      CREATE ({val: 17})
      """
    */
    @Test
    @Category(FailingTests)
    def void testComparability_03() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a = a.val
        RETURN count(a)
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Comparability_03")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Fail when comparing nodes to relationships
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:T]->()
      """
    */
    @Test
    @Category(FailingTests)
    def void testComparability_04() {
        val cypher = CypherParser.parseString('''
        MATCH (a)-[b]->()
        RETURN a = b
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Comparability_04")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Fail when comparing relationships to nodes
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:T]->()
      """
    */
    @Test
    @Category(FailingTests)
    def void testComparability_05() {
        val cypher = CypherParser.parseString('''
        MATCH (a)-[b]->()
        RETURN b = a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Comparability_05")
        Cypher2Relalg.processCypher(cypher)
    }

}
