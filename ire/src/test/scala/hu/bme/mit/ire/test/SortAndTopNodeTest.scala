package hu.bme.mit.ire.test

import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.nodes.unary._
import hu.bme.mit.ire.util.TestUtil._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}
import akka.actor.actorRef2Scala
import scala.Vector

class SortAndTopNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "Sort" should {
    "count with complex keys" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val counter = system.actorOf(Props(
          new SortAndTopNode(echoActor ! _,
              tupleLength = 3,
              selectionMask = mask(0, 1),
              skip = 0,
              limit = 2,
              ascendingOrder = Vector(true, false))
      ))

      counter ! ChangeSet(positive = tupleBag(tuple(2, 3, 4), tuple(0, 2, 3), tuple(0, 3, 3), tuple(5, 6, 7)))
      expectMsg(ChangeSet(positive = tupleBag(tuple(0, 3, 3), tuple(0, 2, 3))))

      counter ! ChangeSet(negative = tupleBag(tuple(0, 2, 3)))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(0, 3, 3), tuple(2, 3, 4)),
        negative = tupleBag(tuple(0, 3, 3), tuple(0, 2, 3))))
    }
    "have bag semantics" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val counter = system.actorOf(Props(new SortAndTopNode(echoActor ! _,
          tupleLength = 3,
          selectionMask = mask(0, 1),
          skip = 0,
          limit = 2,
          ascendingOrder = Vector(true, true))))

      counter ! ChangeSet(positive = tupleBag(tuple(0, 1, 2), tuple(0, 1, 2), tuple(0, 1, 3)))
      expectMsg(ChangeSet(positive = tupleBag(tuple(0, 1, 2), tuple(0, 1, 2))))

      counter ! ChangeSet(negative = tupleBag(tuple(0, 1, 2)))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(0, 1, 2), tuple(0, 1, 3)),
        negative = tupleBag(tuple(0, 1, 2), tuple(0, 1, 2))))

      counter ! ChangeSet(negative = tupleBag(tuple(0, 1, 2)))
      expectMsg(ChangeSet(
        positive = tupleBag(tuple(0, 1, 3)),
        negative = tupleBag(tuple(0, 1, 2), tuple(0, 1, 3))))
    }
  }
}
