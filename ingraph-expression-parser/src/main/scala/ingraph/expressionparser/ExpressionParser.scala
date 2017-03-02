package ingraph.expressionparser

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.util.GenericMath
import relalg._
import relalg.function.{CypherType, FunctionCategory}


object ExpressionParser {
  def parse(expression: Expression, lookup: Map[Variable, Integer]): (Tuple) => Boolean =
    expression match {
      case exp: UnaryLogicalExpression =>
        import UnaryLogicalOperatorType._
        exp.getOperator match {
          case NOT => (t: Tuple) =>
            val operand: (Tuple => Boolean) = parse(exp.getOperand, lookup)
            !operand(t)
          case IS_NULL =>
            def left: (Tuple) => Any = parseValue(exp.getOperand.asInstanceOf[ComparableExpression], lookup)
            left(_) == null
          case IS_NOT_NULL =>
            def left: (Tuple) => Any = parseValue(exp.getOperand.asInstanceOf[ComparableExpression], lookup)
            left(_) != null
        }
      case exp: UnaryGraphObjectLogicalExpression =>
        import UnaryGraphObjectLogicalOperatorType._
        def left: (Tuple) => Any = parseVariable(exp.getOperand, _, lookup)
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
        def left: (Tuple) => Any = parseValue(exp.getLeftOperand, lookup)

        def right: (Tuple) => Any = parseValue(exp.getRightOperand, lookup)
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

  private def parseValue(cmp: Expression, lookup: Map[Variable, Integer]): (Tuple) => Any = cmp match {
    case cmp: DoubleLiteral => _ => cmp.getValue
    case cmp: IntegerLiteral => _ => cmp.getValue
    case cmp: StringLiteral => _ => cmp.getValue
    case cmp: AttributeVariable =>  tuple => tuple(lookup(cmp))
    case cmp: Variable => tuple => tuple(lookup(cmp))
    case cmp: VariableComparableExpression => tuple => tuple(lookup(cmp.getVariable))
    case cmp: VariableExpression => tuple => tuple(lookup(cmp.getVariable))
    case exp: ArithmeticOperationExpression =>
      val left = parseValue(exp.getLeftOperand, lookup)
      val right = parseValue(exp.getRightOperand, lookup)
      import BinaryArithmeticOperatorType._
      exp.getOperator match {
        case PLUS => tuple => GenericMath.add(left(tuple), right(tuple))
        case MINUS => tuple => GenericMath.subtract(left(tuple), right(tuple))
        case MULTIPLICATION => tuple => GenericMath.multiply(left(tuple), right(tuple))
        case DIVISION => tuple => GenericMath.divide(left(tuple), right(tuple))
        case POWER => tuple => GenericMath.power(left(tuple), right(tuple))
        case MOD => tuple => GenericMath.mod(left(tuple), right(tuple))
      }
    case cmp: FunctionExpression =>
      cmp.getArguments.size() match {
        case 0 => tuple => FunctionLookup.fun0(cmp.getFunctor)()
        case 1 =>
          val first = parseValue(cmp.getArguments.get(0), lookup)
          tuple => FunctionLookup.fun1(cmp.getFunctor)(first(tuple))
        case 2 =>
          val first = parseValue(cmp.getArguments.get(0), lookup)
          val second = parseValue(cmp.getArguments.get(1), lookup)
          tuple => FunctionLookup.fun2(cmp.getFunctor)(first(tuple), second(tuple))
        case 3 =>
          val first = parseValue(cmp.getArguments.get(0), lookup)
          val second = parseValue(cmp.getArguments.get(1), lookup)
          val third = parseValue(cmp.getArguments.get(2), lookup)
          tuple => FunctionLookup.fun3(cmp.getFunctor)(first(tuple), second(tuple), third(tuple))
      }
  }
}
