package hu.bme.mit.incquerydcore.trainbenchmark

import java.io.FileInputStream

import scala.Vector
import scala.collection.immutable.HashMap
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.duration.HOURS

import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor.actorRef2Scala
import hu.bme.mit.incquerydcore.ChangeSet
import hu.bme.mit.incquerydcore.Checker
import hu.bme.mit.incquerydcore.HashAntiJoiner
import hu.bme.mit.incquerydcore.HashJoiner
import hu.bme.mit.incquerydcore.InequalityChecker
import hu.bme.mit.incquerydcore.JenaRDFReader
import hu.bme.mit.incquerydcore.Primary
import hu.bme.mit.incquerydcore.Production
import hu.bme.mit.incquerydcore.ReteMessage
import hu.bme.mit.incquerydcore.Secondary
import hu.bme.mit.incquerydcore.Terminator
import hu.bme.mit.incquerydcore.Trimmer
import hu.bme.mit.incquerydcore.WildcardInput
import hu.bme.mit.incquerydcore.nodeType
import hu.bme.mit.incquerydcore.utils

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
  val timeout = Duration(5, HOURS)
  val production: ActorRef
  val inputLookup: Map[String, ChangeSet => Unit]

  val terminator: Terminator

  def getResults(): Set[nodeType] = {
    Await.result(terminator.send, timeout)
  }
}
class PosLength extends TrainbenchmarkQuery {
  println("poslength")
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
  println("connected-segments")
  val system = ActorSystem()
  val production = system.actorOf(Props(new Production("ConnectedSegments")))

  val inequality6_4321 = system.actorOf(Props(new InequalityChecker(production ! _, 6, Vector(5, 4, 3, 2, 1, 0))))
  val inequality5_4321 = system.actorOf(Props(new InequalityChecker(inequality6_4321 ! _, 5, Vector(6, 4, 3, 2, 1, 0))))
  val join1234_56 = system.actorOf(Props(new HashJoiner(inequality5_4321 ! _, 5, Vector(0), 2, Vector(0))))

  val inequality2_34 = system.actorOf(Props(new InequalityChecker(join1234_56 ! Primary(_), 2, Vector(0, 1, 3, 4))))
  val inequality1_34 = system.actorOf(Props(new InequalityChecker(inequality2_34 ! _, 1, Vector(0, 23, 4))))
  val join12_34 = system.actorOf(Props(new HashJoiner(inequality1_34 ! _, 3, Vector(0), 3, Vector(0))))

  val inequality5_6 = system.actorOf(Props(new InequalityChecker(join1234_56 ! Secondary(_), 1, Vector(2, 0))))
  val join5_6 = system.actorOf(Props(new HashJoiner(inequality5_6 ! _, 2, Vector(0), 2, Vector(0))))

  val inequality3_4 = system.actorOf(Props(new InequalityChecker(join12_34 ! Secondary(_), 1, Vector(2, 0))))
  val join3_4 = system.actorOf(Props(new HashJoiner(inequality3_4 ! _, 2, Vector(0), 2, Vector(0))))

  val inequality1_2 = system.actorOf(Props(new InequalityChecker(join12_34 ! Primary(_), 2, Vector(1, 0))))
  val join1_2 = system.actorOf(Props(new HashJoiner(inequality1_2 ! _, 2, Vector(0), 2, Vector(0))))

  override val inputLookup = Map(
    "connectsTo" -> ((cs: ChangeSet) => {
      join1_2 ! Primary(cs)
      join1_2 ! Secondary(cs)
      join3_4 ! Primary(cs)
      join3_4 ! Secondary(cs)
      join5_6 ! Primary(cs)
      join5_6 ! Secondary(cs)

    })
  )
  val inputNodes: List[ReteMessage => Unit] =
    List(join1_2 ! _, join3_4 ! _, join5_6 ! _)
  override val terminator = Terminator(inputNodes, production)
}
  class SwitchSet extends TrainbenchmarkQuery {
    println("switch-set")
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
    val inputNodes: List[ReteMessage => Unit] =
      List(signalChecker ! _, entrySemaphoreJoin ! _, followsEntryJoin ! _, switchSwitchPositionJoin ! _, switchPositionCurrentPositionJoin ! _)
    override val terminator = Terminator(inputNodes, production)


  }

  class SemaphoreNeighbor extends TrainbenchmarkQuery {
    println("semaphore-neighbor")
    val system = ActorSystem()
    val production = system.actorOf(Props(new Production("SemaphoreNeighbor")))
    val antijoin = system.actorOf(Props(new HashAntiJoiner(production ! _, Vector(2, 5), Vector(1, 2))),"a")
    val inequality = system.actorOf(Props(new InequalityChecker(antijoin ! Primary(_), 0, Vector(6))),"b")
    val finalJoin = system.actorOf(Props(new HashJoiner(inequality ! _, 6, Vector(4), 2, Vector(1))),"c")
    val secondToLastJoin = system.actorOf(Props(new HashJoiner(finalJoin ! Primary(_), 3, Vector(1), 4, Vector(0))),"d")
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

    val inputNodes: List[ReteMessage => Unit] =
      List(entryDefined ! _,exitDefined ! _,sensorConnects ! _, rightMostJoin ! _, finalJoin ! _)
    override val terminator = Terminator(inputNodes, production)

  }

  class RouteSensor extends TrainbenchmarkQuery{
    println("route-sensor")
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
    val inputNodes: List[ReteMessage => Unit] = List(antijoin ! _,sensorJoin ! _,followsJoin ! _)
    override val terminator = Terminator(inputNodes, production)
  }
  class SwitchSensor extends TrainbenchmarkQuery {
    println("switch-sensor")
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


    val inputNodes: List[ReteMessage => Unit] = List(antijoin ! _, trimmer ! _)
    override val terminator = Terminator(inputNodes, production)
  }
