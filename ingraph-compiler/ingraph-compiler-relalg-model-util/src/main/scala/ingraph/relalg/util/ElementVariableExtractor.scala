package ingraph.relalg.util

import relalg._

class ElementVariableExtractor {

  /**
    * Finds and extracts (returns) the enclosed ElementVariable instance.
    * If not found, null is returned.
    *
    * For a VertexVariable and EdgeVariable, this is idempotent, i.e. returns the variable instance itself.
    *
    * For an ExpressionVariable, see if it wraps a VertexVariable or EdgeVariable, and if so, returns that variable.
    * Note, that this wrapping can be deep like
    * (ExpressionVariable wraps VariableExpression wraps)+ ElementVariable
    */
  def extractElementVariable(v: Variable): ElementVariable = {
    v match {
      case v: VertexVariable => v
      case e: EdgeVariable => e
      case ev: ExpressionVariable => {
        val exp = ev.getExpression
        exp match {
          case exp: VariableExpression => extractElementVariable(exp.getVariable)
          case _ => throw new IllegalStateException("Cannot extract elementVariable")
        }
      }
    }
  }

}
