package ingraph.cypher2relalg.tck

import org.junit.Test

import ingraph.cypher2relalg.RelalgParser

class MatchAcceptance2Test {
    
    @Test
    def void testMatchAcceptance2_01() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_02() {
        RelalgParser.parse('''
        MATCH ()-[r]->()
        RETURN r
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_03() {
        RelalgParser.parse('''
        MATCH (:Root {name: 'x'})-->(i:TextNode)
        WHERE i.id > 'te'
        RETURN i
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_04() {
        RelalgParser.parse('''
        MATCH (:Root {name: 'x'})-->(i)
        WHERE exists(i.id) OR i.id > 'te'
        RETURN i
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_05() {
        RelalgParser.parse('''
        MATCH (:Root {name: 'x'})-->(i:Child)
        WHERE i.id > 'te'
        RETURN i
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_06() {
        RelalgParser.parse('''
        MATCH (:Root {name: 'x'})-->(i:Child)
        WHERE NOT exists(i.id) OR i.id > 'te'
        RETURN i
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_07() {
        RelalgParser.parse('''
        MATCH p = ()-[*]->()
        WITH count(*) AS count, p AS p
        WITH nodes(p) AS nodes
        RETURN *
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_08() {
        RelalgParser.parse('''
        MATCH (a {name: 'A'})-[:CONTAINS*0..1]->(b)-[:FRIEND*0..1]->(c)
        RETURN a, b, c
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_09() {
        RelalgParser.parse('''
        MATCH (a {name: 'A'})-[*]->(x)
        RETURN x
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_10() {
        RelalgParser.parse('''
        MATCH p = ({name: 'A'})-[:KNOWS*..2]->()
        RETURN p
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_11() {
        RelalgParser.parse('''
        MATCH p = ({name: 'A'})-[:KNOWS*..]->()
        RETURN p
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_12() {
        RelalgParser.parse('''
        MATCH (a {name: 'A'}), (c {name: 'C'})
        MATCH (a)-->(b)
        RETURN a, b, c
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_13() {
        RelalgParser.parse('''
        MATCH (a {name: 'A'}), (b {name: 'B'})
        MATCH (a)-->(x)<-->(b)
        RETURN x
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_14() {
        RelalgParser.parse('''
        MATCH (a {name: 'A'}), (b {name: 'B'}), (c {name: 'C'})
        MATCH (a)-->(x), (b)-->(x), (c)-->(x)
        RETURN x
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_15() {
        RelalgParser.parse('''
        MATCH (a {name: 'a'}), (b {name: 'b'}), (c {name: 'c'})
        MATCH (a)-->(x), (b)-->(x), (c)-->(x)
        RETURN x
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_16() {
        RelalgParser.parse('''
        MATCH (a {name: 'A'})
        OPTIONAL MATCH (a)-[:KNOWS]->()-[:KNOWS]->(foo)
        RETURN foo
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_17() {
        RelalgParser.parse('''
        MATCH (a {name: 'A'}), (x)
        WHERE x.name IN ['B', 'C']
        OPTIONAL MATCH p = (a)-->(x)
        RETURN x, p
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_18() {
        RelalgParser.parse('''
        MATCH (a {name: 'A'})
        OPTIONAL MATCH p = (a)-->(b)-[*]->(c)
        RETURN p
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_19() {
        RelalgParser.parse('''
        MATCH (a {name: 'A'}), (x)
        WHERE x.name IN ['B', 'C']
        OPTIONAL MATCH p = (a)-[r*]->(x)
        RETURN r, x, p
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_20() {
        RelalgParser.parse('''
        MATCH (a:A)
        MATCH (a)-[r*2]->()
        RETURN r
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_21() {
        RelalgParser.parse('''
        MATCH (a:A), (other:B)
        OPTIONAL MATCH (a)-[r]->(other)
        WITH other WHERE r IS NULL
        RETURN other
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_22() {
        RelalgParser.parse('''
        MATCH (n)-->(x0)
        OPTIONAL MATCH (x0)-->(x1)
        WHERE x1.foo = 'bar'
        RETURN x0.name
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_23() {
        RelalgParser.parse('''
        MATCH (a)-->(b)
        WHERE b:B
        OPTIONAL MATCH (a)-->(c)
        WHERE c:C
        RETURN a.name
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_24() {
        RelalgParser.parse('''
        MATCH (a)-[:ADMIN]-(b)
        WHERE a:A
        RETURN a.id, b.id
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_25() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_26() {
        RelalgParser.parse('''
        MATCH (a), (b)
        WHERE a <> b
        RETURN a, b
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_27() {
        RelalgParser.parse('''
        MATCH (a)-->(b), (b)-->(b)
        RETURN b
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_28() {
        RelalgParser.parse('''
        MATCH (a:A), (b:B)
        OPTIONAL MATCH (a)-[r*]-(b)
        WHERE r IS NULL
        AND a <> b
        RETURN b
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_29() {
        RelalgParser.parse('''
        MATCH (a)-[:T|:T]->(b)
        RETURN b
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_30() {
        RelalgParser.parse('''
        MATCH (a:A)-->(n)-->(m)
        RETURN n.x, count(*)
        ORDER BY n.x
        LIMIT 1000
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_31() {
        RelalgParser.parse('''
        MATCH (n)
        WHERE n.foo = 'bar'
        RETURN n
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_32() {
        RelalgParser.parse('''
        MATCH p = (b)<--(a)
        RETURN p
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_33() {
        RelalgParser.parse('''
        OPTIONAL MATCH (n)
        RETURN n
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_34() {
        RelalgParser.parse('''
        MATCH (n)
        OPTIONAL MATCH (n)-[:NOT_EXIST]->(x)
        RETURN n, x
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_35() {
        RelalgParser.parse('''
        MATCH (n)
        OPTIONAL MATCH (n)-[:NOT_EXIST]->(x)
        RETURN n, collect(x)
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_36() {
        RelalgParser.parse('''
        MATCH (a)<--()<--(b)-->()-->(c)
        WHERE a:A
        RETURN c
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_37() {
        RelalgParser.parse('''
        MATCH (a)-->(b:Foo)
        RETURN b
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_38() {
        RelalgParser.parse('''
        MATCH (:A)-[r]->(:B)
        RETURN r
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_39() {
        RelalgParser.parse('''
        MATCH (a:A:B:C)
        RETURN a
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_40() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN (n:Foo)
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_41() {
        RelalgParser.parse('''
        MATCH (advertiser)-[:ADV_HAS_PRODUCT]->(out)-[:AP_HAS_VALUE]->(red)<-[:AA_HAS_VALUE]-(a)
        WHERE advertiser.id = $1
        AND a.id = $2
        AND red.name = 'red'
        AND out.name = 'product1'
        RETURN out.name
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_42() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN (n:Foo)
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_43() {
        RelalgParser.parse('''
        MATCH (n:Person)-->()
        WHERE n.name = 'Bob'
        RETURN n
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_44() {
        RelalgParser.parse('''
        MATCH (a)-->(b)
        MATCH (c)-->(d)
        RETURN a, b, c, d
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_45() {
        RelalgParser.parse('''
        MATCH (a)--(b)--(c)--(d)--(a), (b)--(d)
        WHERE a.id = 1
        AND c.id = 2
        RETURN d
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_46() {
        RelalgParser.parse('''
        MATCH (a)-[:A]->()-[:B]->(a)
        RETURN a.name
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_47() {
        RelalgParser.parse('''
        MATCH (a)-[:A]->(b), (b)-[:B]->(a)
        RETURN a.name
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_48() {
        RelalgParser.parse('''
        MATCH (a)-[r*1..1]->(b)
        RETURN r
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_49() {
        RelalgParser.parse('''
        OPTIONAL MATCH (a)
        WITH a
        MATCH (a)-->(b)
        RETURN b
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_50() {
        RelalgParser.parse('''
        OPTIONAL MATCH (a:Label)
        WITH a
        MATCH (a)-->(b)
        RETURN b
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_51() {
        RelalgParser.parse('''
        OPTIONAL MATCH (a)
        WITH a
        OPTIONAL MATCH (a)-->(b)
        RETURN b
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_52() {
        RelalgParser.parse('''
        OPTIONAL MATCH (a)
        RETURN a
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_53() {
        RelalgParser.parse('''
        MATCH p = (a)
        RETURN p
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_54() {
        RelalgParser.parse('''
        MATCH p = ()-[*0..]->()
        RETURN p
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_55() {
        RelalgParser.parse('''
        MATCH (n)
        RETURN n.prop AS n, count(n) AS count
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_56() {
        RelalgParser.parse('''
        MATCH ()-[r1]->()
        WITH r1 AS r2
        MATCH ()-[r2]->()
        RETURN r2 AS rel
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_57() {
        RelalgParser.parse('''
        MATCH ()-[r1]->()
        WITH r1 AS r2, count(*) AS c
        ORDER BY c
        MATCH ()-[r2]->()
        RETURN r2 AS rel
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_58() {
        RelalgParser.parse('''
        MATCH (a)-[r]->(b)
        WITH a, r, b, count(*) AS c
        ORDER BY c
        MATCH (a)-[r]->(b)
        RETURN r AS rel
        ORDER BY rel.id
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_59() {
        RelalgParser.parse('''
        MATCH ()-[r]->()
        WITH r
        LIMIT 1
        OPTIONAL MATCH (a2)-[r]->(b2)
        RETURN a2, r, b2
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_60() {
        RelalgParser.parse('''
        MATCH (a1)-[r]->()
        WITH r, a1
        LIMIT 1
        OPTIONAL MATCH (a1)-[r]->(b2)
        RETURN a1, r, b2
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_61() {
        RelalgParser.parse('''
        MATCH (a1)-[r]->()
        WITH r, a1
        LIMIT 1
        MATCH (a1:X)-[r]->(b2)
        RETURN a1, r, b2
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_62() {
        RelalgParser.parse('''
        MATCH (a1:X:Y)-[r]->()
        WITH r, a1
        LIMIT 1
        MATCH (a1:Y)-[r]->(b2)
        RETURN a1, r, b2
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_63() {
        RelalgParser.parse('''
        MATCH (a1)-[r:T]->()
        WITH r, a1
        LIMIT 1
        MATCH (a1)-[r:Y]->(b2)
        RETURN a1, r, b2
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_64() {
        RelalgParser.parse('''
        MATCH (a1)-[r:T]->() WITH r, a1
        LIMIT 1
        MATCH (a1)-[r:T]->(b2)
        RETURN a1, r, b2
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_65() {
        RelalgParser.parse('''
        MATCH ()-[r1]->()-[r2]->()
        WITH [r1, r2] AS rs
        LIMIT 1
        MATCH (first)-[rs*]->(second)
        RETURN first, second
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_66() {
        RelalgParser.parse('''
        MATCH (a)-[r1]->()-[r2]->(b)
        WITH [r1, r2] AS rs, a AS first, b AS second
        LIMIT 1
        MATCH (first)-[rs*]->(second)
        RETURN first, second
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_67() {
        RelalgParser.parse('''
        MATCH (a)-[r1]->()-[r2]->(b)
        WITH [r1, r2] AS rs, a AS second, b AS first
        LIMIT 1
        MATCH (first)-[rs*]->(second)
        RETURN first, second
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_68() {
        RelalgParser.parse('''
        MATCH (a1)-[r]->()
        WITH r, a1
        LIMIT 1
        OPTIONAL MATCH (a1)<-[r]-(b2)
        RETURN a1, r, b2
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_69() {
        RelalgParser.parse('''
        MATCH (a1)-[r]->()
        WITH r, a1
        LIMIT 1
        OPTIONAL MATCH (a2)<-[r]-(b2)
        WHERE a1 = a2
        RETURN a1, r, b2, a2
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_70() {
        RelalgParser.parse('''
        MATCH (n)
        WITH n.prop AS n2
        RETURN n2.prop
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_71() {
        RelalgParser.parse('''
        MATCH (foo)
        RETURN foo.bar AS x
        ORDER BY x DESC
        LIMIT 4
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_72() {
        RelalgParser.parse('''
        MATCH (a)
        RETURN count(a) > 0
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_73() {
        RelalgParser.parse('''
        MATCH (a:Artist)-[:WORKED_WITH* {year: 1988}]->(b:Artist)
        RETURN *
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_74() {
        RelalgParser.parse('''
        MATCH (a), (b)
        WHERE a.id = 0
        AND (a)-[:T]->(b:Label)
        OR (a)-[:T*]->(b:MissingLabel)
        RETURN DISTINCT b
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_75() {
        RelalgParser.parse('''
        MATCH (a:Blue)-[r*]->(b:Green)
        RETURN count(r)
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_76() {
        RelalgParser.parse('''
        MATCH p = (n:Movie)--(m)
        RETURN p
        LIMIT 1
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_77() {
        RelalgParser.parse('''
        MATCH p = (a)
        WITH p
        RETURN p
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_78() {
        RelalgParser.parse('''
        MATCH p = (n)-->(m)--(o)
        RETURN p
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_79() {
        RelalgParser.parse('''
        MATCH path = (n)-->(m)--(o)--(p)
        RETURN path
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_80() {
        RelalgParser.parse('''
        MATCH topRoute = (:Start)<-[:CONNECTED_TO]-()-[:CONNECTED_TO*3..3]-(:End)
        RETURN topRoute
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_81() {
        RelalgParser.parse('''
        MATCH (a)
        RETURN a.prop
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_82() {
        RelalgParser.parse('''
        MATCH ()-[r]->()
        RETURN r.prop
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_83() {
        RelalgParser.parse('''
        MATCH (a)-[r]->()
        RETURN a AS foo, r AS bar
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_84() {
        RelalgParser.parse('''
        MATCH (a)
        RETURN a.bar
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_85() {
        RelalgParser.parse('''
        MATCH ()-[r]->()
        RETURN r.bar
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_86() {
        RelalgParser.parse('''
        MATCH (a)
        RETURN a.name, a.age, a.seasons
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_87() {
        RelalgParser.parse('''
        MATCH (a)
        RETURN a.prop + 1 AS foo
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_88() {
        RelalgParser.parse('''
        MATCH (a)
        RETURN a.prop2 + a.prop1 AS foo
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_89() {
        RelalgParser.parse('''
        MATCH ()-[r*0..1]-()
        RETURN last(r) AS l
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_90() {
        RelalgParser.parse('''
        MATCH (a:A)
        OPTIONAL MATCH (a)-[:FOO]->(b:B)
        OPTIONAL MATCH (b)<-[:BAR*]-(c:B)
        RETURN a, b, c
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_91() {
        RelalgParser.parse('''
        MATCH (n)-[r]->(m)
        RETURN [n, r, m] AS r
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_92() {
        RelalgParser.parse('''
        MATCH (n)-[r]->(m)
        RETURN {node1: n, rel: r, node2: m} AS m
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_93() {
        RelalgParser.parse('''
        MATCH p = ({prop: 'a'})-->({prop: 'b'})
        RETURN p
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_94() {
        RelalgParser.parse('''
        MATCH p = ({prop: 'a'})<--({prop: 'b'})
        RETURN p
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_95() {
        RelalgParser.parse('''
        MATCH p = (n)-->(k)<--(n)
        RETURN p
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_96() {
        RelalgParser.parse('''
        MATCH p = (n)<-->(k)<--(n)
        RETURN p
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_97() {
        RelalgParser.parse('''
        MATCH p=(n)<-->(k)<-->(n)
        RETURN p
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_98() {
        RelalgParser.parse('''
        MATCH (n:A:B:C:D:E:F:G:H:I:J:K:L:M)-[:T]->(m:Z:Y:X:W:V:U)
        RETURN n, m
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_99() {
        RelalgParser.parse('''
        MATCH (n {prop: 'start'})-[:T*]->(m {prop: 'end'})
        RETURN m
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_100() {
        RelalgParser.parse('''
        MATCH (a)
        MERGE (b)
        WITH *
        OPTIONAL MATCH (a)--(b)
        RETURN count(*)
        ''')
    }
        
    @Test
    def void testMatchAcceptance2_101() {
        RelalgParser.parse('''
        MATCH ()-[r]-()
        RETURN type(r) AS r
        ''')
    }
        
}
    