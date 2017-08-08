package ingraph.relalg.calculators

import java.util

import ingraph.relalg.collectors.CollectionHelper
import ingraph.relalg.util.visitors.PostOrderTreeVisitor
import relalg._

/**
  * Calculates the internal schema that consists of the external schema plus the extra variables.
  */
class InternalSchemaCalculator {

  import scala.collection.JavaConverters._

  val tv = new PostOrderTreeVisitor
  val jsc = new JoinSchemaCalculator
  val ch = new CollectionHelper
  val maskCalculator = new MaskCalculator
  val factory = RelalgFactory.eINSTANCE

  def calculateInternalSchema(container: RelalgContainer) {
    if (!container.isExtraVariablesInferred) {
      throw new IllegalStateException("ExtraVariablesCalculator must be executed before InternalSchemaCalculator")
    } else if (container.isInternalSchemaInferred) {
      throw new IllegalStateException("InternalSchemaCalculator on relalg container was already executed")
    } else {
      container.setInternalSchemaInferred(true)
    }

    tv.traverseScala(container.getRootExpression, fillInternalSchema)
    tv.traverseScala(container.getRootExpression, maskCalculator.calculateTuples)

    container
  }

  private def fillInternalSchema(op: Operator) {
    op match {
      case op: NullaryOperator => {
        val internalSchema = ch.uniqueUnion(op.getExternalSchema, op.getExtraVariables)
        defineInternalSchema(op, internalSchema)
      }
      case op: UnaryOperator => {
        val internalSchema = op.getInput.getInternalSchema
        defineInternalSchema(op, internalSchema)
      }
      case op: UnionOperator => {
        // TODO left/right inputs should be the same for their external schema
        val internalSchema = op.getLeftInput.getInternalSchema
        defineInternalSchema(op, internalSchema)
      }
      case op: AbstractJoinOperator => {
        val internalSchema = jsc.calculateJoinSchema(op, op.getLeftInput.getInternalSchema, op.getRightInput.getInternalSchema)
        defineInternalSchema(op, internalSchema)
      }
    }
  }

  private def defineInternalSchema(op: Operator, internalSchema: java.util.List[_ <: Variable]) {
    op match {
      case op: ProductionOperator => {
        val internalSchemaNames = internalSchema.asScala.map(_.getName())

        val x: util.List[_ <: ExpressionVariable] = op.getExternalSchema.asScala.map(
          element => {
            val ve = factory.createVariableExpression
            ve.setVariable(element)
            ve.setExpressionContainer(element.getNamedElementContainer)

            val ev = factory.createExpressionVariable
            ev.setExpression(ve)
            ev.setHasInferredName(true)
            ev.setNamedElementContainer(element.getNamedElementContainer)
            ev
          }
        ).asJava
        op.getElements.addAll(x)
        op.getInternalSchema.addAll(op.getExternalSchema)

        // this projects "-1" in a number of cases, e.g. if a literal value was assigned to the variables
        val y: util.List[java.lang.Integer] = op.getInternalSchema.asScala.map(
          variable => internalSchemaNames.indexOf(variable.getName)
          ).map( i => i:java.lang.Integer).asJava
        op.getTupleIndices.addAll(y)
      }

      case op: GroupingOperator => {
        defineInternalSchemaForProjectionOperator(op, internalSchema)
        defineInternalSchemaForGroupingOperator(op, internalSchema)
      }

      case op: ProjectionOperator => {
        defineInternalSchemaForProjectionOperator(op, internalSchema)
      }

      case op: ExpandOperator => {
        op.getInternalSchema.addAll(ch.uniqueUnion(op.getExternalSchema, op.getExtraVariables))
      }

      case _ => op.getInternalSchema.addAll(internalSchema)
    }
  }

  private def defineInternalSchemaForProjectionOperator(op: ProjectionOperator, internalSchema: java.util.List[_ <: Variable]) {
    val internalSchemaNames = internalSchema.asScala.map(_.getName)

    op.getInternalSchema.addAll(ch.uniqueUnion(op.getExternalSchema, op.getExtraVariables))

    op.getInternalElements.addAll(op.getElements)
    op.getInternalElements.addAll(op.getExtraVariables.asScala.map(

      element => {
        val ve = factory.createVariableExpression
        ve.setVariable(element)
        ve.setExpressionContainer(element.getNamedElementContainer)

        val ev = factory.createExpressionVariable
        ev.setHasInferredName(true)
        ev.setNamedElementContainer(element.getNamedElementContainer)
        ev.setExpression(ve)

        ev
      }
    ).asJava)
    op.getTupleIndices.addAll(
      op.getInternalSchema.asScala.map(
        variable => internalSchemaNames.indexOf(variable.getName)
      ).map( i => i:java.lang.Integer).asJava
    )
  }

  private def defineInternalSchemaForGroupingOperator(op: GroupingOperator, internalSchema: java.util.List[_ <: Variable]) {
    op.getAggregationCriteria.addAll(
      op.getExtraVariables.asScala.map(
      extraVariable => {
        val ve = factory.createVariableExpression
        ve.setVariable(extraVariable)
        ve.setExpressionContainer(extraVariable.getNamedElementContainer)
        ve
      }
      ).asJava
    )

    op.getElements.addAll(
      op.getExtraVariables.asScala.map(
        extraVariable => {
          val ve = factory.createVariableExpression
          ve.setVariable(extraVariable)
          ve.setExpressionContainer(extraVariable.getNamedElementContainer)

          val ev = factory.createExpressionVariable
          ev.setNamedElementContainer(extraVariable.getNamedElementContainer)
          ev.setHasInferredName(true)
          ev.setExpression(ve)
          ev
        }
      ).asJava
    )
  }

}
