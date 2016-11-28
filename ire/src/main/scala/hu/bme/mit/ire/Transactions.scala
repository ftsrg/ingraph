package hu.bme.mit.ire

import scala.collection.mutable

trait Transaction extends AutoCloseable {
  override def close(): Unit
  def add(pred: String, node: TupleType)
  def remove(pred: String, node: TupleType)
}

class TransactionFactory(val messageSize: Int = 16) {
  val subscribers = new mutable.HashMap[String, mutable.MutableList[(ChangeSet) => Unit]]
  val usedIDs = new mutable.HashSet[Long]()

  def subscribe(subscriber: Map[String, (ChangeSet) => Unit]) = {
    for ((attribute, func) <- subscriber)
      subscribers.getOrElseUpdate(attribute, mutable.MutableList()) += func
  }

  class BatchTransaction() extends Transaction {
    val positiveChangeSets = mutable.HashMap.empty[String, Vector[TupleType]]
    val negativeChangeSets = mutable.HashMap.empty[String, Vector[TupleType]]

    def close(): Unit = {
      positiveChangeSets.foreach( kv=> subscribers(kv._1).foreach(sub => sub(ChangeSet(positive = kv._2))))
      negativeChangeSets.foreach( kv=> subscribers(kv._1).foreach(sub => sub(ChangeSet(negative = kv._2))))
      positiveChangeSets.clear()
      negativeChangeSets.clear()
    }

    def add(pred: String, node: TupleType) = {
      if (subscribers.contains(pred)) {
        if (!positiveChangeSets.contains(pred))
          positiveChangeSets(pred) = Vector.empty[TupleType]
        positiveChangeSets(pred) +:= node
      }
    }

    def remove(pred: String, node: TupleType) = {
      // DO NOT usedIDs.remove(subj), there are enough long values to go around, that having to deal with transient IDs
      // is not worth it
      if (subscribers.contains(pred)) {
        if (!negativeChangeSets.contains(pred))
          negativeChangeSets(pred) = Vector.empty[TupleType]
        negativeChangeSets(pred) +:= node
      }
    }
  }

  class ContinuousTransaction(messageSize:Int) extends BatchTransaction() {

    override def add(pred: String, node: TupleType) = {
      super.add(pred, node)
      if (subscribers.contains(pred) && positiveChangeSets(pred).size == messageSize) {
        subscribers(pred).foreach(sub => sub(ChangeSet(positive = positiveChangeSets(pred))))
        positiveChangeSets(pred) = Vector.empty[TupleType]
      }
    }

    override def remove(pred: String, node: TupleType) = {
      super.remove(pred, node)
      if (subscribers.contains(pred) && negativeChangeSets(pred).size == messageSize) {
        subscribers(pred).foreach(sub => sub(ChangeSet(negative = negativeChangeSets(pred))))
        negativeChangeSets(pred) = Vector.empty[TupleType]
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
