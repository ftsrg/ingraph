package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2RelAlg
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil

class StartsWithAcceptanceParserTest {
    
    /*
    Scenario: Finding exact matches
    */
    @Test
    def void testStartsWithAcceptance_01() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH 'ABCDEF'
        RETURN a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartsWithAcceptance_01")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Finding beginning of string
    */
    @Test
    def void testStartsWithAcceptance_02() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH 'ABC'
        RETURN a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartsWithAcceptance_02")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Finding end of string 1
    */
    @Test
    def void testStartsWithAcceptance_03() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name ENDS WITH 'DEF'
        RETURN a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartsWithAcceptance_03")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Finding end of string 2
    */
    @Test
    def void testStartsWithAcceptance_04() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name ENDS WITH 'AB'
        RETURN a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartsWithAcceptance_04")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Finding middle of string
    */
    @Test
    def void testStartsWithAcceptance_05() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH 'a'
        AND a.name ENDS WITH 'f'
        RETURN a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartsWithAcceptance_05")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Finding the empty string
    */
    @Test
    def void testStartsWithAcceptance_06() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH ''
        RETURN a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartsWithAcceptance_06")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Finding when the middle is known
    */
    @Test
    def void testStartsWithAcceptance_07() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name CONTAINS 'CD'
        RETURN a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartsWithAcceptance_07")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Finding strings starting with whitespace
    And having executed:
      """
      CREATE (:Label {name: ' Foo '}),
             (:Label {name: '\nFoo\n'}),
             (:Label {name: '\tFoo\t'})
      """
    */
    @Test
    def void testStartsWithAcceptance_08() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH ' '
        RETURN a.name AS name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartsWithAcceptance_08")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Finding strings starting with newline
    And having executed:
      """
      CREATE (:Label {name: ' Foo '}),
             (:Label {name: '\nFoo\n'}),
             (:Label {name: '\tFoo\t'})
      """
    */
    @Test
    def void testStartsWithAcceptance_09() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH '\n'
        RETURN a.name AS name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartsWithAcceptance_09")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Finding strings ending with newline
    And having executed:
      """
      CREATE (:Label {name: ' Foo '}),
             (:Label {name: '\nFoo\n'}),
             (:Label {name: '\tFoo\t'})
      """
    */
    @Test
    def void testStartsWithAcceptance_10() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name ENDS WITH '\n'
        RETURN a.name AS name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartsWithAcceptance_10")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Finding strings ending with whitespace
    And having executed:
      """
      CREATE (:Label {name: ' Foo '}),
             (:Label {name: '\nFoo\n'}),
             (:Label {name: '\tFoo\t'})
      """
    */
    @Test
    def void testStartsWithAcceptance_11() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name ENDS WITH ' '
        RETURN a.name AS name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartsWithAcceptance_11")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Finding strings containing whitespace
    And having executed:
      """
      CREATE (:Label {name: ' Foo '}),
             (:Label {name: '\nFoo\n'}),
             (:Label {name: '\tFoo\t'})
      """
    */
    @Test
    def void testStartsWithAcceptance_12() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name CONTAINS ' '
        RETURN a.name AS name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartsWithAcceptance_12")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Finding strings containing newline
    And having executed:
      """
      CREATE (:Label {name: ' Foo '}),
             (:Label {name: '\nFoo\n'}),
             (:Label {name: '\tFoo\t'})
      """
    */
    @Test
    def void testStartsWithAcceptance_13() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name CONTAINS '\n'
        RETURN a.name AS name
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartsWithAcceptance_13")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: No string starts with null
    */
    @Test
    def void testStartsWithAcceptance_14() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH null
        RETURN a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartsWithAcceptance_14")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: No string does not start with null
    */
    @Test
    def void testStartsWithAcceptance_15() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE NOT a.name STARTS WITH null
        RETURN a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartsWithAcceptance_15")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: No string ends with null
    */
    @Test
    def void testStartsWithAcceptance_16() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name ENDS WITH null
        RETURN a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartsWithAcceptance_16")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: No string does not end with null
    */
    @Test
    def void testStartsWithAcceptance_17() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE NOT a.name ENDS WITH null
        RETURN a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartsWithAcceptance_17")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: No string contains null
    */
    @Test
    def void testStartsWithAcceptance_18() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name CONTAINS null
        RETURN a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartsWithAcceptance_18")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: No string does not contain null
    */
    @Test
    def void testStartsWithAcceptance_19() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE NOT a.name CONTAINS null
        RETURN a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartsWithAcceptance_19")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: Combining string operators
    */
    @Test
    def void testStartsWithAcceptance_20() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH 'A'
        AND a.name CONTAINS 'C'
        AND a.name ENDS WITH 'EF'
        RETURN a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartsWithAcceptance_20")
        Cypher2RelAlg.processCypher(cypher)
    }

    /*
    Scenario: NOT with CONTAINS
    */
    @Test
    def void testStartsWithAcceptance_21() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE NOT a.name CONTAINS 'b'
        RETURN a
        ''')
        CypherUtil.save(cypher, "../ingraph-cypxmi/tck/StartsWithAcceptance_21")
        Cypher2RelAlg.processCypher(cypher)
    }

}
