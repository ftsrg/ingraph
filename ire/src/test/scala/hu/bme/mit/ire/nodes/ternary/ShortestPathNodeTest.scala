package hu.bme.mit.ire.nodes.ternary

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.datatypes.Path
import hu.bme.mit.ire.messages.{IncrementalChangeSet, Primary, Secondary, Ternary}
import hu.bme.mit.ire.util.TestUtil.{tuple, tupleBag}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class ShortestPathNodeTest (_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  val src = 0
  val trg = 2
  val edge = 1

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "A ShortestPathNode" must {
    "calculate shortest path" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val shortestPathNode = system.actorOf(Props(new ShortestPathNode(echoActor ! _, src, trg, edge)))

      shortestPathNode ! Ternary(IncrementalChangeSet(
        positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))
      ))
      expectNoMsg

      shortestPathNode ! Primary(IncrementalChangeSet(
        positive = tupleBag(tuple(1))
      ))
      expectNoMsg

      shortestPathNode ! Secondary(IncrementalChangeSet(
        positive = tupleBag(tuple(2), tuple(3))
      ))
      expectMsg(IncrementalChangeSet(
        positive = tupleBag(tuple(1, 2, Path(100)), tuple(1, 3, Path(100, 101)))
      ))

      shortestPathNode ! Ternary(IncrementalChangeSet(
        positive = tupleBag(tuple(1, 102, 3))
      ))
      expectMsg(IncrementalChangeSet(
        positive = tupleBag(tuple(1, 3, Path(102))),
        negative = tupleBag(tuple(1, 3, Path(100, 101)))
      ))
    }
  }

}
