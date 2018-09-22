package ingraph.ire.nodes.unary

import akka.actor.{ActorSystem, Props, actorRef2Scala}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import ingraph.ire.datatypes.Tuple
import ingraph.ire.messages.ChangeSet
import ingraph.ire.util.TestUtil._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class SortAndTopNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }
  val selectionMask = Vector((v: Tuple) => v(0), (v: Tuple) => v(1))
  "Sort" should {
    "count with complex keys" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val counter = system.actorOf(Props(
          new SortAndTopNode(echoActor ! _,
              tupleLength = 3,
              selectionMask = selectionMask,
              skip = Some(0),
              limit = Some(2),
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
          selectionMask = selectionMask,
          skip = Some(0),
          limit = Some(2),
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
