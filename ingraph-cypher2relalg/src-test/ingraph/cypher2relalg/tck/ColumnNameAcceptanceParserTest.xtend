package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2RelAlg
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil

class ColumnNameAcceptanceParserTest {
    
    /*
    Scenario: Keeping used expression 1
    */
    @Test
    def void testColumnNameAcceptance_01() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        RETURN cOuNt( * )
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ColumnNameAcceptance_01")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Keeping used expression 2
    */
    @Test
    def void testColumnNameAcceptance_02() {
        val cypher = CypherParser.parseString('''
        MATCH p = (n)-->(b)
        RETURN nOdEs( p )
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ColumnNameAcceptance_02")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Keeping used expression 3
    */
    @Test
    def void testColumnNameAcceptance_03() {
        val cypher = CypherParser.parseString('''
        MATCH p = (n)-->(b)
        RETURN coUnt( dIstInct p )
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ColumnNameAcceptance_03")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Keeping used expression 4
    */
    @Test
    def void testColumnNameAcceptance_04() {
        val cypher = CypherParser.parseString('''
        MATCH p = (n)-->(b)
        RETURN aVg(    n.aGe     )
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ColumnNameAcceptance_04")
        Cypher2RelAlg.processCypher(cypher)
    }

}
