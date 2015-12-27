package hu.bme.mit.incquerydcore

import scala.collection.mutable
import scala.collection.mutable.MultiMap

/**
  * Created by wafle on 12/25/2015.
  */
class HashAntiJoiner(override val next: (ReteMessage) => Unit,
                     val primarySelector: Vector[Int],
                     val secondarySelector: Vector[Int],
                     override val expectedTerminatorCount:Int = 2)
  extends BetaNode with SingleForwarder  {
  val primaryValues = new mutable.HashMap[nodeType, mutable.Set[nodeType]] with MultiMap[nodeType, nodeType]
  val secondaryValues = new mutable.HashMap[nodeType, mutable.Set[nodeType]] with MultiMap[nodeType, nodeType]


  def onPrimary(changeSet: ChangeSet): Unit = {
    val positive = changeSet.positive
    val negative = changeSet.negative

    val joinedPositive = for {
      node <- positive
      if !secondaryValues.contains(primarySelector.map(i => node(i)))
    } yield node


    val joinedNegative = for {
      node <- negative
    } yield node

    forward(ChangeSet(joinedPositive, joinedNegative))

    positive.foreach(
      vec => {
        val key = primarySelector.map(i => vec(i))
        primaryValues.addBinding(key, vec)
      }
    )
    negative.foreach(
      vec => {
        val key = primarySelector.map(i => vec(i))
        primaryValues.removeBinding(key, vec)
      }
    )
  }

  def onSecondary(changeSet: ChangeSet): Unit = {
    val positive = changeSet.positive
    val negative = changeSet.negative

    positive.foreach(
      vec => {
        val key = secondarySelector.map(i => vec(i))
        secondaryValues.addBinding(key, vec)
      }
    )
    negative.foreach(
      vec => {
        val key = secondarySelector.map(i => vec(i))
        secondaryValues.removeBinding(key, vec)
      }
    )

    val joinedNegative = (for {//this is switched because antijoin
      node <- positive
      key = secondarySelector.map(i => node(i))
      if secondaryValues.contains(key)
      if primaryValues.contains(key)
      value <- primaryValues(key)
    } yield value).distinct

    val joinedPositive = (for {
      node <- negative
      key = secondarySelector.map(i => node(i))
      if !secondaryValues.contains(key)
      if primaryValues.contains(key)
      value <- primaryValues(key)
    } yield value).distinct

    forward(ChangeSet(joinedPositive, joinedNegative))
  }
}
