package hu.bme.mit.ire.nodes

import akka.actor.{ActorSystem, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.InputMultiplexerFactory
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.util.TestUtil._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class InputMultiplexerFactoryTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {
  def this() = this(ActorSystem())

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "InputMultiplexerFactory" must {

    "send incoming data after subscription" in {
      val input = new InputMultiplexerFactory
      val echoActor = system.actorOf(TestActors.echoActorProps)
      input.subscribe(Map("test" -> (echoActor ! _)))
      val inputMultiplexer = input.newInputMultiplexer
      inputMultiplexer.add("test", tuple(6, 1L))
      inputMultiplexer.add("test", tuple(6, 2L))
      inputMultiplexer.close
      expectMsg(ChangeSet(positive = tupleBag(tuple(6, 2), tuple(6, 1))))
    }

    "do no splitting in batch" in {
      val input = new InputMultiplexerFactory
      val echoActor = system.actorOf(TestActors.echoActorProps)
      input.subscribe(Map("test" -> (echoActor ! _)))
      val inputMultiplexer = input.newInputMultiplexer
      for (i <- 1 to 3) {
        inputMultiplexer.add("test", tuple(6, i))
      }
      inputMultiplexer.close
      expectMsg(ChangeSet(positive = tupleBag(tuple(6, 3), tuple(6, 2), tuple(6, 1))))
    }

  }

}
