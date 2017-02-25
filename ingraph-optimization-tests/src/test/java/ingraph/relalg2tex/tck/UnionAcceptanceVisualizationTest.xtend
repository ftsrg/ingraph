package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.inferencers.BasicSchemaInferencer
import ingraph.relalg2tex.relalgconverters.Relalg2TexTreeConverter

class UnionAcceptanceVisualizationTest {

		extension Relalg2TexTreeConverter converter = new Relalg2TexTreeConverter
		extension BasicSchemaInferencer inferencer = new BasicSchemaInferencer
		
		/*
		Scenario: Should be able to create text output from union queries
		Given an empty graph
		And having executed:
			"""
			CREATE (:A), (:B)
			"""
		*/
		@Test
		def void testUnionAcceptance_01() {
				val container = Cypher2Relalg.processString('''
				MATCH (a:A)
				RETURN a AS a
				UNION
				MATCH (b:B)
				RETURN b AS a
				''')
				container.inferBasicSchema
				container.convert("tck/UnionAcceptance_01")
		}

		/*
		Scenario: Two elements, both unique, not distinct
		Given an empty graph
		*/
		@Test
		def void testUnionAcceptance_02() {
				val container = Cypher2Relalg.processString('''
				RETURN 1 AS x
				UNION ALL
				RETURN 2 AS x
				''')
				container.inferBasicSchema
				container.convert("tck/UnionAcceptance_02")
		}

		/*
		Scenario: Two elements, both unique, distinct
		Given an empty graph
		*/
		@Test
		def void testUnionAcceptance_03() {
				val container = Cypher2Relalg.processString('''
				RETURN 1 AS x
				UNION
				RETURN 2 AS x
				''')
				container.inferBasicSchema
				container.convert("tck/UnionAcceptance_03")
		}

		/*
		Scenario: Three elements, two unique, distinct
		Given an empty graph
		*/
		@Test
		def void testUnionAcceptance_04() {
				val container = Cypher2Relalg.processString('''
				RETURN 2 AS x
				UNION
				RETURN 1 AS x
				UNION
				RETURN 2 AS x
				''')
				container.inferBasicSchema
				container.convert("tck/UnionAcceptance_04")
		}

		/*
		Scenario: Three elements, two unique, not distinct
		Given an empty graph
		*/
		@Test
		def void testUnionAcceptance_05() {
				val container = Cypher2Relalg.processString('''
				RETURN 2 AS x
				UNION ALL
				RETURN 1 AS x
				UNION ALL
				RETURN 2 AS x
				''')
				container.inferBasicSchema
				container.convert("tck/UnionAcceptance_05")
		}

}
