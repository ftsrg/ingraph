package hu.bme.mit.incqueryds

import scala.collection.mutable

trait Transaction {
  def add(subj: Long, pred: String, obj: Any)
  def close(): Unit
  def remove(subj: Long, pred: String, obj: Any)
}

class TransactionFactory(val messageSize: Int = 16) {
  val subscribers = new mutable.HashMap[String, mutable.MutableList[(ChangeSet) => Unit]]
  val usedIDs = new mutable.HashSet[Long]()

  def subscribe(subscriber: Map[String, (ChangeSet) => Unit]) = {
    for ((attribute, func) <- subscriber)
      subscribers.getOrElseUpdate(attribute, mutable.MutableList()) += func
  }

  class BatchTransaction() extends Transaction {
    val positiveChangeSets = mutable.HashMap.empty[String, Vector[nodeType]]
    val negativeChangeSets = mutable.HashMap.empty[String, Vector[nodeType]]

    def close(): Unit = {
      positiveChangeSets.foreach( kv=> subscribers(kv._1).foreach(sub => sub(ChangeSet(positive = kv._2))))
      negativeChangeSets.foreach( kv=> subscribers(kv._1).foreach(sub => sub(ChangeSet(negative = kv._2))))
      positiveChangeSets.clear()
      negativeChangeSets.clear()
    }

    def add(subj: Long, pred: String, obj: Any) = {
      usedIDs.add(subj)
      if (subscribers.contains(pred)) {
        if (!positiveChangeSets.contains(pred))
          positiveChangeSets(pred) = Vector.empty[nodeType]
        positiveChangeSets(pred) +:= Vector(subj, obj)
      }
    }

    def remove(subj: Long, pred: String, obj: Any) = {
      // DO NOT usedIDs.remove(subj), there are enough long values to go around, that having to deal with transient IDs
      // is not worth it
      if (subscribers.contains(pred)) {
        if (!negativeChangeSets.contains(pred))
          negativeChangeSets(pred) = Vector.empty[nodeType]
        negativeChangeSets(pred) +:= Vector(subj, obj)
      }
    }
  }

  class ContinuousTransaction(messageSize:Int) extends BatchTransaction() {

    override def add(subj: Long, pred: String, obj: Any) = {
      super.add(subj, pred, obj)
      if (subscribers.contains(pred) && positiveChangeSets(pred).size == messageSize) {
        subscribers(pred).foreach(sub => sub(ChangeSet(positive = positiveChangeSets(pred))))
        positiveChangeSets(pred) = Vector.empty[nodeType]
      }
    }

    override def remove(subj: Long, pred: String, obj: Any) = {
      super.remove(subj, pred, obj)
      if (subscribers.contains(pred) && negativeChangeSets(pred).size == messageSize) {
        subscribers(pred).foreach(sub => sub(ChangeSet(negative = negativeChangeSets(pred))))
        negativeChangeSets(pred) = Vector.empty[nodeType]
      }
    }
  }

  def newBatchTransaction(): BatchTransaction = {
    new BatchTransaction()
  }

  def newContinousTransaction(): ContinuousTransaction = {
    new ContinuousTransaction(messageSize)
  }

  val idGenerator = new scala.util.Random

  def newKey(): Long = {
    var newId: Long = 0L
    do {
      newId = idGenerator.nextLong()
    } while (usedIDs.contains(newId))

    newId
  }
}
