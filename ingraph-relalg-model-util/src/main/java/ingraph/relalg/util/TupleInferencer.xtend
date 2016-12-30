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
  def dispatch List<Variable> inferDetailedSchema(NullaryOperator op, List<? extends Variable> extraVariables) {
    op.defineDetailedSchema(extraVariables)
  }

  def dispatch List<Variable> inferDetailedSchema(UnaryOperator op, List<? extends Variable> extraVariables) {
    val newExtraVariables = extractUnaryOperatorExtraVariables(op)

    val inputExtraVariables = union(extraVariables, newExtraVariables)
    op.defineDetailedSchema(inputExtraVariables)
    op.input.inferDetailedSchema(inputExtraVariables)
  }

  def dispatch List<Variable> inferDetailedSchema(BinaryOperator op, List<? extends Variable> extraVariables) {
    op.defineDetailedSchema(extraVariables)
    op.leftInput.inferDetailedSchema(extraVariables)
    op.rightInput.inferDetailedSchema(extraVariables)
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
    getAttributes(expression.leftOperand)
  }

  def dispatch List<AttributeVariable> getAttributes(AttributeVariable expression) {
    #[expression]
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
   * extract extra variables required by unary operators
   */
  def dispatch extractUnaryOperatorExtraVariables(ProjectionOperator op) {
    op.elements.map[expression].filter(AttributeVariable).toList
  }

  def dispatch extractUnaryOperatorExtraVariables(GroupingOperator op) {
    op.entries.map[variable]  }

  def dispatch extractUnaryOperatorExtraVariables(SelectionOperator op) {
    getAttributes(op.condition)
  }

  def dispatch extractUnaryOperatorExtraVariables(SortOperator op) {
    op.entries.map[variable]
  }

  def dispatch extractUnaryOperatorExtraVariables(UnwindOperator op) {
    #[op.sourceVariable]
  }
  
  // rest of the unary operators - no extra requirements
  def dispatch extractUnaryOperatorExtraVariables(UnaryOperator op) {
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
    //val indices = op.schemaToMap
    
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
