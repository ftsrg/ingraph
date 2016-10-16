package ingraph.relalg2tex.trainbenchmark

import ingraph.relalg2tex.RelalgTreeSerializer
import ingraph.trainbenchmark.RouteSensorQueryPlanFactory
import org.junit.Before
import org.junit.Test

class RouteSensorOptimizationTreeTest {

	val static RelalgTreeSerializer drawer = new RelalgTreeSerializer
	var RouteSensorQueryPlanFactory f
	
	@Before
	def void init() {
		f = new RouteSensorQueryPlanFactory()	
	}

	@Test
	def void testRouteSensorA() {
		drawer.serialize(f.routeSensorA, "RouteSensorA")
	}

	@Test
	def void testRouteSensorB() {
		drawer.serialize(f.routeSensorB, "RouteSensorB")
	}

	@Test
	def void testRouteSensorC() {
		drawer.serialize(f.routeSensorC, "RouteSensorC")
	}

}
