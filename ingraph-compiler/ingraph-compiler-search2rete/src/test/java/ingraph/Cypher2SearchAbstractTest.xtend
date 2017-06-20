package ingraph

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.optimization.transformations.SimplifyingTransformation
import ingraph.relalg.util.RelalgUtil
import ingraph.relalg2tex.config.RelalgConverterConfigBuilder
import ingraph.relalg2tex.converters.relalgconverters.Relalg2TexExpressionConverter
import ingraph.relalg2tex.converters.relalgconverters.Relalg2TexTreeConverter
import relalg.RelalgContainer

abstract class Cypher2SearchAbstractTest {

//	protected val expressionConverter = new Relalg2TexExpressionConverter(config)

	protected val config1 = new RelalgConverterConfigBuilder().setStandaloneDocument(false).setParentheses(true).
		setIncludeProductionOperator(false).setSchemaIndices(true).setIncludeCommonVariables(true).build
	protected val treeConverter1 = new Relalg2TexTreeConverter(config1)

	protected val config2 = new RelalgConverterConfigBuilder().setStandaloneDocument(false).setParentheses(true).
		setIncludeProductionOperator(false).setSchemaIndices(true).setIncludeCommonVariables(true).build
	protected val treeConverter2 = new Relalg2TexTreeConverter(config2)


	protected abstract def String directory()

//		val searchPlanCalculator = new SearchPlanCalculator
//		searchPlanCalculator.apply(containerSearchBased)


	protected def process(String query, String querySpecification) {
		// Search-based
		val containerSearchBased = Cypher2Relalg.processString(querySpecification, query)
		treeConverter1.convert(containerSearchBased, '''«directory()»/«query»-raw''')
		treeConverter2.convert(containerSearchBased, '''«directory()»/«query»-raw-standalone''')
		RelalgUtil.save(containerSearchBased, '''query-models/«query»-raw''')
		
		val simplifyingTransformation = new SimplifyingTransformation(containerSearchBased)
		val simplifiedPlan = simplifyingTransformation.simplify

		treeConverter1.convert(simplifiedPlan, '''«directory()»/«query»-simplified''')
		treeConverter2.convert(simplifiedPlan, '''«directory()»/«query»-simplified-standalone''')
		RelalgUtil.save(containerSearchBased, '''query-models/«query»-simplified''')
		
	}

	protected abstract def RelalgContainer transform(String querySpecification, String query, RelalgContainer containerSearchBased)

}
