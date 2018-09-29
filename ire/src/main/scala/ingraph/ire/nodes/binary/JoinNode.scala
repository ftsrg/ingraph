package ingraph.ire.nodes.binary

import ingraph.ire.datatypes.Mask
import ingraph.ire.messages.ReteMessage
import ingraph.ire.messages.SingleForwarder

class JoinNode(override val next: (ReteMessage) => Unit,
               override val primaryTupleWidth: Int,
               override val secondaryTupleWidth: Int,
               override val primaryMask: Mask,
               override val secondaryMask: Mask
              ) extends JoinNodeBase with SingleForwarder {
}
