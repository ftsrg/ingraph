package ingraph.relalg.inferencers

import com.google.common.collect.Iterables
import com.google.common.collect.Lists
import ingraph.relalg.calculators.JoinAttributeCalculator
import ingraph.relalg.calculators.ListUnionCalculator
import ingraph.relalg.calculators.MaskCalculator
import ingraph.relalg.util.visitors.PostOrderTreeVisitor
import java.util.List
import relalg.AbstractJoinOperator
import relalg.NullaryOperator
import relalg.Operator
import relalg.RelalgContainer
import relalg.TernaryOperator
import relalg.UnaryOperator
import relalg.UnionOperator
import relalg.Variable

class DetailedSchemaInferencer {

  extension PostOrderTreeVisitor treeVisitor = new PostOrderTreeVisitor
  extension JoinAttributeCalculator joinAttributeCalculator = new JoinAttributeCalculator
  extension ListUnionCalculator listUnionCalculator = new ListUnionCalculator
  extension MaskCalculator maskCalculator = new MaskCalculator
  var RelalgContainer container

  def addDetailedSchemaInformation(RelalgContainer container) {
    if (container.detailedSchemaInferencingCompleted) {
      throw new IllegalStateException("DetailedSchemaInferencer on relalg container was already executed")
    } else {
      container.detailedSchemaInferencingCompleted = true
    }

    this.container = container
    container.rootExpression.traverse([inferDetailedSchema])
    container.rootExpression.traverse([calculateTuples])
    container
  }

  /**
   * inferDetailedSchema
   */
  def dispatch void inferDetailedSchema(NullaryOperator op) {
    val detailedSchema = union(op.schema, op.extraVariables)
    op.defineDetailedSchema(detailedSchema)
  }

  def dispatch void inferDetailedSchema(UnaryOperator op) {
    val detailedSchema = union(op.schema, op.input.extraVariables)
    op.defineDetailedSchema(detailedSchema)
  }

  def dispatch void inferDetailedSchema(UnionOperator op) {
    throw new UnsupportedOperationException("Union not yet supported")
  }

  def dispatch void inferDetailedSchema(AbstractJoinOperator op) {
    val schema = calculateJoinAttributes(op, op.getLeftInput.detailedSchema, op.getRightInput.detailedSchema)
    op.defineDetailedSchema(schema)
  }

  def dispatch void inferDetailedSchema(TernaryOperator op) {
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

}
