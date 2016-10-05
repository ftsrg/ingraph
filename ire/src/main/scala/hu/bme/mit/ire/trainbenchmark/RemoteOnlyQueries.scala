package hu.bme.mit.ire.trainbenchmark

import java.io.{IOException, ObjectInputStream, ObjectOutputStream}

import akka.actor._
import akka.remote.RemoteScope
import com.typesafe.config.ConfigFactory
import hu.bme.mit.ire._
import hu.bme.mit.ire.utils.conversions._

import scala.collection.immutable.HashMap

/**
 * Created by wafle on 10/11/2015.
 */
object RemoteOnlyTrainbenchmarkQuery {
  val system = ActorSystem("Master", ConfigFactory.load("master"))
}

abstract class RemoteOnlyTrainbenchmarkQuery extends TrainbenchmarkQuery with Serializable{
  override val system = RemoteOnlyTrainbenchmarkQuery.system
  val remote1Adress = Address("akka.tcp", "Slave1System", "127.0.0.1", 2552)
  def newRemote1(props: Props, name: String): ActorRef = {
    val actor = system.actorOf(props.withDeploy(Deploy(scope = RemoteScope(remote1Adress))), name + System.currentTimeMillis().toString)
    actors += actor
    actor
  }

  override def newLocal(props: Props, name: String):ActorRef = {
    val actor = system.actorOf(props.withDeploy(Deploy(scope = LocalScope)), name + System.currentTimeMillis().toString)
    actors += actor
    actor
  }

  @throws(classOf[IOException])
  private def writeObject(out: ObjectOutputStream): Unit = {}

  @throws(classOf[IOException])
  private def readObject(in: ObjectInputStream): Unit = {}

}
