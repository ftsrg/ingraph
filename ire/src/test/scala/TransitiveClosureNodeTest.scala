import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.nodes.unary.TransitiveClosureNode
import hu.bme.mit.ire.util.TestUtil._
import hu.bme.mit.ire.datatypes.Path
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class TransitiveClosureNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  val src = 0
  val trg = 2
  val edge = 1

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A TransitiveClosureNode" must {
    "calculate transitive closure" in {
      val changeSet = ChangeSet(
        positive = Vector(tuple(1, 100, 2), tuple(2, 101, 3))
      )

      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureNode(echoActor ! _, src, trg, edge)))

      transitiveClosure ! changeSet
      expectMsg(ChangeSet(
        positive = Vector(tuple(1, 2, Path(100)), tuple(1, 3, Path(100, 101)), tuple(2, 3, Path(101)))
      ))
    }
  }

  "A TransitiveClosureNode" must {
    "handle multiple paths and edges between vertices" in {
      val changeSet = ChangeSet(
        positive = Vector(tuple(1, 100, 2), tuple(2, 101, 3))
      )

      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureNode(echoActor ! _, src, trg, edge)))

      transitiveClosure ! changeSet
      expectMsg(ChangeSet(
        positive = Vector(tuple(1, 2, Path(100)), tuple(1, 3, Path(100, 101)), tuple(2, 3, Path(101)))
      ))

      transitiveClosure ! ChangeSet(positive = Vector(tuple(1, 102, 3), tuple(2, 103, 3)))
      expectMsg(ChangeSet(
        positive = Vector(tuple(1, 3, Path(102)), tuple(1, 3, Path(100, 103)), tuple(2, 3, Path(103)))
      ))
    }
  }

  "A TransitiveClosureNode" must {
    "calculate negative updates" in {
      val changeSet = ChangeSet(
        positive = Vector(tuple(1, 100, 2), tuple(2, 101, 3))
      )

      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureNode(echoActor ! _, src, trg, edge)))

      transitiveClosure ! changeSet
      expectMsg(ChangeSet(
        positive = Vector(tuple(1, 2, Path(100)), tuple(1, 3, Path(100, 101)), tuple(2, 3, Path(101)))
      ))

      transitiveClosure ! ChangeSet(negative = Vector(tuple(2, 101, 3)))
      expectMsg(ChangeSet(
        negative = Vector(tuple(1, 3, Path(100, 101)), tuple(2, 3, Path(101)))
      ))

      transitiveClosure ! ChangeSet(negative = Vector(tuple(1, 100, 2)))
      expectMsg(ChangeSet(
        negative = Vector(tuple(1, 2, Path(100)))
      ))
    }
  }

  "A TransitiveClosureNode" must {
    "include directed cycles" in {
      val changeSet = ChangeSet(
        positive = Vector(tuple(1, 100, 2), tuple(2, 101, 3))
      )

      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureNode(echoActor ! _, src, trg, edge)))

      transitiveClosure ! changeSet
      expectMsg(ChangeSet(
        positive = Vector(tuple(1, 2, Path(100)), tuple(1, 3, Path(100, 101)), tuple(2, 3, Path(101)))
      ))

      transitiveClosure ! ChangeSet(positive = Vector(tuple(3, 102, 1)))
      expectMsg(ChangeSet(
        positive = Vector(tuple(2, 2, Path(101, 102, 100)), tuple(2, 1, Path(101, 102)), tuple(1, 1, Path(100, 101, 102)), tuple(3, 2, Path(102, 100)), tuple(3, 3, Path(102, 100, 101)), tuple(3, 1, Path(102)))
      ))

      transitiveClosure ! ChangeSet(negative = Vector(tuple(2, 101, 3)))
      expectMsg(ChangeSet(
        negative = Vector(tuple(1, 1, Path(100, 101, 102)), tuple(1, 3, Path(100, 101)), tuple(2, 2, Path(101, 102, 100)), tuple(2, 1, Path(101, 102)), tuple(2, 3, Path(101)), tuple(3, 3, Path(102, 100, 101)))
      ))
    }
  }

  "A TransitiveClosureNode" must {
    "not do anything in case of non-present vertices or edges" in {
      val changeSet = ChangeSet(
        positive = Vector(tuple(1, 100, 2), tuple(2, 101, 3))
      )

      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureNode(echoActor ! _, src, trg, edge)))

      transitiveClosure ! changeSet
      expectMsg(ChangeSet(
        positive = Vector(tuple(1, 2, Path(100)), tuple(1, 3, Path(100, 101)), tuple(2, 3, Path(101)))
      ))

      transitiveClosure ! ChangeSet(negative = Vector(tuple(2, 1010, 3)))
      expectNoMsg

      transitiveClosure ! ChangeSet(negative = Vector(tuple(0, 100, 2)))
      expectNoMsg
    }
  }

  "A TransitiveClosureNode" must {
    "discard paths with same edge twice" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureNode(echoActor ! _, src, trg, edge)))

      transitiveClosure ! ChangeSet(positive = Vector(tuple(1, 100, 2), tuple(2, 101, 3)))
      expectMsg(ChangeSet(
        positive = Vector(tuple(1, 2, Path(100)), tuple(1, 3, Path(100, 101)), tuple(2, 3, Path(101)))
      ))

      transitiveClosure ! ChangeSet(positive = Vector(tuple(3, 102, 1)))
      expectMsg(ChangeSet(
        positive = Vector(tuple(2, 2, Path(101, 102, 100)), tuple(2, 1, Path(101, 102)), tuple(1, 1, Path(100, 101, 102)), tuple(3, 2, Path(102, 100)), tuple(3, 3, Path(102, 100, 101)), tuple(3, 1, Path(102)))
      ))

      transitiveClosure ! ChangeSet(positive = Vector(tuple(2, 103, 4)))
      expectMsg(ChangeSet(
        positive = Vector(Vector(2, 4, Path(101, 102, 100, 103)), Vector(1, 4, Path(100, 103)), Vector(3, 4, Path(102, 100, 103)), Vector(2, 4, Path(103)))
      ))
    }
  }

  "A TransitiveClosureNode" must {
    "include paths going through same vertex more times" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureNode(echoActor ! _, src, trg, edge)))

      transitiveClosure ! ChangeSet(positive = Vector(tuple(1, 100, 2), tuple(2, 101, 1)))
      expectMsg(ChangeSet(
        positive = Vector(tuple(1, 2, Path(100)), tuple(1, 1, Path(100, 101)), tuple(2, 2, Path(101, 100)), tuple(2, 1, Path(101)))
      ))

      transitiveClosure ! ChangeSet(positive = Vector(tuple(2, 102, 1)))
      expectMsg(ChangeSet(
        positive = Vector(tuple(1, 1, Path(100, 102)), tuple(2, 1, Path(101, 100, 102)), tuple(2, 2, Path(102, 100)), tuple(2, 1, Path(102, 100, 101)), tuple(2, 1, Path(102)))
      ))

      transitiveClosure ! ChangeSet(positive = Vector(tuple(2, 103, 3)))
      expectMsg(ChangeSet(
        positive = Vector(tuple(1, 3, Path(100, 103)), tuple(2, 3, Path(102, 100, 103)), tuple(2, 3, Path(101, 100, 103)), tuple(2, 3, Path(103)))
      ))

      transitiveClosure ! ChangeSet(negative = Vector(tuple(2, 101, 1)))
      expectMsg(ChangeSet(
        negative = Vector(tuple(1, 1, Path(100, 101)), tuple(2, 2, Path(101, 100)), tuple(2, 1, Path(101)), tuple(2, 1, Path(101, 100, 102)), tuple(2, 1, Path(102, 100, 101)), tuple(2, 3, Path(101, 100, 103)))
      ))
    }
  }

}
