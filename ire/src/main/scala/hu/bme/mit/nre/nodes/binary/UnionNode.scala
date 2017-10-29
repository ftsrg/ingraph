package hu.bme.mit.nre.nodes.binary

import hu.bme.mit.ire._
import hu.bme.mit.ire.messages.{BatchChangeSet, ReteMessage}

class UnionNode(override val next: (ReteMessage) => Unit,
                val bag: Boolean) extends StatelessBinaryNode with SingleForwarder {

  override def onSizeRequest(): Long = 0

  var primaryChangeSet = BatchChangeSet()
  var secondaryChangeSet = BatchChangeSet()

  def onPrimary(changeSet: BatchChangeSet) {
    primaryChangeSet = changeSet
    onUpdate()
  }

  def onSecondary(changeSet: BatchChangeSet) {
    secondaryChangeSet = changeSet
    onUpdate()
  }

  def onUpdate() {
    if (bag)
      forwardAny(BatchChangeSet(primaryChangeSet.changeSet ++ secondaryChangeSet.changeSet))
    else
      forwardAny(BatchChangeSet((primaryChangeSet.changeSet ++ secondaryChangeSet.changeSet).toSeq.distinct))
  }

}
