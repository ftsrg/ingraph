package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import ingraph.relalg.util.RelalgUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class LiteralsParserTest {
    
    /*
    Scenario: Return an integer
    */
    @Test
    @Category(FailingTests)
    def void testLiterals_01() {
        val cypher = CypherParser.parseString('''
        RETURN 1 AS literal
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/Literals_01")
        val container = Cypher2Relalg.processCypher(cypher, "testLiterals_01")
        RelalgUtil.save(container, "relalg-models/tck/Literals_01")
    }

    /*
    Scenario: Return a float
    */
    @Test
    @Category(FailingTests)
    def void testLiterals_02() {
        val cypher = CypherParser.parseString('''
        RETURN 1.0 AS literal
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/Literals_02")
        val container = Cypher2Relalg.processCypher(cypher, "testLiterals_02")
        RelalgUtil.save(container, "relalg-models/tck/Literals_02")
    }

    /*
    Scenario: Return a float in exponent form
    */
    @Test
    @Category(FailingTests)
    def void testLiterals_03() {
        val cypher = CypherParser.parseString('''
        RETURN -1e-9 AS literal
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/Literals_03")
        val container = Cypher2Relalg.processCypher(cypher, "testLiterals_03")
        RelalgUtil.save(container, "relalg-models/tck/Literals_03")
    }

    /*
    Scenario: Return a boolean
    */
    @Test
    @Category(FailingTests)
    def void testLiterals_04() {
        val cypher = CypherParser.parseString('''
        RETURN true AS literal
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/Literals_04")
        val container = Cypher2Relalg.processCypher(cypher, "testLiterals_04")
        RelalgUtil.save(container, "relalg-models/tck/Literals_04")
    }

    /*
    Scenario: Return a single-quoted string
    */
    @Test
    @Category(FailingTests)
    def void testLiterals_05() {
        val cypher = CypherParser.parseString('''
        RETURN '' AS literal
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/Literals_05")
        val container = Cypher2Relalg.processCypher(cypher, "testLiterals_05")
        RelalgUtil.save(container, "relalg-models/tck/Literals_05")
    }

    /*
    Scenario: Return a double-quoted string
    */
    @Test
    @Category(FailingTests)
    def void testLiterals_06() {
        val cypher = CypherParser.parseString('''
        RETURN "" AS literal
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/Literals_06")
        val container = Cypher2Relalg.processCypher(cypher, "testLiterals_06")
        RelalgUtil.save(container, "relalg-models/tck/Literals_06")
    }

    /*
    Scenario: Return null
    */
    @Test
    @Category(FailingTests)
    def void testLiterals_07() {
        val cypher = CypherParser.parseString('''
        RETURN null AS literal
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/Literals_07")
        val container = Cypher2Relalg.processCypher(cypher, "testLiterals_07")
        RelalgUtil.save(container, "relalg-models/tck/Literals_07")
    }

    /*
    Scenario: Return an empty list
    */
    @Test
    @Category(FailingTests)
    def void testLiterals_08() {
        val cypher = CypherParser.parseString('''
        RETURN [] AS literal
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/Literals_08")
        val container = Cypher2Relalg.processCypher(cypher, "testLiterals_08")
        RelalgUtil.save(container, "relalg-models/tck/Literals_08")
    }

    /*
    Scenario: Return a nonempty list
    */
    @Test
    @Category(FailingTests)
    def void testLiterals_09() {
        val cypher = CypherParser.parseString('''
        RETURN [0, 1, 2] AS literal
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/Literals_09")
        val container = Cypher2Relalg.processCypher(cypher, "testLiterals_09")
        RelalgUtil.save(container, "relalg-models/tck/Literals_09")
    }

    /*
    Scenario: Return an empty map
    */
    @Test
    @Category(FailingTests)
    def void testLiterals_10() {
        val cypher = CypherParser.parseString('''
        RETURN {} AS literal
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/Literals_10")
        val container = Cypher2Relalg.processCypher(cypher, "testLiterals_10")
        RelalgUtil.save(container, "relalg-models/tck/Literals_10")
    }

    /*
    Scenario: Return a nonempty map
    */
    @Test
    @Category(FailingTests)
    def void testLiterals_11() {
        val cypher = CypherParser.parseString('''
        RETURN {k1: 0, k2: 'string'} AS literal
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/Literals_11")
        val container = Cypher2Relalg.processCypher(cypher, "testLiterals_11")
        RelalgUtil.save(container, "relalg-models/tck/Literals_11")
    }

}
