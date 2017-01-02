package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.datatypes._
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}
import hu.bme.mit.ire.SingleForwarder

class SumNode(override val next: (ReteMessage) => Unit,
              val aggregationKeys: Mask,
              val sumKey: Int) extends UnaryNode with SumLike with SingleForwarder {

  override def onSizeRequest() = 0

  override def onChangeSet(changeSet: ChangeSet): Unit = {
    val newChangeSet = maintainSum(changeSet, aggregationKeys, sumKey)
    forward(newChangeSet)
  }

}
