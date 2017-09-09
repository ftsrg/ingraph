package hu.bme.mit.ire.nodes.binary

import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.{IncrementalChangeSet, Primary, Secondary}
import hu.bme.mit.ire.util.TestUtil._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class UnionNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "Union" must {
    "do simple set unions 0" in {
      val prim = IncrementalChangeSet(
        positive = tupleBag(tuple(1, 2), tuple(1, 3))
      )
      val sec = IncrementalChangeSet(
        positive = tupleBag(tuple(1, 2), tuple(1, 4))
      )
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val union = system.actorOf(Props(new UnionNode(echoActor ! _, bag = false)))

      union ! Primary(prim)
      expectMsg(IncrementalChangeSet(positive = tupleBag(tuple(1, 2), tuple(1, 3))))
      union ! Secondary(sec)
      expectMsg(IncrementalChangeSet(positive = tupleBag(tuple(1, 4))))
    }

    "do simple bag unions 0" in {
      val prim = IncrementalChangeSet(
        positive = tupleBag(tuple(1, 2), tuple(1, 3))
      )
      val sec = IncrementalChangeSet(
        positive = tupleBag(tuple(1, 2), tuple(1, 4))
      )
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val union = system.actorOf(Props(new UnionNode(echoActor ! _, bag = true)))

      union ! Primary(prim)
      expectMsg(IncrementalChangeSet(positive = tupleBag(tuple(1, 2), tuple(1, 3))))
      union ! Secondary(sec)
      expectMsg(IncrementalChangeSet(positive = tupleBag(tuple(1, 2), tuple(1, 4))))
    }
  }
}
