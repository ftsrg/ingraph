package ingraph.cypher2relalg.util

import relalg.AttributeVariable
import relalg.Expression
import relalg.ExpressionVariable
import relalg.NamedElement
import relalg.VariableExpression

/**
 * Given an Expression, infers its name.
 *
 * Variables and aliases return expressions has their name,
 * but for e.g. expressions like '2 + 3',
 * there should be some deterministic way to assign a name.
 */
class ExpressionNameInferencer {
  static var n=0;
  def dispatch static String inferName(ExpressionVariable e, IngraphLogger logger) {
    if (!e.dontCare) {
      e.name
    } else {
      inferName(e.expression, logger)
    }
  }

  def dispatch static String inferName(AttributeVariable e, IngraphLogger logger) {
    e.element.name + '.' + e.name
  }

  def dispatch static String inferName(NamedElement e, IngraphLogger logger) {
    e.name
  }

  def dispatch static String inferName(VariableExpression e, IngraphLogger logger) {
    inferName(e.variable, logger)
  }

  def dispatch static String inferName(Expression e, IngraphLogger logger) {
    logger.warning('''Don't know kow to assign name to an expression of type «e.class.name», please assign an alias ot the corresponding return clause. For now, we return a generated unique name.''')
    '''_iname«n++»'''
  }
}
