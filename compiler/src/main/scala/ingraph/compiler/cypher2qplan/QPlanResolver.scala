package ingraph.compiler.cypher2qplan

import ingraph.model.{expr, qplan}
import ingraph.model.misc
import org.apache.spark.sql.catalyst.analysis.{UnresolvedAttribute, UnresolvedFunction}
import org.apache.spark.sql.catalyst.expressions.Expression
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
    case qplan.Delete(attributes, detach, child) => qplan.Delete(resolveAttributes(attributes, child), detach, child)
    case qplan.Create(attributes, child) => qplan.Create(filterForAttributesOfChildOutput(attributes, child, invert=true), child)
  }

  val expressionResolver: PartialFunction[Expression, Expression] = {
    case UnresolvedFunction(functionIdentifier, children, isDistinct) => expr.FunctionInvocation(misc.Function.getByPrettyName(functionIdentifier.identifier), children, isDistinct)
  }

  /**
    * Resolve attribute references according to the output schema of the child QNode
    * @param attributes
    * @param child
    * @return
    */
  protected def resolveAttributes(attributes: Seq[cExpr.Attribute], child: qplan.QNode): Seq[cExpr.Attribute] = {
    val transformedAttributes = attributes.flatMap( a => child.output.find( co => co.name == a.name ) )

    if (attributes.length != transformedAttributes.length) {
      throw new RuntimeException(s"Unable to resolve all attributes. Resolved ${transformedAttributes.length} out of ${attributes.length}")
    }

    transformedAttributes
  }

  /**
    * Filters the attributes passed in for being included in the child.output schema
    * @param attributes
    * @param child
    * @param invert iff true, match is inverted, i.e. only those are returned which were not found
    * @return
    */
  protected def filterForAttributesOfChildOutput(attributes: Seq[cExpr.Attribute], child: qplan.QNode, invert: Boolean = false): Seq[cExpr.Attribute] = {
    attributes.flatMap( a => if ( invert.^(child.output.exists( co => co.name == a.name )) ) Some(a) else None )
  }
}
