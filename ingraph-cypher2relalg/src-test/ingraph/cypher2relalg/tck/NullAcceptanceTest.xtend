package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class NullAcceptanceTest {
    
    @Test
    def void testNullAcceptance_01() {
        RelalgParser.parse('''
        OPTIONAL MATCH (a:DoesNotExist)
        SET a.prop = 42
        RETURN a
        ''')
    }
        
    @Test
    def void testNullAcceptance_02() {
        RelalgParser.parse('''
        OPTIONAL MATCH (a:DoesNotExist)
        REMOVE a.prop
        RETURN a
        ''')
    }
        
    @Test
    def void testNullAcceptance_03() {
        RelalgParser.parse('''
        OPTIONAL MATCH (a:DoesNotExist)
        SET a += {prop: 42}
        RETURN a
        ''')
    }
        
    @Test
    def void testNullAcceptance_04() {
        RelalgParser.parse('''
        OPTIONAL MATCH (a:DoesNotExist)
        SET a = {prop: 42}
        RETURN a
        ''')
    }
        
    @Test
    def void testNullAcceptance_05() {
        RelalgParser.parse('''
        OPTIONAL MATCH (a:DoesNotExist)
        SET a:L
        RETURN a
        ''')
    }
        
    @Test
    def void testNullAcceptance_06() {
        RelalgParser.parse('''
        OPTIONAL MATCH (a:DoesNotExist)
        REMOVE a:L
        RETURN a
        ''')
    }
        
    @Test
    def void testNullAcceptance_07() {
        RelalgParser.parse('''
        OPTIONAL MATCH (a:DoesNotExist)
        DELETE a
        RETURN a
        ''')
    }
        
    @Test
    def void testNullAcceptance_08() {
        RelalgParser.parse('''
        OPTIONAL MATCH ()-[r:DoesNotExist]-()
        DELETE r
        RETURN r
        ''')
    }
        
}
    