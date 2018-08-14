package hu.bme.mit.ire

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.ChangeSet

import scala.collection.mutable

trait InputMultiplexer {
  def close(): Unit
  def add(pred: String, tuple: Tuple)
  def remove(pred: String, tuple: Tuple)
}

class InputMultiplexerFactory {
  val subscribers = new mutable.HashMap[String, mutable.MutableList[(ChangeSet) => Unit]]

  def subscribe(subscriber: Map[String, (ChangeSet) => Unit]) = {
    for ((attribute, func) <- subscriber)
      subscribers.getOrElseUpdate(attribute, mutable.MutableList()) += func
  }

  def newInputMultiplexer(): BatchInputMultiplexer = {
    new BatchInputMultiplexer
  }

  class BatchInputMultiplexer extends InputMultiplexer {
    val positiveChangeSets = mutable.HashMap.empty[String, Vector[Tuple]]
    val negativeChangeSets = mutable.HashMap.empty[String, Vector[Tuple]]

    override def close(): Unit = {
      positiveChangeSets.foreach(kv => subscribers(kv._1).foreach(sub => sub(ChangeSet(positive = kv._2))))
      negativeChangeSets.foreach(kv => subscribers(kv._1).foreach(sub => sub(ChangeSet(negative = kv._2))))
      positiveChangeSets.clear()
      negativeChangeSets.clear()
    }

    override def add(pred: String, tuple: Tuple): Unit = {
      if (subscribers.contains(pred)) {
        if (!positiveChangeSets.contains(pred))
          positiveChangeSets(pred) = Vector.empty[Tuple]
        positiveChangeSets(pred) +:= tuple
      }
    }

    override def remove(pred: String, tuple: Tuple): Unit = {
      if (subscribers.contains(pred)) {
        if (!negativeChangeSets.contains(pred))
          negativeChangeSets(pred) = Vector.empty[Tuple]
        negativeChangeSets(pred) +:= tuple
      }
    }
  }

}
