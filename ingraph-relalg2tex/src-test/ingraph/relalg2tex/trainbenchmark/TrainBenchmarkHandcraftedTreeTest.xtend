package ingraph.relalg2tex.trainbenchmark

import ingraph.trainbenchmark.TrainBenchmarkUtil
import org.junit.Test
import ingraph.relalg2tex.RelalgTreeSerializer

class TrainBenchmarkHandcraftedTreeTest {

	val RelalgTreeSerializer serializer = new RelalgTreeSerializer

	@Test
	def void testPosLength() {
		serializer.serialize(TrainBenchmarkUtil.posLength, "trainbenchmark-poslength-handcrafted")
	}

	@Test
	def void testRouteSensor() {
		serializer.serialize(TrainBenchmarkUtil.routeSensor, "trainbenchmark-routesensor-handcrafted")
	}

	@Test
	def void testSemaphoreNeighbor() {
		serializer.serialize(TrainBenchmarkUtil.semaphoreNeighbor, "trainbenchmark-semaphoreneighbor-handcrafted")
	}

	@Test
	def void testSwitchMonitored() {
		serializer.serialize(TrainBenchmarkUtil.switchMonitored, "trainbenchmark-switchmonitored-handcrafted")
	}

	@Test
	def void testSwitchSet() {
		serializer.serialize(TrainBenchmarkUtil.switchSet, "trainbenchmark-switchset-handcrafted")
	}

}
