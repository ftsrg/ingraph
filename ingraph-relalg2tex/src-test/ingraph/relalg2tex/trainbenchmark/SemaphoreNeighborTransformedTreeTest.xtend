package ingraph.relalg2tex.trainbenchmark

import ingraph.trainbenchmark.SemaphoreNeighborQueryPlanFactory
import org.junit.Before
import org.junit.Test
import ingraph.relalg2tex.RelalgTreeSerializer

class SemaphoreNeighborTransformedTreeTest {

	val static RelalgTreeSerializer drawer = new RelalgTreeSerializer(true)
	var SemaphoreNeighborQueryPlanFactory f
	
	@Before
	def void init() {
		f = new SemaphoreNeighborQueryPlanFactory()	
	}

	@Test
	def void testRouteSensorA() {
		drawer.serialize(f.semaphoreNeighborA, "SemaphoreNeighborA")
	}

	@Test
	def void testRouteSensorB() {
		drawer.serialize(f.semaphoreNeighborB, "SemaphoreNeighborB")
	}

	@Test
	def void testRouteSensorC() {
		drawer.serialize(f.semaphoreNeighborC, "SemaphoreNeighborC")
	}

	@Test
	def void testRouteSensorD() {
		drawer.serialize(f.semaphoreNeighborD, "SemaphoreNeighborD")
	}

	@Test
	def void testRouteSensorE() {
		drawer.serialize(f.semaphoreNeighborE, "SemaphoreNeighborE")
	}

	@Test
	def void testRouteSensorF() {
		drawer.serialize(f.semaphoreNeighborF, "SemaphoreNeighborF")
	}

}
