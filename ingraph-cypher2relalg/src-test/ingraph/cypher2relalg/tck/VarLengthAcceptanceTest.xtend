package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class VarLengthAcceptanceTest {
    
    @Test
    def void testVarLengthAcceptance_01() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_02() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*..]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_03() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES..]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_04() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_05() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_06() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_07() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0..2]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_08() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1..2]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_09() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0..0]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_10() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1..1]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_11() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2..2]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_12() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*-2]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_13() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2..1]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_14() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1..0]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_15() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*..0]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_16() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*..1]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_17() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*..2]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_18() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0..]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_19() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1..]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_20() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2..]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_21() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*0]->()-[:LIKES]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_22() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()-[:LIKES*0]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_23() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*1]->()-[:LIKES]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_24() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()-[:LIKES*1]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_25() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES*2]->()-[:LIKES]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_26() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()-[:LIKES*2]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_27() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()-[:LIKES*3]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_28() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)<-[:LIKES]-()-[:LIKES*3]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_29() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[:LIKES]->()<-[:LIKES*3]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_30() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (p)-[:LIKES*1]->()-[:LIKES]->()-[r:LIKES*2]->(c)
        RETURN c.name
        ''')
    }
        
    @Test
    def void testVarLengthAcceptance_31() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (p)-[:LIKES]->()-[:LIKES*2]->()-[r:LIKES]->(c)
        RETURN c.name
        ''')
    }
        
}
    