package ingraph.relalg2tex.trainbenchmark

import ingraph.optimization.transformations.RelAlg2ReteTransformation
import ingraph.relalg2tex.RelalgTreeSerializer
import ingraph.trainbenchmark.RouteSensorQueryPlanFactory
import org.junit.Before
import org.junit.Test

class RouteSensorOptimizationTreeTest {

	val static RelalgTreeSerializer drawer = new RelalgTreeSerializer
	val extension RelAlg2ReteTransformation transformation = new RelAlg2ReteTransformation
	extension RouteSensorQueryPlanFactory factory
	
	@Before
	def void init() {
		factory = new RouteSensorQueryPlanFactory()	
	}

	@Test
	def void testRouteSensorARelAlg() {
		drawer.serialize(routeSensorA, "query-plan-RouteSensorA-RelAlg")
	}

	@Test
	def void testRouteSensorARete() {
		drawer.serialize(routeSensorA.transformToRete, "query-plan-RouteSensorA-Rete")
	}


	@Test
	def void testRouteSensorBRelAlg() {
		drawer.serialize(routeSensorB, "query-plan-RouteSensorB-RelAlg")
	}

	@Test
	def void testRouteSensorBRete() {
		drawer.serialize(routeSensorB.transformToRete, "query-plan-RouteSensorB-Rete")
	}

	@Test
	def void testRouteSensorCRelAlg() {
		drawer.serialize(routeSensorC, "query-plan-RouteSensorC-RelAlg")
	}
	
	@Test
	def void testRouteSensorCRete() {
		drawer.serialize(routeSensorC.transformToRete, "query-plan-RouteSensorC-Rete")
	}

}
