package ingraph

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.optimization.transformations.SimplifyingTransformation
import ingraph.relalg.calculators.ExternalSchemaCalculator
import ingraph.relalg.calculators.ExtraVariablesCalculator
import ingraph.relalg.calculators.InternalSchemaCalculator
import ingraph.relalg.util.RelalgUtil
import ingraph.relalg2tex.config.RelalgConverterConfigBuilder
import ingraph.relalg2tex.converters.relalgconverters.Relalg2TexExpressionConverter
import ingraph.relalg2tex.converters.relalgconverters.Relalg2TexTreeConverter
import relalg.RelalgContainer
import ingraph.optimization.transformations.SearchPlanCalculator

abstract class Cypher2SearchAbstractTest {


	protected extension ExternalSchemaCalculator externalSchemaCalculator = new ExternalSchemaCalculator
	protected extension ExtraVariablesCalculator extraVariablesCalculator = new ExtraVariablesCalculator
	protected extension InternalSchemaCalculator internalSchemaCalculator = new InternalSchemaCalculator

	protected val config = new RelalgConverterConfigBuilder().setStandaloneDocument(true).setParentheses(true).
		setIncludeProductionOperator(true).setSchemaIndices(true).setIncludeCommonVariables(true).build
	protected val expressionConverter = new Relalg2TexExpressionConverter(config)
	protected val treeConverter = new Relalg2TexTreeConverter(config)

	protected abstract def String directory()

	protected def process(String query, String querySpecification) {
		// Search-based
		val containerSearchBased = Cypher2Relalg.processString(querySpecification, query)
		val searchPlanCalculator = new SearchPlanCalculator
		searchPlanCalculator.apply(containerSearchBased)

		treeConverter.convert(containerSearchBased, '''«directory()»/«query»-search-tree''')
		expressionConverter.convert(containerSearchBased, '''«directory()»/«query»-search-expression''')
		RelalgUtil.save(containerSearchBased, '''query-models/«query»-search''')

		// Transform
		transform(querySpecification, query, containerSearchBased)
	}
	
	protected abstract def RelalgContainer transform(String querySpecification, String query, RelalgContainer containerSearchBased)

}
