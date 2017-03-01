package ingraph.expressionparser

import hu.bme.mit.ire.util.GenericMath
import relalg.function.{CypherType, FunctionCategory}

import scala.util.Random

// if a function has optional arguments, we implement it for each possible number of arguments,
// e.g. substring( original, start [, length] ) is implemented both as a fun2 and a fun3
object FunctionLookup {
  import Conversions._
  import collection.JavaConverters._
  import relalg.function.Function._
  import relalg.function.Function

  object RelalgFunction {
    def unapply(arg: relalg.function.Function): Option[Tuple2[FunctionCategory, List[CypherType]]] =
      Some(arg.getCategory, arg.getInputTypes.asScala.toList)
  }

  //  cmp.getFunctor match {
  //    case RelalgFunction(_, CypherType.ANY) =>
  //
  //  }

  def fun0(function: Function): () => Any = {
    function match {
      case E => () => Math.E
      case PI => () => Math.PI
      case RAND => () => Random.nextFloat()
    }
  }


  def fun1(function: Function): (Any) => Any = {
    /* don't do this at home */
    implicit def anyToDouble(any: Any) = any.asInstanceOf[Double]
    implicit def anyToInt(any: Any) = any.asInstanceOf[Int]
    implicit def anyToString(any: Any) = any.asInstanceOf[String]

    function match {
      case TOBOOLEAN => (x) => x.asInstanceOf[Boolean]
      case TOINTEGER | TOINT => (x) => GenericMath.toInt(x)
      case TOFLOAT => (x) => GenericMath.toDouble(x)
      case TOSTRING => (x) => x.toString

      case TAIL => (x) => x.asInstanceOf[Iterable[Any]].tail
      case HEAD => (x) => x.asInstanceOf[Iterable[Any]].head
      case LAST => (x) => x.asInstanceOf[Iterable[Any]].last
      case LENGTH => (x) => x.asInstanceOf[Iterable[Any]].length

      case LOG => (x) => Math.log(x)
      case LOG10 => (x) => Math.log10(x)
      case SQRT => (x) => Math.sqrt(x)

      case ABS => {
        case i: Int => Math.abs(i)
        case d: Double => Math.abs(d)
      }

      case CEIL => (x) => Math.ceil(x)
      case FLOOR => (x) => Math.floor(x)
      case ROUND => (x) => Math.round(x)
      case SIGN => (x) => Math.signum(x)

      case LTRIM => (x) => x.replaceAll("^\\s+", "")
      case RTRIM => (x) => x.replaceAll("\\s+$", "")
      case TRIM => (x) => x.trim

      case REVERSE => (x) => new java.lang.String(x).reverse
      case TOLOWER => (x) => x.toLowerCase
      case TOUPPER => (x) => x.toUpperCase

      case ACOS => (x) => Math.acos(x)
      case ASIN => (x) => Math.asin(x)
      case ATAN => (x) => Math.atan(x)
      case COS => (x) => Math.cos(x)
      case COT => (x) => Math.tanh(x)
      case SIN => (x) => Math.sin(x)
      case TAN => (x) => Math.tan(x)
      
      case DEGREES => (x) => x.asInstanceOf[Double] / 180.0 * Math.PI
      case RADIANS => (x) => x.asInstanceOf[Double] * Math.PI / 180.0
    }
  }

  def fun2(function: Function): (Any, Any) => Any = {
    implicit def anyToInt(any: Any) = any.asInstanceOf[Int]
    implicit def anyToString(any: Any) = any.asInstanceOf[String]
    function match {
      case LEFT => (x, y) => x.substring(0, y)
      case RIGHT => (x, y) => x.substring(x.length - y, x.length)
      case STARTS_WITH => (x, y) => x.startsWith(y)
      case ENDS_WITH => (x, y) => x.endsWith(y)
      case CONTAINS => (x, y) => x.contains(y)
      case SPLIT => (x, y) => x.split(y)
      case SUBSTRING => (x, y) => x.substring(y)
    }
  }

  def fun3(function: Function): (Any, Any, Any) => Any = {
    implicit def anyToInt(any: Any) = any.asInstanceOf[Int]
    implicit def anyToString(any: Any) = any.asInstanceOf[String]
    function match {
      case REPLACE => (x, y, z) => x.replace(y, z)
      case SUBSTRING => (x, y, z) => x.substring(y, z)
    }
  }


}
