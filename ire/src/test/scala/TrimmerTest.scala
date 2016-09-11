import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.incqueryds.{ChangeSet, Trimmer}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

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
        positive = Vector(Map(0 -> 15, 1 -> 16, 2 -> 17, 3 -> 18), Map(0 -> 4, 1 -> 5, 2 -> 6, 3 -> 7)),
        negative = Vector(Map(0 -> -0, 1 -> -1, 2 -> -2, 3 -> -3), Map(0 -> -10, 1 -> -11, 2 -> -12, 3 -> -13))
      )
      val selectionVector = Vector(0, 2)
      val expectedChanges = ChangeSet(
        positive = Vector(Map(0 -> 15, 2 -> 17), Map(0 -> 4, 2 -> 6)),
        negative = Vector(Map(0 -> -0, 2 -> -2), Map(0 -> -10, 2 -> -12))
      )
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val selector = system.actorOf(Props(new Trimmer(echoActor ! _, selectionVector)), name = "testSelector")

      selector ! changes
      expectMsg(expectedChanges)
      selector ! changes
      expectMsg(expectedChanges)
    }

    val changeSet = ChangeSet(positive = Vector(Map(0 -> 0, 1 -> "something")),
      negative = Vector(Map(0 -> 0, 1 -> "something else")))

    "do projection with equal length" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val checker = system.actorOf(Props(new Trimmer(echoActor ! _, Vector(1, 0))))

      checker ! changeSet
      expectMsg(ChangeSet(positive = Vector(Map(1 -> "something", 0  -> 0)),
        negative = Vector(Map(1 -> "something else", 0 -> 0))))
    }

    "do projection with lesser length" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val checker = system.actorOf(Props(new Trimmer(echoActor ! _, Vector(1))))

      checker ! changeSet
      expectMsg(ChangeSet(positive = Vector(Map(1 -> "something")), negative = Vector(Map(1 -> "something else"))))
    }
  }
}
