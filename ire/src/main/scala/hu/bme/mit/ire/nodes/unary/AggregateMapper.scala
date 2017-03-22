package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.{Mask, Tuple}
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

class AggregateMapper(override val next: (ReteMessage) => Unit,
                      mask: Mask, functions: Vector[(Tuple) => Any]
                     ) extends UnaryNode with SingleForwarder {

  override def onChangeSet(changeSet: ChangeSet): Unit = {
    
  }

  override def onSizeRequest(): Long = ???
}
