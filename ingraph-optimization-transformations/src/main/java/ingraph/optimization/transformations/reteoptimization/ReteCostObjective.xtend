package ingraph.optimization.transformations.reteoptimization

import org.eclipse.viatra.dse.objectives.impl.BaseObjective
import org.eclipse.viatra.dse.base.ThreadContext
import ingraph.optimization.transformations.cost.ReteCostFunction
import relalg.RelalgContainer

class ReteCostObjective extends BaseObjective {
	
	val reteCostFunction = new ReteCostFunction()
	
	new() {
		super("Rete cost objective")
	}
	
	override createNew() {
		return new ReteCostObjective()
	}
	
	override getFitness(ThreadContext context) {
		val container = context.model as RelalgContainer
		reteCostFunction.getEstimation(container)
	}
	
	
}