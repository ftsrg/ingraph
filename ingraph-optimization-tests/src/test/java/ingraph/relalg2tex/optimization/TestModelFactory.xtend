package ingraph.relalg2tex.optimization

import relalg.RelalgFactory
import relalg.BinaryLogicalOperatorType

class TestModelFactory {
	
	val extension RelalgFactory factory = RelalgFactory.eINSTANCE
	
	public def testModel1() {
		val ctr = createRelalgContainer
		
		val l = createBooleanLiteral => [value = true]
		val r = createBooleanLiteral => [value = false]
		val condition = createBinaryLogicalExpression => [
			operator = BinaryLogicalOperatorType.AND
			leftOperand = l
			rightOperand = r
		]
		val selectionOperator = createSelectionOperator => [
			it.condition = condition
		]
		val productionOperator = createProductionOperator => [
			input = selectionOperator
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
		]
		val b = createGetEdgesOperator => [
			edgeVariable = eB
			sourceVertexVariable = v2
			targetVertexVariable = v3
		]
		val c = createGetEdgesOperator => [
			edgeVariable = eC
			sourceVertexVariable = v3
			targetVertexVariable = v4
		]
		
		val op1 = createJoinOperator => [
			leftInput = a
			rightInput = b
		]
		val op2 = createJoinOperator => [
			leftInput = op1
			rightInput = c
		]
		val productionOperator = createProductionOperator => [
			input = op2
		]
		ctr.rootExpression = productionOperator
		ctr
	}
	
	public def testModel3() {
		val ctr = createRelalgContainer
		
		val leftLabel = createVertexLabel => [ name = "l" ]
		val rightLabel = createVertexLabel => [ name = "r" ]
		
		val v1 = createVertexVariable => [ name = "v"; vertexLabelSet.vertexLabels.add(leftLabel); container = ctr ]
		val v2 = createVertexVariable => [ name = "v"; vertexLabelSet.vertexLabels.add(rightLabel); container = ctr ]
		
		val leftOp = createGetVerticesOperator => [ vertexVariable = v1 ]
		val rightOp = createGetVerticesOperator => [ vertexVariable = v2 ]
		
		val op = createUnionOperator => [
			leftInput = leftOp
			rightInput = rightOp
		]
		val productionOperator = createProductionOperator => [
			input = op
		]
		ctr.rootExpression = productionOperator
		ctr
	}
	
}