package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

class UnwindNode(override val next: (ReteMessage) => Unit,
                 val index: Int,
                 override val expectedTerminatorCount: Int = 1
                   ) extends UnaryNode with SingleForwarder {

  override def onSizeRequest() = 0

  def unwind(tuples: Vector[Tuple], index: Int): Vector[Tuple] = {
    for {
      tuple <- tuples
      listElement <- tuple(index).asInstanceOf[Iterable[Any]]
    } yield tuple.updated(index, listElement)
  }

  def onChangeSet(changeSet: ChangeSet): Unit = {
    forward(ChangeSet(
      unwind(changeSet.positive, index),
      unwind(changeSet.negative, index)
    ))
  }
}
