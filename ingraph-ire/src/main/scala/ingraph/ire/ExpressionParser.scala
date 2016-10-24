package ingraph.ire

import hu.bme.mit.incqueryds._
import relalg._

object ExpressionParser {
  def parse(expression: Expression): (nodeType) => Boolean =
    expression match {
      case exp: BinaryExpression =>
        println("parsed")
        exp match {
          case exp: ArithmeticComparisonExpression =>
            def left: (nodeType) => Any = parseComparable(exp.getLeftOperand, _)
            def right: (nodeType) => Any = parseComparable(exp.getRightOperand, _)
            import ArithmeticComparisonOperator._
            exp.getOperator match {
              case EQUAL_TO => (t: nodeType) => left(t) == right(t)
              case NOT_EQUAL_TO => (t: nodeType) => left(t) != right(t)
              case LESS_THAN_OR_EQUAL => (t: nodeType) => left(t) match {
                case l: Double => right(t) match {
                  case r: Double => l <= r
                  case r: Integer => l <= r
                }
                case l: Integer => right(t) match {
                  case r: Double => l <= r
                  case r: Integer => l <= r
                }
              }
            }
        }
    }

  private def parseComparable(cmp: ComparableElement, tuple: nodeType) = cmp match {
    case cmp: DoubleLiteral => cmp.getValue
    case cmp: IntegerLiteral => cmp.getValue
    case cmp: StringLiteral => cmp.getValue
    case cmp: AttributeVariable => tuple(cmp.getElement.getName + "_" + cmp.getName)
    case cmp: Variable => tuple(cmp.getName)
  }

}
