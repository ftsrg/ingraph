package ingraph.cypher2relalg.trainbenchmark

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypherparser.CypherParser
import ingraph.relalg.util.RelalgUtil
import ingraph.relalg2tex.config.RelalgConverterConfigBuilder
import ingraph.relalg2tex.converters.relalgconverters.Relalg2TexTreeConverter
import java.io.IOException
import org.junit.Test

class TrainBenchmarkCypher2Relalg2TexTest {
	
	val config = new RelalgConverterConfigBuilder().setStandaloneDocument(true).setConsoleOutput(false).build
	val drawer = new Relalg2TexTreeConverter(config)
	
	def process(String query) {
		val cypher = CypherParser.parseFile("trainbenchmark/" + query)
		val expression = Cypher2Relalg.processCypher(cypher, "trainBenchmark" + query)
		RelalgUtil.save(expression, "relalg-models/trainbenchmark/" + query)
		drawer.convert(expression, "trainbenchmark/" + query + "-search")
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