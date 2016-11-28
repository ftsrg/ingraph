package hu.bme.mit.ire

import scala.collection.mutable

class AntiJoinNode(override val next: (ReteMessage) => Unit,
                   val primarySelector: Vector[Any],
                   val secondarySelector: Vector[Any],
                   override val expectedTerminatorCount:Int = 2)  extends BinaryNode with SingleForwarder  {


  val forwardValues = new mutable.HashMap[Vector[Any], mutable.Set[TupleType]]
    with mutable.MultiMap[Vector[Any], TupleType]
  val antiValues = new mutable.HashMap[Vector[Any], mutable.Set[TupleType]]
    with mutable.MultiMap[Vector[Any], TupleType]

  def onPrimary(changeSet: ChangeSet): Unit = {
    val positive = changeSet.positive
    val negative = changeSet.negative

    val joinedPositive = for {
      node <- positive
      if !antiValues.contains(primarySelector.map(i => node(i)))
    } yield node


    val joinedNegative = for {
      node <- negative
    } yield node

    forward(ChangeSet(joinedPositive, joinedNegative))

    positive.foreach(
      m => {
        val key = primarySelector.map(i => m(i))
        forwardValues.addBinding(key, m)
      }
    )
    negative.foreach(
      m => {
        val key = primarySelector.map(i => m(i))
        forwardValues.removeBinding(key, m)
      }
    )
  }

  def onSecondary(changeSet: ChangeSet): Unit = {
    val positive = changeSet.positive
    val negative = changeSet.negative

    positive.foreach(
      m => {
        val key = secondarySelector.map(i => m(i))
        antiValues.addBinding(key, m)
      }
    )
    negative.foreach(
      m => {
        val key = secondarySelector.map(i => m(i))
        antiValues.removeBinding(key, m)
      }
    )

    val joinedNegative = (for {//this is switched because antijoin
      node <- positive
      key = secondarySelector.map(i => node(i))
      if antiValues.contains(key)
      if forwardValues.contains(key)
      value <- forwardValues(key)
    } yield value).distinct

    val joinedPositive = (for {
      node <- negative
      key = secondarySelector.map(i => node(i))
      if !antiValues.contains(key)
      if forwardValues.contains(key)
      value <- forwardValues(key)
    } yield value).distinct

    forward(ChangeSet(joinedPositive, joinedNegative))
  }
}
