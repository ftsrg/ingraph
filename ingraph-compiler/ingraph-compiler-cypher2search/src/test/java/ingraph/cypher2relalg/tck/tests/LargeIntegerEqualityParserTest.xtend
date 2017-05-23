package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import ingraph.relalg.util.RelalgUtil
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
        CypherUtil.save(cypher, "cypher-asts/tck/LargeIntegerEquality_01")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/LargeIntegerEquality_01")
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
        CypherUtil.save(cypher, "cypher-asts/tck/LargeIntegerEquality_02")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/LargeIntegerEquality_02")
    }

    /*
    Scenario: Handling explicit equality of large integer
    */
    @Test
    @Category(RegressionTests)
    def void testLargeIntegerEquality_03() {
        val cypher = CypherParser.parseString('''
        MATCH (p:Label)
        WHERE p.id = 4611686018427387905
        RETURN p.id
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/LargeIntegerEquality_03")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/LargeIntegerEquality_03")
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
        CypherUtil.save(cypher, "cypher-asts/tck/LargeIntegerEquality_04")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/LargeIntegerEquality_04")
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
        CypherUtil.save(cypher, "cypher-asts/tck/LargeIntegerEquality_05")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/LargeIntegerEquality_05")
    }

}
