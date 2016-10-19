package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil

class LiteralsParserTest {
    
    /*
    Scenario: Return an integer
    */
    @Test
    def void testLiterals_01() {
        val cypher = CypherParser.parseString('''
        RETURN 1 AS literal
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Literals_01")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Return a float
    */
    @Test
    def void testLiterals_02() {
        val cypher = CypherParser.parseString('''
        RETURN 1.0 AS literal
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Literals_02")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Return a float in exponent form
    */
    @Test
    def void testLiterals_03() {
        val cypher = CypherParser.parseString('''
        RETURN -1e-9 AS literal
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Literals_03")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Return a boolean
    */
    @Test
    def void testLiterals_04() {
        val cypher = CypherParser.parseString('''
        RETURN true AS literal
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Literals_04")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Return a single-quoted string
    */
    @Test
    def void testLiterals_05() {
        val cypher = CypherParser.parseString('''
        RETURN '' AS literal
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Literals_05")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Return a double-quoted string
    */
    @Test
    def void testLiterals_06() {
        val cypher = CypherParser.parseString('''
        RETURN "" AS literal
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Literals_06")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Return null
    */
    @Test
    def void testLiterals_07() {
        val cypher = CypherParser.parseString('''
        RETURN null AS literal
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Literals_07")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Return an empty list
    */
    @Test
    def void testLiterals_08() {
        val cypher = CypherParser.parseString('''
        RETURN [] AS literal
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Literals_08")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Return a nonempty list
    */
    @Test
    def void testLiterals_09() {
        val cypher = CypherParser.parseString('''
        RETURN [0, 1, 2] AS literal
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Literals_09")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Return an empty map
    */
    @Test
    def void testLiterals_10() {
        val cypher = CypherParser.parseString('''
        RETURN {} AS literal
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Literals_10")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Return a nonempty map
    */
    @Test
    def void testLiterals_11() {
        val cypher = CypherParser.parseString('''
        RETURN {k1: 0, k2: 'string'} AS literal
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Literals_11")
        Cypher2Relalg.processCypher(cypher)
    }

}
