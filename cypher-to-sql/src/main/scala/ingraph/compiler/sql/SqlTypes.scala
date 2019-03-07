package ingraph.compiler.sql

import ingraph.model.expr.{ExpressionBase, ResolvableName}
import org.apache.spark.sql.catalyst.expressions.UnaryExpression

case class SubqueryAttributeReference(subqueryName: String, child: ResolvableName)
  extends UnaryExpression with ExpressionBase
