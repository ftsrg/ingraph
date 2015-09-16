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
      val input1 = system.actorOf(Props(new Checker(production ! _, c => true)))
      input1 ! ChangeSet(positive = Vector(Vector(15)))
      input1 ! ChangeSet(positive = Vector(Vector(19)))
      val input2 = system.actorOf(Props(new Checker(intermediary ! _, c => true)))
      input2 ! ChangeSet(positive = Vector(Vector(25)))
      input2 ! ChangeSet(positive = Vector(Vector(29)))
      val input3 = system.actorOf(Props(new Checker(intermediary ! _, c => true)))
      val terminator = Terminator(List(input3 ! _, input1 ! _, input2 ! _), production)
      val future = terminator.send
      input1 ! ChangeSet(positive = Vector(Vector(16)))
      input1 ! ChangeSet(positive = Vector(Vector(17)))
      input2 ! ChangeSet(positive = Vector(Vector(26)))
      input2 ! ChangeSet(positive = Vector(Vector(27)))
      val expected = Set(Vector(15),Vector(19), Vector(25), Vector(29))
      assert(Await.result(future, Duration(1,HOURS)) == expected)
    }
  }
  "beta nodes" must {
    "propagate terminator messages" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val production = system.actorOf(Props(new Production("")))
      val intermediary = system.actorOf(Props(new HashJoiner(production ! _,1,Vector(0),1,Vector(0))))
      val input1 = system.actorOf(Props(new HashJoiner(intermediary ! Secondary(_),1,Vector(0), 1,Vector(0))))
      input1 ! Primary(ChangeSet(positive = Vector(Vector(15))))
      input1 ! Secondary(ChangeSet(positive = Vector(Vector(15))))
      intermediary ! Primary(ChangeSet(positive = Vector(Vector(15))))
      val input2 = system.actorOf(Props(new HashJoiner(intermediary ! Secondary(_),1,Vector(0), 1,Vector(0))))
      input2 ! Primary(ChangeSet(positive = Vector(Vector(25))))
      input2 ! Secondary(ChangeSet(positive = Vector(Vector(25))))
      intermediary ! Primary(ChangeSet(positive = Vector(Vector(25))))
      val terminator = Terminator(List(input1 ! _, input2 ! _), production)
      val expected = Set(Vector(15), Vector(25))
      val future = terminator.send
      input1 ! Primary(ChangeSet(positive = Vector(Vector(16))))
      input1 ! Secondary(ChangeSet(positive = Vector(Vector(16))))
      intermediary ! Primary(ChangeSet(positive = Vector(Vector(16))))
      assert(Await.result(future, Duration(1,HOURS)) == expected)
    }
  }
}
