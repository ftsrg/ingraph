import java.io.{FileInputStream, InputStream}

import Workers.nodeType
import akka.actor.{ActorSystem, Props}

import scala.collection.immutable.HashMap

/**
 * Created by Maginecz on 4/20/2015.
 */
object Queries {
  val repairDivide = 10
  var messageSize = 1
  @volatile var readyFlag = false
  def PosLength(input: WildcardInput) = {
  val system = ActorSystem()

  val production = system.actorOf(Props(new Production("PosLength")))
  val trimmer = system.actorOf(Props(new Trimmer(production ! _, Vector(0))))
  def condition(n: nodeType) = n match {
    case Vector(a, length: Int) => length <= 0
  }
  val first = system.actorOf(Props(new Checker(trimmer ! _, condition)))
  val lookup = Map(
    "length" -> ((cs:ChangeSet) => first ! cs)
  )

  input.sendData(attributeFunc = lookup, messageSize = messageSize)
  val inputNodes: Array[ReteMessage => Unit] = Array(first ! _)
  val productionNodes: Array[ReteMessage => Unit] = Array(production ! _)
  TerminatorInitializer(
    inputNodes, productionNodes, "intitial poslength",
    repair(_, res => Vector(res(0),1), first ! _, inputNodes, productionNodes, messageSize)
  )
  waitForReady()
  system.terminate()
  }

  def SwitchSensor(input: WildcardInput) = {
  val system = ActorSystem()
  val production = system.actorOf(Props(new Production("SwitchSensor")))
  val antijoin = system.actorOf(Props(new HashAntiJoiner(production ! _, Vector(0), Vector(0))))
  val trimmer = system.actorOf(Props(new Trimmer(antijoin ! Secondary(_), Vector(0))))
  val attributes = Map(
    "sensor" -> ((cs:ChangeSet) => trimmer ! cs)
  )
  val types = Map(
    "Switch" -> ((cs: ChangeSet) => antijoin ! Primary(cs))
  )

  val inputNodes: Array[ReteMessage => Unit] = Array(antijoin ! _, trimmer ! _)
  val productionNodes: Array[ReteMessage => Unit] = Array(production ! _)
  input.sendData(attributeFunc = attributes, typeFunc = types, messageSize = messageSize)
  TerminatorInitializer(
    inputNodes, productionNodes, "initial switch",
    repair(_, res => Vector(res(0),"whatever"), trimmer ! _, inputNodes, productionNodes, messageSize)
  )
  waitForReady()
  system.terminate()
  }

  def RouteSensor(input: WildcardInput) = {
  val system = ActorSystem()
  val production = system.actorOf(Props(new Production("RouteSensor")))
  val antijoin = system.actorOf(Props(new HashAntiJoiner(production ! _, Vector(2, 3), Vector(0, 1))))
  val sensorJoin = system.actorOf(Props(new HashJoiner(antijoin ! Primary(_), 3, Vector(1), 2, Vector(0))))
  val followsJoin = system.actorOf(Props(new HashJoiner(sensorJoin ! Primary(_), 2, Vector(0), 2, Vector(1))))
  val attributes = HashMap(
    "follows" -> ((cs:ChangeSet) => followsJoin ! Secondary(cs)),
    "sensor" -> ((cs: ChangeSet) => sensorJoin ! Secondary(cs)),
    "definedBy" -> ((cs: ChangeSet) => antijoin ! Secondary(cs))
  )
  val types = Map(
    "SwitchPosition" -> ((cs: ChangeSet) => followsJoin ! Primary(cs))
  )

  val inputNodes: Array[ReteMessage => Unit] = Array(antijoin ! _,sensorJoin ! _,followsJoin ! _)
  val productionNodes: Array[ReteMessage => Unit] = Array(production ! _)
  input.sendData(attributeFunc = attributes, typeFunc = types, messageSize = messageSize)
  TerminatorInitializer(inputNodes, productionNodes, "initial route",
    repair(_, res => Vector(res(1),res(3)), sensorJoin ! Secondary(_), inputNodes, productionNodes, messageSize)
  )
  waitForReady()
  system.terminate()
  }

