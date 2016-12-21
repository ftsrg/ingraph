package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class StartingPointAcceptanceParserTest {
    
    /*
    Scenario: Find all nodes
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'a'}),
             ({name: 'b'}),
             ({name: 'c'})
      """
    */
    @Test
    @Category(RegressionTests)
    def void testStartingPointAcceptance_01() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartingPointAcceptance_01")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Find labelled nodes
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'a'}),
             (:Person),
             (:Animal),
             (:Animal)
      """
    */
    @Test
    @Category(RegressionTests)
    def void testStartingPointAcceptance_02() {
        val cypher = CypherParser.parseString('''
        MATCH (n:Animal)
        RETURN n
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartingPointAcceptance_02")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Find nodes by property
    Given an empty graph
    And having executed:
      """
      CREATE ({prop: 1}),
             ({prop: 2})
      """
    */
    @Test
    @Category(RegressionTests)
    def void testStartingPointAcceptance_03() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        WHERE n.prop = 2
        RETURN n
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartingPointAcceptance_03")
        Cypher2Relalg.processCypher(cypher)
    }

}
