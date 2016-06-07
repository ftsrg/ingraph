package hu.bme.mit.incqueryds.trainbenchmark

import java.io.FileInputStream

import akka.actor._
import akka.remote.RemoteScope
import hu.bme.mit.incqueryds._
import org.apache.log4j.Logger

import scala.collection.immutable.HashMap
import scala.concurrent.Await
import scala.concurrent.duration._
import utils.conversions._

class TrainbenchmarkReader(input: WildcardInput) {
  val log = Logger.getRootLogger
  def idFunction(v: Any) = utils.idStringToLong(v.toString)

  val valueFunctions = Map[String, (Any)=>(Any)](
    "connectsTo" -> ((v: Any) => utils.idStringToLong(v.toString)),
    "gathers" -> ((v: Any) => utils.idStringToLong(v.toString)),
    "follows" -> ((v: Any) => utils.idStringToLong(v.toString)),
    "exit" -> ((v: Any) => utils.idStringToLong(v.toString)),
    "entry" -> ((v: Any) => utils.idStringToLong(v.toString)),
    "target" -> ((v: Any) => utils.idStringToLong(v.toString)),
    "monitoredBy" -> ((v: Any) => utils.idStringToLong(v.toString))
  )

  def read(path: String) {
    val fs = new FileInputStream(path)
    try {
      val tran = input.newBatchTransaction()
      val reader = new WorkingTBRDFReader(tran.add, idFunction(_), valueFunctions)
      log.info("read started")
      reader.read(scala.io.Source.fromFile(path))
      log.info("read finished")
      tran.close()
      log.info("read transaction processed")
    }
    finally {
      fs.close()
    }
  }
}
object TrainbenchmarkQuery {
  val system = ActorSystem()

  def generateTypeHandler(types: Map[String, (ChangeSet => Unit)]): ChangeSet => Unit = {
    cs: ChangeSet => {
      cs.positive.groupBy(v => v(1)).foreach(
        kv => kv._1 match {
          case t: String => if (types.contains(t)) types(t)(ChangeSet(positive = kv._2.map(vec => Vector(vec(0)))))
        }
      )
      cs.negative.groupBy(v => v(1)).foreach(
        kv => kv._1 match {
          case t: String => if (types.contains(t)) types(t)(ChangeSet(negative = kv._2.map(vec => Vector(vec(0)))))
        }
      )
    }
  }
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

class PosLength extends TrainbenchmarkQuery {
  override val production: ActorRef = newLocal(Props(new Production("PosLength")))
  val trimmer = newLocal(Props(new Trimmer(production ! _, Vector(0, 1))))

  def condition(n: nodeType) = n match {
    case Vector(a, length: Int) => length <= 0
  }
  val first = newLocal(Props(new Checker(trimmer ! _, condition)))

  val inputNodes: List[(ReteMessage) => Unit] = List(first ! _)
  override val terminator = Terminator(inputNodes, production)

  override val inputLookup: Map[String, (ChangeSet) => Unit] = Map(
    "length" -> ((cs: ChangeSet) => first ! cs)
  )
}

class ConnectedSegments extends TrainbenchmarkQuery {
  val production = newLocal(Props(new Production("ConnectedSegments")))
  val sensorJoinSecond = newLocal(Props(new HashJoiner(production ! _, 7, Vector(5, 6), 2, Vector(0, 1))))
  val sensorJoinFirst = newLocal(Props(new HashJoiner(sensorJoinSecond ! Primary(_), 6, Vector(0),  2, Vector(0))))
  val join1234_5 = newLocal(Props(new HashJoiner(sensorJoinFirst ! Primary(_), 5, Vector(4), 2, Vector(0))))
  val join12_34 = newLocal(Props(new HashJoiner(join1234_5 ! Primary(_), 3, Vector(2), 3, Vector(0))))

  val join3_4 = newLocal(Props(new HashJoiner(join12_34 ! Secondary(_), 2, Vector(1), 2, Vector(0))))
  val join1_2 = newLocal(Props(new HashJoiner(join12_34 ! Primary(_), 2, Vector(1), 2, Vector(0))))
  val joinSegment1 = newLocal(Props(new HashJoiner(join1_2 ! Primary(_), 2, Vector(0), 1, Vector(0))))
  override val inputLookup = Map(
    "connectsTo" -> ((cs: ChangeSet) => {
      joinSegment1 ! Primary(cs)
      join1_2 ! Secondary(cs)
      join3_4 ! Primary(cs)
      join3_4 ! Secondary(cs)
      join1234_5 ! Secondary(cs)
    }),
    "monitoredBy" -> ((cs: ChangeSet) => {
      sensorJoinFirst ! Secondary(cs)
      sensorJoinSecond ! Secondary(cs)
    }),
    "type" -> ((rawCS: ChangeSet) => {
      val cs = ChangeSet(
        rawCS.positive.filter( vec=> vec(1) == "Segment").map( vec => Vector(vec(0))),
        rawCS.negative.filter( vec=> vec(1) == "Segment").map( vec => Vector(vec(0)))
      )
      if (cs.positive.nonEmpty) {
        joinSegment1 ! Secondary(cs)
      } else if (cs.negative.nonEmpty) {
        joinSegment1 ! Secondary(cs)
      }
    })
  )

