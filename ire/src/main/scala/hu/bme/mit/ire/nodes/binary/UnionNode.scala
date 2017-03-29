package hu.bme.mit.ire.nodes.binary

import hu.bme.mit.ire._
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{Δ, ReteMessage}
import hu.bme.mit.ire.util.SizeCounter

import scala.collection.mutable

class UnionNode(override val next: (ReteMessage) => Unit,
                val bag: Boolean) extends BinaryNode with SingleForwarder {

  val result = mutable.ListBuffer[Tuple]()

  override def onSizeRequest(): Long = SizeCounter.count(result)

  def onPrimary(changeSet: Δ) {
    onUpdate(changeSet)
  }

  def onSecondary(changeSet: Δ) {
    onUpdate(changeSet)
  }

  def onUpdate(changeSet: Δ) {
    if (bag) { // bag semantics
      forward(changeSet)
    } else { // set semantics
      val positive = changeSet.positive
      val negative = changeSet.negative

      val forwardPositive = for {
        tuple <- positive if !result.contains(tuple)
      } yield tuple

      val forwardNegative = for {
        tuple <- negative if !result.contains(tuple)
      } yield tuple

      result ++= forwardPositive
      result --= forwardNegative

      forward(Δ(forwardPositive, forwardNegative))
    }
  }

}
