package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes._
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

class TransitiveClosureNode(override val next: (ReteMessage) => Unit,
                            val condition: (TupleType) => Boolean) extends UnaryNode with SingleForwarder {
  def onChangeSet(changeSet: ChangeSet): Unit = {

  }
}
