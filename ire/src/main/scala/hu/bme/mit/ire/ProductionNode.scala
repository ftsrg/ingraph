package hu.bme.mit.ire

import akka.actor.Actor

import scala.collection.mutable
import scala.concurrent.Promise

/**
  * Created by wafle on 27/12/15.
  */
class ProductionNode(queryName: String, val expectedTerminatorCount: Int = 1) extends Actor {
  val log = context.system.log

  val receivedTerminatorCount = mutable.Map.empty[Int, Int]

  var t0 = System.nanoTime()

  val results = new mutable.HashSet[TupleType]
  val terminatorPromises = mutable.Map.empty[Int, Promise[Set[TupleType]]]
  val inputsToResume = mutable.Map.empty[Int, Iterable[ReteMessage => Unit]]

  val listeners = new mutable.ListBuffer[ChangeListener]

  def getAndResetElapsedTime(): Long = {
    val t1 = System.nanoTime()
    val retVal = t1 - t0
    t0 = t1
    retVal
  }

  override def receive: Actor.Receive = {
    case ChangeSet(p, n) =>
      p.foreach {
        t =>
          results.add(t)
          listeners.foreach(_.added(t))
      }
      n.foreach {
        t =>
          results.remove(t)
          listeners.foreach(_.removed(t))
      }

    case ExpectMoreTerminators(terminatorID, inputs) => inputsToResume(terminatorID) = inputs

    case TerminatorMessage(terminatorID, messageID, route) =>
      log.info(s"terminator$messageID received")
      receivedTerminatorCount(messageID) += 1
      if (receivedTerminatorCount(messageID) == expectedTerminatorCount) {
        inputsToResume(terminatorID).foreach(input => input(Resume(messageID)))
        listeners.foreach(_.terminated())
        terminatorPromises(messageID).success(results.toSet)
        receivedTerminatorCount.drop(messageID)
        terminatorPromises.drop(messageID)
      }

    case ExpectTerminator(terminatorID, messageID, promise) =>
      receivedTerminatorCount(messageID) = 0
      terminatorPromises(messageID) = promise
    case AddListener(listener) =>
      listeners += listener
  }
}

case class AddListener(listener: ChangeListener)

abstract class ChangeListener {
  val positive = new mutable.ListBuffer[TupleType]
  val negative = new mutable.ListBuffer[TupleType]
  def added(tuple: TupleType) = positive += tuple
  def removed(tuple: TupleType) = negative += tuple
  def terminated() = {
    listener(positive.toList, negative.toList)
    positive.clear()
    negative.clear()
  }

  def listener(positive: List[TupleType], negative: List[TupleType])
}
