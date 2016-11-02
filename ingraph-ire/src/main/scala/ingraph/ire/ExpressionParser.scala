package ingraph.ire

import hu.bme.mit.ire.TupleType
import relalg._

object ExpressionParser {
  def parse(expression: Expression): (TupleType) => Boolean =
    expression match {
      case exp: BinaryExpression =>
        exp match {
          case exp: ArithmeticComparisonExpression =>
            def left: (TupleType) => Any = parseComparable(exp.getLeftOperand, _)
            def right: (TupleType) => Any = parseComparable(exp.getRightOperand, _)
            import ArithmeticComparisonOperator._
            exp.getOperator match {
              case EQUAL_TO => (t: TupleType) => left(t) == right(t)
              case NOT_EQUAL_TO => (t: TupleType) => left(t) != right(t)
              case LESS_THAN_OR_EQUAL => (t: TupleType) => left(t) match {
                case l: Double => right(t) match {
                  case r: Double => l <= r
                  case r: Integer => l <= r
                }
                case l: Integer => right(t) match {
                  case r: Double => l <= r
                  case r: Integer => l <= r
                }
              }
              case LESS_THAN => (t: TupleType) => left(t) match {
                case l: Double => right(t) match {
                  case r: Double => l < r
                  case r: Integer => l < r
                }
                case l: Integer => right(t) match {
                  case r: Double => l < r
                  case r: Integer => l < r
                }
              }
              case GREATER_THAN_OR_EQUAL => (t: TupleType) => left(t) match {
                case l: Double => right(t) match {
                  case r: Double => l >= r
                  case r: Integer => l >= r
                }
                case l: Integer => right(t) match {
                  case r: Double => l >= r
                  case r: Integer => l >= r
                }
              }
              case GREATER_THAN => (t: TupleType) => left(t) match {
                case l: Double => right(t) match {
                  case r: Double => l > r
                  case r: Integer => l > r
                }
                case l: Integer => right(t) match {
                  case r: Double => l > r
                  case r: Integer => l > r
                }
              }
            }
        }
    }

  private def parseComparable(cmp: ComparableElement, tuple: TupleType) = cmp match {
    case cmp: DoubleLiteral => cmp.getValue
    case cmp: IntegerLiteral => cmp.getValue
    case cmp: StringLiteral => cmp.getValue
    case cmp: AttributeVariable => tuple(cmp.getElement.getName + "_" + cmp.getName)
    case cmp: Variable => tuple(cmp.getName)
  }

}
