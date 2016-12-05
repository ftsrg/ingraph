package hu.bme.mit.ire.nodes.binary

import hu.bme.mit.ire.SingleForwarder
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage}

import scala.collection.mutable

class AntiJoinNode(override val next: (ReteMessage) => Unit,
                   val primarySelector: Vector[Any],
                   val secondarySelector: Vector[Any]) extends BinaryNode with SingleForwarder {


  val forwardValues = new mutable.HashMap[Vector[Any], mutable.Set[Tuple]]
    with mutable.MultiMap[Vector[Any], Tuple]
  val antiValues = new mutable.HashMap[Vector[Any], mutable.Set[Tuple]]
    with mutable.MultiMap[Vector[Any], Tuple]

  def onPrimary(changeSet: ChangeSet): Unit = {
    val positive = changeSet.positive
    val negative = changeSet.negative

//    val joinedPositive = for {
//      node <- positive
//      if !antiValues.contains(primarySelector.map(i => node(i)))
//    } yield node
//
//
//    val joinedNegative = for {
//      node <- negative
//    } yield node
//
//    forward(ChangeSet(joinedPositive, joinedNegative))
//
//    positive.foreach(
//      m => {
//        val key = primarySelector.map(i => m(i))
//        forwardValues.addBinding(key, m)
//      }
//    )
//    negative.foreach(
//      m => {
//        val key = primarySelector.map(i => m(i))
//        forwardValues.removeBinding(key, m)
//      }
//    )
  }

  def onSecondary(changeSet: ChangeSet): Unit = {
    val positive = changeSet.positive
    val negative = changeSet.negative

//    positive.foreach(
//      m => {
//        val key = secondarySelector.map(i => m(i))
//        antiValues.addBinding(key, m)
//      }
//    )
//    negative.foreach(
//      m => {
//        val key = secondarySelector.map(i => m(i))
//        antiValues.removeBinding(key, m)
//      }
//    )
//
//    val joinedNegative = (for {//this is switched because antijoin
//      node <- positive
//      key = secondarySelector.map(i => node(i))
//      if antiValues.contains(key)
//      if forwardValues.contains(key)
//      value <- forwardValues(key)
//    } yield value).distinct
//
//    val joinedPositive = (for {
//      node <- negative
//      key = secondarySelector.map(i => node(i))
//      if !antiValues.contains(key)
//      if forwardValues.contains(key)
//      value <- forwardValues(key)
//    } yield value).distinct
//
//    forward(ChangeSet(joinedPositive, joinedNegative))
  }
}
