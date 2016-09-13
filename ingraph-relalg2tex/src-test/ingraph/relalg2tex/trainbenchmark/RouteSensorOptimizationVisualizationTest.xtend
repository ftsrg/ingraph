package ingraph.relalg2tex.trainbenchmark

import ingraph.trainbenchmark.RouteSensorQueryPlanFactory
import org.junit.Test
import org.junit.Before
import ingraph.relalg2tex.RelAlgTreeDrawer

class RouteSensorOptimizationVisualizationTest {

	val static RelAlgTreeDrawer drawer = new RelAlgTreeDrawer(true)
	var RouteSensorQueryPlanFactory f
	
	@Before
	def void init() {
		f = new RouteSensorQueryPlanFactory()	
	}

	@Test
	def void testRouteSensorA() {
		print(drawer.serialize(f.routeSensorA, "RouteSensorA"))
	}

	@Test
	def void testRouteSensorB() {
		print(drawer.serialize(f.routeSensorB, "RouteSensorB"))
	}

	@Test
	def void testRouteSensorC() {
		print(drawer.serialize(f.routeSensorC, "RouteSensorC"))
	}

}
