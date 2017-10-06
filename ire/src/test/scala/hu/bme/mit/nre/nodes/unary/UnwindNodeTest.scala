package hu.bme.mit.nre.nodes.unary

import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.{BatchChangeSet, IncrementalChangeSet}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class UnwindNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  import hu.bme.mit.ire.util.TestUtil._

  "Unwind" must {
    "do simple unwind 0" in {
      val changeSet = BatchChangeSet(
        tupleBag(
          tuple("x", cypherList(1, 2, 3), "y"),
          tuple("w", cypherList(), "z")
        )
      )
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val unwind = system.actorOf(Props(new UnwindNode(echoActor ! _, 1)))

      unwind ! changeSet
      expectMsg(BatchChangeSet(
        tupleBag(
          tuple("x", cypherList(1, 2, 3), "y", 1),
          tuple("x", cypherList(1, 2, 3), "y", 2),
          tuple("x", cypherList(1, 2, 3), "y", 3)
        )
      ))
    }

    "do simple unwind 1" in {
      val changeSet = BatchChangeSet(
        tupleBag(
          tuple("x", List(1, 2, 3), "y"),
          tuple("w", List(4, 5), "z")
        )
      )
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val unwind = system.actorOf(Props(new UnwindNode(echoActor ! _, 1)))

      unwind ! changeSet
      expectMsg(BatchChangeSet(
        tupleBag(
          tuple("x", cypherList(1, 2, 3), "y", 1),
          tuple("x", cypherList(1, 2, 3), "y", 2),
          tuple("x", cypherList(1, 2, 3), "y", 3),
          tuple("w", cypherList(4, 5),    "z", 4),
          tuple("w", cypherList(4, 5),    "z", 5)
        )
      ))
    }
  }
}
