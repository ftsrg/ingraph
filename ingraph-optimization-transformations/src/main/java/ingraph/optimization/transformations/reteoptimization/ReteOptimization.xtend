package ingraph.optimization.transformations.reteoptimization

import ingraph.expressionparser.wrapper.ExpressionParserWrapper
import ingraph.optimization.patterns.AssociativeOperatorMatcher
import ingraph.optimization.patterns.CascadableSelectionMatcher
import ingraph.optimization.patterns.CommutativeOperatorMatcher
import ingraph.optimization.patterns.FoldableConstantExpressionMatcher
import ingraph.optimization.patterns.SwappableSelectionMatcher
import ingraph.optimization.transformations.AbstractRelalgTransformation
import org.eclipse.viatra.dse.api.DesignSpaceExplorer
import org.eclipse.viatra.dse.api.DesignSpaceExplorer.DseLoggingLevel
import org.eclipse.viatra.dse.api.Objectives
import org.eclipse.viatra.dse.api.Strategies
import org.eclipse.viatra.dse.solutionstore.SolutionStore
import relalg.RelalgContainer
import relalg.RelalgPackage
import relalg.Variable
import scala.collection.immutable.HashMap
import scala.collection.immutable.Vector

class ReteOptimization extends AbstractRelalgTransformation {

	def performSimpleOptimization(RelalgContainer container) {
		val statements = register(container)
		statements.fireAllCurrent(cascadingSelectionsRule)
		statements.fireAllCurrent(swappableSelectionsRule)
		statements.fireAllCurrent(associativeOperatorRule)
		statements.fireAllCurrent(commutativeOperatorRule)
		statements.fireAllCurrent(constantFoldingRule)
		return container
	}

	def performDseOptimization(RelalgContainer container) {
		DesignSpaceExplorer.turnOnLogging(DseLoggingLevel.OFF)
		val dse = new DesignSpaceExplorer()

		dse.setInitialModel(container)
		dse.addMetaModelPackage(RelalgPackage.eINSTANCE)
		dse.addTransformationRule(cascadingSelectionsRule)
		// dse.setStateCoderFactory(TODO)

		val strategy = Strategies.createDfsStrategy.continueIfHardObjectivesFulfilled
		dse.addObjective(Objectives.createDummyHardObjective)
		dse.addObjective(new ReteCostObjective())

		dse.solutionStore = new SolutionStore(0).storeBestSolutionsOnly
		dse.startExploration(strategy)

		// perform the transformation of a solution on the relalg expression
		val solution = dse.getArbitrarySolution();
		
		if (solution !== null) {
			solution.doTransformation(container)
		} else {
			println("No solution found")
		}
		
		return container
	}

	protected def cascadingSelectionsRule() {
		createRule() //
		.precondition(CascadableSelectionMatcher.querySpecification) //
		.action [ //
			System.err.println("cascadeConditionRule fired")
			val selectionOperator = selectionOperator
			val leftOperand = leftOperand
			val rightOperand = rightOperand

			val selectionOperator1 = createSelectionOperator => [
				condition = leftOperand
				input = selectionOperator.input
			]
			val selectionOperator2 = createSelectionOperator => [
				condition = rightOperand
				input = selectionOperator1
			]
			changeChildOperator(parentOperator, selectionOperator, selectionOperator2)
		].build
	}

	protected def swappableSelectionsRule() {
		createRule() //
		.precondition(SwappableSelectionMatcher.querySpecification) //
		.action [ //
			System.err.println("swappableSelections fired")
			selectionOperator1.input = selectionOperator2.input
			selectionOperator2.input = selectionOperator1

			changeChildOperator(parentOperator, selectionOperator1, selectionOperator2)
		].build
	}

	protected def commutativeOperatorRule() {
		createRule() //
		.precondition(CommutativeOperatorMatcher.querySpecification) //
		.action [ //
			System.err.println("commutativeJoin fired")
			op.rightInput = leftInput
			op.leftInput = rightInput
		].build
	}

	protected def associativeOperatorRule() {
		createRule() //
		.precondition(AssociativeOperatorMatcher.querySpecification) //
		.action [ //
			System.err.println("associativeJoin fired")
			op2.leftInput = a
			op2.rightInput = op1
			op1.leftInput = b
			op1.rightInput = c
		].build
	}
	
	def constantFoldingRule() {
		createRule()//
		.precondition(FoldableConstantExpressionMatcher.querySpecification)//
		.action [
			System.err.println("FIRE!")
			val x = ExpressionParserWrapper.parse(e, new HashMap<String, Integer>())(new Vector<Object>(1,1,1))
			println(x)
		].build
	}

}
