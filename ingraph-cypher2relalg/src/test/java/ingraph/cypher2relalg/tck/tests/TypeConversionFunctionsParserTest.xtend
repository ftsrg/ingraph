package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class TypeConversionFunctionsParserTest {
		
		/*
		Scenario: `toBoolean()` on valid literal string
		Given any graph
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_01() {
				val cypher = CypherParser.parseString('''
				RETURN toBoolean('true') AS b
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_01")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toBoolean()` on booleans
		Given any graph
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_02() {
				val cypher = CypherParser.parseString('''
				UNWIND [true, false] AS b
				RETURN toBoolean(b) AS b
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_02")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toBoolean()` on variables with valid string values
		Given any graph
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_03() {
				val cypher = CypherParser.parseString('''
				UNWIND ['true', 'false'] AS s
				RETURN toBoolean(s) AS b
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_03")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toBoolean()` on invalid strings
		Given any graph
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_04() {
				val cypher = CypherParser.parseString('''
				UNWIND [null, '', ' tru ', 'f alse'] AS things
				RETURN toBoolean(things) AS b
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_04")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toInteger()`
		Given an empty graph
		And having executed:
			"""
			CREATE (:Person {age: '42'})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_05() {
				val cypher = CypherParser.parseString('''
				MATCH (p:Person { age: '42' })
				WITH *
				MATCH (n)
				RETURN toInteger(n.age) AS age
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_05")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toInteger()` on float
		Given any graph
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_06() {
				val cypher = CypherParser.parseString('''
				WITH 82.9 AS weight
				RETURN toInteger(weight)
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_06")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toInteger()` returning null on non-numerical string
		Given any graph
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_07() {
				val cypher = CypherParser.parseString('''
				WITH 'foo' AS foo_string, '' AS empty_string
				RETURN toInteger(foo_string) AS foo, toInteger(empty_string) AS empty
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_07")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toInteger()` handling mixed number types
		Given any graph
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_08() {
				val cypher = CypherParser.parseString('''
				WITH [2, 2.9] AS numbers
				RETURN [n IN numbers | toInteger(n)] AS int_numbers
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_08")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toInteger()` handling Any type
		Given any graph
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_09() {
				val cypher = CypherParser.parseString('''
				WITH [2, 2.9, '1.7'] AS things
				RETURN [n IN things | toInteger(n)] AS int_numbers
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_09")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toInteger()` on a list of strings
		Given any graph
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_10() {
				val cypher = CypherParser.parseString('''
				WITH ['2', '2.9', 'foo'] AS numbers
				RETURN [n IN numbers | toInteger(n)] AS int_numbers
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_10")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toInteger()` on a complex-typed expression
		Given any graph
		And parameters are:
			| param | 1 |
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_11() {
				val cypher = CypherParser.parseString('''
				RETURN toInteger(1 - {param}) AS result
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_11")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toFloat()`
		Given an empty graph
		And having executed:
			"""
			CREATE (:Movie {rating: 4})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_12() {
				val cypher = CypherParser.parseString('''
				MATCH (m:Movie { rating: 4 })
				WITH *
				MATCH (n)
				RETURN toFloat(n.rating) AS float
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_12")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toFloat()` on mixed number types
		Given any graph
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_13() {
				val cypher = CypherParser.parseString('''
				WITH [3.4, 3] AS numbers
				RETURN [n IN numbers | toFloat(n)] AS float_numbers
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_13")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toFloat()` returning null on non-numerical string
		Given any graph
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_14() {
				val cypher = CypherParser.parseString('''
				WITH 'foo' AS foo_string, '' AS empty_string
				RETURN toFloat(foo_string) AS foo, toFloat(empty_string) AS empty
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_14")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toFloat()` handling Any type
		Given any graph
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_15() {
				val cypher = CypherParser.parseString('''
				WITH [3.4, 3, '5'] AS numbers
				RETURN [n IN numbers | toFloat(n)] AS float_numbers
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_15")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toFloat()` on a list of strings
		Given any graph
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_16() {
				val cypher = CypherParser.parseString('''
				WITH ['1', '2', 'foo'] AS numbers
				RETURN [n IN numbers | toFloat(n)] AS float_numbers
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_16")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toString()`
		Given an empty graph
		And having executed:
			"""
			CREATE (:Movie {rating: 4})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_17() {
				val cypher = CypherParser.parseString('''
				MATCH (m:Movie { rating: 4 })
				WITH *
				MATCH (n)
				RETURN toString(n.rating)
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_17")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toString()` handling boolean properties
		Given an empty graph
		And having executed:
			"""
			CREATE (:Movie {watched: true})
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_18() {
				val cypher = CypherParser.parseString('''
				MATCH (m:Movie)
				RETURN toString(m.watched)
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_18")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toString()` handling inlined boolean
		Given any graph
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_19() {
				val cypher = CypherParser.parseString('''
				RETURN toString(1 < 0) AS bool
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_19")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toString()` handling boolean literal
		Given any graph
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_20() {
				val cypher = CypherParser.parseString('''
				RETURN toString(true) AS bool
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_20")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toString()` should work on Any type
		Given any graph
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_21() {
				val cypher = CypherParser.parseString('''
				RETURN [x IN [1, 2.3, true, 'apa'] | toString(x) ] AS list
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_21")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toString()` on a list of integers
		Given any graph
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_22() {
				val cypher = CypherParser.parseString('''
				WITH [1, 2, 3] AS numbers
				RETURN [n IN numbers | toString(n)] AS string_numbers
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_22")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toString()` should accept potentially correct types 1
		Given any graph
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_23() {
				val cypher = CypherParser.parseString('''
				UNWIND ['male', 'female', null] AS gen
				RETURN coalesce(toString(gen), 'x') AS result
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_23")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: `toString()` should accept potentially correct types 2
		Given any graph
		*/
		@Test
		@Category(FailingTests)
		def void testTypeConversionFunctions_24() {
				val cypher = CypherParser.parseString('''
				UNWIND ['male', 'female', null] AS gen
				RETURN toString(coalesce(gen, 'x')) AS result
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/TypeConversionFunctions_24")
				Cypher2Relalg.processCypher(cypher)
		}

}
