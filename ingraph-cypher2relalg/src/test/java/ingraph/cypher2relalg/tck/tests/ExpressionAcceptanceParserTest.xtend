package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class ExpressionAcceptanceParserTest {
		
		/*
		Scenario: IN should work with nested list subscripting
		*/
		@Test
		@Category(FailingTests)
		def void testExpressionAcceptance_01() {
				val cypher = CypherParser.parseString('''
				WITH [[1, 2, 3]] AS list
				RETURN 3 IN list[0] AS r
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_01")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: IN should work with nested literal list subscripting
		*/
		@Test
		@Category(FailingTests)
		def void testExpressionAcceptance_02() {
				val cypher = CypherParser.parseString('''
				RETURN 3 IN [[1, 2, 3]][0] AS r
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_02")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: IN should work with list slices
		*/
		@Test
		@Category(FailingTests)
		def void testExpressionAcceptance_03() {
				val cypher = CypherParser.parseString('''
				WITH [1, 2, 3] AS list
				RETURN 3 IN list[0..1] AS r
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_03")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: IN should work with literal list slices
		*/
		@Test
		@Category(FailingTests)
		def void testExpressionAcceptance_04() {
				val cypher = CypherParser.parseString('''
				RETURN 3 IN [1, 2, 3][0..1] AS r
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_04")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Execute n[0]
		*/
		@Test
		@Category(FailingTests)
		def void testExpressionAcceptance_05() {
				val cypher = CypherParser.parseString('''
				RETURN [1, 2, 3][0] AS value
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_05")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Execute n['name'] in read queries
		And having executed:
			"""
			CREATE ({name: 'Apa'})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testExpressionAcceptance_06() {
				val cypher = CypherParser.parseString('''
				MATCH (n {name: 'Apa'})
				RETURN n['nam' + 'e'] AS value
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_06")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Use dynamic property lookup based on parameters when there is no type information
		And parameters are:
			| expr | {name: 'Apa'} |
			| idx  | 'name'        |
		*/
		@Test
		@Category(FailingTests)
		def void testExpressionAcceptance_08() {
				val cypher = CypherParser.parseString('''
				WITH $expr AS expr, $idx AS idx
				RETURN expr[idx] AS value
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_08")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Use dynamic property lookup based on parameters when there is rhs type information
		And parameters are:
			| expr | {name: 'Apa'} |
			| idx  | 'name'        |
		*/
		@Test
		@Category(FailingTests)
		def void testExpressionAcceptance_10() {
				val cypher = CypherParser.parseString('''
				WITH $expr AS expr, $idx AS idx
				RETURN expr[toString(idx)] AS value
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_10")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Use collection lookup based on parameters when there is no type information
		And parameters are:
			| expr | ['Apa'] |
			| idx  | 0       |
		*/
		@Test
		@Category(FailingTests)
		def void testExpressionAcceptance_11() {
				val cypher = CypherParser.parseString('''
				WITH $expr AS expr, $idx AS idx
				RETURN expr[idx] AS value
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_11")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Use collection lookup based on parameters when there is lhs type information
		And parameters are:
			| idx | 0 |
		*/
		@Test
		@Category(FailingTests)
		def void testExpressionAcceptance_12() {
				val cypher = CypherParser.parseString('''
				WITH ['Apa'] AS expr
				RETURN expr[$idx] AS value
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_12")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Use collection lookup based on parameters when there is rhs type information
		And parameters are:
			| expr | ['Apa'] |
			| idx  | 0       |
		*/
		@Test
		@Category(FailingTests)
		def void testExpressionAcceptance_13() {
				val cypher = CypherParser.parseString('''
				WITH $expr AS expr, $idx AS idx
				RETURN expr[toInteger(idx)] AS value
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_13")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Fail at runtime when attempting to index with an Int into a Map
		And parameters are:
			| expr | {name: 'Apa'} |
			| idx  | 0             |
		*/
		@Test
		@Category(FailingTests)
		def void testExpressionAcceptance_14() {
				val cypher = CypherParser.parseString('''
				WITH $expr AS expr, $idx AS idx
				RETURN expr[idx]
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_14")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Fail at runtime when trying to index into a map with a non-string
		And parameters are:
			| expr | {name: 'Apa'} |
			| idx  | 12.3          |
		*/
		@Test
		@Category(FailingTests)
		def void testExpressionAcceptance_15() {
				val cypher = CypherParser.parseString('''
				WITH $expr AS expr, $idx AS idx
				RETURN expr[idx]
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_15")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Fail at runtime when attempting to index with a String into a Collection
		And parameters are:
			| expr | ['Apa'] |
			| idx  | 'name'  |
		*/
		@Test
		@Category(FailingTests)
		def void testExpressionAcceptance_16() {
				val cypher = CypherParser.parseString('''
				WITH $expr AS expr, $idx AS idx
				RETURN expr[idx]
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_16")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Fail at runtime when trying to index into a list with a list
		And parameters are:
			| expr | ['Apa'] |
			| idx  | ['Apa'] |
		*/
		@Test
		@Category(FailingTests)
		def void testExpressionAcceptance_17() {
				val cypher = CypherParser.parseString('''
				WITH $expr AS expr, $idx AS idx
				RETURN expr[idx]
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_17")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Fail at runtime when trying to index something which is not a map or collection
		And parameters are:
			| expr | 100 |
			| idx  | 0   |
		*/
		@Test
		@Category(FailingTests)
		def void testExpressionAcceptance_18() {
				val cypher = CypherParser.parseString('''
				WITH $expr AS expr, $idx AS idx
				RETURN expr[idx]
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/ExpressionAcceptance_18")
				Cypher2Relalg.processCypher(cypher)
		}

}
