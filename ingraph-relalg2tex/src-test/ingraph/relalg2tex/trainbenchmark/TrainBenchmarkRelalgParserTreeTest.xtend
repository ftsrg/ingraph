package ingraph.relalg2tex.trainbenchmark

import ingraph.cypher2relalg.Cypher2Relalg
import ingraph.relalg2tex.RelalgTreeSerializer
import org.junit.Test

class TrainBenchmarkRelalgParserTreeTest {

	val static RelalgTreeSerializer drawer = new RelalgTreeSerializer

	@Test
	def void testConnectedSegments() {
		val expression = Cypher2Relalg.processFile("trainbenchmark/ConnectedSegments")
		drawer.serialize(expression, "trainbenchmark-connectedsegments-relalgparser")
	}

	@Test
	def void testPosLength() {
		val expression = Cypher2Relalg.processFile("trainbenchmark/PosLength")
		drawer.serialize(expression, "trainbenchmark-poslength-relalgparser")
	}

	@Test
	def void testRouteSensor() {
		val expression = Cypher2Relalg.processFile("trainbenchmark/RouteSensor")
		drawer.serialize(expression, "trainbenchmark-routesensor-relalgparser")
	}

	@Test
	def void testSemaphoreNeighbor() {
		val expression = Cypher2Relalg.processFile("trainbenchmark/SemaphoreNeighbor")
		drawer.serialize(expression, "trainbenchmark-semaphoreneighbor-relalgparser")
	}

	@Test
	def void testSwitchMonitored() {
		val expression = Cypher2Relalg.processFile("trainbenchmark/SwitchMonitored")
		drawer.serialize(expression, "trainbenchmark-switchmonitored-relalgparser")
	}

	@Test
	def void testSwitchSet() {
		val expression = Cypher2Relalg.processFile("trainbenchmark/SwitchSet")
		drawer.serialize(expression, "trainbenchmark-switchset-relalgparser")
	}

}
