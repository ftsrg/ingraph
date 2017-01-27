package ingraph.relalg.inferencers

import com.google.common.collect.Iterables
import com.google.common.collect.Lists
import ingraph.relalg.calculators.JoinAttributeCalculator
import ingraph.relalg.util.visitors.PostOrderTreeVisitor
import java.util.List
import relalg.AbstractJoinOperator
import relalg.AttributeVariable
import relalg.ElementVariable
import relalg.ExpandOperator
import relalg.GetEdgesOperator
import relalg.GetVerticesOperator
import relalg.Operator
import relalg.PathOperator
import relalg.ProjectionOperator
import relalg.RelalgContainer
import relalg.RelalgFactory
import relalg.UnaryOperator
import relalg.UnionOperator
import relalg.Variable
import relalg.VariableExpression

/**
 * Infers the basic schema of the operators in the relational algebra tree.
 * 
 * This inferencing uses a postorder traversal (action are applied from the bottom to the top) 
 * first it uses recursion / dispatch methods to reach the (unary) input nodes,
 * then each method returns with the inferred schema.
 * 
 * For example, a join node concatenates the schema of its input nodes (left/right) and removes the duplicate attributes. 
 */
class SchemaInferencer {

  extension RelalgFactory factory = RelalgFactory.eINSTANCE
  extension PostOrderTreeVisitor treeVisitor = new PostOrderTreeVisitor
  extension JoinAttributeCalculator joinAttributeCalculator = new JoinAttributeCalculator
  val boolean includeEdges
  var RelalgContainer container

  new() {
    this.includeEdges = true
  }

  new(boolean includeEdges) {
    this.includeEdges = includeEdges
  }

  def addSchemaInformation(RelalgContainer container) {
    if (container.schemaInferencingCompleted) {
      throw new IllegalStateException("Schema inferencing on relalg container was already performed")
    } else {
      container.schemaInferencingCompleted = true
    }
     
    this.container = container
    container.rootExpression.traverse([inferSchema])
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
    val schema = op.input.schema

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
    val schema = Lists.newArrayList(op.input.schema)
    
    if (includeEdges) {
      schema.add(op.edgeVariable)
    }
    schema.add(op.targetVertexVariable)
    op.defineSchema(schema)
  }

  // rest of the unary operators
  def dispatch List<Variable> inferSchema(UnaryOperator op) {
    val schema = Lists.newArrayList(op.input.schema)
    op.defineSchema(schema)
  }

  // binary operators
  def dispatch List<Variable> inferSchema(AbstractJoinOperator op) {
    val leftInputSchema = Lists.newArrayList(op.leftInput.schema)
    val rightInputSchema = Lists.newArrayList(op.rightInput.schema)
    val schema = calculateJoinAttributes(op, leftInputSchema, rightInputSchema)
    op.defineSchema(schema)

    // calculate common variables
    leftInputSchema.retainAll(rightInputSchema)
    op.commonVariables.addAll(leftInputSchema)

    op.schema
  }

  def dispatch List<Variable> inferSchema(UnionOperator op) {
    // we only keep the left schema
    op.defineSchema(op.getLeftInput.schema)
  }

  // ternary operators
  def dispatch List<Variable> inferSchema(PathOperator op) {
    val schema = Lists.newArrayList(Iterables.concat(
      op.leftInput.schema,
      op.middleInput.schema,
      op.rightInput.schema  
    ))
    
    val listExpressionVariable = createExpressionVariable => [
      expression = op.getListVariable
    ]
    
    if (includeEdges) {
      schema.add(listExpressionVariable)
    }
    schema.add(op.targetVertexVariable)
    op.defineSchema(schema)
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
