package hu.bme.mit.ire.nodes.binary

import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.{ChangeSet, Primary, Secondary}
import hu.bme.mit.ire.util.TestUtil._
import hu.bme.mit.ire.util.{TestUtil, Utils}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class JoinNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "Join" must {
    "join the values" in {
      val primaryChangeSet = ChangeSet(
        positive = tupleBag(
          tuple(15, 16, 17, 18),
          tuple(4, 5, 6, 7)
        )
      )
      val secondaryChangeSet = ChangeSet(
        positive = tupleBag(tuple(0, 15, 16, 13))
      )
      val primaryTupleWidth = 4
      val secondaryTupleWidth = 4
      val primaryMask = mask(0, 1)
      val secondaryMask = mask(1, 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new JoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      joiner ! Primary(primaryChangeSet)
      joiner ! Secondary(secondaryChangeSet)
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(15, 16, 17, 18, 0, 13))
      ))
    }

    "do join 1" in {
      val primaryTupleWidth = 3
      val secondaryTupleWidth = 2
      val primaryMask = mask(2)
      val secondaryMask = mask(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new JoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      joiner ! Primary(ChangeSet(
        positive = tupleBag(
          tuple(5, 6, 7),
          tuple(10, 11, 7)
        )
      )
      )

      joiner ! Secondary(ChangeSet(positive = tupleBag(tuple(7, 8))))
      expectMsgAnyOf(TestUtil.changeSetPermutations(ChangeSet(
        positive = tupleBag(
          tuple(5, 6, 7, 8),
          tuple(10, 11, 7, 8)
        )
      )
      ): _*
      )
    }
    "do join 2" in {
      val primaryTupleWidth = 2
      val secondaryTupleWidth = 2
      val primaryMask = mask(1)
      val secondaryMask = mask(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new JoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      joiner ! Primary(ChangeSet(
        positive = tupleBag(
          tuple(1, 5),
          tuple(2, 6)
        )
      )
      )

      joiner ! Secondary(ChangeSet(
        positive = tupleBag(tuple(5, 10))
      )
      )
      expectMsg(ChangeSet(positive = tupleBag(tuple(1, 5, 10))))
    }
    "do join new 1" in {
      val primaryTupleWidth = 2
      val secondaryTupleWidth = 2
      val primaryMask = mask(1)
      val secondaryMask = mask(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new JoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      joiner ! Primary(ChangeSet(positive = tupleBag(tuple(2, 4), tuple(3, 4))))

      joiner ! Secondary(ChangeSet(positive = tupleBag(tuple(4, 5))))

      expectMsgAnyOf(TestUtil.changeSetPermutations(ChangeSet(positive = tupleBag(tuple(2, 4, 5), tuple(3, 4, 5)))): _*)

      joiner ! Primary(ChangeSet(negative = tupleBag(tuple(3, 4))))
      expectMsg(ChangeSet(negative = tupleBag(tuple(3, 4, 5))))

      joiner ! Primary(ChangeSet(positive = tupleBag(tuple(3, 4))))
      expectMsg(ChangeSet(positive = tupleBag(tuple(3, 4, 5))))

      joiner ! Secondary(ChangeSet(negative = tupleBag(tuple(4, 5))))
      expectMsgAnyOf(TestUtil.changeSetPermutations(ChangeSet(negative = tupleBag(tuple(2, 4, 5), tuple(3, 4, 5)))): _*)

      joiner ! Secondary(ChangeSet(positive = tupleBag(tuple(4, 5))))
      expectMsgAnyOf(TestUtil.changeSetPermutations(ChangeSet(positive = tupleBag(tuple(2, 4, 5), tuple(3, 4, 5)))): _*)
    }

    "have bag behavior" in {
      val primaryTupleWidth = 2
      val secondaryTupleWidth = 2
      val primaryMask = mask(1)
      val secondaryMask = mask(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new JoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      joiner ! Primary(ChangeSet(positive = tupleBag(tuple(2, 4), tuple(2, 4), tuple(3, 4))))

      joiner ! Secondary(ChangeSet(positive = tupleBag(tuple(4, 5))))

      expectMsgAnyOf(TestUtil.changeSetPermutations(ChangeSet(positive = tupleBag(
        tuple(2, 4, 5), tuple(2, 4, 5), tuple(3, 4, 5)))): _*)

      joiner ! Primary(ChangeSet(negative = tupleBag(tuple(2, 4))))
      expectMsg(ChangeSet(negative = tupleBag(tuple(2, 4, 5))))

      joiner ! Primary(ChangeSet(negative = tupleBag(tuple(2, 4))))
      expectMsg(ChangeSet(negative = tupleBag(tuple(2, 4, 5))))

      joiner ! Primary(ChangeSet(positive = tupleBag(tuple(2, 4))))
      expectMsg(ChangeSet(positive = tupleBag(tuple(2, 4, 5))))
    }
  }
}
