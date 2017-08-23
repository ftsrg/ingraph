package ingraph.expressionparser

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.nodes.unary.aggregation._
import hu.bme.mit.ire.util.GenericMath
import org.antlr.v4.runtime.atn.SemanticContext.{AND, OR}
import org.apache.spark.sql.catalyst.expressions.{And, Attribute, BinaryComparison, BinaryExpression, BinaryOperator, EqualTo, Expression, GreaterThan, GreaterThanOrEqual, IsNotNull, IsNull, LessThan, LessThanOrEqual, Literal, Not, Or, UnaryExpression}
import org.apache.spark.unsafe.types.UTF8String


object ExpressionParser {
  def parse(expression: Expression, lookup: Map[String, Int]): (Tuple) => Boolean =
    expression match {
      case Not(exp) =>
        (t: Tuple) =>
            val operand: (Tuple => Boolean) = parse(exp, lookup)
            !operand(t)
      case IsNull(exp) =>
        def left: (Tuple) => Any = parseValue(exp, lookup)
        left(_) == null
      case IsNotNull(exp) =>
        def left: (Tuple) => Any = parseValue(exp, lookup)
        left(_) != null
      case exp: BinaryComparison =>
        val left: (Tuple => Any) = parseValue(exp.left, lookup)
        val right: (Tuple => Any) = parseValue(exp.right, lookup)
        exp match {
          case _: EqualTo => (t: Tuple) => left(t) == right(t)
          case _: LessThan => (t: Tuple) => GenericMath.compare(left(t), right(t)) <= 0
          case _: LessThanOrEqual => (t: Tuple) => GenericMath.compare(left(t), right(t)) < 0
          case _: GreaterThan => (t: Tuple) => GenericMath.compare(left(t), right(t)) >= 0
          case _: GreaterThanOrEqual => (t: Tuple) => GenericMath.compare(left(t), right(t)) > 0
        }
      case exp: BinaryOperator =>
        val left: (Tuple => Boolean) = parse(exp.left, lookup)
        val right: (Tuple => Boolean) = parse(exp.right, lookup)
        exp match {
          case _: And => (t: Tuple) => left(t) && right(t)
          case _: Or => (t: Tuple) => left(t) || right(t)
        }
    }

  private def parseAttribute(variable: Attribute, tuple: Tuple, lookup: Map[String, Integer]): Any =
    tuple(lookup(variable.toString()))

  def parseValue(exp: Expression, lookup: Map[String, Int]): (Tuple) => Any =
    if (lookup.contains(exp.toString()))
      tuple => tuple(lookup(exp.toString()))
    else exp match {
    case Literal(value, _) =>
      value match {
        case s: UTF8String => _ => s.toString
        case v => _ => v
      }
    case attr: Attribute =>
      val index = lookup(attr.toString())
      tuple => tuple(index)
//    case exp: BinaryArithmeticOperationExpression =>
//      val left = parseValue(exp.getLeftOperand, lookup)
//      val right = parseValue(exp.getRightOperand, lookup)
//      import BinaryArithmeticOperatorType._
//      exp.getOperator match {
//        case PLUS => tuple => GenericMath.add(left(tuple), right(tuple))
//        case MINUS => tuple => GenericMath.subtract(left(tuple), right(tuple))
//        case MULTIPLICATION => tuple => GenericMath.multiply(left(tuple), right(tuple))
//        case DIVISION => tuple => GenericMath.divide(left(tuple), right(tuple))
//        case POWER => tuple => GenericMath.power(left(tuple), right(tuple))
//        case MOD => tuple => GenericMath.mod(left(tuple), right(tuple))
//      }
//    case exp: FunctionExpression if exp.getFunctor.getCategory != FunctionCategory.AGGREGATION =>
//      exp.getArguments.size() match {
//        case 0 =>
//          val function = FunctionLookup.fun0(exp.getFunctor)
//          tuple => function()
//        case 1 =>
//          val first: (Tuple) => Any = parseValue(exp.getArguments.get(0), lookup)
//          val function: (Tuple) => Any = t => FunctionLookup.fun1(exp.getFunctor)(first(t))
//          tuple => function(tuple)
//        case 2 =>
//          val first = parseValue(exp.getArguments.get(0), lookup)
//          val second = parseValue(exp.getArguments.get(1), lookup)
//          val function: (Tuple) => Any = t => FunctionLookup.fun2(exp.getFunctor)(first(t), second(t))
//          tuple => function(tuple)
//        case 3 =>
//          val first = parseValue(exp.getArguments.get(0), lookup)
//          val second = parseValue(exp.getArguments.get(1), lookup)
//          val third = parseValue(exp.getArguments.get(2), lookup)
//          val function: (Tuple) => Any =
//            tuple => FunctionLookup.fun3(exp.getFunctor)(first(tuple), second(tuple), third(tuple))
//          tuple => function(tuple)
//      }
//    case exp: FunctionExpression if exp.getFunctor.getCategory == FunctionCategory.AGGREGATION =>
//      tuple => lookup(exp.fullName)
//    case exp: SimpleCaseExpression =>
//      val testExpression = parseValue(exp.getTest, lookup)
//      import collection.JavaConverters._
//      val cases = exp.getCases.asScala.map(c => (parseValue(c.getWhen, lookup), parseValue(c.getThen, lookup)))
//      val fallback = parseValue(exp.getFallback, lookup)
//      def caseFunction(tuple: Tuple): Any = {
//        val actual = testExpression(tuple)
//        for ((w, t) <- cases)
//          if (w(tuple) == actual)
//            return t(tuple)
//        fallback(tuple)
//      }
//      caseFunction
  }
//
//
//  import relalg.function.Function._
//  def parseAggregate(exp: Expression, lookup: Map[String, Integer]): List[(String, () => StatefulAggregate)] = exp match {
//    case exp: FunctionExpression if exp.getFunctor.getCategory ==  FunctionCategory.AGGREGATION =>
//      if (exp.getFunctor != COLLECT) {
//        val variable = exp.getArguments.get(0).asInstanceOf[VariableExpression].getVariable
//        val index = lookup(variable.fullName)
//        List((exp.fullName, exp.getFunctor match {
//          case AVG => () => new StatefulAverage(index)
//          case COUNT => () => new NullAwareStatefulCount(index)
//          case COUNT_ALL => () => new StatefulCount()
//          case MAX => () => new StatefulMax(index)
//          case MIN => () => new StatefulMin(index)
//          case SUM => () => new StatefulSum(index)
//        }))
//      } else {
//        val list = parseListExpression(exp.getArguments.get(0).asInstanceOf[ListExpression])
//        val indices = list.map(e => lookup(e.asInstanceOf[VariableExpression].getVariable.fullName)).map(_.toInt)
//        List((exp.fullName, () => new StatefulCollect(indices)))
//      }
//    case exp: FunctionExpression => parseAggregate(exp.getArguments.get(0), lookup)
//    case exp: Literal => List()
//    case exp: VariableExpression => List()
//    case exp: BinaryArithmeticOperationExpression =>
//      parseAggregate(exp.getLeftOperand, lookup) ++ parseAggregate(exp.getRightOperand, lookup)
//  }
}
