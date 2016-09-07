package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class VarLengthAcceptance2Test {
    
    @Test
    def void testVarLengthAcceptance2_01() {
        RelalgParser.parse('''
        MATCH ()-[r:EDGE]-()
        MATCH p = (n)-[*0..1]-()-[r]-()-[*0..1]-(m)
        RETURN count(p) AS c
        ''')
    }
        
}
    