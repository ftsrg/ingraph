package hu.bme.mit.ire.stateless.binary

import hu.bme.mit.ire._
import hu.bme.mit.ire.datatypes.Mask
import hu.bme.mit.ire.messages.ReteMessage

class JoinNode(override val next: (ReteMessage) => Unit,
               val primaryTupleWidth: Int,
               val secondaryTupleWidth: Int,
               val primaryMask: Mask,
               val secondaryMask: Mask
              ) extends JoinNodeBase with SingleEmptyForwarder {
}
