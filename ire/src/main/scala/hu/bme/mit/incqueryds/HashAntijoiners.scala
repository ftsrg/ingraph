package hu.bme.mit.incqueryds

import scala.collection.mutable
import scala.collection.mutable.MultiMap

abstract class HashAntijoiner extends BetaNode with SingleForwarder  {
  val primarySelector: Vector[Any]
  val secondarySelector: Vector[Any]

  val forwardValues = new mutable.HashMap[Vector[Any], mutable.Set[nodeType]] with MultiMap[Vector[Any], nodeType]
  val antiValues = new mutable.HashMap[Vector[Any], mutable.Set[nodeType]] with MultiMap[Vector[Any], nodeType]

  def onForwardingSide(changeSet: ChangeSet): Unit = {
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

  def onAntiSide(changeSet: ChangeSet): Unit = {
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

class HashLeftAntijoiner(override val next: (ReteMessage) => Unit,
             override val primarySelector: Vector[Any],
             override val secondarySelector: Vector[Any],
             override val expectedTerminatorCount:Int = 2) extends HashAntijoiner {

  override def onPrimary(changeSet: ChangeSet): Unit = onForwardingSide(changeSet)

  override def onSecondary(changeSet: ChangeSet): Unit = onAntiSide(changeSet)
}

class HashRightAntijoiner(override val next: (ReteMessage) => Unit,
             override val primarySelector: Vector[Any],
             override val secondarySelector: Vector[Any],
             override val expectedTerminatorCount:Int = 2) extends HashAntijoiner {

  override def onPrimary(changeSet: ChangeSet): Unit = onAntiSide(changeSet)

  override def onSecondary(changeSet: ChangeSet): Unit = onForwardingSide(changeSet)
}
