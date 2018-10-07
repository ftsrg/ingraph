package ingraph.ire.nodes.binary

import ingraph.ire.datatypes._
import ingraph.ire.messages.{ChangeSet, ReteMessage}
import ingraph.ire.util.SizeCounter
import ingraph.ire.messages.SingleForwarder

import scala.collection.mutable

class CountingMultiMap[K,V] {
  private def innerMap: mutable.Map[V, Int] = mutable.HashMap.empty[V,Int].withDefault(k => 0)
  private val _values = new mutable.HashMap[K, mutable.Map[V, Int]]().withDefault(k => innerMap)

  def addBinding(key: K, value: V): Boolean = {
    val valueSet = _values(key)
    val newKey = valueSet.isEmpty
    increaseValue(key, value, valueSet)
    newKey
  }

  private def increaseValue(key: K, value: V, valueSet: mutable.Map[V, Int]): Int = {
    val newValue = valueSet(value) + 1
    valueSet(value) = newValue
    _values(key) = valueSet
    newValue
  }

  def put(key: K, values: mutable.Iterable[V]): Unit = {
    val valueSet = _values(key)
    for (v <- values) {
      increaseValue(key, v, valueSet)
    }
  }

  def removeBinding(key: K, value: V): Int = {
    val valueSet: mutable.Map[V, Int] = _values(key)
    if (valueSet(value) == 1) {
      valueSet.remove(value)
      if (valueSet.isEmpty) {
        _values.remove(key)
      }
      0
    } else if (valueSet(value) != 0) {
      val newValue: Int = valueSet(value)
      valueSet(value) = newValue
      newValue
    }
    0
  }

  def remove(key: K): Iterable[V] = {
    _values.remove(key) match {
      case Some(x) => x.flatMap(kv => Seq.fill(kv._2)(kv._1))
      case None => Iterable.empty[V]
    }
  }

  def contains(key: K): Boolean = {
    _values.contains(key)
  }

  def containsComposite(key: K, value: V): Boolean = {
    _values(key).contains(value)
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