  def SemaphoreNeighbor(input: WildcardInput) = {
  val system = ActorSystem()
  val production = system.actorOf(Props(new Production("SemaphoreNeighbor")))
  val antijoin = system.actorOf(Props(new HashAntiJoiner(production ! _, Vector(2, 5), Vector(1, 2))))
  val inequality = system.actorOf(Props(new InequalityChecker(antijoin ! Primary(_), 0, Vector(6))))
  val finalJoin = system.actorOf(Props(new HashJoiner(inequality ! _, 6, Vector(4), 2, Vector(1))))
  val secondTolastJoin = system.actorOf(Props(new HashJoiner(finalJoin ! Primary(_), 3, Vector(1), 4, Vector(0))))
  val rightMostJoin = system.actorOf(Props(new HashJoiner(secondTolastJoin ! Secondary(_), 3, Vector(2), 2, Vector(0))))
  val sensorConnects = system.actorOf(Props(new HashJoiner(rightMostJoin ! Primary(_), 2, Vector(0), 2, Vector(0))))
  val exitDefined = system.actorOf(Props(new HashJoiner(secondTolastJoin ! Primary(_), 2, Vector(0), 2, Vector(0))))
  val entryDefined = system.actorOf(Props(new HashJoiner(antijoin ! Secondary(_), 2, Vector(0), 2, Vector(0))))

  val attributes = Map(
    "entry" -> ((cs: ChangeSet) => entryDefined ! Primary(cs)),
    "definedBy" -> ((cs: ChangeSet) => {
    entryDefined ! Secondary(cs)
    finalJoin ! Secondary(cs)
    exitDefined ! Primary(cs)
    }
    ),
    "exit" -> ((cs: ChangeSet) => exitDefined ! Secondary(cs)),
    "sensor" -> ((cs: ChangeSet) => sensorConnects ! Primary(cs)),
    "connectsTo" -> ((cs: ChangeSet) => sensorConnects ! Secondary(cs))
  )
  input.sendData(attributeFunc = attributes, messageSize = messageSize)
  val inputNodes: Array[ReteMessage => Unit] =
    Array(entryDefined ! _,exitDefined ! _,sensorConnects ! _, rightMostJoin ! _, finalJoin ! _)
  val productionNodes: Array[ReteMessage => Unit] = Array(production ! _)
  TerminatorInitializer(inputNodes, productionNodes, "initial semaphore",
    repair(_, res => Vector(res(0),res(2)), exitDefined ! Secondary(_), inputNodes, productionNodes, messageSize)
  )
  waitForReady()
  system.terminate()
  }

