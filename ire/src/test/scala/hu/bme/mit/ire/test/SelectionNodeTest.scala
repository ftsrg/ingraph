
package hu.bme.mit.ire.test

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.nodes.unary.{EqualityNode, InequalityNode, SelectionNode}
import hu.bme.mit.ire.util.TestUtil._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
import akka.actor.actorRef2Scala

class SelectionNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "Selection" must {
    "check the condition properly" in {
      val changeSet = ChangeSet(
        positive = tupleBag(tuple(0, "something"), tuple(0, "something else"))
      )
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val condition = (n: Tuple) => {
        n(1) == "something"
      }
      val checker = system.actorOf(Props(new SelectionNode(echoActor ! _, condition)))

      checker ! changeSet
      expectMsg(ChangeSet(positive = tupleBag(tuple(0, "something"))))
    }
  }
  "EqualityChecker" must {
    "check equality properly" in {
      val changeSet = ChangeSet(
        positive = tupleBag(tuple(0, 2, 1), tuple(0, 0, 0), tuple(0, 2, 0))
      )
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val equalityChecker = system.actorOf(Props(new EqualityNode(echoActor ! _, 0, mask(1, 2))))

      equalityChecker ! changeSet
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(0, 0, 0))
      ))
    }
  }
  "InequalityChecker" must {
    "check inequality properly" in {
      val changeSet = ChangeSet(
        positive = tupleBag(tuple(0, 2, 1), tuple(0, 3, 0), tuple(0, 0, 0))
      )
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val inequalityChecker = system.actorOf(Props(new InequalityNode(echoActor ! _, 0, mask(1, 2))))

      inequalityChecker ! changeSet
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(0, 2, 1))
      ))
    }
  }
}
