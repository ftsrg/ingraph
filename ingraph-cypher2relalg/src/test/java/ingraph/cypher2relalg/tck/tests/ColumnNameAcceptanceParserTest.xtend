package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import ingraph.relalg.util.RelalgUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class ColumnNameAcceptanceParserTest {
    
    /*
    Scenario: Keeping used expression 1
    */
    @Test
    @Category(FailingTests)
    def void testColumnNameAcceptance_01() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN cOuNt( * )
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/ColumnNameAcceptance_01")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/ColumnNameAcceptance_01")
    }

    /*
    Scenario: Keeping used expression 2
    */
    @Test
    @Category(FailingTests)
    def void testColumnNameAcceptance_02() {
        val cypher = CypherParser.parseString('''
        MATCH p = (n)-->(b)
        RETURN nOdEs( p )
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/ColumnNameAcceptance_02")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/ColumnNameAcceptance_02")
    }

    /*
    Scenario: Keeping used expression 3
    */
    @Test
    @Category(FailingTests)
    def void testColumnNameAcceptance_03() {
        val cypher = CypherParser.parseString('''
        MATCH p = (n)-->(b)
        RETURN coUnt( dIstInct p )
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/ColumnNameAcceptance_03")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/ColumnNameAcceptance_03")
    }

    /*
    Scenario: Keeping used expression 4
    */
    @Test
    @Category(FailingTests)
    def void testColumnNameAcceptance_04() {
        val cypher = CypherParser.parseString('''
        MATCH p = (n)-->(b)
        RETURN aVg(    n.aGe     )
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/ColumnNameAcceptance_04")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/ColumnNameAcceptance_04")
    }

}
