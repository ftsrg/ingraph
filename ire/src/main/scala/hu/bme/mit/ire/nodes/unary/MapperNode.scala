package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

class MapperNode(override val next: (ReteMessage) => Unit,
                 val function: (Any) => Any, val index: Int,
                 override val expectedTerminatorCount: Int = 1
                ) extends UnaryNode with SingleForwarder {
  def onChangeSet(changeSet: ChangeSet): Unit = {
    forward(ChangeSet(
      changeSet.positive.map(t => t.updated(index, function(t(index)))),
      changeSet.negative.map(t => t.updated(index, function(t(index))))
    ))
  }
}
