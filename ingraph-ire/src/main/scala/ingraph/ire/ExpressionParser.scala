package ingraph.ire

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.util.GenericMath
import ingraph.relalg.util.SchemaToMap
import relalg._

object ExpressionParser {
  def parse(expression: Expression, lookup: Map[Variable, Integer]): (Tuple) => Boolean =
    expression match {
      case exp: UnaryLogicalExpression =>
        val operand: (Tuple => Boolean) = parse(exp.getLeftOperand, lookup)
        import UnaryLogicalOperatorType._
        exp.getOperator match {
          case NOT => (t: Tuple) => !operand(t)
        }

      case exp: BinaryLogicalExpression =>
        val left: (Tuple => Boolean) = parse(exp.getLeftOperand, lookup)
        val right: (Tuple => Boolean) = parse(exp.getRightOperand, lookup)
        import BinaryLogicalOperatorType._
        exp.getOperator match {
          case AND => (t: Tuple) => left(t) && right(t)
          case OR => (t: Tuple) => left(t) || right(t)
          case XOR => (t: Tuple) => left(t) ^ right(t)
        }

      case exp: ArithmeticOperationExpression =>
        import BinaryArithmeticOperatorType._
        val left: (Tuple => Boolean) = parse(exp.getLeftOperand, lookup)
        val right: (Tuple => Boolean) = parse(exp.getRightOperand, lookup)
        exp.getOperator match {
          case PLUS => (t: Tuple) => left(t)
          case MINUS => (t: Tuple) => left(t)
          case MULTIPLICATION => (t: Tuple) => left(t)
          case DIVISION => (t: Tuple) => left(t)
          case POWER => (t: Tuple) => left(t)
        }
      case exp: ArithmeticComparisonExpression =>
        def left: (Tuple) => Any = parseComparable(exp.getLeftOperand, _, lookup)

        def right: (Tuple) => Any = parseComparable(exp.getRightOperand, _, lookup)
        import ArithmeticComparisonOperatorType._
        exp.getOperator match {
          case EQUAL_TO => (t: Tuple) => left(t) == right(t)
          case NOT_EQUAL_TO => (t: Tuple) => left(t) != right(t)
          case LESS_THAN_OR_EQUAL => (t: Tuple) => GenericMath.compare(left(t), right(t)) <= 0
          case LESS_THAN => (t: Tuple) => GenericMath.compare(left(t), right(t)) < 0
          case GREATER_THAN_OR_EQUAL => (t: Tuple) => GenericMath.compare(left(t), right(t)) >= 0
          case GREATER_THAN => (t: Tuple) => GenericMath.compare(left(t), right(t)) > 0
        }
    }


  private def parseComparable(cmp: ComparableExpression, tuple: Tuple, lookup: Map[Variable, Integer]) = cmp match {
    case cmp: DoubleLiteral => cmp.getValue
    case cmp: IntegerLiteral => cmp.getValue
    case cmp: StringLiteral => cmp.getValue
    case cmp: AttributeVariable =>  tuple(lookup(cmp)) //TODO
    case cmp: Variable => tuple(lookup(cmp)) //TODO
    case cmp: VariableComparableExpression => tuple(lookup(cmp.getVariable))
  }

}
