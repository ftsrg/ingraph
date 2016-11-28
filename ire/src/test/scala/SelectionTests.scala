
import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
import TestUtil._

/**
 * Created by Maginecz on 4/10/2015.
 */
class SelectionTests(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "Selection" must {
    "check the condition properly" in {
      val changeSet = ChangeSet(Vector(tuple(0, "something"), tuple(0, "something else")))
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val condition = (n: TupleType) => {
        n(1) == "something"
      }
      val checker = system.actorOf(Props(new SelectionNode(echoActor ! _, condition)))

      checker ! changeSet
      expectMsg(ChangeSet(positive = Vector(tuple(0, "something"))))
    }
  }
  "EqualityChecker" must {
    "check equality properly" in {
      val changeSet = ChangeSet(Vector(tuple(0, 2, 1), tuple(0, 0, 0), tuple(0, 2, 0)))
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val equalityChecker = system.actorOf(Props(new Equality(echoActor ! _, 0, Vector(1, 2))))

      equalityChecker ! changeSet
      expectMsg(ChangeSet(Vector(tuple(0, 0, 0))))
    }
  }
  "InequalityChecker" must {
    "check inequality properly" in {
      val changeSet = ChangeSet(Vector(tuple(0, 2, 1), tuple(0, 3, 0), tuple(0, 0, 0)))
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val inequalityChecker = system.actorOf(Props(new InequalityNode(echoActor ! _, 0, Vector(1, 2))))

      inequalityChecker ! changeSet
      expectMsg(ChangeSet(Vector(tuple(0, 2, 1))))
    }
  }
}
