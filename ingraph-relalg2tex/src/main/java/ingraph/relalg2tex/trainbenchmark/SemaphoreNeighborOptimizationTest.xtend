package ingraph.relalg2tex.trainbenchmark

import ingraph.optimization.transformations.Relalg2ReteTransformation
import ingraph.relalg.util.RelalgUtil
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
	def void serializationTest() {
		RelalgUtil.save(semaphoreNeighborA, "/tmp/plan")
	}

}
