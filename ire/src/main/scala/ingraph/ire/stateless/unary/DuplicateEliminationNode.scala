package ingraph.ire.stateless.unary

import ingraph.ire.messages.{ChangeSet, ReteMessage, SingleForwarder}
import ingraph.ire.nodes.unary.UnaryNode
import ingraph.ire.messages.SingleForwarder
import ingraph.ire.nodes.unary.UnaryNode

class DuplicateEliminationNode(override val next: (ReteMessage) => Unit) extends UnaryNode with SingleForwarder {
  override def onSizeRequest(): Long = 0

  def onChangeSet(changeSet: ChangeSet): Unit = {
    forward(ChangeSet(changeSet.positive.distinct))
  }

}
