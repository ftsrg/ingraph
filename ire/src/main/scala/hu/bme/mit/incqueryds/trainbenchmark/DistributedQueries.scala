package hu.bme.mit.incqueryds.trainbenchmark

import java.io.{ObjectInputStream, ObjectOutputStream, IOException}

import akka.actor._
import akka.remote.RemoteScope
import hu.bme.mit.incqueryds._

import scala.collection.immutable.HashMap
import utils.conversions._

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

class DistributedRouteSensor extends  DistributedQuery {
  val production = newLocal(Props(new Production("RouteSensor")))
  val antijoin = newRemote3(Props(new HashAntijoiner(production ! _, Vector(2, 3), Vector(0, 1))), "RouteSensor-antijoin")
  val sensorJoin = newRemote2(Props(new HashJoiner(antijoin ! Primary(_), 3, Vector(1), 2, Vector(0))), "RouteSensor-sensor=join")
  val followsJoin = newRemote1(Props(new HashJoiner(sensorJoin ! Primary(_), 2, Vector(0), 2, Vector(1))), "RouteSensor-follows-join")
  val inputLookup = HashMap(
    "target" -> ((cs: ChangeSet) => followsJoin ! Primary(cs)),
    "follows" -> ((cs:ChangeSet) => followsJoin ! Secondary(cs)),
    "monitoredBy" -> ((cs: ChangeSet) => sensorJoin ! Secondary(cs)),
    "gathers" -> ((cs: ChangeSet) => antijoin ! Secondary(cs))
  )

  val inputNodes: List[ReteMessage => Unit] =
    List(
      antijoin.secondary, sensorJoin.secondary,
      followsJoin.primary, followsJoin.secondary
    )
  override val terminator = Terminator(inputNodes, production)
}

class DistributedSplitRouteSensor extends  DistributedQuery {
  val production = newLocal(Props(new Production("RouteSensor")))
  val antijoin = newRemote1(Props(new HashAntijoiner(production ! _, Vector(2, 3), Vector(0, 1), expectedTerminatorCount = 4)), "RouteSensor-antijoin")
  val sensorJoinA = newRemote2(Props(new HashJoiner(antijoin.primary, 3, Vector(1), 2, Vector(0))), "RouteSensor-sensor=join-A")
  val sensorJoinB = newRemote3(Props(new HashJoiner(antijoin.primary, 3, Vector(1), 2, Vector(0))), "RouteSensor-sensor=join-B")
  val sensorJoinC = newLocal(Props(new HashJoiner(antijoin.primary, 3, Vector(1), 2, Vector(0))), "RouteSensor-sensor=join-C")

  val followsJoin = newRemote1(Props(
    new ParallelHashJoiner(
      Vector(sensorJoinA.primary, sensorJoinB.primary, sensorJoinC.primary)
      , 2, Vector(0), 2, Vector(1),
      hashFunction = n => n(1).hashCode()
    )), "RouteSensor-follows-join")

  val inputLookup = HashMap(
    "target" -> ((cs: ChangeSet) => followsJoin ! Primary(cs)),
    "follows" -> ((cs:ChangeSet) => followsJoin ! Secondary(cs)),
    "monitoredBy" -> ((cs: ChangeSet) =>
      {
        val children = Array(sensorJoinA, sensorJoinB, sensorJoinC)
        cs.positive.groupBy( tup => Math.abs(tup.hashCode()) % 2).foreach(kv => if (kv._2.nonEmpty) children(kv._1) ! Secondary(ChangeSet(positive = kv._2)))
        cs.negative.groupBy( tup => Math.abs(tup.hashCode()) % 2).foreach(kv => if (kv._2.nonEmpty) children(kv._1) ! Secondary(ChangeSet(positive = kv._2)))
      }),
    "gathers" -> ((cs: ChangeSet) => antijoin ! Secondary(cs))
  )

