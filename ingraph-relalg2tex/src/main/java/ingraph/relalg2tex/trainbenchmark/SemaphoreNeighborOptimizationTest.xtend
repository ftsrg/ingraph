package ingraph.relalg2tex.trainbenchmark

import ingraph.optimization.transformations.Relalg2ReteTransformation
import ingraph.relalg2tex.RelalgTreeSerializer
import ingraph.trainbenchmark.SemaphoreNeighborQueryPlanFactory
import org.junit.Before
import org.junit.Test

class SemaphoreNeighborOptimizationTest {

	extension val RelalgTreeSerializer drawer = new RelalgTreeSerializer
	extension val Relalg2ReteTransformation transformation = new Relalg2ReteTransformation
	extension SemaphoreNeighborQueryPlanFactory factory

	@Before
	def void init() {
		factory = new SemaphoreNeighborQueryPlanFactory()
	}

	@Test
	def void testSemaphoreNeighborARelalg() {
		drawer.serialize(semaphoreNeighborA, "query-plan-SemaphoreNeighborA-Relalg")
	}

	@Test
	def void testSemaphoreNeighborARete() {
		drawer.serialize(semaphoreNeighborA.transformToRete, "query-plan-SemaphoreNeighborA-Rete")
	}

	@Test
	def void testSemaphoreNeighborBRelalg() {
		drawer.serialize(semaphoreNeighborB, "query-plan-SemaphoreNeighborB-Relalg")
	}

	@Test
	def void testSemaphoreNeighborBRete() {
		drawer.serialize(semaphoreNeighborB.transformToRete, "query-plan-SemaphoreNeighborB-Rete")
	}

	@Test
	def void testSemaphoreNeighborCRelalg() {
		drawer.serialize(semaphoreNeighborC, "query-plan-SemaphoreNeighborC-Relalg")
	}

	@Test
	def void testSemaphoreNeighborCRete() {
		drawer.serialize(semaphoreNeighborC.transformToRete, "query-plan-SemaphoreNeighborC-Rete")
	}

	@Test
	def void testSemaphoreNeighborDRelalg() {
		drawer.serialize(semaphoreNeighborD, "query-plan-SemaphoreNeighborD-Relalg")
	}

	@Test
	def void testSemaphoreNeighborDRete() {
		drawer.serialize(semaphoreNeighborD.transformToRete, "query-plan-SemaphoreNeighborD-Rete")
	}

	@Test
	def void testSemaphoreNeighborERelalg() {
		drawer.serialize(semaphoreNeighborE, "query-plan-SemaphoreNeighborE-Relalg")
	}

	@Test
	def void testSemaphoreNeighborERete() {
		drawer.serialize(semaphoreNeighborE.transformToRete, "query-plan-SemaphoreNeighborE-Rete")
	}

	@Test
	def void testSemaphoreNeighborFRelalg() {
		drawer.serialize(semaphoreNeighborF, "query-plan-SemaphoreNeighborF-Relalg")
	}

	@Test
	def void testSemaphoreNeighborFRete() {
		drawer.serialize(semaphoreNeighborF.transformToRete, "query-plan-SemaphoreNeighborF-Rete")
	}
}
