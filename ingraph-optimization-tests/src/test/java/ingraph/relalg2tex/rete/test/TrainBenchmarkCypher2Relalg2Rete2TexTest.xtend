package ingraph.relalg2tex.rete.test

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypherparser.CypherParser
import ingraph.optimization.transformations.relalg2rete.Relalg2ReteTransformation
import ingraph.relalg.inferencers.BasicSchemaInferencer
import ingraph.relalg.inferencers.FullSchemaInferencer
import ingraph.relalg2tex.config.RelalgConverterConfig
import ingraph.relalg2tex.relalgconverters.Relalg2TexTreeConverter
import java.io.IOException
import org.junit.Test
import ingraph.relalg.inferencers.ExtraVariableInferencer

class TrainBenchmarkCypher2Relalg2Rete2TexTest {

	extension Relalg2ReteTransformation Relalg2ReteTransformation = new Relalg2ReteTransformation
	extension BasicSchemaInferencer basicSchemaInferencer = new BasicSchemaInferencer
	extension ExtraVariableInferencer extraAttributeInferencer = new ExtraVariableInferencer
	extension FullSchemaInferencer fullSchemaInferencer = new FullSchemaInferencer

	val config = RelalgConverterConfig.builder.standaloneDocument(true).consoleOutput(false).
		includeCommonVariables(true).build
	val drawer = new Relalg2TexTreeConverter(config)

	def process(String query) {
		val cypher = CypherParser.parseFile("trainbenchmark/" + query)
		val container = Cypher2Relalg.processCypher(cypher)

		container.transformToRete
		container.inferBasicSchema
		container.inferExtraAttributes
		container.inferFullSchema
		//RelalgUtil.save(container, "query-models/" + query)
		drawer.convert(container, "trainbenchmark/" + query + "-rete")
	}

	@Test
	def void testConnectedSegments() throws IOException {
		process("ConnectedSegments")
	}

	@Test
	def void testPosLength() throws IOException {
		process("PosLength")
	}

	@Test
	def void testRouteSensor() throws IOException {
		process("RouteSensor")
	}

	@Test
	def void testSwitchMonitored() throws IOException {
		process("SwitchMonitored")
	}

	@Test
	def void testSwitchSet() throws IOException {
		process("SwitchSet")
	}

	@Test
	def void testSemaphoreNeighbor() throws IOException {
		process("SemaphoreNeighbor")
	}

}
