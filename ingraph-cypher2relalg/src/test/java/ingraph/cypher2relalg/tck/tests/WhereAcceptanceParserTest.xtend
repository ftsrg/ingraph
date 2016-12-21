package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class WhereAcceptanceParserTest {
    
    /*
    Scenario: NOT and false
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'a'})
      """
    */
    @Test
    @Category(FailingTests)
    def void testWhereAcceptance_01() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        WHERE NOT(n.name = 'apa' AND false)
        RETURN n
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/WhereAcceptance_01")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Fail when trying to compare strings and numbers
    Given an empty graph
    And having executed:
      """
      CREATE (:Label {prop: '15'})
      """
    */
    @Test
    @Category(RegressionTests)
    def void testWhereAcceptance_02() {
        val cypher = CypherParser.parseString('''
        MATCH (n:Label)
        WHERE n.prop < 10
        RETURN n.prop AS prop
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/WhereAcceptance_02")
        Cypher2Relalg.processCypher(cypher)
    }

}
