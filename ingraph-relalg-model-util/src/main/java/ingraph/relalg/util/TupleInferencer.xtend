package ingraph.relalg.util

import com.google.common.collect.Iterables
import com.google.common.collect.Lists
import java.util.List
import relalg.AbstractJoinOperator
import relalg.ArithmeticComparisonExpression
import relalg.AttributeVariable
import relalg.BinaryOperator
import relalg.Expression
import relalg.GroupingOperator
import relalg.NullaryOperator
import relalg.Operator
import relalg.ProjectionOperator
import relalg.RelalgContainer
import relalg.SelectionOperator
import relalg.SortOperator
import relalg.UnaryLogicalExpression
import relalg.UnaryOperator
import relalg.UnionOperator
import relalg.UnwindOperator
import relalg.Variable
import relalg.VariableExpression

class TupleInferencer {

  extension SchemaToMap schemaToMap = new SchemaToMap
  var RelalgContainer container

  def addDetailedSchemaInformation(RelalgContainer container) {
    val rootExpression = container.getRootExpression
    this.container = container // TODO ugly hack
    rootExpression.inferDetailedSchema(#[])
    rootExpression.traverse([calculateTuples])
    container
  }

  /**
   * inferDetailedSchema
   */
  def dispatch List<Variable> inferDetailedSchema(NullaryOperator op, List<AttributeVariable> extraVariables) {
    op.extraVariables.clear
    op.extraVariables.addAll(extraVariables)
    op.defineDetailedSchema(extraVariables)
  }

  def dispatch List<Variable> inferDetailedSchema(UnaryOperator op, List<AttributeVariable> extraVariables) {
    val newExtraVariables = extractUnaryOperatorExtraVariables(op)

    val inputExtraVariables = union(extraVariables, newExtraVariables)
    op.defineDetailedSchema(inputExtraVariables)
    op.input.inferDetailedSchema(inputExtraVariables)
  }

  def dispatch List<Variable> inferDetailedSchema(BinaryOperator op, List<AttributeVariable> extraVariables) {
    op.defineDetailedSchema(extraVariables)
    
    val leftExtraVariables = extraVariables.filter[op.leftInput.schema.contains(it.element)].toList
    val rightExtraVariables = extraVariables.filter[op.rightInput.schema.contains(it.element)].toList
    
    // remove duplicates as we only need each extra variable once
    // choosing "right\left" over "left\right" is an arbitrary decision - studying, benchmarking and optimizing this is subject to future work
    rightExtraVariables.removeAll(leftExtraVariables)
    
    op.leftInput.inferDetailedSchema(leftExtraVariables)
    op.rightInput.inferDetailedSchema(rightExtraVariables)
  }

  /**
   * defineSchema
   */
  def defineDetailedSchema(Operator op, List<? extends Variable> extraVariables) {
    op.detailedSchema.addAll(op.schema)
    op.detailedSchema.addAll(extraVariables)
    op.detailedSchema
  }

  /**
   * getAttributes
   */
  def dispatch List<AttributeVariable> getAttributes(ArithmeticComparisonExpression expression) {
    Lists.newArrayList(Iterables.concat(
      getAttributes(expression.leftOperand),
      getAttributes(expression.rightOperand)
    ))
  }

  def dispatch List<AttributeVariable> getAttributes(UnaryLogicalExpression expression) {
    getAttributes(expression.operand)
  }

  def dispatch List<AttributeVariable> getAttributes(VariableExpression expression) {
    if (expression.variable instanceof AttributeVariable) {
      #[expression.variable as AttributeVariable]
    } else {
      #[]
    }
  }

  // default branch: no attributes
  def dispatch List<AttributeVariable> getAttributes(Expression expression) {
    #[]
  }

  /**
   * shorthand for creating the union of two lists
   */
  def <T> union(List<? extends T> list1, List<? extends T> list2) {
    Lists.newArrayList(Iterables.concat(list1, list2))
  }
  
  /**
   * extract extra attributes required by unary operators
   */
  def dispatch List<AttributeVariable> extractUnaryOperatorExtraVariables(ProjectionOperator op) {
    op.elements.map[expression].filter(VariableExpression).map[variable].filter(AttributeVariable).toList
  }

//  def dispatch List<AttributeVariable> extractUnaryOperatorExtraVariables(GroupingOperator op) {
//    op.entries // TODO this does not belong here
//  }

  def dispatch List<AttributeVariable> extractUnaryOperatorExtraVariables(SelectionOperator op) {
    getAttributes(op.condition)
  }

//  def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(SortOperator op) {
//    op.entries.map[variable]
//  }
//
//  def dispatch List<? extends Variable> extractUnaryOperatorExtraVariables(UnwindOperator op) {
//    #[op.sourceVariable]
//  }
  
  // rest of the unary operators - no extra requirements
  def dispatch List<AttributeVariable> extractUnaryOperatorExtraVariables(UnaryOperator op) {
    #[]
  }
  
  /**
   * traverse tree and apply a visitor method
   */
  def dispatch void traverse(NullaryOperator op, (Operator) => void visitor) {
    visitor.apply(op)
  }
  
  def dispatch void traverse(UnaryOperator op, (Operator) => void visitor) {
    op.input.traverse(visitor)
    
    visitor.apply(op)
  }
  
  def dispatch void traverse(BinaryOperator op, (Operator) => void visitor) {
    op.leftInput.traverse(visitor)
    op.rightInput.traverse(visitor)
    
    visitor.apply(op)
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
    // this is required for cases where the inferencing is invoked multiple times
    // TODO maybe we should just throw and exception for multiple executions
    op.leftMask.clear
    op.rightMask.clear
    
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
  
  
  
  
}
