package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.{Mask, TupleType}
import hu.bme.mit.ire.messages.ReteMessage

// not sure we need this class - SzG
class EqualityNode(override val next: (ReteMessage) => Unit,
                   val distinguishedElementIndex: Int,
                   val equals: Mask) extends
  SelectionNode(
    next,
    condition = (tuple: TupleType) => { equals.map { i => tuple(i) }.forall { value => value == tuple(distinguishedElementIndex) } }
  ) with SingleForwarder
