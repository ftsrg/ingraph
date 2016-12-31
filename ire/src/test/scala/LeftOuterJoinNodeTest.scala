import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.{ChangeSet, Primary, Secondary}
import hu.bme.mit.ire.nodes.binary.LeftOuterJoinNode
import hu.bme.mit.ire.util.TestUtil._
import hu.bme.mit.ire.util.Utils
import org.scalatest.{BeforeAndAfterAll, Ignore, Matchers, WordSpecLike}

@Ignore class LeftOuterJoinNodeTest(_system: ActorSystem)  extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "LeftOuterJoin" must {
    "left outer join the values" in {
      val primaryChangeSet = ChangeSet(
        positive = Vector(
          tuple(15, 16, 17, 18),
          tuple(4, 5, 6, 7)
        )
      )
      val secondaryChangeSet = ChangeSet(
        positive = Vector(tuple(0, 15, 16, 13))
      )
      val primaryTupleWidth = 4
      val secondaryTupleWidth = 4
      val primaryMask = mask(0, 1)
      val secondaryMask = mask(1, 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new LeftOuterJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      joiner ! Primary(primaryChangeSet)
      joiner ! Secondary(secondaryChangeSet)
      expectMsg(ChangeSet(
        positive = Vector(
          tuple(15, 16, 17, 18, 0, 13),
          tuple(4, 5, 6, 7, null, null)
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

      joiner ! Primary(ChangeSet(
        positive = Vector(
          tuple(5, 6, 7),
          tuple(10, 11, 7)
        )
      )
      )

      joiner ! Secondary(ChangeSet(positive = Vector(tuple(7, 8))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(
        positive = Vector(
          tuple(5, 6, 7, 8),
          tuple(10, 11, 7, 8)
        )
      )
      ): _*
      )
    }
    "do left outer join 2" in {
      val primaryTupleWidth = 2
      val secondaryTupleWidth = 2
      val primaryMask = mask(1)
      val secondaryMask = mask(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new LeftOuterJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      joiner ! Primary(ChangeSet(
        positive = Vector(
          tuple(1, 5),
          tuple(2, 6)
        )
      )
      )

      joiner ! Secondary(ChangeSet(
        positive = Vector(tuple(5, 10))
      )
      )
      expectMsg(ChangeSet(positive = Vector(
        tuple(1, 5, 10),
        tuple(1, 5, null)
      )))
    }
    "do left outer join new 1" in {
      val primaryTupleWidth = 2
      val secondaryTupleWidth = 2
      val primaryMask = mask(1)
      val secondaryMask = mask(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new LeftOuterJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      joiner ! Primary(ChangeSet(positive = Vector(
        tuple(2, 4),
        tuple(3, 4)
      )))

      joiner ! Secondary(ChangeSet(positive = Vector(tuple(4, 5))))

      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(positive = Vector(
        tuple(2, 4, 5),
        tuple(3, 4, 5)
      ))): _*)

      joiner ! Primary(ChangeSet(negative = Vector(tuple(3, 4))))
      expectMsg(ChangeSet(negative = Vector(tuple(3, 4, 5))))

      joiner ! Primary(ChangeSet(positive = Vector(tuple(3, 4))))
      expectMsg(ChangeSet(positive = Vector(tuple(3, 4, 5))))

      joiner ! Secondary(ChangeSet(negative = Vector(tuple(4, 5))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(negative = Vector(
        tuple(2, 4, 5),
        tuple(3, 4, 5)
      ))): _*)

      joiner ! Secondary(ChangeSet(positive = Vector(tuple(4, 5))))
      expectMsgAnyOf(Utils.changeSetPermutations(ChangeSet(positive = Vector(
        tuple(2, 4, 5), tuple(3, 4, 5)
      ))): _*)
    }
  }

}
