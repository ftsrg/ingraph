package ingraph.optimization.test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.optimization.transformations.reteoptimization.ReteOptimization
import ingraph.relalg.inferencers.BasicSchemaInferencer
import ingraph.relalg.inferencers.ExtraVariableInferencer
import ingraph.relalg.inferencers.FullSchemaInferencer
import ingraph.relalg.util.RelalgUtil
import ingraph.relalg2rete.Relalg2ReteTransformation
import ingraph.relalg2tex.config.RelalgConverterConfig
import ingraph.relalg2tex.converters.relalgconverters.Relalg2TexTreeConverter

abstract class Cypher2Relalg2Rete2TexTest {
	
	protected extension Relalg2ReteTransformation Relalg2ReteTransformation = new Relalg2ReteTransformation
	protected extension BasicSchemaInferencer basicSchemaInferencer = new BasicSchemaInferencer
	protected extension ExtraVariableInferencer extraVariableInferencer = new ExtraVariableInferencer
	protected extension FullSchemaInferencer fullSchemaInferencer = new FullSchemaInferencer

	protected extension ReteOptimization optimization = new ReteOptimization

	protected val config = RelalgConverterConfig.builder.consoleOutput(false).standaloneDocument(true).build
	protected val drawer = new Relalg2TexTreeConverter(config)
	
	protected abstract def String directory()
	
	protected def process(String query, String querySpecification) {
		// search-based
		val containerSearchBased = Cypher2Relalg.processString(querySpecification)
		containerSearchBased.inferBasicSchema
		drawer.convert(containerSearchBased, '''«directory()»/«query»-search''')
		RelalgUtil.save(containerSearchBased, '''query-models/«query»-search''')

		// Rete
		val containerRete = Cypher2Relalg.processString(querySpecification)
		containerRete.transformToRete
		containerRete.inferBasicSchema
		containerRete.inferExtraVariables
		containerRete.inferFullSchema
		drawer.convert(containerRete, '''«directory()»/«query»-rete''')
		RelalgUtil.save(containerSearchBased, '''query-models/«query»-rete''')
		return containerSearchBased
	}
	
	
}