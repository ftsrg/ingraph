package ingraph.relalg.calculators

import ingraph.relalg.collectors.CollectionHelper
import ingraph.relalg.util.ElementVariableExtractor
import relalg._

class ExtraVariablesPropagator {

  val collectionHelper = new CollectionHelper
  val elementVariableExtractor = new ElementVariableExtractor

  import scala.collection.JavaConverters._

  def propagateTo(extraVariables: java.util.List[Variable], inputOp: Operator): java.util.List[Variable] = {
    val inputSchemaNames = inputOp.getExternalSchema.asScala.map(_.fullName)

    val attributes = extraVariables.asScala //
      .filter(_.isInstanceOf[AttributeVariable]) //
      .map(_.asInstanceOf[AttributeVariable]) //
      .filter(a => !inputSchemaNames.contains( a.fullName ) && // do not propagate if it is already there
        // TODO check this for joins - e.g. if it is available on the left input, do not propagate to the right
        inputSchemaNames.contains( elementVariableExtractor.extractElementVariable(a.getBaseVariable).fullName )
      )
      .asJava
    val functions = extraVariables.asScala
      .filter(_.isInstanceOf[ExpressionVariable])
      .map(_.asInstanceOf[ExpressionVariable])
      .filter(a => a.getExpression.isInstanceOf[FunctionExpression]) // TODO this should involve a decision
      .filter(a => !inputSchemaNames.contains( a.fullName ))
      .asJava

    return collectionHelper.uniqueUnion(attributes, functions)
  }

}
