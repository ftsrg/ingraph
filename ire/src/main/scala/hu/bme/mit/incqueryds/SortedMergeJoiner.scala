package hu.bme.mit.incqueryds

import java.util
import java.util.Comparator
import scala.util.control.Breaks._

import scala.collection.mutable

/**
  * Created by wafle on 12/28/2015.
  */
class SortedMergeJoiner(override val next: (ReteMessage) => Unit,
            comparator: Comparator[nodeType],
             val primaryLength: Int, val primarySelector: Vector[Int],
             val secondaryLength: Int, val secondarySelector: Vector[Int],
             override val expectedTerminatorCount:Int = 2)
  extends BetaNode with SingleForwarder {
  val inversePrimarySelector = Vector.range(0, primaryLength) filter (i => !primarySelector.contains(i))
  val inverseSecondarySelector = Vector.range(0, secondaryLength) filter (i => !secondarySelector.contains(i))

  val primaryValues = new util.TreeMap[nodeType, mutable.Set[nodeType]](comparator)
  val secondaryValues = new util.TreeMap[nodeType, mutable.Set[nodeType]](comparator)

  override def onPrimary(changeSet: ChangeSet): Unit = {
  val incoming = changeSet.positive.sortWith((a, b) => comparator.compare(a, b) < 0).iterator
  val secondary = secondaryValues.keySet().iterator
  println("primary")
  val matches = new mutable.HashSet[nodeType]

  breakable {
    if (!incoming.hasNext || !secondary.hasNext)
    break
    var nextIn = incoming.next()
    var nextSec = secondary.next()
    while (true) {
    val cmp = comparator.compare(nextIn, nextSec)
    if (cmp == 0) {
      secondaryValues.get(nextSec).foreach(matches += nextIn ++ inverseSecondarySelector.map(i => nextSec(i)))
      nextIn = incoming.next()
      nextSec = secondary.next()
    } else if (cmp > 0) {
      if (!secondary.hasNext) {
      break
      }
      nextSec = secondary.next()
    } else if (cmp < 0) {
      if (!incoming.hasNext) {
      break
      }
      nextIn = incoming.next()
    }
    }
  }

  forward(ChangeSet(positive = matches.toVector))

  changeSet.positive.foreach(
    vec => {
    val key = primarySelector.map(i => vec(i))
    val value = primaryValues.get(key)
    if (value == null) {
      primaryValues.put(key, mutable.HashSet(vec))
    } else value.add(vec)
    }
  )
  }

  override def onSecondary(changeSet: ChangeSet): Unit = {
  val incoming = changeSet.positive.sortWith((a, b) => comparator.compare(a, b) < 0).iterator
  val primary = primaryValues.keySet().iterator

  val matches = new mutable.HashSet[nodeType]

  breakable {
    if (!(incoming.hasNext && primary.hasNext)) {
    break
    }

    var nextIn = incoming.next()
    var nextPrim = primary.next()
    while (true) {
    val cmp = comparator.compare(nextIn, nextPrim)
    if (cmp == 0) {
      primaryValues.get(nextPrim).foreach(matches += nextIn ++ inversePrimarySelector.map(i => nextPrim(i)))
      if (!(incoming.hasNext && primary.hasNext)) {
      break
      }
      nextIn = incoming.next()
      nextPrim = primary.next()
    } else if (cmp > 0) {
      if (!primary.hasNext) {
      break
      }
      nextPrim = primary.next()
    } else if (cmp < 0) {
      if (!incoming.hasNext) {
      break
      }
      nextIn = incoming.next()
    }
    }
  }

  forward(ChangeSet(positive = matches.toVector))

  incoming.foreach(
    vec => {
    val key = primarySelector.map(i => vec(i))
    val value = secondaryValues.get(key)
    if (value == null) {
      secondaryValues.put(key, mutable.HashSet(vec))
    } else value.add(vec)
    }
  )
  }
}
