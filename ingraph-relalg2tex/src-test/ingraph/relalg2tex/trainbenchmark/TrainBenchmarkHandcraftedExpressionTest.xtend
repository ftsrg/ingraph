package ingraph.relalg2tex.trainbenchmark

import ingraph.relalg2tex.RelalgExpressionSerializer
import ingraph.trainbenchmark.TrainBenchmarkUtil
import org.junit.Test

class TrainBenchmarkHandcraftedExpressionTest {

	val RelalgExpressionSerializer serializer = new RelalgExpressionSerializer(true, false)

	@Test
	def void testPosLength() {
		serializer.serialize(TrainBenchmarkUtil.posLength, "trainbenchmark-poslength-handcrafted-expression")
	}

	@Test
	def void testRouteSensor() {
		serializer.serialize(TrainBenchmarkUtil.routeSensor, "trainbenchmark-routesensor-handcrafted-expression")
	}

	@Test
	def void testSemaphoreNeighbor() {
		serializer.serialize(TrainBenchmarkUtil.semaphoreNeighbor, "trainbenchmark-semaphoreneighbor-handcrafted-expression")
	}

	@Test
	def void testSwitchMonitored() {
		serializer.serialize(TrainBenchmarkUtil.switchMonitored, "trainbenchmark-switchmonitored-handcrafted-expression")
	}

	@Test
	def void testSwitchSet() {
		serializer.serialize(TrainBenchmarkUtil.switchSet, "trainbenchmark-switchset-handcrafted-expression")
	}

}
