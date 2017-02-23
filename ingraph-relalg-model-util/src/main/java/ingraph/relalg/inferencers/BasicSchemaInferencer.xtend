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
import relalg.DualObjectSourceOperator

/**
 * Infers the basic schema of the operators in the relational algebra tree.
 * 
 * This inferencing uses a postorder traversal (action are applied from the bottom to the top) 
 * first it uses recursion / dispatch methods to reach the (unary) input nodes,
 * then each method returns with the inferred schema.
 * 
 * For example, a join node concatenates the schema of its input nodes (left/right) and removes the duplicate attributes. 
 */
class BasicSchemaInferencer {

  extension RelalgFactory factory = RelalgFactory.eINSTANCE
  extension PostOrderTreeVisitor treeVisitor = new PostOrderTreeVisitor
  extension JoinAttributeCalculator joinAttributeCalculator = new JoinAttributeCalculator
  val boolean includeEdges

  new() {
    this(true)
  }

  new(boolean includeEdges) {
    this.includeEdges = includeEdges
  }

  def inferBasicSchema(RelalgContainer container) {
    if (container.basicSchemaInferred) {
      throw new IllegalStateException("BasicSchemaInferencer on relalg container was already executed")
    } else {
      container.basicSchemaInferred = true
    }

    container.rootExpression.traverse([fillBasicSchema])
    container
  }

  // nullary operators
  private def dispatch List<Variable> fillBasicSchema(DualObjectSourceOperator op) {
    op.defineBasicSchema(#[])
  }

  private def dispatch List<Variable> fillBasicSchema(GetVerticesOperator op) {
    op.defineBasicSchema(#[op.vertexVariable])
  }

  private def dispatch List<Variable> fillBasicSchema(GetEdgesOperator op) {
    if (includeEdges) {
      op.defineBasicSchema(#[op.sourceVertexVariable, op.edgeVariable, op.targetVertexVariable])
    } else {
      op.defineBasicSchema(#[op.sourceVertexVariable, op.targetVertexVariable])
    }
  }

  // unary operators
  private def dispatch List<Variable> fillBasicSchema(ProjectionOperator op) {
    val schema = op.input.basicSchema

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
    op.defineBasicSchema(elementVariables)
  }

  private def dispatch List<Variable> fillBasicSchema(ExpandOperator op) {    
    val schema = Lists.newArrayList(op.input.basicSchema)
    
    if (includeEdges) {
      schema.add(op.edgeVariable)
    }
    schema.add(op.targetVertexVariable)
    op.defineBasicSchema(schema)
  }

  // rest of the unary operators
  private def dispatch List<Variable> fillBasicSchema(UnaryOperator op) {
    val schema = Lists.newArrayList(op.input.basicSchema)
    op.defineBasicSchema(schema)
  }

  // binary operators
  private def dispatch List<Variable> fillBasicSchema(AbstractJoinOperator op) {
    val leftInputSchema = Lists.newArrayList(op.leftInput.basicSchema)
    val rightInputSchema = Lists.newArrayList(op.rightInput.basicSchema)
    val schema = calculateJoinAttributes(op, leftInputSchema, rightInputSchema)
    op.defineBasicSchema(schema)

    // calculate common variables
    leftInputSchema.retainAll(rightInputSchema)
    op.commonVariables.addAll(leftInputSchema)

    op.basicSchema
  }

  private def dispatch List<Variable> fillBasicSchema(UnionOperator op) {
    // we only keep the left schema
    op.defineBasicSchema(op.getLeftInput.basicSchema)
  }

  // ternary operators
  private def dispatch List<Variable> fillBasicSchema(PathOperator op) {
    val schema = Lists.newArrayList(Iterables.concat(
      op.leftInput.basicSchema,
      op.middleInput.basicSchema,
      op.rightInput.basicSchema  
    ))
    
    val listExpressionVariable = createExpressionVariable => [
      expression = op.getListVariable
    ]
    
    if (includeEdges) {
      schema.add(listExpressionVariable)
    }
    schema.add(op.targetVertexVariable)
    op.defineBasicSchema(schema)
  }

  /**
   * defineSchema
   */
  private def defineBasicSchema(Operator op, List<Variable> basicSchema) {   
    // EObjectEList.addAll() removes duplicates
    op.basicSchema.addAll(basicSchema)
    basicSchema
  }

}
