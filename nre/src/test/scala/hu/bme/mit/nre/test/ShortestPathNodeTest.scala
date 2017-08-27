package hu.bme.mit.nre.test

import akka.testkit.TestKit
import akka.testkit.ImplicitSender
import org.scalatest.{BeforeAndAfterAll, Ignore, Matchers, WordSpecLike}
import akka.actor.ActorSystem
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.nodes.ternary.ShortestPathNode
import akka.actor.Props
import hu.bme.mit.ire.messages.Ternary
import hu.bme.mit.ire.messages.Primary
import akka.testkit.TestActors
import hu.bme.mit.ire.util.TestUtil.tuple
import hu.bme.mit.ire.util.TestUtil.tupleBag
import hu.bme.mit.ire.messages.Secondary
import hu.bme.mit.ire.datatypes.Path

@Ignore class ShortestPathNodeTest (_system: ActorSystem) extends TestKit(_system) with ImplicitSender
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

      shortestPathNode ! Ternary(ChangeSet(
        positive = tupleBag(tuple(1, 100, 2), tuple(2, 101, 3))
      ))
      expectNoMsg

      shortestPathNode ! Primary(ChangeSet(
        positive = tupleBag(tuple(1))
      ))
      expectNoMsg

      shortestPathNode ! Secondary(ChangeSet(
        positive = tupleBag(tuple(2), tuple(3))
      ))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(1, 2, Path(100)), tuple(1, 3, Path(100, 101)))
      ))

      shortestPathNode ! Ternary(ChangeSet(
        positive = tupleBag(tuple(1, 102, 3))
      ))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(1, 3, Path(102))),
        negative = tupleBag(tuple(1, 3, Path(100, 101)))
      ))
    }
  }

}
