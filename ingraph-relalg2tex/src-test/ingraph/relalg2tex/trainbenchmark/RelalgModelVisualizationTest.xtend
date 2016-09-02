package ingraph.relalg2tex.trainbenchmark

import ingraph.cypher2relalg.RelalgParser
import ingraph.relalg2tex.AlgebraTreeDrawer
import ingraph.trainbenchmark.TrainBenchmarkUtil
import org.junit.Test

class RelalgModelVisualizationTest {

	val static AlgebraTreeDrawer drawer = new AlgebraTreeDrawer(true)

	@Test
	def void testPosLength() {
		val container = TrainBenchmarkUtil.posLength
		RelalgParser.save(container, "/tmp/poslength-handcrafted")
		print(drawer.serialize(container, "poslength-handcrafted"))
	}

	@Test
	def void testRouteSensor() {
		val container = TrainBenchmarkUtil.routeSensor
		RelalgParser.save(container, "/tmp/routesensor-handcrafted")
		print(drawer.serialize(container, "routesensor-handcrafted"))
	}

	@Test
	def void testSemaphoreNeighbor() {
		val container = TrainBenchmarkUtil.semaphoreNeighbor
		RelalgParser.save(container, "/tmp/semaphoreneighbor-handcrafted")
		print(drawer.serialize(container, "semaphoreneighbor-handcrafted"))
	}

	@Test
	def void testSwitchMonitored() {
		val container = TrainBenchmarkUtil.switchMonitored
		RelalgParser.save(container, "/tmp/switchmonitored-handcrafted")
		print(drawer.serialize(container, "switchmonitored-handcrafted"))
	}

	@Test
	def void testSwitchSet() {
		val container = TrainBenchmarkUtil.switchSet
		RelalgParser.save(container, "/tmp/switchset-handcrafted")
		print(drawer.serialize(container, "switchset-handcrafted"))
	}


}
