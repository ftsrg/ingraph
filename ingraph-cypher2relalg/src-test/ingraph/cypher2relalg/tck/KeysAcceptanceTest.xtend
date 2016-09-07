package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class KeysAcceptanceTest {
    
    @Test
    def void testKeysAcceptance_01() {
        RelalgParser.parse('''
        MATCH (n)
        UNWIND keys(n) AS x
        RETURN DISTINCT x AS theProps
        ''')
    }
        
    @Test
    def void testKeysAcceptance_02() {
        RelalgParser.parse('''
        MATCH (n)
        UNWIND keys(n) AS x
        RETURN DISTINCT x AS theProps
        ''')
    }
        
    @Test
    def void testKeysAcceptance_03() {
        RelalgParser.parse('''
        MATCH (n)
        UNWIND keys(n) AS x
        RETURN DISTINCT x AS theProps
        ''')
    }
        
    @Test
    def void testKeysAcceptance_04() {
        RelalgParser.parse('''
        OPTIONAL MATCH (n)
        UNWIND keys(n) AS x
        RETURN DISTINCT x AS theProps
        ''')
    }
        
    @Test
    def void testKeysAcceptance_05() {
        RelalgParser.parse('''
        MATCH ()-[r:KNOWS]-()
        UNWIND keys(r) AS x
        RETURN DISTINCT x AS theProps
        ''')
    }
        
    @Test
    def void testKeysAcceptance_06() {
        RelalgParser.parse('''
        MATCH ()-[r:KNOWS]-()
        UNWIND keys(r) AS x
        RETURN DISTINCT x AS theProps
        ''')
    }
        
    @Test
    def void testKeysAcceptance_07() {
        RelalgParser.parse('''
        OPTIONAL MATCH ()-[r:KNOWS]-()
        UNWIND keys(r) AS x
        RETURN DISTINCT x AS theProps
        ''')
    }
        
    @Test
    def void testKeysAcceptance_08() {
        RelalgParser.parse('''
        RETURN keys({name: 'Alice', age: 38, address: {city: 'London', residential: true}}) AS k
        ''')
    }
        
    @Test
    def void testKeysAcceptance_09() {
        RelalgParser.parse('''
        RETURN keys($param) AS k
        ''')
    }
        
}
    