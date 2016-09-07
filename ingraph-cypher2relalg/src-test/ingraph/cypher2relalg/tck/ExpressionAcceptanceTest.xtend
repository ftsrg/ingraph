package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class ExpressionAcceptanceTest {
    
    @Test
    def void testExpressionAcceptance_01() {
        RelalgParser.parse('''
        RETURN [1, 2, 3][0] AS value
        ''')
    }
        
    @Test
    def void testExpressionAcceptance_02() {
        RelalgParser.parse('''
        MATCH (n {name: 'Apa'})
        RETURN n['nam' + 'e'] AS value
        ''')
    }
        
    @Test
    def void testExpressionAcceptance_04() {
        RelalgParser.parse('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx] AS value
        ''')
    }
        
    @Test
    def void testExpressionAcceptance_06() {
        RelalgParser.parse('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[toString(idx)] AS value
        ''')
    }
        
    @Test
    def void testExpressionAcceptance_07() {
        RelalgParser.parse('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx] AS value
        ''')
    }
        
    @Test
    def void testExpressionAcceptance_08() {
        RelalgParser.parse('''
        WITH ['Apa'] AS expr
        RETURN expr[$idx] AS value
        ''')
    }
        
    @Test
    def void testExpressionAcceptance_09() {
        RelalgParser.parse('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[toInteger(idx)] AS value
        ''')
    }
        
    @Test
    def void testExpressionAcceptance_10() {
        RelalgParser.parse('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
    }
        
    @Test
    def void testExpressionAcceptance_11() {
        RelalgParser.parse('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
    }
        
    @Test
    def void testExpressionAcceptance_12() {
        RelalgParser.parse('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
    }
        
    @Test
    def void testExpressionAcceptance_13() {
        RelalgParser.parse('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
    }
        
    @Test
    def void testExpressionAcceptance_14() {
        RelalgParser.parse('''
        WITH $expr AS expr, $idx AS idx
        RETURN expr[idx]
        ''')
    }
        
}
    