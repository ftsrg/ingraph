package hu.bme.mit.ire.trainbenchmark

import akka.actor._
import hu.bme.mit.ire._
import hu.bme.mit.ire.datatypes.TupleType
import hu.bme.mit.ire.listeners.{AddListener, ChangeListener}
import hu.bme.mit.ire.messages.ChangeSet

import scala.concurrent.Await
import scala.concurrent.duration._

object TrainbenchmarkQuery {
  val system = ActorSystem()

}

abstract class TrainbenchmarkQuery {
  lazy val log = system.log
  val system = TrainbenchmarkQuery.system
  val timeout = Duration(5, HOURS)
  val production: ActorRef
  val inputLookup: Map[String, ChangeSet => Unit]
  val terminator: Terminator
  val actors = new collection.mutable.MutableList[ActorRef]()

  def getResults(): Set[TupleType] = {
    log.info("termination started")
    val res = Await.result(terminator.send(), timeout)
    log.info("termination finished")
    res
  }

  def newLocal(props: Props): ActorRef = {
    val actor = system.actorOf(props)
    actors += actor
    actor
  }

  def newLocal(props: Props, name: String): ActorRef = {
    val actor = system.actorOf(props, name + System.currentTimeMillis().toString)
    actors += actor
    actor
  }

  def shutdown(): Unit = {
    actors.foreach(actor => actor ! PoisonPill)
  }

  def addListener(listener: ChangeListener) = {
    production ! AddListener(listener)
  }
}
