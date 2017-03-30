package ingraph.relalg.inferencers

import com.google.common.collect.Iterables
import com.google.common.collect.Lists
import ingraph.relalg.calculators.CollectionHelper
import ingraph.relalg.calculators.JoinAttributeCalculator
import ingraph.relalg.calculators.MaskCalculator
import ingraph.relalg.util.visitors.PostOrderTreeVisitor
import java.util.List
import relalg.AbstractJoinOperator
import relalg.NullaryOperator
import relalg.Operator
import relalg.ProductionOperator
import relalg.ProjectionOperator
import relalg.RelalgContainer
import relalg.RelalgFactory
import relalg.TernaryOperator
import relalg.UnaryOperator
import relalg.UnionOperator
import relalg.Variable

/**
 * Inferences the full schema, including extra attributes.
 */
class FullSchemaInferencer {

	extension PostOrderTreeVisitor treeVisitor = new PostOrderTreeVisitor
	extension JoinAttributeCalculator joinAttributeCalculator = new JoinAttributeCalculator
	extension CollectionHelper listUnionCalculator = new CollectionHelper
	extension MaskCalculator maskCalculator = new MaskCalculator
	extension RelalgFactory factory = RelalgFactory.eINSTANCE

	def inferFullSchema(RelalgContainer container) {
		if (!container.isExtraVariablesInferred) {
			throw new IllegalStateException("ExtraVariableInferencer must be executed before FullSchemaInferencer")
		} else if (container.fullSchemaInferred) {
			throw new IllegalStateException("FullSchemaInferencer on relalg container was already executed")
		} else {
			container.fullSchemaInferred = true
		}

		container.rootExpression.traverse([fillFullSchema])
		container.rootExpression.traverse([calculateTuples])
		container
	}

	/**
	 * fillFullSchema
	 */
	// nullary operators
	private def dispatch void fillFullSchema(NullaryOperator op) {
		val detailedSchema = union(op.basicSchema, op.extraVariables)
		op.defineDetailedSchema(detailedSchema)
	}

	// unary operators
	private def dispatch void fillFullSchema(UnaryOperator op) {
		val detailedSchema = op.input.fullSchema
		op.defineDetailedSchema(detailedSchema)
	}
	
	// binary operators
	private def dispatch void fillFullSchema(UnionOperator op) {
		// TODO left/right inputs should be the same for their detailed schema
		op.defineDetailedSchema(op.leftInput.fullSchema)
	}

	private def dispatch void fillFullSchema(AbstractJoinOperator op) {
		val fullSchema = calculateJoinAttributes(op, op.getLeftInput.fullSchema, op.getRightInput.fullSchema)
		op.defineDetailedSchema(fullSchema)
	}

	// ternary operator
	private def dispatch void fillFullSchema(TernaryOperator op) {
		val fullSchema = Lists.newArrayList(Iterables.concat(
			op.getLeftInput.fullSchema,
			op.getMiddleInput.fullSchema,
			op.getRightInput.fullSchema
		))
		op.defineDetailedSchema(fullSchema)
	}
	
	/**
	 * defineSchema
	 */
	private def dispatch void defineDetailedSchema(ProductionOperator op, List<? extends Variable> fullSchema) {
		// this projects "-1" in a number of cases, e.g.
		// - if a literal value was assigned to the variables
		val fullSchemaNames = fullSchema.map[name]

		op.elements.addAll(op.basicSchema.map[
			val element = it
			createExpressionVariable => [
				expression = createVariableExpression => [
					variable =  element
					container = element.namedElementContainer
				]
				hasInferredName = true
				namedElementContainer = element.namedElementContainer
			]
		])
		op.tupleIndices.addAll(op.basicSchema.map[variable | fullSchemaNames.indexOf(variable.name)])
		op.fullSchema.addAll(op.basicSchema)
	}

	private def dispatch void defineDetailedSchema(ProjectionOperator op, List<? extends Variable> fullSchema) {
//		op.fullSchema.addAll(op.input.fullSchema)
//		op.fullSchema.addAll(op.otherFunctions)
//		op.fullSchema.addAll(op.aggregations)
		val fullSchemaNames = fullSchema.map[name]
		op.tupleIndices.addAll(op.basicSchema.map[variable | fullSchemaNames.indexOf(variable.name)])
		op.fullSchema.addAll(op.basicSchema)
		op.fullSchema.addAll(op.extraVariables)
	}

	private def dispatch void defineDetailedSchema(Operator op, List<? extends Variable> fullSchema) {
		op.fullSchema.addAll(fullSchema)
	}

}
