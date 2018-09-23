package hu.bme.mit.ire.nodes.binary

import hu.bme.mit.ire.datatypes._
import hu.bme.mit.ire.messages.{ChangeSet, ReteMessage, SingleForwarder}
import hu.bme.mit.ire.util.SizeCounter

import scala.collection.mutable

class CountingMultiMap[K,V] {
  private def innerMap: mutable.Map[V, Int] = mutable.HashMap.empty[V,Int].withDefault(k => 0)
  private val _values = new mutable.HashMap[K,mutable.Map[V, Int]]().withDefault(k => innerMap)
  def addBinding(key: K, value: V): Boolean = {
    val valueSet = _values(key)
    val newKey = valueSet.isEmpty
    val newValue = valueSet(value) + 1
    valueSet(value) = newValue
    _values(key) = valueSet
    newKey
  }
  def removeBinding(key: K, value: V): Unit = {
    val valueSet = _values(key)
    if (valueSet(value) == 1) {
      valueSet.remove(value)
      if (valueSet.isEmpty) {
        _values.remove(key)
      }
    } else if (valueSet(value) != 0) {
      valueSet(value) -= 1
    }
  }
  def contains(key: K): Boolean = {
    _values.contains(key)
  }

  def apply(key: K): Iterable[V] = {
    _values(key).flatMap(kv => Seq.fill(kv._2)(kv._1))
  }
}

class AntiJoinNode(override val next: ReteMessage => Unit,
                   override val primaryMask: Mask,
                   override val secondaryMask: Mask)
  extends AbstractJoinNode(primaryMask, secondaryMask) with SingleForwarder {

  val forwardValues = new CountingMultiMap[Seq[Any], Tuple]()
  val antiValues = new CountingMultiMap[Seq[Any], Tuple]()

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

    val newKeys = mutable.HashSet[Seq[Any]]()
    positive.foreach(
      m => {
        val key = secondaryMask.map(i => m(i))
        val newValue = antiValues.addBinding(key, m)
        if (newValue){
          newKeys += key
        }
      }
    )
    negative.foreach(
      m => {
        val key = secondaryMask.map(i => m(i))
        antiValues.removeBinding(key, m)
      }
    )
    val joinedNegativeKeys = (for {//this is switched because antijoin
      node <- positive
      secondaryKey = secondaryMask.map(i => node(i))
      if newKeys.contains(secondaryKey)
      if forwardValues.contains(secondaryKey)
    } yield secondaryKey).distinct
    val joinedNegative = joinedNegativeKeys.flatMap(k => forwardValues(k))

    val joinedPositiveKeys = (for {
      node <- negative
      key = secondaryMask.map(i => node(i))
      if !antiValues.contains(key)
      if forwardValues.contains(key)}
      yield key).distinct
    val joinedPositive = joinedPositiveKeys.flatMap(k => forwardValues(k))

    forward(ChangeSet(joinedPositive, joinedNegative))
  }

  override def onSizeRequest(): Long = ???
}
