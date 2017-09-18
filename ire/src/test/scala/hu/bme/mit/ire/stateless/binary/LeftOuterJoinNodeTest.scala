package hu.bme.mit.ire.stateless.binary

import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.{BatchChangeSet, IncrementalChangeSet, Primary, Secondary}
import hu.bme.mit.ire.util.TestUtil.{tuple, _}
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
      val primaryTupleWidth = 4
      val secondaryTupleWidth = 4
      val primaryMask = mask(0, 1)
      val secondaryMask = mask(1, 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new LeftOuterJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      joiner ! Primary(BatchChangeSet(
        tupleBag(
          //    XX  XX
          tuple(15, 16, 17, 18),
          tuple(4, 5, 6, 7)
        )
      ))
      expectMsg(BatchChangeSet(
        tupleBag(
          tuple(15, 16, 17, 18, null, null),
          tuple(4, 5, 6, 7, null, null)
        )
      ))

      joiner ! Secondary(BatchChangeSet(
        tupleBag(
          //       XX  XX
          tuple(0, 15, 16, 13),
          tuple(1, 15, 16, 14)
        )
      ))
      expectMsg(BatchChangeSet(
        tupleBag(
          tuple(15, 16, 17, 18, 0, 13),
          tuple(15, 16, 17, 18, 1, 14),
          tuple(4, 5, 6, 7, null, null)
        )
      ))

      joiner ! Secondary(BatchChangeSet(
        tupleBag(
          tuple(20, 4, 5, 33)
        )
      ))
      expectMsg(BatchChangeSet(
        tupleBag(
          tuple(15, 16, 17, 18, null, null),
          tuple(4, 5, 6, 7, 20, 33)
        )
      ))
    }

    "do left outer join 1" in {
      val primaryTupleWidth = 3
      val secondaryTupleWidth = 2
      val primaryMask = mask(2)
      val secondaryMask = mask(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new LeftOuterJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      joiner ! Primary(BatchChangeSet(
        tupleBag(
          tuple(5, 6, 7),
          tuple(10, 11, 7)
        )
      ))
      expectMsg(BatchChangeSet(
        tupleBag(
          tuple(5, 6, 7, null),
          tuple(10, 11, 7, null)
        )
      ))

      joiner ! Secondary(BatchChangeSet(
        tupleBag(tuple(7, 8))
      ))
      expectMsg(BatchChangeSet(
        tupleBag(
          tuple(5, 6, 7, 8),
          tuple(10, 11, 7, 8)
        )
      ))
    }
  }

}
