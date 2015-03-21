import akka.actor.{Actor, ActorRef}

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
