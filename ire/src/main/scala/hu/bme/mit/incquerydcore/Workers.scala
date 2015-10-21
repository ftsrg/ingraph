package hu.bme.mit.incquerydcore

import java.io.{ObjectInputStream, ObjectOutputStream, IOException, InputStream}

import akka.actor.{Actor, ActorRef, Stash}
import com.twitter.chill.{Input, ScalaKryoInstantiator}

import scala.collection.mutable
import scala.collection.mutable.MultiMap
import scala.concurrent.{Future, Promise}

/**
 * Created by Maginecz on 3/16/2015.
 */

class ReteMessage() {}

case class ChangeSet(positive: Vector[nodeType] = Vector(), negative: Vector[nodeType] = Vector())
  extends ReteMessage()

case class ExpectMoreTerminators(val id: Int, val inputs: Iterable[ReteMessage => Unit])

case class TerminatorMessage(terminatorID: Int, messageID: Int, route: List[ActorRef] = List.empty) extends ReteMessage()

case class ExpectTerminator(terminatorID: Int, messageID: Int, promise: Promise[Set[nodeType]]) extends ReteMessage()

case class Pause(messageID: Int) extends ReteMessage()

case class Resume(messageID: Int) extends ReteMessage()

class Terminator private(terminatorID: Int, val inputs: Iterable[ReteMessage => Unit], production: ActorRef) extends ReteMessage with Serializable {
  def send(): Future[Set[nodeType]] = {
  val messageID = Terminator.idCounter.getNext()
  val promise = Promise[Set[nodeType]]
  production ! ExpectTerminator(terminatorID, messageID, promise)
  val future = promise.future
  inputs.foreach(input => {
    input(Pause(messageID))
    input(TerminatorMessage(terminatorID, messageID))
  })
  future
  }

  @throws(classOf[IOException])
  private def writeObject(out: ObjectOutputStream): Unit = {}

  @throws(classOf[IOException])
  private def readObject(in: ObjectInputStream): Unit = {}
}

object Terminator {
  val idCounter = new AtomicUniqueCounter

  def apply(inputs: Iterable[ReteMessage => Unit], productionNode: ActorRef): Terminator = {
  val id = idCounter.getNext()
  productionNode ! ExpectMoreTerminators(id, inputs)
  new Terminator(id, inputs, productionNode)
  }
}

trait ForkingForwarder {
  val children: Vector[ReteMessage => Unit]

  if (children.size < 2)
  throw new IllegalArgumentException("use base class for 1 child node")

  def forwardHashFunction(n: nodeType): Int

  def forward(cs: ChangeSet) = {
    cs.positive.groupBy( node => Math.abs(forwardHashFunction(node)) % children.size).foreach(kv => if (kv._2.size > 0) children(kv._1)(ChangeSet(positive = kv._2.toVector)))
    cs.negative.groupBy( node => Math.abs(forwardHashFunction(node)) % children.size).foreach(kv => if (kv._2.size > 0) children(kv._1)(ChangeSet(negative = kv._2.toVector)))
  }

  def forward(t: TerminatorMessage) = children.foreach(_ (t))

}

trait SingleForwarder {
  val next: ReteMessage => Unit

  def forward(cs: ChangeSet) = if (cs.positive.size > 0 || cs.negative.size > 0) next(cs)

  def forward(terminator: TerminatorMessage) = next(terminator)
}

abstract trait Forwarder {
  def forward(cs: ChangeSet)

  def forward(terminator: TerminatorMessage)
}

trait TerminatorHandler {
  val expectedTerminatorCount: Int
  val terminatorCount = new mutable.HashMap[Int, Int]
  def forward(terminator: TerminatorMessage)
  def handleTerminator(terminator: TerminatorMessage): Unit = {
  val count = terminatorCount.getOrElse(terminator.messageID, 0) + 1
  if ( count == expectedTerminatorCount) {
    forward(terminator)
    terminatorCount -= terminator.messageID
  }
  terminatorCount(terminator.messageID) = count
  }
}
abstract class AlphaNode(val next: (ReteMessage) => Unit, val expectedTerminatorCount: Int = 1) extends Actor with Forwarder with Stash with TerminatorHandler {
  KamonInitializer.ping
  val log = context.system.log
  def onChangeSet(changeSet: ChangeSet)

