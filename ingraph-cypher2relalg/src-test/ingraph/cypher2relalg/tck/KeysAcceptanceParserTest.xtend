package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2RelAlg
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil

class KeysAcceptanceParserTest {
    
    /*
    Scenario: Using `keys()` on a single node, non-empty result
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'Andres', surname: 'Lopez'})
      """
    */
    @Test
    def void testKeysAcceptance_01() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        UNWIND keys(n) AS x
        RETURN DISTINCT x AS theProps
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/KeysAcceptance_01")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Using `keys()` on multiple nodes, non-empty result
    Given an empty graph
    And having executed:
      """
      CREATE ({name: 'Andres', surname: 'Lopez'}),
             ({otherName: 'Andres', otherSurname: 'Lopez'})
      """
    */
    @Test
    def void testKeysAcceptance_02() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        UNWIND keys(n) AS x
        RETURN DISTINCT x AS theProps
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/KeysAcceptance_02")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Using `keys()` on a single node, empty result
    Given an empty graph
    And having executed:
      """
      CREATE ()
      """
    */
    @Test
    def void testKeysAcceptance_03() {
        val cypher = CypherParser.parseString('''
        MATCH (n)
        UNWIND keys(n) AS x
        RETURN DISTINCT x AS theProps
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/KeysAcceptance_03")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Using `keys()` on an optionally matched node
    Given an empty graph
    And having executed:
      """
      CREATE ()
      """
    */
    @Test
    def void testKeysAcceptance_04() {
        val cypher = CypherParser.parseString('''
        OPTIONAL MATCH (n)
        UNWIND keys(n) AS x
        RETURN DISTINCT x AS theProps
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/KeysAcceptance_04")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Using `keys()` on a relationship, non-empty result
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:KNOWS {level: 'bad', year: '2015'}]->()
      """
    */
    @Test
    def void testKeysAcceptance_05() {
        val cypher = CypherParser.parseString('''
        MATCH ()-[r:KNOWS]-()
        UNWIND keys(r) AS x
        RETURN DISTINCT x AS theProps
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/KeysAcceptance_05")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Using `keys()` on a relationship, empty result
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:KNOWS]->()
      """
    */
    @Test
    def void testKeysAcceptance_06() {
        val cypher = CypherParser.parseString('''
        MATCH ()-[r:KNOWS]-()
        UNWIND keys(r) AS x
        RETURN DISTINCT x AS theProps
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/KeysAcceptance_06")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Using `keys()` on an optionally matched relationship
    Given an empty graph
    And having executed:
      """
      CREATE ()-[:KNOWS]->()
      """
    */
    @Test
    def void testKeysAcceptance_07() {
        val cypher = CypherParser.parseString('''
        OPTIONAL MATCH ()-[r:KNOWS]-()
        UNWIND keys(r) AS x
        RETURN DISTINCT x AS theProps
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/KeysAcceptance_07")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Using `keys()` on a literal map
    Given any graph
    */
    @Test
    def void testKeysAcceptance_08() {
        val cypher = CypherParser.parseString('''
        RETURN keys({name: 'Alice', age: 38, address: {city: 'London', residential: true}}) AS k
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/KeysAcceptance_08")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Using `keys()` on a parameter map
    Given any graph
    And parameters are:
      | param | {name: 'Alice', age: 38, address: {city: 'London', residential: true}} |
    */
    @Test
    def void testKeysAcceptance_09() {
        val cypher = CypherParser.parseString('''
        RETURN keys($param) AS k
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/KeysAcceptance_09")
        Cypher2RelAlg.processCypher(cypher)
    }

}
