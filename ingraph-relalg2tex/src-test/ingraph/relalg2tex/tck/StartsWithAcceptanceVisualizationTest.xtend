package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.AlgebraTreeDrawer

class StartsWithAcceptanceVisualizationTest {

    val static AlgebraTreeDrawer drawer = new AlgebraTreeDrawer(true)
    
    /*
    Scenario: Finding exact matches
    */
    @Test
    def void testStartsWithAcceptance_01() {
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE a.name STARTS WITH 'ABCDEF'
        RETURN a
        ''')
        drawer.serialize(container, "StartsWithAcceptance")
    }

    /*
    Scenario: Finding beginning of string
    */
    @Test
    def void testStartsWithAcceptance_02() {
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE a.name STARTS WITH 'ABC'
        RETURN a
        ''')
        drawer.serialize(container, "StartsWithAcceptance")
    }

    /*
    Scenario: Finding end of string 1
    */
    @Test
    def void testStartsWithAcceptance_03() {
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE a.name ENDS WITH 'DEF'
        RETURN a
        ''')
        drawer.serialize(container, "StartsWithAcceptance")
    }

    /*
    Scenario: Finding end of string 2
    */
    @Test
    def void testStartsWithAcceptance_04() {
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE a.name ENDS WITH 'AB'
        RETURN a
        ''')
        drawer.serialize(container, "StartsWithAcceptance")
    }

    /*
    Scenario: Finding middle of string
    */
    @Test
    def void testStartsWithAcceptance_05() {
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE a.name STARTS WITH 'a'
        AND a.name ENDS WITH 'f'
        RETURN a
        ''')
        drawer.serialize(container, "StartsWithAcceptance")
    }

    /*
    Scenario: Finding the empty string
    */
    @Test
    def void testStartsWithAcceptance_06() {
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE a.name STARTS WITH ''
        RETURN a
        ''')
        drawer.serialize(container, "StartsWithAcceptance")
    }

    /*
    Scenario: Finding when the middle is known
    */
    @Test
    def void testStartsWithAcceptance_07() {
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE a.name CONTAINS 'CD'
        RETURN a
        ''')
        drawer.serialize(container, "StartsWithAcceptance")
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
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE a.name STARTS WITH ' '
        RETURN a.name AS name
        ''')
        drawer.serialize(container, "StartsWithAcceptance")
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
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE a.name STARTS WITH '\n'
        RETURN a.name AS name
        ''')
        drawer.serialize(container, "StartsWithAcceptance")
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
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE a.name ENDS WITH '\n'
        RETURN a.name AS name
        ''')
        drawer.serialize(container, "StartsWithAcceptance")
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
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE a.name ENDS WITH ' '
        RETURN a.name AS name
        ''')
        drawer.serialize(container, "StartsWithAcceptance")
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
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE a.name CONTAINS ' '
        RETURN a.name AS name
        ''')
        drawer.serialize(container, "StartsWithAcceptance")
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
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE a.name CONTAINS '\n'
        RETURN a.name AS name
        ''')
        drawer.serialize(container, "StartsWithAcceptance")
    }

    /*
    Scenario: No string starts with null
    */
    @Test
    def void testStartsWithAcceptance_14() {
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE a.name STARTS WITH null
        RETURN a
        ''')
        drawer.serialize(container, "StartsWithAcceptance")
    }

    /*
    Scenario: No string does not start with null
    */
    @Test
    def void testStartsWithAcceptance_15() {
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE NOT a.name STARTS WITH null
        RETURN a
        ''')
        drawer.serialize(container, "StartsWithAcceptance")
    }

    /*
    Scenario: No string ends with null
    */
    @Test
    def void testStartsWithAcceptance_16() {
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE a.name ENDS WITH null
        RETURN a
        ''')
        drawer.serialize(container, "StartsWithAcceptance")
    }

    /*
    Scenario: No string does not end with null
    */
    @Test
    def void testStartsWithAcceptance_17() {
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE NOT a.name ENDS WITH null
        RETURN a
        ''')
        drawer.serialize(container, "StartsWithAcceptance")
    }

    /*
    Scenario: No string contains null
    */
    @Test
    def void testStartsWithAcceptance_18() {
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE a.name CONTAINS null
        RETURN a
        ''')
        drawer.serialize(container, "StartsWithAcceptance")
    }

    /*
    Scenario: No string does not contain null
    */
    @Test
    def void testStartsWithAcceptance_19() {
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE NOT a.name CONTAINS null
        RETURN a
        ''')
        drawer.serialize(container, "StartsWithAcceptance")
    }

    /*
    Scenario: Combining string operators
    */
    @Test
    def void testStartsWithAcceptance_20() {
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE a.name STARTS WITH 'A'
        AND a.name CONTAINS 'C'
        AND a.name ENDS WITH 'EF'
        RETURN a
        ''')
        drawer.serialize(container, "StartsWithAcceptance")
    }

    /*
    Scenario: NOT with CONTAINS
    */
    @Test
    def void testStartsWithAcceptance_21() {
        val container = RelalgParser.parse('''
        MATCH (a)
        WHERE NOT a.name CONTAINS 'b'
        RETURN a
        ''')
        drawer.serialize(container, "StartsWithAcceptance")
    }

}
