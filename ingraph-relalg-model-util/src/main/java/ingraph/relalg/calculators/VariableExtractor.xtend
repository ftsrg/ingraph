package ingraph.relalg.calculators

import com.google.common.collect.Iterables
import java.util.List
import relalg.AttributeVariable
import relalg.FunctionExpression
import relalg.GroupingOperator
import relalg.ProjectionOperator
import relalg.RelalgFactory
import relalg.SelectionOperator
import relalg.SortOperator
import relalg.UnaryOperator
import relalg.UnwindOperator
import relalg.Variable
import relalg.VariableExpression
import relalg.function.Function

class VariableExtractor {

	extension ExpressionToAttributes expressionToAttributes = new ExpressionToAttributes
 	extension RelalgFactory factory = RelalgFactory.eINSTANCE
 
 	val metaFunctions = #[Function.KEYS, Function.LABELS, Function.TYPE, Function.PROPERTIES]
 
	/**
	 * Extract extra attributes required by unary operators.
	 */
	def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(ProjectionOperator op) {
		val functions = op.elements.map[expression].filter(FunctionExpression).filter[metaFunctions.contains(functor)].map[ 
			val functionExpression = it
			createExpressionVariable => [
				namedElementContainer = functionExpression.container
				expression = functionExpression
				name = '''«functionExpression.functor.name.toLowerCase»(«(functionExpression.arguments.get(0) as VariableExpression).variable.name»)'''
			]
		]
		val attributes = op.elements.map[expression].filter(VariableExpression).map[variable].filter(AttributeVariable)
		Iterables.concat(functions, attributes).toList
	}

  def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(GroupingOperator op) {
    op.entries
  }

	def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(SelectionOperator op) {
		getAttributes(op.condition)
	}

  def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(SortOperator op) {
    op.entries.map[expression].filter(VariableExpression).map[variable].toList
  }

  def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(UnwindOperator op) {
    #[op.element]
  }
	
	// rest of the unary operators - no extra requirements
	def dispatch List<AttributeVariable> extractUnaryOperatorExtraVariables(UnaryOperator op) {
		#[]
	}

}