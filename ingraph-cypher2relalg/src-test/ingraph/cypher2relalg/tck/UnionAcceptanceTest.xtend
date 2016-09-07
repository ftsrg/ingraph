package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class UnionAcceptanceTest {
    
    @Test
    def void testUnionAcceptance_01() {
        RelalgParser.parse('''
        MATCH (a:A)
        RETURN a AS a
        UNION
        MATCH (b:B)
        RETURN b AS a
        ''')
    }
        
    @Test
    def void testUnionAcceptance_02() {
        RelalgParser.parse('''
        RETURN 1 AS x
        UNION ALL
        RETURN 2 AS x
        ''')
    }
        
    @Test
    def void testUnionAcceptance_03() {
        RelalgParser.parse('''
        RETURN 1 AS x
        UNION
        RETURN 2 AS x
        ''')
    }
        
    @Test
    def void testUnionAcceptance_04() {
        RelalgParser.parse('''
        RETURN 2 AS x
        UNION
        RETURN 1 AS x
        UNION
        RETURN 2 AS x
        ''')
    }
        
    @Test
    def void testUnionAcceptance_05() {
        RelalgParser.parse('''
        RETURN 2 AS x
        UNION ALL
        RETURN 1 AS x
        UNION ALL
        RETURN 2 AS x
        ''')
    }
        
}
    