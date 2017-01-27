package ingraph.cypher2relalg.trainbenchmark

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.cypherparser.CypherParser
import ingraph.cypherparser.CypherUtil
import ingraph.relalg.inferencers.SchemaInferencer
import java.io.IOException
import org.junit.Test

class TrainBenchmarkCypher2RelalgTest {

	def process(String query) {
		val cypher = CypherParser.parseFile("trainbenchmark/" + query)
		CypherUtil.save(cypher, "../ingraph-cypxmi/trainbenchmark/" + query)
		val relalg = Cypher2Relalg.processCypher(cypher)
		val si = new SchemaInferencer
		si.inferSchema(relalg.rootExpression)
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
