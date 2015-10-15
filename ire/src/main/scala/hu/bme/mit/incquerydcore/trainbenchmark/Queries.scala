package hu.bme.mit.incquerydcore.trainbenchmark

import java.io.FileInputStream

import akka.actor._
import akka.remote.RemoteScope
import hu.bme.mit.incquerydcore.{ChangeSet, Checker, HashAntiJoiner, HashJoiner, InequalityChecker, JenaRDFReader, KamonInitializer, Primary, Production, ReteMessage, Secondary, Terminator, Trimmer, WildcardInput, nodeType, utils}

import scala.collection.immutable.HashMap
import scala.concurrent.Await
import scala.concurrent.duration.{Duration, HOURS}

class TrainbenchmarkReader(input: WildcardInput) {

  def idFunction(v: Any) = utils.idStringToLong(v.toString)

  val valueFunctions = Map[String, (AnyRef)=>(Any)](
  "connectsTo" -> ((v: AnyRef) => utils.idStringToLong(v.toString)),
  "definedBy" -> ((v: AnyRef) => utils.idStringToLong(v.toString)),
  "follows" -> ((v: AnyRef) => utils.idStringToLong(v.toString)),
  "exit" -> ((v: AnyRef) => utils.idStringToLong(v.toString)),
  "entry" -> ((v: AnyRef) => utils.idStringToLong(v.toString)),
  "switch" -> ((v: AnyRef) => utils.idStringToLong(v.toString)),
  "sensor" -> ((v: AnyRef) => utils.idStringToLong(v.toString))
  )

  def read(path: String) {
  val fs = new FileInputStream(path)
  try {
    val tran = input.newTransaction()
    val reader = new JenaRDFReader(tran.add(_, _, _), idFunction(_), valueFunctions)
    reader.read(fs)
    input.processTransaction(tran)
  }
  finally {
    fs.close()
  }
  }
}
abstract class TrainbenchmarkQuery {
  KamonInitializer.ping
  val timeout = Duration(5, HOURS)
  val production: ActorRef
  val inputLookup: Map[String, ChangeSet => Unit]
  val system: ActorSystem
  val terminator: Terminator
  lazy val log = system.log
  def getResults(): Set[nodeType] = {
  log.info("termination started")
  val res = Await.result(terminator.send, timeout)
  log.info("termination finished")
  res
  }
  def shutdown(): Unit = Await.result(system.terminate(), timeout)
}

abstract class DistributedTrainbenchmarkQuery extends TrainbenchmarkQuery{

  val remoteActors = new collection.mutable.MutableList[ActorRef]()
  def newRemote(props: Props, address: Address): ActorRef = {
  val actor = system.actorOf(props.withDeploy(Deploy(scope = RemoteScope(address))))
  remoteActors += actor
  actor
  }
  override def shutdown: Unit = {
  remoteActors.foreach( actor => actor ! PoisonPill)
  super.shutdown()
  }
}

class PosLength extends TrainbenchmarkQuery {

  val system = ActorSystem()
  override val production: ActorRef = system.actorOf(Props(new Production("PosLength")))
  val trimmer = system.actorOf(Props(new Trimmer(production ! _, Vector(0, 1))))

  def condition(n: nodeType) = n match {
  case Vector(a, length: Int) => length <= 0
  }
  val first = system.actorOf(Props(new Checker(trimmer ! _, condition)))

  val inputNodes: List[(ReteMessage) => Unit] = List(first ! _)
  override val terminator = Terminator(inputNodes, production)

  override val inputLookup: Map[String, (ChangeSet) => Unit] = Map(
  "length" -> ((cs: ChangeSet) => first ! cs)
  )
}

class ConnectedSegments extends TrainbenchmarkQuery {
  val system = ActorSystem()
  val production = system.actorOf(Props(new Production("ConnectedSegments")))
  val sensorJoinSecond = system.actorOf(Props(new HashJoiner(production ! _, 7, Vector(5, 6), 2, Vector(0, 1))))
  val sensorJoinFirst = system.actorOf(Props(new HashJoiner(sensorJoinSecond ! Primary(_), 6, Vector(0),  2, Vector(0))))
  val join1234_5 = system.actorOf(Props(new HashJoiner(sensorJoinFirst ! Primary(_), 5, Vector(4), 2, Vector(0))))

  val join12_34 = system.actorOf(Props(new HashJoiner(join1234_5 ! Primary(_), 3, Vector(2), 3, Vector(0))))

