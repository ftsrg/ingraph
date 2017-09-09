package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{ChangeSet, DataMessage, ReteMessage}

class SelectionNode(override val next: (ReteMessage) => Unit,
                    val condition: (Tuple) => Boolean,
                    override val expectedTerminatorCount: Int = 1
                   ) extends UnaryNode[DataMessage] with SingleForwarder {
  override def onSizeRequest() = 0
  def onChangeSet(changeSet: DataMessage): Unit = {
//    forward(ChangeSet(
//      changeSet.positive.filter(condition),
//      changeSet.negative.filter(condition)
//    ))
    val vectorses: Seq[Iterable[Tuple]] = changeSet.changeSets.map(_.filter(condition))
    forward(changeSet.createNew(vectorses))
  }
}
