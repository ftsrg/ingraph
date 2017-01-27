package ingraph.relalg.inferencers

import com.google.common.collect.Iterables
import com.google.common.collect.Lists
import ingraph.relalg.calculators.JoinAttributeCalculator
import ingraph.relalg.calculators.ListUnionCalculator
import ingraph.relalg.calculators.MaskCalculator
import ingraph.relalg.calculators.VariableExtractor
import ingraph.relalg.util.visitors.PostOrderTreeVisitor
import java.util.List
import relalg.AbstractJoinOperator
import relalg.AttributeVariable
import relalg.NullaryOperator
import relalg.Operator
import relalg.RelalgContainer
import relalg.TernaryOperator
import relalg.UnaryOperator
import relalg.UnionOperator
import relalg.Variable

class TupleInferencer {

  extension PostOrderTreeVisitor treeVisitor = new PostOrderTreeVisitor
  extension VariableExtractor variableExtractor = new VariableExtractor
  extension JoinAttributeCalculator joinAttributeCalculator = new JoinAttributeCalculator
  extension ListUnionCalculator listUnionCalculator = new ListUnionCalculator
  extension MaskCalculator maskCalculator = new MaskCalculator
  var RelalgContainer container

  def addDetailedSchemaInformation(RelalgContainer container) {
    if (container.tupleInferencingCompleted) {
      throw new IllegalStateException("Tuple inferencing on relalg container was already performed")
    } else {
      container.tupleInferencingCompleted = true
    }

    this.container = container
    container.rootExpression.inferDetailedSchema(#[])
    container.rootExpression.traverse([calculateTuples])
    container
  }

  /**
   * inferDetailedSchema
   */
  def dispatch void inferDetailedSchema(NullaryOperator op, List<AttributeVariable> extraVariables) {
    val detailedSchema = union(op.schema, extraVariables)
    op.defineDetailedSchema(detailedSchema)
  }

  def dispatch void inferDetailedSchema(UnaryOperator op, List<AttributeVariable> extraVariables) {
    val newExtraVariables = extractUnaryOperatorExtraVariables(op)
    
    val inputExtraVariables = union(extraVariables, newExtraVariables)
    op.input.inferDetailedSchema(inputExtraVariables)
    
    val detailedSchema = union(op.schema, inputExtraVariables)
    op.defineDetailedSchema(detailedSchema)
  }

  def dispatch void inferDetailedSchema(UnionOperator op, List<AttributeVariable> extraVariables) {
    throw new UnsupportedOperationException("Union not yet supported")
  }

  def dispatch void inferDetailedSchema(AbstractJoinOperator op, List<AttributeVariable> extraVariables) {
    val leftExtraVariables = extraVariables.filter[op.leftInput.schema.contains(it.element)].toList
    val rightExtraVariables = extraVariables.filter[op.rightInput.schema.contains(it.element)].toList

    // remove duplicates as we only need each extra variable once
    // we choose "right\left" as it works for both equijoin and antijoin operators,
    // as extra attributes that are available from both the left and right input
    rightExtraVariables.removeAll(leftExtraVariables)

    //val orderedExtraVariables = union(leftExtraVariables, rightExtraVariables)
    op.leftInput.inferDetailedSchema(leftExtraVariables)
    op.rightInput.inferDetailedSchema(rightExtraVariables)
    
    val schema = calculateJoinAttributes(op, op.getLeftInput.detailedSchema, op.getRightInput.detailedSchema)
    op.defineDetailedSchema(schema)
  }

  def dispatch void inferDetailedSchema(TernaryOperator op, List<AttributeVariable> extraVariables) {
    val leftExtraVariables = extraVariables.filter[op.leftInput.schema.contains(it.element)].toList
    val middleExtraVariables = extraVariables.filter[op.middleInput.schema.contains(it.element)].toList
    val rightExtraVariables = extraVariables.filter[op.rightInput.schema.contains(it.element)].toList

    // remove duplicates as we only need each extra variable once
    // see the related comment in inferDetailedSchema for BinaryOperators
    middleExtraVariables.removeAll(leftExtraVariables)
    
    rightExtraVariables.removeAll(leftExtraVariables)
    rightExtraVariables.removeAll(middleExtraVariables)

    op.leftInput.inferDetailedSchema(leftExtraVariables)
    op.middleInput.inferDetailedSchema(middleExtraVariables)
    op.rightInput.inferDetailedSchema(rightExtraVariables)

    val detailedSchema = Lists.newArrayList(Iterables.concat(
      op.getLeftInput.detailedSchema,
      op.getMiddleInput.detailedSchema,
      op.getRightInput.detailedSchema
    ))
    op.defineDetailedSchema(detailedSchema)
  }
  
  /**
   * defineSchema
   */
  def void defineDetailedSchema(Operator op, List<? extends Variable> detailedSchema) {
    op.detailedSchema.addAll(detailedSchema)
  }
  
}
