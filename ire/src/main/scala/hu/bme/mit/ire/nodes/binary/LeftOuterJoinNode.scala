package hu.bme.mit.ire.nodes.binary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes._
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

class LeftOuterJoinNode(override val next: (ReteMessage) => Unit,
                        override val primaryMask: Mask,
                        override val secondaryMask: Mask)
  extends AbstractJoinNode(primaryMask, secondaryMask) with SingleForwarder {

  def onPrimary(changeSet: ChangeSet): Unit = {
  }

  def onSecondary(changeSet: ChangeSet): Unit = {
  }

}
