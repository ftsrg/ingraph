package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

class SumNode(override val next: (ReteMessage) => Unit,
              val aggregationKeys: Vector[Int],
              val sumKey: Int) extends UnaryNode with SumLike with SingleForwarder {

  override def onSizeRequest() = 0

  override def onChangeSet(changeSet: ChangeSet): Unit = {
    val newChangeSet = maintainSum(changeSet, aggregationKeys, sumKey)
    forward(newChangeSet)
  }

}
