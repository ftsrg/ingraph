package hu.bme.mit.ire.nodes.unary

import akka.actor.Actor
import hu.bme.mit.ire.datatypes.TupleType
import hu.bme.mit.ire.listeners.{AddListener, ChangeListener}
import hu.bme.mit.ire.messages._

import scala.collection.mutable
import scala.concurrent.Promise

class ProductionNode(queryName: String, val expectedTerminatorCount: Int = 1) extends Actor {
  val log = context.system.log

  val receivedTerminatorCount = mutable.Map.empty[Int, Int]
  val results = new mutable.HashSet[TupleType]
  val terminatorPromises = mutable.Map.empty[Int, Promise[Set[TupleType]]]
  val inputsToResume = mutable.Map.empty[Int, Iterable[ReteMessage => Unit]]
  val listeners = new mutable.ListBuffer[ChangeListener]
  var t0 = System.nanoTime()

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
