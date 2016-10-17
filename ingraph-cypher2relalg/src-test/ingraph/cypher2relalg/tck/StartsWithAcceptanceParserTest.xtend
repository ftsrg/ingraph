package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.CypherParser

class StartsWithAcceptanceParserTest {
    
    /*
    Scenario: Finding exact matches
    */
    @Test
    def void testStartsWithAcceptance_01() {
        CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH 'ABCDEF'
        RETURN a
        ''')
    }

    /*
    Scenario: Finding beginning of string
    */
    @Test
    def void testStartsWithAcceptance_02() {
        CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH 'ABC'
        RETURN a
        ''')
    }

    /*
    Scenario: Finding end of string 1
    */
    @Test
    def void testStartsWithAcceptance_03() {
        CypherParser.parseString('''
        MATCH (a)
        WHERE a.name ENDS WITH 'DEF'
        RETURN a
        ''')
    }

    /*
    Scenario: Finding end of string 2
    */
    @Test
    def void testStartsWithAcceptance_04() {
        CypherParser.parseString('''
        MATCH (a)
        WHERE a.name ENDS WITH 'AB'
        RETURN a
        ''')
    }

    /*
    Scenario: Finding middle of string
    */
    @Test
    def void testStartsWithAcceptance_05() {
        CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH 'a'
        AND a.name ENDS WITH 'f'
        RETURN a
        ''')
    }

    /*
    Scenario: Finding the empty string
    */
    @Test
    def void testStartsWithAcceptance_06() {
        CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH ''
        RETURN a
        ''')
    }

    /*
    Scenario: Finding when the middle is known
    */
    @Test
    def void testStartsWithAcceptance_07() {
        CypherParser.parseString('''
        MATCH (a)
        WHERE a.name CONTAINS 'CD'
        RETURN a
        ''')
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
        CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH ' '
        RETURN a.name AS name
        ''')
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
        CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH '\n'
        RETURN a.name AS name
        ''')
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
        CypherParser.parseString('''
        MATCH (a)
        WHERE a.name ENDS WITH '\n'
        RETURN a.name AS name
        ''')
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
        CypherParser.parseString('''
        MATCH (a)
        WHERE a.name ENDS WITH ' '
        RETURN a.name AS name
        ''')
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
        CypherParser.parseString('''
        MATCH (a)
        WHERE a.name CONTAINS ' '
        RETURN a.name AS name
        ''')
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
        CypherParser.parseString('''
        MATCH (a)
        WHERE a.name CONTAINS '\n'
        RETURN a.name AS name
        ''')
    }

    /*
    Scenario: No string starts with null
    */
    @Test
    def void testStartsWithAcceptance_14() {
        CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH null
        RETURN a
        ''')
    }

    /*
    Scenario: No string does not start with null
    */
    @Test
    def void testStartsWithAcceptance_15() {
        CypherParser.parseString('''
        MATCH (a)
        WHERE NOT a.name STARTS WITH null
        RETURN a
        ''')
    }

    /*
    Scenario: No string ends with null
    */
    @Test
    def void testStartsWithAcceptance_16() {
        CypherParser.parseString('''
        MATCH (a)
        WHERE a.name ENDS WITH null
        RETURN a
        ''')
    }

    /*
    Scenario: No string does not end with null
    */
    @Test
    def void testStartsWithAcceptance_17() {
        CypherParser.parseString('''
        MATCH (a)
        WHERE NOT a.name ENDS WITH null
        RETURN a
        ''')
    }

    /*
    Scenario: No string contains null
    */
    @Test
    def void testStartsWithAcceptance_18() {
        CypherParser.parseString('''
        MATCH (a)
        WHERE a.name CONTAINS null
        RETURN a
        ''')
    }

    /*
    Scenario: No string does not contain null
    */
    @Test
    def void testStartsWithAcceptance_19() {
        CypherParser.parseString('''
        MATCH (a)
        WHERE NOT a.name CONTAINS null
        RETURN a
        ''')
    }

    /*
    Scenario: Combining string operators
    */
    @Test
    def void testStartsWithAcceptance_20() {
        CypherParser.parseString('''
        MATCH (a)
        WHERE a.name STARTS WITH 'A'
        AND a.name CONTAINS 'C'
        AND a.name ENDS WITH 'EF'
        RETURN a
        ''')
    }

    /*
    Scenario: NOT with CONTAINS
    */
    @Test
    def void testStartsWithAcceptance_21() {
        CypherParser.parseString('''
        MATCH (a)
        WHERE NOT a.name CONTAINS 'b'
        RETURN a
        ''')
    }

}
