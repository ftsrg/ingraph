package hu.bme.mit.incqueryds

import scala.collection.mutable
import scala.collection.mutable.MultiMap

abstract class HashAntijoiner extends BetaNode with SingleForwarder  {
  val primarySelector: Vector[Int]
  val secondarySelector: Vector[Int]

  val forwardValues = new mutable.HashMap[nodeType, mutable.Set[nodeType]] with MultiMap[nodeType, nodeType]
  val antiValues = new mutable.HashMap[nodeType, mutable.Set[nodeType]] with MultiMap[nodeType, nodeType]

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
    vec => {
    val key = primarySelector.map(i => vec(i))
    forwardValues.addBinding(key, vec)
    }
  )
  negative.foreach(
    vec => {
    val key = primarySelector.map(i => vec(i))
    forwardValues.removeBinding(key, vec)
    }
  )
  }

  def onAntiSide(changeSet: ChangeSet): Unit = {
  val positive = changeSet.positive
  val negative = changeSet.negative

  positive.foreach(
    vec => {
    val key = secondarySelector.map(i => vec(i))
    antiValues.addBinding(key, vec)
    }
  )
  negative.foreach(
    vec => {
    val key = secondarySelector.map(i => vec(i))
    antiValues.removeBinding(key, vec)
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
             override val primarySelector: Vector[Int],
             override val secondarySelector: Vector[Int],
             override val expectedTerminatorCount:Int = 2) extends HashAntijoiner {

  override def onPrimary(changeSet: ChangeSet): Unit = onForwardingSide(changeSet)

  override def onSecondary(changeSet: ChangeSet): Unit = onAntiSide(changeSet)
}

class HashRightAntijoiner(override val next: (ReteMessage) => Unit,
             override val primarySelector: Vector[Int],
             override val secondarySelector: Vector[Int],
             override val expectedTerminatorCount:Int = 2) extends HashAntijoiner {

  override def onPrimary(changeSet: ChangeSet): Unit = onAntiSide(changeSet)

  override def onSecondary(changeSet: ChangeSet): Unit = onForwardingSide(changeSet)
}
