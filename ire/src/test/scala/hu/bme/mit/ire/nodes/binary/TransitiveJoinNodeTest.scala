package hu.bme.mit.ire.nodes.binary

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.datatypes.Path
import hu.bme.mit.ire.messages.{ChangeSet, Primary, Secondary}
import hu.bme.mit.ire.util.TestUtil._
import hu.bme.mit.ire.util.Utils
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import scala.concurrent.duration.Duration

class TransitiveJoinNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  val timeout = Duration(100, "millis")

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  val primaryTupleWidth = 1
  val secondaryTupleWidth = 3
  val outputTupleWidth = 3
  val primaryMask = mask(0)
  val secondaryMask = mask(0)

  "A TransitiveClosureNode" must {
    "calculate transitive closure" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)

      val transitiveClosure = system.actorOf(Props(new TransitiveJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask, outputTupleWidth, minHops = 0)))

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, Path(), 1), tuple(1, Path(100), 2), tuple(1, Path(100, 101), 3))
      )): _*)

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(2))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(2, Path(), 2), tuple(2, Path(101), 3))
      )): _*)

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

    "calculate transitive closure the other way round" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)

      val transitiveClosure = system.actorOf(Props(new TransitiveJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask, outputTupleWidth, minHops = 0)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, Path(), 1))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, Path(100), 2), tuple(1, Path(100, 101), 3))
      )): _*)
    }

    "honor max path length bound" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask, outputTupleWidth, maxHops = 2)))

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
      val transitiveClosure = system.actorOf(Props(new TransitiveJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask, outputTupleWidth, minHops = 2)))

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
      val transitiveClosure = system.actorOf(Props(new TransitiveJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask, outputTupleWidth, minHops = 2, maxHops = 2)))

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
      val transitiveClosure = system.actorOf(Props(new TransitiveJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask, outputTupleWidth)))

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
      val transitiveClosure = system.actorOf(Props(new TransitiveJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask, outputTupleWidth, minHops = 2, maxHops = 3)))

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
      val transitiveClosure = system.actorOf(Props(new TransitiveJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask, outputTupleWidth)))

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
      val transitiveClosure = system.actorOf(Props(new TransitiveJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask, outputTupleWidth, minHops = 0)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1), tuple(2))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, Path(), 1), tuple(2, Path(), 2))
      )): _*)

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
        negative = tupleBag(tuple(1, Path(), 1), tuple(1, Path(100, 103), 3), tuple(1, Path(100, 102), 1), tuple(1, Path(100), 2))
      )): _*)
    }

    "not do anything in case of non-present vertices or edges" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask, outputTupleWidth)))

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
      val transitiveClosure = system.actorOf(Props(new TransitiveJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask, outputTupleWidth)))

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
      val transitiveClosure = system.actorOf(Props(new TransitiveJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask, outputTupleWidth)))

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
      val transitiveClosure = system.actorOf(Props(new TransitiveJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask, outputTupleWidth)))

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

    "handle multiple target paths from same target vertex" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask, outputTupleWidth)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(36))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(36, 7, 11), tuple(17, 3, 38))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(36, Path(7), 11))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(positive = tupleBag(tuple(17, 2, 21), tuple(21, 4, 38))))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(positive = tupleBag(tuple(11, 1, 17))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(36, Path(7, 1, 3), 38), tuple(36, Path(7, 1, 2, 4), 38), tuple(36, Path(7, 1, 2), 21), tuple(36, Path(7, 1), 17))
      )): _*)
    }

    "handle multiple source paths from same source vertex" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask,outputTupleWidth)))

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(36), tuple(37))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(36, 7, 11), tuple(36, 6, 37))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(36, Path(7), 11), tuple(36, Path(6), 37))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(37, 3, 11))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(36, Path(6, 3), 11), tuple(37, Path(3), 11))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(positive = tupleBag(tuple(11, 1, 21))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(36, Path(6, 3, 1), 21), tuple(36, Path(7, 1), 21), tuple(37, Path(3, 1), 21))
      )): _*)
    }

    "calculate transitive closure with backward edges" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)

      val transitiveClosure = system.actorOf(Props(new TransitiveJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, mask(2), outputTupleWidth, minHops = 0)))

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(2, 100, 1), tuple(3, 101, 2))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, Path(), 1), tuple(1, Path(100), 2), tuple(1, Path(100, 101), 3))
      )): _*)

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(2))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(2, Path(), 2), tuple(2, Path(101), 3))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(3, 102, 1))
      ))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(1, Path(102), 3))
      ))

      transitiveClosure ! Secondary(ChangeSet(
        negative = tupleBag(tuple(3, 101, 2))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        negative = tupleBag(
          tuple(1, Path(100, 101), 3),
          tuple(2, Path(101), 3)
        )
      )): _*)
    }

    "calculate transitive closure with extra attributes" ignore {
      val echoActor = system.actorOf(TestActors.echoActorProps)

      val transitiveClosure = system.actorOf(Props(new TransitiveJoinNode(echoActor ! _, primaryTupleWidth = 2, secondaryTupleWidth = 4, primaryMask, secondaryMask, outputTupleWidth = 5, minHops = 0)))

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(1, 100, 2, "abc"), tuple(2, 101, 3, "def"))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1, 1001))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, 1001, Path(), 1, 1001), tuple(1, 1001, Path(100), 2, "abc"), tuple(1, 1001, Path(100, 101), 3, "def"))
      )): _*)

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(2, 1002))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(2, 1002, Path(), 2, 1002), tuple(2, 1002, Path(101), 3, "def"))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(1, 102, 3, "def"))
      ))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(1, 1001, Path(102), 3, "def"))
      ))

      transitiveClosure ! Secondary(ChangeSet(
        negative = tupleBag(tuple(2, 101, 3, "def"))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        negative = tupleBag(
          tuple(1, 1001, Path(100, 101), 3, "def"),
          tuple(2, 1002, Path(101), 3, "def")
        )
      )): _*)
    }

    "calculate transitive closure with extra attributes and backward edges" ignore {
      val echoActor = system.actorOf(TestActors.echoActorProps)

      val transitiveClosure = system.actorOf(Props(new TransitiveJoinNode(echoActor ! _, primaryTupleWidth = 2, secondaryTupleWidth = 4, primaryMask, mask(2), outputTupleWidth = 5, minHops = 0)))

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(2, 100, 1, "abc"), tuple(3, 101, 2, "def"))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1, 1001))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, 1001, Path(), 1, 1001), tuple(1, 1001, Path(100), 2, "abc"), tuple(1, 1001, Path(100, 101), 3, "def"))
      )): _*)

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(2, 1002))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(2, 1002, Path(), 2, 1002), tuple(2, 1002, Path(101), 3, "def"))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(3, 102, 1, "def"))
      ))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(1, 1001, Path(102), 3, "def"))
      ))

      transitiveClosure ! Secondary(ChangeSet(
        negative = tupleBag(tuple(3, 101, 2, "def"))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        negative = tupleBag(
          tuple(1, 1001, Path(100, 101), 3, "def"),
          tuple(2, 1002, Path(101), 3, "def")
        )
      )): _*)
    }

    "calculate transitive closure with extra attributes multiple target tuple" ignore {
      val echoActor = system.actorOf(TestActors.echoActorProps)

      val transitiveClosure = system.actorOf(Props(new TransitiveJoinNode(echoActor ! _, primaryTupleWidth = 2, secondaryTupleWidth = 4, primaryMask, secondaryMask, outputTupleWidth = 5, minHops = 0)))

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(1, 100, 2, "abc"), tuple(2, 101, 3, "def"))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1, 1001))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, 1001, Path(), 1, 1001), tuple(1, 1001, Path(100), 2, "abc"), tuple(1, 1001, Path(100, 101), 3, "def"))
      )): _*)

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(2, 1002))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(2, 1002, Path(), 2, 1002), tuple(2, 1002, Path(101), 3, "def"))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(1, 102, 3, "wow"))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(
          tuple(1, 1001, Path(102), 3, "wow"),
          tuple(1, 1001, Path(102), 3, "def"),
          tuple(2, 1002, Path(101), 3, "wow"),
          tuple(1, 1001, Path(100, 101), 3, "wow")
        )
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(
        negative = tupleBag(tuple(2, 101, 3, "def"))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        negative = tupleBag(
          tuple(1, 1001, Path(100, 101), 3, "def"),
          tuple(1, 1001, Path(100, 101), 3, "wow"),
          tuple(2, 1002, Path(101), 3, "def"),
          tuple(2, 1002, Path(101), 3, "wow")
        )
      )): _*)
    }

    "handle repeating complex tuple target" ignore {
      val echoActor = system.actorOf(TestActors.echoActorProps)

      val transitiveClosure = system.actorOf(Props(new TransitiveJoinNode(echoActor ! _, primaryTupleWidth = 2, secondaryTupleWidth = 4, primaryMask, secondaryMask, outputTupleWidth = 5, minHops = 0)))

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(1, 100, 2, "abc"), tuple(2, 101, 3, "def"))
      ))
      expectNoMsg(timeout)

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(1, 1001))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(1, 1001, Path(), 1, 1001), tuple(1, 1001, Path(100), 2, "abc"), tuple(1, 1001, Path(100, 101), 3, "def"))
      )): _*)

      transitiveClosure ! Primary(ChangeSet(
        positive = tupleBag(tuple(2, 1002))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(tuple(2, 1002, Path(), 2, 1002), tuple(2, 1002, Path(101), 3, "def"))
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(
        positive = tupleBag(tuple(1, 102, 3, "def"))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = tupleBag(
          tuple(1, 1001, Path(102), 3, "def")
        )
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(
        negative = tupleBag(tuple(2, 101, 3, "def"))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        negative = tupleBag(
          tuple(1, 1001, Path(100, 101), 3, "def"),
          tuple(2, 1002, Path(101), 3, "def")
        )
      )): _*)

      transitiveClosure ! Secondary(ChangeSet(
        negative = tupleBag(tuple(1, 102, 3, "def"))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        negative = tupleBag(
          tuple(1, 1001, Path(102), 3, "def")
        )
      )): _*)
    }
  }
}
