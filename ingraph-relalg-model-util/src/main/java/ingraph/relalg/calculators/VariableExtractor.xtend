package ingraph.relalg.calculators

import java.util.List
import relalg.AttributeVariable
import relalg.FunctionExpression
import relalg.GroupingAndProjectionOperator
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
import relalg.ExpressionVariable

class VariableExtractor {

	extension ExpressionToAttributes expressionToAttributes = new ExpressionToAttributes
	extension RelalgFactory factory = RelalgFactory.eINSTANCE
	extension CollectionHelper unionCalculator = new CollectionHelper

	val metaFunctions = #[Function.KEYS, Function.LABELS, Function.TYPE, Function.PROPERTIES]

	/**
	 * Extract extra variables required by unary operators.
	 */
	def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(ProjectionOperator op) {
		return getExtraVariablesForProjectionOperator(op)
	}

	def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(GroupingOperator op) {
		return getExtraVariablesForGroupingOperator(op)
	}

	def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(GroupingAndProjectionOperator op) {
		union(getExtraVariablesForGroupingOperator(op), getExtraVariablesForProjectionOperator(op))
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

	def List<? extends Variable> getExtraVariablesForProjectionOperator(ProjectionOperator op) {
		val functionExpressions = op.elements.map[expression].filter(FunctionExpression)

		val List<? extends Variable> functions = functionExpressions.filter[metaFunctions.contains(functor)].map [ // deal with metafunctions
			val functionExpression = it
			createExpressionVariable => [
				namedElementContainer = functionExpression.container
				expression = functionExpression
				name = '''«functionExpression.functor.name.toLowerCase»(«(functionExpression.arguments.get(0) as VariableExpression).variable.name»)'''
			]
		].toList

		val attributes = op.elements.map[expression].filter(VariableExpression).map[variable].filter(AttributeVariable)
		union(functions, attributes)
	}

	def List<? extends Variable> getExtraVariablesForGroupingOperator(GroupingOperator op) {
		op.entries
	}

//

	def dispatch List<? extends Variable> extractUnaryOperatorProvidedVariables(GroupingAndProjectionOperator op) {
		union(getProvidedVariablesForProjectionOperator(op))
	}

	def dispatch List<? extends Variable> extractUnaryOperatorProvidedVariables(UnaryOperator op) {
		#[]
	}

	def List<? extends Variable> getProvidedVariablesForProjectionOperator(ProjectionOperator op) {
		op.elements.filter[ it.expression instanceof FunctionExpression ].toList
	}


}
