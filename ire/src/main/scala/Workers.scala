import Workers.nodeType
import akka.actor.Actor

import scala.collection.mutable
/**
 * Created by Maginecz on 3/16/2015.
 */
package object Workers {
  type nodeType = Vector[Long]
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

import collection.mutable.MultiMap

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
      val joinedPositive = positive.filter( primaryVec => {
        secondaryValues.contains(primarySelector.map(i => primaryVec(i)))
      }).
      map{
        primaryVec => {
          val key = primarySelector.map(i => primaryVec(i))
          val rest = inversePrimarySelector.map(i => primaryVec(i))
          secondaryValues(key).map(
            secondaryVec => {
              primaryVec ++ inverseSecondarySelector.map( i=>secondaryVec(i) )
            }
          ).toVector
        }
      }
      val joinedNegative = negative.map(
        primaryVec => {
          val key = primarySelector.map(i => primaryVec(i))
          secondaryValues(key).map(
            secondaryVec => {
              primaryVec ++ inverseSecondarySelector.map( i=>secondaryVec(i) )
            }.toVector
          )
        }
      )
      next(ChangeSet(joinedPositive.flatten,joinedNegative.flatten))
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
      val joinedPositive = positive.map{
        secondaryVec => {
          val key = secondarySelector.map(i => secondaryVec(i))
          val rest = inverseSecondarySelector.map(i => secondaryVec(i))
          primaryValues(key).map(
            primaryVec => {
              primaryVec ++ inverseSecondarySelector.map( i=>secondaryVec(i) )
            }
          ).toVector
        }
      }
      val joinedNegative = negative.map(
        secondaryVec => {
          val key = secondarySelector.map(i => secondaryVec(i))
          val rest = inverseSecondarySelector.map(i => secondaryVec(i))
          primaryValues(key).map(
            primaryVec => {
              primaryVec ++ inverseSecondarySelector.map( i=>secondaryVec(i) )
            }.toVector
          )
        }
      )

      next(ChangeSet(joinedPositive.flatten,joinedNegative.flatten))

      positive.foreach(
        vec => {
          val key = primarySelector.map(i => vec(i))
          secondaryValues.addBinding(key,vec)
        }
      )
      negative.foreach(
        vec =>{
          val key = primarySelector.map(i => vec(i))
          secondaryValues.removeBinding(key,vec)
        }
      )
    }
  }
}

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
