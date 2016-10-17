package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.CypherParser

class SemanticErrorAcceptanceParserTest {
    
    /*
    Scenario: Handling property access on the Any type
    */
    @Test
    def void testSemanticErrorAcceptance_01() {
        CypherParser.parseString('''
        WITH [{prop: 0}, 1] AS list
        RETURN (list[0]).prop
        ''')
    }

    /*
    Scenario: Failing when performing property access on a non-map 1
    */
    @Test
    def void testSemanticErrorAcceptance_02() {
        CypherParser.parseString('''
        WITH [{prop: 0}, 1] AS list
        RETURN (list[1]).prop
        ''')
    }

    /*
    Scenario: Bad arguments for `range()`
    */
    @Test
    def void testSemanticErrorAcceptance_04() {
        CypherParser.parseString('''
        RETURN range(2, 8, 0)
        ''')
    }

}
