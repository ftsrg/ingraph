package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2RelAlg
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil

class EqualsAcceptanceParserTest {
    
    /*
    Scenario: Number-typed integer comparison
    Given an empty graph
    And having executed:
      """
      CREATE ({id: 0})
      """
    */
    @Test
    def void testEqualsAcceptance_01() {
        val cypher = CypherParser.parseString('''
        WITH collect([0, 0.0]) AS numbers
        UNWIND numbers AS arr
        WITH arr[0] AS expected
        MATCH (n) WHERE toInteger(n.id) = expected
        RETURN n
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/EqualsAcceptance_01")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Number-typed float comparison
    Given an empty graph
    And having executed:
      """
      CREATE ({id: 0})
      """
    */
    @Test
    def void testEqualsAcceptance_02() {
        val cypher = CypherParser.parseString('''
        WITH collect([0.5, 0]) AS numbers
        UNWIND numbers AS arr
        WITH arr[0] AS expected
        MATCH (n) WHERE toInteger(n.id) = expected
        RETURN n
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/EqualsAcceptance_02")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Any-typed string comparison
    Given an empty graph
    And having executed:
      """
      CREATE ({id: 0})
      """
    */
    @Test
    def void testEqualsAcceptance_03() {
        val cypher = CypherParser.parseString('''
        WITH collect(['0', 0]) AS things
        UNWIND things AS arr
        WITH arr[0] AS expected
        MATCH (n) WHERE toInteger(n.id) = expected
        RETURN n
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/EqualsAcceptance_03")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Comparing nodes to nodes
    Given an empty graph
    And having executed:
      """
      CREATE ()
      """
    */
    @Test
    def void testEqualsAcceptance_04() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WITH a
        MATCH (b)
        WHERE a = b
        RETURN count(b)
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/EqualsAcceptance_04")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Comparing relationships to relationships
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:T]->()
      """
    */
    @Test
    def void testEqualsAcceptance_05() {
        val cypher = CypherParser.parseString('''
        MATCH ()-[a]->()
        WITH a
        MATCH ()-[b]->()
        WHERE a = b
        RETURN count(b)
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/EqualsAcceptance_05")
        Cypher2RelAlg.processCypher(cypher)
    }

}
