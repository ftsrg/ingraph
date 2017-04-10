package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Mask
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

abstract class ProjectionImpl(val mask: Mask) extends UnaryNode {
  override def onSizeRequest() = 0
  override def onChangeSet(changeSet: ChangeSet) = {
    forward(ChangeSet(
      changeSet.positive.map(t => mask.map(i => t(i))),
      changeSet.negative.map(t => mask.map(i => t(i)))
    ))
  }
}

class ProjectionNode(override val next: (ReteMessage) => Unit,
                     override val mask: Mask)
  extends ProjectionImpl(mask) with SingleForwarder {}
