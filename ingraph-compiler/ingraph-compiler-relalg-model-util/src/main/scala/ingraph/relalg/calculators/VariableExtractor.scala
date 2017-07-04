package ingraph.relalg.calculators

import com.google.common.collect.ImmutableList
import ingraph.relalg.collectors.CollectionHelper
import ingraph.relalg.expressions.ExpressionUnwrapper
import relalg._

class VariableExtractor {

  val etv = new ExpressionToVariables
  val ch  = new CollectionHelper
  val fae = new FunctionArgumentExtractor

  import scala.collection.JavaConverters._

  /**
    * Extract extra variables required by unary operators.
    */
  def extractUnaryOperatorExtraVariables(op: UnaryOperator): java.util.List[_ <: Variable] = {
    op match {
      case op: GroupingOperator => ch.uniqueUnion(getExtraVariablesForProjectionOperator(op), getExtraVariablesForGroupingOperator(op))
      case op: ProjectionOperator => getExtraVariablesForProjectionOperator(op)

      case op: SelectionOperator => etv.getAttributes(op.getCondition)
      case op: ProductionOperator =>
        op.getElements.asScala
          .map(e => e.getExpression)
          .filter(e => e.isInstanceOf[VariableExpression])
          .map(e => e.asInstanceOf[VariableExpression])
          .map(e => e.getVariable)
          .filter(e => e.isInstanceOf[AttributeVariable])
          .map(e => e.asInstanceOf[AttributeVariable])
          .toList.asJava

      case op: SortOperator =>
        op.getEntries.asScala.map(e => e.getExpression)
          .filter(e => e.isInstanceOf[VariableExpression])
          .map(e => e.asInstanceOf[VariableExpression])
          .map(e => e.getVariable)
          .toList.asJava
      case op: UnwindOperator => ImmutableList.of(op.getElement)
      case _ => ImmutableList.of()
    }

  }

  /*
   * auxiliary functions
   */
  def getExtraVariablesForProjectionOperator(op: ProjectionOperator): java.util.List[_ <: Variable] = {
    // functions
    val functionExpressions = op.getElements.asScala
      .map(e => e.getExpression)
      .filter(e => e.isInstanceOf[FunctionExpression])
      .map(e => e.asInstanceOf[FunctionExpression])
      .filter(f => !f.getFunctor.isAggregation)

    val arguments = functionExpressions.map(f => fae.extractFunctionArguments(f).asScala.toList).flatten.asJava

    val expressions = op.getElements.asScala.map(e => e.getExpression)

    // expression variables
    val extraVariables: java.util.List[_ <: Variable] = expressions
      .filter(e => e.isInstanceOf[VariableExpression])
      .map(e => e.asInstanceOf[VariableExpression])
      .map(e => e.getVariable)
      .filter(e => e.isInstanceOf[AttributeVariable])
      .map(e => e.asInstanceOf[AttributeVariable])
      .toList.asJava

    // case expressions
    val caseAttributes: java.util.List[_ <: Variable] = expressions
      .filter(e => e.isInstanceOf[CaseExpression])
      .map(e => e.asInstanceOf[CaseExpression])
      .map(e => etv.getAttributes(e).asScala.toList)
      .flatten.asJava

    ch.uniqueUnion(extraVariables, arguments, caseAttributes)
  }

  def getExtraVariablesForGroupingOperator(op: GroupingOperator): java.util.List[_ <: Variable] = {
    val externalSchemaNames = op.getExternalSchema.asScala.map(e => e.fullName)
    op.getAggregationCriteria.asScala
      .filter(e => e.isInstanceOf[ExpressionVariable])
      .map(e => e.asInstanceOf[ExpressionVariable])
      .map(e => ExpressionUnwrapper.extractExpressionVariable(e))
      .filter(e => !externalSchemaNames.contains(e.fullName))
      .toList.asJava
  }

}
