package ingraph.relalg2tex.trainbenchmark.optimization

import relalg.BinaryLogicalOperator
import relalg.RelalgFactory

class TestModelFactory {
	
	val extension RelalgFactory factory = RelalgFactory.eINSTANCE
	
	public def testModel1() {
		val ctr = createRelalgContainer
		
		val l = createBooleanLiteral => [value = true; container = ctr]
		val r = createBooleanLiteral => [value = false; container = ctr]
		val condition = createBinaryLogicalExpression => [
			operator = BinaryLogicalOperator.AND
			leftOperand = l
			rightOperand = r
			container = ctr
		]
		val selectionOperator = createSelectionOperator => [
			it.condition = condition
			container = ctr
		]
		val productionOperator = createProductionOperator => [
			input = selectionOperator
			container = ctr
		]
		ctr.rootExpression = productionOperator
		ctr
	}
	
	public def testModel2() {
		val ctr = createRelalgContainer
		
		val v1 = createVertexVariable => [ name = "v1"; container = ctr ]
		val v2 = createVertexVariable => [ name = "v2"; container = ctr ]
		val v3 = createVertexVariable => [ name = "v3"; container = ctr ]
		val v4 = createVertexVariable => [ name = "v4"; container = ctr ]
		
		val eA = createEdgeVariable => [ name = "a"; container = ctr ]
		val eB = createEdgeVariable => [ name = "b"; container = ctr ]
		val eC = createEdgeVariable => [ name = "c"; container = ctr ]
		
		val a = createGetEdgesOperator => [ 
			edgeVariable = eA
			sourceVertexVariable = v1
			targetVertexVariable = v2
			container = ctr 
		]
		val b = createGetEdgesOperator => [
			edgeVariable = eB
			sourceVertexVariable = v2
			targetVertexVariable = v3
			container = ctr 
		]
		val c = createGetEdgesOperator => [
			edgeVariable = eC
			sourceVertexVariable = v3
			targetVertexVariable = v4
			container = ctr 
		]
		
		val op1 = createJoinOperator => [
			leftInput = a
			rightInput = b
			container = ctr
		]
		val op2 = createJoinOperator => [
			leftInput = op1
			rightInput = c
			container = ctr
		]
		val productionOperator = createProductionOperator => [
			input = op2
			container = ctr
		]
		ctr.rootExpression = productionOperator
		ctr
	}
	
	public def testModel3() {
		val ctr = createRelalgContainer
		
		val leftLabel = createVertexLabel => [ name = "l" ]
		val rightLabel = createVertexLabel => [ name = "r" ]
		
		val v1 = createVertexVariable => [ name = "v"; vertexLabels.add(leftLabel); container = ctr ]
		val v2 = createVertexVariable => [ name = "v"; vertexLabels.add(rightLabel); container = ctr ]
		
		val leftOp = createGetVerticesOperator => [ vertexVariable = v1; container = ctr ]
		val rightOp = createGetVerticesOperator => [ vertexVariable = v2; container = ctr ]
		
		val op = createUnionOperator => [
			leftInput = leftOp
			rightInput = rightOp
			container = ctr
		]
		val productionOperator = createProductionOperator => [
			input = op
			container = ctr
		]
		ctr.rootExpression = productionOperator
		ctr
	}
	
}