  val join3_4 = system.actorOf(Props(new HashJoiner(join12_34 ! Secondary(_), 2, Vector(1), 2, Vector(0))))
  val join1_2 = system.actorOf(Props(new HashJoiner(join12_34 ! Primary(_), 2, Vector(1), 2, Vector(0))))
  val joinSegment1 = system.actorOf(Props(new HashJoiner(join1_2 ! Primary(_), 2, Vector(0), 1, Vector(0))))
  override val inputLookup = Map(
  "connectsTo" -> ((cs: ChangeSet) => {
    joinSegment1 ! Primary(cs)
    join1_2 ! Secondary(cs)
    join3_4 ! Primary(cs)
    join3_4 ! Secondary(cs)
    join1234_5 ! Secondary(cs)
  }),
  "sensor" -> ((cs: ChangeSet) => {
    sensorJoinFirst ! Secondary(cs)
    sensorJoinSecond ! Secondary(cs)
  }),
  "type" -> ((rawCS: ChangeSet) => {
    val cs = ChangeSet(
    rawCS.positive.filter( vec=> vec(1) == "Segment").map( vec => Vector(vec(0))),
    rawCS.negative.filter( vec=> vec(1) == "Segment").map( vec => Vector(vec(0)))
    )
    if (cs.positive.size > 0) {
    joinSegment1 ! Secondary(cs)
    } else if (cs.negative.size > 0) {
    joinSegment1 ! Secondary(cs)
    }
  })
  )
  import utils.ReteNode
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
  val system = ActorSystem()
  val production = system.actorOf(Props(new Production("SwitchSet")))
  val finalJoin = system.actorOf(Props(new HashJoiner(production ! _, 3, Vector(2), 4, Vector(0))))

  val inequality = system.actorOf(Props(new InequalityChecker(finalJoin ! Secondary(_), 2, Vector(3))))
  val switchPositionCurrentPositionJoin = system.actorOf(Props(new HashJoiner(inequality ! _, 2, Vector(1), 2, Vector(0))))
  val switchSwitchPositionJoin = system.actorOf(Props(new HashJoiner(switchPositionCurrentPositionJoin ! Primary(_), 2, Vector(0), 2, Vector(0))))

  val followsEntryJoin = system.actorOf(Props(new HashJoiner(finalJoin ! Primary(_), 2, Vector(1), 2, Vector(0))))
  val entrySemaphoreJoin = system.actorOf(Props(new HashJoiner(followsEntryJoin ! Primary(_), 2, Vector(0), 2, Vector(1))))
  val leftTrimmer = system.actorOf(Props(new Trimmer(entrySemaphoreJoin ! Primary(_), Vector(0))))
  val signalChecker = system.actorOf(Props(new Checker(leftTrimmer ! _, ((cs) => cs(1) == "SIGNAL_GO"))))


