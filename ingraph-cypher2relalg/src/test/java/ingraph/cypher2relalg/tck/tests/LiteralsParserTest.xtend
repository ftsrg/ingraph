package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class LiteralsParserTest {
		
		/*
		Scenario: Return an integer
		*/
		@Test
		@Category(FailingTests)
		def void testLiterals_01() {
				val cypher = CypherParser.parseString('''
				RETURN 1 AS literal
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Literals_01")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Return a float
		*/
		@Test
		@Category(FailingTests)
		def void testLiterals_02() {
				val cypher = CypherParser.parseString('''
				RETURN 1.0 AS literal
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Literals_02")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Return a float in exponent form
		*/
		@Test
		@Category(FailingTests)
		def void testLiterals_03() {
				val cypher = CypherParser.parseString('''
				RETURN -1e-9 AS literal
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Literals_03")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Return a boolean
		*/
		@Test
		@Category(FailingTests)
		def void testLiterals_04() {
				val cypher = CypherParser.parseString('''
				RETURN true AS literal
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Literals_04")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Return a single-quoted string
		*/
		@Test
		@Category(FailingTests)
		def void testLiterals_05() {
				val cypher = CypherParser.parseString('''
				RETURN '' AS literal
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Literals_05")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Return a double-quoted string
		*/
		@Test
		@Category(FailingTests)
		def void testLiterals_06() {
				val cypher = CypherParser.parseString('''
				RETURN "" AS literal
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Literals_06")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Return null
		*/
		@Test
		@Category(FailingTests)
		def void testLiterals_07() {
				val cypher = CypherParser.parseString('''
				RETURN null AS literal
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Literals_07")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Return an empty list
		*/
		@Test
		@Category(FailingTests)
		def void testLiterals_08() {
				val cypher = CypherParser.parseString('''
				RETURN [] AS literal
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Literals_08")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Return a nonempty list
		*/
		@Test
		@Category(FailingTests)
		def void testLiterals_09() {
				val cypher = CypherParser.parseString('''
				RETURN [0, 1, 2] AS literal
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Literals_09")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Return an empty map
		*/
		@Test
		@Category(FailingTests)
		def void testLiterals_10() {
				val cypher = CypherParser.parseString('''
				RETURN {} AS literal
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Literals_10")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Return a nonempty map
		*/
		@Test
		@Category(FailingTests)
		def void testLiterals_11() {
				val cypher = CypherParser.parseString('''
				RETURN {k1: 0, k2: 'string'} AS literal
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/Literals_11")
				Cypher2Relalg.processCypher(cypher)
		}

}
