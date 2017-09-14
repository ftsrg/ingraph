package hu.bme.mit.ire.stateless.binary

import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.{BatchChangeSet, Primary, Secondary}
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
      val primary = BatchChangeSet(
        tupleBag(tuple(1, 2), tuple(1, 3), tuple(1, 4))
      )
      val secondary = BatchChangeSet(
        tupleBag(tuple(3, 5), tuple(3, 6), tuple(4, 7))
      )
      val primaryMask = mask(1)
      val secondaryMask = mask(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new AntiJoinNode(echoActor ! _, primaryMask, secondaryMask)))

      joiner ! Secondary(secondary)
      expectMsg(BatchChangeSet())
      joiner ! Primary(primary)
      expectMsg(BatchChangeSet(tupleBag(tuple(1, 2))))

      joiner ! Secondary(BatchChangeSet(tupleBag(tuple(2, 5), tuple(4, 7))))
      expectMsg(BatchChangeSet(tupleBag(tuple(1, 3))))
    }

    "do simple antijoins 1" in {
      val primary = BatchChangeSet(
        tupleBag(tuple(1, 2), tuple(1, 3), tuple(1, 4))
      )
      val secondary = BatchChangeSet(
        tupleBag(tuple(3, 5), tuple(3, 6), tuple(4, 7))
      )
      val primaryMask = mask(1)
      val secondaryMask = mask(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new AntiJoinNode(echoActor ! _, primaryMask, secondaryMask)))

      joiner ! Secondary(secondary)
      expectMsg(BatchChangeSet())
      joiner ! Primary(primary)
      expectMsg(BatchChangeSet(tupleBag(tuple(1, 2))))

      joiner ! Secondary(BatchChangeSet(tupleBag(tuple(2, 8), tuple(3, 9))))
      expectMsg(BatchChangeSet(tupleBag(tuple(1, 4))))
    }

    "do simple antijoins 2" in {
      val prim = BatchChangeSet(
        tupleBag(tuple(15, 16, 17, 18), tuple(4, 5, 6, 7))
      )
      val sec = BatchChangeSet(
        tupleBag(tuple(13, 15, 16))
      )
      val primaryMask = mask(0, 1)
      val secondaryMask = mask(1, 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new AntiJoinNode(echoActor ! _, primaryMask, secondaryMask)))

      joiner ! Secondary(sec)
      expectMsg(BatchChangeSet())
      joiner ! Primary(prim)
      expectMsg(BatchChangeSet(
        tupleBag(tuple(4, 5, 6, 7))
      ))
    }
    //based on https://github.com/FTSRG/incqueryd/tree/master/hu.bme.mit.incqueryd.client/hu.bme.mit.incqueryd.rete.nodes/src/test/resources/test-cases
    "do antijoin 1" in {
      val prim = BatchChangeSet(
        tupleBag(tuple(5, 6, 7), tuple(10, 11, 7))
      )
      val sec = BatchChangeSet(
        tupleBag(tuple(7, 8))
      )
      val primaryMask = mask(2)
      val secondaryMask = mask(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new AntiJoinNode(echoActor ! _, primaryMask, secondaryMask)))

      joiner ! Primary(prim)
      expectMsg(BatchChangeSet(tupleBag(tuple(5, 6, 7), tuple(10, 11, 7))))

      joiner ! Secondary(sec)
      expectMsg(BatchChangeSet())
    }
    "do antijoin 2" in {
      val prim = BatchChangeSet(
        tupleBag(tuple(1, 5), tuple(2, 6))
      )
      val sec = BatchChangeSet(
        tupleBag(tuple(5, 10))
      )
      val primaryMask = mask(1)
      val secondaryMask = mask(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new AntiJoinNode(echoActor ! _, primaryMask, secondaryMask)))

      joiner ! Primary(prim)
      expectMsg(BatchChangeSet(tupleBag(tuple(1, 5), tuple(2, 6))))

      joiner ! Secondary(sec)
      expectMsg(BatchChangeSet(tupleBag(tuple(2, 6))))
    }

    "have bag behavior" in {
      val prim = BatchChangeSet(
        tupleBag(tuple(1, 2, 3), tuple(1, 2, 3), tuple(2, 3, 4), tuple(2, 3, 4))
      )

      val primaryMask = mask(0, 1)
      val secondaryMask = mask(1, 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new AntiJoinNode(echoActor ! _, primaryMask, secondaryMask)))

      joiner ! Primary(prim)
      expectMsg(BatchChangeSet(tupleBag(tuple(1, 2, 3), tuple(1, 2, 3), tuple(2, 3, 4), tuple(2, 3, 4))))

      joiner ! Secondary(BatchChangeSet(tupleBag(tuple(0, 0, 0))))
      expectMsg(BatchChangeSet(tupleBag(tuple(1, 2, 3), tuple(1, 2, 3), tuple(2, 3, 4), tuple(2, 3, 4))))

      joiner ! Secondary(BatchChangeSet(tupleBag(tuple(0, 1, 2))))
      expectMsg(BatchChangeSet(tupleBag(tuple(2, 3, 4), tuple(2, 3, 4))))
    }
  }
}
