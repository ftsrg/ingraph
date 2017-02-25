package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class ListComprehensionParserTest {
		
		/*
		Scenario: Returning a list comprehension
		Given an empty graph
		And having executed:
			"""
			CREATE (a:A)
			CREATE (a)-[:T]->(:B),
						 (a)-[:T]->(:C)
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testListComprehension_01() {
				val cypher = CypherParser.parseString('''
				MATCH p = (n)-->()
				RETURN [x IN collect(p) | head(nodes(x))] AS p
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ListComprehension_01")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Using a list comprehension in a WITH
		Given an empty graph
		And having executed:
			"""
			CREATE (a:A)
			CREATE (a)-[:T]->(:B),
						 (a)-[:T]->(:C)
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testListComprehension_02() {
				val cypher = CypherParser.parseString('''
				MATCH p = (n:A)-->()
				WITH [x IN collect(p) | head(nodes(x))] AS p, count(n) AS c
				RETURN p, c
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ListComprehension_02")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Using a list comprehension in a WHERE
		Given an empty graph
		And having executed:
			"""
			CREATE (a:A {prop: 'c'})
			CREATE (a)-[:T]->(:B),
						 (a)-[:T]->(:C)
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testListComprehension_03() {
				val cypher = CypherParser.parseString('''
				MATCH (n)-->(b)
				WHERE n.prop IN [x IN labels(b) | lower(x)]
				RETURN b
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ListComprehension_03")
				Cypher2Relalg.processCypher(cypher)
		}

}
