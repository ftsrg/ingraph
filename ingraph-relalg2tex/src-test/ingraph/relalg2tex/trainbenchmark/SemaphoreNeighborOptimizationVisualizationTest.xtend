package ingraph.relalg2tex.trainbenchmark

import ingraph.relalg2tex.AlgebraTreeDrawer
import ingraph.trainbenchmark.SemaphoreNeighborQueryPlanFactory
import org.junit.Before
import org.junit.Test

class SemaphoreNeighborOptimizationVisualizationTest {

	val static AlgebraTreeDrawer drawer = new AlgebraTreeDrawer(true)
	var SemaphoreNeighborQueryPlanFactory f
	
	@Before
	def void init() {
		f = new SemaphoreNeighborQueryPlanFactory()	
	}

	@Test
	def void testRouteSensorA() {
		print(drawer.serialize(f.semaphoreNeighborA, "SemaphoreNeighborA"))
	}

	@Test
	def void testRouteSensorB() {
		print(drawer.serialize(f.semaphoreNeighborB, "SemaphoreNeighborB"))
	}

	@Test
	def void testRouteSensorC() {
		print(drawer.serialize(f.semaphoreNeighborC, "SemaphoreNeighborC"))
	}

	@Test
	def void testRouteSensorD() {
		print(drawer.serialize(f.semaphoreNeighborD, "SemaphoreNeighborD"))
	}

	@Test
	def void testRouteSensorE() {
		print(drawer.serialize(f.semaphoreNeighborE, "SemaphoreNeighborE"))
	}

	@Test
	def void testRouteSensorF() {
		print(drawer.serialize(f.semaphoreNeighborF, "SemaphoreNeighborF"))
	}

}
