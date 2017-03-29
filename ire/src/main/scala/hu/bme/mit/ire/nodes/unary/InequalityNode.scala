package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.{Mask, Tuple}
import hu.bme.mit.ire.messages.ReteMessage

// not sure we need this class - SzG
class InequalityNode(override val next: (ReteMessage) => Unit,
                     val distinguishedElementIndex: Int,
                     val inequals: Mask) extends
  σNode(
    next,
    condition = (tuple: Tuple) => !inequals.map { i => tuple(i) }.contains(tuple(distinguishedElementIndex))
  ) with SingleForwarder
