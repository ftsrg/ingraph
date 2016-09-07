package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class ListComprehensionTest {
    
    @Test
    def void testListComprehension_01() {
        RelalgParser.parse('''
        MATCH p = (n)-->()
        RETURN [x IN collect(p) | head(nodes(x))] AS p
        ''')
    }
        
    @Test
    def void testListComprehension_02() {
        RelalgParser.parse('''
        MATCH p = (n:A)-->()
        WITH [x IN collect(p) | head(nodes(x))] AS p, count(n) AS c
        RETURN p, c
        ''')
    }
        
    @Test
    def void testListComprehension_03() {
        RelalgParser.parse('''
        MATCH (n)-->(b)
        WHERE n.prop IN [x IN labels(b) | lower(x)]
        RETURN b
        ''')
    }
        
}
    