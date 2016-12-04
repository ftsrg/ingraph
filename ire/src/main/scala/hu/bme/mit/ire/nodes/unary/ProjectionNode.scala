package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

abstract class ProjectionImpl(val selectionVector: Vector[Any]) extends UnaryNode {
  override def onChangeSet(changeSet: ChangeSet) = {
    forward(ChangeSet(
      changeSet.positive.map(m => m.filterKeys(selectionVector.contains)),
      changeSet.negative.map(m => m.filterKeys(selectionVector.contains))
    ))
  }
}

class ProjectionNode(override val next: (ReteMessage) => Unit,
                     override val selectionVector: Vector[Any])
  extends ProjectionImpl(selectionVector) with SingleForwarder {}
