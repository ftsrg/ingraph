package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import ingraph.relalg.util.RelalgUtil
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
    @Category(FailingTests)
    def void testStartingPointAcceptance_01() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN n
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartingPointAcceptance_01")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartingPointAcceptance_01")
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
    @Category(FailingTests)
    def void testStartingPointAcceptance_02() {
        val cypher = CypherParser.parseString('''
        MATCH (n:Animal)
        RETURN n
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartingPointAcceptance_02")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartingPointAcceptance_02")
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
    @Category(FailingTests)
    def void testStartingPointAcceptance_03() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        WHERE n.prop = 2
        RETURN n
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartingPointAcceptance_03")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartingPointAcceptance_03")
    }

}
