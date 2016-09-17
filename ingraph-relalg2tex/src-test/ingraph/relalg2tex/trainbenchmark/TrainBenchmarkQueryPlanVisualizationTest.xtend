package ingraph.relalg2tex.trainbenchmark

import ingraph.cypher2relalg.RelalgParser
import org.junit.Test
import ingraph.relalg2tex.RelalgTreeDrawer

class TrainBenchmarkQueryPlanVisualizationTest {

	val static RelalgTreeDrawer drawer = new RelalgTreeDrawer(true)

	@Test
	def void testConnectedSegments() {
		val expression = RelalgParser.parseFile("trainbenchmark/ConnectedSegments")
		drawer.serialize(expression, "trainbenchmark-connectedsegments-relalgparser")
	}

	@Test
	def void testPosLength() {
		val expression = RelalgParser.parseFile("trainbenchmark/PosLength")
		drawer.serialize(expression, "trainbenchmark-poslength-relalgparser")
	}

	@Test
	def void testRouteSensor() {
		val expression = RelalgParser.parseFile("trainbenchmark/RouteSensor")
		drawer.serialize(expression, "trainbenchmark-routesensor-relalgparser")
	}

	@Test
	def void testSemaphoreNeighbor() {
		val expression = RelalgParser.parseFile("trainbenchmark/SemaphoreNeighbor")
		drawer.serialize(expression, "trainbenchmark-semaphoreneighbor-relalgparser")
	}

	@Test
	def void testSwitchMonitored() {
		val expression = RelalgParser.parseFile("trainbenchmark/SwitchMonitored")
		drawer.serialize(expression, "trainbenchmark-switchmonitored-relalgparser")
	}

	@Test
	def void testSwitchSet() {
		val expression = RelalgParser.parseFile("trainbenchmark/SwitchSet")
		drawer.serialize(expression, "trainbenchmark-switchset-relalgparser")
	}

}
