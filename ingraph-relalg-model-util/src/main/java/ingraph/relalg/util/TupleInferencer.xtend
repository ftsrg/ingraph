package ingraph.relalg.util

import com.google.common.collect.Iterables
import com.google.common.collect.Lists
import java.util.List
import relalg.ArithmeticComparisonExpression
import relalg.AttributeVariable
import relalg.BinaryOperator
import relalg.ExpandOperator
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
import relalg.Variable

class TupleInferencer {

  var RelalgContainer container

  def inferTuples(RelalgContainer container) {
    // TODO
  }

  def addDetailedSchemaInformation(RelalgContainer container) {
    val rootExpression = container.getRootExpression
    this.container = container // TODO ugly hack
    rootExpression.inferDetailedSchema(#[])
    container
  }

  /**
   * inferSchema
   */
  // nullary operators
  def dispatch List<Variable> inferDetailedSchema(NullaryOperator op, List<? extends Variable> extraVariables) {
    op.defineDetailedSchema(extraVariables)
  }

  // unary operators
  def dispatch List<Variable> inferDetailedSchema(ProjectionOperator op, List<? extends Variable> extraVariables) {
    val List<AttributeVariable> newExtraVariables = Lists.newArrayList(
      op.elements.map[expression].filter(AttributeVariable))

    op.defineDetailedSchema(newExtraVariables)
    op.input.inferDetailedSchema(newExtraVariables)
  }

  def dispatch List<Variable> inferDetailedSchema(ExpandOperator op, List<? extends Variable> extraVariables) {
    op.input.inferDetailedSchema(#[])
    // TODO add all attributes used in the expression
    op.defineDetailedSchema(#[])
  }

  def dispatch List<Variable> inferDetailedSchema(GroupingOperator op, List<? extends Variable> extraVariables) {
    op.input.inferDetailedSchema(#[])
    // TODO add all attributes used in the grouping op
    op.defineDetailedSchema(#[])
  }

  def dispatch List<Variable> inferDetailedSchema(SelectionOperator op, List<? extends Variable> extraVariables) {
    val attributesInCondition = getAttributes(op.condition)
    val inputExtraVariables = union(extraVariables, attributesInCondition)

    op.defineDetailedSchema(inputExtraVariables)
    op.input.inferDetailedSchema(inputExtraVariables)
  }

  def dispatch List<Variable> inferDetailedSchema(SortOperator op, List<? extends Variable> extraVariables) {
    val attributesForSort = op.entries.map[variable]
    
    op.input.inferDetailedSchema(union(extraVariables, attributesForSort))
    op.defineDetailedSchema(extraVariables)
  }

  // rest of the unary operators - just pass on the schema
  def dispatch List<Variable> inferDetailedSchema(UnaryOperator op, List<? extends Variable> extraVariables) {
    op.input.inferDetailedSchema(#[])
    op.defineDetailedSchema(#[])
  }

  // binary operators
  def dispatch List<Variable> inferDetailedSchema(BinaryOperator op, List<? extends Variable> extraVariables) {
    op.leftInput.inferDetailedSchema(extraVariables)
    op.rightInput.inferDetailedSchema(extraVariables)
    op.defineDetailedSchema(extraVariables)
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
}
