package ingraph.optimization.transformations.reteoptimization

import relalg.AllDifferentOperator
import relalg.AntiJoinOperator
import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
import relalg.JoinOperator
import relalg.LeftOuterJoinOperator
import relalg.ProductionOperator
import relalg.ProjectionOperator
import relalg.RelalgContainer
import relalg.RelalgFactory
import relalg.SelectionOperator
import relalg.UnionOperator

class ReteCostFunction {

	val extension RelalgFactory factory = RelalgFactory.eINSTANCE

	def estimateCost(RelalgContainer container) {
		estimate(container.rootExpression)
		return container
	}

	def getEstimation(RelalgContainer container) {
		estimateCost(container)
		container.rootExpression.cardinality.value
	}

	/**
	 * Nullary operators
	 */
	def dispatch Double estimate(GetVerticesOperator op) {
		op.cardinality = createCardinality => [
			value = 10.0
		]
		op.cardinality.value
	}

	def dispatch Double estimate(GetEdgesOperator op) {
		op.cardinality = createCardinality => [
			value = 5.0
		]
		op.cardinality.value
	}

	/**
	 * Unary operators
	 */
	def dispatch Double estimate(AllDifferentOperator op) {
		estimate(op.input)
	}

	def dispatch Double estimate(SelectionOperator op) {
		val inputEstimation = estimate(op.input)
		val currentEstimation = inputEstimation / 10	
		
		op.cardinality = createCardinality => [
			value = currentEstimation
		]
		op.cardinality.value
	}

	def dispatch Double estimate(ProductionOperator op) {
		estimate(op.input)
		
		op.cardinality = createCardinality => [
			value = op.input.cardinality.value
		]
		op.cardinality.value
	}

	def dispatch Double estimate(ProjectionOperator op) {
		estimate(op.input)
		
		op.cardinality = createCardinality => [
			value = op.input.cardinality.value
		]
		op.cardinality.value
	}

	/**
	 * Binary operators
	 */
	def dispatch Double estimate(AntiJoinOperator op) {
		estimate(op.leftInput)
		estimate(op.rightInput)
		
		op.cardinality = createCardinality => [
			value = op.leftInput.cardinality.value 
		]
		op.cardinality.value
	}

	def dispatch Double estimate(JoinOperator op) {
		estimate(op.leftInput)
		estimate(op.rightInput)
		
		op.cardinality = createCardinality => [
			value = op.leftInput.cardinality.value * op.rightInput.cardinality.value / 100 
		]
		op.cardinality.value
	}

	def dispatch Double estimate(LeftOuterJoinOperator op) {
		estimate(op.leftInput)
		estimate(op.rightInput)
		
		op.cardinality = createCardinality => [
			value = op.leftInput.cardinality.value 
		]
		op.cardinality.value
	}
	
	def dispatch Double estimate(UnionOperator op) {
		estimate(op.leftInput)
		estimate(op.rightInput)
		
		// for the sake of simplicity, we add the number of elements
		op.cardinality = createCardinality => [
			value = op.leftInput.cardinality.value + op.rightInput.cardinality.value 
		]
		op.cardinality.value
	}

}