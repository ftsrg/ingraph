package ingraph.ire.nodes.unary

import ingraph.ire.datatypes.Tuple
import ingraph.ire.messages.{ChangeSet, ReteMessage, SingleForwarder}
import ingraph.ire.messages.SingleForwarder

class SelectionNode(override val next: (ReteMessage) => Unit,
                    val condition: (Tuple) => Boolean,
                    override val expectedTerminatorCount: Int = 1
                   ) extends UnaryNode with SingleForwarder {
  override def onSizeRequest() = 0
  def onChangeSet(changeSet: ChangeSet): Unit = {
    forward(ChangeSet(
      changeSet.positive.filter(condition),
      changeSet.negative.filter(condition)
    ))
  }
}
