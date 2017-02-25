package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.inferencers.BasicSchemaInferencer
import ingraph.relalg2tex.relalgconverters.Relalg2TexTreeConverter

class VarLengthAcceptanceVisualizationTest {

		extension Relalg2TexTreeConverter converter = new Relalg2TexTreeConverter
		extension BasicSchemaInferencer inferencer = new BasicSchemaInferencer
		
		/*
		Scenario: Handling unbounded variable length match
		*/
		@Test
		def void testVarLengthAcceptance_01() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_01")
		}

		/*
		Scenario: Handling explicitly unbounded variable length match
		*/
		@Test
		def void testVarLengthAcceptance_02() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*..]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_02")
		}

		/*
		Scenario: Handling single bounded variable length match 1
		*/
		@Test
		def void testVarLengthAcceptance_03() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*0]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_03")
		}

		/*
		Scenario: Handling single bounded variable length match 2
		*/
		@Test
		def void testVarLengthAcceptance_04() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*1]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_04")
		}

		/*
		Scenario: Handling single bounded variable length match 3
		*/
		@Test
		def void testVarLengthAcceptance_05() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*2]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_05")
		}

		/*
		Scenario: Handling upper and lower bounded variable length match 1
		*/
		@Test
		def void testVarLengthAcceptance_06() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*0..2]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_06")
		}

		/*
		Scenario: Handling upper and lower bounded variable length match 2
		*/
		@Test
		def void testVarLengthAcceptance_07() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*1..2]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_07")
		}

		/*
		Scenario: Handling symmetrically bounded variable length match, bounds are zero
		*/
		@Test
		def void testVarLengthAcceptance_08() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*0..0]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_08")
		}

		/*
		Scenario: Handling symmetrically bounded variable length match, bounds are one
		*/
		@Test
		def void testVarLengthAcceptance_09() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*1..1]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_09")
		}

		/*
		Scenario: Handling symmetrically bounded variable length match, bounds are two
		*/
		@Test
		def void testVarLengthAcceptance_10() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*2..2]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_10")
		}

		/*
		Scenario: Handling upper and lower bounded variable length match, empty interval 1
		*/
		@Test
		def void testVarLengthAcceptance_11() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*2..1]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_11")
		}

		/*
		Scenario: Handling upper and lower bounded variable length match, empty interval 2
		*/
		@Test
		def void testVarLengthAcceptance_12() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*1..0]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_12")
		}

		/*
		Scenario: Handling upper bounded variable length match, empty interval
		*/
		@Test
		def void testVarLengthAcceptance_13() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*..0]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_13")
		}

		/*
		Scenario: Handling upper bounded variable length match 1
		*/
		@Test
		def void testVarLengthAcceptance_14() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*..1]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_14")
		}

		/*
		Scenario: Handling upper bounded variable length match 2
		*/
		@Test
		def void testVarLengthAcceptance_15() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*..2]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_15")
		}

		/*
		Scenario: Handling lower bounded variable length match 1
		*/
		@Test
		def void testVarLengthAcceptance_16() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*0..]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_16")
		}

		/*
		Scenario: Handling lower bounded variable length match 2
		*/
		@Test
		def void testVarLengthAcceptance_17() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*1..]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_17")
		}

		/*
		Scenario: Handling lower bounded variable length match 3
		*/
		@Test
		def void testVarLengthAcceptance_18() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*2..]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_18")
		}

		/*
		Scenario: Handling a variable length relationship and a standard relationship in chain, zero length 1
		*/
		@Test
		def void testVarLengthAcceptance_19() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*0]->()-[:LIKES]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_19")
		}

		/*
		Scenario: Handling a variable length relationship and a standard relationship in chain, zero length 2
		*/
		@Test
		def void testVarLengthAcceptance_20() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES]->()-[:LIKES*0]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_20")
		}

		/*
		Scenario: Handling a variable length relationship and a standard relationship in chain, single length 1
		*/
		@Test
		def void testVarLengthAcceptance_21() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*1]->()-[:LIKES]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_21")
		}

		/*
		Scenario: Handling a variable length relationship and a standard relationship in chain, single length 2
		*/
		@Test
		def void testVarLengthAcceptance_22() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES]->()-[:LIKES*1]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_22")
		}

		/*
		Scenario: Handling a variable length relationship and a standard relationship in chain, longer 1
		*/
		@Test
		def void testVarLengthAcceptance_23() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES*2]->()-[:LIKES]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_23")
		}

		/*
		Scenario: Handling a variable length relationship and a standard relationship in chain, longer 2
		*/
		@Test
		def void testVarLengthAcceptance_24() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES]->()-[:LIKES*2]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_24")
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
		def void testVarLengthAcceptance_25() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES]->()-[:LIKES*3]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_25")
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
		def void testVarLengthAcceptance_26() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)<-[:LIKES]-()-[:LIKES*3]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_26")
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
		def void testVarLengthAcceptance_27() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (a)-[:LIKES]->()<-[:LIKES*3]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_27")
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
		def void testVarLengthAcceptance_28() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (p)-[:LIKES*1]->()-[:LIKES]->()-[r:LIKES*2]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_28")
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
		def void testVarLengthAcceptance_29() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				MATCH (p)-[:LIKES]->()-[:LIKES*2]->()-[r:LIKES]->(c)
				RETURN c.name
				''')
				container.inferBasicSchema
				container.convert("tck/VarLengthAcceptance_29")
		}

}
