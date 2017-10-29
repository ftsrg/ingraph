package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{IncrementalChangeSet, DataMessage, ReteMessage}

class SelectionNode(override val next: (ReteMessage) => Unit,
                    val condition: (Tuple) => Boolean,
                    override val expectedTerminatorCount: Int = 1
                   ) extends UnaryNode[DataMessage] with SingleForwarder {
  override def onSizeRequest() = 0
  def onChangeSet(dm: DataMessage): Unit = {
    val bags: Seq[Iterable[Tuple]] = dm.bags.map(
      bag => bag.filter(
        tuple => condition(tuple)
      )
      )
    forward(dm.createNew(bags))
  }
}
