package ingraph.expressionparser

import hu.bme.mit.ire.util.GenericMath
import ingraph.model.misc.Function
import ingraph.model.misc.Function._

import scala.util.Random

// if a function has optional arguments, we implement it for each possible number of arguments,
// e.g. substring( original, start [, length] ) is implemented both as a fun2 and a fun3
object FunctionLookup {

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
    implicit def anyToInt(any: Any) = any.asInstanceOf[Long]
    implicit def anyToString(any: Any) = any.asInstanceOf[String]
    function match {
      case TOBOOLEAN => (x) => x.asInstanceOf[Boolean]
      case TOINTEGER | TOINT => (x) => GenericMath.toLong(x)
      case TOFLOAT => (x) => GenericMath.toDouble(x)
      case TOSTRING => (x) => x.toString

      case TAIL => (list) => list.asInstanceOf[Iterable[Any]].tail
      case HEAD => (list) => list.asInstanceOf[List[Any]].headOption.orElse(null)
      case LAST => (list) => list.asInstanceOf[Iterable[Any]].last
      case LENGTH => {
        case i: Iterable[Any] => i.length
        case s: String => s.length
      }
      case SIZE => (list) => list.asInstanceOf[List[Any]].size
      case COALESCE => (list) => list.asInstanceOf[Iterable[Any]] find { _ != null } // TODO this might return null or None :-/

      case LOG => (x) => Math.log(x.asInstanceOf[Number].doubleValue())
      case LOG10 => (x) => Math.log10(x.asInstanceOf[Number].doubleValue())
      case SQRT => (x) => Math.sqrt(x.asInstanceOf[Number].doubleValue())

      case ABS => {
        case i: Int => Math.abs(i)
        case d: Double => Math.abs(d)
      }

      case CEIL => (x) => Math.ceil(x.asInstanceOf[Number].doubleValue())
      case FLOOR => (x) => Math.floor(x.asInstanceOf[Number].doubleValue())
      case ROUND => (x) => Math.round(x.asInstanceOf[Number].doubleValue())
      case SIGN => (x) => Math.signum(x.asInstanceOf[Number].doubleValue())

      case LTRIM => (original) => original.replaceAll("^\\s+", "")
      case RTRIM => (original) => original.replaceAll("\\s+$", "")
      case TRIM => (original) => original.trim

      case REVERSE => (original) => new java.lang.String(original).reverse
      case TOLOWER => (original) => original.toLowerCase
      case TOUPPER => (original) => original.toUpperCase

      case ACOS => (x) => Math.acos(x.asInstanceOf[Number].doubleValue())
      case ASIN => (x) => Math.asin(x.asInstanceOf[Number].doubleValue())
      case ATAN => (x) => Math.atan(x.asInstanceOf[Number].doubleValue())
      case COS => (x) => Math.cos(x.asInstanceOf[Number].doubleValue())
      case COT => (x) => Math.tanh(x.asInstanceOf[Number].doubleValue())
      case SIN => (x) => Math.sin(x.asInstanceOf[Number].doubleValue())
      case TAN => (x) => Math.tan(x.asInstanceOf[Number].doubleValue())

      case DEGREES => (x) => x.asInstanceOf[Double] / 180.0 * Math.PI
      case RADIANS => (x) => x.asInstanceOf[Double] * Math.PI / 180.0

      case STARTNODE => (edge) => ???
      case ENDNODE => (edge) => ???

      case PROPERTIES => (graphObject) => ???
      case TYPE => (edge) => ???

      case KEYS => (graphObject) => ???
      case LABELS => (vertex) => ???

      case RELATIONSHIPS => (path) => ???
      case NODES => (path) => ???

      case EXISTS => (patternOrProperty) => patternOrProperty != null
    }
  }

  def fun2(function: Function): (Any, Any) => Any = {
    implicit def anyToLong(any: Any) = any.asInstanceOf[Long]
    implicit def anyToString(any: Any) = any.asInstanceOf[String]

    function match {
      case LEFT => (original, length) => original.substring(0, length.toInt)
      case RIGHT => (original, length) => original.substring(original.length - length toInt, original.length)
      case SPLIT => (original, splitPattern) => original.split(splitPattern)
      case SUBSTRING => (original, start) => original.substring(start.toInt)
      case RANGE => (start, end) => start.asInstanceOf[Long] to end

      case POW => (base, exp) =>
//        implicit def anyToDouble(any: Any) = any.asInstanceOf[Double]
        Math.pow(base.toDouble, exp.toDouble)
      // these are not define as functions in openCypher, but it's reasonable to treat them as such
      case STARTS_WITH => (string, substring) => string.startsWith(substring)
      case ENDS_WITH => (string, substring) => string.endsWith(substring)
      case CONTAINS => (string, substring) => string.contains(substring)
    }
  }

  def fun3(function: Function): (Any, Any, Any) => Any = {
    implicit def anyToLong(any: Any) = any.asInstanceOf[Long]
    implicit def anyToString(any: Any) = any.asInstanceOf[String]
    function match {
      case REPLACE => (original, search, replace) => original.replace(search, replace)
      case SUBSTRING => (original, start, length) => original.substring(start.toInt,
        start.asInstanceOf[Long] + length.asInstanceOf[Long] toInt)
      case RANGE => (start, end, step) => start.asInstanceOf[Long] to end by step
    }
  }

}
