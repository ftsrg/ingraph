import Workers.nodeType
import akka.actor.Actor

import scala.collection.mutable
/**
 * Created by Maginecz on 3/16/2015.
 */
package object Workers {
  type nodeType = Vector[Any]
}
case class ChangeSet(positive: Vector[nodeType] = Vector(), negative: Vector[nodeType] = Vector())

class Trimmer(val next: (ChangeSet) => Unit, val selectionVector: Vector[Int]) extends Actor {
  override def receive: Receive = {
  case ChangeSet(positive, negative) => {
    next(ChangeSet(
    positive.map(vec => selectionVector.map(i => vec(i))),
    negative.map(vec => selectionVector.map(i => vec(i)))
    )
    )
  }
  }
}

import scala.collection.mutable.MultiMap

case class Primary(changeSet: ChangeSet)
case class Secondary(changeSet: ChangeSet)

class HashJoiner(val next:(ChangeSet) => Unit,
         val primaryLength: Int, val primarySelector: Vector[Int],
         val secondaryLength: Int, val secondarySelector: Vector[Int])
  extends Actor {
  var primaryValues = new mutable.HashMap[nodeType, mutable.Set[nodeType]] with MultiMap[nodeType, nodeType]
  var secondaryValues = new mutable.HashMap[nodeType, mutable.Set[nodeType]] with MultiMap[nodeType, nodeType]

  val inversePrimarySelector = Vector.range(0,primaryLength) filter( i=> !primarySelector.contains(i))
  val inverseSecondarySelector = Vector.range(0,secondaryLength) filter( i=> !secondarySelector.contains(i))

  override def receive: Actor.Receive = {
  case Primary(ChangeSet(positive, negative)) => {
    val joinedPositive = for {
      primaryVec <- positive
      key = primarySelector.map(i => primaryVec(i))
      if secondaryValues.contains(key)
      secondaryVec <- secondaryValues(key)
  }
    yield primaryVec ++ inverseSecondarySelector.map( i=>secondaryVec(i) )


    val joinedNegative = for {
    primaryVec <- negative
    key = primarySelector.map(i => primaryVec(i))
    if secondaryValues.contains(key)
    secondaryVec <- secondaryValues(key)
    }
    yield primaryVec ++ inverseSecondarySelector.map( i=>secondaryVec(i) )

    next(ChangeSet(joinedPositive,joinedNegative))
    positive.foreach(
    vec => {
      val key = primarySelector.map(i => vec(i))
      primaryValues.addBinding(key,vec)
    }
    )
    negative.foreach(
    vec =>{
      val key = primarySelector.map(i => vec(i))
      primaryValues.removeBinding(key,vec)
    }
    )
  }
  case Secondary(ChangeSet(positive, negative)) => {
    val joinedPositive = for {
    secondaryVec <- positive
    key = secondarySelector.map(i => secondaryVec(i))
    if primaryValues.contains(key)
    primaryVec <- primaryValues(key)
  }
     yield primaryVec ++ inverseSecondarySelector.map( i=>secondaryVec(i) )


    val joinedNegative = for {
    secondaryVec <- negative
    key = secondarySelector.map(i => secondaryVec(i))
    if primaryValues.contains(key)
    primaryVec <- primaryValues(key)
  }
     yield primaryVec ++ inverseSecondarySelector.map( i=>secondaryVec(i) )

    next(ChangeSet(joinedPositive,joinedNegative))

    positive.foreach(
    vec => {
      val key = secondarySelector.map(i => vec(i))
      secondaryValues.addBinding(key,vec) //must be used with multimaps
    }
    )
    negative.foreach(
    vec =>{
      val key = secondarySelector.map(i => vec(i))
      secondaryValues.removeBinding(key,vec)
    }
    )
  }
  }
}

class Checker(val next: (ChangeSet)=> Unit, val condition: (nodeType)=> Boolean) extends Actor{
  override def receive: Actor.Receive = {
  case ChangeSet(positive, negative) => {
    next(ChangeSet(
    positive.filter(condition),
    negative.filter(condition)
    ))
  }
  }
}

class InequalityChecker(override val next: (ChangeSet)=> Unit,val nodeIndex: Int, val inequals: Vector[Int]) extends Checker(
  next,
  (node:nodeType)=> { !inequals.map{ i => node(i)}.exists{ value =>value==node(nodeIndex)}}
)

class EqualityChecker(override val next: (ChangeSet)=> Unit,val nodeIndex: Int, val equals: Vector[Int]) extends Checker(
  next,
  (node:nodeType)=> { equals.map{ i => node(i)}.forall{ value =>value==node(nodeIndex)}}
)

class Printer() extends Actor {
  override def receive: Receive = {
  case ChangeSet(positive, negative) => {
    print("+:")
    println(positive)
    print("-:")
    println(negative)
  }
  }
}

class HashAntiJoiner(val next:(ChangeSet) => Unit,
           val primarySelector: Vector[Int],
           val secondarySelector: Vector[Int])
  extends Actor {
  var primaryValues = new mutable.HashMap[nodeType, mutable.Set[nodeType]] with MultiMap[nodeType, nodeType]
  var secondaryValues = new mutable.HashSet[nodeType]

  override def receive: Actor.Receive = {
  case Primary(ChangeSet(positive, negative)) => {
    val joinedPositive=for {
    node <- positive
    if !secondaryValues.contains(primarySelector.map(i => node(i)))
    }
    yield node
    val joinedNegative=for {
    node: nodeType <- negative
    if secondaryValues.contains(primarySelector.map(i => node(i)))
    }
    yield node

    next(ChangeSet(joinedPositive,joinedNegative))

    positive.foreach(
    vec => {
      val key = primarySelector.map(i => vec(i))
      primaryValues.addBinding(key,vec)
    }
    )
    negative.foreach(
    vec =>{
      val key = primarySelector.map(i => vec(i))
      primaryValues.removeBinding(key,vec)
    }
    )
  }
  case Secondary(ChangeSet(positive, negative)) => {
    val joinedNegative=for { //this is switched because antijoin
    node<- positive; //semicolon really is needed here
    key= secondarySelector.map(i => node(i))
    if primaryValues.contains(key)
    }
    yield primaryValues(key)
    val joinedPositive=for{
    node:nodeType <- negative
    key= secondarySelector.map(i => node(i))
    if primaryValues.contains(secondarySelector.map(i => node(i)))
    }
    yield primaryValues(node)

    next(ChangeSet(joinedPositive.flatten,joinedNegative.flatten))

    positive.foreach(
    vec => {
      val key = secondarySelector.map(i => vec(i))
      secondaryValues.add(key)//must be used with multimaps
    }
    )
    negative.foreach(
    vec =>{
      val key = secondarySelector.map(i => vec(i))
      secondaryValues.remove(key)
    }
    )
  }
  }
}
