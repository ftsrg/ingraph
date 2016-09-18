package ingraph.relalg2tex.trainbenchmark

import ingraph.optimization.transformations.Transformation
import ingraph.trainbenchmark.TrainBenchmarkUtil
import org.junit.Test
import ingraph.relalg2tex.RelalgTreeSerializer
import ingraph.optimization.transformations.SchemaInferencer

class TrainBenchmarkHandcraftedTransformedTreeTest {

	val drawer = new RelalgTreeSerializer
	extension Transformation transformation = new Transformation
	extension SchemaInferencer schemaInferencer = new SchemaInferencer

	@Test
	def void testPosLength() {
		val container = TrainBenchmarkUtil.posLength
		container.transform.addSchemaInformation
		drawer.serialize(container, "trainbenchmark-poslength-handcrafted-transformation")
	}

	@Test
	def void testRouteSensor() {
		val container = TrainBenchmarkUtil.routeSensor
		container.transform.addSchemaInformation
		drawer.serialize(container, "trainbenchmark-routesensor-handcrafted-transformation")
	}

	@Test
	def void testSemaphoreNeighbor() {
		val container = TrainBenchmarkUtil.semaphoreNeighbor
		container.transform.addSchemaInformation
		drawer.serialize(container, "trainbenchmark-semaphoreneighbor-handcrafted-transformation")
	}

	@Test
	def void testSwitchMonitored() {
		val container = TrainBenchmarkUtil.switchMonitored
		container.transform.addSchemaInformation
		drawer.serialize(container, "trainbenchmark-switchmonitored-handcrafted-transformation")
	}

	@Test
	def void testSwitchSet() {
		val container = TrainBenchmarkUtil.switchSet
		container.transform.addSchemaInformation
		drawer.serialize(container, "trainbenchmark-switchset-handcrafted-transformation")
	}

}
