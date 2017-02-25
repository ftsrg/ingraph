package ingraph.relalg.inferencers

import com.google.common.collect.Iterables
import ingraph.relalg.calculators.ListUnionCalculator
import ingraph.relalg.calculators.VariableExtractor
import java.util.List
import relalg.AbstractJoinOperator
import relalg.AttributeVariable
import relalg.ExpressionVariable
import relalg.FunctionExpression
import relalg.NullaryOperator
import relalg.Operator
import relalg.RelalgContainer
import relalg.TernaryOperator
import relalg.UnaryOperator
import relalg.UnionOperator
import relalg.Variable

/**
 * Infers extra attributes, for example, a projection or a selection may need extra attributes.
 */
class ExtraVariableInferencer {

	extension VariableExtractor variableExtractor = new VariableExtractor
	extension ListUnionCalculator listUnionCalculator = new ListUnionCalculator

	def inferExtraAttributes(RelalgContainer container) {
		if (!container.basicSchemaInferred) {
			throw new IllegalStateException("BasicSchemaInferencer must be executed before ExtraVariableInferencer")
		} else if (container.extraAttributesInferred) {
			throw new IllegalStateException("ExtraVariableInferencer on relalg container was already executed")
		} else {
			container.extraAttributesInferred = true
		}

		container.rootExpression.fillExtraVariables(#[])
		container
	}

	private def dispatch void fillExtraVariables(NullaryOperator op, List<Variable> extraAttributes) {
		op.extraAttributes.addAll(extraAttributes)
	}

	private def dispatch void fillExtraVariables(UnaryOperator op, List<Variable> extraAttributes) {
		op.extraAttributes.addAll(extraAttributes)
		val newExtraAttributes = extractUnaryOperatorExtraAttributes(op)
		
		val inputExtraAttributes = union(extraAttributes, newExtraAttributes)
		op.input.fillExtraVariables(inputExtraAttributes)
	}

	private def dispatch void fillExtraVariables(UnionOperator op, List<Variable> extraAttributes) {
		op.extraAttributes.addAll(extraAttributes)
		op.leftInput.fillExtraVariables(extraAttributes)
		op.rightInput.fillExtraVariables(extraAttributes)
	}

	private def propagateTo(List<Variable> extraAttributes, Operator inputOp) {
	  val inputSchema = inputOp.basicSchema
	  val attributes = extraAttributes.filter(AttributeVariable).filter[inputSchema.contains(it.element)]
	  val functions = extraAttributes.filter(ExpressionVariable).filter[expression instanceof FunctionExpression] // TODO this should involve a decision
	  Iterables.concat(attributes, functions).toList
	}

	private def dispatch void fillExtraVariables(AbstractJoinOperator op, List<Variable> extraAttributes) {
		op.extraAttributes.addAll(extraAttributes)
		val leftExtraAttributes = extraAttributes.propagateTo(op.leftInput)
		val rightExtraAttributes = extraAttributes.propagateTo(op.rightInput)

		// remove duplicates as we only need each extra variable once
		// we choose "right\left" as it works for both equijoin and antijoin operators,
		// as extra attributes that are available from both the left and right input
		rightExtraAttributes.removeAll(leftExtraAttributes)

		//val orderedExtraAttributes = union(leftExtraAttributes, rightExtraAttributes)
		op.leftInput.fillExtraVariables(leftExtraAttributes)
		op.rightInput.fillExtraVariables(rightExtraAttributes)
	}

	private def dispatch void fillExtraVariables(TernaryOperator op, List<Variable> extraAttributes) {
		op.extraAttributes.addAll(extraAttributes)
		val leftExtraAttributes = extraAttributes.propagateTo(op.leftInput)
		val middleExtraAttributes = extraAttributes.propagateTo(op.middleInput)
		val rightExtraAttributes = extraAttributes.propagateTo(op.rightInput)

		// remove duplicates as we only need each extra variable once
		// see the related comment in inferDetailedSchema for BinaryOperators
		middleExtraAttributes.removeAll(leftExtraAttributes)
		
		rightExtraAttributes.removeAll(leftExtraAttributes)
		rightExtraAttributes.removeAll(middleExtraAttributes)

		op.leftInput.fillExtraVariables(leftExtraAttributes)
		op.middleInput.fillExtraVariables(middleExtraAttributes)
		op.rightInput.fillExtraVariables(rightExtraAttributes)
	}
	
}
