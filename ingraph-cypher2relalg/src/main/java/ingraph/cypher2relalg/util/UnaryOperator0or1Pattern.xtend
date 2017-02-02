package ingraph.cypher2relalg.util

import org.eclipse.xtend.lib.annotations.Accessors
import relalg.UnaryOperator

/**
 * Represents an element in an UnaryOperator pattern, where that
 * particular operator can occur 0 or 1 times, i.e. this is the ? from regular expressions.
 */
class UnaryOperator0or1Pattern {
  @Accessors(PUBLIC_GETTER)
  val Class<? extends UnaryOperator> opc
  @Accessors(PUBLIC_GETTER)
  val boolean optional

  new(Class<? extends UnaryOperator> opc, boolean optional) {
    this.opc = opc
    this.optional = optional
  }

  override toString() {
    opc.name + if (optional) { '?' } else { '' }
  }
}
