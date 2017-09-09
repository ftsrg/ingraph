package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{IncrementalChangeSet, ReteMessage}

class UnwindNode(override val next: (ReteMessage) => Unit,
                 val index: Int,
                 override val expectedTerminatorCount: Int = 1
                   ) extends UnaryNode[IncrementalChangeSet] with SingleForwarder {

  override def onSizeRequest() = 0

  def unwind(tuples: Iterable[Tuple], index: Int): Iterable[Tuple] = {
    for {
      tuple <- tuples
      listElement <- tuple(index).asInstanceOf[Iterable[Any]]
    } yield tuple :+ listElement
  }

  def onChangeSet(changeSet: IncrementalChangeSet): Unit = {
    forward(IncrementalChangeSet(
      unwind(changeSet.positive, index),
      unwind(changeSet.negative, index)
    ))
  }
}
