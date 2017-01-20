package ingraph.optimization.transformations.relalg2rete

import ingraph.optimization.patterns.ExpandOperatorMatcher
import ingraph.optimization.patterns.ExpandVertexAMatcher
import ingraph.optimization.patterns.ExpandVertexBMatcher
import ingraph.optimization.patterns.LeftOuterJoinAndSelectionMatcher
import ingraph.optimization.patterns.SortAndTopOperatorMatcher
import ingraph.optimization.transformations.AbstractRelalgTransformation
import org.apache.log4j.Level
import org.apache.log4j.Logger
import org.eclipse.viatra.query.runtime.util.ViatraQueryLoggingUtil
import relalg.RelalgContainer

class Relalg2ReteTransformation extends AbstractRelalgTransformation {

	new() {
	  val logger = Logger.getRootLogger
	  logger.setLevel(Level.ERROR)
	  ViatraQueryLoggingUtil.setExternalLogger(logger) 
	}

	def log(String log) {
		println(log)
	}

	def transformToRete(RelalgContainer container) {
	  log("Transforming relational algebra expression to Rete network")
		val statements = register(container)
    statements.fireWhilePossible(expandVertexARule)
    statements.fireWhilePossible(expandVertexBRule)
		statements.fireWhilePossible(expandOperatorRule)
		statements.fireWhilePossible(sortAndTopOperatorRule)
		statements.fireWhilePossible(leftOuterAndSelectionRule)
		return container
	}

	/**
	 * [1.A] Replace the GetVertexOperator + default 1..1 ExpandOperator pairs with a single GetEdgesOperator
	 */
	protected def expandVertexARule() {
		createRule() //
		.precondition(ExpandVertexAMatcher.querySpecification) //
		.action [ //
			val expandOperator = expandOperator
			log('''expandVertexARule fired for «expandOperator.edgeVariable.name»''')

			val getEdgesOperator = createGetEdgesOperator => [
				sourceVertexVariable = expandOperator.source
				targetVertexVariable = expandOperator.target
				edgeVariable = expandOperator.edgeVariable
			]

			changeOperator(parentOperator, expandOperator, getEdgesOperator)
		].build
	}
	
	/**
   * [1.B] Replace the GetVertexOperator + non-default ExpandOperator pairs with a GetEdgesOperator and a TransitiveClosureOperator
   */
  protected def expandVertexBRule() {
    createRule() //
    .precondition(ExpandVertexBMatcher.querySpecification) //
    .action [ //
      val expandOperator = expandOperator
      log('''expandVertexBRule fired for «expandOperator.edgeVariable.name»''')

      val getEdgesOperator = createGetEdgesOperator => [
        sourceVertexVariable = expandOperator.source
        targetVertexVariable = expandOperator.target
        edgeVariable = expandOperator.edgeVariable
      ]
      val transitiveClosureOperator = createTransitiveClosureOperator => [
        minHops = expandOperator.minHops
        maxHops = expandOperator.maxHops
        
        sourceVertexVariable = expandOperator.source
        targetVertexVariable = expandOperator.target
        edgeVariable = expandOperator.edgeVariable
        
        val edgeVariable = edgeVariable
        listVariable = createVariableListExpression => [ variable = edgeVariable ]
        input = getEdgesOperator
      ]

      changeOperator(parentOperator, expandOperator, transitiveClosureOperator)
    ].build
  }

	/**
	 * [2] Replace a single expand operator with a GetEdgesOperator and a JoinOperator
	 */
	protected def expandOperatorRule() {
		createRule() //
		.precondition(ExpandOperatorMatcher.querySpecification) //
		.action [ //
			val expandOperator = expandOperator
			log('''expandOperatorRule fired for «expandOperator.edgeVariable.name»''')

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
	
  /**
   * [3] Replace a pair of TopOperator and SortOperator to a single SortAndTopOperator
   */
  protected def sortAndTopOperatorRule() {
    createRule() //
    .precondition(SortAndTopOperatorMatcher.querySpecification) //
    .action [ //
      val sortOperator = sortOperator
      val topOperator = topOperator
      log('''sortAndTopOperatorRule fired for «sortOperator» and «topOperator»''')

      val sortAndTopOperator = createSortAndTopOperator => [
        entries.addAll(sortOperator.entries)
        skip = topOperator.skip
        limit = topOperator.limit
        input = sortOperator.input
      ]
      
      topLevelContainer.rootExpression = sortAndTopOperator
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
      log('''leftOuterAndSelectionRule fired for «selectionOperator» and «leftOuterJoinOperator»''')

      val antiJoinOperator = createAntiJoinOperator => [
        leftInput = leftInputOperator
        rightInput = getEdgesOperator
      ]
      
      changeOperator(parentOperator, selectionOperator, antiJoinOperator)
    ].build
  } 


}
