package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2RelAlg
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil

class ExpressionAcceptanceParserTest {
    
    /*
    Scenario: Execute n[0]
    */
    @Test
    def void testExpressionAcceptance_01() {
        val cypher = CypherParser.parseString('''
        RETURN [1, 2, 3][0] AS value
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_01")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Execute n['name'] in read queries
    And having executed:
      """
      CREATE ({name: 'Apa'})
      """
    */
    @Test
    def void testExpressionAcceptance_02() {
        val cypher = CypherParser.parseString('''
        MATCH (n {name: 'Apa'})
        RETURN n['nam' + 'e'] AS value
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_02")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Use dynamic property lookup based on parameters when there is no type information
    And parameters are:
      | expr | {name: 'Apa'} |
      | idx  | 'name'        |
    */
    @Test
    def void testExpressionAcceptance_04() {
        val cypher = CypherParser.parseString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx] AS value
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_04")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Use dynamic property lookup based on parameters when there is rhs type information
    And parameters are:
      | expr | {name: 'Apa'} |
      | idx  | 'name'        |
    */
    @Test
    def void testExpressionAcceptance_06() {
        val cypher = CypherParser.parseString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[toString(idx)] AS value
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_06")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Use collection lookup based on parameters when there is no type information
    And parameters are:
      | expr | ['Apa'] |
      | idx  | 0       |
    */
    @Test
    def void testExpressionAcceptance_07() {
        val cypher = CypherParser.parseString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx] AS value
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_07")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Use collection lookup based on parameters when there is lhs type information
    And parameters are:
      | idx | 0 |
    */
    @Test
    def void testExpressionAcceptance_08() {
        val cypher = CypherParser.parseString('''
        WITH ['Apa'] AS expr
        RETURN expr[$idx] AS value
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_08")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Use collection lookup based on parameters when there is rhs type information
    And parameters are:
      | expr | ['Apa'] |
      | idx  | 0       |
    */
    @Test
    def void testExpressionAcceptance_09() {
        val cypher = CypherParser.parseString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[toInteger(idx)] AS value
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_09")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Fail at runtime when attempting to index with an Int into a Map
    And parameters are:
      | expr | {name: 'Apa'} |
      | idx  | 0             |
    */
    @Test
    def void testExpressionAcceptance_10() {
        val cypher = CypherParser.parseString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_10")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Fail at runtime when trying to index into a map with a non-string
    And parameters are:
      | expr | {name: 'Apa'} |
      | idx  | 12.3          |
    */
    @Test
    def void testExpressionAcceptance_11() {
        val cypher = CypherParser.parseString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_11")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Fail at runtime when attempting to index with a String into a Collection
    And parameters are:
      | expr | ['Apa'] |
      | idx  | 'name'  |
    */
    @Test
    def void testExpressionAcceptance_12() {
        val cypher = CypherParser.parseString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_12")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Fail at runtime when trying to index into a list with a list
    And parameters are:
      | expr | ['Apa'] |
      | idx  | ['Apa'] |
    */
    @Test
    def void testExpressionAcceptance_13() {
        val cypher = CypherParser.parseString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_13")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Fail at runtime when trying to index something which is not a map or collection
    And parameters are:
      | expr | 100 |
      | idx  | 0   |
    */
    @Test
    def void testExpressionAcceptance_14() {
        val cypher = CypherParser.parseString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_14")
        Cypher2RelAlg.processCypher(cypher)
    }

}
