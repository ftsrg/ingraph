package ingraph.ire.stateless.unary

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import ingraph.ire.messages.ChangeSet
import ingraph.ire.util.TestUtil.{tuple, tupleBag}
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

      duplicateElimination ! ChangeSet(
        positive = tupleBag(tuple(1, 2))
      )
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(1, 2))
      ))

      duplicateElimination ! ChangeSet(
        positive = tupleBag(tuple(1, 2), tuple(3, 4))
      )
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(1, 2), tuple(3, 4))
      ))
    }

    "do simple duplicate elimination 1" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val duplicateElimination = system.actorOf(Props(new DuplicateEliminationNode(echoActor ! _)))

      duplicateElimination ! ChangeSet(positive = tupleBag(tuple(1)))
      expectMsg(ChangeSet(positive = tupleBag(tuple(1))))

      duplicateElimination ! ChangeSet(positive = tupleBag(tuple(1), tuple(1)))
      expectMsg(ChangeSet(positive = tupleBag(tuple(1))))
    }

  }
}