  val inputLookup = Map(
    "signal" -> ((cs: ChangeSet) => signalChecker ! cs),
    "entry" -> ((cs: ChangeSet) => entrySemaphoreJoin ! Secondary(cs)),
    "follows" -> ((cs: ChangeSet) => followsEntryJoin ! Secondary(cs)),
    "switch" -> ((cs: ChangeSet) => switchSwitchPositionJoin ! Primary(cs)),
    "position" -> ((cs: ChangeSet) => switchSwitchPositionJoin ! Secondary(cs)),
    "currentPosition" -> ((cs: ChangeSet) => switchPositionCurrentPositionJoin ! Secondary(cs))
  )
  import utils.ReteNode
  val inputNodes: List[ReteMessage => Unit] =
    List(signalChecker ! _, entrySemaphoreJoin.secondary, followsEntryJoin.secondary,
    switchSwitchPositionJoin.primary, switchSwitchPositionJoin.secondary,
    switchPositionCurrentPositionJoin.secondary
    )
  override val terminator = Terminator(inputNodes, production)


  }

  class SemaphoreNeighbor extends TrainbenchmarkQuery {
  val system = ActorSystem()
  val production = system.actorOf(Props(new Production("SemaphoreNeighbor")))
  val antijoin = system.actorOf(Props(new HashAntiJoiner(production ! _, Vector(2, 5), Vector(1, 2))),"a")
  val inequality = system.actorOf(Props(new InequalityChecker(antijoin ! Primary(_), 0, Vector(6))),"b")
  val finalJoin = system.actorOf(Props(new HashJoiner(inequality ! _, 6, Vector(5), 2, Vector(1))),"c")
  val secondToLastJoin = system.actorOf(Props(new HashJoiner(finalJoin ! Primary(_), 3, Vector(1), 4, Vector(1))),"d")
  val rightMostJoin = system.actorOf(Props(new HashJoiner(secondToLastJoin ! Secondary(_), 3, Vector(2), 2, Vector(0))),"e")
  val sensorConnects = system.actorOf(Props(new HashJoiner(rightMostJoin ! Primary(_), 2, Vector(0), 2, Vector(0))),"f")
  val exitDefined = system.actorOf(Props(new HashJoiner(secondToLastJoin ! Primary(_), 2, Vector(0), 2, Vector(0))),"g")
  val entryDefined = system.actorOf(Props(new HashJoiner(antijoin ! Secondary(_), 2, Vector(0), 2, Vector(0))),"h")

  val inputLookup = Map(
    "entry" -> ((cs: ChangeSet) => entryDefined ! Primary(cs)),
    "definedBy" -> ((cs: ChangeSet) => {
    entryDefined ! Secondary(cs)
    finalJoin ! Secondary(cs)
    exitDefined ! Primary(cs)
    }),
    "exit" -> ((cs: ChangeSet) => exitDefined ! Secondary(cs)),
    "sensor" -> ((cs: ChangeSet) => {
    sensorConnects ! Primary(cs)
    rightMostJoin ! Secondary(cs)
    }),
    "connectsTo" -> ((cs: ChangeSet) => sensorConnects ! Secondary(cs))
  )
  import utils.ReteNode
  val inputNodes: List[ReteMessage => Unit] =
    List(
    entryDefined.primary, entryDefined.secondary,
    exitDefined.primary, exitDefined.secondary,
    sensorConnects.primary, sensorConnects.secondary,
    rightMostJoin.secondary, finalJoin.secondary)
  override val terminator = Terminator(inputNodes, production)

  }

  class RouteSensor extends TrainbenchmarkQuery{
  val system = ActorSystem()
  val production = system.actorOf(Props(new Production("RouteSensor")))
  val antijoin = system.actorOf(Props(new HashAntiJoiner(production ! _, Vector(2, 3), Vector(0, 1))))
  val sensorJoin = system.actorOf(Props(new HashJoiner(antijoin ! Primary(_), 3, Vector(1), 2, Vector(0))))
  val followsJoin = system.actorOf(Props(new HashJoiner(sensorJoin ! Primary(_), 2, Vector(0), 2, Vector(1))))
  val inputLookup = HashMap(
    "switch" -> ((cs: ChangeSet) => followsJoin ! Primary(cs)),
    "follows" -> ((cs:ChangeSet) => followsJoin ! Secondary(cs)),
    "sensor" -> ((cs: ChangeSet) => sensorJoin ! Secondary(cs)),
    "definedBy" -> ((cs: ChangeSet) => antijoin ! Secondary(cs))
  )
  import utils.ReteNode
  val inputNodes: List[ReteMessage => Unit] =
    List(
    antijoin.secondary, sensorJoin.secondary,
    followsJoin.primary, followsJoin.secondary
    )
  override val terminator = Terminator(inputNodes, production)
  }
  class SwitchSensor extends TrainbenchmarkQuery {
  val system = ActorSystem()
  val production = system.actorOf(Props(new Production("SwitchSensor")), "sw-production")
  val antijoin = system.actorOf(Props(new HashAntiJoiner(production ! _, Vector(0), Vector(0))), "sw-antijoin")

  val trimmer = system.actorOf(Props(new Trimmer(antijoin ! Secondary(_), Vector(0))), "sw-trimmer")
  val inputLookup = Map(
    "sensor" -> ((cs: ChangeSet) => trimmer ! cs),
    "type" -> ((rawCS: ChangeSet) => {
    val cs = ChangeSet(
      rawCS.positive.filter( vec=> vec(1) == "Switch").map( vec => Vector(vec(0))),
      rawCS.negative.filter( vec=> vec(1) == "Switch").map( vec => Vector(vec(0)))
    )
    if (cs.positive.size > 0) {
      antijoin ! Primary(cs)
    } else if (cs.negative.size > 0) {
      antijoin ! Primary(cs)
    }
    })
  )

  import utils.ReteNode
  val inputNodes: List[ReteMessage => Unit] = List(antijoin.primary, trimmer ! _)
  override val terminator = Terminator(inputNodes, production)
  }
