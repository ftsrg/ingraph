package ingraph.relalg.inferencers

import com.google.common.collect.Iterables
import ingraph.logger.IngraphLogger
import ingraph.relalg.calculators.VariableExtractor
import ingraph.relalg.collectors.CollectionHelper
import ingraph.relalg.util.ElementVariableExtractor
import java.util.List
import relalg.AbstractJoinOperator
import relalg.AttributeVariable
import relalg.ExpressionVariable
import relalg.FunctionExpression
import relalg.NullaryOperator
import relalg.Operator
import relalg.ProjectionOperator
import relalg.RelalgContainer
import relalg.TernaryOperator
import relalg.UnaryOperator
import relalg.UnionOperator
import relalg.Variable

/**
 * Infers extra variables. For example, a projection or a selection may need extra variables for projecting attributes or evaluating conditions.
 */
class ExtraVariableInferencer {

	extension IngraphLogger logger = new IngraphLogger(ExtraVariableInferencer.name)
	extension VariableExtractor variableExtractor = new VariableExtractor
	extension CollectionHelper listUnionCalculator = new CollectionHelper
	extension ElementVariableExtractor elementVariableExtractor = new ElementVariableExtractor

	def inferExtraVariables(RelalgContainer container) {
		if (!container.incrementalPlan) {
			throw new IllegalStateException("ExtraVariableInferencer must be executed on an incremental query plan")
		}

		if (!container.basicSchemaInferred) {
			throw new IllegalStateException("BasicSchemaInferencer must be executed before ExtraVariableInferencer")
		} else if (container.isExtraVariablesInferred) {
			throw new IllegalStateException("ExtraVariableInferencer on relalg container was already executed")
		} else {
			container.extraVariablesInferred = true
		}

		container.rootExpression.fillExtraVariables(#[])
		container
	}

	private def dispatch void fillExtraVariables(NullaryOperator op, List<Variable> extraVariables) {
		op.extraVariables.addAll(extraVariables)
	}

	// some unary operators, such as selection, projection and grouping, often require extra variables
	private def dispatch void fillExtraVariables(UnaryOperator op, List<Variable> extraVariables) {
		op.extraVariables.addAll(extraVariables)

		val newExtraVariables = extractUnaryOperatorExtraVariables(op)
		var inputExtraVariables = uniqueUnion(extraVariables, newExtraVariables)

		if (op instanceof ProjectionOperator) {
			inputExtraVariables = inputExtraVariables.minus(op.calculatedVariables)
		}

		op.input.fillExtraVariables(inputExtraVariables)
	}

	private def dispatch void fillExtraVariables(UnionOperator op, List<Variable> extraVariables) {
		op.extraVariables.addAll(extraVariables)
		op.leftInput.fillExtraVariables(extraVariables)
		op.rightInput.fillExtraVariables(extraVariables)
	}

	private def propagateTo(List<Variable> extraVariables, Operator inputOp) {
		val inputSchema = inputOp.basicSchema.map[extractElementVariable]
		val attributes = extraVariables.filter(AttributeVariable).filter[inputSchema.contains( it.baseVariable.extractElementVariable )]
		val functions = extraVariables.filter(ExpressionVariable).filter[expression instanceof FunctionExpression] // TODO this should involve a decision

		Iterables.concat(attributes, functions).toList
	}

	private def dispatch void fillExtraVariables(AbstractJoinOperator op, List<Variable> extraVariables) {
		op.extraVariables.addAll(extraVariables)
		val leftExtraVariables = extraVariables.propagateTo(op.leftInput)
		val rightExtraVariables = extraVariables.propagateTo(op.rightInput)

		// remove duplicates as we only need each extra variable once
		// we choose "right\left" as it works for both equijoin and antijoin operators,
		// as extra attributes that are available from both the left and right input
		rightExtraVariables.removeAll(leftExtraVariables)

		// val orderedExtraVariables = union(leftExtraVariables, rightExtraVariables)
		op.leftInput.fillExtraVariables(leftExtraVariables)
		op.rightInput.fillExtraVariables(rightExtraVariables)
	}

	private def dispatch void fillExtraVariables(TernaryOperator op, List<Variable> extraVariables) {
		op.extraVariables.addAll(extraVariables)
		val leftExtraVariables = extraVariables.propagateTo(op.leftInput)
		val middleExtraVariables = extraVariables.propagateTo(op.middleInput)
		val rightExtraVariables = extraVariables.propagateTo(op.rightInput)

		// remove duplicates as we only need each extra variable once
		// see the related comment in inferDetailedSchema for BinaryOperators
		middleExtraVariables.removeAll(leftExtraVariables)

		rightExtraVariables.removeAll(leftExtraVariables)
		rightExtraVariables.removeAll(middleExtraVariables)

		op.leftInput.fillExtraVariables(leftExtraVariables)
		op.middleInput.fillExtraVariables(middleExtraVariables)
		op.rightInput.fillExtraVariables(rightExtraVariables)
	}

}
