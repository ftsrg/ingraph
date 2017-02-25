package ingraph.optimization.transformations.relalg2rete

import ingraph.optimization.patterns.ExpandOperatorAMatcher
import ingraph.optimization.patterns.ExpandOperatorBMatcher
import ingraph.optimization.patterns.ExpandVertexMatcher
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
    statements.fireWhilePossible(expandVertexRule)
    statements.fireWhilePossible(expandOperatorARule)
    statements.fireWhilePossible(expandOperatorBRule)
		statements.fireWhilePossible(sortAndTopOperatorRule)
		statements.fireWhilePossible(leftOuterAndSelectionRule)
		return container
	}

	/**
	 * [1] Replace the GetVertexOperator + default 1..1 ExpandOperator pairs with a single GetEdgesOperator
	 */
	protected def expandVertexRule() {
		createRule() //
		.precondition(ExpandVertexMatcher.querySpecification) //
		.action [ //
			val expandOperator = expandOperator
			log('''expandVertexRule fired for «expandOperator.edgeVariable.name»''')

			val getEdgesOperator = createGetEdgesOperator => [
				sourceVertexVariable = expandOperator.source
				targetVertexVariable = expandOperator.target
				edgeVariable = expandOperator.edgeVariable
			]

			changeOperator(parentOperator, expandOperator, getEdgesOperator)
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
			log('''expandOperatorARule fired for «expandOperator.edgeVariable.name»''')

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
   * [2.B] Replace a non-default expand operator with a PathOperator and a GetVerticesOperator
   */
  protected def expandOperatorBRule() {
    createRule() //
    .precondition(ExpandOperatorBMatcher.querySpecification) //
    .action [ //
      val expandOperator = expandOperator
      log('''expandOperatorBRule fired for «expandOperator.edgeVariable.name»''')

      val getEdgesOperator = createGetEdgesOperator => [
        sourceVertexVariable = expandOperator.source
        targetVertexVariable = expandOperator.target
        edgeVariable = expandOperator.edgeVariable
      ]
      val sourceVertexOperator = expandOperator.input
      val targetVertexOperator = createGetVerticesOperator => [
        vertexVariable = expandOperator.targetVertexVariable        
      ]
      val pathOperator = createPathOperator => [
        minHops = expandOperator.minHops
        maxHops = expandOperator.maxHops

        sourceVertexVariable = expandOperator.source
        targetVertexVariable = expandOperator.target
        edgeVariable = expandOperator.edgeVariable

        val edgeVariable = edgeVariable
        listVariable = createVariableListExpression => [ variable = edgeVariable ]

        leftInput = sourceVertexOperator
        middleInput = getEdgesOperator
        rightInput = targetVertexOperator
      ]

      changeOperator(parentOperator, expandOperator, pathOperator)
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
