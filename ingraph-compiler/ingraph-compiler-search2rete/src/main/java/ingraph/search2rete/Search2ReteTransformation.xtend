package ingraph.search2rete

import ingraph.logger.IngraphLogger
import ingraph.optimization.patterns.DefaultExpandOperatorMatcher
import ingraph.optimization.patterns.GetVerticesAndExpandOperatorMatcher
import ingraph.optimization.patterns.LeftOuterJoinAndSelectionMatcher
import ingraph.optimization.patterns.SortAndTopOperatorMatcher
import ingraph.optimization.patterns.TransitiveExpandOperatorMatcher
import ingraph.optimization.transformations.AbstractRelalgTransformation
import relalg.RelalgContainer

class Search2ReteTransformation extends AbstractRelalgTransformation {

	extension IngraphLogger logger = new IngraphLogger(Search2ReteTransformation.name)

	new(RelalgContainer container) {
		super(container)
	}

	def transformToRete() {
		info("Transforming relational algebra expression to Rete network")

		if (container.incrementalPlan) {
			throw new IllegalStateException(
				"The relalg plan is already a Rete plan. Search2ReteTransformation should be invoked on a (non-incremental) search plan")
		}
		statements.fireWhilePossible(getVerticesAndExpandOperatorRule)
		statements.fireWhilePossible(defaultExpandOperatorRule)
		statements.fireWhilePossible(transitiveExpandOperatorRule)
		statements.fireWhilePossible(sortAndTopOperatorRule)
		statements.fireWhilePossible(leftOuterAndSelectionRule)
		container.incrementalPlan = true
		return container
	}

	/**
	 * [1] Replace the GetVerticesOperator + default 1..1 ExpandOperator pairs with a single GetEdgesOperator
	 */
	protected def getVerticesAndExpandOperatorRule() {
		createRule() //
		.precondition(GetVerticesAndExpandOperatorMatcher.querySpecification) //
		.action [ //
			val expandOperator = expandOperator
			info('''getVerticesAndExpandOperatorRule fired for «expandOperator.edgeVariable.name»''')

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
	 * [2] Replace an 1..1 expand operator with a JoinOperator and a GetEdgesOperator
	 */
	protected def defaultExpandOperatorRule() {
		createRule() //
		.precondition(DefaultExpandOperatorMatcher.querySpecification) //
		.action [ //
			val expandOperator = expandOperator
			info('''defaultExpandOperatorRule fired for «expandOperator.edgeVariable.name»''')

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

	/**
	 * [3] Replace a transitive expand oeprator with a TransitiveClosureJoin and a GetEdgesOperator	
	 */
	protected def transitiveExpandOperatorRule() {
		createRule() //
		.precondition(TransitiveExpandOperatorMatcher.querySpecification) //
		.action [ //
			val inputOperator = inputOperator
			val expandOperator = expandOperator
			val mEdgeListVariable = edgeListVariable
			info('''transitiveExpandOperatorRule fired for «expandOperator.edgeVariable.name»''')

			val getEdgesOperator = createGetEdgesOperator => [
				direction = expandOperator.direction.normalizeDirection
				sourceVertexVariable = expandOperator.source
				targetVertexVariable = expandOperator.target
				// create an edge variable with a single edge
				edgeVariable = createEdgeVariable => [
					namedElementContainer = expandOperator.edgeVariable.namedElementContainer
					name = expandOperator.edgeVariable.name
					edgeLabelSet = expandOperator.edgeVariable.edgeLabelSet
				]
			]
			val tcJoinOperator = createTransitiveClosureJoinOperator => [
				leftInput = inputOperator
				rightInput = getEdgesOperator
				edgeListVariable = mEdgeListVariable
			]

			changeChildOperator(parentOperator, expandOperator, tcJoinOperator)
		].build
	}

	

	/**
	 * [4] Replace an adjacent pair of SortOperator and TopOperator to a single SortAndTopOperator
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
	 * [5] Replace a pair of SelectionOperator and LeftOuterJoinOperator to a single AntiJoinOperator
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
