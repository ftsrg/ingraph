package hu.bme.mit.ire.nodes.binary

import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.{IncrementalChangeSet, Primary, Secondary}
import hu.bme.mit.ire.util.TestUtil._
import hu.bme.mit.ire.util.Utils
import org.scalatest.{Matchers, WordSpecLike, _}

class LeftOuterJoinNodeTest(_system: ActorSystem)  extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "LeftOuterJoin" must {
    "left outer join the values" in {
      val primaryChangeSet = IncrementalChangeSet(
        positive = tupleBag(
          tuple(15, 16, 17, 18),
          tuple(4, 5, 6, 7)
        )
      )
      val secondaryChangeSet = IncrementalChangeSet(
        positive = tupleBag(
          tuple(0, 15, 16, 13),
          tuple(1, 15, 16, 14)
        )
      )
      val secondSecondaryChangeSet = IncrementalChangeSet(
        positive = tupleBag(tuple(20, 4, 5, 33))
      )

      val primaryTupleWidth = 4
      val secondaryTupleWidth = 4
      val primaryMask = mask(0, 1)
      val secondaryMask = mask(1, 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new LeftOuterJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      joiner ! Primary(primaryChangeSet)
      expectMsg(IncrementalChangeSet(
        positive = tupleBag(
          tuple(15, 16, 17, 18, null, null),
          tuple(4, 5, 6, 7, null, null)
        )
      ))

      joiner ! Secondary(secondaryChangeSet)
      expectMsg(IncrementalChangeSet(
        positive = tupleBag(
          tuple(15, 16, 17, 18, 0, 13),
          tuple(15, 16, 17, 18, 1, 14)
        ),
        negative = tupleBag(
          tuple(15, 16, 17, 18, null, null)
        )
      ))

      joiner ! Secondary(secondSecondaryChangeSet)
      expectMsg(IncrementalChangeSet(
        positive = tupleBag(
          tuple(4, 5, 6, 7, 20, 33)
        ),
        negative = tupleBag(
          tuple(4, 5, 6, 7, null, null)
        )
      ))
    }

    "does not produce update in case of pairless secondary update" in {
      val primaryChangeSet = IncrementalChangeSet(
        positive = tupleBag(
          tuple(15, 16, 17, 18),
          tuple(4, 5, 6, 7)
        )
      )
      val secondaryChangeSet = IncrementalChangeSet(
        positive = tupleBag(tuple(0, 18, 19, 13))
      )
      val secondaryNegativeChangeSet = IncrementalChangeSet(
        negative = tupleBag(tuple(0, 18, 19, 13))
      )

      val primaryTupleWidth = 4
      val secondaryTupleWidth = 4
      val primaryMask = mask(0, 1)
      val secondaryMask = mask(1, 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new LeftOuterJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      joiner ! Primary(primaryChangeSet)
      expectMsg(IncrementalChangeSet(
        positive = tupleBag(
          tuple(15, 16, 17, 18, null, null),
          tuple(4, 5, 6, 7, null, null)
        )
      ))

      joiner ! Secondary(secondaryChangeSet)
      expectNoMsg

      joiner ! Secondary(secondaryNegativeChangeSet)
      expectNoMsg
    }

    "do left outer join 1" in {
      val primaryTupleWidth = 3
      val secondaryTupleWidth = 2
      val primaryMask = mask(2)
      val secondaryMask = mask(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new LeftOuterJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      joiner ! Primary(IncrementalChangeSet(
        positive = tupleBag(
          tuple(5, 6, 7),
          tuple(10, 11, 7)
        )))
      expectMsgAnyOf(Utils.changeSetPermutations(IncrementalChangeSet(
        positive = tupleBag(
          tuple(5, 6, 7, null),
          tuple(10, 11, 7, null)
        )
      )): _*)

      joiner ! Secondary(IncrementalChangeSet(
        positive = tupleBag(tuple(7, 8))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(IncrementalChangeSet(
        positive = tupleBag(
          tuple(5, 6, 7, 8),
          tuple(10, 11, 7, 8)
        ),
        negative = tupleBag(
          tuple(5, 6, 7, null),
          tuple(10, 11, 7, null)
        )
      )): _*)
    }

    "primary negative updates" in {
      val primaryChangeSet = IncrementalChangeSet(
        positive = tupleBag(
          tuple(15, 16, 17, 18),
          tuple(4, 5, 6, 7)
        )
      )
      val secondaryChangeSet = IncrementalChangeSet(
        positive = tupleBag(tuple(0, 15, 16, 13))
      )
      val primaryNegativeChangeSet = IncrementalChangeSet(
        negative = tupleBag(
          tuple(15, 16, 17, 18),
          tuple(4, 5, 6, 7)
        )
      )

      val primaryTupleWidth = 4
      val secondaryTupleWidth = 4
      val primaryMask = mask(0, 1)
      val secondaryMask = mask(1, 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new LeftOuterJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      joiner ! Primary(primaryChangeSet)
      expectMsgAnyOf(Utils.changeSetPermutations(IncrementalChangeSet(
        positive = tupleBag(
          tuple(15, 16, 17, 18, null, null),
          tuple(4, 5, 6, 7, null, null)
        )
      )): _*)

      joiner ! Secondary(secondaryChangeSet)
      expectMsg(IncrementalChangeSet(
        positive = tupleBag(
          tuple(15, 16, 17, 18, 0, 13)
        ),
        negative = tupleBag(
          tuple(15, 16, 17, 18, null, null)
        )
      ))

      joiner ! Primary(primaryNegativeChangeSet)
      expectMsgAnyOf(Utils.changeSetPermutations(IncrementalChangeSet(
        negative = tupleBag(
          tuple(4, 5, 6, 7, null, null),
          tuple(15, 16, 17, 18, 0, 13)
        )
      )): _*)
    }

    "secondary negative updates" in {
      val primaryTupleWidth = 2
      val secondaryTupleWidth = 2
      val primaryMask = mask(1)
      val secondaryMask = mask(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new LeftOuterJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      joiner ! Primary(IncrementalChangeSet(
        positive = tupleBag(tuple(2, 4), tuple(3, 4))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(IncrementalChangeSet(
        positive = tupleBag(
          tuple(2, 4, null),
          tuple(3, 4, null)
        )
      )): _*)

      joiner ! Secondary(IncrementalChangeSet(
        positive = tupleBag(tuple(4, 5), tuple(4, 6))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(IncrementalChangeSet(
        positive = tupleBag(
          tuple(2, 4, 5),
          tuple(2, 4, 6),
          tuple(3, 4, 5),
          tuple(3, 4, 6)
        ),
        negative = tupleBag(
          tuple(2, 4, null),
          tuple(3, 4, null)
        )
      )): _*)

      joiner ! Secondary(IncrementalChangeSet(
        negative = tupleBag(tuple(4, 5))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(IncrementalChangeSet(
        negative = tupleBag(
          tuple(2, 4, 5),
          tuple(3, 4, 5)
        )
      )): _*)

      joiner ! Secondary(IncrementalChangeSet(
        negative = tupleBag(tuple(4, 6))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(IncrementalChangeSet(
        positive = tupleBag(
          tuple(2, 4, null),
          tuple(3, 4, null)
        ),
        negative = tupleBag(
          tuple(2, 4, 6),
          tuple(3, 4, 6)
        )
      )): _*)

      joiner ! Secondary(IncrementalChangeSet(
        positive = tupleBag(tuple(4, 7))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(IncrementalChangeSet(
        positive = tupleBag(
          tuple(2, 4, 7),
          tuple(3, 4, 7)
        ),
        negative = tupleBag(
          tuple(2, 4, null),
          tuple(3, 4, null)
        )
      )): _*)

      joiner ! Primary(IncrementalChangeSet(
        negative = tupleBag(tuple(2, 4), tuple(3, 4))
      ))
      expectMsgAnyOf(Utils.changeSetPermutations(IncrementalChangeSet(
        negative = tupleBag(
          tuple(2, 4, 7),
          tuple(3, 4, 7)
        )
      )): _*)
    }
  }

}