  val inputNodes: List[ReteMessage => Unit] =
    List(
      joinSegment1.primary, joinSegment1.secondary,
      join1_2.secondary,
      join3_4.primary, join3_4.secondary,
      join1234_5.secondary,
      sensorJoinFirst.secondary,
      sensorJoinSecond.secondary
    )
  override val terminator = Terminator(inputNodes, production)
}
  class SwitchSet extends TrainbenchmarkQuery {
    val production = newLocal(Props(new Production("SwitchSet")))
    val finalJoin = newLocal(Props(new HashJoiner(production ! _, 3, Vector(2), 4, Vector(0))))

    val inequality = newLocal(Props(new Inequality(finalJoin ! Secondary(_), 2, Vector(3))))
    val switchPositionCurrentPositionJoin = newLocal(Props(new HashJoiner(inequality ! _, 2, Vector(1), 2, Vector(0))))
    val switchSwitchPositionJoin = newLocal(Props(new HashJoiner(switchPositionCurrentPositionJoin ! Primary(_), 2, Vector(0), 2, Vector(0))))

    val followsEntryJoin = newLocal(Props(new HashJoiner(finalJoin ! Primary(_), 2, Vector(1), 2, Vector(0))))
    val entrySemaphoreJoin = newLocal(Props(new HashJoiner(followsEntryJoin ! Primary(_), 2, Vector(0), 2, Vector(1))))
    val leftTrimmer = newLocal(Props(new Trimmer(entrySemaphoreJoin ! Primary(_), Vector(0))))
    val signalChecker = newLocal(Props(new Checker(leftTrimmer ! _, (cs) => cs(1) == "SIGNAL_GO")))


    val inputLookup = Map(
      "signal" -> ((cs: ChangeSet) => signalChecker ! cs),
      "entry" -> ((cs: ChangeSet) => entrySemaphoreJoin ! Secondary(cs)),
      "follows" -> ((cs: ChangeSet) => followsEntryJoin ! Secondary(cs)),
      "target" -> ((cs: ChangeSet) => switchSwitchPositionJoin ! Primary(cs)),
      "position" -> ((cs: ChangeSet) => switchSwitchPositionJoin ! Secondary(cs)),
      "currentPosition" -> ((cs: ChangeSet) => switchPositionCurrentPositionJoin ! Secondary(cs))
    )

    val inputNodes: List[ReteMessage => Unit] =
      List(signalChecker ! _, entrySemaphoreJoin.secondary, followsEntryJoin.secondary,
        switchSwitchPositionJoin.primary, switchSwitchPositionJoin.secondary,
        switchPositionCurrentPositionJoin.secondary
      )
    override val terminator = Terminator(inputNodes, production)


  }

  class SemaphoreNeighbor extends TrainbenchmarkQuery {
    val production = newLocal(Props(new Production("SemaphoreNeighbor")))
    val antijoin = newLocal(Props(new HashAntijoiner(production ! _, Vector(2, 5), Vector(1, 2))),"a")
    val inequality = newLocal(Props(new Inequality(antijoin ! Primary(_), 0, Vector(6))),"b")
    val finalJoin = newLocal(Props(new HashJoiner(inequality ! _, 6, Vector(5), 2, Vector(1))),"c")
    val secondToLastJoin = newLocal(Props(new HashJoiner(finalJoin ! Primary(_), 3, Vector(1), 4, Vector(1))),"d")
    val rightMostJoin = newLocal(Props(new HashJoiner(secondToLastJoin ! Secondary(_), 3, Vector(2), 2, Vector(0))),"e")
    val sensorConnects = newLocal(Props(new HashJoiner(rightMostJoin ! Primary(_), 2, Vector(0), 2, Vector(0))),"f")
    val exitDefined = newLocal(Props(new HashJoiner(secondToLastJoin ! Primary(_), 2, Vector(0), 2, Vector(0))),"g")
    val entryDefined = newLocal(Props(new HashJoiner(antijoin ! Secondary(_), 2, Vector(0), 2, Vector(0))),"h")

    val inputLookup = Map(
      "entry" -> ((cs: ChangeSet) => entryDefined ! Primary(cs)),
      "gathers" -> ((cs: ChangeSet) => {
        entryDefined ! Secondary(cs)
        finalJoin ! Secondary(cs)
        exitDefined ! Primary(cs)
      }),
      "exit" -> ((cs: ChangeSet) => exitDefined ! Secondary(cs)),
      "monitoredBy" -> ((cs: ChangeSet) => {
        sensorConnects ! Primary(cs)
        rightMostJoin ! Secondary(cs)
      }),
      "connectsTo" -> ((cs: ChangeSet) => sensorConnects ! Secondary(cs))
    )

    val inputNodes: List[ReteMessage => Unit] =
      List(
        entryDefined.primary, entryDefined.secondary,
        exitDefined.primary, exitDefined.secondary,
        sensorConnects.primary, sensorConnects.secondary,
        rightMostJoin.secondary, finalJoin.secondary)
    override val terminator = Terminator(inputNodes, production)

  }

  class RouteSensor extends TrainbenchmarkQuery{
    val production = newLocal(Props(new Production("RouteSensor")))
    val antijoin = newLocal(Props(new HashAntijoiner(production ! _, Vector(2, 3), Vector(0, 1))))
    val sensorJoin = newLocal(Props(new HashJoiner(antijoin ! Primary(_), 3, Vector(1), 2, Vector(0))))
    val followsJoin = newLocal(Props(new HashJoiner(sensorJoin ! Primary(_), 2, Vector(0), 2, Vector(1))))
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
  class SwitchSensor extends TrainbenchmarkQuery {
    val production = newLocal(Props(new Production("SwitchSensor")), "sw-production")
    val antijoin = newLocal(Props(new HashAntijoiner(production ! _, Vector(0), Vector(0))), "sw-antijoin")

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
