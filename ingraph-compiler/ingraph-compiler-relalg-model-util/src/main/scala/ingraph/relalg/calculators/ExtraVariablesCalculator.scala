package ingraph.relalg.calculators

import com.google.common.collect.ImmutableList
import ingraph.relalg.collectors.CollectionHelper
import relalg._

/**
  * Calculates extra variables.
  *
  * For example, a projection may need extra variables for projecting attributes
  * or a selection may need extra attributes for evaluating conditions.
  */
class ExtraVariablesCalculator {

  val ve = new VariableExtractor
  val ch = new CollectionHelper
  val evp = new ExtraVariablesPropagator

  def calculateExtraVariables(container: RelalgContainer) {
    if (!container.isExternalSchemaInferred) {
      throw new IllegalStateException("ExternalSchemaCalculator must be executed before ExtraVariableCalculator")
    } else if (container.isExtraVariablesInferred) {
      throw new IllegalStateException("ExtraVariablesCalculator on relalg container was already executed")
    } else {
      container.setExtraVariablesInferred(true)
    }

    fillExtraVariables(container.getRootExpression, ImmutableList.of())
    container
  }

  def fillExtraVariables(op: Operator, extraVariables: java.util.List[Variable]) {
    op.getExtraVariables.addAll(extraVariables)

    op match {
      case op: UnaryOperator => {
        val newExtraVariables = ve.extractUnaryOperatorExtraVariables(op)
        var inputExtraVariables = ch.uniqueUnion(extraVariables, newExtraVariables) //, getCalculatedVariables . . .
        val propagatedInputExtraVariables = evp.propagateTo(inputExtraVariables, op.getInput)

        fillExtraVariables(op.getInput, propagatedInputExtraVariables)
      }
      case op: UnionOperator => {
        fillExtraVariables(op.getLeftInput, extraVariables)
        fillExtraVariables(op.getRightInput, extraVariables)
      }
      case op: AbstractJoinOperator => {
        // Calculate left and right extra variables -- we only need each extra variable once.
        // For variables that could be propagated to both sides,
        // we choose the left side as it works for both equijoin and antijoin operators.
        val leftExtraVariables = evp.propagateTo(extraVariables, op.getLeftInput)
        val rightExtraVariables = evp.propagateTo(extraVariables, op.getRightInput)
        rightExtraVariables.removeAll(leftExtraVariables)

        fillExtraVariables(op.getLeftInput, leftExtraVariables)
        fillExtraVariables(op.getRightInput, rightExtraVariables)
      }
      case _ => ;
    }

  }
}
