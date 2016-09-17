package ingraph.relalg2tex.trainbenchmark

import ingraph.cypher2relalg.RelalgParser
import org.junit.Test
import ingraph.relalg2tex.RelalgTreeDrawer

class TrainBenchmarkQueryPlanVisualizationTest {

	val static RelalgTreeDrawer drawer = new RelalgTreeDrawer(true)

	@Test
	def void testConnectedSegments() {
		val expression = RelalgParser.parseFile("trainbenchmark/ConnectedSegments")
		drawer.serialize(expression, "connectedsegments")
	}

	@Test
	def void testPosLength() {
		val expression = RelalgParser.parseFile("trainbenchmark/PosLength")
		drawer.serialize(expression, "poslength")
	}

	@Test
	def void testRouteSensor() {
		val expression = RelalgParser.parseFile("trainbenchmark/RouteSensor")
		drawer.serialize(expression, "routesensor")
	}

	@Test
	def void testSemaphoreNeighbor() {
		val expression = RelalgParser.parseFile("trainbenchmark/SemaphoreNeighbor")
		drawer.serialize(expression, "semaphoreneighbor")
	}

	@Test
	def void testSwitchMonitored() {
		val expression = RelalgParser.parseFile("trainbenchmark/SwitchMonitored")
		drawer.serialize(expression, "switchmonitored")
	}

	@Test
	def void testSwitchSet() {
		val expression = RelalgParser.parseFile("trainbenchmark/SwitchSet")
		drawer.serialize(expression, "switchset")
	}

}
