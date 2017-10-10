package ingraph.compiler.cypher2qplan

import ingraph.model.qplan
import org.apache.spark.sql.catalyst.expressions.Expression
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan
import org.apache.spark.sql.catalyst.{expressions => cExpr}
import org.apache.spark.sql.types.BooleanType

object QPlanBeautifier {
  def beautifyResolvedQPlan(resolvedQueryPlan: qplan.QNode): qplan.QNode = {
    // should there be other rule sets (partial functions), combine them using orElse,
    // e.g. pfunc1 orElse pfunc2
    val beautiful = resolvedQueryPlan.transform(commonBeautifier)

    beautiful.asInstanceOf[qplan.QNode]
  }

  def beautifyUnresolvedQPlan(unresolvedQueryPlan: qplan.QNode): qplan.QNode = {
    // should there be other rule sets (partial functions), combine them using orElse,
    // e.g. pfunc1 orElse pfunc2
    val beautiful = unresolvedQueryPlan.transform(commonBeautifier)

    beautiful.asInstanceOf[qplan.QNode]
  }

  /**
    * These are the structural beautifier rules that applies to all QPlans,
    * irrespectively whether it is resolved or not.
    *
    * Note: most rules will will be common.
    */
  val commonBeautifier: PartialFunction[LogicalPlan, LogicalPlan] = {
    case qplan.Join(qplan.Dual(), o2) => o2
    case qplan.Selection(condition, child) => qplan.Selection(condition.transform(expressionResolver), child)
  }

  val expressionResolver: PartialFunction[Expression, Expression] = {
    case cExpr.And(cExpr.Literal(true, BooleanType), r) => r
    case cExpr.And(l, cExpr.Literal(true, BooleanType)) => l
    case cExpr.Or(cExpr.Literal(false, BooleanType), r) => r
    case cExpr.Or(l, cExpr.Literal(false, BooleanType)) => l
  }

}
