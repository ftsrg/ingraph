package ingraph.optimization.transformations.relalg2rete

import ingraph.optimization.patterns.ExpandOperatorMatcher
import ingraph.optimization.patterns.ExpandVertexMatcher
import ingraph.optimization.transformations.AbstractRelalgTransformation
import relalg.RelalgContainer

class Relalg2ReteTransformation extends AbstractRelalgTransformation {

	def log(String log) {
		println("hello")
//		println(log)
	}

	def transformToRete(RelalgContainer container) {
		val statements = register(container)
		statements.fireWhilePossible(expandVertexRule)
		statements.fireWhilePossible(expandOperatorRule)
		return container
	}

	/**
	 * Replace the GetVertexOperator + ExpandOperator pairs with a single GetEdgesOperator
	 */
	protected def expandVertexRule() {
		createRule() //
		.precondition(ExpandVertexMatcher.querySpecification) //
		.action [ //
//			log("expandVertexRule fired")
			val expandOperator = expandOperator

			val getEdgesOperator = createGetEdgesOperator => [
				sourceVertexVariable = expandOperator.source
				targetVertexVariable = expandOperator.target
				edgeVariable = expandOperator.edgeVariable
			]

			changeOperator(parentOperator, expandOperator, getEdgesOperator)
		].build
	}

	/**
	 * Replace a single expand operator with a GetEdgesOperator and a JoinOperator
	 */
	protected def expandOperatorRule() {
		createRule() //
		.precondition(ExpandOperatorMatcher.querySpecification) //
		.action [ //
//			println("expandOperatorRule fired")
			val expandOperator = expandOperator

			val getEdgesOperator = createGetEdgesOperator => [
				sourceVertexVariable = expandOperator.source
				targetVertexVariable = expandOperator.target
				edgeVariable = expandOperator.edgeVariable
			]
			val joinOperator = createJoinOperator => [
				leftInput = expandOperator.getInput
				rightInput = getEdgesOperator
			]

			changeOperator(parentOperator, expandOperator, joinOperator)
		].build
	}

}
