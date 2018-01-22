package ingraph.expressionparser

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.nodes.unary.aggregation._
import hu.bme.mit.ire.util.GenericMath
import ingraph.model.expr.{FunctionInvocation, TupleIndexLiteralAttribute, VertexAttribute}
import ingraph.model.misc.FunctionCategory
import org.apache.spark.sql.catalyst.analysis.UnresolvedAlias
import org.apache.spark.sql.catalyst.expressions.{Add, Alias, And, Attribute, BinaryArithmetic, BinaryComparison, BinaryOperator, CaseWhen, Divide, EqualTo, Expression, GreaterThan, GreaterThanOrEqual, IsNotNull, IsNull, LessThan, LessThanOrEqual, Literal, Multiply, Not, Or, Pmod, Remainder, Subtract}
import org.apache.spark.unsafe.types.UTF8String


object ExpressionParser {
  def parse(expression: Expression): (Tuple) => Boolean =
    expression match {
      case Not(exp) =>
        (t: Tuple) =>
            val operand: (Tuple => Boolean) = parse(exp)
            !operand(t)
      case IsNull(exp) =>
        def left: (Tuple) => Any = parseValue(exp)
        left(_) == null
      case IsNotNull(exp) =>
        def left: (Tuple) => Any = parseValue(exp)
        left(_) != null
      case exp: BinaryComparison =>
        val left: (Tuple => Any) = parseValue(exp.left)
        val right: (Tuple => Any) = parseValue(exp.right)
        exp match {
          case _: EqualTo => (t: Tuple) => left(t) == right(t)
          case _: LessThan => (t: Tuple) => GenericMath.compare(left(t), right(t)) <= 0
          case _: LessThanOrEqual => (t: Tuple) => GenericMath.compare(left(t), right(t)) < 0
          case _: GreaterThan => (t: Tuple) => GenericMath.compare(left(t), right(t)) >= 0
          case _: GreaterThanOrEqual => (t: Tuple) => GenericMath.compare(left(t), right(t)) > 0
        }
      case exp: BinaryOperator =>
        val left: (Tuple => Boolean) = parse(exp.left)
        val right: (Tuple => Boolean) = parse(exp.right)
        exp match {
          case _: And => (t: Tuple) => left(t) && right(t)
          case _: Or => (t: Tuple) => left(t) || right(t)
        }
      case l: Literal =>
        (t: Tuple) => l.value.asInstanceOf[Boolean]
    }

  def parseValue(exp: Expression): (Tuple) => Any = exp match {
    case Literal(value, _) =>
      value match {
        case s: UTF8String => _ => s.toString
        case v => _ => v
      }
    case TupleIndexLiteralAttribute(index, _) =>
      tuple  => tuple(index)
    case op: BinaryArithmetic =>
      val left = parseValue(op.left)
      val right = parseValue(op.right)
        op match {
          case _: Add => tuple => GenericMath.add(left(tuple), right(tuple))
          case _: Subtract => tuple => GenericMath.subtract(left(tuple), right(tuple))
          case _: Multiply => tuple => GenericMath.multiply(left(tuple), right(tuple))
          case _: Divide => tuple => GenericMath.divide(left(tuple), right(tuple))
          case _: Remainder => tuple => ???
          case _: Pmod => tuple =>  GenericMath.mod(left(tuple), right(tuple))
        }
    case invoc: FunctionInvocation =>
      val children: Seq[Tuple => Any] = invoc.children.map(parseValue)
      children.length match {
        case 0 => tuple => FunctionLookup.fun0(invoc.functor)()
        case 1 => tuple => FunctionLookup.fun1(invoc.functor)(children.head(tuple))
        case 2 => tuple => FunctionLookup.fun2(invoc.functor)(children(0)(tuple), children(1)(tuple))
        case 3 => tuple => FunctionLookup.fun3(invoc.functor)(children(0)(tuple), children(1)(tuple), children(2)(tuple))
      }
    case exp: CaseWhen =>
      println(exp)
      val cases = exp.branches.map(c => (parse(c._1), parseValue(c._2)))
      val fallback: Tuple => Any = exp.elseValue.map(parseValue)
        .getOrElse((t: Tuple) => throw new Exception("Run into non-existent ELSE clause"))
      def caseFunction(tuple: Tuple): Any = {
        for ((w, t) <- cases)
          if (w(tuple))
            return t(tuple)
        fallback(tuple)
      }
      caseFunction
//    case exp: UnaryExpression =>
//      val first: (Tuple) => Any = parseValue(exp.child, lookup)
//      val function: (Tuple) => Any = t => FunctionLookup.fun1(exp)(first(t))
//      tuple => function(tuple)
//    case exp: BinaryExpression =>
//      val first: (Tuple) => Any = parseValue(exp.left, lookup)
//      val second: (Tuple) => Any = parseValue(exp.right, lookup)
//      val function: (Tuple) => Any = t => FunctionLookup.fun2(exp)(first(t), second(t))
//      tuple => function(tuple)
//    case exp: TernaryExpression =>
//      val first: (Tuple) => Any = parseValue(exp.children(0), lookup)
//      val second: (Tuple) => Any = parseValue(exp.children(1), lookup)
//      val third: (Tuple) => Any = parseValue(exp.children(2), lookup)
//      val function: (Tuple) => Any = t => FunctionLookup.fun3(exp)(first(t), second(t), third(t))
//      tuple => function(tuple)

//    case exp: FunctionExpression if exp.getFunctor.getCategory == FunctionCategory.AGGREGATION =>
//      tuple => lookup(exp.fullName)
  }
//
//

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
