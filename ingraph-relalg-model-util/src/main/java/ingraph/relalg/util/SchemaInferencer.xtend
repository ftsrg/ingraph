package ingraph.relalg.util

import com.google.common.collect.Iterables
import com.google.common.collect.Lists
import java.util.List
import relalg.AbstractJoinOperator
import relalg.AttributeVariable
import relalg.ElementVariable
import relalg.ExpandOperator
import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
import relalg.Operator
import relalg.ProjectionOperator
import relalg.RelalgContainer
import relalg.UnaryOperator
import relalg.UnionOperator
import relalg.Variable
import relalg.VariableExpression

/**
 * Infers the basic schema of the operators in the relational algebra tree.
 * 
 * Uses a bottom-up approach: it uses recursion / dispatch methods to reach the (unary) input nodes,
 * then each method returns with the inferred schema.
 * 
 * For example, a join node concatenates the schema of its input nodes (left/right) and removes the 
 */
class SchemaInferencer {

  val boolean includeEdges
  var RelalgContainer container

  new() {
    this.includeEdges = true
  }

  new(boolean includeEdges) {
    this.includeEdges = includeEdges
  }

  def addSchemaInformation(RelalgContainer container) {
    val rootExpression = container.getRootExpression
    this.container = container // TODO ugly hack
    rootExpression.inferSchema
    container
  }

  /**
   * inferSchema
   */
  // nullary operators
  def dispatch List<Variable> inferSchema(GetVerticesOperator op) {
    op.defineSchema(#[op.vertexVariable])
  }

  def dispatch List<Variable> inferSchema(GetEdgesOperator op) {
    if (includeEdges) {
      op.defineSchema(#[op.sourceVertexVariable, op.edgeVariable, op.targetVertexVariable])
    } else {
      op.defineSchema(#[op.sourceVertexVariable, op.targetVertexVariable])
    }
  }

  // unary operators
  def dispatch List<Variable> inferSchema(ProjectionOperator op) {
    val schema = op.getInput.inferSchema

    // check if all projected variables are in the schema
    op.elements.map[expression].filter(AttributeVariable).forEach [
      if (!schema.contains(it.element)) {
        throw new IllegalStateException("Attribute " + it.name +
          " cannot be projected as its vertex/edge variable does not exists.")
      }
    ]
    op.elements.map[expression].filter(ElementVariable).forEach [
      if (!schema.contains(it)) {
        throw new IllegalStateException("Variable " + it.name + " is not part of the schema in projection operator.")
      }
    ]

    val elementVariables = op.elements.map [
      if (expression instanceof VariableExpression) {
        (expression as VariableExpression).variable
      } else {
        throw new UnsupportedOperationException("Schema should only contain variable expressions, but found instead: " + expression)
      }
    ]
    op.defineSchema(elementVariables)
  }

  def dispatch List<Variable> inferSchema(ExpandOperator op) {
    val schema = Lists.newArrayList(op.getInput.inferSchema)
    
    if (includeEdges) {
      schema.add(op.edgeVariable)
    }
    schema.add(op.targetVertexVariable)
    op.defineSchema(schema)
  }

  // rest of the unary operators
  def dispatch List<Variable> inferSchema(UnaryOperator op) {
    op.defineSchema(op.getInput.inferSchema)
  }

  // binary operators
  def dispatch List<Variable> inferSchema(AbstractJoinOperator op) {
    val leftInputSchema = Lists.newArrayList(op.getLeftInput.inferSchema)
    val rightInputSchema = Lists.newArrayList(op.getRightInput.inferSchema)
    val schema = Lists.newArrayList(Iterables.concat(leftInputSchema, rightInputSchema))
    op.defineSchema(schema)

    // calculate common variables
    leftInputSchema.retainAll(rightInputSchema)
    op.commonVariables.addAll(leftInputSchema)

    op.schema
  }

  def dispatch List<Variable> inferSchema(UnionOperator op) {
    op.getLeftInput.inferSchema
    op.getRightInput.inferSchema

    // we only keep the left schema
    op.defineSchema(op.getLeftInput.schema)
  }

  /**
   * defineSchema
   */
  def defineSchema(Operator op, List<Variable> schema) {   
    // EObjectEList.addAll() removes duplicates
    op.schema.addAll(schema)
    schema
  }

}
