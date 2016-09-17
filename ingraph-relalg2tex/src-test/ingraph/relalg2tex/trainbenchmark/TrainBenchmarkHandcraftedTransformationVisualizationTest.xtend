package ingraph.relalg2tex.trainbenchmark

import ingraph.optimization.transformations.Transformation
import ingraph.relalg2tex.RelalgTreeDrawer
import ingraph.trainbenchmark.TrainBenchmarkUtil
import org.junit.Test

class TrainBenchmarkHandcraftedTransformationVisualizationTest {

	val RelalgTreeDrawer drawer = new RelalgTreeDrawer(true)
	val Transformation transformation = new Transformation

	@Test
	def void testPosLength() {
		val container = TrainBenchmarkUtil.posLength
		transformation.transform(container)
		drawer.serialize(container, "trainbenchmark-poslength-handcrafted-transformation")
	}

	@Test
	def void testRouteSensor() {
		val container = TrainBenchmarkUtil.routeSensor
		transformation.transform(container)
		drawer.serialize(container, "trainbenchmark-routesensor-handcrafted-transformation")
	}

	@Test
	def void testSemaphoreNeighbor() {
		val container = TrainBenchmarkUtil.semaphoreNeighbor
		transformation.transform(container)
		drawer.serialize(container, "trainbenchmark-semaphoreneighbor-handcrafted-transformation")
	}

	@Test
	def void testSwitchMonitored() {
		val container = TrainBenchmarkUtil.switchMonitored
		transformation.transform(container)
		drawer.serialize(container, "trainbenchmark-switchmonitored-handcrafted-transformation")
	}

	@Test
	def void testSwitchSet() {
		val container = TrainBenchmarkUtil.switchSet
		transformation.transform(container)
		drawer.serialize(container, "trainbenchmark-switchset-handcrafted-transformation")
	}

}
