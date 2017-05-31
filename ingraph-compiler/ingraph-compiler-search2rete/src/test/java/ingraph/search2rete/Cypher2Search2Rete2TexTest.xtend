package ingraph.search2rete

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.optimization.transformations.SearchPlanCalculator
import ingraph.relalg.util.RelalgUtil
import ingraph.relalg2tex.config.RelalgConverterConfigBuilder
import ingraph.relalg2tex.converters.relalgconverters.Relalg2TexExpressionConverter
import ingraph.relalg2tex.converters.relalgconverters.Relalg2TexTreeConverter

abstract class Cypher2Search2Rete2TexTest {

	protected val config = new RelalgConverterConfigBuilder().setStandaloneDocument(true).setParentheses(true).
		setIncludeProductionOperator(true).setSchemaIndices(true).setIncludeCommonVariables(true).build
	protected val expressionConverter = new Relalg2TexExpressionConverter(config)
	protected val treeConverter = new Relalg2TexTreeConverter(config)

	protected abstract def String directory()

	protected def process(String query, String querySpecification) {
		// search-based
		val containerSearchBased = Cypher2Relalg.processString(querySpecification, query)
		val searchPlanCalculator = new SearchPlanCalculator
		searchPlanCalculator.apply(containerSearchBased)

		treeConverter.convert(containerSearchBased, '''«directory()»/«query»-search-tree''')
		expressionConverter.convert(containerSearchBased, '''«directory()»/«query»-search-expression''')
		RelalgUtil.save(containerSearchBased, '''query-models/«query»-search''')

		// Rete
		val containerRete = Cypher2Relalg.processString(querySpecification, query)
		val reteCalculator = new Search2ReteTransformationAndSchemaCalculator
		reteCalculator.apply(containerRete)

		treeConverter.convert(containerRete, '''«directory()»/«query»-rete-tree''')		
		RelalgUtil.save(containerSearchBased, '''query-models/«query»-rete''')
		return containerSearchBased
	}

}
