package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.messages.{IncrementalChangeSet, DataMessage, ReteMessage}

/**
  * Currently used for selecting elements from a list.
  *
  * @param next
  * @param function
  * @param index
  * @param expectedTerminatorCount
  */
class MapperNode(override val next: (ReteMessage) => Unit,
                 val function: (Any) => Any, val index: Int,
                 override val expectedTerminatorCount: Int = 1
                ) extends UnaryNode[DataMessage] with SingleForwarder {
  override def onSizeRequest() = 0

  def onChangeSet(changeSet: DataMessage): Unit = {
//    forward(ChangeSet(
//      changeSet.positive.map(),
//      changeSet.negative.map(t => t.updated(index, function(t(index))))
//    ))
    val changeSets = changeSet.bags.map(_.map(
      t => t.updated(index, function(t(index)))
    ))
    changeSets

    forward(changeSet.createNew(changeSets))
  }
}
