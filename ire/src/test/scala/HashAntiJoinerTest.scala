import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActorRef, TestActors, TestKit}
import hu.bme.mit.IQDcore._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

/**
 * Created by Maginecz on 4/19/2015.
 */
class HashAntiJoinerTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "AntiHashJoiner" must {
    "retain positive nodes" in {
      val primaryVec = Vector(15, 16, 17, 18)
      val prim = ChangeSet(
        positive = Vector(primaryVec)
      )
      val sec = ChangeSet(
        positive = Vector(Vector(13, 15, 16))
      )
      val actor = TestActorRef(new HashAntiJoiner(nop => (), Vector(1, 2), Vector(0, 1)))
      val node = actor.underlyingActor
      node.receive(Primary(prim))
      assert(node.primaryValues.get(Vector(16, 17)).get.contains(primaryVec))
      node.receive(Secondary(sec))
      assert(node.secondaryValues.contains(Vector(13, 15)))
    }
    "remove negative nodes" in {
      val prim1 = Vector(15, 16, 17, 18)
      val prim2 = Vector(9, 8, 7, 6)
      val prim = ChangeSet(
        positive = Vector(prim1, prim2)
      )
      val primRemove = ChangeSet(
        negative = Vector(prim1)
      )
      val sec1 = Vector(13, 15, 16, 17)
      val sec2 = Vector(1, 2, 3, 4)
      val sec = ChangeSet(
        positive = Vector(sec1, sec2)
      )

      val actor = TestActorRef(new HashAntiJoiner(nop => (), Vector(1, 2), Vector(0, 1)))
      val node = actor.underlyingActor
      node.receive(Primary(prim))
      node.receive(Secondary(sec))
      node.receive(Primary(ChangeSet(negative = Vector(prim1))))
      assert(node.primaryValues.get(Vector(16, 17)) == None)
      assert(node.primaryValues.get(Vector(8, 7)).get.contains(prim2))
      node.receive(Secondary(ChangeSet(negative = Vector(sec1))))
      assert(!node.secondaryValues.contains(Vector(13, 15)))
      assert(node.secondaryValues.contains(Vector(1, 2)))
    }
  }
  "send primary messages through when empty" in {
    val echoActor = system.actorOf(TestActors.echoActorProps)
    val joiner = system.actorOf(Props(new HashAntiJoiner(echoActor ! _, Vector(0, 1), Vector(0, 1))))
    val msg = ChangeSet(
      positive = Vector(Vector(5, 6, 7)),
      negative = Vector(Vector(8, 9, 10))
    )
    joiner ! Primary(msg)
    expectMsg(msg)
  }
  "do simple antijoins" in {
    val prim = ChangeSet(
      positive = Vector(Vector(15, 16, 17, 18), Vector(4, 5, 6, 7))
    )
    val sec = ChangeSet(
      positive = Vector(Vector(13, 15, 16))
    )
    val primarySel = Vector(0, 1)
    val secondarySel = Vector(1, 2)
    val echoActor = system.actorOf(TestActors.echoActorProps)
    val joiner = system.actorOf(Props(new HashAntiJoiner(echoActor ! _, primarySel, secondarySel)))

    joiner ! Secondary(sec)
    expectMsg(ChangeSet())
    joiner ! Primary(prim)
    expectMsg(ChangeSet(
      positive = Vector(Vector(4, 5, 6, 7))
    ))
  }
  //based on https://github.com/FTSRG/incqueryd/tree/master/hu.bme.mit.incqueryd.client/hu.bme.mit.incqueryd.rete.nodes/src/test/resources/test-cases
  "do antijoin 1" in {
    val prim = ChangeSet(
      positive = Vector(Vector(5,6,7), Vector(10,11,7))
    )
    val sec = ChangeSet(
      positive = Vector(Vector(7,8))
    )
    val primarySel = Vector(2)
    val secondarySel = Vector(0)
    val echoActor = system.actorOf(TestActors.echoActorProps)
    val joiner = system.actorOf(Props(new HashAntiJoiner(echoActor ! _, primarySel, secondarySel)))

    joiner ! Primary(prim)
    expectMsg(ChangeSet(positive = Vector(Vector(5,6,7), Vector(10,11,7))))

    joiner ! Secondary(sec)
//    val values =for (
//      perm <- Vector(Vector(5,6,7), Vector(10,11,7)).permutations
//    ) yield ChangeSet(negative = perm)
    expectMsgAnyOf(
      (for (
            perm <- Vector(Vector(5,6,7), Vector(10,11,7)).permutations
          ) yield ChangeSet(negative = perm)).toSeq:_*
    )
  }
  "do antijoin 2" in {
    val prim = ChangeSet(
      positive = Vector(Vector(1,5), Vector(2, 6))
    )
    val sec = ChangeSet(
      positive = Vector(Vector(5, 10))
    )
    val primarySel = Vector(1)
    val secondarySel = Vector(0)
    val echoActor = system.actorOf(TestActors.echoActorProps)
    val joiner = system.actorOf(Props(new HashAntiJoiner(echoActor ! _, primarySel, secondarySel)))

    joiner ! Primary(prim)
    expectMsgAnyOf(
      utils.changeSetPermutations(ChangeSet(positive = Vector(Vector(1, 5), Vector(2, 6)))):_*
    )

    joiner ! Secondary(sec)
    expectMsg(ChangeSet(
      negative = Vector(Vector(1, 5))
    )
    )
  }
  "do antijoin new 1" in {
    val prim = ChangeSet(
      positive = Vector(Vector(1,2), Vector(3,4))
    )
    val secondary = ChangeSet(
      positive = Vector(Vector(2, 3), Vector(2, 4), Vector(4, 5))
    )
    val primarySel = Vector(1)
    val secondarySel = Vector(0)
    val echoActor = system.actorOf(TestActors.echoActorProps)
    val joiner = system.actorOf(Props(new HashAntiJoiner(echoActor ! _, primarySel, secondarySel)))

    joiner ! Primary(prim)
    expectMsg(ChangeSet(positive = Vector(Vector(1, 2), Vector(3, 4))))

    joiner ! Secondary(secondary)
    expectMsgAnyOf(utils.changeSetPermutations(ChangeSet(negative = Vector(Vector(1, 2), Vector(3, 4)))):_*)

    joiner ! Secondary(ChangeSet(negative = Vector(Vector(4, 5))))
    expectMsg(ChangeSet(positive = Vector(Vector(3, 4))))

    joiner ! Secondary(ChangeSet(negative = Vector(Vector(2, 3))))
    expectMsg(ChangeSet())

    joiner ! Secondary(ChangeSet(negative = Vector(Vector(2, 4))))
    expectMsg(ChangeSet(positive = Vector(Vector(1, 2))))

    joiner ! Secondary(ChangeSet(positive = Vector(Vector(3, 4))))
    expectMsg(ChangeSet())

    joiner ! Secondary(ChangeSet(positive = Vector(Vector(4, 3))))
    expectMsg(ChangeSet(negative = Vector(Vector(3, 4))))

    joiner ! Primary(ChangeSet(positive = Vector(Vector(1, 4))))
    expectMsg(ChangeSet())

    joiner ! Primary(ChangeSet(positive = Vector(Vector(1, 5))))
    expectMsg(ChangeSet(positive = Vector(Vector(1, 5))))

    joiner ! Primary(ChangeSet(negative = Vector(Vector(1, 5))))
    expectMsg(ChangeSet(negative = Vector(Vector(1, 5))))
  }
  "do antijoin new 2" in {
    val prim = ChangeSet(
      positive = Vector(Vector(2,4), Vector(3,4), Vector(5, 4), Vector(6, 4), Vector(1,3 ), Vector(2, 3))
    )
    val secondary = ChangeSet(
      positive = Vector(Vector(4, 8), Vector(4, 9), Vector(3, 4))
    )
    val primarySel = Vector(1)
    val secondarySel = Vector(0)
    val echoActor = system.actorOf(TestActors.echoActorProps)
    val joiner = system.actorOf(Props(new HashAntiJoiner(echoActor ! _, primarySel, secondarySel)))

    joiner ! Primary(prim)
    expectMsg(ChangeSet(positive = Vector(Vector(2,4), Vector(3,4), Vector(5, 4), Vector(6, 4), Vector(1,3 ), Vector(2, 3))))

    joiner ! Secondary(secondary)
    expectMsgAnyOf(
      utils.changeSetPermutations(
        ChangeSet(negative = Vector(Vector(2, 4), Vector(3, 4), Vector(5, 4), Vector(6, 4), Vector(1, 3), Vector(2, 3)))
      ):_*
    )

    joiner ! Secondary(ChangeSet(negative = Vector(Vector(4, 7))))
    expectMsg(ChangeSet())

    joiner ! Secondary(ChangeSet(negative = Vector(Vector(4, 8))))
    expectMsg(ChangeSet())

    joiner ! Secondary(ChangeSet(negative = Vector(Vector(4, 9))))
    expectMsgAnyOf(utils.changeSetPermutations(ChangeSet(positive = Vector(Vector(2, 4), Vector(3, 4), Vector(5, 4), Vector(6, 4)))):_*)

    joiner ! Secondary(ChangeSet(positive = Vector(Vector(4, 5))))
    expectMsgAnyOf(utils.changeSetPermutations(ChangeSet(negative = Vector(Vector(2, 4), Vector(3, 4), Vector(5, 4), Vector(6, 4)))):_*)

    joiner ! Secondary(ChangeSet(negative = Vector(Vector(3, 4))))
    expectMsgAnyOf(utils.changeSetPermutations(ChangeSet(positive = Vector(Vector(1, 3), Vector(2, 3)))):_*)

    joiner ! Primary(ChangeSet(positive = Vector(Vector(4, 3))))
    expectMsg(ChangeSet(positive = Vector(Vector(4, 3))))

    joiner ! Secondary(ChangeSet(positive = Vector(Vector(3, 5))))
    expectMsgAnyOf(utils.changeSetPermutations(ChangeSet(negative = Vector(Vector(1, 3), Vector(2, 3), Vector(4, 3)))):_*)

    joiner ! Primary(ChangeSet(positive = Vector(Vector(7, 4))))
    expectMsg(ChangeSet())
  }
  "do antijoin new 3" in {
    val prim = ChangeSet(
      positive = Vector(Vector(1,2,3,4), Vector(1,5,6,7),Vector(3,2,5,4))
    )

    val primarySel = Vector(1, 3)
    val secondarySel = Vector(0, 2)
    val echoActor = system.actorOf(TestActors.echoActorProps)
    val joiner = system.actorOf(Props(new HashAntiJoiner(echoActor ! _, primarySel, secondarySel)))

    joiner ! Primary(prim)
    expectMsg(ChangeSet(Vector(Vector(1, 2, 3, 4), Vector(1, 5, 6, 7), Vector(3, 2, 5, 4))))

    joiner ! Primary(ChangeSet(positive = Vector(Vector(8,2,6,4))))
    expectMsg(ChangeSet(positive = Vector(Vector(8,2,6,4))))

    joiner ! Secondary(ChangeSet(positive = Vector(Vector(2,5,4,3))))
    expectMsgAnyOf(
      utils.changeSetPermutations(ChangeSet(negative = Vector(Vector(1,2,3,4),Vector(3,2,5,4),Vector(8,2,6,4)))):_*
    )

    joiner ! Secondary(ChangeSet(
      positive = Vector(Vector(5,5,7,3))
    ))
    expectMsg(ChangeSet(negative = Vector(Vector(1,5,6,7))))

    joiner ! Secondary(ChangeSet(negative = Vector(Vector(2,5,4,3))))
    expectMsgAnyOf(
        utils.changeSetPermutations(ChangeSet(positive = Vector(Vector(1,2,3,4),Vector(3,2,5,4), Vector(8,2,6,4)))):_*
    )
  }
}
