import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.nodes.unary.MaxNode
import hu.bme.mit.ire.util.TestUtil._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class MaxNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "Max" must {
    "do simple max 0" in {
      val changeSet = ChangeSet(
        positive = tupleBag(tuple("a", 1), tuple("a", 2), tuple("a", 1.1), tuple("b", 3))
      )
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val max = system.actorOf(Props(new MaxNode(echoActor ! _, mask(0), 1)))

      max ! changeSet
      expectMsg(ChangeSet(
        positive = tupleBag(tuple("a", 2), tuple("b", 3))
      ))

      max ! ChangeSet(
        negative = tupleBag(tuple("a", 2))
      )
      expectMsg(ChangeSet(
        positive = tupleBag(tuple("a", 1.1)),
        negative = tupleBag(tuple("a", 2))
      ))
    }
  }
}
