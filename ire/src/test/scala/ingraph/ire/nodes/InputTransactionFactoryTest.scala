package ingraph.ire.nodes

import akka.actor.{ActorSystem, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import ingraph.ire.inputs.InputTransactionFactory
import ingraph.ire.messages.ChangeSet
import ingraph.ire.util.TestUtil._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class InputTransactionFactoryTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {
  def this() = this(ActorSystem())

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "InputTransactionFactory" must {

    "send incoming data after subscription" in {
      val input = new InputTransactionFactory
      val echoActor = system.actorOf(TestActors.echoActorProps)
      input.subscribe(Map("test" -> (echoActor ! _)))
      val inputTransaction = input.newInputTransaction
      inputTransaction.add("test", tuple(6, 1L))
      inputTransaction.add("test", tuple(6, 2L))
      inputTransaction.sendAll
      expectMsg(ChangeSet(positive = tupleBag(tuple(6, 2), tuple(6, 1))))
    }

    "do no splitting in batch" in {
      val input = new InputTransactionFactory
      val echoActor = system.actorOf(TestActors.echoActorProps)
      input.subscribe(Map("test" -> (echoActor ! _)))
      val inputTransaction = input.newInputTransaction
      for (i <- 1 to 3) {
        inputTransaction.add("test", tuple(6, i))
      }
      inputTransaction.sendAll
      expectMsg(ChangeSet(positive = tupleBag(tuple(6, 3), tuple(6, 2), tuple(6, 1))))
    }

  }

}
