package ingraph.relalg2rete

import ingraph.logger.IngraphLogger
import ingraph.optimization.patterns.EmptyAllDifferentOperatorMatcher
import ingraph.optimization.patterns.ExpandOperatorAMatcher
import ingraph.optimization.patterns.ExpandVertexMatcher
import ingraph.optimization.patterns.LeftOuterJoinAndSelectionMatcher
import ingraph.optimization.patterns.SortAndTopOperatorMatcher
import ingraph.optimization.patterns.UnnecessaryJoinMatcher
import ingraph.optimization.transformations.AbstractRelalgTransformation
import relalg.RelalgContainer

class Relalg2ReteTransformation extends AbstractRelalgTransformation {

	extension IngraphLogger logger = new IngraphLogger(Relalg2ReteTransformation.name)

	new(RelalgContainer container) {
		super(container)
	}

	def transformToRete() {
		info("Transforming relational algebra expression to Rete network")

		if (container.incrementalPlan) {
			throw new IllegalStateException(
				"The query plan is already incremental. Relalg2ReteTransformation should be invoked on a non-incremental search plan")
		}
		// simpliciations
		statements.fireWhilePossible(unnecessaryJoinOperatorRule)
		statements.fireWhilePossible(emptyAllDifferentOperatorRule)
		
		// Rete
		statements.fireWhilePossible(expandVertexRule)
		statements.fireWhilePossible(expandOperatorARule)
		statements.fireWhilePossible(sortAndTopOperatorRule)
		statements.fireWhilePossible(leftOuterAndSelectionRule)
		container.incrementalPlan = true
		return container
	}

	/**
	 * [a] Remove unnecessary JoinOperators
	 */
	protected def unnecessaryJoinOperatorRule() {
		createRule() //
		.precondition(UnnecessaryJoinMatcher.querySpecification) //
		.action [ //
			info('''unnecessaryJoinOperatorRule fired for «equiJoinLikeOperator»''')
			changeChildOperator(parentOperator, equiJoinLikeOperator, leftInputOperator)
		].build
	}

	/**
	 * [b] Remove unnecessary AllDifferentOperators
	 */
	protected def emptyAllDifferentOperatorRule() {
		createRule() //
		.precondition(EmptyAllDifferentOperatorMatcher.querySpecification) //
		.action [ //
			info('''emptyAllDifferentOperatorRule fired for «allDifferentOperator»''')
			changeChildOperator(parentOperator, allDifferentOperator, inputOperator)
		].build
	}

	/**
	 * [1] Replace the GetVertexOperator + default 1..1 ExpandOperator pairs with a single GetEdgesOperator
	 */
	protected def expandVertexRule() {
		createRule() //
		.precondition(ExpandVertexMatcher.querySpecification) //
		.action [ //
			val expandOperator = expandOperator
			info('''expandVertexRule fired for «expandOperator.edgeVariable.name»''')

			val getEdgesOperator = createGetEdgesOperator => [
				direction = expandOperator.direction.normalizeDirection
				sourceVertexVariable = expandOperator.source
				targetVertexVariable = expandOperator.target
				edgeVariable = expandOperator.edgeVariable
			]

			changeChildOperator(parentOperator, expandOperator, getEdgesOperator)
		].build
	}

