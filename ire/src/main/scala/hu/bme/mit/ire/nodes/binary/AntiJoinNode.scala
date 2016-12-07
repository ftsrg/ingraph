package hu.bme.mit.ire.nodes.binary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes._
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

import scala.collection.{GenSetLike, SetLike, mutable}

class AntiJoinNode(override val next: (ReteMessage) => Unit,
                   override val primaryMask: Mask,
                   override val secondaryMask: Mask)
  extends AbstractJoinNode(primaryMask, secondaryMask) with SingleForwarder {

  val primaryIndexer: Indexer = new mutable.HashMap[Tuple, mutable.Set[Tuple]] with mutable.MultiMap[Tuple, Tuple]
  val secondaryTuples: mutable.Set[Tuple] = mutable.Set[Tuple]()
  val secondaryProjectedTuples: mutable.Set[Tuple] = mutable.Set[Tuple]()

  def onPrimary(changeSet: ChangeSet): Unit = {
    val resultPositive = for {
      tuple <- changeSet.positive
      if !secondaryProjectedTuples.contains(extract(tuple, primaryMask))
    } yield tuple

    val resultNegative = for {
      tuple <- changeSet.negative
      if !secondaryProjectedTuples.contains(extract(tuple, primaryMask))
    } yield tuple

    forward(ChangeSet(resultPositive, resultNegative))

    // maintain the content of the slot's indexer
    for (tuple <- changeSet.positive) primaryIndexer.addBinding   (extract(tuple, primaryMask), tuple)
    for (tuple <- changeSet.negative) primaryIndexer.removeBinding(extract(tuple, primaryMask), tuple)
  }

  def onSecondary(changeSet: ChangeSet): Unit = {
    // positive part
    val deltaSProjected = changeSet.positive.map(tuple => extract(tuple, secondaryMask)).toSet
    val deltaSMinusS = deltaSProjected -- secondaryProjectedTuples

    val resultNegative = leftJoin(primaryIndexer.values, deltaSMinusS, primaryMask)

    // negative part
    val sMinusDeltaS = secondaryTuples -- changeSet.negative
    val sMinusDeltaSProjected = sMinusDeltaS.map(tuple => extract(tuple, secondaryMask))

    val x = leftJoin(primaryIndexer.values, secondaryProjectedTuples, primaryMask).toSet
    val y = leftJoin(primaryIndexer.values, sMinusDeltaSProjected, primaryMask).toSet
    val resultPositive = x -- y

    // maintain the content of the slot's indexer
    for (tuple <- changeSet.positive) {
      secondaryTuples.add(tuple)
      secondaryProjectedTuples.add(extract(tuple, secondaryMask))
    }
    for (tuple <- changeSet.negative) {
      secondaryTuples.remove(tuple)
      secondaryProjectedTuples.remove(extract(tuple, secondaryMask))
    }

    forward(ChangeSet(resultPositive.toVector, resultNegative.toVector))
  }

  def projectSecondaryTuples(tuples: Vector[Tuple]): Vector[Tuple] = {
    for {
      tuple <- tuples
    } yield extract(tuple, secondaryMask)
  }

  def leftJoin(leftTuples: Iterable[collection.Set[Tuple]], rightTuples: collection.Set[Tuple], leftMask: Mask): Iterable[Tuple] = {
    val result = for {
      leftTupleSets <- leftTuples
      leftTuple <- leftTupleSets
      if rightTuples.contains(extract(leftTuple, leftMask))
    } yield leftTuple
    result
  }

}
