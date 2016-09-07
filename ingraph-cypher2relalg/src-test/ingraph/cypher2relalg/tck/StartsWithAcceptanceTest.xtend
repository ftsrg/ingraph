package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class StartsWithAcceptanceTest {
    
    @Test
    def void testStartsWithAcceptance_01() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE a.name STARTS WITH 'ABCDEF'
        RETURN a
        ''')
    }
        
    @Test
    def void testStartsWithAcceptance_02() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE a.name STARTS WITH 'ABC'
        RETURN a
        ''')
    }
        
    @Test
    def void testStartsWithAcceptance_03() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE a.name ENDS WITH 'DEF'
        RETURN a
        ''')
    }
        
    @Test
    def void testStartsWithAcceptance_04() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE a.name ENDS WITH 'AB'
        RETURN a
        ''')
    }
        
    @Test
    def void testStartsWithAcceptance_05() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE a.name STARTS WITH 'a'
        AND a.name ENDS WITH 'f'
        RETURN a
        ''')
    }
        
    @Test
    def void testStartsWithAcceptance_06() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE a.name STARTS WITH ''
        RETURN a
        ''')
    }
        
    @Test
    def void testStartsWithAcceptance_07() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE a.name CONTAINS 'CD'
        RETURN a
        ''')
    }
        
    @Test
    def void testStartsWithAcceptance_08() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE a.name STARTS WITH ' '
        RETURN a.name AS name
        ''')
    }
        
    @Test
    def void testStartsWithAcceptance_09() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE a.name STARTS WITH '\n'
        RETURN a.name AS name
        ''')
    }
        
    @Test
    def void testStartsWithAcceptance_10() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE a.name ENDS WITH '\n'
        RETURN a.name AS name
        ''')
    }
        
    @Test
    def void testStartsWithAcceptance_11() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE a.name ENDS WITH ' '
        RETURN a.name AS name
        ''')
    }
        
    @Test
    def void testStartsWithAcceptance_12() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE a.name CONTAINS ' '
        RETURN a.name AS name
        ''')
    }
        
    @Test
    def void testStartsWithAcceptance_13() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE a.name CONTAINS '\n'
        RETURN a.name AS name
        ''')
    }
        
    @Test
    def void testStartsWithAcceptance_14() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE a.name STARTS WITH null
        RETURN a
        ''')
    }
        
    @Test
    def void testStartsWithAcceptance_15() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE NOT a.name STARTS WITH null
        RETURN a
        ''')
    }
        
    @Test
    def void testStartsWithAcceptance_16() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE a.name ENDS WITH null
        RETURN a
        ''')
    }
        
    @Test
    def void testStartsWithAcceptance_17() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE NOT a.name ENDS WITH null
        RETURN a
        ''')
    }
        
    @Test
    def void testStartsWithAcceptance_18() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE a.name CONTAINS null
        RETURN a
        ''')
    }
        
    @Test
    def void testStartsWithAcceptance_19() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE NOT a.name CONTAINS null
        RETURN a
        ''')
    }
        
    @Test
    def void testStartsWithAcceptance_20() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE a.name STARTS WITH 'A'
        AND a.name CONTAINS 'C'
        AND a.name ENDS WITH 'EF'
        RETURN a
        ''')
    }
        
    @Test
    def void testStartsWithAcceptance_21() {
        RelalgParser.parse('''
        MATCH (a)
        WHERE NOT a.name CONTAINS 'b'
        RETURN a
        ''')
    }
        
}
    