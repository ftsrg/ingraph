package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class LargeIntegerEqualityParserTest {
    
    /*
    Scenario: Does not lose precision
    */
    @Test
    @Category(FailingTests)
    def void testLargeIntegerEquality_01() {
        val cypher = CypherParser.parseString('''
        MATCH (p:Label)
        RETURN p.id
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/LargeIntegerEquality_01")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling inlined equality of large integer
    */
    @Test
    @Category(FailingTests)
    def void testLargeIntegerEquality_02() {
        val cypher = CypherParser.parseString('''
        MATCH (p:Label {id: 4611686018427387905})
        RETURN p.id
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/LargeIntegerEquality_02")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling explicit equality of large integer
    */
    @Test
    @Category(FailingTests)
    def void testLargeIntegerEquality_03() {
        val cypher = CypherParser.parseString('''
        MATCH (p:Label)
        WHERE p.id = 4611686018427387905
        RETURN p.id
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/LargeIntegerEquality_03")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling inlined equality of large integer, non-equal values
    */
    @Test
    @Category(FailingTests)
    def void testLargeIntegerEquality_04() {
        val cypher = CypherParser.parseString('''
        MATCH (p:Label {id : 4611686018427387900})
        RETURN p.id
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/LargeIntegerEquality_04")
        Cypher2Relalg.processCypher(cypher)
    }

    /*
    Scenario: Handling explicit equality of large integer, non-equal values
    */
    @Test
    @Category(FailingTests)
    def void testLargeIntegerEquality_05() {
        val cypher = CypherParser.parseString('''
        MATCH (p:Label)
        WHERE p.id = 4611686018427387900
        RETURN p.id
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/LargeIntegerEquality_05")
        Cypher2Relalg.processCypher(cypher)
    }

}
