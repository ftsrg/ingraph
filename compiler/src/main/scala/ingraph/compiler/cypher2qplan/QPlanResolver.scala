package ingraph.compiler.cypher2qplan

import ingraph.model.{expr, qplan}
import ingraph.model.misc
import org.apache.spark.sql.catalyst.analysis.UnresolvedFunction
import org.apache.spark.sql.catalyst.expressions.{Expression, NamedExpression}
import org.apache.spark.sql.catalyst.{expressions => cExpr}
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

object QPlanResolver {
  def resolveQPlan(unresolvedQueryPlan: qplan.QNode): qplan.QNode = {
    // should there be other rule sets (partial functions), combine them using orElse,
    // e.g. pfunc1 orElse pfunc2
    val beautiful = unresolvedQueryPlan.transform(qplanResolver)

    beautiful.asInstanceOf[qplan.QNode]
  }

  /**
    * These are the resolver rules that applies to all unresolved QPlans.
    */
  val qplanResolver: PartialFunction[LogicalPlan, LogicalPlan] = {
    case qplan.Selection(condition, child) => qplan.Selection(condition.transform(expressionResolver), child)
    case qplan.Projection(projectList, child) => {
      val p = qplan.Projection(projectList.map(_.transform(expressionResolver).asInstanceOf[NamedExpression]), child)
      p
    }
  }

  val expressionResolver: PartialFunction[Expression, Expression] = {
    case UnresolvedFunction(functionIdentifier, children, isDistinct) => expr.FunctionInvocation(misc.Function.getByPrettyName(functionIdentifier.identifier), children, isDistinct)
  }
}
