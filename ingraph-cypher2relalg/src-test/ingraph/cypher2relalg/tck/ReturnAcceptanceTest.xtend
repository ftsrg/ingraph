package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class ReturnAcceptanceTest {
    
    @Test
    def void testReturnAcceptance_01() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE a.id = 1337
        RETURN a.version + 5
        ''')
    }
        
    @Test
    def void testReturnAcceptance_02() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n
        LIMIT 2
        ''')
    }
        
    @Test
    def void testReturnAcceptance_03() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n
        ORDER BY n.name ASC
        SKIP 2
        ''')
    }
        
    @Test
    def void testReturnAcceptance_04() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n
        ORDER BY n.name ASC
        SKIP $skipAmount
        ''')
    }
        
    @Test
    def void testReturnAcceptance_05() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n
        ORDER BY n.name ASC
        SKIP 2
        LIMIT 2
        ''')
    }
        
    @Test
    def void testReturnAcceptance_06() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n
        ORDER BY n.name ASC
        SKIP $s
        LIMIT $l
        ''')
    }
        
    @Test
    def void testReturnAcceptance_07() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n.division, max(n.age)
        ORDER BY max(n.age)
        ''')
    }
        
    @Test
    def void testReturnAcceptance_08() {
        RelalgParser.parse('''
        MATCH (a)
        RETURN DISTINCT a
        ORDER BY a.name
        ''')
    }
        
    @Test
    def void testReturnAcceptance_09() {
        RelalgParser.parse('''
        MATCH (a)
        RETURN a AS ColumnName
        ''')
    }
        
    @Test
    def void testReturnAcceptance_10() {
        RelalgParser.parse('''
        MATCH (a)-->(b)
        RETURN DISTINCT b
        ORDER BY b.name
        ''')
    }
        
    @Test
    def void testReturnAcceptance_11() {
        RelalgParser.parse('''
        RETURN 12 / 4 * 3 - 2 * 4
        ''')
    }
        
    @Test
    def void testReturnAcceptance_12() {
        RelalgParser.parse('''
        RETURN 12 / 4 * (3 - 2 * 4)
        ''')
    }
        
    @Test
    def void testReturnAcceptance_13() {
        RelalgParser.parse('''
        MATCH (a)
        RETURN a, count(*)
        ORDER BY count(*)
        ''')
    }
        
    @Test
    def void testReturnAcceptance_14() {
        RelalgParser.parse('''
        RETURN abs(-1)
        ''')
    }
        
    @Test
    def void testReturnAcceptance_15() {
        RelalgParser.parse('''
        RETURN size([1, 2, 3]) AS n
        ''')
    }
        
}
    