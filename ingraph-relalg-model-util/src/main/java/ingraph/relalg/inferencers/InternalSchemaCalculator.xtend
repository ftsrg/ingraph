package ingraph.relalg.inferencers

import ingraph.relalg.calculators.JoinAttributeCalculator
import ingraph.relalg.calculators.MaskCalculator
import ingraph.relalg.collectors.CollectionHelper
import ingraph.relalg.util.visitors.PostOrderTreeVisitor
import java.util.List
import relalg.AbstractJoinOperator
import relalg.GroupingAndProjectionOperator
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
 * Inferences the full schema, including extra attributes.
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
		val detailedSchema = uniqueUnion(op.externalSchema, op.extraVariables)
		op.defineInternalSchema(detailedSchema)
	}

	// unary operators
	private def dispatch void fillInternalSchema(UnaryOperator op) {
		val detailedSchema = op.input.internalSchema
		op.defineInternalSchema(detailedSchema)
	}
	
	// binary operators
	private def dispatch void fillInternalSchema(UnionOperator op) {
		// TODO left/right inputs should be the same for their detailed schema
		op.defineInternalSchema(op.leftInput.internalSchema)
	}

	private def dispatch void fillInternalSchema(AbstractJoinOperator op) {
		val internalSchema = calculateJoinAttributes(op, op.getLeftInput.internalSchema, op.getRightInput.internalSchema)
		op.defineInternalSchema(internalSchema)
	}
	
	/**
	 * defineSchema
	 */
	private def dispatch void defineInternalSchema(ProductionOperator op, List<? extends Variable> internalSchema) {
		// this projects "-1" in a number of cases, e.g.
		// - if a literal value was assigned to the variables
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
		op.tupleIndices.addAll(op.internalSchema.map[variable | internalSchemaNames.indexOf(variable.name)])
	}

	private def dispatch void defineInternalSchema(GroupingAndProjectionOperator op, List<? extends Variable> internalSchema) {
		op.internalSchemaGroupingOperator(internalSchema)
		op.internalSchemaProjectionOperator(internalSchema)
		
		val varNames = union(op.otherFunctions, op.aggregations).map[toString]
		op.order.addAll(op.internalSchema.map[varNames.indexOf(toString)])
	}

	private def dispatch void defineInternalSchema(GroupingOperator op, List<? extends Variable> internalSchema) {
		op.internalSchemaGroupingOperator(internalSchema)
	}

	private def dispatch void defineInternalSchema(ProjectionOperator op, List<? extends Variable> internalSchema) {
		op.internalSchemaProjectionOperator(internalSchema)
	}

	private def internalSchemaGroupingOperator(GroupingOperator op, List<? extends Variable> internalSchema) {
//		op.entries.addAll(op.input.extraVariables)
//			val entries = new ArrayList(op.entries)
//			val newEntries = uniqueUnion(entries, op.input.extraVariables)
//			op.entries.clear
//			op.entries.addAll(newEntries)
	}

	private def internalSchemaProjectionOperator(ProjectionOperator op, List<? extends Variable> internalSchema) {
		val internalSchemaNames = internalSchema.map[name]
		op.internalSchema.addAll(uniqueUnion(op.externalSchema, op.extraVariables))
		op.tupleIndices.addAll(op.internalSchema.map[variable | internalSchemaNames.indexOf(variable.name)])

		val List<Variable> others = op.internalSchema.minus(op.aggregations)
		op.otherFunctions.addAll(others)
	}

	private def dispatch void defineInternalSchema(Operator op, List<? extends Variable> internalSchema) {
		op.internalSchema.addAll(internalSchema)
	}

}
