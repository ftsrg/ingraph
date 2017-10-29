
package hu.bme.mit.ire.nodes.unary

import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.datatypes.Tuple
import hu.bme.mit.ire.messages.IncrementalChangeSet
import hu.bme.mit.ire.util.TestUtil._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class SelectionNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "Selection" must {
    "check the condition properly" in {
      val changeSet = IncrementalChangeSet(
        positive = tupleBag(tuple(0, "something"), tuple(0, "something else"))
      )
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val condition = (n: Tuple) => {
        n(1) == "something"
      }
      val checker = system.actorOf(Props(new SelectionNode(echoActor ! _, condition)))

      checker ! changeSet
      expectMsg(IncrementalChangeSet(positive = tupleBag(tuple(0, "something"))))
    }
  }
}
