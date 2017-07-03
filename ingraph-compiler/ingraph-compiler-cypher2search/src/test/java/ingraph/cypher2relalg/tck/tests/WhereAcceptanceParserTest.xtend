package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import ingraph.relalg.util.RelalgUtil
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
        CypherUtil.save(cypher, "cypher-asts/tck/WhereAcceptance_01")
        val container = Cypher2Relalg.processCypher(cypher, "testWhereAcceptance_01")
        RelalgUtil.save(container, "relalg-models/tck/WhereAcceptance_01")
    }

}
