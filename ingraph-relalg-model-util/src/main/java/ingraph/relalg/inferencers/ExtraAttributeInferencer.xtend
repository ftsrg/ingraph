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

  private def dispatch void fillExtraAttributes(NullaryOperator op, List<AttributeVariable> extraAttributes) {
    op.extraAttributes.addAll(extraAttributes)
  }

  private def dispatch void fillExtraAttributes(UnaryOperator op, List<AttributeVariable> extraAttributes) {
    op.extraAttributes.addAll(extraAttributes)
    val newExtraAttributes = extractUnaryOperatorExtraAttributes(op)
    
    val inputExtraAttributes = union(extraAttributes, newExtraAttributes)
    op.input.fillExtraAttributes(inputExtraAttributes)
  }

  private def dispatch void fillExtraAttributes(UnionOperator op, List<AttributeVariable> extraAttributes) {
    op.extraAttributes.addAll(extraAttributes)
    op.leftInput.fillExtraAttributes(extraAttributes)
    op.rightInput.fillExtraAttributes(extraAttributes)
  }

  private def dispatch void fillExtraAttributes(AbstractJoinOperator op, List<AttributeVariable> extraAttributes) {
    op.extraAttributes.addAll(extraAttributes)
    val leftExtraAttributes = extraAttributes.filter[op.leftInput.basicSchema.contains(it.element)].toList
    val rightExtraAttributes = extraAttributes.filter[op.rightInput.basicSchema.contains(it.element)].toList

    // remove duplicates as we only need each extra variable once
    // we choose "right\left" as it works for both equijoin and antijoin operators,
    // as extra attributes that are available from both the left and right input
    rightExtraAttributes.removeAll(leftExtraAttributes)

    //val orderedExtraAttributes = union(leftExtraAttributes, rightExtraAttributes)
    op.leftInput.fillExtraAttributes(leftExtraAttributes)
    op.rightInput.fillExtraAttributes(rightExtraAttributes)
  }

  private def dispatch void fillExtraAttributes(TernaryOperator op, List<AttributeVariable> extraAttributes) {
    op.extraAttributes.addAll(extraAttributes)
    val leftExtraAttributes = extraAttributes.filter[op.leftInput.basicSchema.contains(it.element)].toList
    val middleExtraAttributes = extraAttributes.filter[op.middleInput.basicSchema.contains(it.element)].toList
    val rightExtraAttributes = extraAttributes.filter[op.rightInput.basicSchema.contains(it.element)].toList

    // remove duplicates as we only need each extra variable once
    // see the related comment in inferDetailedSchema for BinaryOperators
    middleExtraAttributes.removeAll(leftExtraAttributes)
    
    rightExtraAttributes.removeAll(leftExtraAttributes)
    rightExtraAttributes.removeAll(middleExtraAttributes)

    op.leftInput.fillExtraAttributes(leftExtraAttributes)
    op.middleInput.fillExtraAttributes(middleExtraAttributes)
    op.rightInput.fillExtraAttributes(rightExtraAttributes)
  }
  
}
