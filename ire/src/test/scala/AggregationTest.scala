import akka.actor.{ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActors, TestKit}
import hu.bme.mit.ire.{ChangeSet, Checker, _}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

class AggregationTest(_system: ActorSystem) extends TestKit(_system) with ImplicitSender
  with WordSpecLike with Matchers with BeforeAndAfterAll {

  def this() = this(ActorSystem("MySpec"))

  override def afterAll {
    TestKit.shutdownActorSystem(system)
  }

  val odin: TupleType = Map("city" -> "Asgard", "name" -> "Odin", "weapon" -> "Gungnir", "sex" -> "male")
  val thor: TupleType = Map("city" -> "Asgard", "name" -> "Thor", "weapon" -> "Mjölnir", "sex" -> "male")
  val freya: TupleType = Map("city" -> "Asgard", "name" -> "Freya", "weapon" -> "Mjölnir", "sex" -> "female")
  val geirröd: TupleType = Map("city" -> "Jötunheimr", "name" -> "Geirröd ", "sex" -> "male")

  "Count" must {
    "count with complex keys" in {

      val echoActor = system.actorOf(TestActors.echoActorProps)
      val counter = system.actorOf(Props(new Counter(echoActor ! _, Vector("city", "sex"), "count")))
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
}
