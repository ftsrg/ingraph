package hu.bme.mit.ire.stateless.unary

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.{BatchChangeSet, ChangeSet}
import hu.bme.mit.ire.util.TestUtil.{tuple, tupleBag}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class DuplicateEliminationNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "DuplicateElimination node" must {
    "do simple duplicate elimination 0" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val duplicateElimination = system.actorOf(Props(new DuplicateEliminationNode(echoActor ! _)))

      duplicateElimination ! BatchChangeSet(tupleBag(tuple(1, 2)))
      expectMsg(BatchChangeSet(tupleBag(tuple(1, 2))))

      duplicateElimination ! BatchChangeSet(tupleBag(tuple(1, 2), tuple(3, 4)))
      expectMsg(BatchChangeSet(tupleBag(tuple(1, 2), tuple(3, 4))))
    }

    "do simple duplicate elimination 1" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val duplicateElimination = system.actorOf(Props(new DuplicateEliminationNode(echoActor ! _)))

      duplicateElimination ! BatchChangeSet(tupleBag(tuple(1)))
      expectMsg(BatchChangeSet(tupleBag(tuple(1))))

      duplicateElimination ! BatchChangeSet(tupleBag(tuple(1), tuple(1)))
      expectMsg(BatchChangeSet(tupleBag(tuple(1))))
    }

  }
}
