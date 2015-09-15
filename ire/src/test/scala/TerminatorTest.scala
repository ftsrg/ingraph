import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
import scala.concurrent.Await
import scala.concurrent.duration.{Duration, _}
import hu.bme.mit.incquerydcore.Production
import hu.bme.mit.incquerydcore.HashJoiner
import hu.bme.mit.incquerydcore.Terminator
import hu.bme.mit.incquerydcore.Checker
import hu.bme.mit.incquerydcore.Secondary
import hu.bme.mit.incquerydcore.Primary
import hu.bme.mit.incquerydcore.ChangeSet

/**
 * Created by janosmaginecz on 10/05/15.
 */
class TerminatorTest (_system: ActorSystem) extends TestKit(_system) with ImplicitSender
with WordSpecLike with Matchers with BeforeAndAfterAll {
  def this() = this(ActorSystem("MySpec"))
  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }
  "alpha nodes" must {
    "propagate terminator messages" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val production = system.actorOf(Props(new Production("")))
      val intermediary = system.actorOf(Props(new Checker(production ! _, c => true)))
      val input = system.actorOf(Props(new Checker(intermediary ! _, c => true)))
      input ! ChangeSet(positive = Vector(Vector(15)))
      input ! ChangeSet(positive = Vector(Vector(19)))
      val terminator = Terminator(List(input ! _), production)
      val future = terminator.send
      input ! ChangeSet(positive = Vector(Vector(16)))
      input ! ChangeSet(positive = Vector(Vector(17)))
      val expected = Set(Vector(15),Vector(19))
      Await.result(future, Duration(1,SECONDS)) == expected
    }
  }
  "beta nodes" must {
    "propagate terminator messages" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val production = system.actorOf(Props(new Production("")))
      val intermediary = system.actorOf(Props(new HashJoiner(production ! _,1,Vector(0),1,Vector(0))))
      val input = system.actorOf(Props(new HashJoiner(intermediary ! Secondary(_),1,Vector(0), 1,Vector(0))))
      input ! Primary(ChangeSet(positive = Vector(Vector(15))))
      input ! Secondary(ChangeSet(positive = Vector(Vector(15))))
      intermediary ! Primary(ChangeSet(positive = Vector(Vector(15))))
      val terminator = Terminator(List(input ! _), production)
      val expected = Set(Vector(15))
      val future = terminator.send
      input ! Primary(ChangeSet(positive = Vector(Vector(16))))
      input ! Secondary(ChangeSet(positive = Vector(Vector(16))))
      intermediary ! Primary(ChangeSet(positive = Vector(Vector(16))))
      Await.result(future, Duration(1,SECONDS)) == expected
    }
  }
}
