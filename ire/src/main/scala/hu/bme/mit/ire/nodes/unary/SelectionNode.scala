package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.TupleType
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

class SelectionNode(override val next: (ReteMessage) => Unit,
                    val condition: (TupleType) => Boolean) extends UnaryNode with SingleForwarder  {
  def onChangeSet(changeSet: ChangeSet): Unit = {
    forward(ChangeSet(
      changeSet.positive.filter(condition),
      changeSet.negative.filter(condition)
    ))
  }
}
