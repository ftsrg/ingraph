package ingraph.ire.nodes.binary

import akka.actor.{ActorSystem, PoisonPill, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import ingraph.ire.messages.{ChangeSet, Primary, Secondary}
import ingraph.ire.util.TestUtil._
import ingraph.ire.util.Utils
import ingraph.ire.util.TestUtil
import org.scalatest.{Matchers, WordSpecLike, _}

import scala.concurrent.duration.Duration

class LeftOuterJoinNodeTest(_system: ActorSystem)  extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "LeftOuterJoin" must {
    "left outer join the values" in {
      val primaryChangeSet = ChangeSet(
        positive = tupleBag(
          tuple(15, 16, 17, 18),
          tuple(4, 5, 6, 7)
        )
      )
      val secondaryChangeSet = ChangeSet(
        positive = tupleBag(
          tuple(0, 15, 16, 13),
          tuple(1, 15, 16, 14)
        )
      )
      val secondSecondaryChangeSet = ChangeSet(
        positive = tupleBag(tuple(20, 4, 5, 33))
      )

      val primaryTupleWidth = 4
      val secondaryTupleWidth = 4
      val primaryMask = mask(0, 1)
      val secondaryMask = mask(1, 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new LeftOuterJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      joiner ! Primary(primaryChangeSet)
      expectMsg(ChangeSet(
        positive = tupleBag(
          tuple(15, 16, 17, 18, null, null),
          tuple(4, 5, 6, 7, null, null)
        )
      ))

      joiner ! Secondary(secondaryChangeSet)
      expectMsg(ChangeSet(
        positive = tupleBag(
          tuple(15, 16, 17, 18, 0, 13),
          tuple(15, 16, 17, 18, 1, 14)
        ),
        negative = tupleBag(
          tuple(15, 16, 17, 18, null, null)
        )
      ))

      joiner ! Secondary(secondSecondaryChangeSet)
      expectMsg(ChangeSet(
        positive = tupleBag(
          tuple(4, 5, 6, 7, 20, 33)
        ),
        negative = tupleBag(
          tuple(4, 5, 6, 7, null, null)
        )
      ))
    }

    "does not produce update in case of pairless secondary update" in {
      val primaryChangeSet = ChangeSet(
        positive = tupleBag(
          tuple(15, 16, 17, 18),
          tuple(4, 5, 6, 7)
        )
      )
      val secondaryChangeSet = ChangeSet(
        positive = tupleBag(tuple(0, 18, 19, 13))
      )
      val secondaryNegativeChangeSet = ChangeSet(
        negative = tupleBag(tuple(0, 18, 19, 13))
      )

      val primaryTupleWidth = 4
      val secondaryTupleWidth = 4
      val primaryMask = mask(0, 1)
      val secondaryMask = mask(1, 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new LeftOuterJoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      joiner ! Primary(primaryChangeSet)
      expectMsg(ChangeSet(
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

      joiner ! Primary(ChangeSet(
        positive = tupleBag(
          tuple(5, 6, 7),
          tuple(10, 11, 7)
        )))
      expectMsgAnyOf(TestUtil.changeSetPermutations(ChangeSet(
        positive = tupleBag(
          tuple(5, 6, 7, null),
          tuple(10, 11, 7, null)
        )
      )): _*)

      joiner ! Secondary(ChangeSet(
        positive = tupleBag(tuple(7, 8))
      ))
      expectMsgAnyOf(TestUtil.changeSetPermutations(ChangeSet(
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
      val primaryChangeSet = ChangeSet(
        positive = tupleBag(
          tuple(15, 16, 17, 18),
          tuple(4, 5, 6, 7)
        )
      )
      val secondaryChangeSet = ChangeSet(
        positive = tupleBag(tuple(0, 15, 16, 13))
      )
      val primaryNegativeChangeSet = ChangeSet(
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
      expectMsgAnyOf(TestUtil.changeSetPermutations(ChangeSet(
        positive = tupleBag(
          tuple(15, 16, 17, 18, null, null),
          tuple(4, 5, 6, 7, null, null)
        )
      )): _*)

      joiner ! Secondary(secondaryChangeSet)
      expectMsg(ChangeSet(
        positive = tupleBag(
          tuple(15, 16, 17, 18, 0, 13)
        ),
        negative = tupleBag(
          tuple(15, 16, 17, 18, null, null)
        )
      ))

      joiner ! Primary(primaryNegativeChangeSet)
      expectMsgAnyOf(TestUtil.changeSetPermutations(ChangeSet(
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

      joiner ! Primary(ChangeSet(
        positive = tupleBag(tuple(2, 4), tuple(3, 4))
      ))
      expectMsgAnyOf(TestUtil.changeSetPermutations(ChangeSet(
        positive = tupleBag(
          tuple(2, 4, null),
          tuple(3, 4, null)
        )
      )): _*)

      joiner ! Secondary(ChangeSet(
        positive = tupleBag(tuple(4, 5), tuple(4, 6))
      ))
      expectMsgAnyOf(TestUtil.changeSetPermutations(ChangeSet(
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

      joiner ! Secondary(ChangeSet(
        negative = tupleBag(tuple(4, 5))
      ))
      expectMsgAnyOf(TestUtil.changeSetPermutations(ChangeSet(
        negative = tupleBag(
          tuple(2, 4, 5),
          tuple(3, 4, 5)
        )
      )): _*)

      joiner ! Secondary(ChangeSet(
        negative = tupleBag(tuple(4, 6))
      ))
      expectMsgAnyOf(TestUtil.changeSetPermutations(ChangeSet(
        positive = tupleBag(
          tuple(2, 4, null),
          tuple(3, 4, null)
        ),
        negative = tupleBag(
          tuple(2, 4, 6),
          tuple(3, 4, 6)
        )
      )): _*)

      joiner ! Secondary(ChangeSet(
        positive = tupleBag(tuple(4, 7))
      ))
      expectMsgAnyOf(TestUtil.changeSetPermutations(ChangeSet(
        positive = tupleBag(
          tuple(2, 4, 7),
          tuple(3, 4, 7)
        ),
        negative = tupleBag(
          tuple(2, 4, null),
          tuple(3, 4, null)
        )
      )): _*)

      joiner ! Primary(ChangeSet(
        negative = tupleBag(tuple(2, 4), tuple(3, 4))
      ))
      expectMsgAnyOf(TestUtil.changeSetPermutations(ChangeSet(
        negative = tupleBag(
          tuple(2, 4, 7),
          tuple(3, 4, 7)
        )
      )): _*)
    }

    "tpc-h 13 lojo" in {
      println(System.getProperty("user.dir"))
      //select c_custkey, o_orderkey from CUSTOMER left outer join ORDERS on c_custkey = o_custkey
      val customerSource = io.Source.fromFile("../graphs/tpc-h/customers.csv")
      val customers = customerSource.getLines().drop(1)
        .map(_.split(",").take(3))
        .map(f => {
          val Array(custKey, segment, country) = f
          Vector(custKey, segment, country.toLong)
        }).toVector

      val orderSource = io.Source.fromFile("../graphs/tpc-h/orders.csv")
      val orders = orderSource.getLines().drop(1)
        .map(_.split(",").take(3))
        .map(f => {
          val Array(date, orderKey, custKey) = f
          Vector(date, orderKey, custKey)
        }).toVector

      for (i <- 0 to 10) {
        val tupleWidth = 2
        val primaryMask = mask(0)
        val secondaryMask = mask(1)
        val echoActor = system.actorOf(TestActors.echoActorProps)
        val joiner = system.actorOf(Props(
          new LeftOuterJoinNode(echoActor ! _, tupleWidth, tupleWidth, primaryMask, secondaryMask)))
        Utils.time {
          joiner ! Secondary(ChangeSet(positive = orders))
          joiner ! Primary(ChangeSet(positive = customers))
          import scala.concurrent.duration._
          val first = receiveN(1, 60 seconds).head.asInstanceOf[ChangeSet]
          assert(first.positive.length == 150000)
        }
        joiner ! PoisonPill
      }
    }

  }


}
