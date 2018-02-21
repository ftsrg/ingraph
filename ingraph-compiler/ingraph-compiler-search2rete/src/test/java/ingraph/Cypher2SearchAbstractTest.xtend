package ingraph

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.optimization.transformations.SimplifyingTransformation
import ingraph.relalg.calculators.OneStepSchemaCalculator
import ingraph.relalg.util.RelalgUtil
import ingraph.relalg2tex.config.RelalgConverterConfigBuilder
import ingraph.relalg2tex.converters.relalgconverters.Relalg2TexTreeConverter
import ingraph.search2rete.Search2ReteTransformation

abstract class Cypher2SearchAbstractTest {

//	protected val expressionConverter = new Relalg2TexExpressionConverter(config)

	protected val config1 = new RelalgConverterConfigBuilder().setStandaloneDocument(false).setParentheses(true).
		setIncludeProductionOperator(false).setSchemaIndices(true).setIncludeCommonVariables(true).build
	protected val treeConverter1 = new Relalg2TexTreeConverter(config1)

	protected val config2 = new RelalgConverterConfigBuilder().setStandaloneDocument(true).setParentheses(true).
		setIncludeProductionOperator(false).setSchemaIndices(true).setIncludeCommonVariables(true).build
	protected val treeConverter2 = new Relalg2TexTreeConverter(config2)

	protected abstract def String directory()

	protected def process(String query, String querySpecification) {
		val containerSearchBased = Cypher2Relalg.processString(querySpecification, query)
		treeConverter1.convert(containerSearchBased, '''«directory()»/«query»-raw''')
		treeConverter2.convert(containerSearchBased, '''«directory()»/«query»-raw-standalone''')
		RelalgUtil.save(containerSearchBased, '''query-models/«query»-raw''')

		val simplifyingTransformation = new SimplifyingTransformation(containerSearchBased)
		simplifyingTransformation.simplify
		treeConverter1.convert(containerSearchBased, '''«directory()»/«query»-simplified''')
		treeConverter2.convert(containerSearchBased, '''«directory()»/«query»-simplified-standalone''')
		RelalgUtil.save(containerSearchBased, '''query-models/«query»-simplified''')

		val rete = Cypher2Relalg.processString(querySpecification, query)

		val simplifyingTransformation2 = new SimplifyingTransformation(rete)
		simplifyingTransformation2.simplify

		val s2r = new Search2ReteTransformation(rete);
		s2r.transformToRete		
		
		val schemaCalculator = new OneStepSchemaCalculator
		schemaCalculator.calculateSchema(rete)
		treeConverter1.convert(rete, '''«directory()»/«query»-rete''')
		treeConverter2.convert(rete, '''«directory()»/«query»-rete-standalone''')
		RelalgUtil.save(rete, '''query-models/«query»-rete''')		

	}

}
