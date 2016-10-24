package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.util.SchemaInferencer
import ingraph.relalg2tex.RelalgTreeSerializer

class ExpressionAcceptanceVisualizationTest {

    val RelalgTreeSerializer serializer = new RelalgTreeSerializer
    extension SchemaInferencer inferencer = new SchemaInferencer
    
    /*
    Scenario: Execute n[0]
    */
    @Test
    def void testExpressionAcceptance_01() {
        val container = Cypher2Relalg.processString('''
        RETURN [1, 2, 3][0] AS value
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/ExpressionAcceptance_01")
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
        val container = Cypher2Relalg.processString('''
        MATCH (n {name: 'Apa'})
        RETURN n['nam' + 'e'] AS value
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/ExpressionAcceptance_02")
    }

    /*
    Scenario: Use dynamic property lookup based on parameters when there is no type information
    And parameters are:
      | expr | {name: 'Apa'} |
      | idx  | 'name'        |
    */
    @Test
    def void testExpressionAcceptance_04() {
        val container = Cypher2Relalg.processString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx] AS value
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/ExpressionAcceptance_04")
    }

    /*
    Scenario: Use dynamic property lookup based on parameters when there is rhs type information
    And parameters are:
      | expr | {name: 'Apa'} |
      | idx  | 'name'        |
    */
    @Test
    def void testExpressionAcceptance_06() {
        val container = Cypher2Relalg.processString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[toString(idx)] AS value
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/ExpressionAcceptance_06")
    }

    /*
    Scenario: Use collection lookup based on parameters when there is no type information
    And parameters are:
      | expr | ['Apa'] |
      | idx  | 0       |
    */
    @Test
    def void testExpressionAcceptance_07() {
        val container = Cypher2Relalg.processString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx] AS value
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/ExpressionAcceptance_07")
    }

    /*
    Scenario: Use collection lookup based on parameters when there is lhs type information
    And parameters are:
      | idx | 0 |
    */
    @Test
    def void testExpressionAcceptance_08() {
        val container = Cypher2Relalg.processString('''
        WITH ['Apa'] AS expr
        RETURN expr[$idx] AS value
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/ExpressionAcceptance_08")
    }

    /*
    Scenario: Use collection lookup based on parameters when there is rhs type information
    And parameters are:
      | expr | ['Apa'] |
      | idx  | 0       |
    */
    @Test
    def void testExpressionAcceptance_09() {
        val container = Cypher2Relalg.processString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[toInteger(idx)] AS value
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/ExpressionAcceptance_09")
    }

    /*
    Scenario: Fail at runtime when attempting to index with an Int into a Map
    And parameters are:
      | expr | {name: 'Apa'} |
      | idx  | 0             |
    */
    @Test
    def void testExpressionAcceptance_10() {
        val container = Cypher2Relalg.processString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/ExpressionAcceptance_10")
    }

    /*
    Scenario: Fail at runtime when trying to index into a map with a non-string
    And parameters are:
      | expr | {name: 'Apa'} |
      | idx  | 12.3          |
    */
    @Test
    def void testExpressionAcceptance_11() {
        val container = Cypher2Relalg.processString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/ExpressionAcceptance_11")
    }

    /*
    Scenario: Fail at runtime when attempting to index with a String into a Collection
    And parameters are:
      | expr | ['Apa'] |
      | idx  | 'name'  |
    */
    @Test
    def void testExpressionAcceptance_12() {
        val container = Cypher2Relalg.processString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/ExpressionAcceptance_12")
    }

    /*
    Scenario: Fail at runtime when trying to index into a list with a list
    And parameters are:
      | expr | ['Apa'] |
      | idx  | ['Apa'] |
    */
    @Test
    def void testExpressionAcceptance_13() {
        val container = Cypher2Relalg.processString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/ExpressionAcceptance_13")
    }

    /*
    Scenario: Fail at runtime when trying to index something which is not a map or collection
    And parameters are:
      | expr | 100 |
      | idx  | 0   |
    */
    @Test
    def void testExpressionAcceptance_14() {
        val container = Cypher2Relalg.processString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
        container.addSchemaInformation
        serializer.serialize(container, "tck/ExpressionAcceptance_14")
    }

}
