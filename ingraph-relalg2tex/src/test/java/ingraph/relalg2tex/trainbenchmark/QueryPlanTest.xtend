package ingraph.relalg2tex.trainbenchmark

import ingraph.emf.util.PrettyPrinter
import ingraph.optimization.transformations.Relalg2ReteTransformation
import ingraph.relalg2tex.RelalgTreeSerializer
import ingraph.trainbenchmark.RouteSensorQueryPlanFactory
import ingraph.trainbenchmark.SemaphoreNeighborQueryPlanFactory
import org.junit.Test

class QueryPlanTest {

	val extension RelalgTreeSerializer drawer = new RelalgTreeSerializer
	val extension Relalg2ReteTransformation transformation = new Relalg2ReteTransformation
	val extension RouteSensorQueryPlanFactory routeSensorFactory = new RouteSensorQueryPlanFactory
	val extension SemaphoreNeighborQueryPlanFactory semaphoreNeighborFactory = new SemaphoreNeighborQueryPlanFactory()	

	// RouteSensor

	@Test
	def void testRouteSensorARelalg() {
		drawer.serialize(routeSensorA, "query-plans/RouteSensorA-Relalg")
	}

	@Test
	def void testRouteSensorARete() {
		drawer.serialize(routeSensorA.transformToRete, "query-plans/RouteSensorA-Rete")
	}

	@Test
	def void testRouteSensorBRelalg() {
		drawer.serialize(routeSensorB, "query-plans/RouteSensorB-Relalg")
	}

	@Test
	def void testRouteSensorBRete() {
		drawer.serialize(routeSensorB.transformToRete, "query-plans/RouteSensorB-Rete")
	}

	@Test
	def void testRouteSensorCRelalg() {
		drawer.serialize(routeSensorC, "query-plans/RouteSensorC-Relalg")
	}

	@Test
	def void testRouteSensorCRete() {
		drawer.serialize(routeSensorC.transformToRete, "query-plans/RouteSensorC-Rete")
	}
	
	// SemaphoreNeighbor
	
	@Test
	def void testSemaphoreNeighborARelalg() {
		drawer.serialize(semaphoreNeighborA, "query-plans/SemaphoreNeighborA-Relalg")
	}

	@Test
	def void testSemaphoreNeighborARete() {
		drawer.serialize(semaphoreNeighborA.transformToRete, "query-plans/SemaphoreNeighborA-Rete")
	}

	@Test
	def void testSemaphoreNeighborBRelalg() {
		drawer.serialize(semaphoreNeighborB, "query-plans/SemaphoreNeighborB-Relalg")
	}

	@Test
	def void testSemaphoreNeighborBRete() {
		drawer.serialize(semaphoreNeighborB.transformToRete, "query-plans/SemaphoreNeighborB-Rete")
	}

	@Test
	def void testSemaphoreNeighborCRelalg() {
		drawer.serialize(semaphoreNeighborC, "query-plans/SemaphoreNeighborC-Relalg")
	}

	@Test
	def void testSemaphoreNeighborCRete() {
		drawer.serialize(semaphoreNeighborC.transformToRete, "query-plans/SemaphoreNeighborC-Rete")
	}

	@Test
	def void testSemaphoreNeighborDRelalg() {
		drawer.serialize(semaphoreNeighborD, "query-plans/SemaphoreNeighborD-Relalg")
	}

	@Test
	def void testSemaphoreNeighborDRete() {
		drawer.serialize(semaphoreNeighborD.transformToRete, "query-plans/SemaphoreNeighborD-Rete")
	}

	@Test
	def void testSemaphoreNeighborERelalg() {
		drawer.serialize(semaphoreNeighborE, "query-plans/SemaphoreNeighborE-Relalg")
	}

	@Test
	def void testSemaphoreNeighborERete() {
		drawer.serialize(semaphoreNeighborE.transformToRete, "query-plans/SemaphoreNeighborE-Rete")
	}

	@Test
	def void testSemaphoreNeighborFRelalg() {
		drawer.serialize(semaphoreNeighborF, "query-plans/SemaphoreNeighborF-Relalg")
	}

	@Test
	def void testSemaphoreNeighborFRete() {
		drawer.serialize(semaphoreNeighborF.transformToRete, "query-plans/SemaphoreNeighborF-Rete")
	}

}
