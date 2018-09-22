package hu.bme.mit.ire.nodes.binary

import hu.bme.mit.ire.datatypes.Mask
import hu.bme.mit.ire.messages.{ReteMessage, SingleForwarder}

class JoinNode(override val next: (ReteMessage) => Unit,
               override val primaryTupleWidth: Int,
               override val secondaryTupleWidth: Int,
               override val primaryMask: Mask,
               override val secondaryMask: Mask
              ) extends JoinNodeBase with SingleForwarder {
}
