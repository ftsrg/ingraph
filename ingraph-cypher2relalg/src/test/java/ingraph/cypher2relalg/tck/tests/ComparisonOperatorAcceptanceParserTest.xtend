package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class ComparisonOperatorAcceptanceParserTest {
		
		/*
		Scenario: Handling numerical ranges 1
		Given an empty graph
		And having executed:
			"""
			UNWIND [1, 2, 3] AS i
			CREATE ({value: i})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testComparisonOperatorAcceptance_01() {
				val cypher = CypherParser.parseString('''
				MATCH (n)
				WHERE 1 < n.value < 3
				RETURN n.value
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ComparisonOperatorAcceptance_01")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling numerical ranges 2
		Given an empty graph
		And having executed:
			"""
			UNWIND [1, 2, 3] AS i
			CREATE ({value: i})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testComparisonOperatorAcceptance_02() {
				val cypher = CypherParser.parseString('''
				MATCH (n)
				WHERE 1 < n.value <= 3
				RETURN n.value
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ComparisonOperatorAcceptance_02")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling numerical ranges 3
		Given an empty graph
		And having executed:
			"""
			UNWIND [1, 2, 3] AS i
			CREATE ({value: i})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testComparisonOperatorAcceptance_03() {
				val cypher = CypherParser.parseString('''
				MATCH (n)
				WHERE 1 <= n.value < 3
				RETURN n.value
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ComparisonOperatorAcceptance_03")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling numerical ranges 4
		Given an empty graph
		And having executed:
			"""
			UNWIND [1, 2, 3] AS i
			CREATE ({value: i})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testComparisonOperatorAcceptance_04() {
				val cypher = CypherParser.parseString('''
				MATCH (n)
				WHERE 1 <= n.value <= 3
				RETURN n.value
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ComparisonOperatorAcceptance_04")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling string ranges 1
		Given an empty graph
		And having executed:
			"""
			UNWIND ['a', 'b', 'c'] AS c
			CREATE ({value: c})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testComparisonOperatorAcceptance_05() {
				val cypher = CypherParser.parseString('''
				MATCH (n)
				WHERE 'a' < n.value < 'c'
				RETURN n.value
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ComparisonOperatorAcceptance_05")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling string ranges 2
		Given an empty graph
		And having executed:
			"""
			UNWIND ['a', 'b', 'c'] AS c
			CREATE ({value: c})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testComparisonOperatorAcceptance_06() {
				val cypher = CypherParser.parseString('''
				MATCH (n)
				WHERE 'a' < n.value <= 'c'
				RETURN n.value
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ComparisonOperatorAcceptance_06")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling string ranges 3
		Given an empty graph
		And having executed:
			"""
			UNWIND ['a', 'b', 'c'] AS c
			CREATE ({value: c})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testComparisonOperatorAcceptance_07() {
				val cypher = CypherParser.parseString('''
				MATCH (n)
				WHERE 'a' <= n.value < 'c'
				RETURN n.value
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ComparisonOperatorAcceptance_07")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling string ranges 4
		Given an empty graph
		And having executed:
			"""
			UNWIND ['a', 'b', 'c'] AS c
			CREATE ({value: c})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testComparisonOperatorAcceptance_08() {
				val cypher = CypherParser.parseString('''
				MATCH (n)
				WHERE 'a' <= n.value <= 'c'
				RETURN n.value
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ComparisonOperatorAcceptance_08")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling empty range
		Given an empty graph
		And having executed:
			"""
			CREATE ({value: 3})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testComparisonOperatorAcceptance_09() {
				val cypher = CypherParser.parseString('''
				MATCH (n)
				WHERE 10 < n.value <= 3
				RETURN n.value
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ComparisonOperatorAcceptance_09")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling long chains of operators
		Given an empty graph
		And having executed:
			"""
			CREATE (a:A {prop1: 3, prop2: 4})
			CREATE (b:B {prop1: 4, prop2: 5})
			CREATE (c:C {prop1: 4, prop2: 4})
			CREATE (a)-[:R]->(b)
			CREATE (b)-[:R]->(c)
			CREATE (c)-[:R]->(a)
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testComparisonOperatorAcceptance_10() {
				val cypher = CypherParser.parseString('''
				MATCH (n)-->(m)
				WHERE n.prop1 < m.prop1 = n.prop2 <> m.prop2
				RETURN labels(m)
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ComparisonOperatorAcceptance_10")
				Cypher2Relalg.processCypher(cypher)
		}

}
