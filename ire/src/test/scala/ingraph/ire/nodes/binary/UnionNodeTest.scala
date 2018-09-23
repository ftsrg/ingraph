package ingraph.ire.nodes.binary

import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import ingraph.ire.messages.{ChangeSet, Primary, Secondary}
import ingraph.ire.util.TestUtil._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class UnionNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "Union" must {
    "do simple set unions 0" in {
      val prim = ChangeSet(
        positive = tupleBag(tuple(1, 2), tuple(1, 3))
      )
      val sec = ChangeSet(
        positive = tupleBag(tuple(1, 2), tuple(1, 4))
      )
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val union = system.actorOf(Props(new UnionNode(echoActor ! _, all = false)))

      union ! Primary(prim)
      expectMsg(ChangeSet(positive = tupleBag(tuple(1, 2), tuple(1, 3))))
      union ! Secondary(sec)
      expectMsg(ChangeSet(positive = tupleBag(tuple(1, 4))))
    }

    "do simple bag unions 0" in {
      val prim = ChangeSet(
        positive = tupleBag(tuple(1, 2), tuple(1, 3))
      )
      val sec = ChangeSet(
        positive = tupleBag(tuple(1, 2), tuple(1, 4))
      )
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val union = system.actorOf(Props(new UnionNode(echoActor ! _, all = true)))

      union ! Primary(prim)
      expectMsg(ChangeSet(positive = tupleBag(tuple(1, 2), tuple(1, 3))))
      union ! Secondary(sec)
      expectMsg(ChangeSet(positive = tupleBag(tuple(1, 2), tuple(1, 4))))
    }
  }
}
