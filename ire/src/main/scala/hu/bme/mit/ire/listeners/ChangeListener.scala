package hu.bme.mit.ire.listeners

import hu.bme.mit.ire.datatypes.Tuple

import scala.collection.mutable

abstract class ChangeListener {
  val positive = new mutable.ListBuffer[Tuple]
  val negative = new mutable.ListBuffer[Tuple]

  def added(tuple: Tuple) = positive += tuple

  def removed(tuple: Tuple) = negative += tuple

  def terminated() = {
    listener(positive.toList, negative.toList)
    positive.clear()
    negative.clear()
  }

  def listener(positive: List[Tuple], negative: List[Tuple])
}
