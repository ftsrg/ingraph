package ingraph.cypher2relalg.tck.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypher2relalg.tck.FailingTests
import ingraph.cypher2relalg.tck.RegressionTests
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import org.junit.Test
import org.junit.experimental.categories.Category

class VarLengthAcceptanceParserTest {
		
		/*
		Scenario: Handling unbounded variable length match
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance_01() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_01")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling explicitly unbounded variable length match
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance_02() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*..]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_02")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling single bounded variable length match 1
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance_03() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*0]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_03")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling single bounded variable length match 2
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance_04() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*1]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_04")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling single bounded variable length match 3
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance_05() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*2]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_05")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling upper and lower bounded variable length match 1
		*/
		@Test
		@Category(RegressionTests)
		def void testVarLengthAcceptance_06() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*0..2]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_06")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling upper and lower bounded variable length match 2
		*/
		@Test
		@Category(RegressionTests)
		def void testVarLengthAcceptance_07() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*1..2]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_07")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling symmetrically bounded variable length match, bounds are zero
		*/
		@Test
		@Category(RegressionTests)
		def void testVarLengthAcceptance_08() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*0..0]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_08")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling symmetrically bounded variable length match, bounds are one
		*/
		@Test
		@Category(RegressionTests)
		def void testVarLengthAcceptance_09() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*1..1]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_09")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling symmetrically bounded variable length match, bounds are two
		*/
		@Test
		@Category(RegressionTests)
		def void testVarLengthAcceptance_10() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*2..2]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_10")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling upper and lower bounded variable length match, empty interval 1
		*/
		@Test
		@Category(RegressionTests)
		def void testVarLengthAcceptance_11() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*2..1]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_11")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling upper and lower bounded variable length match, empty interval 2
		*/
		@Test
		@Category(RegressionTests)
		def void testVarLengthAcceptance_12() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*1..0]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_12")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling upper bounded variable length match, empty interval
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance_13() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*..0]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_13")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling upper bounded variable length match 1
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance_14() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*..1]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_14")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling upper bounded variable length match 2
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance_15() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*..2]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_15")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling lower bounded variable length match 1
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance_16() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*0..]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_16")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling lower bounded variable length match 2
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance_17() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*1..]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_17")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling lower bounded variable length match 3
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance_18() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*2..]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_18")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling a variable length relationship and a standard relationship in chain, zero length 1
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance_19() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*0]->()-[:LIKES]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_19")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling a variable length relationship and a standard relationship in chain, zero length 2
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance_20() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES]->()-[:LIKES*0]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_20")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling a variable length relationship and a standard relationship in chain, single length 1
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance_21() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*1]->()-[:LIKES]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_21")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling a variable length relationship and a standard relationship in chain, single length 2
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance_22() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES]->()-[:LIKES*1]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_22")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling a variable length relationship and a standard relationship in chain, longer 1
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance_23() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*2]->()-[:LIKES]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_23")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling a variable length relationship and a standard relationship in chain, longer 2
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance_24() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES]->()-[:LIKES*2]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_24")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling a variable length relationship and a standard relationship in chain, longer 3
		And having executed:
			"""
			MATCH (d:D)
			CREATE (e1:E {name: d.name + '0'}),
						 (e2:E {name: d.name + '1'})
			CREATE (d)-[:LIKES]->(e1),
						 (d)-[:LIKES]->(e2)
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance_25() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES]->()-[:LIKES*3]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_25")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling mixed relationship patterns and directions 1
		And having executed:
			"""
			MATCH (a:A)-[r]->(b)
			DELETE r
			CREATE (b)-[:LIKES]->(a)
			"""
		And having executed:
			"""
			MATCH (d:D)
			CREATE (e1:E {name: d.name + '0'}),
						 (e2:E {name: d.name + '1'})
			CREATE (d)-[:LIKES]->(e1),
						 (d)-[:LIKES]->(e2)
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance_26() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)<-[:LIKES]-()-[:LIKES*3]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_26")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling mixed relationship patterns and directions 2
		# This gets hard to follow for a human mind. The answer is named graphs, but it's not crucial to fix.
		And having executed:
			"""
			MATCH (a)-[r]->(b)
			WHERE NOT a:A
			DELETE r
			CREATE (b)-[:LIKES]->(a)
			"""
		And having executed:
			"""
			MATCH (d:D)
			CREATE (e1:E {name: d.name + '0'}),
						 (e2:E {name: d.name + '1'})
			CREATE (d)-[:LIKES]->(e1),
						 (d)-[:LIKES]->(e2)
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance_27() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES]->()<-[:LIKES*3]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_27")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling mixed relationship patterns 1
		And having executed:
			"""
			MATCH (d:D)
			CREATE (e1:E {name: d.name + '0'}),
						 (e2:E {name: d.name + '1'})
			CREATE (d)-[:LIKES]->(e1),
						 (d)-[:LIKES]->(e2)
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance_28() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (p)-[:LIKES*1]->()-[:LIKES]->()-[r:LIKES*2]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_28")
				Cypher2Relalg.processCypher(cypher)
		}

		/*
		Scenario: Handling mixed relationship patterns 2
		And having executed:
			"""
			MATCH (d:D)
			CREATE (e1:E {name: d.name + '0'}),
						 (e2:E {name: d.name + '1'})
			CREATE (d)-[:LIKES]->(e1),
						 (d)-[:LIKES]->(e2)
			"""
		*/
		@Test
		@Category(FailingTests)
		def void testVarLengthAcceptance_29() {
				val cypher = CypherParser.parseString('''
				MATCH (a:A)
				MATCH (p)-[:LIKES]->()-[:LIKES*2]->()-[r:LIKES]->(c)
				RETURN c.name
				''')
				CypherUtil.save(cypher, "../ingraph-cypxmi/tck/VarLengthAcceptance_29")
				Cypher2Relalg.processCypher(cypher)
		}

}
