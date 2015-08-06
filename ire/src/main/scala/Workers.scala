import java.io.InputStream
import java.util.concurrent.atomic.AtomicInteger

import Workers.nodeType
import akka.actor.{Actor, ActorRef}
import com.twitter.chill.{Input, ScalaKryoInstantiator}

import scala.collection.immutable.HashMap
import scala.collection.mutable

/**
 * Created by Maginecz on 3/16/2015.
 */
package object Workers {
  type nodeType = Vector[Any]
}

class ReteMessage(){}
case class ChangeSet(positive: Vector[nodeType] = Vector(), negative: Vector[nodeType] = Vector())
  extends ReteMessage()

class Probe(id: Int, production: ActorRef, route: List[ActorRef]) extends ReteMessage{
  def forkMessage(count: Int): Unit = {
   production ! ExpectMoreTerminators(id, count: Int)
  }
  def exec(current: ActorRef): Probe = {
    new Probe(id, production, current :: route)
  }
}

case class ExpectMoreTerminators(id: Int, count: Int)
case class TerminatorMessage(terminatorID: Int, messageID: Int, route: List[ActorRef] = List.empty) extends ReteMessage()
case class ExpectTerminator(terminatorID: Int, messageID: Int, phase: String, function: Set[nodeType]=> Unit) extends ReteMessage()
class Terminator private (terminatorID: Int, val inputs:List[ReteMessage => Unit], production: ActorRef) extends ReteMessage {
  def send(phase: String, function: Set[nodeType]=>Unit) = {
    val messageID = Terminator.idCounter.getNext()
    production ! ExpectTerminator(terminatorID, messageID, phase, function)
    inputs.foreach(_(TerminatorMessage(terminatorID,messageID)))
  }
}
object Terminator {
  val idCounter = new AtomicUniqueCounter

  def apply(inputs: List[ReteMessage => Unit], productionNode: ActorRef):Terminator = {
    val id = idCounter.getNext()
    productionNode ! ExpectMoreTerminators(id, inputs.size)
    inputs.foreach(_( new Probe(id, productionNode, List.empty[ActorRef]) ))
    new Terminator(id, inputs, productionNode)
  }
}

trait Forwarder {
  val next:ReteMessage => Unit

  def forward(cs: ChangeSet) =  next(cs)
  def forward(terminator: TerminatorMessage) = next(terminator)
  def forward(probe: Probe) = next(probe)
}

abstract class AlphaNode(val next: (ReteMessage) => Unit) extends Actor with Forwarder{

  def onChangeSet(changeSet: ChangeSet)

  override def receive: Actor.Receive = {
    case changeSet:ChangeSet => onChangeSet(changeSet)
    case probe:Probe => forward(probe.exec(self))
    case terminator: TerminatorMessage => forward(terminator)
  }
}

abstract class BetaNode(val next: (ReteMessage) => Unit) extends Actor with Forwarder{

  def onPrimary(changeSet: ChangeSet)
  def onSecondary(changeSet: ChangeSet)

  override def receive: Actor.Receive = {
    case Primary(changeSet: ChangeSet) => onPrimary(changeSet)
    case Secondary(changeSet: ChangeSet) => onSecondary(changeSet)

    case probe: Probe => forward(probe.exec(self))
    case Primary(probe: Probe) => forward(probe.exec(self))
    case Secondary(probe: Probe) => forward(probe.exec(self))
    case terminator: TerminatorMessage => forward(terminator)
    case Primary(terminator: TerminatorMessage) => forward(terminator)
    case Secondary(terminator: TerminatorMessage) => forward(terminator)
  }
}

class Trimmer(override val next: (ReteMessage) => Unit , val selectionVector: Vector[Int]) extends AlphaNode(next) {
  override def onChangeSet(changeSet: ChangeSet) = {
       forward(ChangeSet(
        changeSet.positive.map(vec => selectionVector.map(i => vec(i))),
        changeSet.negative.map(vec => selectionVector.map(i => vec(i)))
      ))
    }
}

import scala.collection.mutable.MultiMap

case class Primary(value: ReteMessage)

case class Secondary(value: ReteMessage)

