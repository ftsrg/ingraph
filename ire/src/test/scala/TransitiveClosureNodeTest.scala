import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.{ChangeSet, Primary, Secondary}
import hu.bme.mit.ire.nodes.unary.TransitiveClosureNode
import hu.bme.mit.ire.util.TestUtil._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class TransitiveClosureNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A TransitiveClosureNode" must {
    "calculate transitive closure" in {
      val changeSet = ChangeSet(
        positive = Vector(tuple(1, "a", 2), tuple(2, "b", 3))
      )
      val src = 0
      val trg = 2
      val edge = 1
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val transitiveClosure = system.actorOf(Props(new TransitiveClosureNode(echoActor ! _, src, trg, edge)), name = "testSelector")

      transitiveClosure ! changeSet
//      expectMsg(ChangeSet(
//        positive = Vector(tuple(1, 2), tuple(2, 3), tuple(2, 3))
//      ))
    }
  }


}
