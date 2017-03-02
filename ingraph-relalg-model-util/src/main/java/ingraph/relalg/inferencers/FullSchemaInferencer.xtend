package ingraph.relalg.inferencers

import com.google.common.collect.Iterables
import com.google.common.collect.Lists
import ingraph.relalg.calculators.JoinAttributeCalculator
import ingraph.relalg.calculators.ListUnionCalculator
import ingraph.relalg.calculators.MaskCalculator
import ingraph.relalg.util.visitors.PostOrderTreeVisitor
import java.util.List
import relalg.AbstractJoinOperator
import relalg.NullaryOperator
import relalg.Operator
import relalg.ProjectionOperator
import relalg.RelalgContainer
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
	extension ListUnionCalculator listUnionCalculator = new ListUnionCalculator
	extension MaskCalculator maskCalculator = new MaskCalculator

	def inferFullSchema(RelalgContainer container) {
		if (!container.extraAttributesInferred) {
			throw new IllegalStateException("ExtraAttributeInferencer must be executed before FullSchemaInferencer")
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
	private def dispatch void fillFullSchema(NullaryOperator op) {
		val detailedSchema = union(op.basicSchema, op.extraAttributes)
		op.defineDetailedSchema(detailedSchema)
	}

	private def dispatch void fillFullSchema(UnaryOperator op) {
		val detailedSchema = op.input.fullSchema
		op.defineDetailedSchema(detailedSchema)
	}

	private def dispatch void fillFullSchema(UnionOperator op) {
		// TODO left/right inputs should be the same for their detailed schema
		op.defineDetailedSchema(op.leftInput.fullSchema)
	}

	private def dispatch void fillFullSchema(AbstractJoinOperator op) {
		val fullSchema = calculateJoinAttributes(op, op.getLeftInput.fullSchema, op.getRightInput.fullSchema)
		op.defineDetailedSchema(fullSchema)
	}

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
	private def dispatch void defineDetailedSchema(ProjectionOperator op, List<? extends Variable> fullSchema) {
		// TODO this projects "-1" if a literal value was assigned to the variables
		println(op.basicSchema)
		println(fullSchema)
		
		op.tupleIndices.addAll(op.basicSchema.map[fullSchema.indexOf(it)])
		
		op.fullSchema.addAll(fullSchema)
	}

	private def dispatch void defineDetailedSchema(Operator op, List<? extends Variable> fullSchema) {
		op.fullSchema.addAll(fullSchema)
	}

}
