package ingraph.compiler.cypher2qplan.structures

import ingraph.model.qplan
import org.apache.spark.sql.catalyst.{expressions => cExpr}

/**
  * Components of an openCypher (OPTIONAL) MATCH clause.
  * @param op Tree build from the patterns in a MATCH clause.
  * @param condition Condition from the attached WHERE clause.
  * @param optional if an OPTIONAL MATCH is described
  */
case class MatchDescriptor(op: Option[qplan.QNode] = None, condition: Option[cExpr.Expression] = None, optional: Option[Boolean] = None) {
  def hasCondition: Boolean = condition.isDefined
  def isOptional: Boolean = optional.contains(true)
}
