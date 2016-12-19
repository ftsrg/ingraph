import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.nodes.unary.MapperNode
import hu.bme.mit.ire.util.TestUtil._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class MapperTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "MapperNode" must {
    "map values" in {
      val changeSet = ChangeSet(positive=Vector(tuple(0, "something")), negative=Vector(tuple(0, "something else")))
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val function = (n: Any) => n match {
        case s: String => s.length
      }
      val checker = system.actorOf(Props(new MapperNode(echoActor ! _, function, 1)))

      checker ! changeSet
      expectMsg(ChangeSet(
        positive = Vector(tuple(0, "something".length)),
        negative = Vector(tuple(0, "something else".length))
      ))
    }
  }
}
