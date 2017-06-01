package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import ingraph.relalg.util.RelalgUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class ComparabilityParserTest {
    
    /*
    Scenario: Comparing strings and integers using > in an AND'd predicate
    Given an empty graph
    And having executed:
      """
      CREATE (root:Root)-[:T]->(:Child {id: 0}),
             (root)-[:T]->(:Child {id: 'xx'}),
             (root)-[:T]->(:Child)
      """
    */
    @Test
    @Category(FailingTests)
    def void testComparability_01() {
        val cypher = CypherParser.parseString('''
        MATCH (:Root)-->(i:Child)
        WHERE exists(i.id) AND i.id > 'x'
        RETURN i.id
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/Comparability_01")
        val container = Cypher2Relalg.processCypher(cypher, "testComparability_01")
        RelalgUtil.save(container, "relalg-models/tck/Comparability_01")
    }

    /*
    Scenario: Comparing strings and integers using > in a OR'd predicate
    Given an empty graph
    And having executed:
      """
      CREATE (root:Root)-[:T]->(:Child {id: 0}),
             (root)-[:T]->(:Child {id: 'xx'}),
             (root)-[:T]->(:Child)
      """
    */
    @Test
    @Category(FailingTests)
    def void testComparability_02() {
        val cypher = CypherParser.parseString('''
        MATCH (:Root)-->(i:Child)
        WHERE NOT exists(i.id) OR i.id > 'x'
        RETURN i.id
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/Comparability_02")
        val container = Cypher2Relalg.processCypher(cypher, "testComparability_02")
        RelalgUtil.save(container, "relalg-models/tck/Comparability_02")
    }

}
