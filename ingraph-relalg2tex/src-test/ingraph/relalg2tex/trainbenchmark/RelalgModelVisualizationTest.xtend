package ingraph.relalg2tex.trainbenchmark

import ingraph.relalg2tex.RelAlgTreeDrawer
import ingraph.trainbenchmark.TrainBenchmarkUtil
import org.junit.Test

class RelalgModelVisualizationTest {

	val static RelAlgTreeDrawer drawer = new RelAlgTreeDrawer(true)

	@Test
	def void testPosLength() {
		drawer.serialize(TrainBenchmarkUtil.posLength, "poslength-handcrafted")
	}

	@Test
	def void testRouteSensor() {
		drawer.serialize(TrainBenchmarkUtil.routeSensor, "routesensor-handcrafted")
	}

	@Test
	def void testSemaphoreNeighbor() {
		drawer.serialize(TrainBenchmarkUtil.semaphoreNeighbor, "semaphoreneighbor-handcrafted")
	}

	@Test
	def void testSwitchMonitored() {
		drawer.serialize(TrainBenchmarkUtil.switchMonitored, "switchmonitored-handcrafted")
	}

	@Test
	def void testSwitchSet() {
		drawer.serialize(TrainBenchmarkUtil.switchSet, "switchset-handcrafted")
	}

}
