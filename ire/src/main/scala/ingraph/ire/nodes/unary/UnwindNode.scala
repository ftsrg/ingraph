package ingraph.ire.nodes.unary

import ingraph.ire.datatypes.Tuple
import ingraph.ire.messages.{ChangeSet, ReteMessage}
import ingraph.ire.messages.SingleForwarder

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
