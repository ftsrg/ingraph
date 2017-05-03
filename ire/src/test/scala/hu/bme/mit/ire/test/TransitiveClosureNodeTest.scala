package hu.bme.mit.ire.test

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.datatypes.Path
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.nodes.unary.TransitiveClosureNode
import hu.bme.mit.ire.util.TestUtil._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
import akka.actor.actorRef2Scala
import hu.bme.mit.ire.messages.Ternary
import hu.bme.mit.ire.messages.Primary
import hu.bme.mit.ire.messages.Secondary
import hu.bme.mit.ire.util.Utils
import scala.concurrent.duration.Duration

class TransitiveClosureNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  val src = 0
  val trg = 2
  val edge = 1
  
  val timeout = Duration(100, "millis")

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A TransitiveClosureNode" must {
    "calculate transitive closure" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureNode(echoActor ! _, src, trg, edge)))

      transitiveClosure ! Ternary(ChangeSet(
        positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(2), tuple(3))
      ))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(1, 2, Path(100)), tuple(1, 3, Path(100, 101)))
      ))

      transitiveClosure ! Secondary(ChangeSet(
        negative = tupleBag(tuple(3))
      ))
      expectMsg(ChangeSet(
        negative = tupleBag(tuple(1, 3, Path(100, 101)))
      ))

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(3))
      ))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(1, 3, Path(100, 101)))
      ))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(2))
      ))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(2, 3, Path(101)))
      ))

      transitiveClosure ! Ternary(ChangeSet(
        positive = tupleBag(tuple(1, 102, 3))
      ))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(1, 3, Path(102)))
      ))

      transitiveClosure ! Ternary(ChangeSet(
        negative = tupleBag(tuple(2, 101, 3))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        negative = tupleBag(
          tuple(1, 3, Path(100, 101)),
          tuple(2, 3, Path(101))
        )
      )): _*)
    }

    "should honor max path length bound" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureNode(echoActor ! _, src, trg, edge, max = 2)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(3), tuple(4))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Ternary(ChangeSet(
        positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(2, 3, Path(101)), tuple(1, 3, Path(100, 101)))
      )): _*)

      transitiveClosure ! Ternary(ChangeSet(
        positive = tupleBag(tuple(3, 102, 4))
      ))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(2, 4, Path(101, 102)))
      ))
    }

    "should honor min path length bound" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureNode(echoActor ! _, src, trg, edge, min = 2)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(2), tuple(3))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Ternary(ChangeSet(
        positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))
      ))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(1, 3, Path(100, 101)))
      ))

      transitiveClosure ! Ternary(ChangeSet(
        negative = tupleBag(tuple(2, 101, 3))
      ))
      expectMsg(ChangeSet(
        negative = tupleBag(tuple(1, 3, Path(100, 101)))
      ))
    }

    "should honor min and max path length bound" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureNode(echoActor ! _, src, trg, edge, min = 2, max = 2)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(3), tuple(4))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Ternary(ChangeSet(
        positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))
      ))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(1, 3, Path(100, 101)))
      ))

      transitiveClosure ! Ternary(ChangeSet(
        positive = tupleBag(tuple(3, 102, 4))
      ))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(2, 4, Path(101, 102)))
      ))

      transitiveClosure ! Ternary(ChangeSet(
        negative = tupleBag(tuple(2, 101, 3))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        negative = tupleBag(tuple(1, 3, Path(100, 101)), tuple(2, 4, Path(101, 102)))
      )): _*)
    }

    "discard paths with same edge twice" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureNode(echoActor ! _, src, trg, edge)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2), tuple(3))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2), tuple(3), tuple(4))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Ternary(ChangeSet(positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, 2, Path(100)), tuple(1, 3, Path(100, 101)), tuple(2, 3, Path(101)))
      )): _*)

      transitiveClosure ! Ternary(ChangeSet(positive = tupleBag(tuple(3, 102, 1))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(2, 2, Path(101, 102, 100)), tuple(2, 1, Path(101, 102)), tuple(1, 1, Path(100, 101, 102)), tuple(3, 2, Path(102, 100)), tuple(3, 3, Path(102, 100, 101)), tuple(3, 1, Path(102)))
      )): _*)

      transitiveClosure ! Ternary(ChangeSet(positive = tupleBag(tuple(2, 103, 4))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(2, 4, Path(101, 102, 100, 103)), tuple(1, 4, Path(100, 103)), tuple(3, 4, Path(102, 100, 103)), tuple(2, 4, Path(103)))
      )): _*)
    }

    "handle compelx test case 1" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureNode(echoActor ! _, src, trg, edge, min = 2, max = 3)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2), tuple(3))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2), tuple(3), tuple(4))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Ternary(ChangeSet(positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, 3, Path(100, 101)))
      )): _*)

      transitiveClosure ! Ternary(ChangeSet(positive = tupleBag(tuple(3, 102, 1))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(2, 2, Path(101, 102, 100)), tuple(2, 1, Path(101, 102)), tuple(1, 1, Path(100, 101, 102)), tuple(3, 2, Path(102, 100)), tuple(3, 3, Path(102, 100, 101)))
      )): _*)

      transitiveClosure ! Ternary(ChangeSet(positive = tupleBag(tuple(2, 103, 4))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, 4, Path(100, 103)), tuple(3, 4, Path(102, 100, 103)))
      )): _*)
    }

    "include paths going through same vertex more times" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureNode(echoActor ! _, src, trg, edge)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2), tuple(3))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2), tuple(3))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Ternary(ChangeSet(positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 1))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, 2, Path(100)), tuple(1, 1, Path(100, 101)), tuple(2, 2, Path(101, 100)), tuple(2, 1, Path(101)))
      )): _*)

      transitiveClosure ! Ternary(ChangeSet(positive = tupleBag(tuple(2, 102, 1))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, 1, Path(100, 102)), tuple(2, 1, Path(101, 100, 102)), tuple(2, 2, Path(102, 100)), tuple(2, 1, Path(102, 100, 101)), tuple(2, 1, Path(102)))
      )): _*)

      transitiveClosure ! Ternary(ChangeSet(positive = tupleBag(tuple(2, 103, 3))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, 3, Path(100, 103)), tuple(2, 3, Path(102, 100, 103)), tuple(2, 3, Path(101, 100, 103)), tuple(2, 3, Path(103)))
      )): _*)

      transitiveClosure ! Ternary(ChangeSet(negative = tupleBag(tuple(2, 101, 1))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        negative = tupleBag(tuple(1, 1, Path(100, 101)), tuple(2, 2, Path(101, 100)), tuple(2, 1, Path(101)), tuple(2, 1, Path(101, 100, 102)), tuple(2, 1, Path(102, 100, 101)), tuple(2, 3, Path(101, 100, 103)))
      )): _*)
    }

    "handle complex test case 2" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureNode(echoActor ! _, src, trg, edge)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(3))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Ternary(ChangeSet(positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 1))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, 1, Path(100, 101)), tuple(2, 1, Path(101)))
      )): _*)

      transitiveClosure ! Ternary(ChangeSet(positive = tupleBag(tuple(2, 102, 1))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, 1, Path(100, 102)), tuple(2, 1, Path(101, 100, 102)), tuple(2, 1, Path(102, 100, 101)), tuple(2, 1, Path(102)))
      )): _*)

      transitiveClosure ! Ternary(ChangeSet(positive = tupleBag(tuple(2, 103, 3))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, 3, Path(100, 103)), tuple(2, 3, Path(102, 100, 103)), tuple(2, 3, Path(101, 100, 103)), tuple(2, 3, Path(103)))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(2))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, 2, Path(100)), tuple(2, 2, Path(102, 100)), tuple(2, 2, Path(101, 100)))
      )): _*)

      transitiveClosure ! Ternary(ChangeSet(negative = tupleBag(tuple(2, 101, 1))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        negative = tupleBag(tuple(1, 1, Path(100, 101)), tuple(2, 2, Path(101, 100)), tuple(2, 1, Path(101)), tuple(2, 1, Path(101, 100, 102)), tuple(2, 1, Path(102, 100, 101)), tuple(2, 3, Path(101, 100, 103)))
      )): _*)
    }

    "not do anything in case of non-present vertices or edges" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureNode(echoActor ! _, src, trg, edge)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(2), tuple(3))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Ternary(ChangeSet(
        positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, 2, Path(100)), tuple(1, 3, Path(100, 101)), tuple(2, 3, Path(101)))
      )): _*)

      transitiveClosure ! Ternary(ChangeSet(negative = tupleBag(tuple(2, 1010, 3))))
      expectNoMsg(timeout)

      transitiveClosure ! Ternary(ChangeSet(negative = tupleBag(tuple(0, 100, 2))))
      expectNoMsg(timeout)
    }

    "include directed cycles" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureNode(echoActor ! _, src, trg, edge)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2), tuple(3))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2), tuple(3))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Ternary(ChangeSet(
        positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, 2, Path(100)), tuple(1, 3, Path(100, 101)), tuple(2, 3, Path(101)))
      )): _*)

      transitiveClosure ! Ternary(ChangeSet(positive = tupleBag(tuple(3, 102, 1))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(2, 2, Path(101, 102, 100)), tuple(2, 1, Path(101, 102)), tuple(1, 1, Path(100, 101, 102)), tuple(3, 2, Path(102, 100)), tuple(3, 3, Path(102, 100, 101)), tuple(3, 1, Path(102)))
      )): _*)

      transitiveClosure ! Ternary(ChangeSet(negative = tupleBag(tuple(2, 101, 3))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        negative = tupleBag(tuple(1, 1, Path(100, 101, 102)), tuple(1, 3, Path(100, 101)), tuple(2, 2, Path(101, 102, 100)), tuple(2, 1, Path(101, 102)), tuple(2, 3, Path(101)), tuple(3, 3, Path(102, 100, 101)))
      )): _*)
    }

    "handle multiple paths and edges between vertices" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureNode(echoActor ! _, src, trg, edge)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(2), tuple(3))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Ternary(ChangeSet(
        positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, 2, Path(100)), tuple(1, 3, Path(100, 101)), tuple(2, 3, Path(101)))
      )): _*)

      transitiveClosure ! Ternary(ChangeSet(positive = tupleBag(tuple(1, 102, 3), tuple(2, 103, 3))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, 3, Path(102)), tuple(1, 3, Path(100, 103)), tuple(2, 3, Path(103)))
      )): _*)
    }

    "calculate negative updates" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureNode(echoActor ! _, src, trg, edge)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(2), tuple(3))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Ternary(ChangeSet(
        positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, 2, Path(100)), tuple(1, 3, Path(100, 101)), tuple(2, 3, Path(101)))
      )): _*)

      transitiveClosure ! Ternary(ChangeSet(negative = tupleBag(tuple(2, 101, 3))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        negative = tupleBag(tuple(1, 3, Path(100, 101)), tuple(2, 3, Path(101)))
      )): _*)

      transitiveClosure ! Ternary(ChangeSet(negative = tupleBag(tuple(1, 100, 2))))
      expectMsg(ChangeSet(
        negative = tupleBag(tuple(1, 2, Path(100)))
      ))
    }
  }
}
