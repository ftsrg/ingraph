package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{DataMessage, ReteMessage}


abstract class ProjectionImpl(val mask: Vector[Tuple => Any]) extends UnaryNode[DataMessage] {
  override def onSizeRequest() = 0

  override def onChangeSet(dm: DataMessage) = {
    val bags: Seq[Iterable[Tuple]] = dm.bags.map(
      bag => bag.map(
        tuple => mask.map(f => f(tuple))
      )
    )
    forward(dm.createNew(bags))
  }

}

class ProjectionNode(override val next: (ReteMessage) => Unit,
                     override val mask: Vector[Tuple => Any])
  extends ProjectionImpl(mask) with SingleForwarder {}