  override def receive: Actor.Receive = {
  case pause: Pause => context.become({
    case resume: Resume => {
    if (resume.messageID == pause.messageID) {
      context.unbecome()
      unstashAll()
    } else stash()
    }
    case terminator: TerminatorMessage => handleTerminator(terminator)
    case _ => stash()
  })
  case changeSet: ChangeSet => onChangeSet(changeSet)
  case terminator: TerminatorMessage => handleTerminator(terminator)
  case other => throw new UnsupportedOperationException(s"alpha received unsupported msg $other")
  }
}

abstract class BetaNode(val expectedTerminatorCount: Int = 2) extends Actor with Forwarder with Stash with TerminatorHandler {

  def onPrimary(changeSet: ChangeSet)

  def onSecondary(changeSet: ChangeSet)

  var primaryPause: Option[Pause] = None
  var secondaryPause: Option[Pause] = None
  override def receive: Actor.Receive = {
  case Primary(reteMessage: ReteMessage) => {
    primaryPause match {
    case Some(pause) => {
      reteMessage match {
      case resume: Resume =>{
        if (resume.messageID == pause.messageID)
        primaryPause = None
        if (secondaryPause.isEmpty)
        unstashAll()
      }
      case terminator: TerminatorMessage => {
        if (terminator.messageID == pause.messageID)
        handleTerminator(terminator)
        else
        stash()
      }
      case other => stash()
      }
    }
    case None => reteMessage match {
      case pause: Pause => primaryPause = Some(pause)
      case cs: ChangeSet => onPrimary(cs)
      case t: TerminatorMessage => handleTerminator(t)
    }
    }
  }
  case Secondary(reteMessage: ReteMessage) => {
    secondaryPause match  {
    case Some(pause) => {
      reteMessage match {
      case resume: Resume =>{
        if (resume.messageID == pause.messageID)
        secondaryPause = None
        if (primaryPause.isEmpty)
        unstashAll()
      }
      case terminator: TerminatorMessage => {
        if (terminator.messageID == pause.messageID)
        handleTerminator(terminator)
        else
        stash()
      }
      case other => stash()
      }
    }
    case None => reteMessage match {
      case pause: Pause => secondaryPause = Some(pause)
      case cs: ChangeSet => onSecondary(cs)
      case t: TerminatorMessage => handleTerminator(t)
    }

    }
  }
  case other => throw new UnsupportedOperationException(s"beta received unsupported msg $other")
  }
}

class Trimmer(override val next: (ReteMessage) => Unit, val selectionVector: Vector[Int]) extends AlphaNode(next) with SingleForwarder {
  override def onChangeSet(changeSet: ChangeSet) = {
  forward(ChangeSet(
    changeSet.positive.map(vec => selectionVector.map(i => vec(i))),
    changeSet.negative.map(vec => selectionVector.map(i => vec(i)))
  ))
  }
}

case class Primary(value: ReteMessage)

case class Secondary(value: ReteMessage)

class HashJoiner(override val next: (ReteMessage) => Unit,
         override val primaryLength: Int, override val primarySelector: Vector[Int],
         override val secondaryLength: Int, override val secondarySelector: Vector[Int]
          ) extends HashJoinerImpl(primaryLength, primarySelector, secondaryLength, secondarySelector) with SingleForwarder {
}

class ParallelHashJoiner(override val children: Vector[(ReteMessage) => Unit],
             override val primaryLength: Int, override val primarySelector: Vector[Int],
             override val secondaryLength: Int, override val secondarySelector: Vector[Int],
             hashFunction: (nodeType) => Int = n => n.hashCode()
              ) extends HashJoinerImpl(primaryLength, primarySelector, secondaryLength, secondarySelector) with ForkingForwarder {
  override def forwardHashFunction(n: nodeType): Int = hashFunction(n)

  override def forward(cs: ChangeSet) = super[ForkingForwarder].forward(cs)
  override def forward(t: TerminatorMessage) = super[ForkingForwarder].forward(t)
}

