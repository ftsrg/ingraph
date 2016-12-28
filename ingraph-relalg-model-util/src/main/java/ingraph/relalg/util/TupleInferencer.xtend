package ingraph.relalg.util

import java.util.List
import relalg.AbstractJoinOperator
import relalg.ExpandOperator
import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
import relalg.Operator
import relalg.ProjectionOperator
import relalg.RelalgContainer
import relalg.UnaryOperator
import relalg.UnionOperator
import relalg.Variable
import relalg.GroupingOperator
import relalg.SortOperator

class TupleInferencer {

  var RelalgContainer container

  def inferTuples(RelalgContainer container) {
    //addDetailedSchemaInformation()
  }

  def addDetailedSchemaInformation(RelalgContainer container) {
    val rootExpression = container.getRootExpression
    this.container = container // TODO ugly hack
    rootExpression.inferDetailedSchema
    container
  }

  /**
   * inferSchema
   */
  // nullary operators
  def dispatch List<Variable> inferDetailedSchema(GetVerticesOperator op) {
    op.defineDetailedSchema(#[])
  }

  def dispatch List<Variable> inferDetailedSchema(GetEdgesOperator op) {
    op.defineDetailedSchema(#[])
  }

  // unary operators
  def dispatch List<Variable> inferDetailedSchema(ProjectionOperator op) {
    op.input.inferDetailedSchema
    // TODO add all attributes used in the projection
    op.defineDetailedSchema(#[])
  }

  def dispatch List<Variable> inferDetailedSchema(ExpandOperator op) {
    op.input.inferDetailedSchema
    // TODO add all attributes used in the expression
    op.defineDetailedSchema(#[])
  }

  def dispatch List<Variable> inferDetailedSchema(GroupingOperator op) {
    op.input.inferDetailedSchema
    // TODO add all attributes used in the grouping op
    op.defineDetailedSchema(#[])
  }

  def dispatch List<Variable> inferDetailedSchema(SortOperator op) {
    op.input.inferDetailedSchema
    // TODO add all attributes used in the sort op
    op.defineDetailedSchema(#[])
  }
  
  // rest of the unary operators - they 
  def dispatch List<Variable> inferDetailedSchema(UnaryOperator op) {
    op.input.inferDetailedSchema
    op.defineDetailedSchema(#[])
  }

  // binary operators
  def dispatch List<Variable> inferDetailedSchema(AbstractJoinOperator op) {
    op.leftInput.inferDetailedSchema
    op.rightInput.inferDetailedSchema
    op.defineDetailedSchema(#[])
  }

  def dispatch List<Variable> inferDetailedSchema(UnionOperator op) {
    op.leftInput.inferDetailedSchema
    op.rightInput.inferDetailedSchema
    op.defineDetailedSchema(#[])
  }

  /**
   * defineSchema
   */
  def defineDetailedSchema(Operator op, List<Variable> schema) {
    op.detailedSchema.addAll(op.schema)
    schema
  }

}
