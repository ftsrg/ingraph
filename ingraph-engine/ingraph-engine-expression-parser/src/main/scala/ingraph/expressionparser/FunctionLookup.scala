package ingraph.expressionparser

import scala.util.Random

import hu.bme.mit.ire.util.GenericMath
import relalg.function.CypherType
import relalg.function.FunctionCategory
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation
import org.apache.commons.math3.stat.descriptive.rank.Percentile

// if a function has optional arguments, we implement it for each possible number of arguments,
// e.g. substring( original, start [, length] ) is implemented both as a fun2 and a fun3
object FunctionLookup {
  import relalg.function.Function
  import relalg.function.Function._
  import collection.JavaConverters._

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

      case LTRIM => (original) => original.replaceAll("^\\s+", "")
      case RTRIM => (original) => original.replaceAll("\\s+$", "")
      case TRIM => (original) => original.trim

      case REVERSE => (original) => new java.lang.String(original).reverse
      case TOLOWER => (original) => original.toLowerCase
      case TOUPPER => (original) => original.toUpperCase

      case ACOS => (x) => Math.acos(x)
      case ASIN => (x) => Math.asin(x)
      case ATAN => (x) => Math.atan(x)
      case COS => (x) => Math.cos(x)
      case COT => (x) => Math.tanh(x)
      case SIN => (x) => Math.sin(x)
      case TAN => (x) => Math.tan(x)

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

      case EXISTS => (patternOrProperty) => ???
    }
  }

  def fun2(function: Function): (Any, Any) => Any = {
    implicit def anyToInt(any: Any) = any.asInstanceOf[Int]
    implicit def anyToString(any: Any) = any.asInstanceOf[String]
    function match {
      case LEFT => (original, length) => original.substring(0, length)
      case RIGHT => (original, length) => original.substring(original.length - length, original.length)
      case SPLIT => (original, splitPattern) => original.split(splitPattern)
      case SUBSTRING => (original, start) => original.substring(start)
      case RANGE => (start, end) => start.asInstanceOf[Int] to end

      // these are not define as functions in openCypher, but it's reasonable to treat them as such
      case STARTS_WITH => (string, substring) => string.startsWith(substring)
      case ENDS_WITH => (string, substring) => string.endsWith(substring)
      case CONTAINS => (string, substring) => string.contains(substring)      
    }
  }

  def fun3(function: Function): (Any, Any, Any) => Any = {
    implicit def anyToInt(any: Any) = any.asInstanceOf[Int]
    implicit def anyToString(any: Any) = any.asInstanceOf[String]
    function match {
      case REPLACE => (original, search, replace) => original.replace(search, replace)
      case SUBSTRING => (original, start, length) => original.substring(start, start.asInstanceOf[Int] + length.asInstanceOf[Int])
      case RANGE => (start, end, step) => start.asInstanceOf[Int] to end by step
    }
  }

}
