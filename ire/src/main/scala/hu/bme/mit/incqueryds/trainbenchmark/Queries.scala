package hu.bme.mit.incqueryds.trainbenchmark

import java.io.FileInputStream

import akka.actor._
import akka.remote.RemoteScope
import hu.bme.mit.incqueryds._
import hu.bme.mit.incqueryds.utils.conversions._

import scala.collection.immutable.HashMap
import scala.concurrent.Await
import scala.concurrent.duration._

object TrainbenchmarkQuery {
  val system = ActorSystem()

}

abstract class TrainbenchmarkQuery {
  val system = TrainbenchmarkQuery.system
  val timeout = Duration(5, HOURS)
  val production: ActorRef
  val inputLookup: Map[String, ChangeSet => Unit]
  val terminator: Terminator
  val actors = new collection.mutable.MutableList[ActorRef]()
  lazy val log = system.log
  def getResults(): Set[nodeType] = {
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
    actors.foreach( actor => actor ! PoisonPill)
  }

}

abstract class DistributedTrainbenchmarkQuery extends TrainbenchmarkQuery {
  val remoteActors = new collection.mutable.MutableList[ActorRef]()
  def newRemote(props: Props, address: Address): ActorRef = {
    val actor = newLocal(props.withDeploy(Deploy(scope = RemoteScope(address))))
    remoteActors += actor
    actor
  }
  override def shutdown: Unit = {
    remoteActors.foreach( actor => actor ! PoisonPill)
    super.shutdown()
  }
}
