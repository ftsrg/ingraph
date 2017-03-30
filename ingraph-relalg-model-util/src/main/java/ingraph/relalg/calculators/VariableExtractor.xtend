package ingraph.relalg.calculators

import java.util.List
import relalg.AttributeVariable
import relalg.ExpressionVariable
import relalg.FunctionExpression
import relalg.GroupingAndProjectionOperator
import relalg.GroupingOperator
import relalg.ProjectionOperator
import relalg.SelectionOperator
import relalg.SortOperator
import relalg.UnaryOperator
import relalg.UnwindOperator
import relalg.Variable
import relalg.VariableExpression

class VariableExtractor {

	extension ExpressionToAttributes expressionToAttributes = new ExpressionToAttributes
	extension CollectionHelper collectionHelper = new CollectionHelper
	extension FunctionArgumentExtractor functionArgumentExtractor = new FunctionArgumentExtractor

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

	// SortOperator and SortAndTopOperator
	def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(SortOperator op) {
		val attributes = op.entries.map[expression].filter(VariableExpression).map[variable].filter(ExpressionVariable).map[expression].filter(VariableExpression).map[variable]
		return attributes.minus(op.basicSchema).toList
	}

	def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(UnwindOperator op) {
		#[op.element]
	}

	// rest of the unary operators, e.g. TopOperator, no extra requirements
	def dispatch List<AttributeVariable> extractUnaryOperatorExtraVariables(UnaryOperator op) {
		#[]
	}

	def List<? extends Variable> getExtraVariablesForProjectionOperator(ProjectionOperator op) {
		val functionExpressions = op.elements.map[expression].filter(FunctionExpression)

		val extraAttributes = op.elements.filter(ExpressionVariable).map[expression].filter(VariableExpression).map[variable].filter(AttributeVariable).toList
		
		// TODO: filter out duplicates
		val List<? extends Variable> arguments = functionExpressions.map[extractFunctionArguments].flatten.toList

		val List<ExpressionVariable> otherFunctions = op.elements.filter[expression instanceof FunctionExpression]
			.filter[!(expression as FunctionExpression).functor.meta && !(expression as FunctionExpression).functor.aggregation]
			.toList

		op.otherFunctions.addAll(otherFunctions)
		
		println(extraAttributes)
		println(arguments)
		
		union(extraAttributes, arguments)
	}
	
	def List<? extends Variable> getExtraVariablesForGroupingOperator(GroupingOperator op) {
		val basicSchemaNames = op.basicSchema.map[name]
		op.entries.filter[!basicSchemaNames.contains(it.name)].toList
	}

//

	def dispatch List<? extends Variable> extractUnaryOperatorProvidedVariables(GroupingAndProjectionOperator op) {
		union(getProvidedVariablesForProjectionOperator(op))
	}

	def dispatch List<? extends Variable> extractUnaryOperatorProvidedVariables(UnaryOperator op) {
		#[]
	}

	def List<? extends Variable> getProvidedVariablesForProjectionOperator(ProjectionOperator op) {
//		op.elements.filter[ it.expression instanceof FunctionExpression ].toList
		union(op.otherFunctions, op.aggregations)
	}

}
