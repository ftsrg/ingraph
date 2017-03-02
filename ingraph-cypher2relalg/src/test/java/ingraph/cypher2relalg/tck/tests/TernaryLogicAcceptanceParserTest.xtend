package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import ingraph.relalg.util.RelalgUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class TernaryLogicAcceptanceParserTest {
    
    /*
    Scenario: The inverse of a null is a null
    */
    @Test
    @Category(FailingTests)
    def void testTernaryLogicAcceptance_01() {
        val cypher = CypherParser.parseString('''
        RETURN NOT null AS value
        ''')
        CypherUtil.save(cypher, "cypher-asts/TernaryLogicAcceptance_01")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/TernaryLogicAcceptance_01")
    }

    /*
    Scenario: A literal null IS null
    */
    @Test
    @Category(FailingTests)
    def void testTernaryLogicAcceptance_02() {
        val cypher = CypherParser.parseString('''
        RETURN null IS NULL AS value
        ''')
        CypherUtil.save(cypher, "cypher-asts/TernaryLogicAcceptance_02")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/TernaryLogicAcceptance_02")
    }

    /*
    Scenario: A literal null is not IS NOT null
    */
    @Test
    @Category(FailingTests)
    def void testTernaryLogicAcceptance_03() {
        val cypher = CypherParser.parseString('''
        RETURN null IS NOT NULL AS value
        ''')
        CypherUtil.save(cypher, "cypher-asts/TernaryLogicAcceptance_03")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/TernaryLogicAcceptance_03")
    }

    /*
    Scenario: It is unknown - i.e. null - if a null is equal to a null
    */
    @Test
    @Category(FailingTests)
    def void testTernaryLogicAcceptance_04() {
        val cypher = CypherParser.parseString('''
        RETURN null = null AS value
        ''')
        CypherUtil.save(cypher, "cypher-asts/TernaryLogicAcceptance_04")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/TernaryLogicAcceptance_04")
    }

    /*
    Scenario: It is unknown - i.e. null - if a null is not equal to a null
    */
    @Test
    @Category(FailingTests)
    def void testTernaryLogicAcceptance_05() {
        val cypher = CypherParser.parseString('''
        RETURN null <> null AS value
        ''')
        CypherUtil.save(cypher, "cypher-asts/TernaryLogicAcceptance_05")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/TernaryLogicAcceptance_05")
    }

}
