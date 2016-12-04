import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class UnionNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  import TestUtil._

  "Union" must {
    "do simple unions 0" in {
      val prim = ChangeSet(
        positive = Vector(tuple(1, 2), tuple(1, 3))
      )
      val sec = ChangeSet(
        positive = Vector(tuple(1, 2), tuple(1, 4))
      )
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new UnionNode(echoActor ! _)))

      joiner ! Primary(prim)
      expectMsg(ChangeSet(positive = Vector(tuple(1, 2), tuple(1, 3))))
      joiner ! Secondary(sec)
      expectMsg(ChangeSet(positive = Vector(tuple(1, 4))))
    }
  }
}
