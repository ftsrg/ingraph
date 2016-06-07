package hu.bme.mit.incqueryds

import akka.actor.Actor

import scala.collection.mutable
import scala.concurrent.Promise

/**
  * Created by wafle on 27/12/15.
  */
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
        results.add
      }
      n.foreach {
        results.remove
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
