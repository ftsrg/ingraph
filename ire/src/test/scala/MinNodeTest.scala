import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.nodes.unary.UnwindNode
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class MinNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  import hu.bme.mit.ire.util.TestUtil._

  "Min" must {
    "do simple min 0" in {
      val changeSet = ChangeSet(
        positive = Vector(
          tuple("a", 1), tuple("a", 2), tuple("b", 3)
        )
      )
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val min = system.actorOf(Props(new UnwindNode(echoActor ! _, 1)))

      min ! changeSet
      expectMsg(ChangeSet(positive = Vector(
        tuple("a", 1), tuple("b", 3)
      )))
    }
  }
}
