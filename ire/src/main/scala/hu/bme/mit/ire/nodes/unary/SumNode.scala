package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Mask
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.messages.ReteMessage

class SumNode(override val next: (ReteMessage) => Unit,
              override val aggregationKeys: Mask,
              override val distinct: Boolean,
              val sumKey: Int) extends AggregationNode with SumLike with SingleForwarder {

  override def onSizeRequest() = 0

  override def onChangeSet(changeSet: ChangeSet): Unit = {
    val newChangeSet = maintainSum(changeSet, aggregationKeys, sumKey)
    forward(newChangeSet)
  }

}
