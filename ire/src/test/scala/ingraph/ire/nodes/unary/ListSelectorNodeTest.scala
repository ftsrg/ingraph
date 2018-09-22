package ingraph.ire.nodes.unary

import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import ingraph.ire.messages.ChangeSet
import ingraph.ire.util.TestUtil._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class ListSelectorNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "ListSelector" must {
    "do select items in lists 0" in {
      val changeSet = ChangeSet(
        positive = tupleBag(tuple(List("a","b","c")))
      )

      val function = (n: Any) => n match {
        case s: List[Any] => s(1)
      }
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val listSelector = system.actorOf(Props(new MapperNode(echoActor ! _, function, 0)))

      listSelector ! changeSet
      expectMsg(ChangeSet(positive = tupleBag(tuple("b"))))
    }
  }
}
