package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class EqualsAcceptanceTest {
    
    @Test
    def void testEqualsAcceptance_01() {
        RelalgParser.parse('''
        WITH collect([0, 0.0]) AS numbers
        UNWIND numbers AS arr
        WITH arr[0] AS expected
        MATCH (n) WHERE toInteger(n.id) = expected
        RETURN n
        ''')
    }
        
    @Test
    def void testEqualsAcceptance_02() {
        RelalgParser.parse('''
        WITH collect([0.5, 0]) AS numbers
        UNWIND numbers AS arr
        WITH arr[0] AS expected
        MATCH (n) WHERE toInteger(n.id) = expected
        RETURN n
        ''')
    }
        
    @Test
    def void testEqualsAcceptance_03() {
        RelalgParser.parse('''
        WITH collect(['0', 0]) AS things
        UNWIND things AS arr
        WITH arr[0] AS expected
        MATCH (n) WHERE toInteger(n.id) = expected
        RETURN n
        ''')
    }
        
    @Test
    def void testEqualsAcceptance_04() {
        RelalgParser.parse('''
        MATCH (a)
        WITH a
        MATCH (b)
        WHERE a = b
        RETURN count(b)
        ''')
    }
        
    @Test
    def void testEqualsAcceptance_05() {
        RelalgParser.parse('''
        MATCH ()-[a]->()
        WITH a
        MATCH ()-[b]->()
        WHERE a = b
        RETURN count(b)
        ''')
    }
        
}
    