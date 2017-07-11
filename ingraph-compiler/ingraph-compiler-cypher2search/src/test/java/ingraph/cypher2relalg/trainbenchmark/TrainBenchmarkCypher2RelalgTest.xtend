package ingraph.cypher2relalg.trainbenchmark

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import ingraph.relalg.calculators.ExternalSchemaCalculator
import java.io.IOException
import org.junit.Test

class TrainBenchmarkCypher2RelalgTest {

	extension ExternalSchemaCalculator externalSchemaCalculator = new ExternalSchemaCalculator

	def process(String query) {
		val cypher = CypherParser.parseFile("trainbenchmark/" + query)
		CypherUtil.save(cypher, "cypher-asts/trainbenchmark/" + query)
		val container = Cypher2Relalg.processCypher(cypher, "trainBenchmark" + query)
		container.calculateExternalSchema
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
