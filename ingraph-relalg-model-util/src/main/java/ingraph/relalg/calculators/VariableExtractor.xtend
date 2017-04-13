package ingraph.relalg.calculators

import ingraph.relalg.collectors.CollectionHelper
import java.util.List
import relalg.AttributeVariable
import relalg.BeamerOperator
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
import ingraph.relalg.expressions.ExpressionUnwrapper

class VariableExtractor {

	extension ExpressionToVariables expressionToVariables = new ExpressionToVariables
	extension CollectionHelper collectionHelper = new CollectionHelper
	extension FunctionArgumentExtractor functionArgumentExtractor = new FunctionArgumentExtractor

	/**
	 * Extract extra variables required by unary operators.
	 */
	// GroupingAndProjection-, Grouping- and ProjectionOperators
	def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(GroupingAndProjectionOperator op) {
		uniqueUnion(getExtraVariablesForGroupingOperator(op), getExtraVariablesForProjectionOperator(op))
	}

	def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(ProjectionOperator op) {
		return getExtraVariablesForProjectionOperator(op)
	}

	def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(GroupingOperator op) {
		return getExtraVariablesForGroupingOperator(op)
	}

	// SelectionOperators
	def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(SelectionOperator op) {
		getAttributes(op.condition)
	}

	// BeamerOperator (i.e. ProductionOperator)
	def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(BeamerOperator op) {
		op.elements.filter(ExpressionVariable).map[expression].filter(VariableExpression).map[variable].filter(AttributeVariable).toList
	}

	// SortOperator and SortAndTopOperator
	def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(SortOperator op) {
		op.entries.map[expression].filter(VariableExpression).map[ExpressionUnwrapper.extractVariableExpression(it)].toList
	}

	def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(UnwindOperator op) {
		#[op.element]
	}

	// rest of the unary operators, e.g. the TopOperator, require no extra attributes
	def dispatch List<AttributeVariable> extractUnaryOperatorExtraVariables(UnaryOperator op) {
		#[]
	}

	def List<? extends Variable> getExtraVariablesForProjectionOperator(ProjectionOperator op) {
		val functionExpressions = op.elements.map[expression].filter(FunctionExpression).filter[!functor.isAggregation]
		val arguments = functionExpressions.map[extractFunctionArguments].flatten.toList

		val extraVariables = op.elements.filter(ExpressionVariable).map[expression].filter(VariableExpression).map[variable].filter(AttributeVariable).toList
		val aggregations = op.aggregations.map[expression].filter(FunctionExpression)
		val aggregationExtraVariables = aggregations.map[extractFunctionArguments].flatten.toList

		uniqueUnion(extraVariables, arguments, aggregationExtraVariables)
	}
	
	def List<? extends Variable> getExtraVariablesForGroupingOperator(GroupingOperator op) {
		val externalSchemaNames = op.externalSchema.map[toString]
		op.entries.filter[!externalSchemaNames.contains(it.toString)].toList
	}


	def List<? extends Variable> getCalculatedVariables(ProjectionOperator op) {
		uniqueUnion(op.otherFunctions, op.aggregations)
	}

}