	/**
	 * [2.A] Replace a default expand operator with a GetEdgesOperator and a JoinOperator
	 */
	protected def expandOperatorARule() {
		createRule() //
		.precondition(ExpandOperatorAMatcher.querySpecification) //
		.action [ //
			val expandOperator = expandOperator
			info('''expandOperatorARule fired for «expandOperator.edgeVariable.name»''')

			val getEdgesOperator = createGetEdgesOperator => [
				direction = expandOperator.direction.normalizeDirection
				sourceVertexVariable = expandOperator.source
				targetVertexVariable = expandOperator.target
				edgeVariable = expandOperator.edgeVariable
			]
			val joinOperator = createJoinOperator => [
				leftInput = expandOperator.getInput
				rightInput = getEdgesOperator
			]

			changeChildOperator(parentOperator, expandOperator, joinOperator)
		].build
	}

//	/**
//	 * [2.B] Replace a non-default expand operator with a PathOperator and a GetVerticesOperator
//	 */
//	protected def expandOperatorBRule() {
//		createRule() //
//		.precondition(ExpandOperatorBMatcher.querySpecification) //
//		.action [ //
//			val expandOperator = expandOperator
//			info('''expandOperatorBRule fired for «expandOperator.edgeVariable.name»''')
//
//			val getEdgesOperator = createGetEdgesOperator => [
//				direction = expandOperator.direction.normalizeDirection
//				sourceVertexVariable = expandOperator.source
//				targetVertexVariable = expandOperator.target
//				edgeVariable = expandOperator.edgeVariable
//			]
//			val sourceVertexOperator = expandOperator.input
//			val targetVertexOperator = createGetVerticesOperator => [
//				vertexVariable = expandOperator.targetVertexVariable
//			]
//			val pathOperator = createPathOperator => [
//				//FIXME: rework for improved relalg metamodel
////				minHops = expandOperator.minHops
////				maxHops = expandOperator.maxHops
////
////				sourceVertexVariable = expandOperator.source
////				targetVertexVariable = expandOperator.target
////				edgeVariable = expandOperator.edgeVariable
////
////				val edgeVariable = edgeVariable
////				listVariable = createVariableListExpression => [variable = edgeVariable]
////
////				leftInput = sourceVertexOperator
////				middleInput = getEdgesOperator
////				rightInput = targetVertexOperator
//			]
//
//			changeChildOperator(parentOperator, expandOperator, pathOperator)
//		].build
//	}

//	/**
//	 * [3.A] Swap an adjacent pair of Top and Projection operators
//	 */
//	protected def swapTopAndProjectionOperatorRule() {
//		createRule() //
//		.precondition(TopAndProjectionOperatorMatcher.querySpecification) //
//		.action [ //
//			info('''swapTopAndProjectionOperatorRule fired for «topOperator» and «projectionOperator»''')
//
//			changeChildOperator(parentOperator, topOperator, projectionOperator)
//			val oldProjectionOperatorInput = projectionOperator.input 
//			projectionOperator.input = topOperator
//			topOperator.input = oldProjectionOperatorInput
//		].build
//	}


	/**
	 * [3.B] Replace an adjacent pair of SortOperator and TopOperator to a single SortAndTopOperator
	 */
	protected def sortAndTopOperatorRule() {
		createRule() //
		.precondition(SortAndTopOperatorMatcher.querySpecification) //
		.action [ //
			val sortOperator = sortOperator
			val topOperator = topOperator
			info('''sortAndTopOperatorRule fired for «sortOperator» and «topOperator»''')

			val sortAndTopOperator = createSortAndTopOperator => [
				entries.addAll(sortOperator.entries)
				skip = topOperator.skip
				limit = topOperator.limit

				input = sortOperator.input
			]

			changeChildOperator(parentOperator, topOperator, sortAndTopOperator)
		].build
	}

	/**
	 * [4] Replace a pair of SelectionOperator and LeftOuterJoinOperator to a single AntiJoinOperator
	 */
	protected def leftOuterAndSelectionRule() {
		createRule() //
		.precondition(LeftOuterJoinAndSelectionMatcher.querySpecification) //
		.action [ //
			val parentOperator = parentOperator
			val selectionOperator = selectionOperator
			val leftOuterJoinOperator = leftOuterJoinOperator
			val leftInputOperator = leftInputOperator
			val getEdgesOperator = getEdgesOperator
			info('''leftOuterAndSelectionRule fired for «selectionOperator» and «leftOuterJoinOperator»''')

			val antiJoinOperator = createAntiJoinOperator => [
				leftInput = leftInputOperator
				rightInput = getEdgesOperator
			]

			changeChildOperator(parentOperator, selectionOperator, antiJoinOperator)
		].build
	}

}
