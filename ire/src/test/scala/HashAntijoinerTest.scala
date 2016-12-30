import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActorRef, TestActors, TestKit}
import hu.bme.mit.ire._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class HashAntijoinerTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
  TestKit.shutdownActorSystem(system)
  }

  "HashJoiner" must {
  "do simple antijoins" in {
    val prim = ChangeSet(
    positive = Vector(Map(0 -> 15, 1 -> 16, 2 -> 17, 3 -> 18), Map(0 -> 4, 1 -> 5, 2 -> 6, 3 -> 7))
    )
    val sec = ChangeSet(
    positive = Vector(Map(0 -> 13, 1 -> 15, 2 -> 16))
    )
    val primarySel = Vector(0, 1)
    val secondarySel = Vector(1, 2)
    val echoActor = system.actorOf(TestActors.echoActorProps)
    val joiner = system.actorOf(Props(new HashAntijoiner(echoActor ! _, primarySel, secondarySel)))

    joiner ! Secondary(sec)

    joiner ! Primary(prim)
    expectMsg(ChangeSet(
    positive = Vector(Map(0 -> 4, 1 -> 5, 2 -> 6, 3 -> 7))
    ))
  }
  //based on https://github.com/FTSRG/incqueryd/tree/master/hu.bme.mit.incqueryd.client/hu.bme.mit.incqueryd.rete.nodes/src/test/resources/test-cases
  "do antijoin 1" in {
    val prim = ChangeSet(
    positive = Vector(Map(0 -> 5, 1 -> 6, 2 -> 7), Map(0 -> 10, 1 -> 11, 2 -> 7))
    )
    val sec = ChangeSet(
    positive = Vector(Map(0 -> 7, 1 -> 8))
    )
    val primarySel = Vector(2)
    val secondarySel = Vector(0)
    val echoActor = system.actorOf(TestActors.echoActorProps)
    val joiner = system.actorOf(Props(new HashAntijoiner(echoActor ! _, primarySel, secondarySel)))

    joiner ! Primary(prim)
    expectMsg(ChangeSet(positive = Vector(Map(0 -> 5, 1 -> 6, 2 -> 7), Map(0 -> 10, 1 -> 11, 2 -> 7))))

    joiner ! Secondary(sec)
    expectMsgAnyOf(utils.changeSetPermutations(
    ChangeSet(negative = Vector(Map(0 -> 5, 1 -> 6, 2 -> 7), Map(0 -> 10, 1 -> 11, 2 -> 7)))):_*)
  }
  "do antijoin 2" in {
    val prim = ChangeSet(
    positive = Vector(Map(0 -> 1, 1 -> 5), Map(0 -> 2, 1 -> 6))
    )
    val sec = ChangeSet(
    positive = Vector(Map(0 -> 5, 1 -> 10))
    )
    val primarySel = Vector(1)
    val secondarySel = Vector(0)
    val echoActor = system.actorOf(TestActors.echoActorProps)
    val joiner = system.actorOf(Props(new HashAntijoiner(echoActor ! _, primarySel, secondarySel)))

    joiner ! Primary(prim)
    expectMsgAnyOf(
    utils.changeSetPermutations(ChangeSet(positive = Vector(Map(0 -> 1, 1 -> 5), Map(0 -> 2, 1 -> 6)))):_*
    )

    joiner ! Secondary(sec)
    expectMsg(ChangeSet(
    negative = Vector(Map(0 -> 1, 1 -> 5))
    )
    )
  }
  "do antijoin new 1" in {
    val prim = ChangeSet(
    positive = Vector(Map(0 -> 1, 1 -> 2), Map(0 -> 3, 1 -> 4))
    )
    val secondary = ChangeSet(
    positive = Vector(Map(0 -> 2, 1 -> 3), Map(0 -> 2, 1 -> 4), Map(0 -> 4, 1 -> 5))
    )
    val primarySel = Vector(1)
    val secondarySel = Vector(0)
    val echoActor = system.actorOf(TestActors.echoActorProps)
    val joiner = system.actorOf(Props(new HashAntijoiner(echoActor ! _, primarySel, secondarySel)))

    joiner ! Primary(prim)
    expectMsg(ChangeSet(positive = Vector(Map(0 -> 1, 1 -> 2), Map(0 -> 3, 1 -> 4))))

    joiner ! Secondary(secondary)
    expectMsgAnyOf(utils.changeSetPermutations(ChangeSet(negative = Vector(Map(0 -> 1, 1 -> 2), Map(0 -> 3, 1 -> 4)))):_*)

    joiner ! Secondary(ChangeSet(negative = Vector(Map(0 -> 4, 1 -> 5))))
    expectMsg(ChangeSet(positive = Vector(Map(0 -> 3, 1 -> 4))))

    joiner ! Secondary(ChangeSet(negative = Vector(Map(0 -> 2, 1 -> 3))))

    joiner ! Secondary(ChangeSet(negative = Vector(Map(0 -> 2, 1 -> 4))))
    expectMsg(ChangeSet(positive = Vector(Map(0 -> 1, 1 -> 2))))

    joiner ! Secondary(ChangeSet(positive = Vector(Map(0 -> 3, 1 -> 4))))

    joiner ! Secondary(ChangeSet(positive = Vector(Map(0 -> 4, 1 -> 3))))
    expectMsg(ChangeSet(negative = Vector(Map(0 -> 3, 1 -> 4))))

    joiner ! Primary(ChangeSet(positive = Vector(Map(0 -> 1, 1 -> 4))))

    joiner ! Primary(ChangeSet(positive = Vector(Map(0 -> 1, 1 -> 5))))
    expectMsg(ChangeSet(positive = Vector(Map(0 -> 1, 1 -> 5))))

    joiner ! Primary(ChangeSet(negative = Vector(Map(0 -> 1, 1 -> 5))))
    expectMsg(ChangeSet(negative = Vector(Map(0 -> 1, 1 -> 5))))
  }
  "do antijoin new 2" in {
    val prim = ChangeSet(
    positive = Vector(Map(0 -> 2, 1 -> 4), Map(0 -> 3, 1 -> 4), Map(0 -> 5, 1 -> 4), Map(0 -> 6, 1 -> 4), Map(0 -> 1, 1 -> 3), Map(0 -> 2, 1 -> 3))
    )
    val secondary = ChangeSet(
    positive = Vector(Map(0 -> 4, 1 -> 8), Map(0 -> 4, 1 -> 9), Map(0 -> 3, 1 -> 4))
    )
    val primarySel = Vector(1)
    val secondarySel = Vector(0)
    val echoActor = system.actorOf(TestActors.echoActorProps)
    val joiner = system.actorOf(Props(new HashAntijoiner(echoActor ! _, primarySel, secondarySel)))

    joiner ! Primary(prim)
    expectMsg(ChangeSet(positive = Vector(Map(0 -> 2, 1 -> 4), Map(0 -> 3, 1 -> 4), Map(0 -> 5, 1 -> 4), Map(0 -> 6, 1 -> 4), Map(0 -> 1, 1 -> 3), Map(0 -> 2, 1 -> 3))))

    joiner ! Secondary(secondary)
    expectMsgAnyOf(
    utils.changeSetPermutations(
      ChangeSet(negative = Vector(Map(0 -> 2, 1 -> 4), Map(0 -> 3, 1 -> 4), Map(0 -> 5, 1 -> 4), Map(0 -> 6, 1 -> 4), Map(0 -> 1, 1 -> 3), Map(0 -> 2, 1 -> 3)))
    ):_*
    )

    joiner ! Secondary(ChangeSet(negative = Vector(Map(0 -> 4, 1 -> 7))))

    joiner ! Secondary(ChangeSet(negative = Vector(Map(0 -> 4, 1 -> 8))))

    joiner ! Secondary(ChangeSet(negative = Vector(Map(0 -> 4, 1 -> 9))))
    expectMsgAnyOf(utils.changeSetPermutations(ChangeSet(positive = Vector(Map(0 -> 2, 1 -> 4), Map(0 -> 3, 1 -> 4), Map(0 -> 5, 1 -> 4), Map(0 -> 6, 1 -> 4)))):_*)

    joiner ! Secondary(ChangeSet(positive = Vector(Map(0 -> 4, 1 -> 5))))
    expectMsgAnyOf(utils.changeSetPermutations(ChangeSet(negative = Vector(Map(0 -> 2, 1 -> 4), Map(0 -> 3, 1 -> 4), Map(0 -> 5, 1 -> 4), Map(0 -> 6, 1 -> 4)))):_*)

    joiner ! Secondary(ChangeSet(negative = Vector(Map(0 -> 3, 1 -> 4))))
    expectMsgAnyOf(utils.changeSetPermutations(ChangeSet(positive = Vector(Map(0 -> 1, 1 -> 3), Map(0 -> 2, 1 -> 3)))):_*)

    joiner ! Primary(ChangeSet(positive = Vector(Map(0 -> 4, 1 -> 3))))
    expectMsg(ChangeSet(positive = Vector(Map(0 -> 4, 1 -> 3))))

    joiner ! Secondary(ChangeSet(positive = Vector(Map(0 -> 3, 1 -> 5))))
    expectMsgAnyOf(utils.changeSetPermutations(ChangeSet(negative = Vector(Map(0 -> 1, 1 -> 3), Map(0 -> 2, 1 -> 3), Map(0 -> 4, 1 -> 3)))):_*)

    joiner ! Primary(ChangeSet(positive = Vector(Map(0 -> 7, 1 -> 4))))
  }
  "do antijoin new 3" in {
    val prim = ChangeSet(
    positive = Vector(Map(0 -> 1, 1 -> 2, 2 -> 3, 3 -> 4), Map(0 -> 1, 1 -> 5, 2 -> 6, 3 -> 7),Map(0 -> 3, 1 -> 2, 2 -> 5, 3 -> 4))
    )

    val primarySel = Vector(1, 3)
    val secondarySel = Vector(0, 2)
    val echoActor = system.actorOf(TestActors.echoActorProps)
    val joiner = system.actorOf(Props(new HashAntijoiner(echoActor ! _, primarySel, secondarySel)))

    joiner ! Primary(prim)
    expectMsg(ChangeSet(Vector(Map(0 -> 1, 1 -> 2, 2 -> 3, 3 -> 4), Map(0 -> 1, 1 -> 5, 2 -> 6, 3 -> 7), Map(0 -> 3, 1 -> 2, 2 -> 5, 3 -> 4))))

    joiner ! Primary(ChangeSet(positive = Vector(Map(0 -> 8, 1 -> 2, 2 -> 6, 3 -> 4))))
    expectMsg(ChangeSet(positive = Vector(Map(0 -> 8, 1 -> 2, 2 -> 6, 3 -> 4))))

    joiner ! Secondary(ChangeSet(positive = Vector(Map(0 -> 2, 1 -> 5, 2 -> 4, 3 -> 3))))
    expectMsgAnyOf(
    utils.changeSetPermutations(ChangeSet(negative = Vector(Map(0 -> 1, 1 -> 2, 2 -> 3, 3 -> 4),Map(0 -> 3, 1 -> 2, 2 -> 5, 3 -> 4),Map(0 -> 8, 1 -> 2, 2 -> 6, 3 -> 4)))):_*
    )

    joiner ! Secondary(ChangeSet(
    positive = Vector(Map(0 -> 5, 1 -> 5, 2 -> 7, 3 -> 3))
    ))
    expectMsg(ChangeSet(negative = Vector(Map(0 -> 1, 1 -> 5, 2 -> 6, 3 -> 7))))

    joiner ! Secondary(ChangeSet(negative = Vector(Map(0 -> 2, 1 -> 5, 2 -> 4, 3 -> 3))))
    expectMsgAnyOf(
      utils.changeSetPermutations(ChangeSet(positive = Vector(Map(0 -> 1, 1 -> 2, 2 -> 3, 3 -> 4),Map(0 -> 3, 1 -> 2, 2 -> 5, 3 -> 4), Map(0 -> 8, 1 -> 2, 2 -> 6, 3 -> 4)))):_*
    )
  }
  }
}
