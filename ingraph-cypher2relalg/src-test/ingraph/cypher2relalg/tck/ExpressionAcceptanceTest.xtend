package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class ExpressionAcceptanceTest {
    
    /*
    Scenario: Execute n[0]
    */
    @Test
    def void testExpressionAcceptance_01() {
        RelalgParser.parse('''
        RETURN [1, 2, 3][0] AS value
        ''')
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
        RelalgParser.parse('''
        MATCH (n {name: 'Apa'})
        RETURN n['nam' + 'e'] AS value
        ''')
    }
        
    /*
    Scenario: Use dynamic property lookup based on parameters when there is no type information
    And parameters are:
      | expr | {name: 'Apa'} |
      | idx  | 'name'        |
    */
    @Test
    def void testExpressionAcceptance_04() {
        RelalgParser.parse('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx] AS value
        ''')
    }
        
    /*
    Scenario: Use dynamic property lookup based on parameters when there is rhs type information
    And parameters are:
      | expr | {name: 'Apa'} |
      | idx  | 'name'        |
    */
    @Test
    def void testExpressionAcceptance_06() {
        RelalgParser.parse('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[toString(idx)] AS value
        ''')
    }
        
    /*
    Scenario: Use collection lookup based on parameters when there is no type information
    And parameters are:
      | expr | ['Apa'] |
      | idx  | 0       |
    */
    @Test
    def void testExpressionAcceptance_07() {
        RelalgParser.parse('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx] AS value
        ''')
    }
        
    /*
    Scenario: Use collection lookup based on parameters when there is lhs type information
    And parameters are:
      | idx | 0 |
    */
    @Test
    def void testExpressionAcceptance_08() {
        RelalgParser.parse('''
        WITH ['Apa'] AS expr
        RETURN expr[$idx] AS value
        ''')
    }
        
    /*
    Scenario: Use collection lookup based on parameters when there is rhs type information
    And parameters are:
      | expr | ['Apa'] |
      | idx  | 0       |
    */
    @Test
    def void testExpressionAcceptance_09() {
        RelalgParser.parse('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[toInteger(idx)] AS value
        ''')
    }
        
    /*
    Scenario: Fail at runtime when attempting to index with an Int into a Map
    And parameters are:
      | expr | {name: 'Apa'} |
      | idx  | 0             |
    */
    @Test
    def void testExpressionAcceptance_10() {
        RelalgParser.parse('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
    }
        
    /*
    Scenario: Fail at runtime when trying to index into a map with a non-string
    And parameters are:
      | expr | {name: 'Apa'} |
      | idx  | 12.3          |
    */
    @Test
    def void testExpressionAcceptance_11() {
        RelalgParser.parse('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
    }
        
    /*
    Scenario: Fail at runtime when attempting to index with a String into a Collection
    And parameters are:
      | expr | ['Apa'] |
      | idx  | 'name'  |
    */
    @Test
    def void testExpressionAcceptance_12() {
        RelalgParser.parse('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
    }
        
    /*
    Scenario: Fail at runtime when trying to index into a list with a list
    And parameters are:
      | expr | ['Apa'] |
      | idx  | ['Apa'] |
    */
    @Test
    def void testExpressionAcceptance_13() {
        RelalgParser.parse('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
    }
        
    /*
    Scenario: Fail at runtime when trying to index something which is not a map or collection
    And parameters are:
      | expr | 100 |
      | idx  | 0   |
    */
    @Test
    def void testExpressionAcceptance_14() {
        RelalgParser.parse('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
    }
        
}
    