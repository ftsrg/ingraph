package hu.bme.mit.incqueryds.trainbenchmark

import java.io.{IOException, ObjectInputStream, ObjectOutputStream}

import akka.actor._
import akka.remote.RemoteScope
import hu.bme.mit.incqueryds._
import hu.bme.mit.incqueryds.utils.conversions._

import scala.collection.immutable.HashMap

/**
 * Created by wafle on 10/20/2015.
 */
abstract class DistributedQuery extends RemoteOnlyTrainbenchmarkQuery with Serializable {
  override val system = RemoteOnlyTrainbenchmarkQuery.system
  val remote2Adress = Address("akka.tcp", "Slave2System", System.getenv("SLAVE2IP"), 2552)
  val remote3Adress = Address("akka.tcp", "Slave3System", System.getenv("SLAVE3IP"), 2552)

  def newRemote2(props: Props, name: String): ActorRef = {
    val actor = system.actorOf(props.withDeploy(Deploy(scope = RemoteScope(remote2Adress))), name + System.currentTimeMillis().toString)
    actors += actor
    actor
  }

  def newRemote3(props: Props, name: String): ActorRef = {
    val actor = system.actorOf(props.withDeploy(Deploy(scope = RemoteScope(remote3Adress))), name + System.currentTimeMillis().toString)
    actors += actor
    actor
  }

  @throws(classOf[IOException])
  private def writeObject(out: ObjectOutputStream): Unit = {}

  @throws(classOf[IOException])
  private def readObject(in: ObjectInputStream): Unit = {}

}
