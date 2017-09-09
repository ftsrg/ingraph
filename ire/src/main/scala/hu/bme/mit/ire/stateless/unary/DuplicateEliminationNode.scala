package hu.bme.mit.ire.stateless.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.messages.{BatchChangeSet, ReteMessage}
import hu.bme.mit.ire.nodes.unary.UnaryNode

class DuplicateEliminationNode(override val next: (ReteMessage) => Unit) extends UnaryNode[BatchChangeSet] with SingleForwarder {
  override def onSizeRequest(): Long = 0

  def onChangeSet(cs: BatchChangeSet): Unit = {
    forward(BatchChangeSet(cs.changeSet.toSeq.distinct))
  }

}
