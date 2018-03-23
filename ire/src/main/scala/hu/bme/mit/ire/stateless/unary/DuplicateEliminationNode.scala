package hu.bme.mit.ire.stateless.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}
import hu.bme.mit.ire.nodes.unary.UnaryNode

class DuplicateEliminationNode(override val next: (ReteMessage) => Unit) extends UnaryNode with SingleForwarder {
  override def onSizeRequest(): Long = 0

  def onChangeSet(changeSet: ChangeSet): Unit = {
    forward(ChangeSet(changeSet.positive.distinct))
  }

}