class HashJoiner(override val next: (ReteMessage) => Unit ,
                 val primaryLength: Int, val primarySelector: Vector[Int],
                 val secondaryLength: Int, val secondarySelector: Vector[Int])
  extends BetaNode(next) {
  val primaryValues = new mutable.HashMap[nodeType, mutable.Set[nodeType]] with MultiMap[nodeType, nodeType]
  val secondaryValues = new mutable.HashMap[nodeType, mutable.Set[nodeType]] with MultiMap[nodeType, nodeType]

  val inversePrimarySelector = Vector.range(0, primaryLength) filter (i => !primarySelector.contains(i))
  val inverseSecondarySelector = Vector.range(0, secondaryLength) filter (i => !secondarySelector.contains(i))


  def onPrimary(changeSet: ChangeSet):Unit = {
    val positive = changeSet.positive
    val negative = changeSet.negative

    val joinedPositive = for {
      primaryVec <- positive
      key = primarySelector.map(i => primaryVec(i))
      if secondaryValues.contains(key)
      secondaryVec <- secondaryValues(key)
    } yield primaryVec ++ inverseSecondarySelector.map(i => secondaryVec(i))

    val joinedNegative = for {
      primaryVec <- negative
      key = primarySelector.map(i => primaryVec(i))
      if secondaryValues.contains(key)
      secondaryVec <- secondaryValues(key)
    } yield primaryVec ++ inverseSecondarySelector.map(i => secondaryVec(i))

    forward(ChangeSet(joinedPositive, joinedNegative))
    positive.foreach(
        vec => {
        val key = primarySelector.map(i => vec(i))
        primaryValues.addBinding(key, vec)
      }
    )
    negative.foreach(
      vec => {
        val key = primarySelector.map(i => vec(i))
        primaryValues.removeBinding(key, vec)
      }
    )
  }

  def onSecondary(changeSet: ChangeSet): Unit = {
      val positive = changeSet.positive
      val negative = changeSet.negative

      val joinedPositive = for {
        secondaryVec <- positive
        key = secondarySelector.map(i => secondaryVec(i))
        if primaryValues.contains(key)
        primaryVec <- primaryValues(key)
      } yield primaryVec ++ inverseSecondarySelector.map(i => secondaryVec(i))


      val joinedNegative = for {
        secondaryVec <- negative
        key = secondarySelector.map(i => secondaryVec(i))
        if primaryValues.contains(key)
        primaryVec <- primaryValues(key)
      } yield primaryVec ++ inverseSecondarySelector.map(i => secondaryVec(i))

      forward(ChangeSet(joinedPositive, joinedNegative))

      positive.foreach(
        vec => {
          val key = secondarySelector.map(i => vec(i))
          secondaryValues.addBinding(key, vec) //must be used with multimaps
        }
      )
      negative.foreach(
        vec => {
          val key = secondarySelector.map(i => vec(i))
          secondaryValues.removeBinding(key, vec)
        }
      )
    }
}

class Checker(override val next: (ReteMessage) => Unit,
              val condition: (nodeType) => Boolean) extends AlphaNode(next) {
  def onChangeSet(changeSet: ChangeSet): Unit = {
    forward(ChangeSet(
      changeSet.positive.filter(condition),
      changeSet.negative.filter(condition)
    ))
  }
}

class InequalityChecker(override val next: (ReteMessage) => Unit ,
                        val nodeIndex: Int, val inequals: Vector[Int]) extends
  Checker(next,  condition=(node: nodeType) =>
          { !inequals.map { i => node(i) }.exists { value => value == node(nodeIndex) } }
)

class EqualityChecker(override val next: (ReteMessage) => Unit ,
                      val nodeIndex: Int, val equals: Vector[Int]) extends
  Checker(next, condition=(node: nodeType) =>
        { equals.map { i => node(i) }.forall { value => value == node(nodeIndex) }  }
)

class HashAntiJoiner(override val next: (ReteMessage) => Unit ,
                     val primarySelector: Vector[Int],
                     val secondarySelector: Vector[Int])
  extends BetaNode(next) {
  val primaryValues = new mutable.HashMap[nodeType, mutable.Set[nodeType]] with MultiMap[nodeType, nodeType]
  val secondaryValues = new mutable.HashSet[nodeType]

  def onPrimary(changeSet: ChangeSet): Unit = {
    val positive = changeSet.positive
    val negative = changeSet.negative

    val joinedPositive = for {
      node <- positive
      if !secondaryValues.contains(primarySelector.map(i => node(i)))
    } yield node


    val joinedNegative = for {
      node: nodeType <- negative
      if secondaryValues.contains(primarySelector.map(i => node(i)))
    } yield node

    forward(ChangeSet(joinedPositive, joinedNegative))

    positive.foreach(
      vec => {
        val key = primarySelector.map(i => vec(i))
        primaryValues.addBinding(key, vec)
      }
    )
    negative.foreach(
      vec => {
        val key = primarySelector.map(i => vec(i))
        primaryValues.removeBinding(key, vec)
      }
    )
  }

  def onSecondary(changeSet: ChangeSet): Unit = {
    val positive = changeSet.positive
    val negative = changeSet.negative

    val joinedNegative = for {//this is switched because antijoin
      node <- positive
      key = secondarySelector.map(i => node(i))
      if primaryValues.contains(key)
    } yield primaryValues(key)

    val joinedPositive = for {
      node: nodeType <- negative
      key = secondarySelector.map(i => node(i))
      if primaryValues.contains(secondarySelector.map(i => node(i)))
    } yield primaryValues(node)

    forward(ChangeSet(joinedPositive.flatten, joinedNegative.flatten))

    positive.foreach(
      vec => {
        val key = secondarySelector.map(i => vec(i))
        secondaryValues.add(key) //must be used with multimaps
      }
    )
    negative.foreach(
      vec => {
        val key = secondarySelector.map(i => vec(i))
        secondaryValues.remove(key)
      }
    )
  }
}

