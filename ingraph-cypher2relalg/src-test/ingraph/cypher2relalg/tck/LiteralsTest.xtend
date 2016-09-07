package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class LiteralsTest {
    
    @Test
    def void testLiterals_01() {
        RelalgParser.parse('''
        RETURN 1 AS literal
        ''')
    }
        
    @Test
    def void testLiterals_02() {
        RelalgParser.parse('''
        RETURN 1.0 AS literal
        ''')
    }
        
    @Test
    def void testLiterals_03() {
        RelalgParser.parse('''
        RETURN -1e-9 AS literal
        ''')
    }
        
    @Test
    def void testLiterals_04() {
        RelalgParser.parse('''
        RETURN true AS literal
        ''')
    }
        
    @Test
    def void testLiterals_05() {
        RelalgParser.parse('''
        RETURN '' AS literal
        ''')
    }
        
    @Test
    def void testLiterals_06() {
        RelalgParser.parse('''
        RETURN "" AS literal
        ''')
    }
        
    @Test
    def void testLiterals_07() {
        RelalgParser.parse('''
        RETURN null AS literal
        ''')
    }
        
    @Test
    def void testLiterals_08() {
        RelalgParser.parse('''
        RETURN [] AS literal
        ''')
    }
        
    @Test
    def void testLiterals_09() {
        RelalgParser.parse('''
        RETURN [0, 1, 2] AS literal
        ''')
    }
        
    @Test
    def void testLiterals_10() {
        RelalgParser.parse('''
        RETURN {} AS literal
        ''')
    }
        
    @Test
    def void testLiterals_11() {
        RelalgParser.parse('''
        RETURN {k1: 0, k2: 'string'} AS literal
        ''')
    }
        
}
    