package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.inferencers.BasicSchemaInferencer
import ingraph.relalg2tex.relalgconverters.Relalg2TexTreeConverter

class ExpressionAcceptanceVisualizationTest {

    extension Relalg2TexTreeConverter converter = new Relalg2TexTreeConverter
    extension BasicSchemaInferencer inferencer = new BasicSchemaInferencer
    
    /*
    Scenario: IN should work with nested list subscripting
    */
    @Test
    def void testExpressionAcceptance_01() {
        val container = Cypher2Relalg.processString('''
        WITH [[1, 2, 3]] AS list
        RETURN 3 IN list[0] AS r
        ''')
        container.inferBasicSchema
        container.convert("tck/ExpressionAcceptance_01")
    }

    /*
    Scenario: IN should work with nested literal list subscripting
    */
    @Test
    def void testExpressionAcceptance_02() {
        val container = Cypher2Relalg.processString('''
        RETURN 3 IN [[1, 2, 3]][0] AS r
        ''')
        container.inferBasicSchema
        container.convert("tck/ExpressionAcceptance_02")
    }

    /*
    Scenario: IN should work with list slices
    */
    @Test
    def void testExpressionAcceptance_03() {
        val container = Cypher2Relalg.processString('''
        WITH [1, 2, 3] AS list
        RETURN 3 IN list[0..1] AS r
        ''')
        container.inferBasicSchema
        container.convert("tck/ExpressionAcceptance_03")
    }

    /*
    Scenario: IN should work with literal list slices
    */
    @Test
    def void testExpressionAcceptance_04() {
        val container = Cypher2Relalg.processString('''
        RETURN 3 IN [1, 2, 3][0..1] AS r
        ''')
        container.inferBasicSchema
        container.convert("tck/ExpressionAcceptance_04")
    }

    /*
    Scenario: Execute n[0]
    */
    @Test
    def void testExpressionAcceptance_05() {
        val container = Cypher2Relalg.processString('''
        RETURN [1, 2, 3][0] AS value
        ''')
        container.inferBasicSchema
        container.convert("tck/ExpressionAcceptance_05")
    }

    /*
    Scenario: Execute n['name'] in read queries
    And having executed:
      """
      CREATE ({name: 'Apa'})
      """
    */
    @Test
    def void testExpressionAcceptance_06() {
        val container = Cypher2Relalg.processString('''
        MATCH (n {name: 'Apa'})
        RETURN n['nam' + 'e'] AS value
        ''')
        container.inferBasicSchema
        container.convert("tck/ExpressionAcceptance_06")
    }

    /*
    Scenario: Use dynamic property lookup based on parameters when there is no type information
    And parameters are:
      | expr | {name: 'Apa'} |
      | idx  | 'name'        |
    */
    @Test
    def void testExpressionAcceptance_08() {
        val container = Cypher2Relalg.processString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx] AS value
        ''')
        container.inferBasicSchema
        container.convert("tck/ExpressionAcceptance_08")
    }

    /*
    Scenario: Use dynamic property lookup based on parameters when there is rhs type information
    And parameters are:
      | expr | {name: 'Apa'} |
      | idx  | 'name'        |
    */
    @Test
    def void testExpressionAcceptance_10() {
        val container = Cypher2Relalg.processString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[toString(idx)] AS value
        ''')
        container.inferBasicSchema
        container.convert("tck/ExpressionAcceptance_10")
    }

    /*
    Scenario: Use collection lookup based on parameters when there is no type information
    And parameters are:
      | expr | ['Apa'] |
      | idx  | 0       |
    */
    @Test
    def void testExpressionAcceptance_11() {
        val container = Cypher2Relalg.processString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx] AS value
        ''')
        container.inferBasicSchema
        container.convert("tck/ExpressionAcceptance_11")
    }

    /*
    Scenario: Use collection lookup based on parameters when there is lhs type information
    And parameters are:
      | idx | 0 |
    */
    @Test
    def void testExpressionAcceptance_12() {
        val container = Cypher2Relalg.processString('''
        WITH ['Apa'] AS expr
        RETURN expr[$idx] AS value
        ''')
        container.inferBasicSchema
        container.convert("tck/ExpressionAcceptance_12")
    }

    /*
    Scenario: Use collection lookup based on parameters when there is rhs type information
    And parameters are:
      | expr | ['Apa'] |
      | idx  | 0       |
    */
    @Test
    def void testExpressionAcceptance_13() {
        val container = Cypher2Relalg.processString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[toInteger(idx)] AS value
        ''')
        container.inferBasicSchema
        container.convert("tck/ExpressionAcceptance_13")
    }

    /*
    Scenario: Fail at runtime when attempting to index with an Int into a Map
    And parameters are:
      | expr | {name: 'Apa'} |
      | idx  | 0             |
    */
    @Test
    def void testExpressionAcceptance_14() {
        val container = Cypher2Relalg.processString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
        container.inferBasicSchema
        container.convert("tck/ExpressionAcceptance_14")
    }

    /*
    Scenario: Fail at runtime when trying to index into a map with a non-string
    And parameters are:
      | expr | {name: 'Apa'} |
      | idx  | 12.3          |
    */
    @Test
    def void testExpressionAcceptance_15() {
        val container = Cypher2Relalg.processString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
        container.inferBasicSchema
        container.convert("tck/ExpressionAcceptance_15")
    }

    /*
    Scenario: Fail at runtime when attempting to index with a String into a Collection
    And parameters are:
      | expr | ['Apa'] |
      | idx  | 'name'  |
    */
    @Test
    def void testExpressionAcceptance_16() {
        val container = Cypher2Relalg.processString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
        container.inferBasicSchema
        container.convert("tck/ExpressionAcceptance_16")
    }

    /*
    Scenario: Fail at runtime when trying to index into a list with a list
    And parameters are:
      | expr | ['Apa'] |
      | idx  | ['Apa'] |
    */
    @Test
    def void testExpressionAcceptance_17() {
        val container = Cypher2Relalg.processString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
        container.inferBasicSchema
        container.convert("tck/ExpressionAcceptance_17")
    }

    /*
    Scenario: Fail at runtime when trying to index something which is not a map or collection
    And parameters are:
      | expr | 100 |
      | idx  | 0   |
    */
    @Test
    def void testExpressionAcceptance_18() {
        val container = Cypher2Relalg.processString('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
        container.inferBasicSchema
        container.convert("tck/ExpressionAcceptance_18")
    }

}
