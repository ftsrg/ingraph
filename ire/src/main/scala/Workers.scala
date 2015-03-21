import akka.actor.{Actor, ActorRef}

/**
 * Created by Maginecz on 3/16/2015.
 */
case class ChangeSet(positive: Vector[Vector[Long]], negative: Vector[Vector[Long]])

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

class TestActor(val expected: ChangeSet) extends Actor {
  override def receive: Receive = {
    case ChangeSet(positive, negative) => {
      assert(positive == expected.positive)
      assert(negative == expected.negative)
    }
  }
}
