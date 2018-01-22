package ingraph.parse

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.util.GenericMath
import ingraph.expressionparser.FunctionLookup
import ingraph.model.expr.{FunctionInvocation, TupleIndexLiteralAttribute}
import org.apache.spark.sql.catalyst.expressions.{Add, And, BinaryArithmetic, BinaryComparison, BinaryOperator, CaseWhen, Divide, EqualTo, Expression, GreaterThan, GreaterThanOrEqual, IsNotNull, IsNull, LessThan, LessThanOrEqual, Literal, Multiply, Not, Or, Pmod, Remainder, Subtract}
import org.apache.spark.unsafe.types.UTF8String


object ExpressionParser {
  def apply[T](exp: Expression): Tuple => T = {
    val e = parse(exp)
    t => e(t).asInstanceOf[T]
  }

  private def parse(exp: Expression): Tuple => Any = exp match {
    case Literal(value, _) =>
      value match {
        case s: UTF8String => _ => s.toString
        case v => _ => v
      }
    case Not(e) =>
      (t: Tuple) =>
        val operand = ExpressionParser[Boolean](e)
        !operand(t)
    case IsNull(e) =>
      def left: Tuple => Any = parse(e)
      left(_) == null
    case IsNotNull(e) =>
      def left: Tuple => Any = parse(e)
      left(_) != null
    case exp: BinaryComparison =>
      val left: Tuple => Any = parse(exp.left)
      val right: Tuple => Any = parse(exp.right)
      exp match {
        case _: EqualTo => (t: Tuple) => left(t) == right(t)
        case _: LessThan => (t: Tuple) => GenericMath.compare(left(t), right(t)) <= 0
        case _: LessThanOrEqual => (t: Tuple) => GenericMath.compare(left(t), right(t)) < 0
        case _: GreaterThan => (t: Tuple) => GenericMath.compare(left(t), right(t)) >= 0
        case _: GreaterThanOrEqual => (t: Tuple) => GenericMath.compare(left(t), right(t)) > 0
      }
    case op: BinaryArithmetic =>
      val left = parse(op.left)
      val right = parse(op.right)
      op match {
        case _: Add => tuple => GenericMath.add(left(tuple), right(tuple))
        case _: Subtract => tuple => GenericMath.subtract(left(tuple), right(tuple))
        case _: Multiply => tuple => GenericMath.multiply(left(tuple), right(tuple))
        case _: Divide => tuple => GenericMath.divide(left(tuple), right(tuple))
        case _: Remainder => tuple => ???
        case _: Pmod => tuple =>  GenericMath.mod(left(tuple), right(tuple))
      }
    case exp: BinaryOperator =>
      val left: Tuple => Boolean = ExpressionParser[Boolean](exp.left)
      val right: Tuple => Boolean = ExpressionParser[Boolean](exp.right)
      exp match {
        case _: And => (t: Tuple) => left(t) && right(t)
        case _: Or => (t: Tuple) => left(t) || right(t)
      }
    case TupleIndexLiteralAttribute(index, _) =>
      tuple  => tuple(index)

    case invoc: FunctionInvocation =>
      val children: Seq[Tuple => Any] = invoc.children.map(parse)
      children.length match {
        case 0 => tuple => FunctionLookup.fun0(invoc.functor)()
        case 1 => tuple => FunctionLookup.fun1(invoc.functor)(children.head(tuple))
        case 2 => tuple => FunctionLookup.fun2(invoc.functor)(children(0)(tuple), children(1)(tuple))
        case 3 => tuple => FunctionLookup.fun3(invoc.functor)(children(0)(tuple), children(1)(tuple), children(2)(tuple))
      }
    case exp: CaseWhen =>
      println(exp)
      val cases = exp.branches.map(c => (ExpressionParser[Boolean](c._1), parse(c._2)))
      val fallback: Tuple => Any = exp.elseValue.map(parse)
        .getOrElse((t: Tuple) => throw new Exception("Run into non-existent ELSE clause"))
      def caseFunction(tuple: Tuple): Any = {
        for ((w, t) <- cases)
          if (w(tuple))
            return t(tuple)
        fallback(tuple)
      }
      caseFunction
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

}
