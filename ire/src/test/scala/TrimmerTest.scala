import scala.Vector
import org.scalatest.BeforeAndAfterAll
import org.scalatest.Matchers
import org.scalatest.WordSpecLike
import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.testkit.ImplicitSender
import akka.testkit.TestActors
import akka.testkit.TestKit
import hu.bme.mit.incqueryds.{ChangeSet, Projection, Trimmer}

/**
 * Created by Maginecz on 3/16/2015.
 */

class TrimmerTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A Trimmer" must {
    "select the values" in {
      val changes = ChangeSet(
        positive = Vector(Vector(15, 16, 17, 18), Vector(4, 5, 6, 7)),
        negative = Vector(Vector(-0, -1, -2, -3), Vector(-10, -11, -12, -13))
      )
      val selectionVector = Vector(0, 2)
      val expectedChanges = ChangeSet(
        positive = Vector(Vector(15, 17), Vector(4, 6)),
        negative = Vector(Vector(-0, -2), Vector(-10, -12))
      )
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val selector = system.actorOf(Props(new Trimmer(echoActor ! _, selectionVector)), name = "testSelector")

      selector ! changes
      expectMsg(expectedChanges)
      selector ! changes
      expectMsg(expectedChanges)
    }
    val changeSet = ChangeSet(positive = Vector(Vector(0, "something")), negative = Vector(Vector(0, "something else")))
    "do projection with equal length" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val checker = system.actorOf(Props(new Trimmer(echoActor ! _, Vector(1, 0))))

      checker ! changeSet
      expectMsg(ChangeSet(positive = Vector(Vector("something", 0)), negative = Vector(Vector("something else", 0))))
    }
    "do projection with lesser length" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val checker = system.actorOf(Props(new Trimmer(echoActor ! _, Vector(1))))

      checker ! changeSet
      expectMsg(ChangeSet(positive = Vector(Vector("something")), negative = Vector(Vector("something else"))))
    }
  }
}
