package ingraph.ire

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.util.GenericMath
import relalg._

object ExpressionParser {
  def parse(expression: Expression, lookup: Map[Variable, Integer]): (Tuple) => Boolean =
    expression match {
      case exp: UnaryLogicalExpression =>
        import UnaryLogicalOperatorType._
        exp.getOperator match {
          case NOT => (t: Tuple) =>
            val operand: (Tuple => Boolean) = parse(exp.getLeftOperand, lookup)
            !operand(t)
          case IS_NULL =>
            def left: (Tuple) => Any = parseComparable(exp.getLeftOperand.asInstanceOf[ComparableExpression], _, lookup)
            left(_) == null
          case IS_NOT_NULL =>
            def left: (Tuple) => Any = parseComparable(exp.getLeftOperand.asInstanceOf[ComparableExpression], _, lookup)
            left(_) != null
        }
      case exp: UnaryGraphObjectLogicalExpression =>
        import UnaryGraphObjectLogicalOperatorType._
        def left: (Tuple) => Any = parseVariable(exp.getLeftOperand, _, lookup)
        exp.getOperator match {
          case IS_NULL =>
            left(_) == null
          case IS_NOT_NULL =>
            left(_) != null
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

  private def parseVariable(variable: Variable, tuple: Tuple, lookup: Map[Variable, Integer]): Any =
    tuple(lookup(variable))

  private def parseComparable(cmp: ComparableExpression, tuple: Tuple, lookup: Map[Variable, Integer]): Any = cmp match {
    case cmp: DoubleLiteral => cmp.getValue
    case cmp: IntegerLiteral => cmp.getValue
    case cmp: StringLiteral => cmp.getValue
    case cmp: AttributeVariable =>  tuple(lookup(cmp)) //TODO
    case cmp: Variable => tuple(lookup(cmp)) //TODO
    case cmp: VariableComparableExpression => tuple(lookup(cmp.getVariable))
    case exp: ArithmeticOperationExpression =>
      // TODO: currently the emf tree is parsed on every function call
      val left = parseComparable(exp.getLeftOperand, tuple, lookup)
      val right = parseComparable(exp.getRightOperand, tuple, lookup)
      import BinaryArithmeticOperatorType._
      exp.getOperator match {
        case PLUS => GenericMath.add(left, right)
        case MINUS => GenericMath.subtract(left, right)
        case MULTIPLICATION => GenericMath.multiply(left, right)
        case DIVISION => GenericMath.divide(left, right)
        case POWER => GenericMath.power(left, right)
        case MOD => GenericMath.mod(left, right)
      }
  }

}
