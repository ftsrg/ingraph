package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class UnionAcceptanceParserTest {
		
		/*
		Scenario: Should be able to create text output from union queries
		Given an empty graph
		And having executed:
			"""
			CREATE (:A), (:B)
			"""
		*/
		@Test
		@Category(RegressionTests)
		def void testUnionAcceptance_01() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				RETURN a AS a
				UNION
				MATCH (b:B)
				RETURN b AS a
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/UnionAcceptance_01")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Two elements, both unique, not distinct
		Given an empty graph
		*/
		@Test
		@Category(FailingTests)
		def void testUnionAcceptance_02() {
				val cypher = CypherParser.parseString('''
				RETURN 1 AS x
				UNION ALL
				RETURN 2 AS x
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/UnionAcceptance_02")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Two elements, both unique, distinct
		Given an empty graph
		*/
		@Test
		@Category(FailingTests)
		def void testUnionAcceptance_03() {
				val cypher = CypherParser.parseString('''
				RETURN 1 AS x
				UNION
				RETURN 2 AS x
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/UnionAcceptance_03")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Three elements, two unique, distinct
		Given an empty graph
		*/
		@Test
		@Category(FailingTests)
		def void testUnionAcceptance_04() {
				val cypher = CypherParser.parseString('''
				RETURN 2 AS x
				UNION
				RETURN 1 AS x
				UNION
				RETURN 2 AS x
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/UnionAcceptance_04")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Three elements, two unique, not distinct
		Given an empty graph
		*/
		@Test
		@Category(FailingTests)
		def void testUnionAcceptance_05() {
				val cypher = CypherParser.parseString('''
				RETURN 2 AS x
				UNION ALL
				RETURN 1 AS x
				UNION ALL
				RETURN 2 AS x
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/UnionAcceptance_05")
				Cypher2Relalg.processCypher(cypher)
		}

}
