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
        positive = tupleBag(tuple(15, 16, 17, 18), tuple(4, 5, 6, 7)),
        negative = tupleBag(tuple(-0, -1, -2, -3), tuple(-10, -11, -12, -13))
      )
      val selectionMask = mask(0, 2)
      val expectedChanges = ChangeSet(
        positive = tupleBag(tuple(15, 17), tuple(4, 6)),
        negative = tupleBag(tuple(-0, -2), tuple(-10, -12))
      )
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val selector = system.actorOf(Props(new ProjectionNode(echoActor ! _, selectionMask)), name = "testSelector")

      selector ! changes
      expectMsg(expectedChanges)
      selector ! changes
      expectMsg(expectedChanges)
    }

    val changeSet = ChangeSet(
      positive = tupleBag(tuple(0, "something")),
      negative = tupleBag(tuple(0, "something else"))
    )

    "do projection with equal length" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val checker = system.actorOf(Props(new ProjectionNode(echoActor ! _, mask(1, 0)))) // swap attributes

      checker ! changeSet
      expectMsg(ChangeSet(
        positive = tupleBag(tuple("something", 0)),
        negative = tupleBag(tuple("something else", 0))
      ))
    }

    "do projection with lesser length" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val checker = system.actorOf(Props(new ProjectionNode(echoActor ! _, mask(1))))

      checker ! changeSet
      expectMsg(ChangeSet(
        positive = tupleBag(tuple("something")),
        negative = tupleBag(tuple("something else"))
      ))
    }
  }
}
