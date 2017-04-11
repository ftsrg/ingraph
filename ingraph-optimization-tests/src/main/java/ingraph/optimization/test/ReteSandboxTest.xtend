package ingraph.optimization.test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg.inferencers.BasicSchemaInferencer
import ingraph.relalg.inferencers.ExtraVariableInferencer
import ingraph.relalg.inferencers.FullSchemaInferencer
import ingraph.relalg.util.RelalgUtil
import ingraph.relalg2rete.Relalg2ReteTransformation
import ingraph.relalg2tex.config.RelalgConverterConfig
import ingraph.relalg2tex.converters.relalgconverters.Relalg2TexTreeConverter

class ReteSandboxTest {

	extension Relalg2ReteTransformation Relalg2ReteTransformation = new Relalg2ReteTransformation
	extension BasicSchemaInferencer basicSchemaInferencer = new BasicSchemaInferencer
	extension ExtraVariableInferencer extraVariableInferencer = new ExtraVariableInferencer
	extension FullSchemaInferencer fullSchemaInferencer = new FullSchemaInferencer

	val config = RelalgConverterConfig.builder.consoleOutput(false).standaloneDocument(true).build
	val drawer = new Relalg2TexTreeConverter(config)

	def process(String queryName, String cypher) {
//		// search-based
//		val containerSearchBased = Cypher2Relalg.processString(cypher)
//		containerSearchBased.inferBasicSchema
//		drawer.convert(containerSearchBased, "sandbox/" + queryName + "-search")
//		RelalgUtil.save(containerSearchBased, "query-models/" + queryName + "-search")

		// Rete
		val containerRete = Cypher2Relalg.processString(cypher)
		containerRete.transformToRete
//		containerRete.inferBasicSchema
//		containerRete.inferExtraVariables
//		containerRete.inferFullSchema
		drawer.convert(containerRete, "sandbox/" + queryName + "-rete")
//		RelalgUtil.save(containerRete, "query-models/" + queryName + "-rete")
//		return containerSearchBased
	}


}