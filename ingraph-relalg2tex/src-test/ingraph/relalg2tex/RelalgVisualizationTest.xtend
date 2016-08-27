package ingraph.relalg2tex

import ingraph.trainbenchmark.TrainBenchmarkUtil
import org.junit.Test

class RelalgVisualizationTest {

	val static ExpressionSerializer serializer = new ExpressionSerializer(false, false)
	val static AlgebraTreeDrawer drawer = new AlgebraTreeDrawer(true)

	@Test
	def void testPosLength() {
		val expression = TrainBenchmarkUtil.posLength
		print(drawer.serialize(expression, "poslength"))
	}

	@Test
	def void testRouteSensor() {
		val expression = TrainBenchmarkUtil.routeSensor
		print(drawer.serialize(expression, "routesensor"))
	}

	@Test
	def void testSemaphoreNeighbor() {
		val expression = TrainBenchmarkUtil.semaphoreNeighbor
		print(drawer.serialize(expression, "semaphoreneighbor"))
	}

	@Test
	def void testSwitchMonitored() {
		val expression = TrainBenchmarkUtil.switchMonitored
		print(drawer.serialize(expression, "switchmonitored"))
	}

	@Test
	def void testSwitchSet() {
		val expression = TrainBenchmarkUtil.switchSet
		print(drawer.serialize(expression, "switchset"))
	}


}
