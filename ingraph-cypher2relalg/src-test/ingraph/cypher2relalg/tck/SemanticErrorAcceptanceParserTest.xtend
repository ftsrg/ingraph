package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2RelAlg
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil

class SemanticErrorAcceptanceParserTest {
    
    /*
    Scenario: Handling property access on the Any type
    */
    @Test
    def void testSemanticErrorAcceptance_01() {
        val cypher = CypherParser.parseString('''
        WITH [{prop: 0}, 1] AS list
        RETURN (list[0]).prop
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/SemanticErrorAcceptance_01")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Failing when performing property access on a non-map 1
    */
    @Test
    def void testSemanticErrorAcceptance_02() {
        val cypher = CypherParser.parseString('''
        WITH [{prop: 0}, 1] AS list
        RETURN (list[1]).prop
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/SemanticErrorAcceptance_02")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Bad arguments for `range()`
    */
    @Test
    def void testSemanticErrorAcceptance_04() {
        val cypher = CypherParser.parseString('''
        RETURN range(2, 8, 0)
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/SemanticErrorAcceptance_04")
        Cypher2RelAlg.processCypher(cypher)
    }

}
