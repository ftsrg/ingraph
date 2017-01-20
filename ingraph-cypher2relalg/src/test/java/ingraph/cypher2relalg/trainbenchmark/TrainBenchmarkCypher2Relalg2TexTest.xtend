package ingraph.cypher2relalg.trainbenchmark

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypherparser.CypherParser
import ingraph.relalg2tex.RelalgTreeSerializer
import java.io.IOException
import org.junit.Test
import ingraph.relalg2tex.RelalgSerializerConfig

class TrainBenchmarkCypher2Relalg2TexTest {
	
	val config = RelalgSerializerConfig.builder.standaloneDocument(true).consoleOutput(false).build
	val drawer = new RelalgTreeSerializer(config)
	
	def process(String query) {
		val cypher = CypherParser.parseFile("trainbenchmark/" + query)
		val expression = Cypher2Relalg.processCypher(cypher)
		drawer.serialize(expression, "trainbenchmark/" + query + "-search")
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