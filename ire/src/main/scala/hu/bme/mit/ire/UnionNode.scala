package hu.bme.mit.ire

import scala.collection.mutable

class UnionNode(override val next: (ReteMessage) => Unit,
                override val expectedTerminatorCount: Int = 2) extends BinaryNode with SingleForwarder  {

  val result = mutable.Set[TupleType]()

  def onUpdate(changeSet: ChangeSet) {
    val positive = changeSet.positive
    val negative = changeSet.negative

    println(positive)
    println(negative)

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
