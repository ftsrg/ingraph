package ingraph.optimization.transformations.relalg2rete

import ingraph.optimization.patterns.ExpandOperatorMatcher
import ingraph.optimization.patterns.ExpandVertexMatcher
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
		//println(log)
	}

	def transformToRete(RelalgContainer container) {
	  log("Transforming relational algebra expression to Rete network")
		val statements = register(container)
		statements.fireWhilePossible(expandVertexRule)
		statements.fireWhilePossible(expandOperatorRule)
		statements.fireWhilePossible(sortAndTopOperatorRule)
		return container
	}

	/**
	 * Replace the GetVertexOperator + ExpandOperator pairs with a single GetEdgesOperator
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
	 * Replace a single expand operator with a GetEdgesOperator and a JoinOperator
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
   * Replace a pair of TopOperator and SortOperator to a single SortAndTopOperator
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
//      changeOperator(parentOperator, topOperator, sortAndTopOperator)
    ].build
  }	

}
