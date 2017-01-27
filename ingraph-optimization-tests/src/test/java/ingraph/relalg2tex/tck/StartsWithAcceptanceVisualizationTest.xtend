package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.inferencers.SchemaInferencer
import ingraph.relalg2tex.RelalgTreeSerializer

class StartsWithAcceptanceVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer
    extension SchemaInferencer inferencer = new SchemaInferencer
    
    /*
    Scenario: Finding exact matches
    */
    @Test
    def void testStartsWithAcceptance_01() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        WHERE a.name STARTS WITH 'ABCDEF'
        RETURN a
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_01")
    }

    /*
    Scenario: Finding beginning of string
    */
    @Test
    def void testStartsWithAcceptance_02() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        WHERE a.name STARTS WITH 'ABC'
        RETURN a
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_02")
    }

    /*
    Scenario: Finding end of string 1
    */
    @Test
    def void testStartsWithAcceptance_03() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        WHERE a.name ENDS WITH 'DEF'
        RETURN a
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_03")
    }

    /*
    Scenario: Finding end of string 2
    */
    @Test
    def void testStartsWithAcceptance_04() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        WHERE a.name ENDS WITH 'AB'
        RETURN a
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_04")
    }

    /*
    Scenario: Finding middle of string
    */
    @Test
    def void testStartsWithAcceptance_05() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        WHERE a.name STARTS WITH 'a'
        AND a.name ENDS WITH 'f'
        RETURN a
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_05")
    }

    /*
    Scenario: Finding the empty string
    */
    @Test
    def void testStartsWithAcceptance_06() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        WHERE a.name STARTS WITH ''
        RETURN a
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_06")
    }

    /*
    Scenario: Finding when the middle is known
    */
    @Test
    def void testStartsWithAcceptance_07() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        WHERE a.name CONTAINS 'CD'
        RETURN a
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_07")
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
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        WHERE a.name STARTS WITH ' '
        RETURN a.name AS name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_08")
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
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        WHERE a.name STARTS WITH '\n'
        RETURN a.name AS name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_09")
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
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        WHERE a.name ENDS WITH '\n'
        RETURN a.name AS name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_10")
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
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        WHERE a.name ENDS WITH ' '
        RETURN a.name AS name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_11")
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
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        WHERE a.name CONTAINS ' '
        RETURN a.name AS name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_12")
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
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        WHERE a.name CONTAINS '\n'
        RETURN a.name AS name
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_13")
    }

    /*
    Scenario: No string starts with null
    */
    @Test
    def void testStartsWithAcceptance_14() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        WHERE a.name STARTS WITH null
        RETURN a
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_14")
    }

    /*
    Scenario: No string does not start with null
    */
    @Test
    def void testStartsWithAcceptance_15() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        WHERE NOT a.name STARTS WITH null
        RETURN a
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_15")
    }

    /*
    Scenario: No string ends with null
    */
    @Test
    def void testStartsWithAcceptance_16() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        WHERE a.name ENDS WITH null
        RETURN a
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_16")
    }

    /*
    Scenario: No string does not end with null
    */
    @Test
    def void testStartsWithAcceptance_17() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        WHERE NOT a.name ENDS WITH null
        RETURN a
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_17")
    }

    /*
    Scenario: No string contains null
    */
    @Test
    def void testStartsWithAcceptance_18() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        WHERE a.name CONTAINS null
        RETURN a
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_18")
    }

    /*
    Scenario: No string does not contain null
    */
    @Test
    def void testStartsWithAcceptance_19() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        WHERE NOT a.name CONTAINS null
        RETURN a
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_19")
    }

    /*
    Scenario: Combining string operators
    */
    @Test
    def void testStartsWithAcceptance_20() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        WHERE a.name STARTS WITH 'A'
        AND a.name CONTAINS 'C'
        AND a.name ENDS WITH 'EF'
        RETURN a
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_20")
    }

    /*
    Scenario: NOT with CONTAINS
    */
    @Test
    def void testStartsWithAcceptance_21() {
        val container = Cypher2Relalg.processString('''
        MATCH (a)
        WHERE NOT a.name CONTAINS 'b'
        RETURN a
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_21")
    }

    /*
    Scenario: Handling non-string operands for STARTS WITH
    */
    @Test
    def void testStartsWithAcceptance_22() {
        val container = Cypher2Relalg.processString('''
        WITH [1, 3.14, true, [], {}, null] AS operands
        UNWIND operands AS op1
        UNWIND operands AS op2
        WITH op1 STARTS WITH op2 AS v
        RETURN v, count(*)
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_22")
    }

    /*
    Scenario: Handling non-string operands for CONTAINS
    */
    @Test
    def void testStartsWithAcceptance_23() {
        val container = Cypher2Relalg.processString('''
        WITH [1, 3.14, true, [], {}, null] AS operands
        UNWIND operands AS op1
        UNWIND operands AS op2
        WITH op1 STARTS WITH op2 AS v
        RETURN v, count(*)
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_23")
    }

    /*
    Scenario: Handling non-string operands for ENDS WITH
    */
    @Test
    def void testStartsWithAcceptance_24() {
        val container = Cypher2Relalg.processString('''
        WITH [1, 3.14, true, [], {}, null] AS operands
        UNWIND operands AS op1
        UNWIND operands AS op2
        WITH op1 STARTS WITH op2 AS v
        RETURN v, count(*)
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/StartsWithAcceptance_24")
    }

}
