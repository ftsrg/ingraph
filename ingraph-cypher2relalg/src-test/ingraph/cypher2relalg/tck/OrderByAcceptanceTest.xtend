package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class OrderByAcceptanceTest {
    
    @Test
    def void testOrderByAcceptance_01() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n.prop AS prop
        ORDER BY n.prop
        ''')
    }
        
    @Test
    def void testOrderByAcceptance_02() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n.prop AS prop
        ORDER BY n.prop DESC
        ''')
    }
        
    @Test
    def void testOrderByAcceptance_03() {
        RelalgParser.parse('''
        WITH [0, 1] AS prows, [[2], [3, 4]] AS qrows
        UNWIND prows AS p
        UNWIND qrows[p] AS q
        WITH p, count(q) AS rng
        RETURN p
        ORDER BY rng
        ''')
    }
        
    @Test
    def void testOrderByAcceptance_04() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n.prop AS n
        ORDER BY n + 2
        ''')
    }
        
    @Test
    def void testOrderByAcceptance_05() {
        RelalgParser.parse('''
        MATCH (c:Crew {name: 'Neo'})
        WITH c, 0 AS relevance
        RETURN c.rank AS rank
        ORDER BY relevance, c.rank
        ''')
    }
        
    @Test
    def void testOrderByAcceptance_06() {
        RelalgParser.parse('''
        UNWIND [true, false] AS bools
        RETURN bools
        ORDER BY bools
        ''')
    }
        
    @Test
    def void testOrderByAcceptance_07() {
        RelalgParser.parse('''
        UNWIND [true, false] AS bools
        RETURN bools
        ORDER BY bools DESC
        ''')
    }
        
    @Test
    def void testOrderByAcceptance_08() {
        RelalgParser.parse('''
        UNWIND ['.*', '', ' ', 'one'] AS strings
        RETURN strings
        ORDER BY strings
        ''')
    }
        
    @Test
    def void testOrderByAcceptance_09() {
        RelalgParser.parse('''
        UNWIND ['.*', '', ' ', 'one'] AS strings
        RETURN strings
        ORDER BY strings DESC
        ''')
    }
        
    @Test
    def void testOrderByAcceptance_10() {
        RelalgParser.parse('''
        UNWIND [1, 3, 2] AS ints
        RETURN ints
        ORDER BY ints
        ''')
    }
        
    @Test
    def void testOrderByAcceptance_11() {
        RelalgParser.parse('''
        UNWIND [1, 3, 2] AS ints
        RETURN ints
        ORDER BY ints DESC
        ''')
    }
        
    @Test
    def void testOrderByAcceptance_12() {
        RelalgParser.parse('''
        UNWIND [1.5, 1.3, 999.99] AS floats
        RETURN floats
        ORDER BY floats
        ''')
    }
        
    @Test
    def void testOrderByAcceptance_13() {
        RelalgParser.parse('''
        UNWIND [1.5, 1.3, 999.99] AS floats
        RETURN floats
        ORDER BY floats DESC
        ''')
    }
        
    @Test
    def void testOrderByAcceptance_14() {
        RelalgParser.parse('''
        MATCH (p:Person)
        RETURN p.name AS name
        ORDER BY p.name
        LIMIT 1
        ''')
    }
        
    @Test
    def void testOrderByAcceptance_15() {
        RelalgParser.parse('''
        MATCH (p:Person)
        RETURN p.name AS name
        ORDER BY p.name
        LIMIT 0
        ''')
    }
        
    @Test
    def void testOrderByAcceptance_16() {
        RelalgParser.parse('''
        MATCH (p:Person)
        RETURN p.name AS name
        ORDER BY p.name
        LIMIT $limit
        ''')
    }
        
    @Test
    def void testOrderByAcceptance_17() {
        RelalgParser.parse('''
        MATCH (p:Person)
        RETURN p.name AS name
        ORDER BY p.name
        LIMIT -1
        ''')
    }
        
}
    