package hu.bme.mit.incqueryds

import java.io.InputStream

import com.twitter.chill.{Input, ScalaKryoInstantiator}

import scala.collection.mutable

/**
 * Created by Maginecz on 3/16/2015.
 */

class WildcardInput(val messageSize: Int = 16) {
  @transient val subscribers = new mutable.HashMap[String, mutable.MutableList[(ChangeSet) => Unit]]
  val usedIDs = new mutable.HashSet[Long]()

  def subscribe(subscriber: Map[String, (ChangeSet) => Unit]) = {
    for ((attribute, func) <- subscriber)
      subscribers.getOrElseUpdate(attribute, mutable.MutableList()) += func
  }

  class BatchTransaction(subsribers: mutable.HashMap[String, mutable.MutableList[(ChangeSet) => Unit]]) {
    def close(): Unit = {
      positiveChangeSets.foreach( kv=> subscribers(kv._1).foreach(sub => sub(ChangeSet(positive = kv._2))))
      negativeChangeSets.foreach( kv=> subscribers(kv._1).foreach(sub => sub(ChangeSet(negative = kv._2))))

    }

    val positiveChangeSets = mutable.HashMap.empty[String, Vector[nodeType]]
    val negativeChangeSets = mutable.HashMap.empty[String, Vector[nodeType]]

    def add(subj: Long, pred: String, obj: Any) = {
      usedIDs.add(subj)
      if (subsribers.contains(pred)) {
        if (!positiveChangeSets.contains(pred))
          positiveChangeSets(pred) = Vector.empty[nodeType]
        positiveChangeSets(pred) +:= Vector(subj, obj)
      }
    }

    def remove(subj: Long, pred: String, obj: Any) = {
      // DO NOT usedIDs.remove(subj), there are enough long values to go around, that having to deal with transient IDs
      // is not worth it
      if (subsribers.contains(pred)) {
        if (!negativeChangeSets.contains(pred))
          negativeChangeSets(pred) = Vector.empty[nodeType]
        negativeChangeSets(pred) +:= Vector(subj, obj)
      }
    }
  }

  class ContinuousTransaction(
    subsribers: mutable.HashMap[String, mutable.MutableList[(ChangeSet) => Unit]],
    messageSize:Int) extends BatchTransaction(subscribers) {

    override def add(subj: Long, pred: String, obj: Any) = {
      super.add(subj, pred, obj)
      if (subsribers.contains(pred) && positiveChangeSets(pred).size == messageSize) {
        subsribers(pred).foreach(sub => sub(ChangeSet(positive = positiveChangeSets(pred))))
        positiveChangeSets(pred) = Vector.empty[nodeType]
      }
    }

    override def remove(subj: Long, pred: String, obj: Any) = {
      super.remove(subj, pred, obj)
      if (subsribers.contains(pred) && negativeChangeSets(pred).size == messageSize) {
        subsribers(pred).foreach(sub => sub(ChangeSet(negative = negativeChangeSets(pred))))
        negativeChangeSets(pred) = Vector.empty[nodeType]
      }
    }
  }

  def newBatchTransaction(): BatchTransaction = {
    return new BatchTransaction(subscribers)
  }

  def newContinousTransaction(): ContinuousTransaction = {
    return new ContinuousTransaction(subscribers, messageSize)
  }

  def initFromTransaction(transaction: ContinuousTransaction): Unit = {
    transaction.close()
  }

  val idGenerator = new scala.util.Random

  def newKey(): Long = {
    var newId: Long = 0L
    do {
      newId = idGenerator.nextLong()
    } while (usedIDs.contains(newId))

    return newId
  }
}

object WildcardInput {
  def apply(stream: InputStream): WildcardInput = {
    val instantiator = new ScalaKryoInstantiator
    instantiator.setRegistrationRequired(false)
    val kryo = instantiator.newKryo()
    val input = new Input(stream)
    kryo.readObject(input, classOf[WildcardInput])
  }
}
