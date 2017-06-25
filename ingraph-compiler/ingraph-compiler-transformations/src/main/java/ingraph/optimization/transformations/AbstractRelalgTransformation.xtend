package ingraph.optimization.transformations

import ingraph.logger.IngraphLogger
import ingraph.optimization.patterns.DefaultExpandOperatorMatcher
import ingraph.optimization.patterns.GetVerticesAndExpandOperatorMatcher
import ingraph.optimization.patterns.LeftOuterJoinAndSelectionMatcher
import ingraph.optimization.patterns.SortAndTopOperatorMatcher
import ingraph.optimization.patterns.TransitiveExpandOperatorMatcher
import java.io.Closeable
import java.io.IOException
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.eclipse.viatra.query.runtime.api.AdvancedViatraQueryEngine
import org.eclipse.viatra.query.runtime.emf.EMFScope
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil
import org.eclipse.viatra.transformation.runtime.emf.rules.batch.BatchTransformationRuleFactory
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformation
import org.eclipse.viatra.transformation.runtime.emf.transformation.batch.BatchTransformationStatements
import org.eclipse.xtext.EcoreUtil2
import relalg.BinaryOperator
import relalg.Direction
import relalg.ExpandOperator
import relalg.Operator
import relalg.RelalgContainer
import relalg.RelalgFactory
import relalg.UnaryOperator

abstract class AbstractRelalgTransformation implements Closeable {

	protected extension IngraphLogger logger = new IngraphLogger(AbstractRelalgTransformation.name)
	protected val extension RelalgFactory relalgFactory = RelalgFactory.eINSTANCE
	protected val extension BatchTransformationRuleFactory ruleFactory = new BatchTransformationRuleFactory

	protected val RelalgContainer container
	protected val AdvancedViatraQueryEngine engine
	protected val BatchTransformationStatements statements

	new(RelalgContainer container) {
		val logger = Logger.getRootLogger
		logger.setLevel(Level.ERROR)
		ViatraQueryLoggingUtil.setExternalLogger(logger)
		
		this.container = container
		val scope = new EMFScope(container)
		engine = AdvancedViatraQueryEngine.createUnmanagedEngine(scope)
		statements = BatchTransformation.forEngine(engine).build.transformationStatements
	}

	protected def void changeChildOperator(Operator parentOperator, Operator currentOperator, Operator newOperator) {
		switch parentOperator {
			UnaryOperator: {
				parentOperator.input = newOperator
			}
			BinaryOperator: {
				if (parentOperator.getLeftInput.equals(currentOperator)) {
					parentOperator.leftInput = newOperator
				}
				if (parentOperator.getRightInput.equals(currentOperator)) {
					parentOperator.rightInput = newOperator
				}
			}
		}
		
		if (currentOperator instanceof ExpandOperator) {
			EcoreUtil2.delete(currentOperator)
		}
	}

	protected def source(ExpandOperator expandOperator) {
		switch (expandOperator.direction) {
			case BOTH,
			case IN: {
				return expandOperator.targetVertexVariable
			}
			case OUT: {
				return expandOperator.sourceVertexVariable
			}
		}
	}

	protected def target(ExpandOperator expandOperator) {
		switch (expandOperator.direction) {
			case BOTH,
			case IN: {
				return expandOperator.sourceVertexVariable
			}
			case OUT: {
				return expandOperator.targetVertexVariable
			}
		}
	}

	override close() throws IOException {
		engine?.dispose
	}

	protected def normalizeDirection(Direction direction) {
		switch (direction) {
			case BOTH: {
				return Direction.BOTH
			}
			default: {
				return Direction.OUT
			}
		}
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
	protected def leftOuterJoinAndSelectionRule() {
		createRule() //
		.precondition(LeftOuterJoinAndSelectionMatcher.querySpecification) //
		.action [ //
			val parentOperator = parentOperator
			val selectionOperator = selectionOperator
			val leftOuterJoinOperator = leftOuterJoinOperator
			val leftInputOperator = leftInputOperator
			val rightInputOperator = rightInputOperator
			info('''leftOuterAndSelectionRule fired for «selectionOperator» and «leftOuterJoinOperator»''')

			val antiJoinOperator = createAntiJoinOperator => [
				leftInput = leftInputOperator
				rightInput = rightInputOperator
			]

			changeChildOperator(parentOperator, selectionOperator, antiJoinOperator)
		].build
	}

}
