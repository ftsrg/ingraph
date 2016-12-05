package hu.bme.mit.ire.listeners

import hu.bme.mit.ire.datatypes.TupleType

import scala.collection.mutable

abstract class ChangeListener {
  val positive = new mutable.ListBuffer[TupleType]
  val negative = new mutable.ListBuffer[TupleType]

  def added(tuple: TupleType) = positive += tuple

  def removed(tuple: TupleType) = negative += tuple

  def terminated() = {
    listener(positive.toList, negative.toList)
    positive.clear()
    negative.clear()
  }

  def listener(positive: List[TupleType], negative: List[TupleType])
}
