package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class PatternComprehensionTest {
    
    @Test
    def void testPatternComprehension_01() {
        RelalgParser.parse('''
        MATCH (liker)
        RETURN [p = (liker)--() | p] AS isNew
        ORDER BY liker.time
        ''')
    }
        
    @Test
    def void testPatternComprehension_02() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN [p = (n)-->() | p] AS ps
        ''')
    }
        
    @Test
    def void testPatternComprehension_03() {
        RelalgParser.parse('''
        MATCH (n:A)
        RETURN [p = (n)-->(:B) | p]
        ''')
    }
        
    @Test
    def void testPatternComprehension_04() {
        RelalgParser.parse('''
        MATCH (a:A), (b:B)
        RETURN [p = (a)-[*]->(b) | p] AS paths
        ''')
    }
        
    @Test
    def void testPatternComprehension_05() {
        RelalgParser.parse('''
        MATCH (n)-->(b)
        WITH [p = (n)-->() | p] AS ps, count(b) AS c
        RETURN ps, c
        ''')
    }
        
    @Test
    def void testPatternComprehension_06() {
        RelalgParser.parse('''
        MATCH (a:A), (b:B)
        WITH [p = (a)-[*]->(b) | p] AS paths, count(a) AS c
        RETURN paths, c
        ''')
    }
        
    @Test
    def void testPatternComprehension_07() {
        RelalgParser.parse('''
        MATCH (n:A)
        RETURN [p = (n)-[:HAS]->() | p] AS ps
        ''')
    }
        
    @Test
    def void testPatternComprehension_08() {
        RelalgParser.parse('''
        MATCH (n:A)
        RETURN count([p = (n)-[:HAS]->() | p]) AS c
        ''')
    }
        
    @Test
    def void testPatternComprehension_09() {
        RelalgParser.parse('''
        MATCH (n:X)
        RETURN n, size([(n)--() | 1]) > 0 AS b
        ''')
    }
        
    @Test
    def void testPatternComprehension_10() {
        RelalgParser.parse('''
        MATCH p = (n:X)-->(b)
        RETURN n, [x IN nodes(p) | size([(x)-->(:Y) | 1])] AS list
        ''')
    }
        
    @Test
    def void testPatternComprehension_11() {
        RelalgParser.parse('''
        MATCH (a:X)
        RETURN size([(a)-->() | 1]) AS length
        ''')
    }
        
    @Test
    def void testPatternComprehension_12() {
        RelalgParser.parse('''
        MATCH (a:X)
        RETURN size([(a)-[:T]->() | 1]) AS length
        ''')
    }
        
    @Test
    def void testPatternComprehension_13() {
        RelalgParser.parse('''
        MATCH (a:X)
        RETURN size([(a)-[:T|OTHER]->() | 1]) AS length
        ''')
    }
        
    @Test
    def void testPatternComprehension_14() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN [(n)-[:T]->(b) | b.prop] AS list
        ''')
    }
        
    @Test
    def void testPatternComprehension_15() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN [(n)-[r:T]->() | r.prop] AS list
        ''')
    }
        
}
    