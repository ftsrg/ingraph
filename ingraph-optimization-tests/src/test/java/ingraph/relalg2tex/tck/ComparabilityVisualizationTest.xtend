package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.inferencers.BasicSchemaInferencer
import ingraph.relalg2tex.relalgconverters.Relalg2TexTreeConverter

class ComparabilityVisualizationTest {

		extension Relalg2TexTreeConverter converter = new Relalg2TexTreeConverter
		extension BasicSchemaInferencer inferencer = new BasicSchemaInferencer
		
		/*
		Scenario: Fail when comparing nodes to parameters
		Given an empty graph
		And having executed:
			"""
			CREATE ()
			"""
		And parameters are:
			| param | 'foo' |
		*/
		@Test
		def void testComparability_01() {
				val container = Cypher2Relalg.processString('''
				MATCH (b)
				WHERE b = $param
				RETURN b
				''')
				container.inferBasicSchema
				container.convert("tck/Comparability_01")
		}

		/*
		Scenario: Fail when comparing parameters to nodes
		Given an empty graph
		And having executed:
			"""
			CREATE ()
			"""
		And parameters are:
			| param | 'foo' |
		*/
		@Test
		def void testComparability_02() {
				val container = Cypher2Relalg.processString('''
				MATCH (b)
				WHERE $param = b
				RETURN b
				''')
				container.inferBasicSchema
				container.convert("tck/Comparability_02")
		}

		/*
		Scenario: Comparing nodes to properties
		Given an empty graph
		And having executed:
			"""
			CREATE ({val: 17})
			"""
		*/
		@Test
		def void testComparability_03() {
				val container = Cypher2Relalg.processString('''
				MATCH (a)
				WHERE a = a.val
				RETURN count(a)
				''')
				container.inferBasicSchema
				container.convert("tck/Comparability_03")
		}

		/*
		Scenario: Fail when comparing nodes to relationships
		Given an empty graph
		And having executed:
			"""
			CREATE ()-[:T]->()
			"""
		*/
		@Test
		def void testComparability_04() {
				val container = Cypher2Relalg.processString('''
				MATCH (a)-[b]->()
				RETURN a = b
				''')
				container.inferBasicSchema
				container.convert("tck/Comparability_04")
		}

		/*
		Scenario: Fail when comparing relationships to nodes
		Given an empty graph
		And having executed:
			"""
			CREATE ()-[:T]->()
			"""
		*/
		@Test
		def void testComparability_05() {
				val container = Cypher2Relalg.processString('''
				MATCH (a)-[b]->()
				RETURN b = a
				''')
				container.inferBasicSchema
				container.convert("tck/Comparability_05")
		}

}
