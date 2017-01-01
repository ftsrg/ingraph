import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.nodes.unary.UnwindNode
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
          tuple("x", 1, "y"),
          tuple("x", 2, "y"),
          tuple("x", 3, "y")
        ),
        negative = tupleBag(
          tuple("a", 1, "b"),
          tuple("a", 2, "b")
        )
      ))
    }

    "do simple unwind 1" in {
      val changeSet = ChangeSet(
        positive = tupleBag(
          tuple("x", List(1,2,3), "y"),
          tuple("w", List(4,5), "z")
        )
      )
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val unwind = system.actorOf(Props(new UnwindNode(echoActor ! _, 1)))

      unwind ! changeSet
      expectMsg(ChangeSet(
        positive = tupleBag(
          tuple("x", 1, "y"),
          tuple("x", 2, "y"),
          tuple("x", 3, "y"),
          tuple("w", 4, "z"),
          tuple("w", 5, "z")
        )
      ))
    }
  }
}
