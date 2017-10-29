package hu.bme.mit.ire.nodes.unary

import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.IncrementalChangeSet
import hu.bme.mit.ire.util.TestUtil._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class ListSelectorNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "ListSelector" must {
    "do select items in lists 0" in {
      val changeSet = IncrementalChangeSet(
        positive = tupleBag(tuple(List("a","b","c")))
      )

      val function = (n: Any) => n match {
        case s: List[Any] => s(1)
      }
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val listSelector = system.actorOf(Props(new MapperNode(echoActor ! _, function, 0)))

      listSelector ! changeSet
      expectMsg(IncrementalChangeSet(positive = tupleBag(tuple("b"))))
    }
  }
}
