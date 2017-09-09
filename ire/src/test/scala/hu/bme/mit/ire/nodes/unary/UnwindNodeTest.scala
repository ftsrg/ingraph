package hu.bme.mit.ire.nodes.unary

import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.ChangeSet
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
      val changeSet = ChangeSet(
        positive = tupleBag(
          tuple("x", cypherList(1, 2, 3), "y"),
          tuple("w", cypherList(), "z")
        ),
        negative = tupleBag(
          tuple("a", cypherList(1, 2), "b"),
          tuple("c", cypherList(), "d")
        )
      )
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val unwind = system.actorOf(Props(new UnwindNode(echoActor ! _, 1)))

      unwind ! changeSet
      expectMsg(ChangeSet(
        positive = tupleBag(
          tuple("x", cypherList(1, 2, 3), "y", 1),
          tuple("x", cypherList(1, 2, 3), "y", 2),
          tuple("x", cypherList(1, 2, 3), "y", 3)
        ),
        negative = tupleBag(
          tuple("a", cypherList(1, 2), "b", 1),
          tuple("a", cypherList(1, 2), "b", 2)
        )
      ))
    }

    "do simple unwind 1" in {
      val changeSet = ChangeSet(
        positive = tupleBag(
          tuple("x", List(1, 2, 3), "y"),
          tuple("w", List(4, 5), "z")
        )
      )
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val unwind = system.actorOf(Props(new UnwindNode(echoActor ! _, 1)))

      unwind ! changeSet
      expectMsg(ChangeSet(
        positive = tupleBag(
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
