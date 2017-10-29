package hu.bme.mit.ire.engine

import akka.actor._
import akka.pattern
import akka.util.Timeout
import hu.bme.mit.ire._
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.listeners.{AddListener, ChangeListener}
import hu.bme.mit.ire.messages.{IncrementalChangeSet, SizeRequest}

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}

object RelationalEngine {
  val system = ActorSystem()

}

abstract class RelationalEngine {
  lazy val log = system.log
  val system = RelationalEngine.system
  val timeout = Duration(5, HOURS)
  val production: ActorRef
  val inputLookup: Map[String, IncrementalChangeSet => Unit]
  val terminator: Terminator
  val actors = new collection.mutable.MutableList[ActorRef]()


  def getCounts: Long = {
    implicit val akkaTimeout = Timeout(1 minute)
    import scala.concurrent.ExecutionContext.Implicits.global
    val x = Future.sequence(actors.map(pattern.ask(_, SizeRequest()).mapTo[Long])).map(_.sum)
    Await.result(x, timeout)
  }

  def getResults(): Iterable[Tuple] = {
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
    val actor = system.actorOf(props, name + System.currentTimeMillis())
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
