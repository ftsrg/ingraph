import Workers.nodeType
import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
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
      val changeSet = ChangeSet(Vector(Vector(0, "something"), Vector(0, "something else")))
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val condition = (n: nodeType) => {
        n(1) == "something"
      }
      val checker = system.actorOf(Props(new Checker(echoActor ! _, condition)))

      checker ! changeSet
      expectMsg(ChangeSet(positive = Vector(Vector(0, "something"))))
    }
  }
  "EqualityChecker" must {
    "check equality properly" in {
      val changeSet = ChangeSet(Vector(Vector(0, 2, 1), Vector(0, 0, 0), Vector(0, 2, 0)))
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val equalityChecker = system.actorOf(Props(new EqualityChecker(echoActor ! _, 0, Vector(1, 2))))

      equalityChecker ! changeSet
      expectMsg(ChangeSet(Vector(Vector(0, 0, 0))))
    }
  }
  "InequalityChecker" must {
    "check inequality properly" in {
      val changeSet = ChangeSet(Vector(Vector(0, 2, 1), Vector(0, 3, 0), Vector(0, 0, 0)))
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val inequalityChecker = system.actorOf(Props(new InequalityChecker(echoActor ! _, 0, Vector(1, 2))))

      inequalityChecker ! changeSet
      expectMsg(ChangeSet(Vector(Vector(0, 2, 1))))
    }
  }
}
