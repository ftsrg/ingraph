package ingraph.relalg.util

import com.google.common.collect.Iterables
import com.google.common.collect.Lists
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
  extension SchemaToMap schemaToMap = new SchemaToMap
  extension VariableExtractor variableExtractor = new VariableExtractor
  extension JoinAttributeCalculator joinAttributeCalculator = new JoinAttributeCalculator
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

  /**
   * shorthand for creating the union of two lists
   */
  def <T> union(List<? extends T>... lists) {
  	lists.forEach[]
    Lists.newArrayList(Iterables.concat(lists))
  }
  
  /**
   * calculateTuples
   */
  def dispatch void calculateTuples(NullaryOperator op) {
    // do nothing
  }
  
  def dispatch void calculateTuples(UnaryOperator op) {
    // do nothing    
  }
  
  def dispatch void calculateTuples(AbstractJoinOperator op) {
    val leftIndices = op.leftInput.schemaToMap
    val rightIndices = op.rightInput.schemaToMap
    
    op.commonVariables.forEach[ variable |
      op.leftMask.add(leftIndices.get(variable))
      op.rightMask.add(rightIndices.get(variable))
    ]
  }
  
  def dispatch void calculateTuples(UnionOperator op) {
    // do nothing
  }
  
  def dispatch void calculateTuples(TernaryOperator op) {
    // TODO do something
  }  
  
}
