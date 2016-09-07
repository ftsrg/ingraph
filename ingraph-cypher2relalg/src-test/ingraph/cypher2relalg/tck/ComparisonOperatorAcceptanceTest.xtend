package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class ComparisonOperatorAcceptanceTest {
    
    @Test
    def void testComparisonOperatorAcceptance_01() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE 1 < n.value < 3
        RETURN n.value
        ''')
    }
        
    @Test
    def void testComparisonOperatorAcceptance_02() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE 1 < n.value <= 3
        RETURN n.value
        ''')
    }
        
    @Test
    def void testComparisonOperatorAcceptance_03() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE 1 <= n.value < 3
        RETURN n.value
        ''')
    }
        
    @Test
    def void testComparisonOperatorAcceptance_04() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE 1 <= n.value <= 3
        RETURN n.value
        ''')
    }
        
    @Test
    def void testComparisonOperatorAcceptance_05() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE 'a' < n.value < 'c'
        RETURN n.value
        ''')
    }
        
    @Test
    def void testComparisonOperatorAcceptance_06() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE 'a' < n.value <= 'c'
        RETURN n.value
        ''')
    }
        
    @Test
    def void testComparisonOperatorAcceptance_07() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE 'a' <= n.value < 'c'
        RETURN n.value
        ''')
    }
        
    @Test
    def void testComparisonOperatorAcceptance_08() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE 'a' <= n.value <= 'c'
        RETURN n.value
        ''')
    }
        
    @Test
    def void testComparisonOperatorAcceptance_09() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE 10 < n.value <= 3
        RETURN n.value
        ''')
    }
        
    @Test
    def void testComparisonOperatorAcceptance_10() {
        RelalgParser.parse('''
        MATCH (n)-->(m)
        WHERE n.prop1 < m.prop1 = n.prop2 <> m.prop2
        RETURN labels(m)
        ''')
    }
        
}
    