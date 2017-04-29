package ingraph.relalg2rete

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.calculators.ExternalSchemaCalculator
import ingraph.relalg.calculators.ExtraVariablesCalculator
import ingraph.relalg.calculators.InternalSchemaCalculator
import ingraph.relalg.util.RelalgUtil
import ingraph.relalg2tex.config.RelalgConverterConfigBuilder
import ingraph.relalg2tex.converters.relalgconverters.Relalg2TexTreeConverter
import ingraph.relalg2tex.converters.relalgconverters.Relalg2TexExpressionConverter

abstract class Cypher2Relalg2Rete2TexTest {

	extension ExternalSchemaCalculator externalSchemaCalculator = new ExternalSchemaCalculator
	extension ExtraVariablesCalculator extraVariablesCalculator = new ExtraVariablesCalculator
	extension InternalSchemaCalculator internalSchemaCalculator = new InternalSchemaCalculator

	protected val config = new RelalgConverterConfigBuilder().setOmitSchema(true).setConsoleOutput(false).
		setStandaloneDocument(true).setIncludeCommonVariables(false).setParentheses(true).
		setIncludeProductionOperator(false).build
	protected val expressionConverter = new Relalg2TexExpressionConverter(config)
	protected val treeConverter = new Relalg2TexTreeConverter(config)

	protected abstract def String directory()

	protected def process(String query, String querySpecification) {
		// search-based
		val containerSearchBased = Cypher2Relalg.processString(querySpecification, query)
		containerSearchBased.calculateExternalSchema
		treeConverter.convert(containerSearchBased, '''«directory()»/«query»-tree''')
		expressionConverter.convert(containerSearchBased, '''«directory()»/«query»-expression''')
		RelalgUtil.save(containerSearchBased, '''query-models/«query»-search''')

//		// Rete
//		val containerRete = Cypher2Relalg.processString(querySpecification, query)
//		val transformation = new Relalg2ReteTransformation(containerRete)
//		transformation.transformToRete
//		containerRete.calculateExternalSchema
//		containerRete.calculateExtraVariables
//		containerRete.calculateInternalSchema
//		treeConverter.convert(containerRete, '''«directory()»/«query»-rete''')
//		RelalgUtil.save(containerSearchBased, '''query-models/«query»-rete''')
//		return containerSearchBased
	}

}
