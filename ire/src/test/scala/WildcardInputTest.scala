import akka.actor.{ActorSystem, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.incquerydcore.{ChangeSet, WildcardInput}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

/**
 * Created by janosmaginecz on 05/05/15.
 */
class WildcardInputTest (_system: ActorSystem) extends TestKit(_system) with ImplicitSender
with WordSpecLike with Matchers with BeforeAndAfterAll {
  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "WildcardInput" must {

    "send incoming data after subscription" in {
      val input = new WildcardInput(messageSize = 4)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      input.subscribe(Map("test" -> (echoActor ! _)))
      val tran = input.newTransaction()
      tran.add(6L, "test", 1L)
      tran.add(6L, "test", 2L)
      input.processTransaction(tran)
      expectMsg(ChangeSet(positive = Vector(Vector(6, 2), Vector(6, 1))))
    }
    "do grouping" in {
      val input = new WildcardInput(messageSize = 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      input.subscribe(Map("test" -> (echoActor ! _)))
      val tran = input.newTransaction()
      for (i <- 1 to 3) {
        tran.add(6L, "test", i)
      }
      input.processTransaction(tran)
      expectMsg(ChangeSet(positive = Vector(Vector(6, 2), Vector(6, 1))))
      expectMsg(ChangeSet(positive = Vector(Vector(6, 3))))
    }
  }
}
