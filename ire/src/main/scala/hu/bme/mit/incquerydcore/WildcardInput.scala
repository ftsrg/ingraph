package hu.bme.mit.incquerydcore

import java.io.InputStream

import com.twitter.chill.{Input, ScalaKryoInstantiator}

import scala.collection.mutable

/**
 * Created by Maginecz on 3/16/2015.
 */


















class WildcardInput(val messageSize: Int = 16) {
  //  val types = new mutable.HashMap[String, mutable.Set[Long]]()
  var multiValueAttributes = createWildcardMap
  //  val attributes = new mutable.HashMap[String, mutable.Map[Long, Any]]

  @transient val subscribers = new mutable.HashMap[String, mutable.MutableList[(ChangeSet) => Unit]]

  private def createWildcardMap() = new mutable.HashMap[String, mutable.HashMap[Long, mutable.Set[Any]] with IterableMultiMap[Long, Any]]

  private def createInnerMap() = new mutable.HashMap[Long, mutable.Set[Any]] with IterableMultiMap[Long, Any]


  class Transaction(subsribers: mutable.HashMap[String, mutable.MutableList[(ChangeSet) => Unit]], messageSize:Int) {
  def close(): Unit = {
    positiveChangeSets.foreach( kv=> subscribers(kv._1).foreach(sub => sub(ChangeSet(positive = kv._2))))
    negativeChangeSets.foreach( kv=> subscribers(kv._1).foreach(sub => sub(ChangeSet(negative = kv._2))))

  }

  val positive = createWildcardMap()
  val negative = createWildcardMap()

  val positiveChangeSets = mutable.HashMap.empty[String, Vector[nodeType]]
  val negativeChangeSets = mutable.HashMap.empty[String, Vector[nodeType]]

  def add(subj: Long, pred: String, obj: Any) = {
    if (subsribers.contains(pred)) {
    if (!positiveChangeSets.contains(pred))
      positiveChangeSets(pred) = Vector.empty[nodeType]
    positiveChangeSets(pred) +:= Vector(subj, obj)
    if (positiveChangeSets(pred).size == messageSize) {
      subsribers(pred).foreach(sub => sub(ChangeSet(positive = positiveChangeSets(pred))))
      positiveChangeSets(pred) = Vector.empty[nodeType]
    }
    }
  }

  def remove(subj: Long, pred: String, obj: Any) = {
    if (subsribers.contains(pred)) {
    if (!negativeChangeSets.contains(pred))
      negativeChangeSets(pred) = Vector.empty[nodeType]
    negativeChangeSets(pred) +:= Vector(subj, obj)
    if (negativeChangeSets(pred).size == messageSize) {
      subsribers(pred).foreach(sub => sub(ChangeSet(negative = negativeChangeSets(pred))))
      negativeChangeSets(pred) = Vector.empty[nodeType]
    }
    }
  }
  }
  def newTransaction(): Transaction = {
  return new Transaction(subscribers, messageSize)
  }
  def initFromTransaction(transaction: Transaction): Unit = {
//  transaction.positive.par.foreach { case (pred, map) => {
//    val (obj, subj) = map.multiUnzip
//    val iterator = (subj zip obj)
//    iterator.toVector.grouped(messageSize).foreach(
//    msgGroup => subscribers.getOrElse(pred, mutable.MutableList.empty[(ChangeSet) => Unit]).foreach(
//      sub => sub(ChangeSet(positive = msgGroup.map(msg => Vector(msg._1, msg._2))))
//    )
//    )
//  }
//  }
//  multiValueAttributes = transaction.positive
  transaction.close()
  }

  def processTransaction(transaction: Transaction): Unit = {
  transaction.negative.par.foreach { case (pred, map) => {
    val (obj, subj) = map.multiUnzip
    remove(obj, pred, subj)
  }
  }
  transaction.positive.par.foreach { case (pred, map) => {
    val (obj, subj) = map.multiUnzip
    add(obj, pred, subj)
  }
  }
  transaction.close()
  }

  def add(subj: Iterable[Long], pred: String, obj: Iterable[Any]) = {
  val iterator = (subj zip obj)
  iterator.toVector.grouped(messageSize).foreach(
    msgGroup => subscribers.getOrElse(pred, mutable.MutableList.empty[(ChangeSet) => Unit]).foreach(
    sub => sub(ChangeSet(positive = msgGroup.map(msg => Vector(msg._1, msg._2))))
    )
  )
  val map = multiValueAttributes.synchronized {
    multiValueAttributes.getOrElseUpdate(pred, createInnerMap())
  }
  iterator.foreach(tup => map.addBinding(tup._1, tup._2))
  }

  def remove(subj: Iterable[Long], pred: String, obj: Iterable[Any]): Unit = {
  val iterator = (subj zip obj)
  iterator.toVector.grouped(messageSize).foreach(
    msgGroup => subscribers(pred).foreach(
    sub => sub(ChangeSet(negative = msgGroup.map(msg => Vector(msg._1, msg._2))))
    )
  )
  val map = multiValueAttributes.synchronized {
    multiValueAttributes.getOrElseUpdate(pred, createInnerMap())
  }
  iterator.foreach(tup => map.removeBinding(tup._1, tup._2))
  }

  def subscribe(subscriber: Map[String, (ChangeSet) => Unit]) = {
  sendAll(recipient = subscriber)
  for ((attribute, func) <- subscriber)
    subscribers.getOrElseUpdate(attribute, mutable.MutableList()) += func
  }

  def sendAll(recipient: Map[String, (ChangeSet) => Unit]) = {
  for ((attribute, func) <- recipient) {
    for ((id, values) <- multiValueAttributes.getOrElseUpdate(attribute, createInnerMap);
       output <- values.grouped(messageSize))
    func(ChangeSet(positive = output.toVector.map((v) => Vector(id, v))))
  }
  }

  val idGenerator = new scala.util.Random

  def newKey(): Long = {
  var newId: Long = 0L
  do {
    newId = idGenerator.nextLong()
  } while (multiValueAttributes.forall(kv => kv._2.contains(newId)))
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
