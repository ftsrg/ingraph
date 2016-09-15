package ingraph.relalg2tex.trainbenchmark

import ingraph.relalg2tex.RelAlgTreeDrawer
import ingraph.trainbenchmark.TrainBenchmarkUtil
import org.junit.Test

class TrainBenchmarkHandcraftedVisualizationTest {

	val static RelAlgTreeDrawer drawer = new RelAlgTreeDrawer(true)

	@Test
	def void testPosLength() {
		drawer.serialize(TrainBenchmarkUtil.posLength, "trainbenchmark-poslength-handcrafted")
	}

	@Test
	def void testRouteSensor() {
		drawer.serialize(TrainBenchmarkUtil.routeSensor, "trainbenchmark-routesensor-handcrafted")
	}

	@Test
	def void testSemaphoreNeighbor() {
		drawer.serialize(TrainBenchmarkUtil.semaphoreNeighbor, "trainbenchmark-semaphoreneighbor-handcrafted")
	}

	@Test
	def void testSwitchMonitored() {
		drawer.serialize(TrainBenchmarkUtil.switchMonitored, "trainbenchmark-switchmonitored-handcrafted")
	}

	@Test
	def void testSwitchSet() {
		drawer.serialize(TrainBenchmarkUtil.switchSet, "trainbenchmark-switchset-handcrafted")
	}

}
