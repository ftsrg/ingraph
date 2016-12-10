package ingraph.report.tests

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.optimization.transformations.relalg2rete.Relalg2ReteTransformation
import ingraph.relalg.util.SchemaInferencer
import ingraph.relalg2tex.RelalgExpressionSerializer
import ingraph.relalg2tex.RelalgSerializerConfig
import ingraph.relalg2tex.RelalgTreeSerializer

class IngraphReportTest {

	protected extension SchemaInferencer inferencer = new SchemaInferencer
	protected extension Relalg2ReteTransformation Relalg2ReteTransformation = new Relalg2ReteTransformation
	protected val treeSerializer = new RelalgTreeSerializer
	protected val config = RelalgSerializerConfig.builder.build
	protected val expressionSerializer = new RelalgExpressionSerializer(config)

	/**
	 * Drops indentation and quotes.
	 */
	def cleanup(String s) {
		s //
		.replaceAll('''"""''', "") // """
		.replaceAll("'''", "") // '''
		.replaceAll("\n      ", "\n") // indentation
		.replaceAll("^\n", "") // newline at the start
		.replaceAll("\n$", "") // newline at the end
	}

	def escape(String s) {
		s //
		.replaceAll('''#''', '''\\#''') //
		.replaceAll('''_''', '''\\_''')
	}

	def expression(CharSequence s) {
		try {
			val container = Cypher2Relalg.processString(s.toString)
			expressionSerializer.serialize(container) //.addSchemaInformation).toString
		} catch (Exception e) {
			e.printStackTrace
			'''\text{Cannot convert to expression.}'''
		}
	}

	def visualize(CharSequence s) {
		try {
			val container = Cypher2Relalg.processString(s.toString)
			treeSerializer.serialize(container) //.addSchemaInformation)
		} catch (Exception e) {
			e.printStackTrace
			'''\text{Cannot visualize tree.}'''
		}
	}

	def visualizeWithTransformations(CharSequence s) {
		try {
			val container = Cypher2Relalg.processString(s.toString)
			treeSerializer.serialize(container.transformToRete) //.addSchemaInformation)
		} catch (Exception e) {
			e.printStackTrace
			'''Cannot visualize incremental tree.'''
		}
	}
	
}