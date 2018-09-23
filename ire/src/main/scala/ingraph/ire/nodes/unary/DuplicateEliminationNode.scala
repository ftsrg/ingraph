package ingraph.ire.nodes.unary

import ingraph.ire.datatypes.Tuple
import ingraph.ire.messages.{ChangeSet, ReteMessage}
import ingraph.ire.messages.SingleForwarder

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class DuplicateEliminationNode(override val next: (ReteMessage) => Unit) extends UnaryNode with SingleForwarder {
  override def onSizeRequest(): Long = 0

  val tuples = new mutable.HashMap[Tuple, Integer].withDefault(d => 0)

  def onChangeSet(changeSet: ChangeSet): Unit = {
    val forwardPositive = new ListBuffer[Tuple]()
    val forwardNegative = new ListBuffer[Tuple]()

    changeSet.positive.foreach { tuple =>
      val oldCount = tuples(tuple)
      val newCount = oldCount + 1
      tuples.put(tuple, newCount)

      if (oldCount == 0) {
        forwardPositive += tuple
      }
    }

    changeSet.negative.foreach { tuple =>
      val oldCount = tuples(tuple)
      val newCount = oldCount - 1
      tuples.put(tuple, newCount)

      if (newCount == 0) {
        forwardNegative += tuple
      }
    }

    forward(ChangeSet(forwardPositive.toVector, forwardNegative.toVector))
  }

}
