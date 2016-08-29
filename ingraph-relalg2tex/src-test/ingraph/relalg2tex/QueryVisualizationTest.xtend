package ingraph.relalg2tex

import ingraph.cypher2relalg.RelalgParser
import org.junit.Test

class QueryVisualizationTest {

	val static ExpressionSerializer serializer = new ExpressionSerializer(false, false)
	val static AlgebraTreeDrawer drawer = new AlgebraTreeDrawer(true)

	@Test
	def void testConnectedSegments() {
		val expression = RelalgParser.parse("ConnectedSegments")
		print(drawer.serialize(expression, "connectedsegments"))
	}

	@Test
	def void testPosLength() {
		val expression = RelalgParser.parse("PosLength")
		print(drawer.serialize(expression, "poslength"))
	}

	@Test
	def void testRouteSensor() {
		val expression = RelalgParser.parse("RouteSensor")
		print(drawer.serialize(expression, "routesensor"))
	}

	@Test
	def void testSemaphoreNeighbor() {
		val expression = RelalgParser.parse("SemaphoreNeighbor")
		print(drawer.serialize(expression, "semaphoreneighbor"))
	}

	@Test
	def void testSwitchMonitored() {
		val expression = RelalgParser.parse("SwitchMonitored")
		print(drawer.serialize(expression, "switchmonitored"))
	}

	@Test
	def void testSwitchSet() {
		val expression = RelalgParser.parse("SwitchSet")
		print(drawer.serialize(expression, "switchset"))
	}


}
