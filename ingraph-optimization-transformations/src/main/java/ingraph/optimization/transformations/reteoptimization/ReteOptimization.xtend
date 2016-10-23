package ingraph.optimization.transformations.reteoptimization

import ingraph.optimization.patterns.AssociativeOperatorMatcher
import ingraph.optimization.patterns.CascadableSelectionMatcher
import ingraph.optimization.patterns.CommutativeOperatorMatcher
import ingraph.optimization.patterns.SwappableSelectionMatcher
import ingraph.optimization.transformations.AbstractRelalgTransformation
import org.eclipse.viatra.dse.api.DesignSpaceExplorer
import org.eclipse.viatra.dse.evolutionary.EvolutionaryStrategyBuilder
import relalg.RelalgContainer
import relalg.RelalgPackage

class ReteOptimization extends AbstractRelalgTransformation {

	def performSimpleOptimization(RelalgContainer container) {
		val statements = register(container)
		statements.fireAllCurrent(cascadingSelectionsRule)
		statements.fireAllCurrent(swappableSelectionsRule)
		statements.fireAllCurrent(associativeOperatorRule)
		statements.fireAllCurrent(commutativeOperatorRule)
		return container
	}
	
	def performDseOptimization(RelalgContainer container) {	
		val dse = new DesignSpaceExplorer()
		
        dse.setInitialModel(container)
        dse.addMetaModelPackage(RelalgPackage.eINSTANCE)
        //dse.setStateCoderFactory(TODO)
        //dse.addTransformationRule(TODO)
        
        //dse.addObjective(TODO)
        
        val populationSize = 40
        val nsga2 = EvolutionaryStrategyBuilder.createNsga2Builder(populationSize)
        //nsga2.initialPopulationSelector =
		//nsga2.mutationRate = 
        //nsga2.addMutation()
        //nsga2.addCrossover() 
		//nsga2.setStopCondition()
		
		dse.startExploration(nsga2.build())
		
		val solutionTrajectory = dse.getArbitrarySolution()
        solutionTrajectory.doTransformation(container)
        
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

			val c = selectionOperator.container

			val selectionOperator1 = createSelectionOperator => [
				container = c
				condition = leftOperand
				input = selectionOperator.input
			]
			val selectionOperator2 = createSelectionOperator => [
				container = c
				condition = rightOperand
				input = selectionOperator1
			]
			changeOperator(parentOperator, selectionOperator, selectionOperator2)
		].build
	}

	protected def swappableSelectionsRule() {
		createRule() //
		.precondition(SwappableSelectionMatcher.querySpecification) //
		.action [ //
			System.err.println("swappableSelections fired")
			selectionOperator1.input = selectionOperator2.input
			selectionOperator2.input = selectionOperator1

			changeOperator(parentOperator, selectionOperator1, selectionOperator2)
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

}
