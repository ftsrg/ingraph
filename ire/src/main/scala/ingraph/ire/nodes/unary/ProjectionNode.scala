package ingraph.ire.nodes.unary

import ingraph.ire.datatypes.Tuple
import ingraph.ire.messages.{ChangeSet, ReteMessage}
import ingraph.ire.messages.SingleForwarder

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
