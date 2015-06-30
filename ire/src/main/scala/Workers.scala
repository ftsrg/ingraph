import java.io.InputStream

import Workers.nodeType
import akka.actor.Actor
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

case class Terminator(id: Int) extends ReteMessage()
case class ExpectTerminator(ids: Range, message: String, function: Set[nodeType]=> Unit) extends ReteMessage()

object TerminatorInitializer {
  private var nextTerminatorIndex: Int = 0

  def getUniqueRange(size: Int): Range = {
  this.synchronized {
    //TODO: prettier way in scala?
    val range = nextTerminatorIndex to nextTerminatorIndex + size
    nextTerminatorIndex += size +1
    return range
  }
  }

  def apply(inputs: Array[ReteMessage => Unit], productionNodes: Iterable[ReteMessage => Unit], message: String, terminationFunction: Set[nodeType] => Unit) {
  for (productionNode <- productionNodes) {
    val range = TerminatorInitializer.getUniqueRange(inputs.size)
    productionNode(ExpectTerminator(range, message, terminationFunction))
    for (i <- range)
    inputs(i % inputs.size)(Terminator(i))
  }
  }
}
trait TerminatorForwarder {
  val next: (Terminator) => Unit
  def onTerminator(msg: Terminator) = next(msg)
}
abstract class AlphaNode(val next: (ReteMessage) => Unit) extends Actor with TerminatorForwarder{

  def onChangeSet(changeSet: ChangeSet): Unit

  override def receive: Actor.Receive = {
  case changeSet:ChangeSet => onChangeSet(changeSet)
  case terminator: Terminator => onTerminator(terminator)
  }
}

abstract class BetaNode(val next: (ReteMessage) => Unit) extends Actor with TerminatorForwarder{

  def onPrimary(changeSet: ChangeSet): Unit
  def onSecondary(changeSet: ChangeSet): Unit

  override def receive: Actor.Receive = {
  case Primary(changeSet: ChangeSet) => onPrimary(changeSet)
  case Secondary(changeSet: ChangeSet) => onSecondary(changeSet)
  //terminators
  case Primary(terminator: Terminator) => onTerminator(terminator)
  case Secondary(terminator: Terminator) => onTerminator(terminator)
  case terminator: Terminator => onTerminator(terminator)
  }
}

class Trimmer(override val next: (ReteMessage) => Unit, val selectionVector: Vector[Int]) extends AlphaNode(next) {
  def onChangeSet(changeSet: ChangeSet): Unit = {
    next(ChangeSet(
    changeSet.positive.map(vec => selectionVector.map(i => vec(i))),
    changeSet.negative.map(vec => selectionVector.map(i => vec(i)))
    )
    )
  }
}

import scala.collection.mutable.MultiMap

case class Primary(value: ReteMessage)

case class Secondary(value: ReteMessage)

class HashJoiner(override val next: (ReteMessage) => Unit,
         val primaryLength: Int, val primarySelector: Vector[Int],
         val secondaryLength: Int, val secondarySelector: Vector[Int])
  extends BetaNode(next) {
  val primaryValues = new mutable.HashMap[nodeType, mutable.Set[nodeType]] with MultiMap[nodeType, nodeType]
  val secondaryValues = new mutable.HashMap[nodeType, mutable.Set[nodeType]] with MultiMap[nodeType, nodeType]

  val inversePrimarySelector = Vector.range(0, primaryLength) filter (i => !primarySelector.contains(i))
  val inverseSecondarySelector = Vector.range(0, secondaryLength) filter (i => !secondarySelector.contains(i))


  def onPrimary(changeSet: ChangeSet): Unit = {
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

  next(ChangeSet(joinedPositive, joinedNegative))
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

    next(ChangeSet(joinedPositive, joinedNegative))

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

class Checker(override val next: (ReteMessage) => Unit, val condition: (nodeType) => Boolean) extends AlphaNode(next) {
  def onChangeSet(changeSet: ChangeSet): Unit = {
  next(ChangeSet(
    changeSet.positive.filter(condition),
    changeSet.negative.filter(condition)
  )
  )
  }
}

class InequalityChecker(override val next: (ReteMessage) => Unit, val nodeIndex: Int, val inequals: Vector[Int]) extends Checker(
  next,
  (node: nodeType) => {
  !inequals.map { i => node(i) }.exists { value => value == node(nodeIndex) }
  }
)

class EqualityChecker(override val next: (ReteMessage) => Unit, val nodeIndex: Int, val equals: Vector[Int]) extends Checker(
  next,
  (node: nodeType) => {
  equals.map { i => node(i) }.forall { value => value == node(nodeIndex) }
  }
)

class HashAntiJoiner(override val next: (ReteMessage) => Unit,
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

  next(ChangeSet(joinedPositive, joinedNegative))

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

  next(ChangeSet(joinedPositive.flatten, joinedNegative.flatten))

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

class Production(name: String) extends Actor {
  val t0 = System.nanoTime()

  val results = new mutable.HashSet[nodeType]
  val terminatorSets = new mutable.Queue[mutable.HashSet[Int]]
  val terminatorMessages = new mutable.Queue[String]
  val terminatorFunctions = new mutable.Queue[Set[nodeType]=> Unit]

  def getElapsedTime() = System.nanoTime() - t0
  override def receive: Actor.Receive = {
  case ChangeSet(p, n) => {
    val t1 = System.nanoTime()
    p.foreach { results.add(_) }
    n.foreach { results.remove(_) }
  }
  case Terminator(id) => {
    var toRemove = new mutable.Queue[Int]
    for (i <-  0 to terminatorSets.size -1 ) {
    if (terminatorSets(i).remove(id)) //set contained id
      if (terminatorSets(i).isEmpty){
      val time = getElapsedTime()
      println(terminatorMessages(i),time,(time/math.pow(10,9)).toString + " s")
      terminatorFunctions(i)(results.toSet)
      toRemove+=i
      }
    }
    toRemove.foreach { i: Int => terminatorSets.drop(i); terminatorMessages.drop(i); terminatorFunctions.drop(i) }
  }
  case ExpectTerminator(ids, message, function) => {
    terminatorSets += (new mutable.HashSet ++ ids) //wtf
    terminatorMessages += message
    terminatorFunctions += function
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
