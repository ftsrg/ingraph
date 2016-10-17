package ingraph.cypher2relalg.trainbenchmark

import java.io.IOException
import org.junit.Test
import ingraph.cypher2relalg.CypherParser

class RelalgParserTrainBenchmarkTest {
	
	@Test
	def void testConnectedSegments() throws IOException {
		CypherParser.parseFile("trainbenchmark/ConnectedSegments")
	}

	@Test
	def void testPosLength() throws IOException {
		CypherParser.parseFile("trainbenchmark/PosLength")
	}

	@Test
	def void testRouteSensor() throws IOException {
		CypherParser.parseFile("trainbenchmark/RouteSensor")
	}

	@Test
	def void testSwitchMonitored() throws IOException {
		CypherParser.parseFile("trainbenchmark/SwitchMonitored")
	}

	@Test
	def void testSwitchSet() throws IOException {
		CypherParser.parseFile("trainbenchmark/SwitchSet")
	}

	@Test
	def void testSemaphoreNeighbor() throws IOException {
		CypherParser.parseFile("trainbenchmark/SemaphoreNeighbor")
	}
}