abstract class HashJoinerImpl(val primaryLength: Int, val primarySelector: Vector[Int],
         val secondaryLength: Int, val secondarySelector: Vector[Int])
  extends BetaNode {
  val primaryValues = new mutable.HashMap[nodeType, mutable.Set[nodeType]] with MultiMap[nodeType, nodeType]
  val secondaryValues = new mutable.HashMap[nodeType, mutable.Set[nodeType]] with mutable.MultiMap[nodeType, nodeType]

  val inversePrimarySelector = Vector.range(0, primaryLength) filter (i => !primarySelector.contains(i))
  val inverseSecondarySelector = Vector.range(0, secondaryLength) filter (i => !secondarySelector.contains(i))


  def onPrimary(changeSet: ChangeSet): Unit = {
  val positive = changeSet.positive
  val negative = changeSet.negative

  val joinedPositive = for {
    primaryVec <- positive
    key = primarySelector.map(i => primaryVec(i))
    if secondaryValues.contains(key)
    secondaryVec <- secondaryValues(key)
  } yield primaryVec ++ inverseSecondarySelector.map(i => secondaryVec(i))

  val joinedNegative = for {
    primaryVec <- negative
    key = primarySelector.map(i => primaryVec(i))
    if secondaryValues.contains(key)
    secondaryVec <- secondaryValues(key)
  } yield primaryVec ++ inverseSecondarySelector.map(i => secondaryVec(i))

  forward(ChangeSet(joinedPositive, joinedNegative))
  positive.foreach(
    vec => {
    val key = primarySelector.map(i => vec(i))
    primaryValues.addBinding(key, vec)
    }
  )
  negative.foreach(
    vec => {
    val key = primarySelector.map(i => vec(i))
    primaryValues.removeBinding(key, vec)
    }
  )
  }

  def onSecondary(changeSet: ChangeSet): Unit = {
  val positive = changeSet.positive
  val negative = changeSet.negative

  val joinedPositive = for {
    secondaryVec <- positive
    key = secondarySelector.map(i => secondaryVec(i))
    if primaryValues.contains(key)
    primaryVec <- primaryValues(key)
  } yield primaryVec ++ inverseSecondarySelector.map(i => secondaryVec(i))


  val joinedNegative = for {
    secondaryVec <- negative
    key = secondarySelector.map(i => secondaryVec(i))
    if primaryValues.contains(key)
    primaryVec <- primaryValues(key)
  } yield primaryVec ++ inverseSecondarySelector.map(i => secondaryVec(i))

  forward(ChangeSet(joinedPositive, joinedNegative))

  positive.foreach(
    vec => {
    val key = secondarySelector.map(i => vec(i))
    secondaryValues.addBinding(key, vec) //must be used with multimaps
    }
  )
  negative.foreach(
    vec => {
    val key = secondarySelector.map(i => vec(i))
    secondaryValues.removeBinding(key, vec)
    }
  )
  }
}



class Checker(override val next: (ReteMessage) => Unit,
        val condition: (nodeType) => Boolean,
        override val expectedTerminatorCount:Int = 1) extends AlphaNode(next) with SingleForwarder  {
  def onChangeSet(changeSet: ChangeSet): Unit = {
  forward(ChangeSet(
    changeSet.positive.filter(condition),
    changeSet.negative.filter(condition)
  ))
  }
}

class InequalityChecker(override val next: (ReteMessage) => Unit,
            val nodeIndex: Int, val inequals: Vector[Int],
            override val expectedTerminatorCount:Int = 1) extends
Checker(next, condition = (node: nodeType) => {
  !inequals.map { i => node(i) }.exists { value => value == node(nodeIndex) }
}
)  with SingleForwarder

class EqualityChecker(override val next: (ReteMessage) => Unit,
            val nodeIndex: Int, val equals: Vector[Int],
            override val expectedTerminatorCount:Int = 1) extends
