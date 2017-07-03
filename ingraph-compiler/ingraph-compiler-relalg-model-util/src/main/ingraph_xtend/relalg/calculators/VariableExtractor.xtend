package ingraph.relalg.calculators

import ingraph.relalg.collectors.CollectionHelper
import ingraph.relalg.expressions.ExpressionUnwrapper
import java.util.List
import relalg.AttributeVariable
import relalg.CaseExpression
import relalg.ExpressionVariable
import relalg.FunctionExpression
import relalg.GroupingOperator
import relalg.Operator
import relalg.ProductionOperator
import relalg.ProjectionOperator
import relalg.SelectionOperator
import relalg.SortOperator
import relalg.UnaryOperator
import relalg.UnwindOperator
import relalg.Variable
import relalg.VariableExpression

class VariableExtractor {

	extension ExpressionToVariables expressionToVariables = new ExpressionToVariables
	extension CollectionHelper collectionHelper = new CollectionHelper
	extension FunctionArgumentExtractor functionArgumentExtractor = new FunctionArgumentExtractor

	/**
	 * Extract extra variables required by unary operators.
	 */
	def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(ProjectionOperator op) {
		op.getExtraVariablesForProjectionOperator
	}

	def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(GroupingOperator op) {
		uniqueUnion(op.getExtraVariablesForProjectionOperator, op.getExtraVariablesForGroupingOperator)
	}

	// SelectionOperator
	def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(SelectionOperator op) {
		getAttributes(op.condition)
	}

	// ProductionOperator
	def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(ProductionOperator op) {
		op.elements.filter(ExpressionVariable).map[expression].filter(VariableExpression).map[variable].filter(AttributeVariable).toList
	}

	// SortOperator and SortAndTopOperator
	def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(SortOperator op) {
		//op.entries.map[expression].filter(VariableExpression).map[ExpressionUnwrapper.extractVariableExpression(it)].toList
		op.entries.map[expression].filter(VariableExpression).map[variable].toList
	}

	// UnwindOperator
	def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(UnwindOperator op) {
		#[op.element]
	}

	// rest of the unary operators, e.g. the TopOperator, require no extra attributes
	def dispatch List<AttributeVariable> extractUnaryOperatorExtraVariables(UnaryOperator op) {
		#[]
	}

	/*
	 * auxiliary functions
	 */
	def List<? extends Variable> getExtraVariablesForProjectionOperator(ProjectionOperator op) {
		// functions
		val functionExpressions = op.elements.map[expression].filter(FunctionExpression).filter[!functor.isAggregation]
		val arguments = functionExpressions.map[extractFunctionArguments].flatten.toList

		val expressions = op.elements.filter(ExpressionVariable).map[expression]

		// expression variables
		val extraVariables = expressions.filter(VariableExpression).map[variable].filter(AttributeVariable).toList
		// case expressions
		val caseAttributes = expressions.filter(CaseExpression).map[getAttributes].flatten.toList
		
		uniqueUnion(extraVariables, arguments, caseAttributes)
	}

	def List<? extends Variable> getExtraVariablesForGroupingOperator(GroupingOperator op) {
		val externalSchemaNames = op.externalSchema.map[fullName]
		op.aggregationCriteria
			.filter(ExpressionVariable).map[ExpressionUnwrapper.extractExpressionVariable(it)]
			.filter[!externalSchemaNames.contains(it.fullName)].toList
	}

	/*
	 * calculatedVariables
	 */
	def dispatch List<? extends Variable> getCalculatedVariables(ProjectionOperator op) {
//		uniqueUnion(op.elementsotherFunctions, op.aggregations)
		#[]
	}

	def dispatch List<? extends Variable> getCalculatedVariables(Operator op) {
		#[]
	}

}
