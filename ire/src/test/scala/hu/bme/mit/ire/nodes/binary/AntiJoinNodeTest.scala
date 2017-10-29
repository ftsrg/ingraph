package hu.bme.mit.ire.nodes.binary

import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.{IncrementalChangeSet, Primary, Secondary}
import hu.bme.mit.ire.util.Utils
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class AntiJoinNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  import hu.bme.mit.ire.util.TestUtil._

  "AntiJoin" must {
    "do simple antijoins 0" in {
      val primary = IncrementalChangeSet(
        positive = tupleBag(tuple(1, 2), tuple(1, 3), tuple(1, 4))
      )
      val secondary = IncrementalChangeSet(
        positive = tupleBag(tuple(3, 5), tuple(3, 6), tuple(4, 7))
      )
      val primaryMask = mask(1)
      val secondaryMask = mask(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new AntiJoinNode(echoActor ! _, primaryMask, secondaryMask)))

      joiner ! Secondary(secondary)
      joiner ! Primary(primary)
      expectMsg(IncrementalChangeSet(positive = tupleBag(tuple(1, 2))))

      joiner ! Secondary(IncrementalChangeSet(negative = tupleBag(tuple(3, 5), tuple(4, 7))))
      expectMsg(IncrementalChangeSet(positive = tupleBag(tuple(1, 4))))
    }

    "do simple antijoins 1" in {
      val primary = IncrementalChangeSet(
        positive = tupleBag(tuple(1, 2), tuple(1, 3), tuple(1, 4))
      )
      val secondary = IncrementalChangeSet(
        positive = tupleBag(tuple(3, 5), tuple(3, 6), tuple(4, 7))
      )
      val primaryMask = mask(1)
      val secondaryMask = mask(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new AntiJoinNode(echoActor ! _, primaryMask, secondaryMask)))

      joiner ! Secondary(secondary)
      joiner ! Primary(primary)
      expectMsg(IncrementalChangeSet(positive = tupleBag(tuple(1, 2))))

      joiner ! Secondary(IncrementalChangeSet(positive = tupleBag(tuple(2, 8), tuple(3, 9))))
      expectMsg(IncrementalChangeSet(negative = tupleBag(tuple(1, 2))))
    }

    "do simple antijoins 2" in {
      val prim = IncrementalChangeSet(
        positive = tupleBag(tuple(15, 16, 17, 18), tuple(4, 5, 6, 7))
      )
      val sec = IncrementalChangeSet(
        positive = tupleBag(tuple(13, 15, 16))
      )
      val primaryMask = mask(0, 1)
      val secondaryMask = mask(1, 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new AntiJoinNode(echoActor ! _, primaryMask, secondaryMask)))

      joiner ! Secondary(sec)

      joiner ! Primary(prim)
      expectMsg(IncrementalChangeSet(
        positive = tupleBag(tuple(4, 5, 6, 7))
      ))
    }
    //based on https://github.com/FTSRG/incqueryd/tree/master/hu.bme.mit.incqueryd.client/hu.bme.mit.incqueryd.rete.nodes/src/test/resources/test-cases
    "do antijoin 1" in {
      val prim = IncrementalChangeSet(
        positive = tupleBag(tuple(5, 6, 7), tuple(10, 11, 7))
      )
      val sec = IncrementalChangeSet(
        positive = tupleBag(tuple(7, 8))
      )
      val primaryMask = mask(2)
      val secondaryMask = mask(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new AntiJoinNode(echoActor ! _, primaryMask, secondaryMask)))

      joiner ! Primary(prim)
      expectMsg(IncrementalChangeSet(positive = tupleBag(tuple(5, 6, 7), tuple(10, 11, 7))))

      joiner ! Secondary(sec)
      expectMsgAnyOf(Utils.changeSetPermutations(
        IncrementalChangeSet(negative = tupleBag(tuple(5, 6, 7), tuple(10, 11, 7)))): _*)
    }
    "do antijoin 2" in {
      val prim = IncrementalChangeSet(
        positive = tupleBag(tuple(1, 5), tuple(2, 6))
      )
      val sec = IncrementalChangeSet(
        positive = tupleBag(tuple(5, 10))
      )
      val primaryMask = mask(1)
      val secondaryMask = mask(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new AntiJoinNode(echoActor ! _, primaryMask, secondaryMask)))

      joiner ! Primary(prim)
      expectMsgAnyOf(
        Utils.changeSetPermutations(IncrementalChangeSet(positive = tupleBag(tuple(1, 5), tuple(2, 6)))): _*
      )

      joiner ! Secondary(sec)
      expectMsg(IncrementalChangeSet(
        negative = tupleBag(tuple(1, 5))
      )
      )
    }
    "do antijoin new 1" in {
      val primaryMask = mask(1)
      val secondaryMask = mask(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new AntiJoinNode(echoActor ! _, primaryMask, secondaryMask)))

      joiner ! Primary(IncrementalChangeSet(positive = tupleBag(tuple(1, 2), tuple(3, 4))))
      expectMsg(
        IncrementalChangeSet(positive = tupleBag(tuple(1, 2), tuple(3, 4)))
      )

      joiner ! Secondary(IncrementalChangeSet(positive = tupleBag(tuple(2, 3), tuple(2, 4), tuple(4, 5))))
      expectMsgAnyOf(Utils.changeSetPermutations(
        IncrementalChangeSet(negative = tupleBag(tuple(1, 2), tuple(3, 4)))
      ): _*)

      joiner ! Secondary(IncrementalChangeSet(negative = tupleBag(tuple(4, 5))))
      expectMsg(
        IncrementalChangeSet(positive = tupleBag(tuple(3, 4)))
      )

      joiner ! Secondary(IncrementalChangeSet(negative = tupleBag(tuple(2, 3))))
      joiner ! Secondary(IncrementalChangeSet(negative = tupleBag(tuple(2, 4))))
      expectMsg(
        IncrementalChangeSet(positive = tupleBag(tuple(1, 2)))
      )

      joiner ! Secondary(IncrementalChangeSet(positive = tupleBag(tuple(3, 4))))

      joiner ! Secondary(IncrementalChangeSet(positive = tupleBag(tuple(4, 3))))
      expectMsg(IncrementalChangeSet(negative = tupleBag(tuple(3, 4))))

      joiner ! Primary(IncrementalChangeSet(positive = tupleBag(tuple(1, 4))))

      joiner ! Primary(IncrementalChangeSet(positive = tupleBag(tuple(1, 5))))
      expectMsg(IncrementalChangeSet(positive = tupleBag(tuple(1, 5))))

      joiner ! Primary(IncrementalChangeSet(negative = tupleBag(tuple(1, 5))))
      expectMsg(IncrementalChangeSet(negative = tupleBag(tuple(1, 5))))
    }
    "do antijoin new 2" in {
      val prim = IncrementalChangeSet(
        positive = tupleBag(tuple(2, 4), tuple(3, 4), tuple(5, 4), tuple(6, 4), tuple(1, 3), tuple(2, 3))
      )
      val secondary = IncrementalChangeSet(
        positive = tupleBag(tuple(4, 8), tuple(4, 9), tuple(3, 4))
      )
      val primaryMask = mask(1)
      val secondaryMask = mask(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new AntiJoinNode(echoActor ! _, primaryMask, secondaryMask)))

      joiner ! Primary(prim)
      expectMsg(IncrementalChangeSet(positive = tupleBag(tuple(2, 4), tuple(3, 4), tuple(5, 4), tuple(6, 4), tuple(1, 3), tuple(2, 3))))

      joiner ! Secondary(secondary)
      expectMsgAnyOf(
        Utils.changeSetPermutations(
          IncrementalChangeSet(negative = tupleBag(tuple(2, 4), tuple(3, 4), tuple(5, 4), tuple(6, 4), tuple(1, 3), tuple(2, 3)))
        ): _*
      )

      joiner ! Secondary(IncrementalChangeSet(negative = tupleBag(tuple(4, 7))))

      joiner ! Secondary(IncrementalChangeSet(negative = tupleBag(tuple(4, 8))))

      joiner ! Secondary(IncrementalChangeSet(negative = tupleBag(tuple(4, 9))))
      expectMsgAnyOf(Utils.changeSetPermutations(IncrementalChangeSet(positive = tupleBag(tuple(2, 4), tuple(3, 4), tuple(5, 4), tuple(6, 4)))): _*)

      joiner ! Secondary(IncrementalChangeSet(positive = tupleBag(tuple(4, 5))))
      expectMsgAnyOf(Utils.changeSetPermutations(IncrementalChangeSet(negative = tupleBag(tuple(2, 4), tuple(3, 4), tuple(5, 4), tuple(6, 4)))): _*)

      joiner ! Secondary(IncrementalChangeSet(negative = tupleBag(tuple(3, 4))))
      expectMsgAnyOf(Utils.changeSetPermutations(IncrementalChangeSet(positive = tupleBag(tuple(1, 3), tuple(2, 3)))): _*)

      joiner ! Primary(IncrementalChangeSet(positive = tupleBag(tuple(4, 3))))
      expectMsg(IncrementalChangeSet(positive = tupleBag(tuple(4, 3))))

      joiner ! Secondary(IncrementalChangeSet(positive = tupleBag(tuple(3, 5))))
      expectMsgAnyOf(Utils.changeSetPermutations(IncrementalChangeSet(negative = tupleBag(tuple(1, 3), tuple(2, 3), tuple(4, 3)))): _*)

      joiner ! Primary(IncrementalChangeSet(positive = tupleBag(tuple(7, 4))))
    }
    "do antijoin new 3" in {
      val prim = IncrementalChangeSet(
        positive = tupleBag(tuple(1, 2, 3, 4), tuple(1, 5, 6, 7), tuple(3, 2, 5, 4))
      )

      val primaryMask = mask(1, 3)
      val secondaryMask = mask(0, 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new AntiJoinNode(echoActor ! _, primaryMask, secondaryMask)))

      joiner ! Primary(prim)
      expectMsg(IncrementalChangeSet(tupleBag(tuple(1, 2, 3, 4), tuple(1, 5, 6, 7), tuple(3, 2, 5, 4))))

      joiner ! Primary(IncrementalChangeSet(positive = tupleBag(tuple(8, 2, 6, 4))))
      expectMsg(IncrementalChangeSet(positive = tupleBag(tuple(8, 2, 6, 4))))

      joiner ! Secondary(IncrementalChangeSet(positive = tupleBag(tuple(2, 5, 4, 3))))
      expectMsgAnyOf(
        Utils.changeSetPermutations(IncrementalChangeSet(negative = tupleBag(tuple(1, 2, 3, 4), tuple(3, 2, 5, 4), tuple(8, 2, 6, 4)))): _*
      )

      joiner ! Secondary(IncrementalChangeSet(
        positive = tupleBag(tuple(5, 5, 7, 3))
      ))
      expectMsg(IncrementalChangeSet(negative = tupleBag(tuple(1, 5, 6, 7))))

      joiner ! Secondary(IncrementalChangeSet(negative = tupleBag(tuple(2, 5, 4, 3))))
      expectMsgAnyOf(
        Utils.changeSetPermutations(IncrementalChangeSet(positive = tupleBag(tuple(1, 2, 3, 4), tuple(3, 2, 5, 4), tuple(8, 2, 6, 4)))): _*
      )
    }

    "have bag behavior" in {
      val prim = IncrementalChangeSet(
        positive = tupleBag(tuple(1, 2, 3, 4), tuple(1, 2, 3, 4), tuple(1, 5, 6, 7), tuple(3, 2, 5, 4))
      )

      val primaryMask = mask(1, 3)
      val secondaryMask = mask(0, 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new AntiJoinNode(echoActor ! _, primaryMask, secondaryMask)))

      joiner ! Primary(prim)
      expectMsg(IncrementalChangeSet(tupleBag(tuple(1, 2, 3, 4), tuple(1, 2, 3, 4), tuple(1, 5, 6, 7), tuple(3, 2, 5, 4))))

      joiner ! Primary(IncrementalChangeSet(positive = tupleBag(tuple(8, 2, 6, 4))))
      expectMsg(IncrementalChangeSet(positive = tupleBag(tuple(8, 2, 6, 4))))

      joiner ! Secondary(IncrementalChangeSet(positive = tupleBag(tuple(2, 5, 4, 3))))
      expectMsgAnyOf(
        Utils.changeSetPermutations(IncrementalChangeSet(
          negative = tupleBag(tuple(1, 2, 3, 4), tuple(1, 2, 3, 4), tuple(3, 2, 5, 4), tuple(8, 2, 6, 4)))): _*
      )

      joiner ! Secondary(IncrementalChangeSet(
        positive = tupleBag(tuple(5, 5, 7, 3))
      ))
      expectMsg(IncrementalChangeSet(negative = tupleBag(tuple(1, 5, 6, 7))))

      joiner ! Secondary(IncrementalChangeSet(negative = tupleBag(tuple(2, 5, 4, 3))))
      expectMsgAnyOf(
        Utils.changeSetPermutations(IncrementalChangeSet(
          positive = tupleBag(tuple(1, 2, 3, 4), tuple(1, 2, 3, 4), tuple(3, 2, 5, 4), tuple(8, 2, 6, 4)))): _*
      )
    }
  }
}
