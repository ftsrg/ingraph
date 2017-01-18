package ingraph.ire

import relalg.function.{CypherType, FunctionCategory}

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
  /* don't do this at home */
  implicit private def anyToDouble(any: Any) = any.asInstanceOf[Double]
  implicit private def anyToInt(any: Any) = any.asInstanceOf[Int]
  implicit private def anyToString(any: Any) = any.asInstanceOf[String]
  def sin(inputs: Any*): Double = Math.sin(inputs(0))
}
