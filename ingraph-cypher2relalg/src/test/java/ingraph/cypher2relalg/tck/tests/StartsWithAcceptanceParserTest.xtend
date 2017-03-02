package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import ingraph.relalg.util.RelalgUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class StartsWithAcceptanceParserTest {
    
    /*
    Scenario: Finding exact matches
    */
    @Test
    @Category(FailingTests)
    def void testStartsWithAcceptance_01() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH 'ABCDEF'
        RETURN a
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_01")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_01")
    }

    /*
    Scenario: Finding beginning of string
    */
    @Test
    @Category(FailingTests)
    def void testStartsWithAcceptance_02() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH 'ABC'
        RETURN a
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_02")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_02")
    }

    /*
    Scenario: Finding end of string 1
    */
    @Test
    @Category(FailingTests)
    def void testStartsWithAcceptance_03() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name ENDS WITH 'DEF'
        RETURN a
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_03")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_03")
    }

    /*
    Scenario: Finding end of string 2
    */
    @Test
    @Category(FailingTests)
    def void testStartsWithAcceptance_04() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name ENDS WITH 'AB'
        RETURN a
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_04")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_04")
    }

    /*
    Scenario: Finding middle of string
    */
    @Test
    @Category(FailingTests)
    def void testStartsWithAcceptance_05() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH 'a'
        AND a.name ENDS WITH 'f'
        RETURN a
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_05")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_05")
    }

    /*
    Scenario: Finding the empty string
    */
    @Test
    @Category(FailingTests)
    def void testStartsWithAcceptance_06() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH ''
        RETURN a
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_06")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_06")
    }

    /*
    Scenario: Finding when the middle is known
    */
    @Test
    @Category(FailingTests)
    def void testStartsWithAcceptance_07() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name CONTAINS 'CD'
        RETURN a
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_07")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_07")
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
    @Category(FailingTests)
    def void testStartsWithAcceptance_08() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH ' '
        RETURN a.name AS name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_08")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_08")
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
    @Category(FailingTests)
    def void testStartsWithAcceptance_09() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH '\n'
        RETURN a.name AS name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_09")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_09")
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
    @Category(FailingTests)
    def void testStartsWithAcceptance_10() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name ENDS WITH '\n'
        RETURN a.name AS name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_10")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_10")
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
    @Category(FailingTests)
    def void testStartsWithAcceptance_11() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name ENDS WITH ' '
        RETURN a.name AS name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_11")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_11")
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
    @Category(FailingTests)
    def void testStartsWithAcceptance_12() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name CONTAINS ' '
        RETURN a.name AS name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_12")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_12")
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
    @Category(FailingTests)
    def void testStartsWithAcceptance_13() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name CONTAINS '\n'
        RETURN a.name AS name
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_13")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_13")
    }

    /*
    Scenario: No string starts with null
    */
    @Test
    @Category(FailingTests)
    def void testStartsWithAcceptance_14() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH null
        RETURN a
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_14")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_14")
    }

    /*
    Scenario: No string does not start with null
    */
    @Test
    @Category(FailingTests)
    def void testStartsWithAcceptance_15() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE NOT a.name STARTS WITH null
        RETURN a
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_15")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_15")
    }

    /*
    Scenario: No string ends with null
    */
    @Test
    @Category(FailingTests)
    def void testStartsWithAcceptance_16() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name ENDS WITH null
        RETURN a
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_16")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_16")
    }

    /*
    Scenario: No string does not end with null
    */
    @Test
    @Category(FailingTests)
    def void testStartsWithAcceptance_17() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE NOT a.name ENDS WITH null
        RETURN a
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_17")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_17")
    }

    /*
    Scenario: No string contains null
    */
    @Test
    @Category(FailingTests)
    def void testStartsWithAcceptance_18() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name CONTAINS null
        RETURN a
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_18")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_18")
    }

    /*
    Scenario: No string does not contain null
    */
    @Test
    @Category(FailingTests)
    def void testStartsWithAcceptance_19() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE NOT a.name CONTAINS null
        RETURN a
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_19")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_19")
    }

    /*
    Scenario: Combining string operators
    */
    @Test
    @Category(FailingTests)
    def void testStartsWithAcceptance_20() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH 'A'
        AND a.name CONTAINS 'C'
        AND a.name ENDS WITH 'EF'
        RETURN a
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_20")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_20")
    }

    /*
    Scenario: NOT with CONTAINS
    */
    @Test
    @Category(FailingTests)
    def void testStartsWithAcceptance_21() {
        val cypher = CypherParser.parseString('''
        MATCH (a)
        WHERE NOT a.name CONTAINS 'b'
        RETURN a
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_21")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_21")
    }

    /*
    Scenario: Handling non-string operands for STARTS WITH
    */
    @Test
    @Category(FailingTests)
    def void testStartsWithAcceptance_22() {
        val cypher = CypherParser.parseString('''
        WITH [1, 3.14, true, [], {}, null] AS operands
        UNWIND operands AS op1
        UNWIND operands AS op2
        WITH op1 STARTS WITH op2 AS v
        RETURN v, count(*)
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_22")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_22")
    }

    /*
    Scenario: Handling non-string operands for CONTAINS
    */
    @Test
    @Category(FailingTests)
    def void testStartsWithAcceptance_23() {
        val cypher = CypherParser.parseString('''
        WITH [1, 3.14, true, [], {}, null] AS operands
        UNWIND operands AS op1
        UNWIND operands AS op2
        WITH op1 STARTS WITH op2 AS v
        RETURN v, count(*)
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_23")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_23")
    }

    /*
    Scenario: Handling non-string operands for ENDS WITH
    */
    @Test
    @Category(FailingTests)
    def void testStartsWithAcceptance_24() {
        val cypher = CypherParser.parseString('''
        WITH [1, 3.14, true, [], {}, null] AS operands
        UNWIND operands AS op1
        UNWIND operands AS op2
        WITH op1 STARTS WITH op2 AS v
        RETURN v, count(*)
        ''')
        CypherUtil.save(cypher, "cypher-asts/tck/StartsWithAcceptance_24")
        val container = Cypher2Relalg.processCypher(cypher)
        RelalgUtil.save(container, "relalg-models/tck/StartsWithAcceptance_24")
    }

}
