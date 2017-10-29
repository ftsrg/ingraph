package hu.bme.mit.ire.nodes.unary

import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.IncrementalChangeSet
import hu.bme.mit.ire.nodes.unary.aggregation.{AggregationNode, StatefulMax}
import hu.bme.mit.ire.util.TestUtil._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class MaxNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "Max" must {
    "do simple max 0" in {
      val changeSet = IncrementalChangeSet(
        positive = tupleBag(tuple("a", 1), tuple("a", 2), tuple("a", 1.1), tuple("b", 3))
      )
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val max = system.actorOf(Props(new AggregationNode(
        echoActor ! _, functionMask(0), () => Vector(new StatefulMax(1)), functionMask(0, 1))))

      max ! changeSet
      expectMsg(IncrementalChangeSet(
        positive = tupleBag(tuple("a", 2), tuple("b", 3))
      ))

      max ! IncrementalChangeSet(
        negative = tupleBag(tuple("a", 2))
      )
      expectMsg(IncrementalChangeSet(
        positive = tupleBag(tuple("a", 1.1)),
        negative = tupleBag(tuple("a", 2))
      ))
    }
  }
}
