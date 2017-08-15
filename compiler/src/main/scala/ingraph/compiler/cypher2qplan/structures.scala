package ingraph.compiler.cypher2qplan.structures

import ingraph.model.{expr, qplan}

/**
  * Components of an openCypher (OPTIONAL) MATCH clause.
  */
class MatchDescriptor {
  /**
    * Tree build of the patterns in a MATCH clause.
    */
  var op: Option[qplan.QNode] = None

  /**
    * Condition from the attached WHERE clause.
    */
  var condition: Option[expr.ExpressionBase] = None
  def hasCondition: Boolean = condition.isDefined

  var optional: Option[Boolean] = None
  def isOptional: Boolean = optional.contains(true)
}
