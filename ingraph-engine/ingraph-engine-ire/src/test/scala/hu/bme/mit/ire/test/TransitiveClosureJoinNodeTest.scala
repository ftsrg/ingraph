package hu.bme.mit.ire.test

import org.scalatest.BeforeAndAfterAll
import org.scalatest.Matchers
import org.scalatest.WordSpecLike
import akka.testkit.ImplicitSender
import akka.actor.ActorSystem
import akka.testkit.TestKit
import scala.concurrent.duration.Duration
import hu.bme.mit.ire.nodes.binary.TransitiveClosureJoinNode
import akka.actor.Props
import akka.testkit.TestActors
import hu.bme.mit.ire.messages.Secondary
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.util.TestUtil._
import hu.bme.mit.ire.messages.Primary
import hu.bme.mit.ire.datatypes.Path
import hu.bme.mit.ire.util.Utils

class TransitiveClosureJoinNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {
  
  val timeout = Duration(100, "millis")

  def this() = this(ActorSystem("MySpec"))
  
  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  val primaryTupleWidth = 3
  val secondaryTupleWidth = 3
  val primaryMask = mask(2)
  val secondaryMask = mask(0)

  
  "A TransitiveClosureNode" must {
    "calculate transitive closure" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)

      val transitiveClosure = system.actorOf(Props(new TransitiveClosureJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1))
      ))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(1, Path(100), 2), tuple(1, Path(100, 101), 3))
      ))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(2))
      ))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(2, Path(101), 3))
      ))

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(1, 102, 3))
      ))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(1, Path(102), 3))
      ))

      transitiveClosure ! Secondary(ChangeSet(
        negative = tupleBag(tuple(2, 101, 3))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        negative = tupleBag(
          tuple(1, Path(100, 101), 3),
          tuple(2, Path(101), 3)
        )
      )): _*)
    }

    "honor max path length bound" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask, maxHops = 2)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, Path(100), 2), tuple(2, Path(101), 3), tuple(1, Path(100, 101), 3))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(3, 102, 4))
      ))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(2, Path(101, 102), 4))
      ))
    }

    "honor min path length bound" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask, minHops = 2)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))
      ))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(1, Path(100, 101), 3))
      ))

      transitiveClosure ! Secondary(ChangeSet(
        negative = tupleBag(tuple(2, 101, 3))
      ))
      expectMsg(ChangeSet(
        negative = tupleBag(tuple(1, Path(100, 101), 3))
      ))
    }

    "honor min and max path length bound" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask, minHops = 2, maxHops = 2)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))
      ))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(1, Path(100, 101), 3))
      ))

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(3, 102, 4))
      ))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(2, Path(101, 102), 4))
      ))

      transitiveClosure ! Secondary(ChangeSet(
        negative = tupleBag(tuple(2, 101, 3))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        negative = tupleBag(tuple(1, Path(100, 101), 3), tuple(2, Path(101, 102), 4))
      )): _*)
    }

    "discard paths with same edge twice" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2), tuple(3))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, Path(100), 2), tuple(1, Path(100, 101), 3), tuple(2, Path(101), 3))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(positive = tupleBag(tuple(3, 102, 1))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(2, Path(101, 102, 100), 2), tuple(2, Path(101, 102), 1), tuple(1, Path(100, 101, 102), 1), tuple(3, Path(102, 100), 2), tuple(3, Path(102, 100, 101), 3), tuple(3, Path(102), 1))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(positive = tupleBag(tuple(2, 103, 4))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(2, Path(101, 102, 100, 103), 4), tuple(1, Path(100, 103), 4), tuple(3, Path(102, 100, 103), 4), tuple(2, Path(103), 4))
      )): _*)
    }

    "handle complex test case 1" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask, minHops = 2, maxHops = 3)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2), tuple(3))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, Path(100, 101), 3))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(positive = tupleBag(tuple(3, 102, 1))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(2, Path(101, 102, 100), 2), tuple(2, Path(101, 102), 1), tuple(1, Path(100, 101, 102), 1), tuple(3, Path(102, 100), 2), tuple(3, Path(102, 100, 101), 3))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(positive = tupleBag(tuple(2, 103, 4))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, Path(100, 103), 4), tuple(3, Path(102, 100, 103), 4))
      )): _*)
      
      transitiveClosure ! Primary(ChangeSet(
        negative = tupleBag(tuple(3))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        negative = tupleBag(tuple(3, Path(102, 100), 2), tuple(3, Path(102, 100, 103), 4), tuple(3, Path(102, 100, 101), 3))
      )): _*)

      transitiveClosure ! Primary(ChangeSet(
        negative = tupleBag(tuple(2))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        negative = tupleBag(tuple(2, Path(101, 102, 100), 2), tuple(2, Path(101, 102), 1))
      )): _*)
      
      transitiveClosure ! Primary(ChangeSet(
        negative = tupleBag(tuple(1))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        negative = tupleBag(tuple(1, Path(100, 101, 102), 1), tuple(1, Path(100, 103), 4), tuple(1, Path(100, 101), 3))
      )): _*)
    }

    "include paths going through same vertex more times" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2), tuple(3))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 1))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, Path(100), 2), tuple(1, Path(100, 101), 1), tuple(2, Path(101, 100), 2), tuple(2, Path(101), 1))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(positive = tupleBag(tuple(2, 102, 1))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, Path(100, 102), 1), tuple(2, Path(101, 100, 102), 1), tuple(2, Path(102, 100), 2), tuple(2, Path(102, 100, 101), 1), tuple(2, Path(102), 1))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(positive = tupleBag(tuple(2, 103, 3))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, Path(100, 103), 3), tuple(2, Path(102, 100, 103), 3), tuple(2, Path(101, 100, 103), 3), tuple(2, Path(103), 3))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(negative = tupleBag(tuple(2, 101, 1))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        negative = tupleBag(tuple(1, Path(100, 101), 1), tuple(2, Path(101, 100), 2), tuple(2, Path(101), 1), tuple(2, Path(101, 100, 102), 1), tuple(2, Path(102, 100, 101), 1), tuple(2, Path(101, 100, 103), 3))
      )): _*)
    }

    "handle complex test case 2" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 1))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, Path(100), 2), tuple(1, Path(100, 101), 1), tuple(2, Path(101), 1), tuple(2, Path(101, 100), 2))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(positive = tupleBag(tuple(2, 102, 1))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, Path(100, 102), 1), tuple(2, Path(101, 100, 102), 1), tuple(2, Path(102, 100, 101), 1), tuple(2, Path(102), 1), tuple(2, Path(102, 100), 2))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(positive = tupleBag(tuple(2, 103, 3))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, Path(100, 103), 3), tuple(2, Path(102, 100, 103), 3), tuple(2, Path(101, 100, 103), 3), tuple(2, Path(103), 3))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(negative = tupleBag(tuple(2, 101, 1))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        negative = tupleBag(tuple(1, Path(100, 101), 1), tuple(2, Path(101, 100), 2), tuple(2, Path(101), 1), tuple(2, Path(101, 100, 102), 1), tuple(2, Path(102, 100, 101), 1), tuple(2, Path(101, 100, 103), 3))
      )): _*)
      
      transitiveClosure ! Primary(ChangeSet(
        negative = tupleBag(tuple(1))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        negative = tupleBag(tuple(1, Path(100, 103), 3), tuple(1, Path(100, 102), 1), tuple(1, Path(100), 2))
      )): _*)
    }

    "not do anything in case of non-present vertices or edges" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, Path(100), 2), tuple(1, Path(100, 101), 3), tuple(2, Path(101), 3))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(negative = tupleBag(tuple(2, 1010, 3))))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(negative = tupleBag(tuple(0, 100, 2))))
      expectNoMsg(timeout)
    }

    "include directed cycles" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2), tuple(3))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, Path(100), 2), tuple(1, Path(100, 101), 3), tuple(2, Path(101), 3))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(positive = tupleBag(tuple(3, 102, 1))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(2, Path(101, 102, 100), 2), tuple(2, Path(101, 102), 1), tuple(1, Path(100, 101, 102), 1), tuple(3, Path(102, 100), 2), tuple(3, Path(102, 100, 101), 3), tuple(3, Path(102), 1))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(negative = tupleBag(tuple(2, 101, 3))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        negative = tupleBag(tuple(1, Path(100, 101, 102), 1), tuple(1, Path(100, 101), 3), tuple(2, Path(101, 102, 100), 2), tuple(2, Path(101, 102), 1), tuple(2, Path(101), 3), tuple(3, Path(102, 100, 101), 3))
      )): _*)
    }

    "handle multiple paths and edges between vertices" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, Path(100), 2), tuple(1, Path(100, 101), 3), tuple(2, Path(101), 3))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(positive = tupleBag(tuple(1, 102, 3), tuple(2, 103, 3))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, Path(102), 3), tuple(1, Path(100, 103), 3), tuple(2, Path(103), 3))
      )): _*)
    }

    "calculate negative updates" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, Path(100), 2), tuple(1, Path(100, 101), 3), tuple(2, Path(101), 3))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(negative = tupleBag(tuple(2, 101, 3))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        negative = tupleBag(tuple(1, Path(100, 101), 3), tuple(2, Path(101), 3))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(negative = tupleBag(tuple(1, 100, 2))))
      expectMsg(ChangeSet(
        negative = tupleBag(tuple(1, Path(100), 2))
      ))
    }
  }
}