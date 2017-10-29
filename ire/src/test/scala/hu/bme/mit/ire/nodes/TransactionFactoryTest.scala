package hu.bme.mit.ire.nodes

import akka.actor.{ActorSystem, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.TransactionFactory
import hu.bme.mit.ire.messages.IncrementalChangeSet
import hu.bme.mit.ire.util.TestUtil._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class TransactionFactoryTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {
  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "TransactionFactory" must {

    "send incoming data after subscription" in {
      val input = new TransactionFactory(messageSize = 4)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      input.subscribe(Map("test" -> (echoActor ! _)))
      val tran = input.newBatchTransaction()
      tran.add("test", tuple(6, 1L))
      tran.add("test", tuple(6, 2L))
      tran.close()
      expectMsg(IncrementalChangeSet(positive = tupleBag(tuple(6, 2), tuple(6, 1))))
    }

    "do no splitting in batch" in {
      val input = new TransactionFactory(messageSize = 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      input.subscribe(Map("test" -> (echoActor ! _)))
      val tran = input.newBatchTransaction()
      for (i <- 1 to 3) {
        tran.add("test", tuple(6, i))
      }
      tran.close()
      expectMsg(IncrementalChangeSet(positive = tupleBag(tuple(6, 3), tuple(6, 2), tuple(6, 1))))
    }
    "send messageSize sized messages when using continuous transactions" in {
      val input = new TransactionFactory(messageSize = 2)
      val echoActor = system.actorOf(TestActors.echoActorProps)
      input.subscribe(Map("test" -> (echoActor ! _)))
      val tran = input.newContinousTransaction()
      for (i <- 1 to 3) {
        tran.add("test", tuple(6, i))
      }
      tran.close()
      expectMsg(IncrementalChangeSet(positive = tupleBag(tuple(6, 2), tuple(6, 1))))
      expectMsg(IncrementalChangeSet(positive = tupleBag(tuple(6, 3))))
    }
  }
}
