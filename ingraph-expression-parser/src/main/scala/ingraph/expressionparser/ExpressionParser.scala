package ingraph.expressionparser

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.nodes.unary.aggregation._
import hu.bme.mit.ire.util.GenericMath
import relalg._


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

  def parseValue(exp: Expression, lookup: Map[Variable, Integer]): (Tuple) => Any = exp match {
    case cmp: DoubleLiteral => _ => cmp.getValue
    case cmp: IntegerLiteral => _ => cmp.getValue
    case cmp: StringLiteral => _ => cmp.getValue
    case cmp: AttributeVariable =>
      val index = lookup(cmp).toInt
      tuple => tuple(index)
    case cmp: Variable =>
      val index = lookup(cmp).toInt
      tuple => tuple(index)
    case cmp: VariableComparableExpression =>
      val index = lookup(cmp.getVariable).toInt
      tuple => tuple(index)
    case cmp: VariableExpression =>
      val variable = cmp.getVariable match {
        case a: AttributeVariable => a
        case e: ExpressionVariable => e.getExpression.asInstanceOf[VariableExpression].getVariable
      }
      val index = lookup(variable).toInt
      tuple => tuple(index)
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
    case exp: FunctionExpression =>
      exp.getArguments.size() match {
        case 0 =>
          val function = FunctionLookup.fun0(exp.getFunctor)
          tuple => function()
        case 1 =>
          val first: (Tuple) => Any = parseValue(exp.getArguments.get(0), lookup)
          val function: (Tuple) => Any = t => FunctionLookup.fun1(exp.getFunctor)(first(t))
          tuple => function(tuple)
        case 2 =>
          val first = parseValue(exp.getArguments.get(0), lookup)
          val second = parseValue(exp.getArguments.get(1), lookup)
          val function: (Tuple) => Any = t => FunctionLookup.fun2(exp.getFunctor)(first(t), second(t))
          tuple => function(tuple)
        case 3 =>
          val first = parseValue(exp.getArguments.get(0), lookup)
          val second = parseValue(exp.getArguments.get(1), lookup)
          val third = parseValue(exp.getArguments.get(2), lookup)
          val function: (Tuple) => Any =
            tuple => FunctionLookup.fun3(exp.getFunctor)(first(tuple), second(tuple), third(tuple))
          tuple => function(tuple)
      }
  }

  def parseAggregate(exp: Expression, lookup: Map[Variable, Integer]
                    ): Option[() => StatefulAggregate] = exp match {
    case exp: VariableExpression => println("FIX ME"); None

    case exp: FunctionExpression =>
      import relalg.function.Function._
      val variable = exp.getArguments.get(0).asInstanceOf[VariableExpression].getVariable
      val index = lookup(variable)
      exp.getFunctor match {
        case AVG => Some(() => new StatefulAverage(index))
        case COUNT => Some(() => new StatefulCount())
        case COUNT_ALL => Some(() => new StatefulCount())
        case MAX => Some(() => new StatefulMax(index))
        case MIN => Some(() => new StatefulMin(index))
        case SUM => Some(() => new StatefulSum(index))
      }
    }
}
