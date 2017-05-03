package ingraph.relalg.calculators

import ingraph.relalg.collectors.CollectionHelper
import ingraph.relalg.util.visitors.PostOrderTreeVisitor
import java.util.List
import relalg.AbstractJoinOperator
import relalg.GroupingOperator
import relalg.NullaryOperator
import relalg.Operator
import relalg.ProductionOperator
import relalg.ProjectionOperator
import relalg.RelalgContainer
import relalg.RelalgFactory
import relalg.UnaryOperator
import relalg.UnionOperator
import relalg.Variable

/**
 * Calculates the internal schema that consists of the external schema plus the extra variables.
 */
class InternalSchemaCalculator {

	extension PostOrderTreeVisitor treeVisitor = new PostOrderTreeVisitor
	extension JoinAttributeCalculator joinAttributeCalculator = new JoinAttributeCalculator
	extension CollectionHelper listUnionCalculator = new CollectionHelper
	extension MaskCalculator maskCalculator = new MaskCalculator
	extension RelalgFactory factory = RelalgFactory.eINSTANCE

	def calculateInternalSchema(RelalgContainer container) {
		if (!container.isExtraVariablesInferred) {
			throw new IllegalStateException("ExtraVariablesCalculator must be executed before InternalSchemaCalculator")
		} else if (container.isInternalSchemaInferred) {
			throw new IllegalStateException("InternalSchemaCalculator on relalg container was already executed")
		} else {
			container.internalSchemaInferred = true
		}

		container.rootExpression.traverse([fillInternalSchema])
		container.rootExpression.traverse([calculateTuples])
		container
	}

	/**
	 * fillInternalSchema
	 */
	// nullary operators
	private def dispatch void fillInternalSchema(NullaryOperator op) {
		val internalSchema = uniqueUnion(op.externalSchema, op.extraVariables)
		op.defineInternalSchema(internalSchema)
	}

	// unary operators
	private def dispatch void fillInternalSchema(UnaryOperator op) {
		val internalSchema = op.input.internalSchema
		op.defineInternalSchema(internalSchema)
	}

	// binary operators
	private def dispatch void fillInternalSchema(UnionOperator op) {
		// TODO left/right inputs should be the same for their external schema
		val internalSchema = op.leftInput.internalSchema
		op.defineInternalSchema(internalSchema)
	}

	private def dispatch void fillInternalSchema(AbstractJoinOperator op) {
		val internalSchema = calculateJoinAttributes(op, op.getLeftInput.internalSchema, op.getRightInput.internalSchema)
		op.defineInternalSchema(internalSchema)
	}

	/**
	 * defineInternalSchema
	 */
	private def dispatch void defineInternalSchema(ProductionOperator op, List<? extends Variable> internalSchema) {
		val internalSchemaNames = internalSchema.map[name]

		op.elements.addAll(op.externalSchema.map[
			val element = it
			createExpressionVariable => [
				expression = createVariableExpression => [
					variable =  element
					expressionContainer = element.namedElementContainer
				]
				hasInferredName = true
				namedElementContainer = element.namedElementContainer
			]
		])
		op.internalSchema.addAll(op.externalSchema)

		// this projects "-1" in a number of cases, e.g. if a literal value was assigned to the variables
		op.tupleIndices.addAll(op.internalSchema.map[variable | internalSchemaNames.indexOf(variable.name)])
	}

	private def dispatch void defineInternalSchema(GroupingOperator op, List<? extends Variable> internalSchema) {
		op.defineInternalSchemaForProjectionOperator(internalSchema)
		op.defineInternalSchemaForGroupingOperator(internalSchema)
	}

	private def dispatch void defineInternalSchema(ProjectionOperator op, List<? extends Variable> internalSchema) {
		op.defineInternalSchemaForProjectionOperator(internalSchema)
	}

	private def dispatch void defineInternalSchema(Operator op, List<? extends Variable> internalSchema) {
		op.internalSchema.addAll(internalSchema)
	}

	/*
	 * Miscellaneous 
	 */
	private def void defineInternalSchemaForProjectionOperator(ProjectionOperator op, List<? extends Variable> internalSchema) {
		val internalSchemaNames = internalSchema.map[name]
		op.internalSchema.addAll(uniqueUnion(op.externalSchema, op.extraVariables))
		op.tupleIndices.addAll(op.internalSchema.map[variable | internalSchemaNames.indexOf(variable.name)])
	}
	
	private def void defineInternalSchemaForGroupingOperator(GroupingOperator op, List<? extends Variable> internalSchema) {
		op.aggregationCriteria.addAll(
			op.extraVariables.map[ 
				val extraVariable = it
				createVariableExpression => [
					variable = extraVariable
					expressionContainer = extraVariable.namedElementContainer
				]
			]
		)

		op.elements.addAll(
			op.extraVariables.map[ 
				val extraVariable = it
				createExpressionVariable => [
					expression = createVariableExpression => [
						variable = extraVariable
						expressionContainer = extraVariable.namedElementContainer
					]
					namedElementContainer = extraVariable.namedElementContainer
					hasInferredName = true
				]
			]
		)
	}

}
