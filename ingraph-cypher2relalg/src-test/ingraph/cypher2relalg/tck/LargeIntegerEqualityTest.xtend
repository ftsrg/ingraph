package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class LargeIntegerEqualityTest {
    
    @Test
    def void testLargeIntegerEquality_01() {
        RelalgParser.parse('''
        MATCH (p:Label)
        RETURN p.id
        ''')
    }
        
    @Test
    def void testLargeIntegerEquality_02() {
        RelalgParser.parse('''
        MATCH (p:Label {id: 4611686018427387905})
        RETURN p.id
        ''')
    }
        
    @Test
    def void testLargeIntegerEquality_03() {
        RelalgParser.parse('''
        MATCH (p:Label)
        WHERE p.id = 4611686018427387905
        RETURN p.id
        ''')
    }
        
    @Test
    def void testLargeIntegerEquality_04() {
        RelalgParser.parse('''
        MATCH (p:Label {id : 4611686018427387900})
        RETURN p.id
        ''')
    }
        
    @Test
    def void testLargeIntegerEquality_05() {
        RelalgParser.parse('''
        MATCH (p:Label)
        WHERE p.id = 4611686018427387900
        RETURN p.id
        ''')
    }
        
}
    