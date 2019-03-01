package ingraph.compiler.cypher2gplan.structures

import ingraph.model.gplan
import org.apache.spark.sql.catalyst.{expressions => cExpr}

/**
  * Components of an openCypher (OPTIONAL) MATCH clause.
  * @param optional if an OPTIONAL MATCH is described
  * @param operatorTree Tree build from the patterns in a MATCH clause.
  * @param condition Condition from the attached WHERE clause.
  */
case class MatchDescriptor(optional: Boolean, operatorTree: gplan.GNode, condition: Option[cExpr.Expression] = None) {
  def hasCondition: Boolean = condition.isDefined
  def isOptional: Boolean = optional
}
