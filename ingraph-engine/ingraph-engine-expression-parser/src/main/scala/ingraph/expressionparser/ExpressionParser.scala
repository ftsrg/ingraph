package ingraph.expressionparser

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.nodes.unary.aggregation._
import hu.bme.mit.ire.util.GenericMath
import relalg._
import relalg.function.FunctionCategory


object ExpressionParser {
  def parse(expression: Expression, lookup: Map[String, Integer]): (Tuple) => Boolean =
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

  private def parseVariable(variable: Variable, tuple: Tuple, lookup: Map[String, Integer]): Any =
    tuple(lookup(variable.toString))

  def parseValue(exp: Expression, lookup: Map[String, Integer]): (Tuple) => Any =
    if (lookup.contains(exp.toString))
      tuple => tuple(lookup(exp.toString))
    else exp match {
    case cmp: DoubleLiteral => _ => cmp.getValue
    case cmp: IntegerLiteral => _ => cmp.getValue
    case cmp: StringLiteral => _ => cmp.getValue
    case cmp: BigIntegerLiteral => _ => cmp.getValue
    case cmp: AttributeVariable =>
      val index = lookup(cmp.toString).toInt
      tuple => tuple(index)
    case cmp: Variable =>
      val index = lookup(cmp.toString).toInt
      tuple => tuple(index)
    case cmp: VariableComparableExpression =>
      val index = lookup(cmp.getVariable.toString).toInt
      tuple => tuple(index)
    case cmp: VariableExpression =>
      val variable = cmp.getVariable match {
        case e: ExpressionVariable => e.getExpression match {
          case v: VariableExpression => v.getVariable
          case f: FunctionExpression => e
        }
        case a => a
      }
      val index = lookup(variable.toString).toInt
      tuple => tuple(index)
    case exp: BinaryArithmeticOperationExpression =>
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
    case exp: FunctionExpression if exp.getFunctor.getCategory != FunctionCategory.AGGREGATION =>
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
    case exp: FunctionExpression if exp.getFunctor.getCategory == FunctionCategory.AGGREGATION =>
      tuple => lookup(exp.toString)
  }

  import relalg.function.Function._
  def parseAggregate(exp: Expression, lookup: Map[String, Integer]): List[(String, () => StatefulAggregate)] = exp match {
    case exp: FunctionExpression if exp.getFunctor.getCategory ==  FunctionCategory.AGGREGATION =>
      if (exp.getFunctor != COLLECT) {
        val variable = exp.getArguments.get(0).asInstanceOf[VariableExpression].getVariable
        val index = lookup(variable.toString)
        List((exp.toString, exp.getFunctor match {
          case AVG => () => new StatefulAverage(index)
          case COUNT => () => new NullAwareStatefulCount(index)
          case COUNT_ALL => () => new StatefulCount()
          case MAX => () => new StatefulMax(index)
          case MIN => () => new StatefulMin(index)
          case SUM => () => new StatefulSum(index)
        }))
      } else {
        val list = parseListExpression(exp.getArguments.get(0).asInstanceOf[ListExpression])
        val indices = list.map(e => lookup(e.asInstanceOf[VariableExpression].getVariable.toString)).map(_.toInt)
        List((exp.toString, () => new StatefulCollect(indices)))
      }
    case exp: FunctionExpression => parseAggregate(exp.getArguments.get(0), lookup)
    case exp: Literal => List()
    case exp: VariableExpression => List()
    case exp: BinaryArithmeticOperationExpression =>
      parseAggregate(exp.getLeftOperand, lookup) ++ parseAggregate(exp.getRightOperand, lookup)
  }

  private def parseListExpression(expr: ListExpression): Vector[Expression] = expr match {
    case f: EmptyListExpression => Vector()
    case nonempty => nonempty.getHead +: parseListExpression(nonempty.getTail)
  }
}
