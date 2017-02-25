package ingraph.relalg2tex.tck

import org.junit.Test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.inferencers.BasicSchemaInferencer
import ingraph.relalg2tex.relalgconverters.Relalg2TexTreeConverter

class WhereAcceptanceVisualizationTest {

		extension Relalg2TexTreeConverter converter = new Relalg2TexTreeConverter
		extension BasicSchemaInferencer inferencer = new BasicSchemaInferencer
		
		/*
		Scenario: NOT and false
		Given an empty graph
		And having executed:
			"""
			CREATE ({name: 'a'})
			"""
		*/
		@Test
		def void testWhereAcceptance_01() {
				val container = Cypher2Relalg.processString('''
				MATCH (n)
				WHERE NOT(n.name = 'apa' AND false)
				RETURN n
				''')
				container.inferBasicSchema
				container.convert("tck/WhereAcceptance_01")
		}

		/*
		Scenario: Fail when trying to compare strings and numbers
		Given an empty graph
		And having executed:
			"""
			CREATE (:Label {prop: '15'})
			"""
		*/
		@Test
		def void testWhereAcceptance_02() {
				val container = Cypher2Relalg.processString('''
				MATCH (n:Label)
				WHERE n.prop < 10
				RETURN n.prop AS prop
				''')
				container.inferBasicSchema
				container.convert("tck/WhereAcceptance_02")
		}

}