  val inputNodes: List[ReteMessage => Unit] =
    List(
      antijoin.secondary,
      sensorJoinA.secondary, sensorJoinB.secondary, sensorJoinC.secondary,
      followsJoin.primary, followsJoin.secondary
    )
  override val terminator = Terminator(inputNodes, production)
}

class DistributedSwitchSensor extends DistributedQuery {
  val production = newLocal(Props(new Production("SwitchSensor")), "sw-production")
  val antijoin = newRemote2(Props(new HashAntijoiner(production ! _, Vector(0), Vector(0))), "sw-antijoin")

  val trimmer = newLocal(Props(new Trimmer(antijoin ! Secondary(_), Vector(0))), "sw-trimmer")
  val inputLookup = Map(
    "monitoredBy" -> ((cs: ChangeSet) => trimmer ! cs),
    "type" -> ((rawCS: ChangeSet) => {
      val cs = ChangeSet(
        rawCS.positive.filter( vec=> vec(1) == "Switch").map( vec => Vector(vec(0))),
        rawCS.negative.filter( vec=> vec(1) == "Switch").map( vec => Vector(vec(0)))
      )
      if (cs.positive.nonEmpty) {
        antijoin ! Primary(cs)
      } else if (cs.negative.nonEmpty) {
        antijoin ! Primary(cs)
      }
    })
  )

  val inputNodes: List[ReteMessage => Unit] = List(antijoin.primary, trimmer ! _)
  override val terminator = Terminator(inputNodes, production)
}

class DistributedSplitSwitchSensor extends DistributedQuery {
  val production = newLocal(Props(new Production("SwitchSensor", expectedTerminatorCount = 4)), "sw-production")
  val antijoinA = newRemote1(Props(new HashAntijoiner(production ! _, Vector(0), Vector(0))), "sw-antijoin-A")
  val antijoinB = newRemote2(Props(new HashAntijoiner(production ! _, Vector(0), Vector(0))), "sw-antijoin-B")
  val antijoinC = newRemote3(Props(new HashAntijoiner(production ! _, Vector(0), Vector(0))), "sw-antijoin-C")
  val antijoinD = newLocal(Props(new HashAntijoiner(production ! _, Vector(0), Vector(0))), "sw-antijoin-D")

  val secondaryChildren: Vector[ReteMessage => Unit] = Vector(antijoinA.secondary, antijoinB.secondary, antijoinC.secondary, antijoinD.secondary)
  val primaryChildren: Vector[ReteMessage => Unit] = Vector(antijoinA.primary, antijoinB.primary, antijoinC.primary, antijoinD.primary)

  val trimmer = newLocal(Props(new ParallelTrimmer(secondaryChildren, Vector(0))), "sw-trimmer")

  val inputLookup = Map(
    "monitoredBy" -> ((cs: ChangeSet) => trimmer ! cs),
    "type" -> ((rawCS: ChangeSet) => {
      val cs = ChangeSet(
        rawCS.positive.filter( vec=> vec(1) == "Switch").map( vec => Vector(vec(0))),
        rawCS.negative.filter( vec=> vec(1) == "Switch").map( vec => Vector(vec(0)))
      )
      if (cs.positive.nonEmpty) {
        cs.positive.groupBy( tup => Math.abs(tup.hashCode()) % primaryChildren.size).foreach(kv => if (kv._2.nonEmpty) primaryChildren(kv._1)(ChangeSet(positive = kv._2)))
      }
      if (cs.negative.nonEmpty) {
        cs.negative.groupBy( tup => Math.abs(tup.hashCode()) % primaryChildren.size).foreach(kv => if (kv._2.nonEmpty) primaryChildren(kv._1)(ChangeSet(negative = kv._2)))
      }
    })
  )

  val inputNodes: List[ReteMessage => Unit] = List(antijoinA.primary, antijoinB.primary, antijoinC.primary, antijoinD.primary, trimmer ! _)
  override val terminator = Terminator(inputNodes, production)
}
