package ingraph.relalg2tex.trainbenchmark

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.AlgebraTreeDrawer
import ingraph.relalg2tex.ExpressionSerializer
import org.junit.Test

class QueryVisualizationTest {

	val static AlgebraTreeDrawer drawer = new AlgebraTreeDrawer(true)

	@Test
	def void testConnectedSegments() {
		val expression = RelalgParser.parseFile("trainbenchmark/ConnectedSegments")
		print(drawer.serialize(expression, "connectedsegments"))
	}

	@Test
	def void testPosLength() {
		val expression = RelalgParser.parseFile("trainbenchmark/PosLength")
		print(drawer.serialize(expression, "poslength"))
	}

	@Test
	def void testRouteSensor() {
		val expression = RelalgParser.parseFile("trainbenchmark/RouteSensor")
		print(drawer.serialize(expression, "routesensor"))
	}

	@Test
	def void testSemaphoreNeighbor() {
		val expression = RelalgParser.parseFile("trainbenchmark/SemaphoreNeighbor")
		print(drawer.serialize(expression, "semaphoreneighbor"))
	}

	@Test
	def void testSwitchMonitored() {
		val expression = RelalgParser.parseFile("trainbenchmark/SwitchMonitored")
		print(drawer.serialize(expression, "switchmonitored"))
	}

	@Test
	def void testSwitchSet() {
		val expression = RelalgParser.parseFile("trainbenchmark/SwitchSet")
		print(drawer.serialize(expression, "switchset"))
	}

}
