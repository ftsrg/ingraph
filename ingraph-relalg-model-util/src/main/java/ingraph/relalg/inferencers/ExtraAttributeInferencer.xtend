package ingraph.relalg.inferencers

import ingraph.relalg.calculators.ListUnionCalculator
import ingraph.relalg.calculators.VariableExtractor
import java.util.List
import relalg.AbstractJoinOperator
import relalg.AttributeVariable
import relalg.NullaryOperator
import relalg.RelalgContainer
import relalg.TernaryOperator
import relalg.UnaryOperator
import relalg.UnionOperator

/**
 * Infers extra attributes, for example, a projection or a selection may need extra attributes.
 */
class ExtraAttributeInferencer {

  extension VariableExtractor variableExtractor = new VariableExtractor
  extension ListUnionCalculator listUnionCalculator = new ListUnionCalculator

  def inferExtraAttributes(RelalgContainer container) {
    if (!container.basicSchemaInferred) {
      throw new IllegalStateException("BasicSchemaInferencer must be executed before ExtraAttributeInferencer")
    } else if (container.extraAttributesInferred) {
      throw new IllegalStateException("ExtraAttributeInferencer on relalg container was already executed")
    } else {
      container.extraAttributesInferred = true
    }

    container.rootExpression.fillExtraAttributes(#[])
    container
  }

  private def dispatch void fillExtraAttributes(NullaryOperator op, List<AttributeVariable> extraVariables) {
    op.extraVariables.addAll(extraVariables)
  }

  private def dispatch void fillExtraAttributes(UnaryOperator op, List<AttributeVariable> extraVariables) {
    op.extraVariables.addAll(extraVariables)
    val newExtraVariables = extractUnaryOperatorExtraVariables(op)
    
    val inputExtraVariables = union(extraVariables, newExtraVariables)
    op.input.fillExtraAttributes(inputExtraVariables)
  }

  private def dispatch void fillExtraAttributes(UnionOperator op, List<AttributeVariable> extraVariables) {
    op.extraVariables.addAll(extraVariables)
    throw new UnsupportedOperationException("Union not yet supported")
  }

  private def dispatch void fillExtraAttributes(AbstractJoinOperator op, List<AttributeVariable> extraVariables) {
    op.extraVariables.addAll(extraVariables)
    val leftExtraVariables = extraVariables.filter[op.leftInput.schema.contains(it.element)].toList
    val rightExtraVariables = extraVariables.filter[op.rightInput.schema.contains(it.element)].toList

    // remove duplicates as we only need each extra variable once
    // we choose "right\left" as it works for both equijoin and antijoin operators,
    // as extra attributes that are available from both the left and right input
    rightExtraVariables.removeAll(leftExtraVariables)

    //val orderedExtraVariables = union(leftExtraVariables, rightExtraVariables)
    op.leftInput.fillExtraAttributes(leftExtraVariables)
    op.rightInput.fillExtraAttributes(rightExtraVariables)
  }

  private def dispatch void fillExtraAttributes(TernaryOperator op, List<AttributeVariable> extraVariables) {
    op.extraVariables.addAll(extraVariables)
    val leftExtraVariables = extraVariables.filter[op.leftInput.schema.contains(it.element)].toList
    val middleExtraVariables = extraVariables.filter[op.middleInput.schema.contains(it.element)].toList
    val rightExtraVariables = extraVariables.filter[op.rightInput.schema.contains(it.element)].toList

    // remove duplicates as we only need each extra variable once
    // see the related comment in inferDetailedSchema for BinaryOperators
    middleExtraVariables.removeAll(leftExtraVariables)
    
    rightExtraVariables.removeAll(leftExtraVariables)
    rightExtraVariables.removeAll(middleExtraVariables)

    op.leftInput.fillExtraAttributes(leftExtraVariables)
    op.middleInput.fillExtraAttributes(middleExtraVariables)
    op.rightInput.fillExtraAttributes(rightExtraVariables)
  }
  
}
