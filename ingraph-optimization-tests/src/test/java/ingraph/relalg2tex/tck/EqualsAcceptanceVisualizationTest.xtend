package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.inferencers.BasicSchemaInferencer
import ingraph.relalg2tex.relalgconverters.Relalg2TexTreeConverter

class EqualsAcceptanceVisualizationTest {

		extension Relalg2TexTreeConverter converter = new Relalg2TexTreeConverter
		extension BasicSchemaInferencer inferencer = new BasicSchemaInferencer
		
		/*
		Scenario: Number-typed integer comparison
		Given an empty graph
		And having executed:
			"""
			CREATE ({id: 0})
			"""
		*/
		@Test
		def void testEqualsAcceptance_01() {
				val container = Cypher2Relalg.processString('''
				WITH collect([0, 0.0]) AS numbers
				UNWIND numbers AS arr
				WITH arr[0] AS expected
				MATCH (n) WHERE toInteger(n.id) = expected
				RETURN n
				''')
				container.inferBasicSchema
				container.convert("tck/EqualsAcceptance_01")
		}

		/*
		Scenario: Number-typed float comparison
		Given an empty graph
		And having executed:
			"""
			CREATE ({id: 0})
			"""
		*/
		@Test
		def void testEqualsAcceptance_02() {
				val container = Cypher2Relalg.processString('''
				WITH collect([0.5, 0]) AS numbers
				UNWIND numbers AS arr
				WITH arr[0] AS expected
				MATCH (n) WHERE toInteger(n.id) = expected
				RETURN n
				''')
				container.inferBasicSchema
				container.convert("tck/EqualsAcceptance_02")
		}

		/*
		Scenario: Any-typed string comparison
		Given an empty graph
		And having executed:
			"""
			CREATE ({id: 0})
			"""
		*/
		@Test
		def void testEqualsAcceptance_03() {
				val container = Cypher2Relalg.processString('''
				WITH collect(['0', 0]) AS things
				UNWIND things AS arr
				WITH arr[0] AS expected
				MATCH (n) WHERE toInteger(n.id) = expected
				RETURN n
				''')
				container.inferBasicSchema
				container.convert("tck/EqualsAcceptance_03")
		}

		/*
		Scenario: Comparing nodes to nodes
		Given an empty graph
		And having executed:
			"""
			CREATE ()
			"""
		*/
		@Test
		def void testEqualsAcceptance_04() {
				val container = Cypher2Relalg.processString('''
				MATCH (a)
				WITH a
				MATCH (b)
				WHERE a = b
				RETURN count(b)
				''')
				container.inferBasicSchema
				container.convert("tck/EqualsAcceptance_04")
		}

		/*
		Scenario: Comparing relationships to relationships
		Given an empty graph
		And having executed:
			"""
			CREATE ()-[:T]->()
			"""
		*/
		@Test
		def void testEqualsAcceptance_05() {
				val container = Cypher2Relalg.processString('''
				MATCH ()-[a]->()
				WITH a
				MATCH ()-[b]->()
				WHERE a = b
				RETURN count(b)
				''')
				container.inferBasicSchema
				container.convert("tck/EqualsAcceptance_05")
		}

}
