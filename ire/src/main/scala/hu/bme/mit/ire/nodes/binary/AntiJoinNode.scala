package hu.bme.mit.ire.nodes.binary

import hu.bme.mit.ire.datatypes._
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage, SingleForwarder}
import hu.bme.mit.ire.util.SizeCounter

import scala.collection.mutable

class AntiJoinNode(override val next: (ReteMessage) => Unit,
                   override val primaryMask: Mask,
                   override val secondaryMask: Mask)
  extends AbstractJoinNode(primaryMask, secondaryMask) with SingleForwarder {

  val forwardValues = new mutable.HashMap[Seq[Any], mutable.Set[Tuple]]
    with mutable.MultiMap[Seq[Any], Tuple]
  val antiValues = new mutable.HashMap[Seq[Any], mutable.Set[Tuple]]
    with mutable.MultiMap[Seq[Any], Tuple]

  def onPrimary(changeSet: ChangeSet): Unit = {
    val positive = changeSet.positive
    val negative = changeSet.negative

    val joinedPositive = for {
      node <- positive
      if !antiValues.contains(primaryMask.map(i => node(i)))
    } yield node


    val joinedNegative = for {
      node <- negative
    } yield node

    forward(ChangeSet(joinedPositive, joinedNegative))

    positive.foreach(
      m => {
        val key = primaryMask.map(i => m(i))
        forwardValues.addBinding(key, m)
      }
    )
    negative.foreach(
      m => {
        val key = primaryMask.map(i => m(i))
        forwardValues.removeBinding(key, m)
      }
    )
  }

  def onSecondary(changeSet: ChangeSet): Unit = {
    val positive = changeSet.positive
    val negative = changeSet.negative

    positive.foreach(
      m => {
        val key = secondaryMask.map(i => m(i))
        antiValues.addBinding(key, m)
      }
    )
    negative.foreach(
      m => {
        val key = secondaryMask.map(i => m(i))
        antiValues.removeBinding(key, m)
      }
    )

    val joinedNegative = (for {//this is switched because antijoin
      node <- positive
      key = secondaryMask.map(i => node(i))
      if antiValues.contains(key)
      if forwardValues.contains(key)
      value <- forwardValues(key)
    } yield value).distinct

    val joinedPositive = (for {
      node <- negative
      key = secondaryMask.map(i => node(i))
      if !antiValues.contains(key)
      if forwardValues.contains(key)
      value <- forwardValues(key)
    } yield value).distinct

    forward(ChangeSet(joinedPositive, joinedNegative))
  }

  override def onSizeRequest(): Long = SizeCounter.countDeeper(forwardValues.values)
  + SizeCounter.count(antiValues.values)
}
