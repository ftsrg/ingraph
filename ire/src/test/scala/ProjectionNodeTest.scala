import TestUtil._
import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.nodes.unary.ProjectionNode
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
        positive = Vector(Map(0 -> 15, 2 -> 17), Map(0 -> 4, 2 -> 6)),
        negative = Vector(Map(0 -> -0, 2 -> -2), Map(0 -> -10, 2 -> -12))
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
      val checker = system.actorOf(Props(new ProjectionNode(echoActor ! _, Vector(1, 0))))

      checker ! changeSet
      expectMsg(ChangeSet(positive = Vector(tuple(0, "something")),
        negative = Vector(tuple(0, "something else"))))
    }

    "do projection with lesser length" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val checker = system.actorOf(Props(new ProjectionNode(echoActor ! _, Vector(1))))

      checker ! changeSet
      expectMsg(ChangeSet(positive = Vector(Map(1 -> "something")), negative = Vector(Map(1 -> "something else"))))
    }
  }
}
