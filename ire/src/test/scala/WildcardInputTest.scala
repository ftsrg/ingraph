import akka.actor.{ActorSystem, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.incqueryds.{ChangeSet, WildcardInput}
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
      val tran = input.newBatchTransaction()
      tran.add(6L, "test", 1L)
      tran.add(6L, "test", 2L)
      tran.close()
      expectMsg(ChangeSet(positive = Vector(Vector(6, 2), Vector(6, 1))))
    }
    "do no splitting in batch" in {
      val input = new WildcardInput(messageSize = 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      input.subscribe(Map("test" -> (echoActor ! _)))
      val tran = input.newBatchTransaction()
      for (i <- 1 to 3) {
        tran.add(6L, "test", i)
      }
      tran.close()
      expectMsg(ChangeSet(positive = Vector(Vector(6, 3), Vector(6, 2), Vector(6, 1))))
    }
    "send messageSize sized messages when using continuous transactions" in {
      val input = new WildcardInput(messageSize = 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      input.subscribe(Map("test" -> (echoActor ! _)))
      val tran = input.newContinousTransaction()
      for (i <- 1 to 3) {
        tran.add(6L, "test", i)
      }
      tran.close()
      expectMsg(ChangeSet(positive = Vector(Vector(6, 2), Vector(6, 1))))
      expectMsg(ChangeSet(positive = Vector(Vector(6, 3))))
    }
  }
}
