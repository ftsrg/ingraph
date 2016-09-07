package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class FunctionsAcceptanceTest {
    
    @Test
    def void testFunctionsAcceptance_01() {
        RelalgParser.parse('''
        MATCH (a)
        RETURN coalesce(a.title, a.name)
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_02() {
        RelalgParser.parse('''
        WITH null AS a
        OPTIONAL MATCH p = (a)-[r]->()
        RETURN length(nodes(p)), type(r), nodes(p), relationships(p)
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_03() {
        RelalgParser.parse('''
        UNWIND split('one1two', '1') AS item
        RETURN count(item) AS item
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_04() {
        RelalgParser.parse('''
        MATCH (p:Person)
        RETURN properties(p) AS m
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_05() {
        RelalgParser.parse('''
        MATCH ()-[r:R]->()
        RETURN properties(r) AS m
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_06() {
        RelalgParser.parse('''
        RETURN properties({name: 'Popeye', level: 9001}) AS m
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_07() {
        RelalgParser.parse('''
        RETURN properties(null)
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_08() {
        RelalgParser.parse('''
        RETURN reverse('raksO')
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_09() {
        RelalgParser.parse('''
        MATCH (n:Person)
        WHERE exists(n['prop'])
        RETURN n
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_10() {
        RelalgParser.parse('''
        WITH <map> AS map
        RETURN exists(map.name) AS exists
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_11() {
        RelalgParser.parse('''
        WITH <map> AS map
        RETURN map.name IS NOT NULL
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_12() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN percentileDisc(n.prop, $percentile) AS p
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_13() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN percentileCont(n.prop, $percentile) AS p
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_14() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN percentileCont(n.prop, $param)
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_15() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN percentileDisc(n.prop, $param)
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_16() {
        RelalgParser.parse('''
        MATCH (n:S)
        WITH n, size([(n)-->() | 1]) AS deg
        WHERE deg > 2
        WITH deg
        LIMIT 100
        RETURN percentileDisc(0.90, deg), deg
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_17() {
        RelalgParser.parse('''
        MATCH ()-[r]->()
        RETURN type(r)
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_18() {
        RelalgParser.parse('''
        MATCH ()-[r1]->()-[r2]->()
        RETURN type(r1), type(r2)
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_19() {
        RelalgParser.parse('''
        MATCH (a)
        OPTIONAL MATCH (a)-[r:NOT_THERE]->()
        RETURN type(r)
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_20() {
        RelalgParser.parse('''
        MATCH (a)
        OPTIONAL MATCH (a)-[r:T]->()
        RETURN type(r)
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_21() {
        RelalgParser.parse('''
        MATCH (a)-[r]->()
        WITH [r, 1] AS list
        RETURN type(list[0])
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_22() {
        RelalgParser.parse('''
        MATCH p = (n)-[r:T]->()
        RETURN [x IN [r, <invalid>] | type(x) ] AS list
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_23() {
        RelalgParser.parse('''
        MATCH (a)
        WITH [a, 1] AS list
        RETURN labels(list[0]) AS l
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_24() {
        RelalgParser.parse('''
        MATCH (a)
        WITH [a, 1] AS list
        RETURN labels(list[1]) AS l
        ''')
    }
        
    @Test
    def void testFunctionsAcceptance_25() {
        RelalgParser.parse('''
        MATCH (n:X)
        RETURN n, EXIsTS(n.prop) AS b
        ''')
    }
        
}
    