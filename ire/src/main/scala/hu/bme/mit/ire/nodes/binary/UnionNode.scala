package hu.bme.mit.ire.nodes.binary

import hu.bme.mit.ire._
import hu.bme.mit.ire.datatypes.TupleType
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

import scala.collection.mutable

class UnionNode(override val next: (ReteMessage) => Unit) extends BinaryNode with SingleForwarder  {

  val result = mutable.Set[TupleType]()

  def onUpdate(changeSet: ChangeSet) {
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

    forward(ChangeSet(forwardPositive, forwardNegative))
  }

  def onPrimary(changeSet: ChangeSet) {
    onUpdate(changeSet)
  }

  def onSecondary(changeSet: ChangeSet) {
    onUpdate(changeSet)
  }

}
