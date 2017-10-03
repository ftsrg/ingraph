package hu.bme.mit.nre.nodes.binary

import hu.bme.mit.ire._
import hu.bme.mit.ire.datatypes.{Mask, TupleBag}
import hu.bme.mit.ire.messages.{BatchChangeSet, ReteMessage}

class AntiJoinNode(val next: (ReteMessage) => Unit,
                   primaryMask: Mask,
                   secondaryMask: Mask
                  ) extends AbstractJoinNode(primaryMask, secondaryMask) with SingleForwarder {

  def antiJoinTuples(): TupleBag =
    primaryChangeSet.changeSet filter (t => !secondaryIndexer.contains(extract(t, primaryMask)))

  def onUpdate(): Unit = {
    val bcs = BatchChangeSet(antiJoinTuples())
    forwardAny(bcs)
  }
}
