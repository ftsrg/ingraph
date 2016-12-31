import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.nodes.unary.ProjectionNode
import hu.bme.mit.ire.util.TestUtil._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class ProjectionNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "Projection" must {
    "select the values" in {
      val changes = ChangeSet(
        positive = Vector(tuple(15, 16, 17, 18), tuple(4, 5, 6, 7)),
        negative = Vector(tuple(-0, -1, -2, -3), tuple(-10, -11, -12, -13))
      )
      val selectionVector = Vector(0, 2)
      val expectedChanges = ChangeSet(
        positive = Vector(tuple(15, 17), tuple(4, 6)),
        negative = Vector(tuple(-0, -2), tuple(-10, -12))
      )
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val selector = system.actorOf(Props(new ProjectionNode(echoActor ! _, selectionVector)), name = "testSelector")

      selector ! changes
      expectMsg(expectedChanges)
      selector ! changes
      expectMsg(expectedChanges)
    }

    val changeSet = ChangeSet(positive = Vector(tuple(0, "something")),
      negative = Vector(tuple(0, "something else")))

    "do projection with equal length" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val checker = system.actorOf(Props(new ProjectionNode(echoActor ! _, Vector(1, 0)))) // swap attributes

      checker ! changeSet
      expectMsg(ChangeSet(
        positive = Vector(tuple("something", 0)),
        negative = Vector(tuple("something else", 0))
      ))
    }

    "do projection with lesser length" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val checker = system.actorOf(Props(new ProjectionNode(echoActor ! _, Vector(1))))

      checker ! changeSet
      expectMsg(ChangeSet(
        positive = Vector(tuple("something")),
        negative = Vector(tuple("something else"))
      ))
    }
  }
}
