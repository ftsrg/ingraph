package hu.bme.mit.ire.nodes.binary

import hu.bme.mit.ire._
import hu.bme.mit.ire.datatypes.{Mask, Tuple}
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage, TerminatorMessage}

class JoinNode(override val next: (ReteMessage) => Unit,
               override val primaryTupleWidth: Int,
               override val secondaryTupleWidth: Int,
               override val primaryMask: Mask,
               override val secondaryMask: Mask
              ) extends JoinNodeBase with SingleForwarder {
}
