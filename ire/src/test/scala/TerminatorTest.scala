import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
import scala.concurrent.Await
import scala.concurrent.duration.{Duration, _}
import hu.bme.mit.incquerydcore._
import utils.ReteNode
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
      import hu.bme.mit.incquerydcore.utils
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val production = system.actorOf(Props(new Production("alpha test", 2)))
      val intermediary = system.actorOf(Props(new Checker(production ! _, c => true, expectedTerminatorCount = 2)))
      val input1 = system.actorOf(Props(new Checker(production ! _, c => true)))
      input1 ! ChangeSet(positive = Vector(Vector(15)))
      input1 ! ChangeSet(positive = Vector(Vector(19)))
      val input2 = system.actorOf(Props(new Checker(intermediary ! _, c => true)))
      input2 ! ChangeSet(positive = Vector(Vector(25)))
      input2 ! ChangeSet(positive = Vector(Vector(29)))
      val input3 = system.actorOf(Props(new Checker(intermediary ! _, c => true)))

      val terminator = Terminator(List(
        input1 ! _, input2 ! _, input3 ! _
      ), production)
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
      val production = system.actorOf(Props(new Production("")), "Production")
      val checker = system.actorOf(Props(new Checker(production ! _, c => true)), "checker")
      val intermediary = system.actorOf(Props(new HashJoiner(checker ! _,1,Vector(0),1,Vector(0))), "intermediary")
      val input1 = system.actorOf(Props(new HashJoiner(intermediary ! Primary(_),1,Vector(0), 1,Vector(0))), "inputBeta")
      val msg15 = ChangeSet(positive = Vector(Vector(15)))
      input1 ! Primary(msg15)
      input1 ! Secondary(msg15)
      intermediary ! Secondary(msg15)
      val msg25 = ChangeSet(positive = Vector(Vector(25)))
      input1 ! Primary(msg25)
      input1 ! Secondary(msg25)
      intermediary ! Secondary(msg25)

      val terminator = Terminator(List(input1.primary, input1.secondary, intermediary.secondary), production)
      val future = terminator.send
      input1 ! Primary(ChangeSet(positive = Vector(Vector(16))))
      input1 ! Secondary(ChangeSet(positive = Vector(Vector(16))))
      intermediary ! Secondary(ChangeSet(positive = Vector(Vector(16))))

      assert(Await.result(future, Duration(1,HOURS)) == Set(Vector(15), Vector(25)))
      assert(Await.result(terminator.send, Duration(1,HOURS)) == Set(Vector(15), Vector(25), Vector(16)))
      (1 to 500).foreach( i => {
        input1 ! Secondary(ChangeSet(negative = Vector(Vector(16))))
        assert(Await.result(terminator.send, Duration(1,HOURS)) == Set(Vector(15), Vector(25)))
        input1 ! Secondary(ChangeSet(positive = Vector(Vector(16))))
        intermediary ! Secondary(ChangeSet(negative = Vector(Vector(15))))
        assert(Await.result(terminator.send, Duration(1,HOURS)) == Set(Vector(25), Vector(16)))
        intermediary ! Secondary(ChangeSet(positive = Vector(Vector(15))))
        assert(Await.result(terminator.send, Duration(1,HOURS)) == Set(Vector(15), Vector(25), Vector(16)))
      })
    }
  }
  "node splitting" should {
    "work" in {

    }
  }
}
