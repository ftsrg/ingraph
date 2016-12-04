import TestUtil._
import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.{ChangeSet, Primary, Secondary}
import hu.bme.mit.ire.nodes.binary.JoinNode
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class TransitiveClosureNodeTestextends(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A TransitiveClosureNode" must {
    "calculate transitive closure" in {
      val prim = ChangeSet(
        positive = Vector(tuple(15, 16, 17, 18), tuple(4, 5, 6, 7))
      )
      val sec = ChangeSet(
        positive = Vector(Map(1 -> 15, 2 -> 16, 4 -> 13))
      )
      val primarySel = Vector(0, 1)
      val secondarySel = Vector(1, 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val joiner = system.actorOf(Props(new JoinNode(echoActor ! _, primarySel, secondarySel)), name = "testSelector")

      joiner ! Primary(prim)
      joiner ! Secondary(sec)
      expectMsg(ChangeSet(
        positive = Vector(tuple(15, 16, 17, 18, 13))
      ))
    }
  }


}
