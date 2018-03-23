package hu.bme.mit.ire.nodes.unary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

class UnwindNode(override val next: (ReteMessage) => Unit,
                 val listParser: Tuple => Seq[Any],
                 override val expectedTerminatorCount: Int = 1
                   ) extends UnaryNode with SingleForwarder {

  override def onSizeRequest() = 0

  def unwind(tuples: Vector[Tuple]): Vector[Tuple] = {
    for {
      tuple <- tuples
      listElement <- listParser(tuple)
    } yield tuple :+ listElement
  }

  def onChangeSet(changeSet: ChangeSet): Unit = {
    forward(ChangeSet(
      unwind(changeSet.positive),
      unwind(changeSet.negative)
    ))
  }
}
