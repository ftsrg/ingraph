package ingraph.cypher2relalg.trainbenchmark

import ingraph.cypher2relalg.RelalgParser
import java.io.IOException
import org.junit.Test

class RelalgParserTrainBenchmarkTest {
	
	@Test
	def void testConnectedSegments() throws IOException {
		RelalgParser.parseFile("trainbenchmark/ConnectedSegments")
	}

	@Test
	def void testPosLength() throws IOException {
		RelalgParser.parseFile("trainbenchmark/PosLength")
	}

	@Test
	def void testRouteSensor() throws IOException {
		RelalgParser.parseFile("trainbenchmark/RouteSensor")
	}

	@Test
	def void testSwitchMonitored() throws IOException {
		RelalgParser.parseFile("trainbenchmark/SwitchMonitored")
	}

	@Test
	def void testSwitchSet() throws IOException {
		RelalgParser.parseFile("trainbenchmark/SwitchSet")
	}

	@Test
	def void testSemaphoreNeighbor() throws IOException {
		RelalgParser.parseFile("trainbenchmark/SemaphoreNeighbor")
	}
}