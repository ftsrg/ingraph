package ingraph.relalg2tex.trainbenchmark

import ingraph.optimization.transformations.SchemaInferencer
import ingraph.relalg2tex.RelalgTreeSerializer
import ingraph.trainbenchmark.TrainBenchmarkUtil
import org.junit.Test

class TrainBenchmarkHandcraftedTreeTest {

	val RelalgTreeSerializer serializer = new RelalgTreeSerializer
	extension SchemaInferencer schemaInferencer = new SchemaInferencer

	@Test
	def void testPosLength() {
		serializer.serialize(TrainBenchmarkUtil.posLength.addSchemaInformation, "trainbenchmark-poslength-handcrafted")
	}

	@Test
	def void testRouteSensor() {
		serializer.serialize(TrainBenchmarkUtil.routeSensor.addSchemaInformation,
			"trainbenchmark-routesensor-handcrafted")
	}

	@Test
	def void testSemaphoreNeighbor() {
		serializer.serialize(TrainBenchmarkUtil.semaphoreNeighbor.addSchemaInformation,
			"trainbenchmark-semaphoreneighbor-handcrafted")
	}

	@Test
	def void testSwitchMonitored() {
		serializer.serialize(TrainBenchmarkUtil.switchMonitored.addSchemaInformation,
			"trainbenchmark-switchmonitored-handcrafted")
	}

	@Test
	def void testSwitchSet() {
		serializer.serialize(TrainBenchmarkUtil.switchSet.addSchemaInformation, "trainbenchmark-switchset-handcrafted")
	}
}
