package ingraph.relalg2tex.trainbenchmark.optimization

import ingraph.optimization.transformations.cost.ReteCostFunction
import ingraph.relalg2tex.RelalgTreeSerializer
import org.junit.Test
import relalg.BinaryLogicalOperator
import relalg.RelalgFactory
import ingraph.optimization.transformations.ReteOptimization

class BasicOptimizationTest {

	val extension RelalgTreeSerializer drawer = new RelalgTreeSerializer(true)
	val extension ReteCostFunction function = new ReteCostFunction
	val extension RelalgFactory factory = RelalgFactory.eINSTANCE
	val extension ReteOptimization optimization = new ReteOptimization

//	val extension Relalg transformation = new 
	@Test
	def void test1() {
		// arrange
		val c = createRelalgContainer
		val l = createBooleanLiteral => [value = true; container = c]
		val r = createBooleanLiteral => [value = false; container = c]
		val condition = createBinaryLogicalExpression => [
			operator = BinaryLogicalOperator.AND
			leftOperand = l
			rightOperand = r
			container = c
		]
		val selectionOperator = createSelectionOperator => [
			it.condition = condition
			container = c
		]
		val productionOperator = createProductionOperator => [
			input = selectionOperator
			container = c
		]
		c.rootExpression = productionOperator

		println(c.serialize)

		// act
		c.optimize()
		
		println(c.serialize)
	}

}
