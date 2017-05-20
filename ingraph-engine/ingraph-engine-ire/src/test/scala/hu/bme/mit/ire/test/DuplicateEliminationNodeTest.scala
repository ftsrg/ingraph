package hu.bme.mit.ire.test

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.util.TestUtil._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
import hu.bme.mit.ire.nodes.unary.DuplicateEliminationNode
import akka.actor.actorRef2Scala

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
        positive = tupleBag(tuple(3, 4))
      ))
      
      duplicateElimination ! ChangeSet(
        positive = tupleBag(tuple(5, 6)),
        negative = tupleBag(tuple(1, 2))
      )
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(5, 6))
      ))

      duplicateElimination ! ChangeSet(
        positive = tupleBag(tuple(7, 8)),
        negative = tupleBag(tuple(1, 2))
      )
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(7, 8)),
        negative = tupleBag(tuple(1, 2))        
      ))
    }
    
    "do simple duplicate elimination 1" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val duplicateElimination = system.actorOf(Props(new DuplicateEliminationNode(echoActor ! _)))

      duplicateElimination ! ChangeSet(positive = tupleBag(tuple(1)))
      expectMsg(ChangeSet(positive = tupleBag(tuple(1))))

      duplicateElimination ! ChangeSet(positive = tupleBag(tuple(1)))
      duplicateElimination ! ChangeSet(positive = tupleBag(tuple(1)))
     
      duplicateElimination ! ChangeSet(negative = tupleBag(tuple(1)))
      duplicateElimination ! ChangeSet(negative = tupleBag(tuple(1)))
      duplicateElimination ! ChangeSet(negative = tupleBag(tuple(1)))
      expectMsg(ChangeSet(negative = tupleBag(tuple(1))))
    }

  }
}
