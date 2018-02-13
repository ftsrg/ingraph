package ingraph.compiler.cypher2qplan.structures

import ingraph.model.qplan
import org.apache.spark.sql.catalyst.{expressions => cExpr}

/**
  * Components of an openCypher (OPTIONAL) MATCH clause.
  * @param optional if an OPTIONAL MATCH is described
  * @param operatorTree Tree build from the patterns in a MATCH clause.
  * @param condition Condition from the attached WHERE clause.
  */
case class MatchDescriptor(optional: Boolean, operatorTree: qplan.QNode, condition: Option[cExpr.Expression] = None) {
  def hasCondition: Boolean = condition.isDefined
  def isOptional: Boolean = optional
}
