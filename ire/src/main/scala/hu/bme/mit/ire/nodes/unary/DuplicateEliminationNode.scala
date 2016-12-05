package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

class DuplicateEliminationNode(override val next: (ReteMessage) => Unit,
                               val condition: (Tuple) => Boolean) extends UnaryNode with SingleForwarder {
  def onChangeSet(changeSet: ChangeSet): Unit = {
    // TODO
  }
}
