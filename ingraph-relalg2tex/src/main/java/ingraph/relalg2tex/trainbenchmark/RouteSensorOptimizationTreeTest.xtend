package ingraph.relalg2tex.trainbenchmark

import ingraph.optimization.transformations.Relalg2ReteTransformation
import ingraph.relalg.util.RelalgUtil
import ingraph.relalg.util.SchemaInferencer
import ingraph.relalg2tex.RelalgTreeSerializer
import ingraph.trainbenchmark.RouteSensorQueryPlanFactory
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

class RouteSensorOptimizationTreeTest {

	val static RelalgTreeSerializer drawer = new RelalgTreeSerializer
	val extension Relalg2ReteTransformation transformation = new Relalg2ReteTransformation
	extension SchemaInferencer schemaInferencer = new SchemaInferencer
	extension RouteSensorQueryPlanFactory factory

	@Before
	def void init() {
		factory = new RouteSensorQueryPlanFactory()
	}

	@Ignore
	@Test
	def void serializationTest() {
		RelalgUtil.save(routeSensorA, "/tmp/query-plan-RouteSensorA-Relalg")
	}

	@Test
	def void testRouteSensorARelalg() {
		drawer.serialize(routeSensorA, "query-plan-RouteSensorA-Relalg")
	}

	@Test
	def void testRouteSensorARete() {
		drawer.serialize(routeSensorA.transformToRete, "query-plan-RouteSensorA-Rete")
	}

	@Test
	def void testRouteSensorBRelalg() {
		drawer.serialize(routeSensorB, "query-plan-RouteSensorB-Relalg")
	}

	@Test
	def void testRouteSensorBRete() {
		drawer.serialize(routeSensorB.transformToRete, "query-plan-RouteSensorB-Rete")
	}

	@Test
	def void testRouteSensorCRelalg() {
		drawer.serialize(routeSensorC, "query-plan-RouteSensorC-Relalg")
	}

	@Test
	def void testRouteSensorCRete() {
		drawer.serialize(routeSensorC.transformToRete, "query-plan-RouteSensorC-Rete")
	}

}
