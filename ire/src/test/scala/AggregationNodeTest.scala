import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.datatypes.TupleType
import hu.bme.mit.ire.messages.ChangeSet
import hu.bme.mit.ire.nodes.unary.{CountNode, SumNode}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

import scala.concurrent.duration.Duration

class AggregationNodeTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  val odin: TupleType = Map("city" -> "Asgard", "name" -> "Odin", "weapon" -> "Gungnir", "sex" -> "male", "height" -> 1)
  val thor: TupleType = Map(
    "city" -> "Asgard", "name" -> "Thor", "weapon" -> "Mjölnir", "sex" -> "male", "height" -> 1.1f)
  val freya: TupleType =
    Map("city" -> "Asgard", "name" -> "Freya", "sex" -> "female", "height" -> 1.1)
  val geirröd: TupleType = Map("city" -> "Jötunheimr", "name" -> "Geirröd ", "sex" -> "male", "height" -> 2L)
  val ragnar: TupleType = Map(
    "city" -> "Midgard", "name" -> "Ragnarr Loðbrók", "sex" -> "male", "height" -> 0.8)

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  "Count" must {
    "count with complex keys" in {

      val echoActor = system.actorOf(TestActors.echoActorProps)
      val counter = system.actorOf(Props(new CountNode(echoActor ! _, Vector("city", "sex"), "count")))
      counter ! ChangeSet(positive = Vector(odin))
      expectMsg(ChangeSet(positive = Vector(Map("sex" -> "male", "city" -> "Asgard", "count" -> 1))))
      counter ! ChangeSet(positive = Vector(thor))
      expectMsg(ChangeSet(positive = Vector(Map("sex" -> "male", "city" -> "Asgard", "count" -> 2)),
        negative = Vector(Map("sex" -> "male", "city" -> "Asgard", "count" -> 1))))
      counter ! ChangeSet(negative = Vector(odin))
      expectMsg(ChangeSet(positive = Vector(Map("sex" -> "male", "city" -> "Asgard", "count" -> 1)),
        negative = Vector(Map("sex" -> "male", "city" -> "Asgard", "count" -> 2))))
      counter ! ChangeSet(positive = Vector(freya))
      expectMsg(ChangeSet(positive = Vector(Map("sex" -> "female", "city" -> "Asgard", "count" -> 1))))
      counter ! ChangeSet(negative = Vector(freya))
      expectMsg(ChangeSet(negative = Vector(Map("sex" -> "female", "city" -> "Asgard", "count" -> 1))))
    }
  }

  def assertNextChangeset(key: Any, positive: Option[Any] = None, negative: Option[Any] = None): Unit = {
    val cs = receiveOne(Duration.Inf).asInstanceOf[ChangeSet]

    def assertEquals(actual: Any, expected: Any) {
      (actual, expected) match {
        case (actual: Double, expected: Double) => actual should be(expected +- 0.01)
        case (actual: Float, expected: Float) => actual should be(expected +- 0.01f)
        case _ => actual should be(expected)
      }
    }
    if (positive.isDefined) {
      assertEquals(cs.positive(0)(key), positive.get)
    }

    if (negative.isDefined) {
      assertEquals(cs.negative(0)(key), negative.get)
    }
  }

  "Sum" must {
    "sum with complex keys" in {
      val echoActor = system.actorOf(TestActors.echoActorProps)
      val counter = system.actorOf(Props(new SumNode(echoActor ! _, Vector("sex"), "height", "sum")))
      counter ! ChangeSet(positive = Vector(odin))
      assertNextChangeset(key = "sum", positive = Some(1))
      counter ! ChangeSet(positive = Vector(thor))
      assertNextChangeset(key = "sum", positive = Some(2.1f), negative = Some(1))
      counter ! ChangeSet(positive = Vector(ragnar))
      assertNextChangeset(key = "sum", positive = Some(2.9), negative = Some(2.1f))
    }
  }

}
