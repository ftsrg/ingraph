import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class HashAntijoinerTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  def t(elements: Int*): Map[Any, Any] = {
    val map = scala.collection.mutable.HashMap.empty[Int, Int]
    for (i <- 0 to elements.length - 1) {
      map += (i -> elements(i))
    }
    return map.toMap
  }

  "HashJoiner" must {
    "do simple antijoins 0" in {
      val prim = ChangeSet(
        positive = Vector(t(1, 2), t(1, 3), t(1, 4))
      )
      val sec = ChangeSet(
        positive = Vector(t(3, 5), t(3, 6), t(4, 7))
      )
      val primarySel = Vector(1)
      val secondarySel = Vector(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new HashAntijoiner(echoActor ! _, primarySel, secondarySel)))

      joiner ! Secondary(sec)
      joiner ! Primary(prim)
      expectMsg(ChangeSet(positive = Vector(t(1, 2))))

      joiner ! Secondary(ChangeSet(negative = Vector(t(3, 5), t(4, 7))))
      expectMsg(ChangeSet(positive = Vector(t(1, 4))))
    }

    "do simple antijoins 1" in {
      val prim = ChangeSet(
        positive = Vector(t(1, 2), t(1, 3), t(1, 4))
      )
      val sec = ChangeSet(
        positive = Vector(t(3, 5), t(3, 6), t(4, 7))
      )
      val primarySel = Vector(1)
      val secondarySel = Vector(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new HashAntijoiner(echoActor ! _, primarySel, secondarySel)))

      joiner ! Secondary(sec)
      joiner ! Primary(prim)
      expectMsg(ChangeSet(positive = Vector(t(1, 2))))

      joiner ! Secondary(ChangeSet(positive = Vector(t(2, 8), t(3, 9))))
      expectMsg(ChangeSet(negative = Vector(t(1, 2))))
    }

    "do simple antijoins 2" in {
      val prim = ChangeSet(
        positive = Vector(t(15, 16, 17, 18), t(4, 5, 6, 7))
      )
      val sec = ChangeSet(
        positive = Vector(t(13, 15, 16))
      )
      val primarySel = Vector(0, 1)
      val secondarySel = Vector(1, 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new HashAntijoiner(echoActor ! _, primarySel, secondarySel)))

      joiner ! Secondary(sec)

      joiner ! Primary(prim)
      expectMsg(ChangeSet(
        positive = Vector(t(4, 5, 6, 7))
      ))
    }
    //based on https://github.com/FTSRG/incqueryd/tree/master/hu.bme.mit.incqueryd.client/hu.bme.mit.incqueryd.rete.nodes/src/test/resources/test-cases
    "do antijoin 1" in {
      val prim = ChangeSet(
        positive = Vector(t(5, 6, 7), t(10, 11, 7))
      )
      val sec = ChangeSet(
        positive = Vector(t(7, 8))
      )
      val primarySel = Vector(2)
      val secondarySel = Vector(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new HashAntijoiner(echoActor ! _, primarySel, secondarySel)))

      joiner ! Primary(prim)
      expectMsg(ChangeSet(positive = Vector(t(5, 6, 7), t(10, 11, 7))))

      joiner ! Secondary(sec)
      expectMsgAnyOf(utils.changeSetPermutations(
        ChangeSet(negative = Vector(t(5, 6, 7), t(10, 11, 7)))):_*)
    }
    "do antijoin 2" in {
      val prim = ChangeSet(
        positive = Vector(t(1, 5), t(2, 6))
      )
      val sec = ChangeSet(
        positive = Vector(t(5, 10))
      )
      val primarySel = Vector(1)
      val secondarySel = Vector(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new HashAntijoiner(echoActor ! _, primarySel, secondarySel)))

      joiner ! Primary(prim)
      expectMsgAnyOf(
        utils.changeSetPermutations(ChangeSet(positive = Vector(t(1, 5), t(2, 6)))):_*
      )

      joiner ! Secondary(sec)
      expectMsg(ChangeSet(
        negative = Vector(t(1, 5))
      )
      )
    }
    "do antijoin new 1" in {
      val prim = ChangeSet(
        positive = Vector(t(1, 2), t(3, 4))
      )
      val secondary = ChangeSet(
        positive = Vector(t(2, 3), t(2, 4), t(4, 5))
      )
      val primarySel = Vector(1)
      val secondarySel = Vector(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new HashAntijoiner(echoActor ! _, primarySel, secondarySel)))

      joiner ! Primary(prim)
      expectMsg(ChangeSet(positive = Vector(t(1, 2), t(3, 4))))

      joiner ! Secondary(secondary)
      expectMsgAnyOf(utils.changeSetPermutations(ChangeSet(negative = Vector(t(1, 2), t(3, 4)))):_*)

      joiner ! Secondary(ChangeSet(negative = Vector(t(4, 5))))
      expectMsg(ChangeSet(positive = Vector(t(3, 4))))

      joiner ! Secondary(ChangeSet(negative = Vector(t(2, 3))))

      joiner ! Secondary(ChangeSet(negative = Vector(t(2, 4))))
      expectMsg(ChangeSet(positive = Vector(t(1, 2))))

      joiner ! Secondary(ChangeSet(positive = Vector(t(3, 4))))

      joiner ! Secondary(ChangeSet(positive = Vector(t(4, 3))))
      expectMsg(ChangeSet(negative = Vector(t(3, 4))))

      joiner ! Primary(ChangeSet(positive = Vector(t(1, 4))))

      joiner ! Primary(ChangeSet(positive = Vector(t(1, 5))))
      expectMsg(ChangeSet(positive = Vector(t(1, 5))))

      joiner ! Primary(ChangeSet(negative = Vector(t(1, 5))))
      expectMsg(ChangeSet(negative = Vector(t(1, 5))))
    }
    "do antijoin new 2" in {
      val prim = ChangeSet(
        positive = Vector(t(2, 4), t(3, 4), t(5, 4), t(6, 4), t(1, 3), t(2, 3))
      )
      val secondary = ChangeSet(
        positive = Vector(t(4, 8), t(4, 9), t(3, 4))
      )
      val primarySel = Vector(1)
      val secondarySel = Vector(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new HashAntijoiner(echoActor ! _, primarySel, secondarySel)))

      joiner ! Primary(prim)
      expectMsg(ChangeSet(positive = Vector(t(2, 4), t(3, 4), t(5, 4), t(6, 4), t(1, 3), t(2, 3))))

      joiner ! Secondary(secondary)
      expectMsgAnyOf(
        utils.changeSetPermutations(
          ChangeSet(negative = Vector(t(2, 4), t(3, 4), t(5, 4), t(6, 4), t(1, 3), t(2, 3)))
        ):_*
      )

      joiner ! Secondary(ChangeSet(negative = Vector(t(4, 7))))

      joiner ! Secondary(ChangeSet(negative = Vector(t(4, 8))))

      joiner ! Secondary(ChangeSet(negative = Vector(t(4, 9))))
      expectMsgAnyOf(utils.changeSetPermutations(ChangeSet(positive = Vector(t(2, 4), t(3, 4), t(5, 4), t(6, 4)))):_*)

      joiner ! Secondary(ChangeSet(positive = Vector(t(4, 5))))
      expectMsgAnyOf(utils.changeSetPermutations(ChangeSet(negative = Vector(t(2, 4), t(3, 4), t(5, 4), t(6, 4)))):_*)

      joiner ! Secondary(ChangeSet(negative = Vector(t(3, 4))))
      expectMsgAnyOf(utils.changeSetPermutations(ChangeSet(positive = Vector(t(1, 3), t(2, 3)))):_*)

      joiner ! Primary(ChangeSet(positive = Vector(t(4, 3))))
      expectMsg(ChangeSet(positive = Vector(t(4, 3))))

      joiner ! Secondary(ChangeSet(positive = Vector(t(3, 5))))
      expectMsgAnyOf(utils.changeSetPermutations(ChangeSet(negative = Vector(t(1, 3), t(2, 3), t(4, 3)))):_*)

      joiner ! Primary(ChangeSet(positive = Vector(t(7, 4))))
    }
    "do antijoin new 3" in {
      val prim = ChangeSet(
        positive = Vector(t(1, 2, 3, 4), t(1, 5, 6, 7),t(3, 2, 5, 4))
      )

      val primarySel = Vector(1, 3)
      val secondarySel = Vector(0, 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new HashAntijoiner(echoActor ! _, primarySel, secondarySel)))

      joiner ! Primary(prim)
      expectMsg(ChangeSet(Vector(t(1, 2, 3, 4), t(1, 5, 6, 7), t(3, 2, 5, 4))))

      joiner ! Primary(ChangeSet(positive = Vector(t(8, 2, 6, 4))))
      expectMsg(ChangeSet(positive = Vector(t(8, 2, 6, 4))))

      joiner ! Secondary(ChangeSet(positive = Vector(t(2, 5, 4, 3))))
      expectMsgAnyOf(
        utils.changeSetPermutations(ChangeSet(negative = Vector(t(1, 2, 3, 4),t(3, 2, 5, 4),t(8, 2, 6, 4)))):_*
      )

      joiner ! Secondary(ChangeSet(
        positive = Vector(t(5, 5, 7, 3))
      ))
      expectMsg(ChangeSet(negative = Vector(t(1, 5, 6, 7))))

      joiner ! Secondary(ChangeSet(negative = Vector(t(2, 5, 4, 3))))
      expectMsgAnyOf(
          utils.changeSetPermutations(ChangeSet(positive = Vector(t(1, 2, 3, 4),t(3, 2, 5, 4), t(8, 2, 6, 4)))):_*
      )
    }
  }
}
