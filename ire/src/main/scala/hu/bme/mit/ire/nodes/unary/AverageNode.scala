package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

class AverageNode(override val next: (ReteMessage) => Unit,
                  val keys: Vector[Any],
                  override val expectedTerminatorCount:Int = 1) extends UnaryNode with SingleForwarder {
  var count = 0
  override def onChangeSet(changeSet: ChangeSet): Unit = {

  }

}