class Production(queryName: String) extends Actor with ResultLogger{
  var expectedTerminators = mutable.Map.empty[Int,Int]
  val receivedTerminatorCount = mutable.Map.empty[Int,Int]

  var t0 = System.nanoTime()

  val results = new mutable.HashSet[nodeType]
  val terminatorPhases = mutable.Map.empty[Int, String]
  val terminatorFunctions = mutable.Map.empty[Int, Set[nodeType]=> Unit]


  def getAndResetElapsedTime(): Long = {
    val t1 = System.nanoTime()
    val retVal = t1 - t0
    t0 = t1
    return retVal
  }
  override def receive: Actor.Receive = {
    case ChangeSet(p, n) => {
      val t1 = System.nanoTime()
      p.foreach { results.add(_) }
      n.foreach { results.remove(_) }
    }
    case ExpectMoreTerminators(id, count) => {
      val current = expectedTerminators.getOrElse(id,0)
      expectedTerminators(id) = current + 1
    }
    case TerminatorMessage(terminatorID, messageID, route) => {
      receivedTerminatorCount(messageID) += 1
      if (receivedTerminatorCount(messageID) == expectedTerminators(terminatorID)){
            val timeNano = getAndResetElapsedTime()
            logResult(queryName, terminatorPhases(messageID), timeNano)
            terminatorFunctions(messageID)(results.toSet)

            receivedTerminatorCount.drop(messageID)
            terminatorPhases.drop(messageID)
            terminatorFunctions.drop(messageID)

          }
    }
    case ExpectTerminator(terminatorID, messageID, phase, function) => {
      receivedTerminatorCount(messageID) = 0
      terminatorPhases(messageID) = phase
      terminatorFunctions(messageID) = function
    }
  }
}

class WildcardInput(multiValue: Set[String] = Set(), specialFunction: Map[String, (Any)=>Any] = Map()) {
  val types = new mutable.HashMap[String, mutable.Set[Long]]()
  val mutliValueAttributes = new mutable.HashMap[String, mutable.Map[Long, mutable.Set[Any]]]
  val attributes = new mutable.HashMap[String, mutable.Map[Long, Any]]

  def addAttribute(strID: String, attribute: String, rawValue: Any): Unit = {
    val id = utils.idStringToLong(strID)
    val value = if (specialFunction.contains(attribute)) {
      specialFunction(attribute)(rawValue)
    } else {
      rawValue
    }

    if (attribute == "type") {
      val valueString = value.toString
      if (!types.contains(valueString)) //withdefault is not applicable, as it would always return the same set
        types.put(valueString, new mutable.HashSet[Long]()) //resulting in all ids going to the same set
      types(valueString).add(id)
    }
    else if (multiValue.contains(attribute)) {
      if (!mutliValueAttributes.contains(attribute))
        mutliValueAttributes.put(attribute, new mutable.HashMap[Long, mutable.Set[Any]])
      if (!mutliValueAttributes(attribute).contains(id))
        mutliValueAttributes(attribute).put(id, new mutable.HashSet[Any])
      mutliValueAttributes(attribute)(id).add(value)
    } else {
      if (!attributes.contains(attribute))
        attributes.put(attribute, new mutable.HashMap[Long, Any])
      if (attributes(attribute).contains(id))
        throw new RuntimeException(s"$attribute already set for $id (maybe this is a multiValue attribute?)")
      attributes(attribute).put(id, value)
    }
  }

  def sendData(attributeFunc: Map[String, (ChangeSet) => Unit] = new HashMap[String, (ChangeSet) => Unit],
               typeFunc: Map[String, (ChangeSet) => Unit] = new HashMap[String, (ChangeSet) => Unit],
               messageSize: Int = 1
                ) = {

    for (
      (attribute, func) <- attributeFunc
    ){
      if (multiValue.contains(attribute)) {
        for (
          (id, values) <- mutliValueAttributes(attribute);
          output <- values.grouped(messageSize)
        )
          func(ChangeSet(positive = output.toVector.map((v) => Vector(id, v))))
      } else {
        for (items <- attributes(attribute).grouped(messageSize))
          func(ChangeSet(positive = items.toVector.map( (v) => Vector(v._1, v._2))))
      }

    }
    for {
      (typeOfNode, func) <- typeFunc
      nodes <- types(typeOfNode).grouped(messageSize)
    }
      func(ChangeSet(positive = nodes.toVector.map((v) => Vector(v))))

  }
}

object WildcardInput {
  def apply(stream: InputStream): WildcardInput = {
    val instantiator = new ScalaKryoInstantiator
    instantiator.setRegistrationRequired(false)
    val kryo = instantiator.newKryo()
    val input = new Input(stream)
    kryo.readObject(input, classOf[WildcardInput])
  }
}
