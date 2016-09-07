package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class TernaryLogicAcceptanceTest {
    
    @Test
    def void testTernaryLogicAcceptance_01() {
        RelalgParser.parse('''
        RETURN NOT null AS value
        ''')
    }
        
    @Test
    def void testTernaryLogicAcceptance_02() {
        RelalgParser.parse('''
        RETURN null IS NULL AS value
        ''')
    }
        
    @Test
    def void testTernaryLogicAcceptance_03() {
        RelalgParser.parse('''
        RETURN null IS NOT NULL AS value
        ''')
    }
        
    @Test
    def void testTernaryLogicAcceptance_04() {
        RelalgParser.parse('''
        RETURN null = null AS value
        ''')
    }
        
    @Test
    def void testTernaryLogicAcceptance_05() {
        RelalgParser.parse('''
        RETURN null <> null AS value
        ''')
    }
        
    @Test
    def void testTernaryLogicAcceptance_06() {
        RelalgParser.parse('''
        RETURN $lhs AND $rhs AS result
        ''')
    }
        
    @Test
    def void testTernaryLogicAcceptance_07() {
        RelalgParser.parse('''
        RETURN $lhs OR $rhs AS result
        ''')
    }
        
    @Test
    def void testTernaryLogicAcceptance_08() {
        RelalgParser.parse('''
        RETURN $lhs XOR $rhs AS result
        ''')
    }
        
    @Test
    def void testTernaryLogicAcceptance_09() {
        RelalgParser.parse('''
        RETURN $elt IN $coll AS result
        ''')
    }
        
}
    