package ingraph.relalg.calculators

import ingraph.relalg.collectors.CollectionHelper
import ingraph.relalg.util.ElementVariableExtractor
import java.util.List
import relalg.AttributeVariable
import relalg.ExpressionVariable
import relalg.FunctionExpression
import relalg.Operator
import relalg.Variable

class ExtraVariablesPropagator {
	
	extension CollectionHelper listUnionCalculator = new CollectionHelper
	extension ElementVariableExtractor elementVariableExtractor = new ElementVariableExtractor
	
	def propagateTo(List<Variable> extraVariables, Operator inputOp) {
		val inputSchemaNames = inputOp.externalSchema.map[toString]

		val attributes = extraVariables.filter(AttributeVariable).filter[
			!inputSchemaNames.contains( it.toString ) && // do not propagate if it is already there
																										// TODO check this for joins - e.g. if it is available on the left input, do not propagate to the right 
			inputSchemaNames.contains( it.baseVariable.extractElementVariable.toString )
		]
		val functions = extraVariables.filter(ExpressionVariable).filter[expression instanceof FunctionExpression] // TODO this should involve a decision
				.filter[!inputSchemaNames.contains( it.toString )]

		uniqueUnion(attributes, functions)
	}

	
}