package ingraph.relalg2tex.trainbenchmark

import ingraph.optimization.transformations.Relalg2ReteTransformation
import ingraph.relalg.util.SchemaInferencer
import ingraph.relalg2tex.RelalgTreeSerializer
import ingraph.trainbenchmark.TrainBenchmarkUtil
import org.junit.Test

class TrainBenchmarkHandcraftedTransformedTreeTest {

	val drawer = new RelalgTreeSerializer
	extension Relalg2ReteTransformation transformation = new Relalg2ReteTransformation
	extension SchemaInferencer schemaInferencer = new SchemaInferencer

	@Test
	def void testPosLength() {
		val container = TrainBenchmarkUtil.posLength
		container.transformToRete.addSchemaInformation
		drawer.serialize(container, "trainbenchmark-poslength-handcrafted-transformation")
	}

	@Test
	def void testRouteSensor() {
		val container = TrainBenchmarkUtil.routeSensor
		container.transformToRete.addSchemaInformation
		drawer.serialize(container, "trainbenchmark-routesensor-handcrafted-transformation")
	}

	@Test
	def void testSemaphoreNeighbor() {
		val container = TrainBenchmarkUtil.semaphoreNeighbor
		container.transformToRete.addSchemaInformation
		drawer.serialize(container, "trainbenchmark-semaphoreneighbor-handcrafted-transformation")
	}

	@Test
	def void testSwitchMonitored() {
		val container = TrainBenchmarkUtil.switchMonitored
		container.transformToRete.addSchemaInformation
		drawer.serialize(container, "trainbenchmark-switchmonitored-handcrafted-transformation")
	}

	@Test
	def void testSwitchSet() {
		val container = TrainBenchmarkUtil.switchSet
		container.transformToRete.addSchemaInformation
		drawer.serialize(container, "trainbenchmark-switchset-handcrafted-transformation")
	}

}
