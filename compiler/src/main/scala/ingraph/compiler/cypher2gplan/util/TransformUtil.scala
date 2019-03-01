package ingraph.compiler.cypher2gplan.util

import org.apache.spark.sql.catalyst.{expressions => cExpr}

object TransformUtil {
  def transformOption(optionExpression: Option[cExpr.Expression], transformRule: PartialFunction[cExpr.Expression, cExpr.Expression]): Option[cExpr.Expression] = {
    optionExpression match {
      case None => None
      case Some(e) => Some(e.transform(transformRule))
    }
  }
}
