package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.{Mask, Tuple}
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

abstract class ProjectionImpl(val mask: Vector[Tuple => Any]) extends UnaryNode {
  override def onSizeRequest() = 0
  override def onChangeSet(changeSet: ChangeSet) = {
    forward(ChangeSet(
      changeSet.positive.map(t => mask.map(f => f(t))),
      changeSet.negative.map(t => mask.map(f => f(t)))
    ))
  }
}

class ProjectionNode(override val next: (ReteMessage) => Unit,
                     override val mask: Vector[Tuple => Any])
  extends ProjectionImpl(mask) with SingleForwarder {}
