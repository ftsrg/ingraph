import java.io.InputStream

import akka.actor.{Actor, ActorRef}
import com.twitter.chill.{Input, ScalaKryoInstantiator}

import scala.concurrent.{Future, Promise}
package hu.bme.mit.IQDcore {

import hu.bme.mit.IQDcore.Workers.nodeType

import scala.collection.mutable

/**
 * Created by Maginecz on 3/16/2015.
 */
package object Workers {
  type nodeType = Vector[Any]
}

class ReteMessage() {}

case class ChangeSet(positive: Vector[nodeType] = Vector(), negative: Vector[nodeType] = Vector())
  extends ReteMessage()

class Probe(id: Int, production: ActorRef, route: List[ActorRef]) extends ReteMessage {
  def forkMessage(count: Int): Unit = {
  production ! ExpectMoreTerminators(id, count: Int)
  }

  def exec(current: ActorRef): Probe = {
  new Probe(id, production, current :: route)
  }
}

case class ExpectMoreTerminators(id: Int, count: Int)

case class TerminatorMessage(terminatorID: Int, messageID: Int, route: List[ActorRef] = List.empty) extends ReteMessage()

case class ExpectTerminator(terminatorID: Int, messageID: Int, promise: Promise[Set[nodeType]]) extends ReteMessage()

class Terminator private(terminatorID: Int, val inputs: Iterable[ReteMessage => Unit], production: ActorRef) extends ReteMessage {
  def send(): Future[Set[nodeType]] = {
  val messageID = Terminator.idCounter.getNext()
  val promise = Promise[Set[nodeType]]
  production ! ExpectTerminator(terminatorID, messageID, promise)
  inputs.foreach(_(TerminatorMessage(terminatorID, messageID)))
  promise.future
  }
}

object Terminator {
  val idCounter = new AtomicUniqueCounter

  def apply(inputs: Iterable[ReteMessage => Unit], productionNode: ActorRef): Terminator = {
  val id = idCounter.getNext()
  productionNode ! ExpectMoreTerminators(id, inputs.size)
  inputs.foreach(_(new Probe(id, productionNode, List.empty[ActorRef])))
  new Terminator(id, inputs, productionNode)
  }
}

trait Forwarder {
  val next: ReteMessage => Unit

  def forward(cs: ChangeSet) = next(cs)

  def forward(terminator: TerminatorMessage) = next(terminator)

  def forward(probe: Probe) = next(probe)
}

abstract class AlphaNode(val next: (ReteMessage) => Unit) extends Actor with Forwarder {

  def onChangeSet(changeSet: ChangeSet)

  override def receive: Actor.Receive = {
  case changeSet: ChangeSet => onChangeSet(changeSet)
  case probe: Probe => forward(probe.exec(self))
  case terminator: TerminatorMessage => forward(terminator)
  }
}

abstract class BetaNode(val next: (ReteMessage) => Unit) extends Actor with Forwarder {

  def onPrimary(changeSet: ChangeSet)

  def onSecondary(changeSet: ChangeSet)

  override def receive: Actor.Receive = {
  case Primary(changeSet: ChangeSet) => onPrimary(changeSet)
  case Secondary(changeSet: ChangeSet) => onSecondary(changeSet)

  case probe: Probe => forward(probe.exec(self))
  case Primary(probe: Probe) => forward(probe.exec(self))
  case Secondary(probe: Probe) => forward(probe.exec(self))
  case terminator: TerminatorMessage => forward(terminator)
  case Primary(terminator: TerminatorMessage) => forward(terminator)
  case Secondary(terminator: TerminatorMessage) => forward(terminator)
  }
}

class Trimmer(override val next: (ReteMessage) => Unit, val selectionVector: Vector[Int]) extends AlphaNode(next) {
  override def onChangeSet(changeSet: ChangeSet) = {
  forward(ChangeSet(
    changeSet.positive.map(vec => selectionVector.map(i => vec(i))),
    changeSet.negative.map(vec => selectionVector.map(i => vec(i)))
  ))
  }
}

import scala.collection.mutable.MultiMap

case class Primary(value: ReteMessage)

case class Secondary(value: ReteMessage)

class HashJoiner(override val next: (ReteMessage) => Unit,
         val primaryLength: Int, val primarySelector: Vector[Int],
         val secondaryLength: Int, val secondarySelector: Vector[Int])
  extends BetaNode(next) {
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
        val condition: (nodeType) => Boolean) extends AlphaNode(next) {
  def onChangeSet(changeSet: ChangeSet): Unit = {
  forward(ChangeSet(
    changeSet.positive.filter(condition),
    changeSet.negative.filter(condition)
  ))
  }
}

class InequalityChecker(override val next: (ReteMessage) => Unit,
            val nodeIndex: Int, val inequals: Vector[Int]) extends
Checker(next, condition = (node: nodeType) => {
  !inequals.map { i => node(i) }.exists { value => value == node(nodeIndex) }
}
)

class EqualityChecker(override val next: (ReteMessage) => Unit,
            val nodeIndex: Int, val equals: Vector[Int]) extends
Checker(next, condition = (node: nodeType) => {
  equals.map { i => node(i) }.forall { value => value == node(nodeIndex) }
}
)

class HashAntiJoiner(override val next: (ReteMessage) => Unit,
           val primarySelector: Vector[Int],
           val secondarySelector: Vector[Int])
  extends BetaNode(next) {
  val primaryValues = new mutable.HashMap[nodeType, mutable.Set[nodeType]] with MultiMap[nodeType, nodeType]
  val secondaryValues = new mutable.HashSet[nodeType]

  def onPrimary(changeSet: ChangeSet): Unit = {
  val positive = changeSet.positive
  val negative = changeSet.negative

  val joinedPositive = for {
    node <- positive
    if !secondaryValues.contains(primarySelector.map(i => node(i)))
  } yield node


  val joinedNegative = for {
    node: nodeType <- negative
    if secondaryValues.contains(primarySelector.map(i => node(i)))
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

  val joinedNegative = for {//this is switched because antijoin
    node <- positive
    key = secondarySelector.map(i => node(i))
    if primaryValues.contains(key)
  } yield primaryValues(key)

  val joinedPositive = for {
    node: nodeType <- negative
    key = secondarySelector.map(i => node(i))
    if primaryValues.contains(secondarySelector.map(i => node(i)))
  } yield primaryValues(node)

  forward(ChangeSet(joinedPositive.flatten, joinedNegative.flatten))

  positive.foreach(
    vec => {
    val key = secondarySelector.map(i => vec(i))
    secondaryValues.add(key) //must be used with multimaps
    }
  )
  negative.foreach(
    vec => {
    val key = secondarySelector.map(i => vec(i))
    secondaryValues.remove(key)
    }
  )
  }
}

class Production(queryName: String) extends Actor {
  var expectedTerminators = mutable.Map.empty[Int, Int]
  val receivedTerminatorCount = mutable.Map.empty[Int, Int]

  var t0 = System.nanoTime()

  val results = new mutable.HashSet[nodeType]
  val terminatorPromises = mutable.Map.empty[Int, Promise[Set[nodeType]]]


  def getAndResetElapsedTime(): Long = {
  val t1 = System.nanoTime()
  val retVal = t1 - t0
  t0 = t1
  return retVal
  }

  override def receive: Actor.Receive = {
  case ChangeSet(p, n) => {
    val t1 = System.nanoTime()
    p.foreach {
    results.add(_)
    }
    n.foreach {
    results.remove(_)
    }
  }
  case ExpectMoreTerminators(id, count) => {
    val current = expectedTerminators.getOrElseUpdate(id, 0)
    expectedTerminators(id) = current + 1
  }
  case TerminatorMessage(terminatorID, messageID, route) => {
    receivedTerminatorCount(messageID) += 1
    if (receivedTerminatorCount(messageID) == expectedTerminators(terminatorID)) {
    val timeNano = getAndResetElapsedTime()
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

class WildcardInput(val messageSize:Int = 16) {
//  val types = new mutable.HashMap[String, mutable.Set[Long]]()
  val multiValueAttributes = createWildcardMap
//  val attributes = new mutable.HashMap[String, mutable.Map[Long, Any]]

  @transient val subscribers = new mutable.HashMap[String, mutable.MutableList[(ChangeSet) => Unit]]

  private def createWildcardMap() =  new mutable.HashMap[String, mutable.HashMap[Long,mutable.Set[Any]] with IterableMultiMap[Long,Any]]
  private def createInnerMap() = new mutable.HashMap[Long,mutable.Set[Any]] with IterableMultiMap[Long, Any]


  class Transaction {
  def close(): Unit = {
    positive.empty
    negative.empty
  }

  val positive =  createWildcardMap()
  val negative= createWildcardMap()

  def add(subj: Long, pred: String, obj: Any) = {
    val map = positive.getOrElseUpdate(pred, createInnerMap())
    map.addBinding(subj, obj)
  }
  def remove(subj: Long, pred: String, obj: Any) = {
    val map = negative.getOrElseUpdate(pred, createInnerMap())
    map.addBinding(subj, obj)
  }
  }

  def newTransaction(): Transaction = {
  return new Transaction
  }

  def processTransaction(transaction: Transaction): Unit = {
  transaction.positive.foreach { case (pred, map) => {
    val (obj, subj) = map.multiUnzip
    add(obj, pred, subj)
    }
  }
  transaction.negative.foreach{ case(pred, map) =>{
    val (obj, subj) = map.multiUnzip
    remove(obj,pred,subj)
  }
  transaction.close()
  }
  }

  def addSingleAttribute(subj: Long, pred: String, obj: Any): Unit = {
  val map = multiValueAttributes.getOrElseUpdate(pred, createInnerMap())
  map.addBinding(subj,obj)
  subscribers.getOrElse(pred, mutable.MutableList.empty[(ChangeSet) => Unit]).foreach(
    sub => sub(ChangeSet(positive = Vector(Vector(subj,obj))))
  )
  }

  def add(subj: Iterable[Long], pred: String, obj:Iterable[Any]) = {
  val iterator =(subj zip obj)
  iterator.toVector.grouped(messageSize).foreach(
    msgGroup => subscribers.getOrElse(pred, mutable.MutableList.empty[(ChangeSet) => Unit]).foreach(
    sub => sub(ChangeSet(positive = msgGroup.map( msg => Vector(msg._1,msg._2))))
    )
  )
  iterator.foreach( tup =>{
    val map = multiValueAttributes.getOrElseUpdate(pred, createInnerMap())
    map.addBinding(tup._1, tup._2)
  }  )
  }

  def removeSingleAttribute(subj: Long, pred: String, obj: Any): Unit = {
  multiValueAttributes(pred).removeBinding(subj,obj)
  subscribers.getOrElse(pred, mutable.MutableList.empty[(ChangeSet) => Unit]).foreach(
    sub => sub(ChangeSet(negative = Vector(Vector(subj,obj))))
  )
  }
  def remove(subj: Iterable[Long], pred: String, obj:Iterable[Any]): Unit = {
  val iterator =(subj zip obj)
  iterator.toVector.grouped(messageSize).foreach(
    msgGroup => subscribers(pred).foreach(
    sub => sub(ChangeSet(negative = msgGroup.map( msg => Vector(msg._1,msg._2))))
    )
  )
  iterator.foreach( tup => {
    val map = multiValueAttributes.getOrElseUpdate(pred, createInnerMap())
    map.removeBinding(tup._1, tup._2)
  }
  )
  }

  def subscribe(subscriber: Map[String, (ChangeSet) => Unit]) = {
  sendAll(recipient = subscriber)
  for ( (attribute, func) <- subscriber )
    subscribers.getOrElseUpdate(attribute, mutable.MutableList()) += func
  }

  def sendAll(recipient: Map[String, (ChangeSet) => Unit]) = {
  for ( (attribute, func) <- recipient ) {
    for ((id, values) <- multiValueAttributes.getOrElseUpdate(attribute, createInnerMap);
       output <- values.grouped(messageSize) )
    func(ChangeSet(positive = output.toVector.map((v) => Vector(id, v))))
  }
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

}
