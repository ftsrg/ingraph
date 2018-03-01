package hu.bme.mit.ire.listeners

import hu.bme.mit.ire.datatypes.Tuple

import scala.collection.mutable

trait ChangeListener {
  def added(tuple: Tuple)
  def removed(tuple: Tuple)
  def terminated()
}

abstract class ConsistentChangeListener extends ChangeListener {
  val positive = mutable.ListBuffer[Tuple]()
  val negative = mutable.ListBuffer[Tuple]()

  override def added(tuple: Tuple): Unit = positive += tuple

  override def removed(tuple: Tuple): Unit = negative += tuple

  override def terminated(): Unit = {
    listener(positive.toVector, negative.toVector)
    positive.clear()
    negative.clear()
  }

  def listener(positive: Vector[Tuple], negative: Vector[Tuple])
}

abstract class InstantChangeListener extends ChangeListener {
  override def terminated(): Unit = {}
}
