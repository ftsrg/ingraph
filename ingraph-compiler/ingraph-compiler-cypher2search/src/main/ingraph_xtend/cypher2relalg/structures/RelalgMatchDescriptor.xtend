package ingraph.cypher2relalg.structures

import org.eclipse.xtend.lib.annotations.Accessors
import relalg.LogicalExpression
import relalg.Operator

/**
 * This holds the compiled form of a MATCH cypher clause.
 * 
 * Condition should be placed in the left outer join
 * or above the possible join.
 */
class RelalgMatchDescriptor {
  @Accessors(PUBLIC_GETTER, PUBLIC_SETTER)
  LogicalExpression condition
  @Accessors(PUBLIC_GETTER, PUBLIC_SETTER)
  Operator op
}