Checker(next, condition = (node: nodeType) => {
  equals.map { i => node(i) }.forall { value => value == node(nodeIndex) }
}
)  with SingleForwarder

class HashAntiJoiner(override val next: (ReteMessage) => Unit,
           val primarySelector: Vector[Int],
           val secondarySelector: Vector[Int],
           override val expectedTerminatorCount:Int = 2)
  extends BetaNode with SingleForwarder  {
  val primaryValues = new mutable.HashMap[nodeType, mutable.Set[nodeType]] with MultiMap[nodeType, nodeType]
  val secondaryValues = new mutable.HashMap[nodeType, mutable.Set[nodeType]] with MultiMap[nodeType, nodeType]


  def onPrimary(changeSet: ChangeSet): Unit = {
  val positive = changeSet.positive
  val negative = changeSet.negative

  val joinedPositive = for {
    node <- positive
    if !secondaryValues.contains(primarySelector.map(i => node(i)))
  } yield node


  val joinedNegative = for {
    node <- negative
  } yield node

  forward(ChangeSet(joinedPositive, joinedNegative))

  positive.foreach(
    vec => {
    val key = primarySelector.map(i => vec(i))
    primaryValues.addBinding(key, vec)
    }
  )
  negative.foreach(
    vec => {
    val key = primarySelector.map(i => vec(i))
    primaryValues.removeBinding(key, vec)
    }
  )
  }

  def onSecondary(changeSet: ChangeSet): Unit = {
  val positive = changeSet.positive
  val negative = changeSet.negative

  positive.foreach(
    vec => {
    val key = secondarySelector.map(i => vec(i))
    secondaryValues.addBinding(key, vec)
    }
  )
  negative.foreach(
    vec => {
    val key = secondarySelector.map(i => vec(i))
    secondaryValues.removeBinding(key, vec)
    }
  )

  val joinedNegative = (for {//this is switched because antijoin
    node <- positive
    key = secondarySelector.map(i => node(i))
    if secondaryValues.contains(key)
    if primaryValues.contains(key)
    value <- primaryValues(key)
  } yield value).distinct

  val joinedPositive = (for {
    node <- negative
    key = secondarySelector.map(i => node(i))
    if !secondaryValues.contains(key)
    if primaryValues.contains(key)
    value <- primaryValues(key)
  } yield value).distinct

  forward(ChangeSet(joinedPositive, joinedNegative))
  }
}

class Production(queryName: String, val expectedTerminatorCount:Int = 1) extends Actor {
  val log = context.system.log

  val receivedTerminatorCount = mutable.Map.empty[Int, Int]

  var t0 = System.nanoTime()

  val results = new mutable.HashSet[nodeType]
  val terminatorPromises = mutable.Map.empty[Int, Promise[Set[nodeType]]]
  val inputsToResume = mutable.Map.empty[Int, Iterable[ReteMessage => Unit]]


  def getAndResetElapsedTime(): Long = {
  val t1 = System.nanoTime()
  val retVal = t1 - t0
  t0 = t1
  return retVal
  }

  override def receive: Actor.Receive = {
  case ChangeSet(p, n) => {
    p.foreach {
    results.add(_)
    }
    n.foreach {
    results.remove(_)
    }
  }

  case ExpectMoreTerminators(terminatorID, inputs) => inputsToResume(terminatorID) = inputs

  case TerminatorMessage(terminatorID, messageID, route) => {
    log.info(s"terminator$messageID received")
    receivedTerminatorCount(messageID) += 1
    if (receivedTerminatorCount(messageID) == expectedTerminatorCount) {
    inputsToResume(terminatorID).foreach(input => input(Resume(messageID)))
    terminatorPromises(messageID).success(results.toSet)
    receivedTerminatorCount.drop(messageID)
    terminatorPromises.drop(messageID)

    }
  }
  case ExpectTerminator(terminatorID, messageID, promise) => {
    receivedTerminatorCount(messageID) = 0
    terminatorPromises(messageID) = promise
  }
  }
}

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
    if (positiveChangeSets(pred).size > messageSize) {
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
    if (negativeChangeSets(pred).size > messageSize) {
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