  def connectedSegmentsLinearJoin(input: WildcardInput): Unit ={
  val system = ActorSystem()
  val production = system.actorOf(Props(new Production("ConnectedSegments")))

  val inequality6 = system.actorOf(Props(new InequalityChecker(production ! _, 6, Vector(5,4,3,2,1))))
  val join6 = system.actorOf(Props(new HashJoiner(inequality6 ! _, 6, Vector(0), 2, Vector(0))))

  val inequality5 = system.actorOf(Props(new InequalityChecker(join6 ! Primary(_), 5, Vector(4,3,2,1))))
  val join5 = system.actorOf(Props(new HashJoiner(inequality5 ! _, 5, Vector(0), 2, Vector(0))))

  val inequality4 = system.actorOf(Props(new InequalityChecker(join5 ! Primary(_), 4, Vector(3,2,1))))
  val join4 = system.actorOf(Props(new HashJoiner(inequality4 ! _, 4, Vector(0), 2, Vector(0))))

  val inequality3 = system.actorOf(Props(new InequalityChecker(join4 ! Primary(_), 3, Vector(2,1))))
  val join3 = system.actorOf(Props(new HashJoiner(inequality3 ! _, 3, Vector(0), 2, Vector(0))))

  val inequality2 = system.actorOf(Props(new InequalityChecker(join3 ! Primary(_), 2, Vector(1))))
  val join2 = system.actorOf(Props(new HashJoiner(inequality2 ! _, 2, Vector(0), 2, Vector(0))))

  val attributes = Map(
    "connectsTo" -> ((cs: ChangeSet) => {
    join2 ! Primary(cs)
    join2 ! Secondary(cs)
    join3 ! Secondary(cs)
    join4 ! Secondary(cs)
    join5 ! Secondary(cs)
    join6 ! Secondary(cs)
    })
  )
  val inputNodes: Array[ReteMessage => Unit] =
    Array(join2 ! _,join3 ! _,join4 ! _, join5 ! _, join6 ! _)
  input.sendData(attributeFunc = attributes, messageSize = messageSize)

  val productionNodes: Array[ReteMessage => Unit] = Array(production ! _)
  TerminatorInitializer(inputNodes, productionNodes, "initial linear connectsTo",
    repair(_, res => Vector(res(0),res(1)), join2 ! Primary(_), inputNodes, productionNodes, messageSize)
  )
  waitForReady()
  system.terminate()
  }
  def connectedSegmentsTreeJoin(input: WildcardInput): Unit ={
  val system = ActorSystem()
  val production = system.actorOf(Props(new Production("ConnectedSegments")))

  val inequality6_4321 = system.actorOf(Props(new InequalityChecker(production ! _, 6, Vector(4,3,2,1))))
  val inequality5_4321 = system.actorOf(Props(new InequalityChecker(inequality6_4321 ! _, 5, Vector(4,3,2,1))))
  val join1234_56 = system.actorOf(Props(new HashJoiner(inequality5_4321 ! _, 5, Vector(0), 2, Vector(0))))

  val inequality2_34 = system.actorOf(Props(new InequalityChecker(join1234_56 ! Primary(_), 2, Vector(3,4))))
  val inequality1_34 = system.actorOf(Props(new InequalityChecker(inequality2_34 ! _, 1, Vector(3,4))))
  val join12_34 = system.actorOf(Props(new HashJoiner(inequality1_34 ! _, 3, Vector(0), 3, Vector(0))))

  val inequality5_6 = system.actorOf(Props(new InequalityChecker(join1234_56 ! Secondary(_), 1, Vector(2))))
  val join5_6 = system.actorOf(Props(new HashJoiner(inequality5_6 ! _, 2, Vector(0), 2, Vector(0))))

  val inequality3_4 = system.actorOf(Props(new InequalityChecker(join12_34 ! Secondary(_), 1, Vector(2))))
  val join3_4 = system.actorOf(Props(new HashJoiner(inequality3_4 ! _, 2, Vector(0), 2, Vector(0))))

  val inequality1_2 = system.actorOf(Props(new InequalityChecker(join12_34 ! Primary(_), 2, Vector(1))))
  val join1_2 = system.actorOf(Props(new HashJoiner(inequality1_2 ! _, 2, Vector(0), 2, Vector(0))))

  val attributes = Map(
    "connectsTo" -> ((cs: ChangeSet) => {
    join1_2 ! Primary(cs)
    join1_2 ! Secondary(cs)
    join3_4 ! Primary(cs)
    join3_4 ! Secondary(cs)
    join5_6 ! Primary(cs)
    join5_6 ! Secondary(cs)

    })
  )
  val inputNodes: Array[ReteMessage => Unit] =
    Array(join1_2 ! _,join3_4 ! _,join5_6 ! _ )
  input.sendData(attributeFunc = attributes, messageSize = messageSize)

  val productionNodes: Array[ReteMessage => Unit] = Array(production ! _)
  TerminatorInitializer(inputNodes, productionNodes, "initial connectsTo",
    repair(_, res => Vector(res(0),res(1)), join1_2 ! Primary(_), inputNodes, productionNodes, messageSize)
  )
  waitForReady()
  system.terminate()
  }

  def waitForReady(): Unit ={
  while(!readyFlag)
    Thread.sleep(2000)
  readyFlag = false
  }

  def repair(results: Set[nodeType], mapper: nodeType => nodeType, target: ReteMessage => Unit,
       inputNodes: Array[ReteMessage => Unit], productionNodes: Array[ReteMessage => Unit],
       messageSize: Int): Unit = {
  val values =results.take(results.size / repairDivide).toVector.map(mapper)
  values.grouped(messageSize).foreach( vec => target(ChangeSet(negative = vec)))
  TerminatorInitializer(inputNodes, productionNodes, "repaired", a => { readyFlag = true})
  }

  def main(args: Array[String]) {
  messageSize = args(1).toInt
  val input = WildcardInput(new FileInputStream(args(0)))
  PosLength(input)
  SemaphoreNeighbor(input)
  SwitchSensor(input)
  RouteSensor(input)
  connectedSegmentsLinearJoin(input)
  connectedSegmentsTreeJoin(input)
  }
}
