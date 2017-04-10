package ingraph.relalg.inferencers

import com.google.common.collect.Iterables
import com.google.common.collect.Lists
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
		val detailedSchema = uniqueUnion(op.basicSchema, op.extraVariables)
		op.defineFullSchema(detailedSchema)
	}

	// unary operators
	private def dispatch void fillFullSchema(UnaryOperator op) {
		val detailedSchema = op.input.fullSchema
		op.defineFullSchema(detailedSchema)
	}
	
	// binary operators
	private def dispatch void fillFullSchema(UnionOperator op) {
		// TODO left/right inputs should be the same for their detailed schema
		op.defineFullSchema(op.leftInput.fullSchema)
	}

	private def dispatch void fillFullSchema(AbstractJoinOperator op) {
		val fullSchema = calculateJoinAttributes(op, op.getLeftInput.fullSchema, op.getRightInput.fullSchema)
		op.defineFullSchema(fullSchema)
	}

	// ternary operator
	private def dispatch void fillFullSchema(TernaryOperator op) {
		val fullSchema = Lists.newArrayList(Iterables.concat(
			op.getLeftInput.fullSchema,
			op.getMiddleInput.fullSchema,
			op.getRightInput.fullSchema
		))
		op.defineFullSchema(fullSchema)
	}
	
	/**
	 * defineSchema
	 */
	private def dispatch void defineFullSchema(ProductionOperator op, List<? extends Variable> fullSchema) {
		// this projects "-1" in a number of cases, e.g.
		// - if a literal value was assigned to the variables
		val fullSchemaNames = fullSchema.map[name]

		op.elements.addAll(op.basicSchema.map[
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
		op.fullSchema.addAll(op.basicSchema)
		op.tupleIndices.addAll(op.fullSchema.map[variable | fullSchemaNames.indexOf(variable.name)])
	}

	private def dispatch void defineFullSchema(GroupingAndProjectionOperator op, List<? extends Variable> fullSchema) {
		op.fullSchemaGroupingOperator(fullSchema)
		op.fullSchemaProjectionOperator(fullSchema)
		
		val varNames = union(op.otherFunctions, op.aggregations).map[toString]
		op.order.addAll(op.fullSchema.map[varNames.indexOf(toString)])
	}

	private def dispatch void defineFullSchema(GroupingOperator op, List<? extends Variable> fullSchema) {
		op.fullSchemaGroupingOperator(fullSchema)
	}

	private def dispatch void defineFullSchema(ProjectionOperator op, List<? extends Variable> fullSchema) {
		op.fullSchemaProjectionOperator(fullSchema)
	}

	private def fullSchemaGroupingOperator(GroupingOperator op, List<? extends Variable> fullSchema) {
//		op.entries.addAll(op.input.extraVariables)
//			val entries = new ArrayList(op.entries)
//			val newEntries = uniqueUnion(entries, op.input.extraVariables)
//			op.entries.clear
//			op.entries.addAll(newEntries)
	}

	private def fullSchemaProjectionOperator(ProjectionOperator op, List<? extends Variable> fullSchema) {
		val fullSchemaNames = fullSchema.map[name]
		op.fullSchema.addAll(uniqueUnion(op.basicSchema, op.extraVariables))
		op.tupleIndices.addAll(op.fullSchema.map[variable | fullSchemaNames.indexOf(variable.name)])

		val List<Variable> others = op.fullSchema.minus(op.aggregations)
		op.otherFunctions.addAll(others)
	}

	private def dispatch void defineFullSchema(Operator op, List<? extends Variable> fullSchema) {
		op.fullSchema.addAll(fullSchema)
	}

}
