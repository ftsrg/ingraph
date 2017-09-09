package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{DataMessage, ReteMessage}


abstract class ProjectionImpl(val mask: Vector[Tuple => Any]) extends UnaryNode[DataMessage] {
  override def onSizeRequest() = 0

  override def onChangeSet(changeSet: DataMessage) = {
    val changeSets: Seq[Iterable[Tuple]] = changeSet.changeSets.map(_.map(
      t => mask.map(f => f(t))
    ))

    forward(changeSet.createNew(changeSets))
  }

}

class ProjectionNode(override val next: (ReteMessage) => Unit,
                     override val mask: Vector[Tuple => Any])
  extends ProjectionImpl(mask) with SingleForwarder {}
