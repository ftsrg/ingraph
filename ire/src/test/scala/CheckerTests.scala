
import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}


/**
 * Created by Maginecz on 4/10/2015.
 */
class CheckerTests(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "Checker" must {
    "check the condition properly" in {
      val changeSet = ChangeSet(Vector(Map(0 -> 0, 1 -> "something"), Map(0 -> 0, 1 -> "something else")))
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val condition = (n: nodeType) => {
        n(1) == "something"
      }
      val checker = system.actorOf(Props(new Checker(echoActor ! _, condition)))

      checker ! changeSet
      expectMsg(ChangeSet(positive = Vector(Map(0 -> 0, 1 -> "something"))))
    }
  }
  "EqualityChecker" must {
    "check equality properly" in {
      val changeSet = ChangeSet(Vector(Map(0 -> 0, 1 -> 2, 2 -> 1), Map(0 -> 0, 1 -> 0, 2 -> 0), Map(0 -> 0, 1 -> 2, 2 -> 0)))
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val equalityChecker = system.actorOf(Props(new Equality(echoActor ! _, 0, Vector(1, 2))))

      equalityChecker ! changeSet
      expectMsg(ChangeSet(Vector(Map(0 -> 0, 1 -> 0, 2 -> 0))))
    }
  }
  "InequalityChecker" must {
    "check inequality properly" in {
      val changeSet = ChangeSet(Vector(Map(0 -> 0, 1 -> 2, 2 -> 1), Map(0 -> 0, 1 -> 3, 2 -> 0), Map(0 -> 0, 1 -> 0, 2 -> 0)))
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val inequalityChecker = system.actorOf(Props(new Inequality(echoActor ! _, 0, Vector(1, 2))))

      inequalityChecker ! changeSet
      expectMsg(ChangeSet(Vector(Map(0 -> 0, 1 -> 2, 2 -> 1))))
    }
  }
}
