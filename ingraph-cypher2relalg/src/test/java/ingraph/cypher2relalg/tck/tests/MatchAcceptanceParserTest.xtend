package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class MatchAcceptanceParserTest {
		
		/*
		Scenario: Path query should return results in written order
		Given an empty graph
		And having executed:
			"""
			CREATE (:Label1)<-[:TYPE]-(:Label2)
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testMatchAcceptance_01() {
				val cypher = CypherParser.parseString('''
				MATCH p = (a:Label1)<--(:Label2)
				RETURN p
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_01")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Longer path query should return results in written order
		Given an empty graph
		And having executed:
			"""
			CREATE (:Label1)<-[:T1]-(:Label2)-[:T2]->(:Label3)
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testMatchAcceptance_02() {
				val cypher = CypherParser.parseString('''
				MATCH p = (a:Label1)<--(:Label2)--()
				RETURN p
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_02")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Use multiple MATCH clauses to do a Cartesian product
		Given an empty graph
		And having executed:
			"""
			CREATE ({value: 1}),
				({value: 2}),
				({value: 3})
			"""
		*/
		@Test
		@Category(RegressionTests)
		def void testMatchAcceptance_03() {
				val cypher = CypherParser.parseString('''
				MATCH (n), (m)
				RETURN n.value AS n, m.value AS m
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_03")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Use params in pattern matching predicates
		Given an empty graph
		And having executed:
			"""
			CREATE (:A)-[:T {foo: 'bar'}]->(:B {name: 'me'})
			"""
		And parameters are:
			| param | 'bar' |
		*/
		@Test
		@Category(RegressionTests)
		def void testMatchAcceptance_04() {
				val cypher = CypherParser.parseString('''
				MATCH (a)-[r]->(b)
				WHERE r.foo = $param
				RETURN b
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_04")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Filter out based on node prop name
		Given an empty graph
		And having executed:
			"""
			CREATE ({name: 'Someone'})<-[:X]-()-[:X]->({name: 'Andres'})
			"""
		*/
		@Test
		@Category(RegressionTests)
		def void testMatchAcceptance_05() {
				val cypher = CypherParser.parseString('''
				MATCH ()-[rel:X]-(a)
				WHERE a.name = 'Andres'
				RETURN a
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_05")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Honour the column name for RETURN items
		Given an empty graph
		And having executed:
			"""
			CREATE ({name: 'Someone'})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testMatchAcceptance_06() {
				val cypher = CypherParser.parseString('''
				MATCH (a)
				WITH a.name AS a
				RETURN a
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_06")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Filter based on rel prop name
		Given an empty graph
		And having executed:
			"""
			CREATE (:A)<-[:KNOWS {name: 'monkey'}]-()-[:KNOWS {name: 'woot'}]->(:B)
			"""
		*/
		@Test
		@Category(RegressionTests)
		def void testMatchAcceptance_07() {
				val cypher = CypherParser.parseString('''
				MATCH (node)-[r:KNOWS]->(a)
				WHERE r.name = 'monkey'
				RETURN a
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_07")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Cope with shadowed variables
		Given an empty graph
		And having executed:
			"""
			CREATE ({value: 1, name: 'King Kong'}),
				({value: 2, name: 'Ann Darrow'})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testMatchAcceptance_08() {
				val cypher = CypherParser.parseString('''
				MATCH (n)
				WITH n.name AS n
				RETURN n
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_08")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Get neighbours
		Given an empty graph
		And having executed:
			"""
			CREATE (a:A {value: 1})-[:KNOWS]->(b:B {value: 2})
			"""
		*/
		@Test
		@Category(RegressionTests)
		def void testMatchAcceptance_09() {
				val cypher = CypherParser.parseString('''
				MATCH (n1)-[rel:KNOWS]->(n2)
				RETURN n1, n2
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_09")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Get two related nodes
		Given an empty graph
		And having executed:
			"""
			CREATE (a:A {value: 1}),
				(a)-[:KNOWS]->(b:B {value: 2}),
				(a)-[:KNOWS]->(c:C {value: 3})
			"""
		*/
		@Test
		@Category(RegressionTests)
		def void testMatchAcceptance_10() {
				val cypher = CypherParser.parseString('''
				MATCH ()-[rel:KNOWS]->(x)
				RETURN x
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_10")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Get related to related to
		Given an empty graph
		And having executed:
			"""
			CREATE (a:A {value: 1})-[:KNOWS]->(b:B {value: 2})-[:FRIEND]->(c:C {value: 3})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testMatchAcceptance_11() {
				val cypher = CypherParser.parseString('''
				MATCH (n)-->(a)-->(b)
				RETURN b
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_11")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handle comparison between node properties
		Given an empty graph
		And having executed:
			"""
			CREATE (a:A {animal: 'monkey'}),
				(b:B {animal: 'cow'}),
				(c:C {animal: 'monkey'}),
				(d:D {animal: 'cow'}),
				(a)-[:KNOWS]->(b),
				(a)-[:KNOWS]->(c),
				(d)-[:KNOWS]->(b),
				(d)-[:KNOWS]->(c)
			"""
		*/
		@Test
		@Category(RegressionTests)
		def void testMatchAcceptance_12() {
				val cypher = CypherParser.parseString('''
				MATCH (n)-[rel]->(x)
				WHERE n.animal = x.animal
				RETURN n, x
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_12")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Return two subgraphs with bound undirected relationship
		Given an empty graph
		And having executed:
			"""
			CREATE (a:A {value: 1})-[:REL {name: 'r'}]->(b:B {value: 2})
			"""
		*/
		@Test
		@Category(RegressionTests)
		def void testMatchAcceptance_13() {
				val cypher = CypherParser.parseString('''
				MATCH (a)-[r {name: 'r'}]-(b)
				RETURN a, b
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_13")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Return two subgraphs with bound undirected relationship and optional relationship
		Given an empty graph
		And having executed:
			"""
			CREATE (a:A {value: 1})-[:REL {name: 'r1'}]->(b:B {value: 2})-[:REL {name: 'r2'}]->(c:C {value: 3})
			"""
		*/
		@Test
		@Category(RegressionTests)
		def void testMatchAcceptance_14() {
				val cypher = CypherParser.parseString('''
				MATCH (a)-[r {name: 'r1'}]-(b)
				OPTIONAL MATCH (b)-[r2]-(c)
				WHERE r <> r2
				RETURN a, b, c
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_14")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Rel type function works as expected
		Given an empty graph
		And having executed:
			"""
			CREATE (a:A {name: 'A'}),
				(b:B {name: 'B'}),
				(c:C {name: 'C'}),
				(a)-[:KNOWS]->(b),
				(a)-[:HATES]->(c)
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testMatchAcceptance_15() {
				val cypher = CypherParser.parseString('''
				MATCH (n {name: 'A'})-[r]->(x)
				WHERE type(r) = 'KNOWS'
				RETURN x
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_15")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Walk alternative relationships
		Given an empty graph
		And having executed:
			"""
			CREATE (a {name: 'A'}),
				(b {name: 'B'}),
				(c {name: 'C'}),
				(a)-[:KNOWS]->(b),
				(a)-[:HATES]->(c),
				(a)-[:WONDERS]->(c)
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testMatchAcceptance_16() {
				val cypher = CypherParser.parseString('''
				MATCH (n)-[r]->(x)
				WHERE type(r) = 'KNOWS' OR type(r) = 'HATES'
				RETURN r
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_16")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handle OR in the WHERE clause
		Given an empty graph
		And having executed:
			"""
			CREATE (a:A {p1: 12}),
				(b:B {p2: 13}),
				(c:C)
			"""
		*/
		@Test
		@Category(RegressionTests)
		def void testMatchAcceptance_17() {
				val cypher = CypherParser.parseString('''
				MATCH (n)
				WHERE n.p1 = 12 OR n.p2 = 13
				RETURN n
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_17")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Return a simple path
		Given an empty graph
		And having executed:
			"""
			CREATE (a:A {name: 'A'})-[:KNOWS]->(b:B {name: 'B'})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testMatchAcceptance_18() {
				val cypher = CypherParser.parseString('''
				MATCH p = (a {name: 'A'})-->(b)
				RETURN p
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_18")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Return a three node path
		Given an empty graph
		And having executed:
			"""
			CREATE (a:A {name: 'A'})-[:KNOWS]->(b:B {name: 'B'})-[:KNOWS]->(c:C {name: 'C'})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testMatchAcceptance_19() {
				val cypher = CypherParser.parseString('''
				MATCH p = (a {name: 'A'})-[rel1]->(b)-[rel2]->(c)
				RETURN p
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_19")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Do not return anything because path length does not match
		Given an empty graph
		And having executed:
			"""
			CREATE (a:A {name: 'A'})-[:KNOWS]->(b:B {name: 'B'})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testMatchAcceptance_20() {
				val cypher = CypherParser.parseString('''
				MATCH p = (n)-->(x)
				WHERE length(p) = 10
				RETURN x
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_20")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Pass the path length test
		Given an empty graph
		And having executed:
			"""
			CREATE (a:A {name: 'A'})-[:KNOWS]->(b:B {name: 'B'})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testMatchAcceptance_21() {
				val cypher = CypherParser.parseString('''
				MATCH p = (n)-->(x)
				WHERE length(p) = 1
				RETURN x
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_21")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Return relationships by fetching them from the path - starting from the end
		Given an empty graph
		And having executed:
			"""
			CREATE (a:A)-[:REL {value: 1}]->(b:B)-[:REL {value: 2}]->(e:End)
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testMatchAcceptance_22() {
				val cypher = CypherParser.parseString('''
				MATCH p = (a)-[:REL*2..2]->(b:End)
				RETURN relationships(p)
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_22")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Return relationships by fetching them from the path
		Given an empty graph
		And having executed:
			"""
			CREATE (s:Start)-[:REL {value: 1}]->(b:B)-[:REL {value: 2}]->(c:C)
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testMatchAcceptance_23() {
				val cypher = CypherParser.parseString('''
				MATCH p = (a:Start)-[:REL*2..2]->(b)
				RETURN relationships(p)
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_23")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Return relationships by collecting them as a list - wrong way
		Given an empty graph
		And having executed:
			"""
			CREATE (a:A)-[:REL {value: 1}]->(b:B)-[:REL {value: 2}]->(e:End)
			"""
		*/
		@Test
		@Category(RegressionTests)
		def void testMatchAcceptance_24() {
				val cypher = CypherParser.parseString('''
				MATCH (a)-[r:REL*2..2]->(b:End)
				RETURN r
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_24")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Return relationships by collecting them as a list - undirected
		Given an empty graph
		And having executed:
			"""
			CREATE (a:End {value: 1})-[:REL {value: 1}]->(b:B)-[:REL {value: 2}]->(c:End {value: 2})
			"""
		*/
		@Test
		@Category(RegressionTests)
		def void testMatchAcceptance_25() {
				val cypher = CypherParser.parseString('''
				MATCH (a)-[r:REL*2..2]-(b:End)
				RETURN r
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_25")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Return relationships by collecting them as a list
		Given an empty graph
		And having executed:
			"""
			CREATE (s:Start)-[:REL {value: 1}]->(b:B)-[:REL {value: 2}]->(c:C)
			"""
		*/
		@Test
		@Category(RegressionTests)
		def void testMatchAcceptance_26() {
				val cypher = CypherParser.parseString('''
				MATCH (a:Start)-[r:REL*2..2]-(b)
				RETURN r
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_26")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Return a var length path
		Given an empty graph
		And having executed:
			"""
			CREATE (a:A {name: 'A'})-[:KNOWS {value: 1}]->(b:B {name: 'B'})-[:KNOWS {value: 2}]->(c:C {name: 'C'})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testMatchAcceptance_27() {
				val cypher = CypherParser.parseString('''
				MATCH p = (n {name: 'A'})-[:KNOWS*1..2]->(x)
				RETURN p
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_27")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Return a var length path of length zero
		Given an empty graph
		And having executed:
			"""
			CREATE (a:A)-[:REL]->(b:B)
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testMatchAcceptance_28() {
				val cypher = CypherParser.parseString('''
				MATCH p = (a)-[*0..1]->(b)
				RETURN a, b, length(p) AS l
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_28")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Return a named var length path of length zero
		Given an empty graph
		And having executed:
			"""
			CREATE (a:A {name: 'A'})-[:KNOWS]->(b:B {name: 'B'})-[:FRIEND]->(c:C {name: 'C'})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testMatchAcceptance_29() {
				val cypher = CypherParser.parseString('''
				MATCH p = (a {name: 'A'})-[:KNOWS*0..1]->(b)-[:FRIEND*0..1]->(c)
				RETURN p
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_29")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Accept skip zero
		Given any graph
		*/
		@Test
		@Category(FailingTests)
		def void testMatchAcceptance_30() {
				val cypher = CypherParser.parseString('''
				MATCH (n)
				WHERE 1 = 0
				RETURN n SKIP 0
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/MatchAcceptance_30")
				Cypher2Relalg.processCypher(cypher)
		}

}
