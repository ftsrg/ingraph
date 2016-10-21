package ingraph.relalg2tex.trainbenchmark.cost

import ingraph.optimization.transformations.Relalg2ReteTransformation
import ingraph.optimization.transformations.cost.ReteCostFunction
import ingraph.relalg2tex.RelalgTreeSerializer
import ingraph.trainbenchmark.RouteSensorQueryPlanFactory
import ingraph.trainbenchmark.SemaphoreNeighborQueryPlanFactory
import org.junit.Test

class CostTest {
	
	val extension RelalgTreeSerializer drawer = new RelalgTreeSerializer
	val extension ReteCostFunction function = new ReteCostFunction
	val extension RouteSensorQueryPlanFactory routesensorFactory = new RouteSensorQueryPlanFactory
	val extension SemaphoreNeighborQueryPlanFactory semaphoreNeighborFactory = new SemaphoreNeighborQueryPlanFactory
	val extension Relalg2ReteTransformation transformation = new Relalg2ReteTransformation
	
	@Test
	def void costRouteSensorA() {
		drawer.serialize(routeSensorA.transformToRete.estimateCost, "query-plans/RouteSensorA-Rete-cost")
	}
	
	@Test
	def void costSemaphoreNeighborA() {
		drawer.serialize(semaphoreNeighborA.transformToRete.estimateCost, "query-plans/SemaphoreNeighborA-Rete-cost")
	}
	
}