package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class ReturnAcceptance2Test {
    
    @Test
    def void testReturnAcceptance2_01() {
        RelalgParser.parse('''
        MATCH (n)
        DELETE n
        RETURN n.p
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_02() {
        RelalgParser.parse('''
        MATCH (n)
        DELETE n
        RETURN labels(n)
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_03() {
        RelalgParser.parse('''
        MATCH ()-[r]->()
        DELETE r
        RETURN r.p
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_04() {
        RelalgParser.parse('''
        MATCH ()-[r]->()
        DELETE r
        RETURN type(r)
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_05() {
        RelalgParser.parse('''
        RETURN '\u01FF' AS a
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_06() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n
        LIMIT 0
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_07() {
        RelalgParser.parse('''
        MATCH (a)
        RETURN DISTINCT a.name
        ORDER BY a.age
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_08() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n
        ORDER BY n
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_09() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n.name, count(*) AS foo
        ORDER BY n.name
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_10() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN DISTINCT n.name
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_11() {
        RelalgParser.parse('''
        MATCH p = (a:Start)-->(b)
        RETURN *
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_12() {
        RelalgParser.parse('''
        MATCH (n)
        SET n.x = [1, 2, 3]
        RETURN size(n.x)
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_13() {
        RelalgParser.parse('''
        MATCH (n)
        SET n.x = [1, 2, 3]
        RETURN size(n.x)
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_14() {
        RelalgParser.parse('''
        RETURN sqrt(12.96)
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_15() {
        RelalgParser.parse('''
        MATCH (me)-[r1:ATE]->()<-[r2:ATE]-(you)
        WHERE me.name = 'Michael'
        WITH me, count(DISTINCT r1) AS H1, count(DISTINCT r2) AS H2, you
        MATCH (me)-[r1:ATE]->()<-[r2:ATE]-(you)
        RETURN me, you, sum((1 - abs(r1.times / H1 - r2.times / H2)) * (r1.times + r2.times) / (H1 + H2)) AS sum
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_16() {
        RelalgParser.parse('''
        MATCH ()-->()
        WITH 1 AS x
        MATCH ()-[r1]->()<--()
        RETURN sum(r1.times)
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_17() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_18() {
        RelalgParser.parse('''
        RETURN {a: 1, b: 'foo'}
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_19() {
        RelalgParser.parse('''
        MATCH (a)
        RETURN exists(a.id), a IS NOT NULL
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_20() {
        RelalgParser.parse('''
        RETURN size([[], []] + [[]]) AS l
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_21() {
        RelalgParser.parse('''
        MATCH (n)
        SET n.array = [1, 2, 3, 4, 5]
        RETURN tail(tail(n.array))
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_22() {
        RelalgParser.parse('''
        MATCH (a)
        RETURN a.count
        ORDER BY a.count
        SKIP 10
        LIMIT 10
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_23() {
        RelalgParser.parse('''
        RETURN substring('0123456789', 1) AS s
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_24() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN *
        ORDER BY n.id
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_25() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN DISTINCT n.id AS id
        ORDER BY id DESC
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_26() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN DISTINCT n
        ORDER BY n.id
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_27() {
        RelalgParser.parse('''
        RETURN 1 + (2 - (3 * (4 / (5 ^ (6 % null))))) AS a
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_28() {
        RelalgParser.parse('''
        RETURN [[1]][0][0]
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_29() {
        RelalgParser.parse('''
        MATCH (a)
        RETURN a.id AS a, a.id
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_30() {
        RelalgParser.parse('''
        MATCH (a)
        RETURN a, count(a) + 3
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_32() {
        RelalgParser.parse('''
        MATCH (a)
        WITH a.a AS a, count(*) AS count
        RETURN count
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_33() {
        RelalgParser.parse('''
        MATCH (person:Person)<--(message)<-[like]-(:Person)
        WITH like.creationDate AS likeTime, person AS person
        ORDER BY likeTime, message.id
        WITH head(collect({likeTime: likeTime})) AS latestLike, person AS person
        RETURN latestLike.likeTime AS likeTime
        ORDER BY likeTime
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_34() {
        RelalgParser.parse('''
        RETURN [1, 10, 100] + [4, 5] AS foo
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_35() {
        RelalgParser.parse('''
        RETURN [false, true] + false AS foo
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_36() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN count(DISTINCT {foo: n.list}) AS count
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_37() {
        RelalgParser.parse('''
        MATCH (n)
        WITH DISTINCT {foo: n.list} AS map
        RETURN count(*)
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_38() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN count(DISTINCT {foo: [[n.list, n.list], [n.list, n.list]]}) AS count
        ''')
    }
        
    @Test
    def void testReturnAcceptance2_39() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN count(DISTINCT {foo: [{bar: n.list}, {baz: {apa: n.list}}]}) AS count
        ''')
    }
        
}
    