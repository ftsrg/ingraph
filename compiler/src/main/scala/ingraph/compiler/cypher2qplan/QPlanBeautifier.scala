package ingraph.compiler.cypher2qplan

import ingraph.model.qplan
import ingraph.model.qplan.QNode
import org.apache.spark.sql.catalyst.plans.logical.LogicalPlan

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
  }
}
