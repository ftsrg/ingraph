package hu.bme.mit.ire.util

import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.IncrementalChangeSet
import hu.bme.mit.ire.nodes.unary.MapperNode
import hu.bme.mit.ire.util.TestUtil._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class MapperTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "MapperNode" must {
    "map values" in {
      val changeSet = IncrementalChangeSet(
        positive = tupleBag(tuple(0, "something")),
        negative = tupleBag(tuple(0, "something else"))
      )
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val function = (n: Any) => n match {
        case s: String => s.length
      }
      val checker = system.actorOf(Props(new MapperNode(echoActor ! _, function, 1)))

      checker ! changeSet
      expectMsg(IncrementalChangeSet(
        positive = tupleBag(tuple(0, "something".length)),
        negative = tupleBag(tuple(0, "something else".length))
      ))
    }
  }
}
