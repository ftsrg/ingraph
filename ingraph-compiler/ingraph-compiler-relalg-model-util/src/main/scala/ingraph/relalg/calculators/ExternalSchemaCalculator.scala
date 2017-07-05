package ingraph.relalg.calculators

import com.google.common.collect.{ImmutableList, ImmutableSet, Lists}
import ingraph.relalg.util.visitors.PostOrderTreeVisitor
import relalg._

/**
  * Calculates the external schema of the operators in the relational algebra tree.
  *
  * This calculation uses a postorder traversal (action are applied from the bottom to the top)
  * first it uses recursion / dispatch methods to reach the (unary) input nodes,
  * then each method returns with the inferred schema.
  *
  * For example, a join node concatenates the schema of its input nodes (left/right) and removes
  * duplicate attributes.
  */
class ExternalSchemaCalculator(val includeEdges: Boolean = true) {

  import scala.collection.JavaConverters._

  val tv = new PostOrderTreeVisitor
  val jsc = new JoinSchemaCalculator
  val factory = RelalgFactory.eINSTANCE

  def calculateExternalSchema(container: RelalgContainer): RelalgContainer = {
    if (container.isExternalSchemaInferred) {
      throw new IllegalStateException("ExternalSchemaCalculator on relalg container was already executed")
    } else {
      container.setExternalSchemaInferred(true)
    }

    tv.traverseScala(container.getRootExpression, fillExternalSchema)
    container
  }

  private def fillExternalSchema(op: Operator): java.util.List[_ <: Variable] = {
    op match {
      case op: DualObjectSourceOperator => defineExternalSchema(op, ImmutableList.of())
      case op: GetVerticesOperator => defineExternalSchema(op, ImmutableList.of(op.getVertexVariable))
      case op: GetEdgesOperator => {
        if (includeEdges) {
          defineExternalSchema(op, ImmutableList.of(op.getSourceVertexVariable, op.getEdgeVariable, op.getTargetVertexVariable))
        } else {
          defineExternalSchema(op, ImmutableList.of(op.getSourceVertexVariable, op.getTargetVertexVariable))
        }
      }

      case op: ProjectionOperator => {
        val inputSchema = op.getInput.getExternalSchema

        // check if all projected variables are in the schema
        // 1) vertices and edges
        op.getElements.asScala.map(_.getExpression)
          .filter(x => x.isInstanceOf[ElementVariable])
          .map(x => x.asInstanceOf[ElementVariable])
          .foreach (
            x => if (!inputSchema.contains(x)) {
              throw new IllegalStateException(s"Variable ${x.getName} is not part of the schema in projection operator.")
            }
          )
        // 2) attributes
        op.getElements.asScala.map(_.getExpression)
          .filter(x => x.isInstanceOf[AttributeVariable])
          .map(x => x.asInstanceOf[AttributeVariable])
          .foreach (
            x => if (!inputSchema.contains(x.getElement)) {
              throw new IllegalStateException(s"Attribute ${x.getName} cannot be projected as its vertex/edge variable does not exists.")
            }
          )

        defineExternalSchema(op, extractVariables(op.getElements))
      }

      case op: ExpandOperator => {
        val schema = Lists.newArrayList(op.getInput.getExternalSchema)

        if (includeEdges) {
          schema.add(op.getEdgeVariable)
        }
        schema.add(op.getTargetVertexVariable)
        defineExternalSchema(op, schema)
      }

      case op: UnaryOperator => {
        val schema = Lists.newArrayList(op.getInput.getExternalSchema)
        defineExternalSchema(op, schema)
      }

      case op: AbstractJoinOperator => {
        val leftInputSchema = Lists.newArrayList(op.getLeftInput.getExternalSchema)
        val rightInputSchema = Lists.newArrayList(op.getRightInput.getExternalSchema)
        val schema = jsc.calculateJoinSchema(op, leftInputSchema, rightInputSchema)
        defineExternalSchema(op, schema)

        // calculate common variables
        val rightSchemaNames = op.getRightInput.getExternalSchema.asScala.map(_.getName)
        val commonVariables = op.getLeftInput.getExternalSchema.asScala.filter(
          variable => rightSchemaNames.contains(variable.getName)
        ).asJava
        op.getCommonVariables.addAll(commonVariables)

        op.getExternalSchema
      }

      case op: UnionOperator => {
        // TODO I think this does the right thing but I am not sure - SzG
        if (op.getLeftInput.getExternalSchema.equals(op.getRightInput.getExternalSchema)) {
          throw new IllegalStateException("All sub queries in a UNION must have the same column names")
        }
        // we only keep the left schema
        defineExternalSchema(op, op.getLeftInput.getExternalSchema)
      }
    }
  }

  /**
    * defineSchema
    */
  private def defineExternalSchema(op: Operator, externalSchema: java.util.List[_ <: Variable]): java.util.List[_ <: Variable] = {
    // EObjectEList.addAll() would remove duplicates anyways,
    // but we use Guava to explicitly remove duplicates (while preserving iteration order)

    //val uniqueList = ImmutableSet.copyOf(externalSchema).asList()
    //op.getExternalSchema.addAll(uniqueList)
    op.getExternalSchema.addAll(externalSchema)
    externalSchema
  }

  private def extractVariables(expressionVariables: java.util.List[ExpressionVariable]): java.util.List[Variable] = {
    expressionVariables.asScala.map(expressionVariable =>
    if (!expressionVariable.isHasInferredName) {
      val ev2 = factory.createExpressionVariable
      ev2.setName(expressionVariable.getName)
      ev2.setNamedElementContainer(expressionVariable.getNamedElementContainer)
      ev2
    } else {
      val expr = expressionVariable.getExpression
      expr match {
        case ve: VariableExpression => ve.getVariable
        case _ => expressionVariable
      }
    }
    ).toList.asJava
  }

}
