/**
  * Created by Maginecz on 3/21/2015.
  */

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActorRef, TestActors, TestKit}
import hu.bme.mit.ire._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class HashJoinerTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A HashJoin" must {
    "join the values" in {
      val prim = ChangeSet(
        positive = Vector(Map(0 -> 15, 1 -> 16, 2 -> 17, 3 -> 18), Map(0 -> 4, 1 -> 5, 2 -> 6, 3 -> 7))
      )
      val sec = ChangeSet(
        positive = Vector(Map(1 -> 15, 2 -> 16, 4 -> 13))
      )
      val primarySel = Vector(0, 1)
      val secondarySel = Vector(1, 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new HashJoiner(echoActor ! _, primarySel, secondarySel)), name = "testSelector")

      joiner ! Primary(prim)
      joiner ! Secondary(sec)
      expectMsg(ChangeSet(
        positive = Vector(Map(0 -> 15, 1 -> 16, 2 -> 17, 3 -> 18, 4 -> 13))
      ))
    }

    "do join 1" in {
      val primarySel = Vector(2)
      val secondarySel = Vector(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new HashJoiner(echoActor ! _,primarySel, secondarySel)))

      joiner ! Primary(ChangeSet(
        positive = Vector(Map(0 -> 5, 1 -> 6, 2 -> 7), Map(0 -> 10, 1 -> 11, 2 -> 7))
      )
      )

      joiner ! Secondary(ChangeSet(positive = Vector(Map(0 -> 7, 3 -> 8))))
      expectMsgAnyOf(utils.changeSetPermutations(ChangeSet(
        positive = Vector(Map(0 -> 10, 1 -> 11, 2 -> 7, 3 -> 8), Map(0 -> 5, 1 -> 6, 2 -> 7, 3 -> 8))
      )
      ): _*
      )
    }
    "do join 2" in {
      val primarySel = Vector(1)
      val secondarySel = Vector(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new HashJoiner(echoActor ! _, primarySel, secondarySel)))

      joiner ! Primary(ChangeSet(
        positive = Vector(Map(0 -> 1, 1 -> 5), Map(0 -> 2, 1 -> 6))
      )
      )

      joiner ! Secondary(ChangeSet(
        positive = Vector(Map(0 -> 5, 2 -> 10))
      )
      )
      expectMsg(ChangeSet(positive = Vector(Map(0 -> 1, 1 -> 5, 2 -> 10))))
    }
    "do join new 1" in {
      val primarySel = Vector(1)
      val secondarySel = Vector(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new HashJoiner(echoActor ! _, primarySel, secondarySel)))

      joiner ! Primary(ChangeSet(positive = Vector(Map(0 -> 2, 1 -> 4), Map(0 -> 3, 1 -> 4))))

      joiner ! Secondary(ChangeSet(positive = Vector(Map(0 -> 4, 2 -> 5))))
      expectMsgAnyOf(utils.changeSetPermutations(
        ChangeSet(positive = Vector(Map(0 -> 2, 1 -> 4, 2 -> 5), Map(0 -> 3, 1 -> 4, 2 -> 5)))): _*)

      joiner ! Primary(ChangeSet(negative = Vector(Map(0 -> 3, 1 -> 4))))
      expectMsg(ChangeSet(negative = Vector(Map(0 -> 3, 1 -> 4, 2 -> 5))))
      joiner ! Primary(ChangeSet(positive = Vector(Map(0 -> 3, 1 -> 4))))
      expectMsg(ChangeSet(positive = Vector(Map(0 -> 3, 1 -> 4, 2 -> 5))))
      joiner ! Secondary(ChangeSet(negative = Vector(Map(0 -> 4, 2 -> 5))))
      expectMsgAnyOf(utils.changeSetPermutations(ChangeSet(negative = Vector(Map(0 -> 2, 1 -> 4, 2 -> 5), Map(0 -> 3, 1 -> 4, 2 -> 5)))): _*)
      joiner ! Secondary(ChangeSet(positive = Vector(Map(0 -> 4, 2 -> 5))))
      expectMsgAnyOf(utils.changeSetPermutations(ChangeSet(positive = Vector(Map(0 -> 2, 1 -> 4, 2 -> 5), Map(0 -> 3, 1 -> 4, 2 -> 5)))): _*)
    }

    "do parallel joins" in {
      val primarySel = Vector(0)
      val secondarySel = Vector(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joinerA = system.actorOf(Props(new HashJoiner(echoActor ! _, primarySel, secondarySel)))
      val joinerB = system.actorOf(Props(new HashJoiner(echoActor ! _, primarySel, secondarySel)))
      val forward: Vector[(ReteMessage) => Unit] = Vector(joinerA ! Primary(_), joinerB ! Primary(_))
      val forkingJoiner = system.actorOf(Props(new ParallelHashJoiner(forward, primarySel, secondarySel,
        hashFunction = (n: TupleType) => n(0).hashCode())))
      forkingJoiner ! Secondary(ChangeSet(positive = Vector(Map(0 -> 0, 2 -> 2))))
      forkingJoiner ! Secondary(ChangeSet(positive = Vector(Map(0 -> 1, 2 -> 3))))

      forkingJoiner ! Primary(ChangeSet(positive = Vector(Map(0 -> 0, 1 -> 2))))
      forkingJoiner ! Primary(ChangeSet(positive = Vector(Map(0 -> 1, 1 -> 2))))

      joinerB ! Secondary(ChangeSet(positive = Vector(Map(0 -> 1, 3 -> 3))))
      expectMsg(ChangeSet(positive = Vector(Map(0 -> 1, 1 -> 2, 2 -> 3, 3 -> 3))))
      joinerA ! Secondary(ChangeSet(positive = Vector(Map(0 -> 0, 3 -> 3))))
      expectMsg(ChangeSet(positive = Vector(Map(0 -> 0, 1 -> 2, 2 -> 2, 3 -> 3))))
    }
  }
}
