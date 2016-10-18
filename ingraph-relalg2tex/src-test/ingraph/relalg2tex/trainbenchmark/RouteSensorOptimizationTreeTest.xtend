package ingraph.relalg2tex.trainbenchmark

import ingraph.relalg2tex.RelalgTreeSerializer
import ingraph.trainbenchmark.RouteSensorQueryPlanFactory
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import ingraph.optimization.transformations.Relalg2ReteTransformation

class RouteSensorOptimizationTreeTest {

	val static RelalgTreeSerializer drawer = new RelalgTreeSerializer
	val extension Relalg2ReteTransformation transformation = new Relalg2ReteTransformation
	extension RouteSensorQueryPlanFactory factory
	
	@Before
	def void init() {
		factory = new RouteSensorQueryPlanFactory()	
	}

	@Test
	def void testRouteSensorA() {
		drawer.serialize(routeSensorA, "query-plan-RouteSensorA")
	}

	@Test
	def void testRouteSensorARete() {
		drawer.serialize(routeSensorA.transformToRete, "query-plan-RouteSensorARete")
	}


	@Ignore
	@Test
	def void testRouteSensorB() {
		drawer.serialize(routeSensorB, "query-plan-RouteSensorB")
	}

	@Ignore
	@Test
	def void testRouteSensorC() {
		drawer.serialize(routeSensorC, "query-plan-RouteSensorC")
	}

}
