package hu.bme.mit.ire.stateless.binary

import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages._
import hu.bme.mit.ire.util.TestUtil._
import hu.bme.mit.ire.util.Utils
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import scala.collection.immutable.Vector

class JoinNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "Join" must {
    "join the values" in {
      val primaryChangeSet = BatchChangeSet(tupleBag(tuple(1, 2, 3, 4), tuple(5, 6, 7, 8)))

      val primaryTupleWidth = 4
      val secondaryTupleWidth = 3
      val primaryMask = mask(0, 1)
      val secondaryMask = mask(1, 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new JoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      joiner ! Primary(primaryChangeSet)
      expectMsg(BatchChangeSet(tupleBag()))

      joiner ! Secondary(BatchChangeSet(tupleBag(tuple(0, 0, 0))))
      expectMsg(BatchChangeSet())

      joiner ! Secondary(BatchChangeSet(tupleBag(tuple(10, 1, 2))))
      expectMsg(BatchChangeSet(tupleBag(tuple(1, 2, 3, 4, 10))))
    }

    "have bag behavior" in {
      val primaryTupleWidth = 2
      val secondaryTupleWidth = 2
      val primaryMask = mask(1)
      val secondaryMask = mask(0)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new JoinNode(echoActor ! _, primaryTupleWidth, secondaryTupleWidth, primaryMask, secondaryMask)))

      joiner ! Primary(BatchChangeSet(tupleBag(tuple(2, 4), tuple(2, 4), tuple(3, 4))))
      expectMsg(BatchChangeSet())

      joiner ! Secondary(BatchChangeSet(tupleBag(tuple(4, 5))))
      expectMsg(BatchChangeSet(tupleBag(tuple(2, 4, 5), tuple(2, 4, 5), tuple(3, 4, 5))))
    }
  }
}
