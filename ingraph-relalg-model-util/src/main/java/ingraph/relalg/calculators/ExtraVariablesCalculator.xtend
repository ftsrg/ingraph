package ingraph.relalg.calculators

import ingraph.logger.IngraphLogger
import ingraph.relalg.collectors.CollectionHelper
import java.util.List
import relalg.AbstractJoinOperator
import relalg.NullaryOperator
import relalg.RelalgContainer
import relalg.UnaryOperator
import relalg.UnionOperator
import relalg.Variable

/**
 * Calculates extra variables.
 * 
 * For example, a projection may need extra variables for projecting attributes
 * or a selection may need extra attributes for evaluating conditions.
 */
class ExtraVariablesCalculator {

	extension IngraphLogger logger = new IngraphLogger(ExtraVariablesCalculator.name)
	extension VariableExtractor variableExtractor = new VariableExtractor
	extension CollectionHelper listUnionCalculator = new CollectionHelper
	extension ExtraVariablesPropagator extraVariablesPropagator = new ExtraVariablesPropagator

	def calculateExtraVariables(RelalgContainer container) {
		if (!container.incrementalPlan) {
			throw new IllegalStateException("ExtraVariablesCalculator must be executed on an incremental query plan")
		}

		if (!container.isExternalSchemaInferred) {
			throw new IllegalStateException("ExternalSchemaCalculator must be executed before ExtraVariableCalculator")
		} else if (container.isExtraVariablesInferred) {
			throw new IllegalStateException("ExtraVariablesCalculator on relalg container was already executed")
		} else {
			container.extraVariablesInferred = true
		}

		container.rootExpression.fillExtraVariables(#[])
		container
	}

	/*
	 * nullary operators
	 */
	private def dispatch void fillExtraVariables(NullaryOperator op, List<Variable> extraVariables) {
		op.extraVariables.addAll(extraVariables)
	}

	/*
	 * unary operators
	 * 
	 * some unary operators, such as selection, projection and grouping, often require extra variables
	 */
	private def dispatch void fillExtraVariables(UnaryOperator op, List<Variable> extraVariables) {
		op.extraVariables.addAll(extraVariables)

		val newExtraVariables = op.extractUnaryOperatorExtraVariables
		var inputExtraVariables = uniqueUnion(extraVariables, newExtraVariables).minus(op.calculatedVariables)
		val propagatedInputExtraVariables = inputExtraVariables.propagateTo(op.input)

		op.input.fillExtraVariables(propagatedInputExtraVariables)
	}

	/*
	 * binary operators 
	 */
	private def dispatch void fillExtraVariables(UnionOperator op, List<Variable> extraVariables) {
		op.extraVariables.addAll(extraVariables)

		op.leftInput.fillExtraVariables(extraVariables)
		op.rightInput.fillExtraVariables(extraVariables)
	}

	private def dispatch void fillExtraVariables(AbstractJoinOperator op, List<Variable> extraVariables) {
		op.extraVariables.addAll(extraVariables)
	
		// Calculate left and right extra variables -- we only need each extra variable once.
		// For variables that could be propagated to both sides,
		// we choose the left side as it works for both equijoin and antijoin operators.
		val leftExtraVariables = extraVariables.propagateTo(op.leftInput)
		val rightExtraVariables = extraVariables.propagateTo(op.rightInput)
		rightExtraVariables.removeAll(leftExtraVariables)

		op.leftInput.fillExtraVariables(leftExtraVariables)
		op.rightInput.fillExtraVariables(rightExtraVariables)
	}

}
