package hu.bme.mit.ire

import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.ChangeSet

import scala.collection.mutable

trait Transaction extends AutoCloseable {
  override def close(): Unit

  def add(pred: String, node: Tuple)

  def remove(pred: String, node: Tuple)
}

class TransactionFactory(val messageSize: Int = 16) {
  val subscribers = new mutable.HashMap[String, mutable.MutableList[(ChangeSet) => Unit]]
  val usedIDs = new mutable.HashSet[Long]()
  val idGenerator = new scala.util.Random

  def subscribe(subscriber: Map[String, (ChangeSet) => Unit]) = {
    for ((attribute, func) <- subscriber)
      subscribers.getOrElseUpdate(attribute, mutable.MutableList()) += func
  }

  def newBatchTransaction(): BatchTransaction = {
    new BatchTransaction()
  }

  def newContinousTransaction(): ContinuousTransaction = {
    new ContinuousTransaction(messageSize)
  }

  def newKey(): Long = {
    var newId: Long = 0L
    do {
      newId = idGenerator.nextLong()
    } while (usedIDs.contains(newId))

    newId
  }

  class BatchTransaction() extends Transaction {
    val positiveChangeSets = mutable.HashMap.empty[String, Vector[Tuple]]
    val negativeChangeSets = mutable.HashMap.empty[String, Vector[Tuple]]

    def close(): Unit = {
      positiveChangeSets.foreach(kv => subscribers(kv._1).foreach(sub => sub(ChangeSet(positive = kv._2))))
      negativeChangeSets.foreach(kv => subscribers(kv._1).foreach(sub => sub(ChangeSet(negative = kv._2))))
      positiveChangeSets.clear()
      negativeChangeSets.clear()
    }

    def add(pred: String, node: Tuple) = {
      if (subscribers.contains(pred)) {
        if (!positiveChangeSets.contains(pred))
          positiveChangeSets(pred) = Vector.empty[Tuple]
        positiveChangeSets(pred) +:= node
      }
    }

    def remove(pred: String, node: Tuple) = {
      // DO NOT usedIDs.remove(subj), there are enough long values to go around, that having to deal with transient IDs
      // is not worth it
      if (subscribers.contains(pred)) {
        if (!negativeChangeSets.contains(pred))
          negativeChangeSets(pred) = Vector.empty[Tuple]
        negativeChangeSets(pred) +:= node
      }
    }
  }

  class ContinuousTransaction(messageSize: Int) extends BatchTransaction() {

    override def add(pred: String, node: Tuple) = {
      super.add(pred, node)
      if (subscribers.contains(pred) && positiveChangeSets(pred).size == messageSize) {
        subscribers(pred).foreach(sub => sub(ChangeSet(positive = positiveChangeSets(pred))))
        positiveChangeSets(pred) = Vector.empty[Tuple]
      }
    }

    override def remove(pred: String, node: Tuple) = {
      super.remove(pred, node)
      if (subscribers.contains(pred) && negativeChangeSets(pred).size == messageSize) {
        subscribers(pred).foreach(sub => sub(ChangeSet(negative = negativeChangeSets(pred))))
        negativeChangeSets(pred) = Vector.empty[Tuple]
      }
    }
  }
}
